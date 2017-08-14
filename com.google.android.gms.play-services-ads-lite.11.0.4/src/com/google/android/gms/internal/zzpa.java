package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzpa extends zzed implements zzoz {
   zzpa(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
   }

   public final IBinder zza(IObjectWrapper var1, IObjectWrapper var2, IObjectWrapper var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      var5.writeInt(11020000);
      Parcel var6;
      IBinder var7 = (var6 = this.zza(1, var5)).readStrongBinder();
      var6.recycle();
      return var7;
   }
}
