package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.R.id;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hu;
import com.google.android.gms.internal.ik;

public final class zza extends ViewGroup {
   private final int[] zzasP = new int[2];
   private final Rect zzasQ = new Rect();
   private final Rect zzasR = new Rect();
   private final zzl zzasS;
   private final zzj zzasT;
   private zzi zzasU;
   private View targetView;
   @Nullable
   private View zzasV;
   @Nullable
   private Animator zzasW;
   private final zzk zzasX;
   private final GestureDetectorCompat zzasY;
   @Nullable
   private GestureDetectorCompat zzasZ;
   private zzh zzata;
   private boolean zzatb;

   public zza(Context var1) {
      super(var1);
      this.setId(id.cast_featurehighlight_view);
      this.setWillNotDraw(false);
      this.zzasT = new zzj(var1);
      this.zzasT.setCallback(this);
      this.zzasS = new zzl(var1);
      this.zzasS.setCallback(this);
      this.zzasX = new zzk(this);
      zzb var2 = new zzb(this);
      this.zzasY = new GestureDetectorCompat(var1, var2);
      this.zzasY.setIsLongpressEnabled(false);
      this.setVisibility(8);
   }

   public final void zza(zzi var1) {
      this.zzasU = (zzi)ik.zzu(var1);
      this.addView(var1.asView(), 0);
   }

   public final void zza(View var1, @Nullable View var2, boolean var3, zzh var4) {
      this.targetView = (View)ik.zzu(var1);
      this.zzasV = null;
      this.zzata = (zzh)ik.zzu(var4);
      zzc var5 = new zzc(this, var1, true, var4);
      this.zzasZ = new GestureDetectorCompat(this.getContext(), var5);
      this.zzasZ.setIsLongpressEnabled(false);
      this.setVisibility(4);
   }

   protected final void onMeasure(int var1, int var2) {
      int var3 = MeasureSpec.getSize(var1);
      int var4 = MeasureSpec.getSize(var2);
      this.setMeasuredDimension(resolveSize(var3, var1), resolveSize(var4, var2));
   }

   protected final void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (this.targetView == null) {
         throw new IllegalStateException("Target view must be set before layout");
      } else {
         if (this.targetView.getParent() != null) {
            View var8 = this.targetView;
            int[] var7 = this.zzasP;
            this.getLocationInWindow(var7);
            int var9 = var7[0];
            int var10 = var7[1];
            var8.getLocationInWindow(var7);
            var7[0] -= var9;
            var7[1] -= var10;
         }

         this.zzasQ.set(this.zzasP[0], this.zzasP[1], this.zzasP[0] + this.targetView.getWidth(), this.zzasP[1] + this.targetView.getHeight());
         this.zzasR.set(var2, var3, var4, var5);
         this.zzasS.setBounds(this.zzasR);
         this.zzasT.setBounds(this.zzasR);
         this.zzasX.zza(this.zzasQ, this.zzasR);
      }
   }

   protected final void onDraw(Canvas var1) {
      var1.save();
      this.zzasS.draw(var1);
      this.zzasT.draw(var1);
      if (this.targetView == null) {
         throw new IllegalStateException("Neither target view nor drawable was set");
      } else {
         if (this.targetView.getParent() != null) {
            Bitmap var2 = Bitmap.createBitmap(this.targetView.getWidth(), this.targetView.getHeight(), Config.ARGB_8888);
            Canvas var3 = new Canvas(var2);
            this.targetView.draw(var3);
            int var4;
            int var5 = Color.red(var4 = this.zzasS.getColor());
            int var6 = Color.green(var4);
            int var7 = Color.blue(var4);

            for(int var8 = 0; var8 < var2.getHeight(); ++var8) {
               for(int var9 = 0; var9 < var2.getWidth(); ++var9) {
                  int var10;
                  if (Color.alpha(var10 = var2.getPixel(var9, var8)) != 0) {
                     var10 = Color.argb(Color.alpha(var10), var5, var6, var7);
                     var2.setPixel(var9, var8, var10);
                  }
               }
            }

            var1.drawBitmap(var2, (float)this.zzasQ.left, (float)this.zzasQ.top, (Paint)null);
         }

         var1.restore();
      }
   }

   public final LayoutParams generateLayoutParams(AttributeSet var1) {
      return new MarginLayoutParams(this.getContext(), var1);
   }

   protected final LayoutParams generateDefaultLayoutParams() {
      return new MarginLayoutParams(-2, -2);
   }

   protected final LayoutParams generateLayoutParams(LayoutParams var1) {
      return new MarginLayoutParams(var1);
   }

   protected final boolean checkLayoutParams(LayoutParams var1) {
      return var1 instanceof MarginLayoutParams;
   }

   protected final boolean verifyDrawable(Drawable var1) {
      return super.verifyDrawable(var1) || var1 == this.zzasS || var1 == this.zzasT || var1 == null;
   }

   public final void zzg(@Nullable Runnable var1) {
      this.addOnLayoutChangeListener(new zzd(this, (Runnable)null));
   }

   public final void zznN() {
      if (this.targetView == null) {
         throw new IllegalStateException("Target view must be set before animation");
      } else {
         this.setVisibility(0);
         ObjectAnimator var2;
         (var2 = ObjectAnimator.ofFloat(this.zzasU.asView(), "alpha", new float[]{0.0F, 1.0F}).setDuration(350L)).setInterpolator(hu.zzEg());
         float var3 = this.zzasQ.exactCenterX() - this.zzasS.getCenterX();
         float var4 = this.zzasQ.exactCenterY() - this.zzasS.getCenterY();
         Animator var5 = this.zzasS.zze(var3, var4);
         zzj var8 = this.zzasT;
         PropertyValuesHolder var9 = PropertyValuesHolder.ofFloat("scale", new float[]{0.0F, 1.0F});
         PropertyValuesHolder var10 = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0F, 1.0F});
         ObjectAnimator var11;
         (var11 = ObjectAnimator.ofPropertyValuesHolder(var8, new PropertyValuesHolder[]{var9, var10})).setInterpolator(hu.zzEg());
         Animator var6 = var11.setDuration(350L);
         AnimatorSet var7;
         (var7 = new AnimatorSet()).playTogether(new Animator[]{var2, var5, var6});
         var7.addListener(new zze(this));
         this.zza((Animator)var7);
      }
   }

   public final void zzh(@Nullable Runnable var1) {
      ObjectAnimator var4;
      (var4 = ObjectAnimator.ofFloat(this.zzasU.asView(), "alpha", new float[]{0.0F}).setDuration(200L)).setInterpolator(hu.zzEh());
      float var5 = this.zzasQ.exactCenterX() - this.zzasS.getCenterX();
      float var6 = this.zzasQ.exactCenterY() - this.zzasS.getCenterY();
      zzl var10 = this.zzasS;
      PropertyValuesHolder var13 = PropertyValuesHolder.ofFloat("scale", new float[]{0.0F});
      PropertyValuesHolder var14 = PropertyValuesHolder.ofInt("alpha", new int[]{0});
      PropertyValuesHolder var15 = PropertyValuesHolder.ofFloat("translationX", new float[]{0.0F, var5});
      PropertyValuesHolder var16 = PropertyValuesHolder.ofFloat("translationY", new float[]{0.0F, var6});
      ObjectAnimator var17;
      (var17 = ObjectAnimator.ofPropertyValuesHolder(var10, new PropertyValuesHolder[]{var13, var15, var16, var14})).setInterpolator(hu.zzEh());
      Animator var7 = var17.setDuration(200L);
      Animator var8 = this.zzasT.zznS();
      AnimatorSet var9;
      (var9 = new AnimatorSet()).playTogether(new Animator[]{var4, var7, var8});
      var9.addListener(new zzg(this, var1));
      this.zza((Animator)var9);
   }

   public final void zzi(@Nullable Runnable var1) {
      ObjectAnimator var4;
      (var4 = ObjectAnimator.ofFloat(this.zzasU.asView(), "alpha", new float[]{0.0F}).setDuration(200L)).setInterpolator(hu.zzEh());
      zzl var8 = this.zzasS;
      PropertyValuesHolder var9 = PropertyValuesHolder.ofFloat("scale", new float[]{1.125F});
      PropertyValuesHolder var10 = PropertyValuesHolder.ofInt("alpha", new int[]{0});
      ObjectAnimator var11;
      (var11 = ObjectAnimator.ofPropertyValuesHolder(var8, new PropertyValuesHolder[]{var9, var10})).setInterpolator(hu.zzEh());
      Animator var5 = var11.setDuration(200L);
      Animator var6 = this.zzasT.zznS();
      AnimatorSet var7;
      (var7 = new AnimatorSet()).playTogether(new Animator[]{var4, var5, var6});
      var7.addListener(new zzf(this, var1));
      this.zza((Animator)var7);
   }

   public final void zzaa(@ColorInt int var1) {
      this.zzasS.setColor(var1);
   }

   final View zznO() {
      return this.zzasU.asView();
   }

   final zzl zznP() {
      return this.zzasS;
   }

   final zzj zznQ() {
      return this.zzasT;
   }

   private final void zza(Animator var1) {
      if (this.zzasW != null) {
         this.zzasW.cancel();
      }

      this.zzasW = var1;
      this.zzasW.start();
   }

   public final boolean onTouchEvent(MotionEvent var1) {
      int var2;
      if ((var2 = var1.getActionMasked()) == 0) {
         this.zzatb = this.zzasQ.contains((int)var1.getX(), (int)var1.getY());
      }

      if (this.zzatb) {
         MotionEvent var3 = var1;
         if (this.zzasZ != null) {
            this.zzasZ.onTouchEvent(var1);
            if (var2 == 1) {
               (var3 = MotionEvent.obtain(var1)).setAction(3);
            }
         }

         if (this.targetView.getParent() != null) {
            this.targetView.onTouchEvent(var3);
         }
      } else {
         this.zzasY.onTouchEvent(var1);
      }

      return true;
   }

   private final boolean zzc(float var1, float var2) {
      return this.zzasR.contains(Math.round(var1), Math.round(var2));
   }

   private final Animator zznR() {
      zzj var1 = this.zzasT;
      AnimatorSet var2 = new AnimatorSet();
      ObjectAnimator var3 = ObjectAnimator.ofFloat(var1, "scale", new float[]{1.0F, 1.1F}).setDuration(500L);
      ObjectAnimator var4 = ObjectAnimator.ofFloat(var1, "scale", new float[]{1.1F, 1.0F}).setDuration(500L);
      PropertyValuesHolder var5 = PropertyValuesHolder.ofFloat("pulseScale", new float[]{1.1F, 2.0F});
      PropertyValuesHolder var6 = PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{1.0F, 0.0F});
      ObjectAnimator var7 = ObjectAnimator.ofPropertyValuesHolder(var1, new PropertyValuesHolder[]{var5, var6}).setDuration(500L);
      var2.play(var3);
      var2.play(var4).with(var7).after(var3);
      var2.setInterpolator(hu.zzEi());
      var2.setStartDelay(500L);
      hl.zza(var2, (Runnable)null);
      return var2;
   }

   // $FF: synthetic method
   static boolean zza(zza var0, float var1, float var2) {
      return var0.zzc(var1, var2);
   }

   // $FF: synthetic method
   static zzl zza(zza var0) {
      return var0.zzasS;
   }

   // $FF: synthetic method
   static zzh zzb(zza var0) {
      return var0.zzata;
   }

   // $FF: synthetic method
   static Animator zza(zza var0, Animator var1) {
      return var0.zzasW = var1;
   }

   // $FF: synthetic method
   static Animator zzc(zza var0) {
      return var0.zznR();
   }

   // $FF: synthetic method
   static Animator zzd(zza var0) {
      return var0.zzasW;
   }
}
