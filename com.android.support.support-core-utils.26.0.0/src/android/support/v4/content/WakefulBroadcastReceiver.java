package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;

/** @deprecated */
@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
   private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
   private static final SparseArray sActiveWakeLocks = new SparseArray();
   private static int mNextId = 1;

   public static ComponentName startWakefulService(Context context, Intent intent) {
      SparseArray var2 = sActiveWakeLocks;
      synchronized(sActiveWakeLocks) {
         int id = mNextId++;
         if (mNextId <= 0) {
            mNextId = 1;
         }

         intent.putExtra("android.support.content.wakelockid", id);
         ComponentName comp = context.startService(intent);
         if (comp == null) {
            return null;
         } else {
            PowerManager pm = (PowerManager)context.getSystemService("power");
            WakeLock wl = pm.newWakeLock(1, "wake:" + comp.flattenToShortString());
            wl.setReferenceCounted(false);
            wl.acquire(60000L);
            sActiveWakeLocks.put(id, wl);
            return comp;
         }
      }
   }

   public static boolean completeWakefulIntent(Intent intent) {
      int id = intent.getIntExtra("android.support.content.wakelockid", 0);
      if (id == 0) {
         return false;
      } else {
         SparseArray var2 = sActiveWakeLocks;
         synchronized(sActiveWakeLocks) {
            WakeLock wl = (WakeLock)sActiveWakeLocks.get(id);
            if (wl != null) {
               wl.release();
               sActiveWakeLocks.remove(id);
               return true;
            } else {
               Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + id);
               return true;
            }
         }
      }
   }
}
