package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;

@zzzn
public final class zzaix {
   private final View mView;
   private Activity zzaaC;
   private boolean zzaaD;
   private boolean zzaaE;
   private boolean zzzE;
   private OnGlobalLayoutListener zzaaF;
   private OnScrollChangedListener zzaaG;

   public zzaix(Activity var1, View var2, OnGlobalLayoutListener var3, OnScrollChangedListener var4) {
      this.zzaaC = var1;
      this.mView = var2;
      this.zzaaF = var3;
      this.zzaaG = var4;
   }

   public final void zzi(Activity var1) {
      this.zzaaC = var1;
   }

   public final void zzig() {
      this.zzzE = true;
      if (this.zzaaE) {
         this.zzii();
      }

   }

   public final void zzih() {
      this.zzzE = false;
      this.zzij();
   }

   public final void onAttachedToWindow() {
      this.zzaaE = true;
      if (this.zzzE) {
         this.zzii();
      }

   }

   public final void onDetachedFromWindow() {
      this.zzaaE = false;
      this.zzij();
   }

   private final void zzii() {
      if (!this.zzaaD) {
         if (this.zzaaF != null) {
            if (this.zzaaC != null) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.zza(this.zzaaC, this.zzaaF);
            }

            com.google.android.gms.ads.internal.zzbs.zzbX();
            zzajv.zza(this.mView, this.zzaaF);
         }

         if (this.zzaaG != null) {
            if (this.zzaaC != null) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.zza(this.zzaaC, this.zzaaG);
            }

            com.google.android.gms.ads.internal.zzbs.zzbX();
            zzajv.zza(this.mView, this.zzaaG);
         }

         this.zzaaD = true;
      }

   }

   private final void zzij() {
      if (this.zzaaC != null) {
         if (this.zzaaD) {
            if (this.zzaaF != null && this.zzaaC != null) {
               com.google.android.gms.ads.internal.zzbs.zzbB().zzb(this.zzaaC, this.zzaaF);
            }

            if (this.zzaaG != null && this.zzaaC != null) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.zzb(this.zzaaC, this.zzaaG);
            }

            this.zzaaD = false;
         }

      }
   }
}
