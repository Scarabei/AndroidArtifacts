package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzzn
public final class zzvj extends zzuu {
   private final MediationAdapter zzNa;
   private zzvk zzNb;

   public zzvj(MediationAdapter var1) {
      this.zzNa = var1;
   }

   public final IObjectWrapper getView() throws RemoteException {
      if (!(this.zzNa instanceof MediationBannerAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationBannerAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationBannerAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         try {
            return zzn.zzw(((MediationBannerAdapter)this.zzNa).getBannerView());
         } catch (Throwable var2) {
            zzajc.zzc("Could not get banner view from adapter.", var2);
            throw new RemoteException();
         }
      }
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, zzuw var5) throws RemoteException {
      this.zza(var1, var2, var3, var4, (String)null, var5);
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, String var5, zzuw var6) throws RemoteException {
      if (!(this.zzNa instanceof MediationBannerAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationBannerAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationBannerAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Requesting banner ad from adapter.");

         try {
            MediationBannerAdapter var7 = (MediationBannerAdapter)this.zzNa;
            HashSet var8 = var3.zzzP != null ? new HashSet(var3.zzzP) : null;
            zzvi var9 = new zzvi(var3.zzzN == -1L ? null : new Date(var3.zzzN), var3.zzzO, var8, var3.zzzV, var3.zzzQ, var3.zzzR, var3.zzAc);
            Bundle var10 = var3.zzzX != null ? var3.zzzX.getBundle(var7.getClass().getName()) : null;
            var7.requestBannerAd((Context)zzn.zzE(var1), new zzvk(var6), this.zza(var4, var3, var5), zzb.zza(var2.width, var2.height, var2.zzAs), var9, var10);
         } catch (Throwable var11) {
            zzajc.zzc("Could not request banner ad from adapter.", var11);
            throw new RemoteException();
         }
      }
   }

   public final Bundle zzfs() {
      if (!(this.zzNa instanceof zzali)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a v2 MediationBannerAdapter: ");
         }

         zzajc.zzaT(var10000);
         return new Bundle();
      } else {
         return ((zzali)this.zzNa).zzfs();
      }
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzuw var4) throws RemoteException {
      this.zza(var1, (zzir)var2, (String)var3, (String)null, (zzuw)var4);
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5) throws RemoteException {
      if (!(this.zzNa instanceof MediationInterstitialAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationInterstitialAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Requesting interstitial ad from adapter.");

         try {
            MediationInterstitialAdapter var6 = (MediationInterstitialAdapter)this.zzNa;
            HashSet var7 = var2.zzzP != null ? new HashSet(var2.zzzP) : null;
            zzvi var8 = new zzvi(var2.zzzN == -1L ? null : new Date(var2.zzzN), var2.zzzO, var7, var2.zzzV, var2.zzzQ, var2.zzzR, var2.zzAc);
            Bundle var9 = var2.zzzX != null ? var2.zzzX.getBundle(var6.getClass().getName()) : null;
            var6.requestInterstitialAd((Context)zzn.zzE(var1), new zzvk(var5), this.zza(var3, var2, var4), var8, var9);
         } catch (Throwable var10) {
            zzajc.zzc("Could not request interstitial ad from adapter.", var10);
            throw new RemoteException();
         }
      }
   }

   public final Bundle getInterstitialAdapterInfo() {
      if (!(this.zzNa instanceof zzalj)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a v2 MediationInterstitialAdapter: ");
         }

         zzajc.zzaT(var10000);
         return new Bundle();
      } else {
         return ((zzalj)this.zzNa).getInterstitialAdapterInfo();
      }
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5, zzon var6, List var7) throws RemoteException {
      if (!(this.zzNa instanceof MediationNativeAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationNativeAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationNativeAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         try {
            MediationNativeAdapter var8 = (MediationNativeAdapter)this.zzNa;
            HashSet var9 = var2.zzzP != null ? new HashSet(var2.zzzP) : null;
            zzvn var10 = new zzvn(var2.zzzN == -1L ? null : new Date(var2.zzzN), var2.zzzO, var9, var2.zzzV, var2.zzzQ, var2.zzzR, var6, var7, var2.zzAc);
            Bundle var11 = var2.zzzX != null ? var2.zzzX.getBundle(var8.getClass().getName()) : null;
            this.zzNb = new zzvk(var5);
            var8.requestNativeAd((Context)zzn.zzE(var1), this.zzNb, this.zza(var3, var2, var4), var10, var11);
         } catch (Throwable var12) {
            zzajc.zzc("Could not request native ad from adapter.", var12);
            throw new RemoteException();
         }
      }
   }

   public final Bundle zzft() {
      return new Bundle();
   }

   public final zzvc zzfq() {
      NativeAdMapper var1;
      return (var1 = this.zzNb.zzfx()) instanceof NativeAppInstallAdMapper ? new zzvl((NativeAppInstallAdMapper)var1) : null;
   }

   public final zzvf zzfr() {
      NativeAdMapper var1;
      return (var1 = this.zzNb.zzfx()) instanceof NativeContentAdMapper ? new zzvm((NativeContentAdMapper)var1) : null;
   }

   public final zzpj zzfv() {
      NativeCustomTemplateAd var1;
      return (var1 = this.zzNb.zzfy()) instanceof zzpm ? ((zzpm)var1).zzex() : null;
   }

   public final boolean zzfu() {
      return this.zzNa instanceof InitializableMediationRewardedVideoAdAdapter;
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzaea var4, String var5) throws RemoteException {
      if (!(this.zzNa instanceof MediationRewardedVideoAdAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Initialize rewarded video adapter.");

         try {
            MediationRewardedVideoAdAdapter var6 = (MediationRewardedVideoAdAdapter)this.zzNa;
            zzvi var7 = null;
            Bundle var8 = null;
            Bundle var9 = this.zza((String)var5, (zzir)var2, (String)null);
            if (var2 != null) {
               HashSet var10 = var2.zzzP != null ? new HashSet(var2.zzzP) : null;
               var7 = new zzvi(var2.zzzN == -1L ? null : new Date(var2.zzzN), var2.zzzO, var10, var2.zzzV, var2.zzzQ, var2.zzzR, var2.zzAc);
               if (var2.zzzX != null) {
                  var8 = var2.zzzX.getBundle(var6.getClass().getName());
               }
            }

            var6.initialize((Context)zzn.zzE(var1), var7, var3, new zzaed(var4), var9, var8);
         } catch (Throwable var11) {
            zzajc.zzc("Could not initialize rewarded video adapter.", var11);
            throw new RemoteException();
         }
      }
   }

   public final void zza(IObjectWrapper var1, zzaea var2, List var3) throws RemoteException {
      if (!(this.zzNa instanceof InitializableMediationRewardedVideoAdAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not an InitializableMediationRewardedVideoAdAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Initialize rewarded video adapter.");

         try {
            InitializableMediationRewardedVideoAdAdapter var4 = (InitializableMediationRewardedVideoAdAdapter)this.zzNa;
            ArrayList var5 = new ArrayList();
            Iterator var6 = var3.iterator();

            while(var6.hasNext()) {
               String var7 = (String)var6.next();
               Bundle var8 = this.zza((String)var7, (zzir)null, (String)null);
               var5.add(var8);
            }

            var4.initialize((Context)zzn.zzE(var1), new zzaed(var2), var5);
         } catch (Throwable var9) {
            zzajc.zzc("Could not initialize rewarded video adapter.", var9);
            throw new RemoteException();
         }
      }
   }

   public final void zzc(zzir var1, String var2) throws RemoteException {
      this.zza((zzir)var1, (String)var2, (String)null);
   }

   public final void zza(zzir var1, String var2, String var3) throws RemoteException {
      if (!(this.zzNa instanceof MediationRewardedVideoAdAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Requesting rewarded video ad from adapter.");

         try {
            MediationRewardedVideoAdAdapter var4 = (MediationRewardedVideoAdAdapter)this.zzNa;
            HashSet var5 = var1.zzzP != null ? new HashSet(var1.zzzP) : null;
            zzvi var6 = new zzvi(var1.zzzN == -1L ? null : new Date(var1.zzzN), var1.zzzO, var5, var1.zzzV, var1.zzzQ, var1.zzzR, var1.zzAc);
            Bundle var7 = var1.zzzX != null ? var1.zzzX.getBundle(var4.getClass().getName()) : null;
            var4.loadAd(var6, this.zza(var2, var1, var3), var7);
         } catch (Throwable var8) {
            zzajc.zzc("Could not load rewarded video ad from adapter.", var8);
            throw new RemoteException();
         }
      }
   }

   public final void showVideo() throws RemoteException {
      if (!(this.zzNa instanceof MediationRewardedVideoAdAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Show rewarded video ad from adapter.");

         try {
            ((MediationRewardedVideoAdAdapter)this.zzNa).showVideo();
         } catch (Throwable var2) {
            zzajc.zzc("Could not show rewarded video ad from adapter.", var2);
            throw new RemoteException();
         }
      }
   }

   public final boolean isInitialized() throws RemoteException {
      if (!(this.zzNa instanceof MediationRewardedVideoAdAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Check if adapter is initialized.");

         try {
            return ((MediationRewardedVideoAdAdapter)this.zzNa).isInitialized();
         } catch (Throwable var2) {
            zzajc.zzc("Could not check if adapter is initialized.", var2);
            throw new RemoteException();
         }
      }
   }

   public final void setImmersiveMode(boolean var1) throws RemoteException {
      if (!(this.zzNa instanceof OnImmersiveModeUpdatedListener)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not an OnImmersiveModeUpdatedListener: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not an OnImmersiveModeUpdatedListener: ");
         }

         zzajc.zzaS(var10000);
      } else {
         try {
            ((OnImmersiveModeUpdatedListener)this.zzNa).onImmersiveModeUpdated(var1);
         } catch (Throwable var3) {
            zzajc.zzc("Could not set immersive mode.", var3);
         }
      }
   }

   public final zzks getVideoController() {
      if (!(this.zzNa instanceof zza)) {
         return null;
      } else {
         try {
            return ((zza)this.zzNa).getVideoController();
         } catch (Throwable var2) {
            zzajc.zzc("Could not get video controller.", var2);
            return null;
         }
      }
   }

   public final void showInterstitial() throws RemoteException {
      if (!(this.zzNa instanceof MediationInterstitialAdapter)) {
         String var10001 = String.valueOf(this.zzNa.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationInterstitialAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Showing interstitial from adapter.");

         try {
            ((MediationInterstitialAdapter)this.zzNa).showInterstitial();
         } catch (Throwable var2) {
            zzajc.zzc("Could not show interstitial from adapter.", var2);
            throw new RemoteException();
         }
      }
   }

   public final void destroy() throws RemoteException {
      try {
         this.zzNa.onDestroy();
      } catch (Throwable var2) {
         zzajc.zzc("Could not destroy adapter.", var2);
         throw new RemoteException();
      }
   }

   public final void pause() throws RemoteException {
      try {
         this.zzNa.onPause();
      } catch (Throwable var2) {
         zzajc.zzc("Could not pause adapter.", var2);
         throw new RemoteException();
      }
   }

   public final void resume() throws RemoteException {
      try {
         this.zzNa.onResume();
      } catch (Throwable var2) {
         zzajc.zzc("Could not resume adapter.", var2);
         throw new RemoteException();
      }
   }

   public final void zzk(IObjectWrapper var1) throws RemoteException {
      try {
         Context var2 = (Context)zzn.zzE(var1);
         ((OnContextChangedListener)this.zzNa).onContextChanged(var2);
      } catch (Throwable var3) {
         zzajc.zza("Could not inform adapter of changed context", var3);
      }
   }

   private final Bundle zza(String var1, zzir var2, String var3) throws RemoteException {
      String var10001 = String.valueOf(var1);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Server parameters: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Server parameters: ");
      }

      zzajc.zzaT(var10000);

      try {
         Bundle var4 = new Bundle();
         if (var1 != null) {
            JSONObject var5 = new JSONObject(var1);
            var4 = new Bundle();
            Iterator var6 = var5.keys();

            while(var6.hasNext()) {
               String var7 = (String)var6.next();
               var4.putString(var7, var5.getString(var7));
            }
         }

         if (this.zzNa instanceof AdMobAdapter) {
            var4.putString("adJson", var3);
            if (var2 != null) {
               var4.putInt("tagForChildDirectedTreatment", var2.zzzR);
            }
         }

         return var4;
      } catch (Throwable var8) {
         zzajc.zzc("Could not get Server Parameters Bundle.", var8);
         throw new RemoteException();
      }
   }
}
