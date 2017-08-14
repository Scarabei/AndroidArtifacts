package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;

public final class zzh extends zzed implements zzf {
   zzh(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.IAppVisibilityListener");
   }

   public final int zznm() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(4, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final IObjectWrapper zznn() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void onAppEnteredForeground() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onAppEnteredBackground() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }
}
