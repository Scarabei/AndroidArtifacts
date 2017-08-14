package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class zzcy extends zzbr {
   private static final String ID;
   private static final String zzbEH;
   private static final String zzbEZ;
   private static final String zzbFa;
   private static final String zzbFb;

   public zzcy() {
      super(ID, zzbEH);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      com.google.android.gms.internal.zzbr var2;
      if ((var2 = (com.google.android.gms.internal.zzbr)var1.get(zzbEH)) == null) {
         return zzgk.zzCh();
      } else {
         com.google.android.gms.internal.zzbr var3;
         String var4 = (var3 = (com.google.android.gms.internal.zzbr)var1.get(zzbEZ)) != null ? zzgk.zzb(var3) : "";
         com.google.android.gms.internal.zzbr var5;
         String var6 = (var5 = (com.google.android.gms.internal.zzbr)var1.get(zzbFa)) != null ? zzgk.zzb(var5) : "=";
         int var7 = zzda.zzbFd;
         com.google.android.gms.internal.zzbr var8 = (com.google.android.gms.internal.zzbr)var1.get(zzbFb);
         HashSet var9 = null;
         if (var8 != null) {
            String var10 = zzgk.zzb(var8);
            if ("url".equals(var10)) {
               var7 = zzda.zzbFe;
            } else {
               if (!"backslash".equals(var10)) {
                  String var10001 = String.valueOf(var10);
                  String var10000;
                  if (var10001.length() != 0) {
                     var10000 = "Joiner: unsupported escape type: ".concat(var10001);
                  } else {
                     String var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Joiner: unsupported escape type: ");
                  }

                  zzdj.e(var10000);
                  return zzgk.zzCh();
               }

               var7 = zzda.zzbFf;
               zza(var9 = new HashSet(), var4);
               zza(var9, var6);
               var9.remove('\\');
            }
         }

         StringBuilder var16 = new StringBuilder();
         switch(var2.type) {
         case 2:
            boolean var11 = true;
            com.google.android.gms.internal.zzbr[] var17 = var2.zzlE;
            int var18 = var2.zzlE.length;

            for(int var19 = 0; var19 < var18; ++var19) {
               com.google.android.gms.internal.zzbr var15 = var17[var19];
               if (!var11) {
                  var16.append(var4);
               }

               var11 = false;
               zza(var16, zzgk.zzb(var15), var7, var9);
            }

            return zzgk.zzI(var16.toString());
         case 3:
            for(int var12 = 0; var12 < var2.zzlF.length; ++var12) {
               if (var12 > 0) {
                  var16.append(var4);
               }

               String var13 = zzgk.zzb(var2.zzlF[var12]);
               String var14 = zzgk.zzb(var2.zzlG[var12]);
               zza(var16, var13, var7, var9);
               var16.append(var6);
               zza(var16, var14, var7, var9);
            }

            return zzgk.zzI(var16.toString());
         default:
            zza(var16, zzgk.zzb(var2), var7, var9);
            return zzgk.zzI(var16.toString());
         }
      }
   }

   private static void zza(Set var0, String var1) {
      for(int var2 = 0; var2 < var1.length(); ++var2) {
         var0.add(var1.charAt(var2));
      }

   }

   private static void zza(StringBuilder var0, String var1, int var2, Set var3) {
      var0.append(zza(var1, var2, var3));
   }

   private static String zza(String var0, int var1, Set var2) {
      switch(zzcz.zzbFc[var1 - 1]) {
      case 1:
         try {
            return zzgo.zzfC(var0);
         } catch (UnsupportedEncodingException var5) {
            zzdj.zzb("Joiner: unsupported encoding", var5);
            return var0;
         }
      case 2:
         var0 = var0.replace("\\", "\\\\");

         String var10002;
         String var4;
         for(Iterator var3 = var2.iterator(); var3.hasNext(); var0 = var0.replace(var4, var10002)) {
            var4 = ((Character)var3.next()).toString();
            String var10003 = String.valueOf(var4);
            if (var10003.length() != 0) {
               var10002 = "\\".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("\\");
            }
         }

         return var0;
      default:
         return var0;
      }
   }

   static {
      ID = zzbf.zzdZ.toString();
      zzbEH = zzbg.zzfR.toString();
      zzbEZ = zzbg.zzhW.toString();
      zzbFa = zzbg.zzia.toString();
      zzbFb = zzbg.zzhq.toString();
   }
}
