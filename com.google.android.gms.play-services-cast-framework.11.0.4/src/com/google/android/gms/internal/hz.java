package com.google.android.gms.internal;

import android.graphics.PointF;

public final class hz implements hx {
   public final PointF zza(float var1, PointF[] var2, PointF var3) {
      float var4;
      float var5 = (1.0F - var1) * ((var4 = 1.0F - var1) * var2[0].x + var1 * var2[1].x) + var1 * (var4 * var2[1].x + var1 * var2[2].x);
      float var6 = var4 * (var4 * var2[0].y + var1 * var2[1].y) + var1 * (var4 * var2[1].y + var1 * var2[2].y);
      var3.set(var5, var6);
      return var3;
   }
}
