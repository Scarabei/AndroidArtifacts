package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzag;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

@zzzn
public final class zzwd implements MediationInterstitialAdapter {
   private Activity zzNo;
   private zznl zzNp;
   private MediationInterstitialListener zzNq;
   private Uri mUri;

   public final void requestInterstitialAd(Context var1, MediationInterstitialListener var2, Bundle var3, MediationAdRequest var4, Bundle var5) {
      this.zzNq = var2;
      if (this.zzNq == null) {
         zzajc.zzaT("Listener not set for mediation. Returning.");
      } else if (!(var1 instanceof Activity)) {
         zzajc.zzaT("AdMobCustomTabs can only work with Activity context. Bailing out.");
         this.zzNq.onAdFailedToLoad(this, 0);
      } else if (!zznl.zzi(var1)) {
         zzajc.zzaT("Default browser does not support custom tabs. Bailing out.");
         this.zzNq.onAdFailedToLoad(this, 0);
      } else {
         String var6;
         if (TextUtils.isEmpty(var6 = var3.getString("tab_url"))) {
            zzajc.zzaT("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.zzNq.onAdFailedToLoad(this, 0);
         } else {
            this.zzNo = (Activity)var1;
            this.mUri = Uri.parse(var6);
            this.zzNp = new zznl();
            zzwe var7 = new zzwe(this);
            this.zzNp.zza((zznm)var7);
            this.zzNp.zzd(this.zzNo);
            this.zzNq.onAdLoaded(this);
         }
      }
   }

   public final void showInterstitial() {
      CustomTabsIntent var1;
      (var1 = (new Builder(this.zzNp.zzdY())).build()).intent.setData(this.mUri);
      zzc var2 = new zzc(var1.intent);
      AdOverlayInfoParcel var3 = new AdOverlayInfoParcel(var2, (zzim)null, new zzwf(this), (zzag)null, new zzaje(0, 0, false));
      zzagz.zzZr.post(new zzwg(this, var3));
      com.google.android.gms.ads.internal.zzbs.zzbD().zzz(false);
   }

   public final void onDestroy() {
      zzajc.zzaC("Destroying AdMobCustomTabsAdapter adapter.");

      try {
         this.zzNp.zzc(this.zzNo);
      } catch (Exception var2) {
         zzajc.zzb("Exception while unbinding from CustomTabsService.", var2);
      }
   }

   public final void onPause() {
      zzajc.zzaC("Pausing AdMobCustomTabsAdapter adapter.");
   }

   public final void onResume() {
      zzajc.zzaC("Resuming AdMobCustomTabsAdapter adapter.");
   }

   // $FF: synthetic method
   static MediationInterstitialListener zza(zzwd var0) {
      return var0.zzNq;
   }

   // $FF: synthetic method
   static Activity zzb(zzwd var0) {
      return var0.zzNo;
   }

   // $FF: synthetic method
   static zznl zzc(zzwd var0) {
      return var0.zzNp;
   }
}
