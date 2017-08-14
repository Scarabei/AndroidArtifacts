package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;

public final class zzalt extends zzj {
   private String zzafc;
   private String zzafd;
   private String zzafe;
   private String zzaff;
   private boolean zzafg;
   private String zzafh;
   private boolean zzafi;
   private double zzafj;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("hitType", this.zzafc);
      var1.put("clientId", this.zzafd);
      var1.put("userId", this.zzafe);
      var1.put("androidAdId", this.zzaff);
      var1.put("AdTargetingEnabled", this.zzafg);
      var1.put("sessionControl", this.zzafh);
      var1.put("nonInteraction", this.zzafi);
      var1.put("sampleRate", this.zzafj);
      return zzh(var1);
   }

   public final String zzjW() {
      return this.zzafc;
   }

   public final void zzbj(String var1) {
      this.zzafc = var1;
   }

   public final String zzjX() {
      return this.zzafd;
   }

   public final void setClientId(String var1) {
      this.zzafd = var1;
   }

   public final String getUserId() {
      return this.zzafe;
   }

   public final void setUserId(String var1) {
      this.zzafe = var1;
   }

   public final String zzjY() {
      return this.zzaff;
   }

   public final void zzbk(String var1) {
      this.zzaff = var1;
   }

   public final boolean zzjZ() {
      return this.zzafg;
   }

   public final void zzG(boolean var1) {
      this.zzafg = var1;
   }

   public final String zzka() {
      return this.zzafh;
   }

   public final boolean zzkb() {
      return this.zzafi;
   }

   public final void zzH(boolean var1) {
      this.zzafi = true;
   }

   public final double zzkc() {
      return this.zzafj;
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalt var3 = (zzalt)var1;
      String var5;
      if (!TextUtils.isEmpty(this.zzafc)) {
         var5 = this.zzafc;
         var3.zzafc = this.zzafc;
      }

      if (!TextUtils.isEmpty(this.zzafd)) {
         var5 = this.zzafd;
         var3.zzafd = this.zzafd;
      }

      if (!TextUtils.isEmpty(this.zzafe)) {
         var5 = this.zzafe;
         var3.zzafe = this.zzafe;
      }

      if (!TextUtils.isEmpty(this.zzaff)) {
         var5 = this.zzaff;
         var3.zzaff = this.zzaff;
      }

      if (this.zzafg) {
         var3.zzafg = true;
      }

      if (!TextUtils.isEmpty(this.zzafh)) {
         var5 = this.zzafh;
         var3.zzafh = this.zzafh;
      }

      if (this.zzafi) {
         boolean var7 = this.zzafi;
         var3.zzafi = this.zzafi;
      }

      if (this.zzafj != 0.0D) {
         double var8 = this.zzafj;
         zzbo.zzb(var8 >= 0.0D && var8 <= 100.0D, "Sample rate must be between 0% and 100%");
         var3.zzafj = var8;
      }

   }
}
