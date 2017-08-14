package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzalk extends zzj {
   private String zzaeH;
   private String zzaeI;
   private String mAppId;
   private String zzaeJ;

   public final void zza(zzalk var1) {
      String var2;
      if (!TextUtils.isEmpty(this.zzaeH)) {
         var2 = this.zzaeH;
         var1.zzaeH = this.zzaeH;
      }

      if (!TextUtils.isEmpty(this.zzaeI)) {
         var2 = this.zzaeI;
         var1.zzaeI = this.zzaeI;
      }

      if (!TextUtils.isEmpty(this.mAppId)) {
         var2 = this.mAppId;
         var1.mAppId = this.mAppId;
      }

      if (!TextUtils.isEmpty(this.zzaeJ)) {
         var2 = this.zzaeJ;
         var1.zzaeJ = this.zzaeJ;
      }

   }

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("appName", this.zzaeH);
      var1.put("appVersion", this.zzaeI);
      var1.put("appId", this.mAppId);
      var1.put("appInstallerId", this.zzaeJ);
      return zzh(var1);
   }

   public final String zzjG() {
      return this.zzaeH;
   }

   public final void setAppName(String var1) {
      this.zzaeH = var1;
   }

   public final String zzjH() {
      return this.zzaeI;
   }

   public final void setAppVersion(String var1) {
      this.zzaeI = var1;
   }

   public final String zzhl() {
      return this.mAppId;
   }

   public final void setAppId(String var1) {
      this.mAppId = var1;
   }

   public final String zzjI() {
      return this.zzaeJ;
   }

   public final void setAppInstallerId(String var1) {
      this.zzaeJ = var1;
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      this.zza((zzalk)var1);
   }
}
