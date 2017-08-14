package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzm extends zzed implements zzl {
   zzm(IBinder var1) {
      super(var1, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
   }

   public final IObjectWrapper zza(IObjectWrapper var1, String var2, int var3, IObjectWrapper var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), (IInterface)var1);
      var5.writeString(var2);
      var5.writeInt(var3);
      zzef.zza(var5, (IInterface)var4);
      Parcel var6;
      IObjectWrapper var7 = IObjectWrapper.zza.zzM((var6 = this.zza(2, var5)).readStrongBinder());
      var6.recycle();
      return var7;
   }
}
