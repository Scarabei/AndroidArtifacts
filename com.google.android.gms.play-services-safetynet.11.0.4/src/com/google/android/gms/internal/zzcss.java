package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzcss {
   private static final String TAG = zzcss.class.getSimpleName();
   private static final char[] zzbCj = "0123456789ABCDEF".toCharArray();
   private static final Pattern zzbCk = Pattern.compile("/\\.\\.");
   private static final Pattern zzbCl = Pattern.compile("0[1-7][0-7]*");
   private static final Pattern zzbCm = Pattern.compile("0x[0-9a-f]*", 2);
   private static final Pattern zzbCn = Pattern.compile("^((?:0x[0-9a-f]+|[0-9\\\\.])+)$", 2);
   private final String zzD;
   private final String zzbCo;
   private final String zzbCp;
   private final int zzbCq;
   private final String mPath;
   private final String zzvi;

   public zzcss(String var1) {
      if (TextUtils.isEmpty(var1)) {
         this.zzD = null;
         this.zzbCo = null;
         this.zzbCp = null;
         this.zzbCq = -1;
         this.mPath = null;
         this.zzvi = null;
      } else {
         int var2;
         var1 = (var2 = (var1 = var1.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("[\\t\\n\\r]", "")).indexOf(35)) != -1 ? var1.substring(0, var2) : var1;
         zzcss.zza var3;
         if (!(var3 = new zzcss.zza(var1, (zzcst)null)).zzAn()) {
            this.zzD = null;
            this.zzbCo = null;
            this.zzbCp = null;
            this.zzbCq = -1;
            this.mPath = null;
            this.zzvi = null;
         } else {
            this.zzbCo = var3.getScheme();
            this.zzbCq = var3.getPort();
            String var4;
            if (this.zzbCo != null) {
               var4 = this.zzbCo;
               var1 = var1.replaceAll((new StringBuilder(2 + String.valueOf(var4).length())).append("^").append(var4).append(":").toString(), "");
            }

            var1 = zzeS(var1.replaceAll("^/+", ""));
            var4 = null;
            int var5;
            if ((var5 = var1.indexOf(63)) != -1) {
               int var6;
               var4 = (var6 = var5 + 1) < var1.length() ? var1.substring(var6) : "";
               var1 = var1.substring(0, var5);
            }

            String var10000;
            if (TextUtils.isEmpty(var1)) {
               var10000 = null;
            } else {
               int var11;
               var10000 = (var11 = var1.indexOf(47)) != -1 ? var1.substring(0, var11) : var1;
               String var10 = var10000;
               int var12;
               var10 = (var12 = var10000.indexOf(64)) != -1 ? var10.substring(var12 + 1) : var10;
               if (this.zzbCq != -1) {
                  int var13 = this.zzbCq;
                  var10 = var10.replaceAll((new StringBuilder(13)).append(":").append(var13).append("$").toString(), "");
               }

               String var15;
               if ((var15 = zzeO(var10 = var10.replaceAll("^\\.*", "").replaceAll("\\.*$", "").replaceAll("\\.+", "."))) != null) {
                  var10 = var15;
               }

               var10000 = var10.toLowerCase(Locale.getDefault());
            }

            String var14 = var10000;
            if (TextUtils.isEmpty(var10000)) {
               this.zzD = null;
               this.zzbCp = null;
               this.mPath = null;
               this.zzvi = null;
            } else {
               String var7 = this.zzeQ(var1);
               this.zzbCp = zzeR(var14);
               this.mPath = zzeR(var7);
               this.zzvi = !TextUtils.isEmpty(var4) ? zzeR(var4) : var4;
               this.zzD = var1;
            }
         }
      }
   }

   private static String zzeO(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         String var1;
         if (zzcsq.zzeI(var1 = var0.replaceAll("^\\[", "").replaceAll("\\]$", ""))) {
            String var6;
            return (var6 = zzcsq.zza(zzcsq.zzeN(var1))).contains(":") ? String.format("[%s]", var6) : var6;
         } else {
            if (TextUtils.isDigitsOnly(var0)) {
               String var2;
               if (!TextUtils.isEmpty(var2 = zzeP(var0))) {
                  return var2;
               }
            } else if (zzbCn.matcher(var1).find()) {
               Matcher var5 = zzbCl.matcher(var1);
               StringBuffer var3 = new StringBuffer();

               int var4;
               while(var5.find()) {
                  var4 = Integer.parseInt(var5.group(), 8);
                  var5.appendReplacement(var3, (new StringBuilder(11)).append(var4).toString());
               }

               var5.appendTail(var3);
               var5 = zzbCm.matcher(var3.toString());
               var3 = new StringBuffer();

               while(var5.find()) {
                  var4 = Integer.parseInt(var5.group().substring(2), 16);
                  var5.appendReplacement(var3, (new StringBuilder(11)).append(var4).toString());
               }

               var5.appendTail(var3);
               String var7;
               if ((var7 = var3.toString()).contains(":")) {
                  return String.format("[%s]", var7);
               }

               return var7;
            }

            return null;
         }
      }
   }

   private static String zzeP(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         try {
            BigInteger var1;
            byte[] var2;
            if ((var2 = (var1 = new BigInteger(var0)).toByteArray()).length < 4) {
               return null;
            } else {
               byte[] var3 = Arrays.copyOfRange(var2, var2.length - 4, var2.length);
               byte[] var4 = new byte[]{0, var3[0], var3[1], var3[2], var3[3]};
               BigInteger var5 = new BigInteger(var4);
               if (var1.equals(var5)) {
                  return Inet4Address.getByAddress(var3).getHostAddress();
               } else {
                  byte[] var6;
                  if (var2.length >= 16) {
                     var6 = Arrays.copyOfRange(var2, var2.length - 16, var2.length);
                  } else {
                     byte[] var7 = new byte[16];
                     int var8 = 0;
                     int var9 = 16 - var2.length;

                     int var10;
                     for(var10 = 1; var10 <= var9; ++var10) {
                        var7[var8++] = 0;
                     }

                     for(var10 = 0; var10 < var2.length; ++var10) {
                        var7[var8++] = var2[var10];
                     }

                     var6 = var7;
                  }

                  return String.format("[%s]", Inet6Address.getByAddress(var6).getHostAddress());
               }
            }
         } catch (NumberFormatException var11) {
            return null;
         } catch (ArrayIndexOutOfBoundsException var12) {
            return null;
         } catch (UnknownHostException var13) {
            return null;
         }
      }
   }

   private final String zzeQ(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return null;
      } else {
         int var2;
         String var3 = ((var2 = var1.indexOf(47)) != -1 ? var1.substring(var2) : "/").replaceAll("/\\./", "/").replaceAll("/\\.$", "/");
         if (zzbCk.matcher(var3).find()) {
            try {
               var3 = (new URI(this.zzbCo, "host", var3, (String)null)).normalize().getRawPath();
            } catch (URISyntaxException var4) {
               ;
            }
         }

         return var3.replaceAll("/+", "/");
      }
   }

   public final List zzAk() {
      LinkedList var10000;
      label100: {
         if (!TextUtils.isEmpty(this.zzD)) {
            label99: {
               Object var8;
               String var9;
               if ((var9 = zzeO(this.zzbCp)) != null) {
                  ((List)(var8 = new ArrayList())).add(var9);
               } else {
                  var8 = this.zzAl();
               }

               if (var8 != null && !((List)var8).isEmpty()) {
                  List var10;
                  if ((var10 = this.zzAm()) != null && !var10.isEmpty()) {
                     LinkedList var11 = new LinkedList();
                     Iterator var12 = ((List)var8).iterator();

                     while(var12.hasNext()) {
                        String var13 = (String)var12.next();

                        String var10001;
                        for(Iterator var14 = var10.iterator(); var14.hasNext(); var11.add(var10001)) {
                           String var15 = (String)var14.next();
                           var10001 = String.valueOf(var13);
                           String var10002 = String.valueOf(var15);
                           if (var10002.length() != 0) {
                              var10001 = var10001.concat(var10002);
                           } else {
                              String var10003 = new String;
                              var10002 = var10001;
                              var10001 = var10003;
                              var10003.<init>(var10002);
                           }
                        }
                     }

                     if (!var11.isEmpty()) {
                        var10000 = var11;
                        break label100;
                     }
                     break label99;
                  }

                  var10000 = null;
                  break label100;
               }

               var10000 = null;
               break label100;
            }
         }

         var10000 = null;
      }

      LinkedList var1 = var10000;
      if (var10000 != null && !var1.isEmpty()) {
         MessageDigest var2 = null;

         try {
            var2 = MessageDigest.getInstance("SHA-256");
         } catch (NoSuchAlgorithmException var17) {
            ;
         }

         if (var2 == null) {
            return null;
         } else {
            ArrayList var3 = new ArrayList(var1.size());
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
               String var5;
               if (!TextUtils.isEmpty(var5 = (String)var4.next())) {
                  try {
                     byte[] var6 = var2.digest(var5.getBytes("UTF-8"));
                     var3.add(new zzcsp(var6));
                  } catch (UnsupportedEncodingException var16) {
                     ;
                  }

                  var2.reset();
               }
            }

            if (!var3.isEmpty()) {
               return var3;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private final List zzAl() {
      if (TextUtils.isEmpty(this.zzbCp)) {
         return null;
      } else {
         boolean var1 = false;
         ArrayList var2 = new ArrayList();

         char[] var3;
         for(int var4 = (var3 = this.zzbCp.toCharArray()).length - 2; var4 > 0 && var2.size() < 4; --var4) {
            if (var3[var4] == '.') {
               if (var1) {
                  var2.add(this.zzbCp.substring(var4 + 1));
               } else {
                  var1 = true;
               }
            }
         }

         var2.add(this.zzbCp);
         return var2;
      }
   }

   private final List zzAm() {
      if (TextUtils.isEmpty(this.mPath)) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();
         char[] var2 = this.mPath.toCharArray();

         for(int var3 = 0; var3 < var2.length && var1.size() < 4; ++var3) {
            if (var2[var3] == '/') {
               var1.add(this.mPath.substring(0, var3 + 1));
            }
         }

         if (!var1.isEmpty() && !((String)var1.get(var1.size() - 1)).equals(this.mPath)) {
            var1.add(this.mPath);
         }

         if (!TextUtils.isEmpty(this.zzvi)) {
            String var5 = this.mPath;
            String var4 = this.zzvi;
            var1.add((new StringBuilder(1 + String.valueOf(var5).length() + String.valueOf(var4).length())).append(var5).append("?").append(var4).toString());
         }

         return var1;
      }
   }

   private static String zzeR(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         byte[] var1;
         try {
            var1 = var0.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var5) {
            return null;
         }

         StringBuilder var2 = new StringBuilder();

         for(int var3 = 0; var3 < var1.length; ++var3) {
            char var4;
            if ((var4 = (char)(var1[var3] & 255)) > ' ' && var4 <= '~' && var4 != '#' && var4 != '%') {
               var2.append(var4);
            } else {
               var2.append("%");
               var2.append(zzbCj[var4 >>> 4]);
               var2.append(zzbCj[var4 & 15]);
            }
         }

         return var2.toString();
      }
   }

   private static boolean isHexDigit(char var0) {
      return var0 >= '0' && var0 <= '9' || var0 >= 'A' && var0 <= 'F' || var0 >= 'a' && var0 <= 'f';
   }

   private static String zzeS(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         String var1 = var0;
         String var2 = null;

         for(int var3 = 0; !var1.equals(var2) && var3 < 1024; ++var3) {
            var2 = var1;
            var1 = zzeT(var1);
         }

         return var1;
      }
   }

   private static String zzeT(String var0) {
      byte[] var1;
      try {
         var1 = var0.replace("\\x", "%").getBytes("UTF-8");
      } catch (UnsupportedEncodingException var11) {
         return null;
      }

      ByteArrayOutputStream var2 = new ByteArrayOutputStream(var1.length);

      for(int var3 = 0; var3 < var1.length; ++var3) {
         byte var4;
         if ((char)((var4 = var1[var3]) & 255) < 128) {
            Byte var10000;
            label37: {
               if (var3 + 2 < var1.length && (char)(var1[var3] & 255) == '%') {
                  char var8 = (char)(var1[var3 + 1] & 255);
                  char var9 = (char)(var1[var3 + 2] & 255);
                  if (isHexDigit(var8) && isHexDigit(var9)) {
                     var10000 = (byte)((Integer.parseInt((new StringBuilder(1)).append(var8).toString(), 16) << 4) + Integer.parseInt((new StringBuilder(1)).append(var9).toString(), 16));
                     break label37;
                  }
               }

               var10000 = null;
            }

            Byte var5 = var10000;
            if (var10000 != null) {
               var2.write(var5.byteValue());
               var3 += 2;
               continue;
            }
         }

         var2.write(var4);
      }

      try {
         return new String(var2.toByteArray(), "UTF-8");
      } catch (UnsupportedEncodingException var10) {
         return null;
      }
   }

   static class zza {
      private static final String[] zzbCr = new String[]{"http", "https", "ftp"};
      private final String zzajd;
      private final Uri zzbCs;
      private final URI zzbCt;
      private final String zzbCo;
      private final Boolean zzbCu;
      private final Integer zzbCv;

      private zza(String var1) {
         this.zzajd = var1;
         this.zzbCs = Uri.parse(this.zzajd);

         try {
            new URI(this.zzajd);
         } catch (URISyntaxException var7) {
            ;
         } finally {
            this.zzbCt = null;
         }

         this.zzbCo = this.getScheme();
         this.zzbCu = this.zzAn();
         int var4;
         this.zzbCv = this.zzbCv != null ? this.zzbCv.intValue() : (this.zzbCs != null && (var4 = this.zzbCs.getPort()) != -1 ? var4 : -1);
      }

      private final String getScheme() {
         if (this.zzbCo != null) {
            return this.zzbCo;
         } else {
            String var1 = null;
            if (this.zzbCs != null) {
               var1 = this.zzbCs.getScheme();
            }

            TextUtils.isEmpty(var1);
            if (TextUtils.isEmpty(var1) && !TextUtils.isEmpty(this.zzajd)) {
               int var2;
               String var3;
               if ((var2 = this.zzajd.indexOf(":")) != -1 && zzeU(var3 = this.zzajd.substring(0, var2).toLowerCase(Locale.US))) {
                  var1 = var3;
               }

               if (TextUtils.isEmpty(var1)) {
                  this.zzajd.startsWith("www.");
                  var1 = "http";
               }
            }

            return var1 != null ? var1.toLowerCase(Locale.US) : null;
         }
      }

      private final int getPort() {
         return this.zzbCv.intValue();
      }

      private final boolean zzAn() {
         return this.zzbCu != null ? this.zzbCu.booleanValue() : zzeU(this.zzbCo);
      }

      private static boolean zzeU(String var0) {
         if (TextUtils.isEmpty(var0)) {
            return false;
         } else {
            for(int var1 = 0; var1 < zzbCr.length; ++var1) {
               if (zzbCr[var1].equals(var0)) {
                  return true;
               }
            }

            return false;
         }
      }

      // $FF: synthetic method
      zza(String var1, zzcst var2) {
         this(var1);
      }
   }
}
