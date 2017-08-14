package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzaev;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzajv;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzakh;
import com.google.android.gms.internal.zzakj;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zzaks;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import com.google.android.gms.internal.zzzn;
import java.lang.ref.WeakReference;
import java.util.List;

@zzzn
public final class zzx extends zzi implements OnGlobalLayoutListener, OnScrollChangedListener {
   private boolean zzsu;
   private WeakReference zzts = new WeakReference((Object)null);

   public zzx(Context var1, zziv var2, String var3, zzuq var4, zzaje var5, zzv var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   protected final zzaka zza(zzafg var1, @Nullable zzw var2, @Nullable zzaet var3) throws zzakm {
      if (this.zzsP.zzvX.zzAu == null && this.zzsP.zzvX.zzAw) {
         zzbt var10000 = this.zzsP;
         zziv var10001;
         if (var1.zzXY.zzAw) {
            var10001 = this.zzsP.zzvX;
         } else {
            String var6 = var1.zzXY.zzTr;
            AdSize var7;
            if (var1.zzXY.zzTr != null) {
               String[] var8;
               (var8 = var6.split("[xX]"))[0] = var8[0].trim();
               var8[1] = var8[1].trim();
               int var9 = Integer.parseInt(var8[0]);
               int var10 = Integer.parseInt(var8[1]);
               var7 = new AdSize(var9, var10);
            } else {
               var7 = this.zzsP.zzvX.zzdl();
            }

            var10001 = new zziv(this.zzsP.zzqD, var7);
         }

         var10000.zzvX = var10001;
      }

      return super.zza(var1, var2, var3);
   }

   public final boolean zza(zzir var1) {
      return super.zza((zzir)(var1.zzzS == this.zzsu ? var1 : new zzir(var1.versionCode, var1.zzzN, var1.extras, var1.zzzO, var1.zzzP, var1.zzzQ, var1.zzzR, var1.zzzS || this.zzsu, var1.zzzT, var1.zzzU, var1.zzzV, var1.zzzW, var1.zzzX, var1.zzzY, var1.zzzZ, var1.zzAa, var1.zzAb, var1.zzAc)));
   }

   public final boolean zza(@Nullable zzaff var1, zzaff var2) {
      if (!super.zza(var1, var2)) {
         return false;
      } else if (this.zzsP.zzcc() && !this.zzd(var1, var2)) {
         this.zze(0);
         return false;
      } else {
         zzakb var4;
         if (var2.zzTG) {
            this.zzc(var2);
            zzbs.zzbX();
            zzajv.zza(this.zzsP.zzvU, (OnGlobalLayoutListener)this);
            zzbs.zzbX();
            zzajv.zza(this.zzsP.zzvU, (OnScrollChangedListener)this);
            if (!var2.zzXM) {
               zzy var3 = new zzy(this);
               zzakb var10000 = var2.zzPg != null ? var2.zzPg.zziw() : null;
               var4 = var10000;
               if (var10000 != null) {
                  var4.zza((zzakj)(new zzz(this, var2, var3)));
               }
            }
         } else {
            label64: {
               if (this.zzsP.zzcd()) {
                  zzme var5 = zzmo.zzFo;
                  if (!((Boolean)zzbs.zzbL().zzd(var5)).booleanValue()) {
                     break label64;
                  }
               }

               this.zza(var2, false);
            }
         }

         if (var2.zzPg != null) {
            zzaks var8 = var2.zzPg.zziH();
            if ((var4 = var2.zzPg.zziw()) != null) {
               var4.zziV();
            }

            if (this.zzsP.zzwk != null && var8 != null) {
               var8.zzb(this.zzsP.zzwk);
            }
         }

         if (this.zzsP.zzcc()) {
            if (var2.zzPg != null) {
               if (var2.zzXL != null) {
                  this.zzsR.zza(this.zzsP.zzvX, var2);
               }

               zzge var7 = new zzge(this.zzsP.zzqD, var2.zzPg.getView());
               if (zzbs.zzbY().zzq(this.zzsP.zzqD)) {
                  var7.zza((zzgi)(new zzaev(this.zzsP.zzqD, this.zzsP.zzvR)));
               }

               if (var2.zzcn()) {
                  var7.zza((zzgi)var2.zzPg);
               } else {
                  var2.zzPg.zziw().zza((zzakh)(new zzaa(this, var7, var2)));
               }
            }
         } else if (this.zzsP.zzws != null && var2.zzXL != null) {
            this.zzsR.zza(this.zzsP.zzvX, var2, this.zzsP.zzws);
         }

         return true;
      }
   }

   private final boolean zzd(@Nullable zzaff var1, zzaff var2) {
      View var3;
      if (var2.zzTo) {
         if ((var3 = zzar.zzd(var2)) == null) {
            zzafr.zzaT("Could not get mediation view");
            return false;
         }

         View var4;
         if ((var4 = this.zzsP.zzvU.getNextView()) != null) {
            if (var4 instanceof zzaka) {
               ((zzaka)var4).destroy();
            }

            this.zzsP.zzvU.removeView(var4);
         }

         if (!zzar.zze(var2)) {
            try {
               if (zzbs.zzbY().zzq(this.zzsP.zzqD)) {
                  (new zzge(this.zzsP.zzqD, var3)).zza((zzgi)(new zzaev(this.zzsP.zzqD, this.zzsP.zzvR)));
               }

               this.zzb(var3);
            } catch (Throwable var6) {
               zzbs.zzbD().zza(var6, "BannerAdManager.swapViews");
               zzafr.zzc("Could not add mediation view to view hierarchy.", var6);
               return false;
            }
         }
      } else if (var2.zzXP != null && var2.zzPg != null) {
         var2.zzPg.zza(var2.zzXP);
         this.zzsP.zzvU.removeAllViews();
         this.zzsP.zzvU.setMinimumWidth(var2.zzXP.widthPixels);
         this.zzsP.zzvU.setMinimumHeight(var2.zzXP.heightPixels);
         this.zzb(var2.zzPg.getView());
      }

      if (this.zzsP.zzvU.getChildCount() > 1) {
         this.zzsP.zzvU.showNext();
      }

      if (var1 != null) {
         if ((var3 = this.zzsP.zzvU.getNextView()) instanceof zzaka) {
            ((zzaka)var3).zza(this.zzsP.zzqD, this.zzsP.zzvX, this.zzsK);
         } else if (var3 != null) {
            this.zzsP.zzvU.removeView(var3);
         }

         this.zzsP.zzcb();
      }

      this.zzsP.zzvU.setVisibility(0);
      return true;
   }

   protected final boolean zzaz() {
      boolean var1 = true;
      zzbs.zzbz();
      if (!zzagz.zzc(this.zzsP.zzqD, this.zzsP.zzqD.getPackageName(), "android.permission.INTERNET")) {
         zzji.zzds().zza(this.zzsP.zzvU, this.zzsP.zzvX, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
         var1 = false;
      }

      zzbs.zzbz();
      if (!zzagz.zzD(this.zzsP.zzqD)) {
         zzji.zzds().zza(this.zzsP.zzvU, this.zzsP.zzvX, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
         var1 = false;
      }

      if (!var1 && this.zzsP.zzvU != null) {
         this.zzsP.zzvU.setVisibility(0);
      }

      return var1;
   }

   public final void setManualImpressionsEnabled(boolean var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setManualImpressionsEnabled must be called from the main thread.");
      this.zzsu = var1;
   }

   public final void showInterstitial() {
      throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
   }

   public final void onGlobalLayout() {
      this.zzc(this.zzsP.zzvY);
   }

   public final void onScrollChanged() {
      this.zzc(this.zzsP.zzvY);
   }

   protected final void zza(@Nullable zzaff var1, boolean var2) {
      super.zza(var1, var2);
      if (zzar.zze(var1)) {
         zzab var4 = new zzab(this);
         zzaff var3 = var1;
         if (var1 != null && zzar.zze(var1)) {
            zzaka var5 = var1.zzPg;
            View var10000 = var1.zzPg != null ? var5.getView() : null;
            View var6 = var10000;
            if (var10000 == null) {
               zzafr.zzaT("AdWebView is null");
               return;
            }

            try {
               List var7;
               if ((var7 = var3.zzMG != null ? var3.zzMG.zzLV : null) != null && !var7.isEmpty()) {
                  zzvc var8 = var3.zzMH != null ? var3.zzMH.zzfq() : null;
                  zzvf var9 = var3.zzMH != null ? var3.zzMH.zzfr() : null;
                  if (var7.contains("2") && var8 != null) {
                     var8.zzm(com.google.android.gms.dynamic.zzn.zzw(var6));
                     if (!var8.getOverrideImpressionRecording()) {
                        var8.recordImpression();
                     }

                     var5.zziw().zza("/nativeExpressViewClicked", zzar.zza((zzvc)var8, (zzvf)null, (zzab)var4));
                     return;
                  }

                  if (var7.contains("1") && var9 != null) {
                     var9.zzm(com.google.android.gms.dynamic.zzn.zzw(var6));
                     if (!var9.getOverrideImpressionRecording()) {
                        var9.recordImpression();
                     }

                     var5.zziw().zza("/nativeExpressViewClicked", zzar.zza((zzvc)null, (zzvf)var9, (zzab)var4));
                     return;
                  }

                  zzafr.zzaT("No matching template id and mapper");
                  return;
               }

               zzafr.zzaT("No template ids present in mediation response");
               return;
            } catch (RemoteException var10) {
               zzafr.zzc("Error occurred while recording impression and registering for clicks", var10);
            }
         }
      }

   }

   final void zzc(@Nullable zzaff var1) {
      if (var1 != null) {
         if (!var1.zzXM) {
            if (this.zzsP.zzvU != null && zzbs.zzbz().zza((View)this.zzsP.zzvU, (Context)this.zzsP.zzqD) && this.zzsP.zzvU.getGlobalVisibleRect(new Rect(), (Point)null)) {
               if (var1 != null && var1.zzPg != null && var1.zzPg.zziw() != null) {
                  var1.zzPg.zziw().zza((zzakj)null);
               }

               this.zza(var1, false);
               var1.zzXM = true;
            }
         }
      }
   }

   @Nullable
   public final zzks getVideoController() {
      com.google.android.gms.common.internal.zzbo.zzcz("getVideoController must be called from the main thread.");
      return this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null ? this.zzsP.zzvY.zzPg.zziH() : null;
   }
}
