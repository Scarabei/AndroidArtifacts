package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class zzbfk extends ConstantState {
   int mChangingConfigurations;
   int zzaGD;

   zzbfk(zzbfk var1) {
      if (var1 != null) {
         this.mChangingConfigurations = var1.mChangingConfigurations;
         this.zzaGD = var1.zzaGD;
      }

   }

   public final Drawable newDrawable() {
      return new zzbfg(this);
   }

   public final int getChangingConfigurations() {
      return this.mChangingConfigurations;
   }
}
