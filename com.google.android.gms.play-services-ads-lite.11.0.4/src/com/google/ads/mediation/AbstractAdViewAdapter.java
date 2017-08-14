package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzalj;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzzn;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@zzzn
public abstract class AbstractAdViewAdapter implements com.google.android.gms.ads.mediation.MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, com.google.android.gms.ads.mediation.zza, MediationRewardedVideoAdAdapter, zzalj {
   public static final String AD_UNIT_ID_PARAMETER = "pubid";
   private AdView zzcM;
   private InterstitialAd zzcN;
   private AdLoader zzcO;
   private Context zzcP;
   private InterstitialAd zzcQ;
   private MediationRewardedVideoAdListener zzcR;
   private RewardedVideoAdListener zzcS = new zza(this);

   protected abstract Bundle zza(Bundle var1, Bundle var2);

   private final AdRequest zza(Context var1, com.google.android.gms.ads.mediation.MediationAdRequest var2, Bundle var3, Bundle var4) {
      AdRequest.Builder var5 = new AdRequest.Builder();
      Date var6;
      if ((var6 = var2.getBirthday()) != null) {
         var5.setBirthday(var6);
      }

      int var7;
      if ((var7 = var2.getGender()) != 0) {
         var5.setGender(var7);
      }

      Set var8;
      if ((var8 = var2.getKeywords()) != null) {
         Iterator var9 = var8.iterator();

         while(var9.hasNext()) {
            String var10 = (String)var9.next();
            var5.addKeyword(var10);
         }
      }

      Location var12;
      if ((var12 = var2.getLocation()) != null) {
         var5.setLocation(var12);
      }

      if (var2.isTesting()) {
         zzji.zzds();
         var5.addTestDevice(zzaiy.zzV(var1));
      }

      if (var2.taggedForChildDirectedTreatment() != -1) {
         var5.tagForChildDirectedTreatment(var2.taggedForChildDirectedTreatment() == 1);
      }

      var5.setIsDesignedForFamilies(var2.isDesignedForFamilies());
      Bundle var11 = this.zza(var3, var4);
      var5.addNetworkExtrasBundle(AdMobAdapter.class, var11);
      return var5.build();
   }

   public void onDestroy() {
      if (this.zzcM != null) {
         this.zzcM.destroy();
         this.zzcM = null;
      }

      if (this.zzcN != null) {
         this.zzcN = null;
      }

      if (this.zzcO != null) {
         this.zzcO = null;
      }

      if (this.zzcQ != null) {
         this.zzcQ = null;
      }

   }

   public void onPause() {
      if (this.zzcM != null) {
         this.zzcM.pause();
      }

   }

   public void onResume() {
      if (this.zzcM != null) {
         this.zzcM.resume();
      }

   }

   public String getAdUnitId(Bundle var1) {
      return var1.getString("pubid");
   }

   public void requestBannerAd(Context var1, com.google.android.gms.ads.mediation.MediationBannerListener var2, Bundle var3, AdSize var4, com.google.android.gms.ads.mediation.MediationAdRequest var5, Bundle var6) {
      this.zzcM = new AdView(var1);
      this.zzcM.setAdSize(new AdSize(var4.getWidth(), var4.getHeight()));
      this.zzcM.setAdUnitId(this.getAdUnitId(var3));
      this.zzcM.setAdListener(new AbstractAdViewAdapter.zzc(this, var2));
      this.zzcM.loadAd(this.zza(var1, var5, var6, var3));
   }

   public View getBannerView() {
      return this.zzcM;
   }

   public void requestInterstitialAd(Context var1, com.google.android.gms.ads.mediation.MediationInterstitialListener var2, Bundle var3, com.google.android.gms.ads.mediation.MediationAdRequest var4, Bundle var5) {
      this.zzcN = new InterstitialAd(var1);
      this.zzcN.setAdUnitId(this.getAdUnitId(var3));
      this.zzcN.setAdListener(new AbstractAdViewAdapter.zzd(this, var2));
      this.zzcN.loadAd(this.zza(var1, var4, var5, var3));
   }

   public void onImmersiveModeUpdated(boolean var1) {
      if (this.zzcN != null) {
         this.zzcN.setImmersiveMode(var1);
      }

      if (this.zzcQ != null) {
         this.zzcQ.setImmersiveMode(var1);
      }

   }

   public zzks getVideoController() {
      VideoController var1;
      return this.zzcM != null && (var1 = this.zzcM.getVideoController()) != null ? var1.zzae() : null;
   }

   public void showInterstitial() {
      this.zzcN.show();
   }

   public Bundle getInterstitialAdapterInfo() {
      return (new com.google.android.gms.ads.mediation.MediationAdapter.zza()).zzB(1).zzjh();
   }

   public void requestNativeAd(Context var1, MediationNativeListener var2, Bundle var3, NativeMediationAdRequest var4, Bundle var5) {
      AbstractAdViewAdapter.zze var6 = new AbstractAdViewAdapter.zze(this, var2);
      String var12 = var3.getString("pubid");
      AdLoader.Builder var7 = (new AdLoader.Builder(var1, var12)).withAdListener(var6);
      NativeAdOptions var8;
      if ((var8 = var4.getNativeAdOptions()) != null) {
         var7.withNativeAdOptions(var8);
      }

      if (var4.isAppInstallAdRequested()) {
         var7.forAppInstallAd(var6);
      }

      if (var4.isContentAdRequested()) {
         var7.forContentAd(var6);
      }

      if (var4.zzfz()) {
         Iterator var9 = var4.zzfA().keySet().iterator();

         while(var9.hasNext()) {
            String var10 = (String)var9.next();
            var7.forCustomTemplateAd(var10, var6, ((Boolean)var4.zzfA().get(var10)).booleanValue() ? var6 : null);
         }
      }

      this.zzcO = var7.build();
      this.zzcO.loadAd(this.zza(var1, var4, var5, var3));
   }

   public void initialize(Context var1, com.google.android.gms.ads.mediation.MediationAdRequest var2, String var3, MediationRewardedVideoAdListener var4, Bundle var5, Bundle var6) {
      this.zzcP = var1.getApplicationContext();
      this.zzcR = var4;
      this.zzcR.onInitializationSucceeded(this);
   }

   public void loadAd(com.google.android.gms.ads.mediation.MediationAdRequest var1, Bundle var2, Bundle var3) {
      if (this.zzcP != null && this.zzcR != null) {
         this.zzcQ = new InterstitialAd(this.zzcP);
         this.zzcQ.zza(true);
         this.zzcQ.setAdUnitId(this.getAdUnitId(var2));
         this.zzcQ.setRewardedVideoAdListener(this.zzcS);
         this.zzcQ.loadAd(this.zza(this.zzcP, var1, var3, var2));
      } else {
         zzajc.e("AdMobAdapter.loadAd called before initialize.");
      }
   }

   public void showVideo() {
      this.zzcQ.show();
   }

   public boolean isInitialized() {
      return this.zzcR != null;
   }

   // $FF: synthetic method
   static MediationRewardedVideoAdListener zza(AbstractAdViewAdapter var0) {
      return var0.zzcR;
   }

   // $FF: synthetic method
   static InterstitialAd zza(AbstractAdViewAdapter var0, InterstitialAd var1) {
      return var0.zzcQ = null;
   }

   static class zzb extends NativeContentAdMapper {
      private final NativeContentAd zzcV;

      public zzb(NativeContentAd var1) {
         this.zzcV = var1;
         this.setHeadline(var1.getHeadline().toString());
         this.setImages(var1.getImages());
         this.setBody(var1.getBody().toString());
         if (var1.getLogo() != null) {
            this.setLogo(var1.getLogo());
         }

         this.setCallToAction(var1.getCallToAction().toString());
         this.setAdvertiser(var1.getAdvertiser().toString());
         this.setOverrideImpressionRecording(true);
         this.setOverrideClickHandling(true);
         this.zza(var1.getVideoController());
      }

      public final void trackView(View var1) {
         if (var1 instanceof NativeAdView) {
            ((NativeAdView)var1).setNativeAd(this.zzcV);
         }

      }
   }

   static class zza extends NativeAppInstallAdMapper {
      private final NativeAppInstallAd zzcU;

      public zza(NativeAppInstallAd var1) {
         this.zzcU = var1;
         this.setHeadline(var1.getHeadline().toString());
         this.setImages(var1.getImages());
         this.setBody(var1.getBody().toString());
         this.setIcon(var1.getIcon());
         this.setCallToAction(var1.getCallToAction().toString());
         if (var1.getStarRating() != null) {
            this.setStarRating(var1.getStarRating().doubleValue());
         }

         if (var1.getStore() != null) {
            this.setStore(var1.getStore().toString());
         }

         if (var1.getPrice() != null) {
            this.setPrice(var1.getPrice().toString());
         }

         this.setOverrideImpressionRecording(true);
         this.setOverrideClickHandling(true);
         this.zza(var1.getVideoController());
      }

      public final void trackView(View var1) {
         if (var1 instanceof NativeAdView) {
            ((NativeAdView)var1).setNativeAd(this.zzcU);
         }

      }
   }

   static final class zze extends AdListener implements NativeAppInstallAd.OnAppInstallAdLoadedListener, NativeContentAd.OnContentAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener {
      private AbstractAdViewAdapter zzcW;
      private MediationNativeListener zzcZ;

      public zze(AbstractAdViewAdapter var1, MediationNativeListener var2) {
         this.zzcW = var1;
         this.zzcZ = var2;
      }

      public final void onAdLoaded() {
      }

      public final void onAdFailedToLoad(int var1) {
         this.zzcZ.onAdFailedToLoad(this.zzcW, var1);
      }

      public final void onAdOpened() {
         this.zzcZ.onAdOpened(this.zzcW);
      }

      public final void onAdClosed() {
         this.zzcZ.onAdClosed(this.zzcW);
      }

      public final void onAdLeftApplication() {
         this.zzcZ.onAdLeftApplication(this.zzcW);
      }

      public final void onAdClicked() {
         this.zzcZ.onAdClicked(this.zzcW);
      }

      public final void onAdImpression() {
         this.zzcZ.onAdImpression(this.zzcW);
      }

      public final void onAppInstallAdLoaded(NativeAppInstallAd var1) {
         this.zzcZ.onAdLoaded(this.zzcW, new AbstractAdViewAdapter.zza(var1));
      }

      public final void onContentAdLoaded(NativeContentAd var1) {
         this.zzcZ.onAdLoaded(this.zzcW, new AbstractAdViewAdapter.zzb(var1));
      }

      public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd var1) {
         this.zzcZ.zza(this.zzcW, var1);
      }

      public final void onCustomClick(NativeCustomTemplateAd var1, String var2) {
         this.zzcZ.zza(this.zzcW, var1, var2);
      }
   }

   static final class zzd extends AdListener implements zzim {
      private AbstractAdViewAdapter zzcW;
      private com.google.android.gms.ads.mediation.MediationInterstitialListener zzcY;

      public zzd(AbstractAdViewAdapter var1, com.google.android.gms.ads.mediation.MediationInterstitialListener var2) {
         this.zzcW = var1;
         this.zzcY = var2;
      }

      public final void onAdLoaded() {
         this.zzcY.onAdLoaded(this.zzcW);
      }

      public final void onAdFailedToLoad(int var1) {
         this.zzcY.onAdFailedToLoad(this.zzcW, var1);
      }

      public final void onAdOpened() {
         this.zzcY.onAdOpened(this.zzcW);
      }

      public final void onAdClosed() {
         this.zzcY.onAdClosed(this.zzcW);
      }

      public final void onAdLeftApplication() {
         this.zzcY.onAdLeftApplication(this.zzcW);
      }

      public final void onAdClicked() {
         this.zzcY.onAdClicked(this.zzcW);
      }
   }

   static final class zzc extends AdListener implements AppEventListener, zzim {
      private AbstractAdViewAdapter zzcW;
      private com.google.android.gms.ads.mediation.MediationBannerListener zzcX;

      public zzc(AbstractAdViewAdapter var1, com.google.android.gms.ads.mediation.MediationBannerListener var2) {
         this.zzcW = var1;
         this.zzcX = var2;
      }

      public final void onAdLoaded() {
         this.zzcX.onAdLoaded(this.zzcW);
      }

      public final void onAdFailedToLoad(int var1) {
         this.zzcX.onAdFailedToLoad(this.zzcW, var1);
      }

      public final void onAdOpened() {
         this.zzcX.onAdOpened(this.zzcW);
      }

      public final void onAdClosed() {
         this.zzcX.onAdClosed(this.zzcW);
      }

      public final void onAdLeftApplication() {
         this.zzcX.onAdLeftApplication(this.zzcW);
      }

      public final void onAdClicked() {
         this.zzcX.onAdClicked(this.zzcW);
      }

      public final void onAppEvent(String var1, String var2) {
         this.zzcX.zza(this.zzcW, var1, var2);
      }
   }
}
