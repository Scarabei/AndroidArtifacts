package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.id;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CollapsingToolbarLayout extends FrameLayout {
   private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
   private boolean mRefreshToolbar;
   private int mToolbarId;
   private Toolbar mToolbar;
   private View mToolbarDirectChild;
   private View mDummyView;
   private int mExpandedMarginStart;
   private int mExpandedMarginTop;
   private int mExpandedMarginEnd;
   private int mExpandedMarginBottom;
   private final Rect mTmpRect;
   final CollapsingTextHelper mCollapsingTextHelper;
   private boolean mCollapsingTitleEnabled;
   private boolean mDrawCollapsingTitle;
   private Drawable mContentScrim;
   Drawable mStatusBarScrim;
   private int mScrimAlpha;
   private boolean mScrimsAreShown;
   private ValueAnimator mScrimAnimator;
   private long mScrimAnimationDuration;
   private int mScrimVisibleHeightTrigger;
   private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener;
   int mCurrentOffset;
   WindowInsetsCompat mLastInsets;

   public CollapsingToolbarLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public CollapsingToolbarLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public CollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mRefreshToolbar = true;
      this.mTmpRect = new Rect();
      this.mScrimVisibleHeightTrigger = -1;
      ThemeUtils.checkAppCompatTheme(context);
      this.mCollapsingTextHelper = new CollapsingTextHelper(this);
      this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.CollapsingToolbarLayout, defStyleAttr, style.Widget_Design_CollapsingToolbar);
      this.mCollapsingTextHelper.setExpandedTextGravity(a.getInt(styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
      this.mCollapsingTextHelper.setCollapsedTextGravity(a.getInt(styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
      this.mExpandedMarginStart = this.mExpandedMarginTop = this.mExpandedMarginEnd = this.mExpandedMarginBottom = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
      if (a.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
         this.mExpandedMarginStart = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
      }

      if (a.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
         this.mExpandedMarginEnd = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
      }

      if (a.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
         this.mExpandedMarginTop = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
      }

      if (a.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
         this.mExpandedMarginBottom = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
      }

      this.mCollapsingTitleEnabled = a.getBoolean(styleable.CollapsingToolbarLayout_titleEnabled, true);
      this.setTitle(a.getText(styleable.CollapsingToolbarLayout_title));
      this.mCollapsingTextHelper.setExpandedTextAppearance(style.TextAppearance_Design_CollapsingToolbar_Expanded);
      this.mCollapsingTextHelper.setCollapsedTextAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
      if (a.hasValue(styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
         this.mCollapsingTextHelper.setExpandedTextAppearance(a.getResourceId(styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
      }

      if (a.hasValue(styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
         this.mCollapsingTextHelper.setCollapsedTextAppearance(a.getResourceId(styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
      }

      this.mScrimVisibleHeightTrigger = a.getDimensionPixelSize(styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
      this.mScrimAnimationDuration = (long)a.getInt(styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
      this.setContentScrim(a.getDrawable(styleable.CollapsingToolbarLayout_contentScrim));
      this.setStatusBarScrim(a.getDrawable(styleable.CollapsingToolbarLayout_statusBarScrim));
      this.mToolbarId = a.getResourceId(styleable.CollapsingToolbarLayout_toolbarId, -1);
      a.recycle();
      this.setWillNotDraw(false);
      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
         public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
            return CollapsingToolbarLayout.this.onWindowInsetChanged(insets);
         }
      });
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ViewParent parent = this.getParent();
      if (parent instanceof AppBarLayout) {
         ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows((View)parent));
         if (this.mOnOffsetChangedListener == null) {
            this.mOnOffsetChangedListener = new CollapsingToolbarLayout.OffsetUpdateListener();
         }

         ((AppBarLayout)parent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
         ViewCompat.requestApplyInsets(this);
      }

   }

   protected void onDetachedFromWindow() {
      ViewParent parent = this.getParent();
      if (this.mOnOffsetChangedListener != null && parent instanceof AppBarLayout) {
         ((AppBarLayout)parent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
      }

      super.onDetachedFromWindow();
   }

   WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat insets) {
      WindowInsetsCompat newInsets = null;
      if (ViewCompat.getFitsSystemWindows(this)) {
         newInsets = insets;
      }

      if (!ObjectsCompat.equals(this.mLastInsets, newInsets)) {
         this.mLastInsets = newInsets;
         this.requestLayout();
      }

      return insets.consumeSystemWindowInsets();
   }

   public void draw(Canvas canvas) {
      super.draw(canvas);
      this.ensureToolbar();
      if (this.mToolbar == null && this.mContentScrim != null && this.mScrimAlpha > 0) {
         this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
         this.mContentScrim.draw(canvas);
      }

      if (this.mCollapsingTitleEnabled && this.mDrawCollapsingTitle) {
         this.mCollapsingTextHelper.draw(canvas);
      }

      if (this.mStatusBarScrim != null && this.mScrimAlpha > 0) {
         int topInset = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
         if (topInset > 0) {
            this.mStatusBarScrim.setBounds(0, -this.mCurrentOffset, this.getWidth(), topInset - this.mCurrentOffset);
            this.mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mStatusBarScrim.draw(canvas);
         }
      }

   }

   protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
      boolean invalidated = false;
      if (this.mContentScrim != null && this.mScrimAlpha > 0 && this.isToolbarChild(child)) {
         this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
         this.mContentScrim.draw(canvas);
         invalidated = true;
      }

      return super.drawChild(canvas, child, drawingTime) || invalidated;
   }

   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      if (this.mContentScrim != null) {
         this.mContentScrim.setBounds(0, 0, w, h);
      }

   }

   private void ensureToolbar() {
      if (this.mRefreshToolbar) {
         this.mToolbar = null;
         this.mToolbarDirectChild = null;
         if (this.mToolbarId != -1) {
            this.mToolbar = (Toolbar)this.findViewById(this.mToolbarId);
            if (this.mToolbar != null) {
               this.mToolbarDirectChild = this.findDirectChild(this.mToolbar);
            }
         }

         if (this.mToolbar == null) {
            Toolbar toolbar = null;
            int i = 0;

            for(int count = this.getChildCount(); i < count; ++i) {
               View child = this.getChildAt(i);
               if (child instanceof Toolbar) {
                  toolbar = (Toolbar)child;
                  break;
               }
            }

            this.mToolbar = toolbar;
         }

         this.updateDummyView();
         this.mRefreshToolbar = false;
      }
   }

   private boolean isToolbarChild(View child) {
      return this.mToolbarDirectChild != null && this.mToolbarDirectChild != this ? child == this.mToolbarDirectChild : child == this.mToolbar;
   }

   private View findDirectChild(View descendant) {
      View directChild = descendant;

      for(ViewParent p = descendant.getParent(); p != this && p != null; p = p.getParent()) {
         if (p instanceof View) {
            directChild = (View)p;
         }
      }

      return directChild;
   }

   private void updateDummyView() {
      if (!this.mCollapsingTitleEnabled && this.mDummyView != null) {
         ViewParent parent = this.mDummyView.getParent();
         if (parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView(this.mDummyView);
         }
      }

      if (this.mCollapsingTitleEnabled && this.mToolbar != null) {
         if (this.mDummyView == null) {
            this.mDummyView = new View(this.getContext());
         }

         if (this.mDummyView.getParent() == null) {
            this.mToolbar.addView(this.mDummyView, -1, -1);
         }
      }

   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.ensureToolbar();
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      int mode = MeasureSpec.getMode(heightMeasureSpec);
      int topInset = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
      if (mode == 0 && topInset > 0) {
         heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() + topInset, 1073741824);
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      int i;
      int z;
      if (this.mLastInsets != null) {
         i = this.mLastInsets.getSystemWindowInsetTop();
         z = 0;

         for(int z = this.getChildCount(); z < z; ++z) {
            View child = this.getChildAt(z);
            if (!ViewCompat.getFitsSystemWindows(child) && child.getTop() < i) {
               ViewCompat.offsetTopAndBottom(child, i);
            }
         }
      }

      if (this.mCollapsingTitleEnabled && this.mDummyView != null) {
         this.mDrawCollapsingTitle = ViewCompat.isAttachedToWindow(this.mDummyView) && this.mDummyView.getVisibility() == 0;
         if (this.mDrawCollapsingTitle) {
            boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
            z = this.getMaxOffsetForPinChild((View)(this.mToolbarDirectChild != null ? this.mToolbarDirectChild : this.mToolbar));
            ViewGroupUtils.getDescendantRect(this, this.mDummyView, this.mTmpRect);
            this.mCollapsingTextHelper.setCollapsedBounds(this.mTmpRect.left + (isRtl ? this.mToolbar.getTitleMarginEnd() : this.mToolbar.getTitleMarginStart()), this.mTmpRect.top + z + this.mToolbar.getTitleMarginTop(), this.mTmpRect.right + (isRtl ? this.mToolbar.getTitleMarginStart() : this.mToolbar.getTitleMarginEnd()), this.mTmpRect.bottom + z - this.mToolbar.getTitleMarginBottom());
            this.mCollapsingTextHelper.setExpandedBounds(isRtl ? this.mExpandedMarginEnd : this.mExpandedMarginStart, this.mTmpRect.top + this.mExpandedMarginTop, right - left - (isRtl ? this.mExpandedMarginStart : this.mExpandedMarginEnd), bottom - top - this.mExpandedMarginBottom);
            this.mCollapsingTextHelper.recalculate();
         }
      }

      i = 0;

      for(z = this.getChildCount(); i < z; ++i) {
         getViewOffsetHelper(this.getChildAt(i)).onViewLayout();
      }

      if (this.mToolbar != null) {
         if (this.mCollapsingTitleEnabled && TextUtils.isEmpty(this.mCollapsingTextHelper.getText())) {
            this.mCollapsingTextHelper.setText(this.mToolbar.getTitle());
         }

         if (this.mToolbarDirectChild != null && this.mToolbarDirectChild != this) {
            this.setMinimumHeight(getHeightWithMargins(this.mToolbarDirectChild));
         } else {
            this.setMinimumHeight(getHeightWithMargins(this.mToolbar));
         }
      }

      this.updateScrimVisibility();
   }

   private static int getHeightWithMargins(@NonNull View view) {
      android.view.ViewGroup.LayoutParams lp = view.getLayoutParams();
      if (lp instanceof MarginLayoutParams) {
         MarginLayoutParams mlp = (MarginLayoutParams)lp;
         return view.getHeight() + mlp.topMargin + mlp.bottomMargin;
      } else {
         return view.getHeight();
      }
   }

   static ViewOffsetHelper getViewOffsetHelper(View view) {
      ViewOffsetHelper offsetHelper = (ViewOffsetHelper)view.getTag(id.view_offset_helper);
      if (offsetHelper == null) {
         offsetHelper = new ViewOffsetHelper(view);
         view.setTag(id.view_offset_helper, offsetHelper);
      }

      return offsetHelper;
   }

   public void setTitle(@Nullable CharSequence title) {
      this.mCollapsingTextHelper.setText(title);
   }

   @Nullable
   public CharSequence getTitle() {
      return this.mCollapsingTitleEnabled ? this.mCollapsingTextHelper.getText() : null;
   }

   public void setTitleEnabled(boolean enabled) {
      if (enabled != this.mCollapsingTitleEnabled) {
         this.mCollapsingTitleEnabled = enabled;
         this.updateDummyView();
         this.requestLayout();
      }

   }

   public boolean isTitleEnabled() {
      return this.mCollapsingTitleEnabled;
   }

   public void setScrimsShown(boolean shown) {
      this.setScrimsShown(shown, ViewCompat.isLaidOut(this) && !this.isInEditMode());
   }

   public void setScrimsShown(boolean shown, boolean animate) {
      if (this.mScrimsAreShown != shown) {
         if (animate) {
            this.animateScrim(shown ? 255 : 0);
         } else {
            this.setScrimAlpha(shown ? 255 : 0);
         }

         this.mScrimsAreShown = shown;
      }

   }

   private void animateScrim(int targetAlpha) {
      this.ensureToolbar();
      if (this.mScrimAnimator == null) {
         this.mScrimAnimator = new ValueAnimator();
         this.mScrimAnimator.setDuration(this.mScrimAnimationDuration);
         this.mScrimAnimator.setInterpolator(targetAlpha > this.mScrimAlpha ? AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR : AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
         this.mScrimAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animator) {
               CollapsingToolbarLayout.this.setScrimAlpha(((Integer)animator.getAnimatedValue()).intValue());
            }
         });
      } else if (this.mScrimAnimator.isRunning()) {
         this.mScrimAnimator.cancel();
      }

      this.mScrimAnimator.setIntValues(new int[]{this.mScrimAlpha, targetAlpha});
      this.mScrimAnimator.start();
   }

   void setScrimAlpha(int alpha) {
      if (alpha != this.mScrimAlpha) {
         Drawable contentScrim = this.mContentScrim;
         if (contentScrim != null && this.mToolbar != null) {
            ViewCompat.postInvalidateOnAnimation(this.mToolbar);
         }

         this.mScrimAlpha = alpha;
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   int getScrimAlpha() {
      return this.mScrimAlpha;
   }

   public void setContentScrim(@Nullable Drawable drawable) {
      if (this.mContentScrim != drawable) {
         if (this.mContentScrim != null) {
            this.mContentScrim.setCallback((Callback)null);
         }

         this.mContentScrim = drawable != null ? drawable.mutate() : null;
         if (this.mContentScrim != null) {
            this.mContentScrim.setBounds(0, 0, this.getWidth(), this.getHeight());
            this.mContentScrim.setCallback(this);
            this.mContentScrim.setAlpha(this.mScrimAlpha);
         }

         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   public void setContentScrimColor(@ColorInt int color) {
      this.setContentScrim(new ColorDrawable(color));
   }

   public void setContentScrimResource(@DrawableRes int resId) {
      this.setContentScrim(ContextCompat.getDrawable(this.getContext(), resId));
   }

   @Nullable
   public Drawable getContentScrim() {
      return this.mContentScrim;
   }

   public void setStatusBarScrim(@Nullable Drawable drawable) {
      if (this.mStatusBarScrim != drawable) {
         if (this.mStatusBarScrim != null) {
            this.mStatusBarScrim.setCallback((Callback)null);
         }

         this.mStatusBarScrim = drawable != null ? drawable.mutate() : null;
         if (this.mStatusBarScrim != null) {
            if (this.mStatusBarScrim.isStateful()) {
               this.mStatusBarScrim.setState(this.getDrawableState());
            }

            DrawableCompat.setLayoutDirection(this.mStatusBarScrim, ViewCompat.getLayoutDirection(this));
            this.mStatusBarScrim.setVisible(this.getVisibility() == 0, false);
            this.mStatusBarScrim.setCallback(this);
            this.mStatusBarScrim.setAlpha(this.mScrimAlpha);
         }

         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      int[] state = this.getDrawableState();
      boolean changed = false;
      Drawable d = this.mStatusBarScrim;
      if (d != null && d.isStateful()) {
         changed |= d.setState(state);
      }

      d = this.mContentScrim;
      if (d != null && d.isStateful()) {
         changed |= d.setState(state);
      }

      if (this.mCollapsingTextHelper != null) {
         changed |= this.mCollapsingTextHelper.setState(state);
      }

      if (changed) {
         this.invalidate();
      }

   }

   protected boolean verifyDrawable(Drawable who) {
      return super.verifyDrawable(who) || who == this.mContentScrim || who == this.mStatusBarScrim;
   }

   public void setVisibility(int visibility) {
      super.setVisibility(visibility);
      boolean visible = visibility == 0;
      if (this.mStatusBarScrim != null && this.mStatusBarScrim.isVisible() != visible) {
         this.mStatusBarScrim.setVisible(visible, false);
      }

      if (this.mContentScrim != null && this.mContentScrim.isVisible() != visible) {
         this.mContentScrim.setVisible(visible, false);
      }

   }

   public void setStatusBarScrimColor(@ColorInt int color) {
      this.setStatusBarScrim(new ColorDrawable(color));
   }

   public void setStatusBarScrimResource(@DrawableRes int resId) {
      this.setStatusBarScrim(ContextCompat.getDrawable(this.getContext(), resId));
   }

   @Nullable
   public Drawable getStatusBarScrim() {
      return this.mStatusBarScrim;
   }

   public void setCollapsedTitleTextAppearance(@StyleRes int resId) {
      this.mCollapsingTextHelper.setCollapsedTextAppearance(resId);
   }

   public void setCollapsedTitleTextColor(@ColorInt int color) {
      this.setCollapsedTitleTextColor(ColorStateList.valueOf(color));
   }

   public void setCollapsedTitleTextColor(@NonNull ColorStateList colors) {
      this.mCollapsingTextHelper.setCollapsedTextColor(colors);
   }

   public void setCollapsedTitleGravity(int gravity) {
      this.mCollapsingTextHelper.setCollapsedTextGravity(gravity);
   }

   public int getCollapsedTitleGravity() {
      return this.mCollapsingTextHelper.getCollapsedTextGravity();
   }

   public void setExpandedTitleTextAppearance(@StyleRes int resId) {
      this.mCollapsingTextHelper.setExpandedTextAppearance(resId);
   }

   public void setExpandedTitleColor(@ColorInt int color) {
      this.setExpandedTitleTextColor(ColorStateList.valueOf(color));
   }

   public void setExpandedTitleTextColor(@NonNull ColorStateList colors) {
      this.mCollapsingTextHelper.setExpandedTextColor(colors);
   }

   public void setExpandedTitleGravity(int gravity) {
      this.mCollapsingTextHelper.setExpandedTextGravity(gravity);
   }

   public int getExpandedTitleGravity() {
      return this.mCollapsingTextHelper.getExpandedTextGravity();
   }

   public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
      this.mCollapsingTextHelper.setCollapsedTypeface(typeface);
   }

   @NonNull
   public Typeface getCollapsedTitleTypeface() {
      return this.mCollapsingTextHelper.getCollapsedTypeface();
   }

   public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
      this.mCollapsingTextHelper.setExpandedTypeface(typeface);
   }

   @NonNull
   public Typeface getExpandedTitleTypeface() {
      return this.mCollapsingTextHelper.getExpandedTypeface();
   }

   public void setExpandedTitleMargin(int start, int top, int end, int bottom) {
      this.mExpandedMarginStart = start;
      this.mExpandedMarginTop = top;
      this.mExpandedMarginEnd = end;
      this.mExpandedMarginBottom = bottom;
      this.requestLayout();
   }

   public int getExpandedTitleMarginStart() {
      return this.mExpandedMarginStart;
   }

   public void setExpandedTitleMarginStart(int margin) {
      this.mExpandedMarginStart = margin;
      this.requestLayout();
   }

   public int getExpandedTitleMarginTop() {
      return this.mExpandedMarginTop;
   }

   public void setExpandedTitleMarginTop(int margin) {
      this.mExpandedMarginTop = margin;
      this.requestLayout();
   }

   public int getExpandedTitleMarginEnd() {
      return this.mExpandedMarginEnd;
   }

   public void setExpandedTitleMarginEnd(int margin) {
      this.mExpandedMarginEnd = margin;
      this.requestLayout();
   }

   public int getExpandedTitleMarginBottom() {
      return this.mExpandedMarginBottom;
   }

   public void setExpandedTitleMarginBottom(int margin) {
      this.mExpandedMarginBottom = margin;
      this.requestLayout();
   }

   public void setScrimVisibleHeightTrigger(@IntRange(from = 0L) int height) {
      if (this.mScrimVisibleHeightTrigger != height) {
         this.mScrimVisibleHeightTrigger = height;
         this.updateScrimVisibility();
      }

   }

   public int getScrimVisibleHeightTrigger() {
      if (this.mScrimVisibleHeightTrigger >= 0) {
         return this.mScrimVisibleHeightTrigger;
      } else {
         int insetTop = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
         int minHeight = ViewCompat.getMinimumHeight(this);
         return minHeight > 0 ? Math.min(minHeight * 2 + insetTop, this.getHeight()) : this.getHeight() / 3;
      }
   }

   public void setScrimAnimationDuration(@IntRange(from = 0L) long duration) {
      this.mScrimAnimationDuration = duration;
   }

   public long getScrimAnimationDuration() {
      return this.mScrimAnimationDuration;
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p instanceof CollapsingToolbarLayout.LayoutParams;
   }

   protected CollapsingToolbarLayout.LayoutParams generateDefaultLayoutParams() {
      return new CollapsingToolbarLayout.LayoutParams(-1, -1);
   }

   public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new CollapsingToolbarLayout.LayoutParams(this.getContext(), attrs);
   }

   protected android.widget.FrameLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return new CollapsingToolbarLayout.LayoutParams(p);
   }

   final void updateScrimVisibility() {
      if (this.mContentScrim != null || this.mStatusBarScrim != null) {
         this.setScrimsShown(this.getHeight() + this.mCurrentOffset < this.getScrimVisibleHeightTrigger());
      }

   }

   final int getMaxOffsetForPinChild(View child) {
      ViewOffsetHelper offsetHelper = getViewOffsetHelper(child);
      CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams)child.getLayoutParams();
      return this.getHeight() - offsetHelper.getLayoutTop() - child.getHeight() - lp.bottomMargin;
   }

   private class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
      public void onOffsetChanged(AppBarLayout layout, int verticalOffset) {
         CollapsingToolbarLayout.this.mCurrentOffset = verticalOffset;
         int insetTop = CollapsingToolbarLayout.this.mLastInsets != null ? CollapsingToolbarLayout.this.mLastInsets.getSystemWindowInsetTop() : 0;
         int expandRange = 0;

         for(int z = CollapsingToolbarLayout.this.getChildCount(); expandRange < z; ++expandRange) {
            View child = CollapsingToolbarLayout.this.getChildAt(expandRange);
            CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams)child.getLayoutParams();
            ViewOffsetHelper offsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(child);
            switch(lp.mCollapseMode) {
            case 1:
               offsetHelper.setTopAndBottomOffset(MathUtils.clamp(-verticalOffset, 0, CollapsingToolbarLayout.this.getMaxOffsetForPinChild(child)));
               break;
            case 2:
               offsetHelper.setTopAndBottomOffset(Math.round((float)(-verticalOffset) * lp.mParallaxMult));
            }
         }

         CollapsingToolbarLayout.this.updateScrimVisibility();
         if (CollapsingToolbarLayout.this.mStatusBarScrim != null && insetTop > 0) {
            ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
         }

         expandRange = CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this) - insetTop;
         CollapsingToolbarLayout.this.mCollapsingTextHelper.setExpansionFraction((float)Math.abs(verticalOffset) / (float)expandRange);
      }
   }

   public static class LayoutParams extends android.widget.FrameLayout.LayoutParams {
      private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5F;
      public static final int COLLAPSE_MODE_OFF = 0;
      public static final int COLLAPSE_MODE_PIN = 1;
      public static final int COLLAPSE_MODE_PARALLAX = 2;
      int mCollapseMode = 0;
      float mParallaxMult = 0.5F;

      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
         TypedArray a = c.obtainStyledAttributes(attrs, styleable.CollapsingToolbarLayout_Layout);
         this.mCollapseMode = a.getInt(styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
         this.setParallaxMultiplier(a.getFloat(styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5F));
         a.recycle();
      }

      public LayoutParams(int width, int height) {
         super(width, height);
      }

      public LayoutParams(int width, int height, int gravity) {
         super(width, height, gravity);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams p) {
         super(p);
      }

      public LayoutParams(MarginLayoutParams source) {
         super(source);
      }

      @RequiresApi(19)
      public LayoutParams(android.widget.FrameLayout.LayoutParams source) {
         super(source);
      }

      public void setCollapseMode(int collapseMode) {
         this.mCollapseMode = collapseMode;
      }

      public int getCollapseMode() {
         return this.mCollapseMode;
      }

      public void setParallaxMultiplier(float multiplier) {
         this.mParallaxMult = multiplier;
      }

      public float getParallaxMultiplier() {
         return this.mParallaxMult;
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      @interface CollapseMode {
      }
   }
}
