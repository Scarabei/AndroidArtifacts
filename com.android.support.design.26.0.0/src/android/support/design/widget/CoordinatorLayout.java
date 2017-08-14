package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
   static final String TAG = "CoordinatorLayout";
   static final String WIDGET_PACKAGE_NAME;
   private static final int TYPE_ON_INTERCEPT = 0;
   private static final int TYPE_ON_TOUCH = 1;
   static final Class[] CONSTRUCTOR_PARAMS;
   static final ThreadLocal sConstructors;
   static final int EVENT_PRE_DRAW = 0;
   static final int EVENT_NESTED_SCROLL = 1;
   static final int EVENT_VIEW_REMOVED = 2;
   static final Comparator TOP_SORTED_CHILDREN_COMPARATOR;
   private static final Pool sRectPool;
   private final List mDependencySortedChildren;
   private final DirectedAcyclicGraph mChildDag;
   private final List mTempList1;
   private final List mTempDependenciesList;
   private final int[] mTempIntPair;
   private Paint mScrimPaint;
   private boolean mDisallowInterceptReset;
   private boolean mIsAttachedToWindow;
   private int[] mKeylines;
   private View mBehaviorTouchView;
   private View mNestedScrollingTarget;
   private CoordinatorLayout.OnPreDrawListener mOnPreDrawListener;
   private boolean mNeedsPreDrawListener;
   private WindowInsetsCompat mLastInsets;
   private boolean mDrawStatusBarBackground;
   private Drawable mStatusBarBackground;
   OnHierarchyChangeListener mOnHierarchyChangeListener;
   private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
   private final NestedScrollingParentHelper mNestedScrollingParentHelper;

   @NonNull
   private static Rect acquireTempRect() {
      Rect rect = (Rect)sRectPool.acquire();
      if (rect == null) {
         rect = new Rect();
      }

      return rect;
   }

   private static void releaseTempRect(@NonNull Rect rect) {
      rect.setEmpty();
      sRectPool.release(rect);
   }

   public CoordinatorLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public CoordinatorLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public CoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mDependencySortedChildren = new ArrayList();
      this.mChildDag = new DirectedAcyclicGraph();
      this.mTempList1 = new ArrayList();
      this.mTempDependenciesList = new ArrayList();
      this.mTempIntPair = new int[2];
      this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
      ThemeUtils.checkAppCompatTheme(context);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.CoordinatorLayout, defStyleAttr, style.Widget_Design_CoordinatorLayout);
      int keylineArrayRes = a.getResourceId(styleable.CoordinatorLayout_keylines, 0);
      if (keylineArrayRes != 0) {
         Resources res = context.getResources();
         this.mKeylines = res.getIntArray(keylineArrayRes);
         float density = res.getDisplayMetrics().density;
         int count = this.mKeylines.length;

         for(int i = 0; i < count; ++i) {
            this.mKeylines[i] = (int)((float)this.mKeylines[i] * density);
         }
      }

      this.mStatusBarBackground = a.getDrawable(styleable.CoordinatorLayout_statusBarBackground);
      a.recycle();
      this.setupForInsets();
      super.setOnHierarchyChangeListener(new CoordinatorLayout.HierarchyChangeListener());
   }

   public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
      this.mOnHierarchyChangeListener = onHierarchyChangeListener;
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.resetTouchBehaviors();
      if (this.mNeedsPreDrawListener) {
         if (this.mOnPreDrawListener == null) {
            this.mOnPreDrawListener = new CoordinatorLayout.OnPreDrawListener();
         }

         ViewTreeObserver vto = this.getViewTreeObserver();
         vto.addOnPreDrawListener(this.mOnPreDrawListener);
      }

      if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows(this)) {
         ViewCompat.requestApplyInsets(this);
      }

      this.mIsAttachedToWindow = true;
   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.resetTouchBehaviors();
      if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
         ViewTreeObserver vto = this.getViewTreeObserver();
         vto.removeOnPreDrawListener(this.mOnPreDrawListener);
      }

      if (this.mNestedScrollingTarget != null) {
         this.onStopNestedScroll(this.mNestedScrollingTarget);
      }

      this.mIsAttachedToWindow = false;
   }

   public void setStatusBarBackground(@Nullable Drawable bg) {
      if (this.mStatusBarBackground != bg) {
         if (this.mStatusBarBackground != null) {
            this.mStatusBarBackground.setCallback((Callback)null);
         }

         this.mStatusBarBackground = bg != null ? bg.mutate() : null;
         if (this.mStatusBarBackground != null) {
            if (this.mStatusBarBackground.isStateful()) {
               this.mStatusBarBackground.setState(this.getDrawableState());
            }

            DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
            this.mStatusBarBackground.setVisible(this.getVisibility() == 0, false);
            this.mStatusBarBackground.setCallback(this);
         }

         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   @Nullable
   public Drawable getStatusBarBackground() {
      return this.mStatusBarBackground;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      int[] state = this.getDrawableState();
      boolean changed = false;
      Drawable d = this.mStatusBarBackground;
      if (d != null && d.isStateful()) {
         changed |= d.setState(state);
      }

      if (changed) {
         this.invalidate();
      }

   }

   protected boolean verifyDrawable(Drawable who) {
      return super.verifyDrawable(who) || who == this.mStatusBarBackground;
   }

   public void setVisibility(int visibility) {
      super.setVisibility(visibility);
      boolean visible = visibility == 0;
      if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != visible) {
         this.mStatusBarBackground.setVisible(visible, false);
      }

   }

   public void setStatusBarBackgroundResource(@DrawableRes int resId) {
      this.setStatusBarBackground(resId != 0 ? ContextCompat.getDrawable(this.getContext(), resId) : null);
   }

   public void setStatusBarBackgroundColor(@ColorInt int color) {
      this.setStatusBarBackground(new ColorDrawable(color));
   }

   final WindowInsetsCompat setWindowInsets(WindowInsetsCompat insets) {
      if (!ObjectsCompat.equals(this.mLastInsets, insets)) {
         this.mLastInsets = insets;
         this.mDrawStatusBarBackground = insets != null && insets.getSystemWindowInsetTop() > 0;
         this.setWillNotDraw(!this.mDrawStatusBarBackground && this.getBackground() == null);
         insets = this.dispatchApplyWindowInsetsToBehaviors(insets);
         this.requestLayout();
      }

      return insets;
   }

   final WindowInsetsCompat getLastWindowInsets() {
      return this.mLastInsets;
   }

   private void resetTouchBehaviors() {
      if (this.mBehaviorTouchView != null) {
         CoordinatorLayout.Behavior b = ((CoordinatorLayout.LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
         if (b != null) {
            long now = SystemClock.uptimeMillis();
            MotionEvent cancelEvent = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
            b.onTouchEvent(this, this.mBehaviorTouchView, cancelEvent);
            cancelEvent.recycle();
         }

         this.mBehaviorTouchView = null;
      }

      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View child = this.getChildAt(i);
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         lp.resetTouchBehaviorTracking();
      }

      this.mDisallowInterceptReset = false;
   }

   private void getTopSortedChildren(List out) {
      out.clear();
      boolean useCustomOrder = this.isChildrenDrawingOrderEnabled();
      int childCount = this.getChildCount();

      for(int i = childCount - 1; i >= 0; --i) {
         int childIndex = useCustomOrder ? this.getChildDrawingOrder(childCount, i) : i;
         View child = this.getChildAt(childIndex);
         out.add(child);
      }

      if (TOP_SORTED_CHILDREN_COMPARATOR != null) {
         Collections.sort(out, TOP_SORTED_CHILDREN_COMPARATOR);
      }

   }

   private boolean performIntercept(MotionEvent ev, int type) {
      boolean intercepted = false;
      boolean newBlock = false;
      MotionEvent cancelEvent = null;
      int action = ev.getActionMasked();
      List topmostChildList = this.mTempList1;
      this.getTopSortedChildren(topmostChildList);
      int childCount = topmostChildList.size();

      for(int i = 0; i < childCount; ++i) {
         View child = (View)topmostChildList.get(i);
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         CoordinatorLayout.Behavior b = lp.getBehavior();
         if ((intercepted || newBlock) && action != 0) {
            if (b != null) {
               if (cancelEvent == null) {
                  long now = SystemClock.uptimeMillis();
                  cancelEvent = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
               }

               switch(type) {
               case 0:
                  b.onInterceptTouchEvent(this, child, cancelEvent);
                  break;
               case 1:
                  b.onTouchEvent(this, child, cancelEvent);
               }
            }
         } else {
            if (!intercepted && b != null) {
               switch(type) {
               case 0:
                  intercepted = b.onInterceptTouchEvent(this, child, ev);
                  break;
               case 1:
                  intercepted = b.onTouchEvent(this, child, ev);
               }

               if (intercepted) {
                  this.mBehaviorTouchView = child;
               }
            }

            boolean wasBlocking = lp.didBlockInteraction();
            boolean isBlocking = lp.isBlockingInteractionBelow(this, child);
            newBlock = isBlocking && !wasBlocking;
            if (isBlocking && !newBlock) {
               break;
            }
         }
      }

      topmostChildList.clear();
      return intercepted;
   }

   public boolean onInterceptTouchEvent(MotionEvent ev) {
      MotionEvent cancelEvent = null;
      int action = ev.getActionMasked();
      if (action == 0) {
         this.resetTouchBehaviors();
      }

      boolean intercepted = this.performIntercept(ev, 0);
      if (cancelEvent != null) {
         ((MotionEvent)cancelEvent).recycle();
      }

      if (action == 1 || action == 3) {
         this.resetTouchBehaviors();
      }

      return intercepted;
   }

   public boolean onTouchEvent(MotionEvent ev) {
      boolean handled = false;
      boolean cancelSuper = false;
      MotionEvent cancelEvent = null;
      int action = ev.getActionMasked();
      if (this.mBehaviorTouchView != null || (cancelSuper = this.performIntercept(ev, 1))) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)this.mBehaviorTouchView.getLayoutParams();
         CoordinatorLayout.Behavior b = lp.getBehavior();
         if (b != null) {
            handled = b.onTouchEvent(this, this.mBehaviorTouchView, ev);
         }
      }

      if (this.mBehaviorTouchView == null) {
         handled |= super.onTouchEvent(ev);
      } else if (cancelSuper) {
         if (cancelEvent == null) {
            long now = SystemClock.uptimeMillis();
            cancelEvent = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
         }

         super.onTouchEvent(cancelEvent);
      }

      if (!handled && action == 0) {
         ;
      }

      if (cancelEvent != null) {
         cancelEvent.recycle();
      }

      if (action == 1 || action == 3) {
         this.resetTouchBehaviors();
      }

      return handled;
   }

   public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
      super.requestDisallowInterceptTouchEvent(disallowIntercept);
      if (disallowIntercept && !this.mDisallowInterceptReset) {
         this.resetTouchBehaviors();
         this.mDisallowInterceptReset = true;
      }

   }

   private int getKeyline(int index) {
      if (this.mKeylines == null) {
         Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + index);
         return 0;
      } else if (index >= 0 && index < this.mKeylines.length) {
         return this.mKeylines[index];
      } else {
         Log.e("CoordinatorLayout", "Keyline index " + index + " out of range for " + this);
         return 0;
      }
   }

   static CoordinatorLayout.Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
      if (TextUtils.isEmpty(name)) {
         return null;
      } else {
         String fullName;
         if (name.startsWith(".")) {
            fullName = context.getPackageName() + name;
         } else if (name.indexOf(46) >= 0) {
            fullName = name;
         } else {
            fullName = !TextUtils.isEmpty(WIDGET_PACKAGE_NAME) ? WIDGET_PACKAGE_NAME + '.' + name : name;
         }

         try {
            Map constructors = (Map)sConstructors.get();
            if (constructors == null) {
               constructors = new HashMap();
               sConstructors.set(constructors);
            }

            Constructor c = (Constructor)((Map)constructors).get(fullName);
            if (c == null) {
               Class clazz = Class.forName(fullName, true, context.getClassLoader());
               c = clazz.getConstructor(CONSTRUCTOR_PARAMS);
               c.setAccessible(true);
               ((Map)constructors).put(fullName, c);
            }

            return (CoordinatorLayout.Behavior)c.newInstance(context, attrs);
         } catch (Exception var7) {
            throw new RuntimeException("Could not inflate Behavior subclass " + fullName, var7);
         }
      }
   }

   CoordinatorLayout.LayoutParams getResolvedLayoutParams(View child) {
      CoordinatorLayout.LayoutParams result = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (!result.mBehaviorResolved) {
         Class childClass = child.getClass();

         CoordinatorLayout.DefaultBehavior defaultBehavior;
         for(defaultBehavior = null; childClass != null && (defaultBehavior = (CoordinatorLayout.DefaultBehavior)childClass.getAnnotation(CoordinatorLayout.DefaultBehavior.class)) == null; childClass = childClass.getSuperclass()) {
            ;
         }

         if (defaultBehavior != null) {
            try {
               result.setBehavior((CoordinatorLayout.Behavior)defaultBehavior.value().getDeclaredConstructor().newInstance());
            } catch (Exception var6) {
               Log.e("CoordinatorLayout", "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget a default constructor?", var6);
            }
         }

         result.mBehaviorResolved = true;
      }

      return result;
   }

   private void prepareChildren() {
      this.mDependencySortedChildren.clear();
      this.mChildDag.clear();
      int i = 0;

      for(int count = this.getChildCount(); i < count; ++i) {
         View view = this.getChildAt(i);
         CoordinatorLayout.LayoutParams lp = this.getResolvedLayoutParams(view);
         lp.findAnchorView(this, view);
         this.mChildDag.addNode(view);

         for(int j = 0; j < count; ++j) {
            if (j != i) {
               View other = this.getChildAt(j);
               if (lp.dependsOn(this, view, other)) {
                  if (!this.mChildDag.contains(other)) {
                     this.mChildDag.addNode(other);
                  }

                  this.mChildDag.addEdge(other, view);
               }
            }
         }
      }

      this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
      Collections.reverse(this.mDependencySortedChildren);
   }

   void getDescendantRect(View descendant, Rect out) {
      ViewGroupUtils.getDescendantRect(this, descendant, out);
   }

   protected int getSuggestedMinimumWidth() {
      return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
   }

   protected int getSuggestedMinimumHeight() {
      return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
   }

   public void onMeasureChild(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
      this.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.prepareChildren();
      this.ensurePreDrawListener();
      int paddingLeft = this.getPaddingLeft();
      int paddingTop = this.getPaddingTop();
      int paddingRight = this.getPaddingRight();
      int paddingBottom = this.getPaddingBottom();
      int layoutDirection = ViewCompat.getLayoutDirection(this);
      boolean isRtl = layoutDirection == 1;
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      int widthPadding = paddingLeft + paddingRight;
      int heightPadding = paddingTop + paddingBottom;
      int widthUsed = this.getSuggestedMinimumWidth();
      int heightUsed = this.getSuggestedMinimumHeight();
      int childState = 0;
      boolean applyInsets = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
      int childCount = this.mDependencySortedChildren.size();

      int width;
      for(width = 0; width < childCount; ++width) {
         View child = (View)this.mDependencySortedChildren.get(width);
         if (child.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            int keylineWidthUsed = 0;
            int childWidthMeasureSpec;
            int childHeightMeasureSpec;
            if (lp.keyline >= 0 && widthMode != 0) {
               childWidthMeasureSpec = this.getKeyline(lp.keyline);
               childHeightMeasureSpec = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(lp.gravity), layoutDirection) & 7;
               if ((childHeightMeasureSpec != 3 || isRtl) && (childHeightMeasureSpec != 5 || !isRtl)) {
                  if (childHeightMeasureSpec == 5 && !isRtl || childHeightMeasureSpec == 3 && isRtl) {
                     keylineWidthUsed = Math.max(0, childWidthMeasureSpec - paddingLeft);
                  }
               } else {
                  keylineWidthUsed = Math.max(0, widthSize - paddingRight - childWidthMeasureSpec);
               }
            }

            childWidthMeasureSpec = widthMeasureSpec;
            childHeightMeasureSpec = heightMeasureSpec;
            if (applyInsets && !ViewCompat.getFitsSystemWindows(child)) {
               int horizInsets = this.mLastInsets.getSystemWindowInsetLeft() + this.mLastInsets.getSystemWindowInsetRight();
               int vertInsets = this.mLastInsets.getSystemWindowInsetTop() + this.mLastInsets.getSystemWindowInsetBottom();
               childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize - horizInsets, widthMode);
               childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize - vertInsets, heightMode);
            }

            CoordinatorLayout.Behavior b = lp.getBehavior();
            if (b == null || !b.onMeasureChild(this, child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0)) {
               this.onMeasureChild(child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0);
            }

            widthUsed = Math.max(widthUsed, widthPadding + child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
            heightUsed = Math.max(heightUsed, heightPadding + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            childState = View.combineMeasuredStates(childState, child.getMeasuredState());
         }
      }

      width = View.resolveSizeAndState(widthUsed, widthMeasureSpec, childState & -16777216);
      int height = View.resolveSizeAndState(heightUsed, heightMeasureSpec, childState << 16);
      this.setMeasuredDimension(width, height);
   }

   private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat insets) {
      if (insets.isConsumed()) {
         return insets;
      } else {
         int i = 0;

         for(int z = this.getChildCount(); i < z; ++i) {
            View child = this.getChildAt(i);
            if (ViewCompat.getFitsSystemWindows(child)) {
               CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
               CoordinatorLayout.Behavior b = lp.getBehavior();
               if (b != null) {
                  insets = b.onApplyWindowInsets(this, child, insets);
                  if (insets.isConsumed()) {
                     break;
                  }
               }
            }
         }

         return insets;
      }
   }

   public void onLayoutChild(View child, int layoutDirection) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (lp.checkAnchorChanged()) {
         throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
      } else {
         if (lp.mAnchorView != null) {
            this.layoutChildWithAnchor(child, lp.mAnchorView, layoutDirection);
         } else if (lp.keyline >= 0) {
            this.layoutChildWithKeyline(child, lp.keyline, layoutDirection);
         } else {
            this.layoutChild(child, layoutDirection);
         }

      }
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      int layoutDirection = ViewCompat.getLayoutDirection(this);
      int childCount = this.mDependencySortedChildren.size();

      for(int i = 0; i < childCount; ++i) {
         View child = (View)this.mDependencySortedChildren.get(i);
         if (child.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            CoordinatorLayout.Behavior behavior = lp.getBehavior();
            if (behavior == null || !behavior.onLayoutChild(this, child, layoutDirection)) {
               this.onLayoutChild(child, layoutDirection);
            }
         }
      }

   }

   public void onDraw(Canvas c) {
      super.onDraw(c);
      if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
         int inset = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
         if (inset > 0) {
            this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), inset);
            this.mStatusBarBackground.draw(c);
         }
      }

   }

   public void setFitsSystemWindows(boolean fitSystemWindows) {
      super.setFitsSystemWindows(fitSystemWindows);
      this.setupForInsets();
   }

   void recordLastChildRect(View child, Rect r) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      lp.setLastChildRect(r);
   }

   void getLastChildRect(View child, Rect out) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      out.set(lp.getLastChildRect());
   }

   void getChildRect(View child, boolean transform, Rect out) {
      if (!child.isLayoutRequested() && child.getVisibility() != 8) {
         if (transform) {
            this.getDescendantRect(child, out);
         } else {
            out.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
         }

      } else {
         out.setEmpty();
      }
   }

   private void getDesiredAnchoredChildRectWithoutConstraints(View child, int layoutDirection, Rect anchorRect, Rect out, CoordinatorLayout.LayoutParams lp, int childWidth, int childHeight) {
      int absGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(lp.gravity), layoutDirection);
      int absAnchorGravity = GravityCompat.getAbsoluteGravity(resolveGravity(lp.anchorGravity), layoutDirection);
      int hgrav = absGravity & 7;
      int vgrav = absGravity & 112;
      int anchorHgrav = absAnchorGravity & 7;
      int anchorVgrav = absAnchorGravity & 112;
      int left;
      switch(anchorHgrav) {
      case 1:
         left = anchorRect.left + anchorRect.width() / 2;
         break;
      case 2:
      case 3:
      case 4:
      default:
         left = anchorRect.left;
         break;
      case 5:
         left = anchorRect.right;
      }

      int top;
      switch(anchorVgrav) {
      case 16:
         top = anchorRect.top + anchorRect.height() / 2;
         break;
      case 48:
      default:
         top = anchorRect.top;
         break;
      case 80:
         top = anchorRect.bottom;
      }

      switch(hgrav) {
      case 1:
         left -= childWidth / 2;
         break;
      case 2:
      case 3:
      case 4:
      default:
         left -= childWidth;
      case 5:
      }

      switch(vgrav) {
      case 16:
         top -= childHeight / 2;
         break;
      case 48:
      default:
         top -= childHeight;
      case 80:
      }

      out.set(left, top, left + childWidth, top + childHeight);
   }

   private void constrainChildRect(CoordinatorLayout.LayoutParams lp, Rect out, int childWidth, int childHeight) {
      int width = this.getWidth();
      int height = this.getHeight();
      int left = Math.max(this.getPaddingLeft() + lp.leftMargin, Math.min(out.left, width - this.getPaddingRight() - childWidth - lp.rightMargin));
      int top = Math.max(this.getPaddingTop() + lp.topMargin, Math.min(out.top, height - this.getPaddingBottom() - childHeight - lp.bottomMargin));
      out.set(left, top, left + childWidth, top + childHeight);
   }

   void getDesiredAnchoredChildRect(View child, int layoutDirection, Rect anchorRect, Rect out) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      int childWidth = child.getMeasuredWidth();
      int childHeight = child.getMeasuredHeight();
      this.getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, out, lp, childWidth, childHeight);
      this.constrainChildRect(lp, out, childWidth, childHeight);
   }

   private void layoutChildWithAnchor(View child, View anchor, int layoutDirection) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      Rect anchorRect = acquireTempRect();
      Rect childRect = acquireTempRect();

      try {
         this.getDescendantRect(anchor, anchorRect);
         this.getDesiredAnchoredChildRect(child, layoutDirection, anchorRect, childRect);
         child.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
      } finally {
         releaseTempRect(anchorRect);
         releaseTempRect(childRect);
      }

   }

   private void layoutChildWithKeyline(View child, int keyline, int layoutDirection) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      int absGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(lp.gravity), layoutDirection);
      int hgrav = absGravity & 7;
      int vgrav = absGravity & 112;
      int width = this.getWidth();
      int height = this.getHeight();
      int childWidth = child.getMeasuredWidth();
      int childHeight = child.getMeasuredHeight();
      if (layoutDirection == 1) {
         keyline = width - keyline;
      }

      int left = this.getKeyline(keyline) - childWidth;
      int top = 0;
      switch(hgrav) {
      case 1:
         left += childWidth / 2;
      case 2:
      case 3:
      case 4:
      default:
         break;
      case 5:
         left += childWidth;
      }

      switch(vgrav) {
      case 16:
         top += childHeight / 2;
      case 48:
      default:
         break;
      case 80:
         top += childHeight;
      }

      left = Math.max(this.getPaddingLeft() + lp.leftMargin, Math.min(left, width - this.getPaddingRight() - childWidth - lp.rightMargin));
      top = Math.max(this.getPaddingTop() + lp.topMargin, Math.min(top, height - this.getPaddingBottom() - childHeight - lp.bottomMargin));
      child.layout(left, top, left + childWidth, top + childHeight);
   }

   private void layoutChild(View child, int layoutDirection) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      Rect parent = acquireTempRect();
      parent.set(this.getPaddingLeft() + lp.leftMargin, this.getPaddingTop() + lp.topMargin, this.getWidth() - this.getPaddingRight() - lp.rightMargin, this.getHeight() - this.getPaddingBottom() - lp.bottomMargin);
      if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(child)) {
         parent.left += this.mLastInsets.getSystemWindowInsetLeft();
         parent.top += this.mLastInsets.getSystemWindowInsetTop();
         parent.right -= this.mLastInsets.getSystemWindowInsetRight();
         parent.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
      }

      Rect out = acquireTempRect();
      GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), parent, out, layoutDirection);
      child.layout(out.left, out.top, out.right, out.bottom);
      releaseTempRect(parent);
      releaseTempRect(out);
   }

   private static int resolveGravity(int gravity) {
      if ((gravity & 7) == 0) {
         gravity |= 8388611;
      }

      if ((gravity & 112) == 0) {
         gravity |= 48;
      }

      return gravity;
   }

   private static int resolveKeylineGravity(int gravity) {
      return gravity == 0 ? 8388661 : gravity;
   }

   private static int resolveAnchoredChildGravity(int gravity) {
      return gravity == 0 ? 17 : gravity;
   }

   protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (lp.mBehavior != null) {
         float scrimAlpha = lp.mBehavior.getScrimOpacity(this, child);
         if (scrimAlpha > 0.0F) {
            if (this.mScrimPaint == null) {
               this.mScrimPaint = new Paint();
            }

            this.mScrimPaint.setColor(lp.mBehavior.getScrimColor(this, child));
            this.mScrimPaint.setAlpha(MathUtils.clamp(Math.round(255.0F * scrimAlpha), 0, 255));
            int saved = canvas.save();
            if (child.isOpaque()) {
               canvas.clipRect((float)child.getLeft(), (float)child.getTop(), (float)child.getRight(), (float)child.getBottom(), Op.DIFFERENCE);
            }

            canvas.drawRect((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()), this.mScrimPaint);
            canvas.restoreToCount(saved);
         }
      }

      return super.drawChild(canvas, child, drawingTime);
   }

   final void onChildViewsChanged(int type) {
      int layoutDirection = ViewCompat.getLayoutDirection(this);
      int childCount = this.mDependencySortedChildren.size();
      Rect inset = acquireTempRect();
      Rect drawRect = acquireTempRect();
      Rect lastDrawRect = acquireTempRect();

      for(int i = 0; i < childCount; ++i) {
         View child = (View)this.mDependencySortedChildren.get(i);
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         if (type != 0 || child.getVisibility() != 8) {
            int j;
            View checkChild;
            for(j = 0; j < i; ++j) {
               checkChild = (View)this.mDependencySortedChildren.get(j);
               if (lp.mAnchorDirectChild == checkChild) {
                  this.offsetChildToAnchor(child, layoutDirection);
               }
            }

            this.getChildRect(child, true, drawRect);
            if (lp.insetEdge != 0 && !drawRect.isEmpty()) {
               j = GravityCompat.getAbsoluteGravity(lp.insetEdge, layoutDirection);
               switch(j & 112) {
               case 48:
                  inset.top = Math.max(inset.top, drawRect.bottom);
                  break;
               case 80:
                  inset.bottom = Math.max(inset.bottom, this.getHeight() - drawRect.top);
               }

               switch(j & 7) {
               case 3:
                  inset.left = Math.max(inset.left, drawRect.right);
                  break;
               case 5:
                  inset.right = Math.max(inset.right, this.getWidth() - drawRect.left);
               }
            }

            if (lp.dodgeInsetEdges != 0 && child.getVisibility() == 0) {
               this.offsetChildByInset(child, inset, layoutDirection);
            }

            if (type != 2) {
               this.getLastChildRect(child, lastDrawRect);
               if (lastDrawRect.equals(drawRect)) {
                  continue;
               }

               this.recordLastChildRect(child, drawRect);
            }

            for(j = i + 1; j < childCount; ++j) {
               checkChild = (View)this.mDependencySortedChildren.get(j);
               CoordinatorLayout.LayoutParams checkLp = (CoordinatorLayout.LayoutParams)checkChild.getLayoutParams();
               CoordinatorLayout.Behavior b = checkLp.getBehavior();
               if (b != null && b.layoutDependsOn(this, checkChild, child)) {
                  if (type == 0 && checkLp.getChangedAfterNestedScroll()) {
                     checkLp.resetChangedAfterNestedScroll();
                  } else {
                     boolean handled;
                     switch(type) {
                     case 2:
                        b.onDependentViewRemoved(this, checkChild, child);
                        handled = true;
                        break;
                     default:
                        handled = b.onDependentViewChanged(this, checkChild, child);
                     }

                     if (type == 1) {
                        checkLp.setChangedAfterNestedScroll(handled);
                     }
                  }
               }
            }
         }
      }

      releaseTempRect(inset);
      releaseTempRect(drawRect);
      releaseTempRect(lastDrawRect);
   }

   private void offsetChildByInset(View child, Rect inset, int layoutDirection) {
      if (ViewCompat.isLaidOut(child)) {
         if (child.getWidth() > 0 && child.getHeight() > 0) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            CoordinatorLayout.Behavior behavior = lp.getBehavior();
            Rect dodgeRect = acquireTempRect();
            Rect bounds = acquireTempRect();
            bounds.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (behavior != null && behavior.getInsetDodgeRect(this, child, dodgeRect)) {
               if (!bounds.contains(dodgeRect)) {
                  throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + dodgeRect.toShortString() + " | Bounds:" + bounds.toShortString());
               }
            } else {
               dodgeRect.set(bounds);
            }

            releaseTempRect(bounds);
            if (dodgeRect.isEmpty()) {
               releaseTempRect(dodgeRect);
            } else {
               int absDodgeInsetEdges = GravityCompat.getAbsoluteGravity(lp.dodgeInsetEdges, layoutDirection);
               boolean offsetY = false;
               int distance;
               if ((absDodgeInsetEdges & 48) == 48) {
                  distance = dodgeRect.top - lp.topMargin - lp.mInsetOffsetY;
                  if (distance < inset.top) {
                     this.setInsetOffsetY(child, inset.top - distance);
                     offsetY = true;
                  }
               }

               if ((absDodgeInsetEdges & 80) == 80) {
                  distance = this.getHeight() - dodgeRect.bottom - lp.bottomMargin + lp.mInsetOffsetY;
                  if (distance < inset.bottom) {
                     this.setInsetOffsetY(child, distance - inset.bottom);
                     offsetY = true;
                  }
               }

               if (!offsetY) {
                  this.setInsetOffsetY(child, 0);
               }

               boolean offsetX = false;
               int distance;
               if ((absDodgeInsetEdges & 3) == 3) {
                  distance = dodgeRect.left - lp.leftMargin - lp.mInsetOffsetX;
                  if (distance < inset.left) {
                     this.setInsetOffsetX(child, inset.left - distance);
                     offsetX = true;
                  }
               }

               if ((absDodgeInsetEdges & 5) == 5) {
                  distance = this.getWidth() - dodgeRect.right - lp.rightMargin + lp.mInsetOffsetX;
                  if (distance < inset.right) {
                     this.setInsetOffsetX(child, distance - inset.right);
                     offsetX = true;
                  }
               }

               if (!offsetX) {
                  this.setInsetOffsetX(child, 0);
               }

               releaseTempRect(dodgeRect);
            }
         }
      }
   }

   private void setInsetOffsetX(View child, int offsetX) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (lp.mInsetOffsetX != offsetX) {
         int dx = offsetX - lp.mInsetOffsetX;
         ViewCompat.offsetLeftAndRight(child, dx);
         lp.mInsetOffsetX = offsetX;
      }

   }

   private void setInsetOffsetY(View child, int offsetY) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (lp.mInsetOffsetY != offsetY) {
         int dy = offsetY - lp.mInsetOffsetY;
         ViewCompat.offsetTopAndBottom(child, dy);
         lp.mInsetOffsetY = offsetY;
      }

   }

   public void dispatchDependentViewsChanged(View view) {
      List dependents = this.mChildDag.getIncomingEdges(view);
      if (dependents != null && !dependents.isEmpty()) {
         for(int i = 0; i < dependents.size(); ++i) {
            View child = (View)dependents.get(i);
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            CoordinatorLayout.Behavior b = lp.getBehavior();
            if (b != null) {
               b.onDependentViewChanged(this, child, view);
            }
         }
      }

   }

   @NonNull
   public List getDependencies(@NonNull View child) {
      List dependencies = this.mChildDag.getOutgoingEdges(child);
      this.mTempDependenciesList.clear();
      if (dependencies != null) {
         this.mTempDependenciesList.addAll(dependencies);
      }

      return this.mTempDependenciesList;
   }

   @NonNull
   public List getDependents(@NonNull View child) {
      List edges = this.mChildDag.getIncomingEdges(child);
      this.mTempDependenciesList.clear();
      if (edges != null) {
         this.mTempDependenciesList.addAll(edges);
      }

      return this.mTempDependenciesList;
   }

   @VisibleForTesting
   final List getDependencySortedChildren() {
      this.prepareChildren();
      return Collections.unmodifiableList(this.mDependencySortedChildren);
   }

   void ensurePreDrawListener() {
      boolean hasDependencies = false;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View child = this.getChildAt(i);
         if (this.hasDependencies(child)) {
            hasDependencies = true;
            break;
         }
      }

      if (hasDependencies != this.mNeedsPreDrawListener) {
         if (hasDependencies) {
            this.addPreDrawListener();
         } else {
            this.removePreDrawListener();
         }
      }

   }

   private boolean hasDependencies(View child) {
      return this.mChildDag.hasOutgoingEdges(child);
   }

   void addPreDrawListener() {
      if (this.mIsAttachedToWindow) {
         if (this.mOnPreDrawListener == null) {
            this.mOnPreDrawListener = new CoordinatorLayout.OnPreDrawListener();
         }

         ViewTreeObserver vto = this.getViewTreeObserver();
         vto.addOnPreDrawListener(this.mOnPreDrawListener);
      }

      this.mNeedsPreDrawListener = true;
   }

   void removePreDrawListener() {
      if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
         ViewTreeObserver vto = this.getViewTreeObserver();
         vto.removeOnPreDrawListener(this.mOnPreDrawListener);
      }

      this.mNeedsPreDrawListener = false;
   }

   void offsetChildToAnchor(View child, int layoutDirection) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      if (lp.mAnchorView != null) {
         Rect anchorRect = acquireTempRect();
         Rect childRect = acquireTempRect();
         Rect desiredChildRect = acquireTempRect();
         this.getDescendantRect(lp.mAnchorView, anchorRect);
         this.getChildRect(child, false, childRect);
         int childWidth = child.getMeasuredWidth();
         int childHeight = child.getMeasuredHeight();
         this.getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, desiredChildRect, lp, childWidth, childHeight);
         boolean changed = desiredChildRect.left != childRect.left || desiredChildRect.top != childRect.top;
         this.constrainChildRect(lp, desiredChildRect, childWidth, childHeight);
         int dx = desiredChildRect.left - childRect.left;
         int dy = desiredChildRect.top - childRect.top;
         if (dx != 0) {
            ViewCompat.offsetLeftAndRight(child, dx);
         }

         if (dy != 0) {
            ViewCompat.offsetTopAndBottom(child, dy);
         }

         if (changed) {
            CoordinatorLayout.Behavior b = lp.getBehavior();
            if (b != null) {
               b.onDependentViewChanged(this, child, lp.mAnchorView);
            }
         }

         releaseTempRect(anchorRect);
         releaseTempRect(childRect);
         releaseTempRect(desiredChildRect);
      }

   }

   public boolean isPointInChildBounds(View child, int x, int y) {
      Rect r = acquireTempRect();
      this.getDescendantRect(child, r);

      boolean var5;
      try {
         var5 = r.contains(x, y);
      } finally {
         releaseTempRect(r);
      }

      return var5;
   }

   public boolean doViewsOverlap(View first, View second) {
      if (first.getVisibility() == 0 && second.getVisibility() == 0) {
         Rect firstRect = acquireTempRect();
         this.getChildRect(first, first.getParent() != this, firstRect);
         Rect secondRect = acquireTempRect();
         this.getChildRect(second, second.getParent() != this, secondRect);

         boolean var5;
         try {
            var5 = firstRect.left <= secondRect.right && firstRect.top <= secondRect.bottom && firstRect.right >= secondRect.left && firstRect.bottom >= secondRect.top;
         } finally {
            releaseTempRect(firstRect);
            releaseTempRect(secondRect);
         }

         return var5;
      } else {
         return false;
      }
   }

   public CoordinatorLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new CoordinatorLayout.LayoutParams(this.getContext(), attrs);
   }

   protected CoordinatorLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      if (p instanceof CoordinatorLayout.LayoutParams) {
         return new CoordinatorLayout.LayoutParams((CoordinatorLayout.LayoutParams)p);
      } else {
         return p instanceof MarginLayoutParams ? new CoordinatorLayout.LayoutParams((MarginLayoutParams)p) : new CoordinatorLayout.LayoutParams(p);
      }
   }

   protected CoordinatorLayout.LayoutParams generateDefaultLayoutParams() {
      return new CoordinatorLayout.LayoutParams(-2, -2);
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof CoordinatorLayout.LayoutParams && super.checkLayoutParams(p);
   }

   public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
      return this.onStartNestedScroll(child, target, nestedScrollAxes, 0);
   }

   public boolean onStartNestedScroll(View child, View target, int axes, int type) {
      boolean handled = false;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         if (view.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
            if (viewBehavior != null) {
               boolean accepted = viewBehavior.onStartNestedScroll(this, view, child, target, axes, type);
               handled |= accepted;
               lp.setNestedScrollAccepted(type, accepted);
            } else {
               lp.setNestedScrollAccepted(type, false);
            }
         }
      }

      return handled;
   }

   public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
      this.onNestedScrollAccepted(child, target, nestedScrollAxes, 0);
   }

   public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes, int type) {
      this.mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes, type);
      this.mNestedScrollingTarget = target;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
         if (lp.isNestedScrollAccepted(type)) {
            CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
            if (viewBehavior != null) {
               viewBehavior.onNestedScrollAccepted(this, view, child, target, nestedScrollAxes, type);
            }
         }
      }

   }

   public void onStopNestedScroll(View target) {
      this.onStopNestedScroll(target, 0);
   }

   public void onStopNestedScroll(View target, int type) {
      this.mNestedScrollingParentHelper.onStopNestedScroll(target, type);
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
         if (lp.isNestedScrollAccepted(type)) {
            CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
            if (viewBehavior != null) {
               viewBehavior.onStopNestedScroll(this, view, target, type);
            }

            lp.resetNestedScroll(type);
            lp.resetChangedAfterNestedScroll();
         }
      }

      this.mNestedScrollingTarget = null;
   }

   public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
      this.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
   }

   public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
      int childCount = this.getChildCount();
      boolean accepted = false;

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         if (view.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            if (lp.isNestedScrollAccepted(type)) {
               CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
               if (viewBehavior != null) {
                  viewBehavior.onNestedScroll(this, view, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
                  accepted = true;
               }
            }
         }
      }

      if (accepted) {
         this.onChildViewsChanged(1);
      }

   }

   public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
      this.onNestedPreScroll(target, dx, dy, consumed, 0);
   }

   public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
      int xConsumed = 0;
      int yConsumed = 0;
      boolean accepted = false;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         if (view.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            if (lp.isNestedScrollAccepted(type)) {
               CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
               if (viewBehavior != null) {
                  this.mTempIntPair[0] = this.mTempIntPair[1] = 0;
                  viewBehavior.onNestedPreScroll(this, view, target, dx, dy, this.mTempIntPair, type);
                  xConsumed = dx > 0 ? Math.max(xConsumed, this.mTempIntPair[0]) : Math.min(xConsumed, this.mTempIntPair[0]);
                  yConsumed = dy > 0 ? Math.max(yConsumed, this.mTempIntPair[1]) : Math.min(yConsumed, this.mTempIntPair[1]);
                  accepted = true;
               }
            }
         }
      }

      consumed[0] = xConsumed;
      consumed[1] = yConsumed;
      if (accepted) {
         this.onChildViewsChanged(1);
      }

   }

   public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
      boolean handled = false;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         if (view.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            if (lp.isNestedScrollAccepted(0)) {
               CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
               if (viewBehavior != null) {
                  handled |= viewBehavior.onNestedFling(this, view, target, velocityX, velocityY, consumed);
               }
            }
         }
      }

      if (handled) {
         this.onChildViewsChanged(1);
      }

      return handled;
   }

   public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
      boolean handled = false;
      int childCount = this.getChildCount();

      for(int i = 0; i < childCount; ++i) {
         View view = this.getChildAt(i);
         if (view.getVisibility() != 8) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            if (lp.isNestedScrollAccepted(0)) {
               CoordinatorLayout.Behavior viewBehavior = lp.getBehavior();
               if (viewBehavior != null) {
                  handled |= viewBehavior.onNestedPreFling(this, view, target, velocityX, velocityY);
               }
            }
         }
      }

      return handled;
   }

   public int getNestedScrollAxes() {
      return this.mNestedScrollingParentHelper.getNestedScrollAxes();
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof CoordinatorLayout.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         CoordinatorLayout.SavedState ss = (CoordinatorLayout.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         SparseArray behaviorStates = ss.behaviorStates;
         int i = 0;

         for(int count = this.getChildCount(); i < count; ++i) {
            View child = this.getChildAt(i);
            int childId = child.getId();
            CoordinatorLayout.LayoutParams lp = this.getResolvedLayoutParams(child);
            CoordinatorLayout.Behavior b = lp.getBehavior();
            if (childId != -1 && b != null) {
               Parcelable savedState = (Parcelable)behaviorStates.get(childId);
               if (savedState != null) {
                  b.onRestoreInstanceState(this, child, savedState);
               }
            }
         }

      }
   }

   protected Parcelable onSaveInstanceState() {
      CoordinatorLayout.SavedState ss = new CoordinatorLayout.SavedState(super.onSaveInstanceState());
      SparseArray behaviorStates = new SparseArray();
      int i = 0;

      for(int count = this.getChildCount(); i < count; ++i) {
         View child = this.getChildAt(i);
         int childId = child.getId();
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         CoordinatorLayout.Behavior b = lp.getBehavior();
         if (childId != -1 && b != null) {
            Parcelable state = b.onSaveInstanceState(this, child);
            if (state != null) {
               behaviorStates.append(childId, state);
            }
         }
      }

      ss.behaviorStates = behaviorStates;
      return ss;
   }

   public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
      CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
      CoordinatorLayout.Behavior behavior = lp.getBehavior();
      return behavior != null && behavior.onRequestChildRectangleOnScreen(this, child, rectangle, immediate) ? true : super.requestChildRectangleOnScreen(child, rectangle, immediate);
   }

   private void setupForInsets() {
      if (VERSION.SDK_INT >= 21) {
         if (ViewCompat.getFitsSystemWindows(this)) {
            if (this.mApplyWindowInsetsListener == null) {
               this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {
                  public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                     return CoordinatorLayout.this.setWindowInsets(insets);
                  }
               };
            }

            ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
            this.setSystemUiVisibility(1280);
         } else {
            ViewCompat.setOnApplyWindowInsetsListener(this, (OnApplyWindowInsetsListener)null);
         }

      }
   }

   static {
      Package pkg = CoordinatorLayout.class.getPackage();
      WIDGET_PACKAGE_NAME = pkg != null ? pkg.getName() : null;
      if (VERSION.SDK_INT >= 21) {
         TOP_SORTED_CHILDREN_COMPARATOR = new CoordinatorLayout.ViewElevationComparator();
      } else {
         TOP_SORTED_CHILDREN_COMPARATOR = null;
      }

      CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
      sConstructors = new ThreadLocal();
      sRectPool = new SynchronizedPool(12);
   }

   protected static class SavedState extends AbsSavedState {
      SparseArray behaviorStates;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public CoordinatorLayout.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new CoordinatorLayout.SavedState(in, loader);
         }

         public CoordinatorLayout.SavedState createFromParcel(Parcel in) {
            return new CoordinatorLayout.SavedState(in, (ClassLoader)null);
         }

         public CoordinatorLayout.SavedState[] newArray(int size) {
            return new CoordinatorLayout.SavedState[size];
         }
      };

      public SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         int size = source.readInt();
         int[] ids = new int[size];
         source.readIntArray(ids);
         Parcelable[] states = source.readParcelableArray(loader);
         this.behaviorStates = new SparseArray(size);

         for(int i = 0; i < size; ++i) {
            this.behaviorStates.append(ids[i], states[i]);
         }

      }

      public SavedState(Parcelable superState) {
         super(superState);
      }

      public void writeToParcel(Parcel dest, int flags) {
         super.writeToParcel(dest, flags);
         int size = this.behaviorStates != null ? this.behaviorStates.size() : 0;
         dest.writeInt(size);
         int[] ids = new int[size];
         Parcelable[] states = new Parcelable[size];

         for(int i = 0; i < size; ++i) {
            ids[i] = this.behaviorStates.keyAt(i);
            states[i] = (Parcelable)this.behaviorStates.valueAt(i);
         }

         dest.writeIntArray(ids);
         dest.writeParcelableArray(states, flags);
      }
   }

   private class HierarchyChangeListener implements OnHierarchyChangeListener {
      public void onChildViewAdded(View parent, View child) {
         if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
            CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(parent, child);
         }

      }

      public void onChildViewRemoved(View parent, View child) {
         CoordinatorLayout.this.onChildViewsChanged(2);
         if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
            CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
         }

      }
   }

   public static class LayoutParams extends MarginLayoutParams {
      CoordinatorLayout.Behavior mBehavior;
      boolean mBehaviorResolved = false;
      public int gravity = 0;
      public int anchorGravity = 0;
      public int keyline = -1;
      int mAnchorId = -1;
      public int insetEdge = 0;
      public int dodgeInsetEdges = 0;
      int mInsetOffsetX;
      int mInsetOffsetY;
      View mAnchorView;
      View mAnchorDirectChild;
      private boolean mDidBlockInteraction;
      private boolean mDidAcceptNestedScrollTouch;
      private boolean mDidAcceptNestedScrollNonTouch;
      private boolean mDidChangeAfterNestedScroll;
      final Rect mLastChildRect = new Rect();
      Object mBehaviorTag;

      public LayoutParams(int width, int height) {
         super(width, height);
      }

      LayoutParams(Context context, AttributeSet attrs) {
         super(context, attrs);
         TypedArray a = context.obtainStyledAttributes(attrs, styleable.CoordinatorLayout_Layout);
         this.gravity = a.getInteger(styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
         this.mAnchorId = a.getResourceId(styleable.CoordinatorLayout_Layout_layout_anchor, -1);
         this.anchorGravity = a.getInteger(styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
         this.keyline = a.getInteger(styleable.CoordinatorLayout_Layout_layout_keyline, -1);
         this.insetEdge = a.getInt(styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
         this.dodgeInsetEdges = a.getInt(styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
         this.mBehaviorResolved = a.hasValue(styleable.CoordinatorLayout_Layout_layout_behavior);
         if (this.mBehaviorResolved) {
            this.mBehavior = CoordinatorLayout.parseBehavior(context, attrs, a.getString(styleable.CoordinatorLayout_Layout_layout_behavior));
         }

         a.recycle();
         if (this.mBehavior != null) {
            this.mBehavior.onAttachedToLayoutParams(this);
         }

      }

      public LayoutParams(CoordinatorLayout.LayoutParams p) {
         super(p);
      }

      public LayoutParams(MarginLayoutParams p) {
         super(p);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams p) {
         super(p);
      }

      @IdRes
      public int getAnchorId() {
         return this.mAnchorId;
      }

      public void setAnchorId(@IdRes int id) {
         this.invalidateAnchor();
         this.mAnchorId = id;
      }

      @Nullable
      public CoordinatorLayout.Behavior getBehavior() {
         return this.mBehavior;
      }

      public void setBehavior(@Nullable CoordinatorLayout.Behavior behavior) {
         if (this.mBehavior != behavior) {
            if (this.mBehavior != null) {
               this.mBehavior.onDetachedFromLayoutParams();
            }

            this.mBehavior = behavior;
            this.mBehaviorTag = null;
            this.mBehaviorResolved = true;
            if (behavior != null) {
               behavior.onAttachedToLayoutParams(this);
            }
         }

      }

      void setLastChildRect(Rect r) {
         this.mLastChildRect.set(r);
      }

      Rect getLastChildRect() {
         return this.mLastChildRect;
      }

      boolean checkAnchorChanged() {
         return this.mAnchorView == null && this.mAnchorId != -1;
      }

      boolean didBlockInteraction() {
         if (this.mBehavior == null) {
            this.mDidBlockInteraction = false;
         }

         return this.mDidBlockInteraction;
      }

      boolean isBlockingInteractionBelow(CoordinatorLayout parent, View child) {
         return this.mDidBlockInteraction ? true : (this.mDidBlockInteraction |= this.mBehavior != null ? this.mBehavior.blocksInteractionBelow(parent, child) : false);
      }

      void resetTouchBehaviorTracking() {
         this.mDidBlockInteraction = false;
      }

      void resetNestedScroll(int type) {
         this.setNestedScrollAccepted(type, false);
      }

      void setNestedScrollAccepted(int type, boolean accept) {
         switch(type) {
         case 0:
            this.mDidAcceptNestedScrollTouch = accept;
            break;
         case 1:
            this.mDidAcceptNestedScrollNonTouch = accept;
         }

      }

      boolean isNestedScrollAccepted(int type) {
         switch(type) {
         case 0:
            return this.mDidAcceptNestedScrollTouch;
         case 1:
            return this.mDidAcceptNestedScrollNonTouch;
         default:
            return false;
         }
      }

      boolean getChangedAfterNestedScroll() {
         return this.mDidChangeAfterNestedScroll;
      }

      void setChangedAfterNestedScroll(boolean changed) {
         this.mDidChangeAfterNestedScroll = changed;
      }

      void resetChangedAfterNestedScroll() {
         this.mDidChangeAfterNestedScroll = false;
      }

      boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
         return dependency == this.mAnchorDirectChild || this.shouldDodge(dependency, ViewCompat.getLayoutDirection(parent)) || this.mBehavior != null && this.mBehavior.layoutDependsOn(parent, child, dependency);
      }

      void invalidateAnchor() {
         this.mAnchorView = this.mAnchorDirectChild = null;
      }

      View findAnchorView(CoordinatorLayout parent, View forChild) {
         if (this.mAnchorId == -1) {
            this.mAnchorView = this.mAnchorDirectChild = null;
            return null;
         } else {
            if (this.mAnchorView == null || !this.verifyAnchorView(forChild, parent)) {
               this.resolveAnchorView(forChild, parent);
            }

            return this.mAnchorView;
         }
      }

      private void resolveAnchorView(View forChild, CoordinatorLayout parent) {
         this.mAnchorView = parent.findViewById(this.mAnchorId);
         if (this.mAnchorView == null) {
            if (parent.isInEditMode()) {
               this.mAnchorView = this.mAnchorDirectChild = null;
            } else {
               throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + parent.getResources().getResourceName(this.mAnchorId) + " to anchor view " + forChild);
            }
         } else if (this.mAnchorView == parent) {
            if (parent.isInEditMode()) {
               this.mAnchorView = this.mAnchorDirectChild = null;
            } else {
               throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
            }
         } else {
            View directChild = this.mAnchorView;

            for(ViewParent p = this.mAnchorView.getParent(); p != parent && p != null; p = p.getParent()) {
               if (p == forChild) {
                  if (parent.isInEditMode()) {
                     this.mAnchorView = this.mAnchorDirectChild = null;
                     return;
                  }

                  throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
               }

               if (p instanceof View) {
                  directChild = (View)p;
               }
            }

            this.mAnchorDirectChild = directChild;
         }
      }

      private boolean verifyAnchorView(View forChild, CoordinatorLayout parent) {
         if (this.mAnchorView.getId() != this.mAnchorId) {
            return false;
         } else {
            View directChild = this.mAnchorView;

            for(ViewParent p = this.mAnchorView.getParent(); p != parent; p = p.getParent()) {
               if (p == null || p == forChild) {
                  this.mAnchorView = this.mAnchorDirectChild = null;
                  return false;
               }

               if (p instanceof View) {
                  directChild = (View)p;
               }
            }

            this.mAnchorDirectChild = directChild;
            return true;
         }
      }

      private boolean shouldDodge(View other, int layoutDirection) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)other.getLayoutParams();
         int absInset = GravityCompat.getAbsoluteGravity(lp.insetEdge, layoutDirection);
         return absInset != 0 && (absInset & GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, layoutDirection)) == absInset;
      }
   }

   public abstract static class Behavior {
      public Behavior() {
      }

      public Behavior(Context context, AttributeSet attrs) {
      }

      public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
      }

      public void onDetachedFromLayoutParams() {
      }

      public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
         return false;
      }

      public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
         return false;
      }

      @ColorInt
      public int getScrimColor(CoordinatorLayout parent, View child) {
         return -16777216;
      }

      @FloatRange(
         from = 0.0D,
         to = 1.0D
      )
      public float getScrimOpacity(CoordinatorLayout parent, View child) {
         return 0.0F;
      }

      public boolean blocksInteractionBelow(CoordinatorLayout parent, View child) {
         return this.getScrimOpacity(parent, child) > 0.0F;
      }

      public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
         return false;
      }

      public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
         return false;
      }

      public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
      }

      public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
         return false;
      }

      public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
         return false;
      }

      public static void setTag(View child, Object tag) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         lp.mBehaviorTag = tag;
      }

      public static Object getTag(View child) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         return lp.mBehaviorTag;
      }

      /** @deprecated */
      @Deprecated
      public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
         return false;
      }

      public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
         return type == 0 ? this.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes) : false;
      }

      /** @deprecated */
      @Deprecated
      public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
      }

      public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
         if (type == 0) {
            this.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes);
         }

      }

      /** @deprecated */
      @Deprecated
      public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target) {
      }

      public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
         if (type == 0) {
            this.onStopNestedScroll(coordinatorLayout, child, target);
         }

      }

      /** @deprecated */
      @Deprecated
      public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
      }

      public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
         if (type == 0) {
            this.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
         }

      }

      /** @deprecated */
      @Deprecated
      public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
      }

      public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
         if (type == 0) {
            this.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
         }

      }

      public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
         return false;
      }

      public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
         return false;
      }

      @NonNull
      public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, View child, WindowInsetsCompat insets) {
         return insets;
      }

      public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View child, Rect rectangle, boolean immediate) {
         return false;
      }

      public void onRestoreInstanceState(CoordinatorLayout parent, View child, Parcelable state) {
      }

      public Parcelable onSaveInstanceState(CoordinatorLayout parent, View child) {
         return BaseSavedState.EMPTY_STATE;
      }

      public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull Rect rect) {
         return false;
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface DefaultBehavior {
      Class value();
   }

   static class ViewElevationComparator implements Comparator {
      public int compare(View lhs, View rhs) {
         float lz = ViewCompat.getZ(lhs);
         float rz = ViewCompat.getZ(rhs);
         if (lz > rz) {
            return -1;
         } else {
            return lz < rz ? 1 : 0;
         }
      }
   }

   class OnPreDrawListener implements android.view.ViewTreeObserver.OnPreDrawListener {
      public boolean onPreDraw() {
         CoordinatorLayout.this.onChildViewsChanged(0);
         return true;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface DispatchChangeEvent {
   }
}
