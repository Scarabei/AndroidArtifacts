package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.app.job.JobInfo.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
   static final String TAG = "JobIntentService";
   static final boolean DEBUG = false;
   JobIntentService.CompatJobEngine mJobImpl;
   JobIntentService.WorkEnqueuer mCompatWorkEnqueuer;
   JobIntentService.CommandProcessor mCurProcessor;
   boolean mInterruptIfStopped = false;
   boolean mStopped = false;
   final ArrayList mCompatQueue;
   static final Object sLock = new Object();
   static final HashMap sClassWorkEnqueuer = new HashMap();

   public JobIntentService() {
      if (VERSION.SDK_INT >= 26) {
         this.mCompatQueue = null;
      } else {
         this.mCompatQueue = new ArrayList();
      }

   }

   public void onCreate() {
      super.onCreate();
      if (VERSION.SDK_INT >= 26) {
         this.mJobImpl = new JobIntentService.JobServiceEngineImpl(this);
         this.mCompatWorkEnqueuer = null;
      } else {
         this.mJobImpl = null;
         ComponentName cn = new ComponentName(this, this.getClass());
         this.mCompatWorkEnqueuer = getWorkEnqueuer(this, cn, false, 0);
         this.mCompatWorkEnqueuer.serviceCreated();
      }

   }

   public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
      if (this.mCompatQueue != null) {
         this.mCompatWorkEnqueuer.serviceStartReceived();
         ArrayList var4 = this.mCompatQueue;
         synchronized(this.mCompatQueue) {
            this.mCompatQueue.add(new JobIntentService.CompatWorkItem(intent != null ? intent : new Intent(), startId));
            this.ensureProcessorRunningLocked();
            return 3;
         }
      } else {
         return 2;
      }
   }

   public IBinder onBind(@NonNull Intent intent) {
      if (this.mJobImpl != null) {
         IBinder engine = this.mJobImpl.compatGetBinder();
         return engine;
      } else {
         return null;
      }
   }

   public void onDestroy() {
      super.onDestroy();
      if (this.mCompatWorkEnqueuer != null) {
         this.mCompatWorkEnqueuer.serviceDestroyed();
      }

   }

   public static void enqueueWork(@NonNull Context context, @NonNull Class cls, int jobId, @NonNull Intent work) {
      enqueueWork(context, new ComponentName(context, cls), jobId, work);
   }

   public static void enqueueWork(@NonNull Context context, @NonNull ComponentName component, int jobId, @NonNull Intent work) {
      if (work == null) {
         throw new IllegalArgumentException("work must not be null");
      } else {
         Object var4 = sLock;
         synchronized(sLock) {
            JobIntentService.WorkEnqueuer we = getWorkEnqueuer(context, component, true, jobId);
            we.ensureJobId(jobId);
            we.enqueueWork(work);
         }
      }
   }

   static JobIntentService.WorkEnqueuer getWorkEnqueuer(Context context, ComponentName cn, boolean hasJobId, int jobId) {
      JobIntentService.WorkEnqueuer we = (JobIntentService.WorkEnqueuer)sClassWorkEnqueuer.get(cn);
      if (we == null) {
         if (VERSION.SDK_INT >= 26) {
            if (!hasJobId) {
               throw new IllegalArgumentException("Can't be here without a job id");
            }

            we = new JobIntentService.JobWorkEnqueuer(context, cn, jobId);
         } else {
            we = new JobIntentService.CompatWorkEnqueuer(context, cn);
         }

         sClassWorkEnqueuer.put(cn, we);
      }

      return (JobIntentService.WorkEnqueuer)we;
   }

   protected abstract void onHandleWork(@NonNull Intent var1);

   public void setInterruptIfStopped(boolean interruptIfStopped) {
      this.mInterruptIfStopped = interruptIfStopped;
   }

   public boolean isStopped() {
      return this.mStopped;
   }

   public boolean onStopCurrentWork() {
      return true;
   }

   boolean doStopCurrentWork() {
      if (this.mCurProcessor != null) {
         this.mCurProcessor.cancel(this.mInterruptIfStopped);
      }

      this.mStopped = true;
      return this.onStopCurrentWork();
   }

   void ensureProcessorRunningLocked() {
      if (this.mCurProcessor == null) {
         this.mCurProcessor = new JobIntentService.CommandProcessor();
         this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      }

   }

   void processorFinished() {
      if (this.mCompatQueue != null) {
         ArrayList var1 = this.mCompatQueue;
         synchronized(this.mCompatQueue) {
            this.mCurProcessor = null;
            if (this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
               this.ensureProcessorRunningLocked();
            }
         }
      }

   }

   JobIntentService.GenericWorkItem dequeueWork() {
      if (this.mJobImpl != null) {
         return this.mJobImpl.dequeueWork();
      } else {
         ArrayList var1 = this.mCompatQueue;
         synchronized(this.mCompatQueue) {
            return this.mCompatQueue.size() > 0 ? (JobIntentService.GenericWorkItem)this.mCompatQueue.remove(0) : null;
         }
      }
   }

   final class CommandProcessor extends AsyncTask {
      protected Void doInBackground(Void... params) {
         JobIntentService.GenericWorkItem work;
         while((work = JobIntentService.this.dequeueWork()) != null) {
            JobIntentService.this.onHandleWork(work.getIntent());
            work.complete();
         }

         return null;
      }

      protected void onCancelled(Void aVoid) {
         JobIntentService.this.processorFinished();
      }

      protected void onPostExecute(Void aVoid) {
         JobIntentService.this.processorFinished();
      }
   }

   final class CompatWorkItem implements JobIntentService.GenericWorkItem {
      final Intent mIntent;
      final int mStartId;

      CompatWorkItem(Intent intent, int startId) {
         this.mIntent = intent;
         this.mStartId = startId;
      }

      public Intent getIntent() {
         return this.mIntent;
      }

      public void complete() {
         JobIntentService.this.stopSelf(this.mStartId);
      }
   }

   interface GenericWorkItem {
      Intent getIntent();

      void complete();
   }

   @RequiresApi(26)
   static final class JobWorkEnqueuer extends JobIntentService.WorkEnqueuer {
      private final JobInfo mJobInfo;
      private final JobScheduler mJobScheduler;

      JobWorkEnqueuer(Context context, ComponentName cn, int jobId) {
         super(context, cn);
         this.ensureJobId(jobId);
         Builder b = new Builder(jobId, this.mComponentName);
         this.mJobInfo = b.setOverrideDeadline(0L).build();
         this.mJobScheduler = (JobScheduler)context.getApplicationContext().getSystemService("jobscheduler");
      }

      void enqueueWork(Intent work) {
         this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(work));
      }
   }

   @RequiresApi(26)
   static final class JobServiceEngineImpl extends JobServiceEngine implements JobIntentService.CompatJobEngine {
      static final String TAG = "JobServiceEngineImpl";
      static final boolean DEBUG = false;
      final JobIntentService mService;
      final Object mLock = new Object();
      JobParameters mParams;

      JobServiceEngineImpl(JobIntentService service) {
         super(service);
         this.mService = service;
      }

      public IBinder compatGetBinder() {
         return this.getBinder();
      }

      public boolean onStartJob(JobParameters params) {
         this.mParams = params;
         this.mService.ensureProcessorRunningLocked();
         return true;
      }

      public boolean onStopJob(JobParameters params) {
         boolean result = this.mService.doStopCurrentWork();
         Object var3 = this.mLock;
         synchronized(this.mLock) {
            this.mParams = null;
            return result;
         }
      }

      public JobIntentService.GenericWorkItem dequeueWork() {
         Object var2 = this.mLock;
         JobWorkItem work;
         synchronized(this.mLock) {
            if (this.mParams == null) {
               return null;
            }

            work = this.mParams.dequeueWork();
         }

         if (work != null) {
            work.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
            return new JobIntentService.JobServiceEngineImpl.WrapperWorkItem(work);
         } else {
            return null;
         }
      }

      final class WrapperWorkItem implements JobIntentService.GenericWorkItem {
         final JobWorkItem mJobWork;

         WrapperWorkItem(JobWorkItem jobWork) {
            this.mJobWork = jobWork;
         }

         public Intent getIntent() {
            return this.mJobWork.getIntent();
         }

         public void complete() {
            Object var1 = JobServiceEngineImpl.this.mLock;
            synchronized(JobServiceEngineImpl.this.mLock) {
               if (JobServiceEngineImpl.this.mParams != null) {
                  JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
               }

            }
         }
      }
   }

   static final class CompatWorkEnqueuer extends JobIntentService.WorkEnqueuer {
      private final Context mContext;
      private final WakeLock mLaunchWakeLock;
      private final WakeLock mRunWakeLock;
      boolean mLaunchingService;
      boolean mServiceRunning;

      CompatWorkEnqueuer(Context context, ComponentName cn) {
         super(context, cn);
         this.mContext = context.getApplicationContext();
         PowerManager pm = (PowerManager)context.getSystemService("power");
         this.mLaunchWakeLock = pm.newWakeLock(1, cn.getClassName() + ":launch");
         this.mLaunchWakeLock.setReferenceCounted(false);
         this.mRunWakeLock = pm.newWakeLock(1, cn.getClassName() + ":run");
         this.mRunWakeLock.setReferenceCounted(false);
      }

      void enqueueWork(Intent work) {
         Intent intent = new Intent(work);
         intent.setComponent(this.mComponentName);
         if (this.mContext.startService(intent) != null) {
            synchronized(this) {
               if (!this.mLaunchingService) {
                  this.mLaunchingService = true;
                  if (!this.mServiceRunning) {
                     this.mLaunchWakeLock.acquire(60000L);
                  }
               }
            }
         }

      }

      public void serviceCreated() {
         synchronized(this) {
            if (!this.mServiceRunning) {
               this.mServiceRunning = true;
               this.mRunWakeLock.acquire();
               this.mLaunchWakeLock.release();
            }

         }
      }

      public void serviceStartReceived() {
         synchronized(this) {
            this.mLaunchingService = false;
         }
      }

      public void serviceDestroyed() {
         synchronized(this) {
            if (this.mLaunchingService) {
               this.mLaunchWakeLock.acquire(60000L);
            }

            this.mServiceRunning = false;
            this.mRunWakeLock.release();
         }
      }
   }

   interface CompatJobEngine {
      IBinder compatGetBinder();

      JobIntentService.GenericWorkItem dequeueWork();
   }

   abstract static class WorkEnqueuer {
      final ComponentName mComponentName;
      boolean mHasJobId;
      int mJobId;

      WorkEnqueuer(Context context, ComponentName cn) {
         this.mComponentName = cn;
      }

      void ensureJobId(int jobId) {
         if (!this.mHasJobId) {
            this.mHasJobId = true;
            this.mJobId = jobId;
         } else if (this.mJobId != jobId) {
            throw new IllegalArgumentException("Given job ID " + jobId + " is different than previous " + this.mJobId);
         }

      }

      abstract void enqueueWork(Intent var1);

      public void serviceCreated() {
      }

      public void serviceStartReceived() {
      }

      public void serviceDestroyed() {
      }
   }
}
