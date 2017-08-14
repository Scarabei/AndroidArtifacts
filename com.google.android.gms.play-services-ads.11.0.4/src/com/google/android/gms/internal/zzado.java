package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzado implements Runnable {
   // $FF: synthetic field
   private zzut zzWF;
   // $FF: synthetic field
   private zzir zztY;
   // $FF: synthetic field
   private zzadu zzWH;
   // $FF: synthetic field
   private zzadm zzWG;

   zzado(zzadm var1, zzut var2, zzir var3, zzadu var4) {
      this.zzWG = var1;
      this.zzWF = var2;
      this.zztY = var3;
      this.zzWH = var4;
      super();
   }

   public final void run() {
      try {
         this.zzWF.zza(zzn.zzw(zzadm.zza(this.zzWG)), this.zztY, (String)null, this.zzWH, zzadm.zzb(this.zzWG));
      } catch (RemoteException var2) {
         String var10001 = String.valueOf(zzadm.zzc(this.zzWG));
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Fail to initialize adapter ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Fail to initialize adapter ");
         }

         zzafr.zzc(var10000, var2);
         this.zzWG.zza(zzadm.zzc(this.zzWG), 0);
      }
   }
}
