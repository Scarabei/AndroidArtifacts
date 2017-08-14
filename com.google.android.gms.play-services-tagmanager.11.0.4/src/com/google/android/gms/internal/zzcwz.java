package com.google.android.gms.internal;

final class zzcwz implements Runnable {
   // $FF: synthetic field
   private boolean zzbJC;
   // $FF: synthetic field
   private String zzbJw;
   // $FF: synthetic field
   private zzcwn.zzb zzbJD;

   zzcwz(zzcwn.zzb var1, boolean var2, String var3) {
      this.zzbJD = var1;
      this.zzbJC = var2;
      this.zzbJw = var3;
      super();
   }

   public final void run() {
      if (zzcwn.zza(this.zzbJD.zzbJp) == 2) {
         if (this.zzbJC) {
            zzcwn.zza(this.zzbJD.zzbJp, 3);
            String var1 = this.zzbJw;
            zzcvk.v((new StringBuilder(18 + String.valueOf(var1).length())).append("Container ").append(var1).append(" loaded.").toString());
         } else {
            zzcwn.zza(this.zzbJD.zzbJp, 4);
            String var10001 = String.valueOf(this.zzbJw);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Error loading container:".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Error loading container:");
            }

            zzcvk.e(var10000);
         }

         while(!zzcwn.zze(this.zzbJD.zzbJp).isEmpty()) {
            zzcwn.zzf(this.zzbJD.zzbJp).execute((Runnable)zzcwn.zze(this.zzbJD.zzbJp).remove());
         }
      } else {
         zzcvk.zzaT("Container load callback completed after timeout");
      }

   }
}
