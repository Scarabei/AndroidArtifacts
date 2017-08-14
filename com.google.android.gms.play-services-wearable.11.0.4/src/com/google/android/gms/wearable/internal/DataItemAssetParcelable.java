package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.wearable.DataItemAsset;

@KeepName
public class DataItemAssetParcelable extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable, DataItemAsset {
   public static final Creator CREATOR = new zzby();
   private final String zzIi;
   private final String zzBN;

   DataItemAssetParcelable(String var1, String var2) {
      this.zzIi = var1;
      this.zzBN = var2;
   }

   public DataItemAssetParcelable(DataItemAsset var1) {
      this.zzIi = (String)com.google.android.gms.common.internal.zzbo.zzu(var1.getId());
      this.zzBN = (String)com.google.android.gms.common.internal.zzbo.zzu(var1.getDataItemKey());
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getDataItemKey(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean isDataValid() {
      return true;
   }

   public String getId() {
      return this.zzIi;
   }

   public String getDataItemKey() {
      return this.zzBN;
   }

   public String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("DataItemAssetParcelable[");
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
