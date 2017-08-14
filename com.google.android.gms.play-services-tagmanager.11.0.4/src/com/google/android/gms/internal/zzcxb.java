package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzcxb implements Runnable {
   // $FF: synthetic field
   private String zzbJw;
   // $FF: synthetic field
   private String zzbJx;
   // $FF: synthetic field
   private String zzbJy;
   // $FF: synthetic field
   private zzcvd zzbJG;
   // $FF: synthetic field
   private zzcxa zzbJH;

   zzcxb(zzcxa var1, String var2, String var3, String var4, zzcvd var5) {
      this.zzbJH = var1;
      this.zzbJw = var2;
      this.zzbJx = var3;
      this.zzbJy = var4;
      this.zzbJG = var5;
      super();
   }

   public final void run() {
      boolean var1;
      try {
         if (zzcxa.zza(this.zzbJH).containsKey(this.zzbJw)) {
            var1 = true;
         } else {
            zzcuf var2 = zzcxa.zzb(this.zzbJH).zzm(this.zzbJw, this.zzbJx, this.zzbJy);
            zzcxa.zza(this.zzbJH).put(this.zzbJw, var2);
            var1 = true;
         }
      } catch (Throwable var4) {
         var1 = false;
         zzcup.zza("Fail to load container: ", var4, zzcxa.zzc(this.zzbJH));
      }

      try {
         if (this.zzbJG != null) {
            this.zzbJG.zza(var1, this.zzbJw);
         }

      } catch (RemoteException var3) {
         zzcup.zza("Error relaying callback: ", var3, zzcxa.zzc(this.zzbJH));
      }
   }
}
