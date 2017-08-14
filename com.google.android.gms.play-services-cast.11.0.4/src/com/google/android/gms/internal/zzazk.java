package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzazk extends zzed implements zzazj {
   zzazk(IBinder var1) {
      super(var1, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
   }

   public final void disconnect() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(3, var1);
   }

   public final void zza(zzazh var1, zzazl var2, String var3, String var4, Bundle var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      var6.writeString(var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzc(7, var6);
   }

   public final void zza(zzazh var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzc(5, var3);
   }

   public final void zza(zzazh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(6, var2);
   }
}
