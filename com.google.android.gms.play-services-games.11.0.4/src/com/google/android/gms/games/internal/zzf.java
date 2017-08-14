package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;

public interface zzf extends IInterface {
   void zzg(int var1, String var2) throws RemoteException;

   void zzf(DataHolder var1) throws RemoteException;

   void zzh(int var1, String var2) throws RemoteException;

   void zzh(DataHolder var1) throws RemoteException;

   void zza(DataHolder var1, DataHolder var2) throws RemoteException;

   void zzi(DataHolder var1) throws RemoteException;

   void zzj(DataHolder var1) throws RemoteException;

   void zzk(DataHolder var1) throws RemoteException;

   void zzl(DataHolder var1) throws RemoteException;

   void zzuq() throws RemoteException;

   void zzm(DataHolder var1) throws RemoteException;

   void zzn(DataHolder var1) throws RemoteException;

   void zzu(DataHolder var1) throws RemoteException;

   void zzv(DataHolder var1) throws RemoteException;

   void onLeftRoom(int var1, String var2) throws RemoteException;

   void zzw(DataHolder var1) throws RemoteException;

   void zzx(DataHolder var1) throws RemoteException;

   void zzy(DataHolder var1) throws RemoteException;

   void zzz(DataHolder var1) throws RemoteException;

   void zzA(DataHolder var1) throws RemoteException;

   void zza(DataHolder var1, String[] var2) throws RemoteException;

   void zzb(DataHolder var1, String[] var2) throws RemoteException;

   void zzc(DataHolder var1, String[] var2) throws RemoteException;

   void zzd(DataHolder var1, String[] var2) throws RemoteException;

   void zze(DataHolder var1, String[] var2) throws RemoteException;

   void zzf(DataHolder var1, String[] var2) throws RemoteException;

   void onRealTimeMessageReceived(RealTimeMessage var1) throws RemoteException;

   void zzb(int var1, int var2, String var3) throws RemoteException;

   void onP2PConnected(String var1) throws RemoteException;

   void onP2PDisconnected(String var1) throws RemoteException;

   void zzB(DataHolder var1) throws RemoteException;

   void zzb(int var1, Bundle var2) throws RemoteException;

   void zzp(DataHolder var1) throws RemoteException;

   void zzq(DataHolder var1) throws RemoteException;

   void zzr(DataHolder var1) throws RemoteException;

   void zzs(DataHolder var1) throws RemoteException;

   void zzi(int var1, String var2) throws RemoteException;

   void zzt(DataHolder var1) throws RemoteException;

   void onTurnBasedMatchRemoved(String var1) throws RemoteException;

   void onInvitationRemoved(String var1) throws RemoteException;

   void zzo(DataHolder var1) throws RemoteException;

   void onRequestRemoved(String var1) throws RemoteException;

   void zzC(DataHolder var1) throws RemoteException;

   void zzc(int var1, Bundle var2) throws RemoteException;

   void zzD(DataHolder var1) throws RemoteException;

   void zza(DataHolder var1, com.google.android.gms.drive.zzc var2) throws RemoteException;

   void zza(DataHolder var1, String var2, com.google.android.gms.drive.zzc var3, com.google.android.gms.drive.zzc var4, com.google.android.gms.drive.zzc var5) throws RemoteException;

   void zzE(DataHolder var1) throws RemoteException;

   void zzj(int var1, String var2) throws RemoteException;

   void zzg(DataHolder var1) throws RemoteException;

   void zzF(DataHolder var1) throws RemoteException;

   void zzG(DataHolder var1) throws RemoteException;

   void zzH(DataHolder var1) throws RemoteException;

   void zzI(DataHolder var1) throws RemoteException;

   void zzJ(DataHolder var1) throws RemoteException;

   void zza(int var1, VideoCapabilities var2) throws RemoteException;

   void zzh(int var1, boolean var2) throws RemoteException;

   void onCaptureOverlayStateChanged(int var1) throws RemoteException;

   void zzd(int var1, Bundle var2) throws RemoteException;
}
