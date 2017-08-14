package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzaq;
import com.google.android.gms.common.internal.zzbo;

@zzzn
public final class zzajz {
   private final zzaka zzJH;
   private final Context mContext;
   private final ViewGroup zzabm;
   private zzaa zzQK;

   public zzajz(Context var1, ViewGroup var2, zzaka var3) {
      this(var1, var2, var3, (zzaa)null);
   }

   private zzajz(Context var1, ViewGroup var2, zzaka var3, zzaa var4) {
      this.mContext = var1;
      this.zzabm = var2;
      this.zzJH = var3;
      this.zzQK = null;
   }

   public final void zze(int var1, int var2, int var3, int var4) {
      zzbo.zzcz("The underlay may only be modified from the UI thread.");
      if (this.zzQK != null) {
         this.zzQK.zzd(var1, var2, var3, var4);
      }

   }

   public final void zza(int var1, int var2, int var3, int var4, int var5, boolean var6, zzaq var7) {
      if (this.zzQK == null) {
         zzmu.zza(this.zzJH.zziG().zzdR(), this.zzJH.zziF(), "vpr2");
         this.zzQK = new zzaa(this.mContext, this.zzJH, var5, var6, this.zzJH.zziG().zzdR(), var7);
         this.zzabm.addView(this.zzQK, 0, new LayoutParams(-1, -1));
         this.zzQK.zzd(var1, var2, var3, var4);
         this.zzJH.zziw().zzE(false);
      }
   }

   public final zzaa zzip() {
      zzbo.zzcz("getAdVideoUnderlay must be called from the UI thread.");
      return this.zzQK;
   }

   public final void onPause() {
      zzbo.zzcz("onPause must be called from the UI thread.");
      if (this.zzQK != null) {
         this.zzQK.pause();
      }

   }

   public final void onDestroy() {
      zzbo.zzcz("onDestroy must be called from the UI thread.");
      if (this.zzQK != null) {
         this.zzQK.destroy();
         this.zzabm.removeView(this.zzQK);
         this.zzQK = null;
      }

   }
}
