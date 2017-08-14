package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkr extends zzed implements zzkq {
   zzkr(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
   }

   public final IBinder zza(IObjectWrapper var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(11020000);
      Parcel var4;
      IBinder var5 = (var4 = this.zza(1, var3)).readStrongBinder();
      var4.recycle();
      return var5;
   }
}
