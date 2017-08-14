package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzm;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class zzaos {
   private static final char[] zzaiO = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public static Map zzbB(String var0) {
      HashMap var1 = new HashMap();
      String[] var2;
      int var3 = (var2 = var0.split("&")).length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String[] var5;
         if ((var5 = var2[var4].split("=", 3)).length > 1) {
            var1.put(var5[0], TextUtils.isEmpty(var5[1]) ? null : var5[1]);
            if (var5.length == 3 && !TextUtils.isEmpty(var5[1]) && !var1.containsKey(var5[1])) {
               var1.put(var5[1], TextUtils.isEmpty(var5[2]) ? null : var5[2]);
            }
         } else if (var5.length == 1 && var5[0].length() != 0) {
            var1.put(var5[0], (Object)null);
         }
      }

      return var1;
   }

   public static double zza(String var0, double var1) {
      if (var0 == null) {
         return 100.0D;
      } else {
         try {
            return Double.parseDouble(var0);
         } catch (NumberFormatException var3) {
            return 100.0D;
         }
      }
   }

   public static long zzbC(String var0) {
      if (var0 == null) {
         return 0L;
      } else {
         try {
            return Long.parseLong(var0);
         } catch (NumberFormatException var1) {
            return 0L;
         }
      }
   }

   public static boolean zzf(String var0, boolean var1) {
      if (var0 != null) {
         if (var0.equalsIgnoreCase("true") || var0.equalsIgnoreCase("yes") || var0.equalsIgnoreCase("1")) {
            return true;
         }

         if (var0.equalsIgnoreCase("false") || var0.equalsIgnoreCase("no") || var0.equalsIgnoreCase("0")) {
            return false;
         }
      }

      return true;
   }

   public static String zzI(boolean var0) {
      return var0 ? "1" : "0";
   }

   public static String zzbD(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         String var1 = var0;
         String[] var2;
         if (var0.contains("?") && (var2 = var0.split("[\\?]")).length > 1) {
            var1 = var2[1];
         }

         if (var1.contains("%3D")) {
            try {
               var1 = URLDecoder.decode(var1, "UTF-8");
            } catch (UnsupportedEncodingException var6) {
               return null;
            }
         } else if (!var1.contains("=")) {
            return null;
         }

         Map var7 = zzbB(var1);
         String[] var3 = new String[]{"dclid", "utm_source", "gclid", "aclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "anid", "gmob_t"};
         StringBuilder var4 = new StringBuilder();

         for(int var5 = 0; var5 < 11; ++var5) {
            if (!TextUtils.isEmpty((CharSequence)var7.get(var3[var5]))) {
               if (var4.length() > 0) {
                  var4.append("&");
               }

               var4.append(var3[var5]).append("=").append((String)var7.get(var3[var5]));
            }
         }

         return var4.toString();
      }
   }

   public static zzall zza(zzaoc var0, String var1) {
      zzbo.zzu(var0);
      if (TextUtils.isEmpty(var1)) {
         return null;
      } else {
         new HashMap();

         Map var2;
         try {
            URI var10000 = new URI;
            String var10003 = String.valueOf(var1);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "?".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("?");
            }

            var10000.<init>(var10002);
            var2 = zzm.zza(var10000, "UTF-8");
         } catch (URISyntaxException var4) {
            var0.zzd("No valid campaign data found", var4);
            return null;
         }

         zzall var3;
         (var3 = new zzall()).zzbd((String)var2.get("utm_content"));
         var3.zzbb((String)var2.get("utm_medium"));
         var3.setName((String)var2.get("utm_campaign"));
         var3.zzba((String)var2.get("utm_source"));
         var3.zzbc((String)var2.get("utm_term"));
         var3.zzbe((String)var2.get("utm_id"));
         var3.zzbf((String)var2.get("anid"));
         var3.zzbg((String)var2.get("gclid"));
         var3.zzbh((String)var2.get("dclid"));
         var3.zzbi((String)var2.get("aclid"));
         return var3;
      }
   }

   public static String zza(Locale var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1;
         if (TextUtils.isEmpty(var1 = var0.getLanguage())) {
            return null;
         } else {
            StringBuilder var2;
            (var2 = new StringBuilder()).append(var1.toLowerCase());
            if (!TextUtils.isEmpty(var0.getCountry())) {
               var2.append("-").append(var0.getCountry().toLowerCase());
            }

            return var2.toString();
         }
      }
   }

   public static void zzb(Map var0, String var1, String var2) {
      if (var2 != null && !var0.containsKey(var1)) {
         var0.put(var1, var2);
      }

   }

   public static void zzc(Map var0, String var1, String var2) {
      if (var2 != null && TextUtils.isEmpty((CharSequence)var0.get(var1))) {
         var0.put(var1, var2);
      }

   }

   public static void zzb(Map var0, String var1, boolean var2) {
      if (!var0.containsKey(var1)) {
         var0.put(var1, var2 ? "1" : "0");
      }

   }

   public static void zza(Map var0, String var1, Map var2) {
      zzb(var0, var1, (String)var2.get(var1));
   }

   public static MessageDigest zzbE(String var0) {
      for(int var1 = 0; var1 < 2; ++var1) {
         try {
            MessageDigest var2;
            if ((var2 = MessageDigest.getInstance(var0)) != null) {
               return var2;
            }
         } catch (NoSuchAlgorithmException var3) {
            ;
         }
      }

      return null;
   }

   public static boolean zza(double var0, String var2) {
      if (var0 > 0.0D && var0 < 100.0D) {
         String var3 = var2;
         int var4 = 1;
         if (!TextUtils.isEmpty(var2)) {
            var4 = 0;

            for(int var6 = var2.length() - 1; var6 >= 0; --var6) {
               char var7 = var3.charAt(var6);
               int var5;
               var4 = (var5 = (var4 = (var4 << 6 & 268435455) + var7 + (var7 << 14)) & 266338304) != 0 ? var4 ^ var5 >> 21 : var4;
            }
         }

         return (double)(var4 % 10000) >= var0 * 100.0D;
      } else {
         return false;
      }
   }

   public static boolean zzbF(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return true;
      } else {
         return !var0.startsWith("http:");
      }
   }

   public static boolean zza(Context var0, String var1, boolean var2) {
      try {
         ActivityInfo var3;
         if ((var3 = var0.getPackageManager().getReceiverInfo(new ComponentName(var0, var1), 2)) != null && var3.enabled && (!var2 || var3.exported)) {
            return true;
         }
      } catch (NameNotFoundException var4) {
         ;
      }

      return false;
   }

   public static boolean zzw(Context var0, String var1) {
      try {
         ServiceInfo var2;
         if ((var2 = var0.getPackageManager().getServiceInfo(new ComponentName(var0, var1), 4)) != null && var2.enabled) {
            return true;
         }
      } catch (NameNotFoundException var3) {
         ;
      }

      return false;
   }
}
