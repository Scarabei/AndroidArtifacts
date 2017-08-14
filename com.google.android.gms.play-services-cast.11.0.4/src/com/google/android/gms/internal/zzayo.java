package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import java.util.Locale;

public final class zzayo {
   private static boolean zzayv = false;
   private String mTag;
   private final boolean zzayw;
   private boolean zzayx;
   private boolean zzayy;
   private String zzayz;

   private zzayo(String var1, boolean var2) {
      zzbo.zzh(var1, "The log tag cannot be null or empty.");
      this.mTag = var1;
      this.zzayw = var1.length() <= 23;
      this.zzayx = false;
      this.zzayy = false;
   }

   public zzayo(String var1) {
      this(var1, false);
   }

   public final void zzcn(String var1) {
      this.zzayz = TextUtils.isEmpty(var1) ? null : String.format("[%s] ", var1);
   }

   private final boolean zzoL() {
      return this.zzayx || this.zzayw && Log.isLoggable(this.mTag, 3);
   }

   public final void zzaa(boolean var1) {
      this.zzayx = true;
   }

   public final void zzb(String var1, Object... var2) {
      if (this.zzoL()) {
         Log.d(this.mTag, this.zzg(var1, var2));
      }

   }

   public final void zzb(Throwable var1, String var2, Object... var3) {
      if (this.zzoL()) {
         Log.d(this.mTag, this.zzg(var2, var3), var1);
      }

   }

   public final void zze(String var1, Object... var2) {
      Log.i(this.mTag, this.zzg(var1, var2));
   }

   public final void zzf(String var1, Object... var2) {
      Log.w(this.mTag, this.zzg(var1, var2));
   }

   public final void zzc(Throwable var1, String var2, Object... var3) {
      Log.w(this.mTag, this.zzg(var2, var3), var1);
   }

   public final void zzc(String var1, Object... var2) {
      Log.e(this.mTag, this.zzg(var1, var2));
   }

   public final void zza(Throwable var1, String var2, Object... var3) {
      Log.e(this.mTag, this.zzg(var2, var3), var1);
   }

   private String zzg(String var1, Object... var2) {
      String var3 = var2.length == 0 ? var1 : String.format(Locale.ROOT, var1, var2);
      if (!TextUtils.isEmpty(this.zzayz)) {
         String var10000 = String.valueOf(this.zzayz);
         String var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            String var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         var3 = var10000;
      }

      return var3;
   }
}
