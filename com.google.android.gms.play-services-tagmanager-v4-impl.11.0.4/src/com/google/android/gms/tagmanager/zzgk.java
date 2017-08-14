package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class zzgk {
   private static final Object zzbHe = null;
   private static Long zzbHf = new Long(0L);
   private static Double zzbHg = new Double(0.0D);
   private static zzgj zzbHh = zzgj.zzai(0L);
   private static String zzbHi = new String("");
   private static Boolean zzbHj = new Boolean(false);
   private static List zzbHk = new ArrayList(0);
   private static Map zzbHl = new HashMap();
   private static com.google.android.gms.internal.zzbr zzbHm;

   public static Object zzCb() {
      return null;
   }

   public static Long zzCc() {
      return zzbHf;
   }

   public static Double zzCd() {
      return zzbHg;
   }

   public static Boolean zzCe() {
      return zzbHj;
   }

   public static zzgj zzCf() {
      return zzbHh;
   }

   public static String zzCg() {
      return zzbHi;
   }

   public static com.google.android.gms.internal.zzbr zzCh() {
      return zzbHm;
   }

   private static String zzH(Object var0) {
      return var0 == null ? zzbHi : var0.toString();
   }

   public static String zzb(com.google.android.gms.internal.zzbr var0) {
      return zzH(zzg(var0));
   }

   public static zzgj zzc(com.google.android.gms.internal.zzbr var0) {
      Object var1;
      if ((var1 = zzg(var0)) instanceof zzgj) {
         return (zzgj)var1;
      } else if (zzK(var1)) {
         return zzgj.zzai(zzL(var1));
      } else {
         return zzJ(var1) ? zzgj.zza(getDouble(var1)) : zzfz(zzH(var1));
      }
   }

   public static Long zzd(com.google.android.gms.internal.zzbr var0) {
      Object var1;
      if (zzK(var1 = zzg(var0))) {
         return zzL(var1);
      } else {
         zzgj var2;
         return (var2 = zzfz(zzH(var1))) == zzbHh ? zzbHf : var2.longValue();
      }
   }

   public static Double zze(com.google.android.gms.internal.zzbr var0) {
      Object var1;
      if (zzJ(var1 = zzg(var0))) {
         return getDouble(var1);
      } else {
         zzgj var2;
         return (var2 = zzfz(zzH(var1))) == zzbHh ? zzbHg : var2.doubleValue();
      }
   }

   public static Boolean zzf(com.google.android.gms.internal.zzbr var0) {
      Object var1;
      if ((var1 = zzg(var0)) instanceof Boolean) {
         return (Boolean)var1;
      } else {
         String var2 = zzH(var1);
         if ("true".equalsIgnoreCase(var2)) {
            return Boolean.TRUE;
         } else {
            return "false".equalsIgnoreCase(var2) ? Boolean.FALSE : zzbHj;
         }
      }
   }

   public static com.google.android.gms.internal.zzbr zzI(Object var0) {
      com.google.android.gms.internal.zzbr var1 = new com.google.android.gms.internal.zzbr();
      boolean var2 = false;
      if (var0 instanceof com.google.android.gms.internal.zzbr) {
         return (com.google.android.gms.internal.zzbr)var0;
      } else {
         if (var0 instanceof String) {
            var1.type = 1;
            var1.string = (String)var0;
         } else {
            ArrayList var4;
            if (var0 instanceof List) {
               var1.type = 2;
               List var10 = (List)var0;
               var4 = new ArrayList(var10.size());
               Iterator var11 = var10.iterator();

               while(var11.hasNext()) {
                  com.google.android.gms.internal.zzbr var12;
                  if ((var12 = zzI(var11.next())) == zzbHm) {
                     return zzbHm;
                  }

                  var2 = var2 || var12.zzlN;
                  var4.add(var12);
               }

               var1.zzlE = (com.google.android.gms.internal.zzbr[])var4.toArray(new com.google.android.gms.internal.zzbr[0]);
            } else if (!(var0 instanceof Map)) {
               if (zzJ(var0)) {
                  var1.type = 1;
                  var1.string = var0.toString();
               } else if (zzK(var0)) {
                  var1.type = 6;
                  var1.zzlJ = zzL(var0);
               } else {
                  if (!(var0 instanceof Boolean)) {
                     String var10001 = String.valueOf(var0 == null ? "null" : var0.getClass().toString());
                     String var10000;
                     if (var10001.length() != 0) {
                        var10000 = "Converting to Value from unknown object type: ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Converting to Value from unknown object type: ");
                     }

                     zzdj.e(var10000);
                     return zzbHm;
                  }

                  var1.type = 8;
                  var1.zzlK = ((Boolean)var0).booleanValue();
               }
            } else {
               var1.type = 3;
               Set var3 = ((Map)var0).entrySet();
               var4 = new ArrayList(var3.size());
               ArrayList var5 = new ArrayList(var3.size());
               Iterator var6 = var3.iterator();

               while(true) {
                  if (!var6.hasNext()) {
                     var1.zzlF = (com.google.android.gms.internal.zzbr[])var4.toArray(new com.google.android.gms.internal.zzbr[0]);
                     var1.zzlG = (com.google.android.gms.internal.zzbr[])var5.toArray(new com.google.android.gms.internal.zzbr[0]);
                     break;
                  }

                  Entry var7;
                  com.google.android.gms.internal.zzbr var8 = zzI((var7 = (Entry)var6.next()).getKey());
                  com.google.android.gms.internal.zzbr var9 = zzI(var7.getValue());
                  if (var8 == zzbHm || var9 == zzbHm) {
                     return zzbHm;
                  }

                  var2 = var2 || var8.zzlN || var9.zzlN;
                  var4.add(var8);
                  var5.add(var9);
               }
            }
         }

         var1.zzlN = var2;
         return var1;
      }
   }

   public static com.google.android.gms.internal.zzbr zzfy(String var0) {
      com.google.android.gms.internal.zzbr var1;
      (var1 = new com.google.android.gms.internal.zzbr()).type = 5;
      var1.zzlI = var0;
      return var1;
   }

   private static boolean zzJ(Object var0) {
      return var0 instanceof Double || var0 instanceof Float || var0 instanceof zzgj && ((zzgj)var0).zzBZ();
   }

   private static double getDouble(Object var0) {
      if (var0 instanceof Number) {
         return ((Number)var0).doubleValue();
      } else {
         zzdj.e("getDouble received non-Number");
         return 0.0D;
      }
   }

   private static boolean zzK(Object var0) {
      return var0 instanceof Byte || var0 instanceof Short || var0 instanceof Integer || var0 instanceof Long || var0 instanceof zzgj && ((zzgj)var0).zzCa();
   }

   private static long zzL(Object var0) {
      if (var0 instanceof Number) {
         return ((Number)var0).longValue();
      } else {
         zzdj.e("getInt64 received non-Number");
         return 0L;
      }
   }

   private static zzgj zzfz(String var0) {
      try {
         return zzgj.zzfx(var0);
      } catch (NumberFormatException var1) {
         zzdj.e((new StringBuilder(33 + String.valueOf(var0).length())).append("Failed to convert '").append(var0).append("' to a number.").toString());
         return zzbHh;
      }
   }

   public static Object zzg(com.google.android.gms.internal.zzbr var0) {
      if (var0 == null) {
         return null;
      } else {
         com.google.android.gms.internal.zzbr[] var2;
         int var3;
         int var4;
         switch(var0.type) {
         case 1:
            return var0.string;
         case 2:
            ArrayList var7 = new ArrayList(var0.zzlE.length);
            var2 = var0.zzlE;
            var3 = var0.zzlE.length;

            for(var4 = 0; var4 < var3; ++var4) {
               Object var12;
               if ((var12 = zzg(var2[var4])) == null) {
                  return null;
               }

               var7.add(var12);
            }

            return var7;
         case 3:
            if (var0.zzlF.length != var0.zzlG.length) {
               String var10001 = String.valueOf(var0.toString());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Converting an invalid value to object: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Converting an invalid value to object: ");
               }

               zzdj.e(var10000);
               return null;
            } else {
               HashMap var6 = new HashMap(var0.zzlG.length);

               for(int var9 = 0; var9 < var0.zzlF.length; ++var9) {
                  Object var10 = zzg(var0.zzlF[var9]);
                  Object var11 = zzg(var0.zzlG[var9]);
                  if (var10 == null || var11 == null) {
                     return null;
                  }

                  var6.put(var10, var11);
               }

               return var6;
            }
         case 4:
            zzdj.e("Trying to convert a macro reference to object");
            return null;
         case 5:
            zzdj.e("Trying to convert a function id to object");
            return null;
         case 6:
            return var0.zzlJ;
         case 7:
            StringBuffer var1 = new StringBuffer();
            var2 = var0.zzlL;
            var3 = var0.zzlL.length;

            for(var4 = 0; var4 < var3; ++var4) {
               String var5;
               if ((var5 = zzb(var2[var4])) == zzbHi) {
                  return null;
               }

               var1.append(var5);
            }

            return var1.toString();
         case 8:
            return var0.zzlK;
         default:
            int var8 = var0.type;
            zzdj.e((new StringBuilder(46)).append("Failed to convert a value of type: ").append(var8).toString());
            return null;
         }
      }
   }

   static {
      zzbHm = zzI(zzbHi);
   }
}
