package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.layout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class NavigationMenuPresenter implements MenuPresenter {
   private static final String STATE_HIERARCHY = "android:menu:list";
   private static final String STATE_ADAPTER = "android:menu:adapter";
   private static final String STATE_HEADER = "android:menu:header";
   private NavigationMenuView mMenuView;
   LinearLayout mHeaderLayout;
   private Callback mCallback;
   MenuBuilder mMenu;
   private int mId;
   NavigationMenuPresenter.NavigationMenuAdapter mAdapter;
   LayoutInflater mLayoutInflater;
   int mTextAppearance;
   boolean mTextAppearanceSet;
   ColorStateList mTextColor;
   ColorStateList mIconTintList;
   Drawable mItemBackground;
   private int mPaddingTopDefault;
   int mPaddingSeparator;
   final OnClickListener mOnClickListener = new OnClickListener() {
      public void onClick(View v) {
         NavigationMenuItemView itemView = (NavigationMenuItemView)v;
         NavigationMenuPresenter.this.setUpdateSuspended(true);
         MenuItemImpl item = itemView.getItemData();
         boolean result = NavigationMenuPresenter.this.mMenu.performItemAction(item, NavigationMenuPresenter.this, 0);
         if (item != null && item.isCheckable() && result) {
            NavigationMenuPresenter.this.mAdapter.setCheckedItem(item);
         }

         NavigationMenuPresenter.this.setUpdateSuspended(false);
         NavigationMenuPresenter.this.updateMenuView(false);
      }
   };

   public void initForMenu(Context context, MenuBuilder menu) {
      this.mLayoutInflater = LayoutInflater.from(context);
      this.mMenu = menu;
      Resources res = context.getResources();
      this.mPaddingSeparator = res.getDimensionPixelOffset(dimen.design_navigation_separator_vertical_padding);
   }

   public MenuView getMenuView(ViewGroup root) {
      if (this.mMenuView == null) {
         this.mMenuView = (NavigationMenuView)this.mLayoutInflater.inflate(layout.design_navigation_menu, root, false);
         if (this.mAdapter == null) {
            this.mAdapter = new NavigationMenuPresenter.NavigationMenuAdapter();
         }

         this.mHeaderLayout = (LinearLayout)this.mLayoutInflater.inflate(layout.design_navigation_item_header, this.mMenuView, false);
         this.mMenuView.setAdapter(this.mAdapter);
      }

      return this.mMenuView;
   }

   public void updateMenuView(boolean cleared) {
      if (this.mAdapter != null) {
         this.mAdapter.update();
      }

   }

   public void setCallback(Callback cb) {
      this.mCallback = cb;
   }

   public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
      return false;
   }

   public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      if (this.mCallback != null) {
         this.mCallback.onCloseMenu(menu, allMenusAreClosing);
      }

   }

   public boolean flagActionItems() {
      return false;
   }

   public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
      return false;
   }

   public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
      return false;
   }

   public int getId() {
      return this.mId;
   }

   public void setId(int id) {
      this.mId = id;
   }

   public Parcelable onSaveInstanceState() {
      if (VERSION.SDK_INT >= 11) {
         Bundle state = new Bundle();
         SparseArray header;
         if (this.mMenuView != null) {
            header = new SparseArray();
            this.mMenuView.saveHierarchyState(header);
            state.putSparseParcelableArray("android:menu:list", header);
         }

         if (this.mAdapter != null) {
            state.putBundle("android:menu:adapter", this.mAdapter.createInstanceState());
         }

         if (this.mHeaderLayout != null) {
            header = new SparseArray();
            this.mHeaderLayout.saveHierarchyState(header);
            state.putSparseParcelableArray("android:menu:header", header);
         }

         return state;
      } else {
         return null;
      }
   }

   public void onRestoreInstanceState(Parcelable parcelable) {
      if (parcelable instanceof Bundle) {
         Bundle state = (Bundle)parcelable;
         SparseArray hierarchy = state.getSparseParcelableArray("android:menu:list");
         if (hierarchy != null) {
            this.mMenuView.restoreHierarchyState(hierarchy);
         }

         Bundle adapterState = state.getBundle("android:menu:adapter");
         if (adapterState != null) {
            this.mAdapter.restoreInstanceState(adapterState);
         }

         SparseArray header = state.getSparseParcelableArray("android:menu:header");
         if (header != null) {
            this.mHeaderLayout.restoreHierarchyState(header);
         }
      }

   }

   public void setCheckedItem(MenuItemImpl item) {
      this.mAdapter.setCheckedItem(item);
   }

   public View inflateHeaderView(@LayoutRes int res) {
      View view = this.mLayoutInflater.inflate(res, this.mHeaderLayout, false);
      this.addHeaderView(view);
      return view;
   }

   public void addHeaderView(@NonNull View view) {
      this.mHeaderLayout.addView(view);
      this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
   }

   public void removeHeaderView(@NonNull View view) {
      this.mHeaderLayout.removeView(view);
      if (this.mHeaderLayout.getChildCount() == 0) {
         this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
      }

   }

   public int getHeaderCount() {
      return this.mHeaderLayout.getChildCount();
   }

   public View getHeaderView(int index) {
      return this.mHeaderLayout.getChildAt(index);
   }

   @Nullable
   public ColorStateList getItemTintList() {
      return this.mIconTintList;
   }

   public void setItemIconTintList(@Nullable ColorStateList tint) {
      this.mIconTintList = tint;
      this.updateMenuView(false);
   }

   @Nullable
   public ColorStateList getItemTextColor() {
      return this.mTextColor;
   }

   public void setItemTextColor(@Nullable ColorStateList textColor) {
      this.mTextColor = textColor;
      this.updateMenuView(false);
   }

   public void setItemTextAppearance(@StyleRes int resId) {
      this.mTextAppearance = resId;
      this.mTextAppearanceSet = true;
      this.updateMenuView(false);
   }

   @Nullable
   public Drawable getItemBackground() {
      return this.mItemBackground;
   }

   public void setItemBackground(@Nullable Drawable itemBackground) {
      this.mItemBackground = itemBackground;
      this.updateMenuView(false);
   }

   public void setUpdateSuspended(boolean updateSuspended) {
      if (this.mAdapter != null) {
         this.mAdapter.setUpdateSuspended(updateSuspended);
      }

   }

   public void dispatchApplyWindowInsets(WindowInsetsCompat insets) {
      int top = insets.getSystemWindowInsetTop();
      if (this.mPaddingTopDefault != top) {
         this.mPaddingTopDefault = top;
         if (this.mHeaderLayout.getChildCount() == 0) {
            this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
         }
      }

      ViewCompat.dispatchApplyWindowInsets(this.mHeaderLayout, insets);
   }

   private static class NavigationMenuHeaderItem implements NavigationMenuPresenter.NavigationMenuItem {
   }

   private static class NavigationMenuSeparatorItem implements NavigationMenuPresenter.NavigationMenuItem {
      private final int mPaddingTop;
      private final int mPaddingBottom;

      public NavigationMenuSeparatorItem(int paddingTop, int paddingBottom) {
         this.mPaddingTop = paddingTop;
         this.mPaddingBottom = paddingBottom;
      }

      public int getPaddingTop() {
         return this.mPaddingTop;
      }

      public int getPaddingBottom() {
         return this.mPaddingBottom;
      }
   }

   private static class NavigationMenuTextItem implements NavigationMenuPresenter.NavigationMenuItem {
      private final MenuItemImpl mMenuItem;
      boolean needsEmptyIcon;

      NavigationMenuTextItem(MenuItemImpl item) {
         this.mMenuItem = item;
      }

      public MenuItemImpl getMenuItem() {
         return this.mMenuItem;
      }
   }

   private interface NavigationMenuItem {
   }

   private class NavigationMenuAdapter extends Adapter {
      private static final String STATE_CHECKED_ITEM = "android:menu:checked";
      private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
      private static final int VIEW_TYPE_NORMAL = 0;
      private static final int VIEW_TYPE_SUBHEADER = 1;
      private static final int VIEW_TYPE_SEPARATOR = 2;
      private static final int VIEW_TYPE_HEADER = 3;
      private final ArrayList mItems = new ArrayList();
      private MenuItemImpl mCheckedItem;
      private boolean mUpdateSuspended;

      NavigationMenuAdapter() {
         this.prepareMenuItems();
      }

      public long getItemId(int position) {
         return (long)position;
      }

      public int getItemCount() {
         return this.mItems.size();
      }

      public int getItemViewType(int position) {
         NavigationMenuPresenter.NavigationMenuItem item = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(position);
         if (item instanceof NavigationMenuPresenter.NavigationMenuSeparatorItem) {
            return 2;
         } else if (item instanceof NavigationMenuPresenter.NavigationMenuHeaderItem) {
            return 3;
         } else if (item instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
            NavigationMenuPresenter.NavigationMenuTextItem textItem = (NavigationMenuPresenter.NavigationMenuTextItem)item;
            return textItem.getMenuItem().hasSubMenu() ? 1 : 0;
         } else {
            throw new RuntimeException("Unknown item type.");
         }
      }

      public NavigationMenuPresenter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         switch(viewType) {
         case 0:
            return new NavigationMenuPresenter.NormalViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent, NavigationMenuPresenter.this.mOnClickListener);
         case 1:
            return new NavigationMenuPresenter.SubheaderViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent);
         case 2:
            return new NavigationMenuPresenter.SeparatorViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent);
         case 3:
            return new NavigationMenuPresenter.HeaderViewHolder(NavigationMenuPresenter.this.mHeaderLayout);
         default:
            return null;
         }
      }

      public void onBindViewHolder(NavigationMenuPresenter.ViewHolder holder, int position) {
         NavigationMenuPresenter.NavigationMenuTextItem itemx;
         switch(this.getItemViewType(position)) {
         case 0:
            NavigationMenuItemView itemView = (NavigationMenuItemView)holder.itemView;
            itemView.setIconTintList(NavigationMenuPresenter.this.mIconTintList);
            if (NavigationMenuPresenter.this.mTextAppearanceSet) {
               itemView.setTextAppearance(NavigationMenuPresenter.this.mTextAppearance);
            }

            if (NavigationMenuPresenter.this.mTextColor != null) {
               itemView.setTextColor(NavigationMenuPresenter.this.mTextColor);
            }

            ViewCompat.setBackground(itemView, NavigationMenuPresenter.this.mItemBackground != null ? NavigationMenuPresenter.this.mItemBackground.getConstantState().newDrawable() : null);
            itemx = (NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(position);
            itemView.setNeedsEmptyIcon(itemx.needsEmptyIcon);
            itemView.initialize(itemx.getMenuItem(), 0);
            break;
         case 1:
            TextView subHeader = (TextView)holder.itemView;
            itemx = (NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(position);
            subHeader.setText(itemx.getMenuItem().getTitle());
            break;
         case 2:
            NavigationMenuPresenter.NavigationMenuSeparatorItem item = (NavigationMenuPresenter.NavigationMenuSeparatorItem)this.mItems.get(position);
            holder.itemView.setPadding(0, item.getPaddingTop(), 0, item.getPaddingBottom());
         case 3:
         }

      }

      public void onViewRecycled(NavigationMenuPresenter.ViewHolder holder) {
         if (holder instanceof NavigationMenuPresenter.NormalViewHolder) {
            ((NavigationMenuItemView)holder.itemView).recycle();
         }

      }

      public void update() {
         this.prepareMenuItems();
         this.notifyDataSetChanged();
      }

      private void prepareMenuItems() {
         if (!this.mUpdateSuspended) {
            this.mUpdateSuspended = true;
            this.mItems.clear();
            this.mItems.add(new NavigationMenuPresenter.NavigationMenuHeaderItem());
            int currentGroupId = -1;
            int currentGroupStart = 0;
            boolean currentGroupHasIcon = false;
            int i = 0;

            for(int totalSize = NavigationMenuPresenter.this.mMenu.getVisibleItems().size(); i < totalSize; ++i) {
               MenuItemImpl item = (MenuItemImpl)NavigationMenuPresenter.this.mMenu.getVisibleItems().get(i);
               if (item.isChecked()) {
                  this.setCheckedItem(item);
               }

               if (item.isCheckable()) {
                  item.setExclusiveCheckable(false);
               }

               if (!item.hasSubMenu()) {
                  int groupId = item.getGroupId();
                  if (groupId != currentGroupId) {
                     currentGroupStart = this.mItems.size();
                     currentGroupHasIcon = item.getIcon() != null;
                     if (i != 0) {
                        ++currentGroupStart;
                        this.mItems.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, NavigationMenuPresenter.this.mPaddingSeparator));
                     }
                  } else if (!currentGroupHasIcon && item.getIcon() != null) {
                     currentGroupHasIcon = true;
                     this.appendTransparentIconIfMissing(currentGroupStart, this.mItems.size());
                  }

                  NavigationMenuPresenter.NavigationMenuTextItem textItem = new NavigationMenuPresenter.NavigationMenuTextItem(item);
                  textItem.needsEmptyIcon = currentGroupHasIcon;
                  this.mItems.add(textItem);
                  currentGroupId = groupId;
               } else {
                  SubMenu subMenu = item.getSubMenu();
                  if (subMenu.hasVisibleItems()) {
                     if (i != 0) {
                        this.mItems.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, 0));
                     }

                     this.mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem(item));
                     boolean subMenuHasIcon = false;
                     int subMenuStart = this.mItems.size();
                     int j = 0;

                     for(int size = subMenu.size(); j < size; ++j) {
                        MenuItemImpl subMenuItem = (MenuItemImpl)subMenu.getItem(j);
                        if (subMenuItem.isVisible()) {
                           if (!subMenuHasIcon && subMenuItem.getIcon() != null) {
                              subMenuHasIcon = true;
                           }

                           if (subMenuItem.isCheckable()) {
                              subMenuItem.setExclusiveCheckable(false);
                           }

                           if (item.isChecked()) {
                              this.setCheckedItem(item);
                           }

                           this.mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem(subMenuItem));
                        }
                     }

                     if (subMenuHasIcon) {
                        this.appendTransparentIconIfMissing(subMenuStart, this.mItems.size());
                     }
                  }
               }
            }

            this.mUpdateSuspended = false;
         }
      }

      private void appendTransparentIconIfMissing(int startIndex, int endIndex) {
         for(int i = startIndex; i < endIndex; ++i) {
            NavigationMenuPresenter.NavigationMenuTextItem textItem = (NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(i);
            textItem.needsEmptyIcon = true;
         }

      }

      public void setCheckedItem(MenuItemImpl checkedItem) {
         if (this.mCheckedItem != checkedItem && checkedItem.isCheckable()) {
            if (this.mCheckedItem != null) {
               this.mCheckedItem.setChecked(false);
            }

            this.mCheckedItem = checkedItem;
            checkedItem.setChecked(true);
         }
      }

      public Bundle createInstanceState() {
         Bundle state = new Bundle();
         if (this.mCheckedItem != null) {
            state.putInt("android:menu:checked", this.mCheckedItem.getItemId());
         }

         SparseArray actionViewStates = new SparseArray();
         int i = 0;

         for(int size = this.mItems.size(); i < size; ++i) {
            NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(i);
            if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
               MenuItemImpl item = ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem();
               View actionView = item != null ? item.getActionView() : null;
               if (actionView != null) {
                  ParcelableSparseArray container = new ParcelableSparseArray();
                  actionView.saveHierarchyState(container);
                  actionViewStates.put(item.getItemId(), container);
               }
            }
         }

         state.putSparseParcelableArray("android:menu:action_views", actionViewStates);
         return state;
      }

      public void restoreInstanceState(Bundle state) {
         int checkedItem = state.getInt("android:menu:checked", 0);
         int ix;
         if (checkedItem != 0) {
            this.mUpdateSuspended = true;
            int i = 0;

            for(ix = this.mItems.size(); i < ix; ++i) {
               NavigationMenuPresenter.NavigationMenuItem item = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(i);
               if (item instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
                  MenuItemImpl menuItem = ((NavigationMenuPresenter.NavigationMenuTextItem)item).getMenuItem();
                  if (menuItem != null && menuItem.getItemId() == checkedItem) {
                     this.setCheckedItem(menuItem);
                     break;
                  }
               }
            }

            this.mUpdateSuspended = false;
            this.prepareMenuItems();
         }

         SparseArray actionViewStates = state.getSparseParcelableArray("android:menu:action_views");
         if (actionViewStates != null) {
            ix = 0;

            for(int size = this.mItems.size(); ix < size; ++ix) {
               NavigationMenuPresenter.NavigationMenuItem navigationMenuItem = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(ix);
               if (navigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem) {
                  MenuItemImpl itemx = ((NavigationMenuPresenter.NavigationMenuTextItem)navigationMenuItem).getMenuItem();
                  if (itemx != null) {
                     View actionView = itemx.getActionView();
                     if (actionView != null) {
                        ParcelableSparseArray container = (ParcelableSparseArray)actionViewStates.get(itemx.getItemId());
                        if (container != null) {
                           actionView.restoreHierarchyState(container);
                        }
                     }
                  }
               }
            }
         }

      }

      public void setUpdateSuspended(boolean updateSuspended) {
         this.mUpdateSuspended = updateSuspended;
      }
   }

   private static class HeaderViewHolder extends NavigationMenuPresenter.ViewHolder {
      public HeaderViewHolder(View itemView) {
         super(itemView);
      }
   }

   private static class SeparatorViewHolder extends NavigationMenuPresenter.ViewHolder {
      public SeparatorViewHolder(LayoutInflater inflater, ViewGroup parent) {
         super(inflater.inflate(layout.design_navigation_item_separator, parent, false));
      }
   }

   private static class SubheaderViewHolder extends NavigationMenuPresenter.ViewHolder {
      public SubheaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
         super(inflater.inflate(layout.design_navigation_item_subheader, parent, false));
      }
   }

   private static class NormalViewHolder extends NavigationMenuPresenter.ViewHolder {
      public NormalViewHolder(LayoutInflater inflater, ViewGroup parent, OnClickListener listener) {
         super(inflater.inflate(layout.design_navigation_item, parent, false));
         this.itemView.setOnClickListener(listener);
      }
   }

   private abstract static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
      public ViewHolder(View itemView) {
         super(itemView);
      }
   }
}
