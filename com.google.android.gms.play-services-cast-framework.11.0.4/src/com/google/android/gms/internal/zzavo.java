package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

final class zzavo implements zzavd {
   // $FF: synthetic field
   private zzavn zzave;

   zzavo(zzavn var1) {
      this.zzave = var1;
      super();
   }

   public final void zzc(Bitmap var1) {
      Bitmap var10000;
      if (var1 == null) {
         var10000 = null;
      } else {
         int var3 = var1.getWidth();
         int var4 = var1.getHeight();
         int var5;
         float var6 = (float)(((var5 = (int)((float)var3 * 9.0F / 16.0F + 0.5F)) - var4) / 2);
         RectF var7 = new RectF(0.0F, var6, (float)var3, var6 + (float)var4);
         Bitmap var8 = Bitmap.createBitmap(var3, var5, var1.getConfig());
         (new Canvas(var8)).drawBitmap(var1, (Rect)null, var7, (Paint)null);
         var10000 = var8;
      }

      var1 = var10000;
      zzavn.zza(this.zzave, var1, 0);
   }
}
