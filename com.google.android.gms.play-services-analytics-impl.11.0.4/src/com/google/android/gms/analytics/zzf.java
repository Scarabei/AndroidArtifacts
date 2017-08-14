package com.google.android.gms.analytics;

import com.google.android.gms.internal.zzaob;

public final class zzf {
   public static String zzC(int var0) {
      return zzc("&cd", var0);
   }

   public static String zzD(int var0) {
      return zzc("cd", var0);
   }

   public static String zzE(int var0) {
      return zzc("&cm", var0);
   }

   public static String zzF(int var0) {
      return zzc("cm", var0);
   }

   private static String zzc(String var0, int var1) {
      if (var1 <= 0) {
         zzaob.zzf("index out of range for prefix", var0);
         return "";
      } else {
         return (new StringBuilder(11 + String.valueOf(var0).length())).append(var0).append(var1).toString();
      }
   }

   public static String zzG(int var0) {
      return zzc("&pr", var0);
   }

   public static String zzH(int var0) {
      return zzc("pr", var0);
   }

   public static String zzI(int var0) {
      return zzc("&promo", var0);
   }

   public static String zzJ(int var0) {
      return zzc("promo", var0);
   }

   public static String zzK(int var0) {
      return zzc("pi", var0);
   }

   public static String zzL(int var0) {
      return zzc("&il", var0);
   }

   public static String zzM(int var0) {
      return zzc("il", var0);
   }

   public static String zzN(int var0) {
      return zzc("cd", var0);
   }

   public static String zzO(int var0) {
      return zzc("cm", var0);
   }
}
