package android.support.test.internal.runner.lifecycle;

import android.app.Application;
import android.support.test.internal.util.Checks;
import android.support.test.runner.lifecycle.ApplicationLifecycleCallback;
import android.support.test.runner.lifecycle.ApplicationLifecycleMonitor;
import android.support.test.runner.lifecycle.ApplicationStage;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicationLifecycleMonitorImpl implements ApplicationLifecycleMonitor {
   private static final String TAG = "ApplicationLifecycleMonitorImpl";
   private final List mCallbacks = new ArrayList();

   public void addLifecycleCallback(ApplicationLifecycleCallback callback) {
      Checks.checkNotNull(callback);
      List var2 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         boolean needsAdd = true;
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ApplicationLifecycleCallback storedCallback = (ApplicationLifecycleCallback)((WeakReference)refIter.next()).get();
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

   public void removeLifecycleCallback(ApplicationLifecycleCallback callback) {
      Checks.checkNotNull(callback);
      List var2 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ApplicationLifecycleCallback storedCallback = (ApplicationLifecycleCallback)((WeakReference)refIter.next()).get();
            if (null == storedCallback) {
               refIter.remove();
            } else if (storedCallback == callback) {
               refIter.remove();
            }
         }

      }
   }

   public void signalLifecycleChange(Application app, ApplicationStage stage) {
      List var3 = this.mCallbacks;
      synchronized(this.mCallbacks) {
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            ApplicationLifecycleCallback callback = (ApplicationLifecycleCallback)((WeakReference)refIter.next()).get();
            if (null == callback) {
               refIter.remove();
            } else {
               try {
                  Log.d("ApplicationLifecycleMonitorImpl", "running callback: " + callback);
                  callback.onApplicationLifecycleChanged(app, stage);
                  Log.d("ApplicationLifecycleMonitorImpl", "callback completes: " + callback);
               } catch (RuntimeException var8) {
                  Log.e("ApplicationLifecycleMonitorImpl", String.format("Callback threw exception! (callback: %s stage: %s)", callback, stage), var8);
               }
            }
         }

      }
   }
}
