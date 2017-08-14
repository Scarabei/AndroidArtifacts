package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ButtonBarLayout extends LinearLayout {
   private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
   private static final int PEEK_BUTTON_DP = 16;
   private boolean mAllowStacking;
   private int mLastWidthSize = -1;
   private int mMinimumHeight = 0;

   public ButtonBarLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
      boolean allowStackingDefault = this.getResources().getConfiguration().screenHeightDp >= 320;
      TypedArray ta = context.obtainStyledAttributes(attrs, styleable.ButtonBarLayout);
      this.mAllowStacking = ta.getBoolean(styleable.ButtonBarLayout_allowStacking, allowStackingDefault);
      ta.recycle();
   }

   public void setAllowStacking(boolean allowStacking) {
      if (this.mAllowStacking != allowStacking) {
         this.mAllowStacking = allowStacking;
         if (!this.mAllowStacking && this.getOrientation() == 1) {
            this.setStacked(false);
         }

         this.requestLayout();
      }

   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      if (this.mAllowStacking) {
         if (widthSize > this.mLastWidthSize && this.isStacked()) {
            this.setStacked(false);
         }

         this.mLastWidthSize = widthSize;
      }

      boolean needsRemeasure = false;
      int initialWidthMeasureSpec;
      if (!this.isStacked() && MeasureSpec.getMode(widthMeasureSpec) == 1073741824) {
         initialWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, Integer.MIN_VALUE);
         needsRemeasure = true;
      } else {
         initialWidthMeasureSpec = widthMeasureSpec;
      }

      super.onMeasure(initialWidthMeasureSpec, heightMeasureSpec);
      int firstVisible;
      if (this.mAllowStacking && !this.isStacked()) {
         firstVisible = this.getMeasuredWidthAndState();
         int measuredWidthState = firstVisible & -16777216;
         boolean stack = measuredWidthState == 16777216;
         if (stack) {
            this.setStacked(true);
            needsRemeasure = true;
         }
      }

      if (needsRemeasure) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

      int minHeight = 0;
      firstVisible = this.getNextVisibleChildIndex(0);
      if (firstVisible >= 0) {
         View firstButton = this.getChildAt(firstVisible);
         LayoutParams firstParams = (LayoutParams)firstButton.getLayoutParams();
         minHeight += this.getPaddingTop() + firstButton.getMeasuredHeight() + firstParams.topMargin + firstParams.bottomMargin;
         if (this.isStacked()) {
            int secondVisible = this.getNextVisibleChildIndex(firstVisible + 1);
            if (secondVisible >= 0) {
               minHeight += this.getChildAt(secondVisible).getPaddingTop() + (int)(16.0F * this.getResources().getDisplayMetrics().density);
            }
         } else {
            minHeight += this.getPaddingBottom();
         }
      }

      if (ViewCompat.getMinimumHeight(this) != minHeight) {
         this.setMinimumHeight(minHeight);
      }

   }

   private int getNextVisibleChildIndex(int index) {
      int i = index;

      for(int count = this.getChildCount(); i < count; ++i) {
         if (this.getChildAt(i).getVisibility() == 0) {
            return i;
         }
      }

      return -1;
   }

   public int getMinimumHeight() {
      return Math.max(this.mMinimumHeight, super.getMinimumHeight());
   }

   private void setStacked(boolean stacked) {
      this.setOrientation(stacked ? 1 : 0);
      this.setGravity(stacked ? 5 : 80);
      View spacer = this.findViewById(id.spacer);
      if (spacer != null) {
         spacer.setVisibility(stacked ? 8 : 4);
      }

      int childCount = this.getChildCount();

      for(int i = childCount - 2; i >= 0; --i) {
         this.bringChildToFront(this.getChildAt(i));
      }

   }

   private boolean isStacked() {
      return this.getOrientation() == 1;
   }
}
