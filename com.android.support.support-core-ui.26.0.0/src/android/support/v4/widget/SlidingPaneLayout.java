package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
   private static final String TAG = "SlidingPaneLayout";
   private static final int DEFAULT_OVERHANG_SIZE = 32;
   private static final int DEFAULT_FADE_COLOR = -858993460;
   private int mSliderFadeColor;
   private static final int MIN_FLING_VELOCITY = 400;
   private int mCoveredFadeColor;
   private Drawable mShadowDrawableLeft;
   private Drawable mShadowDrawableRight;
   private final int mOverhangSize;
   private boolean mCanSlide;
   View mSlideableView;
   float mSlideOffset;
   private float mParallaxOffset;
   int mSlideRange;
   boolean mIsUnableToDrag;
   private int mParallaxBy;
   private float mInitialMotionX;
   private float mInitialMotionY;
   private SlidingPaneLayout.PanelSlideListener mPanelSlideListener;
   final ViewDragHelper mDragHelper;
   boolean mPreservedOpenState;
   private boolean mFirstLayout;
   private final Rect mTmpRect;
   final ArrayList mPostedRunnables;
   static final SlidingPaneLayout.SlidingPanelLayoutImpl IMPL;

   public SlidingPaneLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public SlidingPaneLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public SlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      this.mSliderFadeColor = -858993460;
      this.mFirstLayout = true;
      this.mTmpRect = new Rect();
      this.mPostedRunnables = new ArrayList();
      float density = context.getResources().getDisplayMetrics().density;
      this.mOverhangSize = (int)(32.0F * density + 0.5F);
      this.setWillNotDraw(false);
      ViewCompat.setAccessibilityDelegate(this, new SlidingPaneLayout.AccessibilityDelegate());
      ViewCompat.setImportantForAccessibility(this, 1);
      this.mDragHelper = ViewDragHelper.create(this, 0.5F, new SlidingPaneLayout.DragHelperCallback());
      this.mDragHelper.setMinVelocity(400.0F * density);
   }

   public void setParallaxDistance(int parallaxBy) {
      this.mParallaxBy = parallaxBy;
      this.requestLayout();
   }

   public int getParallaxDistance() {
      return this.mParallaxBy;
   }

   public void setSliderFadeColor(@ColorInt int color) {
      this.mSliderFadeColor = color;
   }

   @ColorInt
   public int getSliderFadeColor() {
      return this.mSliderFadeColor;
   }

   public void setCoveredFadeColor(@ColorInt int color) {
      this.mCoveredFadeColor = color;
   }

   @ColorInt
   public int getCoveredFadeColor() {
      return this.mCoveredFadeColor;
   }

   public void setPanelSlideListener(SlidingPaneLayout.PanelSlideListener listener) {
      this.mPanelSlideListener = listener;
   }

   void dispatchOnPanelSlide(View panel) {
      if (this.mPanelSlideListener != null) {
         this.mPanelSlideListener.onPanelSlide(panel, this.mSlideOffset);
      }

   }

   void dispatchOnPanelOpened(View panel) {
      if (this.mPanelSlideListener != null) {
         this.mPanelSlideListener.onPanelOpened(panel);
      }

      this.sendAccessibilityEvent(32);
   }

   void dispatchOnPanelClosed(View panel) {
      if (this.mPanelSlideListener != null) {
         this.mPanelSlideListener.onPanelClosed(panel);
      }

      this.sendAccessibilityEvent(32);
   }

   void updateObscuredViewsVisibility(View panel) {
      boolean isLayoutRtl = this.isLayoutRtlSupport();
      int startBound = isLayoutRtl ? this.getWidth() - this.getPaddingRight() : this.getPaddingLeft();
      int endBound = isLayoutRtl ? this.getPaddingLeft() : this.getWidth() - this.getPaddingRight();
      int topBound = this.getPaddingTop();
      int bottomBound = this.getHeight() - this.getPaddingBottom();
      int left;
      int right;
      int top;
      int bottom;
      if (panel != null && viewIsOpaque(panel)) {
         left = panel.getLeft();
         right = panel.getRight();
         top = panel.getTop();
         bottom = panel.getBottom();
      } else {
         bottom = 0;
         top = 0;
         right = 0;
         left = 0;
      }

      int i = 0;

      for(int childCount = this.getChildCount(); i < childCount; ++i) {
         View child = this.getChildAt(i);
         if (child == panel) {
            break;
         }

         if (child.getVisibility() != 8) {
            int clampedChildLeft = Math.max(isLayoutRtl ? endBound : startBound, child.getLeft());
            int clampedChildTop = Math.max(topBound, child.getTop());
            int clampedChildRight = Math.min(isLayoutRtl ? startBound : endBound, child.getRight());
            int clampedChildBottom = Math.min(bottomBound, child.getBottom());
            byte vis;
            if (clampedChildLeft >= left && clampedChildTop >= top && clampedChildRight <= right && clampedChildBottom <= bottom) {
               vis = 4;
            } else {
               vis = 0;
            }

            child.setVisibility(vis);
         }
      }

   }

   void setAllChildrenVisible() {
      int i = 0;

      for(int childCount = this.getChildCount(); i < childCount; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() == 4) {
            child.setVisibility(0);
         }
      }

   }

   private static boolean viewIsOpaque(View v) {
      if (v.isOpaque()) {
         return true;
      } else if (VERSION.SDK_INT >= 18) {
         return false;
      } else {
         Drawable bg = v.getBackground();
         if (bg != null) {
            return bg.getOpacity() == -1;
         } else {
            return false;
         }
      }
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mFirstLayout = true;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.mFirstLayout = true;
      int i = 0;

      for(int count = this.mPostedRunnables.size(); i < count; ++i) {
         SlidingPaneLayout.DisableLayerRunnable dlr = (SlidingPaneLayout.DisableLayerRunnable)this.mPostedRunnables.get(i);
         dlr.run();
      }

      this.mPostedRunnables.clear();
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      if (widthMode != 1073741824) {
         if (!this.isInEditMode()) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
         }

         if (widthMode == Integer.MIN_VALUE) {
            widthMode = 1073741824;
         } else if (widthMode == 0) {
            widthMode = 1073741824;
            widthSize = 300;
         }
      } else if (heightMode == 0) {
         if (!this.isInEditMode()) {
            throw new IllegalStateException("Height must not be UNSPECIFIED");
         }

         if (heightMode == 0) {
            heightMode = Integer.MIN_VALUE;
            heightSize = 300;
         }
      }

      int layoutHeight = 0;
      int maxLayoutHeight = 0;
      switch(heightMode) {
      case Integer.MIN_VALUE:
         maxLayoutHeight = heightSize - this.getPaddingTop() - this.getPaddingBottom();
         break;
      case 1073741824:
         layoutHeight = maxLayoutHeight = heightSize - this.getPaddingTop() - this.getPaddingBottom();
      }

      float weightSum = 0.0F;
      boolean canSlide = false;
      int widthAvailable = widthSize - this.getPaddingLeft() - this.getPaddingRight();
      int widthRemaining = widthAvailable;
      int childCount = this.getChildCount();
      if (childCount > 2) {
         Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
      }

      this.mSlideableView = null;

      int fixedPanelWidthLimit;
      int measuredWidth;
      int childHeightSpec;
      int horizontalMargin;
      for(fixedPanelWidthLimit = 0; fixedPanelWidthLimit < childCount; ++fixedPanelWidthLimit) {
         View child = this.getChildAt(fixedPanelWidthLimit);
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)child.getLayoutParams();
         if (child.getVisibility() == 8) {
            lp.dimWhenOffset = false;
         } else {
            if (lp.weight > 0.0F) {
               weightSum += lp.weight;
               if (lp.width == 0) {
                  continue;
               }
            }

            int horizontalMargin = lp.leftMargin + lp.rightMargin;
            int childWidthSpec;
            if (lp.width == -2) {
               childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, Integer.MIN_VALUE);
            } else if (lp.width == -1) {
               childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, 1073741824);
            } else {
               childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, 1073741824);
            }

            if (lp.height == -2) {
               measuredWidth = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
            } else if (lp.height == -1) {
               measuredWidth = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
            } else {
               measuredWidth = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
            }

            child.measure(childWidthSpec, measuredWidth);
            childHeightSpec = child.getMeasuredWidth();
            horizontalMargin = child.getMeasuredHeight();
            if (heightMode == Integer.MIN_VALUE && horizontalMargin > layoutHeight) {
               layoutHeight = Math.min(horizontalMargin, maxLayoutHeight);
            }

            widthRemaining -= childHeightSpec;
            canSlide |= lp.slideable = widthRemaining < 0;
            if (lp.slideable) {
               this.mSlideableView = child;
            }
         }
      }

      int i;
      if (canSlide || weightSum > 0.0F) {
         fixedPanelWidthLimit = widthAvailable - this.mOverhangSize;

         for(i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
               SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)child.getLayoutParams();
               if (child.getVisibility() != 8) {
                  boolean skippedFirstPass = lp.width == 0 && lp.weight > 0.0F;
                  measuredWidth = skippedFirstPass ? 0 : child.getMeasuredWidth();
                  if (canSlide && child != this.mSlideableView) {
                     if (lp.width < 0 && (measuredWidth > fixedPanelWidthLimit || lp.weight > 0.0F)) {
                        if (skippedFirstPass) {
                           if (lp.height == -2) {
                              childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                           } else if (lp.height == -1) {
                              childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                           } else {
                              childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                           }
                        } else {
                           childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                        }

                        horizontalMargin = MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, 1073741824);
                        child.measure(horizontalMargin, childHeightSpec);
                     }
                  } else if (lp.weight > 0.0F) {
                     if (lp.width == 0) {
                        if (lp.height == -2) {
                           childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                        } else if (lp.height == -1) {
                           childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                        } else {
                           childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                        }
                     } else {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                     }

                     int newWidth;
                     int childWidthSpec;
                     if (canSlide) {
                        horizontalMargin = lp.leftMargin + lp.rightMargin;
                        newWidth = widthAvailable - horizontalMargin;
                        childWidthSpec = MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                        if (measuredWidth != newWidth) {
                           child.measure(childWidthSpec, childHeightSpec);
                        }
                     } else {
                        horizontalMargin = Math.max(0, widthRemaining);
                        newWidth = (int)(lp.weight * (float)horizontalMargin / weightSum);
                        childWidthSpec = MeasureSpec.makeMeasureSpec(measuredWidth + newWidth, 1073741824);
                        child.measure(childWidthSpec, childHeightSpec);
                     }
                  }
               }
            }
         }
      }

      i = layoutHeight + this.getPaddingTop() + this.getPaddingBottom();
      this.setMeasuredDimension(widthSize, i);
      this.mCanSlide = canSlide;
      if (this.mDragHelper.getViewDragState() != 0 && !canSlide) {
         this.mDragHelper.abort();
      }

   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      boolean isLayoutRtl = this.isLayoutRtlSupport();
      if (isLayoutRtl) {
         this.mDragHelper.setEdgeTrackingEnabled(2);
      } else {
         this.mDragHelper.setEdgeTrackingEnabled(1);
      }

      int width = r - l;
      int paddingStart = isLayoutRtl ? this.getPaddingRight() : this.getPaddingLeft();
      int paddingEnd = isLayoutRtl ? this.getPaddingLeft() : this.getPaddingRight();
      int paddingTop = this.getPaddingTop();
      int childCount = this.getChildCount();
      int xStart = paddingStart;
      int nextXStart = paddingStart;
      if (this.mFirstLayout) {
         this.mSlideOffset = this.mCanSlide && this.mPreservedOpenState ? 1.0F : 0.0F;
      }

      int i;
      for(i = 0; i < childCount; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int offset = 0;
            int childRight;
            int childLeft;
            int childBottom;
            if (lp.slideable) {
               childRight = lp.leftMargin + lp.rightMargin;
               childLeft = Math.min(nextXStart, width - paddingEnd - this.mOverhangSize) - xStart - childRight;
               this.mSlideRange = childLeft;
               int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
               lp.dimWhenOffset = xStart + lpMargin + childLeft + childWidth / 2 > width - paddingEnd;
               childBottom = (int)((float)childLeft * this.mSlideOffset);
               xStart += childBottom + lpMargin;
               this.mSlideOffset = (float)childBottom / (float)this.mSlideRange;
            } else if (this.mCanSlide && this.mParallaxBy != 0) {
               offset = (int)((1.0F - this.mSlideOffset) * (float)this.mParallaxBy);
               xStart = nextXStart;
            } else {
               xStart = nextXStart;
            }

            if (isLayoutRtl) {
               childRight = width - xStart + offset;
               childLeft = childRight - childWidth;
            } else {
               childLeft = xStart - offset;
               childRight = childLeft + childWidth;
            }

            childBottom = paddingTop + child.getMeasuredHeight();
            child.layout(childLeft, paddingTop, childRight, childBottom);
            nextXStart += child.getWidth();
         }
      }

      if (this.mFirstLayout) {
         if (this.mCanSlide) {
            if (this.mParallaxBy != 0) {
               this.parallaxOtherViews(this.mSlideOffset);
            }

            if (((SlidingPaneLayout.LayoutParams)this.mSlideableView.getLayoutParams()).dimWhenOffset) {
               this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
            }
         } else {
            for(i = 0; i < childCount; ++i) {
               this.dimChildView(this.getChildAt(i), 0.0F, this.mSliderFadeColor);
            }
         }

         this.updateObscuredViewsVisibility(this.mSlideableView);
      }

      this.mFirstLayout = false;
   }

   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      if (w != oldw) {
         this.mFirstLayout = true;
      }

   }

   public void requestChildFocus(View child, View focused) {
      super.requestChildFocus(child, focused);
      if (!this.isInTouchMode() && !this.mCanSlide) {
         this.mPreservedOpenState = child == this.mSlideableView;
      }

   }

   public boolean onInterceptTouchEvent(MotionEvent ev) {
      int action = ev.getActionMasked();
      if (!this.mCanSlide && action == 0 && this.getChildCount() > 1) {
         View secondChild = this.getChildAt(1);
         if (secondChild != null) {
            this.mPreservedOpenState = !this.mDragHelper.isViewUnder(secondChild, (int)ev.getX(), (int)ev.getY());
         }
      }

      if (!this.mCanSlide || this.mIsUnableToDrag && action != 0) {
         this.mDragHelper.cancel();
         return super.onInterceptTouchEvent(ev);
      } else if (action != 3 && action != 1) {
         boolean interceptTap = false;
         float x;
         float y;
         switch(action) {
         case 0:
            this.mIsUnableToDrag = false;
            x = ev.getX();
            y = ev.getY();
            this.mInitialMotionX = x;
            this.mInitialMotionY = y;
            if (this.mDragHelper.isViewUnder(this.mSlideableView, (int)x, (int)y) && this.isDimmed(this.mSlideableView)) {
               interceptTap = true;
            }
            break;
         case 2:
            x = ev.getX();
            y = ev.getY();
            float adx = Math.abs(x - this.mInitialMotionX);
            float ady = Math.abs(y - this.mInitialMotionY);
            int slop = this.mDragHelper.getTouchSlop();
            if (adx > (float)slop && ady > adx) {
               this.mDragHelper.cancel();
               this.mIsUnableToDrag = true;
               return false;
            }
         }

         boolean interceptForDrag = this.mDragHelper.shouldInterceptTouchEvent(ev);
         return interceptForDrag || interceptTap;
      } else {
         this.mDragHelper.cancel();
         return false;
      }
   }

   public boolean onTouchEvent(MotionEvent ev) {
      if (!this.mCanSlide) {
         return super.onTouchEvent(ev);
      } else {
         this.mDragHelper.processTouchEvent(ev);
         boolean wantTouchEvents = true;
         float x;
         float y;
         switch(ev.getActionMasked()) {
         case 0:
            x = ev.getX();
            y = ev.getY();
            this.mInitialMotionX = x;
            this.mInitialMotionY = y;
            break;
         case 1:
            if (this.isDimmed(this.mSlideableView)) {
               x = ev.getX();
               y = ev.getY();
               float dx = x - this.mInitialMotionX;
               float dy = y - this.mInitialMotionY;
               int slop = this.mDragHelper.getTouchSlop();
               if (dx * dx + dy * dy < (float)(slop * slop) && this.mDragHelper.isViewUnder(this.mSlideableView, (int)x, (int)y)) {
                  this.closePane(this.mSlideableView, 0);
               }
            }
         }

         return wantTouchEvents;
      }
   }

   private boolean closePane(View pane, int initialVelocity) {
      if (!this.mFirstLayout && !this.smoothSlideTo(0.0F, initialVelocity)) {
         return false;
      } else {
         this.mPreservedOpenState = false;
         return true;
      }
   }

   private boolean openPane(View pane, int initialVelocity) {
      if (!this.mFirstLayout && !this.smoothSlideTo(1.0F, initialVelocity)) {
         return false;
      } else {
         this.mPreservedOpenState = true;
         return true;
      }
   }

   /** @deprecated */
   @Deprecated
   public void smoothSlideOpen() {
      this.openPane();
   }

   public boolean openPane() {
      return this.openPane(this.mSlideableView, 0);
   }

   /** @deprecated */
   @Deprecated
   public void smoothSlideClosed() {
      this.closePane();
   }

   public boolean closePane() {
      return this.closePane(this.mSlideableView, 0);
   }

   public boolean isOpen() {
      return !this.mCanSlide || this.mSlideOffset == 1.0F;
   }

   /** @deprecated */
   @Deprecated
   public boolean canSlide() {
      return this.mCanSlide;
   }

   public boolean isSlideable() {
      return this.mCanSlide;
   }

   void onPanelDragged(int newLeft) {
      if (this.mSlideableView == null) {
         this.mSlideOffset = 0.0F;
      } else {
         boolean isLayoutRtl = this.isLayoutRtlSupport();
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)this.mSlideableView.getLayoutParams();
         int childWidth = this.mSlideableView.getWidth();
         int newStart = isLayoutRtl ? this.getWidth() - newLeft - childWidth : newLeft;
         int paddingStart = isLayoutRtl ? this.getPaddingRight() : this.getPaddingLeft();
         int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
         int startBound = paddingStart + lpMargin;
         this.mSlideOffset = (float)(newStart - startBound) / (float)this.mSlideRange;
         if (this.mParallaxBy != 0) {
            this.parallaxOtherViews(this.mSlideOffset);
         }

         if (lp.dimWhenOffset) {
            this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
         }

         this.dispatchOnPanelSlide(this.mSlideableView);
      }
   }

   private void dimChildView(View v, float mag, int fadeColor) {
      SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)v.getLayoutParams();
      if (mag > 0.0F && fadeColor != 0) {
         int baseAlpha = (fadeColor & -16777216) >>> 24;
         int imag = (int)((float)baseAlpha * mag);
         int color = imag << 24 | fadeColor & 16777215;
         if (lp.dimPaint == null) {
            lp.dimPaint = new Paint();
         }

         lp.dimPaint.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_OVER));
         if (v.getLayerType() != 2) {
            v.setLayerType(2, lp.dimPaint);
         }

         this.invalidateChildRegion(v);
      } else if (v.getLayerType() != 0) {
         if (lp.dimPaint != null) {
            lp.dimPaint.setColorFilter((ColorFilter)null);
         }

         SlidingPaneLayout.DisableLayerRunnable dlr = new SlidingPaneLayout.DisableLayerRunnable(v);
         this.mPostedRunnables.add(dlr);
         ViewCompat.postOnAnimation(this, dlr);
      }

   }

   protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
      SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)child.getLayoutParams();
      int save = canvas.save(2);
      if (this.mCanSlide && !lp.slideable && this.mSlideableView != null) {
         canvas.getClipBounds(this.mTmpRect);
         if (this.isLayoutRtlSupport()) {
            this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
         } else {
            this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
         }

         canvas.clipRect(this.mTmpRect);
      }

      boolean result = super.drawChild(canvas, child, drawingTime);
      canvas.restoreToCount(save);
      return result;
   }

   void invalidateChildRegion(View v) {
      IMPL.invalidateChildRegion(this, v);
   }

   boolean smoothSlideTo(float slideOffset, int velocity) {
      if (!this.mCanSlide) {
         return false;
      } else {
         boolean isLayoutRtl = this.isLayoutRtlSupport();
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)this.mSlideableView.getLayoutParams();
         int x;
         int startBound;
         if (isLayoutRtl) {
            startBound = this.getPaddingRight() + lp.rightMargin;
            int childWidth = this.mSlideableView.getWidth();
            x = (int)((float)this.getWidth() - ((float)startBound + slideOffset * (float)this.mSlideRange + (float)childWidth));
         } else {
            startBound = this.getPaddingLeft() + lp.leftMargin;
            x = (int)((float)startBound + slideOffset * (float)this.mSlideRange);
         }

         if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, x, this.mSlideableView.getTop())) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public void computeScroll() {
      if (this.mDragHelper.continueSettling(true)) {
         if (!this.mCanSlide) {
            this.mDragHelper.abort();
            return;
         }

         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setShadowDrawable(Drawable d) {
      this.setShadowDrawableLeft(d);
   }

   public void setShadowDrawableLeft(Drawable d) {
      this.mShadowDrawableLeft = d;
   }

   public void setShadowDrawableRight(Drawable d) {
      this.mShadowDrawableRight = d;
   }

   /** @deprecated */
   @Deprecated
   public void setShadowResource(@DrawableRes int resId) {
      this.setShadowDrawable(this.getResources().getDrawable(resId));
   }

   public void setShadowResourceLeft(int resId) {
      this.setShadowDrawableLeft(ContextCompat.getDrawable(this.getContext(), resId));
   }

   public void setShadowResourceRight(int resId) {
      this.setShadowDrawableRight(ContextCompat.getDrawable(this.getContext(), resId));
   }

   public void draw(Canvas c) {
      super.draw(c);
      boolean isLayoutRtl = this.isLayoutRtlSupport();
      Drawable shadowDrawable;
      if (isLayoutRtl) {
         shadowDrawable = this.mShadowDrawableRight;
      } else {
         shadowDrawable = this.mShadowDrawableLeft;
      }

      View shadowView = this.getChildCount() > 1 ? this.getChildAt(1) : null;
      if (shadowView != null && shadowDrawable != null) {
         int top = shadowView.getTop();
         int bottom = shadowView.getBottom();
         int shadowWidth = shadowDrawable.getIntrinsicWidth();
         int left;
         int right;
         if (this.isLayoutRtlSupport()) {
            left = shadowView.getRight();
            right = left + shadowWidth;
         } else {
            right = shadowView.getLeft();
            left = right - shadowWidth;
         }

         shadowDrawable.setBounds(left, top, right, bottom);
         shadowDrawable.draw(c);
      }
   }

   private void parallaxOtherViews(float slideOffset) {
      boolean isLayoutRtl = this.isLayoutRtlSupport();
      SlidingPaneLayout.LayoutParams slideLp = (SlidingPaneLayout.LayoutParams)this.mSlideableView.getLayoutParams();
      boolean dimViews = slideLp.dimWhenOffset && (isLayoutRtl ? slideLp.rightMargin : slideLp.leftMargin) <= 0;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View v = this.getChildAt(i);
         if (v != this.mSlideableView) {
            int oldOffset = (int)((1.0F - this.mParallaxOffset) * (float)this.mParallaxBy);
            this.mParallaxOffset = slideOffset;
            int newOffset = (int)((1.0F - slideOffset) * (float)this.mParallaxBy);
            int dx = oldOffset - newOffset;
            v.offsetLeftAndRight(isLayoutRtl ? -dx : dx);
            if (dimViews) {
               this.dimChildView(v, isLayoutRtl ? this.mParallaxOffset - 1.0F : 1.0F - this.mParallaxOffset, this.mCoveredFadeColor);
            }
         }
      }

   }

   protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
      if (v instanceof ViewGroup) {
         ViewGroup group = (ViewGroup)v;
         int scrollX = v.getScrollX();
         int scrollY = v.getScrollY();
         int count = group.getChildCount();

         for(int i = count - 1; i >= 0; --i) {
            View child = group.getChildAt(i);
            if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom() && this.canScroll(child, true, dx, x + scrollX - child.getLeft(), y + scrollY - child.getTop())) {
               return true;
            }
         }
      }

      return checkV && v.canScrollHorizontally(this.isLayoutRtlSupport() ? dx : -dx);
   }

   boolean isDimmed(View child) {
      if (child == null) {
         return false;
      } else {
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)child.getLayoutParams();
         return this.mCanSlide && lp.dimWhenOffset && this.mSlideOffset > 0.0F;
      }
   }

   protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return new SlidingPaneLayout.LayoutParams();
   }

   protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof MarginLayoutParams ? new SlidingPaneLayout.LayoutParams((MarginLayoutParams)p) : new SlidingPaneLayout.LayoutParams(p);
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof SlidingPaneLayout.LayoutParams && super.checkLayoutParams(p);
   }

   public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new SlidingPaneLayout.LayoutParams(this.getContext(), attrs);
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      SlidingPaneLayout.SavedState ss = new SlidingPaneLayout.SavedState(superState);
      ss.isOpen = this.isSlideable() ? this.isOpen() : this.mPreservedOpenState;
      return ss;
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof SlidingPaneLayout.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         SlidingPaneLayout.SavedState ss = (SlidingPaneLayout.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         if (ss.isOpen) {
            this.openPane();
         } else {
            this.closePane();
         }

         this.mPreservedOpenState = ss.isOpen;
      }
   }

   boolean isLayoutRtlSupport() {
      return ViewCompat.getLayoutDirection(this) == 1;
   }

   static {
      if (VERSION.SDK_INT >= 17) {
         IMPL = new SlidingPaneLayout.SlidingPanelLayoutImplJBMR1();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new SlidingPaneLayout.SlidingPanelLayoutImplJB();
      } else {
         IMPL = new SlidingPaneLayout.SlidingPanelLayoutImplBase();
      }

   }

   private class DisableLayerRunnable implements Runnable {
      final View mChildView;

      DisableLayerRunnable(View childView) {
         this.mChildView = childView;
      }

      public void run() {
         if (this.mChildView.getParent() == SlidingPaneLayout.this) {
            this.mChildView.setLayerType(0, (Paint)null);
            SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
         }

         SlidingPaneLayout.this.mPostedRunnables.remove(this);
      }
   }

   class AccessibilityDelegate extends AccessibilityDelegateCompat {
      private final Rect mTmpRect = new Rect();

      public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
         AccessibilityNodeInfoCompat superNode = AccessibilityNodeInfoCompat.obtain(info);
         super.onInitializeAccessibilityNodeInfo(host, superNode);
         this.copyNodeInfoNoChildren(info, superNode);
         superNode.recycle();
         info.setClassName(SlidingPaneLayout.class.getName());
         info.setSource(host);
         ViewParent parent = ViewCompat.getParentForAccessibility(host);
         if (parent instanceof View) {
            info.setParent((View)parent);
         }

         int childCount = SlidingPaneLayout.this.getChildCount();

         for(int i = 0; i < childCount; ++i) {
            View child = SlidingPaneLayout.this.getChildAt(i);
            if (!this.filter(child) && child.getVisibility() == 0) {
               ViewCompat.setImportantForAccessibility(child, 1);
               info.addChild(child);
            }
         }

      }

      public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
         super.onInitializeAccessibilityEvent(host, event);
         event.setClassName(SlidingPaneLayout.class.getName());
      }

      public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
         return !this.filter(child) ? super.onRequestSendAccessibilityEvent(host, child, event) : false;
      }

      public boolean filter(View child) {
         return SlidingPaneLayout.this.isDimmed(child);
      }

      private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat dest, AccessibilityNodeInfoCompat src) {
         Rect rect = this.mTmpRect;
         src.getBoundsInParent(rect);
         dest.setBoundsInParent(rect);
         src.getBoundsInScreen(rect);
         dest.setBoundsInScreen(rect);
         dest.setVisibleToUser(src.isVisibleToUser());
         dest.setPackageName(src.getPackageName());
         dest.setClassName(src.getClassName());
         dest.setContentDescription(src.getContentDescription());
         dest.setEnabled(src.isEnabled());
         dest.setClickable(src.isClickable());
         dest.setFocusable(src.isFocusable());
         dest.setFocused(src.isFocused());
         dest.setAccessibilityFocused(src.isAccessibilityFocused());
         dest.setSelected(src.isSelected());
         dest.setLongClickable(src.isLongClickable());
         dest.addAction(src.getActions());
         dest.setMovementGranularities(src.getMovementGranularities());
      }
   }

   @RequiresApi(17)
   static class SlidingPanelLayoutImplJBMR1 extends SlidingPaneLayout.SlidingPanelLayoutImplBase {
      public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
         ViewCompat.setLayerPaint(child, ((SlidingPaneLayout.LayoutParams)child.getLayoutParams()).dimPaint);
      }
   }

   @RequiresApi(16)
   static class SlidingPanelLayoutImplJB extends SlidingPaneLayout.SlidingPanelLayoutImplBase {
      private Method mGetDisplayList;
      private Field mRecreateDisplayList;

      SlidingPanelLayoutImplJB() {
         try {
            this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[])null);
         } catch (NoSuchMethodException var3) {
            Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", var3);
         }

         try {
            this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
            this.mRecreateDisplayList.setAccessible(true);
         } catch (NoSuchFieldException var2) {
            Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", var2);
         }

      }

      public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
         if (this.mGetDisplayList != null && this.mRecreateDisplayList != null) {
            try {
               this.mRecreateDisplayList.setBoolean(child, true);
               this.mGetDisplayList.invoke(child, (Object[])null);
            } catch (Exception var4) {
               Log.e("SlidingPaneLayout", "Error refreshing display list state", var4);
            }

            super.invalidateChildRegion(parent, child);
         } else {
            child.invalidate();
         }
      }
   }

   static class SlidingPanelLayoutImplBase implements SlidingPaneLayout.SlidingPanelLayoutImpl {
      public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
         ViewCompat.postInvalidateOnAnimation(parent, child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
      }
   }

   interface SlidingPanelLayoutImpl {
      void invalidateChildRegion(SlidingPaneLayout var1, View var2);
   }

   static class SavedState extends AbsSavedState {
      boolean isOpen;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public SlidingPaneLayout.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new SlidingPaneLayout.SavedState(in, (ClassLoader)null);
         }

         public SlidingPaneLayout.SavedState createFromParcel(Parcel in) {
            return new SlidingPaneLayout.SavedState(in, (ClassLoader)null);
         }

         public SlidingPaneLayout.SavedState[] newArray(int size) {
            return new SlidingPaneLayout.SavedState[size];
         }
      };

      SavedState(Parcelable superState) {
         super(superState);
      }

      SavedState(Parcel in, ClassLoader loader) {
         super(in, loader);
         this.isOpen = in.readInt() != 0;
      }

      public void writeToParcel(Parcel out, int flags) {
         super.writeToParcel(out, flags);
         out.writeInt(this.isOpen ? 1 : 0);
      }
   }

   public static class LayoutParams extends MarginLayoutParams {
      private static final int[] ATTRS = new int[]{16843137};
      public float weight = 0.0F;
      boolean slideable;
      boolean dimWhenOffset;
      Paint dimPaint;

      public LayoutParams() {
         super(-1, -1);
      }

      public LayoutParams(int width, int height) {
         super(width, height);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams source) {
         super(source);
      }

      public LayoutParams(MarginLayoutParams source) {
         super(source);
      }

      public LayoutParams(SlidingPaneLayout.LayoutParams source) {
         super(source);
         this.weight = source.weight;
      }

      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
         TypedArray a = c.obtainStyledAttributes(attrs, ATTRS);
         this.weight = a.getFloat(0, 0.0F);
         a.recycle();
      }
   }

   private class DragHelperCallback extends ViewDragHelper.Callback {
      public boolean tryCaptureView(View child, int pointerId) {
         return SlidingPaneLayout.this.mIsUnableToDrag ? false : ((SlidingPaneLayout.LayoutParams)child.getLayoutParams()).slideable;
      }

      public void onViewDragStateChanged(int state) {
         if (SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
            if (SlidingPaneLayout.this.mSlideOffset == 0.0F) {
               SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
               SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
               SlidingPaneLayout.this.mPreservedOpenState = false;
            } else {
               SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
               SlidingPaneLayout.this.mPreservedOpenState = true;
            }
         }

      }

      public void onViewCaptured(View capturedChild, int activePointerId) {
         SlidingPaneLayout.this.setAllChildrenVisible();
      }

      public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
         SlidingPaneLayout.this.onPanelDragged(left);
         SlidingPaneLayout.this.invalidate();
      }

      public void onViewReleased(View releasedChild, float xvel, float yvel) {
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)releasedChild.getLayoutParams();
         int left;
         if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
            int startToRight = SlidingPaneLayout.this.getPaddingRight() + lp.rightMargin;
            if (xvel < 0.0F || xvel == 0.0F && SlidingPaneLayout.this.mSlideOffset > 0.5F) {
               startToRight += SlidingPaneLayout.this.mSlideRange;
            }

            int childWidth = SlidingPaneLayout.this.mSlideableView.getWidth();
            left = SlidingPaneLayout.this.getWidth() - startToRight - childWidth;
         } else {
            left = SlidingPaneLayout.this.getPaddingLeft() + lp.leftMargin;
            if (xvel > 0.0F || xvel == 0.0F && SlidingPaneLayout.this.mSlideOffset > 0.5F) {
               left += SlidingPaneLayout.this.mSlideRange;
            }
         }

         SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
         SlidingPaneLayout.this.invalidate();
      }

      public int getViewHorizontalDragRange(View child) {
         return SlidingPaneLayout.this.mSlideRange;
      }

      public int clampViewPositionHorizontal(View child, int left, int dx) {
         SlidingPaneLayout.LayoutParams lp = (SlidingPaneLayout.LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
         int newLeft;
         int startBound;
         int endBound;
         if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
            startBound = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + lp.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth());
            endBound = startBound - SlidingPaneLayout.this.mSlideRange;
            newLeft = Math.max(Math.min(left, startBound), endBound);
         } else {
            startBound = SlidingPaneLayout.this.getPaddingLeft() + lp.leftMargin;
            endBound = startBound + SlidingPaneLayout.this.mSlideRange;
            newLeft = Math.min(Math.max(left, startBound), endBound);
         }

         return newLeft;
      }

      public int clampViewPositionVertical(View child, int top, int dy) {
         return child.getTop();
      }

      public void onEdgeDragStarted(int edgeFlags, int pointerId) {
         SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, pointerId);
      }
   }

   public static class SimplePanelSlideListener implements SlidingPaneLayout.PanelSlideListener {
      public void onPanelSlide(View panel, float slideOffset) {
      }

      public void onPanelOpened(View panel) {
      }

      public void onPanelClosed(View panel) {
      }
   }

   public interface PanelSlideListener {
      void onPanelSlide(View var1, float var2);

      void onPanelOpened(View var1);

      void onPanelClosed(View var1);
   }
}
