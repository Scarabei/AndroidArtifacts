package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.atomic.AtomicBoolean;

@zzzn
public abstract class zzxr implements zzahp, zzakf {
   private zzxy zzQP;
   protected final Context mContext;
   protected final zzaka zzJH;
   private zzafg zzQQ;
   protected zzaai zzQR;
   private Runnable zzQS;
   private Object zzQT = new Object();
   private AtomicBoolean zzQU = new AtomicBoolean(true);

   protected zzxr(Context var1, zzafg var2, zzaka var3, zzxy var4) {
      this.mContext = var1;
      this.zzQQ = var2;
      this.zzQR = this.zzQQ.zzXY;
      this.zzJH = var3;
      this.zzQP = var4;
   }

   protected abstract void zzgo();

   public final void zza(zzaka var1, boolean var2) {
      zzafr.zzaC("WebView finished loading.");
      if (this.zzQU.getAndSet(false)) {
         this.zzr(var2 ? -2 : 0);
         zzagz.zzZr.removeCallbacks(this.zzQS);
      }
   }

   public void cancel() {
      if (this.zzQU.getAndSet(false)) {
         this.zzJH.stopLoading();
         com.google.android.gms.ads.internal.zzbs.zzbB();
         zzahe.zzk(this.zzJH);
         this.zzr(-1);
         zzagz.zzZr.removeCallbacks(this.zzQS);
      }
   }

   protected void zzr(int var1) {
      if (var1 != -2) {
         this.zzQR = new zzaai(var1, this.zzQR.zzMg);
      }

      this.zzJH.zzir();
      zzaae var4 = this.zzQQ.zzUj;
      this.zzQP.zzb(new zzaff(var4.zzSz, this.zzJH, this.zzQR.zzMa, var1, this.zzQR.zzMb, this.zzQR.zzTq, this.zzQR.orientation, this.zzQR.zzMg, var4.zzSC, this.zzQR.zzTo, (zzua)null, (zzut)null, (String)null, (zzub)null, (zzud)null, this.zzQR.zzTp, this.zzQQ.zzvX, this.zzQR.zzTn, this.zzQQ.zzXR, this.zzQR.zzTs, this.zzQR.zzTt, this.zzQQ.zzXL, (zzoa)null, this.zzQR.zzTD, this.zzQR.zzTE, this.zzQR.zzTF, this.zzQR.zzTG, this.zzQR.zzTH, (String)null, this.zzQR.zzMd, this.zzQR.zzTK, this.zzQQ.zzXX));
   }

   // $FF: synthetic method
   public final Object zzgp() {
      zzbo.zzcz("Webview render task needs to be called on UI thread.");
      this.zzQS = new zzxs(this);
      zzme var2 = zzmo.zzEL;
      zzagz.zzZr.postDelayed(this.zzQS, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).longValue());
      this.zzgo();
      return null;
   }

   // $FF: synthetic method
   static AtomicBoolean zza(zzxr var0) {
      return var0.zzQU;
   }
}
