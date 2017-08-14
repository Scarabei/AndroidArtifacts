package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

@zzzn
final class zzajw extends zzajy implements OnGlobalLayoutListener {
   private final WeakReference zzabk;

   public zzajw(View var1, OnGlobalLayoutListener var2) {
      super(var1);
      this.zzabk = new WeakReference(var2);
   }

   public final void onGlobalLayout() {
      OnGlobalLayoutListener var1;
      if ((var1 = (OnGlobalLayoutListener)this.zzabk.get()) != null) {
         var1.onGlobalLayout();
      } else {
         this.detach();
      }
   }

   protected final void zza(ViewTreeObserver var1) {
      var1.addOnGlobalLayoutListener(this);
   }

   protected final void zzb(ViewTreeObserver var1) {
      com.google.android.gms.ads.internal.zzbs.zzbB().zza((ViewTreeObserver)var1, (OnGlobalLayoutListener)this);
   }
}
