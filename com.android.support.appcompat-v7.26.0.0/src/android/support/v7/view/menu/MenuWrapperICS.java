package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RequiresApi(14)
class MenuWrapperICS extends BaseMenuWrapper implements Menu {
   MenuWrapperICS(Context context, SupportMenu object) {
      super(context, object);
   }

   public MenuItem add(CharSequence title) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(title));
   }

   public MenuItem add(int titleRes) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(titleRes));
   }

   public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(groupId, itemId, order, title));
   }

   public MenuItem add(int groupId, int itemId, int order, int titleRes) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(groupId, itemId, order, titleRes));
   }

   public SubMenu addSubMenu(CharSequence title) {
      return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(title));
   }

   public SubMenu addSubMenu(int titleRes) {
      return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(titleRes));
   }

   public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
      return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(groupId, itemId, order, title));
   }

   public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
      return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(groupId, itemId, order, titleRes));
   }

   public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
      MenuItem[] items = null;
      if (outSpecificItems != null) {
         items = new MenuItem[outSpecificItems.length];
      }

      int result = ((SupportMenu)this.mWrappedObject).addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, items);
      if (items != null) {
         int i = 0;

         for(int z = items.length; i < z; ++i) {
            outSpecificItems[i] = this.getMenuItemWrapper(items[i]);
         }
      }

      return result;
   }

   public void removeItem(int id) {
      this.internalRemoveItem(id);
      ((SupportMenu)this.mWrappedObject).removeItem(id);
   }

   public void removeGroup(int groupId) {
      this.internalRemoveGroup(groupId);
      ((SupportMenu)this.mWrappedObject).removeGroup(groupId);
   }

   public void clear() {
      this.internalClear();
      ((SupportMenu)this.mWrappedObject).clear();
   }

   public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
      ((SupportMenu)this.mWrappedObject).setGroupCheckable(group, checkable, exclusive);
   }

   public void setGroupVisible(int group, boolean visible) {
      ((SupportMenu)this.mWrappedObject).setGroupVisible(group, visible);
   }

   public void setGroupEnabled(int group, boolean enabled) {
      ((SupportMenu)this.mWrappedObject).setGroupEnabled(group, enabled);
   }

   public boolean hasVisibleItems() {
      return ((SupportMenu)this.mWrappedObject).hasVisibleItems();
   }

   public MenuItem findItem(int id) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).findItem(id));
   }

   public int size() {
      return ((SupportMenu)this.mWrappedObject).size();
   }

   public MenuItem getItem(int index) {
      return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).getItem(index));
   }

   public void close() {
      ((SupportMenu)this.mWrappedObject).close();
   }

   public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
      return ((SupportMenu)this.mWrappedObject).performShortcut(keyCode, event, flags);
   }

   public boolean isShortcutKey(int keyCode, KeyEvent event) {
      return ((SupportMenu)this.mWrappedObject).isShortcutKey(keyCode, event);
   }

   public boolean performIdentifierAction(int id, int flags) {
      return ((SupportMenu)this.mWrappedObject).performIdentifierAction(id, flags);
   }

   public void setQwertyMode(boolean isQwerty) {
      ((SupportMenu)this.mWrappedObject).setQwertyMode(isQwerty);
   }
}
