package android.support.test.internal.runner.intent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.runner.intent.IntentCallback;
import android.support.test.runner.intent.IntentMonitor;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class IntentMonitorImpl implements IntentMonitor {
   private static final String TAG = "IntentMonitorImpl";
   List mCallbacks = Collections.synchronizedList(new ArrayList());

   public void addIntentCallback(@NonNull IntentCallback callback) {
      if (null == callback) {
         throw new NullPointerException("callback cannot be null!");
      } else {
         boolean needsAdd = true;
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            IntentCallback storedCallback = (IntentCallback)((WeakReference)refIter.next()).get();
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

   public void removeIntentCallback(@NonNull IntentCallback callback) {
      if (null == callback) {
         throw new NullPointerException("callback cannot be null!");
      } else {
         Iterator refIter = this.mCallbacks.iterator();

         while(refIter.hasNext()) {
            IntentCallback storedCallback = (IntentCallback)((WeakReference)refIter.next()).get();
            if (null == storedCallback) {
               refIter.remove();
            } else if (storedCallback == callback) {
               refIter.remove();
            }
         }

      }
   }

   public void signalIntent(Intent intent) {
      Iterator refIter = this.mCallbacks.iterator();

      while(refIter.hasNext()) {
         IntentCallback callback = (IntentCallback)((WeakReference)refIter.next()).get();
         if (null == callback) {
            refIter.remove();
         } else {
            try {
               callback.onIntentSent(new Intent(intent));
            } catch (RuntimeException var5) {
               Log.e("IntentMonitorImpl", String.format("Callback threw exception! (callback: %s intent: %s)", callback, intent), var5);
            }
         }
      }

   }
}
