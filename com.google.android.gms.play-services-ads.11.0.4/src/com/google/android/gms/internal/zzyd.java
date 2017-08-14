package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zzzn
public final class zzyd extends zzxt {
   private zzuq zzsX;
   private zztz zzRc;
   private zzub zzMu;
   protected zzuh zzRd;
   private final zznb zzsK;
   private final zzaka zzJH;
   private boolean zzRe;

   zzyd(Context var1, zzafg var2, zzuq var3, zzxy var4, zznb var5, zzaka var6) {
      super(var1, var2, var4);
      this.zzsX = var3;
      this.zzMu = var2.zzXN;
      this.zzsK = var5;
      this.zzJH = var6;
   }

   protected final zzaff zzs(int var1) {
      zzaae var2 = this.zzQQ.zzUj;
      return new zzaff(var2.zzSz, this.zzJH, this.zzQR.zzMa, var1, this.zzQR.zzMb, this.zzQR.zzTq, this.zzQR.orientation, this.zzQR.zzMg, var2.zzSC, this.zzQR.zzTo, this.zzRd != null ? this.zzRd.zzMG : null, this.zzRd != null ? this.zzRd.zzMH : null, this.zzRd != null ? this.zzRd.zzMI : AdMobAdapter.class.getName(), this.zzMu, this.zzRd != null ? this.zzRd.zzMJ : null, this.zzQR.zzTp, this.zzQQ.zzvX, this.zzQR.zzTn, this.zzQQ.zzXR, this.zzQR.zzTs, this.zzQR.zzTt, this.zzQQ.zzXL, (zzoa)null, this.zzQR.zzTD, this.zzQR.zzTE, this.zzQR.zzTF, this.zzMu != null ? this.zzMu.zzMl : false, this.zzQR.zzTH, this.zzRc != null ? zzi(this.zzRc.zzfg()) : null, this.zzQR.zzMd, this.zzQR.zzTK, this.zzQQ.zzXX);
   }

   protected final void zzd(long var1) throws zzxw {
      Object var3 = this.zzQT;
      synchronized(this.zzQT) {
         zzme var11;
         Object var10001;
         if (this.zzMu.zzMj != -1) {
            var11 = zzmo.zzEL;
            var10001 = new zzuk(this.mContext, this.zzQQ.zzUj, this.zzsX, this.zzMu, this.zzQR.zzAv, this.zzQR.zzAx, this.zzQR.zzTI, var1, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var11)).longValue(), 2);
         } else {
            var11 = zzmo.zzEL;
            var10001 = new zzun(this.mContext, this.zzQQ.zzUj, this.zzsX, this.zzMu, this.zzQR.zzAv, this.zzQR.zzAx, this.zzQR.zzTI, var1, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var11)).longValue(), this.zzsK);
         }

         this.zzRc = (zztz)var10001;
      }

      ArrayList var16 = new ArrayList(this.zzMu.zzLY);
      boolean var4 = false;
      Bundle var5 = this.zzQQ.zzUj.zzSz.zzzX;
      String var6 = "com.google.ads.mediation.admob.AdMobAdapter";
      Bundle var7;
      if (var5 != null && (var7 = var5.getBundle(var6)) != null) {
         var4 = var7.getBoolean("_skipMediation");
      }

      if (var4) {
         ListIterator var17 = var16.listIterator();

         while(var17.hasNext()) {
            if (!((zzua)var17.next()).zzLJ.contains(var6)) {
               var17.remove();
            }
         }
      }

      this.zzRd = this.zzRc.zzf(var16);
      switch(this.zzRd.zzMF) {
      case 0:
         if (this.zzRd.zzMG != null && this.zzRd.zzMG.zzLS != null) {
            zzyd var8 = this;
            CountDownLatch var9 = new CountDownLatch(1);
            zzagz.zzZr.post(new zzye(this, var9));

            try {
               var9.await(10L, TimeUnit.SECONDS);
            } catch (InterruptedException var14) {
               String var19 = String.valueOf(var14);
               throw new zzxw((new StringBuilder(38 + String.valueOf(var19).length())).append("Interrupted while waiting for latch : ").append(var19).toString(), 0);
            }

            Object var10 = this.zzQT;
            synchronized(this.zzQT) {
               if (!var8.zzRe) {
                  throw new zzxw("View could not be prepared", 0);
               }

               if (var8.zzJH.isDestroyed()) {
                  throw new zzxw("Assets not loaded, web view is destroyed", 0);
               }

               return;
            }
         }

         return;
      case 1:
         throw new zzxw("No fill from any mediation ad networks.", 3);
      default:
         int var18 = this.zzRd.zzMF;
         throw new zzxw((new StringBuilder(40)).append("Unexpected mediation result: ").append(var18).toString(), 0);
      }
   }

   public final void onStop() {
      Object var1 = this.zzQT;
      synchronized(this.zzQT) {
         super.onStop();
         if (this.zzRc != null) {
            this.zzRc.cancel();
         }

      }
   }

   private static String zzi(List var0) {
      String var1 = "";
      if (var0 == null) {
         return var1.toString();
      } else {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            zzuh var3;
            if ((var3 = (zzuh)var2.next()) != null && var3.zzMG != null && !TextUtils.isEmpty(var3.zzMG.zzLK)) {
               String var4 = String.valueOf(var1);
               String var7 = var3.zzMG.zzLK;
               byte var10000;
               switch(var3.zzMF) {
               case -1:
                  var10000 = 4;
                  break;
               case 0:
                  var10000 = 0;
                  break;
               case 1:
                  var10000 = 1;
                  break;
               case 2:
               default:
                  var10000 = 6;
                  break;
               case 3:
                  var10000 = 2;
                  break;
               case 4:
                  var10000 = 3;
                  break;
               case 5:
                  var10000 = 5;
               }

               byte var8 = var10000;
               long var9 = var3.zzML;
               String var5 = String.valueOf((new StringBuilder(33 + String.valueOf(var7).length())).append(var7).append(".").append(var8).append(".").append(var9).toString());
               var1 = (new StringBuilder(1 + String.valueOf(var4).length() + String.valueOf(var5).length())).append(var4).append(var5).append("_").toString();
            }
         }

         return var1.substring(0, Math.max(0, var1.length() - 1));
      }
   }

   // $FF: synthetic method
   static boolean zza(zzyd var0, boolean var1) {
      return var0.zzRe = var1;
   }

   // $FF: synthetic method
   static zzaka zza(zzyd var0) {
      return var0.zzJH;
   }
}
