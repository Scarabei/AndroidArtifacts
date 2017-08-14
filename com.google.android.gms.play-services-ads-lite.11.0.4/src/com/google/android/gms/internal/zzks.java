package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzks extends IInterface {
   void play() throws RemoteException;

   void pause() throws RemoteException;

   void mute(boolean var1) throws RemoteException;

   boolean isMuted() throws RemoteException;

   int getPlaybackState() throws RemoteException;

   float zzdv() throws RemoteException;

   float zzdw() throws RemoteException;

   void zza(zzkv var1) throws RemoteException;

   float getAspectRatio() throws RemoteException;

   boolean isCustomControlsEnabled() throws RemoteException;
}
