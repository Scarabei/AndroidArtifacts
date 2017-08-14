package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.ActionBarPolicy;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemSelectedListener {
   private static final String TAG = "ScrollingTabContainerView";
   Runnable mTabSelector;
   private ScrollingTabContainerView.TabClickListener mTabClickListener;
   LinearLayoutCompat mTabLayout;
   private Spinner mTabSpinner;
   private boolean mAllowCollapse;
   int mMaxTabWidth;
   int mStackedTabMaxWidth;
   private int mContentHeight;
   private int mSelectedTabIndex;
   protected ViewPropertyAnimator mVisibilityAnim;
   protected final ScrollingTabContainerView.VisibilityAnimListener mVisAnimListener = new ScrollingTabContainerView.VisibilityAnimListener();
   private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
   private static final int FADE_DURATION = 200;

   public ScrollingTabContainerView(Context context) {
      super(context);
      this.setHorizontalScrollBarEnabled(false);
      ActionBarPolicy abp = ActionBarPolicy.get(context);
      this.setContentHeight(abp.getTabContainerHeight());
      this.mStackedTabMaxWidth = abp.getStackedTabMaxWidth();
      this.mTabLayout = this.createTabLayout();
      this.addView(this.mTabLayout, new LayoutParams(-2, -1));
   }

   public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      boolean lockedExpanded = widthMode == 1073741824;
      this.setFillViewport(lockedExpanded);
      int childCount = this.mTabLayout.getChildCount();
      if (childCount <= 1 || widthMode != 1073741824 && widthMode != Integer.MIN_VALUE) {
         this.mMaxTabWidth = -1;
      } else {
         if (childCount > 2) {
            this.mMaxTabWidth = (int)((float)MeasureSpec.getSize(widthMeasureSpec) * 0.4F);
         } else {
            this.mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
         }

         this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
      }

      heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824);
      boolean canCollapse = !lockedExpanded && this.mAllowCollapse;
      if (canCollapse) {
         this.mTabLayout.measure(0, heightMeasureSpec);
         if (this.mTabLayout.getMeasuredWidth() > MeasureSpec.getSize(widthMeasureSpec)) {
            this.performCollapse();
         } else {
            this.performExpand();
         }
      } else {
         this.performExpand();
      }

      int oldWidth = this.getMeasuredWidth();
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      int newWidth = this.getMeasuredWidth();
      if (lockedExpanded && oldWidth != newWidth) {
         this.setTabSelected(this.mSelectedTabIndex);
      }

   }

   private boolean isCollapsed() {
      return this.mTabSpinner != null && this.mTabSpinner.getParent() == this;
   }

   public void setAllowCollapse(boolean allowCollapse) {
      this.mAllowCollapse = allowCollapse;
   }

   private void performCollapse() {
      if (!this.isCollapsed()) {
         if (this.mTabSpinner == null) {
            this.mTabSpinner = this.createSpinner();
         }

         this.removeView(this.mTabLayout);
         this.addView(this.mTabSpinner, new LayoutParams(-2, -1));
         if (this.mTabSpinner.getAdapter() == null) {
            this.mTabSpinner.setAdapter(new ScrollingTabContainerView.TabAdapter());
         }

         if (this.mTabSelector != null) {
            this.removeCallbacks(this.mTabSelector);
            this.mTabSelector = null;
         }

         this.mTabSpinner.setSelection(this.mSelectedTabIndex);
      }
   }

   private boolean performExpand() {
      if (!this.isCollapsed()) {
         return false;
      } else {
         this.removeView(this.mTabSpinner);
         this.addView(this.mTabLayout, new LayoutParams(-2, -1));
         this.setTabSelected(this.mTabSpinner.getSelectedItemPosition());
         return false;
      }
   }

   public void setTabSelected(int position) {
      this.mSelectedTabIndex = position;
      int tabCount = this.mTabLayout.getChildCount();

      for(int i = 0; i < tabCount; ++i) {
         View child = this.mTabLayout.getChildAt(i);
         boolean isSelected = i == position;
         child.setSelected(isSelected);
         if (isSelected) {
            this.animateToTab(position);
         }
      }

      if (this.mTabSpinner != null && position >= 0) {
         this.mTabSpinner.setSelection(position);
      }

   }

   public void setContentHeight(int contentHeight) {
      this.mContentHeight = contentHeight;
      this.requestLayout();
   }

   private LinearLayoutCompat createTabLayout() {
      LinearLayoutCompat tabLayout = new LinearLayoutCompat(this.getContext(), (AttributeSet)null, attr.actionBarTabBarStyle);
      tabLayout.setMeasureWithLargestChildEnabled(true);
      tabLayout.setGravity(17);
      tabLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
      return tabLayout;
   }

   private Spinner createSpinner() {
      Spinner spinner = new AppCompatSpinner(this.getContext(), (AttributeSet)null, attr.actionDropDownStyle);
      spinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
      spinner.setOnItemSelectedListener(this);
      return spinner;
   }

   protected void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      ActionBarPolicy abp = ActionBarPolicy.get(this.getContext());
      this.setContentHeight(abp.getTabContainerHeight());
      this.mStackedTabMaxWidth = abp.getStackedTabMaxWidth();
   }

   public void animateToVisibility(int visibility) {
      if (this.mVisibilityAnim != null) {
         this.mVisibilityAnim.cancel();
      }

      ViewPropertyAnimator anim;
      if (visibility == 0) {
         if (this.getVisibility() != 0) {
            this.setAlpha(0.0F);
         }

         anim = this.animate().alpha(1.0F);
         anim.setDuration(200L);
         anim.setInterpolator(sAlphaInterpolator);
         anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
         anim.start();
      } else {
         anim = this.animate().alpha(0.0F);
         anim.setDuration(200L);
         anim.setInterpolator(sAlphaInterpolator);
         anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
         anim.start();
      }

   }

   public void animateToTab(int position) {
      final View tabView = this.mTabLayout.getChildAt(position);
      if (this.mTabSelector != null) {
         this.removeCallbacks(this.mTabSelector);
      }

      this.mTabSelector = new Runnable() {
         public void run() {
            int scrollPos = tabView.getLeft() - (ScrollingTabContainerView.this.getWidth() - tabView.getWidth()) / 2;
            ScrollingTabContainerView.this.smoothScrollTo(scrollPos, 0);
            ScrollingTabContainerView.this.mTabSelector = null;
         }
      };
      this.post(this.mTabSelector);
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.mTabSelector != null) {
         this.post(this.mTabSelector);
      }

   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.mTabSelector != null) {
         this.removeCallbacks(this.mTabSelector);
      }

   }

   ScrollingTabContainerView.TabView createTabView(ActionBar.Tab tab, boolean forAdapter) {
      ScrollingTabContainerView.TabView tabView = new ScrollingTabContainerView.TabView(this.getContext(), tab, forAdapter);
      if (forAdapter) {
         tabView.setBackgroundDrawable((Drawable)null);
         tabView.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, this.mContentHeight));
      } else {
         tabView.setFocusable(true);
         if (this.mTabClickListener == null) {
            this.mTabClickListener = new ScrollingTabContainerView.TabClickListener();
         }

         tabView.setOnClickListener(this.mTabClickListener);
      }

      return tabView;
   }

   public void addTab(ActionBar.Tab tab, boolean setSelected) {
      ScrollingTabContainerView.TabView tabView = this.createTabView(tab, false);
      this.mTabLayout.addView(tabView, new LinearLayoutCompat.LayoutParams(0, -1, 1.0F));
      if (this.mTabSpinner != null) {
         ((ScrollingTabContainerView.TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
      }

      if (setSelected) {
         tabView.setSelected(true);
      }

      if (this.mAllowCollapse) {
         this.requestLayout();
      }

   }

   public void addTab(ActionBar.Tab tab, int position, boolean setSelected) {
      ScrollingTabContainerView.TabView tabView = this.createTabView(tab, false);
      this.mTabLayout.addView(tabView, position, new LinearLayoutCompat.LayoutParams(0, -1, 1.0F));
      if (this.mTabSpinner != null) {
         ((ScrollingTabContainerView.TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
      }

      if (setSelected) {
         tabView.setSelected(true);
      }

      if (this.mAllowCollapse) {
         this.requestLayout();
      }

   }

   public void updateTab(int position) {
      ((ScrollingTabContainerView.TabView)this.mTabLayout.getChildAt(position)).update();
      if (this.mTabSpinner != null) {
         ((ScrollingTabContainerView.TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
      }

      if (this.mAllowCollapse) {
         this.requestLayout();
      }

   }

   public void removeTabAt(int position) {
      this.mTabLayout.removeViewAt(position);
      if (this.mTabSpinner != null) {
         ((ScrollingTabContainerView.TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
      }

      if (this.mAllowCollapse) {
         this.requestLayout();
      }

   }

   public void removeAllTabs() {
      this.mTabLayout.removeAllViews();
      if (this.mTabSpinner != null) {
         ((ScrollingTabContainerView.TabAdapter)this.mTabSpinner.getAdapter()).notifyDataSetChanged();
      }

      if (this.mAllowCollapse) {
         this.requestLayout();
      }

   }

   public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
      ScrollingTabContainerView.TabView tabView = (ScrollingTabContainerView.TabView)view;
      tabView.getTab().select();
   }

   public void onNothingSelected(AdapterView adapterView) {
   }

   protected class VisibilityAnimListener extends AnimatorListenerAdapter {
      private boolean mCanceled = false;
      private int mFinalVisibility;

      public ScrollingTabContainerView.VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator animation, int visibility) {
         this.mFinalVisibility = visibility;
         ScrollingTabContainerView.this.mVisibilityAnim = animation;
         return this;
      }

      public void onAnimationStart(Animator animator) {
         ScrollingTabContainerView.this.setVisibility(0);
         this.mCanceled = false;
      }

      public void onAnimationEnd(Animator animator) {
         if (!this.mCanceled) {
            ScrollingTabContainerView.this.mVisibilityAnim = null;
            ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
         }
      }

      public void onAnimationCancel(Animator animator) {
         this.mCanceled = true;
      }
   }

   private class TabClickListener implements OnClickListener {
      public void onClick(View view) {
         ScrollingTabContainerView.TabView tabView = (ScrollingTabContainerView.TabView)view;
         tabView.getTab().select();
         int tabCount = ScrollingTabContainerView.this.mTabLayout.getChildCount();

         for(int i = 0; i < tabCount; ++i) {
            View child = ScrollingTabContainerView.this.mTabLayout.getChildAt(i);
            child.setSelected(child == view);
         }

      }
   }

   private class TabAdapter extends BaseAdapter {
      public int getCount() {
         return ScrollingTabContainerView.this.mTabLayout.getChildCount();
      }

      public Object getItem(int position) {
         return ((ScrollingTabContainerView.TabView)ScrollingTabContainerView.this.mTabLayout.getChildAt(position)).getTab();
      }

      public long getItemId(int position) {
         return (long)position;
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         if (convertView == null) {
            convertView = ScrollingTabContainerView.this.createTabView((ActionBar.Tab)this.getItem(position), true);
         } else {
            ((ScrollingTabContainerView.TabView)convertView).bindTab((ActionBar.Tab)this.getItem(position));
         }

         return (View)convertView;
      }
   }

   private class TabView extends LinearLayoutCompat {
      private final int[] BG_ATTRS = new int[]{16842964};
      private ActionBar.Tab mTab;
      private TextView mTextView;
      private ImageView mIconView;
      private View mCustomView;

      public TabView(Context context, ActionBar.Tab tab, boolean forList) {
         super(context, (AttributeSet)null, attr.actionBarTabStyle);
         this.mTab = tab;
         TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)null, this.BG_ATTRS, attr.actionBarTabStyle, 0);
         if (a.hasValue(0)) {
            this.setBackgroundDrawable(a.getDrawable(0));
         }

         a.recycle();
         if (forList) {
            this.setGravity(8388627);
         }

         this.update();
      }

      public void bindTab(ActionBar.Tab tab) {
         this.mTab = tab;
         this.update();
      }

      public void setSelected(boolean selected) {
         boolean changed = this.isSelected() != selected;
         super.setSelected(selected);
         if (changed && selected) {
            this.sendAccessibilityEvent(4);
         }

      }

      public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
         super.onInitializeAccessibilityEvent(event);
         event.setClassName(ActionBar.Tab.class.getName());
      }

      public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
         super.onInitializeAccessibilityNodeInfo(info);
         info.setClassName(ActionBar.Tab.class.getName());
      }

      public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         if (ScrollingTabContainerView.this.mMaxTabWidth > 0 && this.getMeasuredWidth() > ScrollingTabContainerView.this.mMaxTabWidth) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(ScrollingTabContainerView.this.mMaxTabWidth, 1073741824), heightMeasureSpec);
         }

      }

      public void update() {
         ActionBar.Tab tab = this.mTab;
         View custom = tab.getCustomView();
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
         } else {
            if (this.mCustomView != null) {
               this.removeView(this.mCustomView);
               this.mCustomView = null;
            }

            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
               if (this.mIconView == null) {
                  ImageView iconView = new AppCompatImageView(this.getContext());
                  LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(-2, -2);
                  lp.gravity = 16;
                  iconView.setLayoutParams(lp);
                  this.addView(iconView, 0);
                  this.mIconView = iconView;
               }

               this.mIconView.setImageDrawable(icon);
               this.mIconView.setVisibility(0);
            } else if (this.mIconView != null) {
               this.mIconView.setVisibility(8);
               this.mIconView.setImageDrawable((Drawable)null);
            }

            boolean hasText = !TextUtils.isEmpty(text);
            if (hasText) {
               if (this.mTextView == null) {
                  TextView textView = new AppCompatTextView(this.getContext(), (AttributeSet)null, attr.actionBarTabTextStyle);
                  textView.setEllipsize(TruncateAt.END);
                  LinearLayoutCompat.LayoutParams lpx = new LinearLayoutCompat.LayoutParams(-2, -2);
                  lpx.gravity = 16;
                  textView.setLayoutParams(lpx);
                  this.addView(textView);
                  this.mTextView = textView;
               }

               this.mTextView.setText(text);
               this.mTextView.setVisibility(0);
            } else if (this.mTextView != null) {
               this.mTextView.setVisibility(8);
               this.mTextView.setText((CharSequence)null);
            }

            if (this.mIconView != null) {
               this.mIconView.setContentDescription(tab.getContentDescription());
            }

            TooltipCompat.setTooltipText(this, hasText ? null : tab.getContentDescription());
         }

      }

      public ActionBar.Tab getTab() {
         return this.mTab;
      }
   }
}
