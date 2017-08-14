package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zzzn
public final class zzud extends zzux {
   private final Object mLock = new Object();
   private zzui zzMq;
   private zzuc zzMr;

   public final void zza(zzui var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzMq = var1;
      }
   }

   public final void onAdClicked() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zzaC();
         }

      }
   }

   public final void onAdClosed() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zzaD();
         }

      }
   }

   public final void onAdFailedToLoad(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMq != null) {
            int var3 = var1 == 3 ? 1 : 2;
            this.zzMq.zzo(var3);
            this.zzMq = null;
         }

      }
   }

   public final void onAdLeftApplication() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zzaE();
         }

      }
   }

   public final void onAdOpened() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zzaF();
         }

      }
   }

   public final void onAdLoaded() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMq != null) {
            this.zzMq.zzo(0);
            this.zzMq = null;
         } else {
            if (this.zzMr != null) {
               this.zzMr.zzaG();
            }

         }
      }
   }

   public final void zza(zzuz var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMq != null) {
            this.zzMq.zza(0, var1);
            this.zzMq = null;
         } else {
            if (this.zzMr != null) {
               this.zzMr.zzaG();
            }

         }
      }
   }

   public final void onAdImpression() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zzaH();
         }

      }
   }

   public final void onAppEvent(String var1, String var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zze(var1, var2);
         }

      }
   }

   public final void zzb(zzpj var1, String var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzMr != null) {
            this.zzMr.zza(var1, var2);
         }

      }
   }

   public final void zza(@Nullable zzuc var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzMr = var1;
      }
   }
}
