package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R.color;
import android.support.design.R.dimen;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BottomNavigationView extends FrameLayout {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] DISABLED_STATE_SET = new int[]{-16842910};
   private static final int MENU_PRESENTER_ID = 1;
   private final MenuBuilder mMenu;
   private final BottomNavigationMenuView mMenuView;
   private final BottomNavigationPresenter mPresenter;
   private MenuInflater mMenuInflater;
   private BottomNavigationView.OnNavigationItemSelectedListener mSelectedListener;
   private BottomNavigationView.OnNavigationItemReselectedListener mReselectedListener;

   public BottomNavigationView(Context context) {
      this(context, (AttributeSet)null);
   }

   public BottomNavigationView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mPresenter = new BottomNavigationPresenter();
      ThemeUtils.checkAppCompatTheme(context);
      this.mMenu = new BottomNavigationMenu(context);
      this.mMenuView = new BottomNavigationMenuView(context);
      LayoutParams params = new LayoutParams(-2, -2);
      params.gravity = 17;
      this.mMenuView.setLayoutParams(params);
      this.mPresenter.setBottomNavigationMenuView(this.mMenuView);
      this.mPresenter.setId(1);
      this.mMenuView.setPresenter(this.mPresenter);
      this.mMenu.addMenuPresenter(this.mPresenter);
      this.mPresenter.initForMenu(this.getContext(), this.mMenu);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.BottomNavigationView, defStyleAttr, style.Widget_Design_BottomNavigationView);
      if (a.hasValue(styleable.BottomNavigationView_itemIconTint)) {
         this.mMenuView.setIconTintList(a.getColorStateList(styleable.BottomNavigationView_itemIconTint));
      } else {
         this.mMenuView.setIconTintList(this.createDefaultColorStateList(16842808));
      }

      if (a.hasValue(styleable.BottomNavigationView_itemTextColor)) {
         this.mMenuView.setItemTextColor(a.getColorStateList(styleable.BottomNavigationView_itemTextColor));
      } else {
         this.mMenuView.setItemTextColor(this.createDefaultColorStateList(16842808));
      }

      if (a.hasValue(styleable.BottomNavigationView_elevation)) {
         ViewCompat.setElevation(this, (float)a.getDimensionPixelSize(styleable.BottomNavigationView_elevation, 0));
      }

      int itemBackground = a.getResourceId(styleable.BottomNavigationView_itemBackground, 0);
      this.mMenuView.setItemBackgroundRes(itemBackground);
      if (a.hasValue(styleable.BottomNavigationView_menu)) {
         this.inflateMenu(a.getResourceId(styleable.BottomNavigationView_menu, 0));
      }

      a.recycle();
      this.addView(this.mMenuView, params);
      if (VERSION.SDK_INT < 21) {
         this.addCompatibilityTopDivider(context);
      }

      this.mMenu.setCallback(new Callback() {
         public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            if (BottomNavigationView.this.mReselectedListener != null && item.getItemId() == BottomNavigationView.this.getSelectedItemId()) {
               BottomNavigationView.this.mReselectedListener.onNavigationItemReselected(item);
               return true;
            } else {
               return BottomNavigationView.this.mSelectedListener != null && !BottomNavigationView.this.mSelectedListener.onNavigationItemSelected(item);
            }
         }

         public void onMenuModeChange(MenuBuilder menu) {
         }
      });
   }

   public void setOnNavigationItemSelectedListener(@Nullable BottomNavigationView.OnNavigationItemSelectedListener listener) {
      this.mSelectedListener = listener;
   }

   public void setOnNavigationItemReselectedListener(@Nullable BottomNavigationView.OnNavigationItemReselectedListener listener) {
      this.mReselectedListener = listener;
   }

   @NonNull
   public Menu getMenu() {
      return this.mMenu;
   }

   public void inflateMenu(int resId) {
      this.mPresenter.setUpdateSuspended(true);
      this.getMenuInflater().inflate(resId, this.mMenu);
      this.mPresenter.setUpdateSuspended(false);
      this.mPresenter.updateMenuView(true);
   }

   public int getMaxItemCount() {
      return 5;
   }

   @Nullable
   public ColorStateList getItemIconTintList() {
      return this.mMenuView.getIconTintList();
   }

   public void setItemIconTintList(@Nullable ColorStateList tint) {
      this.mMenuView.setIconTintList(tint);
   }

   @Nullable
   public ColorStateList getItemTextColor() {
      return this.mMenuView.getItemTextColor();
   }

   public void setItemTextColor(@Nullable ColorStateList textColor) {
      this.mMenuView.setItemTextColor(textColor);
   }

   @DrawableRes
   public int getItemBackgroundResource() {
      return this.mMenuView.getItemBackgroundRes();
   }

   public void setItemBackgroundResource(@DrawableRes int resId) {
      this.mMenuView.setItemBackgroundRes(resId);
   }

   @IdRes
   public int getSelectedItemId() {
      return this.mMenuView.getSelectedItemId();
   }

   public void setSelectedItemId(@IdRes int itemId) {
      MenuItem item = this.mMenu.findItem(itemId);
      if (item != null && !this.mMenu.performItemAction(item, this.mPresenter, 0)) {
         item.setChecked(true);
      }

   }

   private void addCompatibilityTopDivider(Context context) {
      View divider = new View(context);
      divider.setBackgroundColor(ContextCompat.getColor(context, color.design_bottom_navigation_shadow_color));
      LayoutParams dividerParams = new LayoutParams(-1, this.getResources().getDimensionPixelSize(dimen.design_bottom_navigation_shadow_height));
      divider.setLayoutParams(dividerParams);
      this.addView(divider);
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

   protected Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      BottomNavigationView.SavedState savedState = new BottomNavigationView.SavedState(superState);
      savedState.menuPresenterState = new Bundle();
      this.mMenu.savePresenterStates(savedState.menuPresenterState);
      return savedState;
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof BottomNavigationView.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         BottomNavigationView.SavedState savedState = (BottomNavigationView.SavedState)state;
         super.onRestoreInstanceState(savedState.getSuperState());
         this.mMenu.restorePresenterStates(savedState.menuPresenterState);
      }
   }

   static class SavedState extends AbsSavedState {
      Bundle menuPresenterState;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public BottomNavigationView.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new BottomNavigationView.SavedState(in, loader);
         }

         public BottomNavigationView.SavedState createFromParcel(Parcel in) {
            return new BottomNavigationView.SavedState(in, (ClassLoader)null);
         }

         public BottomNavigationView.SavedState[] newArray(int size) {
            return new BottomNavigationView.SavedState[size];
         }
      };

      public SavedState(Parcelable superState) {
         super(superState);
      }

      public SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         this.readFromParcel(source, loader);
      }

      public void writeToParcel(@NonNull Parcel out, int flags) {
         super.writeToParcel(out, flags);
         out.writeBundle(this.menuPresenterState);
      }

      private void readFromParcel(Parcel in, ClassLoader loader) {
         this.menuPresenterState = in.readBundle(loader);
      }
   }

   public interface OnNavigationItemReselectedListener {
      void onNavigationItemReselected(@NonNull MenuItem var1);
   }

   public interface OnNavigationItemSelectedListener {
      boolean onNavigationItemSelected(@NonNull MenuItem var1);
   }
}
