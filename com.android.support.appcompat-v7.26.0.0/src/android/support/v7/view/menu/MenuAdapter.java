package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MenuAdapter extends BaseAdapter {
   static final int ITEM_LAYOUT;
   MenuBuilder mAdapterMenu;
   private int mExpandedIndex = -1;
   private boolean mForceShowIcon;
   private final boolean mOverflowOnly;
   private final LayoutInflater mInflater;

   public MenuAdapter(MenuBuilder menu, LayoutInflater inflater, boolean overflowOnly) {
      this.mOverflowOnly = overflowOnly;
      this.mInflater = inflater;
      this.mAdapterMenu = menu;
      this.findExpandedIndex();
   }

   public boolean getForceShowIcon() {
      return this.mForceShowIcon;
   }

   public void setForceShowIcon(boolean forceShow) {
      this.mForceShowIcon = forceShow;
   }

   public int getCount() {
      ArrayList items = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
      return this.mExpandedIndex < 0 ? items.size() : items.size() - 1;
   }

   public MenuBuilder getAdapterMenu() {
      return this.mAdapterMenu;
   }

   public MenuItemImpl getItem(int position) {
      ArrayList items = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
      if (this.mExpandedIndex >= 0 && position >= this.mExpandedIndex) {
         ++position;
      }

      return (MenuItemImpl)items.get(position);
   }

   public long getItemId(int position) {
      return (long)position;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
         convertView = this.mInflater.inflate(ITEM_LAYOUT, parent, false);
      }

      MenuView.ItemView itemView = (MenuView.ItemView)convertView;
      if (this.mForceShowIcon) {
         ((ListMenuItemView)convertView).setForceShowIcon(true);
      }

      itemView.initialize(this.getItem(position), 0);
      return convertView;
   }

   void findExpandedIndex() {
      MenuItemImpl expandedItem = this.mAdapterMenu.getExpandedItem();
      if (expandedItem != null) {
         ArrayList items = this.mAdapterMenu.getNonActionItems();
         int count = items.size();

         for(int i = 0; i < count; ++i) {
            MenuItemImpl item = (MenuItemImpl)items.get(i);
            if (item == expandedItem) {
               this.mExpandedIndex = i;
               return;
            }
         }
      }

      this.mExpandedIndex = -1;
   }

   public void notifyDataSetChanged() {
      this.findExpandedIndex();
      super.notifyDataSetChanged();
   }

   static {
      ITEM_LAYOUT = layout.abc_popup_menu_item_layout;
   }
}
