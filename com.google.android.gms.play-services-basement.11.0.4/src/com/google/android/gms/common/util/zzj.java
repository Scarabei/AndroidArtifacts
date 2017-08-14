package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzj {
   private static Boolean zzaJJ;
   private static Boolean zzaJK;
   private static Boolean zzaJL;
   private static Boolean zzaJM;
   private static Boolean zzaJN;

   public static boolean zza(Resources var0) {
      if (var0 == null) {
         return false;
      } else {
         if (zzaJJ == null) {
            boolean var10000;
            label36: {
               if ((var0.getConfiguration().screenLayout & 15) <= 3) {
                  if (zzaJK == null) {
                     Configuration var2;
                     zzaJK = ((var2 = var0.getConfiguration()).screenLayout & 15) <= 3 && var2.smallestScreenWidthDp >= 600;
                  }

                  if (!zzaJK.booleanValue()) {
                     var10000 = false;
                     break label36;
                  }
               }

               var10000 = true;
            }

            zzaJJ = var10000;
         }

         return zzaJJ.booleanValue();
      }
   }

   @TargetApi(20)
   public static boolean zzaG(Context var0) {
      if (zzaJL == null) {
         zzaJL = zzq.zzsd() && var0.getPackageManager().hasSystemFeature("android.hardware.type.watch");
      }

      return zzaJL.booleanValue();
   }

   @TargetApi(24)
   public static boolean zzaH(Context var0) {
      return (!zzq.isAtLeastN() || zzaI(var0)) && zzaG(var0);
   }

   @TargetApi(21)
   public static boolean zzaI(Context var0) {
      if (zzaJM == null) {
         zzaJM = zzq.zzse() && var0.getPackageManager().hasSystemFeature("cn.google");
      }

      return zzaJM.booleanValue();
   }

   public static boolean zzaJ(Context var0) {
      if (zzaJN == null) {
         zzaJN = var0.getPackageManager().hasSystemFeature("android.hardware.type.iot") || var0.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
      }

      return zzaJN.booleanValue();
   }
}
