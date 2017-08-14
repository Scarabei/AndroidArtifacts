package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzaj {
   private static int zzaHZ = 15;
   private static final String zzaIa = null;
   private final String zzaIb;
   private final String zzaIc;

   private zzaj(String var1, String var2) {
      zzbo.zzb(var1, "log tag cannot be null");
      zzbo.zzb(var1.length() <= 23, "tag \"%s\" is longer than the %d character maximum", var1, Integer.valueOf(23));
      this.zzaIb = var1;
      this.zzaIc = null;
   }

   public zzaj(String var1) {
      this(var1, (String)null);
   }

   private final boolean zzaB(int var1) {
      return Log.isLoggable(this.zzaIb, var1);
   }

   public final void zzx(String var1, String var2) {
      if (this.zzaB(3)) {
         Log.d(var1, this.zzcE(var2));
      }

   }

   public final void zzb(String var1, String var2, Throwable var3) {
      if (this.zzaB(4)) {
         Log.i(var1, this.zzcE(var2), var3);
      }

   }

   public final void zzy(String var1, String var2) {
      if (this.zzaB(5)) {
         Log.w(var1, this.zzcE(var2));
      }

   }

   public final void zzc(String var1, String var2, Throwable var3) {
      if (this.zzaB(5)) {
         Log.w(var1, this.zzcE(var2), var3);
      }

   }

   public final void zzz(String var1, String var2) {
      if (this.zzaB(6)) {
         Log.e(var1, this.zzcE(var2));
      }

   }

   public final void zzd(String var1, String var2, Throwable var3) {
      if (this.zzaB(6)) {
         Log.e(var1, this.zzcE(var2), var3);
      }

   }

   public final void zze(String var1, String var2, Throwable var3) {
      if (this.zzaB(7)) {
         Log.e(var1, this.zzcE(var2), var3);
         Log.wtf(var1, this.zzcE(var2), var3);
      }

   }

   private final String zzcE(String var1) {
      return this.zzaIc == null ? var1 : this.zzaIc.concat(var1);
   }
}
