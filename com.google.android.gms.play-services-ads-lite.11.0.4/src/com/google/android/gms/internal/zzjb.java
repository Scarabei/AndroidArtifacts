package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzjb extends zziz.zza {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zziv zzAG;
   // $FF: synthetic field
   private String zztD;
   // $FF: synthetic field
   private zziz zzAI;

   zzjb(zziz var1, Context var2, zziv var3, String var4) {
      this.zzAI = var1;
      this.zztF = var2;
      this.zzAG = var3;
      this.zztD = var4;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzjz var2;
      if ((var2 = zziz.zzb(this.zzAI).zza(this.zztF, this.zzAG, this.zztD, (zzuq)null, 3)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "search");
         return new zzln();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createSearchAdManager(zzn.zzw(this.zztF), this.zzAG, this.zztD, 11020000);
   }
}
