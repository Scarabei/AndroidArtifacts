package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarContainer extends FrameLayout {
   private boolean mIsTransitioning;
   private View mTabContainer;
   private View mActionBarView;
   private View mContextView;
   Drawable mBackground;
   Drawable mStackedBackground;
   Drawable mSplitBackground;
   boolean mIsSplit;
   boolean mIsStacked;
   private int mHeight;

   public ActionBarContainer(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActionBarContainer(Context context, AttributeSet attrs) {
      super(context, attrs);
      Drawable bg = VERSION.SDK_INT >= 21 ? new ActionBarBackgroundDrawableV21(this) : new ActionBarBackgroundDrawable(this);
      ViewCompat.setBackground(this, (Drawable)bg);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.ActionBar);
      this.mBackground = a.getDrawable(styleable.ActionBar_background);
      this.mStackedBackground = a.getDrawable(styleable.ActionBar_backgroundStacked);
      this.mHeight = a.getDimensionPixelSize(styleable.ActionBar_height, -1);
      if (this.getId() == id.split_action_bar) {
         this.mIsSplit = true;
         this.mSplitBackground = a.getDrawable(styleable.ActionBar_backgroundSplit);
      }

      a.recycle();
      this.setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
   }

   public void onFinishInflate() {
      super.onFinishInflate();
      this.mActionBarView = this.findViewById(id.action_bar);
      this.mContextView = this.findViewById(id.action_context_bar);
   }

   public void setPrimaryBackground(Drawable bg) {
      if (this.mBackground != null) {
         this.mBackground.setCallback((Callback)null);
         this.unscheduleDrawable(this.mBackground);
      }

      this.mBackground = bg;
      if (bg != null) {
         bg.setCallback(this);
         if (this.mActionBarView != null) {
            this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
         }
      }

      this.setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
      this.invalidate();
   }

   public void setStackedBackground(Drawable bg) {
      if (this.mStackedBackground != null) {
         this.mStackedBackground.setCallback((Callback)null);
         this.unscheduleDrawable(this.mStackedBackground);
      }

      this.mStackedBackground = bg;
      if (bg != null) {
         bg.setCallback(this);
         if (this.mIsStacked && this.mStackedBackground != null) {
            this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
         }
      }

      this.setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
      this.invalidate();
   }

   public void setSplitBackground(Drawable bg) {
      if (this.mSplitBackground != null) {
         this.mSplitBackground.setCallback((Callback)null);
         this.unscheduleDrawable(this.mSplitBackground);
      }

      this.mSplitBackground = bg;
      if (bg != null) {
         bg.setCallback(this);
         if (this.mIsSplit && this.mSplitBackground != null) {
            this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
         }
      }

      this.setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
      this.invalidate();
   }

   public void setVisibility(int visibility) {
      super.setVisibility(visibility);
      boolean isVisible = visibility == 0;
      if (this.mBackground != null) {
         this.mBackground.setVisible(isVisible, false);
      }

      if (this.mStackedBackground != null) {
         this.mStackedBackground.setVisible(isVisible, false);
      }

      if (this.mSplitBackground != null) {
         this.mSplitBackground.setVisible(isVisible, false);
      }

   }

   protected boolean verifyDrawable(Drawable who) {
      return who == this.mBackground && !this.mIsSplit || who == this.mStackedBackground && this.mIsStacked || who == this.mSplitBackground && this.mIsSplit || super.verifyDrawable(who);
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if (this.mBackground != null && this.mBackground.isStateful()) {
         this.mBackground.setState(this.getDrawableState());
      }

      if (this.mStackedBackground != null && this.mStackedBackground.isStateful()) {
         this.mStackedBackground.setState(this.getDrawableState());
      }

      if (this.mSplitBackground != null && this.mSplitBackground.isStateful()) {
         this.mSplitBackground.setState(this.getDrawableState());
      }

   }

   public void jumpDrawablesToCurrentState() {
      super.jumpDrawablesToCurrentState();
      if (this.mBackground != null) {
         this.mBackground.jumpToCurrentState();
      }

      if (this.mStackedBackground != null) {
         this.mStackedBackground.jumpToCurrentState();
      }

      if (this.mSplitBackground != null) {
         this.mSplitBackground.jumpToCurrentState();
      }

   }

   public void setTransitioning(boolean isTransitioning) {
      this.mIsTransitioning = isTransitioning;
      this.setDescendantFocusability(isTransitioning ? 393216 : 262144);
   }

   public boolean onInterceptTouchEvent(MotionEvent ev) {
      return this.mIsTransitioning || super.onInterceptTouchEvent(ev);
   }

   public boolean onTouchEvent(MotionEvent ev) {
      super.onTouchEvent(ev);
      return true;
   }

   public boolean onHoverEvent(MotionEvent ev) {
      super.onHoverEvent(ev);
      return true;
   }

   public void setTabContainer(ScrollingTabContainerView tabView) {
      if (this.mTabContainer != null) {
         this.removeView(this.mTabContainer);
      }

      this.mTabContainer = tabView;
      if (tabView != null) {
         this.addView(tabView);
         LayoutParams lp = tabView.getLayoutParams();
         lp.width = -1;
         lp.height = -2;
         tabView.setAllowCollapse(false);
      }

   }

   public View getTabContainer() {
      return this.mTabContainer;
   }

   public ActionMode startActionModeForChild(View child, android.view.ActionMode.Callback callback) {
      return null;
   }

   public ActionMode startActionModeForChild(View child, android.view.ActionMode.Callback callback, int type) {
      return type != 0 ? super.startActionModeForChild(child, callback, type) : null;
   }

   private boolean isCollapsed(View view) {
      return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
   }

   private int getMeasuredHeightWithMargins(View view) {
      android.widget.FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
      return view.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
   }

   public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      if (this.mActionBarView == null && MeasureSpec.getMode(heightMeasureSpec) == Integer.MIN_VALUE && this.mHeight >= 0) {
         heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.min(this.mHeight, MeasureSpec.getSize(heightMeasureSpec)), Integer.MIN_VALUE);
      }

      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      if (this.mActionBarView != null) {
         int mode = MeasureSpec.getMode(heightMeasureSpec);
         if (this.mTabContainer != null && this.mTabContainer.getVisibility() != 8 && mode != 1073741824) {
            int topMarginForTabs;
            if (!this.isCollapsed(this.mActionBarView)) {
               topMarginForTabs = this.getMeasuredHeightWithMargins(this.mActionBarView);
            } else if (!this.isCollapsed(this.mContextView)) {
               topMarginForTabs = this.getMeasuredHeightWithMargins(this.mContextView);
            } else {
               topMarginForTabs = 0;
            }

            int maxHeight = mode == Integer.MIN_VALUE ? MeasureSpec.getSize(heightMeasureSpec) : Integer.MAX_VALUE;
            this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(topMarginForTabs + this.getMeasuredHeightWithMargins(this.mTabContainer), maxHeight));
         }

      }
   }

   public void onLayout(boolean changed, int l, int t, int r, int b) {
      super.onLayout(changed, l, t, r, b);
      View tabContainer = this.mTabContainer;
      boolean hasTabs = tabContainer != null && tabContainer.getVisibility() != 8;
      if (tabContainer != null && tabContainer.getVisibility() != 8) {
         int containerHeight = this.getMeasuredHeight();
         android.widget.FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams)tabContainer.getLayoutParams();
         int tabHeight = tabContainer.getMeasuredHeight();
         tabContainer.layout(l, containerHeight - tabHeight - lp.bottomMargin, r, containerHeight - lp.bottomMargin);
      }

      boolean needsInvalidate = false;
      if (this.mIsSplit) {
         if (this.mSplitBackground != null) {
            this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            needsInvalidate = true;
         }
      } else {
         if (this.mBackground != null) {
            if (this.mActionBarView.getVisibility() == 0) {
               this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            } else if (this.mContextView != null && this.mContextView.getVisibility() == 0) {
               this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
            } else {
               this.mBackground.setBounds(0, 0, 0, 0);
            }

            needsInvalidate = true;
         }

         this.mIsStacked = hasTabs;
         if (hasTabs && this.mStackedBackground != null) {
            this.mStackedBackground.setBounds(tabContainer.getLeft(), tabContainer.getTop(), tabContainer.getRight(), tabContainer.getBottom());
            needsInvalidate = true;
         }
      }

      if (needsInvalidate) {
         this.invalidate();
      }

   }
}
