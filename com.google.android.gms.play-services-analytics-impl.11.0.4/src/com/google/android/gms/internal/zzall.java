package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzall extends zzj {
   private String mName;
   private String zzaeK;
   private String zzaeL;
   private String zzaeM;
   private String zzHg;
   private String zzIi;
   private String zzaeN;
   private String zzaeO;
   private String zzaeP;
   private String zzaeQ;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("name", this.mName);
      var1.put("source", this.zzaeK);
      var1.put("medium", this.zzaeL);
      var1.put("keyword", this.zzaeM);
      var1.put("content", this.zzHg);
      var1.put("id", this.zzIi);
      var1.put("adNetworkId", this.zzaeN);
      var1.put("gclid", this.zzaeO);
      var1.put("dclid", this.zzaeP);
      var1.put("aclid", this.zzaeQ);
      return zzh(var1);
   }

   public final String getName() {
      return this.mName;
   }

   public final void setName(String var1) {
      this.mName = var1;
   }

   public final String getSource() {
      return this.zzaeK;
   }

   public final void zzba(String var1) {
      this.zzaeK = var1;
   }

   public final String zzjJ() {
      return this.zzaeL;
   }

   public final void zzbb(String var1) {
      this.zzaeL = var1;
   }

   public final String zzjK() {
      return this.zzaeM;
   }

   public final void zzbc(String var1) {
      this.zzaeM = var1;
   }

   public final String getContent() {
      return this.zzHg;
   }

   public final void zzbd(String var1) {
      this.zzHg = var1;
   }

   public final String getId() {
      return this.zzIi;
   }

   public final void zzbe(String var1) {
      this.zzIi = var1;
   }

   public final String zzjL() {
      return this.zzaeN;
   }

   public final void zzbf(String var1) {
      this.zzaeN = var1;
   }

   public final String zzjM() {
      return this.zzaeO;
   }

   public final void zzbg(String var1) {
      this.zzaeO = var1;
   }

   public final String zzjN() {
      return this.zzaeP;
   }

   public final void zzbh(String var1) {
      this.zzaeP = var1;
   }

   public final String zzjO() {
      return this.zzaeQ;
   }

   public final void zzbi(String var1) {
      this.zzaeQ = var1;
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzall var3 = (zzall)var1;
      String var4;
      if (!TextUtils.isEmpty(this.mName)) {
         var4 = this.mName;
         var3.mName = this.mName;
      }

      if (!TextUtils.isEmpty(this.zzaeK)) {
         var4 = this.zzaeK;
         var3.zzaeK = this.zzaeK;
      }

      if (!TextUtils.isEmpty(this.zzaeL)) {
         var4 = this.zzaeL;
         var3.zzaeL = this.zzaeL;
      }

      if (!TextUtils.isEmpty(this.zzaeM)) {
         var4 = this.zzaeM;
         var3.zzaeM = this.zzaeM;
      }

      if (!TextUtils.isEmpty(this.zzHg)) {
         var4 = this.zzHg;
         var3.zzHg = this.zzHg;
      }

      if (!TextUtils.isEmpty(this.zzIi)) {
         var4 = this.zzIi;
         var3.zzIi = this.zzIi;
      }

      if (!TextUtils.isEmpty(this.zzaeN)) {
         var4 = this.zzaeN;
         var3.zzaeN = this.zzaeN;
      }

      if (!TextUtils.isEmpty(this.zzaeO)) {
         var4 = this.zzaeO;
         var3.zzaeO = this.zzaeO;
      }

      if (!TextUtils.isEmpty(this.zzaeP)) {
         var4 = this.zzaeP;
         var3.zzaeP = this.zzaeP;
      }

      if (!TextUtils.isEmpty(this.zzaeQ)) {
         var4 = this.zzaeQ;
         var3.zzaeQ = this.zzaeQ;
      }

   }
}
