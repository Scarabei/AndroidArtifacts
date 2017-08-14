package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzzn;
import java.util.HashMap;
import java.util.Map;

@zzzn
public final class zzaa extends FrameLayout implements zzx {
   private final zzaka zzJH;
   private final FrameLayout zzPr;
   private final zznb zzPs;
   private final zzas zzPt;
   private final long zzPu;
   @Nullable
   private zzy zzPv;
   private boolean zzPw;
   private boolean zzPx;
   private boolean zzPy;
   private boolean zzPz;
   private long zzPA;
   private long zzPB;
   private String zzJM;
   private Bitmap zzPC;
   private ImageView zzPD;
   private boolean zzPE;

   public static void zzh(zzaka var0) {
      HashMap var1;
      (var1 = new HashMap()).put("event", "no_video_view");
      var0.zza("onVideoEvent", (Map)var1);
   }

   public static void zzc(zzaka var0, Map var1) {
      HashMap var2;
      (var2 = new HashMap()).put("event", "decoderProps");
      var2.put("mimeTypes", var1);
      var0.zza("onVideoEvent", (Map)var2);
   }

   public static void zza(zzaka var0, String var1) {
      HashMap var2;
      (var2 = new HashMap()).put("event", "decoderProps");
      var2.put("error", var1);
      var0.zza("onVideoEvent", (Map)var2);
   }

   public zzaa(Context var1, zzaka var2, int var3, boolean var4, zznb var5, zzaq var6) {
      super(var1);
      this.zzJH = var2;
      this.zzPs = var5;
      this.zzPr = new FrameLayout(var1);
      this.addView(this.zzPr, new LayoutParams(-1, -1));
      com.google.android.gms.common.internal.zzc.zzr(var2.zzak());
      this.zzPv = var2.zzak().zztn.zza(var1, var2, var3, var4, var5, var6);
      zzme var7;
      if (this.zzPv != null) {
         this.zzPr.addView(this.zzPv, new LayoutParams(-1, -1, 17));
         var7 = zzmo.zzCw;
         if (((Boolean)zzbs.zzbL().zzd(var7)).booleanValue()) {
            this.zzgb();
         }
      }

      this.zzPD = new ImageView(var1);
      var7 = zzmo.zzCA;
      this.zzPu = ((Long)zzbs.zzbL().zzd(var7)).longValue();
      var7 = zzmo.zzCy;
      this.zzPz = ((Boolean)zzbs.zzbL().zzd(var7)).booleanValue();
      if (this.zzPs != null) {
         this.zzPs.zzh("spinner_used", this.zzPz ? "1" : "0");
      }

      this.zzPt = new zzas(this);
      if (this.zzPv != null) {
         this.zzPv.zza(this);
      }

      if (this.zzPv == null) {
         this.zzj("AdVideoUnderlay Error", "Allocating player failed.");
      }

   }

   public final void zzd(int var1, int var2, int var3, int var4) {
      if (var3 != 0 && var4 != 0) {
         LayoutParams var5;
         (var5 = new LayoutParams(var3, var4)).setMargins(var1, var2, 0, 0);
         this.zzPr.setLayoutParams(var5);
         this.requestLayout();
      }
   }

   public final void zzaq(String var1) {
      this.zzJM = var1;
   }

   public final void zza(float var1, float var2) {
      if (this.zzPv != null) {
         this.zzPv.zza(var1, var2);
      }

   }

   public final void zzfY() {
      if (this.zzPv != null) {
         if (!TextUtils.isEmpty(this.zzJM)) {
            this.zzPv.setVideoPath(this.zzJM);
         } else {
            this.zza("no_src");
         }
      }
   }

   public final void pause() {
      if (this.zzPv != null) {
         this.zzPv.pause();
      }
   }

   public final void play() {
      if (this.zzPv != null) {
         this.zzPv.play();
      }
   }

   public final void seekTo(int var1) {
      if (this.zzPv != null) {
         this.zzPv.seekTo(var1);
      }
   }

   public final void zzfZ() {
      if (this.zzPv != null) {
         zzy var1 = this.zzPv;
         this.zzPv.zzPq.setMuted(true);
         var1.zzfH();
      }
   }

   public final void zzga() {
      if (this.zzPv != null) {
         zzy var1 = this.zzPv;
         this.zzPv.zzPq.setMuted(false);
         var1.zzfH();
      }
   }

   public final void zzb(float var1) {
      if (this.zzPv != null) {
         zzy var2 = this.zzPv;
         this.zzPv.zzPq.zzb(var1);
         var2.zzfH();
      }
   }

   @TargetApi(14)
   public final void zze(MotionEvent var1) {
      if (this.zzPv != null) {
         this.zzPv.dispatchTouchEvent(var1);
      }
   }

   @TargetApi(14)
   public final void zzgb() {
      if (this.zzPv != null) {
         Context var1 = this.zzPv.getContext();
         TextView var2;
         TextView var10000 = var2 = new TextView(var1);
         String var10002 = String.valueOf(this.zzPv.zzfD());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "AdMob - ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("AdMob - ");
         }

         var10000.setText(var10001);
         var2.setTextColor(-65536);
         var2.setBackgroundColor(-256);
         this.zzPr.addView(var2, new LayoutParams(-2, -2, 17));
         this.zzPr.bringChildToFront(var2);
      }
   }

   public final void zzfS() {
      this.zzPt.resume();
      zzagz.zzZr.post(new zzab(this));
   }

   public final void zzfT() {
      if (this.zzPv != null) {
         if (this.zzPB == 0L) {
            float var1 = (float)this.zzPv.getDuration() / 1000.0F;
            int var2 = this.zzPv.getVideoWidth();
            int var3 = this.zzPv.getVideoHeight();
            this.zza("canplaythrough", "duration", String.valueOf(var1), "videoWidth", String.valueOf(var2), "videoHeight", String.valueOf(var3));
         }

      }
   }

   public final void zzfU() {
      if (this.zzJH.zzis() != null && !this.zzPx) {
         android.view.WindowManager.LayoutParams var2 = this.zzJH.zzis().getWindow().getAttributes();
         this.zzPy = (var2.flags & 128) != 0;
         if (!this.zzPy) {
            this.zzJH.zzis().getWindow().addFlags(128);
            this.zzPx = true;
         }
      }

      this.zzPw = true;
   }

   public final void onPaused() {
      this.zza("pause");
      this.zzge();
      this.zzPw = false;
   }

   public final void zzfV() {
      this.zza("ended");
      this.zzge();
   }

   public final void zzj(String var1, @Nullable String var2) {
      this.zza("error", "what", var1, "extra", var2);
   }

   public final void zzfW() {
      if (this.zzPE && this.zzPC != null && !this.zzgd()) {
         this.zzPD.setImageBitmap(this.zzPC);
         this.zzPD.invalidate();
         this.zzPr.addView(this.zzPD, new LayoutParams(-1, -1));
         this.zzPr.bringChildToFront(this.zzPD);
      }

      this.zzPt.pause();
      this.zzPB = this.zzPA;
      zzagz.zzZr.post(new zzac(this));
   }

   public final void destroy() {
      this.zzPt.pause();
      if (this.zzPv != null) {
         this.zzPv.stop();
      }

      this.zzge();
   }

   final void zzgc() {
      if (this.zzPv != null) {
         long var1 = (long)this.zzPv.getCurrentPosition();
         if (this.zzPA != var1 && var1 > 0L) {
            float var3 = (float)var1 / 1000.0F;
            this.zza("timeupdate", "time", String.valueOf(var3));
            this.zzPA = var1;
         }

      }
   }

   public final void zzfX() {
      if (this.zzPw && this.zzgd()) {
         this.zzPr.removeView(this.zzPD);
      }

      if (this.zzPC != null) {
         long var2 = zzbs.zzbF().elapsedRealtime();
         if (this.zzPv.getBitmap(this.zzPC) != null) {
            this.zzPE = true;
         }

         long var4 = zzbs.zzbF().elapsedRealtime() - var2;
         if (zzafr.zzhM()) {
            zzafr.v((new StringBuilder(46)).append("Spinner frame grab took ").append(var4).append("ms").toString());
         }

         if (var4 > this.zzPu) {
            zzafr.zzaT("Spinner frame grab crossed jank threshold! Suspending spinner.");
            this.zzPz = false;
            this.zzPC = null;
            if (this.zzPs != null) {
               this.zzPs.zzh("spinner_jank", Long.toString(var4));
            }
         }
      }

   }

   public final void zzd(int var1, int var2) {
      if (this.zzPz) {
         zzme var6 = zzmo.zzCz;
         int var4 = Math.max(var1 / ((Integer)zzbs.zzbL().zzd(var6)).intValue(), 1);
         var6 = zzmo.zzCz;
         int var5 = Math.max(var2 / ((Integer)zzbs.zzbL().zzd(var6)).intValue(), 1);
         if (this.zzPC == null || this.zzPC.getWidth() != var4 || this.zzPC.getHeight() != var5) {
            this.zzPC = Bitmap.createBitmap(var4, var5, Config.ARGB_8888);
            this.zzPE = false;
         }
      }

   }

   private final boolean zzgd() {
      return this.zzPD.getParent() != null;
   }

   private final void zza(String var1, String... var2) {
      HashMap var3;
      (var3 = new HashMap()).put("event", var1);
      String var4 = null;
      String[] var5 = var2;
      int var6 = var2.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String var8 = var5[var7];
         if (var4 == null) {
            var4 = var8;
         } else {
            var3.put(var4, var8);
            var4 = null;
         }
      }

      this.zzJH.zza("onVideoEvent", (Map)var3);
   }

   private final void zzge() {
      if (this.zzJH.zzis() != null) {
         if (this.zzPx && !this.zzPy) {
            this.zzJH.zzis().getWindow().clearFlags(128);
            this.zzPx = false;
         }

      }
   }

   // $FF: synthetic method
   static void zza(zzaa var0, String var1, String[] var2) {
      var0.zza(var1, var2);
   }
}
