package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.zzac;
import com.google.android.gms.cast.framework.zzi;
import com.google.android.gms.cast.framework.zzk;
import com.google.android.gms.cast.framework.zzm;
import com.google.android.gms.cast.framework.zzs;
import com.google.android.gms.cast.framework.zzu;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.zzd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public interface zzaum extends IInterface {
   zzk zza(IObjectWrapper var1, CastOptions var2, zzauo var3, Map var4) throws RemoteException;

   zzu zza(String var1, String var2, zzac var3) throws RemoteException;

   zzm zza(CastOptions var1, IObjectWrapper var2, zzi var3) throws RemoteException;

   zzd zza(IObjectWrapper var1, IObjectWrapper var2, IObjectWrapper var3, CastMediaOptions var4) throws RemoteException;

   zzs zza(IObjectWrapper var1, IObjectWrapper var2, IObjectWrapper var3) throws RemoteException;

   zzavj zza(IObjectWrapper var1, zzavl var2, int var3, int var4, boolean var5, long var6, int var8, int var9, int var10) throws RemoteException;
}
