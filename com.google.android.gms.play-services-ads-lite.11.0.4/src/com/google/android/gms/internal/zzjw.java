package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public final class zzjw extends zzed implements zzju {
   zzjw(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
   }

   public final zzjr zzaZ() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(1, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader")) instanceof zzjr ? (zzjr)var5 : new zzjt(var4));
      var2.recycle();
      return (zzjr)var3;
   }

   public final void zzb(zzjo var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zza(zzpn var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zza(zzpq var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void zza(String var1, zzpw var2, zzpt var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(5, var4);
   }

   public final void zza(zzon var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(6, var2);
   }

   public final void zzb(zzkk var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(7, var2);
   }

   public final void zza(zzpz var1, zziv var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(8, var3);
   }

   public final void zza(PublisherAdViewOptions var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }
}
