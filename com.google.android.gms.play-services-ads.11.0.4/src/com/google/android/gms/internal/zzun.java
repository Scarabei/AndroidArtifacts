package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzun implements zztz {
   private final zzaae zzMM;
   private final zzuq zzsX;
   private final Context mContext;
   private final Object mLock = new Object();
   private final zzub zzMu;
   private final boolean zzwJ;
   private final long mStartTime;
   private final long zzMN;
   private final zznb zzsK;
   private final boolean zzMy;
   private final String zzMR;
   private boolean zzMP = false;
   private zzue zzMW;
   private List zzMS = new ArrayList();

   public zzun(Context var1, zzaae var2, zzuq var3, zzub var4, boolean var5, boolean var6, String var7, long var8, long var10, zznb var12) {
      this.mContext = var1;
      this.zzMM = var2;
      this.zzsX = var3;
      this.zzMu = var4;
      this.zzwJ = var5;
      this.zzMy = var6;
      this.zzMR = var7;
      this.mStartTime = var8;
      this.zzMN = var10;
      this.zzsK = var12;
   }

   public final zzuh zzf(List var1) {
      zzafr.zzaC("Starting mediation.");
      ArrayList var2 = new ArrayList();
      zzmz var3 = this.zzsK.zzdS();
      zziv var4 = this.zzMM.zzvX;
      int[] var5 = new int[2];
      if (var4.zzAu != null) {
         com.google.android.gms.ads.internal.zzbs.zzbS();
         if (zzuj.zza(this.zzMR, var5)) {
            int var6 = var5[0];
            int var7 = var5[1];
            zziv[] var8 = var4.zzAu;
            int var9 = var4.zzAu.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               zziv var11 = var8[var10];
               if (var6 == var11.width && var7 == var11.height) {
                  var4 = var11;
                  break;
               }
            }
         }
      }

      Iterator var14 = var1.iterator();

      while(var14.hasNext()) {
         zzua var15 = (zzua)var14.next();
         String var10001 = String.valueOf(var15.zzLI);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Trying mediation network: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Trying mediation network: ");
         }

         zzafr.zzaS(var10000);
         Iterator var16 = var15.zzLJ.iterator();

         while(var16.hasNext()) {
            String var17 = (String)var16.next();
            zzmz var18 = this.zzsK.zzdS();
            Object var19 = this.mLock;
            synchronized(this.mLock) {
               if (this.zzMP) {
                  return new zzuh(-1);
               }

               this.zzMW = new zzue(this.mContext, var17, this.zzsX, this.zzMu, var15, this.zzMM.zzSz, var4, this.zzMM.zzvT, this.zzwJ, this.zzMy, this.zzMM.zzwj, this.zzMM.zzwq, this.zzMM.zzSO, this.zzMM.zzTj);
            }

            zzuh var20 = this.zzMW.zza(this.mStartTime, this.zzMN);
            this.zzMS.add(var20);
            if (var20.zzMF == 0) {
               zzafr.zzaC("Adapter succeeded.");
               this.zzsK.zzh("mediation_network_succeed", var17);
               if (!var2.isEmpty()) {
                  this.zzsK.zzh("mediation_networks_fail", TextUtils.join(",", var2));
               }

               this.zzsK.zza(var18, "mls");
               this.zzsK.zza(var3, "ttm");
               return var20;
            }

            var2.add(var17);
            this.zzsK.zza(var18, "mlf");
            if (var20.zzMH != null) {
               zzagz.zzZr.post(new zzuo(this, var20));
            }
         }
      }

      if (!var2.isEmpty()) {
         this.zzsK.zzh("mediation_networks_fail", TextUtils.join(",", var2));
      }

      return new zzuh(1);
   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzMP = true;
         if (this.zzMW != null) {
            this.zzMW.cancel();
         }

      }
   }

   public final List zzfg() {
      return this.zzMS;
   }
}
