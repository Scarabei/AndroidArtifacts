package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

@zzzn
abstract class zzajy {
   private final WeakReference zzabl;

   public zzajy(View var1) {
      this.zzabl = new WeakReference(var1);
   }

   public final void zzio() {
      ViewTreeObserver var1;
      if ((var1 = this.getViewTreeObserver()) != null) {
         this.zza(var1);
      }

   }

   public final void detach() {
      ViewTreeObserver var1;
      if ((var1 = this.getViewTreeObserver()) != null) {
         this.zzb(var1);
      }

   }

   protected abstract void zza(ViewTreeObserver var1);

   protected abstract void zzb(ViewTreeObserver var1);

   private final ViewTreeObserver getViewTreeObserver() {
      View var1;
      if ((var1 = (View)this.zzabl.get()) == null) {
         return null;
      } else {
         ViewTreeObserver var2;
         return (var2 = var1.getViewTreeObserver()) != null && var2.isAlive() ? var2 : null;
      }
   }
}
