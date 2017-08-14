package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzac implements Callback {
   private final zzad zzaHE;
   private final ArrayList zzaHF = new ArrayList();
   private ArrayList zzaHG = new ArrayList();
   private final ArrayList zzaHH = new ArrayList();
   private volatile boolean zzaHI = false;
   private final AtomicInteger zzaHJ = new AtomicInteger(0);
   private boolean zzaHK = false;
   private final Handler mHandler;
   private final Object mLock = new Object();

   public zzac(Looper var1, zzad var2) {
      this.zzaHE = var2;
      this.mHandler = new Handler(var1, this);
   }

   public final void zzrz() {
      this.zzaHI = false;
      this.zzaHJ.incrementAndGet();
   }

   public final void zzrA() {
      this.zzaHI = true;
   }

   public final void zzn(Bundle var1) {
      zzbo.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         zzbo.zzae(!this.zzaHK);
         this.mHandler.removeMessages(1);
         this.zzaHK = true;
         zzbo.zzae(this.zzaHG.size() == 0);
         ArrayList var3 = new ArrayList(this.zzaHF);
         int var4 = this.zzaHJ.get();
         ArrayList var7;
         int var8 = (var7 = (ArrayList)var3).size();
         int var9 = 0;

         while(var9 < var8) {
            Object var10000 = var7.get(var9);
            ++var9;
            GoogleApiClient.ConnectionCallbacks var5 = (GoogleApiClient.ConnectionCallbacks)var10000;
            if (!this.zzaHI || !this.zzaHE.isConnected() || this.zzaHJ.get() != var4) {
               break;
            }

            if (!this.zzaHG.contains(var5)) {
               var5.onConnected(var1);
            }
         }

         this.zzaHG.clear();
         this.zzaHK = false;
      }
   }

   public final void zzaA(int var1) {
      zzbo.zza(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
      this.mHandler.removeMessages(1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzaHK = true;
         ArrayList var3 = new ArrayList(this.zzaHF);
         int var4 = this.zzaHJ.get();
         ArrayList var7;
         int var8 = (var7 = (ArrayList)var3).size();
         int var9 = 0;

         while(var9 < var8) {
            Object var10000 = var7.get(var9);
            ++var9;
            GoogleApiClient.ConnectionCallbacks var5 = (GoogleApiClient.ConnectionCallbacks)var10000;
            if (!this.zzaHI || this.zzaHJ.get() != var4) {
               break;
            }

            if (this.zzaHF.contains(var5)) {
               var5.onConnectionSuspended(var1);
            }
         }

         this.zzaHG.clear();
         this.zzaHK = false;
      }
   }

   public final void zzk(ConnectionResult var1) {
      zzbo.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
      this.mHandler.removeMessages(1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         ArrayList var3 = new ArrayList(this.zzaHH);
         int var4 = this.zzaHJ.get();
         ArrayList var7;
         int var8 = (var7 = (ArrayList)var3).size();
         int var9 = 0;

         while(var9 < var8) {
            Object var10000 = var7.get(var9);
            ++var9;
            GoogleApiClient.OnConnectionFailedListener var5 = (GoogleApiClient.OnConnectionFailedListener)var10000;
            if (!this.zzaHI || this.zzaHJ.get() != var4) {
               return;
            }

            if (this.zzaHH.contains(var5)) {
               var5.onConnectionFailed(var1);
            }
         }

      }
   }

   public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzaHF.contains(var1)) {
            String var3 = String.valueOf(var1);
            Log.w("GmsClientEvents", (new StringBuilder(62 + String.valueOf(var3).length())).append("registerConnectionCallbacks(): listener ").append(var3).append(" is already registered").toString());
         } else {
            this.zzaHF.add(var1);
         }
      }

      if (this.zzaHE.isConnected()) {
         this.mHandler.sendMessage(this.mHandler.obtainMessage(1, var1));
      }

   }

   public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaHF.contains(var1);
      }
   }

   public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzaHF.remove(var1)) {
            String var3 = String.valueOf(var1);
            Log.w("GmsClientEvents", (new StringBuilder(52 + String.valueOf(var3).length())).append("unregisterConnectionCallbacks(): listener ").append(var3).append(" not found").toString());
         } else if (this.zzaHK) {
            this.zzaHG.add(var1);
         }

      }
   }

   public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzaHH.contains(var1)) {
            String var3 = String.valueOf(var1);
            Log.w("GmsClientEvents", (new StringBuilder(67 + String.valueOf(var3).length())).append("registerConnectionFailedListener(): listener ").append(var3).append(" is already registered").toString());
         } else {
            this.zzaHH.add(var1);
         }

      }
   }

   public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaHH.contains(var1);
      }
   }

   public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener var1) {
      zzbo.zzu(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzaHH.remove(var1)) {
            String var3 = String.valueOf(var1);
            Log.w("GmsClientEvents", (new StringBuilder(57 + String.valueOf(var3).length())).append("unregisterConnectionFailedListener(): listener ").append(var3).append(" not found").toString());
         }

      }
   }

   public final boolean handleMessage(Message var1) {
      if (var1.what == 1) {
         GoogleApiClient.ConnectionCallbacks var7 = (GoogleApiClient.ConnectionCallbacks)var1.obj;
         Object var3 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzaHI && this.zzaHE.isConnected() && this.zzaHF.contains(var7)) {
               Bundle var4 = this.zzaHE.zzoC();
               var7.onConnected(var4);
            }

            return true;
         }
      } else {
         int var2 = var1.what;
         Log.wtf("GmsClientEvents", (new StringBuilder(45)).append("Don't know how to handle message: ").append(var2).toString(), new Exception());
         return false;
      }
   }
}
