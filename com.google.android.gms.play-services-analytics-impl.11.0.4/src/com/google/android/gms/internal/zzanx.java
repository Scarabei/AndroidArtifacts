package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzanx {
   private final Map zzHa;
   private final List zzaib;
   private final long zzaic;
   private final long zzaid;
   private final int zzaie;
   private final boolean zzaif;
   private final String zzaig;

   public zzanx(zzamg var1, Map var2, long var3, boolean var5) {
      this(var1, var2, var3, var5, 0L, 0, (List)null);
   }

   public zzanx(zzamg var1, Map var2, long var3, boolean var5, long var6, int var8) {
      this(var1, var2, var3, var5, var6, var8, (List)null);
   }

   public zzanx(zzamg var1, Map var2, long var3, boolean var5, long var6, int var8, List var9) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzaid = var3;
      this.zzaif = var5;
      this.zzaic = var6;
      this.zzaie = var8;
      this.zzaib = var9 != null ? var9 : Collections.emptyList();
      this.zzaig = zzt(var9);
      HashMap var10 = new HashMap();
      Iterator var11 = var2.entrySet().iterator();

      Entry var12;
      String var13;
      String var14;
      while(var11.hasNext()) {
         if (zzj((var12 = (Entry)var11.next()).getKey()) && (var13 = zza(var1, var12.getKey())) != null) {
            var14 = zzb(var1, var12.getValue());
            var10.put(var13, var14);
         }
      }

      var11 = var2.entrySet().iterator();

      while(var11.hasNext()) {
         if (!zzj((var12 = (Entry)var11.next()).getKey()) && (var13 = zza(var1, var12.getKey())) != null) {
            var14 = zzb(var1, var12.getValue());
            var10.put(var13, var14);
         }
      }

      if (!TextUtils.isEmpty(this.zzaig)) {
         zzaos.zzb(var10, "_v", this.zzaig);
         if (this.zzaig.equals("ma4.0.0") || this.zzaig.equals("ma4.0.1")) {
            var10.remove("adid");
         }
      }

      this.zzHa = Collections.unmodifiableMap(var10);
   }

   private static boolean zzj(Object var0) {
      return var0 == null ? false : var0.toString().startsWith("&");
   }

   private static String zza(zzamg var0, Object var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2;
         String var10000 = (var2 = var1.toString()).startsWith("&") ? var2.substring(1) : var2;
         String var3 = var10000;
         int var4;
         if ((var4 = var10000.length()) > 256) {
            var3 = var3.substring(0, 256);
            var0.zzc("Hit param name is too long and will be trimmed", var4, var3);
         }

         return TextUtils.isEmpty(var3) ? null : var3;
      }
   }

   private static String zzb(zzamg var0, Object var1) {
      String var2;
      if (var1 == null) {
         var2 = "";
      } else {
         var2 = var1.toString();
      }

      int var3;
      if ((var3 = var2.length()) > 8192) {
         var2 = var2.substring(0, 8192);
         var0.zzc("Hit param value is too long and will be trimmed", var3, var2);
      }

      return var2;
   }

   private static String zzt(List var0) {
      String var1 = null;
      if (var0 != null) {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            zzane var3 = (zzane)var2.next();
            if ("appendVersion".equals(var3.getId())) {
               var1 = var3.getValue();
               break;
            }
         }
      }

      return TextUtils.isEmpty(var1) ? null : var1;
   }

   public final int zzlE() {
      return this.zzaie;
   }

   public final Map zzdV() {
      return this.zzHa;
   }

   public final long zzlF() {
      return this.zzaic;
   }

   public final long zzlG() {
      return this.zzaid;
   }

   public final List zzlH() {
      return this.zzaib;
   }

   public final boolean zzlI() {
      return this.zzaif;
   }

   public final long zzlJ() {
      return zzaos.zzbC(this.zzn("_s", "0"));
   }

   public final String zzlK() {
      return this.zzn("_m", "");
   }

   private final String zzn(String var1, String var2) {
      zzbo.zzcF(var1);
      zzbo.zzb(!var1.startsWith("&"), "Short param name required");
      String var3;
      return (var3 = (String)this.zzHa.get(var1)) != null ? var3 : var2;
   }

   public final String toString() {
      StringBuffer var1;
      (var1 = new StringBuffer()).append("ht=").append(this.zzaid);
      if (this.zzaic != 0L) {
         var1.append(", dbId=").append(this.zzaic);
      }

      if (this.zzaie != 0) {
         var1.append(", appUID=").append(this.zzaie);
      }

      ArrayList var2;
      Collections.sort(var2 = new ArrayList(this.zzHa.keySet()));
      ArrayList var4;
      int var5 = (var4 = (ArrayList)var2).size();
      int var6 = 0;

      while(var6 < var5) {
         Object var10000 = var4.get(var6);
         ++var6;
         String var3 = (String)var10000;
         var1.append(", ");
         var1.append(var3);
         var1.append("=");
         var1.append((String)this.zzHa.get(var3));
      }

      return var1.toString();
   }
}
