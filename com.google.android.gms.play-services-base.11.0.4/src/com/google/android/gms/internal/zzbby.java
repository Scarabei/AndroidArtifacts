package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public class zzbby implements Releasable, Result {
   private Status mStatus;
   protected final DataHolder zzaCX;

   protected zzbby(DataHolder var1, Status var2) {
      this.mStatus = var2;
      this.zzaCX = var1;
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public void release() {
      if (this.zzaCX != null) {
         this.zzaCX.close();
      }

   }
}
