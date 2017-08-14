package com.google.android.gms.internal;

import java.util.List;

@zzzn
public final class zzya extends zzafp {
   private final zzxy zzQP;
   private final zzaai zzQR;
   private final zzafg zzQQ;

   public zzya(zzafg var1, zzxy var2) {
      this.zzQQ = var1;
      this.zzQR = this.zzQQ.zzXY;
      this.zzQP = var2;
   }

   public final void zzbd() {
      zzaff var1 = new zzaff(this.zzQQ.zzUj.zzSz, (zzaka)null, (List)null, 0, (List)null, (List)null, this.zzQR.orientation, this.zzQR.zzMg, this.zzQQ.zzUj.zzSC, false, (zzua)null, (zzut)null, (String)null, (zzub)null, (zzud)null, this.zzQR.zzTp, this.zzQQ.zzvX, this.zzQR.zzTn, this.zzQQ.zzXR, this.zzQR.zzTs, this.zzQR.zzTt, this.zzQQ.zzXL, (zzoa)null, (zzaee)null, (List)null, (List)null, this.zzQQ.zzXY.zzTG, this.zzQQ.zzXY.zzTH, (String)null, (List)null, (String)null, this.zzQQ.zzXX);
      zzagz.zzZr.post(new zzyb(this, var1));
   }

   public final void onStop() {
   }

   // $FF: synthetic method
   static zzxy zza(zzya var0) {
      return var0.zzQP;
   }
}
