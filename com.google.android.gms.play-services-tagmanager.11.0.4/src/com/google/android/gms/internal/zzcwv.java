package com.google.android.gms.internal;

final class zzcwv implements Runnable {
   // $FF: synthetic field
   private zzcwu zzbJz;

   zzcwv(zzcwu var1) {
      this.zzbJz = var1;
      super();
   }

   public final void run() {
      if (zzcwn.zza(this.zzbJz.zzbJp) == 1 || zzcwn.zza(this.zzbJz.zzbJp) == 2) {
         zzcwn.zza(this.zzbJz.zzbJp, 4);
         zzcvk.e("Container load timed out after 5000ms.");

         while(!zzcwn.zze(this.zzbJz.zzbJp).isEmpty()) {
            zzcwn.zzf(this.zzbJz.zzbJp).execute((Runnable)zzcwn.zze(this.zzbJz.zzbJp).remove());
         }
      }

   }
}
