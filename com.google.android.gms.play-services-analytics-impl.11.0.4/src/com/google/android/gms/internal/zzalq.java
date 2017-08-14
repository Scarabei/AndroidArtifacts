package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzalq extends zzj {
   private final List zzadN = new ArrayList();
   private final List zzadM = new ArrayList();
   private final Map zzadL = new HashMap();
   private ProductAction zzadK;

   public final ProductAction zzjS() {
      return this.zzadK;
   }

   public final String toString() {
      HashMap var1 = new HashMap();
      if (!this.zzadN.isEmpty()) {
         var1.put("products", this.zzadN);
      }

      if (!this.zzadM.isEmpty()) {
         var1.put("promotions", this.zzadM);
      }

      if (!this.zzadL.isEmpty()) {
         var1.put("impressions", this.zzadL);
      }

      var1.put("productAction", this.zzadK);
      return zzh(var1);
   }

   public final List zzjT() {
      return Collections.unmodifiableList(this.zzadN);
   }

   public final Map zzjU() {
      return this.zzadL;
   }

   public final List zzjV() {
      return Collections.unmodifiableList(this.zzadM);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalq var3 = (zzalq)var1;
      var3.zzadN.addAll(this.zzadN);
      var3.zzadM.addAll(this.zzadM);
      Iterator var4 = this.zzadL.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5;
         String var6 = (String)(var5 = (Entry)var4.next()).getKey();
         Iterator var7 = ((List)var5.getValue()).iterator();

         while(var7.hasNext()) {
            Product var8 = (Product)var7.next();
            String var11 = var6;
            if (var8 != null) {
               if (var6 == null) {
                  var11 = "";
               }

               if (!var3.zzadL.containsKey(var11)) {
                  var3.zzadL.put(var11, new ArrayList());
               }

               ((List)var3.zzadL.get(var11)).add(var8);
            }
         }
      }

      if (this.zzadK != null) {
         var3.zzadK = this.zzadK;
      }

   }
}
