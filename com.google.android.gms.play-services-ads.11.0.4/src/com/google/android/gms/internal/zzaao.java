package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaao extends zzed implements zzaam {
   zzaao(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.request.IAdRequestService");
   }

   public final zzaai zzc(zzaae var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      zzaai var4 = (zzaai)zzef.zza(var3 = this.zza(1, var2), zzaai.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zza(zzaae var1, zzaap var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(2, var3);
   }

   public final void zza(zzaax var1, zzaas var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(3, var3);
   }
}
