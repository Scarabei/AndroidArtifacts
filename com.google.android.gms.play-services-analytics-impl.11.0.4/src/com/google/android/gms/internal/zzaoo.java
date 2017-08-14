package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;

final class zzaoo {
   private final zze zzvw;
   private long mStartTime;

   public zzaoo(zze var1) {
      zzbo.zzu(var1);
      this.zzvw = var1;
   }

   public zzaoo(zze var1, long var2) {
      zzbo.zzu(var1);
      this.zzvw = var1;
      this.mStartTime = var2;
   }

   public final void start() {
      this.mStartTime = this.zzvw.elapsedRealtime();
   }

   public final void clear() {
      this.mStartTime = 0L;
   }

   public final boolean zzu(long var1) {
      if (this.mStartTime == 0L) {
         return true;
      } else {
         return this.zzvw.elapsedRealtime() - this.mStartTime > var1;
      }
   }
}
