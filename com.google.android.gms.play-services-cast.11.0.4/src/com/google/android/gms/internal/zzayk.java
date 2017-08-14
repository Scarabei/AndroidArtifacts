package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzz;

public final class zzayk extends zzed implements zzayj {
   zzayk(IBinder var1) {
      super(var1, "com.google.android.gms.cast.internal.ICastDeviceController");
   }

   public final void disconnect() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(1, var1);
   }

   public final void zzoK() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(4, var1);
   }

   public final void zzcc(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzc(5, var2);
   }

   public final void requestStatus() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(6, var1);
   }

   public final void zza(double var1, double var3, boolean var5) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeDouble(var1);
      var6.writeDouble(var3);
      zzef.zza(var6, var5);
      this.zzc(7, var6);
   }

   public final void zza(boolean var1, double var2, boolean var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeDouble(var2);
      zzef.zza(var5, var4);
      this.zzc(8, var5);
   }

   public final void zzb(String var1, String var2, long var3) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeString(var2);
      var5.writeLong(var3);
      this.zzc(9, var5);
   }

   public final void zzcl(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzc(11, var2);
   }

   public final void zzcm(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzc(12, var2);
   }

   public final void zzb(String var1, LaunchOptions var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzc(13, var3);
   }

   public final void zza(String var1, String var2, zzz var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzc(14, var4);
   }
}
