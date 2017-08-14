package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@zzzn
public class zzjk extends AdListener {
   private final Object lock = new Object();
   private AdListener zzAQ;

   public final void zza(AdListener var1) {
      Object var2 = this.lock;
      synchronized(this.lock) {
         this.zzAQ = var1;
      }
   }

   public void onAdClosed() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         if (this.zzAQ != null) {
            this.zzAQ.onAdClosed();
         }

      }
   }

   public void onAdFailedToLoad(int var1) {
      Object var2 = this.lock;
      synchronized(this.lock) {
         if (this.zzAQ != null) {
            this.zzAQ.onAdFailedToLoad(var1);
         }

      }
   }

   public void onAdLeftApplication() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         if (this.zzAQ != null) {
            this.zzAQ.onAdLeftApplication();
         }

      }
   }

   public void onAdOpened() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         if (this.zzAQ != null) {
            this.zzAQ.onAdOpened();
         }

      }
   }

   public void onAdLoaded() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         if (this.zzAQ != null) {
            this.zzAQ.onAdLoaded();
         }

      }
   }
}
