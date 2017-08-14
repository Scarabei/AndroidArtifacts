package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzalr extends zzj {
   private String mCategory;
   private String zzaeX;
   private String zzaeY;
   private long zzaeZ;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("category", this.mCategory);
      var1.put("action", this.zzaeX);
      var1.put("label", this.zzaeY);
      var1.put("value", this.zzaeZ);
      return zzh(var1);
   }

   public final String getCategory() {
      return this.mCategory;
   }

   public final String getAction() {
      return this.zzaeX;
   }

   public final String getLabel() {
      return this.zzaeY;
   }

   public final long getValue() {
      return this.zzaeZ;
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalr var3 = (zzalr)var1;
      String var4;
      if (!TextUtils.isEmpty(this.mCategory)) {
         var4 = this.mCategory;
         var3.mCategory = this.mCategory;
      }

      if (!TextUtils.isEmpty(this.zzaeX)) {
         var4 = this.zzaeX;
         var3.zzaeX = this.zzaeX;
      }

      if (!TextUtils.isEmpty(this.zzaeY)) {
         var4 = this.zzaeY;
         var3.zzaeY = this.zzaeY;
      }

      if (this.zzaeZ != 0L) {
         long var6 = this.zzaeZ;
         var3.zzaeZ = this.zzaeZ;
      }

   }
}
