package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;

public final class zzfn implements zzgs {
   @Nullable
   private final View mView;
   @Nullable
   private final zzaff zzxk;

   public zzfn(View var1, zzaff var2) {
      this.mView = var1;
      this.zzxk = var2;
   }

   public final View zzcv() {
      return this.mView;
   }

   public final boolean zzcw() {
      return this.zzxk == null || this.mView == null;
   }

   public final zzgs zzcx() {
      return this;
   }
}
