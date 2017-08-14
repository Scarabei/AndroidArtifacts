package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface MenuItemHoverListener {
   void onItemHoverExit(@NonNull MenuBuilder var1, @NonNull MenuItem var2);

   void onItemHoverEnter(@NonNull MenuBuilder var1, @NonNull MenuItem var2);
}
