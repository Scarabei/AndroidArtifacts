package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;

public class NavigationView extends ScrimInsetsFrameLayout {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] DISABLED_STATE_SET = new int[]{-16842910};
   private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
   private final NavigationMenu mMenu;
   private final NavigationMenuPresenter mPresenter;
   NavigationView.OnNavigationItemSelectedListener mListener;
   private int mMaxWidth;
   private MenuInflater mMenuInflater;

   public NavigationView(Context context) {
      this(context, (AttributeSet)null);
   }

   public NavigationView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mPresenter = new NavigationMenuPresenter();
      ThemeUtils.checkAppCompatTheme(context);
      this.mMenu = new NavigationMenu(context);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.NavigationView, defStyleAttr, style.Widget_Design_NavigationView);
      ViewCompat.setBackground(this, a.getDrawable(styleable.NavigationView_android_background));
      if (a.hasValue(styleable.NavigationView_elevation)) {
         ViewCompat.setElevation(this, (float)a.getDimensionPixelSize(styleable.NavigationView_elevation, 0));
      }

      ViewCompat.setFitsSystemWindows(this, a.getBoolean(styleable.NavigationView_android_fitsSystemWindows, false));
      this.mMaxWidth = a.getDimensionPixelSize(styleable.NavigationView_android_maxWidth, 0);
      ColorStateList itemIconTint;
      if (a.hasValue(styleable.NavigationView_itemIconTint)) {
         itemIconTint = a.getColorStateList(styleable.NavigationView_itemIconTint);
      } else {
         itemIconTint = this.createDefaultColorStateList(16842808);
      }

      boolean textAppearanceSet = false;
      int textAppearance = 0;
      if (a.hasValue(styleable.NavigationView_itemTextAppearance)) {
         textAppearance = a.getResourceId(styleable.NavigationView_itemTextAppearance, 0);
         textAppearanceSet = true;
      }

      ColorStateList itemTextColor = null;
      if (a.hasValue(styleable.NavigationView_itemTextColor)) {
         itemTextColor = a.getColorStateList(styleable.NavigationView_itemTextColor);
      }

      if (!textAppearanceSet && itemTextColor == null) {
         itemTextColor = this.createDefaultColorStateList(16842806);
      }

      Drawable itemBackground = a.getDrawable(styleable.NavigationView_itemBackground);
      this.mMenu.setCallback(new Callback() {
         public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            return NavigationView.this.mListener != null && NavigationView.this.mListener.onNavigationItemSelected(item);
         }

         public void onMenuModeChange(MenuBuilder menu) {
         }
      });
      this.mPresenter.setId(1);
      this.mPresenter.initForMenu(context, this.mMenu);
      this.mPresenter.setItemIconTintList(itemIconTint);
      if (textAppearanceSet) {
         this.mPresenter.setItemTextAppearance(textAppearance);
      }

      this.mPresenter.setItemTextColor(itemTextColor);
      this.mPresenter.setItemBackground(itemBackground);
      this.mMenu.addMenuPresenter(this.mPresenter);
      this.addView((View)this.mPresenter.getMenuView(this));
      if (a.hasValue(styleable.NavigationView_menu)) {
         this.inflateMenu(a.getResourceId(styleable.NavigationView_menu, 0));
      }

      if (a.hasValue(styleable.NavigationView_headerLayout)) {
         this.inflateHeaderView(a.getResourceId(styleable.NavigationView_headerLayout, 0));
      }

      a.recycle();
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      NavigationView.SavedState state = new NavigationView.SavedState(superState);
      state.menuState = new Bundle();
      this.mMenu.savePresenterStates(state.menuState);
      return state;
   }

   protected void onRestoreInstanceState(Parcelable savedState) {
      if (!(savedState instanceof NavigationView.SavedState)) {
         super.onRestoreInstanceState(savedState);
      } else {
         NavigationView.SavedState state = (NavigationView.SavedState)savedState;
         super.onRestoreInstanceState(state.getSuperState());
         this.mMenu.restorePresenterStates(state.menuState);
      }
   }

   public void setNavigationItemSelectedListener(@Nullable NavigationView.OnNavigationItemSelectedListener listener) {
      this.mListener = listener;
   }

   protected void onMeasure(int widthSpec, int heightSpec) {
      switch(MeasureSpec.getMode(widthSpec)) {
      case Integer.MIN_VALUE:
         widthSpec = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(widthSpec), this.mMaxWidth), 1073741824);
         break;
      case 0:
         widthSpec = MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
      case 1073741824:
      }

      super.onMeasure(widthSpec, heightSpec);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void onInsetsChanged(WindowInsetsCompat insets) {
      this.mPresenter.dispatchApplyWindowInsets(insets);
   }

   public void inflateMenu(int resId) {
      this.mPresenter.setUpdateSuspended(true);
      this.getMenuInflater().inflate(resId, this.mMenu);
      this.mPresenter.setUpdateSuspended(false);
      this.mPresenter.updateMenuView(false);
   }

   public Menu getMenu() {
      return this.mMenu;
   }

   public View inflateHeaderView(@LayoutRes int res) {
      return this.mPresenter.inflateHeaderView(res);
   }

   public void addHeaderView(@NonNull View view) {
      this.mPresenter.addHeaderView(view);
   }

   public void removeHeaderView(@NonNull View view) {
      this.mPresenter.removeHeaderView(view);
   }

   public int getHeaderCount() {
      return this.mPresenter.getHeaderCount();
   }

   public View getHeaderView(int index) {
      return this.mPresenter.getHeaderView(index);
   }

   @Nullable
   public ColorStateList getItemIconTintList() {
      return this.mPresenter.getItemTintList();
   }

   public void setItemIconTintList(@Nullable ColorStateList tint) {
      this.mPresenter.setItemIconTintList(tint);
   }

   @Nullable
   public ColorStateList getItemTextColor() {
      return this.mPresenter.getItemTextColor();
   }

   public void setItemTextColor(@Nullable ColorStateList textColor) {
      this.mPresenter.setItemTextColor(textColor);
   }

   @Nullable
   public Drawable getItemBackground() {
      return this.mPresenter.getItemBackground();
   }

   public void setItemBackgroundResource(@DrawableRes int resId) {
      this.setItemBackground(ContextCompat.getDrawable(this.getContext(), resId));
   }

   public void setItemBackground(@Nullable Drawable itemBackground) {
      this.mPresenter.setItemBackground(itemBackground);
   }

   public void setCheckedItem(@IdRes int id) {
      MenuItem item = this.mMenu.findItem(id);
      if (item != null) {
         this.mPresenter.setCheckedItem((MenuItemImpl)item);
      }

   }

   public void setItemTextAppearance(@StyleRes int resId) {
      this.mPresenter.setItemTextAppearance(resId);
   }

   private MenuInflater getMenuInflater() {
      if (this.mMenuInflater == null) {
         this.mMenuInflater = new SupportMenuInflater(this.getContext());
      }

      return this.mMenuInflater;
   }

   private ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
      TypedValue value = new TypedValue();
      if (!this.getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
         return null;
      } else {
         ColorStateList baseColor = AppCompatResources.getColorStateList(this.getContext(), value.resourceId);
         if (!this.getContext().getTheme().resolveAttribute(attr.colorPrimary, value, true)) {
            return null;
         } else {
            int colorPrimary = value.data;
            int defaultColor = baseColor.getDefaultColor();
            return new ColorStateList(new int[][]{DISABLED_STATE_SET, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{baseColor.getColorForState(DISABLED_STATE_SET, defaultColor), colorPrimary, defaultColor});
         }
      }
   }

   public static class SavedState extends AbsSavedState {
      public Bundle menuState;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public NavigationView.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new NavigationView.SavedState(in, loader);
         }

         public NavigationView.SavedState createFromParcel(Parcel in) {
            return new NavigationView.SavedState(in, (ClassLoader)null);
         }

         public NavigationView.SavedState[] newArray(int size) {
            return new NavigationView.SavedState[size];
         }
      };

      public SavedState(Parcel in, ClassLoader loader) {
         super(in, loader);
         this.menuState = in.readBundle(loader);
      }

      public SavedState(Parcelable superState) {
         super(superState);
      }

      public void writeToParcel(@NonNull Parcel dest, int flags) {
         super.writeToParcel(dest, flags);
         dest.writeBundle(this.menuState);
      }
   }

   public interface OnNavigationItemSelectedListener {
      boolean onNavigationItemSelected(@NonNull MenuItem var1);
   }
}
