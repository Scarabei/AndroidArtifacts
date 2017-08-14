package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
   public static final int HORIZONTAL = 0;
   public static final int VERTICAL = 1;
   public static final int SHOW_DIVIDER_NONE = 0;
   public static final int SHOW_DIVIDER_BEGINNING = 1;
   public static final int SHOW_DIVIDER_MIDDLE = 2;
   public static final int SHOW_DIVIDER_END = 4;
   private boolean mBaselineAligned;
   private int mBaselineAlignedChildIndex;
   private int mBaselineChildTop;
   private int mOrientation;
   private int mGravity;
   private int mTotalLength;
   private float mWeightSum;
   private boolean mUseLargestChild;
   private int[] mMaxAscent;
   private int[] mMaxDescent;
   private static final int VERTICAL_GRAVITY_COUNT = 4;
   private static final int INDEX_CENTER_VERTICAL = 0;
   private static final int INDEX_TOP = 1;
   private static final int INDEX_BOTTOM = 2;
   private static final int INDEX_FILL = 3;
   private Drawable mDivider;
   private int mDividerWidth;
   private int mDividerHeight;
   private int mShowDividers;
   private int mDividerPadding;

   public LinearLayoutCompat(Context context) {
      this(context, (AttributeSet)null);
   }

   public LinearLayoutCompat(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public LinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mBaselineAligned = true;
      this.mBaselineAlignedChildIndex = -1;
      this.mBaselineChildTop = 0;
      this.mGravity = 8388659;
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.LinearLayoutCompat, defStyleAttr, 0);
      int index = a.getInt(styleable.LinearLayoutCompat_android_orientation, -1);
      if (index >= 0) {
         this.setOrientation(index);
      }

      index = a.getInt(styleable.LinearLayoutCompat_android_gravity, -1);
      if (index >= 0) {
         this.setGravity(index);
      }

      boolean baselineAligned = a.getBoolean(styleable.LinearLayoutCompat_android_baselineAligned, true);
      if (!baselineAligned) {
         this.setBaselineAligned(baselineAligned);
      }

      this.mWeightSum = a.getFloat(styleable.LinearLayoutCompat_android_weightSum, -1.0F);
      this.mBaselineAlignedChildIndex = a.getInt(styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
      this.mUseLargestChild = a.getBoolean(styleable.LinearLayoutCompat_measureWithLargestChild, false);
      this.setDividerDrawable(a.getDrawable(styleable.LinearLayoutCompat_divider));
      this.mShowDividers = a.getInt(styleable.LinearLayoutCompat_showDividers, 0);
      this.mDividerPadding = a.getDimensionPixelSize(styleable.LinearLayoutCompat_dividerPadding, 0);
      a.recycle();
   }

   public void setShowDividers(int showDividers) {
      if (showDividers != this.mShowDividers) {
         this.requestLayout();
      }

      this.mShowDividers = showDividers;
   }

   public boolean shouldDelayChildPressedState() {
      return false;
   }

   public int getShowDividers() {
      return this.mShowDividers;
   }

   public Drawable getDividerDrawable() {
      return this.mDivider;
   }

   public void setDividerDrawable(Drawable divider) {
      if (divider != this.mDivider) {
         this.mDivider = divider;
         if (divider != null) {
            this.mDividerWidth = divider.getIntrinsicWidth();
            this.mDividerHeight = divider.getIntrinsicHeight();
         } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
         }

         this.setWillNotDraw(divider == null);
         this.requestLayout();
      }
   }

   public void setDividerPadding(int padding) {
      this.mDividerPadding = padding;
   }

   public int getDividerPadding() {
      return this.mDividerPadding;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int getDividerWidth() {
      return this.mDividerWidth;
   }

   protected void onDraw(Canvas canvas) {
      if (this.mDivider != null) {
         if (this.mOrientation == 1) {
            this.drawDividersVertical(canvas);
         } else {
            this.drawDividersHorizontal(canvas);
         }

      }
   }

   void drawDividersVertical(Canvas canvas) {
      int count = this.getVirtualChildCount();

      LinearLayoutCompat.LayoutParams lp;
      for(int i = 0; i < count; ++i) {
         View child = this.getVirtualChildAt(i);
         if (child != null && child.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            int top = child.getTop() - lp.topMargin - this.mDividerHeight;
            this.drawHorizontalDivider(canvas, top);
         }
      }

      if (this.hasDividerBeforeChildAt(count)) {
         View child = this.getVirtualChildAt(count - 1);
         int bottom = false;
         int bottom;
         if (child == null) {
            bottom = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
         } else {
            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            bottom = child.getBottom() + lp.bottomMargin;
         }

         this.drawHorizontalDivider(canvas, bottom);
      }

   }

   void drawDividersHorizontal(Canvas canvas) {
      int count = this.getVirtualChildCount();
      boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);

      LinearLayoutCompat.LayoutParams lp;
      for(int i = 0; i < count; ++i) {
         View child = this.getVirtualChildAt(i);
         if (child != null && child.getVisibility() != 8 && this.hasDividerBeforeChildAt(i)) {
            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            int position;
            if (isLayoutRtl) {
               position = child.getRight() + lp.rightMargin;
            } else {
               position = child.getLeft() - lp.leftMargin - this.mDividerWidth;
            }

            this.drawVerticalDivider(canvas, position);
         }
      }

      if (this.hasDividerBeforeChildAt(count)) {
         View child = this.getVirtualChildAt(count - 1);
         int position;
         if (child == null) {
            if (isLayoutRtl) {
               position = this.getPaddingLeft();
            } else {
               position = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            }
         } else {
            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            if (isLayoutRtl) {
               position = child.getLeft() - lp.leftMargin - this.mDividerWidth;
            } else {
               position = child.getRight() + lp.rightMargin;
            }
         }

         this.drawVerticalDivider(canvas, position);
      }

   }

   void drawHorizontalDivider(Canvas canvas, int top) {
      this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, top, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, top + this.mDividerHeight);
      this.mDivider.draw(canvas);
   }

   void drawVerticalDivider(Canvas canvas, int left) {
      this.mDivider.setBounds(left, this.getPaddingTop() + this.mDividerPadding, left + this.mDividerWidth, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
      this.mDivider.draw(canvas);
   }

   public boolean isBaselineAligned() {
      return this.mBaselineAligned;
   }

   public void setBaselineAligned(boolean baselineAligned) {
      this.mBaselineAligned = baselineAligned;
   }

   public boolean isMeasureWithLargestChildEnabled() {
      return this.mUseLargestChild;
   }

   public void setMeasureWithLargestChildEnabled(boolean enabled) {
      this.mUseLargestChild = enabled;
   }

   public int getBaseline() {
      if (this.mBaselineAlignedChildIndex < 0) {
         return super.getBaseline();
      } else if (this.getChildCount() <= this.mBaselineAlignedChildIndex) {
         throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
      } else {
         View child = this.getChildAt(this.mBaselineAlignedChildIndex);
         int childBaseline = child.getBaseline();
         if (childBaseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
               return -1;
            } else {
               throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
         } else {
            int childTop = this.mBaselineChildTop;
            if (this.mOrientation == 1) {
               int majorGravity = this.mGravity & 112;
               if (majorGravity != 48) {
                  switch(majorGravity) {
                  case 16:
                     childTop += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2;
                     break;
                  case 80:
                     childTop = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength;
                  }
               }
            }

            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            return childTop + lp.topMargin + childBaseline;
         }
      }
   }

   public int getBaselineAlignedChildIndex() {
      return this.mBaselineAlignedChildIndex;
   }

   public void setBaselineAlignedChildIndex(int i) {
      if (i >= 0 && i < this.getChildCount()) {
         this.mBaselineAlignedChildIndex = i;
      } else {
         throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
      }
   }

   View getVirtualChildAt(int index) {
      return this.getChildAt(index);
   }

   int getVirtualChildCount() {
      return this.getChildCount();
   }

   public float getWeightSum() {
      return this.mWeightSum;
   }

   public void setWeightSum(float weightSum) {
      this.mWeightSum = Math.max(0.0F, weightSum);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      if (this.mOrientation == 1) {
         this.measureVertical(widthMeasureSpec, heightMeasureSpec);
      } else {
         this.measureHorizontal(widthMeasureSpec, heightMeasureSpec);
      }

   }

   protected boolean hasDividerBeforeChildAt(int childIndex) {
      if (childIndex == 0) {
         return (this.mShowDividers & 1) != 0;
      } else if (childIndex == this.getChildCount()) {
         return (this.mShowDividers & 4) != 0;
      } else if ((this.mShowDividers & 2) == 0) {
         return false;
      } else {
         boolean hasVisibleViewBefore = false;

         for(int i = childIndex - 1; i >= 0; --i) {
            if (this.getChildAt(i).getVisibility() != 8) {
               hasVisibleViewBefore = true;
               break;
            }
         }

         return hasVisibleViewBefore;
      }
   }

   void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
      this.mTotalLength = 0;
      int maxWidth = 0;
      int childState = 0;
      int alternativeMaxWidth = 0;
      int weightedMaxWidth = 0;
      boolean allFillParent = true;
      float totalWeight = 0.0F;
      int count = this.getVirtualChildCount();
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      boolean matchWidth = false;
      boolean skippedMeasure = false;
      int baselineChildIndex = this.mBaselineAlignedChildIndex;
      boolean useLargestChild = this.mUseLargestChild;
      int largestChildHeight = Integer.MIN_VALUE;

      int i;
      View child;
      LinearLayoutCompat.LayoutParams lp;
      int oldHeight;
      int margin;
      for(i = 0; i < count; ++i) {
         child = this.getVirtualChildAt(i);
         if (child == null) {
            this.mTotalLength += this.measureNullChild(i);
         } else if (child.getVisibility() == 8) {
            i += this.getChildrenSkipCount(child, i);
         } else {
            if (this.hasDividerBeforeChildAt(i)) {
               this.mTotalLength += this.mDividerHeight;
            }

            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            totalWeight += lp.weight;
            int measuredWidth;
            if (heightMode == 1073741824 && lp.height == 0 && lp.weight > 0.0F) {
               oldHeight = this.mTotalLength;
               this.mTotalLength = Math.max(oldHeight, oldHeight + lp.topMargin + lp.bottomMargin);
               skippedMeasure = true;
            } else {
               oldHeight = Integer.MIN_VALUE;
               if (lp.height == 0 && lp.weight > 0.0F) {
                  oldHeight = 0;
                  lp.height = -2;
               }

               this.measureChildBeforeLayout(child, i, widthMeasureSpec, 0, heightMeasureSpec, totalWeight == 0.0F ? this.mTotalLength : 0);
               if (oldHeight != Integer.MIN_VALUE) {
                  lp.height = oldHeight;
               }

               margin = child.getMeasuredHeight();
               measuredWidth = this.mTotalLength;
               this.mTotalLength = Math.max(measuredWidth, measuredWidth + margin + lp.topMargin + lp.bottomMargin + this.getNextLocationOffset(child));
               if (useLargestChild) {
                  largestChildHeight = Math.max(margin, largestChildHeight);
               }
            }

            if (baselineChildIndex >= 0 && baselineChildIndex == i + 1) {
               this.mBaselineChildTop = this.mTotalLength;
            }

            if (i < baselineChildIndex && lp.weight > 0.0F) {
               throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
            }

            boolean matchWidthLocally = false;
            if (widthMode != 1073741824 && lp.width == -1) {
               matchWidth = true;
               matchWidthLocally = true;
            }

            margin = lp.leftMargin + lp.rightMargin;
            measuredWidth = child.getMeasuredWidth() + margin;
            maxWidth = Math.max(maxWidth, measuredWidth);
            childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            allFillParent = allFillParent && lp.width == -1;
            if (lp.weight > 0.0F) {
               weightedMaxWidth = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
            } else {
               alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
            }

            i += this.getChildrenSkipCount(child, i);
         }
      }

      if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(count)) {
         this.mTotalLength += this.mDividerHeight;
      }

      if (useLargestChild && (heightMode == Integer.MIN_VALUE || heightMode == 0)) {
         this.mTotalLength = 0;

         for(i = 0; i < count; ++i) {
            child = this.getVirtualChildAt(i);
            if (child == null) {
               this.mTotalLength += this.measureNullChild(i);
            } else if (child.getVisibility() == 8) {
               i += this.getChildrenSkipCount(child, i);
            } else {
               lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
               oldHeight = this.mTotalLength;
               this.mTotalLength = Math.max(oldHeight, oldHeight + largestChildHeight + lp.topMargin + lp.bottomMargin + this.getNextLocationOffset(child));
            }
         }
      }

      this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
      i = this.mTotalLength;
      i = Math.max(i, this.getSuggestedMinimumHeight());
      int heightSizeAndState = View.resolveSizeAndState(i, heightMeasureSpec, 0);
      i = heightSizeAndState & 16777215;
      int delta = i - this.mTotalLength;
      if (skippedMeasure || delta != 0 && totalWeight > 0.0F) {
         float weightSum = this.mWeightSum > 0.0F ? this.mWeightSum : totalWeight;
         this.mTotalLength = 0;

         for(margin = 0; margin < count; ++margin) {
            View child = this.getVirtualChildAt(margin);
            if (child.getVisibility() != 8) {
               LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
               float childExtra = lp.weight;
               int share;
               int childWidthMeasureSpec;
               if (childExtra > 0.0F) {
                  share = (int)(childExtra * (float)delta / weightSum);
                  weightSum -= childExtra;
                  delta -= share;
                  childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, this.getPaddingLeft() + this.getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
                  if (lp.height == 0 && heightMode == 1073741824) {
                     child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, 1073741824));
                  } else {
                     int childHeight = child.getMeasuredHeight() + share;
                     if (childHeight < 0) {
                        childHeight = 0;
                     }

                     child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(childHeight, 1073741824));
                  }

                  childState = View.combineMeasuredStates(childState, child.getMeasuredState() & -256);
               }

               share = lp.leftMargin + lp.rightMargin;
               childWidthMeasureSpec = child.getMeasuredWidth() + share;
               maxWidth = Math.max(maxWidth, childWidthMeasureSpec);
               boolean matchWidthLocally = widthMode != 1073741824 && lp.width == -1;
               alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? share : childWidthMeasureSpec);
               allFillParent = allFillParent && lp.width == -1;
               int totalLength = this.mTotalLength;
               this.mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + this.getNextLocationOffset(child));
            }
         }

         this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
      } else {
         alternativeMaxWidth = Math.max(alternativeMaxWidth, weightedMaxWidth);
         if (useLargestChild && heightMode != 1073741824) {
            for(oldHeight = 0; oldHeight < count; ++oldHeight) {
               View child = this.getVirtualChildAt(oldHeight);
               if (child != null && child.getVisibility() != 8) {
                  LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
                  float childExtra = lp.weight;
                  if (childExtra > 0.0F) {
                     child.measure(MeasureSpec.makeMeasureSpec(child.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(largestChildHeight, 1073741824));
                  }
               }
            }
         }
      }

      if (!allFillParent && widthMode != 1073741824) {
         maxWidth = alternativeMaxWidth;
      }

      maxWidth += this.getPaddingLeft() + this.getPaddingRight();
      maxWidth = Math.max(maxWidth, this.getSuggestedMinimumWidth());
      this.setMeasuredDimension(View.resolveSizeAndState(maxWidth, widthMeasureSpec, childState), heightSizeAndState);
      if (matchWidth) {
         this.forceUniformWidth(count, heightMeasureSpec);
      }

   }

   private void forceUniformWidth(int count, int heightMeasureSpec) {
      int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);

      for(int i = 0; i < count; ++i) {
         View child = this.getVirtualChildAt(i);
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

   void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
      this.mTotalLength = 0;
      int maxHeight = 0;
      int childState = 0;
      int alternativeMaxHeight = 0;
      int weightedMaxHeight = 0;
      boolean allFillParent = true;
      float totalWeight = 0.0F;
      int count = this.getVirtualChildCount();
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      boolean matchHeight = false;
      boolean skippedMeasure = false;
      if (this.mMaxAscent == null || this.mMaxDescent == null) {
         this.mMaxAscent = new int[4];
         this.mMaxDescent = new int[4];
      }

      int[] maxAscent = this.mMaxAscent;
      int[] maxDescent = this.mMaxDescent;
      maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
      maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;
      boolean baselineAligned = this.mBaselineAligned;
      boolean useLargestChild = this.mUseLargestChild;
      boolean isExactly = widthMode == 1073741824;
      int largestChildWidth = Integer.MIN_VALUE;

      int i;
      View child;
      LinearLayoutCompat.LayoutParams lp;
      int totalLength;
      int margin;
      int childHeight;
      int index;
      for(i = 0; i < count; ++i) {
         child = this.getVirtualChildAt(i);
         if (child == null) {
            this.mTotalLength += this.measureNullChild(i);
         } else if (child.getVisibility() == 8) {
            i += this.getChildrenSkipCount(child, i);
         } else {
            if (this.hasDividerBeforeChildAt(i)) {
               this.mTotalLength += this.mDividerWidth;
            }

            lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            totalWeight += lp.weight;
            if (widthMode == 1073741824 && lp.width == 0 && lp.weight > 0.0F) {
               if (isExactly) {
                  this.mTotalLength += lp.leftMargin + lp.rightMargin;
               } else {
                  totalLength = this.mTotalLength;
                  this.mTotalLength = Math.max(totalLength, totalLength + lp.leftMargin + lp.rightMargin);
               }

               if (baselineAligned) {
                  totalLength = MeasureSpec.makeMeasureSpec(0, 0);
                  child.measure(totalLength, totalLength);
               } else {
                  skippedMeasure = true;
               }
            } else {
               totalLength = Integer.MIN_VALUE;
               if (lp.width == 0 && lp.weight > 0.0F) {
                  totalLength = 0;
                  lp.width = -2;
               }

               this.measureChildBeforeLayout(child, i, widthMeasureSpec, totalWeight == 0.0F ? this.mTotalLength : 0, heightMeasureSpec, 0);
               if (totalLength != Integer.MIN_VALUE) {
                  lp.width = totalLength;
               }

               margin = child.getMeasuredWidth();
               if (isExactly) {
                  this.mTotalLength += margin + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child);
               } else {
                  childHeight = this.mTotalLength;
                  this.mTotalLength = Math.max(childHeight, childHeight + margin + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child));
               }

               if (useLargestChild) {
                  largestChildWidth = Math.max(margin, largestChildWidth);
               }
            }

            boolean matchHeightLocally = false;
            if (heightMode != 1073741824 && lp.height == -1) {
               matchHeight = true;
               matchHeightLocally = true;
            }

            margin = lp.topMargin + lp.bottomMargin;
            childHeight = child.getMeasuredHeight() + margin;
            childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            if (baselineAligned) {
               int childBaseline = child.getBaseline();
               if (childBaseline != -1) {
                  int gravity = (lp.gravity < 0 ? this.mGravity : lp.gravity) & 112;
                  index = (gravity >> 4 & -2) >> 1;
                  maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                  maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
               }
            }

            maxHeight = Math.max(maxHeight, childHeight);
            allFillParent = allFillParent && lp.height == -1;
            if (lp.weight > 0.0F) {
               weightedMaxHeight = Math.max(weightedMaxHeight, matchHeightLocally ? margin : childHeight);
            } else {
               alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? margin : childHeight);
            }

            i += this.getChildrenSkipCount(child, i);
         }
      }

      if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(count)) {
         this.mTotalLength += this.mDividerWidth;
      }

      int widthSizeAndState;
      if (maxAscent[1] != -1 || maxAscent[0] != -1 || maxAscent[2] != -1 || maxAscent[3] != -1) {
         i = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
         widthSizeAndState = Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2])));
         maxHeight = Math.max(maxHeight, i + widthSizeAndState);
      }

      if (useLargestChild && (widthMode == Integer.MIN_VALUE || widthMode == 0)) {
         this.mTotalLength = 0;

         for(i = 0; i < count; ++i) {
            child = this.getVirtualChildAt(i);
            if (child == null) {
               this.mTotalLength += this.measureNullChild(i);
            } else if (child.getVisibility() == 8) {
               i += this.getChildrenSkipCount(child, i);
            } else {
               lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
               if (isExactly) {
                  this.mTotalLength += largestChildWidth + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child);
               } else {
                  totalLength = this.mTotalLength;
                  this.mTotalLength = Math.max(totalLength, totalLength + largestChildWidth + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child));
               }
            }
         }
      }

      this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
      i = this.mTotalLength;
      i = Math.max(i, this.getSuggestedMinimumWidth());
      widthSizeAndState = View.resolveSizeAndState(i, widthMeasureSpec, 0);
      i = widthSizeAndState & 16777215;
      int delta = i - this.mTotalLength;
      if (skippedMeasure || delta != 0 && totalWeight > 0.0F) {
         float weightSum = this.mWeightSum > 0.0F ? this.mWeightSum : totalWeight;
         maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
         maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;
         maxHeight = -1;
         this.mTotalLength = 0;

         for(margin = 0; margin < count; ++margin) {
            View child = this.getVirtualChildAt(margin);
            if (child != null && child.getVisibility() != 8) {
               LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
               float childExtra = lp.weight;
               int childHeightMeasureSpec;
               int childHeight;
               if (childExtra > 0.0F) {
                  index = (int)(childExtra * (float)delta / weightSum);
                  weightSum -= childExtra;
                  delta -= index;
                  childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, this.getPaddingTop() + this.getPaddingBottom() + lp.topMargin + lp.bottomMargin, lp.height);
                  if (lp.width == 0 && widthMode == 1073741824) {
                     child.measure(MeasureSpec.makeMeasureSpec(index > 0 ? index : 0, 1073741824), childHeightMeasureSpec);
                  } else {
                     childHeight = child.getMeasuredWidth() + index;
                     if (childHeight < 0) {
                        childHeight = 0;
                     }

                     child.measure(MeasureSpec.makeMeasureSpec(childHeight, 1073741824), childHeightMeasureSpec);
                  }

                  childState = View.combineMeasuredStates(childState, child.getMeasuredState() & -16777216);
               }

               if (isExactly) {
                  this.mTotalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child);
               } else {
                  index = this.mTotalLength;
                  this.mTotalLength = Math.max(index, index + child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + this.getNextLocationOffset(child));
               }

               boolean matchHeightLocally = heightMode != 1073741824 && lp.height == -1;
               childHeightMeasureSpec = lp.topMargin + lp.bottomMargin;
               childHeight = child.getMeasuredHeight() + childHeightMeasureSpec;
               maxHeight = Math.max(maxHeight, childHeight);
               alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? childHeightMeasureSpec : childHeight);
               allFillParent = allFillParent && lp.height == -1;
               if (baselineAligned) {
                  int childBaseline = child.getBaseline();
                  if (childBaseline != -1) {
                     int gravity = (lp.gravity < 0 ? this.mGravity : lp.gravity) & 112;
                     int index = (gravity >> 4 & -2) >> 1;
                     maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                     maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
                  }
               }
            }
         }

         this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
         if (maxAscent[1] != -1 || maxAscent[0] != -1 || maxAscent[2] != -1 || maxAscent[3] != -1) {
            margin = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
            childHeight = Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2])));
            maxHeight = Math.max(maxHeight, margin + childHeight);
         }
      } else {
         alternativeMaxHeight = Math.max(alternativeMaxHeight, weightedMaxHeight);
         if (useLargestChild && widthMode != 1073741824) {
            for(totalLength = 0; totalLength < count; ++totalLength) {
               View child = this.getVirtualChildAt(totalLength);
               if (child != null && child.getVisibility() != 8) {
                  LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
                  float childExtra = lp.weight;
                  if (childExtra > 0.0F) {
                     child.measure(MeasureSpec.makeMeasureSpec(largestChildWidth, 1073741824), MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824));
                  }
               }
            }
         }
      }

      if (!allFillParent && heightMode != 1073741824) {
         maxHeight = alternativeMaxHeight;
      }

      maxHeight += this.getPaddingTop() + this.getPaddingBottom();
      maxHeight = Math.max(maxHeight, this.getSuggestedMinimumHeight());
      this.setMeasuredDimension(widthSizeAndState | childState & -16777216, View.resolveSizeAndState(maxHeight, heightMeasureSpec, childState << 16));
      if (matchHeight) {
         this.forceUniformHeight(count, widthMeasureSpec);
      }

   }

   private void forceUniformHeight(int count, int widthMeasureSpec) {
      int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);

      for(int i = 0; i < count; ++i) {
         View child = this.getVirtualChildAt(i);
         if (child.getVisibility() != 8) {
            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            if (lp.height == -1) {
               int oldWidth = lp.width;
               lp.width = child.getMeasuredWidth();
               this.measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
               lp.width = oldWidth;
            }
         }
      }

   }

   int getChildrenSkipCount(View child, int index) {
      return 0;
   }

   int measureNullChild(int childIndex) {
      return 0;
   }

   void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
      this.measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
   }

   int getLocationOffset(View child) {
      return 0;
   }

   int getNextLocationOffset(View child) {
      return 0;
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      if (this.mOrientation == 1) {
         this.layoutVertical(l, t, r, b);
      } else {
         this.layoutHorizontal(l, t, r, b);
      }

   }

   void layoutVertical(int left, int top, int right, int bottom) {
      int paddingLeft = this.getPaddingLeft();
      int width = right - left;
      int childRight = width - this.getPaddingRight();
      int childSpace = width - paddingLeft - this.getPaddingRight();
      int count = this.getVirtualChildCount();
      int majorGravity = this.mGravity & 112;
      int minorGravity = this.mGravity & 8388615;
      int childTop;
      switch(majorGravity) {
      case 16:
         childTop = this.getPaddingTop() + (bottom - top - this.mTotalLength) / 2;
         break;
      case 48:
      default:
         childTop = this.getPaddingTop();
         break;
      case 80:
         childTop = this.getPaddingTop() + bottom - top - this.mTotalLength;
      }

      for(int i = 0; i < count; ++i) {
         View child = this.getVirtualChildAt(i);
         if (child == null) {
            childTop += this.measureNullChild(i);
         } else if (child.getVisibility() != 8) {
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            int gravity = lp.gravity;
            if (gravity < 0) {
               gravity = minorGravity;
            }

            int layoutDirection = ViewCompat.getLayoutDirection(this);
            int absoluteGravity = GravityCompat.getAbsoluteGravity(gravity, layoutDirection);
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
               childTop += this.mDividerHeight;
            }

            childTop += lp.topMargin;
            this.setChildFrame(child, childLeft, childTop + this.getLocationOffset(child), childWidth, childHeight);
            childTop += childHeight + lp.bottomMargin + this.getNextLocationOffset(child);
            i += this.getChildrenSkipCount(child, i);
         }
      }

   }

   void layoutHorizontal(int left, int top, int right, int bottom) {
      boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
      int paddingTop = this.getPaddingTop();
      int height = bottom - top;
      int childBottom = height - this.getPaddingBottom();
      int childSpace = height - paddingTop - this.getPaddingBottom();
      int count = this.getVirtualChildCount();
      int majorGravity = this.mGravity & 8388615;
      int minorGravity = this.mGravity & 112;
      boolean baselineAligned = this.mBaselineAligned;
      int[] maxAscent = this.mMaxAscent;
      int[] maxDescent = this.mMaxDescent;
      int layoutDirection = ViewCompat.getLayoutDirection(this);
      int childLeft;
      switch(GravityCompat.getAbsoluteGravity(majorGravity, layoutDirection)) {
      case 1:
         childLeft = this.getPaddingLeft() + (right - left - this.mTotalLength) / 2;
         break;
      case 2:
      case 3:
      case 4:
      default:
         childLeft = this.getPaddingLeft();
         break;
      case 5:
         childLeft = this.getPaddingLeft() + right - left - this.mTotalLength;
      }

      int start = 0;
      int dir = 1;
      if (isLayoutRtl) {
         start = count - 1;
         dir = -1;
      }

      for(int i = 0; i < count; ++i) {
         int childIndex = start + dir * i;
         View child = this.getVirtualChildAt(childIndex);
         if (child == null) {
            childLeft += this.measureNullChild(childIndex);
         } else if (child.getVisibility() != 8) {
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int childBaseline = -1;
            LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams)child.getLayoutParams();
            if (baselineAligned && lp.height != -1) {
               childBaseline = child.getBaseline();
            }

            int gravity = lp.gravity;
            if (gravity < 0) {
               gravity = minorGravity;
            }

            int childTop;
            switch(gravity & 112) {
            case 16:
               childTop = paddingTop + (childSpace - childHeight) / 2 + lp.topMargin - lp.bottomMargin;
               break;
            case 48:
               childTop = paddingTop + lp.topMargin;
               if (childBaseline != -1) {
                  childTop += maxAscent[1] - childBaseline;
               }
               break;
            case 80:
               childTop = childBottom - childHeight - lp.bottomMargin;
               if (childBaseline != -1) {
                  int descent = child.getMeasuredHeight() - childBaseline;
                  childTop -= maxDescent[2] - descent;
               }
               break;
            default:
               childTop = paddingTop;
            }

            if (this.hasDividerBeforeChildAt(childIndex)) {
               childLeft += this.mDividerWidth;
            }

            childLeft += lp.leftMargin;
            this.setChildFrame(child, childLeft + this.getLocationOffset(child), childTop, childWidth, childHeight);
            childLeft += childWidth + lp.rightMargin + this.getNextLocationOffset(child);
            i += this.getChildrenSkipCount(child, childIndex);
         }
      }

   }

   private void setChildFrame(View child, int left, int top, int width, int height) {
      child.layout(left, top, left + width, top + height);
   }

   public void setOrientation(int orientation) {
      if (this.mOrientation != orientation) {
         this.mOrientation = orientation;
         this.requestLayout();
      }

   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public void setGravity(int gravity) {
      if (this.mGravity != gravity) {
         if ((gravity & 8388615) == 0) {
            gravity |= 8388611;
         }

         if ((gravity & 112) == 0) {
            gravity |= 48;
         }

         this.mGravity = gravity;
         this.requestLayout();
      }

   }

   public int getGravity() {
      return this.mGravity;
   }

   public void setHorizontalGravity(int horizontalGravity) {
      int gravity = horizontalGravity & 8388615;
      if ((this.mGravity & 8388615) != gravity) {
         this.mGravity = this.mGravity & -8388616 | gravity;
         this.requestLayout();
      }

   }

   public void setVerticalGravity(int verticalGravity) {
      int gravity = verticalGravity & 112;
      if ((this.mGravity & 112) != gravity) {
         this.mGravity = this.mGravity & -113 | gravity;
         this.requestLayout();
      }

   }

   public LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new LinearLayoutCompat.LayoutParams(this.getContext(), attrs);
   }

   protected LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
      if (this.mOrientation == 0) {
         return new LinearLayoutCompat.LayoutParams(-2, -2);
      } else {
         return this.mOrientation == 1 ? new LinearLayoutCompat.LayoutParams(-1, -2) : null;
      }
   }

   protected LinearLayoutCompat.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return new LinearLayoutCompat.LayoutParams(p);
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof LinearLayoutCompat.LayoutParams;
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
      if (VERSION.SDK_INT >= 14) {
         super.onInitializeAccessibilityEvent(event);
         event.setClassName(LinearLayoutCompat.class.getName());
      }

   }

   public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
      if (VERSION.SDK_INT >= 14) {
         super.onInitializeAccessibilityNodeInfo(info);
         info.setClassName(LinearLayoutCompat.class.getName());
      }

   }

   public static class LayoutParams extends MarginLayoutParams {
      public float weight;
      public int gravity = -1;

      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
         TypedArray a = c.obtainStyledAttributes(attrs, styleable.LinearLayoutCompat_Layout);
         this.weight = a.getFloat(styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
         this.gravity = a.getInt(styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
         a.recycle();
      }

      public LayoutParams(int width, int height) {
         super(width, height);
         this.weight = 0.0F;
      }

      public LayoutParams(int width, int height, float weight) {
         super(width, height);
         this.weight = weight;
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams p) {
         super(p);
      }

      public LayoutParams(MarginLayoutParams source) {
         super(source);
      }

      public LayoutParams(LinearLayoutCompat.LayoutParams source) {
         super(source);
         this.weight = source.weight;
         this.gravity = source.gravity;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface DividerMode {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface OrientationMode {
   }
}
