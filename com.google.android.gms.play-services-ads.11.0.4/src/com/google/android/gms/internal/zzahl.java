package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(18)
public class zzahl extends zzahk {
   public boolean isAttachedToWindow(View var1) {
      return super.isAttachedToWindow(var1) || var1.getWindowId() != null;
   }

   public final int zzhV() {
      return 14;
   }
}
