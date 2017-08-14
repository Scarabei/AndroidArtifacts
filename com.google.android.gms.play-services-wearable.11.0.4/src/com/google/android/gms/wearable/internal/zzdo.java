package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzdo extends com.google.android.gms.internal.zzed implements zzdn {
   zzdo(IBinder var1) {
      super(var1, "com.google.android.gms.wearable.internal.IWearableService");
   }

   public final void zza(zzdi var1, PutDataRequest var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var3, var2);
      this.zzb(6, var3);
   }

   public final void zza(zzdi var1, Uri var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var3, var2);
      this.zzb(7, var3);
   }

   public final void zza(zzdi var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(8, var2);
   }

   public final void zza(zzdi var1, Uri var2, int var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var4, var2);
      var4.writeInt(var3);
      this.zzb(40, var4);
   }

   public final void zzb(zzdi var1, Uri var2, int var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var4, var2);
      var4.writeInt(var3);
      this.zzb(41, var4);
   }

   public final void zza(zzdi var1, String var2, String var3, byte[] var4) throws RemoteException {
      Parcel var5;
      com.google.android.gms.internal.zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      var5.writeString(var3);
      var5.writeByteArray(var4);
      this.zzb(12, var5);
   }

   public final void zza(zzdi var1, Asset var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var3, var2);
      this.zzb(13, var3);
   }

   public final void zzb(zzdi var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final void zzc(zzdi var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(15, var2);
   }

   public final void zza(zzdi var1, String var2, int var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeInt(var3);
      this.zzb(42, var4);
   }

   public final void zza(zzdi var1, int var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(43, var3);
   }

   public final void zza(zzdi var1, String var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(46, var3);
   }

   public final void zzb(zzdi var1, String var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(47, var3);
   }

   public final void zza(zzdi var1, zzd var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var3, var2);
      this.zzb(16, var3);
   }

   public final void zza(zzdi var1, zzeo var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var3, var2);
      this.zzb(17, var3);
   }

   public final void zza(zzdi var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeString(var3);
      this.zzb(31, var4);
   }

   public final void zzc(zzdi var1, String var2) throws RemoteException {
      Parcel var3;
      com.google.android.gms.internal.zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(32, var3);
   }

   public final void zzb(zzdi var1, String var2, int var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeInt(var3);
      this.zzb(33, var4);
   }

   public final void zza(zzdi var1, zzdg var2, String var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var4, var2);
      var4.writeString(var3);
      this.zzb(34, var4);
   }

   public final void zzb(zzdi var1, zzdg var2, String var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      com.google.android.gms.internal.zzef.zza(var4, var2);
      var4.writeString(var3);
      this.zzb(35, var4);
   }

   public final void zza(zzdi var1, String var2, ParcelFileDescriptor var3) throws RemoteException {
      Parcel var4;
      com.google.android.gms.internal.zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      com.google.android.gms.internal.zzef.zza(var4, var3);
      this.zzb(38, var4);
   }

   public final void zza(zzdi var1, String var2, ParcelFileDescriptor var3, long var4, long var6) throws RemoteException {
      Parcel var8;
      com.google.android.gms.internal.zzef.zza(var8 = this.zzZ(), var1);
      var8.writeString(var2);
      com.google.android.gms.internal.zzef.zza(var8, var3);
      var8.writeLong(var4);
      var8.writeLong(var6);
      this.zzb(39, var8);
   }
}
