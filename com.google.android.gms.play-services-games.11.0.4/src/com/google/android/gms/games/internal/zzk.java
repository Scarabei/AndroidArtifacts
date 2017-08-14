package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzk extends zzed implements zzj {
   zzk(IBinder var1) {
      super(var1, "com.google.android.gms.games.internal.IGamesService");
   }

   public final void zzC(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(5001, var3);
   }

   public final void zza(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5002, var2);
   }

   public final String zzhl() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(5003, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final Bundle zzoC() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(5004, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(IBinder var1, Bundle var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeStrongBinder(var1);
      zzef.zza(var3, var2);
      this.zzb(5005, var3);
   }

   public final void zzuP() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5006, var1);
   }

   public final String zzus() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(5007, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String zzuR() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(5012, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final DataHolder zzuS() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      DataHolder var3 = (DataHolder)zzef.zza(var2 = this.zza(5013, var1), DataHolder.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(zzf var1, int var2, boolean var3, boolean var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeInt(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzb(5015, var5);
   }

   public final void zza(zzf var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException {
      Parcel var7;
      zzef.zza(var7 = this.zzZ(), var1);
      var7.writeString(var2);
      var7.writeInt(var3);
      var7.writeInt(var4);
      var7.writeInt(var5);
      zzef.zza(var7, var6);
      this.zzb(5019, var7);
   }

   public final void zzb(zzf var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException {
      Parcel var7;
      zzef.zza(var7 = this.zzZ(), var1);
      var7.writeString(var2);
      var7.writeInt(var3);
      var7.writeInt(var4);
      var7.writeInt(var5);
      zzef.zza(var7, var6);
      this.zzb(5020, var7);
   }

   public final void zza(zzf var1, Bundle var2, int var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      var5.writeInt(var3);
      var5.writeInt(var4);
      this.zzb(5021, var5);
   }

   public final void zza(zzf var1, String var2, IBinder var3, Bundle var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      var5.writeStrongBinder(var3);
      zzef.zza(var5, var4);
      this.zzb(5023, var5);
   }

   public final void zzb(zzf var1, String var2, IBinder var3, Bundle var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      var5.writeStrongBinder(var3);
      zzef.zza(var5, var4);
      this.zzb(5024, var5);
   }

   public final void zza(zzf var1, String var2, int var3, IBinder var4, Bundle var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeInt(var3);
      var6.writeStrongBinder(var4);
      zzef.zza(var6, var5);
      this.zzb(5025, var6);
   }

   public final void zzb(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5026, var2);
   }

   public final void zzp(String var1, int var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeInt(var2);
      this.zzb(5028, var3);
   }

   public final void zzo(String var1, int var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeInt(var2);
      this.zzb(5029, var3);
   }

   public final void zza(zzf var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(5058, var4);
   }

   public final void zzD(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(5059, var3);
   }

   public final void zza(zzf var1, IBinder var2, int var3, String[] var4, Bundle var5, boolean var6, long var7) throws RemoteException {
      Parcel var9;
      zzef.zza(var9 = this.zzZ(), var1);
      var9.writeStrongBinder(var2);
      var9.writeInt(var3);
      var9.writeStringArray(var4);
      zzef.zza(var9, var5);
      zzef.zza(var9, false);
      var9.writeLong(var7);
      this.zzb(5030, var9);
   }

   public final void zza(zzf var1, IBinder var2, String var3, boolean var4, long var5) throws RemoteException {
      Parcel var7;
      zzef.zza(var7 = this.zzZ(), var1);
      var7.writeStrongBinder(var2);
      var7.writeString(var3);
      zzef.zza(var7, false);
      var7.writeLong(var5);
      this.zzb(5031, var7);
   }

   public final void zza(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(5032, var3);
   }

   public final int zza(zzf var1, byte[] var2, String var3, String var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeByteArray(var2);
      var5.writeString(var3);
      var5.writeString(var4);
      Parcel var6;
      int var7 = (var6 = this.zza(5033, var5)).readInt();
      var6.recycle();
      return var7;
   }

   public final int zzb(byte[] var1, String var2, String[] var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeByteArray(var1);
      var4.writeString(var2);
      var4.writeStringArray(var3);
      Parcel var5;
      int var6 = (var5 = this.zza(5034, var4)).readInt();
      var5.recycle();
      return var6;
   }

   public final void zzba(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(5036, var2);
   }

   public final DataHolder zzuT() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      DataHolder var3 = (DataHolder)zzef.zza(var2 = this.zza(5502, var1), DataHolder.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(6001, var3);
   }

   public final void zzb(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(6503, var3);
   }

   public final void zza(zzf var1, String var2, boolean var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(6504, var4);
   }

   public final void zza(zzf var1, String var2, long var3, String var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeLong(var3);
      var6.writeString(var5);
      this.zzb(7002, var6);
   }

   public final void zzb(zzf var1, String var2, int var3, IBinder var4, Bundle var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeInt(var3);
      var6.writeStrongBinder(var4);
      zzef.zza(var6, var5);
      this.zzb(7003, var6);
   }

   public final void zza(zzf var1, String var2, String var3, int var4, int var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString((String)null);
      var6.writeString(var3);
      var6.writeInt(var4);
      var6.writeInt(var5);
      this.zzb(8001, var6);
   }

   public final void zzdm(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(8002, var2);
   }

   public final void zza(zzf var1, int var2, int var3, String[] var4, Bundle var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeInt(var2);
      var6.writeInt(var3);
      var6.writeStringArray(var4);
      zzef.zza(var6, var5);
      this.zzb(8004, var6);
   }

   public final void zzb(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(8005, var3);
   }

   public final void zzc(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(8006, var3);
   }

   public final void zza(zzf var1, String var2, byte[] var3, String var4, ParticipantResult[] var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeByteArray(var3);
      var6.writeString(var4);
      var6.writeTypedArray(var5, 0);
      this.zzb(8007, var6);
   }

   public final void zza(zzf var1, String var2, byte[] var3, ParticipantResult[] var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      var5.writeByteArray(var3);
      var5.writeTypedArray(var4, 0);
      this.zzb(8008, var5);
   }

   public final void zzd(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(8009, var3);
   }

   public final void zze(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(8010, var3);
   }

   public final void zza(zzf var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeString(var3);
      this.zzb(8011, var4);
   }

   public final void zzb(zzf var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(8012, var4);
   }

   public final void zzE(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(8013, var3);
   }

   public final void zzf(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(8014, var3);
   }

   public final int zzuG() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(8024, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void zzc(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(8027, var3);
   }

   public final Intent zzuv() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9003, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Intent zzuw() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9005, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Intent zzux() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9006, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Intent zzuy() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9007, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Intent zzb(int var1, int var2, boolean var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeInt(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      Intent var6 = (Intent)zzef.zza(var5 = this.zza(9008, var4), Intent.CREATOR);
      var5.recycle();
      return var6;
   }

   public final Intent zzc(int var1, int var2, boolean var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeInt(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      Intent var6 = (Intent)zzef.zza(var5 = this.zza(9009, var4), Intent.CREATOR);
      var5.recycle();
      return var6;
   }

   public final Intent zzuD() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9010, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Intent zza(RoomEntity var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      Parcel var4;
      Intent var5 = (Intent)zzef.zza(var4 = this.zza(9011, var3), Intent.CREATOR);
      var4.recycle();
      return var5;
   }

   public final Intent zzuE() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(9012, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final int zzuF() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(9019, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void zza(zzf var1, String var2, int var3, boolean var4, boolean var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeInt(var3);
      zzef.zza(var6, var4);
      zzef.zza(var6, var5);
      this.zzb(9020, var6);
   }

   public final void zzc(zzf var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(10001, var4);
   }

   public final void zzF(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(10002, var3);
   }

   public final void zza(zzf var1, String[] var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeStringArray(var2);
      this.zzb(10006, var3);
   }

   public final void zzb(zzf var1, String[] var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeStringArray(var2);
      this.zzb(10007, var3);
   }

   public final void zza(zzf var1, int var2, int var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeInt(var2);
      var5.writeInt(var3);
      var5.writeInt(var4);
      this.zzb(10009, var5);
   }

   public final Intent zza(int var1, byte[] var2, int var3, String var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeInt(var1);
      var5.writeByteArray(var2);
      var5.writeInt(var3);
      var5.writeString(var4);
      Parcel var6;
      Intent var7 = (Intent)zzef.zza(var6 = this.zza(10012, var5), Intent.CREATOR);
      var6.recycle();
      return var7;
   }

   public final int zzuI() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(10013, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final int zzuJ() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(10023, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final Intent zzuH() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(10015, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(zzf var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(10016, var3);
   }

   public final void zza(zzf var1, int var2, int[] var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeInt(var2);
      var4.writeIntArray(var3);
      this.zzb(10018, var4);
   }

   public final Intent zza(String var1, boolean var2, boolean var3, int var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      var5.writeInt(var4);
      Parcel var6;
      Intent var7 = (Intent)zzef.zza(var6 = this.zza(12001, var5), Intent.CREATOR);
      var6.recycle();
      return var7;
   }

   public final void zzd(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(12002, var3);
   }

   public final void zza(zzf var1, String var2, com.google.android.gms.games.snapshot.zze var3, com.google.android.gms.drive.zzc var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzb(12007, var5);
   }

   public final void zza(com.google.android.gms.drive.zzc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12019, var2);
   }

   public final void zzg(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(12020, var3);
   }

   public final void zza(zzf var1, String var2, String var3, com.google.android.gms.games.snapshot.zze var4, com.google.android.gms.drive.zzc var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeString(var3);
      zzef.zza(var6, var4);
      zzef.zza(var6, var5);
      this.zzb(12033, var6);
   }

   public final int zzuK() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(12035, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final int zzuL() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(12036, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void zze(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(12016, var3);
   }

   public final void zza(zzf var1, boolean var2, String[] var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      var4.writeStringArray(var3);
      this.zzb(12031, var4);
   }

   public final void zzn(String var1, int var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeInt(var2);
      this.zzb(12017, var3);
   }

   public final void zzh(zzf var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(12008, var3);
   }

   public final void zzb(zzf var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeString(var3);
      this.zzb(12009, var4);
   }

   public final void zza(zzf var1, int[] var2, int var3, boolean var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeIntArray(var2);
      var5.writeInt(var3);
      zzef.zza(var5, var4);
      this.zzb(12010, var5);
   }

   public final void zza(zzf var1, String[] var2, boolean var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeStringArray(var2);
      zzef.zza(var4, var3);
      this.zzb(12029, var4);
   }

   public final void zzd(zzf var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(12011, var4);
   }

   public final void zzG(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(12012, var3);
   }

   public final Intent zzb(int[] var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeIntArray(var1);
      Parcel var3;
      Intent var4 = (Intent)zzef.zza(var3 = this.zza(12030, var2), Intent.CREATOR);
      var3.recycle();
      return var4;
   }

   public final Intent zzdk(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      Intent var4 = (Intent)zzef.zza(var3 = this.zza(12034, var2), Intent.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zza(String var1, IBinder var2, Bundle var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeStrongBinder(var2);
      zzef.zza(var4, var3);
      this.zzb(13002, var4);
   }

   public final void zzb(zzf var1, String var2, boolean var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      this.zzb(13006, var4);
   }

   public final void zza(zzf var1, String var2, boolean var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      var5.writeInt(var4);
      this.zzb(15001, var5);
   }

   public final void zza(zzh var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(15501, var4);
   }

   public final Intent zza(PlayerEntity var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      Intent var4 = (Intent)zzef.zza(var3 = this.zza(15503, var2), Intent.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zzf(zzf var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(17001, var3);
   }

   public final Intent zzk(String var1, int var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeInt(var2);
      var4.writeInt(var3);
      Parcel var5;
      Intent var6 = (Intent)zzef.zza(var5 = this.zza(18001, var4), Intent.CREATOR);
      var5.recycle();
      return var6;
   }

   public final Intent zzuU() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Intent var3 = (Intent)zzef.zza(var2 = this.zza(19002, var1), Intent.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(String var1, zzf var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(20001, var3);
   }

   public final void zzc(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(21007, var2);
   }

   public final void zzb(zzf var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(22016, var3);
   }

   public final void zze(zzf var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(22026, var4);
   }

   public final void zzH(long var1) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeLong(var1);
      this.zzb(22027, var3);
   }

   public final void zzd(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22028, var2);
   }

   public final boolean zzuN() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(22030, var1));
      var2.recycle();
      return var3;
   }
}
