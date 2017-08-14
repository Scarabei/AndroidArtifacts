package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;

public interface zzj extends IInterface {
   void zzC(long var1) throws RemoteException;

   void zza(zzf var1) throws RemoteException;

   String zzhl() throws RemoteException;

   Bundle zzoC() throws RemoteException;

   void zza(IBinder var1, Bundle var2) throws RemoteException;

   void zzuP() throws RemoteException;

   String zzus() throws RemoteException;

   String zzuR() throws RemoteException;

   DataHolder zzuS() throws RemoteException;

   void zza(zzf var1, int var2, boolean var3, boolean var4) throws RemoteException;

   void zza(zzf var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException;

   void zzb(zzf var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException;

   void zza(zzf var1, Bundle var2, int var3, int var4) throws RemoteException;

   void zza(zzf var1, String var2, IBinder var3, Bundle var4) throws RemoteException;

   void zzb(zzf var1, String var2, IBinder var3, Bundle var4) throws RemoteException;

   void zza(zzf var1, String var2, int var3, IBinder var4, Bundle var5) throws RemoteException;

   void zzb(zzf var1) throws RemoteException;

   void zzp(String var1, int var2) throws RemoteException;

   void zzo(String var1, int var2) throws RemoteException;

   void zza(zzf var1, long var2) throws RemoteException;

   void zzD(long var1) throws RemoteException;

   void zza(zzf var1, IBinder var2, int var3, String[] var4, Bundle var5, boolean var6, long var7) throws RemoteException;

   void zza(zzf var1, IBinder var2, String var3, boolean var4, long var5) throws RemoteException;

   void zza(zzf var1, String var2) throws RemoteException;

   int zza(zzf var1, byte[] var2, String var3, String var4) throws RemoteException;

   int zzb(byte[] var1, String var2, String[] var3) throws RemoteException;

   void zzba(int var1) throws RemoteException;

   DataHolder zzuT() throws RemoteException;

   void zza(zzf var1, boolean var2) throws RemoteException;

   void zzb(zzf var1, boolean var2) throws RemoteException;

   void zza(zzf var1, String var2, boolean var3) throws RemoteException;

   void zza(zzf var1, String var2, long var3, String var5) throws RemoteException;

   void zzb(zzf var1, String var2, int var3, IBinder var4, Bundle var5) throws RemoteException;

   void zza(zzf var1, String var2, String var3, int var4, int var5) throws RemoteException;

   void zzdm(String var1) throws RemoteException;

   void zza(zzf var1, int var2, int var3, String[] var4, Bundle var5) throws RemoteException;

   void zzb(zzf var1, String var2) throws RemoteException;

   void zzc(zzf var1, String var2) throws RemoteException;

   void zza(zzf var1, String var2, byte[] var3, String var4, ParticipantResult[] var5) throws RemoteException;

   void zza(zzf var1, String var2, byte[] var3, ParticipantResult[] var4) throws RemoteException;

   void zzd(zzf var1, String var2) throws RemoteException;

   void zze(zzf var1, String var2) throws RemoteException;

   void zza(zzf var1, String var2, String var3) throws RemoteException;

   void zzb(zzf var1, long var2) throws RemoteException;

   void zzE(long var1) throws RemoteException;

   void zzf(zzf var1, String var2) throws RemoteException;

   int zzuG() throws RemoteException;

   void zzc(zzf var1, boolean var2) throws RemoteException;

   Intent zzuv() throws RemoteException;

   Intent zzuw() throws RemoteException;

   Intent zzux() throws RemoteException;

   Intent zzuy() throws RemoteException;

   Intent zzb(int var1, int var2, boolean var3) throws RemoteException;

   Intent zzc(int var1, int var2, boolean var3) throws RemoteException;

   Intent zzuD() throws RemoteException;

   Intent zza(RoomEntity var1, int var2) throws RemoteException;

   Intent zzuE() throws RemoteException;

   int zzuF() throws RemoteException;

   void zza(zzf var1, String var2, int var3, boolean var4, boolean var5) throws RemoteException;

   void zzc(zzf var1, long var2) throws RemoteException;

   void zzF(long var1) throws RemoteException;

   void zza(zzf var1, String[] var2) throws RemoteException;

   void zzb(zzf var1, String[] var2) throws RemoteException;

   void zza(zzf var1, int var2, int var3, int var4) throws RemoteException;

   Intent zza(int var1, byte[] var2, int var3, String var4) throws RemoteException;

   int zzuI() throws RemoteException;

   int zzuJ() throws RemoteException;

   Intent zzuH() throws RemoteException;

   void zza(zzf var1, int var2) throws RemoteException;

   void zza(zzf var1, int var2, int[] var3) throws RemoteException;

   Intent zza(String var1, boolean var2, boolean var3, int var4) throws RemoteException;

   void zzd(zzf var1, boolean var2) throws RemoteException;

   void zza(zzf var1, String var2, com.google.android.gms.games.snapshot.zze var3, com.google.android.gms.drive.zzc var4) throws RemoteException;

   void zza(com.google.android.gms.drive.zzc var1) throws RemoteException;

   void zzg(zzf var1, String var2) throws RemoteException;

   void zza(zzf var1, String var2, String var3, com.google.android.gms.games.snapshot.zze var4, com.google.android.gms.drive.zzc var5) throws RemoteException;

   int zzuK() throws RemoteException;

   int zzuL() throws RemoteException;

   void zze(zzf var1, boolean var2) throws RemoteException;

   void zza(zzf var1, boolean var2, String[] var3) throws RemoteException;

   void zzn(String var1, int var2) throws RemoteException;

   void zzh(zzf var1, String var2) throws RemoteException;

   void zzb(zzf var1, String var2, String var3) throws RemoteException;

   void zza(zzf var1, int[] var2, int var3, boolean var4) throws RemoteException;

   void zza(zzf var1, String[] var2, boolean var3) throws RemoteException;

   void zzd(zzf var1, long var2) throws RemoteException;

   void zzG(long var1) throws RemoteException;

   Intent zzb(int[] var1) throws RemoteException;

   Intent zzdk(String var1) throws RemoteException;

   void zza(String var1, IBinder var2, Bundle var3) throws RemoteException;

   void zzb(zzf var1, String var2, boolean var3) throws RemoteException;

   void zza(zzf var1, String var2, boolean var3, int var4) throws RemoteException;

   void zza(zzh var1, long var2) throws RemoteException;

   Intent zza(PlayerEntity var1) throws RemoteException;

   void zzf(zzf var1, boolean var2) throws RemoteException;

   Intent zzk(String var1, int var2, int var3) throws RemoteException;

   Intent zzuU() throws RemoteException;

   void zza(String var1, zzf var2) throws RemoteException;

   void zzc(zzf var1) throws RemoteException;

   void zzb(zzf var1, int var2) throws RemoteException;

   void zze(zzf var1, long var2) throws RemoteException;

   void zzH(long var1) throws RemoteException;

   void zzd(zzf var1) throws RemoteException;

   boolean zzuN() throws RemoteException;
}
