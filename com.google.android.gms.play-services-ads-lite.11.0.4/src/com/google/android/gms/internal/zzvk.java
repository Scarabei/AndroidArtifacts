package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzbo;

@zzzn
public final class zzvk implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
   private final zzuw zzNc;
   private NativeAdMapper zzNd;
   private NativeCustomTemplateAd zzNe;

   public zzvk(zzuw var1) {
      this.zzNc = var1;
   }

   public final void zza(MediationBannerAdapter var1, String var2, String var3) {
      zzbo.zzcz("onAppEvent must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAppEvent.");

      try {
         this.zzNc.onAppEvent(var2, var3);
      } catch (RemoteException var5) {
         zzajc.zzc("Could not call onAppEvent.", var5);
      }
   }

   public final void onAdClicked(MediationBannerAdapter var1) {
      zzbo.zzcz("onAdClicked must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClicked.");

      try {
         this.zzNc.onAdClicked();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClicked.", var3);
      }
   }

   public final void onAdClosed(MediationBannerAdapter var1) {
      zzbo.zzcz("onAdClosed must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClosed.");

      try {
         this.zzNc.onAdClosed();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClosed.", var3);
      }
   }

   public final void onAdFailedToLoad(MediationBannerAdapter var1, int var2) {
      zzbo.zzcz("onAdFailedToLoad must be called on the main UI thread.");
      zzajc.zzaC((new StringBuilder(55)).append("Adapter called onAdFailedToLoad with error. ").append(var2).toString());

      try {
         this.zzNc.onAdFailedToLoad(var2);
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdFailedToLoad.", var4);
      }
   }

   public final void onAdLeftApplication(MediationBannerAdapter var1) {
      zzbo.zzcz("onAdLeftApplication must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLeftApplication.");

      try {
         this.zzNc.onAdLeftApplication();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLeftApplication.", var3);
      }
   }

   public final void onAdOpened(MediationBannerAdapter var1) {
      zzbo.zzcz("onAdOpened must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdOpened.");

      try {
         this.zzNc.onAdOpened();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdOpened.", var3);
      }
   }

   public final void onAdLoaded(MediationBannerAdapter var1) {
      zzbo.zzcz("onAdLoaded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLoaded.");

      try {
         this.zzNc.onAdLoaded();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLoaded.", var3);
      }
   }

   public final void onAdClicked(MediationInterstitialAdapter var1) {
      zzbo.zzcz("onAdClicked must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClicked.");

      try {
         this.zzNc.onAdClicked();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClicked.", var3);
      }
   }

   public final void onAdClosed(MediationInterstitialAdapter var1) {
      zzbo.zzcz("onAdClosed must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClosed.");

      try {
         this.zzNc.onAdClosed();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClosed.", var3);
      }
   }

   public final void onAdFailedToLoad(MediationInterstitialAdapter var1, int var2) {
      zzbo.zzcz("onAdFailedToLoad must be called on the main UI thread.");
      zzajc.zzaC((new StringBuilder(55)).append("Adapter called onAdFailedToLoad with error ").append(var2).append(".").toString());

      try {
         this.zzNc.onAdFailedToLoad(var2);
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdFailedToLoad.", var4);
      }
   }

   public final void onAdLeftApplication(MediationInterstitialAdapter var1) {
      zzbo.zzcz("onAdLeftApplication must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLeftApplication.");

      try {
         this.zzNc.onAdLeftApplication();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLeftApplication.", var3);
      }
   }

   public final void onAdOpened(MediationInterstitialAdapter var1) {
      zzbo.zzcz("onAdOpened must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdOpened.");

      try {
         this.zzNc.onAdOpened();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdOpened.", var3);
      }
   }

   public final void onAdLoaded(MediationInterstitialAdapter var1) {
      zzbo.zzcz("onAdLoaded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLoaded.");

      try {
         this.zzNc.onAdLoaded();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLoaded.", var3);
      }
   }

   public final void onAdLoaded(MediationNativeAdapter var1, NativeAdMapper var2) {
      zzbo.zzcz("onAdLoaded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLoaded.");
      this.zzNd = var2;

      try {
         this.zzNc.onAdLoaded();
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdLoaded.", var4);
      }
   }

   public final void zza(MediationNativeAdapter var1, NativeCustomTemplateAd var2) {
      zzbo.zzcz("onAdLoaded must be called on the main UI thread.");
      String var10001 = String.valueOf(var2.getCustomTemplateId());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Adapter called onAdLoaded with template id ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Adapter called onAdLoaded with template id ");
      }

      zzajc.zzaC(var10000);
      this.zzNe = var2;

      try {
         this.zzNc.onAdLoaded();
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdLoaded.", var4);
      }
   }

   public final void zza(MediationNativeAdapter var1, NativeCustomTemplateAd var2, String var3) {
      if (var2 instanceof zzpm) {
         try {
            this.zzNc.zzb(((zzpm)var2).zzex(), var3);
         } catch (RemoteException var5) {
            zzajc.zzc("Could not call onCustomClick.", var5);
         }
      } else {
         zzajc.zzaT("Unexpected native custom template ad type.");
      }
   }

   public final void onAdFailedToLoad(MediationNativeAdapter var1, int var2) {
      zzbo.zzcz("onAdFailedToLoad must be called on the main UI thread.");
      zzajc.zzaC((new StringBuilder(55)).append("Adapter called onAdFailedToLoad with error ").append(var2).append(".").toString());

      try {
         this.zzNc.onAdFailedToLoad(var2);
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdFailedToLoad.", var4);
      }
   }

   public final void onAdOpened(MediationNativeAdapter var1) {
      zzbo.zzcz("onAdOpened must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdOpened.");

      try {
         this.zzNc.onAdOpened();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdOpened.", var3);
      }
   }

   public final void onAdClosed(MediationNativeAdapter var1) {
      zzbo.zzcz("onAdClosed must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClosed.");

      try {
         this.zzNc.onAdClosed();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClosed.", var3);
      }
   }

   public final void onAdLeftApplication(MediationNativeAdapter var1) {
      zzbo.zzcz("onAdLeftApplication must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLeftApplication.");

      try {
         this.zzNc.onAdLeftApplication();
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLeftApplication.", var3);
      }
   }

   public final void onAdClicked(MediationNativeAdapter var1) {
      zzbo.zzcz("onAdClicked must be called on the main UI thread.");
      NativeAdMapper var2 = this.zzNd;
      if (this.zzNe == null) {
         if (var2 == null) {
            zzajc.zzaT("Could not call onAdClicked since NativeAdMapper is null.");
            return;
         }

         if (!var2.getOverrideClickHandling()) {
            zzajc.zzaC("Could not call onAdClicked since setOverrideClickHandling is not set to true");
            return;
         }
      }

      zzajc.zzaC("Adapter called onAdClicked.");

      try {
         this.zzNc.onAdClicked();
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdClicked.", var4);
      }
   }

   public final void onAdImpression(MediationNativeAdapter var1) {
      zzbo.zzcz("onAdImpression must be called on the main UI thread.");
      NativeAdMapper var2 = this.zzNd;
      if (this.zzNe == null) {
         if (var2 == null) {
            zzajc.zzaT("Could not call onAdImpression since NativeAdMapper is null. ");
            return;
         }

         if (!var2.getOverrideImpressionRecording()) {
            zzajc.zzaC("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
            return;
         }
      }

      zzajc.zzaC("Adapter called onAdImpression.");

      try {
         this.zzNc.onAdImpression();
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdImpression.", var4);
      }
   }

   public final NativeAdMapper zzfx() {
      return this.zzNd;
   }

   public final NativeCustomTemplateAd zzfy() {
      return this.zzNe;
   }
}
