package com.google.android.gms.internal;

import android.graphics.PointF;

public final class hy implements hx {
   public final PointF zza(float var1, PointF[] var2, PointF var3) {
      float var4;
      float var5;
      float var6 = (var5 = (1.0F - var1) * (var4 = 1.0F - var1)) * var4;
      float var7;
      float var8 = (var7 = var1 * var1) * var1;
      float var9 = var6 * var2[0].x + 3.0F * var5 * var1 * var2[1].x + 3.0F * var4 * var7 * var2[2].x + var8 * var2[3].x;
      float var10 = var6 * var2[0].y + 3.0F * var5 * var1 * var2[1].y + 3.0F * var4 * var7 * var2[2].y + var8 * var2[3].y;
      var3.set(var9, var10);
      return var3;
   }
}
