package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzf;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Product {
   private Map zzafu = new HashMap();

   private final void put(String var1, String var2) {
      zzbo.zzb(var1, "Name should be non-null");
      this.zzafu.put(var1, var2);
   }

   public Product setId(String var1) {
      this.put("id", var1);
      return this;
   }

   public Product setName(String var1) {
      this.put("nm", var1);
      return this;
   }

   public Product setBrand(String var1) {
      this.put("br", var1);
      return this;
   }

   public Product setCategory(String var1) {
      this.put("ca", var1);
      return this;
   }

   public Product setVariant(String var1) {
      this.put("va", var1);
      return this;
   }

   public Product setPosition(int var1) {
      this.put("ps", Integer.toString(var1));
      return this;
   }

   public Product setPrice(double var1) {
      this.put("pr", Double.toString(var1));
      return this;
   }

   public Product setQuantity(int var1) {
      this.put("qt", Integer.toString(var1));
      return this;
   }

   public Product setCouponCode(String var1) {
      this.put("cc", var1);
      return this;
   }

   public Product setCustomDimension(int var1, String var2) {
      this.put(zzf.zzN(var1), var2);
      return this;
   }

   public Product setCustomMetric(int var1, int var2) {
      this.put(zzf.zzO(var1), Integer.toString(var2));
      return this;
   }

   public final Map zzbl(String var1) {
      HashMap var2 = new HashMap();

      String var10001;
      Entry var4;
      for(Iterator var3 = this.zzafu.entrySet().iterator(); var3.hasNext(); var2.put(var10001, (String)var4.getValue())) {
         var4 = (Entry)var3.next();
         var10001 = String.valueOf(var1);
         String var10002 = String.valueOf((String)var4.getKey());
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            String var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }
      }

      return var2;
   }

   public String toString() {
      return zzj.zzk(this.zzafu);
   }
}
