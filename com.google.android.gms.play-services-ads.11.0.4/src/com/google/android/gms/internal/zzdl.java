package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

public final class zzdl extends zzec {
   private static volatile zzbu zzrm = null;
   private static final Object zzrl = new Object();
   private zzau zzrn = null;

   public zzdl(zzdb var1, String var2, String var3, zzax var4, int var5, int var6, zzau var7) {
      super(var1, var2, var3, var4, var5, 27);
      this.zzrn = var7;
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (zzrm == null || zzdg.zzo(zzrm.zzaT) || zzrm.zzaT.equals("E") || zzrm.zzaT.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            byte var13;
            if (!zzdg.zzo(zza(this.zzrn))) {
               var13 = 4;
            } else {
               label113: {
                  zzau var5 = this.zzrn;
                  if (zzdg.zzo(zza(this.zzrn)) && var5 != null && var5.zzaQ != null && var5.zzaQ.zzaS.intValue() == 3.booleanValue()) {
                     boolean var10001;
                     label74: {
                        if (this.zzpJ.zzJ()) {
                           zzme var6 = zzmo.zzFc;
                           if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
                              var6 = zzmo.zzFd;
                              if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
                                 var10001 = true;
                                 break label74;
                              }
                           }
                        }

                        var10001 = false;
                     }

                     if (var10001) {
                        var13 = 3;
                        break label113;
                     }
                  }

                  var13 = 2;
               }
            }

            byte var11 = var13;
            String var12 = (String)this.zzrx.invoke((Object)null, this.zzpJ.getContext(), var11 == 2);
            if (zzdg.zzo((zzrm = new zzbu(var12)).zzaT) || zzrm.zzaT.equals("E")) {
               switch(var11) {
               case 3:
                  String var7;
                  if (!zzdg.zzo(var7 = this.zzU())) {
                     zzrm.zzaT = var7;
                  }
                  break;
               case 4:
                  zzrm.zzaT = this.zzrn.zzaR.zzaT;
               }
            }
         }
      }

      zzax var10 = this.zzro;
      synchronized(this.zzro) {
         if (zzrm != null) {
            this.zzro.zzaT = zzrm.zzaT;
            this.zzro.zzbx = zzrm.zzlO;
            this.zzro.zzaV = zzrm.zzaV;
            this.zzro.zzaW = zzrm.zzaW;
            this.zzro.zzaX = zzrm.zzaX;
         }

      }
   }

   private static String zza(zzau var0) {
      return var0 != null && var0.zzaR != null && !zzdg.zzo(var0.zzaR.zzaT) ? var0.zzaR.zzaT : null;
   }

   private final String zzU() {
      try {
         if (this.zzpJ.zzL() != null) {
            this.zzpJ.zzL().get();
         }

         zzax var1;
         if ((var1 = this.zzpJ.zzK()) != null && var1.zzaT != null) {
            return var1.zzaT;
         }
      } catch (ExecutionException | InterruptedException var2) {
         ;
      }

      return null;
   }
}
