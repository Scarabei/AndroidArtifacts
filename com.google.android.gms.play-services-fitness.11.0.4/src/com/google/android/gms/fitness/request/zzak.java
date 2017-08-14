package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataPoint;

public final class zzak extends com.google.android.gms.fitness.data.zzu {
   private final OnDataPointListener zzaWS;

   private zzak(OnDataPointListener var1) {
      this.zzaWS = (OnDataPointListener)zzbo.zzu(var1);
   }

   public final void zzc(DataPoint var1) throws RemoteException {
      this.zzaWS.onDataPoint(var1);
   }

   // $FF: synthetic method
   zzak(OnDataPointListener var1, zzal var2) {
      this(var1);
   }
}
