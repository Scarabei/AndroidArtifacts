package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.zzafp;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzzn;

@zzzn
final class zzan extends zzafp {
   private final int zzuo;
   // $FF: synthetic field
   final zzal zzun;

   public zzan(zzal var1, int var2) {
      this.zzun = var1;
      this.zzuo = var2;
   }

   public final void zzbd() {
      zzap var1 = new zzap(this.zzun.zzsP.zzur, this.zzun.zzba(), zzal.zza(this.zzun), zzal.zzb(this.zzun), this.zzun.zzsP.zzur ? this.zzuo : -1, zzal.zzc(this.zzun));
      int var2;
      if ((var2 = this.zzun.zzsP.zzvY.zzPg.getRequestedOrientation()) == -1) {
         var2 = this.zzun.zzsP.zzvY.orientation;
      }

      AdOverlayInfoParcel var3 = new AdOverlayInfoParcel(this.zzun, this.zzun, this.zzun, this.zzun.zzsP.zzvY.zzPg, var2, this.zzun.zzsP.zzvT, this.zzun.zzsP.zzvY.zzTt, var1);
      zzagz.zzZr.post(new zzao(this, var3));
   }

   public final void onStop() {
   }
}
