package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public final class zzbw extends com.google.android.gms.common.data.zzc implements DataEvent {
   private final int zzbcP;

   public zzbw(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcP = var3;
   }

   public final DataItem getDataItem() {
      return new zzcd(this.zzaCX, this.zzaFx, this.zzbcP);
   }

   public final int getType() {
      return this.getInteger("event_type");
   }

   public final String toString() {
      String var1 = this.getType() == 1 ? "changed" : (this.getType() == 2 ? "deleted" : "unknown");
      String var2 = String.valueOf(this.getDataItem());
      return (new StringBuilder(32 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("DataEventRef{ type=").append(var1).append(", dataitem=").append(var2).append(" }").toString();
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzbv(this);
   }
}
