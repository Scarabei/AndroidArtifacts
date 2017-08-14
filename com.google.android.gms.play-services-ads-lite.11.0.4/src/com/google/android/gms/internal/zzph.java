package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.ArrayList;
import java.util.List;

public final class zzph extends zzed implements zzpf {
   zzph(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
   }

   public final IObjectWrapper zzei() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(2, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final String getHeadline() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(3, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final List getImages() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = zzef.zzb(var2 = this.zza(4, var1));
      var2.recycle();
      return var3;
   }

   public final String getBody() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(5, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final zzos zzem() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(6, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage")) instanceof zzos ? (zzos)var5 : new zzou(var4));
      var2.recycle();
      return (zzos)var3;
   }

   public final String getCallToAction() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(7, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String getAdvertiser() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(8, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final Bundle getExtras() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(9, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(10, var1);
   }

   public final zzks getVideoController() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzks var3 = zzkt.zzg((var2 = this.zza(11, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void zzc(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12, var2);
   }

   public final boolean zzd(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(13, var2));
      var3.recycle();
      return var4;
   }

   public final void zze(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }
}
