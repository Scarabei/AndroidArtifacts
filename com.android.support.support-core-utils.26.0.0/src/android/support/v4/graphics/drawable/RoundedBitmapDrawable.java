package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;

@RequiresApi(9)
public abstract class RoundedBitmapDrawable extends Drawable {
   private static final int DEFAULT_PAINT_FLAGS = 3;
   final Bitmap mBitmap;
   private int mTargetDensity = 160;
   private int mGravity = 119;
   private final Paint mPaint = new Paint(3);
   private final BitmapShader mBitmapShader;
   private final Matrix mShaderMatrix = new Matrix();
   private float mCornerRadius;
   final Rect mDstRect = new Rect();
   private final RectF mDstRectF = new RectF();
   private boolean mApplyGravity = true;
   private boolean mIsCircular;
   private int mBitmapWidth;
   private int mBitmapHeight;

   public final Paint getPaint() {
      return this.mPaint;
   }

   public final Bitmap getBitmap() {
      return this.mBitmap;
   }

   private void computeBitmapSize() {
      this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
      this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
   }

   public void setTargetDensity(Canvas canvas) {
      this.setTargetDensity(canvas.getDensity());
   }

   public void setTargetDensity(DisplayMetrics metrics) {
      this.setTargetDensity(metrics.densityDpi);
   }

   public void setTargetDensity(int density) {
      if (this.mTargetDensity != density) {
         this.mTargetDensity = density == 0 ? 160 : density;
         if (this.mBitmap != null) {
            this.computeBitmapSize();
         }

         this.invalidateSelf();
      }

   }

   public int getGravity() {
      return this.mGravity;
   }

   public void setGravity(int gravity) {
      if (this.mGravity != gravity) {
         this.mGravity = gravity;
         this.mApplyGravity = true;
         this.invalidateSelf();
      }

   }

   public void setMipMap(boolean mipMap) {
      throw new UnsupportedOperationException();
   }

   public boolean hasMipMap() {
      throw new UnsupportedOperationException();
   }

   public void setAntiAlias(boolean aa) {
      this.mPaint.setAntiAlias(aa);
      this.invalidateSelf();
   }

   public boolean hasAntiAlias() {
      return this.mPaint.isAntiAlias();
   }

   public void setFilterBitmap(boolean filter) {
      this.mPaint.setFilterBitmap(filter);
      this.invalidateSelf();
   }

   public void setDither(boolean dither) {
      this.mPaint.setDither(dither);
      this.invalidateSelf();
   }

   void gravityCompatApply(int gravity, int bitmapWidth, int bitmapHeight, Rect bounds, Rect outRect) {
      throw new UnsupportedOperationException();
   }

   void updateDstRect() {
      if (this.mApplyGravity) {
         if (this.mIsCircular) {
            int minDimen = Math.min(this.mBitmapWidth, this.mBitmapHeight);
            this.gravityCompatApply(this.mGravity, minDimen, minDimen, this.getBounds(), this.mDstRect);
            int minDrawDimen = Math.min(this.mDstRect.width(), this.mDstRect.height());
            int insetX = Math.max(0, (this.mDstRect.width() - minDrawDimen) / 2);
            int insetY = Math.max(0, (this.mDstRect.height() - minDrawDimen) / 2);
            this.mDstRect.inset(insetX, insetY);
            this.mCornerRadius = 0.5F * (float)minDrawDimen;
         } else {
            this.gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, this.getBounds(), this.mDstRect);
         }

         this.mDstRectF.set(this.mDstRect);
         if (this.mBitmapShader != null) {
            this.mShaderMatrix.setTranslate(this.mDstRectF.left, this.mDstRectF.top);
            this.mShaderMatrix.preScale(this.mDstRectF.width() / (float)this.mBitmap.getWidth(), this.mDstRectF.height() / (float)this.mBitmap.getHeight());
            this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
            this.mPaint.setShader(this.mBitmapShader);
         }

         this.mApplyGravity = false;
      }

   }

   public void draw(Canvas canvas) {
      Bitmap bitmap = this.mBitmap;
      if (bitmap != null) {
         this.updateDstRect();
         if (this.mPaint.getShader() == null) {
            canvas.drawBitmap(bitmap, (Rect)null, this.mDstRect, this.mPaint);
         } else {
            canvas.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, this.mPaint);
         }

      }
   }

   public void setAlpha(int alpha) {
      int oldAlpha = this.mPaint.getAlpha();
      if (alpha != oldAlpha) {
         this.mPaint.setAlpha(alpha);
         this.invalidateSelf();
      }

   }

   public int getAlpha() {
      return this.mPaint.getAlpha();
   }

   public void setColorFilter(ColorFilter cf) {
      this.mPaint.setColorFilter(cf);
      this.invalidateSelf();
   }

   public ColorFilter getColorFilter() {
      return this.mPaint.getColorFilter();
   }

   public void setCircular(boolean circular) {
      this.mIsCircular = circular;
      this.mApplyGravity = true;
      if (circular) {
         this.updateCircularCornerRadius();
         this.mPaint.setShader(this.mBitmapShader);
         this.invalidateSelf();
      } else {
         this.setCornerRadius(0.0F);
      }

   }

   private void updateCircularCornerRadius() {
      int minCircularSize = Math.min(this.mBitmapHeight, this.mBitmapWidth);
      this.mCornerRadius = (float)(minCircularSize / 2);
   }

   public boolean isCircular() {
      return this.mIsCircular;
   }

   public void setCornerRadius(float cornerRadius) {
      if (this.mCornerRadius != cornerRadius) {
         this.mIsCircular = false;
         if (isGreaterThanZero(cornerRadius)) {
            this.mPaint.setShader(this.mBitmapShader);
         } else {
            this.mPaint.setShader((Shader)null);
         }

         this.mCornerRadius = cornerRadius;
         this.invalidateSelf();
      }
   }

   protected void onBoundsChange(Rect bounds) {
      super.onBoundsChange(bounds);
      if (this.mIsCircular) {
         this.updateCircularCornerRadius();
      }

      this.mApplyGravity = true;
   }

   public float getCornerRadius() {
      return this.mCornerRadius;
   }

   public int getIntrinsicWidth() {
      return this.mBitmapWidth;
   }

   public int getIntrinsicHeight() {
      return this.mBitmapHeight;
   }

   public int getOpacity() {
      if (this.mGravity == 119 && !this.mIsCircular) {
         Bitmap bm = this.mBitmap;
         return bm != null && !bm.hasAlpha() && this.mPaint.getAlpha() >= 255 && !isGreaterThanZero(this.mCornerRadius) ? -1 : -3;
      } else {
         return -3;
      }
   }

   RoundedBitmapDrawable(Resources res, Bitmap bitmap) {
      if (res != null) {
         this.mTargetDensity = res.getDisplayMetrics().densityDpi;
      }

      this.mBitmap = bitmap;
      if (this.mBitmap != null) {
         this.computeBitmapSize();
         this.mBitmapShader = new BitmapShader(this.mBitmap, TileMode.CLAMP, TileMode.CLAMP);
      } else {
         this.mBitmapWidth = this.mBitmapHeight = -1;
         this.mBitmapShader = null;
      }

   }

   private static boolean isGreaterThanZero(float toCompare) {
      return toCompare > 0.05F;
   }
}
