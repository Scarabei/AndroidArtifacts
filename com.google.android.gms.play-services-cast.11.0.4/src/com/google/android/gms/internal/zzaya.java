package com.google.android.gms.internal;

final class zzaya implements Runnable {
   // $FF: synthetic field
   private zzaxx zzayi;
   // $FF: synthetic field
   private int zzayj;

   zzaya(zzaxz var1, zzaxx var2, int var3) {
      this.zzayi = var2;
      this.zzayj = var3;
      super();
   }

   public final void run() {
      zzaxx.zzd(this.zzayi).onApplicationDisconnected(this.zzayj);
   }
}
