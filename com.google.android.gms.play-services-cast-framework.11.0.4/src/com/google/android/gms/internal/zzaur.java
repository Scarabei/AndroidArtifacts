package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaur extends zzed implements zzauq {
   zzaur(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
   }

   public final void zzc(String var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }

   public final void zzd(String var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(2, var3);
   }

   public final void zze(String var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(3, var3);
   }

   public final void zzf(String var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(4, var3);
   }

   public final void zza(String var1, Bundle var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      zzef.zza(var4, var2);
      var4.writeInt(var3);
      this.zzb(6, var4);
   }
}
