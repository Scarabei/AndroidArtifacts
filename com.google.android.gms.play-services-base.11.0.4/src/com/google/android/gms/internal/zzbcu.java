package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbcu extends Handler {
   // $FF: synthetic field
   private zzbcp zzaDN;

   zzbcu(zzbcp var1, Looper var2) {
      this.zzaDN = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 1:
         zzbcp.zzb(this.zzaDN);
         return;
      case 2:
         zzbcp.zza(this.zzaDN);
         return;
      default:
         int var2 = var1.what;
         Log.w("GoogleApiClientImpl", (new StringBuilder(31)).append("Unknown message id: ").append(var2).toString());
      }
   }
}
