package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzv extends zzed implements zzu {
   zzv(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.ISession");
   }

   public final IObjectWrapper zznw() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final String getCategory() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String getSessionId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(3, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String zznx() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(4, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final boolean isConnected() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(5, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isConnecting() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(6, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isDisconnecting() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(7, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isDisconnected() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(8, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isResuming() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(9, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isSuspended() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(10, var1));
      var2.recycle();
      return var3;
   }

   public final void notifySessionStarted(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(11, var2);
   }

   public final void notifyFailedToStartSession(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(12, var2);
   }

   public final void notifySessionEnded(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(13, var2);
   }

   public final void notifySessionResumed(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final void notifyFailedToResumeSession(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(15, var2);
   }

   public final void notifySessionSuspended(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(16, var2);
   }
}
