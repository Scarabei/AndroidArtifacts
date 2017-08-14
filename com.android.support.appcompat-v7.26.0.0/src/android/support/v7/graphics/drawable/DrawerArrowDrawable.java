package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.style;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
   public static final int ARROW_DIRECTION_LEFT = 0;
   public static final int ARROW_DIRECTION_RIGHT = 1;
   public static final int ARROW_DIRECTION_START = 2;
   public static final int ARROW_DIRECTION_END = 3;
   private final Paint mPaint = new Paint();
   private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0D);
   private float mArrowHeadLength;
   private float mBarLength;
   private float mArrowShaftLength;
   private float mBarGap;
   private boolean mSpin;
   private final Path mPath = new Path();
   private final int mSize;
   private boolean mVerticalMirror = false;
   private float mProgress;
   private float mMaxCutForBarSize;
   private int mDirection = 2;

   public DrawerArrowDrawable(Context context) {
      this.mPaint.setStyle(Style.STROKE);
      this.mPaint.setStrokeJoin(Join.MITER);
      this.mPaint.setStrokeCap(Cap.BUTT);
      this.mPaint.setAntiAlias(true);
      TypedArray a = context.getTheme().obtainStyledAttributes((AttributeSet)null, styleable.DrawerArrowToggle, attr.drawerArrowStyle, style.Base_Widget_AppCompat_DrawerArrowToggle);
      this.setColor(a.getColor(styleable.DrawerArrowToggle_color, 0));
      this.setBarThickness(a.getDimension(styleable.DrawerArrowToggle_thickness, 0.0F));
      this.setSpinEnabled(a.getBoolean(styleable.DrawerArrowToggle_spinBars, true));
      this.setGapSize((float)Math.round(a.getDimension(styleable.DrawerArrowToggle_gapBetweenBars, 0.0F)));
      this.mSize = a.getDimensionPixelSize(styleable.DrawerArrowToggle_drawableSize, 0);
      this.mBarLength = (float)Math.round(a.getDimension(styleable.DrawerArrowToggle_barLength, 0.0F));
      this.mArrowHeadLength = (float)Math.round(a.getDimension(styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
      this.mArrowShaftLength = a.getDimension(styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
      a.recycle();
   }

   public void setArrowHeadLength(float length) {
      if (this.mArrowHeadLength != length) {
         this.mArrowHeadLength = length;
         this.invalidateSelf();
      }

   }

   public float getArrowHeadLength() {
      return this.mArrowHeadLength;
   }

   public void setArrowShaftLength(float length) {
      if (this.mArrowShaftLength != length) {
         this.mArrowShaftLength = length;
         this.invalidateSelf();
      }

   }

   public float getArrowShaftLength() {
      return this.mArrowShaftLength;
   }

   public float getBarLength() {
      return this.mBarLength;
   }

   public void setBarLength(float length) {
      if (this.mBarLength != length) {
         this.mBarLength = length;
         this.invalidateSelf();
      }

   }

   public void setColor(@ColorInt int color) {
      if (color != this.mPaint.getColor()) {
         this.mPaint.setColor(color);
         this.invalidateSelf();
      }

   }

   @ColorInt
   public int getColor() {
      return this.mPaint.getColor();
   }

   public void setBarThickness(float width) {
      if (this.mPaint.getStrokeWidth() != width) {
         this.mPaint.setStrokeWidth(width);
         this.mMaxCutForBarSize = (float)((double)(width / 2.0F) * Math.cos((double)ARROW_HEAD_ANGLE));
         this.invalidateSelf();
      }

   }

   public float getBarThickness() {
      return this.mPaint.getStrokeWidth();
   }

   public float getGapSize() {
      return this.mBarGap;
   }

   public void setGapSize(float gap) {
      if (gap != this.mBarGap) {
         this.mBarGap = gap;
         this.invalidateSelf();
      }

   }

   public void setDirection(int direction) {
      if (direction != this.mDirection) {
         this.mDirection = direction;
         this.invalidateSelf();
      }

   }

   public boolean isSpinEnabled() {
      return this.mSpin;
   }

   public void setSpinEnabled(boolean enabled) {
      if (this.mSpin != enabled) {
         this.mSpin = enabled;
         this.invalidateSelf();
      }

   }

   public int getDirection() {
      return this.mDirection;
   }

   public void setVerticalMirror(boolean verticalMirror) {
      if (this.mVerticalMirror != verticalMirror) {
         this.mVerticalMirror = verticalMirror;
         this.invalidateSelf();
      }

   }

   public void draw(Canvas canvas) {
      Rect bounds = this.getBounds();
      boolean flipToPointRight;
      switch(this.mDirection) {
      case 0:
         flipToPointRight = false;
         break;
      case 1:
         flipToPointRight = true;
         break;
      case 2:
      default:
         flipToPointRight = DrawableCompat.getLayoutDirection(this) == 1;
         break;
      case 3:
         flipToPointRight = DrawableCompat.getLayoutDirection(this) == 0;
      }

      float arrowHeadBarLength = (float)Math.sqrt((double)(this.mArrowHeadLength * this.mArrowHeadLength * 2.0F));
      arrowHeadBarLength = lerp(this.mBarLength, arrowHeadBarLength, this.mProgress);
      float arrowShaftLength = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
      float arrowShaftCut = (float)Math.round(lerp(0.0F, this.mMaxCutForBarSize, this.mProgress));
      float rotation = lerp(0.0F, ARROW_HEAD_ANGLE, this.mProgress);
      float canvasRotate = lerp(flipToPointRight ? 0.0F : -180.0F, flipToPointRight ? 180.0F : 0.0F, this.mProgress);
      float arrowWidth = (float)Math.round((double)arrowHeadBarLength * Math.cos((double)rotation));
      float arrowHeight = (float)Math.round((double)arrowHeadBarLength * Math.sin((double)rotation));
      this.mPath.rewind();
      float topBottomBarOffset = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
      float arrowEdge = -arrowShaftLength / 2.0F;
      this.mPath.moveTo(arrowEdge + arrowShaftCut, 0.0F);
      this.mPath.rLineTo(arrowShaftLength - arrowShaftCut * 2.0F, 0.0F);
      this.mPath.moveTo(arrowEdge, topBottomBarOffset);
      this.mPath.rLineTo(arrowWidth, arrowHeight);
      this.mPath.moveTo(arrowEdge, -topBottomBarOffset);
      this.mPath.rLineTo(arrowWidth, -arrowHeight);
      this.mPath.close();
      canvas.save();
      float barThickness = this.mPaint.getStrokeWidth();
      int remainingSpace = (int)((float)bounds.height() - barThickness * 3.0F - this.mBarGap * 2.0F);
      float yOffset = (float)(remainingSpace / 4 * 2);
      yOffset += barThickness * 1.5F + this.mBarGap;
      canvas.translate((float)bounds.centerX(), yOffset);
      if (this.mSpin) {
         canvas.rotate(canvasRotate * (float)(this.mVerticalMirror ^ flipToPointRight ? -1 : 1));
      } else if (flipToPointRight) {
         canvas.rotate(180.0F);
      }

      canvas.drawPath(this.mPath, this.mPaint);
      canvas.restore();
   }

   public void setAlpha(int alpha) {
      if (alpha != this.mPaint.getAlpha()) {
         this.mPaint.setAlpha(alpha);
         this.invalidateSelf();
      }

   }

   public void setColorFilter(ColorFilter colorFilter) {
      this.mPaint.setColorFilter(colorFilter);
      this.invalidateSelf();
   }

   public int getIntrinsicHeight() {
      return this.mSize;
   }

   public int getIntrinsicWidth() {
      return this.mSize;
   }

   public int getOpacity() {
      return -3;
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getProgress() {
      return this.mProgress;
   }

   public void setProgress(@FloatRange(from = 0.0D,to = 1.0D) float progress) {
      if (this.mProgress != progress) {
         this.mProgress = progress;
         this.invalidateSelf();
      }

   }

   public final Paint getPaint() {
      return this.mPaint;
   }

   private static float lerp(float a, float b, float t) {
      return a + (b - a) * t;
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface ArrowDirection {
   }
}
