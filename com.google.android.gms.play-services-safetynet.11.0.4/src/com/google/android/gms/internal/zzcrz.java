package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcrz extends zzed implements zzcry {
   zzcrz(IBinder var1) {
      super(var1, "com.google.android.gms.safetynet.internal.ISafetyNetService");
   }

   public final void zza(zzcrw var1, byte[] var2, String var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeByteArray(var2);
      var4.writeString(var3);
      this.zzb(7, var4);
   }

   public final void zza(zzcrw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12, var2);
   }

   public final void zzAj() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(13, var1);
   }

   public final void zza(zzcrw var1, String var2, int[] var3, int var4, String var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeIntArray(var3);
      var6.writeInt(var4);
      var6.writeString(var5);
      this.zzb(3, var6);
   }

   public final void zzb(zzcrw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final void zzc(zzcrw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void zzd(zzcrw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void zza(zzcrw var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(6, var3);
   }
}
