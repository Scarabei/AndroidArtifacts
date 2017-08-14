package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;

public final class zzbsy extends zzed implements zzbsw {
   zzbsy(IBinder var1) {
      super(var1, "com.google.android.gms.drive.realtime.internal.IRealtimeService");
   }

   public final void zza(String var1, zzbsz var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }

   public final void zza(zzbsc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zza(zzbtb var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zzb(zzbsc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(33, var2);
   }

   public final void zzb(zzbtb var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(35, var2);
   }

   public final void zza(zzbsu var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(40, var2);
   }

   public final void zzc(zzbsc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(45, var2);
   }

   public final void zza(String var1, int var2, zzbsz var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(46, var4);
   }

   public final void zza(String var1, String var2, zzbsi var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(4, var4);
   }

   public final void zza(String var1, zzbsu var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(5, var3);
   }

   public final void zza(String var1, DataHolder var2, zzbsq var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(6, var4);
   }

   public final void zza(String var1, zzbsq var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(7, var3);
   }

   public final void zza(String var1, zzbsi var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(20, var3);
   }

   public final void zza(String var1, String var2, zzbsk var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(21, var4);
   }

   public final void zzb(String var1, zzbsu var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(8, var3);
   }

   public final void zzb(String var1, zzbsz var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(9, var3);
   }

   public final void zza(String var1, int var2, String var3, zzbsq var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeInt(var2);
      var5.writeString(var3);
      zzef.zza(var5, var4);
      this.zzb(10, var5);
   }

   public final void zza(String var1, int var2, int var3, zzbsq var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeInt(var2);
      var5.writeInt(var3);
      zzef.zza(var5, var4);
      this.zzb(11, var5);
   }

   public final void zza(String var1, String var2, zzbsq var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(12, var4);
   }

   public final void zzb(String var1, zzbsi var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(13, var3);
   }

   public final void zzc(String var1, zzbsu var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(14, var3);
   }

   public final void zza(String var1, int var2, DataHolder var3, zzbsq var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeInt(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzb(15, var5);
   }

   public final void zza(String var1, int var2, DataHolder var3, zzbsk var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeInt(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzb(16, var5);
   }

   public final void zza(String var1, int var2, int var3, zzbsk var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeInt(var2);
      var5.writeInt(var3);
      zzef.zza(var5, var4);
      this.zzb(17, var5);
   }

   public final void zza(String var1, int var2, String var3, int var4, zzbsq var5) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeString(var1);
      var6.writeInt(var2);
      var6.writeString(var3);
      var6.writeInt(var4);
      zzef.zza(var6, var5);
      this.zzb(37, var6);
   }

   public final void zza(zzbry var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(18, var3);
   }

   public final void zza(zzbsa var1, zzbsq var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(41, var3);
   }

   public final void zza(zzbsq var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22, var2);
   }

   public final void zzb(zzbsq var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(23, var2);
   }

   public final void zzd(zzbsc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(24, var2);
   }

   public final void zze(zzbsc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(25, var2);
   }

   public final void zza(boolean var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(47, var3);
   }

   public final void zza(int var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeInt(var1);
      zzef.zza(var3, var2);
      this.zzb(50, var3);
   }

   public final void zza(zzbtf var1, zzbsz var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(26, var3);
   }

   public final void zza(String var1, zzbss var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(27, var3);
   }

   public final void zza(String var1, int var2, zzbtb var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(28, var4);
   }

   public final void zzb(zzbsu var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(29, var2);
   }

   public final void zza(int var1, zzbsq var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeInt(var1);
      zzef.zza(var3, var2);
      this.zzb(30, var3);
   }

   public final void zza(zzbsg var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(31, var2);
   }

   public final void zza(zzbse var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(32, var2);
   }

   public final void zza(zzbso var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(34, var2);
   }

   public final void zza(zzbsm var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(36, var2);
   }

   public final void zza(String var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(38, var3);
   }

   public final void zzb(String var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(39, var3);
   }

   public final void zzb(String var1, String var2, zzbsi var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(42, var4);
   }

   public final void zza(String var1, String var2, DataHolder var3, zzbsq var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzb(43, var5);
   }

   public final void zztw() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(44, var1);
   }

   public final void zza(DriveId var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(48, var3);
   }

   public final void zzc(zzbtb var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(49, var2);
   }

   public final void zza(zzbsa var1, zzbtb var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(19, var3);
   }

   public final zzbtn zzd(String var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      var4.writeString(var3);
      Parcel var5;
      zzbtn var6 = (zzbtn)zzef.zza(var5 = this.zza(51, (Parcel)var4), zzbtn.CREATOR);
      var5.recycle();
      return var6;
   }
}
