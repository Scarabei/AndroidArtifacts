package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

public final class zzbvu extends zzed implements zzbvs {
   zzbvu(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IDailyTotalCallback");
   }

   public final void zza(DailyTotalResult var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
