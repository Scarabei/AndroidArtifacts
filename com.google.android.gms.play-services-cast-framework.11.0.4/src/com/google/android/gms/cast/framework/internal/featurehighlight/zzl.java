package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.util.TypedValue;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.R.color;
import com.google.android.gms.R.dimen;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.hu;
import com.google.android.gms.internal.hw;

final class zzl extends Drawable {
   private final int zzatx;
   private final int zzaty;
   private final int zzatz;
   private final Rect zzasQ = new Rect();
   private final Rect zzatr = new Rect();
   private final Paint zzasL = new Paint();
   private float zzasM;
   private float zzato = 1.0F;
   private float centerX;
   private float centerY;
   private float zzatA = 0.0F;
   private float zzatB = 0.0F;
   private int zzatC = 244;

   public zzl(Context var1) {
      if (zzq.zzse()) {
         TypedValue var4 = new TypedValue();
         var1.getTheme().resolveAttribute(16843827, var4, true);
         this.setColor(ColorUtils.setAlphaComponent(var4.data, 244));
      } else {
         this.setColor(var1.getResources().getColor(color.cast_libraries_material_featurehighlight_outer_highlight_default_color));
      }

      this.zzasL.setAntiAlias(true);
      this.zzasL.setStyle(Style.FILL);
      Resources var2 = var1.getResources();
      this.zzatx = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_center_threshold);
      this.zzaty = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
      this.zzatz = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_outer_padding);
   }

   public final void draw(Canvas var1) {
      var1.drawCircle(this.centerX + this.zzatA, this.centerY + this.zzatB, this.zzasM * this.zzato, this.zzasL);
   }

   public final void setAlpha(int var1) {
      this.zzasL.setAlpha(var1);
      this.invalidateSelf();
   }

   public final int getAlpha() {
      return this.zzasL.getAlpha();
   }

   public final void setColorFilter(ColorFilter var1) {
      this.zzasL.setColorFilter(var1);
      this.invalidateSelf();
   }

   public final int getOpacity() {
      return -3;
   }

   @ColorInt
   public final int getColor() {
      return this.zzasL.getColor();
   }

   public final void setColor(@ColorInt int var1) {
      this.zzasL.setColor(var1);
      this.zzatC = this.zzasL.getAlpha();
      this.invalidateSelf();
   }

   public final void zzb(Rect var1, Rect var2) {
      this.zzasQ.set(var1);
      this.zzatr.set(var2);
      float var3 = var1.exactCenterX();
      float var4 = var1.exactCenterY();
      Rect var5 = this.getBounds();
      if (Math.min(var4 - (float)var5.top, (float)var5.bottom - var4) < (float)this.zzatx) {
         this.centerX = var3;
         this.centerY = var4;
      } else {
         boolean var6 = var3 <= var5.exactCenterX();
         this.centerX = var6 ? var2.exactCenterX() + (float)this.zzaty : var2.exactCenterX() - (float)this.zzaty;
         this.centerY = var2.exactCenterY();
      }

      this.zzasM = (float)this.zzatz + Math.max(zza(this.centerX, this.centerY, var1), zza(this.centerX, this.centerY, var2));
      this.invalidateSelf();
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setScale(float var1) {
      this.zzato = var1;
      this.invalidateSelf();
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setTranslationX(float var1) {
      this.zzatA = var1;
      this.invalidateSelf();
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setTranslationY(float var1) {
      this.zzatB = var1;
      this.invalidateSelf();
   }

   public final float getCenterX() {
      return this.centerX;
   }

   public final float getCenterY() {
      return this.centerY;
   }

   public final boolean zzd(float var1, float var2) {
      return hw.zza(var1, var2, this.centerX, this.centerY) < this.zzasM;
   }

   private static float zza(float var0, float var1, Rect var2) {
      float var10002 = (float)var2.left;
      float var10003 = (float)var2.top;
      float var10004 = (float)var2.right;
      float var8 = (float)var2.bottom;
      float var7 = var10004;
      float var6 = var10003;
      float var5 = var10002;
      float var10000 = hw.zza(var0, var1, var5, var6);
      float var10001 = hw.zza(var0, var1, var7, var6);
      var10002 = hw.zza(var0, var1, var7, var8);
      float var12 = hw.zza(var0, var1, var5, var8);
      float var11 = var10002;
      float var10 = var10001;
      float var9 = var10000;
      return (float)Math.ceil((double)(var10000 > var10 && var9 > var11 && var9 > var12 ? var9 : (var10 > var11 && var10 > var12 ? var10 : (var11 > var12 ? var11 : var12))));
   }

   public final Animator zze(float var1, float var2) {
      PropertyValuesHolder var3 = PropertyValuesHolder.ofFloat("scale", new float[]{0.0F, 1.0F});
      PropertyValuesHolder var4 = PropertyValuesHolder.ofFloat("translationX", new float[]{var1, 0.0F});
      PropertyValuesHolder var5 = PropertyValuesHolder.ofFloat("translationY", new float[]{var2, 0.0F});
      PropertyValuesHolder var6 = PropertyValuesHolder.ofInt("alpha", new int[]{0, this.zzatC});
      ObjectAnimator var7;
      (var7 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{var3, var4, var5, var6})).setInterpolator(hu.zzEg());
      return var7.setDuration(350L);
   }
}
