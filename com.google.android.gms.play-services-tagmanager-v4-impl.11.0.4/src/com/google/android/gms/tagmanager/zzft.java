package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler.Callback;

final class zzft implements Callback {
   // $FF: synthetic field
   private zzfs zzbGQ;

   zzft(zzfs var1) {
      this.zzbGQ = var1;
      super();
   }

   public final boolean handleMessage(Message var1) {
      if (1 == var1.what && zzfo.zzBX().equals(var1.obj)) {
         this.zzbGQ.zzbGP.dispatch();
         if (!zzfo.zzb(this.zzbGQ.zzbGP)) {
            this.zzbGQ.zzs((long)zzfo.zzc(this.zzbGQ.zzbGP));
         }
      }

      return true;
   }
}
