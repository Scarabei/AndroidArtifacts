package com.google.android.gms.internal;

import com.google.android.gms.common.zze;

public final class zzami {
   public static final String VERSION;
   public static final String zzafL;

   static {
      VERSION = String.valueOf(zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
      String var10001 = String.valueOf(VERSION);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "ma".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("ma");
      }

      zzafL = var10000;
   }
}
