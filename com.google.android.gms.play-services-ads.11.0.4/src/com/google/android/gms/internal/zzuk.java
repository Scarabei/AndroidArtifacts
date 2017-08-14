package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
public final class zzuk implements zztz {
   private final zzaae zzMM;
   private final zzuq zzsX;
   private final Context mContext;
   private final zzub zzMu;
   private final boolean zzwJ;
   private final long mStartTime;
   private final long zzMN;
   private final int zzMO;
   private final Object mLock = new Object();
   private boolean zzMP = false;
   private final Map zzMQ = new HashMap();
   private final boolean zzMy;
   private final String zzMR;
   private List zzMS = new ArrayList();

   public zzuk(Context var1, zzaae var2, zzuq var3, zzub var4, boolean var5, boolean var6, String var7, long var8, long var10, int var12) {
      this.mContext = var1;
      this.zzMM = var2;
      this.zzsX = var3;
      this.zzMu = var4;
      this.zzwJ = var5;
      this.zzMy = var6;
      this.zzMR = var7;
      this.mStartTime = var8;
      this.zzMN = var10;
      this.zzMO = 2;
   }

   public final zzuh zzf(List var1) {
      zzafr.zzaC("Starting mediation.");
      ExecutorService var2 = Executors.newCachedThreadPool();
      ArrayList var3 = new ArrayList();
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

      Iterator var12 = var1.iterator();

      while(var12.hasNext()) {
         zzua var13 = (zzua)var12.next();
         String var10001 = String.valueOf(var13.zzLI);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Trying mediation network: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Trying mediation network: ");
         }

         zzafr.zzaS(var10000);
         Iterator var14 = var13.zzLJ.iterator();

         while(var14.hasNext()) {
            String var15 = (String)var14.next();
            zzue var16 = new zzue(this.mContext, var15, this.zzsX, this.zzMu, var13, this.zzMM.zzSz, var4, this.zzMM.zzvT, this.zzwJ, this.zzMy, this.zzMM.zzwj, this.zzMM.zzwq, this.zzMM.zzSO, this.zzMM.zzTj);
            zzajm var17 = zzagt.zza(var2, new zzul(this, var16));
            this.zzMQ.put(var17, var16);
            var3.add(var17);
         }
      }

      switch(this.zzMO) {
      case 2:
         return this.zzh(var3);
      default:
         return this.zzg(var3);
      }
   }

   private final zzuh zzg(List var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMP) {
            return new zzuh(-1);
         }
      }

      Iterator var7 = var1.iterator();

      while(var7.hasNext()) {
         zzajm var3 = (zzajm)var7.next();

         try {
            zzuh var4 = (zzuh)var3.get();
            this.zzMS.add(var4);
            if (var4 != null && var4.zzMF == 0) {
               this.zza(var3);
               return var4;
            }
         } catch (ExecutionException | InterruptedException var5) {
            zzafr.zzc("Exception while processing an adapter; continuing with other adapters", var5);
         }
      }

      this.zza((zzajm)null);
      return new zzuh(1);
   }

   private final zzuh zzh(List var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMP) {
            return new zzuh(-1);
         }
      }

      int var23 = -1;
      zzajm var3 = null;
      zzuh var4 = null;
      long var5 = this.zzMu.zzMk != -1L ? this.zzMu.zzMk : 10000L;
      Iterator var7 = var1.iterator();

      while(var7.hasNext()) {
         zzajm var8 = (zzajm)var7.next();
         long var9 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
         boolean var19 = false;

         long var24;
         label133: {
            try {
               var19 = true;
               zzuh var11;
               if (var5 == 0L && var8.isDone()) {
                  var11 = (zzuh)var8.get();
               } else {
                  var11 = (zzuh)var8.get(var5, TimeUnit.MILLISECONDS);
               }

               this.zzMS.add(var11);
               if (var11 != null) {
                  if (var11.zzMF == 0) {
                     zzuz var12 = var11.zzMK;
                     if (var11.zzMK != null) {
                        if (var12.zzfo() > var23) {
                           var23 = var12.zzfo();
                           var3 = var8;
                           var4 = var11;
                           var19 = false;
                        } else {
                           var19 = false;
                        }
                     } else {
                        var19 = false;
                     }
                  } else {
                     var19 = false;
                  }
               } else {
                  var19 = false;
               }
               break label133;
            } catch (ExecutionException | RemoteException | TimeoutException | InterruptedException var20) {
               zzafr.zzc("Exception while processing an adapter; continuing with other adapters", var20);
               var19 = false;
            } finally {
               if (var19) {
                  long var14 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis() - var9;
                  Math.max(var5 - var14, 0L);
               }
            }

            var24 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis() - var9;
            var5 = Math.max(var5 - var24, 0L);
            continue;
         }

         var24 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis() - var9;
         var5 = Math.max(var5 - var24, 0L);
      }

      this.zza(var3);
      return var4 == null ? new zzuh(1) : var4;
   }

   private final void zza(zzajm var1) {
      zzagz.zzZr.post(new zzum(this, var1));
   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzMP = true;
         Iterator var2 = this.zzMQ.values().iterator();

         while(var2.hasNext()) {
            ((zzue)var2.next()).cancel();
         }

      }
   }

   public final List zzfg() {
      return this.zzMS;
   }

   // $FF: synthetic method
   static Object zza(zzuk var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static boolean zzb(zzuk var0) {
      return var0.zzMP;
   }

   // $FF: synthetic method
   static long zzc(zzuk var0) {
      return var0.mStartTime;
   }

   // $FF: synthetic method
   static long zzd(zzuk var0) {
      return var0.zzMN;
   }

   // $FF: synthetic method
   static Map zze(zzuk var0) {
      return var0.zzMQ;
   }
}
