package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
   private int mBaseline = -1;

   public BaselineLayout(Context context) {
      super(context, (AttributeSet)null, 0);
   }

   public BaselineLayout(Context context, AttributeSet attrs) {
      super(context, attrs, 0);
   }

   public BaselineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int count = this.getChildCount();
      int maxWidth = 0;
      int maxHeight = 0;
      int maxChildBaseline = -1;
      int maxChildDescent = -1;
      int childState = 0;

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int baseline = child.getBaseline();
            if (baseline != -1) {
               maxChildBaseline = Math.max(maxChildBaseline, baseline);
               maxChildDescent = Math.max(maxChildDescent, child.getMeasuredHeight() - baseline);
            }

            maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            childState = View.combineMeasuredStates(childState, child.getMeasuredState());
         }
      }

      if (maxChildBaseline != -1) {
         maxChildDescent = Math.max(maxChildDescent, this.getPaddingBottom());
         maxHeight = Math.max(maxHeight, maxChildBaseline + maxChildDescent);
         this.mBaseline = maxChildBaseline;
      }

      maxHeight = Math.max(maxHeight, this.getSuggestedMinimumHeight());
      maxWidth = Math.max(maxWidth, this.getSuggestedMinimumWidth());
      this.setMeasuredDimension(View.resolveSizeAndState(maxWidth, widthMeasureSpec, childState), View.resolveSizeAndState(maxHeight, heightMeasureSpec, childState << 16));
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      int count = this.getChildCount();
      int parentLeft = this.getPaddingLeft();
      int parentRight = right - left - this.getPaddingRight();
      int parentContentWidth = parentRight - parentLeft;
      int parentTop = this.getPaddingTop();

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int childLeft = parentLeft + (parentContentWidth - width) / 2;
            int childTop;
            if (this.mBaseline != -1 && child.getBaseline() != -1) {
               childTop = parentTop + this.mBaseline - child.getBaseline();
            } else {
               childTop = parentTop;
            }

            child.layout(childLeft, childTop, childLeft + width, childTop + height);
         }
      }

   }

   public int getBaseline() {
      return this.mBaseline;
   }
}
