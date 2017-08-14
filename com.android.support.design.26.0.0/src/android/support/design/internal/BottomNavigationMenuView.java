package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView extends ViewGroup implements MenuView {
   private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
   private final TransitionSet mSet;
   private final int mInactiveItemMaxWidth;
   private final int mInactiveItemMinWidth;
   private final int mActiveItemMaxWidth;
   private final int mItemHeight;
   private final OnClickListener mOnClickListener;
   private final Pool mItemPool;
   private boolean mShiftingMode;
   private BottomNavigationItemView[] mButtons;
   private int mSelectedItemId;
   private int mSelectedItemPosition;
   private ColorStateList mItemIconTint;
   private ColorStateList mItemTextColor;
   private int mItemBackgroundRes;
   private int[] mTempChildWidths;
   private BottomNavigationPresenter mPresenter;
   private MenuBuilder mMenu;

   public BottomNavigationMenuView(Context context) {
      this(context, (AttributeSet)null);
   }

   public BottomNavigationMenuView(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mItemPool = new SynchronizedPool(5);
      this.mShiftingMode = true;
      this.mSelectedItemId = 0;
      this.mSelectedItemPosition = 0;
      Resources res = this.getResources();
      this.mInactiveItemMaxWidth = res.getDimensionPixelSize(dimen.design_bottom_navigation_item_max_width);
      this.mInactiveItemMinWidth = res.getDimensionPixelSize(dimen.design_bottom_navigation_item_min_width);
      this.mActiveItemMaxWidth = res.getDimensionPixelSize(dimen.design_bottom_navigation_active_item_max_width);
      this.mItemHeight = res.getDimensionPixelSize(dimen.design_bottom_navigation_height);
      this.mSet = new AutoTransition();
      this.mSet.setOrdering(0);
      this.mSet.setDuration(115L);
      this.mSet.setInterpolator(new FastOutSlowInInterpolator());
      this.mSet.addTransition(new TextScale());
      this.mOnClickListener = new OnClickListener() {
         public void onClick(View v) {
            BottomNavigationItemView itemView = (BottomNavigationItemView)v;
            MenuItem item = itemView.getItemData();
            if (!BottomNavigationMenuView.this.mMenu.performItemAction(item, BottomNavigationMenuView.this.mPresenter, 0)) {
               item.setChecked(true);
            }

         }
      };
      this.mTempChildWidths = new int[5];
   }

   public void initialize(MenuBuilder menu) {
      this.mMenu = menu;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int width = MeasureSpec.getSize(widthMeasureSpec);
      int count = this.getChildCount();
      int heightSpec = MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
      int totalWidth;
      int i;
      int extra;
      int i;
      if (this.mShiftingMode) {
         totalWidth = count - 1;
         i = width - totalWidth * this.mInactiveItemMinWidth;
         extra = Math.min(i, this.mActiveItemMaxWidth);
         i = (width - extra) / totalWidth;
         int inactiveWidth = Math.min(i, this.mInactiveItemMaxWidth);
         int extra = width - extra - inactiveWidth * totalWidth;

         for(int i = 0; i < count; ++i) {
            this.mTempChildWidths[i] = i == this.mSelectedItemPosition ? extra : inactiveWidth;
            if (extra > 0) {
               ++this.mTempChildWidths[i];
               --extra;
            }
         }
      } else {
         totalWidth = width / (count == 0 ? 1 : count);
         i = Math.min(totalWidth, this.mActiveItemMaxWidth);
         extra = width - i * count;

         for(i = 0; i < count; ++i) {
            this.mTempChildWidths[i] = i;
            if (extra > 0) {
               ++this.mTempChildWidths[i];
               --extra;
            }
         }
      }

      totalWidth = 0;

      for(i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            child.measure(MeasureSpec.makeMeasureSpec(this.mTempChildWidths[i], 1073741824), heightSpec);
            LayoutParams params = child.getLayoutParams();
            params.width = child.getMeasuredWidth();
            totalWidth += child.getMeasuredWidth();
         }
      }

      this.setMeasuredDimension(View.resolveSizeAndState(totalWidth, MeasureSpec.makeMeasureSpec(totalWidth, 1073741824), 0), View.resolveSizeAndState(this.mItemHeight, heightSpec, 0));
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      int count = this.getChildCount();
      int width = right - left;
      int height = bottom - top;
      int used = 0;

      for(int i = 0; i < count; ++i) {
         View child = this.getChildAt(i);
         if (child.getVisibility() != 8) {
            if (ViewCompat.getLayoutDirection(this) == 1) {
               child.layout(width - used - child.getMeasuredWidth(), 0, width - used, height);
            } else {
               child.layout(used, 0, child.getMeasuredWidth() + used, height);
            }

            used += child.getMeasuredWidth();
         }
      }

   }

   public int getWindowAnimations() {
      return 0;
   }

   public void setIconTintList(ColorStateList tint) {
      this.mItemIconTint = tint;
      if (this.mButtons != null) {
         BottomNavigationItemView[] var2 = this.mButtons;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            BottomNavigationItemView item = var2[var4];
            item.setIconTintList(tint);
         }

      }
   }

   @Nullable
   public ColorStateList getIconTintList() {
      return this.mItemIconTint;
   }

   public void setItemTextColor(ColorStateList color) {
      this.mItemTextColor = color;
      if (this.mButtons != null) {
         BottomNavigationItemView[] var2 = this.mButtons;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            BottomNavigationItemView item = var2[var4];
            item.setTextColor(color);
         }

      }
   }

   public ColorStateList getItemTextColor() {
      return this.mItemTextColor;
   }

   public void setItemBackgroundRes(int background) {
      this.mItemBackgroundRes = background;
      if (this.mButtons != null) {
         BottomNavigationItemView[] var2 = this.mButtons;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            BottomNavigationItemView item = var2[var4];
            item.setItemBackground(background);
         }

      }
   }

   public int getItemBackgroundRes() {
      return this.mItemBackgroundRes;
   }

   public void setPresenter(BottomNavigationPresenter presenter) {
      this.mPresenter = presenter;
   }

   public void buildMenuView() {
      this.removeAllViews();
      if (this.mButtons != null) {
         BottomNavigationItemView[] var1 = this.mButtons;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            BottomNavigationItemView item = var1[var3];
            this.mItemPool.release(item);
         }
      }

      if (this.mMenu.size() == 0) {
         this.mSelectedItemId = 0;
         this.mSelectedItemPosition = 0;
         this.mButtons = null;
      } else {
         this.mButtons = new BottomNavigationItemView[this.mMenu.size()];
         this.mShiftingMode = this.mMenu.size() > 3;

         for(int i = 0; i < this.mMenu.size(); ++i) {
            this.mPresenter.setUpdateSuspended(true);
            this.mMenu.getItem(i).setCheckable(true);
            this.mPresenter.setUpdateSuspended(false);
            BottomNavigationItemView child = this.getNewItem();
            this.mButtons[i] = child;
            child.setIconTintList(this.mItemIconTint);
            child.setTextColor(this.mItemTextColor);
            child.setItemBackground(this.mItemBackgroundRes);
            child.setShiftingMode(this.mShiftingMode);
            child.initialize((MenuItemImpl)this.mMenu.getItem(i), 0);
            child.setItemPosition(i);
            child.setOnClickListener(this.mOnClickListener);
            this.addView(child);
         }

         this.mSelectedItemPosition = Math.min(this.mMenu.size() - 1, this.mSelectedItemPosition);
         this.mMenu.getItem(this.mSelectedItemPosition).setChecked(true);
      }
   }

   public void updateMenuView() {
      int menuSize = this.mMenu.size();
      if (menuSize != this.mButtons.length) {
         this.buildMenuView();
      } else {
         int previousSelectedId = this.mSelectedItemId;

         int i;
         for(i = 0; i < menuSize; ++i) {
            MenuItem item = this.mMenu.getItem(i);
            if (item.isChecked()) {
               this.mSelectedItemId = item.getItemId();
               this.mSelectedItemPosition = i;
            }
         }

         if (previousSelectedId != this.mSelectedItemId) {
            TransitionManager.beginDelayedTransition(this, this.mSet);
         }

         for(i = 0; i < menuSize; ++i) {
            this.mPresenter.setUpdateSuspended(true);
            this.mButtons[i].initialize((MenuItemImpl)this.mMenu.getItem(i), 0);
            this.mPresenter.setUpdateSuspended(false);
         }

      }
   }

   private BottomNavigationItemView getNewItem() {
      BottomNavigationItemView item = (BottomNavigationItemView)this.mItemPool.acquire();
      if (item == null) {
         item = new BottomNavigationItemView(this.getContext());
      }

      return item;
   }

   public int getSelectedItemId() {
      return this.mSelectedItemId;
   }

   void tryRestoreSelectedItemId(int itemId) {
      int size = this.mMenu.size();

      for(int i = 0; i < size; ++i) {
         MenuItem item = this.mMenu.getItem(i);
         if (itemId == item.getItemId()) {
            this.mSelectedItemId = itemId;
            this.mSelectedItemPosition = i;
            item.setChecked(true);
            break;
         }
      }

   }
}
