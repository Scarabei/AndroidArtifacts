package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class cm implements Result {
   private final Status mStatus;
   private final int zzBM;
   private final cn zzbKG;
   private final dj zzbKH;

   public cm(Status var1, int var2) {
      this(var1, var2, (cn)null, (dj)null);
   }

   public cm(Status var1, int var2, cn var3, dj var4) {
      this.mStatus = var1;
      this.zzBM = var2;
      this.zzbKG = var3;
      this.zzbKH = var4;
   }

   public final cn zzCQ() {
      return this.zzbKG;
   }

   public final dj zzCR() {
      return this.zzbKH;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final int getSource() {
      return this.zzBM;
   }

   public final String zzCS() {
      if (this.zzBM == 0) {
         return "Network";
      } else if (this.zzBM == 1) {
         return "Saved file on disk";
      } else if (this.zzBM == 2) {
         return "Default resource";
      } else {
         throw new IllegalStateException("Resource source is unknown.");
      }
   }
}
