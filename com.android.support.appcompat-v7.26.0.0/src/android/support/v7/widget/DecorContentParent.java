package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.MenuPresenter;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window.Callback;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface DecorContentParent {
   void setWindowCallback(Callback var1);

   void setWindowTitle(CharSequence var1);

   CharSequence getTitle();

   void initFeature(int var1);

   void setUiOptions(int var1);

   boolean hasIcon();

   boolean hasLogo();

   void setIcon(int var1);

   void setIcon(Drawable var1);

   void setLogo(int var1);

   boolean canShowOverflowMenu();

   boolean isOverflowMenuShowing();

   boolean isOverflowMenuShowPending();

   boolean showOverflowMenu();

   boolean hideOverflowMenu();

   void setMenuPrepared();

   void setMenu(Menu var1, MenuPresenter.Callback var2);

   void saveToolbarHierarchyState(SparseArray var1);

   void restoreToolbarHierarchyState(SparseArray var1);

   void dismissPopups();
}
