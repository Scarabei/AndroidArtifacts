package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbkk extends zzed implements zzbkj {
   zzbkk(IBinder var1) {
      super(var1, "com.google.android.gms.contextmanager.internal.IContextManagerService");
   }

   public final void zza(zzbkh var1, String var2, String var3, String var4, zzbjj var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeString(var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzb(13, var6);
   }

   public final void zza(zzbkh var1, String var2, String var3, String var4, zzaub var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeString(var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzb(15, var6);
   }

   public final void zza(zzbkh var1, String var2, String var3, String var4, zzbja var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeString(var2);
      var6.writeString(var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzb(16, var6);
   }
}
