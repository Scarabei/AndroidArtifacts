package com.google.android.gms.internal;

import android.os.Bundle;

@zzzn
public final class zzafn {
   private final Object mLock;
   private int zzYS;
   private int zzYT;
   private final zzafk zzvt;
   private final String zzYb;

   public zzafn(String var1) {
      this(com.google.android.gms.ads.internal.zzbs.zzbD(), var1);
   }

   private zzafn(zzafk var1, String var2) {
      this.mLock = new Object();
      this.zzvt = var1;
      this.zzYb = var2;
   }

   public final void zzg(int var1, int var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzYS = var1;
         this.zzYT = var2;
         this.zzvt.zza(this.zzYb, this);
      }
   }

   public final Bundle toBundle() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         Bundle var2;
         (var2 = new Bundle()).putInt("pmnli", this.zzYS);
         var2.putInt("pmnll", this.zzYT);
         return var2;
      }
   }
}
