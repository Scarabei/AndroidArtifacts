package com.google.android.gms.internal;

public final class zzcai {
   public static String zzbc(int var0) {
      switch(var0) {
      case 0:
         return "DAILY";
      case 1:
         return "WEEKLY";
      case 2:
         return "ALL_TIME";
      default:
         throw new IllegalArgumentException((new StringBuilder(29)).append("Unknown time span ").append(var0).toString());
      }
   }
}
