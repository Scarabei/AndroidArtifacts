package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzalp extends zzj {
   private String zzaeT;
   public int zzaeU;
   public int zzNY;
   public int zzNZ;
   public int zzaeV;
   public int zzaeW;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("language", this.zzaeT);
      var1.put("screenColors", this.zzaeU);
      var1.put("screenWidth", this.zzNY);
      var1.put("screenHeight", this.zzNZ);
      var1.put("viewportWidth", this.zzaeV);
      var1.put("viewportHeight", this.zzaeW);
      return zzh(var1);
   }

   public final String getLanguage() {
      return this.zzaeT;
   }

   public final void setLanguage(String var1) {
      this.zzaeT = var1;
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalp var3 = (zzalp)var1;
      int var4;
      if (this.zzaeU != 0) {
         var4 = this.zzaeU;
         var3.zzaeU = this.zzaeU;
      }

      if (this.zzNY != 0) {
         var4 = this.zzNY;
         var3.zzNY = this.zzNY;
      }

      if (this.zzNZ != 0) {
         var4 = this.zzNZ;
         var3.zzNZ = this.zzNZ;
      }

      if (this.zzaeV != 0) {
         var4 = this.zzaeV;
         var3.zzaeV = this.zzaeV;
      }

      if (this.zzaeW != 0) {
         var4 = this.zzaeW;
         var3.zzaeW = this.zzaeW;
      }

      if (!TextUtils.isEmpty(this.zzaeT)) {
         String var5 = this.zzaeT;
         var3.zzaeT = this.zzaeT;
      }

   }
}
