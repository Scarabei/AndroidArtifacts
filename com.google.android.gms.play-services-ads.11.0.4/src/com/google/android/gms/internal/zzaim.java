package com.google.android.gms.internal;

final class zzaim implements Runnable {
   // $FF: synthetic field
   private zzajm zzaao;
   // $FF: synthetic field
   private zzaij zzaan;

   zzaim(zzaij var1, zzajm var2) {
      this.zzaan = var1;
      this.zzaao = var2;
      super();
   }

   public final void run() {
      try {
         zzaij.zzb(this.zzaan).zzb(this.zzaao.get());
      } catch (Exception var2) {
         zzafr.zzb("Error occured while dispatching http response in getter.", var2);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var2, (String)"HttpGetter.deliverResponse.1");
      }
   }
}
