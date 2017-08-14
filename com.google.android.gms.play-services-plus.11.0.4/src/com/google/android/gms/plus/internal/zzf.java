package com.google.android.gms.plus.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzao;
import java.util.List;

public interface zzf extends IInterface {
   String getAccountName() throws RemoteException;

   void zzAe() throws RemoteException;

   zzao zza(zzb var1, int var2, int var3, int var4, String var5) throws RemoteException;

   void zza(zzb var1) throws RemoteException;

   void zza(zzb var1, List var2) throws RemoteException;
}
