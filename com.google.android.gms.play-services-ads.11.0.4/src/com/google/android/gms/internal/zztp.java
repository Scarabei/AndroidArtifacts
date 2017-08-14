package com.google.android.gms.internal;

import android.os.Bundle;

@zzzn
public final class zztp {
   private static zztp zzKC = new zztp();
   private int zzKD;
   private int zzKE;
   private int zzKF;
   private int zzKG;
   private int zzKH;

   public static zztp zzeN() {
      return zzKC;
   }

   final void zzn(int var1) {
      this.zzKD += var1;
   }

   final void zzeO() {
      ++this.zzKE;
   }

   final void zzeP() {
      ++this.zzKF;
   }

   final void zzeQ() {
      ++this.zzKG;
   }

   final void zzeR() {
      ++this.zzKH;
   }

   public final int zzeS() {
      return this.zzKE;
   }

   public final int zzeT() {
      return this.zzKF;
   }

   public final int zzeU() {
      return this.zzKG;
   }

   public final int zzeV() {
      return this.zzKH;
   }

   public final Bundle asBundle() {
      Bundle var1;
      (var1 = new Bundle()).putInt("ipl", this.zzKD);
      var1.putInt("ipds", this.zzKE);
      var1.putInt("ipde", this.zzKF);
      var1.putInt("iph", this.zzKG);
      var1.putInt("ipm", this.zzKH);
      return var1;
   }
}
