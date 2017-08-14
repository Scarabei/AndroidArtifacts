package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzoa;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzud;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzut;
import com.google.android.gms.internal.zzwv;
import com.google.android.gms.internal.zzxx;
import com.google.android.gms.internal.zzzn;

@zzzn
public class zzi extends zzd implements zzag, zzwv {
   private boolean zzta;

   public zzi(Context var1, zziv var2, String var3, zzuq var4, zzaje var5, zzv var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   protected zzaka zza(zzafg var1, @Nullable zzw var2, @Nullable zzaet var3) throws zzakm {
      zzaka var4 = null;
      View var5;
      if ((var5 = this.zzsP.zzvU.getNextView()) instanceof zzaka) {
         var4 = (zzaka)var5;
         zzme var6 = zzmo.zzDE;
         if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
            zzafr.zzaC("Reusing webview...");
            var4.zza(this.zzsP.zzqD, this.zzsP.zzvX, this.zzsK);
         } else {
            var4.destroy();
            var4 = null;
         }
      }

      if (var4 == null) {
         if (var5 != null) {
            this.zzsP.zzvU.removeView(var5);
         }

         var4 = zzbs.zzbA().zza(this.zzsP.zzqD, this.zzsP.zzvX, false, false, this.zzsP.zzvS, this.zzsP.zzvT, this.zzsK, this, this.zzsS, var1.zzXX);
         if (this.zzsP.zzvX.zzAu == null) {
            this.zzb(var4.getView());
         }
      }

      var4.zziw().zza(this, this, this, this, false, (zzrm)null, var2, this, var3);
      this.zza((com.google.android.gms.ads.internal.js.zzai)var4);
      var4.zzaV(var1.zzUj.zzSM);
      return var4;
   }

   protected final void zza(com.google.android.gms.ads.internal.js.zzai var1) {
      var1.zza("/trackActiveViewUnit", (zzrd)(new zzj(this)));
   }

   protected void zzas() {
      super.zzas();
      if (this.zzta) {
         zzme var1 = zzmo.zzFC;
         if (((Boolean)zzbs.zzbL().zzd(var1)).booleanValue()) {
            this.zza(this.zzsP.zzvY.zzPg);
         }
      }

   }

   final void zza(zzaka var1) {
      if (this.zzsP.zzvY != null) {
         this.zzsR.zza(this.zzsP.zzvX, this.zzsP.zzvY, (View)var1.getView(), var1);
         this.zzta = false;
      } else {
         this.zzta = true;
         zzafr.zzaT("Request to enable ActiveView before adState is available.");
      }
   }

   protected void zza(zzafg var1, zznb var2) {
      if (var1.errorCode != -2) {
         zzagz.zzZr.post(new zzk(this, var1));
      } else {
         if (var1.zzvX != null) {
            this.zzsP.zzvX = var1.zzvX;
         }

         if (var1.zzXY.zzTo && !var1.zzXY.zzAx) {
            this.zzsP.zzwt = 0;
            zzbt var10000 = this.zzsP;
            zzbs.zzby();
            var10000.zzvW = zzxx.zza(this.zzsP.zzqD, this, var1, this.zzsP.zzvS, (zzaka)null, this.zzsX, this, var2);
         } else {
            zzaet var3 = this.zzsS.zzto.zza(this.zzsP.zzqD, this.zzsP.zzvT, var1.zzXY);
            zzagz.zzZr.post(new zzl(this, var1, var3, var2));
         }
      }
   }

   protected boolean zza(@Nullable zzaff var1, zzaff var2) {
      if (this.zzsP.zzcc() && this.zzsP.zzvU != null) {
         this.zzsP.zzvU.zzcf().zzaP(var2.zzTt);
      }

      return super.zza(var1, var2);
   }

   public final void zza(zznh var1) {
      com.google.android.gms.common.internal.zzbo.zzcz("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
      this.zzsP.zzwo = var1;
   }

   public final void zzc(View var1) {
      this.zzsP.zzws = var1;
      this.zzb(new zzaff(this.zzsP.zzvZ, (zzaka)null, (zzua)null, (zzut)null, (String)null, (zzud)null, (zzoa)null, (String)null));
   }

   public final void zzaL() {
      this.onAdClicked();
   }

   public final void zzaM() {
      this.recordImpression();
      this.zzao();
   }

   public final void zza(int var1, int var2, int var3, int var4) {
      this.zzar();
   }

   public final void zzaN() {
      this.zzap();
   }
}
