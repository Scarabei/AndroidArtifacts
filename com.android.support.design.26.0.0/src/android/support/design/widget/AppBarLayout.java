package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.attr;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(AppBarLayout.Behavior.class)
public class AppBarLayout extends LinearLayout {
   static final int PENDING_ACTION_NONE = 0;
   static final int PENDING_ACTION_EXPANDED = 1;
   static final int PENDING_ACTION_COLLAPSED = 2;
   static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
   static final int PENDING_ACTION_FORCE = 8;
   private static final int INVALID_SCROLL_RANGE = -1;
   private int mTotalScrollRange;
   private int mDownPreScrollRange;
   private int mDownScrollRange;
   private boolean mHaveChildWithInterpolator;
   private int mPendingAction;
   private WindowInsetsCompat mLastInsets;
   private List mListeners;
   private boolean mCollapsible;
   private boolean mCollapsed;
   private int[] mTmpStatesArray;

   public AppBarLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public AppBarLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mTotalScrollRange = -1;
      this.mDownPreScrollRange = -1;
      this.mDownScrollRange = -1;
      this.mPendingAction = 0;
      this.setOrientation(1);
      ThemeUtils.checkAppCompatTheme(context);
      if (VERSION.SDK_INT >= 21) {
         ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
         ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, attrs, 0, style.Widget_Design_AppBarLayout);
      }

      TypedArray a = context.obtainStyledAttributes(attrs, styleable.AppBarLayout, 0, style.Widget_Design_AppBarLayout);
      ViewCompat.setBackground(this, a.getDrawable(styleable.AppBarLayout_android_background));
      if (a.hasValue(styleable.AppBarLayout_expanded)) {
         this.setExpanded(a.getBoolean(styleable.AppBarLayout_expanded, false), false, false);
      }

      if (VERSION.SDK_INT >= 21 && a.hasValue(styleable.AppBarLayout_elevation)) {
         ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, (float)a.getDimensionPixelSize(styleable.AppBarLayout_elevation, 0));
      }

      if (VERSION.SDK_INT >= 26) {
         if (a.hasValue(styleable.AppBarLayout_android_keyboardNavigationCluster)) {
            this.setKeyboardNavigationCluster(a.getBoolean(styleable.AppBarLayout_android_keyboardNavigationCluster, false));
         }

         if (a.hasValue(styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
            this.setTouchscreenBlocksFocus(a.getBoolean(styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
         }
      }

      a.recycle();
      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
         public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
            return AppBarLayout.this.onWindowInsetChanged(insets);
         }
      });
   }

   public void addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
      if (this.mListeners == null) {
         this.mListeners = new ArrayList();
      }

      if (listener != null && !this.mListeners.contains(listener)) {
         this.mListeners.add(listener);
      }

   }

   public void removeOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
      if (this.mListeners != null && listener != null) {
         this.mListeners.remove(listener);
      }

   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      this.invalidateScrollRanges();
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      super.onLayout(changed, l, t, r, b);
      this.invalidateScrollRanges();
      this.mHaveChildWithInterpolator = false;
      int i = 0;

      for(int z = this.getChildCount(); i < z; ++i) {
         View child = this.getChildAt(i);
         AppBarLayout.LayoutParams childLp = (AppBarLayout.LayoutParams)child.getLayoutParams();
         Interpolator interpolator = childLp.getScrollInterpolator();
         if (interpolator != null) {
            this.mHaveChildWithInterpolator = true;
            break;
         }
      }

      this.updateCollapsible();
   }

   private void updateCollapsible() {
      boolean haveCollapsibleChild = false;
      int i = 0;

      for(int z = this.getChildCount(); i < z; ++i) {
         if (((AppBarLayout.LayoutParams)this.getChildAt(i).getLayoutParams()).isCollapsible()) {
            haveCollapsibleChild = true;
            break;
         }
      }

      this.setCollapsibleState(haveCollapsibleChild);
   }

   private void invalidateScrollRanges() {
      this.mTotalScrollRange = -1;
      this.mDownPreScrollRange = -1;
      this.mDownScrollRange = -1;
   }

   public void setOrientation(int orientation) {
      if (orientation != 1) {
         throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
      } else {
         super.setOrientation(orientation);
      }
   }

   public void setExpanded(boolean expanded) {
      this.setExpanded(expanded, ViewCompat.isLaidOut(this));
   }

   public void setExpanded(boolean expanded, boolean animate) {
      this.setExpanded(expanded, animate, true);
   }

   private void setExpanded(boolean expanded, boolean animate, boolean force) {
      this.mPendingAction = (expanded ? 1 : 2) | (animate ? 4 : 0) | (force ? 8 : 0);
      this.requestLayout();
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof AppBarLayout.LayoutParams;
   }

   protected AppBarLayout.LayoutParams generateDefaultLayoutParams() {
      return new AppBarLayout.LayoutParams(-1, -2);
   }

   public AppBarLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new AppBarLayout.LayoutParams(this.getContext(), attrs);
   }

   protected AppBarLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      if (VERSION.SDK_INT >= 19 && p instanceof android.widget.LinearLayout.LayoutParams) {
         return new AppBarLayout.LayoutParams((android.widget.LinearLayout.LayoutParams)p);
      } else {
         return p instanceof MarginLayoutParams ? new AppBarLayout.LayoutParams((MarginLayoutParams)p) : new AppBarLayout.LayoutParams(p);
      }
   }

   boolean hasChildWithInterpolator() {
      return this.mHaveChildWithInterpolator;
   }

   public final int getTotalScrollRange() {
      if (this.mTotalScrollRange != -1) {
         return this.mTotalScrollRange;
      } else {
         int range = 0;
         int i = 0;

         for(int z = this.getChildCount(); i < z; ++i) {
            View child = this.getChildAt(i);
            AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams)child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.mScrollFlags;
            if ((flags & 1) == 0) {
               break;
            }

            range += childHeight + lp.topMargin + lp.bottomMargin;
            if ((flags & 2) != 0) {
               range -= ViewCompat.getMinimumHeight(child);
               break;
            }
         }

         return this.mTotalScrollRange = Math.max(0, range - this.getTopInset());
      }
   }

   boolean hasScrollableChildren() {
      return this.getTotalScrollRange() != 0;
   }

   int getUpNestedPreScrollRange() {
      return this.getTotalScrollRange();
   }

   int getDownNestedPreScrollRange() {
      if (this.mDownPreScrollRange != -1) {
         return this.mDownPreScrollRange;
      } else {
         int range = 0;

         for(int i = this.getChildCount() - 1; i >= 0; --i) {
            View child = this.getChildAt(i);
            AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams)child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.mScrollFlags;
            if ((flags & 5) == 5) {
               range += lp.topMargin + lp.bottomMargin;
               if ((flags & 8) != 0) {
                  range += ViewCompat.getMinimumHeight(child);
               } else if ((flags & 2) != 0) {
                  range += childHeight - ViewCompat.getMinimumHeight(child);
               } else {
                  range += childHeight - this.getTopInset();
               }
            } else if (range > 0) {
               break;
            }
         }

         return this.mDownPreScrollRange = Math.max(0, range);
      }
   }

   int getDownNestedScrollRange() {
      if (this.mDownScrollRange != -1) {
         return this.mDownScrollRange;
      } else {
         int range = 0;
         int i = 0;

         for(int z = this.getChildCount(); i < z; ++i) {
            View child = this.getChildAt(i);
            AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams)child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            childHeight += lp.topMargin + lp.bottomMargin;
            int flags = lp.mScrollFlags;
            if ((flags & 1) == 0) {
               break;
            }

            range += childHeight;
            if ((flags & 2) != 0) {
               range -= ViewCompat.getMinimumHeight(child) + this.getTopInset();
               break;
            }
         }

         return this.mDownScrollRange = Math.max(0, range);
      }
   }

   void dispatchOffsetUpdates(int offset) {
      if (this.mListeners != null) {
         int i = 0;

         for(int z = this.mListeners.size(); i < z; ++i) {
            AppBarLayout.OnOffsetChangedListener listener = (AppBarLayout.OnOffsetChangedListener)this.mListeners.get(i);
            if (listener != null) {
               listener.onOffsetChanged(this, offset);
            }
         }
      }

   }

   final int getMinimumHeightForVisibleOverlappingContent() {
      int topInset = this.getTopInset();
      int minHeight = ViewCompat.getMinimumHeight(this);
      if (minHeight != 0) {
         return minHeight * 2 + topInset;
      } else {
         int childCount = this.getChildCount();
         int lastChildMinHeight = childCount >= 1 ? ViewCompat.getMinimumHeight(this.getChildAt(childCount - 1)) : 0;
         return lastChildMinHeight != 0 ? lastChildMinHeight * 2 + topInset : this.getHeight() / 3;
      }
   }

   protected int[] onCreateDrawableState(int extraSpace) {
      if (this.mTmpStatesArray == null) {
         this.mTmpStatesArray = new int[2];
      }

      int[] extraStates = this.mTmpStatesArray;
      int[] states = super.onCreateDrawableState(extraSpace + extraStates.length);
      extraStates[0] = this.mCollapsible ? attr.state_collapsible : -attr.state_collapsible;
      extraStates[1] = this.mCollapsible && this.mCollapsed ? attr.state_collapsed : -attr.state_collapsed;
      return mergeDrawableStates(states, extraStates);
   }

   private boolean setCollapsibleState(boolean collapsible) {
      if (this.mCollapsible != collapsible) {
         this.mCollapsible = collapsible;
         this.refreshDrawableState();
         return true;
      } else {
         return false;
      }
   }

   boolean setCollapsedState(boolean collapsed) {
      if (this.mCollapsed != collapsed) {
         this.mCollapsed = collapsed;
         this.refreshDrawableState();
         return true;
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setTargetElevation(float elevation) {
      if (VERSION.SDK_INT >= 21) {
         ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, elevation);
      }

   }

   /** @deprecated */
   @Deprecated
   public float getTargetElevation() {
      return 0.0F;
   }

   int getPendingAction() {
      return this.mPendingAction;
   }

   void resetPendingAction() {
      this.mPendingAction = 0;
   }

   @VisibleForTesting
   final int getTopInset() {
      return this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
   }

   WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat insets) {
      WindowInsetsCompat newInsets = null;
      if (ViewCompat.getFitsSystemWindows(this)) {
         newInsets = insets;
      }

      if (!ObjectsCompat.equals(this.mLastInsets, newInsets)) {
         this.mLastInsets = newInsets;
         this.invalidateScrollRanges();
      }

      return insets;
   }

   public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
      public ScrollingViewBehavior() {
      }

      public ScrollingViewBehavior(Context context, AttributeSet attrs) {
         super(context, attrs);
         TypedArray a = context.obtainStyledAttributes(attrs, styleable.ScrollingViewBehavior_Layout);
         this.setOverlayTop(a.getDimensionPixelSize(styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
         a.recycle();
      }

      public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
         return dependency instanceof AppBarLayout;
      }

      public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
         this.offsetChildAsNeeded(parent, child, dependency);
         return false;
      }

      public boolean onRequestChildRectangleOnScreen(CoordinatorLayout parent, View child, Rect rectangle, boolean immediate) {
         AppBarLayout header = this.findFirstDependency(parent.getDependencies(child));
         if (header != null) {
            rectangle.offset(child.getLeft(), child.getTop());
            Rect parentRect = this.mTempRect1;
            parentRect.set(0, 0, parent.getWidth(), parent.getHeight());
            if (!parentRect.contains(rectangle)) {
               header.setExpanded(false, !immediate);
               return true;
            }
         }

         return false;
      }

      private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
         CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)dependency.getLayoutParams()).getBehavior();
         if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior ablBehavior = (AppBarLayout.Behavior)behavior;
            ViewCompat.offsetTopAndBottom(child, dependency.getBottom() - child.getTop() + ablBehavior.mOffsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(dependency));
         }

      }

      float getOverlapRatioForOffset(View header) {
         if (header instanceof AppBarLayout) {
            AppBarLayout abl = (AppBarLayout)header;
            int totalScrollRange = abl.getTotalScrollRange();
            int preScrollDown = abl.getDownNestedPreScrollRange();
            int offset = getAppBarLayoutOffset(abl);
            if (preScrollDown != 0 && totalScrollRange + offset <= preScrollDown) {
               return 0.0F;
            }

            int availScrollRange = totalScrollRange - preScrollDown;
            if (availScrollRange != 0) {
               return 1.0F + (float)offset / (float)availScrollRange;
            }
         }

         return 0.0F;
      }

      private static int getAppBarLayoutOffset(AppBarLayout abl) {
         CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)abl.getLayoutParams()).getBehavior();
         return behavior instanceof AppBarLayout.Behavior ? ((AppBarLayout.Behavior)behavior).getTopBottomOffsetForScrollingSibling() : 0;
      }

      AppBarLayout findFirstDependency(List views) {
         int i = 0;

         for(int z = views.size(); i < z; ++i) {
            View view = (View)views.get(i);
            if (view instanceof AppBarLayout) {
               return (AppBarLayout)view;
            }
         }

         return null;
      }

      int getScrollRange(View v) {
         return v instanceof AppBarLayout ? ((AppBarLayout)v).getTotalScrollRange() : super.getScrollRange(v);
      }
   }

   public static class Behavior extends HeaderBehavior {
      private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
      private static final int INVALID_POSITION = -1;
      private int mOffsetDelta;
      private ValueAnimator mOffsetAnimator;
      private int mOffsetToChildIndexOnLayout = -1;
      private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
      private float mOffsetToChildIndexOnLayoutPerc;
      private WeakReference mLastNestedScrollingChildRef;
      private AppBarLayout.Behavior.DragCallback mOnDragCallback;

      public Behavior() {
      }

      public Behavior(Context context, AttributeSet attrs) {
         super(context, attrs);
      }

      public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
         boolean started = (nestedScrollAxes & 2) != 0 && child.hasScrollableChildren() && parent.getHeight() - directTargetChild.getHeight() <= child.getHeight();
         if (started && this.mOffsetAnimator != null) {
            this.mOffsetAnimator.cancel();
         }

         this.mLastNestedScrollingChildRef = null;
         return started;
      }

      public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
         if (dy != 0) {
            int min;
            int max;
            if (dy < 0) {
               min = -child.getTotalScrollRange();
               max = min + child.getDownNestedPreScrollRange();
            } else {
               min = -child.getUpNestedPreScrollRange();
               max = 0;
            }

            if (min != max) {
               consumed[1] = this.scroll(coordinatorLayout, child, dy, min, max);
            }
         }

      }

      public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
         if (dyUnconsumed < 0) {
            this.scroll(coordinatorLayout, child, dyUnconsumed, -child.getDownNestedScrollRange(), 0);
         }

      }

      public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target, int type) {
         if (type == 0) {
            this.snapToChildIfNeeded(coordinatorLayout, abl);
         }

         this.mLastNestedScrollingChildRef = new WeakReference(target);
      }

      public void setDragCallback(@Nullable AppBarLayout.Behavior.DragCallback callback) {
         this.mOnDragCallback = callback;
      }

      private void animateOffsetTo(CoordinatorLayout coordinatorLayout, AppBarLayout child, int offset, float velocity) {
         int distance = Math.abs(this.getTopBottomOffsetForScrollingSibling() - offset);
         velocity = Math.abs(velocity);
         int duration;
         if (velocity > 0.0F) {
            duration = 3 * Math.round(1000.0F * ((float)distance / velocity));
         } else {
            float distanceRatio = (float)distance / (float)child.getHeight();
            duration = (int)((distanceRatio + 1.0F) * 150.0F);
         }

         this.animateOffsetWithDuration(coordinatorLayout, child, offset, duration);
      }

      private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final AppBarLayout child, int offset, int duration) {
         int currentOffset = this.getTopBottomOffsetForScrollingSibling();
         if (currentOffset == offset) {
            if (this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning()) {
               this.mOffsetAnimator.cancel();
            }

         } else {
            if (this.mOffsetAnimator == null) {
               this.mOffsetAnimator = new ValueAnimator();
               this.mOffsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
               this.mOffsetAnimator.addUpdateListener(new AnimatorUpdateListener() {
                  public void onAnimationUpdate(ValueAnimator animation) {
                     Behavior.this.setHeaderTopBottomOffset(coordinatorLayout, child, ((Integer)animation.getAnimatedValue()).intValue());
                  }
               });
            } else {
               this.mOffsetAnimator.cancel();
            }

            this.mOffsetAnimator.setDuration((long)Math.min(duration, 600));
            this.mOffsetAnimator.setIntValues(new int[]{currentOffset, offset});
            this.mOffsetAnimator.start();
         }
      }

      private int getChildIndexOnOffset(AppBarLayout abl, int offset) {
         int i = 0;

         for(int count = abl.getChildCount(); i < count; ++i) {
            View child = abl.getChildAt(i);
            if (child.getTop() <= -offset && child.getBottom() >= -offset) {
               return i;
            }
         }

         return -1;
      }

      private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, AppBarLayout abl) {
         int offset = this.getTopBottomOffsetForScrollingSibling();
         int offsetChildIndex = this.getChildIndexOnOffset(abl, offset);
         if (offsetChildIndex >= 0) {
            View offsetChild = abl.getChildAt(offsetChildIndex);
            AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams)offsetChild.getLayoutParams();
            int flags = lp.getScrollFlags();
            if ((flags & 17) == 17) {
               int snapTop = -offsetChild.getTop();
               int snapBottom = -offsetChild.getBottom();
               if (offsetChildIndex == abl.getChildCount() - 1) {
                  snapBottom += abl.getTopInset();
               }

               int seam;
               if (checkFlag(flags, 2)) {
                  snapBottom += ViewCompat.getMinimumHeight(offsetChild);
               } else if (checkFlag(flags, 5)) {
                  seam = snapBottom + ViewCompat.getMinimumHeight(offsetChild);
                  if (offset < seam) {
                     snapTop = seam;
                  } else {
                     snapBottom = seam;
                  }
               }

               seam = offset < (snapBottom + snapTop) / 2 ? snapBottom : snapTop;
               this.animateOffsetTo(coordinatorLayout, abl, MathUtils.clamp(seam, -abl.getTotalScrollRange(), 0), 0.0F);
            }
         }

      }

      private static boolean checkFlag(int flags, int check) {
         return (flags & check) == check;
      }

      public boolean onMeasureChild(CoordinatorLayout parent, AppBarLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         if (lp.height == -2) {
            parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, MeasureSpec.makeMeasureSpec(0, 0), heightUsed);
            return true;
         } else {
            return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
         }
      }

      public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
         boolean handled = super.onLayoutChild(parent, abl, layoutDirection);
         int pendingAction = abl.getPendingAction();
         int offset;
         if (this.mOffsetToChildIndexOnLayout >= 0 && (pendingAction & 8) == 0) {
            View child = abl.getChildAt(this.mOffsetToChildIndexOnLayout);
            offset = -child.getBottom();
            if (this.mOffsetToChildIndexOnLayoutIsMinHeight) {
               offset += ViewCompat.getMinimumHeight(child) + abl.getTopInset();
            } else {
               offset += Math.round((float)child.getHeight() * this.mOffsetToChildIndexOnLayoutPerc);
            }

            this.setHeaderTopBottomOffset(parent, abl, offset);
         } else if (pendingAction != 0) {
            boolean animate = (pendingAction & 4) != 0;
            if ((pendingAction & 2) != 0) {
               offset = -abl.getUpNestedPreScrollRange();
               if (animate) {
                  this.animateOffsetTo(parent, abl, offset, 0.0F);
               } else {
                  this.setHeaderTopBottomOffset(parent, abl, offset);
               }
            } else if ((pendingAction & 1) != 0) {
               if (animate) {
                  this.animateOffsetTo(parent, abl, 0, 0.0F);
               } else {
                  this.setHeaderTopBottomOffset(parent, abl, 0);
               }
            }
         }

         abl.resetPendingAction();
         this.mOffsetToChildIndexOnLayout = -1;
         this.setTopAndBottomOffset(MathUtils.clamp(this.getTopAndBottomOffset(), -abl.getTotalScrollRange(), 0));
         this.updateAppBarLayoutDrawableState(parent, abl, this.getTopAndBottomOffset(), 0, true);
         abl.dispatchOffsetUpdates(this.getTopAndBottomOffset());
         return handled;
      }

      boolean canDragView(AppBarLayout view) {
         if (this.mOnDragCallback != null) {
            return this.mOnDragCallback.canDrag(view);
         } else if (this.mLastNestedScrollingChildRef == null) {
            return true;
         } else {
            View scrollingView = (View)this.mLastNestedScrollingChildRef.get();
            return scrollingView != null && scrollingView.isShown() && !scrollingView.canScrollVertically(-1);
         }
      }

      void onFlingFinished(CoordinatorLayout parent, AppBarLayout layout) {
         this.snapToChildIfNeeded(parent, layout);
      }

      int getMaxDragOffset(AppBarLayout view) {
         return -view.getDownNestedScrollRange();
      }

      int getScrollRangeForDragFling(AppBarLayout view) {
         return view.getTotalScrollRange();
      }

      int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int newOffset, int minOffset, int maxOffset) {
         int curOffset = this.getTopBottomOffsetForScrollingSibling();
         int consumed = 0;
         if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset) {
            newOffset = MathUtils.clamp(newOffset, minOffset, maxOffset);
            if (curOffset != newOffset) {
               int interpolatedOffset = appBarLayout.hasChildWithInterpolator() ? this.interpolateOffset(appBarLayout, newOffset) : newOffset;
               boolean offsetChanged = this.setTopAndBottomOffset(interpolatedOffset);
               consumed = curOffset - newOffset;
               this.mOffsetDelta = newOffset - interpolatedOffset;
               if (!offsetChanged && appBarLayout.hasChildWithInterpolator()) {
                  coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
               }

               appBarLayout.dispatchOffsetUpdates(this.getTopAndBottomOffset());
               this.updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, newOffset, newOffset < curOffset ? -1 : 1, false);
            }
         } else {
            this.mOffsetDelta = 0;
         }

         return consumed;
      }

      @VisibleForTesting
      boolean isOffsetAnimatorRunning() {
         return this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning();
      }

      private int interpolateOffset(AppBarLayout layout, int offset) {
         int absOffset = Math.abs(offset);
         int i = 0;

         for(int z = layout.getChildCount(); i < z; ++i) {
            View child = layout.getChildAt(i);
            AppBarLayout.LayoutParams childLp = (AppBarLayout.LayoutParams)child.getLayoutParams();
            Interpolator interpolator = childLp.getScrollInterpolator();
            if (absOffset >= child.getTop() && absOffset <= child.getBottom()) {
               if (interpolator != null) {
                  int childScrollableHeight = 0;
                  int flags = childLp.getScrollFlags();
                  if ((flags & 1) != 0) {
                     childScrollableHeight += child.getHeight() + childLp.topMargin + childLp.bottomMargin;
                     if ((flags & 2) != 0) {
                        childScrollableHeight -= ViewCompat.getMinimumHeight(child);
                     }
                  }

                  if (ViewCompat.getFitsSystemWindows(child)) {
                     childScrollableHeight -= layout.getTopInset();
                  }

                  if (childScrollableHeight > 0) {
                     int offsetForView = absOffset - child.getTop();
                     int interpolatedDiff = Math.round((float)childScrollableHeight * interpolator.getInterpolation((float)offsetForView / (float)childScrollableHeight));
                     return Integer.signum(offset) * (child.getTop() + interpolatedDiff);
                  }
               }
               break;
            }
         }

         return offset;
      }

      private void updateAppBarLayoutDrawableState(CoordinatorLayout parent, AppBarLayout layout, int offset, int direction, boolean forceJump) {
         View child = getAppBarChildOnOffset(layout, offset);
         if (child != null) {
            AppBarLayout.LayoutParams childLp = (AppBarLayout.LayoutParams)child.getLayoutParams();
            int flags = childLp.getScrollFlags();
            boolean collapsed = false;
            if ((flags & 1) != 0) {
               int minHeight = ViewCompat.getMinimumHeight(child);
               if (direction > 0 && (flags & 12) != 0) {
                  collapsed = -offset >= child.getBottom() - minHeight - layout.getTopInset();
               } else if ((flags & 2) != 0) {
                  collapsed = -offset >= child.getBottom() - minHeight - layout.getTopInset();
               }
            }

            boolean changed = layout.setCollapsedState(collapsed);
            if (VERSION.SDK_INT >= 11 && (forceJump || changed && this.shouldJumpElevationState(parent, layout))) {
               layout.jumpDrawablesToCurrentState();
            }
         }

      }

      private boolean shouldJumpElevationState(CoordinatorLayout parent, AppBarLayout layout) {
         List dependencies = parent.getDependents(layout);
         int i = 0;

         for(int size = dependencies.size(); i < size; ++i) {
            View dependency = (View)dependencies.get(i);
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)dependency.getLayoutParams();
            CoordinatorLayout.Behavior behavior = lp.getBehavior();
            if (behavior instanceof AppBarLayout.ScrollingViewBehavior) {
               return ((AppBarLayout.ScrollingViewBehavior)behavior).getOverlayTop() != 0;
            }
         }

         return false;
      }

      private static View getAppBarChildOnOffset(AppBarLayout layout, int offset) {
         int absOffset = Math.abs(offset);
         int i = 0;

         for(int z = layout.getChildCount(); i < z; ++i) {
            View child = layout.getChildAt(i);
            if (absOffset >= child.getTop() && absOffset <= child.getBottom()) {
               return child;
            }
         }

         return null;
      }

      int getTopBottomOffsetForScrollingSibling() {
         return this.getTopAndBottomOffset() + this.mOffsetDelta;
      }

      public Parcelable onSaveInstanceState(CoordinatorLayout parent, AppBarLayout abl) {
         Parcelable superState = super.onSaveInstanceState(parent, abl);
         int offset = this.getTopAndBottomOffset();
         int i = 0;

         for(int count = abl.getChildCount(); i < count; ++i) {
            View child = abl.getChildAt(i);
            int visBottom = child.getBottom() + offset;
            if (child.getTop() + offset <= 0 && visBottom >= 0) {
               AppBarLayout.Behavior.SavedState ss = new AppBarLayout.Behavior.SavedState(superState);
               ss.firstVisibleChildIndex = i;
               ss.firstVisibleChildAtMinimumHeight = visBottom == ViewCompat.getMinimumHeight(child) + abl.getTopInset();
               ss.firstVisibleChildPercentageShown = (float)visBottom / (float)child.getHeight();
               return ss;
            }
         }

         return superState;
      }

      public void onRestoreInstanceState(CoordinatorLayout parent, AppBarLayout appBarLayout, Parcelable state) {
         if (state instanceof AppBarLayout.Behavior.SavedState) {
            AppBarLayout.Behavior.SavedState ss = (AppBarLayout.Behavior.SavedState)state;
            super.onRestoreInstanceState(parent, appBarLayout, ss.getSuperState());
            this.mOffsetToChildIndexOnLayout = ss.firstVisibleChildIndex;
            this.mOffsetToChildIndexOnLayoutPerc = ss.firstVisibleChildPercentageShown;
            this.mOffsetToChildIndexOnLayoutIsMinHeight = ss.firstVisibleChildAtMinimumHeight;
         } else {
            super.onRestoreInstanceState(parent, appBarLayout, state);
            this.mOffsetToChildIndexOnLayout = -1;
         }

      }

      protected static class SavedState extends AbsSavedState {
         int firstVisibleChildIndex;
         float firstVisibleChildPercentageShown;
         boolean firstVisibleChildAtMinimumHeight;
         public static final Creator CREATOR = new ClassLoaderCreator() {
            public AppBarLayout.Behavior.SavedState createFromParcel(Parcel source, ClassLoader loader) {
               return new AppBarLayout.Behavior.SavedState(source, loader);
            }

            public AppBarLayout.Behavior.SavedState createFromParcel(Parcel source) {
               return new AppBarLayout.Behavior.SavedState(source, (ClassLoader)null);
            }

            public AppBarLayout.Behavior.SavedState[] newArray(int size) {
               return new AppBarLayout.Behavior.SavedState[size];
            }
         };

         public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.firstVisibleChildIndex = source.readInt();
            this.firstVisibleChildPercentageShown = source.readFloat();
            this.firstVisibleChildAtMinimumHeight = source.readByte() != 0;
         }

         public SavedState(Parcelable superState) {
            super(superState);
         }

         public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.firstVisibleChildIndex);
            dest.writeFloat(this.firstVisibleChildPercentageShown);
            dest.writeByte((byte)(this.firstVisibleChildAtMinimumHeight ? 1 : 0));
         }
      }

      public abstract static class DragCallback {
         public abstract boolean canDrag(@NonNull AppBarLayout var1);
      }
   }

   public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
      public static final int SCROLL_FLAG_SCROLL = 1;
      public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
      public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
      public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
      public static final int SCROLL_FLAG_SNAP = 16;
      static final int FLAG_QUICK_RETURN = 5;
      static final int FLAG_SNAP = 17;
      static final int COLLAPSIBLE_FLAGS = 10;
      int mScrollFlags = 1;
      Interpolator mScrollInterpolator;

      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
         TypedArray a = c.obtainStyledAttributes(attrs, styleable.AppBarLayout_Layout);
         this.mScrollFlags = a.getInt(styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
         if (a.hasValue(styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
            int resId = a.getResourceId(styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0);
            this.mScrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(c, resId);
         }

         a.recycle();
      }

      public LayoutParams(int width, int height) {
         super(width, height);
      }

      public LayoutParams(int width, int height, float weight) {
         super(width, height, weight);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams p) {
         super(p);
      }

      public LayoutParams(MarginLayoutParams source) {
         super(source);
      }

      @RequiresApi(19)
      public LayoutParams(android.widget.LinearLayout.LayoutParams source) {
         super(source);
      }

      @RequiresApi(19)
      public LayoutParams(AppBarLayout.LayoutParams source) {
         super(source);
         this.mScrollFlags = source.mScrollFlags;
         this.mScrollInterpolator = source.mScrollInterpolator;
      }

      public void setScrollFlags(int flags) {
         this.mScrollFlags = flags;
      }

      public int getScrollFlags() {
         return this.mScrollFlags;
      }

      public void setScrollInterpolator(Interpolator interpolator) {
         this.mScrollInterpolator = interpolator;
      }

      public Interpolator getScrollInterpolator() {
         return this.mScrollInterpolator;
      }

      boolean isCollapsible() {
         return (this.mScrollFlags & 1) == 1 && (this.mScrollFlags & 10) != 0;
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      public @interface ScrollFlags {
      }
   }

   public interface OnOffsetChangedListener {
      void onOffsetChanged(AppBarLayout var1, int var2);
   }
}
