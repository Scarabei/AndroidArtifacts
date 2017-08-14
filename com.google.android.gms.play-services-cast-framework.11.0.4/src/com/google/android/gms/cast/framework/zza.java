package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;

public final class zza extends zzg {
   private final AppVisibilityListener zzarJ;

   public zza(AppVisibilityListener var1) {
      this.zzarJ = var1;
   }

   public final int zznm() {
      return 11020208;
   }

   public final IObjectWrapper zznn() {
      return com.google.android.gms.dynamic.zzn.zzw(this.zzarJ);
   }

   public final void onAppEnteredForeground() {
      this.zzarJ.onAppEnteredForeground();
   }

   public final void onAppEnteredBackground() {
      this.zzarJ.onAppEnteredBackground();
   }
}
