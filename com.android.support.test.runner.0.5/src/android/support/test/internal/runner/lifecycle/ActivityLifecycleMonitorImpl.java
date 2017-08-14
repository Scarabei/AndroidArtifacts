package android.support.test.internal.runner.lifecycle;

import android.app.Activity;
import android.os.Looper;
import android.support.test.internal.util.Checks;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ActivityLifecycleMonitorImpl implements ActivityLifecycleMonitor {
   private static final String TAG = "LifecycleMonitor";
   private final boolean mDeclawThreadCheck;
   private final List mCallbacks;
   private List mActivityStatuses;

   public ActivityLifecycleMonitorImpl() {
      this(false);
   }

   public ActivityLifecycleMonitorImpl(boolean declawThreadCheck) {
      this.mCallbacks = new ArrayList();
      this.mActivityStatuses = new ArrayList();
      this.mDeclawThreadCheck = declawThreadCheck;
   }

   public void addLifecycleCallback(ActivityLifecycleCallback callback) {
      Checks.checkNotNull(callback);
      List var2 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         boolean needsAdd = true;
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ActivityLifecycleCallback storedCallback = (ActivityLifecycleCallback)((WeakReference)refIter.next()).get();
            if (null == storedCallback) {
               refIter.remove();
            } else if (storedCallback == callback) {
               needsAdd = false;
            }
         }

         if (needsAdd) {
            this.mCallbacks.add(new WeakReference(callback));
         }

      }
   }

   public void removeLifecycleCallback(ActivityLifecycleCallback callback) {
      Checks.checkNotNull(callback);
      List var2 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ActivityLifecycleCallback storedCallback = (ActivityLifecycleCallback)((WeakReference)refIter.next()).get();
            if (null == storedCallback) {
               refIter.remove();
            } else if (storedCallback == callback) {
               refIter.remove();
            }
         }

      }
   }

   public Stage getLifecycleStageOf(Activity activity) {
      this.checkMainThread();
      Checks.checkNotNull(activity);
      Iterator statusIterator = this.mActivityStatuses.iterator();

      while(statusIterator.hasNext()) {
         ActivityLifecycleMonitorImpl.ActivityStatus status = (ActivityLifecycleMonitorImpl.ActivityStatus)statusIterator.next();
         Activity statusActivity = (Activity)status.mActivityRef.get();
         if (null == statusActivity) {
            statusIterator.remove();
         } else if (activity == statusActivity) {
            return status.mLifecycleStage;
         }
      }

      throw new IllegalArgumentException("Unknown activity: " + activity);
   }

   public Collection getActivitiesInStage(Stage stage) {
      this.checkMainThread();
      Checks.checkNotNull(stage);
      List activities = new ArrayList();
      Iterator statusIterator = this.mActivityStatuses.iterator();

      while(statusIterator.hasNext()) {
         ActivityLifecycleMonitorImpl.ActivityStatus status = (ActivityLifecycleMonitorImpl.ActivityStatus)statusIterator.next();
         Activity statusActivity = (Activity)status.mActivityRef.get();
         if (null == statusActivity) {
            statusIterator.remove();
         } else if (stage == status.mLifecycleStage) {
            activities.add(statusActivity);
         }
      }

      return activities;
   }

   public void signalLifecycleChange(Stage stage, Activity activity) {
      Log.d("LifecycleMonitor", "Lifecycle status change: " + activity + " in: " + stage);
      boolean needsAdd = true;
      Iterator statusIterator = this.mActivityStatuses.iterator();

      while(statusIterator.hasNext()) {
         ActivityLifecycleMonitorImpl.ActivityStatus status = (ActivityLifecycleMonitorImpl.ActivityStatus)statusIterator.next();
         Activity statusActivity = (Activity)status.mActivityRef.get();
         if (null == statusActivity) {
            statusIterator.remove();
         } else if (activity == statusActivity) {
            needsAdd = false;
            status.mLifecycleStage = stage;
         }
      }

      if (needsAdd) {
         this.mActivityStatuses.add(new ActivityLifecycleMonitorImpl.ActivityStatus(activity, stage));
      }

      List var12 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ActivityLifecycleCallback callback = (ActivityLifecycleCallback)((WeakReference)refIter.next()).get();
            if (null == callback) {
               refIter.remove();
            } else {
               try {
                  Log.d("LifecycleMonitor", "running callback: " + callback);
                  callback.onActivityLifecycleChanged(activity, stage);
                  Log.d("LifecycleMonitor", "callback completes: " + callback);
               } catch (RuntimeException var10) {
                  Log.e("LifecycleMonitor", String.format("Callback threw exception! (callback: %s activity: %s stage: %s)", callback, activity, stage), var10);
               }
            }
         }

      }
   }

   private void checkMainThread() {
      if (!this.mDeclawThreadCheck) {
         if (!Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Querying activity state off main thread is not allowed.");
         }
      }
   }

   private static class ActivityStatus {
      private final WeakReference mActivityRef;
      private Stage mLifecycleStage;

      ActivityStatus(Activity activity, Stage stage) {
         this.mActivityRef = new WeakReference(Checks.checkNotNull(activity));
         this.mLifecycleStage = (Stage)Checks.checkNotNull(stage);
      }
   }
}
