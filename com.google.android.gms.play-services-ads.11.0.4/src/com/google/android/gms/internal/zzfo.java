package com.google.android.gms.internal;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzfo implements zzgs {
   private final WeakReference zzxl;
   private final WeakReference zzxm;

   public zzfo(View var1, zzaff var2) {
      this.zzxl = new WeakReference(var1);
      this.zzxm = new WeakReference(var2);
   }

   public final View zzcv() {
      return (View)this.zzxl.get();
   }

   public final boolean zzcw() {
      return this.zzxl.get() == null || this.zzxm.get() == null;
   }

   public final zzgs zzcx() {
      return new zzfn((View)this.zzxl.get(), (zzaff)this.zzxm.get());
   }
}
