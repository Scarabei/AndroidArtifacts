package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzl extends zzed implements zzk {
   zzl(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.ICastContext");
   }

   public final Bundle zznr() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(1, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final boolean isAppVisible() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(2, var1));
      var2.recycle();
      return var3;
   }

   public final void zza(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zzb(zzf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final zzw zzns() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(5, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager")) instanceof zzw ? (zzw)var5 : new zzx(var4));
      var2.recycle();
      return (zzw)var3;
   }

   public final zzq zznt() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(6, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager")) instanceof zzq ? (zzq)var5 : new zzr(var4));
      var2.recycle();
      return (zzq)var3;
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(7, var1);
   }

   public final void zzx(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(8, var2);
   }

   public final void zzy(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }

   public final IObjectWrapper zznu() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(10, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
