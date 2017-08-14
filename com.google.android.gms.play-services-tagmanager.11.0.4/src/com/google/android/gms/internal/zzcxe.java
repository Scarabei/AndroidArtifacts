package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class zzcxe {
   static class zza {
      private static volatile ExecutorService zzbJJ = null;

      public static ExecutorService zzbx(Context var0) {
         if (zzbJJ == null) {
            Class var1 = zzcxe.zza.class;
            synchronized(zzcxe.zza.class) {
               if (zzbJJ == null) {
                  zzbJJ = new zzcuu(var0, 1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new zzcxf());
               }
            }
         }

         return zzbJJ;
      }
   }
}
