package com.google.android.gms.internal;

final class zzyq implements Runnable {
   // $FF: synthetic field
   private zzajg zzRH;
   // $FF: synthetic field
   private String zzRI;
   // $FF: synthetic field
   private zzyn zzRF;

   zzyq(zzyn var1, zzajg var2, String var3) {
      this.zzRF = var1;
      this.zzRH = var2;
      this.zzRI = var3;
      super();
   }

   public final void run() {
      this.zzRH.zzg((zzpw)zzyn.zzb(this.zzRF).zzbl().get(this.zzRI));
   }
}
