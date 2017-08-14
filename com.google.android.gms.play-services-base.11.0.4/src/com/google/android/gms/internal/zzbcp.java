package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzbcp extends GoogleApiClient implements zzbdq {
   private final Lock zzaCv;
   private boolean zzaBh;
   private final zzac zzaDy;
   private zzbdp zzaDz = null;
   private final int zzaBb;
   private final Context mContext;
   private final Looper zzrM;
   final Queue zzaCJ = new LinkedList();
   private volatile boolean zzaDA;
   private long zzaDB = 120000L;
   private long zzaDC = 5000L;
   private final zzbcu zzaDD;
   private final GoogleApiAvailability zzaBd;
   private zzbdk zzaDE;
   final Map zzaDF;
   Set zzaDG = new HashSet();
   private zzq zzaCA;
   private Map zzaCD;
   private Api.zza zzaBe;
   private final zzbea zzaDH = new zzbea();
   private final ArrayList zzaDI;
   private Integer zzaDJ = null;
   Set zzaDK = null;
   final zzbev zzaDL;
   private final zzad zzaDM = new zzbcq(this);

   public zzbcp(Context var1, Lock var2, Looper var3, zzq var4, GoogleApiAvailability var5, Api.zza var6, Map var7, List var8, List var9, Map var10, int var11, int var12, ArrayList var13, boolean var14) {
      this.mContext = var1;
      this.zzaCv = var2;
      this.zzaBh = false;
      this.zzaDy = new zzac(var3, this.zzaDM);
      this.zzrM = var3;
      this.zzaDD = new zzbcu(this, var3);
      this.zzaBd = var5;
      this.zzaBb = var11;
      if (this.zzaBb >= 0) {
         this.zzaDJ = var12;
      }

      this.zzaCD = var7;
      this.zzaDF = var10;
      this.zzaDI = var13;
      this.zzaDL = new zzbev(this.zzaDF);
      Iterator var15 = var8.iterator();

      while(var15.hasNext()) {
         GoogleApiClient.ConnectionCallbacks var16 = (GoogleApiClient.ConnectionCallbacks)var15.next();
         this.zzaDy.registerConnectionCallbacks(var16);
      }

      var15 = var9.iterator();

      while(var15.hasNext()) {
         GoogleApiClient.OnConnectionFailedListener var17 = (GoogleApiClient.OnConnectionFailedListener)var15.next();
         this.zzaDy.registerConnectionFailedListener(var17);
      }

      this.zzaCA = var4;
      this.zzaBe = var6;
   }

   public final zzbay zzd(@NonNull zzbay var1) {
      zzbo.zzb(var1.zzpd() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
      boolean var10000 = this.zzaDF.containsKey(var1.zzpd());
      String var2 = var1.zzpg() != null ? var1.zzpg().getName() : "the API";
      zzbo.zzb(var10000, (new StringBuilder(65 + String.valueOf(var2).length())).append("GoogleApiClient is not configured to use ").append(var2).append(" required for this call.").toString());
      this.zzaCv.lock();

      zzbay var6;
      try {
         if (this.zzaDz != null) {
            var6 = this.zzaDz.zzd(var1);
            return var6;
         }

         this.zzaCJ.add(var1);
         var6 = var1;
      } finally {
         this.zzaCv.unlock();
      }

      return var6;
   }

   public final zzbay zze(@NonNull zzbay var1) {
      zzbo.zzb(var1.zzpd() != null, "This task can not be executed (it's probably a Batch or malformed)");
      boolean var10000 = this.zzaDF.containsKey(var1.zzpd());
      String var2 = var1.zzpg() != null ? var1.zzpg().getName() : "the API";
      zzbo.zzb(var10000, (new StringBuilder(65 + String.valueOf(var2).length())).append("GoogleApiClient is not configured to use ").append(var2).append(" required for this call.").toString());
      this.zzaCv.lock();

      zzbay var6;
      try {
         if (this.zzaDz == null) {
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
         }

         if (this.zzaDA) {
            this.zzaCJ.add(var1);

            while(!this.zzaCJ.isEmpty()) {
               var6 = (zzbay)this.zzaCJ.remove();
               this.zzaDL.zzb(var6);
               var6.zzr(Status.zzaBo);
            }

            var6 = var1;
            return var6;
         }

         var6 = this.zzaDz.zze(var1);
      } finally {
         this.zzaCv.unlock();
      }

      return var6;
   }

   public final zzbdw zzp(@NonNull Object var1) {
      this.zzaCv.lock();

      zzbdw var2;
      try {
         Looper var5 = this.zzrM;
         var2 = this.zzaDH.zza(var1, var5, "NO_TYPE");
      } finally {
         this.zzaCv.unlock();
      }

      return var2;
   }

   @NonNull
   public final Api.zze zza(@NonNull Api.zzc var1) {
      Api.zze var2;
      zzbo.zzb(var2 = (Api.zze)this.zzaDF.get(var1), "Appropriate Api was not requested.");
      return var2;
   }

   public final boolean zza(@NonNull Api var1) {
      return this.zzaDF.containsKey(var1.zzpd());
   }

   public final boolean hasConnectedApi(@NonNull Api var1) {
      if (!this.isConnected()) {
         return false;
      } else {
         Api.zze var2;
         return (var2 = (Api.zze)this.zzaDF.get(var1.zzpd())) != null && var2.isConnected();
      }
   }

   @NonNull
   public final ConnectionResult getConnectionResult(@NonNull Api var1) {
      this.zzaCv.lock();
      boolean var6 = false;

      ConnectionResult var3;
      label81: {
         label80: {
            ConnectionResult var2;
            try {
               var6 = true;
               if (!this.isConnected() && !this.zzaDA) {
                  throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
               }

               if (!this.zzaDF.containsKey(var1.zzpd())) {
                  throw new IllegalArgumentException(String.valueOf(var1.getName()).concat(" was never registered with GoogleApiClient"));
               }

               if ((var2 = this.zzaDz.getConnectionResult(var1)) == null) {
                  if (this.zzaDA) {
                     var3 = ConnectionResult.zzazX;
                     var6 = false;
                     break label81;
                  }

                  Log.w("GoogleApiClientImpl", this.zzqg());
                  Log.wtf("GoogleApiClientImpl", String.valueOf(var1.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                  var3 = new ConnectionResult(8, (PendingIntent)null);
                  var6 = false;
                  break label80;
               }

               var6 = false;
            } finally {
               if (var6) {
                  this.zzaCv.unlock();
               }
            }

            this.zzaCv.unlock();
            return var2;
         }

         this.zzaCv.unlock();
         return var3;
      }

      this.zzaCv.unlock();
      return var3;
   }

   public final void connect() {
      this.zzaCv.lock();

      try {
         if (this.zzaBb >= 0) {
            zzbo.zza(this.zzaDJ != null, "Sign-in mode should have been set explicitly by auto-manage.");
         } else if (this.zzaDJ == null) {
            this.zzaDJ = zza(this.zzaDF.values(), false);
         } else if (this.zzaDJ.intValue() == 2) {
            throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
         }

         this.connect(this.zzaDJ.intValue());
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void connect(int var1) {
      this.zzaCv.lock();

      try {
         zzbo.zzb(var1 == 3 || var1 == 1 || var1 == 2, (new StringBuilder(33)).append("Illegal sign-in mode: ").append(var1).toString());
         this.zzap(var1);
         this.zzqc();
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final ConnectionResult blockingConnect() {
      zzbo.zza(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
      this.zzaCv.lock();

      ConnectionResult var1;
      try {
         if (this.zzaBb >= 0) {
            zzbo.zza(this.zzaDJ != null, "Sign-in mode should have been set explicitly by auto-manage.");
         } else if (this.zzaDJ == null) {
            this.zzaDJ = zza(this.zzaDF.values(), false);
         } else if (this.zzaDJ.intValue() == 2) {
            throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
         }

         this.zzap(this.zzaDJ.intValue());
         this.zzaDy.zzrA();
         var1 = this.zzaDz.blockingConnect();
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   public final ConnectionResult blockingConnect(long var1, @NonNull TimeUnit var3) {
      zzbo.zza(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
      zzbo.zzb(var3, "TimeUnit must not be null");
      this.zzaCv.lock();

      ConnectionResult var4;
      try {
         if (this.zzaDJ == null) {
            this.zzaDJ = zza(this.zzaDF.values(), false);
         } else if (this.zzaDJ.intValue() == 2) {
            throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
         }

         this.zzap(this.zzaDJ.intValue());
         this.zzaDy.zzrA();
         var4 = this.zzaDz.blockingConnect(var1, var3);
      } finally {
         this.zzaCv.unlock();
      }

      return var4;
   }

   public final void disconnect() {
      this.zzaCv.lock();

      try {
         this.zzaDL.release();
         if (this.zzaDz != null) {
            this.zzaDz.disconnect();
         }

         this.zzaDH.release();
         Iterator var1 = this.zzaCJ.iterator();

         while(var1.hasNext()) {
            zzbay var2;
            (var2 = (zzbay)var1.next()).zza((zzbex)null);
            var2.cancel();
         }

         this.zzaCJ.clear();
         if (this.zzaDz != null) {
            this.zzqe();
            this.zzaDy.zzrz();
            return;
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void reconnect() {
      this.disconnect();
      this.connect();
   }

   public final PendingResult clearDefaultAccountAndReconnect() {
      zzbo.zza(this.isConnected(), "GoogleApiClient is not connected yet.");
      zzbo.zza(this.zzaDJ.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
      zzben var1 = new zzben(this);
      if (this.zzaDF.containsKey(zzbfo.zzajR)) {
         this.zza(this, var1, false);
      } else {
         AtomicReference var2 = new AtomicReference();
         zzbcr var3 = new zzbcr(this, var2, var1);
         zzbcs var4 = new zzbcs(this, var1);
         GoogleApiClient var5 = (new GoogleApiClient.Builder(this.mContext)).addApi(zzbfo.API).addConnectionCallbacks(var3).addOnConnectionFailedListener(var4).setHandler(this.zzaDD).build();
         var2.set(var5);
         var5.connect();
      }

      return var1;
   }

   private final void zza(GoogleApiClient var1, zzben var2, boolean var3) {
      zzbfo.zzaIy.zzd(var1).setResultCallback(new zzbct(this, var2, var3, var1));
   }

   public final void stopAutoManage(@NonNull FragmentActivity var1) {
      zzbdr var3 = new zzbdr(var1);
      if (this.zzaBb >= 0) {
         zzbau.zza(var3).zzal(this.zzaBb);
      } else {
         throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
      }
   }

   public final boolean isConnected() {
      return this.zzaDz != null && this.zzaDz.isConnected();
   }

   public final boolean isConnecting() {
      return this.zzaDz != null && this.zzaDz.isConnecting();
   }

   private final void zzap(int var1) {
      if (this.zzaDJ == null) {
         this.zzaDJ = var1;
      } else if (this.zzaDJ.intValue() != var1) {
         String var6 = String.valueOf(zzaq(var1));
         String var7 = String.valueOf(zzaq(this.zzaDJ.intValue()));
         throw new IllegalStateException((new StringBuilder(51 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("Cannot use sign-in mode: ").append(var6).append(". Mode was already set to ").append(var7).toString());
      }

      if (this.zzaDz == null) {
         boolean var2 = false;
         boolean var3 = false;
         Iterator var4 = this.zzaDF.values().iterator();

         while(var4.hasNext()) {
            Api.zze var5;
            if ((var5 = (Api.zze)var4.next()).zzmv()) {
               var2 = true;
            }

            if (var5.zzmG()) {
               var3 = true;
            }
         }

         switch(this.zzaDJ.intValue()) {
         case 1:
            if (!var2) {
               throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }

            if (var3) {
               throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            break;
         case 2:
            if (var2) {
               if (this.zzaBh) {
                  this.zzaDz = new zzbbp(this.mContext, this.zzaCv, this.zzrM, this.zzaBd, this.zzaDF, this.zzaCA, this.zzaCD, this.zzaBe, this.zzaDI, this, true);
                  return;
               }

               this.zzaDz = zzbbk.zza(this.mContext, this, this.zzaCv, this.zzrM, this.zzaBd, this.zzaDF, this.zzaCA, this.zzaCD, this.zzaBe, this.zzaDI);
               return;
            }
         case 3:
         }

         if (this.zzaBh && !var3) {
            this.zzaDz = new zzbbp(this.mContext, this.zzaCv, this.zzrM, this.zzaBd, this.zzaDF, this.zzaCA, this.zzaCD, this.zzaBe, this.zzaDI, this, false);
         } else {
            this.zzaDz = new zzbcx(this.mContext, this, this.zzaCv, this.zzrM, this.zzaBd, this.zzaDF, this.zzaCA, this.zzaCD, this.zzaBe, this.zzaDI, this);
         }
      }
   }

   private final void zzqc() {
      this.zzaDy.zzrA();
      this.zzaDz.connect();
   }

   private final void resume() {
      this.zzaCv.lock();

      try {
         if (this.zzaDA) {
            this.zzqc();
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   private final void zzqd() {
      this.zzaCv.lock();

      try {
         if (this.zzqe()) {
            this.zzqc();
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   final boolean zzqe() {
      if (!this.zzaDA) {
         return false;
      } else {
         this.zzaDA = false;
         this.zzaDD.removeMessages(2);
         this.zzaDD.removeMessages(1);
         if (this.zzaDE != null) {
            this.zzaDE.unregister();
            this.zzaDE = null;
         }

         return true;
      }
   }

   public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      this.zzaDy.registerConnectionCallbacks(var1);
   }

   public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      return this.zzaDy.isConnectionCallbacksRegistered(var1);
   }

   public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      this.zzaDy.unregisterConnectionCallbacks(var1);
   }

   public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      this.zzaDy.registerConnectionFailedListener(var1);
   }

   public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      return this.zzaDy.isConnectionFailedListenerRegistered(var1);
   }

   public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      this.zzaDy.unregisterConnectionFailedListener(var1);
   }

   public final void zzm(Bundle var1) {
      while(!this.zzaCJ.isEmpty()) {
         this.zze((zzbay)this.zzaCJ.remove());
      }

      this.zzaDy.zzn(var1);
   }

   public final void zzc(ConnectionResult var1) {
      if (!zze.zze(this.mContext, var1.getErrorCode())) {
         this.zzqe();
      }

      if (!this.zzaDA) {
         this.zzaDy.zzk(var1);
         this.zzaDy.zzrz();
      }

   }

   public final void zze(int var1, boolean var2) {
      if (var1 == 1 && !var2 && !this.zzaDA) {
         this.zzaDA = true;
         if (this.zzaDE == null) {
            this.zzaDE = GoogleApiAvailability.zza((Context)this.mContext.getApplicationContext(), (zzbdl)(new zzbcv(this)));
         }

         this.zzaDD.sendMessageDelayed(this.zzaDD.obtainMessage(1), this.zzaDB);
         this.zzaDD.sendMessageDelayed(this.zzaDD.obtainMessage(2), this.zzaDC);
      }

      this.zzaDL.zzqM();
      this.zzaDy.zzaA(var1);
      this.zzaDy.zzrz();
      if (var1 == 2) {
         this.zzqc();
      }

   }

   public final Context getContext() {
      return this.mContext;
   }

   public final Looper getLooper() {
      return this.zzrM;
   }

   public final boolean zza(zzbei var1) {
      return this.zzaDz != null && this.zzaDz.zza(var1);
   }

   public final void zzpl() {
      if (this.zzaDz != null) {
         this.zzaDz.zzpl();
      }

   }

   public final void zza(zzbes var1) {
      this.zzaCv.lock();

      try {
         if (this.zzaDK == null) {
            this.zzaDK = new HashSet();
         }

         this.zzaDK.add(var1);
      } finally {
         this.zzaCv.unlock();
      }

   }

   public final void zzb(zzbes var1) {
      this.zzaCv.lock();

      try {
         if (this.zzaDK == null) {
            Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
         } else if (!this.zzaDK.remove(var1)) {
            Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
         } else if (!this.zzqf()) {
            this.zzaDz.zzpE();
         }
      } finally {
         this.zzaCv.unlock();
      }

   }

   final boolean zzqf() {
      this.zzaCv.lock();

      boolean var1;
      try {
         if (this.zzaDK == null) {
            return false;
         }

         var1 = !this.zzaDK.isEmpty();
      } finally {
         this.zzaCv.unlock();
      }

      return var1;
   }

   final String zzqg() {
      StringWriter var1 = new StringWriter();
      this.dump("", (FileDescriptor)null, new PrintWriter(var1), (String[])null);
      return var1.toString();
   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      var3.append(var1).append("mContext=").println(this.mContext);
      var3.append(var1).append("mResuming=").print(this.zzaDA);
      var3.append(" mWorkQueue.size()=").print(this.zzaCJ.size());
      zzbev var5 = this.zzaDL;
      var3.append(" mUnconsumedApiCalls.size()=").println(var5.zzaFl.size());
      if (this.zzaDz != null) {
         this.zzaDz.dump(var1, var2, var3, var4);
      }

   }

   public static int zza(Iterable var0, boolean var1) {
      boolean var2 = false;
      boolean var3 = false;
      Iterator var4 = var0.iterator();

      while(var4.hasNext()) {
         Api.zze var5;
         if ((var5 = (Api.zze)var4.next()).zzmv()) {
            var2 = true;
         }

         if (var5.zzmG()) {
            var3 = true;
         }
      }

      if (var2) {
         if (var3 && var1) {
            return 2;
         } else {
            return 1;
         }
      } else {
         return 3;
      }
   }

   private static String zzaq(int var0) {
      switch(var0) {
      case 1:
         return "SIGN_IN_MODE_REQUIRED";
      case 2:
         return "SIGN_IN_MODE_OPTIONAL";
      case 3:
         return "SIGN_IN_MODE_NONE";
      default:
         return "UNKNOWN";
      }
   }

   // $FF: synthetic method
   static void zza(zzbcp var0) {
      var0.resume();
   }

   // $FF: synthetic method
   static void zzb(zzbcp var0) {
      var0.zzqd();
   }

   // $FF: synthetic method
   static void zza(zzbcp var0, GoogleApiClient var1, zzben var2, boolean var3) {
      var0.zza(var1, var2, true);
   }

   // $FF: synthetic method
   static Context zzc(zzbcp var0) {
      return var0.mContext;
   }
}
