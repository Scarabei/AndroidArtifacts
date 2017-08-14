package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.util.Locale;

@zzzn
public final class zzafo {
   private static final Object zzuF = new Object();
   private static String zzYU;

   public static String zzb(Context var0, String var1, String var2) {
      Object var3 = zzuF;
      synchronized(zzuF) {
         if (zzYU == null && !TextUtils.isEmpty(var1)) {
            String var7 = var2;
            Context var5 = var0;

            ClassLoader var8;
            Class var9;
            try {
               var8 = var5.createPackageContext(var7, 3).getClassLoader();
               var9 = Class.forName("com.google.ads.mediation.MediationAdapter", false, var8);
            } catch (Throwable var13) {
               zzYU = "err";
               return zzYU;
            }

            BigInteger var10 = new BigInteger(new byte[1]);
            String[] var11 = var1.split(",");

            for(int var12 = 0; var12 < var11.length; ++var12) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               if (zzagz.zza(var8, var9, var11[var12])) {
                  var10 = var10.setBit(var12);
               }
            }

            zzYU = String.format(Locale.US, "%X", var10);
         }

         return zzYU;
      }
   }

   public static String zzhK() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         return zzYU;
      }
   }
}
