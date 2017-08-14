package com.google.android.gms.internal;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import java.util.concurrent.Callable;

public final class zzcag {
   public static Object zzb(Callable var0) throws Exception {
      ThreadPolicy var1 = StrictMode.getThreadPolicy();

      Object var2;
      try {
         StrictMode.setThreadPolicy(ThreadPolicy.LAX);
         var2 = var0.call();
      } finally {
         StrictMode.setThreadPolicy(var1);
      }

      return var2;
   }
}
