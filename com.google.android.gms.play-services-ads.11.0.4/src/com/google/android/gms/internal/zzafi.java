package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;

@zzzn
final class zzafi {
   private long zzYi = -1L;
   private long zzYj = -1L;

   public final long zzhf() {
      return this.zzYj;
   }

   public final void zzhg() {
      this.zzYj = SystemClock.elapsedRealtime();
   }

   public final void zzhh() {
      this.zzYi = SystemClock.elapsedRealtime();
   }

   public final Bundle toBundle() {
      Bundle var1;
      (var1 = new Bundle()).putLong("topen", this.zzYi);
      var1.putLong("tclose", this.zzYj);
      return var1;
   }
}
