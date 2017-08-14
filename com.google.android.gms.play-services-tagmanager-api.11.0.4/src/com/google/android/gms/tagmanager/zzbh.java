package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement.EventInterceptor;

final class zzbh implements EventInterceptor {
   // $FF: synthetic field
   private zzck zzbEF;

   zzbh(zzbg var1, zzck var2) {
      this.zzbEF = var2;
      super();
   }

   public final void interceptEvent(String var1, String var2, Bundle var3, long var4) {
      try {
         this.zzbEF.interceptEvent(var1, var2, var3, var4);
      } catch (RemoteException var7) {
         throw new IllegalStateException(var7);
      }
   }
}
