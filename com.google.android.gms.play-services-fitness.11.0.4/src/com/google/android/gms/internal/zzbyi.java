package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzbyi extends zzbvt {
   // $FF: synthetic field
   private zzbyh zzaVP;

   zzbyi(zzbyh var1) {
      this.zzaVP = var1;
      super();
   }

   public final void zza(DailyTotalResult var1) throws RemoteException {
      this.zzaVP.setResult(var1);
   }
}
