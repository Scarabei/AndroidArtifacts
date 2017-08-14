package com.google.android.gms.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzf extends Handler {
   // $FF: synthetic field
   private zze zzbhn;

   zzf(zze var1, Looper var2) {
      this.zzbhn = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      this.zzbhn.zzc(var1);
   }
}
