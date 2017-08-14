package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbo;

public class BooleanResult implements Result {
   private final Status mStatus;
   private final boolean zzaAI;

   public BooleanResult(Status var1, boolean var2) {
      this.mStatus = (Status)zzbo.zzb(var1, "Status must not be null");
      this.zzaAI = var2;
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public boolean getValue() {
      return this.zzaAI;
   }

   public final int hashCode() {
      return (527 + this.mStatus.hashCode()) * 31 + (this.zzaAI ? 1 : 0);
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof BooleanResult)) {
         return false;
      } else {
         BooleanResult var2 = (BooleanResult)var1;
         return this.mStatus.equals(var2.mStatus) && this.zzaAI == var2.zzaAI;
      }
   }
}
