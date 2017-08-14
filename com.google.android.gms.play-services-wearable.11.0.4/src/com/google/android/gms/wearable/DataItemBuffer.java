package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.zzcd;

public class DataItemBuffer extends com.google.android.gms.common.data.zzg implements Result {
   private final Status mStatus;

   public DataItemBuffer(DataHolder var1) {
      super(var1);
      this.mStatus = new Status(var1.getStatusCode());
   }

   protected final String zzqS() {
      return "path";
   }

   public Status getStatus() {
      return this.mStatus;
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new zzcd(this.zzaCX, var1, var2);
   }
}
