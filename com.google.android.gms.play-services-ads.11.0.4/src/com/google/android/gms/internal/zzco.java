package com.google.android.gms.internal;

final class zzco implements Runnable {
   // $FF: synthetic field
   private zzcn zzpO;

   zzco(zzcn var1) {
      this.zzpO = var1;
      super();
   }

   public final void run() {
      if (this.zzpO.zzpM == null) {
         synchronized(zzcn.zzA()) {
            if (this.zzpO.zzpM == null) {
               zzme var4 = zzmo.zzEM;
               boolean var2;
               if (var2 = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
                  try {
                     zzcn.zzpL = new zzazn(zzcn.zza(this.zzpO).zzqD, "ADSHIELD", (String)null);
                  } catch (Throwable var5) {
                     var2 = false;
                  }
               }

               this.zzpO.zzpM = var2;
               zzcn.zzA().open();
            }
         }
      }
   }
}
