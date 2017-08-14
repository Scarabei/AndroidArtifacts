package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzaob;
import com.google.android.gms.internal.zzaos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HitBuilders {
   public static class ScreenViewBuilder extends HitBuilders.HitBuilder {
      public ScreenViewBuilder() {
         this.set("&t", "screenview");
      }
   }

   /** @deprecated */
   @Deprecated
   public static class AppViewBuilder extends HitBuilders.HitBuilder {
      public AppViewBuilder() {
         this.set("&t", "screenview");
      }
   }

   /** @deprecated */
   @Deprecated
   public static class ItemBuilder extends HitBuilders.HitBuilder {
      public ItemBuilder() {
         this.set("&t", "item");
      }

      public HitBuilders.ItemBuilder setTransactionId(String var1) {
         this.set("&ti", var1);
         return this;
      }

      public HitBuilders.ItemBuilder setName(String var1) {
         this.set("&in", var1);
         return this;
      }

      public HitBuilders.ItemBuilder setSku(String var1) {
         this.set("&ic", var1);
         return this;
      }

      public HitBuilders.ItemBuilder setCategory(String var1) {
         this.set("&iv", var1);
         return this;
      }

      public HitBuilders.ItemBuilder setPrice(double var1) {
         this.set("&ip", Double.toString(var1));
         return this;
      }

      public HitBuilders.ItemBuilder setQuantity(long var1) {
         this.set("&iq", Long.toString(var1));
         return this;
      }

      public HitBuilders.ItemBuilder setCurrencyCode(String var1) {
         this.set("&cu", var1);
         return this;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class TransactionBuilder extends HitBuilders.HitBuilder {
      public TransactionBuilder() {
         this.set("&t", "transaction");
      }

      public HitBuilders.TransactionBuilder setTransactionId(String var1) {
         this.set("&ti", var1);
         return this;
      }

      public HitBuilders.TransactionBuilder setAffiliation(String var1) {
         this.set("&ta", var1);
         return this;
      }

      public HitBuilders.TransactionBuilder setRevenue(double var1) {
         this.set("&tr", Double.toString(var1));
         return this;
      }

      public HitBuilders.TransactionBuilder setTax(double var1) {
         this.set("&tt", Double.toString(var1));
         return this;
      }

      public HitBuilders.TransactionBuilder setShipping(double var1) {
         this.set("&ts", Double.toString(var1));
         return this;
      }

      public HitBuilders.TransactionBuilder setCurrencyCode(String var1) {
         this.set("&cu", var1);
         return this;
      }
   }

   public static class ExceptionBuilder extends HitBuilders.HitBuilder {
      public ExceptionBuilder() {
         this.set("&t", "exception");
      }

      public HitBuilders.ExceptionBuilder setDescription(String var1) {
         this.set("&exd", var1);
         return this;
      }

      public HitBuilders.ExceptionBuilder setFatal(boolean var1) {
         this.set("&exf", zzaos.zzI(var1));
         return this;
      }
   }

   public static class TimingBuilder extends HitBuilders.HitBuilder {
      public TimingBuilder() {
         this.set("&t", "timing");
      }

      public TimingBuilder(String var1, String var2, long var3) {
         this();
         this.setVariable(var2);
         this.setValue(var3);
         this.setCategory(var1);
      }

      public HitBuilders.TimingBuilder setVariable(String var1) {
         this.set("&utv", var1);
         return this;
      }

      public HitBuilders.TimingBuilder setValue(long var1) {
         this.set("&utt", Long.toString(var1));
         return this;
      }

      public HitBuilders.TimingBuilder setCategory(String var1) {
         this.set("&utc", var1);
         return this;
      }

      public HitBuilders.TimingBuilder setLabel(String var1) {
         this.set("&utl", var1);
         return this;
      }
   }

   public static class SocialBuilder extends HitBuilders.HitBuilder {
      public SocialBuilder() {
         this.set("&t", "social");
      }

      public HitBuilders.SocialBuilder setNetwork(String var1) {
         this.set("&sn", var1);
         return this;
      }

      public HitBuilders.SocialBuilder setAction(String var1) {
         this.set("&sa", var1);
         return this;
      }

      public HitBuilders.SocialBuilder setTarget(String var1) {
         this.set("&st", var1);
         return this;
      }
   }

   public static class EventBuilder extends HitBuilders.HitBuilder {
      public EventBuilder() {
         this.set("&t", "event");
      }

      public EventBuilder(String var1, String var2) {
         this();
         this.setCategory(var1);
         this.setAction(var2);
      }

      public HitBuilders.EventBuilder setCategory(String var1) {
         this.set("&ec", var1);
         return this;
      }

      public HitBuilders.EventBuilder setAction(String var1) {
         this.set("&ea", var1);
         return this;
      }

      public HitBuilders.EventBuilder setLabel(String var1) {
         this.set("&el", var1);
         return this;
      }

      public HitBuilders.EventBuilder setValue(long var1) {
         this.set("&ev", Long.toString(var1));
         return this;
      }
   }

   public static class HitBuilder {
      private Map zzadJ = new HashMap();
      private ProductAction zzadK;
      private Map zzadL = new HashMap();
      private List zzadM = new ArrayList();
      private List zzadN = new ArrayList();

      public HitBuilders.HitBuilder setNewSession() {
         this.set("&sc", "start");
         return this;
      }

      public HitBuilders.HitBuilder setNonInteraction(boolean var1) {
         this.set("&ni", zzaos.zzI(var1));
         return this;
      }

      public HitBuilders.HitBuilder setCampaignParamsFromUrl(String var1) {
         String var2;
         if (TextUtils.isEmpty(var2 = zzaos.zzbD(var1))) {
            return this;
         } else {
            Map var3 = zzaos.zzbB(var2);
            this.zzk("&cc", (String)var3.get("utm_content"));
            this.zzk("&cm", (String)var3.get("utm_medium"));
            this.zzk("&cn", (String)var3.get("utm_campaign"));
            this.zzk("&cs", (String)var3.get("utm_source"));
            this.zzk("&ck", (String)var3.get("utm_term"));
            this.zzk("&ci", (String)var3.get("utm_id"));
            this.zzk("&anid", (String)var3.get("anid"));
            this.zzk("&gclid", (String)var3.get("gclid"));
            this.zzk("&dclid", (String)var3.get("dclid"));
            this.zzk("&aclid", (String)var3.get("aclid"));
            this.zzk("&gmob_t", (String)var3.get("gmob_t"));
            return this;
         }
      }

      public HitBuilders.HitBuilder setCustomDimension(int var1, String var2) {
         this.set(zzf.zzC(var1), var2);
         return this;
      }

      public HitBuilders.HitBuilder setCustomMetric(int var1, float var2) {
         this.set(zzf.zzE(var1), Float.toString(var2));
         return this;
      }

      public final HitBuilders.HitBuilder set(String var1, String var2) {
         if (var1 != null) {
            this.zzadJ.put(var1, var2);
         } else {
            zzaob.zzaT("HitBuilder.set() called with a null paramName.");
         }

         return this;
      }

      private final HitBuilders.HitBuilder zzk(String var1, String var2) {
         if (var2 != null) {
            this.zzadJ.put(var1, var2);
         }

         return this;
      }

      public final HitBuilders.HitBuilder setAll(Map var1) {
         if (var1 == null) {
            return this;
         } else {
            this.zzadJ.putAll(new HashMap(var1));
            return this;
         }
      }

      public Map build() {
         HashMap var1 = new HashMap(this.zzadJ);
         if (this.zzadK != null) {
            var1.putAll(this.zzadK.build());
         }

         int var2 = 1;

         Iterator var3;
         for(var3 = this.zzadM.iterator(); var3.hasNext(); ++var2) {
            Promotion var4 = (Promotion)var3.next();
            var1.putAll(var4.zzbl(zzf.zzI(var2)));
         }

         var2 = 1;

         for(var3 = this.zzadN.iterator(); var3.hasNext(); ++var2) {
            Product var10 = (Product)var3.next();
            var1.putAll(var10.zzbl(zzf.zzG(var2)));
         }

         var2 = 1;

         for(var3 = this.zzadL.entrySet().iterator(); var3.hasNext(); ++var2) {
            Entry var11;
            List var5 = (List)(var11 = (Entry)var3.next()).getValue();
            String var6 = zzf.zzL(var2);
            int var7 = 1;

            String var10002;
            String var10003;
            for(Iterator var8 = var5.iterator(); var8.hasNext(); ++var7) {
               Product var9 = (Product)var8.next();
               var10002 = String.valueOf(var6);
               var10003 = String.valueOf(zzf.zzK(var7));
               if (var10003.length() != 0) {
                  var10002 = var10002.concat(var10003);
               } else {
                  String var10004 = new String;
                  var10003 = var10002;
                  var10002 = var10004;
                  var10004.<init>(var10003);
               }

               var1.putAll(var9.zzbl(var10002));
            }

            if (!TextUtils.isEmpty((CharSequence)var11.getKey())) {
               String var10001 = String.valueOf(var6);
               var10002 = String.valueOf("nm");
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               var1.put(var10001, (String)var11.getKey());
            }
         }

         return var1;
      }

      public HitBuilders.HitBuilder setProductAction(ProductAction var1) {
         this.zzadK = var1;
         return this;
      }

      public HitBuilders.HitBuilder addImpression(Product var1, String var2) {
         if (var1 == null) {
            zzaob.zzaT("product should be non-null");
            return this;
         } else {
            if (var2 == null) {
               var2 = "";
            }

            if (!this.zzadL.containsKey(var2)) {
               this.zzadL.put(var2, new ArrayList());
            }

            ((List)this.zzadL.get(var2)).add(var1);
            return this;
         }
      }

      public HitBuilders.HitBuilder addPromotion(Promotion var1) {
         if (var1 == null) {
            zzaob.zzaT("promotion should be non-null");
            return this;
         } else {
            this.zzadM.add(var1);
            return this;
         }
      }

      public HitBuilders.HitBuilder setPromotionAction(String var1) {
         this.zzadJ.put("&promoa", var1);
         return this;
      }

      public HitBuilders.HitBuilder addProduct(Product var1) {
         if (var1 == null) {
            zzaob.zzaT("product should be non-null");
            return this;
         } else {
            this.zzadN.add(var1);
            return this;
         }
      }

      protected HitBuilders.HitBuilder setHitType(String var1) {
         this.set("&t", var1);
         return this;
      }

      protected String get(String var1) {
         return (String)this.zzadJ.get(var1);
      }
   }
}
