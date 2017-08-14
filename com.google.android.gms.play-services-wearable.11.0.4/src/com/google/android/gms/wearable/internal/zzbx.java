package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public final class zzbx implements DataItemAsset {
   private final String zzIi;
   private final String zzBN;

   public zzbx(DataItemAsset var1) {
      this.zzIi = var1.getId();
      this.zzBN = var1.getDataItemKey();
   }

   public final boolean isDataValid() {
      return true;
   }

   public final String getId() {
      return this.zzIi;
   }

   public final String getDataItemKey() {
      return this.zzBN;
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("DataItemAssetEntity[");
      var1.append("@");
      var1.append(Integer.toHexString(this.hashCode()));
      if (this.zzIi == null) {
         var1.append(",noid");
      } else {
         var1.append(",");
         var1.append(this.zzIi);
      }

      var1.append(", key=");
      var1.append(this.zzBN);
      var1.append("]");
      return var1.toString();
   }
}
