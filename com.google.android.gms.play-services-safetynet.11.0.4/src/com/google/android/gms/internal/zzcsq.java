package com.google.android.gms.internal;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

final class zzcsq {
   private static final Pattern zzbCf = Pattern.compile("[.]");
   private static final Inet4Address zzbCg = (Inet4Address)zzeN("127.0.0.1");
   private static final Inet4Address zzbCh = (Inet4Address)zzeN("0.0.0.0");

   static boolean zzeI(String var0) {
      return zzeJ(var0) != null;
   }

   private static byte[] zzeJ(String var0) {
      boolean var1 = false;
      boolean var2 = false;

      for(int var3 = 0; var3 < var0.length(); ++var3) {
         char var4;
         if ((var4 = var0.charAt(var3)) == '.') {
            var2 = true;
         } else if (var4 == ':') {
            if (var2) {
               return null;
            }

            var1 = true;
         } else if (Character.digit(var4, 16) == -1) {
            return null;
         }
      }

      if (var1) {
         if (var2) {
            int var6 = var0.lastIndexOf(58);
            String var7 = var0.substring(0, var6 + 1);
            String var10000;
            byte[] var8;
            if ((var8 = zzeK(var0.substring(var6 + 1))) == null) {
               var10000 = null;
            } else {
               String var9 = Integer.toHexString((var8[0] & 255) << 8 | var8[1] & 255);
               String var10 = Integer.toHexString((var8[2] & 255) << 8 | var8[3] & 255);
               var10000 = (new StringBuilder(1 + String.valueOf(var7).length() + String.valueOf(var9).length() + String.valueOf(var10).length())).append(var7).append(var9).append(":").append(var10).toString();
            }

            var0 = var10000;
            if (var10000 == null) {
               return null;
            }
         }

         return zzeL(var0);
      } else if (var2) {
         return zzeK(var0);
      } else {
         return null;
      }
   }

   private static byte[] zzeK(String var0) {
      byte[] var1 = new byte[4];
      int var2 = 0;

      try {
         String[] var3;
         int var4 = (var3 = zzbCf.split(var0, 4)).length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            int var10001 = var2++;
            int var8;
            if ((var8 = Integer.parseInt(var6)) > 255 || var6.startsWith("0") && var6.length() > 1) {
               throw new NumberFormatException();
            }

            var1[var10001] = (byte)var8;
         }
      } catch (NumberFormatException var9) {
         return null;
      }

      return var2 == 4 ? var1 : null;
   }

   private static byte[] zzeL(String var0) {
      String[] var1;
      if ((var1 = var0.split(":", 10)).length >= 3 && var1.length <= 9) {
         int var2 = -1;

         int var3;
         for(var3 = 1; var3 < var1.length - 1; ++var3) {
            if (var1[var3].length() == 0) {
               if (var2 >= 0) {
                  return null;
               }

               var2 = var3;
            }
         }

         int var4;
         if (var2 >= 0) {
            var3 = var2;
            var4 = var1.length - var2 - 1;
            if (var1[0].length() == 0) {
               var3 = var2 - 1;
               if (var3 != 0) {
                  return null;
               }
            }

            if (var1[var1.length - 1].length() == 0) {
               --var4;
               if (var4 != 0) {
                  return null;
               }
            }
         } else {
            var3 = var1.length;
            var4 = 0;
         }

         int var5 = 8 - (var3 + var4);
         if (var2 >= 0) {
            if (var5 <= 0) {
               return null;
            }
         } else if (var5 != 0) {
            return null;
         }

         ByteBuffer var6 = ByteBuffer.allocate(16);

         try {
            int var7;
            for(var7 = 0; var7 < var3; ++var7) {
               var6.putShort(zzeM(var1[var7]));
            }

            for(var7 = 0; var7 < var5; ++var7) {
               var6.putShort((short)0);
            }

            for(var7 = var4; var7 > 0; --var7) {
               var6.putShort(zzeM(var1[var1.length - var7]));
            }
         } catch (NumberFormatException var8) {
            return null;
         }

         return var6.array();
      } else {
         return null;
      }
   }

   private static short zzeM(String var0) {
      int var1;
      if ((var1 = Integer.parseInt(var0, 16)) > 65535) {
         throw new NumberFormatException();
      } else {
         return (short)var1;
      }
   }

   static InetAddress zzeN(String var0) {
      byte[] var1;
      if ((var1 = zzeJ(var0)) == null) {
         Object[] var3 = new Object[]{var0};
         String var2 = "'%s' is not an IP string literal.";
         throw new IllegalArgumentException(String.format(Locale.ROOT, var2, var3));
      } else {
         return zzq(var1);
      }
   }

   private static InetAddress zzq(byte[] var0) {
      try {
         return InetAddress.getByAddress(var0);
      } catch (UnknownHostException var2) {
         throw new AssertionError(var2);
      }
   }

   static String zza(InetAddress var0) {
      if (var0 instanceof Inet4Address) {
         return var0.getHostAddress();
      } else {
         byte[] var1 = var0.getAddress();
         int[] var2 = new int[8];

         for(int var3 = 0; var3 < 8; ++var3) {
            byte var10002 = var1[2 * var3];
            byte var7 = var1[2 * var3 + 1];
            byte var6 = var10002;
            var2[var3] = 0 | (var6 & 255) << 8 | var7 & 255;
         }

         int[] var4 = var2;
         int var5 = -1;
         int var11 = -1;
         int var13 = -1;

         for(int var8 = 0; var8 < 9; ++var8) {
            if (var8 < 8 && var4[var8] == 0) {
               if (var13 < 0) {
                  var13 = var8;
               }
            } else if (var13 >= 0) {
               int var9;
               if ((var9 = var8 - var13) > var11) {
                  var5 = var13;
                  var11 = var9;
               }

               var13 = -1;
            }
         }

         if (var11 >= 2) {
            Arrays.fill(var4, var5, var5 + var11, -1);
         }

         var4 = var2;
         StringBuilder var10 = new StringBuilder(39);
         boolean var12 = false;

         for(var13 = 0; var13 < 8; ++var13) {
            boolean var14;
            if (var14 = var4[var13] >= 0) {
               if (var12) {
                  var10.append(':');
               }

               var10.append(Integer.toHexString(var4[var13]));
            } else if (var13 == 0 || var12) {
               var10.append("::");
            }

            var12 = var14;
         }

         return var10.toString();
      }
   }
}
