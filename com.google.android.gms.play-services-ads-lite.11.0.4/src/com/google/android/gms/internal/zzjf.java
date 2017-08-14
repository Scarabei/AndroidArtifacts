package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzn;

final class zzjf extends zziz.zza {
   // $FF: synthetic field
   private FrameLayout zzAJ;
   // $FF: synthetic field
   private FrameLayout zzAK;
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zziz zzAI;

   zzjf(zziz var1, FrameLayout var2, FrameLayout var3, Context var4) {
      this.zzAI = var1;
      this.zzAJ = var2;
      this.zzAK = var3;
      this.zztF = var4;
      super();
   }

   // $FF: synthetic method
   public final Object zzdo() throws RemoteException {
      zzow var2;
      if ((var2 = zziz.zze(this.zzAI).zzb(this.zztF, this.zzAJ, this.zzAK)) != null) {
         return var2;
      } else {
         zziz.zza(this.zzAI, this.zztF, "native_ad_view_delegate");
         return new zzlq();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzkh var1) throws RemoteException {
      return var1.createNativeAdViewDelegate(zzn.zzw(this.zzAJ), zzn.zzw(this.zzAK));
   }
}
