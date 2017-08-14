package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzd extends zzp {
   private final CastStateListener zzasj;

   public zzd(CastStateListener var1) {
      this.zzasj = var1;
   }

   public final IObjectWrapper zznn() {
      return com.google.android.gms.dynamic.zzn.zzw(this.zzasj);
   }

   public final void onCastStateChanged(int var1) {
      this.zzasj.onCastStateChanged(var1);
   }
}
