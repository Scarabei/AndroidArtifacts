package android.support.test.runner;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation.ActivityResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Build.VERSION;
import android.os.MessageQueue.IdleHandler;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.runner.hidden.ExposedInstrumentationApi;
import android.support.test.internal.runner.intent.IntentMonitorImpl;
import android.support.test.internal.runner.lifecycle.ActivityLifecycleMonitorImpl;
import android.support.test.internal.runner.lifecycle.ApplicationLifecycleMonitorImpl;
import android.support.test.internal.util.Checks;
import android.support.test.runner.intent.IntentMonitorRegistry;
import android.support.test.runner.intent.IntentStubberRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.ApplicationStage;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MonitoringInstrumentation extends ExposedInstrumentationApi {
   private static final long MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
   private static final long MILLIS_TO_POLL_FOR_ACTIVITY_STOP;
   private static final String TAG = "MonitoringInstrumentation";
   private static final int START_ACTIVITY_TIMEOUT_SECONDS = 45;
   private ActivityLifecycleMonitorImpl mLifecycleMonitor = new ActivityLifecycleMonitorImpl();
   private ApplicationLifecycleMonitorImpl mApplicationMonitor = new ApplicationLifecycleMonitorImpl();
   private IntentMonitorImpl mIntentMonitor = new IntentMonitorImpl();
   private ExecutorService mExecutorService;
   private Handler mHandlerForMainLooper;
   private AtomicBoolean mAnActivityHasBeenLaunched = new AtomicBoolean(false);
   private AtomicLong mLastIdleTime = new AtomicLong(0L);
   private AtomicInteger mStartedActivityCounter = new AtomicInteger(0);
   private IdleHandler mIdleHandler = new IdleHandler() {
      public boolean queueIdle() {
         MonitoringInstrumentation.this.mLastIdleTime.set(System.currentTimeMillis());
         return true;
      }
   };
   private volatile boolean mFinished = false;

   public void onCreate(Bundle arguments) {
      Log.i("MonitoringInstrumentation", "Instrumentation Started!");
      this.logUncaughtExceptions();
      this.installMultidex();
      InstrumentationRegistry.registerInstance(this, arguments);
      ActivityLifecycleMonitorRegistry.registerInstance(this.mLifecycleMonitor);
      ApplicationLifecycleMonitorRegistry.registerInstance(this.mApplicationMonitor);
      IntentMonitorRegistry.registerInstance(this.mIntentMonitor);
      this.mHandlerForMainLooper = new Handler(Looper.getMainLooper());
      int corePoolSize = false;
      long keepAliveTime = 0L;
      this.mExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory() {
         public Thread newThread(Runnable runnable) {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setName(MonitoringInstrumentation.class.getSimpleName());
            return thread;
         }
      });
      Looper.myQueue().addIdleHandler(this.mIdleHandler);
      super.onCreate(arguments);
      this.specifyDexMakerCacheProperty();
      this.setupDexmakerClassloader();
   }

   private final void installMultidex() {
      if (VERSION.SDK_INT <= 15) {
         try {
            Class multidex = Class.forName("android.support.multidex.MultiDex");
            Method install = multidex.getDeclaredMethod("install", Context.class);
            install.invoke((Object)null, this.getTargetContext());
         } catch (ClassNotFoundException var3) {
            Log.i("MonitoringInstrumentation", "No multidex.");
         } catch (NoSuchMethodException var4) {
            Log.i("MonitoringInstrumentation", "No multidex.");
         } catch (InvocationTargetException var5) {
            throw new RuntimeException("multidex is available at runtime, but calling it failed.", var5);
         } catch (IllegalAccessException var6) {
            throw new RuntimeException("multidex is available at runtime, but calling it failed.", var6);
         }
      }

   }

   protected final void specifyDexMakerCacheProperty() {
      File dexCache = this.getTargetContext().getDir("dxmaker_cache", 0);
      System.getProperties().put("dexmaker.dexcache", dexCache.getAbsolutePath());
   }

   private void setupDexmakerClassloader() {
      ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
      ClassLoader newClassLoader = this.getClass().getClassLoader();
      Log.i("MonitoringInstrumentation", String.format("Setting context classloader to '%s', Original: '%s'", newClassLoader.toString(), originalClassLoader.toString()));
      Thread.currentThread().setContextClassLoader(newClassLoader);
   }

   private void logUncaughtExceptions() {
      final UncaughtExceptionHandler standardHandler = Thread.currentThread().getUncaughtExceptionHandler();
      Thread.currentThread().setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
         public void uncaughtException(Thread t, Throwable e) {
            MonitoringInstrumentation.this.onException(t, e);
            if (null != standardHandler) {
               standardHandler.uncaughtException(t, e);
            }

         }
      });
   }

   public void onStart() {
      super.onStart();
      this.runOnMainSync(new Runnable() {
         public void run() {
            MonitoringInstrumentation.this.tryLoadingJsBridge();
         }
      });
      this.waitForIdleSync();
   }

   public void finish(int resultCode, Bundle results) {
      if (this.mFinished) {
         Log.w("MonitoringInstrumentation", "finish called 2x!");
      } else {
         this.mFinished = true;
         this.mHandlerForMainLooper.post(new MonitoringInstrumentation.ActivityFinisher());
         long startTime = System.currentTimeMillis();
         this.waitForActivitiesToComplete();
         long endTime = System.currentTimeMillis();
         Log.i("MonitoringInstrumentation", String.format("waitForActivitiesToComplete() took: %sms", endTime - startTime));
         ActivityLifecycleMonitorRegistry.registerInstance((ActivityLifecycleMonitor)null);
         super.finish(resultCode, results);
      }
   }

   protected void waitForActivitiesToComplete() {
      long endTime = System.currentTimeMillis() + MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
      int currentActivityCount = this.mStartedActivityCounter.get();

      while(currentActivityCount > 0 && System.currentTimeMillis() < endTime) {
         try {
            Log.i("MonitoringInstrumentation", "Unstopped activity count: " + currentActivityCount);
            Thread.sleep(MILLIS_TO_POLL_FOR_ACTIVITY_STOP);
            currentActivityCount = this.mStartedActivityCounter.get();
         } catch (InterruptedException var5) {
            Log.i("MonitoringInstrumentation", "Abandoning activity wait due to interruption.", var5);
            break;
         }
      }

      if (currentActivityCount > 0) {
         this.dumpThreadStateToOutputs("ThreadState-unstopped.txt");
         Log.w("MonitoringInstrumentation", String.format("Still %s activities active after waiting %s ms.", currentActivityCount, MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP));
      }

   }

   public void onDestroy() {
      Log.i("MonitoringInstrumentation", "Instrumentation Finished!");
      Looper.myQueue().removeIdleHandler(this.mIdleHandler);
      super.onDestroy();
   }

   public void callApplicationOnCreate(Application app) {
      this.mApplicationMonitor.signalLifecycleChange(app, ApplicationStage.PRE_ON_CREATE);
      super.callApplicationOnCreate(app);
      this.mApplicationMonitor.signalLifecycleChange(app, ApplicationStage.CREATED);
   }

   public Activity startActivitySync(final Intent intent) {
      Checks.checkNotMainThread();
      long lastIdleTimeBeforeLaunch = this.mLastIdleTime.get();
      if (this.mAnActivityHasBeenLaunched.compareAndSet(false, true)) {
         intent.addFlags(67108864);
      }

      Future startedActivity = this.mExecutorService.submit(new Callable() {
         public Activity call() {
            return MonitoringInstrumentation.super.startActivitySync(intent);
         }
      });

      try {
         return (Activity)startedActivity.get(45L, TimeUnit.SECONDS);
      } catch (TimeoutException var6) {
         startedActivity.cancel(true);
         this.dumpThreadStateToOutputs("ThreadState-startActivityTimeout.txt");
         throw new RuntimeException(String.format("Could not launch intent %s within %s seconds. Perhaps the main thread has not gone idle within a reasonable amount of time? There could be an animation or something constantly repainting the screen. Or the activity is doing network calls on creation? See the threaddump logs. For your reference the last time the event queue was idle before your activity launch request was %s and now the last time the queue went idle was: %s. If these numbers are the same your activity might be hogging the event queue.", intent, Integer.valueOf(45), lastIdleTimeBeforeLaunch, this.mLastIdleTime.get()));
      } catch (ExecutionException var7) {
         throw new RuntimeException("Could not launch activity", var7.getCause());
      } catch (InterruptedException var8) {
         Thread.currentThread().interrupt();
         throw new RuntimeException("interrupted", var8);
      }
   }

   public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode) {
      Log.d("MonitoringInstrumentation", "execStartActivity(context, ibinder, ibinder, activity, intent, int)");
      this.mIntentMonitor.signalIntent(intent);
      ActivityResult ar = this.stubResultFor(intent);
      if (ar != null) {
         Log.i("MonitoringInstrumentation", String.format("Stubbing intent %s", intent));
         return ar;
      } else {
         return super.execStartActivity(who, contextThread, token, target, intent, requestCode);
      }
   }

   public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
      Log.d("MonitoringInstrumentation", "execStartActivity(context, ibinder, ibinder, activity, intent, int, bundle");
      this.mIntentMonitor.signalIntent(intent);
      ActivityResult ar = this.stubResultFor(intent);
      if (ar != null) {
         Log.i("MonitoringInstrumentation", String.format("Stubbing intent %s", intent));
         return ar;
      } else {
         return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
      }
   }

   public void execStartActivities(Context who, IBinder contextThread, IBinder token, Activity target, Intent[] intents, Bundle options) {
      Log.d("MonitoringInstrumentation", "execStartActivities(context, ibinder, ibinder, activity, intent[], bundle)");
      int requestCode = -1;
      Intent[] arr$ = intents;
      int len$ = intents.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Intent intent = arr$[i$];
         this.execStartActivity(who, contextThread, token, (Activity)target, intent, requestCode, options);
      }

   }

   public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment target, Intent intent, int requestCode, Bundle options) {
      Log.d("MonitoringInstrumentation", "execStartActivity(context, IBinder, IBinder, Fragment, Intent, int, Bundle)");
      this.mIntentMonitor.signalIntent(intent);
      ActivityResult ar = this.stubResultFor(intent);
      if (ar != null) {
         Log.i("MonitoringInstrumentation", String.format("Stubbing intent %s", intent));
         return ar;
      } else {
         return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
      }
   }

   private ActivityResult stubResultFor(Intent intent) {
      if (IntentStubberRegistry.isLoaded()) {
         if (Looper.myLooper() != Looper.getMainLooper()) {
            FutureTask task = new FutureTask(new MonitoringInstrumentation.StubResultCallable(intent));
            this.runOnMainSync(task);

            try {
               return (ActivityResult)task.get();
            } catch (ExecutionException var4) {
               throw new RuntimeException(String.format("Could not retrieve stub result for intent %s", intent), var4);
            } catch (InterruptedException var5) {
               Thread.currentThread().interrupt();
               throw new RuntimeException(var5);
            }
         } else {
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(intent);
         }
      } else {
         return null;
      }
   }

   public boolean onException(Object obj, Throwable e) {
      String error = String.format("Exception encountered by: %s. Dumping thread state to outputs and pining for the fjords.", obj);
      Log.e("MonitoringInstrumentation", error, e);
      this.dumpThreadStateToOutputs("ThreadState-onException.txt");
      Log.e("MonitoringInstrumentation", "Dying now...");
      return super.onException(obj, e);
   }

   protected void dumpThreadStateToOutputs(String outputFileName) {
      String threadState = this.getThreadState();
      Log.e("THREAD_STATE", threadState);
   }

   protected String getThreadState() {
      Set threads = Thread.getAllStackTraces().entrySet();
      StringBuilder threadState = new StringBuilder();
      Iterator i$ = threads.iterator();

      while(i$.hasNext()) {
         Entry threadAndStack = (Entry)i$.next();
         StringBuilder threadMessage = (new StringBuilder("  ")).append(threadAndStack.getKey());
         threadMessage.append("\n");
         StackTraceElement[] arr$ = (StackTraceElement[])threadAndStack.getValue();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            StackTraceElement ste = arr$[i$];
            threadMessage.append("    ");
            threadMessage.append(ste.toString());
            threadMessage.append("\n");
         }

         threadMessage.append("\n");
         threadState.append(threadMessage.toString());
      }

      return threadState.toString();
   }

   public void callActivityOnDestroy(Activity activity) {
      super.callActivityOnDestroy(activity);
      this.mLifecycleMonitor.signalLifecycleChange(Stage.DESTROYED, activity);
   }

   public void callActivityOnRestart(Activity activity) {
      super.callActivityOnRestart(activity);
      this.mLifecycleMonitor.signalLifecycleChange(Stage.RESTARTED, activity);
   }

   public void callActivityOnCreate(Activity activity, Bundle bundle) {
      this.mLifecycleMonitor.signalLifecycleChange(Stage.PRE_ON_CREATE, activity);
      super.callActivityOnCreate(activity, bundle);
      this.mLifecycleMonitor.signalLifecycleChange(Stage.CREATED, activity);
   }

   public void callActivityOnStart(Activity activity) {
      this.mStartedActivityCounter.incrementAndGet();

      try {
         super.callActivityOnStart(activity);
         this.mLifecycleMonitor.signalLifecycleChange(Stage.STARTED, activity);
      } catch (RuntimeException var3) {
         this.mStartedActivityCounter.decrementAndGet();
         throw var3;
      }
   }

   public void callActivityOnStop(Activity activity) {
      try {
         super.callActivityOnStop(activity);
         this.mLifecycleMonitor.signalLifecycleChange(Stage.STOPPED, activity);
      } finally {
         this.mStartedActivityCounter.decrementAndGet();
      }

   }

   public void callActivityOnResume(Activity activity) {
      super.callActivityOnResume(activity);
      this.mLifecycleMonitor.signalLifecycleChange(Stage.RESUMED, activity);
   }

   public void callActivityOnPause(Activity activity) {
      super.callActivityOnPause(activity);
      this.mLifecycleMonitor.signalLifecycleChange(Stage.PAUSED, activity);
   }

   public Activity newActivity(Class clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws InstantiationException, IllegalAccessException {
      String activityClassPackageName = clazz.getPackage().getName();
      String contextPackageName = context.getPackageName();
      ComponentName intentComponentName = intent.getComponent();
      if (!contextPackageName.equals(intentComponentName.getPackageName()) && activityClassPackageName.equals(intentComponentName.getPackageName())) {
         intent.setComponent(new ComponentName(contextPackageName, intentComponentName.getClassName()));
      }

      return super.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
   }

   private void tryLoadingJsBridge() {
      Checks.checkMainThread();

      try {
         Class jsBridge = Class.forName("android.support.test.espresso.web.bridge.JavaScriptBridge");
         Method install = jsBridge.getDeclaredMethod("installBridge");
         install.invoke((Object)null);
      } catch (ClassNotFoundException var3) {
         Log.i("MonitoringInstrumentation", "No JSBridge.");
      } catch (NoSuchMethodException var4) {
         Log.i("MonitoringInstrumentation", "No JSBridge.");
      } catch (InvocationTargetException var5) {
         throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", var5);
      } catch (IllegalAccessException var6) {
         throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", var6);
      }

   }

   static {
      MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP = TimeUnit.SECONDS.toMillis(2L);
      MILLIS_TO_POLL_FOR_ACTIVITY_STOP = MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP / 40L;
   }

   public class ActivityFinisher implements Runnable {
      public void run() {
         List activities = new ArrayList();
         Iterator i$ = EnumSet.range(Stage.CREATED, Stage.STOPPED).iterator();

         while(i$.hasNext()) {
            Stage s = (Stage)i$.next();
            activities.addAll(MonitoringInstrumentation.this.mLifecycleMonitor.getActivitiesInStage(s));
         }

         Log.i("MonitoringInstrumentation", "Activities that are still in CREATED to STOPPED: " + activities.size());
         i$ = activities.iterator();

         while(i$.hasNext()) {
            Activity activity = (Activity)i$.next();
            if (!activity.isFinishing()) {
               try {
                  Log.i("MonitoringInstrumentation", "Finishing activity: " + activity);
                  activity.finish();
               } catch (RuntimeException var5) {
                  Log.e("MonitoringInstrumentation", "Failed to finish activity.", var5);
               }
            }
         }

      }
   }

   private static class StubResultCallable implements Callable {
      private final Intent mIntent;

      StubResultCallable(Intent intent) {
         this.mIntent = intent;
      }

      public ActivityResult call() {
         return IntentStubberRegistry.getInstance().getActivityResultForIntent(this.mIntent);
      }
   }
}
