package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzsw extends zzjm {
   // $FF: synthetic field
   private zzsj zzKb;

   zzsw(zzsj var1) {
      this.zzKb = var1;
      super();
   }

   public final void onAdClicked() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsx(this));
   }
}
