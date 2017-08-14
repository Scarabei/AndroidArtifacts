package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.zzv;

public final class zzbo {
   public static Object zzu(Object var0) {
      if (var0 == null) {
         throw new NullPointerException("null reference");
      } else {
         return var0;
      }
   }

   public static String zzcF(String var0) {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("Given String is empty or null");
      } else {
         return var0;
      }
   }

   public static String zzh(String var0, Object var1) {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException(String.valueOf(var1));
      } else {
         return var0;
      }
   }

   public static Object zzb(Object var0, Object var1) {
      if (var0 == null) {
         throw new NullPointerException(String.valueOf(var1));
      } else {
         return var0;
      }
   }

   public static long zza(long var0, Object var2) {
      if (var0 == 0L) {
         throw new IllegalArgumentException(String.valueOf(var2));
      } else {
         return var0;
      }
   }

   public static void zzae(boolean var0) {
      if (!var0) {
         throw new IllegalStateException();
      }
   }

   public static void zza(boolean var0, Object var1) {
      if (!var0) {
         throw new IllegalStateException(String.valueOf(var1));
      }
   }

   public static void zza(boolean var0, String var1, Object... var2) {
      if (!var0) {
         throw new IllegalStateException(String.format(var1, var2));
      }
   }

   public static void zzb(boolean var0, Object var1) {
      if (!var0) {
         throw new IllegalArgumentException(String.valueOf(var1));
      }
   }

   public static void zzb(boolean var0, String var1, Object... var2) {
      if (!var0) {
         throw new IllegalArgumentException(String.format(var1, var2));
      }
   }

   public static void zzaf(boolean var0) {
      if (!var0) {
         throw new IllegalArgumentException();
      }
   }

   public static void zzcz(String var0) {
      if (!zzv.zzS()) {
         throw new IllegalStateException(var0);
      }
   }

   public static void zzcG(String var0) {
      if (zzv.zzS()) {
         throw new IllegalStateException(var0);
      }
   }

   public static void zza(Handler var0) {
      if (Looper.myLooper() != var0.getLooper()) {
         throw new IllegalStateException("Must be called on the handler thread");
      }
   }
}
