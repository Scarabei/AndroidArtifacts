package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.List;

public class NestedScrollView extends FrameLayout implements NestedScrollingParent, NestedScrollingChild2, ScrollingView {
   static final int ANIMATED_SCROLL_GAP = 250;
   static final float MAX_SCROLL_FACTOR = 0.5F;
   private static final String TAG = "NestedScrollView";
   private long mLastScroll;
   private final Rect mTempRect;
   private OverScroller mScroller;
   private EdgeEffect mEdgeGlowTop;
   private EdgeEffect mEdgeGlowBottom;
   private int mLastMotionY;
   private boolean mIsLayoutDirty;
   private boolean mIsLaidOut;
   private View mChildToScrollTo;
   private boolean mIsBeingDragged;
   private VelocityTracker mVelocityTracker;
   private boolean mFillViewport;
   private boolean mSmoothScrollingEnabled;
   private int mTouchSlop;
   private int mMinimumVelocity;
   private int mMaximumVelocity;
   private int mActivePointerId;
   private final int[] mScrollOffset;
   private final int[] mScrollConsumed;
   private int mNestedYOffset;
   private int mLastScrollerY;
   private static final int INVALID_POINTER = -1;
   private NestedScrollView.SavedState mSavedState;
   private static final NestedScrollView.AccessibilityDelegate ACCESSIBILITY_DELEGATE = new NestedScrollView.AccessibilityDelegate();
   private static final int[] SCROLLVIEW_STYLEABLE = new int[]{16843130};
   private final NestedScrollingParentHelper mParentHelper;
   private final NestedScrollingChildHelper mChildHelper;
   private float mVerticalScrollFactor;
   private NestedScrollView.OnScrollChangeListener mOnScrollChangeListener;

   public NestedScrollView(Context context) {
      this(context, (AttributeSet)null);
   }

   public NestedScrollView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mTempRect = new Rect();
      this.mIsLayoutDirty = true;
      this.mIsLaidOut = false;
      this.mChildToScrollTo = null;
      this.mIsBeingDragged = false;
      this.mSmoothScrollingEnabled = true;
      this.mActivePointerId = -1;
      this.mScrollOffset = new int[2];
      this.mScrollConsumed = new int[2];
      this.initScrollView();
      TypedArray a = context.obtainStyledAttributes(attrs, SCROLLVIEW_STYLEABLE, defStyleAttr, 0);
      this.setFillViewport(a.getBoolean(0, false));
      a.recycle();
      this.mParentHelper = new NestedScrollingParentHelper(this);
      this.mChildHelper = new NestedScrollingChildHelper(this);
      this.setNestedScrollingEnabled(true);
      ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
   }

   public void setNestedScrollingEnabled(boolean enabled) {
      this.mChildHelper.setNestedScrollingEnabled(enabled);
   }

   public boolean isNestedScrollingEnabled() {
      return this.mChildHelper.isNestedScrollingEnabled();
   }

   public boolean startNestedScroll(int axes) {
      return this.mChildHelper.startNestedScroll(axes);
   }

   public boolean startNestedScroll(int axes, int type) {
      return this.mChildHelper.startNestedScroll(axes, type);
   }

   public void stopNestedScroll() {
      this.mChildHelper.stopNestedScroll();
   }

   public void stopNestedScroll(int type) {
      this.mChildHelper.stopNestedScroll(type);
   }

   public boolean hasNestedScrollingParent() {
      return this.mChildHelper.hasNestedScrollingParent();
   }

   public boolean hasNestedScrollingParent(int type) {
      return this.mChildHelper.hasNestedScrollingParent(type);
   }

   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
      return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
   }

   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
      return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
   }

   public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
      return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
   }

   public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
      return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
   }

   public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
      return this.mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
   }

   public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
      return this.mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
   }

   public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
      return (nestedScrollAxes & 2) != 0;
   }

   public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
      this.mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
      this.startNestedScroll(2);
   }

   public void onStopNestedScroll(View target) {
      this.mParentHelper.onStopNestedScroll(target);
      this.stopNestedScroll();
   }

   public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
      int oldScrollY = this.getScrollY();
      this.scrollBy(0, dyUnconsumed);
      int myConsumed = this.getScrollY() - oldScrollY;
      int myUnconsumed = dyUnconsumed - myConsumed;
      this.dispatchNestedScroll(0, myConsumed, 0, myUnconsumed, (int[])null);
   }

   public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
      this.dispatchNestedPreScroll(dx, dy, consumed, (int[])null);
   }

   public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
      if (!consumed) {
         this.flingWithNestedDispatch((int)velocityY);
         return true;
      } else {
         return false;
      }
   }

   public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
      return this.dispatchNestedPreFling(velocityX, velocityY);
   }

   public int getNestedScrollAxes() {
      return this.mParentHelper.getNestedScrollAxes();
   }

   public boolean shouldDelayChildPressedState() {
      return true;
   }

   protected float getTopFadingEdgeStrength() {
      if (this.getChildCount() == 0) {
         return 0.0F;
      } else {
         int length = this.getVerticalFadingEdgeLength();
         int scrollY = this.getScrollY();
         return scrollY < length ? (float)scrollY / (float)length : 1.0F;
      }
   }

   protected float getBottomFadingEdgeStrength() {
      if (this.getChildCount() == 0) {
         return 0.0F;
      } else {
         int length = this.getVerticalFadingEdgeLength();
         int bottomEdge = this.getHeight() - this.getPaddingBottom();
         int span = this.getChildAt(0).getBottom() - this.getScrollY() - bottomEdge;
         return span < length ? (float)span / (float)length : 1.0F;
      }
   }

   public int getMaxScrollAmount() {
      return (int)(0.5F * (float)this.getHeight());
   }

   private void initScrollView() {
      this.mScroller = new OverScroller(this.getContext());
      this.setFocusable(true);
      this.setDescendantFocusability(262144);
      this.setWillNotDraw(false);
      ViewConfiguration configuration = ViewConfiguration.get(this.getContext());
      this.mTouchSlop = configuration.getScaledTouchSlop();
      this.mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
      this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
   }

   public void addView(View child) {
      if (this.getChildCount() > 0) {
         throw new IllegalStateException("ScrollView can host only one direct child");
      } else {
         super.addView(child);
      }
   }

   public void addView(View child, int index) {
      if (this.getChildCount() > 0) {
         throw new IllegalStateException("ScrollView can host only one direct child");
      } else {
         super.addView(child, index);
      }
   }

   public void addView(View child, LayoutParams params) {
      if (this.getChildCount() > 0) {
         throw new IllegalStateException("ScrollView can host only one direct child");
      } else {
         super.addView(child, params);
      }
   }

   public void addView(View child, int index, LayoutParams params) {
      if (this.getChildCount() > 0) {
         throw new IllegalStateException("ScrollView can host only one direct child");
      } else {
         super.addView(child, index, params);
      }
   }

   public void setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener l) {
      this.mOnScrollChangeListener = l;
   }

   private boolean canScroll() {
      View child = this.getChildAt(0);
      if (child != null) {
         int childHeight = child.getHeight();
         return this.getHeight() < childHeight + this.getPaddingTop() + this.getPaddingBottom();
      } else {
         return false;
      }
   }

   public boolean isFillViewport() {
      return this.mFillViewport;
   }

   public void setFillViewport(boolean fillViewport) {
      if (fillViewport != this.mFillViewport) {
         this.mFillViewport = fillViewport;
         this.requestLayout();
      }

   }

   public boolean isSmoothScrollingEnabled() {
      return this.mSmoothScrollingEnabled;
   }

   public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
      this.mSmoothScrollingEnabled = smoothScrollingEnabled;
   }

   protected void onScrollChanged(int l, int t, int oldl, int oldt) {
      super.onScrollChanged(l, t, oldl, oldt);
      if (this.mOnScrollChangeListener != null) {
         this.mOnScrollChangeListener.onScrollChange(this, l, t, oldl, oldt);
      }

   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      if (this.mFillViewport) {
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         if (heightMode != 0) {
            if (this.getChildCount() > 0) {
               View child = this.getChildAt(0);
               int height = this.getMeasuredHeight();
               if (child.getMeasuredHeight() < height) {
                  android.widget.FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams)child.getLayoutParams();
                  int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, this.getPaddingLeft() + this.getPaddingRight(), lp.width);
                  height -= this.getPaddingTop();
                  height -= this.getPaddingBottom();
                  int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, 1073741824);
                  child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
               }
            }

         }
      }
   }

   public boolean dispatchKeyEvent(KeyEvent event) {
      return super.dispatchKeyEvent(event) || this.executeKeyEvent(event);
   }

   public boolean executeKeyEvent(KeyEvent event) {
      this.mTempRect.setEmpty();
      if (this.canScroll()) {
         boolean handled = false;
         if (event.getAction() == 0) {
            switch(event.getKeyCode()) {
            case 19:
               if (!event.isAltPressed()) {
                  handled = this.arrowScroll(33);
               } else {
                  handled = this.fullScroll(33);
               }
               break;
            case 20:
               if (!event.isAltPressed()) {
                  handled = this.arrowScroll(130);
               } else {
                  handled = this.fullScroll(130);
               }
               break;
            case 62:
               this.pageScroll(event.isShiftPressed() ? 33 : 130);
            }
         }

         return handled;
      } else if (this.isFocused() && event.getKeyCode() != 4) {
         View currentFocused = this.findFocus();
         if (currentFocused == this) {
            currentFocused = null;
         }

         View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, 130);
         return nextFocused != null && nextFocused != this && nextFocused.requestFocus(130);
      } else {
         return false;
      }
   }

   private boolean inChild(int x, int y) {
      if (this.getChildCount() <= 0) {
         return false;
      } else {
         int scrollY = this.getScrollY();
         View child = this.getChildAt(0);
         return y >= child.getTop() - scrollY && y < child.getBottom() - scrollY && x >= child.getLeft() && x < child.getRight();
      }
   }

   private void initOrResetVelocityTracker() {
      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      } else {
         this.mVelocityTracker.clear();
      }

   }

   private void initVelocityTrackerIfNotExists() {
      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

   }

   private void recycleVelocityTracker() {
      if (this.mVelocityTracker != null) {
         this.mVelocityTracker.recycle();
         this.mVelocityTracker = null;
      }

   }

   public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
      if (disallowIntercept) {
         this.recycleVelocityTracker();
      }

      super.requestDisallowInterceptTouchEvent(disallowIntercept);
   }

   public boolean onInterceptTouchEvent(MotionEvent ev) {
      int action = ev.getAction();
      if (action == 2 && this.mIsBeingDragged) {
         return true;
      } else {
         int activePointerId;
         switch(action & 255) {
         case 0:
            activePointerId = (int)ev.getY();
            if (!this.inChild((int)ev.getX(), activePointerId)) {
               this.mIsBeingDragged = false;
               this.recycleVelocityTracker();
            } else {
               this.mLastMotionY = activePointerId;
               this.mActivePointerId = ev.getPointerId(0);
               this.initOrResetVelocityTracker();
               this.mVelocityTracker.addMovement(ev);
               this.mScroller.computeScrollOffset();
               this.mIsBeingDragged = !this.mScroller.isFinished();
               this.startNestedScroll(2, 0);
            }
            break;
         case 1:
         case 3:
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
            this.recycleVelocityTracker();
            if (this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
               ViewCompat.postInvalidateOnAnimation(this);
            }

            this.stopNestedScroll(0);
            break;
         case 2:
            activePointerId = this.mActivePointerId;
            if (activePointerId != -1) {
               int pointerIndex = ev.findPointerIndex(activePointerId);
               if (pointerIndex == -1) {
                  Log.e("NestedScrollView", "Invalid pointerId=" + activePointerId + " in onInterceptTouchEvent");
               } else {
                  int y = (int)ev.getY(pointerIndex);
                  int yDiff = Math.abs(y - this.mLastMotionY);
                  if (yDiff > this.mTouchSlop && (this.getNestedScrollAxes() & 2) == 0) {
                     this.mIsBeingDragged = true;
                     this.mLastMotionY = y;
                     this.initVelocityTrackerIfNotExists();
                     this.mVelocityTracker.addMovement(ev);
                     this.mNestedYOffset = 0;
                     ViewParent parent = this.getParent();
                     if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                     }
                  }
               }
            }
         case 4:
         case 5:
         default:
            break;
         case 6:
            this.onSecondaryPointerUp(ev);
         }

         return this.mIsBeingDragged;
      }
   }

   public boolean onTouchEvent(MotionEvent ev) {
      this.initVelocityTrackerIfNotExists();
      MotionEvent vtev = MotionEvent.obtain(ev);
      int actionMasked = ev.getActionMasked();
      if (actionMasked == 0) {
         this.mNestedYOffset = 0;
      }

      vtev.offsetLocation(0.0F, (float)this.mNestedYOffset);
      int range;
      int overscrollMode;
      switch(actionMasked) {
      case 0:
         if (this.getChildCount() == 0) {
            return false;
         }

         if (this.mIsBeingDragged = !this.mScroller.isFinished()) {
            ViewParent parent = this.getParent();
            if (parent != null) {
               parent.requestDisallowInterceptTouchEvent(true);
            }
         }

         if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
         }

         this.mLastMotionY = (int)ev.getY();
         this.mActivePointerId = ev.getPointerId(0);
         this.startNestedScroll(2, 0);
         break;
      case 1:
         VelocityTracker velocityTracker = this.mVelocityTracker;
         velocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
         range = (int)velocityTracker.getYVelocity(this.mActivePointerId);
         if (Math.abs(range) > this.mMinimumVelocity) {
            this.flingWithNestedDispatch(-range);
         } else if (this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
            ViewCompat.postInvalidateOnAnimation(this);
         }

         this.mActivePointerId = -1;
         this.endDrag();
         break;
      case 2:
         int activePointerIndex = ev.findPointerIndex(this.mActivePointerId);
         if (activePointerIndex == -1) {
            Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
         } else {
            int y = (int)ev.getY(activePointerIndex);
            int deltaY = this.mLastMotionY - y;
            if (this.dispatchNestedPreScroll(0, deltaY, this.mScrollConsumed, this.mScrollOffset, 0)) {
               deltaY -= this.mScrollConsumed[1];
               vtev.offsetLocation(0.0F, (float)this.mScrollOffset[1]);
               this.mNestedYOffset += this.mScrollOffset[1];
            }

            if (!this.mIsBeingDragged && Math.abs(deltaY) > this.mTouchSlop) {
               ViewParent parent = this.getParent();
               if (parent != null) {
                  parent.requestDisallowInterceptTouchEvent(true);
               }

               this.mIsBeingDragged = true;
               if (deltaY > 0) {
                  deltaY -= this.mTouchSlop;
               } else {
                  deltaY += this.mTouchSlop;
               }
            }

            if (this.mIsBeingDragged) {
               this.mLastMotionY = y - this.mScrollOffset[1];
               int oldY = this.getScrollY();
               range = this.getScrollRange();
               overscrollMode = this.getOverScrollMode();
               boolean canOverscroll = overscrollMode == 0 || overscrollMode == 1 && range > 0;
               if (this.overScrollByCompat(0, deltaY, 0, this.getScrollY(), 0, range, 0, 0, true) && !this.hasNestedScrollingParent(0)) {
                  this.mVelocityTracker.clear();
               }

               int scrolledDeltaY = this.getScrollY() - oldY;
               int unconsumedY = deltaY - scrolledDeltaY;
               if (this.dispatchNestedScroll(0, scrolledDeltaY, 0, unconsumedY, this.mScrollOffset, 0)) {
                  this.mLastMotionY -= this.mScrollOffset[1];
                  vtev.offsetLocation(0.0F, (float)this.mScrollOffset[1]);
                  this.mNestedYOffset += this.mScrollOffset[1];
               } else if (canOverscroll) {
                  this.ensureGlows();
                  int pulledToY = oldY + deltaY;
                  if (pulledToY < 0) {
                     EdgeEffectCompat.onPull(this.mEdgeGlowTop, (float)deltaY / (float)this.getHeight(), ev.getX(activePointerIndex) / (float)this.getWidth());
                     if (!this.mEdgeGlowBottom.isFinished()) {
                        this.mEdgeGlowBottom.onRelease();
                     }
                  } else if (pulledToY > range) {
                     EdgeEffectCompat.onPull(this.mEdgeGlowBottom, (float)deltaY / (float)this.getHeight(), 1.0F - ev.getX(activePointerIndex) / (float)this.getWidth());
                     if (!this.mEdgeGlowTop.isFinished()) {
                        this.mEdgeGlowTop.onRelease();
                     }
                  }

                  if (this.mEdgeGlowTop != null && (!this.mEdgeGlowTop.isFinished() || !this.mEdgeGlowBottom.isFinished())) {
                     ViewCompat.postInvalidateOnAnimation(this);
                  }
               }
            }
         }
         break;
      case 3:
         if (this.mIsBeingDragged && this.getChildCount() > 0 && this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
            ViewCompat.postInvalidateOnAnimation(this);
         }

         this.mActivePointerId = -1;
         this.endDrag();
      case 4:
      default:
         break;
      case 5:
         overscrollMode = ev.getActionIndex();
         this.mLastMotionY = (int)ev.getY(overscrollMode);
         this.mActivePointerId = ev.getPointerId(overscrollMode);
         break;
      case 6:
         this.onSecondaryPointerUp(ev);
         this.mLastMotionY = (int)ev.getY(ev.findPointerIndex(this.mActivePointerId));
      }

      if (this.mVelocityTracker != null) {
         this.mVelocityTracker.addMovement(vtev);
      }

      vtev.recycle();
      return true;
   }

   private void onSecondaryPointerUp(MotionEvent ev) {
      int pointerIndex = ev.getActionIndex();
      int pointerId = ev.getPointerId(pointerIndex);
      if (pointerId == this.mActivePointerId) {
         int newPointerIndex = pointerIndex == 0 ? 1 : 0;
         this.mLastMotionY = (int)ev.getY(newPointerIndex);
         this.mActivePointerId = ev.getPointerId(newPointerIndex);
         if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
         }
      }

   }

   public boolean onGenericMotionEvent(MotionEvent event) {
      if ((event.getSource() & 2) != 0) {
         switch(event.getAction()) {
         case 8:
            if (!this.mIsBeingDragged) {
               float vscroll = event.getAxisValue(9);
               if (vscroll != 0.0F) {
                  int delta = (int)(vscroll * this.getVerticalScrollFactorCompat());
                  int range = this.getScrollRange();
                  int oldScrollY = this.getScrollY();
                  int newScrollY = oldScrollY - delta;
                  if (newScrollY < 0) {
                     newScrollY = 0;
                  } else if (newScrollY > range) {
                     newScrollY = range;
                  }

                  if (newScrollY != oldScrollY) {
                     super.scrollTo(this.getScrollX(), newScrollY);
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private float getVerticalScrollFactorCompat() {
      if (this.mVerticalScrollFactor == 0.0F) {
         TypedValue outValue = new TypedValue();
         Context context = this.getContext();
         if (!context.getTheme().resolveAttribute(16842829, outValue, true)) {
            throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
         }

         this.mVerticalScrollFactor = outValue.getDimension(context.getResources().getDisplayMetrics());
      }

      return this.mVerticalScrollFactor;
   }

   protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
      super.scrollTo(scrollX, scrollY);
   }

   boolean overScrollByCompat(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
      int overScrollMode = this.getOverScrollMode();
      boolean canScrollHorizontal = this.computeHorizontalScrollRange() > this.computeHorizontalScrollExtent();
      boolean canScrollVertical = this.computeVerticalScrollRange() > this.computeVerticalScrollExtent();
      boolean overScrollHorizontal = overScrollMode == 0 || overScrollMode == 1 && canScrollHorizontal;
      boolean overScrollVertical = overScrollMode == 0 || overScrollMode == 1 && canScrollVertical;
      int newScrollX = scrollX + deltaX;
      if (!overScrollHorizontal) {
         maxOverScrollX = 0;
      }

      int newScrollY = scrollY + deltaY;
      if (!overScrollVertical) {
         maxOverScrollY = 0;
      }

      int left = -maxOverScrollX;
      int right = maxOverScrollX + scrollRangeX;
      int top = -maxOverScrollY;
      int bottom = maxOverScrollY + scrollRangeY;
      boolean clampedX = false;
      if (newScrollX > right) {
         newScrollX = right;
         clampedX = true;
      } else if (newScrollX < left) {
         newScrollX = left;
         clampedX = true;
      }

      boolean clampedY = false;
      if (newScrollY > bottom) {
         newScrollY = bottom;
         clampedY = true;
      } else if (newScrollY < top) {
         newScrollY = top;
         clampedY = true;
      }

      if (clampedY && !this.hasNestedScrollingParent(1)) {
         this.mScroller.springBack(newScrollX, newScrollY, 0, 0, 0, this.getScrollRange());
      }

      this.onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);
      return clampedX || clampedY;
   }

   int getScrollRange() {
      int scrollRange = 0;
      if (this.getChildCount() > 0) {
         View child = this.getChildAt(0);
         scrollRange = Math.max(0, child.getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
      }

      return scrollRange;
   }

   private View findFocusableViewInBounds(boolean topFocus, int top, int bottom) {
      List focusables = this.getFocusables(2);
      View focusCandidate = null;
      boolean foundFullyContainedFocusable = false;
      int count = focusables.size();

      for(int i = 0; i < count; ++i) {
         View view = (View)focusables.get(i);
         int viewTop = view.getTop();
         int viewBottom = view.getBottom();
         if (top < viewBottom && viewTop < bottom) {
            boolean viewIsFullyContained = top < viewTop && viewBottom < bottom;
            if (focusCandidate == null) {
               focusCandidate = view;
               foundFullyContainedFocusable = viewIsFullyContained;
            } else {
               boolean viewIsCloserToBoundary = topFocus && viewTop < focusCandidate.getTop() || !topFocus && viewBottom > focusCandidate.getBottom();
               if (foundFullyContainedFocusable) {
                  if (viewIsFullyContained && viewIsCloserToBoundary) {
                     focusCandidate = view;
                  }
               } else if (viewIsFullyContained) {
                  focusCandidate = view;
                  foundFullyContainedFocusable = true;
               } else if (viewIsCloserToBoundary) {
                  focusCandidate = view;
               }
            }
         }
      }

      return focusCandidate;
   }

   public boolean pageScroll(int direction) {
      boolean down = direction == 130;
      int height = this.getHeight();
      if (down) {
         this.mTempRect.top = this.getScrollY() + height;
         int count = this.getChildCount();
         if (count > 0) {
            View view = this.getChildAt(count - 1);
            if (this.mTempRect.top + height > view.getBottom()) {
               this.mTempRect.top = view.getBottom() - height;
            }
         }
      } else {
         this.mTempRect.top = this.getScrollY() - height;
         if (this.mTempRect.top < 0) {
            this.mTempRect.top = 0;
         }
      }

      this.mTempRect.bottom = this.mTempRect.top + height;
      return this.scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
   }

   public boolean fullScroll(int direction) {
      boolean down = direction == 130;
      int height = this.getHeight();
      this.mTempRect.top = 0;
      this.mTempRect.bottom = height;
      if (down) {
         int count = this.getChildCount();
         if (count > 0) {
            View view = this.getChildAt(count - 1);
            this.mTempRect.bottom = view.getBottom() + this.getPaddingBottom();
            this.mTempRect.top = this.mTempRect.bottom - height;
         }
      }

      return this.scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
   }

   private boolean scrollAndFocus(int direction, int top, int bottom) {
      boolean handled = true;
      int height = this.getHeight();
      int containerTop = this.getScrollY();
      int containerBottom = containerTop + height;
      boolean up = direction == 33;
      View newFocused = this.findFocusableViewInBounds(up, top, bottom);
      if (newFocused == null) {
         newFocused = this;
      }

      if (top >= containerTop && bottom <= containerBottom) {
         handled = false;
      } else {
         int delta = up ? top - containerTop : bottom - containerBottom;
         this.doScrollY(delta);
      }

      if (newFocused != this.findFocus()) {
         ((View)newFocused).requestFocus(direction);
      }

      return handled;
   }

   public boolean arrowScroll(int direction) {
      View currentFocused = this.findFocus();
      if (currentFocused == this) {
         currentFocused = null;
      }

      View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
      int maxJump = this.getMaxScrollAmount();
      int scrollDelta;
      if (nextFocused != null && this.isWithinDeltaOfScreen(nextFocused, maxJump, this.getHeight())) {
         nextFocused.getDrawingRect(this.mTempRect);
         this.offsetDescendantRectToMyCoords(nextFocused, this.mTempRect);
         scrollDelta = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
         this.doScrollY(scrollDelta);
         nextFocused.requestFocus(direction);
      } else {
         scrollDelta = maxJump;
         if (direction == 33 && this.getScrollY() < maxJump) {
            scrollDelta = this.getScrollY();
         } else if (direction == 130 && this.getChildCount() > 0) {
            int daBottom = this.getChildAt(0).getBottom();
            int screenBottom = this.getScrollY() + this.getHeight() - this.getPaddingBottom();
            if (daBottom - screenBottom < maxJump) {
               scrollDelta = daBottom - screenBottom;
            }
         }

         if (scrollDelta == 0) {
            return false;
         }

         this.doScrollY(direction == 130 ? scrollDelta : -scrollDelta);
      }

      if (currentFocused != null && currentFocused.isFocused() && this.isOffScreen(currentFocused)) {
         scrollDelta = this.getDescendantFocusability();
         this.setDescendantFocusability(131072);
         this.requestFocus();
         this.setDescendantFocusability(scrollDelta);
      }

      return true;
   }

   private boolean isOffScreen(View descendant) {
      return !this.isWithinDeltaOfScreen(descendant, 0, this.getHeight());
   }

   private boolean isWithinDeltaOfScreen(View descendant, int delta, int height) {
      descendant.getDrawingRect(this.mTempRect);
      this.offsetDescendantRectToMyCoords(descendant, this.mTempRect);
      return this.mTempRect.bottom + delta >= this.getScrollY() && this.mTempRect.top - delta <= this.getScrollY() + height;
   }

   private void doScrollY(int delta) {
      if (delta != 0) {
         if (this.mSmoothScrollingEnabled) {
            this.smoothScrollBy(0, delta);
         } else {
            this.scrollBy(0, delta);
         }
      }

   }

   public final void smoothScrollBy(int dx, int dy) {
      if (this.getChildCount() != 0) {
         long duration = AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll;
         if (duration > 250L) {
            int height = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
            int bottom = this.getChildAt(0).getHeight();
            int maxY = Math.max(0, bottom - height);
            int scrollY = this.getScrollY();
            dy = Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY;
            this.mScroller.startScroll(this.getScrollX(), scrollY, 0, dy);
            ViewCompat.postInvalidateOnAnimation(this);
         } else {
            if (!this.mScroller.isFinished()) {
               this.mScroller.abortAnimation();
            }

            this.scrollBy(dx, dy);
         }

         this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
      }
   }

   public final void smoothScrollTo(int x, int y) {
      this.smoothScrollBy(x - this.getScrollX(), y - this.getScrollY());
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeVerticalScrollRange() {
      int count = this.getChildCount();
      int contentHeight = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
      if (count == 0) {
         return contentHeight;
      } else {
         int scrollRange = this.getChildAt(0).getBottom();
         int scrollY = this.getScrollY();
         int overscrollBottom = Math.max(0, scrollRange - contentHeight);
         if (scrollY < 0) {
            scrollRange -= scrollY;
         } else if (scrollY > overscrollBottom) {
            scrollRange += scrollY - overscrollBottom;
         }

         return scrollRange;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeVerticalScrollOffset() {
      return Math.max(0, super.computeVerticalScrollOffset());
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeVerticalScrollExtent() {
      return super.computeVerticalScrollExtent();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeHorizontalScrollRange() {
      return super.computeHorizontalScrollRange();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeHorizontalScrollOffset() {
      return super.computeHorizontalScrollOffset();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int computeHorizontalScrollExtent() {
      return super.computeHorizontalScrollExtent();
   }

   protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
      LayoutParams lp = child.getLayoutParams();
      int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, this.getPaddingLeft() + this.getPaddingRight(), lp.width);
      int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
   }

   protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
      MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
      int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, this.getPaddingLeft() + this.getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
      int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, 0);
      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
   }

   public void computeScroll() {
      if (this.mScroller.computeScrollOffset()) {
         int x = this.mScroller.getCurrX();
         int y = this.mScroller.getCurrY();
         int dy = y - this.mLastScrollerY;
         if (this.dispatchNestedPreScroll(0, dy, this.mScrollConsumed, (int[])null, 1)) {
            dy -= this.mScrollConsumed[1];
         }

         if (dy != 0) {
            int range = this.getScrollRange();
            int oldScrollY = this.getScrollY();
            this.overScrollByCompat(0, dy, this.getScrollX(), oldScrollY, 0, range, 0, 0, false);
            int scrolledDeltaY = this.getScrollY() - oldScrollY;
            int unconsumedY = dy - scrolledDeltaY;
            if (!this.dispatchNestedScroll(0, scrolledDeltaY, 0, unconsumedY, (int[])null, 1)) {
               int mode = this.getOverScrollMode();
               boolean canOverscroll = mode == 0 || mode == 1 && range > 0;
               if (canOverscroll) {
                  this.ensureGlows();
                  if (y <= 0 && oldScrollY > 0) {
                     this.mEdgeGlowTop.onAbsorb((int)this.mScroller.getCurrVelocity());
                  } else if (y >= range && oldScrollY < range) {
                     this.mEdgeGlowBottom.onAbsorb((int)this.mScroller.getCurrVelocity());
                  }
               }
            }
         }

         this.mLastScrollerY = y;
         ViewCompat.postInvalidateOnAnimation(this);
      } else {
         if (this.hasNestedScrollingParent(1)) {
            this.stopNestedScroll(1);
         }

         this.mLastScrollerY = 0;
      }

   }

   private void scrollToChild(View child) {
      child.getDrawingRect(this.mTempRect);
      this.offsetDescendantRectToMyCoords(child, this.mTempRect);
      int scrollDelta = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
      if (scrollDelta != 0) {
         this.scrollBy(0, scrollDelta);
      }

   }

   private boolean scrollToChildRect(Rect rect, boolean immediate) {
      int delta = this.computeScrollDeltaToGetChildRectOnScreen(rect);
      boolean scroll = delta != 0;
      if (scroll) {
         if (immediate) {
            this.scrollBy(0, delta);
         } else {
            this.smoothScrollBy(0, delta);
         }
      }

      return scroll;
   }

   protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         int height = this.getHeight();
         int screenTop = this.getScrollY();
         int screenBottom = screenTop + height;
         int fadingEdge = this.getVerticalFadingEdgeLength();
         if (rect.top > 0) {
            screenTop += fadingEdge;
         }

         if (rect.bottom < this.getChildAt(0).getHeight()) {
            screenBottom -= fadingEdge;
         }

         int scrollYDelta = 0;
         if (rect.bottom > screenBottom && rect.top > screenTop) {
            if (rect.height() > height) {
               scrollYDelta += rect.top - screenTop;
            } else {
               scrollYDelta += rect.bottom - screenBottom;
            }

            int bottom = this.getChildAt(0).getBottom();
            int distanceToBottom = bottom - screenBottom;
            scrollYDelta = Math.min(scrollYDelta, distanceToBottom);
         } else if (rect.top < screenTop && rect.bottom < screenBottom) {
            if (rect.height() > height) {
               scrollYDelta -= screenBottom - rect.bottom;
            } else {
               scrollYDelta -= screenTop - rect.top;
            }

            scrollYDelta = Math.max(scrollYDelta, -this.getScrollY());
         }

         return scrollYDelta;
      }
   }

   public void requestChildFocus(View child, View focused) {
      if (!this.mIsLayoutDirty) {
         this.scrollToChild(focused);
      } else {
         this.mChildToScrollTo = focused;
      }

      super.requestChildFocus(child, focused);
   }

   protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
      if (direction == 2) {
         direction = 130;
      } else if (direction == 1) {
         direction = 33;
      }

      View nextFocus = previouslyFocusedRect == null ? FocusFinder.getInstance().findNextFocus(this, (View)null, direction) : FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
      if (nextFocus == null) {
         return false;
      } else {
         return this.isOffScreen(nextFocus) ? false : nextFocus.requestFocus(direction, previouslyFocusedRect);
      }
   }

   public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
      rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
      return this.scrollToChildRect(rectangle, immediate);
   }

   public void requestLayout() {
      this.mIsLayoutDirty = true;
      super.requestLayout();
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      super.onLayout(changed, l, t, r, b);
      this.mIsLayoutDirty = false;
      if (this.mChildToScrollTo != null && isViewDescendantOf(this.mChildToScrollTo, this)) {
         this.scrollToChild(this.mChildToScrollTo);
      }

      this.mChildToScrollTo = null;
      if (!this.mIsLaidOut) {
         if (this.mSavedState != null) {
            this.scrollTo(this.getScrollX(), this.mSavedState.scrollPosition);
            this.mSavedState = null;
         }

         int childHeight = this.getChildCount() > 0 ? this.getChildAt(0).getMeasuredHeight() : 0;
         int scrollRange = Math.max(0, childHeight - (b - t - this.getPaddingBottom() - this.getPaddingTop()));
         if (this.getScrollY() > scrollRange) {
            this.scrollTo(this.getScrollX(), scrollRange);
         } else if (this.getScrollY() < 0) {
            this.scrollTo(this.getScrollX(), 0);
         }
      }

      this.scrollTo(this.getScrollX(), this.getScrollY());
      this.mIsLaidOut = true;
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mIsLaidOut = false;
   }

   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      View currentFocused = this.findFocus();
      if (null != currentFocused && this != currentFocused) {
         if (this.isWithinDeltaOfScreen(currentFocused, 0, oldh)) {
            currentFocused.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(currentFocused, this.mTempRect);
            int scrollDelta = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
            this.doScrollY(scrollDelta);
         }

      }
   }

   private static boolean isViewDescendantOf(View child, View parent) {
      if (child == parent) {
         return true;
      } else {
         ViewParent theParent = child.getParent();
         return theParent instanceof ViewGroup && isViewDescendantOf((View)theParent, parent);
      }
   }

   public void fling(int velocityY) {
      if (this.getChildCount() > 0) {
         this.startNestedScroll(2, 1);
         this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, velocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
         this.mLastScrollerY = this.getScrollY();
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   private void flingWithNestedDispatch(int velocityY) {
      int scrollY = this.getScrollY();
      boolean canFling = (scrollY > 0 || velocityY > 0) && (scrollY < this.getScrollRange() || velocityY < 0);
      if (!this.dispatchNestedPreFling(0.0F, (float)velocityY)) {
         this.dispatchNestedFling(0.0F, (float)velocityY, canFling);
         this.fling(velocityY);
      }

   }

   private void endDrag() {
      this.mIsBeingDragged = false;
      this.recycleVelocityTracker();
      this.stopNestedScroll(0);
      if (this.mEdgeGlowTop != null) {
         this.mEdgeGlowTop.onRelease();
         this.mEdgeGlowBottom.onRelease();
      }

   }

   public void scrollTo(int x, int y) {
      if (this.getChildCount() > 0) {
         View child = this.getChildAt(0);
         x = clamp(x, this.getWidth() - this.getPaddingRight() - this.getPaddingLeft(), child.getWidth());
         y = clamp(y, this.getHeight() - this.getPaddingBottom() - this.getPaddingTop(), child.getHeight());
         if (x != this.getScrollX() || y != this.getScrollY()) {
            super.scrollTo(x, y);
         }
      }

   }

   private void ensureGlows() {
      if (this.getOverScrollMode() != 2) {
         if (this.mEdgeGlowTop == null) {
            Context context = this.getContext();
            this.mEdgeGlowTop = new EdgeEffect(context);
            this.mEdgeGlowBottom = new EdgeEffect(context);
         }
      } else {
         this.mEdgeGlowTop = null;
         this.mEdgeGlowBottom = null;
      }

   }

   public void draw(Canvas canvas) {
      super.draw(canvas);
      if (this.mEdgeGlowTop != null) {
         int scrollY = this.getScrollY();
         int restoreCount;
         int width;
         if (!this.mEdgeGlowTop.isFinished()) {
            restoreCount = canvas.save();
            width = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            canvas.translate((float)this.getPaddingLeft(), (float)Math.min(0, scrollY));
            this.mEdgeGlowTop.setSize(width, this.getHeight());
            if (this.mEdgeGlowTop.draw(canvas)) {
               ViewCompat.postInvalidateOnAnimation(this);
            }

            canvas.restoreToCount(restoreCount);
         }

         if (!this.mEdgeGlowBottom.isFinished()) {
            restoreCount = canvas.save();
            width = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            int height = this.getHeight();
            canvas.translate((float)(-width + this.getPaddingLeft()), (float)(Math.max(this.getScrollRange(), scrollY) + height));
            canvas.rotate(180.0F, (float)width, 0.0F);
            this.mEdgeGlowBottom.setSize(width, height);
            if (this.mEdgeGlowBottom.draw(canvas)) {
               ViewCompat.postInvalidateOnAnimation(this);
            }

            canvas.restoreToCount(restoreCount);
         }
      }

   }

   private static int clamp(int n, int my, int child) {
      if (my < child && n >= 0) {
         return my + n > child ? child - my : n;
      } else {
         return 0;
      }
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof NestedScrollView.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         NestedScrollView.SavedState ss = (NestedScrollView.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         this.mSavedState = ss;
         this.requestLayout();
      }
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      NestedScrollView.SavedState ss = new NestedScrollView.SavedState(superState);
      ss.scrollPosition = this.getScrollY();
      return ss;
   }

   static class AccessibilityDelegate extends AccessibilityDelegateCompat {
      public boolean performAccessibilityAction(View host, int action, Bundle arguments) {
         if (super.performAccessibilityAction(host, action, arguments)) {
            return true;
         } else {
            NestedScrollView nsvHost = (NestedScrollView)host;
            if (!nsvHost.isEnabled()) {
               return false;
            } else {
               int viewportHeight;
               int targetScrollY;
               switch(action) {
               case 4096:
                  viewportHeight = nsvHost.getHeight() - nsvHost.getPaddingBottom() - nsvHost.getPaddingTop();
                  targetScrollY = Math.min(nsvHost.getScrollY() + viewportHeight, nsvHost.getScrollRange());
                  if (targetScrollY != nsvHost.getScrollY()) {
                     nsvHost.smoothScrollTo(0, targetScrollY);
                     return true;
                  }

                  return false;
               case 8192:
                  viewportHeight = nsvHost.getHeight() - nsvHost.getPaddingBottom() - nsvHost.getPaddingTop();
                  targetScrollY = Math.max(nsvHost.getScrollY() - viewportHeight, 0);
                  if (targetScrollY != nsvHost.getScrollY()) {
                     nsvHost.smoothScrollTo(0, targetScrollY);
                     return true;
                  }

                  return false;
               default:
                  return false;
               }
            }
         }
      }

      public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
         super.onInitializeAccessibilityNodeInfo(host, info);
         NestedScrollView nsvHost = (NestedScrollView)host;
         info.setClassName(ScrollView.class.getName());
         if (nsvHost.isEnabled()) {
            int scrollRange = nsvHost.getScrollRange();
            if (scrollRange > 0) {
               info.setScrollable(true);
               if (nsvHost.getScrollY() > 0) {
                  info.addAction(8192);
               }

               if (nsvHost.getScrollY() < scrollRange) {
                  info.addAction(4096);
               }
            }
         }

      }

      public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
         super.onInitializeAccessibilityEvent(host, event);
         NestedScrollView nsvHost = (NestedScrollView)host;
         event.setClassName(ScrollView.class.getName());
         boolean scrollable = nsvHost.getScrollRange() > 0;
         event.setScrollable(scrollable);
         event.setScrollX(nsvHost.getScrollX());
         event.setScrollY(nsvHost.getScrollY());
         AccessibilityRecordCompat.setMaxScrollX(event, nsvHost.getScrollX());
         AccessibilityRecordCompat.setMaxScrollY(event, nsvHost.getScrollRange());
      }
   }

   static class SavedState extends BaseSavedState {
      public int scrollPosition;
      public static final Creator CREATOR = new Creator() {
         public NestedScrollView.SavedState createFromParcel(Parcel in) {
            return new NestedScrollView.SavedState(in);
         }

         public NestedScrollView.SavedState[] newArray(int size) {
            return new NestedScrollView.SavedState[size];
         }
      };

      SavedState(Parcelable superState) {
         super(superState);
      }

      SavedState(Parcel source) {
         super(source);
         this.scrollPosition = source.readInt();
      }

      public void writeToParcel(Parcel dest, int flags) {
         super.writeToParcel(dest, flags);
         dest.writeInt(this.scrollPosition);
      }

      public String toString() {
         return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
      }
   }

   public interface OnScrollChangeListener {
      void onScrollChange(NestedScrollView var1, int var2, int var3, int var4, int var5);
   }
}
