package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzcxp {
   public static boolean zza(dp var0) {
      zzbo.zzaf(var0 != null);
      if (var0 != dv.zzbLu && var0 != dv.zzbLt) {
         if (var0 instanceof ds) {
            return ((Boolean)((ds)var0).zzDn()).booleanValue();
         } else {
            if (var0 instanceof dt) {
               if (((Double)((dt)var0).zzDo()).doubleValue() == 0.0D || ((Double)((dt)var0).zzDo()).doubleValue() == -0.0D || Double.isNaN(((Double)((dt)var0).zzDo()).doubleValue())) {
                  return false;
               }
            } else if (var0 instanceof eb) {
               if (((String)((eb)var0).value()).isEmpty()) {
                  return false;
               }
            } else if (zzf(var0)) {
               String var1 = String.valueOf(var0.toString());
               throw new IllegalArgumentException((new StringBuilder(33 + String.valueOf(var1).length())).append("Illegal type given to isTruthy: ").append(var1).append(".").toString());
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static double zzb(dp var0) {
      while(true) {
         zzbo.zzaf(var0 != null);
         if (var0 == dv.zzbLu) {
            return Double.NaN;
         }

         if (var0 == dv.zzbLt) {
            return 0.0D;
         }

         if (var0 instanceof ds) {
            if (((Boolean)((ds)var0).zzDn()).booleanValue()) {
               return 1.0D;
            }

            return 0.0D;
         }

         if (var0 instanceof dt) {
            return ((Double)((dt)var0).zzDo()).doubleValue();
         }

         if (var0 instanceof dw) {
            dw var1;
            if (((List)(var1 = (dw)var0).zzDs()).isEmpty()) {
               return 0.0D;
            }

            if (((List)var1.zzDs()).size() == 1) {
               var0 = new eb(zzd(var1.zzbG(0)));
               continue;
            }
         } else if (var0 instanceof eb) {
            eb var4;
            if (((String)(var4 = (eb)var0).value()).isEmpty()) {
               return 0.0D;
            }

            try {
               return Double.parseDouble((String)var4.value());
            } catch (NumberFormatException var2) {
               return Double.NaN;
            }
         }

         if (zzf((dp)var0)) {
            String var3 = String.valueOf(((dp)var0).toString());
            throw new IllegalArgumentException((new StringBuilder(41 + String.valueOf(var3).length())).append("Illegal type given to numberEquivalent: ").append(var3).append(".").toString());
         }

         return Double.NaN;
      }
   }

   public static double zzc(dp var0) {
      double var1;
      if (Double.isNaN(var1 = zzb(var0))) {
         return 0.0D;
      } else {
         return var1 != 0.0D && var1 != -0.0D && !Double.isInfinite(var1) ? Math.signum(var1) * Math.floor(Math.abs(var1)) : var1;
      }
   }

   public static String zzd(dp var0) {
      zzbo.zzaf(var0 != null);
      if (var0 == dv.zzbLu) {
         return "undefined";
      } else if (var0 == dv.zzbLt) {
         return "null";
      } else if (var0 instanceof ds) {
         return ((Boolean)((ds)var0).zzDn()).booleanValue() ? "true" : "false";
      } else {
         String var7;
         if (var0 instanceof dt) {
            int var10;
            if ((var10 = (var7 = Double.toString(((Double)((dt)var0).zzDo()).doubleValue())).indexOf("E")) > 0) {
               int var11;
               if ((var11 = Integer.parseInt(var7.substring(var10 + 1, var7.length()))) < 0) {
                  if (var11 > -7) {
                     var7 = var7.substring(0, var10).replace(".", "");
                     StringBuilder var4;
                     (var4 = new StringBuilder()).append("0.");

                     while(var11 + 1 < 0) {
                        var4.append("0");
                        ++var11;
                     }

                     var4.append(var7);
                     var7 = var4.toString();
                  } else {
                     var7 = var7.replace("E", "e");
                  }
               } else if (var11 < 21) {
                  var7 = var7.substring(0, var10).replace(".", "");
                  int var12 = var11 + 1 - (var7.length() - (var7.startsWith("-") ? 1 : 0));
                  StringBuilder var5 = new StringBuilder();
                  if (var12 < 0) {
                     int var6 = var7.length() + var12;
                     var5.append(var7.substring(0, var6));
                     var5.append(".");
                     var5.append(var7.substring(var6, var7.length()));
                  } else {
                     var5.append(var7);

                     while(var12 > 0) {
                        var5.append("0");
                        --var12;
                     }
                  }

                  var7 = var5.toString();
               } else {
                  var7 = var7.replace("E", "e+");
               }
            } else if (var7.endsWith(".0") && (var7 = var7.substring(0, var7.length() - 2)).equals("-0")) {
               var7 = "0";
            }

            return var7;
         } else {
            if (var0 instanceof du) {
               zzcxo var1;
               if ((var1 = (zzcxo)((du)var0).zzDp()) instanceof zzcxn) {
                  return ((zzcxn)var1).getName();
               }
            } else {
               if (var0 instanceof dw) {
                  ArrayList var8 = new ArrayList();
                  Iterator var9 = ((List)((dw)var0).zzDs()).iterator();

                  while(true) {
                     while(var9.hasNext()) {
                        dp var3;
                        if ((var3 = (dp)var9.next()) != dv.zzbLt && var3 != dv.zzbLu) {
                           var8.add(zzd(var3));
                        } else {
                           var8.add("");
                        }
                     }

                     return TextUtils.join(",", var8);
                  }
               }

               if (var0 instanceof dz) {
                  return "[object Object]";
               }

               if (var0 instanceof eb) {
                  return (String)((eb)var0).value();
               }
            }

            if (zzf(var0)) {
               String var2 = String.valueOf(var0.toString());
               var7 = (new StringBuilder(41 + String.valueOf(var2).length())).append("Illegal type given to stringEquivalent: ").append(var2).append(".").toString();
            } else {
               var7 = "Unknown type in stringEquivalent.";
            }

            throw new IllegalArgumentException(var7);
         }
      }
   }

   public static double zza(dp var0, dp var1) {
      zzbo.zzaf(var0 != null);
      zzbo.zzaf(var1 != null);
      double var2 = zzb(var0);
      double var4 = zzb(var1);
      if (!Double.isNaN(var2) && !Double.isNaN(var4)) {
         if (var2 == Double.POSITIVE_INFINITY && var4 == Double.NEGATIVE_INFINITY || var2 == Double.NEGATIVE_INFINITY && var4 == Double.POSITIVE_INFINITY) {
            return Double.NaN;
         } else if (Double.isInfinite(var2) && !Double.isInfinite(var4)) {
            return var2;
         } else {
            return !Double.isInfinite(var2) && Double.isInfinite(var4) ? var4 : var2 + var4;
         }
      } else {
         return Double.NaN;
      }
   }

   public static boolean zzb(dp var0, dp var1) {
      zzbo.zzaf(var0 != null);
      zzbo.zzaf(var1 != null);
      String var6;
      if (zzf((dp)var0)) {
         var6 = String.valueOf(((dp)var0).toString());
         throw new IllegalArgumentException((new StringBuilder(50 + String.valueOf(var6).length())).append("Illegal type given to abstractRelationalCompare: ").append(var6).append(".").toString());
      } else if (zzf((dp)var1)) {
         var6 = String.valueOf(((dp)var1).toString());
         throw new IllegalArgumentException((new StringBuilder(50 + String.valueOf(var6).length())).append("Illegal type given to abstractRelationalCompare: ").append(var6).append(".").toString());
      } else {
         if (var0 instanceof dz || var0 instanceof dw || var0 instanceof du) {
            var0 = new eb(zzd((dp)var0));
         }

         if (var1 instanceof dz || var1 instanceof dw || var1 instanceof du) {
            var1 = new eb(zzd((dp)var1));
         }

         if (var0 instanceof eb && var1 instanceof eb) {
            var6 = (String)((eb)var0).value();
            String var3 = (String)((eb)var1).value();
            return var6.compareTo(var3) < 0;
         } else {
            double var2 = zzb((dp)var0);
            double var4 = zzb((dp)var1);
            if (!Double.isNaN(var2) && !Double.isNaN(var4)) {
               if (var2 == 0.0D && var4 == -0.0D || var2 == -0.0D && var4 == 0.0D) {
                  return false;
               } else if (var2 == Double.POSITIVE_INFINITY) {
                  return false;
               } else if (var4 == Double.POSITIVE_INFINITY) {
                  return true;
               } else if (var4 == Double.NEGATIVE_INFINITY) {
                  return false;
               } else if (var2 == Double.NEGATIVE_INFINITY) {
                  return true;
               } else {
                  return Double.compare(var2, var4) < 0;
               }
            } else {
               return false;
            }
         }
      }
   }

   private static String zze(dp var0) {
      if (var0 == dv.zzbLu) {
         return "Undefined";
      } else if (var0 == dv.zzbLt) {
         return "Null";
      } else if (var0 instanceof ds) {
         return "Boolean";
      } else if (var0 instanceof dt) {
         return "Number";
      } else {
         return var0 instanceof eb ? "String" : "Object";
      }
   }

   public static boolean zzc(dp var0, dp var1) {
      while(true) {
         zzbo.zzaf(var0 != null);
         zzbo.zzaf(var1 != null);
         String var2;
         if (zzf((dp)var0)) {
            var2 = String.valueOf(((dp)var0).toString());
            throw new IllegalArgumentException((new StringBuilder(48 + String.valueOf(var2).length())).append("Illegal type given to abstractEqualityCompare: ").append(var2).append(".").toString());
         }

         if (zzf((dp)var1)) {
            var2 = String.valueOf(((dp)var1).toString());
            throw new IllegalArgumentException((new StringBuilder(48 + String.valueOf(var2).length())).append("Illegal type given to abstractEqualityCompare: ").append(var2).append(".").toString());
         }

         var2 = zze((dp)var0);
         String var3 = zze((dp)var1);
         if (var2.equals(var3)) {
            byte var5 = -1;
            switch(var2.hashCode()) {
            case -1950496919:
               if (var2.equals("Number")) {
                  var5 = 2;
               }
               break;
            case -1939501217:
               if (var2.equals("Object")) {
                  var5 = 5;
               }
               break;
            case -1808118735:
               if (var2.equals("String")) {
                  var5 = 3;
               }
               break;
            case 2439591:
               if (var2.equals("Null")) {
                  var5 = 1;
               }
               break;
            case 965837104:
               if (var2.equals("Undefined")) {
                  var5 = 0;
               }
               break;
            case 1729365000:
               if (var2.equals("Boolean")) {
                  var5 = 4;
               }
            }

            switch(var5) {
            case 0:
            case 1:
               return true;
            case 2:
               double var6 = ((Double)((dt)var0).zzDo()).doubleValue();
               double var8 = ((Double)((dt)var1).zzDo()).doubleValue();
               if (!Double.isNaN(var6) && !Double.isNaN(var8)) {
                  if (var6 == var8) {
                     return true;
                  }

                  return false;
               }

               return false;
            case 3:
               return ((String)((eb)var0).value()).equals((String)((eb)var1).value());
            case 4:
               if ((Boolean)((ds)var0).zzDn() == (Boolean)((ds)var1).zzDn()) {
                  return true;
               }

               return false;
            case 5:
               if (var0 == var1) {
                  return true;
               }

               return false;
            default:
               return false;
            }
         }

         if ((var0 == dv.zzbLu || var0 == dv.zzbLt) && (var1 == dv.zzbLu || var1 == dv.zzbLt)) {
            return true;
         }

         if (var2.equals("Number") && var3.equals("String")) {
            var1 = new dt(zzb((dp)var1));
            var0 = var0;
         } else if (var2.equals("String") && var3.equals("Number")) {
            var0 = new dt(zzb((dp)var0));
         } else if (var2.equals("Boolean")) {
            var0 = new dt(zzb((dp)var0));
         } else if (var3.equals("Boolean")) {
            var1 = new dt(zzb((dp)var1));
            var0 = var0;
         } else if ((var2.equals("String") || var2.equals("Number")) && var3.equals("Object")) {
            var1 = new eb(zzd((dp)var1));
            var0 = var0;
         } else {
            if (!var2.equals("Object") || !var3.equals("String") && !var3.equals("Number")) {
               return false;
            }

            var0 = new eb(zzd((dp)var0));
         }
      }
   }

   public static boolean zzd(dp var0, dp var1) {
      zzbo.zzaf(var0 != null);
      zzbo.zzaf(var1 != null);
      String var2;
      if (zzf(var0)) {
         var2 = String.valueOf(var0.toString());
         throw new IllegalArgumentException((new StringBuilder(46 + String.valueOf(var2).length())).append("Illegal type given to strictEqualityCompare: ").append(var2).append(".").toString());
      } else if (zzf(var1)) {
         var2 = String.valueOf(var1.toString());
         throw new IllegalArgumentException((new StringBuilder(46 + String.valueOf(var2).length())).append("Illegal type given to strictEqualityCompare: ").append(var2).append(".").toString());
      } else {
         var2 = zze(var0);
         String var3 = zze(var1);
         if (!var2.equals(var3)) {
            return false;
         } else {
            byte var5 = -1;
            switch(var2.hashCode()) {
            case -1950496919:
               if (var2.equals("Number")) {
                  var5 = 2;
               }
               break;
            case -1808118735:
               if (var2.equals("String")) {
                  var5 = 3;
               }
               break;
            case 2439591:
               if (var2.equals("Null")) {
                  var5 = 1;
               }
               break;
            case 965837104:
               if (var2.equals("Undefined")) {
                  var5 = 0;
               }
               break;
            case 1729365000:
               if (var2.equals("Boolean")) {
                  var5 = 4;
               }
            }

            switch(var5) {
            case 0:
            case 1:
               return true;
            case 2:
               double var6 = ((Double)((dt)var0).zzDo()).doubleValue();
               double var8 = ((Double)((dt)var1).zzDo()).doubleValue();
               if (!Double.isNaN(var6) && !Double.isNaN(var8)) {
                  if (var6 == var8) {
                     return true;
                  }

                  return false;
               }

               return false;
            case 3:
               String var10 = (String)((eb)var0).value();
               String var11 = (String)((eb)var1).value();
               if (var10.equals(var11)) {
                  return true;
               }

               return false;
            case 4:
               if ((Boolean)((ds)var0).zzDn() == (Boolean)((ds)var1).zzDn()) {
                  return true;
               }

               return false;
            default:
               return var0 == var1;
            }
         }
      }
   }

   private static boolean zzf(dp var0) {
      return var0 instanceof ea || var0 instanceof dv && var0 != dv.zzbLu && var0 != dv.zzbLt;
   }
}
