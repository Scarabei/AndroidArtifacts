package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzo extends zzed implements zzm {
   zzo(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
   }

   public final String getName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(1, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final String getShortName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void activate() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final boolean zza(zzm var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(4, var2));
      var3.recycle();
      return var4;
   }

   public final int hashCodeRemote() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(5, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
