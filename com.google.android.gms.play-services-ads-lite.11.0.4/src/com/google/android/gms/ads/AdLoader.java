package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzio;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzjr;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.internal.zzqe;
import com.google.android.gms.internal.zzqf;
import com.google.android.gms.internal.zzqg;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzup;

public class AdLoader {
   private final zziu zzrQ;
   private final Context mContext;
   private final zzjr zzrR;

   AdLoader(Context var1, zzjr var2) {
      this(var1, var2, zziu.zzAr);
   }

   private AdLoader(Context var1, zzjr var2, zziu var3) {
      this.mContext = var1;
      this.zzrR = var2;
      this.zzrQ = var3;
   }

   private final void zza(zzla var1) {
      try {
         this.zzrR.zzc(zziu.zza(this.mContext, var1));
      } catch (RemoteException var3) {
         zzajc.zzb("Failed to load ad.", var3);
      }
   }

   @RequiresPermission("android.permission.INTERNET")
   public void loadAd(AdRequest var1) {
      this.zza(var1.zzab());
   }

   public void loadAd(PublisherAdRequest var1) {
      this.zza(var1.zzab());
   }

   public String getMediationAdapterClassName() {
      try {
         return this.zzrR.zzaI();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to get the mediation adapter class name.", var2);
         return null;
      }
   }

   public boolean isLoading() {
      try {
         return this.zzrR.isLoading();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to check if ad is loading.", var2);
         return false;
      }
   }

   public static class Builder {
      private final Context mContext;
      private final zzju zzrS;

      public Builder(Context var1, String var2) {
         this((Context)zzbo.zzb(var1, "context cannot be null"), zzji.zzdt().zzb(var1, var2, new zzup()));
      }

      private Builder(Context var1, zzju var2) {
         this.mContext = var1;
         this.zzrS = var2;
      }

      public AdLoader.Builder forContentAd(NativeContentAd.OnContentAdLoadedListener var1) {
         try {
            this.zzrS.zza((zzpq)(new zzqe(var1)));
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to add content ad listener", var3);
         }

         return this;
      }

      public AdLoader.Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener var1) {
         try {
            this.zzrS.zza((zzpn)(new zzqd(var1)));
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to add app install ad listener", var3);
         }

         return this;
      }

      public AdLoader.Builder forCustomTemplateAd(String var1, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener var2, NativeCustomTemplateAd.OnCustomClickListener var3) {
         try {
            this.zzrS.zza(var1, new zzqg(var2), var3 == null ? null : new zzqf(var3));
         } catch (RemoteException var5) {
            zzajc.zzc("Failed to add custom template ad listener", var5);
         }

         return this;
      }

      public AdLoader.Builder forPublisherAdView(OnPublisherAdViewLoadedListener var1, AdSize... var2) {
         if (var2 != null && var2.length > 0) {
            try {
               zziv var3 = new zziv(this.mContext, var2);
               this.zzrS.zza(new zzqh(var1), var3);
            } catch (RemoteException var4) {
               zzajc.zzc("Failed to add publisher banner ad listener", var4);
            }

            return this;
         } else {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
         }
      }

      public AdLoader.Builder withAdListener(AdListener var1) {
         try {
            this.zzrS.zzb((zzjo)(new zzio(var1)));
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to set AdListener.", var3);
         }

         return this;
      }

      public AdLoader.Builder withNativeAdOptions(NativeAdOptions var1) {
         try {
            this.zzrS.zza(new zzon(var1));
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to specify native ad options", var3);
         }

         return this;
      }

      public AdLoader.Builder withPublisherAdViewOptions(PublisherAdViewOptions var1) {
         try {
            this.zzrS.zza(var1);
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to specify DFP banner ad options", var3);
         }

         return this;
      }

      public AdLoader.Builder withCorrelator(@NonNull Correlator var1) {
         zzbo.zzu(var1);

         try {
            this.zzrS.zzb((zzkk)var1.zzac());
         } catch (RemoteException var3) {
            zzajc.zzc("Failed to set correlator.", var3);
         }

         return this;
      }

      public AdLoader build() {
         try {
            return new AdLoader(this.mContext, this.zzrS.zzaZ());
         } catch (RemoteException var2) {
            zzajc.zzb("Failed to build AdLoader.", var2);
            return null;
         }
      }
   }
}
