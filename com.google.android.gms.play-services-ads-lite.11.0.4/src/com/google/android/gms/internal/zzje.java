package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzje extends zziz.zza {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zziz zzAI;

   zzje(zziz var1, Context var2) {
      this.zzAI = var1;
      this.zztF = var2;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzkn var2;
      if ((var2 = zziz.zzd(this.zzAI).zzh(this.zztF)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "mobile_ads_settings");
         return new zzlp();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.getMobileAdsSettingsManagerWithClientJarVersion(zzn.zzw(this.zztF), 11020000);
   }
}
