package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class adq {
   public static String zzd(adp var0) {
      if (var0 == null) {
         return "";
      } else {
         StringBuffer var1 = new StringBuffer();

         String var10001;
         try {
            zza((String)null, var0, new StringBuffer(), var1);
         } catch (IllegalAccessException var3) {
            var10001 = String.valueOf(var3.getMessage());
            if (var10001.length() != 0) {
               return "Error printing proto: ".concat(var10001);
            }

            return new String("Error printing proto: ");
         } catch (InvocationTargetException var4) {
            var10001 = String.valueOf(var4.getMessage());
            if (var10001.length() != 0) {
               return "Error printing proto: ".concat(var10001);
            }

            return new String("Error printing proto: ");
         }

         return var1.toString();
      }
   }

   private static void zza(String var0, Object var1, StringBuffer var2, StringBuffer var3) throws IllegalAccessException, InvocationTargetException {
      if (var1 != null) {
         if (var1 instanceof adp) {
            int var20 = var2.length();
            if (var0 != null) {
               var3.append(var2).append(zzhR(var0)).append(" <\n");
               var2.append("  ");
            }

            Class var5;
            Field[] var6;
            int var7 = (var6 = (var5 = var1.getClass()).getFields()).length;

            int var8;
            String var11;
            for(var8 = 0; var8 < var7; ++var8) {
               Field var9;
               int var10 = (var9 = var6[var8]).getModifiers();
               var11 = var9.getName();
               if (!"cachedSize".equals(var11) && (var10 & 1) == 1 && (var10 & 8) != 8 && !var11.startsWith("_") && !var11.endsWith("_")) {
                  Class var12 = var9.getType();
                  Object var13 = var9.get(var1);
                  if (var12.isArray() && var12.getComponentType() != Byte.TYPE) {
                     int var14 = var13 == null ? 0 : Array.getLength(var13);

                     for(int var15 = 0; var15 < var14; ++var15) {
                        Object var16 = Array.get(var13, var15);
                        zza(var11, var16, var2, var3);
                     }
                  } else {
                     zza(var11, var13, var2, var3);
                  }
               }
            }

            Method[] var21;
            var7 = (var21 = var5.getMethods()).length;

            for(var8 = 0; var8 < var7; ++var8) {
               String var22;
               if ((var22 = var21[var8].getName()).startsWith("set")) {
                  var11 = var22.substring(3);

                  String var10001;
                  String var10002;
                  String var10003;
                  Method var23;
                  try {
                     var10002 = String.valueOf(var11);
                     if (var10002.length() != 0) {
                        var10001 = "has".concat(var10002);
                     } else {
                        var10003 = new String;
                        var10001 = var10003;
                        var10003.<init>("has");
                     }

                     var23 = var5.getMethod(var10001);
                  } catch (NoSuchMethodException var19) {
                     continue;
                  }

                  if (((Boolean)var23.invoke(var1)).booleanValue()) {
                     Method var24;
                     try {
                        var10002 = String.valueOf(var11);
                        if (var10002.length() != 0) {
                           var10001 = "get".concat(var10002);
                        } else {
                           var10003 = new String;
                           var10001 = var10003;
                           var10003.<init>("get");
                        }

                        var24 = var5.getMethod(var10001);
                     } catch (NoSuchMethodException var18) {
                        continue;
                     }

                     zza(var11, var24.invoke(var1), var2, var3);
                  }
               }
            }

            if (var0 != null) {
               var2.setLength(var20);
               var3.append(var2).append(">\n");
            }

            return;
         }

         var0 = zzhR(var0);
         var3.append(var2).append(var0).append(": ");
         if (var1 instanceof String) {
            String var17;
            if (!(var17 = (String)var1).startsWith("http") && var17.length() > 200) {
               var17 = String.valueOf(var17.substring(0, 200)).concat("[...]");
            }

            String var4 = zzcK(var17);
            var3.append("\"").append(var4).append("\"");
         } else if (var1 instanceof byte[]) {
            zza((byte[])var1, var3);
         } else {
            var3.append(var1);
         }

         var3.append("\n");
      }

   }

   private static String zzhR(String var0) {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if (var2 == 0) {
            var1.append(Character.toLowerCase(var3));
         } else if (Character.isUpperCase(var3)) {
            var1.append('_').append(Character.toLowerCase(var3));
         } else {
            var1.append(var3);
         }
      }

      return var1.toString();
   }

   private static String zzcK(String var0) {
      int var1 = var0.length();
      StringBuilder var2 = new StringBuilder(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         char var4;
         if ((var4 = var0.charAt(var3)) >= ' ' && var4 <= '~' && var4 != '"' && var4 != '\'') {
            var2.append(var4);
         } else {
            var2.append(String.format("\\u%04x", Integer.valueOf(var4)));
         }
      }

      return var2.toString();
   }

   private static void zza(byte[] var0, StringBuffer var1) {
      if (var0 == null) {
         var1.append("\"\"");
      } else {
         var1.append('"');

         for(int var2 = 0; var2 < var0.length; ++var2) {
            int var3;
            if ((var3 = var0[var2] & 255) != 92 && var3 != 34) {
               if (var3 >= 32 && var3 < 127) {
                  var1.append((char)var3);
               } else {
                  var1.append(String.format("\\%03o", var3));
               }
            } else {
               var1.append('\\').append((char)var3);
            }
         }

         var1.append('"');
      }
   }
}
