package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzr;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbbp implements zzbdp {
   private final Map zzaCB = new HashMap();
   private final Map zzaCC = new HashMap();
   private final Map zzaCD;
   private final zzbdb zzaAN;
   private final zzbcp zzaCE;
   private final Lock zzaCv;
   private final Looper zzrM;
   private final zze zzaCF;
   private final Condition zzaCG;
   private final zzq zzaCA;
   private final boolean zzaCH;
   private final boolean zzaCI;
   private final Queue zzaCJ = new LinkedList();
   private boolean zzaCK;
   private Map zzaCL;
   private Map zzaCM;
   private zzbbs zzaCN;
   private ConnectionResult zzaCO;

   public zzbbp(Context var1, Lock var2, Looper var3, zze var4, Map var5, zzq var6, Map var7, Api.zza var8, ArrayList var9, zzbcp var10, boolean var11) {
      this.zzaCv = var2;
      this.zzrM = var3;
      this.zzaCG = var2.newCondition();
      this.zzaCF = var4;
      this.zzaCE = var10;
      this.zzaCD = var7;
      this.zzaCA = var6;
      this.zzaCH = var11;
      HashMap var12 = new HashMap();
      Iterator var13 = var7.keySet().iterator();

      while(var13.hasNext()) {
         Api var14 = (Api)var13.next();
         var12.put(var14.zzpd(), var14);
      }

      HashMap var26 = new HashMap();
      ArrayList var23;
      int var24 = (var23 = (ArrayList)var9).size();
      int var25 = 0;

      while(var25 < var24) {
         Object var10000 = var23.get(var25);
         ++var25;
         zzbbi var15 = (zzbbi)var10000;
         var26.put(var15.zzayW, var15);
      }

      boolean var27 = false;
      boolean var28 = true;
      boolean var16 = false;
      Iterator var17 = var5.entrySet().iterator();

      while(var17.hasNext()) {
         Entry var18 = (Entry)var17.next();
         Api var19 = (Api)var12.get(var18.getKey());
         Api.zze var20;
         if ((var20 = (Api.zze)var18.getValue()).zzpe()) {
            var16 = true;
            if (!((Boolean)this.zzaCD.get(var19)).booleanValue()) {
               var27 = true;
            }
         } else {
            var28 = false;
         }

         zzbbi var21 = (zzbbi)var26.get(var19);
         zzbbo var22 = new zzbbo(var1, var19, var3, var20, var21, var6, var8);
         this.zzaCB.put((Api.zzc)var18.getKey(), var22);
         if (var20.zzmv()) {
            this.zzaCC.put((Api.zzc)var18.getKey(), var22);
         }
      }

      this.zzaCI = var16 && !var28 && !var27;
      this.zzaAN = zzbdb.zzqk();
   }

   public final zzbay zzd(@NonNull zzbay var1) {
      if (this.zzaCH && this.zzg(var1)) {
         return var1;
      } else if (!this.isConnected()) {
         this.zzaCJ.add(var1);
         return var1;
      } else {
         this.zzaCE.zzaDL.zzb(var1);
         return ((zzbbo)this.zzaCB.get(var1.zzpd())).zza(var1);
      }
   }

   public final zzbay zze(@NonNull zzbay var1) {
      Api.zzc var2 = var1.zzpd();
      if (this.zzaCH && this.zzg(var1)) {
         return var1;
      } else {
         this.zzaCE.zzaDL.zzb(var1);
         return ((zzbbo)this.zzaCB.get(var2)).zzb(var1);
      }
   }

   private final boolean zzg(@NonNull zzbay var1) {
      Api.zzc var2 = var1.zzpd();
      ConnectionResult var3;
      if ((var3 = this.zzb(var2)) != null && var3.getErrorCode() == 4) {
         var1.zzr(new Status(4, (String)null, this.zzaAN.zza(((zzbbo)this.zzaCB.get(var2)).zzph(), System.identityHashCode(this.zzaCE))));
         return true;
      } else {
         return false;
      }
   }

   public final void connect() {
      this.zzaCv.lock();

      try {
         if (this.zzaCK) {
            return;
         }

         this.zzaCK = true;
         this.zzaCL = null;
         this.zzaCM = null;
         this.zzaCN = null;
         this.zzaCO = null;
         this.zzaAN.zzps();
         this.zzaAN.zza((Iterable)this.zzaCB.values()).addOnCompleteListener(new zzbgv(this.zzrM), new zzbbr(this, (zzbbq)null));
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final ConnectionResult blockingConnect() {
      this.connect();

      while(this.isConnecting()) {
         try {
            this.zzaCG.await();
         } catch (InterruptedException var1) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent)null);
         }
      }

      if (this.isConnected()) {
         return ConnectionResult.zzazX;
      } else {
         return this.zzaCO != null ? this.zzaCO : new ConnectionResult(13, (PendingIntent)null);
      }
   }

   public final ConnectionResult blockingConnect(long var1, TimeUnit var3) {
      this.connect();
      long var4 = var3.toNanos(var1);

      while(this.isConnecting()) {
         try {
            if (var4 <= 0L) {
               this.disconnect();
               return new ConnectionResult(14, (PendingIntent)null);
            }

            var4 = this.zzaCG.awaitNanos(var4);
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent)null);
         }
      }

      if (this.isConnected()) {
         return ConnectionResult.zzazX;
      } else if (this.zzaCO != null) {
         return this.zzaCO;
      } else {
         return new ConnectionResult(13, (PendingIntent)null);
      }
   }

   public final void disconnect() {
      this.zzaCv.lock();

      try {
         this.zzaCK = false;
         this.zzaCL = null;
         this.zzaCM = null;
         if (this.zzaCN != null) {
            this.zzaCN.cancel();
            this.zzaCN = null;
         }

         this.zzaCO = null;

         while(!this.zzaCJ.isEmpty()) {
            zzbay var1;
            (var1 = (zzbay)this.zzaCJ.remove()).zza((zzbex)null);
            var1.cancel();
         }

         this.zzaCG.signalAll();
      } finally {
         this.zzaCv.unlock();
      }
   }

   @Nullable
   public final ConnectionResult getConnectionResult(@NonNull Api var1) {
      return this.zzb(var1.zzpd());
   }

   @Nullable
   private final ConnectionResult zzb(@NonNull Api.zzc var1) {
      this.zzaCv.lock();

      ConnectionResult var3;
      try {
         zzbbo var2 = (zzbbo)this.zzaCB.get(var1);
         if (this.zzaCL == null || var2 == null) {
            return null;
         }

         var3 = (ConnectionResult)this.zzaCL.get(var2.zzph());
      } finally {
         this.zzaCv.unlock();
      }

      return var3;
   }

   public final boolean isConnected() {
      this.zzaCv.lock();

      boolean var1;
      try {
         var1 = this.zzaCL != null && this.zzaCO == null;
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   public final boolean isConnecting() {
      this.zzaCv.lock();

      boolean var1;
      try {
         var1 = this.zzaCL == null && this.zzaCK;
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   private final boolean zzpK() {
      // $FF: Couldn't be decompiled
   }

   public final boolean zza(zzbei var1) {
      this.zzaCv.lock();

      try {
         if (!this.zzaCK || this.zzpK()) {
            return false;
         }

         this.zzaAN.zzps();
         this.zzaCN = new zzbbs(this, var1);
         this.zzaAN.zza((Iterable)this.zzaCC.values()).addOnCompleteListener(new zzbgv(this.zzrM), this.zzaCN);
      } finally {
         this.zzaCv.unlock();
      }

      return true;
   }

   public final void zzpl() {
      this.zzaCv.lock();

      try {
         this.zzaAN.zzpl();
         if (this.zzaCN != null) {
            this.zzaCN.cancel();
            this.zzaCN = null;
         }

         if (this.zzaCM == null) {
            this.zzaCM = new ArrayMap(this.zzaCC.size());
         }

         ConnectionResult var1 = new ConnectionResult(4);
         Iterator var2 = this.zzaCC.values().iterator();

         while(true) {
            if (!var2.hasNext()) {
               if (this.zzaCL != null) {
                  this.zzaCL.putAll(this.zzaCM);
               }
               break;
            }

            zzbbo var3 = (zzbbo)var2.next();
            this.zzaCM.put(var3.zzph(), var1);
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
   }

   public final void zzpE() {
   }

   private final void zzpL() {
      if (this.zzaCA == null) {
         this.zzaCE.zzaDG = Collections.emptySet();
      } else {
         HashSet var1 = new HashSet(this.zzaCA.zzrn());
         Map var2;
         Iterator var3 = (var2 = this.zzaCA.zzrp()).keySet().iterator();

         while(var3.hasNext()) {
            Api var4 = (Api)var3.next();
            ConnectionResult var5;
            if ((var5 = this.getConnectionResult(var4)) != null && var5.isSuccess()) {
               var1.addAll(((zzr)var2.get(var4)).zzame);
            }
         }

         this.zzaCE.zzaDG = var1;
      }
   }

   private final void zzpM() {
      while(!this.zzaCJ.isEmpty()) {
         this.zze((zzbay)this.zzaCJ.remove());
      }

      this.zzaCE.zzm((Bundle)null);
   }

   private final boolean zza(zzbbo var1, ConnectionResult var2) {
      return !var2.isSuccess() && !var2.hasResolution() && ((Boolean)this.zzaCD.get(var1.zzpg())).booleanValue() && var1.zzpJ().zzpe() && this.zzaCF.isUserResolvableError(var2.getErrorCode());
   }

   @Nullable
   private final ConnectionResult zzpN() {
      ConnectionResult var1 = null;
      int var2 = 0;
      ConnectionResult var3 = null;
      int var4 = 0;
      Iterator var5 = this.zzaCB.values().iterator();

      while(true) {
         ConnectionResult var9;
         int var10;
         do {
            while(true) {
               Api var7;
               zzbat var8;
               do {
                  do {
                     if (!var5.hasNext()) {
                        if (var1 != null && var3 != null && var2 > var4) {
                           return var3;
                        }

                        return var1;
                     }

                     zzbbo var6;
                     var7 = (var6 = (zzbbo)var5.next()).zzpg();
                     var8 = var6.zzph();
                  } while((var9 = (ConnectionResult)this.zzaCL.get(var8)).isSuccess());
               } while(((Boolean)this.zzaCD.get(var7)).booleanValue() && !var9.hasResolution() && !this.zzaCF.isUserResolvableError(var9.getErrorCode()));

               if (var9.getErrorCode() == 4 && this.zzaCH) {
                  var10 = var7.zzpb().getPriority();
                  break;
               }

               var10 = var7.zzpb().getPriority();
               if (var1 == null || var2 > var10) {
                  var1 = var9;
                  var2 = var10;
               }
            }
         } while(var3 != null && var4 <= var10);

         var3 = var9;
         var4 = var10;
      }
   }

   // $FF: synthetic method
   static Lock zza(zzbbp var0) {
      return var0.zzaCv;
   }

   // $FF: synthetic method
   static boolean zzb(zzbbp var0) {
      return var0.zzaCK;
   }

   // $FF: synthetic method
   static Map zza(zzbbp var0, Map var1) {
      return var0.zzaCL = var1;
   }

   // $FF: synthetic method
   static Map zzc(zzbbp var0) {
      return var0.zzaCB;
   }

   // $FF: synthetic method
   static Map zzd(zzbbp var0) {
      return var0.zzaCL;
   }

   // $FF: synthetic method
   static boolean zze(zzbbp var0) {
      return var0.zzaCI;
   }

   // $FF: synthetic method
   static boolean zza(zzbbp var0, zzbbo var1, ConnectionResult var2) {
      return var0.zza(var1, var2);
   }

   // $FF: synthetic method
   static ConnectionResult zza(zzbbp var0, ConnectionResult var1) {
      return var0.zzaCO = var1;
   }

   // $FF: synthetic method
   static ConnectionResult zzf(zzbbp var0) {
      return var0.zzpN();
   }

   // $FF: synthetic method
   static Map zzg(zzbbp var0) {
      return var0.zzaCM;
   }

   // $FF: synthetic method
   static ConnectionResult zzh(zzbbp var0) {
      return var0.zzaCO;
   }

   // $FF: synthetic method
   static void zzi(zzbbp var0) {
      var0.zzpL();
   }

   // $FF: synthetic method
   static void zzj(zzbbp var0) {
      var0.zzpM();
   }

   // $FF: synthetic method
   static boolean zza(zzbbp var0, boolean var1) {
      return var0.zzaCK = false;
   }

   // $FF: synthetic method
   static zzbcp zzk(zzbbp var0) {
      return var0.zzaCE;
   }

   // $FF: synthetic method
   static Condition zzl(zzbbp var0) {
      return var0.zzaCG;
   }

   // $FF: synthetic method
   static Map zzb(zzbbp var0, Map var1) {
      return var0.zzaCM = var1;
   }

   // $FF: synthetic method
   static Map zzm(zzbbp var0) {
      return var0.zzaCC;
   }
}
