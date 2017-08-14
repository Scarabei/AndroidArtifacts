package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class NativeExpressAdView extends BaseAdView {
   public NativeExpressAdView(Context var1) {
      super(var1, 1);
   }

   public NativeExpressAdView(Context var1, AttributeSet var2) {
      super(var1, var2, 1);
   }

   public NativeExpressAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3, 1);
   }

   public final VideoController getVideoController() {
      return this.zzrZ.getVideoController();
   }

   public final void setVideoOptions(VideoOptions var1) {
      this.zzrZ.setVideoOptions(var1);
   }

   public final VideoOptions getVideoOptions() {
      return this.zzrZ.getVideoOptions();
   }
}
