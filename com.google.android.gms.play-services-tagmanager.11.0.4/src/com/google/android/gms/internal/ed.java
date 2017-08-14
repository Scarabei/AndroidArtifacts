package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class ed {
   public static dp zzQ(@Nullable Object var0) {
      if (var0 == null) {
         return dv.zzbLt;
      } else if (var0 instanceof dp) {
         return (dp)var0;
      } else if (var0 instanceof Boolean) {
         return new ds((Boolean)var0);
      } else if (var0 instanceof Short) {
         return new dt(((Short)var0).doubleValue());
      } else if (var0 instanceof Integer) {
         return new dt(((Integer)var0).doubleValue());
      } else if (var0 instanceof Long) {
         return new dt(((Long)var0).doubleValue());
      } else if (var0 instanceof Float) {
         return new dt(((Float)var0).doubleValue());
      } else if (var0 instanceof Double) {
         return new dt((Double)var0);
      } else if (var0 instanceof Byte) {
         return new eb(var0.toString());
      } else if (var0 instanceof Character) {
         return new eb(var0.toString());
      } else if (var0 instanceof String) {
         return new eb((String)var0);
      } else {
         Iterator var2;
         if (var0 instanceof List) {
            ArrayList var6 = new ArrayList();
            var2 = ((List)var0).iterator();

            while(var2.hasNext()) {
               Object var8 = var2.next();
               var6.add(zzQ(var8));
            }

            return new dw(var6);
         } else {
            HashMap var1;
            if (var0 instanceof Map) {
               var1 = new HashMap();
               var2 = ((Map)var0).entrySet().iterator();

               while(var2.hasNext()) {
                  Entry var7;
                  zzbo.zzaf((var7 = (Entry)var2.next()).getKey() instanceof String);
                  var1.put((String)var7.getKey(), zzQ(var7.getValue()));
               }

               return new dz(var1);
            } else if (!(var0 instanceof Bundle)) {
               String var5 = String.valueOf(var0.getClass());
               throw new UnsupportedOperationException((new StringBuilder(20 + String.valueOf(var5).length())).append("Type not supported: ").append(var5).toString());
            } else {
               var1 = new HashMap();
               var2 = ((Bundle)var0).keySet().iterator();

               while(var2.hasNext()) {
                  String var3 = (String)var2.next();
                  Object var4 = ((Bundle)var0).get(var3);
                  var1.put(var3, zzQ(var4));
               }

               return new dz(var1);
            }
         }
      }
   }

   public static Object zzj(dp var0) {
      if (var0 == null) {
         return null;
      } else if (var0 == dv.zzbLt) {
         return null;
      } else if (var0 instanceof ds) {
         return (Boolean)((ds)var0).zzDl();
      } else if (var0 instanceof dt) {
         double var7;
         return !Double.isInfinite(var7 = ((Double)((dt)var0).zzDl()).doubleValue()) && var7 - Math.floor(var7) < 1.0E-5D ? (int)var7 : ((Double)((dt)var0).zzDl()).toString();
      } else if (var0 instanceof eb) {
         return (String)((eb)var0).zzDl();
      } else {
         Iterator var2;
         Object var4;
         if (var0 instanceof dw) {
            ArrayList var6 = new ArrayList();
            var2 = ((List)((dw)var0).zzDl()).iterator();

            while(var2.hasNext()) {
               dp var8;
               if ((var4 = zzj(var8 = (dp)var2.next())) == null) {
                  zzcvk.e(String.format("Failure to convert a list element to object: %s (%s)", var8, var8.getClass().getCanonicalName()));
                  return null;
               }

               var6.add(var4);
            }

            return var6;
         } else if (var0 instanceof dz) {
            HashMap var5 = new HashMap();
            var2 = ((Map)((dz)var0).zzDl()).entrySet().iterator();

            while(var2.hasNext()) {
               Entry var3;
               if ((var4 = zzj((dp)(var3 = (Entry)var2.next()).getValue())) == null) {
                  zzcvk.e(String.format("Failure to convert a map's value to object: %s (%s)", var3.getValue(), ((dp)var3.getValue()).getClass().getCanonicalName()));
                  return null;
               }

               var5.put((String)var3.getKey(), var4);
            }

            return var5;
         } else {
            String var1 = String.valueOf(var0.getClass());
            zzcvk.e((new StringBuilder(49 + String.valueOf(var1).length())).append("Converting to Object from unknown abstract type: ").append(var1).toString());
            return null;
         }
      }
   }

   public static Bundle zzy(Map var0) {
      if (var0 == null) {
         return null;
      } else {
         Bundle var1 = new Bundle(var0.size());
         Iterator var2 = var0.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3;
            if ((var3 = (Entry)var2.next()).getValue() instanceof eb) {
               var1.putString((String)var3.getKey(), (String)((eb)var3.getValue()).zzDl());
            } else if (var3.getValue() instanceof ds) {
               var1.putBoolean((String)var3.getKey(), ((Boolean)((ds)var3.getValue()).zzDl()).booleanValue());
            } else if (var3.getValue() instanceof dt) {
               var1.putDouble((String)var3.getKey(), ((Double)((dt)var3.getValue()).zzDl()).doubleValue());
            } else {
               if (!(var3.getValue() instanceof dz)) {
                  throw new IllegalArgumentException(String.format("Invalid param type for key '%s'. Only boolean, double and string types and maps of thereof are supported.", var3.getKey()));
               }

               var1.putBundle((String)var3.getKey(), zzy((Map)((dz)var3.getValue()).zzDl()));
            }
         }

         return var1;
      }
   }

   public static Map zzC(Bundle var0) {
      HashMap var1 = new HashMap();
      Iterator var2 = var0.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         Object var4;
         if ((var4 = var0.get(var3)) instanceof Bundle) {
            var1.put(var3, zzC((Bundle)var4));
         } else if (var4 instanceof List) {
            var1.put(var3, zzM((List)var4));
         } else {
            var1.put(var3, var4);
         }
      }

      return var1;
   }

   private static List zzM(List var0) {
      ArrayList var1 = new ArrayList();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         Object var3;
         if ((var3 = var2.next()) instanceof Bundle) {
            var1.add(zzC((Bundle)var3));
         } else if (var3 instanceof List) {
            var1.add(zzM((List)var3));
         } else {
            var1.add(var3);
         }
      }

      return var1;
   }

   public static dp zzk(dp var0) {
      if (!(var0 instanceof dz)) {
         return var0;
      } else {
         HashSet var1 = new HashSet();
         Map var2;
         Iterator var3 = (var2 = (Map)((dz)var0).zzDl()).entrySet().iterator();

         while(var3.hasNext()) {
            Entry var4;
            if ((var4 = (Entry)var3.next()).getValue() == dv.zzbLu) {
               var1.add((String)var4.getKey());
            }
         }

         var3 = var1.iterator();

         while(var3.hasNext()) {
            String var5 = (String)var3.next();
            var2.remove(var5);
         }

         return var0;
      }
   }

   public static dp zza(zzcwa var0, dp var1) {
      zzbo.zzu(var1);
      dp var2;
      if (!zzl(var1) && !(var1 instanceof du) && !(var1 instanceof dw) && !(var1 instanceof dz)) {
         if (!(var1 instanceof ea)) {
            throw new UnsupportedOperationException("Attempting to evaluate unknown type");
         }

         var2 = zza(var0, (ea)var1);
      } else {
         var2 = var1;
      }

      if (var2 != null) {
         if (var2 instanceof ea) {
            throw new IllegalArgumentException("AbstractType evaluated to illegal type Statement.");
         } else {
            return var2;
         }
      } else {
         throw new IllegalArgumentException("AbstractType evaluated to Java null");
      }
   }

   public static dv zza(zzcwa var0, List var1) {
      Iterator var2 = var1.iterator();

      dp var3;
      dp var4;
      do {
         if (!var2.hasNext()) {
            return dv.zzbLu;
         }

         zzbo.zzaf((var3 = (dp)var2.next()) instanceof ea);
      } while(!zzm(var4 = zza(var0, var3)));

      return (dv)var4;
   }

   public static boolean zzl(dp var0) {
      if (!(var0 instanceof ds) && !(var0 instanceof dt) && !(var0 instanceof eb)) {
         return var0 == dv.zzbLt || var0 == dv.zzbLu;
      } else {
         return true;
      }
   }

   public static boolean zzm(dp var0) {
      return var0 == dv.zzbLs || var0 == dv.zzbLr || var0 instanceof dv && ((dv)var0).zzDr();
   }

   public static dp zza(zzcwa var0, ea var1) {
      String var2 = var1.zzDv();
      List var3 = var1.zzDw();
      dp var4;
      if ((var4 = var0.zzfK(var2)) == null) {
         throw new UnsupportedOperationException((new StringBuilder(28 + String.valueOf(var2).length())).append("Function '").append(var2).append("' is not supported").toString());
      } else if (!(var4 instanceof du)) {
         throw new UnsupportedOperationException((new StringBuilder(29 + String.valueOf(var2).length())).append("Function '").append(var2).append("' is not a function").toString());
      } else {
         return ((zzcxo)((du)var4).zzDl()).zzb(var0, (dp[])var3.toArray(new dp[var3.size()]));
      }
   }
}
