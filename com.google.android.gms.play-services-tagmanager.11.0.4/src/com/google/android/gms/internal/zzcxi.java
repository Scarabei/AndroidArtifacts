package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.tagmanager.TagManagerService;

public final class zzcxi implements ServiceConnection {
   private final Context mContext;
   private final zza zzaHQ;
   private volatile boolean zzbJL;
   private volatile boolean zzbJM;
   private zzcvg zzbJN;

   public zzcxi(Context var1) {
      this(var1, zza.zzrU());
   }

   private zzcxi(Context var1, zza var2) {
      this.zzbJL = false;
      this.zzbJM = false;
      this.mContext = var1;
      this.zzaHQ = var2;
   }

   @WorkerThread
   private final boolean zzCE() {
      if (this.zzbJL) {
         return true;
      } else {
         synchronized(this) {
            if (this.zzbJL) {
               return true;
            } else {
               if (!this.zzbJM) {
                  Intent var2 = new Intent(this.mContext, TagManagerService.class);
                  if (!this.zzaHQ.zza(this.mContext, var2, this, 1)) {
                     return false;
                  }

                  this.zzbJM = true;
               }

               while(this.zzbJM) {
                  try {
                     this.wait();
                     this.zzbJM = false;
                  } catch (InterruptedException var4) {
                     zzcvk.zzc("Error connecting to TagManagerService", var4);
                     this.zzbJM = false;
                  }
               }

               return this.zzbJL;
            }
         }
      }
   }

   @MainThread
   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      synchronized(this) {
         IInterface var6;
         this.zzbJN = (zzcvg)(var2 == null ? null : ((var6 = var2.queryLocalInterface("com.google.android.gms.tagmanager.internal.ITagManagerService")) instanceof zzcvg ? (zzcvg)var6 : new zzcvi(var2)));
         this.zzbJL = true;
         this.zzbJM = false;
         this.notifyAll();
      }
   }

   @MainThread
   public final void onServiceDisconnected(ComponentName var1) {
      synchronized(this) {
         this.zzbJN = null;
         this.zzbJL = false;
         this.zzbJM = false;
      }
   }

   @WorkerThread
   public final void zzb(String var1, @Nullable String var2, @Nullable String var3, @Nullable zzcvd var4) {
      if (this.zzCE()) {
         try {
            this.zzbJN.zza(var1, var2, var3, var4);
            return;
         } catch (RemoteException var10) {
            zzcvk.zzc("Error calling service to load container", var10);
         }
      }

      String var7 = var1;
      zzcvd var6 = var4;
      if (var4 != null) {
         try {
            var6.zza(false, var7);
            return;
         } catch (RemoteException var9) {
            zzcvk.zzb("Error - local callback should not throw RemoteException", var9);
         }
      }

   }

   @WorkerThread
   public final boolean zzCF() {
      if (this.zzCE()) {
         try {
            this.zzbJN.zzCr();
            return true;
         } catch (RemoteException var2) {
            zzcvk.zzc("Error in resetting service", var2);
         }
      }

      return false;
   }

   @WorkerThread
   public final void zza(String var1, Bundle var2, String var3, long var4, boolean var6) {
      if (this.zzCE()) {
         try {
            this.zzbJN.zza(var1, var2, var3, var4, var6);
            return;
         } catch (RemoteException var8) {
            zzcvk.zzc("Error calling service to emit event", var8);
         }
      }

   }

   @WorkerThread
   public final void dispatch() {
      if (this.zzCE()) {
         try {
            this.zzbJN.dispatch();
            return;
         } catch (RemoteException var2) {
            zzcvk.zzc("Error calling service to dispatch pending events", var2);
         }
      }

   }
}
