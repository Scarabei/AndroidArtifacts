package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzalw extends zzj {
   public String zzafs;
   public long zzaft;
   public String mCategory;
   public String zzaeY;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("variableName", this.zzafs);
      var1.put("timeInMillis", this.zzaft);
      var1.put("category", this.mCategory);
      var1.put("label", this.zzaeY);
      return zzh(var1);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalw var3 = (zzalw)var1;
      String var4;
      if (!TextUtils.isEmpty(this.zzafs)) {
         var4 = this.zzafs;
         var3.zzafs = this.zzafs;
      }

      if (this.zzaft != 0L) {
         long var6 = this.zzaft;
         var3.zzaft = this.zzaft;
      }

      if (!TextUtils.isEmpty(this.mCategory)) {
         var4 = this.mCategory;
         var3.mCategory = this.mCategory;
      }

      if (!TextUtils.isEmpty(this.zzaeY)) {
         var4 = this.zzaeY;
         var3.zzaeY = this.zzaeY;
      }

   }
}
