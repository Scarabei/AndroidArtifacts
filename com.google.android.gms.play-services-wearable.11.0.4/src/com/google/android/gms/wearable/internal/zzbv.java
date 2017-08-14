package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public final class zzbv implements DataEvent {
   private int zzamr;
   private DataItem zzbSB;

   public zzbv(DataEvent var1) {
      this.zzamr = var1.getType();
      this.zzbSB = (DataItem)var1.getDataItem().freeze();
   }

   public final boolean isDataValid() {
      return true;
   }

   public final DataItem getDataItem() {
      return this.zzbSB;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final String toString() {
      String var1 = this.getType() == 1 ? "changed" : (this.getType() == 2 ? "deleted" : "unknown");
      String var2 = String.valueOf(this.getDataItem());
      return (new StringBuilder(35 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("DataEventEntity{ type=").append(var1).append(", dataitem=").append(var2).append(" }").toString();
   }
}
