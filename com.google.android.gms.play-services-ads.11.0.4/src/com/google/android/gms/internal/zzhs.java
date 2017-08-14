package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

@zzzn
public final class zzhs {
   private final Runnable zzzp = new zzht(this);
   private final Object mLock = new Object();
   @Nullable
   private zzhz zzzq;
   @Nullable
   private Context mContext;
   @Nullable
   private zzid zzzr;

   public final void initialize(Context var1) {
      if (var1 != null) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.mContext == null) {
               this.mContext = var1.getApplicationContext();
               zzme var4 = zzmo.zzGk;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
                  this.connect();
               } else {
                  var4 = zzmo.zzGj;
                  if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
                     zzhu var6 = new zzhu(this);
                     com.google.android.gms.ads.internal.zzbs.zzbC().zza(var6);
                  }
               }

            }
         }
      }
   }

   public final void zzcX() {
      zzme var3 = zzmo.zzGl;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
         Object var1 = this.mLock;
         synchronized(this.mLock) {
            this.connect();
            com.google.android.gms.ads.internal.zzbs.zzbz();
            zzagz.zzZr.removeCallbacks(this.zzzp);
            com.google.android.gms.ads.internal.zzbs.zzbz();
            var3 = zzmo.zzGm;
            zzagz.zzZr.postDelayed(this.zzzp, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).longValue());
         }
      }
   }

   public final zzhx zza(zzia var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzzr == null) {
            return new zzhx();
         } else {
            zzhx var10000;
            try {
               var10000 = this.zzzr.zza(var1);
            } catch (RemoteException var5) {
               zzafr.zzb("Unable to call into cache service.", var5);
               return new zzhx();
            }

            return var10000;
         }
      }
   }

   private final void connect() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.mContext != null && this.zzzq == null) {
            zzhv var2 = new zzhv(this);
            zzhw var3 = new zzhw(this);
            this.zzzq = new zzhz(this.mContext, com.google.android.gms.ads.internal.zzbs.zzbP().zzie(), var2, var3);
            this.zzzq.zzrb();
         }
      }
   }

   private final void disconnect() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzzq != null) {
            if (this.zzzq.isConnected() || this.zzzq.isConnecting()) {
               this.zzzq.disconnect();
            }

            this.zzzq = null;
            this.zzzr = null;
            Binder.flushPendingCommands();
         }
      }
   }

   // $FF: synthetic method
   static void zza(zzhs var0) {
      var0.disconnect();
   }

   // $FF: synthetic method
   static void zzb(zzhs var0) {
      var0.connect();
   }

   // $FF: synthetic method
   static Object zzc(zzhs var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static zzhz zzd(zzhs var0) {
      return var0.zzzq;
   }

   // $FF: synthetic method
   static zzid zza(zzhs var0, zzid var1) {
      return var0.zzzr = var1;
   }

   // $FF: synthetic method
   static zzhz zza(zzhs var0, zzhz var1) {
      return var0.zzzq = null;
   }
}
