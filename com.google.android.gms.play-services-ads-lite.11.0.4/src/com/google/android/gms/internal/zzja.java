package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzja extends zziz.zza {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zziv zzAG;
   // $FF: synthetic field
   private String zztD;
   // $FF: synthetic field
   private zzuq zzAH;
   // $FF: synthetic field
   private zziz zzAI;

   zzja(zziz var1, Context var2, zziv var3, String var4, zzuq var5) {
      this.zzAI = var1;
      this.zztF = var2;
      this.zzAG = var3;
      this.zztD = var4;
      this.zzAH = var5;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzjz var2;
      if ((var2 = zziz.zzb(this.zzAI).zza(this.zztF, this.zzAG, this.zztD, this.zzAH, 1)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "banner");
         return new zzln();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createBannerAdManager(zzn.zzw(this.zztF), this.zzAG, this.zztD, this.zzAH, 11020000);
   }
}
