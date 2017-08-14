package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ProductAction {
   public static final String ACTION_DETAIL = "detail";
   public static final String ACTION_CLICK = "click";
   public static final String ACTION_ADD = "add";
   public static final String ACTION_REMOVE = "remove";
   public static final String ACTION_CHECKOUT = "checkout";
   public static final String ACTION_CHECKOUT_OPTION = "checkout_option";
   /** @deprecated */
   @Deprecated
   public static final String ACTION_CHECKOUT_OPTIONS = "checkout_options";
   public static final String ACTION_PURCHASE = "purchase";
   public static final String ACTION_REFUND = "refund";
   private Map zzafu = new HashMap();

   private final void put(String var1, String var2) {
      zzbo.zzb(var1, "Name should be non-null");
      this.zzafu.put(var1, var2);
   }

   public ProductAction(String var1) {
      this.put("&pa", var1);
   }

   public ProductAction setTransactionId(String var1) {
      this.put("&ti", var1);
      return this;
   }

   public ProductAction setTransactionAffiliation(String var1) {
      this.put("&ta", var1);
      return this;
   }

   public ProductAction setTransactionRevenue(double var1) {
      this.put("&tr", Double.toString(var1));
      return this;
   }

   public ProductAction setTransactionTax(double var1) {
      this.put("&tt", Double.toString(var1));
      return this;
   }

   public ProductAction setTransactionShipping(double var1) {
      this.put("&ts", Double.toString(var1));
      return this;
   }

   public ProductAction setTransactionCouponCode(String var1) {
      this.put("&tcc", var1);
      return this;
   }

   public ProductAction setCheckoutStep(int var1) {
      this.put("&cos", Integer.toString(var1));
      return this;
   }

   public ProductAction setCheckoutOptions(String var1) {
      this.put("&col", var1);
      return this;
   }

   public ProductAction setProductActionList(String var1) {
      this.put("&pal", var1);
      return this;
   }

   public ProductAction setProductListSource(String var1) {
      this.put("&pls", var1);
      return this;
   }

   public final Map build() {
      return new HashMap(this.zzafu);
   }

   public String toString() {
      HashMap var1 = new HashMap();
      Iterator var2 = this.zzafu.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3;
         if (((String)(var3 = (Entry)var2.next()).getKey()).startsWith("&")) {
            var1.put(((String)var3.getKey()).substring(1), (String)var3.getValue());
         } else {
            var1.put((String)var3.getKey(), (String)var3.getValue());
         }
      }

      return zzj.zzk(var1);
   }
}
