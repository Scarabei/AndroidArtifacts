package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.styleable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ForegroundLinearLayout extends LinearLayoutCompat {
   private Drawable mForeground;
   private final Rect mSelfBounds;
   private final Rect mOverlayBounds;
   private int mForegroundGravity;
   protected boolean mForegroundInPadding;
   boolean mForegroundBoundsChanged;

   public ForegroundLinearLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public ForegroundLinearLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ForegroundLinearLayout(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      this.mSelfBounds = new Rect();
      this.mOverlayBounds = new Rect();
      this.mForegroundGravity = 119;
      this.mForegroundInPadding = true;
      this.mForegroundBoundsChanged = false;
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.ForegroundLinearLayout, defStyle, 0);
      this.mForegroundGravity = a.getInt(styleable.ForegroundLinearLayout_android_foregroundGravity, this.mForegroundGravity);
      Drawable d = a.getDrawable(styleable.ForegroundLinearLayout_android_foreground);
      if (d != null) {
         this.setForeground(d);
      }

      this.mForegroundInPadding = a.getBoolean(styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
      a.recycle();
   }

   public int getForegroundGravity() {
      return this.mForegroundGravity;
   }

   public void setForegroundGravity(int foregroundGravity) {
      if (this.mForegroundGravity != foregroundGravity) {
         if ((foregroundGravity & 8388615) == 0) {
            foregroundGravity |= 8388611;
         }

         if ((foregroundGravity & 112) == 0) {
            foregroundGravity |= 48;
         }

         this.mForegroundGravity = foregroundGravity;
         if (this.mForegroundGravity == 119 && this.mForeground != null) {
            Rect padding = new Rect();
            this.mForeground.getPadding(padding);
         }

         this.requestLayout();
      }

   }

   protected boolean verifyDrawable(Drawable who) {
      return super.verifyDrawable(who) || who == this.mForeground;
   }

   @RequiresApi(11)
   public void jumpDrawablesToCurrentState() {
      super.jumpDrawablesToCurrentState();
      if (this.mForeground != null) {
         this.mForeground.jumpToCurrentState();
      }

   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if (this.mForeground != null && this.mForeground.isStateful()) {
         this.mForeground.setState(this.getDrawableState());
      }

   }

   public void setForeground(Drawable drawable) {
      if (this.mForeground != drawable) {
         if (this.mForeground != null) {
            this.mForeground.setCallback((Callback)null);
            this.unscheduleDrawable(this.mForeground);
         }

         this.mForeground = drawable;
         if (drawable != null) {
            this.setWillNotDraw(false);
            drawable.setCallback(this);
            if (drawable.isStateful()) {
               drawable.setState(this.getDrawableState());
            }

            if (this.mForegroundGravity == 119) {
               Rect padding = new Rect();
               drawable.getPadding(padding);
            }
         } else {
            this.setWillNotDraw(true);
         }

         this.requestLayout();
         this.invalidate();
      }

   }

   public Drawable getForeground() {
      return this.mForeground;
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      this.mForegroundBoundsChanged |= changed;
   }

   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      this.mForegroundBoundsChanged = true;
   }

   public void draw(@NonNull Canvas canvas) {
      super.draw(canvas);
      if (this.mForeground != null) {
         Drawable foreground = this.mForeground;
         if (this.mForegroundBoundsChanged) {
            this.mForegroundBoundsChanged = false;
            Rect selfBounds = this.mSelfBounds;
            Rect overlayBounds = this.mOverlayBounds;
            int w = this.getRight() - this.getLeft();
            int h = this.getBottom() - this.getTop();
            if (this.mForegroundInPadding) {
               selfBounds.set(0, 0, w, h);
            } else {
               selfBounds.set(this.getPaddingLeft(), this.getPaddingTop(), w - this.getPaddingRight(), h - this.getPaddingBottom());
            }

            Gravity.apply(this.mForegroundGravity, foreground.getIntrinsicWidth(), foreground.getIntrinsicHeight(), selfBounds, overlayBounds);
            foreground.setBounds(overlayBounds);
         }

         foreground.draw(canvas);
      }

   }

   @RequiresApi(21)
   public void drawableHotspotChanged(float x, float y) {
      super.drawableHotspotChanged(x, y);
      if (this.mForeground != null) {
         this.mForeground.setHotspot(x, y);
      }

   }
}
