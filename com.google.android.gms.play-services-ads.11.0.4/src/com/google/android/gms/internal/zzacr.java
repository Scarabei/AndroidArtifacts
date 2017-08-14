package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzacr extends zzacz {
   private final Context mContext;
   private final Object mLock;
   private final zzaje zztW;
   private final zzacs zzWq;

   public zzacr(Context var1, zzv var2, zzuq var3, zzaje var4) {
      this(var1, var4, new zzacs(var1, var2, zziv.zzdk(), var3, var4));
   }

   private zzacr(Context var1, zzaje var2, zzacs var3) {
      this.mLock = new Object();
      this.mContext = var1;
      this.zztW = var2;
      this.zzWq = var3;
   }

   public final void zza(zzadj var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.zza(var1);
      }
   }

   public final void show() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.zzgP();
      }
   }

   public final void zza(zzadd var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.zza(var1);
      }
   }

   public final void setUserId(String var1) {
      zzafr.zzaT("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
   }

   public final boolean isLoaded() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzWq.isLoaded();
      }
   }

   public final void pause() {
      this.zzf((IObjectWrapper)null);
   }

   public final void zzf(IObjectWrapper var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.pause();
      }
   }

   public final void resume() {
      this.zzg((IObjectWrapper)null);
   }

   public final void zzg(IObjectWrapper var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         try {
            Context var10000 = var1 == null ? null : (Context)zzn.zzE(var1);
            Context var3 = var10000;
            if (var10000 != null) {
               this.zzWq.onContextChanged(var3);
            }
         } catch (Exception var5) {
            zzafr.zzc("Unable to extract updated context.", var5);
         }

         this.zzWq.resume();
      }
   }

   public final void destroy() {
      this.zzh((IObjectWrapper)null);
   }

   public final void zzh(IObjectWrapper var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.destroy();
      }
   }

   public final String getMediationAdapterClassName() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzWq.getMediationAdapterClassName();
      }
   }

   public final void setImmersiveMode(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzWq.setImmersiveMode(var1);
      }
   }
}
