package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzjd extends zziz.zza {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private String zztD;
   // $FF: synthetic field
   private zzuq zzAH;
   // $FF: synthetic field
   private zziz zzAI;

   zzjd(zziz var1, Context var2, String var3, zzuq var4) {
      this.zzAI = var1;
      this.zztF = var2;
      this.zztD = var3;
      this.zzAH = var4;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzju var2;
      if ((var2 = zziz.zzc(this.zzAI).zza(this.zztF, this.zztD, this.zzAH)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "native_ad");
         return new zzlj();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createAdLoaderBuilder(zzn.zzw(this.zztF), this.zztD, this.zzAH, 11020000);
   }
}
