package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

final class cg {
   private Status mStatus;
   private Object mData;
   private long zzbKC;

   public cg(Status var1, Object var2, long var3) {
      this.mStatus = var1;
      this.mData = var2;
      this.zzbKC = var3;
   }

   public final void zzJ(Status var1) {
      this.mStatus = var1;
   }

   public final long zzCO() {
      return this.zzbKC;
   }

   public final void zzak(long var1) {
      this.zzbKC = var1;
   }

   public final void zzP(Object var1) {
      this.mData = var1;
   }
}
