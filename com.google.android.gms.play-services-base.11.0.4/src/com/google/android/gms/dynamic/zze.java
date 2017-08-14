package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zze implements zzi {
   // $FF: synthetic field
   private FrameLayout zzaSx;
   // $FF: synthetic field
   private LayoutInflater zzaSy;
   // $FF: synthetic field
   private ViewGroup zzaSz;
   // $FF: synthetic field
   private Bundle zzxV;
   // $FF: synthetic field
   private zza zzaSv;

   zze(zza var1, FrameLayout var2, LayoutInflater var3, ViewGroup var4, Bundle var5) {
      this.zzaSv = var1;
      this.zzaSx = var2;
      this.zzaSy = var3;
      this.zzaSz = var4;
      this.zzxV = var5;
      super();
   }

   public final int getState() {
      return 2;
   }

   public final void zzb(LifecycleDelegate var1) {
      this.zzaSx.removeAllViews();
      this.zzaSx.addView(zza.zzb(this.zzaSv).onCreateView(this.zzaSy, this.zzaSz, this.zzxV));
   }
}
