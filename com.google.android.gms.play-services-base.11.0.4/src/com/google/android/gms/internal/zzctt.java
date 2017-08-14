package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzal;

public final class zzctt extends zzed implements zzcts {
   zzctt(IBinder var1) {
      super(var1, "com.google.android.gms.signin.internal.ISignInService");
   }

   public final void zzbv(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(7, var2);
   }

   public final void zza(zzal var1, int var2, boolean var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(9, var4);
   }

   public final void zza(zzctv var1, zzctq var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(12, var3);
   }
}
