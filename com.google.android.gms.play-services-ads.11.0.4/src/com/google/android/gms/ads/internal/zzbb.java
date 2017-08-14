package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.View;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzfh;
import com.google.android.gms.internal.zzfk;
import com.google.android.gms.internal.zzgs;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zznn;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zznu;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zznx;
import com.google.android.gms.internal.zzny;
import com.google.android.gms.internal.zznz;
import com.google.android.gms.internal.zzoa;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import com.google.android.gms.internal.zzxg;
import com.google.android.gms.internal.zzxx;
import com.google.android.gms.internal.zzyh;
import com.google.android.gms.internal.zzzn;
import java.util.List;

@zzzn
public final class zzbb extends zzd implements zznz {
   private final Object mLock = new Object();
   private zzaka zzuO;
   private boolean zzta;
   private zzyh zzuP;

   public zzbb(Context var1, zzv var2, zziv var3, String var4, zzuq var5, zzaje var6) {
      super(var1, var3, var4, var5, var6, var2);
   }

   public final boolean zza(zzir var1, zznb var2) {
      try {
         zzbb var10 = this;
         zzme var7 = zzmo.zzFu;
         if (((Boolean)zzbs.zzbL().zzd(var7)).booleanValue()) {
            Object var5 = this.mLock;
            synchronized(this.mLock) {
               var10.zzuP = new zzyh(var10.zzsP.zzqD, var10, var10.zzsP.zzvS, var10.zzsP.zzvT);
               var10.zzuP.zzgs();
               var10.zzuP.zzgt();
            }
         }
      } catch (Exception var9) {
         String var4 = "Error initializing webview.";
         if (zzajc.zzz(4)) {
            Log.i("Ads", var4, var9);
         }

         return false;
      }

      return super.zza(var1, var2);
   }

   @Nullable
   public final zzyh zzbi() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzuP;
      }
   }

   public final void zza(zzafg var1, zznb var2) {
      if (var1.zzvX != null) {
         this.zzsP.zzvX = var1.zzvX;
      }

      if (var1.errorCode != -2) {
         zzagz.zzZr.post(new zzbc(this, var1));
      } else {
         this.zzsP.zzwt = 0;
         zzbt var10000 = this.zzsP;
         zzbs.zzby();
         var10000.zzvW = zzxx.zza(this.zzsP.zzqD, this, var1, this.zzsP.zzvS, (zzaka)null, this.zzsX, this, var2);
         String var10001 = String.valueOf(this.zzsP.zzvW.getClass().getName());
         String var3;
         if (var10001.length() != 0) {
            var3 = "AdRenderer: ".concat(var10001);
         } else {
            String var10002 = new String;
            var3 = var10002;
            var10002.<init>("AdRenderer: ");
         }

         zzafr.zzaC(var3);
      }
   }

   protected final void zze(int var1) {
      zzme var2 = zzmo.zzFu;
      if (((Boolean)zzbs.zzbL().zzd(var2)).booleanValue()) {
         this.zzbn();
      }

      super.zze(var1);
   }

   protected final boolean zza(zzir var1, zzaff var2, boolean var3) {
      return this.zzsO.zzbo();
   }

   protected final boolean zza(zzaff var1, zzaff var2) {
      this.zzc((List)null);
      if (!this.zzsP.zzcc()) {
         throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
      } else {
         if (var2.zzTo) {
            zzme var7 = zzmo.zzFu;
            if (((Boolean)zzbs.zzbL().zzd(var7)).booleanValue()) {
               this.zzbn();
            }

            try {
               zzvc var11 = var2.zzMH != null ? var2.zzMH.zzfq() : null;
               zzvf var4 = var2.zzMH != null ? var2.zzMH.zzfr() : null;
               zzpj var5 = var2.zzMH != null ? var2.zzMH.zzfv() : null;
               if (var11 != null && this.zzsP.zzwf != null) {
                  zznq var12;
                  (var12 = new zznq(var11.getHeadline(), var11.getImages(), var11.getBody(), var11.zzeh() != null ? var11.zzeh() : null, var11.getCallToAction(), var11.getStarRating(), var11.getStore(), var11.getPrice(), (zznn)null, var11.getExtras(), var11.getVideoController(), (View)null)).zzb((zzny)(new zznx(this.zzsP.zzqD, this, this.zzsP.zzvS, var11, var12)));
                  this.zza(var12);
               } else if (var4 != null && this.zzsP.zzwg != null) {
                  zzns var6;
                  (var6 = new zzns(var4.getHeadline(), var4.getImages(), var4.getBody(), var4.zzem() != null ? var4.zzem() : null, var4.getCallToAction(), var4.getAdvertiser(), (zznn)null, var4.getExtras(), var4.getVideoController(), (View)null)).zzb((zzny)(new zznx(this.zzsP.zzqD, this, this.zzsP.zzvS, var4, var6)));
                  this.zza(var6);
               } else {
                  if (var5 == null || this.zzsP.zzwi == null || this.zzsP.zzwi.get(var5.getCustomTemplateId()) == null) {
                     zzafr.zzaT("No matching mapper/listener for retrieved native ad template.");
                     this.zze(0);
                     return false;
                  }

                  zzagz.zzZr.post(new zzbg(this, var5));
               }
            } catch (RemoteException var10) {
               zzafr.zzc("Failed to get native ad mapper", var10);
            }
         } else {
            zzoa var3 = var2.zzXT;
            if (var2.zzXT instanceof zzns && this.zzsP.zzwg != null) {
               this.zza((zzns)var2.zzXT);
            } else if (var3 instanceof zznq && this.zzsP.zzwf != null) {
               this.zza((zznq)var2.zzXT);
            } else {
               if (!(var3 instanceof zznu) || this.zzsP.zzwi == null || this.zzsP.zzwi.get(((zznu)var3).getCustomTemplateId()) == null) {
                  zzafr.zzaT("No matching listener for retrieved native ad template.");
                  this.zze(0);
                  return false;
               }

               String var9 = ((zznu)var3).getCustomTemplateId();
               zzagz.zzZr.post(new zzbf(this, var9, var2));
            }
         }

         return super.zza(var1, var2);
      }
   }

   public final void pause() {
      throw new IllegalStateException("Native Ad DOES NOT support pause().");
   }

   public final void resume() {
      throw new IllegalStateException("Native Ad DOES NOT support resume().");
   }

   private final void zza(zznq var1) {
      zzagz.zzZr.post(new zzbd(this, var1));
   }

   private final void zza(zzns var1) {
      zzagz.zzZr.post(new zzbe(this, var1));
   }

   protected final void zzas() {
      super.zzas();
      if (this.zzta) {
         zzme var1 = zzmo.zzFC;
         if (((Boolean)zzbs.zzbL().zzd(var1)).booleanValue()) {
            this.zzbj();
         }
      }

   }

   public final void zza(zzny var1) {
      if (this.zzsP.zzvY.zzXL != null) {
         zzfh var10000 = zzbs.zzbD().zzhG();
         zzaff var3 = this.zzsP.zzvY;
         zziv var10002 = this.zzsP.zzvX;
         var10000.zza(this.zzsP.zzvX, var3, (zzgs)(new zzfk(var1)), (com.google.android.gms.ads.internal.js.zzai)null);
      }

   }

   public final void zzbj() {
      if (this.zzsP.zzvY != null && this.zzuO != null) {
         zzbs.zzbD().zzhG().zza(this.zzsP.zzvX, this.zzsP.zzvY, (View)this.zzuO.getView(), this.zzuO);
         this.zzta = false;
      } else {
         this.zzta = true;
         zzafr.zzaT("Request to enable ActiveView before adState is available.");
      }
   }

   public final void zzbk() {
      this.zzta = false;
      if (this.zzsP.zzvY != null && this.zzuO != null) {
         zzbs.zzbD().zzhG().zzg(this.zzsP.zzvY);
      } else {
         zzafr.zzaT("Request to enable ActiveView before adState is available.");
      }
   }

   public final String getAdUnitId() {
      return this.zzsP.zzvR;
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

   public final SimpleArrayMap zzbl() {
      com.google.android.gms.common.internal.zzbo.zzcz("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
      return this.zzsP.zzwi;
   }

   public final void zzd(zzaka var1) {
      this.zzuO = var1;
   }

   public final void zzaO() {
      if (this.zzuO != null) {
         this.zzuO.destroy();
         this.zzuO = null;
      }

   }

   public final void zza(zznw var1) {
      if (this.zzuO != null) {
         this.zzuO.zzb(var1);
      }

   }

   public final void zzbm() {
      if (this.zzuO != null && this.zzuO.zziH() != null && this.zzsP.zzwj != null && this.zzsP.zzwj.zzIr != null) {
         this.zzuO.zziH().zzb(this.zzsP.zzwj.zzIr);
      }

   }

   public final boolean zzaP() {
      return this.zzsP.zzvY != null && this.zzsP.zzvY.zzTo && this.zzsP.zzvY.zzXN != null && this.zzsP.zzvY.zzXN.zzMm;
   }

   public final void zza(zzxg var1) {
      throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
   }

   public final void zza(zznh var1) {
      throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
   }

   public final void showInterstitial() {
      throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
   }

   public final void zzaC() {
      if (this.zzsP.zzvY != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzsP.zzvY.zzMI)) {
         this.zzau();
      } else {
         super.zzaC();
      }
   }

   public final void zzaH() {
      if (this.zzsP.zzvY != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzsP.zzvY.zzMI)) {
         this.zzat();
      } else {
         super.zzaH();
      }
   }

   private final void zzbn() {
      zzagz.zzb(new zzbh(this));
   }

   // $FF: synthetic method
   static Object zza(zzbb var0) {
      return var0.mLock;
   }
}
