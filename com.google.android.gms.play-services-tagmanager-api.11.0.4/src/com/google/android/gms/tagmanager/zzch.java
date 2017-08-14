package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzch extends IInterface {
   void onEvent(String var1, String var2, Bundle var3, long var4) throws RemoteException;
}
