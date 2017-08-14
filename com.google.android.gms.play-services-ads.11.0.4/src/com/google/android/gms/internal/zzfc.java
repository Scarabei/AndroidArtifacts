package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzfc extends zzed implements zzfb {
   zzfc(IBinder var1) {
      super(var1, "com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
   }

   public final IBinder zza(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var5 = (var4 = this.zza(1, var3)).readStrongBinder();
      var4.recycle();
      return var5;
   }

   public final IBinder zzb(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var5 = (var4 = this.zza(2, var3)).readStrongBinder();
      var4.recycle();
      return var5;
   }
}
