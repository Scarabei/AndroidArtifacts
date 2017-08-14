package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.math.MathUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior {
   final Rect mTempRect1 = new Rect();
   final Rect mTempRect2 = new Rect();
   private int mVerticalLayoutGap = 0;
   private int mOverlayTop;

   public HeaderScrollingViewBehavior() {
   }

   public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
      int childLpHeight = child.getLayoutParams().height;
      if (childLpHeight == -1 || childLpHeight == -2) {
         List dependencies = parent.getDependencies(child);
         View header = this.findFirstDependency(dependencies);
         if (header != null) {
            if (ViewCompat.getFitsSystemWindows(header) && !ViewCompat.getFitsSystemWindows(child)) {
               ViewCompat.setFitsSystemWindows(child, true);
               if (ViewCompat.getFitsSystemWindows(child)) {
                  child.requestLayout();
                  return true;
               }
            }

            int availableHeight = MeasureSpec.getSize(parentHeightMeasureSpec);
            if (availableHeight == 0) {
               availableHeight = parent.getHeight();
            }

            int height = availableHeight - header.getMeasuredHeight() + this.getScrollRange(header);
            int heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, childLpHeight == -1 ? 1073741824 : Integer.MIN_VALUE);
            parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            return true;
         }
      }

      return false;
   }

   protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
      List dependencies = parent.getDependencies(child);
      View header = this.findFirstDependency(dependencies);
      if (header != null) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         Rect available = this.mTempRect1;
         available.set(parent.getPaddingLeft() + lp.leftMargin, header.getBottom() + lp.topMargin, parent.getWidth() - parent.getPaddingRight() - lp.rightMargin, parent.getHeight() + header.getBottom() - parent.getPaddingBottom() - lp.bottomMargin);
         WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
         if (parentInsets != null && ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
            available.left += parentInsets.getSystemWindowInsetLeft();
            available.right -= parentInsets.getSystemWindowInsetRight();
         }

         Rect out = this.mTempRect2;
         GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), available, out, layoutDirection);
         int overlap = this.getOverlapPixelsForOffset(header);
         child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap);
         this.mVerticalLayoutGap = out.top - header.getBottom();
      } else {
         super.layoutChild(parent, child, layoutDirection);
         this.mVerticalLayoutGap = 0;
      }

   }

   float getOverlapRatioForOffset(View header) {
      return 1.0F;
   }

   final int getOverlapPixelsForOffset(View header) {
      return this.mOverlayTop == 0 ? 0 : MathUtils.clamp((int)(this.getOverlapRatioForOffset(header) * (float)this.mOverlayTop), 0, this.mOverlayTop);
   }

   private static int resolveGravity(int gravity) {
      return gravity == 0 ? 8388659 : gravity;
   }

   abstract View findFirstDependency(List var1);

   int getScrollRange(View v) {
      return v.getMeasuredHeight();
   }

   final int getVerticalLayoutGap() {
      return this.mVerticalLayoutGap;
   }

   public final void setOverlayTop(int overlayTop) {
      this.mOverlayTop = overlayTop;
   }

   public final int getOverlayTop() {
      return this.mOverlayTop;
   }
}
