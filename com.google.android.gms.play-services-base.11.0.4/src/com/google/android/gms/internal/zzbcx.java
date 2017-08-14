package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbcx implements zzbbj, zzbdp {
   private final Lock zzaCv;
   private final Condition zzaDS;
   private final Context mContext;
   private final zze zzaCF;
   private final zzbcz zzaDT;
   final Map zzaDF;
   final Map zzaDU = new HashMap();
   private zzq zzaCA;
   private Map zzaCD;
   private Api.zza zzaBe;
   private volatile zzbcw zzaDV;
   private ConnectionResult zzaDW = null;
   int zzaDX;
   final zzbcp zzaCl;
   final zzbdq zzaDY;

   public zzbcx(Context var1, zzbcp var2, Lock var3, Looper var4, zze var5, Map var6, zzq var7, Map var8, Api.zza var9, ArrayList var10, zzbdq var11) {
      this.mContext = var1;
      this.zzaCv = var3;
      this.zzaCF = var5;
      this.zzaDF = var6;
      this.zzaCA = var7;
      this.zzaCD = var8;
      this.zzaBe = var9;
      this.zzaCl = var2;
      this.zzaDY = var11;
      ArrayList var12;
      int var13 = (var12 = (ArrayList)var10).size();
      int var14 = 0;

      while(var14 < var13) {
         Object var10000 = var12.get(var14);
         ++var14;
         ((zzbbi)var10000).zza(this);
      }

      this.zzaDT = new zzbcz(this, var4);
      this.zzaDS = var3.newCondition();
      this.zzaDV = new zzbco(this);
   }

   public final zzbay zzd(@NonNull zzbay var1) {
      var1.zzpC();
      return this.zzaDV.zzd(var1);
   }

   public final zzbay zze(@NonNull zzbay var1) {
      var1.zzpC();
      return this.zzaDV.zze(var1);
   }

   public final void connect() {
      this.zzaDV.connect();
   }

   public final ConnectionResult blockingConnect() {
      this.connect();

      while(this.isConnecting()) {
         try {
            this.zzaDS.await();
         } catch (InterruptedException var1) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent)null);
         }
      }

      if (this.isConnected()) {
         return ConnectionResult.zzazX;
      } else {
         return this.zzaDW != null ? this.zzaDW : new ConnectionResult(13, (PendingIntent)null);
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

            var4 = this.zzaDS.awaitNanos(var4);
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, (PendingIntent)null);
         }
      }

      if (this.isConnected()) {
         return ConnectionResult.zzazX;
      } else if (this.zzaDW != null) {
         return this.zzaDW;
      } else {
         return new ConnectionResult(13, (PendingIntent)null);
      }
   }

   public final void disconnect() {
      if (this.zzaDV.disconnect()) {
         this.zzaDU.clear();
      }

   }

   @Nullable
   public final ConnectionResult getConnectionResult(@NonNull Api var1) {
      Api.zzc var2 = var1.zzpd();
      if (this.zzaDF.containsKey(var2)) {
         if (((Api.zze)this.zzaDF.get(var2)).isConnected()) {
            return ConnectionResult.zzazX;
         }

         if (this.zzaDU.containsKey(var2)) {
            return (ConnectionResult)this.zzaDU.get(var2);
         }
      }

      return null;
   }

   final void zzqh() {
      this.zzaCv.lock();

      try {
         this.zzaDV = new zzbcd(this, this.zzaCA, this.zzaCD, this.zzaCF, this.zzaBe, this.zzaCv, this.mContext);
         this.zzaDV.begin();
         this.zzaDS.signalAll();
      } finally {
         this.zzaCv.unlock();
      }

   }

   final void zzqi() {
      this.zzaCv.lock();

      try {
         this.zzaCl.zzqe();
         this.zzaDV = new zzbca(this);
         this.zzaDV.begin();
         this.zzaDS.signalAll();
      } finally {
         this.zzaCv.unlock();
      }

   }

   final void zzg(ConnectionResult var1) {
      this.zzaCv.lock();

      try {
         this.zzaDW = var1;
         this.zzaDV = new zzbco(this);
         this.zzaDV.begin();
         this.zzaDS.signalAll();
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final boolean isConnected() {
      return this.zzaDV instanceof zzbca;
   }

   public final boolean isConnecting() {
      return this.zzaDV instanceof zzbcd;
   }

   public final boolean zza(zzbei var1) {
      return false;
   }

   public final void zzpl() {
   }

   public final void zzpE() {
      if (this.isConnected()) {
         ((zzbca)this.zzaDV).zzpU();
      }

   }

   public final void zza(@NonNull ConnectionResult var1, @NonNull Api var2, boolean var3) {
      this.zzaCv.lock();

      try {
         this.zzaDV.zza(var1, var2, var3);
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void onConnected(@Nullable Bundle var1) {
      this.zzaCv.lock();

      try {
         this.zzaDV.onConnected(var1);
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void onConnectionSuspended(int var1) {
      this.zzaCv.lock();

      try {
         this.zzaDV.onConnectionSuspended(var1);
      } finally {
         this.zzaCv.unlock();
      }

   }

   final void zza(zzbcy var1) {
      Message var2 = this.zzaDT.obtainMessage(1, var1);
      this.zzaDT.sendMessage(var2);
   }

   final void zza(RuntimeException var1) {
      Message var2 = this.zzaDT.obtainMessage(2, var1);
      this.zzaDT.sendMessage(var2);
   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      String var5 = String.valueOf(var1).concat("  ");
      var3.append(var1).append("mState=").println(this.zzaDV);
      Iterator var6 = this.zzaCD.keySet().iterator();

      while(var6.hasNext()) {
         Api var7 = (Api)var6.next();
         var3.append(var1).append(var7.getName()).println(":");
         ((Api.zze)this.zzaDF.get(var7.zzpd())).dump(var5, var2, var3, var4);
      }

   }

   // $FF: synthetic method
   static Lock zza(zzbcx var0) {
      return var0.zzaCv;
   }

   // $FF: synthetic method
   static zzbcw zzb(zzbcx var0) {
      return var0.zzaDV;
   }
}
