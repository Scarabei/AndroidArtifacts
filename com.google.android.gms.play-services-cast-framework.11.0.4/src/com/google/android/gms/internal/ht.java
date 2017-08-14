package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.Choreographer;

@TargetApi(16)
final class ht extends hn {
   private Choreographer zzbUw = Choreographer.getInstance();

   public final void zza(hp var1) {
      this.zzbUw.postFrameCallback(var1.zzEe());
   }
}
