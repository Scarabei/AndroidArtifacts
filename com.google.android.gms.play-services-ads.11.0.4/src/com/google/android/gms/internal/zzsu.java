package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzsu extends zzni {
   // $FF: synthetic field
   private zzsj zzKb;

   zzsu(zzsj var1) {
      this.zzKb = var1;
      super();
   }

   public final void zza(zzne var1) throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsv(this, var1));
   }
}
