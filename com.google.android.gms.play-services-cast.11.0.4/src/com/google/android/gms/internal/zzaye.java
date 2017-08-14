package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Locale;
import java.util.regex.Pattern;

public final class zzaye {
   private static final Pattern zzayn = Pattern.compile("urn:x-cast:[-A-Za-z0-9_]+(\\.[-A-Za-z0-9_]+)*");

   public static boolean zza(Object var0, Object var1) {
      return var0 == null && var1 == null || var0 != null && var1 != null && var0.equals(var1);
   }

   public static void zzci(String var0) throws IllegalArgumentException {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("Namespace cannot be null or empty");
      } else if (var0.length() > 128) {
         throw new IllegalArgumentException("Invalid namespace length");
      } else if (!var0.startsWith("urn:x-cast:")) {
         throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
      } else if (var0.length() == 11) {
         throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
      }
   }

   public static String zzcj(String var0) {
      String var10000 = String.valueOf("urn:x-cast:");
      String var10001 = String.valueOf(var0);
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }

   public static String zzck(String var0) {
      if (zzayn.matcher(var0).matches()) {
         return var0;
      } else {
         StringBuilder var1 = new StringBuilder(var0.length());

         for(int var2 = 0; var2 < var0.length(); ++var2) {
            char var3;
            char var4;
            if (((var4 = var3 = var0.charAt(var2)) < 'A' || var4 > 'Z') && (var4 < 'a' || var4 > 'z') && (var4 < '0' || var4 > '9') && var4 != '_' && var4 != '-' && var3 != '.' && var3 != ':') {
               var1.append(String.format("%%%04x", var3 & '\uffff'));
            } else {
               var1.append(var3);
            }
         }

         return var1.toString();
      }
   }

   public static String zzb(Locale var0) {
      StringBuilder var1;
      (var1 = new StringBuilder(20)).append(var0.getLanguage());
      String var2;
      if (!TextUtils.isEmpty(var2 = var0.getCountry())) {
         var1.append('-').append(var2);
      }

      String var3;
      if (!TextUtils.isEmpty(var3 = var0.getVariant())) {
         var1.append('-').append(var3);
      }

      return var1.toString();
   }
}
