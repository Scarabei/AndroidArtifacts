package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.fe;

final class zzc {
   static Rect zza(Text var0) {
      int var1 = Integer.MAX_VALUE;
      int var2 = Integer.MIN_VALUE;
      int var3 = Integer.MAX_VALUE;
      int var4 = Integer.MIN_VALUE;
      Point[] var5;
      int var6 = (var5 = var0.getCornerPoints()).length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Point var8 = var5[var7];
         var1 = Math.min(var1, var8.x);
         var2 = Math.max(var2, var8.x);
         var3 = Math.min(var3, var8.y);
         var4 = Math.max(var4, var8.y);
      }

      return new Rect(var1, var3, var2, var4);
   }

   static Point[] zza(fe var0) {
      Point[] var1 = new Point[4];
      double var2 = Math.sin(Math.toRadians((double)var0.zzbNW));
      double var4 = Math.cos(Math.toRadians((double)var0.zzbNW));
      var1[0] = new Point(var0.left, var0.top);
      var1[1] = new Point((int)((double)var0.left + (double)var0.width * var4), (int)((double)var0.top + (double)var0.width * var2));
      var1[2] = new Point((int)((double)var1[1].x - (double)var0.height * var2), (int)((double)var1[1].y + (double)var0.height * var4));
      var1[3] = new Point(var1[0].x + (var1[2].x - var1[1].x), var1[0].y + (var1[2].y - var1[1].y));
      return var1;
   }
}
