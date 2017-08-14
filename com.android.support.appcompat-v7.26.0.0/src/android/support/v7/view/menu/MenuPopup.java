package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

abstract class MenuPopup implements ShowableListMenu, MenuPresenter, OnItemClickListener {
   private Rect mEpicenterBounds;

   public abstract void setForceShowIcon(boolean var1);

   public abstract void addMenu(MenuBuilder var1);

   public abstract void setGravity(int var1);

   public abstract void setAnchorView(View var1);

   public abstract void setHorizontalOffset(int var1);

   public abstract void setVerticalOffset(int var1);

   public void setEpicenterBounds(Rect bounds) {
      this.mEpicenterBounds = bounds;
   }

   public Rect getEpicenterBounds() {
      return this.mEpicenterBounds;
   }

   public abstract void setShowTitle(boolean var1);

   public abstract void setOnDismissListener(OnDismissListener var1);

   public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menu) {
   }

   public MenuView getMenuView(ViewGroup root) {
      throw new UnsupportedOperationException("MenuPopups manage their own views");
   }

   public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
      return false;
   }

   public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
      return false;
   }

   public int getId() {
      return 0;
   }

   public void onItemClick(AdapterView parent, View view, int position, long id) {
      ListAdapter outerAdapter = (ListAdapter)parent.getAdapter();
      MenuAdapter wrappedAdapter = toMenuAdapter(outerAdapter);
      wrappedAdapter.mAdapterMenu.performItemAction((MenuItem)outerAdapter.getItem(position), this, this.closeMenuOnSubMenuOpened() ? 0 : 4);
   }

   protected static int measureIndividualMenuWidth(ListAdapter adapter, ViewGroup parent, Context context, int maxAllowedWidth) {
      int maxWidth = 0;
      View itemView = null;
      int itemType = 0;
      int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
      int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
      int count = adapter.getCount();

      for(int i = 0; i < count; ++i) {
         int positionType = adapter.getItemViewType(i);
         if (positionType != itemType) {
            itemType = positionType;
            itemView = null;
         }

         if (parent == null) {
            parent = new FrameLayout(context);
         }

         itemView = adapter.getView(i, itemView, (ViewGroup)parent);
         itemView.measure(widthMeasureSpec, heightMeasureSpec);
         int itemWidth = itemView.getMeasuredWidth();
         if (itemWidth >= maxAllowedWidth) {
            return maxAllowedWidth;
         }

         if (itemWidth > maxWidth) {
            maxWidth = itemWidth;
         }
      }

      return maxWidth;
   }

   protected static MenuAdapter toMenuAdapter(ListAdapter adapter) {
      return adapter instanceof HeaderViewListAdapter ? (MenuAdapter)((HeaderViewListAdapter)adapter).getWrappedAdapter() : (MenuAdapter)adapter;
   }

   protected static boolean shouldPreserveIconSpacing(MenuBuilder menu) {
      boolean preserveIconSpacing = false;
      int count = menu.size();

      for(int i = 0; i < count; ++i) {
         MenuItem childItem = menu.getItem(i);
         if (childItem.isVisible() && childItem.getIcon() != null) {
            preserveIconSpacing = true;
            break;
         }
      }

      return preserveIconSpacing;
   }

   protected boolean closeMenuOnSubMenuOpened() {
      return true;
   }
}
