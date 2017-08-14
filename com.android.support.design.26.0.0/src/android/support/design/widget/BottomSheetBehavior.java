package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.styleable;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior extends CoordinatorLayout.Behavior {
   public static final int STATE_DRAGGING = 1;
   public static final int STATE_SETTLING = 2;
   public static final int STATE_EXPANDED = 3;
   public static final int STATE_COLLAPSED = 4;
   public static final int STATE_HIDDEN = 5;
   public static final int PEEK_HEIGHT_AUTO = -1;
   private static final float HIDE_THRESHOLD = 0.5F;
   private static final float HIDE_FRICTION = 0.1F;
   private float mMaximumVelocity;
   private int mPeekHeight;
   private boolean mPeekHeightAuto;
   private int mPeekHeightMin;
   int mMinOffset;
   int mMaxOffset;
   boolean mHideable;
   private boolean mSkipCollapsed;
   int mState = 4;
   ViewDragHelper mViewDragHelper;
   private boolean mIgnoreEvents;
   private int mLastNestedScrollDy;
   private boolean mNestedScrolled;
   int mParentHeight;
   WeakReference mViewRef;
   WeakReference mNestedScrollingChildRef;
   private BottomSheetBehavior.BottomSheetCallback mCallback;
   private VelocityTracker mVelocityTracker;
   int mActivePointerId;
   private int mInitialY;
   boolean mTouchingScrollingChild;
   private final Callback mDragCallback = new Callback() {
      public boolean tryCaptureView(View child, int pointerId) {
         if (BottomSheetBehavior.this.mState == 1) {
            return false;
         } else if (BottomSheetBehavior.this.mTouchingScrollingChild) {
            return false;
         } else {
            if (BottomSheetBehavior.this.mState == 3 && BottomSheetBehavior.this.mActivePointerId == pointerId) {
               View scroll = (View)BottomSheetBehavior.this.mNestedScrollingChildRef.get();
               if (scroll != null && scroll.canScrollVertically(-1)) {
                  return false;
               }
            }

            return BottomSheetBehavior.this.mViewRef != null && BottomSheetBehavior.this.mViewRef.get() == child;
         }
      }

      public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
         BottomSheetBehavior.this.dispatchOnSlide(top);
      }

      public void onViewDragStateChanged(int state) {
         if (state == 1) {
            BottomSheetBehavior.this.setStateInternal(1);
         }

      }

      public void onViewReleased(View releasedChild, float xvel, float yvel) {
         int top;
         byte targetState;
         if (yvel < 0.0F) {
            top = BottomSheetBehavior.this.mMinOffset;
            targetState = 3;
         } else if (BottomSheetBehavior.this.mHideable && BottomSheetBehavior.this.shouldHide(releasedChild, yvel)) {
            top = BottomSheetBehavior.this.mParentHeight;
            targetState = 5;
         } else if (yvel == 0.0F) {
            int currentTop = releasedChild.getTop();
            if (Math.abs(currentTop - BottomSheetBehavior.this.mMinOffset) < Math.abs(currentTop - BottomSheetBehavior.this.mMaxOffset)) {
               top = BottomSheetBehavior.this.mMinOffset;
               targetState = 3;
            } else {
               top = BottomSheetBehavior.this.mMaxOffset;
               targetState = 4;
            }
         } else {
            top = BottomSheetBehavior.this.mMaxOffset;
            targetState = 4;
         }

         if (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top)) {
            BottomSheetBehavior.this.setStateInternal(2);
            ViewCompat.postOnAnimation(releasedChild, BottomSheetBehavior.this.new SettleRunnable(releasedChild, targetState));
         } else {
            BottomSheetBehavior.this.setStateInternal(targetState);
         }

      }

      public int clampViewPositionVertical(View child, int top, int dy) {
         return MathUtils.clamp(top, BottomSheetBehavior.this.mMinOffset, BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight : BottomSheetBehavior.this.mMaxOffset);
      }

      public int clampViewPositionHorizontal(View child, int left, int dx) {
         return child.getLeft();
      }

      public int getViewVerticalDragRange(View child) {
         return BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset : BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
      }
   };

   public BottomSheetBehavior() {
   }

   public BottomSheetBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.BottomSheetBehavior_Layout);
      TypedValue value = a.peekValue(styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
      if (value != null && value.data == -1) {
         this.setPeekHeight(value.data);
      } else {
         this.setPeekHeight(a.getDimensionPixelSize(styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
      }

      this.setHideable(a.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
      this.setSkipCollapsed(a.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
      a.recycle();
      ViewConfiguration configuration = ViewConfiguration.get(context);
      this.mMaximumVelocity = (float)configuration.getScaledMaximumFlingVelocity();
   }

   public Parcelable onSaveInstanceState(CoordinatorLayout parent, View child) {
      return new BottomSheetBehavior.SavedState(super.onSaveInstanceState(parent, child), this.mState);
   }

   public void onRestoreInstanceState(CoordinatorLayout parent, View child, Parcelable state) {
      BottomSheetBehavior.SavedState ss = (BottomSheetBehavior.SavedState)state;
      super.onRestoreInstanceState(parent, child, ss.getSuperState());
      if (ss.state != 1 && ss.state != 2) {
         this.mState = ss.state;
      } else {
         this.mState = 4;
      }

   }

   public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
      if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
         ViewCompat.setFitsSystemWindows(child, true);
      }

      int savedTop = child.getTop();
      parent.onLayoutChild(child, layoutDirection);
      this.mParentHeight = parent.getHeight();
      int peekHeight;
      if (this.mPeekHeightAuto) {
         if (this.mPeekHeightMin == 0) {
            this.mPeekHeightMin = parent.getResources().getDimensionPixelSize(dimen.design_bottom_sheet_peek_height_min);
         }

         peekHeight = Math.max(this.mPeekHeightMin, this.mParentHeight - parent.getWidth() * 9 / 16);
      } else {
         peekHeight = this.mPeekHeight;
      }

      this.mMinOffset = Math.max(0, this.mParentHeight - child.getHeight());
      this.mMaxOffset = Math.max(this.mParentHeight - peekHeight, this.mMinOffset);
      if (this.mState == 3) {
         ViewCompat.offsetTopAndBottom(child, this.mMinOffset);
      } else if (this.mHideable && this.mState == 5) {
         ViewCompat.offsetTopAndBottom(child, this.mParentHeight);
      } else if (this.mState == 4) {
         ViewCompat.offsetTopAndBottom(child, this.mMaxOffset);
      } else if (this.mState == 1 || this.mState == 2) {
         ViewCompat.offsetTopAndBottom(child, savedTop - child.getTop());
      }

      if (this.mViewDragHelper == null) {
         this.mViewDragHelper = ViewDragHelper.create(parent, this.mDragCallback);
      }

      this.mViewRef = new WeakReference(child);
      this.mNestedScrollingChildRef = new WeakReference(this.findScrollingChild(child));
      return true;
   }

   public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
      if (!child.isShown()) {
         this.mIgnoreEvents = true;
         return false;
      } else {
         int action = event.getActionMasked();
         if (action == 0) {
            this.reset();
         }

         if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
         }

         this.mVelocityTracker.addMovement(event);
         switch(action) {
         case 0:
            int initialX = (int)event.getX();
            this.mInitialY = (int)event.getY();
            View scroll = this.mNestedScrollingChildRef != null ? (View)this.mNestedScrollingChildRef.get() : null;
            if (scroll != null && parent.isPointInChildBounds(scroll, initialX, this.mInitialY)) {
               this.mActivePointerId = event.getPointerId(event.getActionIndex());
               this.mTouchingScrollingChild = true;
            }

            this.mIgnoreEvents = this.mActivePointerId == -1 && !parent.isPointInChildBounds(child, initialX, this.mInitialY);
            break;
         case 1:
         case 3:
            this.mTouchingScrollingChild = false;
            this.mActivePointerId = -1;
            if (this.mIgnoreEvents) {
               this.mIgnoreEvents = false;
               return false;
            }
         case 2:
         }

         if (!this.mIgnoreEvents && this.mViewDragHelper.shouldInterceptTouchEvent(event)) {
            return true;
         } else {
            View scroll = (View)this.mNestedScrollingChildRef.get();
            return action == 2 && scroll != null && !this.mIgnoreEvents && this.mState != 1 && !parent.isPointInChildBounds(scroll, (int)event.getX(), (int)event.getY()) && Math.abs((float)this.mInitialY - event.getY()) > (float)this.mViewDragHelper.getTouchSlop();
         }
      }
   }

   public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
      if (!child.isShown()) {
         return false;
      } else {
         int action = event.getActionMasked();
         if (this.mState == 1 && action == 0) {
            return true;
         } else {
            this.mViewDragHelper.processTouchEvent(event);
            if (action == 0) {
               this.reset();
            }

            if (this.mVelocityTracker == null) {
               this.mVelocityTracker = VelocityTracker.obtain();
            }

            this.mVelocityTracker.addMovement(event);
            if (action == 2 && !this.mIgnoreEvents && Math.abs((float)this.mInitialY - event.getY()) > (float)this.mViewDragHelper.getTouchSlop()) {
               this.mViewDragHelper.captureChildView(child, event.getPointerId(event.getActionIndex()));
            }

            return !this.mIgnoreEvents;
         }
      }
   }

   public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
      this.mLastNestedScrollDy = 0;
      this.mNestedScrolled = false;
      return (nestedScrollAxes & 2) != 0;
   }

   public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
      View scrollingChild = (View)this.mNestedScrollingChildRef.get();
      if (target == scrollingChild) {
         int currentTop = child.getTop();
         int newTop = currentTop - dy;
         if (dy > 0) {
            if (newTop < this.mMinOffset) {
               consumed[1] = currentTop - this.mMinOffset;
               ViewCompat.offsetTopAndBottom(child, -consumed[1]);
               this.setStateInternal(3);
            } else {
               consumed[1] = dy;
               ViewCompat.offsetTopAndBottom(child, -dy);
               this.setStateInternal(1);
            }
         } else if (dy < 0 && !target.canScrollVertically(-1)) {
            if (newTop > this.mMaxOffset && !this.mHideable) {
               consumed[1] = currentTop - this.mMaxOffset;
               ViewCompat.offsetTopAndBottom(child, -consumed[1]);
               this.setStateInternal(4);
            } else {
               consumed[1] = dy;
               ViewCompat.offsetTopAndBottom(child, -dy);
               this.setStateInternal(1);
            }
         }

         this.dispatchOnSlide(child.getTop());
         this.mLastNestedScrollDy = dy;
         this.mNestedScrolled = true;
      }
   }

   public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
      if (child.getTop() == this.mMinOffset) {
         this.setStateInternal(3);
      } else if (this.mNestedScrollingChildRef != null && target == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
         int top;
         byte targetState;
         if (this.mLastNestedScrollDy > 0) {
            top = this.mMinOffset;
            targetState = 3;
         } else if (this.mHideable && this.shouldHide(child, this.getYVelocity())) {
            top = this.mParentHeight;
            targetState = 5;
         } else if (this.mLastNestedScrollDy == 0) {
            int currentTop = child.getTop();
            if (Math.abs(currentTop - this.mMinOffset) < Math.abs(currentTop - this.mMaxOffset)) {
               top = this.mMinOffset;
               targetState = 3;
            } else {
               top = this.mMaxOffset;
               targetState = 4;
            }
         } else {
            top = this.mMaxOffset;
            targetState = 4;
         }

         if (this.mViewDragHelper.smoothSlideViewTo(child, child.getLeft(), top)) {
            this.setStateInternal(2);
            ViewCompat.postOnAnimation(child, new BottomSheetBehavior.SettleRunnable(child, targetState));
         } else {
            this.setStateInternal(targetState);
         }

         this.mNestedScrolled = false;
      }
   }

   public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
      return target == this.mNestedScrollingChildRef.get() && (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY));
   }

   public final void setPeekHeight(int peekHeight) {
      boolean layout = false;
      if (peekHeight == -1) {
         if (!this.mPeekHeightAuto) {
            this.mPeekHeightAuto = true;
            layout = true;
         }
      } else if (this.mPeekHeightAuto || this.mPeekHeight != peekHeight) {
         this.mPeekHeightAuto = false;
         this.mPeekHeight = Math.max(0, peekHeight);
         this.mMaxOffset = this.mParentHeight - peekHeight;
         layout = true;
      }

      if (layout && this.mState == 4 && this.mViewRef != null) {
         View view = (View)this.mViewRef.get();
         if (view != null) {
            view.requestLayout();
         }
      }

   }

   public final int getPeekHeight() {
      return this.mPeekHeightAuto ? -1 : this.mPeekHeight;
   }

   public void setHideable(boolean hideable) {
      this.mHideable = hideable;
   }

   public boolean isHideable() {
      return this.mHideable;
   }

   public void setSkipCollapsed(boolean skipCollapsed) {
      this.mSkipCollapsed = skipCollapsed;
   }

   public boolean getSkipCollapsed() {
      return this.mSkipCollapsed;
   }

   public void setBottomSheetCallback(BottomSheetBehavior.BottomSheetCallback callback) {
      this.mCallback = callback;
   }

   public final void setState(final int state) {
      if (state != this.mState) {
         if (this.mViewRef == null) {
            if (state == 4 || state == 3 || this.mHideable && state == 5) {
               this.mState = state;
            }

         } else {
            final View child = (View)this.mViewRef.get();
            if (child != null) {
               ViewParent parent = child.getParent();
               if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(child)) {
                  child.post(new Runnable() {
                     public void run() {
                        BottomSheetBehavior.this.startSettlingAnimation(child, state);
                     }
                  });
               } else {
                  this.startSettlingAnimation(child, state);
               }

            }
         }
      }
   }

   public final int getState() {
      return this.mState;
   }

   void setStateInternal(int state) {
      if (this.mState != state) {
         this.mState = state;
         View bottomSheet = (View)this.mViewRef.get();
         if (bottomSheet != null && this.mCallback != null) {
            this.mCallback.onStateChanged(bottomSheet, state);
         }

      }
   }

   private void reset() {
      this.mActivePointerId = -1;
      if (this.mVelocityTracker != null) {
         this.mVelocityTracker.recycle();
         this.mVelocityTracker = null;
      }

   }

   boolean shouldHide(View child, float yvel) {
      if (this.mSkipCollapsed) {
         return true;
      } else if (child.getTop() < this.mMaxOffset) {
         return false;
      } else {
         float newTop = (float)child.getTop() + yvel * 0.1F;
         return Math.abs(newTop - (float)this.mMaxOffset) / (float)this.mPeekHeight > 0.5F;
      }
   }

   @VisibleForTesting
   View findScrollingChild(View view) {
      if (ViewCompat.isNestedScrollingEnabled(view)) {
         return view;
      } else {
         if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;
            int i = 0;

            for(int count = group.getChildCount(); i < count; ++i) {
               View scrollingChild = this.findScrollingChild(group.getChildAt(i));
               if (scrollingChild != null) {
                  return scrollingChild;
               }
            }
         }

         return null;
      }
   }

   private float getYVelocity() {
      this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
      return this.mVelocityTracker.getYVelocity(this.mActivePointerId);
   }

   void startSettlingAnimation(View child, int state) {
      int top;
      if (state == 4) {
         top = this.mMaxOffset;
      } else if (state == 3) {
         top = this.mMinOffset;
      } else {
         if (!this.mHideable || state != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + state);
         }

         top = this.mParentHeight;
      }

      if (this.mViewDragHelper.smoothSlideViewTo(child, child.getLeft(), top)) {
         this.setStateInternal(2);
         ViewCompat.postOnAnimation(child, new BottomSheetBehavior.SettleRunnable(child, state));
      } else {
         this.setStateInternal(state);
      }

   }

   void dispatchOnSlide(int top) {
      View bottomSheet = (View)this.mViewRef.get();
      if (bottomSheet != null && this.mCallback != null) {
         if (top > this.mMaxOffset) {
            this.mCallback.onSlide(bottomSheet, (float)(this.mMaxOffset - top) / (float)(this.mParentHeight - this.mMaxOffset));
         } else {
            this.mCallback.onSlide(bottomSheet, (float)(this.mMaxOffset - top) / (float)(this.mMaxOffset - this.mMinOffset));
         }
      }

   }

   @VisibleForTesting
   int getPeekHeightMin() {
      return this.mPeekHeightMin;
   }

   public static BottomSheetBehavior from(View view) {
      LayoutParams params = view.getLayoutParams();
      if (!(params instanceof CoordinatorLayout.LayoutParams)) {
         throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
      } else {
         CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)params).getBehavior();
         if (!(behavior instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
         } else {
            return (BottomSheetBehavior)behavior;
         }
      }
   }

   protected static class SavedState extends AbsSavedState {
      final int state;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public BottomSheetBehavior.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new BottomSheetBehavior.SavedState(in, loader);
         }

         public BottomSheetBehavior.SavedState createFromParcel(Parcel in) {
            return new BottomSheetBehavior.SavedState(in, (ClassLoader)null);
         }

         public BottomSheetBehavior.SavedState[] newArray(int size) {
            return new BottomSheetBehavior.SavedState[size];
         }
      };

      public SavedState(Parcel source) {
         this(source, (ClassLoader)null);
      }

      public SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         this.state = source.readInt();
      }

      public SavedState(Parcelable superState, int state) {
         super(superState);
         this.state = state;
      }

      public void writeToParcel(Parcel out, int flags) {
         super.writeToParcel(out, flags);
         out.writeInt(this.state);
      }
   }

   private class SettleRunnable implements Runnable {
      private final View mView;
      private final int mTargetState;

      SettleRunnable(View view, int targetState) {
         this.mView = view;
         this.mTargetState = targetState;
      }

      public void run() {
         if (BottomSheetBehavior.this.mViewDragHelper != null && BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
         } else {
            BottomSheetBehavior.this.setStateInternal(this.mTargetState);
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface State {
   }

   public abstract static class BottomSheetCallback {
      public abstract void onStateChanged(@NonNull View var1, int var2);

      public abstract void onSlide(@NonNull View var1, float var2);
   }
}
