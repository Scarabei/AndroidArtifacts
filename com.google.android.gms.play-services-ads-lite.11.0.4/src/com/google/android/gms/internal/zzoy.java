package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public final class zzoy extends zzed implements zzow {
   zzoy(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
   }

   public final void zzd(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }

   public final IObjectWrapper zzL(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IObjectWrapper var4 = zza.zzM((var3 = this.zza(2, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final void zze(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void zzb(IObjectWrapper var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(5, var3);
   }
}
