package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzk extends zzed implements zzj {
   zzk(IBinder var1) {
      super(var1, "com.google.android.gms.dynamite.IDynamiteLoader");
   }

   public final IObjectWrapper zza(IObjectWrapper var1, String var2, int var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), (IInterface)var1);
      var4.writeString(var2);
      var4.writeInt(var3);
      Parcel var5;
      IObjectWrapper var6 = IObjectWrapper.zza.zzM((var5 = this.zza(2, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final int zza(IObjectWrapper var1, String var2, boolean var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), (IInterface)var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      int var6 = (var5 = this.zza(3, var4)).readInt();
      var5.recycle();
      return var6;
   }
}
