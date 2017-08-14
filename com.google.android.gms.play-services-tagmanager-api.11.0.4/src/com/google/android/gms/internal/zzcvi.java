package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcvi extends zzed implements zzcvg {
   zzcvi(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.internal.ITagManagerService");
   }

   public final void zzn(String var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      var4.writeString(var3);
      this.zzb(1, var4);
   }

   public final void zza(String var1, String var2, String var3, zzcvd var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeString(var2);
      var5.writeString(var3);
      zzef.zza(var5, var4);
      this.zzb(2, var5);
   }

   public final void zzCr() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void zza(String var1, Bundle var2, String var3, long var4, boolean var6) throws RemoteException {
      Parcel var7;
      (var7 = this.zzZ()).writeString(var1);
      zzef.zza(var7, var2);
      var7.writeString(var3);
      var7.writeLong(var4);
      zzef.zza(var7, var6);
      this.zzb(101, var7);
   }

   public final void dispatch() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(102, var1);
   }
}
