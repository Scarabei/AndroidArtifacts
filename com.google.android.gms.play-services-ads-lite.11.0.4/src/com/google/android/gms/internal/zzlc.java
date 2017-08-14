package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.concurrent.atomic.AtomicBoolean;

@zzzn
public final class zzlc {
   private final zzup zzBb;
   private final zziu zzrQ;
   private final AtomicBoolean zzBc;
   private final VideoController zzBd;
   private zzjk zzBe;
   private zzim zzzL;
   private AdListener zzzM;
   private AdSize[] zzAy;
   private AppEventListener zzsw;
   private Correlator zzBf;
   private zzjz zzBg;
   private OnCustomRenderedAdLoadedListener zzBh;
   private VideoOptions zzsr;
   private String zztV;
   private ViewGroup zzBi;
   private int zzBj;
   private boolean zzsu;

   public zzlc(ViewGroup var1) {
      this(var1, (AttributeSet)null, false, zziu.zzAr, 0);
   }

   public zzlc(ViewGroup var1, int var2) {
      this(var1, (AttributeSet)null, false, zziu.zzAr, var2);
   }

   public zzlc(ViewGroup var1, AttributeSet var2, boolean var3) {
      this(var1, var2, var3, zziu.zzAr, 0);
   }

   public zzlc(ViewGroup var1, AttributeSet var2, boolean var3, int var4) {
      this(var1, var2, false, zziu.zzAr, var4);
   }

   private zzlc(ViewGroup var1, AttributeSet var2, boolean var3, zziu var4, zzjz var5, int var6) {
      this.zzBb = new zzup();
      this.zzBd = new VideoController();
      this.zzBe = new zzld(this);
      this.zzBi = var1;
      this.zzrQ = var4;
      this.zzBg = null;
      this.zzBc = new AtomicBoolean(false);
      this.zzBj = var6;
      if (var2 != null) {
         Context var7 = var1.getContext();

         try {
            zziy var8 = new zziy(var7, var2);
            this.zzAy = var8.zzg(var3);
            this.zztV = var8.getAdUnitId();
         } catch (IllegalArgumentException var14) {
            zzji.zzds().zza(var1, new zziv(var7, AdSize.BANNER), var14.getMessage(), var14.getMessage());
            return;
         }

         if (var1.isInEditMode()) {
            zzaiy var10000 = zzji.zzds();
            AdSize var10003 = this.zzAy[0];
            int var11 = this.zzBj;
            AdSize var10 = var10003;
            zziv var12;
            (var12 = new zziv(var7, var10)).zzAx = zzl(var11);
            var10000.zza(var1, var12, "Ads by Google");
         }
      }

   }

   private zzlc(ViewGroup var1, AttributeSet var2, boolean var3, zziu var4, int var5) {
      this(var1, var2, var3, var4, (zzjz)null, var5);
   }

   public final void destroy() {
      try {
         if (this.zzBg != null) {
            this.zzBg.destroy();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Failed to destroy AdView.", var2);
      }
   }

   public final AdListener getAdListener() {
      return this.zzzM;
   }

   public final AdSize getAdSize() {
      try {
         zziv var1;
         if (this.zzBg != null && (var1 = this.zzBg.zzam()) != null) {
            return var1.zzdl();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to get the current AdSize.", var2);
      }

      return this.zzAy != null ? this.zzAy[0] : null;
   }

   public final AdSize[] getAdSizes() {
      return this.zzAy;
   }

   public final String getAdUnitId() {
      if (this.zztV == null && this.zzBg != null) {
         try {
            this.zztV = this.zzBg.getAdUnitId();
         } catch (RemoteException var2) {
            zzajc.zzc("Failed to get ad unit id.", var2);
         }
      }

      return this.zztV;
   }

   public final AppEventListener getAppEventListener() {
      return this.zzsw;
   }

   public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
      return this.zzBh;
   }

   public final void zza(zzla var1) {
      try {
         if (this.zzBg == null) {
            if ((this.zzAy == null || this.zztV == null) && this.zzBg == null) {
               throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
            }

            Context var5;
            zziv var6;
            zziv var7 = var6 = zza(var5 = this.zzBi.getContext(), this.zzAy, this.zzBj);
            String var10;
            zzjz var16;
            zziz var10001;
            zziz var17;
            if ("search_v2".equals(var7.zzAs)) {
               var10001 = zzji.zzdt();
               var10 = this.zztV;
               var17 = var10001;
               var16 = (zzjz)zziz.zza(var5, false, new zzjb(var17, var5, var6, var10));
            } else {
               var10001 = zzji.zzdt();
               zzup var11 = this.zzBb;
               var10 = this.zztV;
               var17 = var10001;
               var16 = (zzjz)zziz.zza(var5, false, new zzja(var17, var5, var6, var10, var11));
            }

            this.zzBg = var16;
            this.zzBg.zza((zzjo)(new zzio(this.zzBe)));
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

            if (this.zzsr != null) {
               this.zzBg.zza(new zzlx(this.zzsr));
            }

            this.zzBg.setManualImpressionsEnabled(this.zzsu);
            zzlc var12 = this;

            try {
               IObjectWrapper var13;
               if ((var13 = var12.zzBg.zzal()) != null) {
                  var12.zzBi.addView((View)zzn.zzE(var13));
               }
            } catch (RemoteException var14) {
               zzajc.zzc("Failed to get an ad frame.", var14);
            }
         }

         if (this.zzBg.zza(zziu.zza(this.zzBi.getContext(), var1))) {
            this.zzBb.zzg(var1.zzdz());
         }

      } catch (RemoteException var15) {
         zzajc.zzc("Failed to load ad.", var15);
      }
   }

   public final void pause() {
      try {
         if (this.zzBg != null) {
            this.zzBg.pause();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Failed to call pause.", var2);
      }
   }

   public final void recordManualImpression() {
      if (!this.zzBc.getAndSet(true)) {
         try {
            if (this.zzBg != null) {
               this.zzBg.zzao();
            }

         } catch (RemoteException var2) {
            zzajc.zzc("Failed to record impression.", var2);
         }
      }
   }

   public final void resume() {
      try {
         if (this.zzBg != null) {
            this.zzBg.resume();
         }

      } catch (RemoteException var2) {
         zzajc.zzc("Failed to call resume.", var2);
      }
   }

   public final void setAdListener(AdListener var1) {
      this.zzzM = var1;
      this.zzBe.zza(var1);
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

   public final void setAdSizes(AdSize... var1) {
      if (this.zzAy != null) {
         throw new IllegalStateException("The ad size can only be set once on AdView.");
      } else {
         this.zza(var1);
      }
   }

   public final void zza(AdSize... var1) {
      this.zzAy = var1;

      try {
         if (this.zzBg != null) {
            this.zzBg.zza(zza(this.zzBi.getContext(), this.zzAy, this.zzBj));
         }
      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the ad size.", var3);
      }

      this.zzBi.requestLayout();
   }

   public final void setAdUnitId(String var1) {
      if (this.zztV != null) {
         throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
      this.zzBh = var1;

      try {
         if (this.zzBg != null) {
            this.zzBg.zza((zznh)(var1 != null ? new zznk(var1) : null));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set the onCustomRenderedAdLoadedListener.", var3);
      }
   }

   public final void setManualImpressionsEnabled(boolean var1) {
      this.zzsu = var1;

      try {
         if (this.zzBg != null) {
            this.zzBg.setManualImpressionsEnabled(this.zzsu);
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set manual impressions.", var3);
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

   public final boolean isLoading() {
      try {
         if (this.zzBg != null) {
            return this.zzBg.isLoading();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to check if ad is loading.", var2);
      }

      return false;
   }

   public final VideoController getVideoController() {
      return this.zzBd;
   }

   public final zzks zzae() {
      if (this.zzBg == null) {
         return null;
      } else {
         try {
            return this.zzBg.getVideoController();
         } catch (RemoteException var2) {
            zzajc.zzc("Failed to retrieve VideoController.", var2);
            return null;
         }
      }
   }

   public final void setVideoOptions(VideoOptions var1) {
      this.zzsr = var1;

      try {
         if (this.zzBg != null) {
            this.zzBg.zza(var1 == null ? null : new zzlx(var1));
         }

      } catch (RemoteException var3) {
         zzajc.zzc("Failed to set video options.", var3);
      }
   }

   public final VideoOptions getVideoOptions() {
      return this.zzsr;
   }

   public final boolean zza(zzjz var1) {
      if (var1 == null) {
         return false;
      } else {
         IObjectWrapper var2;
         try {
            var2 = var1.zzal();
         } catch (RemoteException var4) {
            zzajc.zzc("Failed to get an ad frame.", var4);
            return false;
         }

         if (var2 == null) {
            return false;
         } else if (((View)zzn.zzE(var2)).getParent() != null) {
            return false;
         } else {
            this.zzBi.addView((View)zzn.zzE(var2));
            this.zzBg = var1;
            return true;
         }
      }
   }

   private static zziv zza(Context var0, AdSize[] var1, int var2) {
      zziv var3;
      (var3 = new zziv(var0, var1)).zzAx = zzl(var2);
      return var3;
   }

   private static boolean zzl(int var0) {
      return var0 == 1;
   }

   // $FF: synthetic method
   static VideoController zza(zzlc var0) {
      return var0.zzBd;
   }
}
