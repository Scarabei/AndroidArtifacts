package com.google.android.gms.tagmanager;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;

final class zzbg extends zzco {
   // $FF: synthetic field
   private AppMeasurement zzbEE;

   zzbg(AppMeasurement var1) {
      this.zzbEE = var1;
      super();
   }

   public final void logEventInternalNoInterceptor(String var1, String var2, Bundle var3, long var4) {
      this.zzbEE.logEventInternalNoInterceptor(var1, var2, var3, var4);
   }

   public final Map zzBh() {
      return this.zzbEE.getUserProperties(true);
   }

   public final void zza(zzck var1) {
      this.zzbEE.setEventInterceptor(new zzbh(this, var1));
   }

   public final void zza(zzch var1) {
      this.zzbEE.registerOnMeasurementEventListener(new zzbi(this, var1));
   }
}
