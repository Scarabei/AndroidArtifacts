package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzt;
import java.util.Iterator;
import java.util.List;

final class zzc implements SensorEventDispatcher {
   private final zzt zzaWV;

   zzc(zzt var1) {
      this.zzaWV = (zzt)zzbo.zzu(var1);
   }

   public final void publish(DataPoint var1) throws RemoteException {
      var1.zztK();
      this.zzaWV.zzc(var1);
   }

   public final void publish(List var1) throws RemoteException {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         DataPoint var3 = (DataPoint)var2.next();
         this.publish(var3);
      }

   }
}
