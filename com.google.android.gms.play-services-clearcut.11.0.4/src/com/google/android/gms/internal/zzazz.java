package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzazz extends zzbay {
   private final zzazu zzazM;

   zzazz(zzazu var1, GoogleApiClient var2) {
      super(zzazn.API, var2);
      this.zzazM = var1;
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbab var3 = (zzbab)var1;
      zzazz var2 = this;
      zzbaa var4 = new zzbaa(this);

      try {
         zzazu var6 = var2.zzazM;
         if (var2.zzazM.zzazu != null && var6.zzazB.zzctX.length == 0) {
            var6.zzazB.zzctX = var6.zzazu.zzoU();
         }

         if (var6.zzazL != null && var6.zzazB.zzcue.length == 0) {
            var6.zzazB.zzcue = var6.zzazL.zzoU();
         }

         var6.zzazF = adp.zzc(var6.zzazB);
      } catch (RuntimeException var7) {
         Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", var7);
         this.zzr(new Status(10, "MessageProducer"));
         return;
      }

      ((zzbaf)var3.zzrf()).zza(var4, this.zzazM);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
