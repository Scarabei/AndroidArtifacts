package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

abstract class BaseMenuWrapper extends BaseWrapper {
   final Context mContext;
   private Map mMenuItems;
   private Map mSubMenus;

   BaseMenuWrapper(Context context, Object object) {
      super(object);
      this.mContext = context;
   }

   final MenuItem getMenuItemWrapper(MenuItem menuItem) {
      if (menuItem instanceof SupportMenuItem) {
         SupportMenuItem supportMenuItem = (SupportMenuItem)menuItem;
         if (this.mMenuItems == null) {
            this.mMenuItems = new ArrayMap();
         }

         MenuItem wrappedItem = (MenuItem)this.mMenuItems.get(menuItem);
         if (null == wrappedItem) {
            wrappedItem = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, supportMenuItem);
            this.mMenuItems.put(supportMenuItem, wrappedItem);
         }

         return wrappedItem;
      } else {
         return menuItem;
      }
   }

   final SubMenu getSubMenuWrapper(SubMenu subMenu) {
      if (subMenu instanceof SupportSubMenu) {
         SupportSubMenu supportSubMenu = (SupportSubMenu)subMenu;
         if (this.mSubMenus == null) {
            this.mSubMenus = new ArrayMap();
         }

         SubMenu wrappedMenu = (SubMenu)this.mSubMenus.get(supportSubMenu);
         if (null == wrappedMenu) {
            wrappedMenu = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, supportSubMenu);
            this.mSubMenus.put(supportSubMenu, wrappedMenu);
         }

         return wrappedMenu;
      } else {
         return subMenu;
      }
   }

   final void internalClear() {
      if (this.mMenuItems != null) {
         this.mMenuItems.clear();
      }

      if (this.mSubMenus != null) {
         this.mSubMenus.clear();
      }

   }

   final void internalRemoveGroup(int groupId) {
      if (this.mMenuItems != null) {
         Iterator iterator = this.mMenuItems.keySet().iterator();

         while(iterator.hasNext()) {
            MenuItem menuItem = (MenuItem)iterator.next();
            if (groupId == menuItem.getGroupId()) {
               iterator.remove();
            }
         }

      }
   }

   final void internalRemoveItem(int id) {
      if (this.mMenuItems != null) {
         Iterator iterator = this.mMenuItems.keySet().iterator();

         while(iterator.hasNext()) {
            MenuItem menuItem = (MenuItem)iterator.next();
            if (id == menuItem.getItemId()) {
               iterator.remove();
               break;
            }
         }

      }
   }
}
