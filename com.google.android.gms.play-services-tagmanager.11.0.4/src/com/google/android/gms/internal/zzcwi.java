package com.google.android.gms.internal;

import android.os.Message;
import android.os.Handler.Callback;

final class zzcwi implements Callback {
   // $FF: synthetic field
   private zzcwh zzbJb;

   zzcwi(zzcwh var1) {
      this.zzbJb = var1;
      super();
   }

   public final boolean handleMessage(Message var1) {
      if (1 == var1.what && zzcwd.zzBX().equals(var1.obj)) {
         this.zzbJb.zzbJa.dispatch();
         if (!zzcwd.zzb(this.zzbJb.zzbJa)) {
            this.zzbJb.zzs((long)zzcwd.zzc(this.zzbJb.zzbJa));
         }
      }

      return true;
   }
}
