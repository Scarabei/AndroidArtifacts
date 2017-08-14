package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.zzbo;

public final class AdView extends BaseAdView {
   public AdView(Context var1) {
      super(var1, 0);
      zzbo.zzb(var1, "Context cannot be null");
   }

   public AdView(Context var1, AttributeSet var2) {
      super(var1, var2, 0);
   }

   public AdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3, 0);
   }

   public final VideoController getVideoController() {
      return this.zzrZ != null ? this.zzrZ.getVideoController() : null;
   }
}
