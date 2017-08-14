package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.SeekBar;

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
   private final SeekBar mView;
   private Drawable mTickMark;
   private ColorStateList mTickMarkTintList = null;
   private Mode mTickMarkTintMode = null;
   private boolean mHasTickMarkTint = false;
   private boolean mHasTickMarkTintMode = false;

   AppCompatSeekBarHelper(SeekBar view) {
      super(view);
      this.mView = view;
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      super.loadFromAttributes(attrs, defStyleAttr);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, styleable.AppCompatSeekBar, defStyleAttr, 0);
      Drawable drawable = a.getDrawableIfKnown(styleable.AppCompatSeekBar_android_thumb);
      if (drawable != null) {
         this.mView.setThumb(drawable);
      }

      Drawable tickMark = a.getDrawable(styleable.AppCompatSeekBar_tickMark);
      this.setTickMark(tickMark);
      if (a.hasValue(styleable.AppCompatSeekBar_tickMarkTintMode)) {
         this.mTickMarkTintMode = DrawableUtils.parseTintMode(a.getInt(styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mTickMarkTintMode);
         this.mHasTickMarkTintMode = true;
      }

      if (a.hasValue(styleable.AppCompatSeekBar_tickMarkTint)) {
         this.mTickMarkTintList = a.getColorStateList(styleable.AppCompatSeekBar_tickMarkTint);
         this.mHasTickMarkTint = true;
      }

      a.recycle();
      this.applyTickMarkTint();
   }

   void setTickMark(@Nullable Drawable tickMark) {
      if (this.mTickMark != null) {
         this.mTickMark.setCallback((Callback)null);
      }

      this.mTickMark = tickMark;
      if (tickMark != null) {
         tickMark.setCallback(this.mView);
         DrawableCompat.setLayoutDirection(tickMark, ViewCompat.getLayoutDirection(this.mView));
         if (tickMark.isStateful()) {
            tickMark.setState(this.mView.getDrawableState());
         }

         this.applyTickMarkTint();
      }

      this.mView.invalidate();
   }

   @Nullable
   Drawable getTickMark() {
      return this.mTickMark;
   }

   void setTickMarkTintList(@Nullable ColorStateList tint) {
      this.mTickMarkTintList = tint;
      this.mHasTickMarkTint = true;
      this.applyTickMarkTint();
   }

   @Nullable
   ColorStateList getTickMarkTintList() {
      return this.mTickMarkTintList;
   }

   void setTickMarkTintMode(@Nullable Mode tintMode) {
      this.mTickMarkTintMode = tintMode;
      this.mHasTickMarkTintMode = true;
      this.applyTickMarkTint();
   }

   @Nullable
   Mode getTickMarkTintMode() {
      return this.mTickMarkTintMode;
   }

   private void applyTickMarkTint() {
      if (this.mTickMark != null && (this.mHasTickMarkTint || this.mHasTickMarkTintMode)) {
         this.mTickMark = DrawableCompat.wrap(this.mTickMark.mutate());
         if (this.mHasTickMarkTint) {
            DrawableCompat.setTintList(this.mTickMark, this.mTickMarkTintList);
         }

         if (this.mHasTickMarkTintMode) {
            DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode);
         }

         if (this.mTickMark.isStateful()) {
            this.mTickMark.setState(this.mView.getDrawableState());
         }
      }

   }

   @RequiresApi(11)
   void jumpDrawablesToCurrentState() {
      if (this.mTickMark != null) {
         this.mTickMark.jumpToCurrentState();
      }

   }

   void drawableStateChanged() {
      Drawable tickMark = this.mTickMark;
      if (tickMark != null && tickMark.isStateful() && tickMark.setState(this.mView.getDrawableState())) {
         this.mView.invalidateDrawable(tickMark);
      }

   }

   void drawTickMarks(Canvas canvas) {
      if (this.mTickMark != null) {
         int count = this.mView.getMax();
         if (count > 1) {
            int w = this.mTickMark.getIntrinsicWidth();
            int h = this.mTickMark.getIntrinsicHeight();
            int halfW = w >= 0 ? w / 2 : 1;
            int halfH = h >= 0 ? h / 2 : 1;
            this.mTickMark.setBounds(-halfW, -halfH, halfW, halfH);
            float spacing = (float)(this.mView.getWidth() - this.mView.getPaddingLeft() - this.mView.getPaddingRight()) / (float)count;
            int saveCount = canvas.save();
            canvas.translate((float)this.mView.getPaddingLeft(), (float)(this.mView.getHeight() / 2));

            for(int i = 0; i <= count; ++i) {
               this.mTickMark.draw(canvas);
               canvas.translate(spacing, 0.0F);
            }

            canvas.restoreToCount(saveCount);
         }
      }

   }
}
