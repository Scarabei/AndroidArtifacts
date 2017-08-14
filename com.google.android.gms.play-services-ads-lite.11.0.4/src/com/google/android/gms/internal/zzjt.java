package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzjt extends zzed implements zzjr {
   zzjt(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdLoader");
   }

   public final void zzc(zzir var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final String getMediationAdapterClassName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final boolean isLoading() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(3, var1));
      var2.recycle();
      return var3;
   }

   public final String zzaI() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(4, var1)).readString();
      var2.recycle();
      return var3;
   }
}
