package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.Random;

final class zztj extends zzjp {
   private final zzjo zzKm;

   zztj(zzjo var1) {
      this.zzKm = var1;
   }

   public final void onAdClosed() throws RemoteException {
      if (zzts.zzeX()) {
         zzme var5 = zzmo.zzEg;
         int var1 = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).intValue();
         var5 = zzmo.zzEh;
         int var2 = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).intValue();
         if (var1 > 0 && var2 >= 0) {
            long var3 = (long)(var1 + (new Random()).nextInt(var2 + 1));
            zzagz.zzZr.postDelayed(zztk.zzKn, var3);
         } else {
            com.google.android.gms.ads.internal.zzbs.zzbN().zzeG();
         }
      }

      this.zzKm.onAdClosed();
   }

   public final void onAdFailedToLoad(int var1) throws RemoteException {
      this.zzKm.onAdFailedToLoad(var1);
   }

   public final void onAdLeftApplication() throws RemoteException {
      this.zzKm.onAdLeftApplication();
   }

   public final void onAdLoaded() throws RemoteException {
      this.zzKm.onAdLoaded();
   }

   public final void onAdOpened() throws RemoteException {
      this.zzKm.onAdOpened();
   }

   public final void onAdImpression() throws RemoteException {
      this.zzKm.onAdImpression();
   }

   public final void onAdClicked() throws RemoteException {
      this.zzKm.onAdClicked();
   }
}
