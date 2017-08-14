package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.GoalsReadRequest;

public final class zzbwm extends zzed implements zzbwl {
   zzbwm(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
   }

   public final void zza(GoalsReadRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }
}
