package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;

final class zzbi implements OnEventListener {
   // $FF: synthetic field
   private zzch zzbEG;

   zzbi(zzbg var1, zzch var2) {
      this.zzbEG = var2;
      super();
   }

   public final void onEvent(String var1, String var2, Bundle var3, long var4) {
      try {
         this.zzbEG.onEvent(var1, var2, var3, var4);
      } catch (RemoteException var7) {
         throw new IllegalStateException(var7);
      }
   }
}
