package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzsm implements zzth {
   // $FF: synthetic field
   private int zzKc;

   zzsm(zzsk var1, int var2) {
      this.zzKc = var2;
      super();
   }

   public final void zzb(zzti var1) throws RemoteException {
      if (var1.zztK != null) {
         var1.zztK.onAdFailedToLoad(this.zzKc);
      }

   }
}
