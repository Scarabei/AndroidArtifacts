package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Map;

@zzzn
public final class zzwt extends zzwu implements zzrd {
   private final zzaka zzJH;
   private final Context mContext;
   private final WindowManager zzwR;
   private final zzlz zzNW;
   private DisplayMetrics zzxF;
   private float zzNX;
   private int zzNY = -1;
   private int zzNZ = -1;
   private int zzOa;
   private int zzOb = -1;
   private int zzOc = -1;
   private int zzOd = -1;
   private int zzOe = -1;

   public zzwt(zzaka var1, Context var2, zzlz var3) {
      super(var1);
      this.zzJH = var1;
      this.mContext = var2;
      this.zzNW = var3;
      this.zzwR = (WindowManager)var2.getSystemService("window");
   }

   public final void zzc(int var1, int var2) {
      int var3 = 0;
      if (this.mContext instanceof Activity) {
         var3 = com.google.android.gms.ads.internal.zzbs.zzbz().zzh((Activity)this.mContext)[0];
      }

      if (this.zzJH.zzam() == null || !this.zzJH.zzam().zzAt) {
         zzji.zzds();
         this.zzOd = zzaiy.zzd(this.mContext, this.zzJH.getWidth());
         zzji.zzds();
         this.zzOe = zzaiy.zzd(this.mContext, this.zzJH.getHeight());
      }

      this.zzc(var1, var2 - var3, this.zzOd, this.zzOe);
      this.zzJH.zziw().zzb(var1, var2);
   }

   public final void zza(zzaka var1, Map var2) {
      this.zzxF = new DisplayMetrics();
      Display var5;
      (var5 = this.zzwR.getDefaultDisplay()).getMetrics(this.zzxF);
      this.zzNX = this.zzxF.density;
      this.zzOa = var5.getRotation();
      zzwt var4;
      zzwt var10000 = var4 = this;
      zzji.zzds();
      var10000.zzNY = zzaiy.zzb(var4.zzxF, var4.zzxF.widthPixels);
      zzji.zzds();
      var4.zzNZ = zzaiy.zzb(var4.zzxF, var4.zzxF.heightPixels);
      Activity var8;
      if ((var8 = var4.zzJH.zzis()) != null && var8.getWindow() != null) {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         int[] var6 = zzagz.zzf(var8);
         zzji.zzds();
         var4.zzOb = zzaiy.zzb(var4.zzxF, var6[0]);
         zzji.zzds();
         var4.zzOc = zzaiy.zzb(var4.zzxF, var6[1]);
      } else {
         var4.zzOb = var4.zzNY;
         var4.zzOc = var4.zzNZ;
      }

      if (this.zzJH.zzam().zzAt) {
         this.zzOd = this.zzNY;
         this.zzOe = this.zzNZ;
      } else {
         this.zzJH.measure(0, 0);
      }

      this.zza(this.zzNY, this.zzNZ, this.zzOb, this.zzOc, this.zzNX, this.zzOa);
      zzws var7 = (new zzws()).zzm(this.zzNW.zzdE()).zzl(this.zzNW.zzdF()).zzn(this.zzNW.zzdH()).zzo(this.zzNW.zzdG()).zzp(true);
      zzwq var9 = new zzwq(var7, (zzwr)null);
      this.zzJH.zzb("onDeviceFeaturesReceived", var9.toJson());
      int[] var10 = new int[2];
      this.zzJH.getLocationOnScreen(var10);
      zzji.zzds();
      int var10001 = zzaiy.zzd(this.mContext, var10[0]);
      zzji.zzds();
      this.zzc(var10001, zzaiy.zzd(this.mContext, var10[1]));
      if (zzafr.zzz(2)) {
         zzafr.zzaS("Dispatching Ready Event.");
      }

      this.zzao(this.zzJH.zziz().zzaP);
   }
}
