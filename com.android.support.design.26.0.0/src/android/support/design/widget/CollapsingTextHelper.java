package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.v4.math.MathUtils;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class CollapsingTextHelper {
   private static final boolean USE_SCALING_TEXTURE;
   private static final boolean DEBUG_DRAW = false;
   private static final Paint DEBUG_DRAW_PAINT;
   private final View mView;
   private boolean mDrawTitle;
   private float mExpandedFraction;
   private final Rect mExpandedBounds;
   private final Rect mCollapsedBounds;
   private final RectF mCurrentBounds;
   private int mExpandedTextGravity = 16;
   private int mCollapsedTextGravity = 16;
   private float mExpandedTextSize = 15.0F;
   private float mCollapsedTextSize = 15.0F;
   private ColorStateList mExpandedTextColor;
   private ColorStateList mCollapsedTextColor;
   private float mExpandedDrawY;
   private float mCollapsedDrawY;
   private float mExpandedDrawX;
   private float mCollapsedDrawX;
   private float mCurrentDrawX;
   private float mCurrentDrawY;
   private Typeface mCollapsedTypeface;
   private Typeface mExpandedTypeface;
   private Typeface mCurrentTypeface;
   private CharSequence mText;
   private CharSequence mTextToDraw;
   private boolean mIsRtl;
   private boolean mUseTexture;
   private Bitmap mExpandedTitleTexture;
   private Paint mTexturePaint;
   private float mTextureAscent;
   private float mTextureDescent;
   private float mScale;
   private float mCurrentTextSize;
   private int[] mState;
   private boolean mBoundsChanged;
   private final TextPaint mTextPaint;
   private Interpolator mPositionInterpolator;
   private Interpolator mTextSizeInterpolator;
   private float mCollapsedShadowRadius;
   private float mCollapsedShadowDx;
   private float mCollapsedShadowDy;
   private int mCollapsedShadowColor;
   private float mExpandedShadowRadius;
   private float mExpandedShadowDx;
   private float mExpandedShadowDy;
   private int mExpandedShadowColor;

   public CollapsingTextHelper(View view) {
      this.mView = view;
      this.mTextPaint = new TextPaint(129);
      this.mCollapsedBounds = new Rect();
      this.mExpandedBounds = new Rect();
      this.mCurrentBounds = new RectF();
   }

   void setTextSizeInterpolator(Interpolator interpolator) {
      this.mTextSizeInterpolator = interpolator;
      this.recalculate();
   }

   void setPositionInterpolator(Interpolator interpolator) {
      this.mPositionInterpolator = interpolator;
      this.recalculate();
   }

   void setExpandedTextSize(float textSize) {
      if (this.mExpandedTextSize != textSize) {
         this.mExpandedTextSize = textSize;
         this.recalculate();
      }

   }

   void setCollapsedTextSize(float textSize) {
      if (this.mCollapsedTextSize != textSize) {
         this.mCollapsedTextSize = textSize;
         this.recalculate();
      }

   }

   void setCollapsedTextColor(ColorStateList textColor) {
      if (this.mCollapsedTextColor != textColor) {
         this.mCollapsedTextColor = textColor;
         this.recalculate();
      }

   }

   void setExpandedTextColor(ColorStateList textColor) {
      if (this.mExpandedTextColor != textColor) {
         this.mExpandedTextColor = textColor;
         this.recalculate();
      }

   }

   void setExpandedBounds(int left, int top, int right, int bottom) {
      if (!rectEquals(this.mExpandedBounds, left, top, right, bottom)) {
         this.mExpandedBounds.set(left, top, right, bottom);
         this.mBoundsChanged = true;
         this.onBoundsChanged();
      }

   }

   void setCollapsedBounds(int left, int top, int right, int bottom) {
      if (!rectEquals(this.mCollapsedBounds, left, top, right, bottom)) {
         this.mCollapsedBounds.set(left, top, right, bottom);
         this.mBoundsChanged = true;
         this.onBoundsChanged();
      }

   }

   void onBoundsChanged() {
      this.mDrawTitle = this.mCollapsedBounds.width() > 0 && this.mCollapsedBounds.height() > 0 && this.mExpandedBounds.width() > 0 && this.mExpandedBounds.height() > 0;
   }

   void setExpandedTextGravity(int gravity) {
      if (this.mExpandedTextGravity != gravity) {
         this.mExpandedTextGravity = gravity;
         this.recalculate();
      }

   }

   int getExpandedTextGravity() {
      return this.mExpandedTextGravity;
   }

   void setCollapsedTextGravity(int gravity) {
      if (this.mCollapsedTextGravity != gravity) {
         this.mCollapsedTextGravity = gravity;
         this.recalculate();
      }

   }

   int getCollapsedTextGravity() {
      return this.mCollapsedTextGravity;
   }

   void setCollapsedTextAppearance(int resId) {
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), resId, styleable.TextAppearance);
      if (a.hasValue(styleable.TextAppearance_android_textColor)) {
         this.mCollapsedTextColor = a.getColorStateList(styleable.TextAppearance_android_textColor);
      }

      if (a.hasValue(styleable.TextAppearance_android_textSize)) {
         this.mCollapsedTextSize = (float)a.getDimensionPixelSize(styleable.TextAppearance_android_textSize, (int)this.mCollapsedTextSize);
      }

      this.mCollapsedShadowColor = a.getInt(styleable.TextAppearance_android_shadowColor, 0);
      this.mCollapsedShadowDx = a.getFloat(styleable.TextAppearance_android_shadowDx, 0.0F);
      this.mCollapsedShadowDy = a.getFloat(styleable.TextAppearance_android_shadowDy, 0.0F);
      this.mCollapsedShadowRadius = a.getFloat(styleable.TextAppearance_android_shadowRadius, 0.0F);
      a.recycle();
      if (VERSION.SDK_INT >= 16) {
         this.mCollapsedTypeface = this.readFontFamilyTypeface(resId);
      }

      this.recalculate();
   }

   void setExpandedTextAppearance(int resId) {
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), resId, styleable.TextAppearance);
      if (a.hasValue(styleable.TextAppearance_android_textColor)) {
         this.mExpandedTextColor = a.getColorStateList(styleable.TextAppearance_android_textColor);
      }

      if (a.hasValue(styleable.TextAppearance_android_textSize)) {
         this.mExpandedTextSize = (float)a.getDimensionPixelSize(styleable.TextAppearance_android_textSize, (int)this.mExpandedTextSize);
      }

      this.mExpandedShadowColor = a.getInt(styleable.TextAppearance_android_shadowColor, 0);
      this.mExpandedShadowDx = a.getFloat(styleable.TextAppearance_android_shadowDx, 0.0F);
      this.mExpandedShadowDy = a.getFloat(styleable.TextAppearance_android_shadowDy, 0.0F);
      this.mExpandedShadowRadius = a.getFloat(styleable.TextAppearance_android_shadowRadius, 0.0F);
      a.recycle();
      if (VERSION.SDK_INT >= 16) {
         this.mExpandedTypeface = this.readFontFamilyTypeface(resId);
      }

      this.recalculate();
   }

   private Typeface readFontFamilyTypeface(int resId) {
      TypedArray a = this.mView.getContext().obtainStyledAttributes(resId, new int[]{16843692});

      Typeface var4;
      try {
         String family = a.getString(0);
         if (family == null) {
            return null;
         }

         var4 = Typeface.create(family, 0);
      } finally {
         a.recycle();
      }

      return var4;
   }

   void setCollapsedTypeface(Typeface typeface) {
      if (this.areTypefacesDifferent(this.mCollapsedTypeface, typeface)) {
         this.mCollapsedTypeface = typeface;
         this.recalculate();
      }

   }

   void setExpandedTypeface(Typeface typeface) {
      if (this.areTypefacesDifferent(this.mExpandedTypeface, typeface)) {
         this.mExpandedTypeface = typeface;
         this.recalculate();
      }

   }

   void setTypefaces(Typeface typeface) {
      this.mCollapsedTypeface = this.mExpandedTypeface = typeface;
      this.recalculate();
   }

   Typeface getCollapsedTypeface() {
      return this.mCollapsedTypeface != null ? this.mCollapsedTypeface : Typeface.DEFAULT;
   }

   Typeface getExpandedTypeface() {
      return this.mExpandedTypeface != null ? this.mExpandedTypeface : Typeface.DEFAULT;
   }

   void setExpansionFraction(float fraction) {
      fraction = MathUtils.clamp(fraction, 0.0F, 1.0F);
      if (fraction != this.mExpandedFraction) {
         this.mExpandedFraction = fraction;
         this.calculateCurrentOffsets();
      }

   }

   final boolean setState(int[] state) {
      this.mState = state;
      if (this.isStateful()) {
         this.recalculate();
         return true;
      } else {
         return false;
      }
   }

   final boolean isStateful() {
      return this.mCollapsedTextColor != null && this.mCollapsedTextColor.isStateful() || this.mExpandedTextColor != null && this.mExpandedTextColor.isStateful();
   }

   float getExpansionFraction() {
      return this.mExpandedFraction;
   }

   float getCollapsedTextSize() {
      return this.mCollapsedTextSize;
   }

   float getExpandedTextSize() {
      return this.mExpandedTextSize;
   }

   private void calculateCurrentOffsets() {
      this.calculateOffsets(this.mExpandedFraction);
   }

   private void calculateOffsets(float fraction) {
      this.interpolateBounds(fraction);
      this.mCurrentDrawX = lerp(this.mExpandedDrawX, this.mCollapsedDrawX, fraction, this.mPositionInterpolator);
      this.mCurrentDrawY = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, fraction, this.mPositionInterpolator);
      this.setInterpolatedTextSize(lerp(this.mExpandedTextSize, this.mCollapsedTextSize, fraction, this.mTextSizeInterpolator));
      if (this.mCollapsedTextColor != this.mExpandedTextColor) {
         this.mTextPaint.setColor(blendColors(this.getCurrentExpandedTextColor(), this.getCurrentCollapsedTextColor(), fraction));
      } else {
         this.mTextPaint.setColor(this.getCurrentCollapsedTextColor());
      }

      this.mTextPaint.setShadowLayer(lerp(this.mExpandedShadowRadius, this.mCollapsedShadowRadius, fraction, (Interpolator)null), lerp(this.mExpandedShadowDx, this.mCollapsedShadowDx, fraction, (Interpolator)null), lerp(this.mExpandedShadowDy, this.mCollapsedShadowDy, fraction, (Interpolator)null), blendColors(this.mExpandedShadowColor, this.mCollapsedShadowColor, fraction));
      ViewCompat.postInvalidateOnAnimation(this.mView);
   }

   @ColorInt
   private int getCurrentExpandedTextColor() {
      return this.mState != null ? this.mExpandedTextColor.getColorForState(this.mState, 0) : this.mExpandedTextColor.getDefaultColor();
   }

   @ColorInt
   private int getCurrentCollapsedTextColor() {
      return this.mState != null ? this.mCollapsedTextColor.getColorForState(this.mState, 0) : this.mCollapsedTextColor.getDefaultColor();
   }

   private void calculateBaseOffsets() {
      float currentTextSize = this.mCurrentTextSize;
      this.calculateUsingTextSize(this.mCollapsedTextSize);
      float width = this.mTextToDraw != null ? this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()) : 0.0F;
      int collapsedAbsGravity = GravityCompat.getAbsoluteGravity(this.mCollapsedTextGravity, this.mIsRtl ? 1 : 0);
      float textHeight;
      switch(collapsedAbsGravity & 112) {
      case 16:
      default:
         float textHeight = this.mTextPaint.descent() - this.mTextPaint.ascent();
         textHeight = textHeight / 2.0F - this.mTextPaint.descent();
         this.mCollapsedDrawY = (float)this.mCollapsedBounds.centerY() + textHeight;
         break;
      case 48:
         this.mCollapsedDrawY = (float)this.mCollapsedBounds.top - this.mTextPaint.ascent();
         break;
      case 80:
         this.mCollapsedDrawY = (float)this.mCollapsedBounds.bottom;
      }

      switch(collapsedAbsGravity & 8388615) {
      case 1:
         this.mCollapsedDrawX = (float)this.mCollapsedBounds.centerX() - width / 2.0F;
         break;
      case 2:
      case 3:
      case 4:
      default:
         this.mCollapsedDrawX = (float)this.mCollapsedBounds.left;
         break;
      case 5:
         this.mCollapsedDrawX = (float)this.mCollapsedBounds.right - width;
      }

      this.calculateUsingTextSize(this.mExpandedTextSize);
      width = this.mTextToDraw != null ? this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()) : 0.0F;
      int expandedAbsGravity = GravityCompat.getAbsoluteGravity(this.mExpandedTextGravity, this.mIsRtl ? 1 : 0);
      switch(expandedAbsGravity & 112) {
      case 16:
      default:
         textHeight = this.mTextPaint.descent() - this.mTextPaint.ascent();
         float textOffset = textHeight / 2.0F - this.mTextPaint.descent();
         this.mExpandedDrawY = (float)this.mExpandedBounds.centerY() + textOffset;
         break;
      case 48:
         this.mExpandedDrawY = (float)this.mExpandedBounds.top - this.mTextPaint.ascent();
         break;
      case 80:
         this.mExpandedDrawY = (float)this.mExpandedBounds.bottom;
      }

      switch(expandedAbsGravity & 8388615) {
      case 1:
         this.mExpandedDrawX = (float)this.mExpandedBounds.centerX() - width / 2.0F;
         break;
      case 2:
      case 3:
      case 4:
      default:
         this.mExpandedDrawX = (float)this.mExpandedBounds.left;
         break;
      case 5:
         this.mExpandedDrawX = (float)this.mExpandedBounds.right - width;
      }

      this.clearTexture();
      this.setInterpolatedTextSize(currentTextSize);
   }

   private void interpolateBounds(float fraction) {
      this.mCurrentBounds.left = lerp((float)this.mExpandedBounds.left, (float)this.mCollapsedBounds.left, fraction, this.mPositionInterpolator);
      this.mCurrentBounds.top = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, fraction, this.mPositionInterpolator);
      this.mCurrentBounds.right = lerp((float)this.mExpandedBounds.right, (float)this.mCollapsedBounds.right, fraction, this.mPositionInterpolator);
      this.mCurrentBounds.bottom = lerp((float)this.mExpandedBounds.bottom, (float)this.mCollapsedBounds.bottom, fraction, this.mPositionInterpolator);
   }

   public void draw(Canvas canvas) {
      int saveCount = canvas.save();
      if (this.mTextToDraw != null && this.mDrawTitle) {
         float x = this.mCurrentDrawX;
         float y = this.mCurrentDrawY;
         boolean drawTexture = this.mUseTexture && this.mExpandedTitleTexture != null;
         float ascent;
         if (drawTexture) {
            ascent = this.mTextureAscent * this.mScale;
            float var10000 = this.mTextureDescent * this.mScale;
         } else {
            ascent = this.mTextPaint.ascent() * this.mScale;
            float var7 = this.mTextPaint.descent() * this.mScale;
         }

         if (drawTexture) {
            y += ascent;
         }

         if (this.mScale != 1.0F) {
            canvas.scale(this.mScale, this.mScale, x, y);
         }

         if (drawTexture) {
            canvas.drawBitmap(this.mExpandedTitleTexture, x, y, this.mTexturePaint);
         } else {
            canvas.drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), x, y, this.mTextPaint);
         }
      }

      canvas.restoreToCount(saveCount);
   }

   private boolean calculateIsRtl(CharSequence text) {
      boolean defaultIsRtl = ViewCompat.getLayoutDirection(this.mView) == 1;
      return (defaultIsRtl ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(text, 0, text.length());
   }

   private void setInterpolatedTextSize(float textSize) {
      this.calculateUsingTextSize(textSize);
      this.mUseTexture = USE_SCALING_TEXTURE && this.mScale != 1.0F;
      if (this.mUseTexture) {
         this.ensureExpandedTexture();
      }

      ViewCompat.postInvalidateOnAnimation(this.mView);
   }

   private boolean areTypefacesDifferent(Typeface first, Typeface second) {
      return first != null && !first.equals(second) || first == null && second != null;
   }

   private void calculateUsingTextSize(float textSize) {
      if (this.mText != null) {
         float collapsedWidth = (float)this.mCollapsedBounds.width();
         float expandedWidth = (float)this.mExpandedBounds.width();
         boolean updateDrawText = false;
         float availableWidth;
         float newTextSize;
         if (isClose(textSize, this.mCollapsedTextSize)) {
            newTextSize = this.mCollapsedTextSize;
            this.mScale = 1.0F;
            if (this.areTypefacesDifferent(this.mCurrentTypeface, this.mCollapsedTypeface)) {
               this.mCurrentTypeface = this.mCollapsedTypeface;
               updateDrawText = true;
            }

            availableWidth = collapsedWidth;
         } else {
            newTextSize = this.mExpandedTextSize;
            if (this.areTypefacesDifferent(this.mCurrentTypeface, this.mExpandedTypeface)) {
               this.mCurrentTypeface = this.mExpandedTypeface;
               updateDrawText = true;
            }

            if (isClose(textSize, this.mExpandedTextSize)) {
               this.mScale = 1.0F;
            } else {
               this.mScale = textSize / this.mExpandedTextSize;
            }

            float textSizeRatio = this.mCollapsedTextSize / this.mExpandedTextSize;
            float scaledDownWidth = expandedWidth * textSizeRatio;
            if (scaledDownWidth > collapsedWidth) {
               availableWidth = Math.min(collapsedWidth / textSizeRatio, expandedWidth);
            } else {
               availableWidth = expandedWidth;
            }
         }

         if (availableWidth > 0.0F) {
            updateDrawText = this.mCurrentTextSize != newTextSize || this.mBoundsChanged || updateDrawText;
            this.mCurrentTextSize = newTextSize;
            this.mBoundsChanged = false;
         }

         if (this.mTextToDraw == null || updateDrawText) {
            this.mTextPaint.setTextSize(this.mCurrentTextSize);
            this.mTextPaint.setTypeface(this.mCurrentTypeface);
            this.mTextPaint.setLinearText(this.mScale != 1.0F);
            CharSequence title = TextUtils.ellipsize(this.mText, this.mTextPaint, availableWidth, TruncateAt.END);
            if (!TextUtils.equals(title, this.mTextToDraw)) {
               this.mTextToDraw = title;
               this.mIsRtl = this.calculateIsRtl(this.mTextToDraw);
            }
         }

      }
   }

   private void ensureExpandedTexture() {
      if (this.mExpandedTitleTexture == null && !this.mExpandedBounds.isEmpty() && !TextUtils.isEmpty(this.mTextToDraw)) {
         this.calculateOffsets(0.0F);
         this.mTextureAscent = this.mTextPaint.ascent();
         this.mTextureDescent = this.mTextPaint.descent();
         int w = Math.round(this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()));
         int h = Math.round(this.mTextureDescent - this.mTextureAscent);
         if (w > 0 && h > 0) {
            this.mExpandedTitleTexture = Bitmap.createBitmap(w, h, Config.ARGB_8888);
            Canvas c = new Canvas(this.mExpandedTitleTexture);
            c.drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), 0.0F, (float)h - this.mTextPaint.descent(), this.mTextPaint);
            if (this.mTexturePaint == null) {
               this.mTexturePaint = new Paint(3);
            }

         }
      }
   }

   public void recalculate() {
      if (this.mView.getHeight() > 0 && this.mView.getWidth() > 0) {
         this.calculateBaseOffsets();
         this.calculateCurrentOffsets();
      }

   }

   void setText(CharSequence text) {
      if (text == null || !text.equals(this.mText)) {
         this.mText = text;
         this.mTextToDraw = null;
         this.clearTexture();
         this.recalculate();
      }

   }

   CharSequence getText() {
      return this.mText;
   }

   private void clearTexture() {
      if (this.mExpandedTitleTexture != null) {
         this.mExpandedTitleTexture.recycle();
         this.mExpandedTitleTexture = null;
      }

   }

   private static boolean isClose(float value, float targetValue) {
      return Math.abs(value - targetValue) < 0.001F;
   }

   ColorStateList getExpandedTextColor() {
      return this.mExpandedTextColor;
   }

   ColorStateList getCollapsedTextColor() {
      return this.mCollapsedTextColor;
   }

   private static int blendColors(int color1, int color2, float ratio) {
      float inverseRatio = 1.0F - ratio;
      float a = (float)Color.alpha(color1) * inverseRatio + (float)Color.alpha(color2) * ratio;
      float r = (float)Color.red(color1) * inverseRatio + (float)Color.red(color2) * ratio;
      float g = (float)Color.green(color1) * inverseRatio + (float)Color.green(color2) * ratio;
      float b = (float)Color.blue(color1) * inverseRatio + (float)Color.blue(color2) * ratio;
      return Color.argb((int)a, (int)r, (int)g, (int)b);
   }

   private static float lerp(float startValue, float endValue, float fraction, Interpolator interpolator) {
      if (interpolator != null) {
         fraction = interpolator.getInterpolation(fraction);
      }

      return AnimationUtils.lerp(startValue, endValue, fraction);
   }

   private static boolean rectEquals(Rect r, int left, int top, int right, int bottom) {
      return r.left == left && r.top == top && r.right == right && r.bottom == bottom;
   }

   static {
      USE_SCALING_TEXTURE = VERSION.SDK_INT < 18;
      DEBUG_DRAW_PAINT = null;
      if (DEBUG_DRAW_PAINT != null) {
         DEBUG_DRAW_PAINT.setAntiAlias(true);
         DEBUG_DRAW_PAINT.setColor(-65281);
      }

   }
}
