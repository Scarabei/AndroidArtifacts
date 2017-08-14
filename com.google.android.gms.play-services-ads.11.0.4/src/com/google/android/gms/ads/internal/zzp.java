package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzzn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

@zzzn
public final class zzp {
   public static Object[] zza(String var0, zzir var1, String var2, int var3, @Nullable zziv var4) {
      HashSet var5 = new HashSet(Arrays.asList(var0.split(",")));
      ArrayList var6;
      (var6 = new ArrayList()).add(var0);
      var6.add(var2);
      if (var5.contains("formatString")) {
         var6.add((Object)null);
      }

      if (var5.contains("networkType")) {
         var6.add(var3);
      }

      if (var5.contains("birthday")) {
         var6.add(var1.zzzN);
      }

      if (var5.contains("extras")) {
         var6.add(bundleToString(var1.extras));
      }

      if (var5.contains("gender")) {
         var6.add(var1.zzzO);
      }

      if (var5.contains("keywords")) {
         if (var1.zzzP != null) {
            var6.add(var1.zzzP.toString());
         } else {
            var6.add((Object)null);
         }
      }

      if (var5.contains("isTestDevice")) {
         var6.add(var1.zzzQ);
      }

      if (var5.contains("tagForChildDirectedTreatment")) {
         var6.add(var1.zzzR);
      }

      if (var5.contains("manualImpressionsEnabled")) {
         var6.add(var1.zzzS);
      }

      if (var5.contains("publisherProvidedId")) {
         var6.add(var1.zzzT);
      }

      if (var5.contains("location")) {
         if (var1.zzzV != null) {
            var6.add(var1.zzzV.toString());
         } else {
            var6.add((Object)null);
         }
      }

      if (var5.contains("contentUrl")) {
         var6.add(var1.zzzW);
      }

      if (var5.contains("networkExtras")) {
         var6.add(bundleToString(var1.zzzX));
      }

      if (var5.contains("customTargeting")) {
         var6.add(bundleToString(var1.zzzY));
      }

      if (var5.contains("categoryExclusions")) {
         if (var1.zzzZ != null) {
            var6.add(var1.zzzZ.toString());
         } else {
            var6.add((Object)null);
         }
      }

      if (var5.contains("requestAgent")) {
         var6.add(var1.zzAa);
      }

      if (var5.contains("requestPackage")) {
         var6.add(var1.zzAb);
      }

      if (var5.contains("isDesignedForFamilies")) {
         var6.add(var1.zzAc);
      }

      return var6.toArray();
   }

   private static String bundleToString(@Nullable Bundle var0) {
      if (var0 == null) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();

         String var5;
         for(Iterator var2 = (new TreeSet(var0.keySet())).iterator(); var2.hasNext(); var1.append(var5)) {
            String var3 = (String)var2.next();
            Object var4;
            if ((var4 = var0.get(var3)) == null) {
               var5 = "null";
            } else if (var4 instanceof Bundle) {
               var5 = bundleToString((Bundle)var4);
            } else {
               var5 = var4.toString();
            }
         }

         return var1.toString();
      }
   }
}
