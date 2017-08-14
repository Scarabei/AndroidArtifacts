package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemAsset;

public final class zzbz extends com.google.android.gms.common.data.zzc implements DataItemAsset {
   public zzbz(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getId() {
      return this.getString("asset_id");
   }

   public final String getDataItemKey() {
      return this.getString("asset_key");
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzbx(this);
   }
}
