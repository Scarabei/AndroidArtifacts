package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzcq extends IInterface {
   void initialize(IObjectWrapper var1, zzcn var2, zzce var3) throws RemoteException;

   void preview(Intent var1, IObjectWrapper var2) throws RemoteException;

   void previewIntent(Intent var1, IObjectWrapper var2, IObjectWrapper var3, zzcn var4, zzce var5) throws RemoteException;
}
