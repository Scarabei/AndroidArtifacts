package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.layout;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.DecorView;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.TooltipCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@DecorView
public class TabLayout extends HorizontalScrollView {
   private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
   static final int DEFAULT_GAP_TEXT_ICON = 8;
   private static final int INVALID_WIDTH = -1;
   private static final int DEFAULT_HEIGHT = 48;
   private static final int TAB_MIN_WIDTH_MARGIN = 56;
   static final int FIXED_WRAP_GUTTER_MIN = 16;
   static final int MOTION_NON_ADJACENT_OFFSET = 24;
   private static final int ANIMATION_DURATION = 300;
   private static final Pool sTabPool = new SynchronizedPool(16);
   public static final int MODE_SCROLLABLE = 0;
   public static final int MODE_FIXED = 1;
   public static final int GRAVITY_FILL = 0;
   public static final int GRAVITY_CENTER = 1;
   private final ArrayList mTabs;
   private TabLayout.Tab mSelectedTab;
   private final TabLayout.SlidingTabStrip mTabStrip;
   int mTabPaddingStart;
   int mTabPaddingTop;
   int mTabPaddingEnd;
   int mTabPaddingBottom;
   int mTabTextAppearance;
   ColorStateList mTabTextColors;
   float mTabTextSize;
   float mTabTextMultiLineSize;
   final int mTabBackgroundResId;
   int mTabMaxWidth;
   private final int mRequestedTabMinWidth;
   private final int mRequestedTabMaxWidth;
   private final int mScrollableTabMinWidth;
   private int mContentInsetStart;
   int mTabGravity;
   int mMode;
   private TabLayout.OnTabSelectedListener mSelectedListener;
   private final ArrayList mSelectedListeners;
   private TabLayout.OnTabSelectedListener mCurrentVpSelectedListener;
   private ValueAnimator mScrollAnimator;
   ViewPager mViewPager;
   private PagerAdapter mPagerAdapter;
   private DataSetObserver mPagerAdapterObserver;
   private TabLayout.TabLayoutOnPageChangeListener mPageChangeListener;
   private TabLayout.AdapterChangeListener mAdapterChangeListener;
   private boolean mSetupViewPagerImplicitly;
   private final Pool mTabViewPool;

   public TabLayout(Context context) {
      this(context, (AttributeSet)null);
   }

   public TabLayout(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mTabs = new ArrayList();
      this.mTabMaxWidth = Integer.MAX_VALUE;
      this.mSelectedListeners = new ArrayList();
      this.mTabViewPool = new SimplePool(12);
      ThemeUtils.checkAppCompatTheme(context);
      this.setHorizontalScrollBarEnabled(false);
      this.mTabStrip = new TabLayout.SlidingTabStrip(context);
      super.addView(this.mTabStrip, 0, new LayoutParams(-2, -1));
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.TabLayout, defStyleAttr, style.Widget_Design_TabLayout);
      this.mTabStrip.setSelectedIndicatorHeight(a.getDimensionPixelSize(styleable.TabLayout_tabIndicatorHeight, 0));
      this.mTabStrip.setSelectedIndicatorColor(a.getColor(styleable.TabLayout_tabIndicatorColor, 0));
      this.mTabPaddingStart = this.mTabPaddingTop = this.mTabPaddingEnd = this.mTabPaddingBottom = a.getDimensionPixelSize(styleable.TabLayout_tabPadding, 0);
      this.mTabPaddingStart = a.getDimensionPixelSize(styleable.TabLayout_tabPaddingStart, this.mTabPaddingStart);
      this.mTabPaddingTop = a.getDimensionPixelSize(styleable.TabLayout_tabPaddingTop, this.mTabPaddingTop);
      this.mTabPaddingEnd = a.getDimensionPixelSize(styleable.TabLayout_tabPaddingEnd, this.mTabPaddingEnd);
      this.mTabPaddingBottom = a.getDimensionPixelSize(styleable.TabLayout_tabPaddingBottom, this.mTabPaddingBottom);
      this.mTabTextAppearance = a.getResourceId(styleable.TabLayout_tabTextAppearance, style.TextAppearance_Design_Tab);
      TypedArray ta = context.obtainStyledAttributes(this.mTabTextAppearance, android.support.v7.appcompat.R.styleable.TextAppearance);

      try {
         this.mTabTextSize = (float)ta.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
         this.mTabTextColors = ta.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
      } finally {
         ta.recycle();
      }

      if (a.hasValue(styleable.TabLayout_tabTextColor)) {
         this.mTabTextColors = a.getColorStateList(styleable.TabLayout_tabTextColor);
      }

      if (a.hasValue(styleable.TabLayout_tabSelectedTextColor)) {
         int selected = a.getColor(styleable.TabLayout_tabSelectedTextColor, 0);
         this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), selected);
      }

      this.mRequestedTabMinWidth = a.getDimensionPixelSize(styleable.TabLayout_tabMinWidth, -1);
      this.mRequestedTabMaxWidth = a.getDimensionPixelSize(styleable.TabLayout_tabMaxWidth, -1);
      this.mTabBackgroundResId = a.getResourceId(styleable.TabLayout_tabBackground, 0);
      this.mContentInsetStart = a.getDimensionPixelSize(styleable.TabLayout_tabContentStart, 0);
      this.mMode = a.getInt(styleable.TabLayout_tabMode, 1);
      this.mTabGravity = a.getInt(styleable.TabLayout_tabGravity, 0);
      a.recycle();
      Resources res = this.getResources();
      this.mTabTextMultiLineSize = (float)res.getDimensionPixelSize(dimen.design_tab_text_size_2line);
      this.mScrollableTabMinWidth = res.getDimensionPixelSize(dimen.design_tab_scrollable_min_width);
      this.applyModeAndGravity();
   }

   public void setSelectedTabIndicatorColor(@ColorInt int color) {
      this.mTabStrip.setSelectedIndicatorColor(color);
   }

   public void setSelectedTabIndicatorHeight(int height) {
      this.mTabStrip.setSelectedIndicatorHeight(height);
   }

   public void setScrollPosition(int position, float positionOffset, boolean updateSelectedText) {
      this.setScrollPosition(position, positionOffset, updateSelectedText, true);
   }

   void setScrollPosition(int position, float positionOffset, boolean updateSelectedText, boolean updateIndicatorPosition) {
      int roundedPosition = Math.round((float)position + positionOffset);
      if (roundedPosition >= 0 && roundedPosition < this.mTabStrip.getChildCount()) {
         if (updateIndicatorPosition) {
            this.mTabStrip.setIndicatorPositionFromTabPosition(position, positionOffset);
         }

         if (this.mScrollAnimator != null && this.mScrollAnimator.isRunning()) {
            this.mScrollAnimator.cancel();
         }

         this.scrollTo(this.calculateScrollXForTab(position, positionOffset), 0);
         if (updateSelectedText) {
            this.setSelectedTabView(roundedPosition);
         }

      }
   }

   private float getScrollPosition() {
      return this.mTabStrip.getIndicatorPosition();
   }

   public void addTab(@NonNull TabLayout.Tab tab) {
      this.addTab(tab, this.mTabs.isEmpty());
   }

   public void addTab(@NonNull TabLayout.Tab tab, int position) {
      this.addTab(tab, position, this.mTabs.isEmpty());
   }

   public void addTab(@NonNull TabLayout.Tab tab, boolean setSelected) {
      this.addTab(tab, this.mTabs.size(), setSelected);
   }

   public void addTab(@NonNull TabLayout.Tab tab, int position, boolean setSelected) {
      if (tab.mParent != this) {
         throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
      } else {
         this.configureTab(tab, position);
         this.addTabView(tab);
         if (setSelected) {
            tab.select();
         }

      }
   }

   private void addTabFromItemView(@NonNull TabItem item) {
      TabLayout.Tab tab = this.newTab();
      if (item.mText != null) {
         tab.setText(item.mText);
      }

      if (item.mIcon != null) {
         tab.setIcon(item.mIcon);
      }

      if (item.mCustomLayout != 0) {
         tab.setCustomView(item.mCustomLayout);
      }

      if (!TextUtils.isEmpty(item.getContentDescription())) {
         tab.setContentDescription(item.getContentDescription());
      }

      this.addTab(tab);
   }

   /** @deprecated */
   @Deprecated
   public void setOnTabSelectedListener(@Nullable TabLayout.OnTabSelectedListener listener) {
      if (this.mSelectedListener != null) {
         this.removeOnTabSelectedListener(this.mSelectedListener);
      }

      this.mSelectedListener = listener;
      if (listener != null) {
         this.addOnTabSelectedListener(listener);
      }

   }

   public void addOnTabSelectedListener(@NonNull TabLayout.OnTabSelectedListener listener) {
      if (!this.mSelectedListeners.contains(listener)) {
         this.mSelectedListeners.add(listener);
      }

   }

   public void removeOnTabSelectedListener(@NonNull TabLayout.OnTabSelectedListener listener) {
      this.mSelectedListeners.remove(listener);
   }

   public void clearOnTabSelectedListeners() {
      this.mSelectedListeners.clear();
   }

   @NonNull
   public TabLayout.Tab newTab() {
      TabLayout.Tab tab = (TabLayout.Tab)sTabPool.acquire();
      if (tab == null) {
         tab = new TabLayout.Tab();
      }

      tab.mParent = this;
      tab.mView = this.createTabView(tab);
      return tab;
   }

   public int getTabCount() {
      return this.mTabs.size();
   }

   @Nullable
   public TabLayout.Tab getTabAt(int index) {
      return index >= 0 && index < this.getTabCount() ? (TabLayout.Tab)this.mTabs.get(index) : null;
   }

   public int getSelectedTabPosition() {
      return this.mSelectedTab != null ? this.mSelectedTab.getPosition() : -1;
   }

   public void removeTab(TabLayout.Tab tab) {
      if (tab.mParent != this) {
         throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
      } else {
         this.removeTabAt(tab.getPosition());
      }
   }

   public void removeTabAt(int position) {
      int selectedTabPosition = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : 0;
      this.removeTabViewAt(position);
      TabLayout.Tab removedTab = (TabLayout.Tab)this.mTabs.remove(position);
      if (removedTab != null) {
         removedTab.reset();
         sTabPool.release(removedTab);
      }

      int newTabCount = this.mTabs.size();

      for(int i = position; i < newTabCount; ++i) {
         ((TabLayout.Tab)this.mTabs.get(i)).setPosition(i);
      }

      if (selectedTabPosition == position) {
         this.selectTab(this.mTabs.isEmpty() ? null : (TabLayout.Tab)this.mTabs.get(Math.max(0, position - 1)));
      }

   }

   public void removeAllTabs() {
      for(int i = this.mTabStrip.getChildCount() - 1; i >= 0; --i) {
         this.removeTabViewAt(i);
      }

      Iterator i = this.mTabs.iterator();

      while(i.hasNext()) {
         TabLayout.Tab tab = (TabLayout.Tab)i.next();
         i.remove();
         tab.reset();
         sTabPool.release(tab);
      }

      this.mSelectedTab = null;
   }

   public void setTabMode(int mode) {
      if (mode != this.mMode) {
         this.mMode = mode;
         this.applyModeAndGravity();
      }

   }

   public int getTabMode() {
      return this.mMode;
   }

   public void setTabGravity(int gravity) {
      if (this.mTabGravity != gravity) {
         this.mTabGravity = gravity;
         this.applyModeAndGravity();
      }

   }

   public int getTabGravity() {
      return this.mTabGravity;
   }

   public void setTabTextColors(@Nullable ColorStateList textColor) {
      if (this.mTabTextColors != textColor) {
         this.mTabTextColors = textColor;
         this.updateAllTabs();
      }

   }

   @Nullable
   public ColorStateList getTabTextColors() {
      return this.mTabTextColors;
   }

   public void setTabTextColors(int normalColor, int selectedColor) {
      this.setTabTextColors(createColorStateList(normalColor, selectedColor));
   }

   public void setupWithViewPager(@Nullable ViewPager viewPager) {
      this.setupWithViewPager(viewPager, true);
   }

   public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
      this.setupWithViewPager(viewPager, autoRefresh, false);
   }

   private void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh, boolean implicitSetup) {
      if (this.mViewPager != null) {
         if (this.mPageChangeListener != null) {
            this.mViewPager.removeOnPageChangeListener(this.mPageChangeListener);
         }

         if (this.mAdapterChangeListener != null) {
            this.mViewPager.removeOnAdapterChangeListener(this.mAdapterChangeListener);
         }
      }

      if (this.mCurrentVpSelectedListener != null) {
         this.removeOnTabSelectedListener(this.mCurrentVpSelectedListener);
         this.mCurrentVpSelectedListener = null;
      }

      if (viewPager != null) {
         this.mViewPager = viewPager;
         if (this.mPageChangeListener == null) {
            this.mPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(this);
         }

         this.mPageChangeListener.reset();
         viewPager.addOnPageChangeListener(this.mPageChangeListener);
         this.mCurrentVpSelectedListener = new TabLayout.ViewPagerOnTabSelectedListener(viewPager);
         this.addOnTabSelectedListener(this.mCurrentVpSelectedListener);
         PagerAdapter adapter = viewPager.getAdapter();
         if (adapter != null) {
            this.setPagerAdapter(adapter, autoRefresh);
         }

         if (this.mAdapterChangeListener == null) {
            this.mAdapterChangeListener = new TabLayout.AdapterChangeListener();
         }

         this.mAdapterChangeListener.setAutoRefresh(autoRefresh);
         viewPager.addOnAdapterChangeListener(this.mAdapterChangeListener);
         this.setScrollPosition(viewPager.getCurrentItem(), 0.0F, true);
      } else {
         this.mViewPager = null;
         this.setPagerAdapter((PagerAdapter)null, false);
      }

      this.mSetupViewPagerImplicitly = implicitSetup;
   }

   /** @deprecated */
   @Deprecated
   public void setTabsFromPagerAdapter(@Nullable PagerAdapter adapter) {
      this.setPagerAdapter(adapter, false);
   }

   public boolean shouldDelayChildPressedState() {
      return this.getTabScrollRange() > 0;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.mViewPager == null) {
         ViewParent vp = this.getParent();
         if (vp instanceof ViewPager) {
            this.setupWithViewPager((ViewPager)vp, true, true);
         }
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.mSetupViewPagerImplicitly) {
         this.setupWithViewPager((ViewPager)null);
         this.mSetupViewPagerImplicitly = false;
      }

   }

   private int getTabScrollRange() {
      return Math.max(0, this.mTabStrip.getWidth() - this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
   }

   void setPagerAdapter(@Nullable PagerAdapter adapter, boolean addObserver) {
      if (this.mPagerAdapter != null && this.mPagerAdapterObserver != null) {
         this.mPagerAdapter.unregisterDataSetObserver(this.mPagerAdapterObserver);
      }

      this.mPagerAdapter = adapter;
      if (addObserver && adapter != null) {
         if (this.mPagerAdapterObserver == null) {
            this.mPagerAdapterObserver = new TabLayout.PagerAdapterObserver();
         }

         adapter.registerDataSetObserver(this.mPagerAdapterObserver);
      }

      this.populateFromPagerAdapter();
   }

   void populateFromPagerAdapter() {
      this.removeAllTabs();
      if (this.mPagerAdapter != null) {
         int adapterCount = this.mPagerAdapter.getCount();

         int curItem;
         for(curItem = 0; curItem < adapterCount; ++curItem) {
            this.addTab(this.newTab().setText(this.mPagerAdapter.getPageTitle(curItem)), false);
         }

         if (this.mViewPager != null && adapterCount > 0) {
            curItem = this.mViewPager.getCurrentItem();
            if (curItem != this.getSelectedTabPosition() && curItem < this.getTabCount()) {
               this.selectTab(this.getTabAt(curItem));
            }
         }
      }

   }

   private void updateAllTabs() {
      int i = 0;

      for(int z = this.mTabs.size(); i < z; ++i) {
         ((TabLayout.Tab)this.mTabs.get(i)).updateView();
      }

   }

   private TabLayout.TabView createTabView(@NonNull TabLayout.Tab tab) {
      TabLayout.TabView tabView = this.mTabViewPool != null ? (TabLayout.TabView)this.mTabViewPool.acquire() : null;
      if (tabView == null) {
         tabView = new TabLayout.TabView(this.getContext());
      }

      tabView.setTab(tab);
      tabView.setFocusable(true);
      tabView.setMinimumWidth(this.getTabMinWidth());
      return tabView;
   }

   private void configureTab(TabLayout.Tab tab, int position) {
      tab.setPosition(position);
      this.mTabs.add(position, tab);
      int count = this.mTabs.size();

      for(int i = position + 1; i < count; ++i) {
         ((TabLayout.Tab)this.mTabs.get(i)).setPosition(i);
      }

   }

   private void addTabView(TabLayout.Tab tab) {
      TabLayout.TabView tabView = tab.mView;
      this.mTabStrip.addView(tabView, tab.getPosition(), this.createLayoutParamsForTabs());
   }

   public void addView(View child) {
      this.addViewInternal(child);
   }

   public void addView(View child, int index) {
      this.addViewInternal(child);
   }

   public void addView(View child, android.view.ViewGroup.LayoutParams params) {
      this.addViewInternal(child);
   }

   public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
      this.addViewInternal(child);
   }

   private void addViewInternal(View child) {
      if (child instanceof TabItem) {
         this.addTabFromItemView((TabItem)child);
      } else {
         throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
      }
   }

   private android.widget.LinearLayout.LayoutParams createLayoutParamsForTabs() {
      android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(-2, -1);
      this.updateTabViewLayoutParams(lp);
      return lp;
   }

   private void updateTabViewLayoutParams(android.widget.LinearLayout.LayoutParams lp) {
      if (this.mMode == 1 && this.mTabGravity == 0) {
         lp.width = 0;
         lp.weight = 1.0F;
      } else {
         lp.width = -2;
         lp.weight = 0.0F;
      }

   }

   int dpToPx(int dps) {
      return Math.round(this.getResources().getDisplayMetrics().density * (float)dps);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int idealHeight = this.dpToPx(this.getDefaultHeight()) + this.getPaddingTop() + this.getPaddingBottom();
      switch(MeasureSpec.getMode(heightMeasureSpec)) {
      case Integer.MIN_VALUE:
         heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.min(idealHeight, MeasureSpec.getSize(heightMeasureSpec)), 1073741824);
         break;
      case 0:
         heightMeasureSpec = MeasureSpec.makeMeasureSpec(idealHeight, 1073741824);
      }

      int specWidth = MeasureSpec.getSize(widthMeasureSpec);
      if (MeasureSpec.getMode(widthMeasureSpec) != 0) {
         this.mTabMaxWidth = this.mRequestedTabMaxWidth > 0 ? this.mRequestedTabMaxWidth : specWidth - this.dpToPx(56);
      }

      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      if (this.getChildCount() == 1) {
         View child = this.getChildAt(0);
         boolean remeasure = false;
         switch(this.mMode) {
         case 0:
            remeasure = child.getMeasuredWidth() < this.getMeasuredWidth();
            break;
         case 1:
            remeasure = child.getMeasuredWidth() != this.getMeasuredWidth();
         }

         if (remeasure) {
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, this.getPaddingTop() + this.getPaddingBottom(), child.getLayoutParams().height);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
         }
      }

   }

   private void removeTabViewAt(int position) {
      TabLayout.TabView view = (TabLayout.TabView)this.mTabStrip.getChildAt(position);
      this.mTabStrip.removeViewAt(position);
      if (view != null) {
         view.reset();
         this.mTabViewPool.release(view);
      }

      this.requestLayout();
   }

   private void animateToTab(int newPosition) {
      if (newPosition != -1) {
         if (this.getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.mTabStrip.childrenNeedLayout()) {
            int startScrollX = this.getScrollX();
            int targetScrollX = this.calculateScrollXForTab(newPosition, 0.0F);
            if (startScrollX != targetScrollX) {
               this.ensureScrollAnimator();
               this.mScrollAnimator.setIntValues(new int[]{startScrollX, targetScrollX});
               this.mScrollAnimator.start();
            }

            this.mTabStrip.animateIndicatorToPosition(newPosition, 300);
         } else {
            this.setScrollPosition(newPosition, 0.0F, true);
         }
      }
   }

   private void ensureScrollAnimator() {
      if (this.mScrollAnimator == null) {
         this.mScrollAnimator = new ValueAnimator();
         this.mScrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         this.mScrollAnimator.setDuration(300L);
         this.mScrollAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animator) {
               TabLayout.this.scrollTo(((Integer)animator.getAnimatedValue()).intValue(), 0);
            }
         });
      }

   }

   void setScrollAnimatorListener(AnimatorListener listener) {
      this.ensureScrollAnimator();
      this.mScrollAnimator.addListener(listener);
   }

   private void setSelectedTabView(int position) {
      int tabCount = this.mTabStrip.getChildCount();
      if (position < tabCount) {
         for(int i = 0; i < tabCount; ++i) {
            View child = this.mTabStrip.getChildAt(i);
            child.setSelected(i == position);
         }
      }

   }

   void selectTab(TabLayout.Tab tab) {
      this.selectTab(tab, true);
   }

   void selectTab(TabLayout.Tab tab, boolean updateIndicator) {
      TabLayout.Tab currentTab = this.mSelectedTab;
      if (currentTab == tab) {
         if (currentTab != null) {
            this.dispatchTabReselected(tab);
            this.animateToTab(tab.getPosition());
         }
      } else {
         int newPosition = tab != null ? tab.getPosition() : -1;
         if (updateIndicator) {
            if ((currentTab == null || currentTab.getPosition() == -1) && newPosition != -1) {
               this.setScrollPosition(newPosition, 0.0F, true);
            } else {
               this.animateToTab(newPosition);
            }

            if (newPosition != -1) {
               this.setSelectedTabView(newPosition);
            }
         }

         if (currentTab != null) {
            this.dispatchTabUnselected(currentTab);
         }

         this.mSelectedTab = tab;
         if (tab != null) {
            this.dispatchTabSelected(tab);
         }
      }

   }

   private void dispatchTabSelected(@NonNull TabLayout.Tab tab) {
      for(int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
         ((TabLayout.OnTabSelectedListener)this.mSelectedListeners.get(i)).onTabSelected(tab);
      }

   }

   private void dispatchTabUnselected(@NonNull TabLayout.Tab tab) {
      for(int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
         ((TabLayout.OnTabSelectedListener)this.mSelectedListeners.get(i)).onTabUnselected(tab);
      }

   }

   private void dispatchTabReselected(@NonNull TabLayout.Tab tab) {
      for(int i = this.mSelectedListeners.size() - 1; i >= 0; --i) {
         ((TabLayout.OnTabSelectedListener)this.mSelectedListeners.get(i)).onTabReselected(tab);
      }

   }

   private int calculateScrollXForTab(int position, float positionOffset) {
      if (this.mMode == 0) {
         View selectedChild = this.mTabStrip.getChildAt(position);
         View nextChild = position + 1 < this.mTabStrip.getChildCount() ? this.mTabStrip.getChildAt(position + 1) : null;
         int selectedWidth = selectedChild != null ? selectedChild.getWidth() : 0;
         int nextWidth = nextChild != null ? nextChild.getWidth() : 0;
         int scrollBase = selectedChild.getLeft() + selectedWidth / 2 - this.getWidth() / 2;
         int scrollOffset = (int)((float)(selectedWidth + nextWidth) * 0.5F * positionOffset);
         return ViewCompat.getLayoutDirection(this) == 0 ? scrollBase + scrollOffset : scrollBase - scrollOffset;
      } else {
         return 0;
      }
   }

   private void applyModeAndGravity() {
      int paddingStart = 0;
      if (this.mMode == 0) {
         paddingStart = Math.max(0, this.mContentInsetStart - this.mTabPaddingStart);
      }

      ViewCompat.setPaddingRelative(this.mTabStrip, paddingStart, 0, 0, 0);
      switch(this.mMode) {
      case 0:
         this.mTabStrip.setGravity(8388611);
         break;
      case 1:
         this.mTabStrip.setGravity(1);
      }

      this.updateTabViews(true);
   }

   void updateTabViews(boolean requestLayout) {
      for(int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
         View child = this.mTabStrip.getChildAt(i);
         child.setMinimumWidth(this.getTabMinWidth());
         this.updateTabViewLayoutParams((android.widget.LinearLayout.LayoutParams)child.getLayoutParams());
         if (requestLayout) {
            child.requestLayout();
         }
      }

   }

   private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
      int[][] states = new int[2][];
      int[] colors = new int[2];
      int i = 0;
      states[i] = SELECTED_STATE_SET;
      colors[i] = selectedColor;
      int i = i + 1;
      states[i] = EMPTY_STATE_SET;
      colors[i] = defaultColor;
      ++i;
      return new ColorStateList(states, colors);
   }

   private int getDefaultHeight() {
      boolean hasIconAndText = false;
      int i = 0;

      for(int count = this.mTabs.size(); i < count; ++i) {
         TabLayout.Tab tab = (TabLayout.Tab)this.mTabs.get(i);
         if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
            hasIconAndText = true;
            break;
         }
      }

      return hasIconAndText ? 72 : 48;
   }

   private int getTabMinWidth() {
      if (this.mRequestedTabMinWidth != -1) {
         return this.mRequestedTabMinWidth;
      } else {
         return this.mMode == 0 ? this.mScrollableTabMinWidth : 0;
      }
   }

   public LayoutParams generateLayoutParams(AttributeSet attrs) {
      return this.generateDefaultLayoutParams();
   }

   int getTabMaxWidth() {
      return this.mTabMaxWidth;
   }

   private class AdapterChangeListener implements OnAdapterChangeListener {
      private boolean mAutoRefresh;

      public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
         if (TabLayout.this.mViewPager == viewPager) {
            TabLayout.this.setPagerAdapter(newAdapter, this.mAutoRefresh);
         }

      }

      void setAutoRefresh(boolean autoRefresh) {
         this.mAutoRefresh = autoRefresh;
      }
   }

   private class PagerAdapterObserver extends DataSetObserver {
      public void onChanged() {
         TabLayout.this.populateFromPagerAdapter();
      }

      public void onInvalidated() {
         TabLayout.this.populateFromPagerAdapter();
      }
   }

   public static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
      private final ViewPager mViewPager;

      public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
         this.mViewPager = viewPager;
      }

      public void onTabSelected(TabLayout.Tab tab) {
         this.mViewPager.setCurrentItem(tab.getPosition());
      }

      public void onTabUnselected(TabLayout.Tab tab) {
      }

      public void onTabReselected(TabLayout.Tab tab) {
      }
   }

   public static class TabLayoutOnPageChangeListener implements OnPageChangeListener {
      private final WeakReference mTabLayoutRef;
      private int mPreviousScrollState;
      private int mScrollState;

      public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
         this.mTabLayoutRef = new WeakReference(tabLayout);
      }

      public void onPageScrollStateChanged(int state) {
         this.mPreviousScrollState = this.mScrollState;
         this.mScrollState = state;
      }

      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
         TabLayout tabLayout = (TabLayout)this.mTabLayoutRef.get();
         if (tabLayout != null) {
            boolean updateText = this.mScrollState != 2 || this.mPreviousScrollState == 1;
            boolean updateIndicator = this.mScrollState != 2 || this.mPreviousScrollState != 0;
            tabLayout.setScrollPosition(position, positionOffset, updateText, updateIndicator);
         }

      }

      public void onPageSelected(int position) {
         TabLayout tabLayout = (TabLayout)this.mTabLayoutRef.get();
         if (tabLayout != null && tabLayout.getSelectedTabPosition() != position && position < tabLayout.getTabCount()) {
            boolean updateIndicator = this.mScrollState == 0 || this.mScrollState == 2 && this.mPreviousScrollState == 0;
            tabLayout.selectTab(tabLayout.getTabAt(position), updateIndicator);
         }

      }

      void reset() {
         this.mPreviousScrollState = this.mScrollState = 0;
      }
   }

   private class SlidingTabStrip extends LinearLayout {
      private int mSelectedIndicatorHeight;
      private final Paint mSelectedIndicatorPaint;
      int mSelectedPosition = -1;
      float mSelectionOffset;
      private int mLayoutDirection = -1;
      private int mIndicatorLeft = -1;
      private int mIndicatorRight = -1;
      private ValueAnimator mIndicatorAnimator;

      SlidingTabStrip(Context context) {
         super(context);
         this.setWillNotDraw(false);
         this.mSelectedIndicatorPaint = new Paint();
      }

      void setSelectedIndicatorColor(int color) {
         if (this.mSelectedIndicatorPaint.getColor() != color) {
            this.mSelectedIndicatorPaint.setColor(color);
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }

      void setSelectedIndicatorHeight(int height) {
         if (this.mSelectedIndicatorHeight != height) {
            this.mSelectedIndicatorHeight = height;
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }

      boolean childrenNeedLayout() {
         int i = 0;

         for(int z = this.getChildCount(); i < z; ++i) {
            View child = this.getChildAt(i);
            if (child.getWidth() <= 0) {
               return true;
            }
         }

         return false;
      }

      void setIndicatorPositionFromTabPosition(int position, float positionOffset) {
         if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
         }

         this.mSelectedPosition = position;
         this.mSelectionOffset = positionOffset;
         this.updateIndicatorPosition();
      }

      float getIndicatorPosition() {
         return (float)this.mSelectedPosition + this.mSelectionOffset;
      }

      public void onRtlPropertiesChanged(int layoutDirection) {
         super.onRtlPropertiesChanged(layoutDirection);
         if (VERSION.SDK_INT < 23 && this.mLayoutDirection != layoutDirection) {
            this.requestLayout();
            this.mLayoutDirection = layoutDirection;
         }

      }

      protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         if (MeasureSpec.getMode(widthMeasureSpec) == 1073741824) {
            if (TabLayout.this.mMode == 1 && TabLayout.this.mTabGravity == 1) {
               int count = this.getChildCount();
               int largestTabWidth = 0;
               int gutter = 0;

               for(int z = count; gutter < z; ++gutter) {
                  View child = this.getChildAt(gutter);
                  if (child.getVisibility() == 0) {
                     largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
                  }
               }

               if (largestTabWidth <= 0) {
                  return;
               }

               gutter = TabLayout.this.dpToPx(16);
               boolean remeasure = false;
               if (largestTabWidth * count > this.getMeasuredWidth() - gutter * 2) {
                  TabLayout.this.mTabGravity = 0;
                  TabLayout.this.updateTabViews(false);
                  remeasure = true;
               } else {
                  for(int i = 0; i < count; ++i) {
                     android.widget.LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams)this.getChildAt(i).getLayoutParams();
                     if (lp.width != largestTabWidth || lp.weight != 0.0F) {
                        lp.width = largestTabWidth;
                        lp.weight = 0.0F;
                        remeasure = true;
                     }
                  }
               }

               if (remeasure) {
                  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
               }
            }

         }
      }

      protected void onLayout(boolean changed, int l, int t, int r, int b) {
         super.onLayout(changed, l, t, r, b);
         if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
            long duration = this.mIndicatorAnimator.getDuration();
            this.animateIndicatorToPosition(this.mSelectedPosition, Math.round((1.0F - this.mIndicatorAnimator.getAnimatedFraction()) * (float)duration));
         } else {
            this.updateIndicatorPosition();
         }

      }

      private void updateIndicatorPosition() {
         View selectedTitle = this.getChildAt(this.mSelectedPosition);
         int left;
         int right;
         if (selectedTitle != null && selectedTitle.getWidth() > 0) {
            left = selectedTitle.getLeft();
            right = selectedTitle.getRight();
            if (this.mSelectionOffset > 0.0F && this.mSelectedPosition < this.getChildCount() - 1) {
               View nextTitle = this.getChildAt(this.mSelectedPosition + 1);
               left = (int)(this.mSelectionOffset * (float)nextTitle.getLeft() + (1.0F - this.mSelectionOffset) * (float)left);
               right = (int)(this.mSelectionOffset * (float)nextTitle.getRight() + (1.0F - this.mSelectionOffset) * (float)right);
            }
         } else {
            right = -1;
            left = -1;
         }

         this.setIndicatorPosition(left, right);
      }

      void setIndicatorPosition(int left, int right) {
         if (left != this.mIndicatorLeft || right != this.mIndicatorRight) {
            this.mIndicatorLeft = left;
            this.mIndicatorRight = right;
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }

      void animateIndicatorToPosition(final int position, int duration) {
         if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
         }

         boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
         View targetView = this.getChildAt(position);
         if (targetView == null) {
            this.updateIndicatorPosition();
         } else {
            final int targetLeft = targetView.getLeft();
            final int targetRight = targetView.getRight();
            final int startLeft;
            final int startRight;
            if (Math.abs(position - this.mSelectedPosition) <= 1) {
               startLeft = this.mIndicatorLeft;
               startRight = this.mIndicatorRight;
            } else {
               int offset = TabLayout.this.dpToPx(24);
               if (position < this.mSelectedPosition) {
                  if (isRtl) {
                     startLeft = startRight = targetLeft - offset;
                  } else {
                     startLeft = startRight = targetRight + offset;
                  }
               } else if (isRtl) {
                  startLeft = startRight = targetRight + offset;
               } else {
                  startLeft = startRight = targetLeft - offset;
               }
            }

            if (startLeft != targetLeft || startRight != targetRight) {
               ValueAnimator animator = this.mIndicatorAnimator = new ValueAnimator();
               animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
               animator.setDuration((long)duration);
               animator.setFloatValues(new float[]{0.0F, 1.0F});
               animator.addUpdateListener(new AnimatorUpdateListener() {
                  public void onAnimationUpdate(ValueAnimator animator) {
                     float fraction = animator.getAnimatedFraction();
                     SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(startLeft, targetLeft, fraction), AnimationUtils.lerp(startRight, targetRight, fraction));
                  }
               });
               animator.addListener(new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator animator) {
                     SlidingTabStrip.this.mSelectedPosition = position;
                     SlidingTabStrip.this.mSelectionOffset = 0.0F;
                  }
               });
               animator.start();
            }

         }
      }

      public void draw(Canvas canvas) {
         super.draw(canvas);
         if (this.mIndicatorLeft >= 0 && this.mIndicatorRight > this.mIndicatorLeft) {
            canvas.drawRect((float)this.mIndicatorLeft, (float)(this.getHeight() - this.mSelectedIndicatorHeight), (float)this.mIndicatorRight, (float)this.getHeight(), this.mSelectedIndicatorPaint);
         }

      }
   }

   class TabView extends LinearLayout {
      private TabLayout.Tab mTab;
      private TextView mTextView;
      private ImageView mIconView;
      private View mCustomView;
      private TextView mCustomTextView;
      private ImageView mCustomIconView;
      private int mDefaultMaxLines = 2;

      public TabView(Context context) {
         super(context);
         if (TabLayout.this.mTabBackgroundResId != 0) {
            ViewCompat.setBackground(this, AppCompatResources.getDrawable(context, TabLayout.this.mTabBackgroundResId));
         }

         ViewCompat.setPaddingRelative(this, TabLayout.this.mTabPaddingStart, TabLayout.this.mTabPaddingTop, TabLayout.this.mTabPaddingEnd, TabLayout.this.mTabPaddingBottom);
         this.setGravity(17);
         this.setOrientation(1);
         this.setClickable(true);
         ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
      }

      public boolean performClick() {
         boolean handled = super.performClick();
         if (this.mTab != null) {
            if (!handled) {
               this.playSoundEffect(0);
            }

            this.mTab.select();
            return true;
         } else {
            return handled;
         }
      }

      public void setSelected(boolean selected) {
         boolean changed = this.isSelected() != selected;
         super.setSelected(selected);
         if (changed && selected && VERSION.SDK_INT < 16) {
            this.sendAccessibilityEvent(4);
         }

         if (this.mTextView != null) {
            this.mTextView.setSelected(selected);
         }

         if (this.mIconView != null) {
            this.mIconView.setSelected(selected);
         }

         if (this.mCustomView != null) {
            this.mCustomView.setSelected(selected);
         }

      }

      public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
         super.onInitializeAccessibilityEvent(event);
         event.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
      }

      public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
         super.onInitializeAccessibilityNodeInfo(info);
         info.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
      }

      public void onMeasure(int origWidthMeasureSpec, int origHeightMeasureSpec) {
         int specWidthSize = MeasureSpec.getSize(origWidthMeasureSpec);
         int specWidthMode = MeasureSpec.getMode(origWidthMeasureSpec);
         int maxWidth = TabLayout.this.getTabMaxWidth();
         int widthMeasureSpec;
         if (maxWidth <= 0 || specWidthMode != 0 && specWidthSize <= maxWidth) {
            widthMeasureSpec = origWidthMeasureSpec;
         } else {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(TabLayout.this.mTabMaxWidth, Integer.MIN_VALUE);
         }

         super.onMeasure(widthMeasureSpec, origHeightMeasureSpec);
         if (this.mTextView != null) {
            Resources res = this.getResources();
            float textSize = TabLayout.this.mTabTextSize;
            int maxLines = this.mDefaultMaxLines;
            if (this.mIconView != null && this.mIconView.getVisibility() == 0) {
               maxLines = 1;
            } else if (this.mTextView != null && this.mTextView.getLineCount() > 1) {
               textSize = TabLayout.this.mTabTextMultiLineSize;
            }

            float curTextSize = this.mTextView.getTextSize();
            int curLineCount = this.mTextView.getLineCount();
            int curMaxLines = TextViewCompat.getMaxLines(this.mTextView);
            if (textSize != curTextSize || curMaxLines >= 0 && maxLines != curMaxLines) {
               boolean updateTextView = true;
               if (TabLayout.this.mMode == 1 && textSize > curTextSize && curLineCount == 1) {
                  Layout layout = this.mTextView.getLayout();
                  if (layout == null || this.approximateLineWidth(layout, 0, textSize) > (float)(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight())) {
                     updateTextView = false;
                  }
               }

               if (updateTextView) {
                  this.mTextView.setTextSize(0, textSize);
                  this.mTextView.setMaxLines(maxLines);
                  super.onMeasure(widthMeasureSpec, origHeightMeasureSpec);
               }
            }
         }

      }

      void setTab(@Nullable TabLayout.Tab tab) {
         if (tab != this.mTab) {
            this.mTab = tab;
            this.update();
         }

      }

      void reset() {
         this.setTab((TabLayout.Tab)null);
         this.setSelected(false);
      }

      final void update() {
         TabLayout.Tab tab = this.mTab;
         View custom = tab != null ? tab.getCustomView() : null;
         if (custom != null) {
            ViewParent customParent = custom.getParent();
            if (customParent != this) {
               if (customParent != null) {
                  ((ViewGroup)customParent).removeView(custom);
               }

               this.addView(custom);
            }

            this.mCustomView = custom;
            if (this.mTextView != null) {
               this.mTextView.setVisibility(8);
            }

            if (this.mIconView != null) {
               this.mIconView.setVisibility(8);
               this.mIconView.setImageDrawable((Drawable)null);
            }

            this.mCustomTextView = (TextView)custom.findViewById(16908308);
            if (this.mCustomTextView != null) {
               this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mCustomTextView);
            }

            this.mCustomIconView = (ImageView)custom.findViewById(16908294);
         } else {
            if (this.mCustomView != null) {
               this.removeView(this.mCustomView);
               this.mCustomView = null;
            }

            this.mCustomTextView = null;
            this.mCustomIconView = null;
         }

         if (this.mCustomView == null) {
            if (this.mIconView == null) {
               ImageView iconView = (ImageView)LayoutInflater.from(this.getContext()).inflate(layout.design_layout_tab_icon, this, false);
               this.addView(iconView, 0);
               this.mIconView = iconView;
            }

            if (this.mTextView == null) {
               TextView textView = (TextView)LayoutInflater.from(this.getContext()).inflate(layout.design_layout_tab_text, this, false);
               this.addView(textView);
               this.mTextView = textView;
               this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mTextView);
            }

            TextViewCompat.setTextAppearance(this.mTextView, TabLayout.this.mTabTextAppearance);
            if (TabLayout.this.mTabTextColors != null) {
               this.mTextView.setTextColor(TabLayout.this.mTabTextColors);
            }

            this.updateTextAndIcon(this.mTextView, this.mIconView);
         } else if (this.mCustomTextView != null || this.mCustomIconView != null) {
            this.updateTextAndIcon(this.mCustomTextView, this.mCustomIconView);
         }

         this.setSelected(tab != null && tab.isSelected());
      }

      private void updateTextAndIcon(@Nullable TextView textView, @Nullable ImageView iconView) {
         Drawable icon = this.mTab != null ? this.mTab.getIcon() : null;
         CharSequence text = this.mTab != null ? this.mTab.getText() : null;
         CharSequence contentDesc = this.mTab != null ? this.mTab.getContentDescription() : null;
         if (iconView != null) {
            if (icon != null) {
               iconView.setImageDrawable(icon);
               iconView.setVisibility(0);
               this.setVisibility(0);
            } else {
               iconView.setVisibility(8);
               iconView.setImageDrawable((Drawable)null);
            }

            iconView.setContentDescription(contentDesc);
         }

         boolean hasText = !TextUtils.isEmpty(text);
         if (textView != null) {
            if (hasText) {
               textView.setText(text);
               textView.setVisibility(0);
               this.setVisibility(0);
            } else {
               textView.setVisibility(8);
               textView.setText((CharSequence)null);
            }

            textView.setContentDescription(contentDesc);
         }

         if (iconView != null) {
            MarginLayoutParams lp = (MarginLayoutParams)iconView.getLayoutParams();
            int bottomMargin = 0;
            if (hasText && iconView.getVisibility() == 0) {
               bottomMargin = TabLayout.this.dpToPx(8);
            }

            if (bottomMargin != lp.bottomMargin) {
               lp.bottomMargin = bottomMargin;
               iconView.requestLayout();
            }
         }

         TooltipCompat.setTooltipText(this, hasText ? null : contentDesc);
      }

      public TabLayout.Tab getTab() {
         return this.mTab;
      }

      private float approximateLineWidth(Layout layout, int line, float textSize) {
         return layout.getLineWidth(line) * (textSize / layout.getPaint().getTextSize());
      }
   }

   public static final class Tab {
      public static final int INVALID_POSITION = -1;
      private Object mTag;
      private Drawable mIcon;
      private CharSequence mText;
      private CharSequence mContentDesc;
      private int mPosition = -1;
      private View mCustomView;
      TabLayout mParent;
      TabLayout.TabView mView;

      @Nullable
      public Object getTag() {
         return this.mTag;
      }

      @NonNull
      public TabLayout.Tab setTag(@Nullable Object tag) {
         this.mTag = tag;
         return this;
      }

      @Nullable
      public View getCustomView() {
         return this.mCustomView;
      }

      @NonNull
      public TabLayout.Tab setCustomView(@Nullable View view) {
         this.mCustomView = view;
         this.updateView();
         return this;
      }

      @NonNull
      public TabLayout.Tab setCustomView(@LayoutRes int resId) {
         LayoutInflater inflater = LayoutInflater.from(this.mView.getContext());
         return this.setCustomView(inflater.inflate(resId, this.mView, false));
      }

      @Nullable
      public Drawable getIcon() {
         return this.mIcon;
      }

      public int getPosition() {
         return this.mPosition;
      }

      void setPosition(int position) {
         this.mPosition = position;
      }

      @Nullable
      public CharSequence getText() {
         return this.mText;
      }

      @NonNull
      public TabLayout.Tab setIcon(@Nullable Drawable icon) {
         this.mIcon = icon;
         this.updateView();
         return this;
      }

      @NonNull
      public TabLayout.Tab setIcon(@DrawableRes int resId) {
         if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         } else {
            return this.setIcon(AppCompatResources.getDrawable(this.mParent.getContext(), resId));
         }
      }

      @NonNull
      public TabLayout.Tab setText(@Nullable CharSequence text) {
         this.mText = text;
         this.updateView();
         return this;
      }

      @NonNull
      public TabLayout.Tab setText(@StringRes int resId) {
         if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         } else {
            return this.setText(this.mParent.getResources().getText(resId));
         }
      }

      public void select() {
         if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         } else {
            this.mParent.selectTab(this);
         }
      }

      public boolean isSelected() {
         if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         } else {
            return this.mParent.getSelectedTabPosition() == this.mPosition;
         }
      }

      @NonNull
      public TabLayout.Tab setContentDescription(@StringRes int resId) {
         if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         } else {
            return this.setContentDescription(this.mParent.getResources().getText(resId));
         }
      }

      @NonNull
      public TabLayout.Tab setContentDescription(@Nullable CharSequence contentDesc) {
         this.mContentDesc = contentDesc;
         this.updateView();
         return this;
      }

      @Nullable
      public CharSequence getContentDescription() {
         return this.mContentDesc;
      }

      void updateView() {
         if (this.mView != null) {
            this.mView.update();
         }

      }

      void reset() {
         this.mParent = null;
         this.mView = null;
         this.mTag = null;
         this.mIcon = null;
         this.mText = null;
         this.mContentDesc = null;
         this.mPosition = -1;
         this.mCustomView = null;
      }
   }

   public interface OnTabSelectedListener {
      void onTabSelected(TabLayout.Tab var1);

      void onTabUnselected(TabLayout.Tab var1);

      void onTabReselected(TabLayout.Tab var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface TabGravity {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface Mode {
   }
}
