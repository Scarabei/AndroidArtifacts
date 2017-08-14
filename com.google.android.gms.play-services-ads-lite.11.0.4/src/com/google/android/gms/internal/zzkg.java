package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzkg extends zzed implements zzke {
   zzkg(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAppEventListener");
   }

   public final void onAppEvent(String var1, String var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeString(var2);
      this.zzb(1, var3);
   }
}
