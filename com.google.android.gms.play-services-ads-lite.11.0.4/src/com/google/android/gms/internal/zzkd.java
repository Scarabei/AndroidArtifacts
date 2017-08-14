package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkd extends zzed implements zzkc {
   zzkd(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
   }

   public final IBinder zza(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5, int var6) throws RemoteException {
      Parcel var7;
      zzef.zza(var7 = this.zzZ(), var1);
      zzef.zza(var7, var2);
      var7.writeString(var3);
      zzef.zza(var7, var4);
      var7.writeInt(11020000);
      var7.writeInt(var6);
      Parcel var8;
      IBinder var9 = (var8 = this.zza(2, var7)).readStrongBinder();
      var8.recycle();
      return var9;
   }
}
