package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import java.util.ArrayList;
import java.util.List;

public final class zzl extends zzed implements zzj {
   zzl(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
   }

   public final int getActiveLevelIndex() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(1, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final int getDefaultLevelIndex() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(2, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final List getLevels() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = (var2 = this.zza(3, var1)).createBinderArrayList();
      var2.recycle();
      return var3;
   }

   public final boolean isUnderground() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(4, var1));
      var2.recycle();
      return var3;
   }

   public final boolean zzb(zzj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(5, var2));
      var3.recycle();
      return var4;
   }

   public final int hashCodeRemote() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(6, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
