package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

@zzzn
final class zzajx extends zzajy implements OnScrollChangedListener {
   private final WeakReference zzabk;

   public zzajx(View var1, OnScrollChangedListener var2) {
      super(var1);
      this.zzabk = new WeakReference(var2);
   }

   public final void onScrollChanged() {
      OnScrollChangedListener var1;
      if ((var1 = (OnScrollChangedListener)this.zzabk.get()) != null) {
         var1.onScrollChanged();
      } else {
         this.detach();
      }
   }

   protected final void zza(ViewTreeObserver var1) {
      var1.addOnScrollChangedListener(this);
   }

   protected final void zzb(ViewTreeObserver var1) {
      var1.removeOnScrollChangedListener(this);
   }
}
