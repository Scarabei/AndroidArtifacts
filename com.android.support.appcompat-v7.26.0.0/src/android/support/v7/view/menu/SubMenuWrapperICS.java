package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(14)
class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
   SubMenuWrapperICS(Context context, SupportSubMenu subMenu) {
      super(context, subMenu);
   }

   public SupportSubMenu getWrappedObject() {
      return (SupportSubMenu)this.mWrappedObject;
   }

   public SubMenu setHeaderTitle(int titleRes) {
      this.getWrappedObject().setHeaderTitle(titleRes);
      return this;
   }

   public SubMenu setHeaderTitle(CharSequence title) {
      this.getWrappedObject().setHeaderTitle(title);
      return this;
   }

   public SubMenu setHeaderIcon(int iconRes) {
      this.getWrappedObject().setHeaderIcon(iconRes);
      return this;
   }

   public SubMenu setHeaderIcon(Drawable icon) {
      this.getWrappedObject().setHeaderIcon(icon);
      return this;
   }

   public SubMenu setHeaderView(View view) {
      this.getWrappedObject().setHeaderView(view);
      return this;
   }

   public void clearHeader() {
      this.getWrappedObject().clearHeader();
   }

   public SubMenu setIcon(int iconRes) {
      this.getWrappedObject().setIcon(iconRes);
      return this;
   }

   public SubMenu setIcon(Drawable icon) {
      this.getWrappedObject().setIcon(icon);
      return this;
   }

   public MenuItem getItem() {
      return this.getMenuItemWrapper(this.getWrappedObject().getItem());
   }
}
