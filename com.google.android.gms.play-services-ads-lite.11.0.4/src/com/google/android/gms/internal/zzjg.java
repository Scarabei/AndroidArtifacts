package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzjg extends zziz.zza {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzuq zzAH;
   // $FF: synthetic field
   private zziz zzAI;

   zzjg(zziz var1, Context var2, zzuq var3) {
      this.zzAI = var1;
      this.zztF = var2;
      this.zzAH = var3;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzacy var2;
      if ((var2 = zziz.zzf(this.zzAI).zza(this.zztF, this.zzAH)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "rewarded_video");
         return new zzlr();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createRewardedVideoAd(zzn.zzw(this.zztF), this.zzAH, 11020000);
   }
}
