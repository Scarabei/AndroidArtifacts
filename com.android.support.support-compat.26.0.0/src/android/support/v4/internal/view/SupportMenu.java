package android.support.v4.internal.view;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.Menu;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface SupportMenu extends Menu {
   int USER_MASK = 65535;
   int USER_SHIFT = 0;
   int CATEGORY_MASK = -65536;
   int CATEGORY_SHIFT = 16;
   int SUPPORTED_MODIFIERS_MASK = 69647;
   int FLAG_KEEP_OPEN_ON_SUBMENU_OPENED = 4;
}
