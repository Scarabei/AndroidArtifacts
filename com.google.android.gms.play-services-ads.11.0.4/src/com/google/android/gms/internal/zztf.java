package com.google.android.gms.internal;

import android.os.RemoteException;

final class zztf implements zzth {
   // $FF: synthetic field
   private int zzKc;

   zztf(zzsy var1, int var2) {
      this.zzKc = var2;
      super();
   }

   public final void zzb(zzti var1) throws RemoteException {
      if (var1.zzKl != null) {
         var1.zzKl.onRewardedVideoAdFailedToLoad(this.zzKc);
      }

   }
}
