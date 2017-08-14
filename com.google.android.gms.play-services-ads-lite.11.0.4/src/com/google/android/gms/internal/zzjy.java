package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzjy extends zzed implements zzjx {
   zzjy(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
   }

   public final IBinder zza(IObjectWrapper var1, String var2, zzuq var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      var5.writeInt(11020000);
      Parcel var6;
      IBinder var7 = (var6 = this.zza(1, var5)).readStrongBinder();
      var6.recycle();
      return var7;
   }
}
