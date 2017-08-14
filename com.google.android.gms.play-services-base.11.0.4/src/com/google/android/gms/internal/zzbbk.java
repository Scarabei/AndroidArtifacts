package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzbbk implements zzbdp {
   private final Context mContext;
   private final zzbcp zzaCl;
   private final Looper zzrM;
   private final zzbcx zzaCm;
   private final zzbcx zzaCn;
   private final Map zzaCo;
   private final Set zzaCp = Collections.newSetFromMap(new WeakHashMap());
   private final Api.zze zzaCq;
   private Bundle zzaCr;
   private ConnectionResult zzaCs = null;
   private ConnectionResult zzaCt = null;
   private boolean zzaCu = false;
   private final Lock zzaCv;
   private int zzaCw = 0;

   public static zzbbk zza(Context var0, zzbcp var1, Lock var2, Looper var3, zze var4, Map var5, zzq var6, Map var7, Api.zza var8, ArrayList var9) {
      Api.zze var10 = null;
      ArrayMap var11 = new ArrayMap();
      ArrayMap var12 = new ArrayMap();
      Iterator var13 = var5.entrySet().iterator();

      while(var13.hasNext()) {
         Entry var14;
         Api.zze var15;
         if ((var15 = (Api.zze)(var14 = (Entry)var13.next()).getValue()).zzmG()) {
            var10 = var15;
         }

         if (var15.zzmv()) {
            var11.put((Api.zzc)var14.getKey(), var15);
         } else {
            var12.put((Api.zzc)var14.getKey(), var15);
         }
      }

      zzbo.zza(!var11.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
      ArrayMap var22 = new ArrayMap();
      ArrayMap var23 = new ArrayMap();
      Iterator var24 = var7.keySet().iterator();

      while(var24.hasNext()) {
         Api var16;
         Api.zzc var17 = (var16 = (Api)var24.next()).zzpd();
         if (var11.containsKey(var17)) {
            var22.put(var16, (Boolean)var7.get(var16));
         } else {
            if (!var12.containsKey(var17)) {
               throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }

            var23.put(var16, (Boolean)var7.get(var16));
         }
      }

      ArrayList var25 = new ArrayList();
      ArrayList var26 = new ArrayList();
      ArrayList var19;
      int var20 = (var19 = (ArrayList)var9).size();
      int var21 = 0;

      while(var21 < var20) {
         Object var10000 = var19.get(var21);
         ++var21;
         zzbbi var18 = (zzbbi)var10000;
         if (var22.containsKey(var18.zzayW)) {
            var25.add(var18);
         } else {
            if (!var23.containsKey(var18.zzayW)) {
               throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }

            var26.add(var18);
         }
      }

      return new zzbbk(var0, var1, var2, var3, var4, var11, var12, var6, var8, var10, var25, var26, var22, var23);
   }

   private zzbbk(Context var1, zzbcp var2, Lock var3, Looper var4, zze var5, Map var6, Map var7, zzq var8, Api.zza var9, Api.zze var10, ArrayList var11, ArrayList var12, Map var13, Map var14) {
      this.mContext = var1;
      this.zzaCl = var2;
      this.zzaCv = var3;
      this.zzrM = var4;
      this.zzaCq = var10;
      this.zzaCm = new zzbcx(var1, this.zzaCl, var3, var4, var5, var7, (zzq)null, var14, (Api.zza)null, var12, new zzbbm(this, (zzbbl)null));
      this.zzaCn = new zzbcx(var1, this.zzaCl, var3, var4, var5, var6, var8, var13, var9, var11, new zzbbn(this, (zzbbl)null));
      ArrayMap var15 = new ArrayMap();
      Iterator var16 = var7.keySet().iterator();

      Api.zzc var17;
      while(var16.hasNext()) {
         var17 = (Api.zzc)var16.next();
         var15.put(var17, this.zzaCm);
      }

      var16 = var6.keySet().iterator();

      while(var16.hasNext()) {
         var17 = (Api.zzc)var16.next();
         var15.put(var17, this.zzaCn);
      }

      this.zzaCo = Collections.unmodifiableMap(var15);
   }

   public final zzbay zzd(@NonNull zzbay var1) {
      if (this.zzf(var1)) {
         if (this.zzpH()) {
            var1.zzr(new Status(4, (String)null, this.zzpI()));
            return var1;
         } else {
            return this.zzaCn.zzd(var1);
         }
      } else {
         return this.zzaCm.zzd(var1);
      }
   }

   public final zzbay zze(@NonNull zzbay var1) {
      if (this.zzf(var1)) {
         if (this.zzpH()) {
            var1.zzr(new Status(4, (String)null, this.zzpI()));
            return var1;
         } else {
            return this.zzaCn.zze(var1);
         }
      } else {
         return this.zzaCm.zze(var1);
      }
   }

   @Nullable
   public final ConnectionResult getConnectionResult(@NonNull Api var1) {
      if (((zzbcx)this.zzaCo.get(var1.zzpd())).equals(this.zzaCn)) {
         return this.zzpH() ? new ConnectionResult(4, this.zzpI()) : this.zzaCn.getConnectionResult(var1);
      } else {
         return this.zzaCm.getConnectionResult(var1);
      }
   }

   public final void connect() {
      this.zzaCw = 2;
      this.zzaCu = false;
      this.zzaCt = null;
      this.zzaCs = null;
      this.zzaCm.connect();
      this.zzaCn.connect();
   }

   public final ConnectionResult blockingConnect() {
      throw new UnsupportedOperationException();
   }

   public final ConnectionResult blockingConnect(long var1, @NonNull TimeUnit var3) {
      throw new UnsupportedOperationException();
   }

   public final void disconnect() {
      this.zzaCt = null;
      this.zzaCs = null;
      this.zzaCw = 0;
      this.zzaCm.disconnect();
      this.zzaCn.disconnect();
      this.zzpG();
   }

   public final boolean isConnected() {
      this.zzaCv.lock();

      boolean var1;
      try {
         var1 = this.zzaCm.isConnected() && (this.zzaCn.isConnected() || this.zzpH() || this.zzaCw == 1);
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   public final boolean isConnecting() {
      this.zzaCv.lock();

      boolean var1;
      try {
         var1 = this.zzaCw == 2;
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   public final boolean zza(zzbei var1) {
      this.zzaCv.lock();

      try {
         if (!this.isConnecting() && !this.isConnected() || this.zzaCn.isConnected()) {
            return false;
         }

         this.zzaCp.add(var1);
         if (this.zzaCw == 0) {
            this.zzaCw = 1;
         }

         this.zzaCt = null;
         this.zzaCn.connect();
      } finally {
         this.zzaCv.unlock();
      }

      return true;
   }

   public final void zzpE() {
      this.zzaCm.zzpE();
      this.zzaCn.zzpE();
   }

   public final void zzpl() {
      this.zzaCv.lock();

      try {
         boolean var1 = this.isConnecting();
         this.zzaCn.disconnect();
         this.zzaCt = new ConnectionResult(4);
         if (var1) {
            (new Handler(this.zzrM)).post(new zzbbl(this));
         } else {
            this.zzpG();
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   private final void zzpF() {
      if (zzb(this.zzaCs)) {
         if (zzb(this.zzaCt) || this.zzpH()) {
            switch(this.zzaCw) {
            case 2:
               this.zzaCl.zzm(this.zzaCr);
            case 1:
               this.zzpG();
               break;
            default:
               Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            }

            this.zzaCw = 0;
            return;
         }

         if (this.zzaCt != null) {
            if (this.zzaCw == 1) {
               this.zzpG();
               return;
            }

            this.zza(this.zzaCt);
            this.zzaCm.disconnect();
            return;
         }
      } else {
         if (this.zzaCs != null && zzb(this.zzaCt)) {
            this.zzaCn.disconnect();
            this.zza(this.zzaCs);
            return;
         }

         if (this.zzaCs != null && this.zzaCt != null) {
            ConnectionResult var1 = this.zzaCs;
            if (this.zzaCn.zzaDX < this.zzaCm.zzaDX) {
               var1 = this.zzaCt;
            }

            this.zza(var1);
         }
      }

   }

   private final void zza(ConnectionResult var1) {
      switch(this.zzaCw) {
      case 2:
         this.zzaCl.zzc(var1);
      case 1:
         this.zzpG();
         break;
      default:
         Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
      }

      this.zzaCw = 0;
   }

   private final void zzpG() {
      Iterator var1 = this.zzaCp.iterator();

      while(var1.hasNext()) {
         ((zzbei)var1.next()).zzmF();
      }

      this.zzaCp.clear();
   }

   private final void zzd(int var1, boolean var2) {
      this.zzaCl.zze(var1, var2);
      this.zzaCt = null;
      this.zzaCs = null;
   }

   private final boolean zzpH() {
      return this.zzaCt != null && this.zzaCt.getErrorCode() == 4;
   }

   private final boolean zzf(zzbay var1) {
      Api.zzc var2 = var1.zzpd();
      zzbo.zzb(this.zzaCo.containsKey(var2), "GoogleApiClient is not configured to use the API required for this call.");
      return ((zzbcx)this.zzaCo.get(var2)).equals(this.zzaCn);
   }

   @Nullable
   private final PendingIntent zzpI() {
      return this.zzaCq == null ? null : PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzaCl), this.zzaCq.zzmH(), 134217728);
   }

   private final void zzl(Bundle var1) {
      if (this.zzaCr == null) {
         this.zzaCr = var1;
      } else {
         if (var1 != null) {
            this.zzaCr.putAll(var1);
         }

      }
   }

   private static boolean zzb(ConnectionResult var0) {
      return var0 != null && var0.isSuccess();
   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      var3.append(var1).append("authClient").println(":");
      this.zzaCn.dump(String.valueOf(var1).concat("  "), var2, var3, var4);
      var3.append(var1).append("anonClient").println(":");
      this.zzaCm.dump(String.valueOf(var1).concat("  "), var2, var3, var4);
   }

   // $FF: synthetic method
   static Lock zza(zzbbk var0) {
      return var0.zzaCv;
   }

   // $FF: synthetic method
   static void zzb(zzbbk var0) {
      var0.zzpF();
   }

   // $FF: synthetic method
   static void zza(zzbbk var0, Bundle var1) {
      var0.zzl(var1);
   }

   // $FF: synthetic method
   static ConnectionResult zza(zzbbk var0, ConnectionResult var1) {
      return var0.zzaCs = var1;
   }

   // $FF: synthetic method
   static boolean zzc(zzbbk var0) {
      return var0.zzaCu;
   }

   // $FF: synthetic method
   static ConnectionResult zzd(zzbbk var0) {
      return var0.zzaCt;
   }

   // $FF: synthetic method
   static boolean zza(zzbbk var0, boolean var1) {
      return var0.zzaCu = var1;
   }

   // $FF: synthetic method
   static void zza(zzbbk var0, int var1, boolean var2) {
      var0.zzd(var1, var2);
   }

   // $FF: synthetic method
   static zzbcx zze(zzbbk var0) {
      return var0.zzaCn;
   }

   // $FF: synthetic method
   static ConnectionResult zzb(zzbbk var0, ConnectionResult var1) {
      return var0.zzaCt = var1;
   }

   // $FF: synthetic method
   static zzbcx zzf(zzbbk var0) {
      return var0.zzaCm;
   }
}
