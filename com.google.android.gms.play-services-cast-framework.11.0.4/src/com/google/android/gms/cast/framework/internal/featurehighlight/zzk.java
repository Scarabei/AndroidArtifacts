package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.R.dimen;
import com.google.android.gms.internal.ik;

final class zzk {
   private final Rect zzatr = new Rect();
   private final int zzats;
   private final int zzatt;
   private final int zzatu;
   private final int zzatv;
   private final zza zzatw;

   zzk(zza var1) {
      this.zzatw = (zza)ik.zzu(var1);
      Resources var2 = var1.getResources();
      this.zzats = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_inner_radius);
      this.zzatt = var2.getDimensionPixelOffset(dimen.cast_libraries_material_featurehighlight_inner_margin);
      this.zzatu = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_text_max_width);
      this.zzatv = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
   }

   final void zza(Rect var1, Rect var2) {
      View var3 = this.zzatw.zznO();
      if (!var1.isEmpty() && !var2.isEmpty()) {
         int var4 = var1.centerY();
         int var5 = var1.centerX();
         boolean var6 = var4 < var2.centerY();
         int var14 = var1.height();
         int var7 = Math.max(2 * this.zzats, var14);
         int var8 = var4 + var7 / 2 + this.zzatt;
         int var9;
         int var10;
         if (var6) {
            var9 = var2.bottom - var8;
            this.zza(var3, var2.width(), var9);
            var10 = this.zza(var3, var2.left, var2.right, var3.getMeasuredWidth(), var5);
            var3.layout(var10, var8, var10 + var3.getMeasuredWidth(), var8 + var3.getMeasuredHeight());
         } else {
            var10 = (var9 = var4 - var7 / 2 - this.zzatt) - var2.top;
            this.zza(var3, var2.width(), var10);
            int var11 = this.zza(var3, var2.left, var2.right, var3.getMeasuredWidth(), var5);
            var3.layout(var11, var9 - var3.getMeasuredHeight(), var11 + var3.getMeasuredWidth(), var9);
         }
      } else {
         var3.layout(0, 0, 0, 0);
      }

      Rect var13 = this.zzatr;
      var13.set(var3.getLeft(), var3.getTop(), var3.getRight(), var3.getBottom());
      this.zzatw.zznP().zzb(var1, this.zzatr);
      this.zzatw.zznQ().zzc(var1);
   }

   private final int zza(View var1, int var2, int var3, int var4, int var5) {
      MarginLayoutParams var6 = (MarginLayoutParams)var1.getLayoutParams();
      int var7 = var4 / 2;
      int var8 = var5 - var2;
      int var9 = var3 - var5;
      int var10;
      if ((var10 = var8 <= var9 ? var5 - var7 + this.zzatv : var5 - var7 - this.zzatv) - var6.leftMargin < var2) {
         return var2 + var6.leftMargin;
      } else {
         return var10 + var4 + var6.rightMargin > var3 ? var3 - var4 - var6.rightMargin : var10;
      }
   }

   private final void zza(View var1, int var2, int var3) {
      MarginLayoutParams var4 = (MarginLayoutParams)var1.getLayoutParams();
      int var5 = Math.min(var2 - var4.leftMargin - var4.rightMargin, this.zzatu);
      var1.measure(MeasureSpec.makeMeasureSpec(var5, 1073741824), MeasureSpec.makeMeasureSpec(var3, Integer.MIN_VALUE));
   }
}
