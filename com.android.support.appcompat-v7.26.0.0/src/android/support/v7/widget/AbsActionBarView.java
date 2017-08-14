package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

abstract class AbsActionBarView extends ViewGroup {
   private static final int FADE_DURATION = 200;
   protected final AbsActionBarView.VisibilityAnimListener mVisAnimListener;
   protected final Context mPopupContext;
   protected ActionMenuView mMenuView;
   protected ActionMenuPresenter mActionMenuPresenter;
   protected int mContentHeight;
   protected ViewPropertyAnimatorCompat mVisibilityAnim;
   private boolean mEatingTouch;
   private boolean mEatingHover;

   AbsActionBarView(Context context) {
      this(context, (AttributeSet)null);
   }

   AbsActionBarView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   AbsActionBarView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      this.mVisAnimListener = new AbsActionBarView.VisibilityAnimListener();
      TypedValue tv = new TypedValue();
      if (context.getTheme().resolveAttribute(attr.actionBarPopupTheme, tv, true) && tv.resourceId != 0) {
         this.mPopupContext = new ContextThemeWrapper(context, tv.resourceId);
      } else {
         this.mPopupContext = context;
      }

   }

   protected void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      TypedArray a = this.getContext().obtainStyledAttributes((AttributeSet)null, styleable.ActionBar, attr.actionBarStyle, 0);
      this.setContentHeight(a.getLayoutDimension(styleable.ActionBar_height, 0));
      a.recycle();
      if (this.mActionMenuPresenter != null) {
         this.mActionMenuPresenter.onConfigurationChanged(newConfig);
      }

   }

   public boolean onTouchEvent(MotionEvent ev) {
      int action = ev.getActionMasked();
      if (action == 0) {
         this.mEatingTouch = false;
      }

      if (!this.mEatingTouch) {
         boolean handled = super.onTouchEvent(ev);
         if (action == 0 && !handled) {
            this.mEatingTouch = true;
         }
      }

      if (action == 1 || action == 3) {
         this.mEatingTouch = false;
      }

      return true;
   }

   public boolean onHoverEvent(MotionEvent ev) {
      int action = ev.getActionMasked();
      if (action == 9) {
         this.mEatingHover = false;
      }

      if (!this.mEatingHover) {
         boolean handled = super.onHoverEvent(ev);
         if (action == 9 && !handled) {
            this.mEatingHover = true;
         }
      }

      if (action == 10 || action == 3) {
         this.mEatingHover = false;
      }

      return true;
   }

   public void setContentHeight(int height) {
      this.mContentHeight = height;
      this.requestLayout();
   }

   public int getContentHeight() {
      return this.mContentHeight;
   }

   public int getAnimatedVisibility() {
      return this.mVisibilityAnim != null ? this.mVisAnimListener.mFinalVisibility : this.getVisibility();
   }

   public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int visibility, long duration) {
      if (this.mVisibilityAnim != null) {
         this.mVisibilityAnim.cancel();
      }

      ViewPropertyAnimatorCompat anim;
      if (visibility == 0) {
         if (this.getVisibility() != 0) {
            this.setAlpha(0.0F);
         }

         anim = ViewCompat.animate(this).alpha(1.0F);
         anim.setDuration(duration);
         anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
         return anim;
      } else {
         anim = ViewCompat.animate(this).alpha(0.0F);
         anim.setDuration(duration);
         anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
         return anim;
      }
   }

   public void animateToVisibility(int visibility) {
      ViewPropertyAnimatorCompat anim = this.setupAnimatorToVisibility(visibility, 200L);
      anim.start();
   }

   public void setVisibility(int visibility) {
      if (visibility != this.getVisibility()) {
         if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
         }

         super.setVisibility(visibility);
      }

   }

   public boolean showOverflowMenu() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.showOverflowMenu() : false;
   }

   public void postShowOverflowMenu() {
      this.post(new Runnable() {
         public void run() {
            AbsActionBarView.this.showOverflowMenu();
         }
      });
   }

   public boolean hideOverflowMenu() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.hideOverflowMenu() : false;
   }

   public boolean isOverflowMenuShowing() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowing() : false;
   }

   public boolean isOverflowMenuShowPending() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowPending() : false;
   }

   public boolean isOverflowReserved() {
      return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
   }

   public boolean canShowOverflowMenu() {
      return this.isOverflowReserved() && this.getVisibility() == 0;
   }

   public void dismissPopupMenus() {
      if (this.mActionMenuPresenter != null) {
         this.mActionMenuPresenter.dismissPopupMenus();
      }

   }

   protected int measureChildView(View child, int availableWidth, int childSpecHeight, int spacing) {
      child.measure(MeasureSpec.makeMeasureSpec(availableWidth, Integer.MIN_VALUE), childSpecHeight);
      availableWidth -= child.getMeasuredWidth();
      availableWidth -= spacing;
      return Math.max(0, availableWidth);
   }

   protected static int next(int x, int val, boolean isRtl) {
      return isRtl ? x - val : x + val;
   }

   protected int positionChild(View child, int x, int y, int contentHeight, boolean reverse) {
      int childWidth = child.getMeasuredWidth();
      int childHeight = child.getMeasuredHeight();
      int childTop = y + (contentHeight - childHeight) / 2;
      if (reverse) {
         child.layout(x - childWidth, childTop, x, childTop + childHeight);
      } else {
         child.layout(x, childTop, x + childWidth, childTop + childHeight);
      }

      return reverse ? -childWidth : childWidth;
   }

   protected class VisibilityAnimListener implements ViewPropertyAnimatorListener {
      private boolean mCanceled = false;
      int mFinalVisibility;

      public AbsActionBarView.VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat animation, int visibility) {
         AbsActionBarView.this.mVisibilityAnim = animation;
         this.mFinalVisibility = visibility;
         return this;
      }

      public void onAnimationStart(View view) {
         AbsActionBarView.super.setVisibility(0);
         this.mCanceled = false;
      }

      public void onAnimationEnd(View view) {
         if (!this.mCanceled) {
            AbsActionBarView.this.mVisibilityAnim = null;
            AbsActionBarView.super.setVisibility(this.mFinalVisibility);
         }
      }

      public void onAnimationCancel(View view) {
         this.mCanceled = true;
      }
   }
}
