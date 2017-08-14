package com.google.android.gms.internal;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public final class hc {
   public static hd zza(DataMap var0) {
      he var1 = new he();
      ArrayList var2 = new ArrayList();
      var1.zzbTH = zza((DataMap)var0, (List)var2);
      return new hd(var1, var2);
   }

   public static DataMap zza(hd var0) {
      DataMap var1 = new DataMap();
      hf[] var2 = var0.zzbTF.zzbTH;
      int var3 = var0.zzbTF.zzbTH.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         hf var5 = var2[var4];
         zza(var0.zzbTG, var1, var5.name, var5.zzbTJ);
      }

      return var1;
   }

   private static hf[] zza(DataMap var0, List var1) {
      TreeSet var2;
      hf[] var3 = new hf[(var2 = new TreeSet(var0.keySet())).size()];
      int var4 = 0;

      for(Iterator var5 = var2.iterator(); var5.hasNext(); ++var4) {
         String var6 = (String)var5.next();
         Object var7 = var0.get(var6);
         var3[var4] = new hf();
         var3[var4].name = var6;
         var3[var4].zzbTJ = zza(var1, var7);
      }

      return var3;
   }

   private static hg zza(List var0, Object var1) {
      hg var2 = new hg();
      if (var1 == null) {
         var2.type = 14;
         return var2;
      } else {
         var2.zzbTL = new hh();
         if (var1 instanceof String) {
            var2.type = 2;
            var2.zzbTL.zzbTN = (String)var1;
         } else if (var1 instanceof Integer) {
            var2.type = 6;
            var2.zzbTL.zzbTR = ((Integer)var1).intValue();
         } else if (var1 instanceof Long) {
            var2.type = 5;
            var2.zzbTL.zzbTQ = ((Long)var1).longValue();
         } else if (var1 instanceof Double) {
            var2.type = 3;
            var2.zzbTL.zzbTO = ((Double)var1).doubleValue();
         } else if (var1 instanceof Float) {
            var2.type = 4;
            var2.zzbTL.zzbTP = ((Float)var1).floatValue();
         } else if (var1 instanceof Boolean) {
            var2.type = 8;
            var2.zzbTL.zzbTT = ((Boolean)var1).booleanValue();
         } else if (var1 instanceof Byte) {
            var2.type = 7;
            var2.zzbTL.zzbTS = ((Byte)var1).byteValue();
         } else if (var1 instanceof byte[]) {
            var2.type = 1;
            var2.zzbTL.zzbTM = (byte[])var1;
         } else if (var1 instanceof String[]) {
            var2.type = 11;
            var2.zzbTL.zzbTW = (String[])var1;
         } else if (var1 instanceof long[]) {
            var2.type = 12;
            var2.zzbTL.zzbTX = (long[])var1;
         } else if (var1 instanceof float[]) {
            var2.type = 15;
            var2.zzbTL.zzbTY = (float[])var1;
         } else if (var1 instanceof Asset) {
            var2.type = 13;
            hh var10000 = var2.zzbTL;
            Asset var14 = (Asset)var1;
            var0.add(var14);
            var10000.zzbTZ = (long)(var0.size() - 1);
         } else if (var1 instanceof DataMap) {
            var2.type = 9;
            DataMap var3 = (DataMap)var1;
            TreeSet var4;
            hf[] var5 = new hf[(var4 = new TreeSet(var3.keySet())).size()];
            int var6 = 0;

            for(Iterator var7 = var4.iterator(); var7.hasNext(); ++var6) {
               String var8 = (String)var7.next();
               var5[var6] = new hf();
               var5[var6].name = var8;
               var5[var6].zzbTJ = zza(var0, var3.get(var8));
            }

            var2.zzbTL.zzbTU = var5;
         } else {
            if (!(var1 instanceof ArrayList)) {
               RuntimeException var21 = new RuntimeException;
               String var10003 = String.valueOf(var1.getClass().getSimpleName());
               String var10002;
               if (var10003.length() != 0) {
                  var10002 = "newFieldValueFromValue: unexpected value ".concat(var10003);
               } else {
                  String var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("newFieldValueFromValue: unexpected value ");
               }

               var21.<init>(var10002);
               throw var21;
            }

            var2.type = 10;
            ArrayList var15;
            hg[] var16 = new hg[(var15 = (ArrayList)var1).size()];
            int var17 = 14;
            Object var18 = null;
            int var19 = 0;

            for(int var20 = var15.size(); var19 < var20; ++var19) {
               Object var9 = var15.get(var19);
               hg var10;
               String var11;
               if ((var10 = zza(var0, var9)).type != 14 && var10.type != 2 && var10.type != 6 && var10.type != 9) {
                  var11 = String.valueOf(var9.getClass());
                  throw new IllegalArgumentException((new StringBuilder(130 + String.valueOf(var11).length())).append("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a ").append(var11).toString());
               }

               if (var17 == 14 && var10.type != 14) {
                  var17 = var10.type;
                  var18 = var9;
               } else if (var10.type != var17) {
                  var11 = String.valueOf(var18.getClass());
                  String var12 = String.valueOf(var9.getClass());
                  throw new IllegalArgumentException((new StringBuilder(80 + String.valueOf(var11).length() + String.valueOf(var12).length())).append("ArrayList elements must all be of the sameclass, but this one contains a ").append(var11).append(" and a ").append(var12).toString());
               }

               var16[var19] = var10;
            }

            var2.zzbTL.zzbTV = var16;
         }

         return var2;
      }
   }

   private static void zza(List var0, DataMap var1, String var2, hg var3) {
      int var4 = var3.type;
      if (var3.type == 14) {
         var1.putString(var2, (String)null);
      } else {
         hh var5 = var3.zzbTL;
         if (var4 == 1) {
            var1.putByteArray(var2, var5.zzbTM);
         } else if (var4 == 11) {
            var1.putStringArray(var2, var5.zzbTW);
         } else if (var4 == 12) {
            var1.putLongArray(var2, var5.zzbTX);
         } else if (var4 == 15) {
            var1.putFloatArray(var2, var5.zzbTY);
         } else if (var4 == 2) {
            var1.putString(var2, var5.zzbTN);
         } else if (var4 == 3) {
            var1.putDouble(var2, var5.zzbTO);
         } else if (var4 == 4) {
            var1.putFloat(var2, var5.zzbTP);
         } else if (var4 == 5) {
            var1.putLong(var2, var5.zzbTQ);
         } else if (var4 == 6) {
            var1.putInt(var2, var5.zzbTR);
         } else if (var4 == 7) {
            var1.putByte(var2, (byte)var5.zzbTS);
         } else if (var4 == 8) {
            var1.putBoolean(var2, var5.zzbTT);
         } else if (var4 == 13) {
            if (var0 == null) {
               RuntimeException var10000 = new RuntimeException;
               String var10003 = String.valueOf(var2);
               String var10002;
               if (var10003.length() != 0) {
                  var10002 = "populateBundle: unexpected type for: ".concat(var10003);
               } else {
                  String var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("populateBundle: unexpected type for: ");
               }

               var10000.<init>(var10002);
               throw var10000;
            } else {
               var1.putAsset(var2, (Asset)var0.get((int)var5.zzbTZ));
            }
         } else if (var4 != 9) {
            if (var4 == 10) {
               int var12 = zza(var2, var5.zzbTV);
               ArrayList var7 = zza(var0, var5, var12);
               if (var12 == 14) {
                  var1.putStringArrayList(var2, var7);
               } else if (var12 == 9) {
                  var1.putDataMapArrayList(var2, var7);
               } else if (var12 == 2) {
                  var1.putStringArrayList(var2, var7);
               } else if (var12 == 6) {
                  var1.putIntegerArrayList(var2, var7);
               } else {
                  throw new IllegalStateException((new StringBuilder(39)).append("Unexpected typeOfArrayList: ").append(var12).toString());
               }
            } else {
               throw new RuntimeException((new StringBuilder(43)).append("populateBundle: unexpected type ").append(var4).toString());
            }
         } else {
            DataMap var6 = new DataMap();
            hf[] var8 = var5.zzbTU;
            int var9 = var5.zzbTU.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               hf var11 = var8[var10];
               zza(var0, var6, var11.name, var11.zzbTJ);
            }

            var1.putDataMap(var2, var6);
         }
      }
   }

   private static int zza(String var0, hg[] var1) {
      int var2 = 14;
      hg[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         hg var6 = var3[var5];
         if (var2 == 14) {
            if (var6.type != 9 && var6.type != 2 && var6.type != 6) {
               if (var6.type != 14) {
                  int var7 = var6.type;
                  throw new IllegalArgumentException((new StringBuilder(48 + String.valueOf(var0).length())).append("Unexpected TypedValue type: ").append(var7).append(" for key ").append(var0).toString());
               }
            } else {
               var2 = var6.type;
            }
         } else if (var6.type != var2) {
            int var8 = var6.type;
            throw new IllegalArgumentException((new StringBuilder(126 + String.valueOf(var0).length())).append("The ArrayList elements should all be the same type, but ArrayList with key ").append(var0).append(" contains items of type ").append(var2).append(" and ").append(var8).toString());
         }
      }

      return var2;
   }

   private static ArrayList zza(List var0, hh var1, int var2) {
      ArrayList var3 = new ArrayList(var1.zzbTV.length);
      hg[] var4 = var1.zzbTV;
      int var5 = var1.zzbTV.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         hg var7;
         if ((var7 = var4[var6]).type == 14) {
            var3.add((Object)null);
         } else if (var2 != 9) {
            if (var2 == 2) {
               var3.add(var7.zzbTL.zzbTN);
            } else {
               if (var2 != 6) {
                  throw new IllegalArgumentException((new StringBuilder(39)).append("Unexpected typeOfArrayList: ").append(var2).toString());
               }

               var3.add(var7.zzbTL.zzbTR);
            }
         } else {
            DataMap var8 = new DataMap();
            hf[] var9 = var7.zzbTL.zzbTU;
            int var10 = var7.zzbTL.zzbTU.length;

            for(int var11 = 0; var11 < var10; ++var11) {
               hf var12 = var9[var11];
               zza(var0, var8, var12.name, var12.zzbTJ);
            }

            var3.add(var8);
         }
      }

      return var3;
   }
}
