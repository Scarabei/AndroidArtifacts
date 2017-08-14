package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkp extends zzed implements zzkn {
   zzkp(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
   }

   public final void initialize() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void setAppVolume(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(2, var2);
   }

   public final void zzu(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(3, var2);
   }

   public final void setAppMuted(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void zzb(IObjectWrapper var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(5, var3);
   }

   public final void zzc(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(6, var3);
   }
}
