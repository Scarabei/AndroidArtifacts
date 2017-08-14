package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.Window;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.zzaai;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzaee;
import com.google.android.gms.internal.zzaeq;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzaev;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzakh;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzub;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzwv;
import com.google.android.gms.internal.zzzn;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzal extends zzi implements zzrm, zzrv {
   private transient boolean zzuf = false;
   private int zzug = -1;
   private boolean zzuh;
   private float zzui;
   private boolean zzuj;
   private final zzaev zzuk;
   private String zzul;
   private final String zzum;

   public zzal(Context var1, zziv var2, String var3, zzuq var4, zzaje var5, zzv var6) {
      super(var1, var2, var3, var4, var5, var6);
      this.zzuk = zzbs.zzbY().zzs(var1) ? new zzaev(var1, var3) : null;
      this.zzum = var2 != null && "reward_mb".equals(var2.zzAs) ? "/Rewarded" : "/Interstitial";
   }

   public final boolean zza(zzir var1, zznb var2) {
      if (this.zzsP.zzvY != null) {
         zzafr.zzaT("An interstitial is already loading. Aborting.");
         return false;
      } else {
         return super.zza((zzir)var1, (zznb)var2);
      }
   }

   public final void zza(zzafg var1, zznb var2) {
      zzme var6 = zzmo.zzDY;
      if (!((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
         super.zza(var1, var2);
      } else if (var1.errorCode != -2) {
         super.zza(var1, var2);
      } else {
         Bundle var3;
         boolean var4 = (var3 = var1.zzUj.zzSz.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) == null || !var3.containsKey("gw");
         boolean var5 = !var1.zzXY.zzTo;
         if (var4 && var5) {
            this.zzsP.zzvZ = zzb(var1);
         }

         super.zza(this.zzsP.zzvZ, var2);
      }
   }

   protected final zzaka zza(zzafg var1, @Nullable zzw var2, @Nullable zzaet var3) throws zzakm {
      zzaka var4;
      zzakb var10000 = (var4 = zzbs.zzbA().zza(this.zzsP.zzqD, this.zzsP.zzvX, false, false, this.zzsP.zzvS, this.zzsP.zzvT, this.zzsK, this, this.zzsS, var1.zzXX)).zziw();
      zzme var5 = zzmo.zzDn;
      var10000.zza(this, (com.google.android.gms.ads.internal.overlay.zzw)null, this, this, ((Boolean)zzbs.zzbL().zzd(var5)).booleanValue(), this, var2, (zzwv)null, var3);
      this.zza(var4);
      var4.zzaV(var1.zzUj.zzSM);
      var4.zziw().zza((String)"/reward", (zzrd)(new zzru(this)));
      return var4;
   }

   protected final boolean zza(zzir var1, zzaff var2, boolean var3) {
      if (this.zzsP.zzcc() && var2.zzPg != null) {
         zzbs.zzbB();
         zzahe.zzk(var2.zzPg);
      }

      return this.zzsO.zzbo();
   }

   public final boolean zza(@Nullable zzaff var1, zzaff var2) {
      if (!super.zza(var1, var2)) {
         return false;
      } else {
         if (!this.zzsP.zzcc() && this.zzsP.zzws != null && var2.zzXL != null) {
            this.zzsR.zza(this.zzsP.zzvX, var2, this.zzsP.zzws);
         }

         return true;
      }
   }

   public final void zzaB() {
      this.recordImpression();
      super.zzaB();
      zzakb var1;
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null && (var1 = this.zzsP.zzvY.zzPg.zziw()) != null) {
         var1.zziV();
      }

      if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
         if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null) {
            zzbs.zzbY().zze(this.zzsP.zzvY.zzPg.getContext(), this.zzul);
         }

         this.zzuk.zzu(true);
      }

   }

   public final void zzaA() {
      super.zzaA();
      this.zzsR.zzg(this.zzsP.zzvY);
      if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
         this.zzuk.zzu(false);
      }

   }

   protected final void zzap() {
      this.zzbb();
      super.zzap();
   }

   public final void zzc(boolean var1) {
      this.zzsP.zzur = var1;
   }

   public final void zza(boolean var1, float var2) {
      this.zzuh = var1;
      this.zzui = var2;
   }

   public final void showInterstitial() {
      com.google.android.gms.common.internal.zzbo.zzcz("showInterstitial must be called on the main UI thread.");
      if (zzbs.zzbY().zzs(this.zzsP.zzqD)) {
         this.zzul = zzbs.zzbY().zzt(this.zzsP.zzqD);
         String var10001 = String.valueOf(this.zzul);
         String var10002 = String.valueOf(this.zzum);
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            String var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }

         this.zzul = var10001;
      }

      if (this.zzsP.zzvY == null) {
         zzafr.zzaT("The interstitial has not loaded.");
      } else {
         zzme var5 = zzmo.zzEv;
         if (((Boolean)zzbs.zzbL().zzd(var5)).booleanValue()) {
            String var1 = this.zzsP.zzqD.getApplicationContext() != null ? this.zzsP.zzqD.getApplicationContext().getPackageName() : this.zzsP.zzqD.getPackageName();
            Bundle var2;
            if (!this.zzuf) {
               zzafr.zzaT("It is not recommended to show an interstitial before onAdLoaded completes.");
               (var2 = new Bundle()).putString("appid", var1);
               var2.putString("action", "show_interstitial_before_load_finish");
               this.zzb(var2);
            }

            zzbs.zzbz();
            if (!zzagz.zzJ(this.zzsP.zzqD)) {
               zzafr.zzaT("It is not recommended to show an interstitial when app is not in foreground.");
               (var2 = new Bundle()).putString("appid", var1);
               var2.putString("action", "show_interstitial_app_not_in_foreground");
               this.zzb(var2);
            }
         }

         if (!this.zzsP.zzcd()) {
            if (this.zzsP.zzvY.zzTo && this.zzsP.zzvY.zzMH != null) {
               try {
                  var5 = zzmo.zzDT;
                  if (((Boolean)zzbs.zzbL().zzd(var5)).booleanValue()) {
                     this.zzsP.zzvY.zzMH.setImmersiveMode(this.zzuj);
                  }

                  this.zzsP.zzvY.zzMH.showInterstitial();
               } catch (RemoteException var8) {
                  zzafr.zzc("Could not show interstitial.", var8);
                  this.zzbb();
               }
            } else if (this.zzsP.zzvY.zzPg == null) {
               zzafr.zzaT("The interstitial failed to load.");
            } else if (this.zzsP.zzvY.zzPg.zziA()) {
               zzafr.zzaT("The interstitial is already showing.");
            } else {
               this.zzsP.zzvY.zzPg.zzA(true);
               if (this.zzsP.zzvY.zzXL != null) {
                  this.zzsR.zza(this.zzsP.zzvX, this.zzsP.zzvY);
               }

               zzaff var9 = this.zzsP.zzvY;
               if (this.zzsP.zzvY.zzcn()) {
                  (new zzge(this.zzsP.zzqD, var9.zzPg.getView())).zza((zzgi)var9.zzPg);
               } else {
                  var9.zzPg.zziw().zza((zzakh)(new zzam(this, var9)));
               }

               Bitmap var10000;
               if (this.zzsP.zzur) {
                  zzbs.zzbz();
                  var10000 = zzagz.zzK(this.zzsP.zzqD);
               } else {
                  var10000 = null;
               }

               Bitmap var10 = var10000;
               this.zzug = zzbs.zzbU().zzb(var10);
               var5 = zzmo.zzFh;
               if (((Boolean)zzbs.zzbL().zzd(var5)).booleanValue() && var10 != null) {
                  (new zzan(this, this.zzug)).zzhL();
               } else {
                  zzap var11 = new zzap(this.zzsP.zzur, this.zzba(), false, 0.0F, -1, this.zzuj);
                  int var3;
                  if ((var3 = this.zzsP.zzvY.zzPg.getRequestedOrientation()) == -1) {
                     var3 = this.zzsP.zzvY.orientation;
                  }

                  AdOverlayInfoParcel var4 = new AdOverlayInfoParcel(this, this, this, this.zzsP.zzvY.zzPg, var3, this.zzsP.zzvT, this.zzsP.zzvY.zzTt, var11);
                  zzbs.zzbx();
                  Context var12 = this.zzsP.zzqD;
                  com.google.android.gms.ads.internal.overlay.zzu.zza(this.zzsP.zzqD, var4, true);
               }
            }
         }
      }
   }

   public final void setImmersiveMode(boolean var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setImmersiveMode must be called on the main UI thread.");
      this.zzuj = var1;
   }

   private final void zzb(Bundle var1) {
      zzbs.zzbz().zzb(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, "gmob-apps", var1, false);
   }

   protected final boolean zzba() {
      if (!(this.zzsP.zzqD instanceof Activity)) {
         return false;
      } else {
         Window var1;
         if ((var1 = ((Activity)this.zzsP.zzqD).getWindow()) != null && var1.getDecorView() != null) {
            Rect var2 = new Rect();
            Rect var3 = new Rect();
            var1.getDecorView().getGlobalVisibleRect(var2, (Point)null);
            var1.getDecorView().getWindowVisibleDisplayFrame(var3);
            return var2.bottom != 0 && var3.bottom != 0 && var2.top == var3.top;
         } else {
            return false;
         }
      }
   }

   protected final void zzas() {
      super.zzas();
      this.zzuf = true;
   }

   public final void zzbb() {
      zzbs.zzbU().zzb(this.zzug);
      if (this.zzsP.zzcc()) {
         this.zzsP.zzca();
         this.zzsP.zzvY = null;
         this.zzsP.zzur = false;
         this.zzuf = false;
      }

   }

   public final void zzbc() {
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzXQ != null) {
         zzbs.zzbz();
         zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzXQ);
      }

      this.zzav();
   }

   public final void zzb(zzaee var1) {
      if (this.zzsP.zzvY != null) {
         if (this.zzsP.zzvY.zzTF != null) {
            zzbs.zzbz();
            zzagz.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY.zzTF);
         }

         if (this.zzsP.zzvY.zzTD != null) {
            var1 = this.zzsP.zzvY.zzTD;
         }
      }

      this.zza(var1);
   }

   private static zzafg zzb(zzafg var0) {
      String var1;
      String var2;
      try {
         var1 = zzabt.zzb(var0.zzXY).toString();
         JSONObject var3;
         (var3 = new JSONObject()).put("pubid", var0.zzUj.zzvR);
         var2 = var3.toString();
      } catch (JSONException var8) {
         zzafr.zzb("Unable to generate ad state for an interstitial ad with pooling.", var8);
         return var0;
      }

      zzua var9 = new zzua(var1, (String)null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), (String)null, (String)null, Collections.emptyList(), Collections.emptyList(), var2, (String)null, Collections.emptyList(), Collections.emptyList(), (String)null, (String)null, (String)null, (List)null, (String)null, Collections.emptyList(), (String)null);
      zzaai var4 = var0.zzXY;
      List var10002 = Collections.singletonList(var9);
      zzme var7 = zzmo.zzEL;
      zzub var5 = new zzub(var10002, ((Long)zzbs.zzbL().zzd(var7)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), var4.zzMd, var4.zzMe, "", -1L, 0, 1, (String)null, 0, -1, -1L, false);
      zzaai var6 = new zzaai(var0.zzUj, var4.zzPi, var4.body, Collections.emptyList(), Collections.emptyList(), var4.zzTn, true, var4.zzTp, Collections.emptyList(), var4.zzMg, var4.orientation, var4.zzTr, var4.zzTs, var4.zzTt, var4.zzTu, var4.zzTv, (String)null, var4.zzTx, var4.zzAv, var4.zzSH, var4.zzTy, var4.zzTz, var4.zzTC, var4.zzAw, var4.zzAx, (zzaee)null, Collections.emptyList(), Collections.emptyList(), var4.zzTG, var4.zzTH, var4.zzSV, var4.zzSW, var4.zzMd, var4.zzMe, var4.zzTI, (zzaeq)null, var4.zzTK, var4.zzTL, var4.zzTh);
      return new zzafg(var0.zzUj, var6, var5, var0.zzvX, var0.errorCode, var0.zzXR, var0.zzXS, (JSONObject)null, var0.zzXX);
   }

   // $FF: synthetic method
   static boolean zza(zzal var0) {
      return var0.zzuh;
   }

   // $FF: synthetic method
   static float zzb(zzal var0) {
      return var0.zzui;
   }

   // $FF: synthetic method
   static boolean zzc(zzal var0) {
      return var0.zzuj;
   }
}
