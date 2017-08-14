package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzt extends zzed implements zzs {
   zzt(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.IReconnectionService");
   }

   public final void onCreate() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final int onStartCommand(Intent var1, int var2, int var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeInt(var2);
      var4.writeInt(var3);
      Parcel var5;
      int var6 = (var5 = this.zza(2, var4)).readInt();
      var5.recycle();
      return var6;
   }

   public final IBinder onBind(Intent var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IBinder var4 = (var3 = this.zza(3, var2)).readStrongBinder();
      var3.recycle();
      return var4;
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final IObjectWrapper zznv() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(5, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
