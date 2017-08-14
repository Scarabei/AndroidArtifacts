package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zznn;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zznx;
import com.google.android.gms.internal.zzny;
import com.google.android.gms.internal.zznz;
import com.google.android.gms.internal.zzoa;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzud;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzut;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import com.google.android.gms.internal.zzxx;
import com.google.android.gms.internal.zzzn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@zzzn
public final class zzq extends zzd implements zznz {
   private boolean zzsu;
   private zzaff zztg;
   private boolean zzth = false;

   public zzq(Context var1, zzv var2, zziv var3, String var4, zzuq var5, zzaje var6) {
      super(var1, var3, var4, var5, var6, var2);
   }

   public final boolean zza(zzir var1) {
      if (this.zzsP.zzwn != null && this.zzsP.zzwn.size() == 1 && ((Integer)this.zzsP.zzwn.get(0)).intValue() == 2) {
         zzafr.e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
         this.zze(0);
         return false;
      } else {
         return this.zzsP.zzwm == null ? super.zza(var1) : super.zza(var1.zzzS == this.zzsu ? var1 : new zzir(var1.versionCode, var1.zzzN, var1.extras, var1.zzzO, var1.zzzP, var1.zzzQ, var1.zzzR, var1.zzzS || this.zzsu, var1.zzzT, var1.zzzU, var1.zzzV, var1.zzzW, var1.zzzX, var1.zzzY, var1.zzzZ, var1.zzAa, var1.zzAb, var1.zzAc));
      }
   }

   public final void zza(zzafg var1, zznb var2) {
      this.zztg = null;
      if (var1.errorCode != -2) {
         this.zztg = zza(var1, var1.errorCode);
      } else if (!var1.zzXY.zzTo) {
         zzafr.zzaT("partialAdState is not mediation");
         this.zztg = zza(var1, 0);
      }

      if (this.zztg != null) {
         zzagz.zzZr.post(new zzr(this));
      } else {
         if (var1.zzvX != null) {
            this.zzsP.zzvX = var1.zzvX;
         }

         this.zzsP.zzwt = 0;
         zzbt var10000 = this.zzsP;
         zzbs.zzby();
         var10000.zzvW = zzxx.zza(this.zzsP.zzqD, this, var1, this.zzsP.zzvS, (zzaka)null, this.zzsX, this, var2);
      }
   }

   private static zzaff zza(zzafg var0, int var1) {
      return new zzaff(var0.zzUj.zzSz, (zzaka)null, var0.zzXY.zzMa, var1, var0.zzXY.zzMb, var0.zzXY.zzTq, var0.zzXY.orientation, var0.zzXY.zzMg, var0.zzUj.zzSC, var0.zzXY.zzTo, (zzua)null, (zzut)null, (String)null, var0.zzXN, (zzud)null, var0.zzXY.zzTp, var0.zzvX, var0.zzXY.zzTn, var0.zzXR, var0.zzXS, var0.zzXY.zzTt, var0.zzXL, (zzoa)null, var0.zzXY.zzTD, var0.zzXY.zzTE, var0.zzXY.zzTE, var0.zzXY.zzTG, var0.zzXY.zzTH, (String)null, var0.zzXY.zzMd, var0.zzXY.zzTK, var0.zzXX);
   }

   protected final boolean zza(zzir var1, zzaff var2, boolean var3) {
      return false;
   }

   protected final boolean zza(@Nullable zzaff var1, zzaff var2) {
      if (!this.zzsP.zzcc()) {
         throw new IllegalStateException("AdLoader API does not support custom rendering.");
      } else if (!var2.zzTo) {
         this.zze(0);
         zzafr.zzaT("newState is not mediation.");
         return false;
      } else {
         if (var2.zzMG != null && var2.zzMG.zzfh()) {
            if (this.zzsP.zzcc() && this.zzsP.zzvU != null) {
               this.zzsP.zzvU.zzcf().zzaP(var2.zzTt);
            }

            boolean var10000;
            if (!super.zza(var1, var2)) {
               var10000 = false;
            } else if (this.zzsP.zzcc() && !this.zzc(var1, var2)) {
               this.zze(0);
               var10000 = false;
            } else {
               if (!this.zzsP.zzcd()) {
                  super.zza(var2, false);
               }

               var10000 = true;
            }

            if (!var10000) {
               return false;
            }

            this.zzth = true;
         } else {
            if (var2.zzMG == null || !var2.zzMG.zzfi()) {
               this.zze(0);
               zzafr.zzaT("Response is neither banner nor native.");
               return false;
            }

            if (!this.zzb(var1, var2)) {
               return false;
            }
         }

         this.zzd(new ArrayList(Arrays.asList(Integer.valueOf(2))));
         return true;
      }
   }

   private final boolean zzb(zzaff var1, zzaff var2) {
      this.zzc((List)null);
      if (!this.zzsP.zzcc()) {
         zzafr.zzaT("Native ad does not have custom rendering mode.");
         this.zze(0);
         return false;
      } else {
         try {
            zzvc var3 = var2.zzMH != null ? var2.zzMH.zzfq() : null;
            zzvf var4 = var2.zzMH != null ? var2.zzMH.zzfr() : null;
            zzpj var5 = var2.zzMH != null ? var2.zzMH.zzfv() : null;
            if (var3 != null && this.zzsP.zzwf != null) {
               zznq var10;
               (var10 = new zznq(var3.getHeadline(), var3.getImages(), var3.getBody(), var3.zzeh() != null ? var3.zzeh() : null, var3.getCallToAction(), var3.getStarRating(), var3.getStore(), var3.getPrice(), (zznn)null, var3.getExtras(), var3.getVideoController(), (View)null)).zzb((zzny)(new zznx(this.zzsP.zzqD, this, this.zzsP.zzvS, var3, var10)));
               zzagz.zzZr.post(new zzs(this, var10));
            } else if (var4 != null && this.zzsP.zzwg != null) {
               zzns var6;
               (var6 = new zzns(var4.getHeadline(), var4.getImages(), var4.getBody(), var4.zzem() != null ? var4.zzem() : null, var4.getCallToAction(), var4.getAdvertiser(), (zznn)null, var4.getExtras(), var4.getVideoController(), (View)null)).zzb((zzny)(new zznx(this.zzsP.zzqD, this, this.zzsP.zzvS, var4, var6)));
               zzagz.zzZr.post(new zzt(this, var6));
            } else {
               if (var5 == null || this.zzsP.zzwi == null || this.zzsP.zzwi.get(var5.getCustomTemplateId()) == null) {
                  zzafr.zzaT("No matching mapper/listener for retrieved native ad template.");
                  this.zze(0);
                  return false;
               }

               zzagz.zzZr.post(new zzu(this, var5));
            }
         } catch (RemoteException var9) {
            zzafr.zzc("Failed to get native ad mapper", var9);
            this.zze(0);
            return false;
         }

         return super.zza(var1, var2);
      }
   }

   public final void zza(zzny var1) {
      zzafr.zzaT("Unexpected call to AdLoaderManager method");
   }

   public final void zzaO() {
      zzafr.zzaT("Unexpected call to AdLoaderManager method");
   }

   public final void zza(zznw var1) {
      zzafr.zzaT("Unexpected call to AdLoaderManager method");
   }

   public final boolean zzaP() {
      return this.zzsP.zzvY != null && this.zzsP.zzvY.zzTo && this.zzsP.zzvY.zzXN != null && this.zzsP.zzvY.zzXN.zzMm;
   }

   private final boolean zzc(zzaff var1, zzaff var2) {
      View var3;
      if ((var3 = zzar.zzd(var2)) == null) {
         return false;
      } else {
         View var4;
         if ((var4 = this.zzsP.zzvU.getNextView()) != null) {
            if (var4 instanceof zzaka) {
               ((zzaka)var4).destroy();
            }

            this.zzsP.zzvU.removeView(var4);
         }

         if (!zzar.zze(var2)) {
            try {
               this.zzb(var3);
            } catch (Throwable var6) {
               zzbs.zzbD().zza(var6, "AdLoaderManager.swapBannerViews");
               zzafr.zzc("Could not add mediation view to view hierarchy.", var6);
               return false;
            }
         }

         if (this.zzsP.zzvU.getChildCount() > 1) {
            this.zzsP.zzvU.showNext();
         }

         if (var1 != null) {
            View var5;
            if ((var5 = this.zzsP.zzvU.getNextView()) != null) {
               this.zzsP.zzvU.removeView(var5);
            }

            this.zzsP.zzcb();
         }

         this.zzsP.zzvU.setMinimumWidth(this.zzam().widthPixels);
         this.zzsP.zzvU.setMinimumHeight(this.zzam().heightPixels);
         this.zzsP.zzvU.requestLayout();
         this.zzsP.zzvU.setVisibility(0);
         return true;
      }
   }

   public final void pause() {
      if (!this.zzth) {
         throw new IllegalStateException("Native Ad does not support pause().");
      } else {
         super.pause();
      }
   }

   public final void resume() {
      if (!this.zzth) {
         throw new IllegalStateException("Native Ad does not support resume().");
      } else {
         super.resume();
      }
   }

   public final void zzc(@Nullable List var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setNativeTemplates must be called on the main UI thread.");
      this.zzsP.zzwq = var1;
   }

   @Nullable
   public final zzpt zzs(String var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("getOnCustomClickListener must be called on the main UI thread.");
      return (zzpt)this.zzsP.zzwh.get(var1);
   }

   public final void setManualImpressionsEnabled(boolean var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setManualImpressionsEnabled must be called from the main thread.");
      this.zzsu = var1;
   }

   public final void zza(zznh var1) {
      throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
   }

   public final void showInterstitial() {
      throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
   }

   @Nullable
   public final zzks getVideoController() {
      return null;
   }

   public final void zzd(List var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setAllowedAdTypes must be called on the main UI thread.");
      this.zzsP.zzwn = var1;
   }

   protected final void zzas() {
      super.zzas();
      zzaff var1 = this.zzsP.zzvY;
      if (this.zzsP.zzvY != null && var1.zzMG != null && var1.zzMG.zzfh() && this.zzsP.zzwm != null) {
         try {
            this.zzsP.zzwm.zza(this, com.google.android.gms.dynamic.zzn.zzw(this.zzsP.zzqD));
            return;
         } catch (RemoteException var3) {
            zzafr.zzc("Could not call PublisherAdViewLoadedListener.onPublisherAdViewLoaded().", var3);
         }
      }

   }

   public final void zzaC() {
      if (this.zzsP.zzvY != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzsP.zzvY.zzMI) && this.zzsP.zzvY.zzMG != null && this.zzsP.zzvY.zzMG.zzfi()) {
         this.zzau();
      } else {
         super.zzaC();
      }
   }

   public final void zzaH() {
      if (this.zzsP.zzvY != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzsP.zzvY.zzMI) && this.zzsP.zzvY.zzMG != null && this.zzsP.zzvY.zzMG.zzfi()) {
         this.zzat();
      } else {
         super.zzaH();
      }
   }

   // $FF: synthetic method
   static zzaff zza(zzq var0) {
      return var0.zztg;
   }
}
