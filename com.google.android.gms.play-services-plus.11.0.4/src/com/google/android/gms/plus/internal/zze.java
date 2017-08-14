package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zze extends zzed implements zzd {
   zze(IBinder var1) {
      super(var1, "com.google.android.gms.plus.internal.IPlusOneButtonCreator");
   }

   public final IObjectWrapper zza(IObjectWrapper var1, int var2, int var3, String var4, int var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeInt(var2);
      var6.writeInt(var3);
      var6.writeString(var4);
      var6.writeInt(var5);
      Parcel var7;
      IObjectWrapper var8 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var7 = this.zza(1, var6)).readStrongBinder());
      var7.recycle();
      return var8;
   }
}
