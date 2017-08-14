package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

@zzzn
public final class zzle {
   private final zzup zzBb;
   private final Context mContext;
   private final zziu zzrQ;
   private AdListener zzzM;
   private zzim zzzL;
   private zzjz zzBg;
   private String zztV;
   private AppEventListener zzsw;
   private PublisherInterstitialAd zzBl;
   private OnCustomRenderedAdLoadedListener zzBh;
   private Correlator zzBf;
   private RewardedVideoAdListener zzcS;
   private boolean zzBm;
   private boolean zzuj;

   public zzle(Context var1) {
      this(var1, zziu.zzAr, (PublisherInterstitialAd)null);
   }

   public zzle(Context var1, PublisherInterstitialAd var2) {
      this(var1, zziu.zzAr, var2);
   }

   private zzle(Context var1, zziu var2, PublisherInterstitialAd var3) {
      this.zzBb = new zzup();
      this.mContext = var1;
      this.zzrQ = var2;
      this.zzBl = var3;
   }

   public final AdListener getAdListener() {
      return this.zzzM;
   }

   public final String getAdUnitId() {
      return this.zztV;
   }

   public final AppEventListener getAppEventListener() {
      return this.zzsw;
   }

   public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
      return this.zzBh;
   }

   public final boolean isLoaded() {
      try {
         return this.zzBg == null ? false : this.zzBg.isReady();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to check if ad is ready.", var2);
         return false;
      }
   }

   public final boolean isLoading() {
      try {
         return this.zzBg == null ? false : this.zzBg.isLoading();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to check if ad is loading.", var2);
         return false;
      }
   }

   public final void zza(zzla var1) {
      try {
         if (this.zzBg == null) {
            String var4 = "loadAd";
            if (this.zztV == null) {
               this.zzK(var4);
            }

            zziv var5 = this.zzBm ? zziv.zzdk() : new zziv();
            zziz var10001 = zzji.zzdt();
            zzup var10 = this.zzBb;
            String var9 = this.zztV;
            Context var7 = this.mContext;
            zziz var6 = var10001;
            this.zzBg = (zzjz)zziz.zza(var7, false, new zzjc(var6, var7, var5, var9, var10));
            if (this.zzzM != null) {
               this.zzBg.zza((zzjo)(new zzio(this.zzzM)));
            }

            if (this.zzzL != null) {
               this.zzBg.zza((zzjl)(new zzin(this.zzzL)));
            }

            if (this.zzsw != null) {
               this.zzBg.zza((zzke)(new zzix(this.zzsw)));
            }

            if (this.zzBh != null) {
               this.zzBg.zza((zznh)(new zznk(this.zzBh)));
            }

            if (this.zzBf != null) {
               this.zzBg.zza((zzkk)this.zzBf.zzac());
            }

            if (this.zzcS != null) {
               this.zzBg.zza((zzadd)(new zzadi(this.zzcS)));
            }

            this.zzBg.setImmersiveMode(this.zzuj);
         }

         if (this.zzBg.zza(zziu.zza(this.mContext, var1))) {
            this.zzBb.zzg(var1.zzdz());
         }

      } catch (RemoteException var11) {
         zzajc.zzc("Failed to load ad.", var11);
      }
   }

   public final void setAdListener(AdListener var1) {
      try {
         this.zzzM = var1;
         if (this.zzBg != null) {
            this.zzBg.zza((zzjo)(var1 != null ? new zzio(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the AdListener.", var3);
      }
   }

   public final void zza(zzim var1) {
      try {
         this.zzzL = var1;
         if (this.zzBg != null) {
            this.zzBg.zza((zzjl)(var1 != null ? new zzin(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the AdClickListener.", var3);
      }
   }

   public final void setAdUnitId(String var1) {
      if (this.zztV != null) {
         throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
      } else {
         this.zztV = var1;
      }
   }

   public final void setAppEventListener(AppEventListener var1) {
      try {
         this.zzsw = var1;
         if (this.zzBg != null) {
            this.zzBg.zza((zzke)(var1 != null ? new zzix(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the AppEventListener.", var3);
      }
   }

   public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener var1) {
      try {
         this.zzBh = var1;
         if (this.zzBg != null) {
            this.zzBg.zza((zznh)(var1 != null ? new zznk(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the OnCustomRenderedAdLoadedListener.", var3);
      }
   }

   public final void setCorrelator(Correlator var1) {
      this.zzBf = var1;

      try {
         if (this.zzBg != null) {
            this.zzBg.zza((zzkk)(this.zzBf == null ? null : this.zzBf.zzac()));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set correlator.", var3);
      }
   }

   public final void setRewardedVideoAdListener(RewardedVideoAdListener var1) {
      try {
         this.zzcS = var1;
         if (this.zzBg != null) {
            this.zzBg.zza((zzadd)(var1 != null ? new zzadi(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the AdListener.", var3);
      }
   }

   public final void zza(boolean var1) {
      this.zzBm = true;
   }

   public final String getMediationAdapterClassName() {
      try {
         if (this.zzBg != null) {
            return this.zzBg.zzaI();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to get the mediation adapter class name.", var2);
      }

      return null;
   }

   public final void show() {
      try {
         this.zzK("show");
         this.zzBg.showInterstitial();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to show interstitial.", var2);
      }
   }

   public final void setImmersiveMode(boolean var1) {
      try {
         this.zzuj = var1;
         if (this.zzBg != null) {
            this.zzBg.setImmersiveMode(var1);
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set immersive mode", var3);
      }
   }

   private final void zzK(String var1) {
      if (this.zzBg == null) {
         throw new IllegalStateException((new StringBuilder(63 + String.valueOf(var1).length())).append("The ad unit ID must be set on InterstitialAd before ").append(var1).append(" is called.").toString());
      }
   }
}
