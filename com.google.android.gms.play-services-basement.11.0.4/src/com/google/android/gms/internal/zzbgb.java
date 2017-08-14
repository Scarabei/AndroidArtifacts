package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.util.Log;

public final class zzbgb {
   private final String mTag;
   private final String zzaIc;
   private final com.google.android.gms.common.internal.zzaj zzaIA;
   private final int zzagX;

   private static String zzb(String... var0) {
      if (var0.length == 0) {
         return "";
      } else {
         StringBuilder var1;
         (var1 = new StringBuilder()).append('[');
         String[] var2 = var0;
         int var3 = var0.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (var1.length() > 1) {
               var1.append(",");
            }

            var1.append(var5);
         }

         var1.append(']').append(' ');
         return var1.toString();
      }
   }

   public zzbgb(String var1, String... var2) {
      this(var1, zzb(var2));
   }

   private zzbgb(String var1, String var2) {
      this.zzaIc = var2;
      this.mTag = var1;
      this.zzaIA = new com.google.android.gms.common.internal.zzaj(var1);
      this.zzagX = this.getLogLevel();
   }

   private final int getLogLevel() {
      int var1;
      for(var1 = 2; 7 >= var1 && !Log.isLoggable(this.mTag, var1); ++var1) {
         ;
      }

      return var1;
   }

   private final boolean zzz(int var1) {
      return this.zzagX <= var1;
   }

   public final void zza(String var1, @Nullable Object... var2) {
      if (this.zzz(2)) {
         Log.v(this.mTag, this.format(var1, var2));
      }

   }

   public final void zzb(String var1, @Nullable Object... var2) {
      if (this.zzz(3)) {
         Log.d(this.mTag, this.format(var1, var2));
      }

   }

   public final void zze(String var1, @Nullable Object... var2) {
      Log.i(this.mTag, this.format(var1, var2));
   }

   public final void zzf(String var1, @Nullable Object... var2) {
      Log.w(this.mTag, this.format(var1, var2));
   }

   public final void zza(String var1, Throwable var2, @Nullable Object... var3) {
      Log.wtf(this.mTag, this.format(var1, var3), var2);
   }

   public final void zzd(Throwable var1) {
      Log.wtf(this.mTag, var1);
   }

   private final String format(String var1, @Nullable Object... var2) {
      if (var2 != null && var2.length > 0) {
         var1 = String.format(var1, var2);
      }

      return this.zzaIc.concat(var1);
   }
}
