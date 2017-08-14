package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.ArrayList;
import java.util.List;

public final class zzvh extends zzed implements zzvf {
   zzvh(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
   }

   public final String getHeadline() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final List getImages() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = zzef.zzb(var2 = this.zza(3, var1));
      var2.recycle();
      return var3;
   }

   public final String getBody() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(4, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final zzos zzem() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzos var3 = zzot.zzi((var2 = this.zza(5, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final String getCallToAction() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(6, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String getAdvertiser() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(7, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void recordImpression() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void zzl(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }

   public final void zzm(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(10, var2);
   }

   public final boolean getOverrideImpressionRecording() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final boolean getOverrideClickHandling() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(12, var1));
      var2.recycle();
      return var3;
   }

   public final Bundle getExtras() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(13, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zzn(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final IObjectWrapper zzfw() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(15, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final zzks getVideoController() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzks var3 = zzkt.zzg((var2 = this.zza(16, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
