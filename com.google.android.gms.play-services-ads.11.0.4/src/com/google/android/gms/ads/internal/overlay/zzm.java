package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzwv;
import com.google.android.gms.internal.zzwy;
import com.google.android.gms.internal.zzzn;
import java.util.Collections;

@zzzn
public final class zzm extends zzwy implements zzaj {
   private static int zzOF = Color.argb(0, 0, 0, 0);
   private final Activity mActivity;
   AdOverlayInfoParcel zzOG;
   private zzaka zzJH;
   private zzr zzOH;
   private zzae zzOI;
   private boolean zzOJ = false;
   private FrameLayout zzOK;
   private CustomViewCallback zzOL;
   private boolean zzOM = false;
   private boolean zzON = false;
   private zzq zzOO;
   private boolean zzOP = false;
   private int zzOQ = 0;
   private final Object zzOR = new Object();
   private Runnable zzOS;
   private boolean zzOT;
   private boolean zzOU;
   private boolean zzOV = false;
   private boolean zzOW = false;
   private boolean zzOX = true;

   public zzm(Activity var1) {
      this.mActivity = var1;
   }

   public final void close() {
      this.zzOQ = 2;
      this.mActivity.finish();
   }

   public final void zzfI() {
      if (this.zzOG != null && this.zzOJ) {
         this.setRequestedOrientation(this.zzOG.orientation);
      }

      if (this.zzOK != null) {
         this.mActivity.setContentView(this.zzOO);
         this.zzOU = true;
         this.zzOK.removeAllViews();
         this.zzOK = null;
      }

      if (this.zzOL != null) {
         this.zzOL.onCustomViewHidden();
         this.zzOL = null;
      }

      this.zzOJ = false;
   }

   public final void zzfJ() {
      this.zzOQ = 1;
      this.mActivity.finish();
   }

   public final void onBackPressed() {
      this.zzOQ = 0;
   }

   public final boolean zzfK() {
      this.zzOQ = 0;
      if (this.zzJH == null) {
         return true;
      } else {
         boolean var1;
         if (!(var1 = this.zzJH.zziC())) {
            this.zzJH.zza("onbackblocked", Collections.emptyMap());
         }

         return var1;
      }
   }

   public final void onCreate(Bundle var1) {
      this.mActivity.requestWindowFeature(1);
      this.zzOM = var1 != null ? var1.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;

      try {
         this.zzOG = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
         if (this.zzOG == null) {
            throw new zzp("Could not get info for ad overlay.");
         } else {
            if (this.zzOG.zzvT.zzaaP > 7500000) {
               this.zzOQ = 3;
            }

            if (this.mActivity.getIntent() != null) {
               this.zzOX = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }

            if (this.zzOG.zzPo != null) {
               this.zzON = this.zzOG.zzPo.zzur;
            } else {
               this.zzON = false;
            }

            zzme var2 = zzmo.zzFh;
            if (((Boolean)zzbs.zzbL().zzd(var2)).booleanValue() && this.zzON && this.zzOG.zzPo.zzuw != -1) {
               (new zzs(this, (zzn)null)).zzhL();
            }

            if (var1 == null) {
               if (this.zzOG.zzPf != null && this.zzOX) {
                  this.zzOG.zzPf.zzaB();
               }

               if (this.zzOG.zzPm != 1 && this.zzOG.zzPe != null) {
                  this.zzOG.zzPe.onAdClicked();
               }
            }

            this.zzOO = new zzq(this.mActivity, this.zzOG.zzPn, this.zzOG.zzvT.zzaP);
            this.zzOO.setId(1000);
            switch(this.zzOG.zzPm) {
            case 1:
               this.zzs(false);
               return;
            case 2:
               this.zzOH = new zzr(this.zzOG.zzPg);
               this.zzs(false);
               return;
            case 3:
               this.zzs(true);
               return;
            case 4:
               if (this.zzOM) {
                  this.zzOQ = 3;
                  this.mActivity.finish();
                  return;
               } else {
                  zzbs.zzbw();
                  if (!zza.zza(this.mActivity, (zzc)this.zzOG.zzPd, this.zzOG.zzPl)) {
                     this.zzOQ = 3;
                     this.mActivity.finish();
                     return;
                  }

                  return;
               }
            default:
               throw new zzp("Could not determine ad overlay type.");
            }
         }
      } catch (zzp var3) {
         zzafr.zzaT(var3.getMessage());
         this.zzOQ = 3;
         this.mActivity.finish();
      }
   }

   public final void onRestart() {
   }

   public final void onStart() {
      zzme var1 = zzmo.zzGA;
      if (((Boolean)zzbs.zzbL().zzd(var1)).booleanValue()) {
         if (this.zzJH != null && !this.zzJH.isDestroyed()) {
            zzbs.zzbB();
            zzahe.zzl(this.zzJH);
            return;
         }

         zzafr.zzaT("The webview does not exist. Ignoring action.");
      }

   }

   public final void onResume() {
      if (this.zzOG != null && this.zzOG.zzPm == 4) {
         if (this.zzOM) {
            this.zzOQ = 3;
            this.mActivity.finish();
         } else {
            this.zzOM = true;
         }
      }

      if (this.zzOG.zzPf != null) {
         this.zzOG.zzPf.onResume();
      }

      zzme var1 = zzmo.zzGA;
      if (!((Boolean)zzbs.zzbL().zzd(var1)).booleanValue()) {
         if (this.zzJH != null && !this.zzJH.isDestroyed()) {
            zzbs.zzbB();
            zzahe.zzl(this.zzJH);
            return;
         }

         zzafr.zzaT("The webview does not exist. Ignoring action.");
      }

   }

   public final void onPause() {
      this.zzfI();
      if (this.zzOG.zzPf != null) {
         this.zzOG.zzPf.onPause();
      }

      zzme var1 = zzmo.zzGA;
      if (!((Boolean)zzbs.zzbL().zzd(var1)).booleanValue() && this.zzJH != null && (!this.mActivity.isFinishing() || this.zzOH == null)) {
         zzbs.zzbB();
         zzahe.zzk(this.zzJH);
      }

      this.zzfM();
   }

   public final void onActivityResult(int var1, int var2, Intent var3) {
   }

   public final void zzo(IObjectWrapper var1) {
      zzme var3 = zzmo.zzGz;
      if (((Boolean)zzbs.zzbL().zzd(var3)).booleanValue() && com.google.android.gms.common.util.zzq.isAtLeastN()) {
         Configuration var2 = (Configuration)com.google.android.gms.dynamic.zzn.zzE(var1);
         zzbs.zzbz();
         if (zzagz.zza(this.mActivity, var2)) {
            this.mActivity.getWindow().addFlags(1024);
            this.mActivity.getWindow().clearFlags(2048);
            return;
         }

         this.mActivity.getWindow().addFlags(2048);
         this.mActivity.getWindow().clearFlags(1024);
      }

   }

   public final void onSaveInstanceState(Bundle var1) {
      var1.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzOM);
   }

   public final void onStop() {
      zzme var1 = zzmo.zzGA;
      if (((Boolean)zzbs.zzbL().zzd(var1)).booleanValue() && this.zzJH != null && (!this.mActivity.isFinishing() || this.zzOH == null)) {
         zzbs.zzbB();
         zzahe.zzk(this.zzJH);
      }

      this.zzfM();
   }

   public final void onDestroy() {
      if (this.zzJH != null) {
         this.zzOO.removeView(this.zzJH.getView());
      }

      this.zzfM();
   }

   private final void zzr(boolean var1) {
      zzme var6 = zzmo.zzGB;
      int var2 = ((Integer)zzbs.zzbL().zzd(var6)).intValue();
      zzaf var3;
      (var3 = new zzaf()).size = 50;
      var3.paddingLeft = var1 ? var2 : 0;
      var3.paddingRight = var1 ? 0 : var2;
      var3.paddingTop = 0;
      var3.paddingBottom = var2;
      this.zzOI = new zzae(this.mActivity, var3, this);
      LayoutParams var4;
      (var4 = new LayoutParams(-2, -2)).addRule(10);
      int var5 = var1 ? 11 : 9;
      var4.addRule(var5);
      this.zzOI.zza(var1, this.zzOG.zzPj);
      this.zzOO.addView(this.zzOI, var4);
   }

   public final void zzaa() {
      this.zzOU = true;
   }

   public final void zza(boolean var1, boolean var2) {
      if (this.zzOI != null) {
         this.zzOI.zza(var1, var2);
      }

   }

   public final void zzfL() {
      this.zzOO.removeView(this.zzOI);
      this.zzr(true);
   }

   public final void setRequestedOrientation(int var1) {
      this.mActivity.setRequestedOrientation(var1);
   }

   public final void zza(View var1, CustomViewCallback var2) {
      this.zzOK = new FrameLayout(this.mActivity);
      this.zzOK.setBackgroundColor(-16777216);
      this.zzOK.addView(var1, -1, -1);
      this.mActivity.setContentView(this.zzOK);
      this.zzOU = true;
      this.zzOL = var2;
      this.zzOJ = true;
   }

   private final void zzs(boolean var1) throws zzp {
      if (!this.zzOU) {
         this.mActivity.requestWindowFeature(1);
      }

      Window var2;
      if ((var2 = this.mActivity.getWindow()) == null) {
         throw new zzp("Invalid activity, no window available.");
      } else {
         boolean var3 = true;
         zzme var8;
         if (com.google.android.gms.common.util.zzq.isAtLeastN()) {
            var8 = zzmo.zzGz;
            if (((Boolean)zzbs.zzbL().zzd(var8)).booleanValue()) {
               zzbs.zzbz();
               var3 = zzagz.zza(this.mActivity, this.mActivity.getResources().getConfiguration());
            }
         }

         boolean var4 = this.zzOG.zzPo != null && this.zzOG.zzPo.zzus;
         if ((!this.zzON || var4) && var3) {
            var2.setFlags(1024, 1024);
            var8 = zzmo.zzDT;
            if (((Boolean)zzbs.zzbL().zzd(var8)).booleanValue() && com.google.android.gms.common.util.zzq.zzsc() && this.zzOG.zzPo != null && this.zzOG.zzPo.zzux) {
               var2.getDecorView().setSystemUiVisibility(4098);
            }
         }

         zzakb var5;
         boolean var6 = (var5 = this.zzOG.zzPg.zziw()) != null ? var5.zzcn() : false;
         this.zzOP = false;
         if (var6) {
            if (this.zzOG.orientation == zzbs.zzbB().zzhT()) {
               this.zzOP = this.mActivity.getResources().getConfiguration().orientation == 1;
            } else if (this.zzOG.orientation == zzbs.zzbB().zzhU()) {
               this.zzOP = this.mActivity.getResources().getConfiguration().orientation == 2;
            }
         }

         boolean var7 = this.zzOP;
         zzafr.zzaC((new StringBuilder(46)).append("Delay onShow to next orientation change: ").append(var7).toString());
         this.setRequestedOrientation(this.zzOG.orientation);
         if (zzbs.zzbB().zza(var2)) {
            zzafr.zzaC("Hardware acceleration on the AdActivity window enabled.");
         }

         if (!this.zzON) {
            this.zzOO.setBackgroundColor(-16777216);
         } else {
            this.zzOO.setBackgroundColor(zzOF);
         }

         this.mActivity.setContentView(this.zzOO);
         this.zzOU = true;
         if (var1) {
            try {
               this.zzJH = zzbs.zzbA().zza(this.mActivity, this.zzOG.zzPg.zzam(), true, var6, (zzcu)null, this.zzOG.zzvT, (zznb)null, (zzbl)null, this.zzOG.zzPg.zzak(), zzig.zzde());
            } catch (Exception var9) {
               throw new zzp("Could not obtain webview for the overlay.");
            }

            this.zzJH.zziw().zza((zzim)null, (zzw)null, this.zzOG.zzPh, this.zzOG.zzPl, true, (zzrm)null, this.zzOG.zzPg.zziw().zziO(), (zzwv)null, (zzaet)null);
            this.zzJH.zziw().zza((zzakf)(new zzn(this)));
            if (this.zzOG.url != null) {
               this.zzJH.loadUrl(this.zzOG.url);
            } else {
               if (this.zzOG.zzPk == null) {
                  throw new zzp("No URL or HTML to display in ad overlay.");
               }

               this.zzJH.loadDataWithBaseURL(this.zzOG.zzPi, this.zzOG.zzPk, "text/html", "UTF-8", (String)null);
            }

            if (this.zzOG.zzPg != null) {
               this.zzOG.zzPg.zzc(this);
            }
         } else {
            this.zzJH = this.zzOG.zzPg;
            this.zzJH.setContext(this.mActivity);
         }

         this.zzJH.zzb(this);
         ViewParent var10;
         if ((var10 = this.zzJH.getParent()) != null && var10 instanceof ViewGroup) {
            ((ViewGroup)var10).removeView(this.zzJH.getView());
         }

         if (this.zzON) {
            this.zzJH.zziN();
         }

         this.zzOO.addView(this.zzJH.getView(), -1, -1);
         if (!var1 && !this.zzOP) {
            this.zzfP();
         }

         this.zzr(var6);
         if (this.zzJH.zzix()) {
            this.zza(var6, true);
         }

      }
   }

   private final void zzfM() {
      if (this.mActivity.isFinishing() && !this.zzOV) {
         this.zzOV = true;
         if (this.zzJH != null) {
            int var4 = this.zzOQ;
            this.zzJH.zzA(var4);
            Object var1 = this.zzOR;
            synchronized(this.zzOR) {
               if (!this.zzOT && this.zzJH.zziI()) {
                  this.zzOS = new zzo(this);
                  zzme var3 = zzmo.zzDS;
                  zzagz.zzZr.postDelayed(this.zzOS, ((Long)zzbs.zzbL().zzd(var3)).longValue());
                  return;
               }
            }
         }

         this.zzfN();
      }
   }

   final void zzfN() {
      if (!this.zzOW) {
         this.zzOW = true;
         if (this.zzJH != null) {
            this.zzOO.removeView(this.zzJH.getView());
            if (this.zzOH != null) {
               this.zzJH.setContext(this.zzOH.zzqD);
               this.zzJH.zzA(false);
               this.zzOH.parent.addView(this.zzJH.getView(), this.zzOH.index, this.zzOH.zzPa);
               this.zzOH = null;
            } else if (this.mActivity.getApplicationContext() != null) {
               this.zzJH.setContext(this.mActivity.getApplicationContext());
            }

            this.zzJH = null;
         }

         if (this.zzOG != null && this.zzOG.zzPf != null) {
            this.zzOG.zzPf.zzaA();
         }

      }
   }

   public final void zzfO() {
      if (this.zzOP) {
         this.zzOP = false;
         this.zzfP();
      }

   }

   private final void zzfP() {
      this.zzJH.zzfP();
   }

   public final void zzfQ() {
      this.zzOO.zzOZ = true;
   }

   public final void zzfR() {
      Object var1 = this.zzOR;
      synchronized(this.zzOR) {
         this.zzOT = true;
         if (this.zzOS != null) {
            zzagz.zzZr.removeCallbacks(this.zzOS);
            zzagz.zzZr.post(this.zzOS);
         }

      }
   }

   // $FF: synthetic method
   static Activity zza(zzm var0) {
      return var0.mActivity;
   }
}
