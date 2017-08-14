package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzdj;
import com.google.android.gms.tagmanager.zzgk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class eg {
   public static ek zza(zzbn var0) throws eo {
      zzbr[] var1 = new zzbr[var0.zzkP.length];

      for(int var2 = 0; var2 < var0.zzkP.length; ++var2) {
         zza(var2, var0, var1, new HashSet(0));
      }

      el var10 = ek.zzDz();
      ArrayList var3 = new ArrayList();

      for(int var4 = 0; var4 < var0.zzkS.length; ++var4) {
         var3.add(zza(var0.zzkS[var4], var0, var1, var4));
      }

      ArrayList var11 = new ArrayList();

      for(int var5 = 0; var5 < var0.zzkT.length; ++var5) {
         var11.add(zza(var0.zzkT[var5], var0, var1, var5));
      }

      ArrayList var12 = new ArrayList();

      for(int var6 = 0; var6 < var0.zzkR.length; ++var6) {
         ei var7 = zza(var0.zzkR[var6], var0, var1, var6);
         var10.zzc(var7);
         var12.add(var7);
      }

      zzbo[] var13 = var0.zzkU;
      int var14 = var0.zzkU.length;

      for(int var8 = 0; var8 < var14; ++var8) {
         zzbo var9 = var13[var8];
         var10.zzb(zza(var9, var3, var12, var11, var0));
      }

      var10.zzgd(var0.version);
      var10.zzbJ(var0.zzlc);
      return var10.zzDB();
   }

   public static zzbr zzj(zzbr var0) {
      zzbr var1;
      (var1 = new zzbr()).type = var0.type;
      var1.zzlM = (int[])var0.zzlM.clone();
      if (var0.zzlN) {
         var1.zzlN = var0.zzlN;
      }

      return var1;
   }

   private static zzbr zza(int var0, zzbn var1, zzbr[] var2, Set var3) throws eo {
      if (var3.contains(var0)) {
         String var4 = String.valueOf(var3);
         zzfQ((new StringBuilder(90 + String.valueOf(var4).length())).append("Value cycle detected.  Current value reference: ").append(var0).append(".  Previous value references: ").append(var4).append(".").toString());
      }

      zzbr var12 = (zzbr)zza(var1.zzkP, var0, "values");
      if (var2[var0] != null) {
         return var2[var0];
      } else {
         zzbr var5;
         var5 = null;
         var3.add(var0);
         zzbh.zza var6;
         int var7;
         int[] var8;
         int var9;
         int var10;
         int var11;
         label57:
         switch(var12.type) {
         case 1:
         case 5:
         case 6:
         case 8:
            var5 = var12;
            break;
         case 2:
            var6 = zzk(var12);
            (var5 = zzj(var12)).zzlE = new zzbr[var6.zzlq.length];
            var7 = 0;
            var8 = var6.zzlq;
            var9 = var6.zzlq.length;
            var10 = 0;

            while(true) {
               if (var10 >= var9) {
                  break label57;
               }

               var11 = var8[var10];
               var5.zzlE[var7++] = zza(var11, var1, var2, var3);
               ++var10;
            }
         case 3:
            var5 = zzj(var12);
            if ((var6 = zzk(var12)).zzlr.length != var6.zzls.length) {
               var7 = var6.zzlr.length;
               int var15 = var6.zzls.length;
               zzfQ((new StringBuilder(58)).append("Uneven map keys (").append(var7).append(") and map values (").append(var15).append(")").toString());
            }

            var5.zzlF = new zzbr[var6.zzlr.length];
            var5.zzlG = new zzbr[var6.zzlr.length];
            var7 = 0;
            var8 = var6.zzlr;
            var9 = var6.zzlr.length;

            for(var10 = 0; var10 < var9; ++var10) {
               var11 = var8[var10];
               var5.zzlF[var7++] = zza(var11, var1, var2, var3);
            }

            var7 = 0;
            var8 = var6.zzls;
            var9 = var6.zzls.length;
            var10 = 0;

            while(true) {
               if (var10 >= var9) {
                  break label57;
               }

               var11 = var8[var10];
               var5.zzlG[var7++] = zza(var11, var1, var2, var3);
               ++var10;
            }
         case 4:
            var5 = zzj(var12);
            String var14 = zzgk.zzb(zza(zzk(var12).zzlv, var1, var2, var3));
            var5.zzlH = var14;
            break;
         case 7:
            var5 = zzj(var12);
            var6 = zzk(var12);
            var5.zzlL = new zzbr[var6.zzlu.length];
            var7 = 0;
            var8 = var6.zzlu;
            var9 = var6.zzlu.length;

            for(var10 = 0; var10 < var9; ++var10) {
               var11 = var8[var10];
               var5.zzlL[var7++] = zza(var11, var1, var2, var3);
            }
         }

         if (var5 == null) {
            String var13 = String.valueOf(var12);
            zzfQ((new StringBuilder(15 + String.valueOf(var13).length())).append("Invalid value: ").append(var13).toString());
         }

         var2[var0] = var5;
         var3.remove(var0);
         return var5;
      }
   }

   private static zzbh.zza zzk(zzbr var0) throws eo {
      if ((zzbh.zza)var0.zza((adk)zzbh.zza.zzlo) == null) {
         String var1 = String.valueOf(var0);
         zzfQ((new StringBuilder(54 + String.valueOf(var1).length())).append("Expected a ServingValue and didn't get one. Value is: ").append(var1).toString());
      }

      return (zzbh.zza)var0.zza((adk)zzbh.zza.zzlo);
   }

   private static void zzfQ(String var0) throws eo {
      zzdj.e(var0);
      throw new eo(var0);
   }

   private static Object zza(Object[] var0, int var1, String var2) throws eo {
      if (var1 < 0 || var1 >= var0.length) {
         zzfQ((new StringBuilder(45 + String.valueOf(var2).length())).append("Index out of bounds detected: ").append(var1).append(" in ").append(var2).toString());
      }

      return var0[var1];
   }

   private static ei zza(zzbj var0, zzbn var1, zzbr[] var2, int var3) throws eo {
      ej var4 = ei.zzDx();
      int[] var5 = var0.zzkA;
      int var6 = var0.zzkA.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Integer var8 = var5[var7];
         zzbm var9 = (zzbm)zza(var1.zzkQ, var8.intValue(), "properties");
         String var10 = (String)zza(var1.zzkO, var9.key, "keys");
         zzbr var11 = (zzbr)zza(var2, var9.value, "values");
         if (zzbg.zziZ.toString().equals(var10)) {
            var4.zzl(var11);
         } else {
            var4.zzb(var10, var11);
         }
      }

      return var4.zzDy();
   }

   private static em zza(zzbo var0, List var1, List var2, List var3, zzbn var4) {
      en var5 = new en((eh)null);
      int[] var6 = var0.zzle;
      int var7 = var0.zzle.length;

      int var8;
      Integer var9;
      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzd((ei)var3.get(var9.intValue()));
      }

      var6 = var0.zzlf;
      var7 = var0.zzlf.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zze((ei)var3.get(var9.intValue()));
      }

      var6 = var0.zzlg;
      var7 = var0.zzlg.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzf((ei)var1.get(var9.intValue()));
      }

      var6 = var0.zzli;
      var7 = var0.zzli.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzge(var4.zzkP[var9.intValue()].string);
      }

      var6 = var0.zzlh;
      var7 = var0.zzlh.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzg((ei)var1.get(var9.intValue()));
      }

      var6 = var0.zzlj;
      var7 = var0.zzlj.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzgf(var4.zzkP[var9.intValue()].string);
      }

      var6 = var0.zzlk;
      var7 = var0.zzlk.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzh((ei)var2.get(var9.intValue()));
      }

      var6 = var0.zzlm;
      var7 = var0.zzlm.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzgg(var4.zzkP[var9.intValue()].string);
      }

      var6 = var0.zzll;
      var7 = var0.zzll.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzi((ei)var2.get(var9.intValue()));
      }

      var6 = var0.zzln;
      var7 = var0.zzln.length;

      for(var8 = 0; var8 < var7; ++var8) {
         var9 = var6[var8];
         var5.zzgh(var4.zzkP[var9.intValue()].string);
      }

      return var5.zzDE();
   }

   public static void zzb(InputStream var0, OutputStream var1) throws IOException {
      byte[] var2 = new byte[1024];

      int var3;
      while((var3 = var0.read(var2)) != -1) {
         var1.write(var2, 0, var3);
      }

   }
}
