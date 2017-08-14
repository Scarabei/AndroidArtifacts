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
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.R.dimen;
import com.google.android.gms.R.integer;
import com.google.android.gms.internal.hu;

final class zzj extends Drawable {
   private final Paint zzasL = new Paint();
   private final Paint zzatl = new Paint();
   private final Rect zzasQ = new Rect();
   private final int zzatm;
   private final int zzatn;
   private float zzasM;
   private float zzato = 1.0F;
   private float centerX;
   private float centerY;
   private float zzatp;
   private float zzatq;

   public zzj(Context var1) {
      Resources var2 = var1.getResources();
      this.zzatm = var2.getDimensionPixelSize(dimen.cast_libraries_material_featurehighlight_inner_radius);
      this.zzatn = var2.getInteger(integer.cast_libraries_material_featurehighlight_pulse_base_alpha);
      this.zzasL.setAntiAlias(true);
      this.zzasL.setStyle(Style.FILL);
      this.zzatl.setAntiAlias(true);
      this.zzatl.setStyle(Style.FILL);
      this.zzasL.setColor(-1);
      this.zzatl.setColor(-1);
      this.invalidateSelf();
   }

   public final void draw(Canvas var1) {
      if (this.zzatq > 0.0F) {
         float var2 = this.zzasM * this.zzatp;
         this.zzatl.setAlpha((int)((float)this.zzatn * this.zzatq));
         var1.drawCircle(this.centerX, this.centerY, var2, this.zzatl);
      }

      var1.drawCircle(this.centerX, this.centerY, this.zzasM * this.zzato, this.zzasL);
   }

   public final void setAlpha(int var1) {
      this.zzasL.setAlpha(var1);
      this.invalidateSelf();
   }

   public final void setColorFilter(ColorFilter var1) {
      this.zzasL.setColorFilter(var1);
      this.invalidateSelf();
   }

   public final int getOpacity() {
      return -3;
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setScale(float var1) {
      this.zzato = var1;
      this.invalidateSelf();
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setPulseScale(float var1) {
      this.zzatp = var1;
      this.invalidateSelf();
   }

   @UsedByReflection("FeatureHighlightView.java")
   public final void setPulseAlpha(float var1) {
      this.zzatq = var1;
      this.invalidateSelf();
   }

   public final void zzc(Rect var1) {
      this.zzasQ.set(var1);
      this.centerX = this.zzasQ.exactCenterX();
      this.centerY = this.zzasQ.exactCenterY();
      this.zzasM = Math.max((float)this.zzatm, Math.max((float)this.zzasQ.width() / 2.0F, (float)this.zzasQ.height() / 2.0F));
      this.invalidateSelf();
   }

   public final Animator zznS() {
      PropertyValuesHolder var1 = PropertyValuesHolder.ofFloat("scale", new float[]{0.0F});
      PropertyValuesHolder var2 = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0F});
      PropertyValuesHolder var3 = PropertyValuesHolder.ofFloat("pulseScale", new float[]{0.0F});
      PropertyValuesHolder var4 = PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{0.0F});
      ObjectAnimator var5;
      (var5 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{var1, var2, var3, var4})).setInterpolator(hu.zzEh());
      return var5.setDuration(200L);
   }
}
