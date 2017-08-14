package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzaed implements MediationRewardedVideoAdListener {
   private final zzaea zzWV;

   public zzaed(zzaea var1) {
      this.zzWV = var1;
   }

   public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onInitializationSucceeded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onInitializationSucceeded.");

      try {
         this.zzWV.zzq(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onInitializationSucceeded.", var3);
      }
   }

   public final void onInitializationFailed(MediationRewardedVideoAdAdapter var1, int var2) {
      zzbo.zzcz("onInitializationFailed must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onInitializationFailed.");

      try {
         this.zzWV.zzc(zzn.zzw(var1), var2);
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onInitializationFailed.", var4);
      }
   }

   public final void onAdLoaded(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onAdLoaded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLoaded.");

      try {
         this.zzWV.zzr(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLoaded.", var3);
      }
   }

   public final void onAdOpened(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onAdOpened must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdOpened.");

      try {
         this.zzWV.zzs(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdOpened.", var3);
      }
   }

   public final void onVideoStarted(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onVideoStarted must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onVideoStarted.");

      try {
         this.zzWV.zzt(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onVideoStarted.", var3);
      }
   }

   public final void onAdClosed(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onAdClosed must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClosed.");

      try {
         this.zzWV.zzu(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClosed.", var3);
      }
   }

   public final void onRewarded(MediationRewardedVideoAdAdapter var1, RewardItem var2) {
      zzbo.zzcz("onRewarded must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onRewarded.");

      try {
         if (var2 != null) {
            this.zzWV.zza(zzn.zzw(var1), new zzaee(var2));
         } else {
            this.zzWV.zza(zzn.zzw(var1), new zzaee("", 1));
         }
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onRewarded.", var4);
      }
   }

   public final void onAdClicked(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onAdClicked must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdClicked.");

      try {
         this.zzWV.zzv(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdClicked.", var3);
      }
   }

   public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter var1, int var2) {
      zzbo.zzcz("onAdFailedToLoad must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdFailedToLoad.");

      try {
         this.zzWV.zzd(zzn.zzw(var1), var2);
      } catch (RemoteException var4) {
         zzajc.zzc("Could not call onAdFailedToLoad.", var4);
      }
   }

   public final void onAdLeftApplication(MediationRewardedVideoAdAdapter var1) {
      zzbo.zzcz("onAdLeftApplication must be called on the main UI thread.");
      zzajc.zzaC("Adapter called onAdLeftApplication.");

      try {
         this.zzWV.zzw(zzn.zzw(var1));
      } catch (RemoteException var3) {
         zzajc.zzc("Could not call onAdLeftApplication.", var3);
      }
   }
}
