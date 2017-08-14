package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzx extends zzed implements zzw {
   zzx(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.ISessionManager");
   }

   public final IObjectWrapper zzny() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void zza(zzy var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zzb(zzy var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zza(zzo var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void zzb(zzo var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void zzb(boolean var1, boolean var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), true);
      zzef.zza(var3, var2);
      this.zzb(6, var3);
   }

   public final IObjectWrapper zznu() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(7, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final int getCastState() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(8, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
