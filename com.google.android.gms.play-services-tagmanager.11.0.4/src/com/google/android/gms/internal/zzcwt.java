package com.google.android.gms.internal;

final class zzcwt implements Runnable {
   // $FF: synthetic field
   private String zzbJw;
   // $FF: synthetic field
   private String zzbJx;
   // $FF: synthetic field
   private String zzbJy;
   // $FF: synthetic field
   private zzcwn zzbJp;

   zzcwt(zzcwn var1, String var2, String var3, String var4) {
      this.zzbJp = var1;
      this.zzbJw = var2;
      this.zzbJx = var3;
      this.zzbJy = null;
      super();
   }

   public final void run() {
      String var1 = this.zzbJw;
      zzcvk.v((new StringBuilder(28 + String.valueOf(var1).length())).append("Starting to load container ").append(var1).append(".").toString());
      if (zzcwn.zza(this.zzbJp) != 1) {
         zzcup.zzd("Unexpected state - container loading already initiated.", zzcwn.zzd(this.zzbJp));
      } else {
         zzcwn.zza(this.zzbJp, 2);
         zzcwn.zzb(this.zzbJp).zzb(this.zzbJw, this.zzbJx, this.zzbJy, this.zzbJp.new zzb((zzcwo)null));
      }
   }
}
