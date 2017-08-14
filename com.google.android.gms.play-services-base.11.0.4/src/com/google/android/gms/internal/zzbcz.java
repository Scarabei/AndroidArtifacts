package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbcz extends Handler {
   // $FF: synthetic field
   private zzbcx zzaEa;

   zzbcz(zzbcx var1, Looper var2) {
      this.zzaEa = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 1:
         ((zzbcy)var1.obj).zzc(this.zzaEa);
         return;
      case 2:
         throw (RuntimeException)var1.obj;
      default:
         int var2 = var1.what;
         Log.w("GACStateManager", (new StringBuilder(31)).append("Unknown message id: ").append(var2).toString());
      }
   }
}
