package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class zzfk implements zzgs {
   private WeakReference zzxi;

   public zzfk(zzny var1) {
      this.zzxi = new WeakReference(var1);
   }

   @Nullable
   public final View zzcv() {
      zzny var1;
      return (var1 = (zzny)this.zzxi.get()) != null ? var1.zzeu() : null;
   }

   public final boolean zzcw() {
      return this.zzxi.get() == null;
   }

   public final zzgs zzcx() {
      return new zzfm((zzny)this.zzxi.get());
   }
}
