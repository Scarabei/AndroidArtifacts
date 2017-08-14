package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzq extends zzed implements zzp {
   zzq(IBinder var1) {
      super(var1, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
   }

   public final void zza(PlaceFilter var1, zzat var2, zzv var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(6, var4);
   }

   public final void zza(PlaceReport var1, zzat var2, zzv var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(7, var4);
   }
}
