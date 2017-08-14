package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafp;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzzn;

@zzzn
final class zzs extends zzafp {
   // $FF: synthetic field
   final zzm zzOY;

   private zzs(zzm var1) {
      this.zzOY = var1;
   }

   public final void zzbd() {
      Bitmap var1;
      if ((var1 = zzbs.zzbU().zza(this.zzOY.zzOG.zzPo.zzuw)) != null) {
         Drawable var2 = zzbs.zzbB().zza(zzm.zza(this.zzOY), var1, this.zzOY.zzOG.zzPo.zzuu, this.zzOY.zzOG.zzPo.zzuv);
         zzagz.zzZr.post(new zzt(this, var2));
      }

   }

   public final void onStop() {
   }

   // $FF: synthetic method
   zzs(zzm var1, zzn var2) {
      this(var1);
   }
}
