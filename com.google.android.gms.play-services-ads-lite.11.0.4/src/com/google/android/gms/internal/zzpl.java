package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.ArrayList;
import java.util.List;

public final class zzpl extends zzed implements zzpj {
   zzpl(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
   }

   public final String zzP(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      String var4 = (var3 = this.zza(1, var2)).readString();
      var3.recycle();
      return var4;
   }

   public final zzos zzQ(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IBinder var5;
      IInterface var6;
      Object var4 = (var5 = (var3 = this.zza(2, var2)).readStrongBinder()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage")) instanceof zzos ? (zzos)var6 : new zzou(var5));
      var3.recycle();
      return (zzos)var4;
   }

   public final List getAvailableAssetNames() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = (var2 = this.zza(3, var1)).createStringArrayList();
      var2.recycle();
      return var3;
   }

   public final String getCustomTemplateId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(4, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void performClick(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(5, var2);
   }

   public final void recordImpression() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final zzks getVideoController() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzks var3 = zzkt.zzg((var2 = this.zza(7, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final IObjectWrapper zzen() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(9, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final boolean zzj(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(10, var2));
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzei() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(11, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
