package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzkv extends IInterface {
   void onVideoStart() throws RemoteException;

   void onVideoPlay() throws RemoteException;

   void onVideoPause() throws RemoteException;

   void onVideoEnd() throws RemoteException;

   void onVideoMute(boolean var1) throws RemoteException;
}
