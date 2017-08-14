package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzng extends zzed implements zzne {
   zzng(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
   }

   public final String zzdX() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(1, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String getContent() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void zzi(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void recordClick() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void recordImpression() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }
}
