package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzss extends zzkf {
   // $FF: synthetic field
   private zzsj zzKb;

   zzss(zzsj var1) {
      this.zzKb = var1;
      super();
   }

   public final void onAppEvent(String var1, String var2) throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzst(this, var1, var2));
   }
}
