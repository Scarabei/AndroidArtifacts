package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzzn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzai extends zzjs {
   private final Context mContext;
   private final zzjo zztK;
   private final zzuq zzsX;
   @Nullable
   private final zzpn zztL;
   @Nullable
   private final zzpq zztM;
   @Nullable
   private final zzpz zztN;
   @Nullable
   private final zziv zztO;
   @Nullable
   private final PublisherAdViewOptions zztP;
   private final SimpleArrayMap zztQ;
   private final SimpleArrayMap zztR;
   private final zzon zztS;
   private final List zztT;
   private final zzkk zztU;
   private final String zztV;
   private final zzaje zztW;
   @Nullable
   private WeakReference zztX;
   private final zzv zzsS;
   private final Object mLock = new Object();

   zzai(Context var1, String var2, zzuq var3, zzaje var4, zzjo var5, zzpn var6, zzpq var7, SimpleArrayMap var8, SimpleArrayMap var9, zzon var10, zzkk var11, zzv var12, zzpz var13, zziv var14, PublisherAdViewOptions var15) {
      this.mContext = var1;
      this.zztV = var2;
      this.zzsX = var3;
      this.zztW = var4;
      this.zztK = var5;
      this.zztM = var7;
      this.zztL = var6;
      this.zztQ = var8;
      this.zztR = var9;
      this.zztS = var10;
      this.zztT = this.zzaY();
      this.zztU = var11;
      this.zzsS = var12;
      this.zztN = var13;
      this.zztO = var14;
      this.zztP = var15;
      zzmo.initialize(this.mContext);
   }

   public final void zzc(zzir var1) {
      zzaj var2 = new zzaj(this, var1);
      zzagz.zzZr.post(var2);
   }

   private final void zzd(zzir var1) {
      zzq var2 = new zzq(this.mContext, this.zzsS, this.zztO, this.zztV, this.zzsX, this.zztW);
      this.zztX = new WeakReference(var2);
      zzpz var4 = this.zztN;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
      var2.zzsP.zzwm = var4;
      if (this.zztP != null) {
         if (this.zztP.zzai() != null) {
            var2.zza((zzke)this.zztP.zzai());
         }

         var2.setManualImpressionsEnabled(this.zztP.getManualImpressionsEnabled());
      }

      zzpn var5 = this.zztL;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
      var2.zzsP.zzwf = var5;
      zzpq var6 = this.zztM;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnContentAdLoadedListener must be called on the main UI thread.");
      var2.zzsP.zzwg = var6;
      SimpleArrayMap var7 = this.zztQ;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
      var2.zzsP.zzwi = var7;
      var7 = this.zztR;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnCustomClickListener must be called on the main UI thread.");
      var2.zzsP.zzwh = var7;
      zzon var8 = this.zztS;
      com.google.android.gms.common.internal.zzbo.zzcz("setNativeAdOptions must be called on the main UI thread.");
      var2.zzsP.zzwj = var8;
      var2.zzc(this.zzaY());
      var2.zza((zzjo)this.zztK);
      var2.zza((zzkk)this.zztU);
      ArrayList var9 = new ArrayList();
      if (this.zzaX()) {
         var9.add(Integer.valueOf(1));
      }

      if (this.zztN != null) {
         var9.add(Integer.valueOf(2));
      }

      var2.zzd(var9);
      if (this.zzaX()) {
         var1.extras.putBoolean("ina", true);
      }

      if (this.zztN != null) {
         var1.extras.putBoolean("iba", true);
      }

      var2.zza(var1);
   }

   private final void zze(zzir var1) {
      zzbb var2 = new zzbb(this.mContext, this.zzsS, zziv.zzg(this.mContext), this.zztV, this.zzsX, this.zztW);
      this.zztX = new WeakReference(var2);
      zzpn var4 = this.zztL;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
      var2.zzsP.zzwf = var4;
      zzpq var6 = this.zztM;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnContentAdLoadedListener must be called on the main UI thread.");
      var2.zzsP.zzwg = var6;
      SimpleArrayMap var7 = this.zztQ;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
      var2.zzsP.zzwi = var7;
      var2.zza((zzjo)this.zztK);
      var7 = this.zztR;
      com.google.android.gms.common.internal.zzbo.zzcz("setOnCustomClickListener must be called on the main UI thread.");
      var2.zzsP.zzwh = var7;
      var2.zzc(this.zzaY());
      zzon var5 = this.zztS;
      com.google.android.gms.common.internal.zzbo.zzcz("setNativeAdOptions must be called on the main UI thread.");
      var2.zzsP.zzwj = var5;
      var2.zza((zzkk)this.zztU);
      var2.zza((zzir)var1);
   }

   private final boolean zzaW() {
      zzme var1 = zzmo.zzDP;
      return ((Boolean)zzbs.zzbL().zzd(var1)).booleanValue() && this.zztN != null;
   }

   private final boolean zzaX() {
      return this.zztL != null || this.zztM != null || this.zztQ != null && this.zztQ.size() > 0;
   }

   @Nullable
   public final String getMediationAdapterClassName() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zztX != null) {
            zzd var2;
            return (var2 = (zzd)this.zztX.get()) != null ? var2.getMediationAdapterClassName() : null;
         } else {
            return null;
         }
      }
   }

   @Nullable
   public final String zzaI() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zztX != null) {
            zzd var2;
            return (var2 = (zzd)this.zztX.get()) != null ? var2.zzaI() : null;
         } else {
            return null;
         }
      }
   }

   public final boolean isLoading() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zztX != null) {
            zzd var2;
            return (var2 = (zzd)this.zztX.get()) != null ? var2.isLoading() : false;
         } else {
            return false;
         }
      }
   }

   private final List zzaY() {
      ArrayList var1 = new ArrayList();
      if (this.zztM != null) {
         var1.add("1");
      }

      if (this.zztL != null) {
         var1.add("2");
      }

      if (this.zztQ.size() > 0) {
         var1.add("3");
      }

      return var1;
   }

   // $FF: synthetic method
   static Object zza(zzai var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static boolean zzb(zzai var0) {
      return var0.zzaW();
   }

   // $FF: synthetic method
   static void zza(zzai var0, zzir var1) {
      var0.zzd(var1);
   }

   // $FF: synthetic method
   static void zzb(zzai var0, zzir var1) {
      var0.zze(var1);
   }
}
