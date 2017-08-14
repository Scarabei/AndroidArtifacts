package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.id;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AlertDialogLayout extends LinearLayoutCompat {
   public AlertDialogLayout(@Nullable Context context) {
      super(context);
   }

   public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      if (!this.tryOnMeasure(widthMeasureSpec, heightMeasureSpec)) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

   }

   private boolean tryOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      View topPanel = null;
      View buttonPanel = null;
      View middlePanel = null;
      int count = this.getChildCount();

      int heightMode;
      int widthMode;
      for(heightMode = 0; heightMode < count; ++heightMode) {
         View child = this.getChildAt(heightMode);
         if (child.getVisibility() != 8) {
            widthMode = child.getId();
            if (widthMode == id.topPanel) {
               topPanel = child;
            } else if (widthMode == id.buttonPanel) {
               buttonPanel = child;
            } else {
               if (widthMode != id.contentPanel && widthMode != id.customPanel) {
                  return false;
               }

               if (middlePanel != null) {
                  return false;
               }

               middlePanel = child;
            }
         }
      }

      heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int childState = 0;
      int usedHeight = this.getPaddingTop() + this.getPaddingBottom();
      if (topPanel != null) {
         topPanel.measure(widthMeasureSpec, 0);
         usedHeight += topPanel.getMeasuredHeight();
         childState = View.combineMeasuredStates(childState, topPanel.getMeasuredState());
      }

      int buttonHeight = 0;
      int buttonWantsHeight = 0;
      if (buttonPanel != null) {
         buttonPanel.measure(widthMeasureSpec, 0);
         buttonHeight = resolveMinimumHeight(buttonPanel);
         buttonWantsHeight = buttonPanel.getMeasuredHeight() - buttonHeight;
         usedHeight += buttonHeight;
         childState = View.combineMeasuredStates(childState, buttonPanel.getMeasuredState());
      }

      int middleHeight = 0;
      int remainingHeight;
      if (middlePanel != null) {
         if (heightMode == 0) {
            remainingHeight = 0;
         } else {
            remainingHeight = MeasureSpec.makeMeasureSpec(Math.max(0, heightSize - usedHeight), heightMode);
         }

         middlePanel.measure(widthMeasureSpec, remainingHeight);
         middleHeight = middlePanel.getMeasuredHeight();
         usedHeight += middleHeight;
         childState = View.combineMeasuredStates(childState, middlePanel.getMeasuredState());
      }

      remainingHeight = heightSize - usedHeight;
      int maxWidth;
      int widthSizeAndState;
      if (buttonPanel != null) {
         usedHeight -= buttonHeight;
         maxWidth = Math.min(remainingHeight, buttonWantsHeight);
         if (maxWidth > 0) {
            remainingHeight -= maxWidth;
            buttonHeight += maxWidth;
         }

         widthSizeAndState = MeasureSpec.makeMeasureSpec(buttonHeight, 1073741824);
         buttonPanel.measure(widthMeasureSpec, widthSizeAndState);
         usedHeight += buttonPanel.getMeasuredHeight();
         childState = View.combineMeasuredStates(childState, buttonPanel.getMeasuredState());
      }

      if (middlePanel != null && remainingHeight > 0) {
         usedHeight -= middleHeight;
         maxWidth = remainingHeight;
         remainingHeight -= remainingHeight;
         middleHeight += maxWidth;
         widthSizeAndState = MeasureSpec.makeMeasureSpec(middleHeight, heightMode);
         middlePanel.measure(widthMeasureSpec, widthSizeAndState);
         usedHeight += middlePanel.getMeasuredHeight();
         childState = View.combineMeasuredStates(childState, middlePanel.getMeasuredState());
      }

      maxWidth = 0;

      for(widthSizeAndState = 0; widthSizeAndState < count; ++widthSizeAndState) {
         View child = this.getChildAt(widthSizeAndState);
         if (child.getVisibility() != 8) {
            maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
         }
      }

      maxWidth += this.getPaddingLeft() + this.getPaddingRight();
      widthSizeAndState = View.resolveSizeAndState(maxWidth, widthMeasureSpec, childState);
      int heightSizeAndState = View.resolveSizeAndState(usedHeight, heightMeasureSpec, 0);
      this.setMeasuredDimension(widthSizeAndState, heightSizeAndState);
      if (widthMode != 1073741824) {
         this.forceUniformWidth(count, heightMeasureSpec);
      }

      return true;
   }

   private void forceUniformWidth(int count, int heightMeasureSpec) {
      int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            if (lp.width == -1) {
               int oldHeight = lp.height;
               lp.height = child.getMeasuredHeight();
               this.measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
               lp.height = oldHeight;
            }
         }
      }

   }

   private static int resolveMinimumHeight(View v) {
      int minHeight = ViewCompat.getMinimumHeight(v);
      if (minHeight > 0) {
         return minHeight;
      } else {
         if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup)v;
            if (vg.getChildCount() == 1) {
               return resolveMinimumHeight(vg.getChildAt(0));
            }
         }

         return 0;
      }
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      int paddingLeft = this.getPaddingLeft();
      int width = right - left;
      int childRight = width - this.getPaddingRight();
      int childSpace = width - paddingLeft - this.getPaddingRight();
      int totalLength = this.getMeasuredHeight();
      int count = this.getChildCount();
      int gravity = this.getGravity();
      int majorGravity = gravity & 112;
      int minorGravity = gravity & 8388615;
      int childTop;
      switch(majorGravity) {
      case 16:
         childTop = this.getPaddingTop() + (bottom - top - totalLength) / 2;
         break;
      case 48:
      default:
         childTop = this.getPaddingTop();
         break;
      case 80:
         childTop = this.getPaddingTop() + bottom - top - totalLength;
      }

      Drawable dividerDrawable = this.getDividerDrawable();
      int dividerHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child != null && child.getVisibility() != 8) {
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            int layoutGravity = lp.gravity;
            if (layoutGravity < 0) {
               layoutGravity = minorGravity;
            }

            int layoutDirection = ViewCompat.getLayoutDirection(this);
            int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutGravity, layoutDirection);
            int childLeft;
            switch(absoluteGravity & 7) {
            case 1:
               childLeft = paddingLeft + (childSpace - childWidth) / 2 + lp.leftMargin - lp.rightMargin;
               break;
            case 2:
            case 3:
            case 4:
            default:
               childLeft = paddingLeft + lp.leftMargin;
               break;
            case 5:
               childLeft = childRight - childWidth - lp.rightMargin;
            }

            if (this.hasDividerBeforeChildAt(i)) {
               childTop += dividerHeight;
            }

            childTop += lp.topMargin;
            this.setChildFrame(child, childLeft, childTop, childWidth, childHeight);
            childTop += childHeight + lp.bottomMargin;
         }
      }

   }

   private void setChildFrame(View child, int left, int top, int width, int height) {
      child.layout(left, top, left + width, top + height);
   }
}
