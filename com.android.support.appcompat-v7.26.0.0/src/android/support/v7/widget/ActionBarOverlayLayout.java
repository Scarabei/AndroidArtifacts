package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window.Callback;
import android.widget.OverScroller;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent {
   private static final String TAG = "ActionBarOverlayLayout";
   private int mActionBarHeight;
   private int mWindowVisibility;
   private ContentFrameLayout mContent;
   ActionBarContainer mActionBarTop;
   private DecorToolbar mDecorToolbar;
   private Drawable mWindowContentOverlay;
   private boolean mIgnoreWindowContentOverlay;
   private boolean mOverlayMode;
   private boolean mHasNonEmbeddedTabs;
   private boolean mHideOnContentScroll;
   boolean mAnimatingForFling;
   private int mHideOnContentScrollReference;
   private int mLastSystemUiVisibility;
   private final Rect mBaseContentInsets;
   private final Rect mLastBaseContentInsets;
   private final Rect mContentInsets;
   private final Rect mBaseInnerInsets;
   private final Rect mLastBaseInnerInsets;
   private final Rect mInnerInsets;
   private final Rect mLastInnerInsets;
   private ActionBarOverlayLayout.ActionBarVisibilityCallback mActionBarVisibilityCallback;
   private final int ACTION_BAR_ANIMATE_DELAY;
   private OverScroller mFlingEstimator;
   ViewPropertyAnimator mCurrentActionBarTopAnimator;
   final AnimatorListenerAdapter mTopAnimatorListener;
   private final Runnable mRemoveActionBarHideOffset;
   private final Runnable mAddActionBarHideOffset;
   static final int[] ATTRS;
   private final NestedScrollingParentHelper mParentHelper;

   public ActionBarOverlayLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActionBarOverlayLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mWindowVisibility = 0;
      this.mBaseContentInsets = new Rect();
      this.mLastBaseContentInsets = new Rect();
      this.mContentInsets = new Rect();
      this.mBaseInnerInsets = new Rect();
      this.mLastBaseInnerInsets = new Rect();
      this.mInnerInsets = new Rect();
      this.mLastInnerInsets = new Rect();
      this.ACTION_BAR_ANIMATE_DELAY = 600;
      this.mTopAnimatorListener = new AnimatorListenerAdapter() {
         public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
         }

         public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
         }
      };
      this.mRemoveActionBarHideOffset = new Runnable() {
         public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY(0.0F).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
         }
      };
      this.mAddActionBarHideOffset = new Runnable() {
         public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY((float)(-ActionBarOverlayLayout.this.mActionBarTop.getHeight())).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
         }
      };
      this.init(context);
      this.mParentHelper = new NestedScrollingParentHelper(this);
   }

   private void init(Context context) {
      TypedArray ta = this.getContext().getTheme().obtainStyledAttributes(ATTRS);
      this.mActionBarHeight = ta.getDimensionPixelSize(0, 0);
      this.mWindowContentOverlay = ta.getDrawable(1);
      this.setWillNotDraw(this.mWindowContentOverlay == null);
      ta.recycle();
      this.mIgnoreWindowContentOverlay = context.getApplicationInfo().targetSdkVersion < 19;
      this.mFlingEstimator = new OverScroller(context);
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.haltActionBarHideOffsetAnimations();
   }

   public void setActionBarVisibilityCallback(ActionBarOverlayLayout.ActionBarVisibilityCallback cb) {
      this.mActionBarVisibilityCallback = cb;
      if (this.getWindowToken() != null) {
         this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
         if (this.mLastSystemUiVisibility != 0) {
            int newVis = this.mLastSystemUiVisibility;
            this.onWindowSystemUiVisibilityChanged(newVis);
            ViewCompat.requestApplyInsets(this);
         }
      }

   }

   public void setOverlayMode(boolean overlayMode) {
      this.mOverlayMode = overlayMode;
      this.mIgnoreWindowContentOverlay = overlayMode && this.getContext().getApplicationInfo().targetSdkVersion < 19;
   }

   public boolean isInOverlayMode() {
      return this.mOverlayMode;
   }

   public void setHasNonEmbeddedTabs(boolean hasNonEmbeddedTabs) {
      this.mHasNonEmbeddedTabs = hasNonEmbeddedTabs;
   }

   public void setShowingForActionMode(boolean showing) {
   }

   protected void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      this.init(this.getContext());
      ViewCompat.requestApplyInsets(this);
   }

   public void onWindowSystemUiVisibilityChanged(int visible) {
      if (VERSION.SDK_INT >= 16) {
         super.onWindowSystemUiVisibilityChanged(visible);
      }

      this.pullChildren();
      int diff = this.mLastSystemUiVisibility ^ visible;
      this.mLastSystemUiVisibility = visible;
      boolean barVisible = (visible & 4) == 0;
      boolean stable = (visible & 256) != 0;
      if (this.mActionBarVisibilityCallback != null) {
         this.mActionBarVisibilityCallback.enableContentAnimations(!stable);
         if (!barVisible && stable) {
            this.mActionBarVisibilityCallback.hideForSystem();
         } else {
            this.mActionBarVisibilityCallback.showForSystem();
         }
      }

      if ((diff & 256) != 0 && this.mActionBarVisibilityCallback != null) {
         ViewCompat.requestApplyInsets(this);
      }

   }

   protected void onWindowVisibilityChanged(int visibility) {
      super.onWindowVisibilityChanged(visibility);
      this.mWindowVisibility = visibility;
      if (this.mActionBarVisibilityCallback != null) {
         this.mActionBarVisibilityCallback.onWindowVisibilityChanged(visibility);
      }

   }

   private boolean applyInsets(View view, Rect insets, boolean left, boolean top, boolean bottom, boolean right) {
      boolean changed = false;
      ActionBarOverlayLayout.LayoutParams lp = (ActionBarOverlayLayout.LayoutParams)view.getLayoutParams();
      if (left && lp.leftMargin != insets.left) {
         changed = true;
         lp.leftMargin = insets.left;
      }

      if (top && lp.topMargin != insets.top) {
         changed = true;
         lp.topMargin = insets.top;
      }

      if (right && lp.rightMargin != insets.right) {
         changed = true;
         lp.rightMargin = insets.right;
      }

      if (bottom && lp.bottomMargin != insets.bottom) {
         changed = true;
         lp.bottomMargin = insets.bottom;
      }

      return changed;
   }

   protected boolean fitSystemWindows(Rect insets) {
      this.pullChildren();
      int vis = ViewCompat.getWindowSystemUiVisibility(this);
      boolean stable = (vis & 256) != 0;
      boolean changed = this.applyInsets(this.mActionBarTop, insets, true, true, false, true);
      this.mBaseInnerInsets.set(insets);
      ViewUtils.computeFitSystemWindows(this, this.mBaseInnerInsets, this.mBaseContentInsets);
      if (!this.mLastBaseInnerInsets.equals(this.mBaseInnerInsets)) {
         changed = true;
         this.mLastBaseInnerInsets.set(this.mBaseInnerInsets);
      }

      if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
         changed = true;
         this.mLastBaseContentInsets.set(this.mBaseContentInsets);
      }

      if (changed) {
         this.requestLayout();
      }

      return true;
   }

   protected ActionBarOverlayLayout.LayoutParams generateDefaultLayoutParams() {
      return new ActionBarOverlayLayout.LayoutParams(-1, -1);
   }

   public ActionBarOverlayLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new ActionBarOverlayLayout.LayoutParams(this.getContext(), attrs);
   }

   protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return new ActionBarOverlayLayout.LayoutParams(p);
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof ActionBarOverlayLayout.LayoutParams;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.pullChildren();
      int maxHeight = 0;
      int maxWidth = 0;
      int childState = 0;
      int topInset = 0;
      int bottomInset = 0;
      this.measureChildWithMargins(this.mActionBarTop, widthMeasureSpec, 0, heightMeasureSpec, 0);
      ActionBarOverlayLayout.LayoutParams lp = (ActionBarOverlayLayout.LayoutParams)this.mActionBarTop.getLayoutParams();
      int maxWidth = Math.max(maxWidth, this.mActionBarTop.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
      int maxHeight = Math.max(maxHeight, this.mActionBarTop.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
      int childState = View.combineMeasuredStates(childState, this.mActionBarTop.getMeasuredState());
      int vis = ViewCompat.getWindowSystemUiVisibility(this);
      boolean stable = (vis & 256) != 0;
      if (stable) {
         topInset = this.mActionBarHeight;
         if (this.mHasNonEmbeddedTabs) {
            View tabs = this.mActionBarTop.getTabContainer();
            if (tabs != null) {
               topInset += this.mActionBarHeight;
            }
         }
      } else if (this.mActionBarTop.getVisibility() != 8) {
         topInset = this.mActionBarTop.getMeasuredHeight();
      }

      this.mContentInsets.set(this.mBaseContentInsets);
      this.mInnerInsets.set(this.mBaseInnerInsets);
      if (!this.mOverlayMode && !stable) {
         this.mContentInsets.top += topInset;
         this.mContentInsets.bottom += bottomInset;
      } else {
         this.mInnerInsets.top += topInset;
         this.mInnerInsets.bottom += bottomInset;
      }

      this.applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
      if (!this.mLastInnerInsets.equals(this.mInnerInsets)) {
         this.mLastInnerInsets.set(this.mInnerInsets);
         this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
      }

      this.measureChildWithMargins(this.mContent, widthMeasureSpec, 0, heightMeasureSpec, 0);
      lp = (ActionBarOverlayLayout.LayoutParams)this.mContent.getLayoutParams();
      maxWidth = Math.max(maxWidth, this.mContent.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
      maxHeight = Math.max(maxHeight, this.mContent.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
      childState = View.combineMeasuredStates(childState, this.mContent.getMeasuredState());
      maxWidth += this.getPaddingLeft() + this.getPaddingRight();
      maxHeight += this.getPaddingTop() + this.getPaddingBottom();
      maxHeight = Math.max(maxHeight, this.getSuggestedMinimumHeight());
      maxWidth = Math.max(maxWidth, this.getSuggestedMinimumWidth());
      this.setMeasuredDimension(View.resolveSizeAndState(maxWidth, widthMeasureSpec, childState), View.resolveSizeAndState(maxHeight, heightMeasureSpec, childState << 16));
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      int count = this.getChildCount();
      int parentLeft = this.getPaddingLeft();
      int parentRight = right - left - this.getPaddingRight();
      int parentTop = this.getPaddingTop();
      int parentBottom = bottom - top - this.getPaddingBottom();

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            ActionBarOverlayLayout.LayoutParams lp = (ActionBarOverlayLayout.LayoutParams)child.getLayoutParams();
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int childLeft = parentLeft + lp.leftMargin;
            int childTop = parentTop + lp.topMargin;
            child.layout(childLeft, childTop, childLeft + width, childTop + height);
         }
      }

   }

   public void draw(Canvas c) {
      super.draw(c);
      if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
         int top = this.mActionBarTop.getVisibility() == 0 ? (int)((float)this.mActionBarTop.getBottom() + this.mActionBarTop.getTranslationY() + 0.5F) : 0;
         this.mWindowContentOverlay.setBounds(0, top, this.getWidth(), top + this.mWindowContentOverlay.getIntrinsicHeight());
         this.mWindowContentOverlay.draw(c);
      }

   }

   public boolean shouldDelayChildPressedState() {
      return false;
   }

   public boolean onStartNestedScroll(View child, View target, int axes) {
      return (axes & 2) != 0 && this.mActionBarTop.getVisibility() == 0 ? this.mHideOnContentScroll : false;
   }

   public void onNestedScrollAccepted(View child, View target, int axes) {
      this.mParentHelper.onNestedScrollAccepted(child, target, axes);
      this.mHideOnContentScrollReference = this.getActionBarHideOffset();
      this.haltActionBarHideOffsetAnimations();
      if (this.mActionBarVisibilityCallback != null) {
         this.mActionBarVisibilityCallback.onContentScrollStarted();
      }

   }

   public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
      this.mHideOnContentScrollReference += dyConsumed;
      this.setActionBarHideOffset(this.mHideOnContentScrollReference);
   }

   public void onStopNestedScroll(View target) {
      if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
         if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
            this.postRemoveActionBarHideOffset();
         } else {
            this.postAddActionBarHideOffset();
         }
      }

      if (this.mActionBarVisibilityCallback != null) {
         this.mActionBarVisibilityCallback.onContentScrollStopped();
      }

   }

   public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
      if (this.mHideOnContentScroll && consumed) {
         if (this.shouldHideActionBarOnFling(velocityX, velocityY)) {
            this.addActionBarHideOffset();
         } else {
            this.removeActionBarHideOffset();
         }

         this.mAnimatingForFling = true;
         return true;
      } else {
         return false;
      }
   }

   public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
   }

   public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
      return false;
   }

   public int getNestedScrollAxes() {
      return this.mParentHelper.getNestedScrollAxes();
   }

   void pullChildren() {
      if (this.mContent == null) {
         this.mContent = (ContentFrameLayout)this.findViewById(id.action_bar_activity_content);
         this.mActionBarTop = (ActionBarContainer)this.findViewById(id.action_bar_container);
         this.mDecorToolbar = this.getDecorToolbar(this.findViewById(id.action_bar));
      }

   }

   private DecorToolbar getDecorToolbar(View view) {
      if (view instanceof DecorToolbar) {
         return (DecorToolbar)view;
      } else if (view instanceof Toolbar) {
         return ((Toolbar)view).getWrapper();
      } else {
         throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
      }
   }

   public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
      if (hideOnContentScroll != this.mHideOnContentScroll) {
         this.mHideOnContentScroll = hideOnContentScroll;
         if (!hideOnContentScroll) {
            this.haltActionBarHideOffsetAnimations();
            this.setActionBarHideOffset(0);
         }
      }

   }

   public boolean isHideOnContentScrollEnabled() {
      return this.mHideOnContentScroll;
   }

   public int getActionBarHideOffset() {
      return this.mActionBarTop != null ? -((int)this.mActionBarTop.getTranslationY()) : 0;
   }

   public void setActionBarHideOffset(int offset) {
      this.haltActionBarHideOffsetAnimations();
      int topHeight = this.mActionBarTop.getHeight();
      offset = Math.max(0, Math.min(offset, topHeight));
      this.mActionBarTop.setTranslationY((float)(-offset));
   }

   void haltActionBarHideOffsetAnimations() {
      this.removeCallbacks(this.mRemoveActionBarHideOffset);
      this.removeCallbacks(this.mAddActionBarHideOffset);
      if (this.mCurrentActionBarTopAnimator != null) {
         this.mCurrentActionBarTopAnimator.cancel();
      }

   }

   private void postRemoveActionBarHideOffset() {
      this.haltActionBarHideOffsetAnimations();
      this.postDelayed(this.mRemoveActionBarHideOffset, 600L);
   }

   private void postAddActionBarHideOffset() {
      this.haltActionBarHideOffsetAnimations();
      this.postDelayed(this.mAddActionBarHideOffset, 600L);
   }

   private void removeActionBarHideOffset() {
      this.haltActionBarHideOffsetAnimations();
      this.mRemoveActionBarHideOffset.run();
   }

   private void addActionBarHideOffset() {
      this.haltActionBarHideOffsetAnimations();
      this.mAddActionBarHideOffset.run();
   }

   private boolean shouldHideActionBarOnFling(float velocityX, float velocityY) {
      this.mFlingEstimator.fling(0, 0, 0, (int)velocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
      int finalY = this.mFlingEstimator.getFinalY();
      return finalY > this.mActionBarTop.getHeight();
   }

   public void setWindowCallback(Callback cb) {
      this.pullChildren();
      this.mDecorToolbar.setWindowCallback(cb);
   }

   public void setWindowTitle(CharSequence title) {
      this.pullChildren();
      this.mDecorToolbar.setWindowTitle(title);
   }

   public CharSequence getTitle() {
      this.pullChildren();
      return this.mDecorToolbar.getTitle();
   }

   public void initFeature(int windowFeature) {
      this.pullChildren();
      switch(windowFeature) {
      case 2:
         this.mDecorToolbar.initProgress();
         break;
      case 5:
         this.mDecorToolbar.initIndeterminateProgress();
         break;
      case 109:
         this.setOverlayMode(true);
      }

   }

   public void setUiOptions(int uiOptions) {
   }

   public boolean hasIcon() {
      this.pullChildren();
      return this.mDecorToolbar.hasIcon();
   }

   public boolean hasLogo() {
      this.pullChildren();
      return this.mDecorToolbar.hasLogo();
   }

   public void setIcon(int resId) {
      this.pullChildren();
      this.mDecorToolbar.setIcon(resId);
   }

   public void setIcon(Drawable d) {
      this.pullChildren();
      this.mDecorToolbar.setIcon(d);
   }

   public void setLogo(int resId) {
      this.pullChildren();
      this.mDecorToolbar.setLogo(resId);
   }

   public boolean canShowOverflowMenu() {
      this.pullChildren();
      return this.mDecorToolbar.canShowOverflowMenu();
   }

   public boolean isOverflowMenuShowing() {
      this.pullChildren();
      return this.mDecorToolbar.isOverflowMenuShowing();
   }

   public boolean isOverflowMenuShowPending() {
      this.pullChildren();
      return this.mDecorToolbar.isOverflowMenuShowPending();
   }

   public boolean showOverflowMenu() {
      this.pullChildren();
      return this.mDecorToolbar.showOverflowMenu();
   }

   public boolean hideOverflowMenu() {
      this.pullChildren();
      return this.mDecorToolbar.hideOverflowMenu();
   }

   public void setMenuPrepared() {
      this.pullChildren();
      this.mDecorToolbar.setMenuPrepared();
   }

   public void setMenu(Menu menu, MenuPresenter.Callback cb) {
      this.pullChildren();
      this.mDecorToolbar.setMenu(menu, cb);
   }

   public void saveToolbarHierarchyState(SparseArray toolbarStates) {
      this.pullChildren();
      this.mDecorToolbar.saveHierarchyState(toolbarStates);
   }

   public void restoreToolbarHierarchyState(SparseArray toolbarStates) {
      this.pullChildren();
      this.mDecorToolbar.restoreHierarchyState(toolbarStates);
   }

   public void dismissPopups() {
      this.pullChildren();
      this.mDecorToolbar.dismissPopupMenus();
   }

   static {
      ATTRS = new int[]{attr.actionBarSize, 16842841};
   }

   public interface ActionBarVisibilityCallback {
      void onWindowVisibilityChanged(int var1);

      void showForSystem();

      void hideForSystem();

      void enableContentAnimations(boolean var1);

      void onContentScrollStarted();

      void onContentScrollStopped();
   }

   public static class LayoutParams extends MarginLayoutParams {
      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
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
   }
}
