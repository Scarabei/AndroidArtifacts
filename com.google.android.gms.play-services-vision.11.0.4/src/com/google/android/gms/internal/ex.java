package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public interface ex extends IInterface {
   Barcode[] zza(IObjectWrapper var1, fc var2) throws RemoteException;

   Barcode[] zzb(IObjectWrapper var1, fc var2) throws RemoteException;

   void zzDP() throws RemoteException;
}
