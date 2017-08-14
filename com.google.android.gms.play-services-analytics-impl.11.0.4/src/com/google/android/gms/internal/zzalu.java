package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;
import java.util.UUID;

public final class zzalu extends zzj {
   private String zzul;
   private int zzafk;
   private int zzafl;
   private String zzafm;
   private String zzafn;
   private boolean zzafo;
   private boolean zzafp;

   public zzalu() {
      this(false);
   }

   private zzalu(boolean var1) {
      int var10002;
      UUID var2;
      int var3;
      if ((var3 = (int)((var2 = UUID.randomUUID()).getLeastSignificantBits() & 2147483647L)) != 0) {
         var10002 = var3;
      } else if ((var3 = (int)(var2.getMostSignificantBits() & 2147483647L)) != 0) {
         var10002 = var3;
      } else {
         Log.e("GAv4", "UUID.randomUUID() returned 0.");
         var10002 = Integer.MAX_VALUE;
      }

      this(false, var10002);
   }

   private zzalu(boolean var1, int var2) {
      if (var2 == 0) {
         throw new IllegalArgumentException("Given Integer is zero");
      } else {
         this.zzafk = var2;
         this.zzafp = false;
      }
   }

   public final String zzkd() {
      return this.zzul;
   }

   public final int zzke() {
      return this.zzafk;
   }

   public final String zzkf() {
      return this.zzafn;
   }

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("screenName", this.zzul);
      var1.put("interstitial", this.zzafo);
      var1.put("automatic", this.zzafp);
      var1.put("screenId", this.zzafk);
      var1.put("referrerScreenId", this.zzafl);
      var1.put("referrerScreenName", this.zzafm);
      var1.put("referrerUri", this.zzafn);
      return zzh(var1);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalu var3 = (zzalu)var1;
      String var5;
      if (!TextUtils.isEmpty(this.zzul)) {
         var5 = this.zzul;
         var3.zzul = var5;
      }

      int var6;
      if (this.zzafk != 0) {
         var6 = this.zzafk;
         var3.zzafk = var6;
      }

      if (this.zzafl != 0) {
         var6 = this.zzafl;
         var3.zzafl = var6;
      }

      if (!TextUtils.isEmpty(this.zzafm)) {
         var5 = this.zzafm;
         var3.zzafm = var5;
      }

      if (!TextUtils.isEmpty(this.zzafn)) {
         var5 = this.zzafn;
         if (TextUtils.isEmpty(var5)) {
            var3.zzafn = null;
         } else {
            var3.zzafn = var5;
         }
      }

      boolean var7;
      if (this.zzafo) {
         var7 = this.zzafo;
         var3.zzafo = var7;
      }

      if (this.zzafp) {
         var7 = this.zzafp;
         var3.zzafp = var7;
      }

   }
}
