package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzabr implements Runnable {
   // $FF: synthetic field
   private zzaae zzUG;
   // $FF: synthetic field
   private zzaap zzUH;
   // $FF: synthetic field
   private zzabm zzUI;

   zzabr(zzabm var1, zzaae var2, zzaap var3) {
      this.zzUI = var1;
      this.zzUG = var2;
      this.zzUH = var3;
      super();
   }

   public final void run() {
      zzaai var1 = null;

      try {
         var1 = this.zzUI.zzc(this.zzUG);
      } catch (Exception var4) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var4, (String)"AdRequestServiceImpl.loadAdAsync");
         zzafr.zzc("Could not fetch ad response due to an Exception.", var4);
      }

      if (var1 == null) {
         var1 = new zzaai(0);
      }

      try {
         this.zzUH.zza(var1);
      } catch (RemoteException var3) {
         zzafr.zzc("Fail to forward ad response.", var3);
      }
   }
}
