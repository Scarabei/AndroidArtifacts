package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.layout;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
   private static final String TAG = "ActionMenuPresenter";
   ActionMenuPresenter.OverflowMenuButton mOverflowButton;
   private Drawable mPendingOverflowIcon;
   private boolean mPendingOverflowIconSet;
   private boolean mReserveOverflow;
   private boolean mReserveOverflowSet;
   private int mWidthLimit;
   private int mActionItemWidthLimit;
   private int mMaxItems;
   private boolean mMaxItemsSet;
   private boolean mStrictWidthLimit;
   private boolean mWidthLimitSet;
   private boolean mExpandedActionViewsExclusive;
   private int mMinCellSize;
   private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
   private View mScrapActionButtonView;
   ActionMenuPresenter.OverflowPopup mOverflowPopup;
   ActionMenuPresenter.ActionButtonSubmenu mActionButtonPopup;
   ActionMenuPresenter.OpenOverflowRunnable mPostedOpenRunnable;
   private ActionMenuPresenter.ActionMenuPopupCallback mPopupCallback;
   final ActionMenuPresenter.PopupPresenterCallback mPopupPresenterCallback = new ActionMenuPresenter.PopupPresenterCallback();
   int mOpenSubMenuId;

   public ActionMenuPresenter(Context context) {
      super(context, layout.abc_action_menu_layout, layout.abc_action_menu_item_layout);
   }

   public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menu) {
      super.initForMenu(context, menu);
      Resources res = context.getResources();
      ActionBarPolicy abp = ActionBarPolicy.get(context);
      if (!this.mReserveOverflowSet) {
         this.mReserveOverflow = abp.showsOverflowMenuButton();
      }

      if (!this.mWidthLimitSet) {
         this.mWidthLimit = abp.getEmbeddedMenuWidthLimit();
      }

      if (!this.mMaxItemsSet) {
         this.mMaxItems = abp.getMaxActionButtons();
      }

      int width = this.mWidthLimit;
      if (this.mReserveOverflow) {
         if (this.mOverflowButton == null) {
            this.mOverflowButton = new ActionMenuPresenter.OverflowMenuButton(this.mSystemContext);
            if (this.mPendingOverflowIconSet) {
               this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
               this.mPendingOverflowIcon = null;
               this.mPendingOverflowIconSet = false;
            }

            int spec = MeasureSpec.makeMeasureSpec(0, 0);
            this.mOverflowButton.measure(spec, spec);
         }

         width -= this.mOverflowButton.getMeasuredWidth();
      } else {
         this.mOverflowButton = null;
      }

      this.mActionItemWidthLimit = width;
      this.mMinCellSize = (int)(56.0F * res.getDisplayMetrics().density);
      this.mScrapActionButtonView = null;
   }

   public void onConfigurationChanged(Configuration newConfig) {
      if (!this.mMaxItemsSet) {
         this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
      }

      if (this.mMenu != null) {
         this.mMenu.onItemsChanged(true);
      }

   }

   public void setWidthLimit(int width, boolean strict) {
      this.mWidthLimit = width;
      this.mStrictWidthLimit = strict;
      this.mWidthLimitSet = true;
   }

   public void setReserveOverflow(boolean reserveOverflow) {
      this.mReserveOverflow = reserveOverflow;
      this.mReserveOverflowSet = true;
   }

   public void setItemLimit(int itemCount) {
      this.mMaxItems = itemCount;
      this.mMaxItemsSet = true;
   }

   public void setExpandedActionViewsExclusive(boolean isExclusive) {
      this.mExpandedActionViewsExclusive = isExclusive;
   }

   public void setOverflowIcon(Drawable icon) {
      if (this.mOverflowButton != null) {
         this.mOverflowButton.setImageDrawable(icon);
      } else {
         this.mPendingOverflowIconSet = true;
         this.mPendingOverflowIcon = icon;
      }

   }

   public Drawable getOverflowIcon() {
      if (this.mOverflowButton != null) {
         return this.mOverflowButton.getDrawable();
      } else {
         return this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null;
      }
   }

   public MenuView getMenuView(ViewGroup root) {
      MenuView oldMenuView = this.mMenuView;
      MenuView result = super.getMenuView(root);
      if (oldMenuView != result) {
         ((ActionMenuView)result).setPresenter(this);
      }

      return result;
   }

   public View getItemView(MenuItemImpl item, View convertView, ViewGroup parent) {
      View actionView = item.getActionView();
      if (actionView == null || item.hasCollapsibleActionView()) {
         actionView = super.getItemView(item, convertView, parent);
      }

      actionView.setVisibility(item.isActionViewExpanded() ? 8 : 0);
      ActionMenuView menuParent = (ActionMenuView)parent;
      LayoutParams lp = actionView.getLayoutParams();
      if (!menuParent.checkLayoutParams(lp)) {
         actionView.setLayoutParams(menuParent.generateLayoutParams(lp));
      }

      return actionView;
   }

   public void bindItemView(MenuItemImpl item, MenuView.ItemView itemView) {
      itemView.initialize(item, 0);
      ActionMenuView menuView = (ActionMenuView)this.mMenuView;
      ActionMenuItemView actionItemView = (ActionMenuItemView)itemView;
      actionItemView.setItemInvoker(menuView);
      if (this.mPopupCallback == null) {
         this.mPopupCallback = new ActionMenuPresenter.ActionMenuPopupCallback();
      }

      actionItemView.setPopupCallback(this.mPopupCallback);
   }

   public boolean shouldIncludeItem(int childIndex, MenuItemImpl item) {
      return item.isActionButton();
   }

   public void updateMenuView(boolean cleared) {
      super.updateMenuView(cleared);
      ((View)this.mMenuView).requestLayout();
      ArrayList nonActionItems;
      int count;
      if (this.mMenu != null) {
         nonActionItems = this.mMenu.getActionItems();
         int count = nonActionItems.size();

         for(count = 0; count < count; ++count) {
            ActionProvider provider = ((MenuItemImpl)nonActionItems.get(count)).getSupportActionProvider();
            if (provider != null) {
               provider.setSubUiVisibilityListener(this);
            }
         }
      }

      nonActionItems = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
      boolean hasOverflow = false;
      if (this.mReserveOverflow && nonActionItems != null) {
         count = nonActionItems.size();
         if (count == 1) {
            hasOverflow = !((MenuItemImpl)nonActionItems.get(0)).isActionViewExpanded();
         } else {
            hasOverflow = count > 0;
         }
      }

      if (hasOverflow) {
         if (this.mOverflowButton == null) {
            this.mOverflowButton = new ActionMenuPresenter.OverflowMenuButton(this.mSystemContext);
         }

         ViewGroup parent = (ViewGroup)this.mOverflowButton.getParent();
         if (parent != this.mMenuView) {
            if (parent != null) {
               parent.removeView(this.mOverflowButton);
            }

            ActionMenuView menuView = (ActionMenuView)this.mMenuView;
            menuView.addView(this.mOverflowButton, menuView.generateOverflowButtonLayoutParams());
         }
      } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
         ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
      }

      ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
   }

   public boolean filterLeftoverView(ViewGroup parent, int childIndex) {
      return parent.getChildAt(childIndex) == this.mOverflowButton ? false : super.filterLeftoverView(parent, childIndex);
   }

   public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
      if (!subMenu.hasVisibleItems()) {
         return false;
      } else {
         SubMenuBuilder topSubMenu;
         for(topSubMenu = subMenu; topSubMenu.getParentMenu() != this.mMenu; topSubMenu = (SubMenuBuilder)topSubMenu.getParentMenu()) {
            ;
         }

         View anchor = this.findViewForItem(topSubMenu.getItem());
         if (anchor == null) {
            return false;
         } else {
            this.mOpenSubMenuId = subMenu.getItem().getItemId();
            boolean preserveIconSpacing = false;
            int count = subMenu.size();

            for(int i = 0; i < count; ++i) {
               MenuItem childItem = subMenu.getItem(i);
               if (childItem.isVisible() && childItem.getIcon() != null) {
                  preserveIconSpacing = true;
                  break;
               }
            }

            this.mActionButtonPopup = new ActionMenuPresenter.ActionButtonSubmenu(this.mContext, subMenu, anchor);
            this.mActionButtonPopup.setForceShowIcon(preserveIconSpacing);
            this.mActionButtonPopup.show();
            super.onSubMenuSelected(subMenu);
            return true;
         }
      }
   }

   private View findViewForItem(MenuItem item) {
      ViewGroup parent = (ViewGroup)this.mMenuView;
      if (parent == null) {
         return null;
      } else {
         int count = parent.getChildCount();

         for(int i = 0; i < count; ++i) {
            View child = parent.getChildAt(i);
            if (child instanceof MenuView.ItemView && ((MenuView.ItemView)child).getItemData() == item) {
               return child;
            }
         }

         return null;
      }
   }

   public boolean showOverflowMenu() {
      if (this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
         ActionMenuPresenter.OverflowPopup popup = new ActionMenuPresenter.OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true);
         this.mPostedOpenRunnable = new ActionMenuPresenter.OpenOverflowRunnable(popup);
         ((View)this.mMenuView).post(this.mPostedOpenRunnable);
         super.onSubMenuSelected((SubMenuBuilder)null);
         return true;
      } else {
         return false;
      }
   }

   public boolean hideOverflowMenu() {
      if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
         ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
         this.mPostedOpenRunnable = null;
         return true;
      } else {
         MenuPopupHelper popup = this.mOverflowPopup;
         if (popup != null) {
            popup.dismiss();
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean dismissPopupMenus() {
      boolean result = this.hideOverflowMenu();
      result |= this.hideSubMenus();
      return result;
   }

   public boolean hideSubMenus() {
      if (this.mActionButtonPopup != null) {
         this.mActionButtonPopup.dismiss();
         return true;
      } else {
         return false;
      }
   }

   public boolean isOverflowMenuShowing() {
      return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
   }

   public boolean isOverflowMenuShowPending() {
      return this.mPostedOpenRunnable != null || this.isOverflowMenuShowing();
   }

   public boolean isOverflowReserved() {
      return this.mReserveOverflow;
   }

   public boolean flagActionItems() {
      ArrayList visibleItems;
      int itemsSize;
      if (this.mMenu != null) {
         visibleItems = this.mMenu.getVisibleItems();
         itemsSize = visibleItems.size();
      } else {
         visibleItems = null;
         itemsSize = 0;
      }

      int maxActions = this.mMaxItems;
      int widthLimit = this.mActionItemWidthLimit;
      int querySpec = MeasureSpec.makeMeasureSpec(0, 0);
      ViewGroup parent = (ViewGroup)this.mMenuView;
      int requiredItems = 0;
      int requestedItems = 0;
      int firstActionWidth = 0;
      boolean hasOverflow = false;

      for(int i = 0; i < itemsSize; ++i) {
         MenuItemImpl item = (MenuItemImpl)visibleItems.get(i);
         if (item.requiresActionButton()) {
            ++requiredItems;
         } else if (item.requestsActionButton()) {
            ++requestedItems;
         } else {
            hasOverflow = true;
         }

         if (this.mExpandedActionViewsExclusive && item.isActionViewExpanded()) {
            maxActions = 0;
         }
      }

      if (this.mReserveOverflow && (hasOverflow || requiredItems + requestedItems > maxActions)) {
         --maxActions;
      }

      maxActions -= requiredItems;
      SparseBooleanArray seenGroups = this.mActionButtonGroups;
      seenGroups.clear();
      int cellSize = 0;
      int cellsRemaining = 0;
      int i;
      if (this.mStrictWidthLimit) {
         cellsRemaining = widthLimit / this.mMinCellSize;
         i = widthLimit % this.mMinCellSize;
         cellSize = this.mMinCellSize + i / cellsRemaining;
      }

      for(i = 0; i < itemsSize; ++i) {
         MenuItemImpl item = (MenuItemImpl)visibleItems.get(i);
         if (item.requiresActionButton()) {
            View v = this.getItemView(item, this.mScrapActionButtonView, parent);
            if (this.mScrapActionButtonView == null) {
               this.mScrapActionButtonView = v;
            }

            if (this.mStrictWidthLimit) {
               cellsRemaining -= ActionMenuView.measureChildForCells(v, cellSize, cellsRemaining, querySpec, 0);
            } else {
               v.measure(querySpec, querySpec);
            }

            int measuredWidth = v.getMeasuredWidth();
            widthLimit -= measuredWidth;
            if (firstActionWidth == 0) {
               firstActionWidth = measuredWidth;
            }

            int groupId = item.getGroupId();
            if (groupId != 0) {
               seenGroups.put(groupId, true);
            }

            item.setIsActionButton(true);
         } else if (!item.requestsActionButton()) {
            item.setIsActionButton(false);
         } else {
            int groupId = item.getGroupId();
            boolean inGroup = seenGroups.get(groupId);
            boolean isAction = (maxActions > 0 || inGroup) && widthLimit > 0 && (!this.mStrictWidthLimit || cellsRemaining > 0);
            if (isAction) {
               View v = this.getItemView(item, this.mScrapActionButtonView, parent);
               if (this.mScrapActionButtonView == null) {
                  this.mScrapActionButtonView = v;
               }

               int measuredWidth;
               if (this.mStrictWidthLimit) {
                  measuredWidth = ActionMenuView.measureChildForCells(v, cellSize, cellsRemaining, querySpec, 0);
                  cellsRemaining -= measuredWidth;
                  if (measuredWidth == 0) {
                     isAction = false;
                  }
               } else {
                  v.measure(querySpec, querySpec);
               }

               measuredWidth = v.getMeasuredWidth();
               widthLimit -= measuredWidth;
               if (firstActionWidth == 0) {
                  firstActionWidth = measuredWidth;
               }

               if (this.mStrictWidthLimit) {
                  isAction &= widthLimit >= 0;
               } else {
                  isAction &= widthLimit + firstActionWidth > 0;
               }
            }

            if (isAction && groupId != 0) {
               seenGroups.put(groupId, true);
            } else if (inGroup) {
               seenGroups.put(groupId, false);

               for(int j = 0; j < i; ++j) {
                  MenuItemImpl areYouMyGroupie = (MenuItemImpl)visibleItems.get(j);
                  if (areYouMyGroupie.getGroupId() == groupId) {
                     if (areYouMyGroupie.isActionButton()) {
                        ++maxActions;
                     }

                     areYouMyGroupie.setIsActionButton(false);
                  }
               }
            }

            if (isAction) {
               --maxActions;
            }

            item.setIsActionButton(isAction);
         }
      }

      return true;
   }

   public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      this.dismissPopupMenus();
      super.onCloseMenu(menu, allMenusAreClosing);
   }

   public Parcelable onSaveInstanceState() {
      ActionMenuPresenter.SavedState state = new ActionMenuPresenter.SavedState();
      state.openSubMenuId = this.mOpenSubMenuId;
      return state;
   }

   public void onRestoreInstanceState(Parcelable state) {
      if (state instanceof ActionMenuPresenter.SavedState) {
         ActionMenuPresenter.SavedState saved = (ActionMenuPresenter.SavedState)state;
         if (saved.openSubMenuId > 0) {
            MenuItem item = this.mMenu.findItem(saved.openSubMenuId);
            if (item != null) {
               SubMenuBuilder subMenu = (SubMenuBuilder)item.getSubMenu();
               this.onSubMenuSelected(subMenu);
            }
         }

      }
   }

   public void onSubUiVisibilityChanged(boolean isVisible) {
      if (isVisible) {
         super.onSubMenuSelected((SubMenuBuilder)null);
      } else if (this.mMenu != null) {
         this.mMenu.close(false);
      }

   }

   public void setMenuView(ActionMenuView menuView) {
      this.mMenuView = menuView;
      menuView.initialize(this.mMenu);
   }

   private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
      public ShowableListMenu getPopup() {
         return ActionMenuPresenter.this.mActionButtonPopup != null ? ActionMenuPresenter.this.mActionButtonPopup.getPopup() : null;
      }
   }

   private class OpenOverflowRunnable implements Runnable {
      private ActionMenuPresenter.OverflowPopup mPopup;

      public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup popup) {
         this.mPopup = popup;
      }

      public void run() {
         if (ActionMenuPresenter.this.mMenu != null) {
            ActionMenuPresenter.this.mMenu.changeMenuMode();
         }

         View menuView = (View)ActionMenuPresenter.this.mMenuView;
         if (menuView != null && menuView.getWindowToken() != null && this.mPopup.tryShow()) {
            ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
         }

         ActionMenuPresenter.this.mPostedOpenRunnable = null;
      }
   }

   private class PopupPresenterCallback implements MenuPresenter.Callback {
      public boolean onOpenSubMenu(MenuBuilder subMenu) {
         if (subMenu == null) {
            return false;
         } else {
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)subMenu).getItem().getItemId();
            MenuPresenter.Callback cb = ActionMenuPresenter.this.getCallback();
            return cb != null ? cb.onOpenSubMenu(subMenu) : false;
         }
      }

      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
         if (menu instanceof SubMenuBuilder) {
            menu.getRootMenu().close(false);
         }

         MenuPresenter.Callback cb = ActionMenuPresenter.this.getCallback();
         if (cb != null) {
            cb.onCloseMenu(menu, allMenusAreClosing);
         }

      }
   }

   private class ActionButtonSubmenu extends MenuPopupHelper {
      public ActionButtonSubmenu(Context context, SubMenuBuilder subMenu, View anchorView) {
         super(context, subMenu, anchorView, false, attr.actionOverflowMenuStyle);
         MenuItemImpl item = (MenuItemImpl)subMenu.getItem();
         if (!item.isActionButton()) {
            this.setAnchorView((View)(ActionMenuPresenter.this.mOverflowButton == null ? (View)ActionMenuPresenter.this.mMenuView : ActionMenuPresenter.this.mOverflowButton));
         }

         this.setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
      }

      protected void onDismiss() {
         ActionMenuPresenter.this.mActionButtonPopup = null;
         ActionMenuPresenter.this.mOpenSubMenuId = 0;
         super.onDismiss();
      }
   }

   private class OverflowPopup extends MenuPopupHelper {
      public OverflowPopup(Context context, MenuBuilder menu, View anchorView, boolean overflowOnly) {
         super(context, menu, anchorView, overflowOnly, attr.actionOverflowMenuStyle);
         this.setGravity(8388613);
         this.setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
      }

      protected void onDismiss() {
         if (ActionMenuPresenter.this.mMenu != null) {
            ActionMenuPresenter.this.mMenu.close();
         }

         ActionMenuPresenter.this.mOverflowPopup = null;
         super.onDismiss();
      }
   }

   private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
      private final float[] mTempPts = new float[2];

      public OverflowMenuButton(Context context) {
         super(context, (AttributeSet)null, attr.actionOverflowButtonStyle);
         this.setClickable(true);
         this.setFocusable(true);
         this.setVisibility(0);
         this.setEnabled(true);
         TooltipCompat.setTooltipText(this, this.getContentDescription());
         this.setOnTouchListener(new ForwardingListener(this) {
            public ShowableListMenu getPopup() {
               return ActionMenuPresenter.this.mOverflowPopup == null ? null : ActionMenuPresenter.this.mOverflowPopup.getPopup();
            }

            public boolean onForwardingStarted() {
               ActionMenuPresenter.this.showOverflowMenu();
               return true;
            }

            public boolean onForwardingStopped() {
               if (ActionMenuPresenter.this.mPostedOpenRunnable != null) {
                  return false;
               } else {
                  ActionMenuPresenter.this.hideOverflowMenu();
                  return true;
               }
            }
         });
      }

      public boolean performClick() {
         if (super.performClick()) {
            return true;
         } else {
            this.playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
         }
      }

      public boolean needsDividerBefore() {
         return false;
      }

      public boolean needsDividerAfter() {
         return false;
      }

      protected boolean setFrame(int l, int t, int r, int b) {
         boolean changed = super.setFrame(l, t, r, b);
         Drawable d = this.getDrawable();
         Drawable bg = this.getBackground();
         if (d != null && bg != null) {
            int width = this.getWidth();
            int height = this.getHeight();
            int halfEdge = Math.max(width, height) / 2;
            int offsetX = this.getPaddingLeft() - this.getPaddingRight();
            int offsetY = this.getPaddingTop() - this.getPaddingBottom();
            int centerX = (width + offsetX) / 2;
            int centerY = (height + offsetY) / 2;
            DrawableCompat.setHotspotBounds(bg, centerX - halfEdge, centerY - halfEdge, centerX + halfEdge, centerY + halfEdge);
         }

         return changed;
      }
   }

   private static class SavedState implements Parcelable {
      public int openSubMenuId;
      public static final Creator CREATOR = new Creator() {
         public ActionMenuPresenter.SavedState createFromParcel(Parcel in) {
            return new ActionMenuPresenter.SavedState(in);
         }

         public ActionMenuPresenter.SavedState[] newArray(int size) {
            return new ActionMenuPresenter.SavedState[size];
         }
      };

      SavedState() {
      }

      SavedState(Parcel in) {
         this.openSubMenuId = in.readInt();
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(this.openSubMenuId);
      }
   }
}
