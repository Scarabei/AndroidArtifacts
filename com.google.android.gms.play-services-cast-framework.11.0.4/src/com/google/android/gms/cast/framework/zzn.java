package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzn extends zzed implements zzm {
   zzn(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.ICastSession");
   }

   public final void onConnected(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void onConnectionSuspended(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(2, var2);
   }

   public final void onConnectionFailed(ConnectionResult var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zza(ApplicationMetadata var1, String var2, String var3, boolean var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      var5.writeString(var3);
      zzef.zza(var5, var4);
      this.zzb(4, var5);
   }

   public final void zzZ(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(5, var2);
   }

   public final void zzb(boolean var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(6, var3);
   }
}
