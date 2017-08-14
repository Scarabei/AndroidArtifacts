package com.google.android.gms.ads.internal;

import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzacq;
import com.google.android.gms.internal.zzadd;
import com.google.android.gms.internal.zzaee;
import com.google.android.gms.internal.zzaez;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafm;
import com.google.android.gms.internal.zzafn;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzfh;
import com.google.android.gms.internal.zzii;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjl;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzqk;
import com.google.android.gms.internal.zzxg;
import com.google.android.gms.internal.zzxo;
import com.google.android.gms.internal.zzxy;
import com.google.android.gms.internal.zzzn;
import com.google.android.gms.internal.zzzp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

@zzzn
public abstract class zza extends zzka implements com.google.android.gms.ads.internal.overlay.zzag, zzafm, zzim, zzqk, zzxy, zzzp {
   protected zznb zzsK;
   private zzmz zzsL;
   private zzmz zzsM;
   protected boolean zzsN = false;
   protected final zzbi zzsO;
   protected final zzbt zzsP;
   @Nullable
   protected transient zzir zzsQ;
   protected final zzfh zzsR;
   protected final zzv zzsS;

   public final zzv zzak() {
      return this.zzsS;
   }

   zza(zzbt var1, @Nullable zzbi var2, zzv var3) {
      this.zzsP = var1;
      this.zzsO = new zzbi(this);
      this.zzsS = var3;
      zzbs.zzbz().zzE(this.zzsP.zzqD);
      zzbs.zzbD().zzd(this.zzsP.zzqD, this.zzsP.zzvT);
      zzbs.zzbE().initialize(this.zzsP.zzqD);
      this.zzsR = zzbs.zzbD().zzhG();
      zzbs.zzbC().initialize(this.zzsP.zzqD);
      zzme var8 = zzmo.zzFE;
      if (((Boolean)zzbs.zzbL().zzd(var8)).booleanValue()) {
         Timer var5 = new Timer();
         var8 = zzmo.zzFG;
         CountDownLatch var6 = new CountDownLatch(((Integer)zzbs.zzbL().zzd(var8)).intValue());
         zzb var7 = new zzb(this, var6, var5);
         var8 = zzmo.zzFF;
         var5.schedule(var7, 0L, ((Long)zzbs.zzbL().zzd(var8)).longValue());
      }

   }

   public void destroy() {
      com.google.android.gms.common.internal.zzbo.zzcz("destroy must be called on the main UI thread.");
      this.zzsO.cancel();
      this.zzsR.zzh(this.zzsP.zzvY);
      zzbt var1 = this.zzsP;
      zzbt var2 = this.zzsP;
      if (this.zzsP.zzvU != null) {
         var2.zzvU.zzcg();
      }

      var1.zzwc = null;
      var1.zzwd = null;
      var1.zzwo = null;
      var1.zzwe = null;
      var1.zze(false);
      if (var1.zzvU != null) {
         var1.zzvU.removeAllViews();
      }

      var1.zzca();
      var1.zzcb();
      var1.zzvY = null;
   }

   public final IObjectWrapper zzal() {
      com.google.android.gms.common.internal.zzbo.zzcz("getAdFrame must be called on the main UI thread.");
      return com.google.android.gms.dynamic.zzn.zzw(this.zzsP.zzvU);
   }

   @Nullable
   public final zziv zzam() {
      com.google.android.gms.common.internal.zzbo.zzcz("getAdSize must be called on the main UI thread.");
      return this.zzsP.zzvX == null ? null : new zzlv(this.zzsP.zzvX);
   }

   public final boolean isReady() {
      com.google.android.gms.common.internal.zzbo.zzcz("isLoaded must be called on the main UI thread.");
      return this.zzsP.zzvV == null && this.zzsP.zzvW == null && this.zzsP.zzvY != null;
   }

   public void setManualImpressionsEnabled(boolean var1) {
      throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
   }

   public boolean zza(zzir var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("loadAd must be called on the main UI thread.");
      zzbs.zzbE().zzcX();
      zzme var3 = zzmo.zzDR;
      if (((Boolean)zzbs.zzbL().zzd(var3)).booleanValue()) {
         zzir.zzh(var1);
      }

      var1 = com.google.android.gms.common.util.zzj.zzaI(this.zzsP.zzqD) && var1.zzzV != null ? (new zzis(var1)).zza((Location)null).zzdj() : var1;
      if (this.zzsP.zzvV == null && this.zzsP.zzvW == null) {
         zzafr.zzaS("Starting ad request.");
         zzme var4 = zzmo.zzCQ;
         this.zzsK = new zznb(((Boolean)zzbs.zzbL().zzd(var4)).booleanValue(), "load_ad", this.zzsP.zzvX.zzAs);
         this.zzsL = new zzmz(-1L, (String)null, (zzmz)null);
         this.zzsM = new zzmz(-1L, (String)null, (zzmz)null);
         this.zzsL = this.zzsK.zzdS();
         if (var1.zzzQ) {
            zzafr.zzaS("This request is sent from a test device.");
         } else {
            zzji.zzds();
            String var2 = String.valueOf(zzaiy.zzV(this.zzsP.zzqD));
            zzafr.zzaS((new StringBuilder(71 + String.valueOf(var2).length())).append("Use AdRequest.Builder.addTestDevice(\"").append(var2).append("\") to get test ads on this device.").toString());
         }

         this.zzsO.zzf(var1);
         this.zzsN = this.zza(var1, this.zzsK);
         return this.zzsN;
      } else {
         if (this.zzsQ != null) {
            zzafr.zzaT("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
         } else {
            zzafr.zzaT("Loading already in progress, saving this object for future refreshes.");
         }

         this.zzsQ = var1;
         return false;
      }
   }

   protected abstract boolean zza(zzir var1, zznb var2);

   public final void zza(zzafg var1) {
      long var2;
      if (var1.zzXY.zzTs != -1L && !TextUtils.isEmpty(var1.zzXY.zzTB) && (var2 = zzr(var1.zzXY.zzTB)) != -1L) {
         zzmz var4 = this.zzsK.zzc(var1.zzXY.zzTs + var2);
         this.zzsK.zza(var4, "stc");
      }

      this.zzsK.zzO(var1.zzXY.zzTB);
      this.zzsK.zza(this.zzsL, "arf");
      this.zzsM = this.zzsK.zzdS();
      this.zzsK.zzh("gqi", var1.zzXY.zzTC);
      this.zzsP.zzvV = null;
      this.zzsP.zzvZ = var1;
      var1.zzXX.zza((zzii)(new zzc(this, var1)));
      var1.zzXX.zzdf();
      this.zza(var1, this.zzsK);
   }

   protected abstract void zza(zzafg var1, zznb var2);

   boolean zza(zzaff var1) {
      return false;
   }

   public void zzb(zzaff var1) {
      this.zzsK.zza(this.zzsM, "awr");
      this.zzsP.zzvW = null;
      if (var1.errorCode != -2 && var1.errorCode != 3) {
         zzbs.zzbD().zzb(this.zzsP.zzbZ());
      }

      if (var1.errorCode == -1) {
         this.zzsN = false;
      } else {
         if (this.zza(var1)) {
            zzafr.zzaC("Ad refresh scheduled.");
         }

         if (var1.errorCode != -2) {
            this.zze(var1.errorCode);
         } else {
            if (this.zzsP.zzwr == null) {
               this.zzsP.zzwr = new zzafn(this.zzsP.zzvR);
            }

            this.zzsR.zzg(this.zzsP.zzvY);
            if (this.zza(this.zzsP.zzvY, var1)) {
               this.zzsP.zzvY = var1;
               zzbt var2 = this.zzsP;
               if (this.zzsP.zzwa != null) {
                  if (var2.zzvY != null) {
                     var2.zzwa.zzh(var2.zzvY.zzXR);
                     var2.zzwa.zzi(var2.zzvY.zzXS);
                     var2.zzwa.zzw(var2.zzvY.zzTo);
                  }

                  var2.zzwa.zzv(var2.zzvX.zzAt);
               }

               this.zzsK.zzh("is_mraid", this.zzsP.zzvY.zzcn() ? "1" : "0");
               this.zzsK.zzh("is_mediation", this.zzsP.zzvY.zzTo ? "1" : "0");
               if (this.zzsP.zzvY.zzPg != null && this.zzsP.zzvY.zzPg.zziw() != null) {
                  this.zzsK.zzh("is_delay_pl", this.zzsP.zzvY.zzPg.zziw().zziS() ? "1" : "0");
               }

               this.zzsK.zza(this.zzsL, "ttc");
               if (zzbs.zzbD().zzhr() != null) {
                  zzbs.zzbD().zzhr().zza(this.zzsK);
               }

               this.zzaw();
               if (this.zzsP.zzcc()) {
                  this.zzas();
               }
            }

            if (var1.zzMd != null) {
               zzbs.zzbz().zza(this.zzsP.zzqD, var1.zzMd);
            }

         }
      }
   }

   protected abstract boolean zza(@Nullable zzaff var1, zzaff var2);

   public void onAdClicked() {
      if (this.zzsP.zzvY == null) {
         zzafr.zzaT("Ad state was null when trying to ping click URLs.");
      } else {
         zzafr.zzaC("Pinging click URLs.");
         if (this.zzsP.zzwa != null) {
            this.zzsP.zzwa.zzhd();
         }

         if (this.zzsP.zzvY.zzMa != null) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzb(this.zzsP.zzvY.zzMa));
         }

         if (this.zzsP.zzwb != null) {
            try {
               this.zzsP.zzwb.onAdClicked();
               return;
            } catch (RemoteException var2) {
               zzafr.zzc("Could not notify onAdClicked event.", var2);
            }
         }

      }
   }

   public final void zzan() {
      this.zzaq();
   }

   public final void onAppEvent(String var1, @Nullable String var2) {
      if (this.zzsP.zzwd != null) {
         try {
            this.zzsP.zzwd.onAppEvent(var1, var2);
            return;
         } catch (RemoteException var4) {
            zzafr.zzc("Could not call the AppEventListener.", var4);
         }
      }

   }

   public final void zzao() {
      com.google.android.gms.common.internal.zzbo.zzcz("recordManualImpression must be called on the main UI thread.");
      if (this.zzsP.zzvY == null) {
         zzafr.zzaT("Ad state was null when trying to ping manual tracking URLs.");
      } else {
         zzafr.zzaC("Pinging manual tracking URLs.");
         if (this.zzsP.zzvY.zzTq != null && !this.zzsP.zzvY.zzXV) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzTq);
            this.zzsP.zzvY.zzXV = true;
         }

      }
   }

   public void pause() {
      com.google.android.gms.common.internal.zzbo.zzcz("pause must be called on the main UI thread.");
   }

   public void resume() {
      com.google.android.gms.common.internal.zzbo.zzcz("resume must be called on the main UI thread.");
   }

   protected boolean zzb(zzir var1) {
      if (this.zzsP.zzvU == null) {
         return false;
      } else {
         ViewParent var2;
         if (!((var2 = this.zzsP.zzvU.getParent()) instanceof View)) {
            return false;
         } else {
            View var3 = (View)var2;
            return zzbs.zzbz().zza(var3, var3.getContext());
         }
      }
   }

   public final void zza(zzjo var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setAdListener must be called on the main UI thread.");
      this.zzsP.zzwc = var1;
   }

   public final void zza(zzadd var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setRewardedVideoAdListener can only be called from the UI thread.");
      this.zzsP.zzwp = var1;
   }

   public final void setUserId(String var1) {
      zzafr.zzaT("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
   }

   public final void zza(zzjl var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setAdListener must be called on the main UI thread.");
      this.zzsP.zzwb = var1;
   }

   public final void zza(zziv var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setAdSize must be called on the main UI thread.");
      this.zzsP.zzvX = var1;
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null && this.zzsP.zzwt == 0) {
         this.zzsP.zzvY.zzPg.zza(var1);
      }

      if (this.zzsP.zzvU != null) {
         if (this.zzsP.zzvU.getChildCount() > 1) {
            this.zzsP.zzvU.removeView(this.zzsP.zzvU.getNextView());
         }

         this.zzsP.zzvU.setMinimumWidth(var1.widthPixels);
         this.zzsP.zzvU.setMinimumHeight(var1.heightPixels);
         this.zzsP.zzvU.requestLayout();
      }
   }

   public final void zza(zzke var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setAppEventListener must be called on the main UI thread.");
      this.zzsP.zzwd = var1;
   }

   public void zza(zzxg var1) {
      zzafr.zzaT("setInAppPurchaseListener is deprecated and should not be called.");
   }

   public void zza(zznh var1) {
      throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
   }

   public final void zza(zzxo var1, String var2) {
      zzafr.zzaT("setPlayStorePurchaseParams is deprecated and should not be called.");
   }

   public final void zza(zzkk var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setCorrelationIdProvider must be called on the main UI thread");
      this.zzsP.zzwe = var1;
   }

   public final void stopLoading() {
      com.google.android.gms.common.internal.zzbo.zzcz("stopLoading must be called on the main UI thread.");
      this.zzsN = false;
      this.zzsP.zze(true);
   }

   public final boolean isLoading() {
      return this.zzsN;
   }

   public zzks getVideoController() {
      return null;
   }

   public final void zza(@Nullable zzlx var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setVideoOptions must be called on the main UI thread.");
      this.zzsP.zzwk = var1;
   }

   public final void zza(@Nullable zzky var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setIconAdOptions must be called on the main UI thread.");
      this.zzsP.zzwl = var1;
   }

   public void setImmersiveMode(boolean var1) {
      throw new IllegalStateException("onImmersiveModeUpdated is not supported for current ad type");
   }

   private static long zzr(String var0) {
      int var1 = var0.indexOf("ufe");
      int var2;
      if ((var2 = var0.indexOf(44, var1)) == -1) {
         var2 = var0.length();
      }

      try {
         return Long.parseLong(var0.substring(var1 + 4, var2));
      } catch (IndexOutOfBoundsException var3) {
         zzafr.zzaT("Invalid index for Url fetch time in CSI latency info.");
      } catch (NumberFormatException var4) {
         zzafr.zzaT("Cannot find valid format of Url fetch time in CSI latency info.");
      }

      return -1L;
   }

   protected final void zzb(View var1) {
      zzbu var2 = this.zzsP.zzvU;
      if (this.zzsP.zzvU != null) {
         var2.addView(var1, zzbs.zzbB().zzhW());
      }

   }

   protected void zzap() {
      zzafr.zzaS("Ad closing.");
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdClosed();
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdClosed().", var2);
         }
      }

      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoAdClosed();
            return;
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", var3);
         }
      }

   }

   protected final void zzaq() {
      zzafr.zzaS("Ad leaving application.");
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdLeftApplication();
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdLeftApplication().", var2);
         }
      }

      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoAdLeftApplication();
            return;
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", var3);
         }
      }

   }

   protected final void zzar() {
      zzafr.zzaS("Ad opening.");
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdOpened();
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdOpened().", var2);
         }
      }

      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoAdOpened();
            return;
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", var3);
         }
      }

   }

   protected void zzas() {
      zzafr.zzaS("Ad finished loading.");
      this.zzsN = false;
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdLoaded();
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdLoaded().", var2);
         }
      }

      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoAdLoaded();
            return;
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", var3);
         }
      }

   }

   protected void zze(int var1) {
      zzafr.zzaT((new StringBuilder(30)).append("Failed to load ad: ").append(var1).toString());
      this.zzsN = false;
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdFailedToLoad(var1);
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call AdListener.onAdFailedToLoad().", var3);
         }
      }

      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoAdFailedToLoad(var1);
            return;
         } catch (RemoteException var4) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", var4);
         }
      }

   }

   public final void zzat() {
      zzafr.zzaS("Ad impression.");
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdImpression();
            return;
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdImpression().", var2);
         }
      }

   }

   public final void zzau() {
      zzafr.zzaS("Ad clicked.");
      if (this.zzsP.zzwc != null) {
         try {
            this.zzsP.zzwc.onAdClicked();
            return;
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call AdListener.onAdClicked().", var2);
         }
      }

   }

   protected final void zzav() {
      if (this.zzsP.zzwp != null) {
         try {
            this.zzsP.zzwp.onRewardedVideoStarted();
         } catch (RemoteException var2) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onVideoStarted().", var2);
         }
      }
   }

   protected final void zza(@Nullable zzaee var1) {
      if (this.zzsP.zzwp != null) {
         try {
            String var2 = "";
            int var3 = 1;
            if (var1 != null) {
               var2 = var1.type;
               var3 = var1.zzWW;
            }

            this.zzsP.zzwp.zza(new zzacq(var2, var3));
         } catch (RemoteException var4) {
            zzafr.zzc("Could not call RewardedVideoAdListener.onRewarded().", var4);
         }
      }
   }

   public final void zzaw() {
      zzaff var2 = this.zzsP.zzvY;
      if (var2 != null && !TextUtils.isEmpty(var2.zzTK) && !var2.zzXW && zzbs.zzbH().zzic()) {
         zzafr.zzaC("Sending troubleshooting signals to the server.");
         zzbs.zzbH().zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, var2.zzTK, this.zzsP.zzvR);
         var2.zzXW = true;
      }
   }

   public final void zza(HashSet var1) {
      this.zzsP.zza(var1);
   }

   protected final List zzb(List var1) {
      ArrayList var2 = new ArrayList(var1.size());
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         var2.add(zzaez.zzb(var4, this.zzsP.zzqD));
      }

      return var2;
   }

   public String getAdUnitId() {
      return this.zzsP.zzvR;
   }

   public final zzke zzax() {
      return this.zzsP.zzwd;
   }

   public final zzjo zzay() {
      return this.zzsP.zzwc;
   }
}
