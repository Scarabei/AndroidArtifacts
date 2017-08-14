package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window.Callback;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface DecorToolbar {
   ViewGroup getViewGroup();

   Context getContext();

   boolean hasExpandedActionView();

   void collapseActionView();

   void setWindowCallback(Callback var1);

   void setWindowTitle(CharSequence var1);

   CharSequence getTitle();

   void setTitle(CharSequence var1);

   CharSequence getSubtitle();

   void setSubtitle(CharSequence var1);

   void initProgress();

   void initIndeterminateProgress();

   boolean hasIcon();

   boolean hasLogo();

   void setIcon(int var1);

   void setIcon(Drawable var1);

   void setLogo(int var1);

   void setLogo(Drawable var1);

   boolean canShowOverflowMenu();

   boolean isOverflowMenuShowing();

   boolean isOverflowMenuShowPending();

   boolean showOverflowMenu();

   boolean hideOverflowMenu();

   void setMenuPrepared();

   void setMenu(Menu var1, MenuPresenter.Callback var2);

   void dismissPopupMenus();

   int getDisplayOptions();

   void setDisplayOptions(int var1);

   void setEmbeddedTabView(ScrollingTabContainerView var1);

   boolean hasEmbeddedTabs();

   boolean isTitleTruncated();

   void setCollapsible(boolean var1);

   void setHomeButtonEnabled(boolean var1);

   int getNavigationMode();

   void setNavigationMode(int var1);

   void setDropdownParams(SpinnerAdapter var1, OnItemSelectedListener var2);

   void setDropdownSelectedPosition(int var1);

   int getDropdownSelectedPosition();

   int getDropdownItemCount();

   void setCustomView(View var1);

   View getCustomView();

   void animateToVisibility(int var1);

   ViewPropertyAnimatorCompat setupAnimatorToVisibility(int var1, long var2);

   void setNavigationIcon(Drawable var1);

   void setNavigationIcon(int var1);

   void setNavigationContentDescription(CharSequence var1);

   void setNavigationContentDescription(int var1);

   void setDefaultNavigationContentDescription(int var1);

   void setDefaultNavigationIcon(Drawable var1);

   void saveHierarchyState(SparseArray var1);

   void restoreHierarchyState(SparseArray var1);

   void setBackgroundDrawable(Drawable var1);

   int getHeight();

   void setVisibility(int var1);

   int getVisibility();

   void setMenuCallbacks(MenuPresenter.Callback var1, MenuBuilder.Callback var2);

   Menu getMenu();
}
