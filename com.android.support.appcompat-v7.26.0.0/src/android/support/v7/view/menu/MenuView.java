package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface MenuView {
   void initialize(MenuBuilder var1);

   int getWindowAnimations();

   public interface ItemView {
      void initialize(MenuItemImpl var1, int var2);

      MenuItemImpl getItemData();

      void setTitle(CharSequence var1);

      void setEnabled(boolean var1);

      void setCheckable(boolean var1);

      void setChecked(boolean var1);

      void setShortcut(boolean var1, char var2);

      void setIcon(Drawable var1);

      boolean prefersCondensedTitle();

      boolean showsIcon();
   }
}
