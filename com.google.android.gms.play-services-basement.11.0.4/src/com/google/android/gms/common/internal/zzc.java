package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

public final class zzc {
   public static void zzr(Object var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("null reference");
      }
   }

   public static void zzae(boolean var0) {
      if (!var0) {
         throw new IllegalStateException();
      }
   }

   public static void zzcz(String var0) {
      if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
         String var1 = String.valueOf(Thread.currentThread());
         String var2 = String.valueOf(Looper.getMainLooper().getThread());
         Log.e("Asserts", (new StringBuilder(57 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("checkMainThread: current thread ").append(var1).append(" IS NOT the main thread ").append(var2).append("!").toString());
         throw new IllegalStateException(var0);
      }
   }
}
