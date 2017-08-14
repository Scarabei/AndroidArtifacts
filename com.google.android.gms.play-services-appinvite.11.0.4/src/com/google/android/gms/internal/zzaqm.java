package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaqm extends zzed implements zzaql {
   zzaqm(IBinder var1) {
      super(var1, "com.google.android.gms.appinvite.internal.IAppInviteService");
   }

   public final void zza(zzaqj var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(1, var3);
   }

   public final void zzb(zzaqj var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(2, var3);
   }

   public final void zza(zzaqj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }
}
