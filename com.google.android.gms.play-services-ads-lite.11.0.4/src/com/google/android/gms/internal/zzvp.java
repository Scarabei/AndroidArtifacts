package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

@zzzn
public final class zzvp implements MediationBannerListener, MediationInterstitialListener {
   private final zzuw zzNc;

   public zzvp(zzuw var1) {
      this.zzNc = var1;
   }

   public final void onClick(MediationBannerAdapter var1) {
      zzajc.zzaC("Adapter called onClick.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onClick must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvq(this));
      } else {
         try {
            this.zzNc.onAdClicked();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdClicked.", var3);
         }
      }
   }

   public final void onDismissScreen(MediationBannerAdapter var1) {
      zzajc.zzaC("Adapter called onDismissScreen.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onDismissScreen must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvt(this));
      } else {
         try {
            this.zzNc.onAdClosed();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdClosed.", var3);
         }
      }
   }

   public final void onFailedToReceiveAd(MediationBannerAdapter var1, AdRequest.ErrorCode var2) {
      String var3 = String.valueOf(var2);
      zzajc.zzaC((new StringBuilder(47 + String.valueOf(var3).length())).append("Adapter called onFailedToReceiveAd with error. ").append(var3).toString());
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onFailedToReceiveAd must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvu(this, var2));
      } else {
         try {
            this.zzNc.onAdFailedToLoad(zzwb.zza(var2));
         } catch (RemoteException var4) {
            zzajc.zzc("Could not call onAdFailedToLoad.", var4);
         }
      }
   }

   public final void onLeaveApplication(MediationBannerAdapter var1) {
      zzajc.zzaC("Adapter called onLeaveApplication.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onLeaveApplication must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvv(this));
      } else {
         try {
            this.zzNc.onAdLeftApplication();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdLeftApplication.", var3);
         }
      }
   }

   public final void onPresentScreen(MediationBannerAdapter var1) {
      zzajc.zzaC("Adapter called onPresentScreen.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onPresentScreen must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvw(this));
      } else {
         try {
            this.zzNc.onAdOpened();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdOpened.", var3);
         }
      }
   }

   public final void onReceivedAd(MediationBannerAdapter var1) {
      zzajc.zzaC("Adapter called onReceivedAd.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onReceivedAd must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvx(this));
      } else {
         try {
            this.zzNc.onAdLoaded();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdLoaded.", var3);
         }
      }
   }

   public final void onDismissScreen(MediationInterstitialAdapter var1) {
      zzajc.zzaC("Adapter called onDismissScreen.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onDismissScreen must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvy(this));
      } else {
         try {
            this.zzNc.onAdClosed();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdClosed.", var3);
         }
      }
   }

   public final void onFailedToReceiveAd(MediationInterstitialAdapter var1, AdRequest.ErrorCode var2) {
      String var3 = String.valueOf(var2);
      zzajc.zzaC((new StringBuilder(47 + String.valueOf(var3).length())).append("Adapter called onFailedToReceiveAd with error ").append(var3).append(".").toString());
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onFailedToReceiveAd must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvz(this, var2));
      } else {
         try {
            this.zzNc.onAdFailedToLoad(zzwb.zza(var2));
         } catch (RemoteException var4) {
            zzajc.zzc("Could not call onAdFailedToLoad.", var4);
         }
      }
   }

   public final void onLeaveApplication(MediationInterstitialAdapter var1) {
      zzajc.zzaC("Adapter called onLeaveApplication.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onLeaveApplication must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzwa(this));
      } else {
         try {
            this.zzNc.onAdLeftApplication();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdLeftApplication.", var3);
         }
      }
   }

   public final void onPresentScreen(MediationInterstitialAdapter var1) {
      zzajc.zzaC("Adapter called onPresentScreen.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onPresentScreen must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvr(this));
      } else {
         try {
            this.zzNc.onAdOpened();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdOpened.", var3);
         }
      }
   }

   public final void onReceivedAd(MediationInterstitialAdapter var1) {
      zzajc.zzaC("Adapter called onReceivedAd.");
      zzji.zzds();
      if (!zzaiy.zzil()) {
         zzajc.zzaT("onReceivedAd must be called on the main UI thread.");
         zzaiy.zzaaH.post(new zzvs(this));
      } else {
         try {
            this.zzNc.onAdLoaded();
         } catch (RemoteException var3) {
            zzajc.zzc("Could not call onAdLoaded.", var3);
         }
      }
   }

   // $FF: synthetic method
   static zzuw zza(zzvp var0) {
      return var0.zzNc;
   }
}
