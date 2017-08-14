package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.Map;

public interface zzcn extends IInterface {
   void logEventInternalNoInterceptor(String var1, String var2, Bundle var3, long var4) throws RemoteException;

   Map zzBh() throws RemoteException;

   void zza(zzck var1) throws RemoteException;

   void zza(zzch var1) throws RemoteException;
}
