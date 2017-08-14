package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Promotion {
   public static final String ACTION_CLICK = "click";
   public static final String ACTION_VIEW = "view";
   private Map zzafu = new HashMap();

   private final void put(String var1, String var2) {
      zzbo.zzb(var1, "Name should be non-null");
      this.zzafu.put(var1, var2);
   }

   public Promotion setId(String var1) {
      this.put("id", var1);
      return this;
   }

   public Promotion setName(String var1) {
      this.put("nm", var1);
      return this;
   }

   public Promotion setCreative(String var1) {
      this.put("cr", var1);
      return this;
   }

   public Promotion setPosition(String var1) {
      this.put("ps", var1);
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
