package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzff extends zzed implements zzfd {
   zzff(IBinder var1) {
      super(var1, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
   }

   public final String getId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(1, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final boolean zzb(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(2, var2));
      var3.recycle();
      return var4;
   }

   public final String zzq(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      String var4 = (var3 = this.zza(3, var2)).readString();
      var3.recycle();
      return var4;
   }

   public final void zzc(String var1, boolean var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(4, var3);
   }
}
