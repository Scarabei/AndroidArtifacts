package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.drawable;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ToolbarWidgetWrapper implements DecorToolbar {
   private static final String TAG = "ToolbarWidgetWrapper";
   private static final int AFFECTS_LOGO_MASK = 3;
   private static final long DEFAULT_FADE_DURATION_MS = 200L;
   Toolbar mToolbar;
   private int mDisplayOpts;
   private View mTabView;
   private Spinner mSpinner;
   private View mCustomView;
   private Drawable mIcon;
   private Drawable mLogo;
   private Drawable mNavIcon;
   private boolean mTitleSet;
   CharSequence mTitle;
   private CharSequence mSubtitle;
   private CharSequence mHomeDescription;
   Callback mWindowCallback;
   boolean mMenuPrepared;
   private ActionMenuPresenter mActionMenuPresenter;
   private int mNavigationMode;
   private int mDefaultNavigationContentDescription;
   private Drawable mDefaultNavigationIcon;

   public ToolbarWidgetWrapper(Toolbar toolbar, boolean style) {
      this(toolbar, style, string.abc_action_bar_up_description, drawable.abc_ic_ab_back_material);
   }

   public ToolbarWidgetWrapper(Toolbar toolbar, boolean style, int defaultNavigationContentDescription, int defaultNavigationIcon) {
      this.mNavigationMode = 0;
      this.mDefaultNavigationContentDescription = 0;
      this.mToolbar = toolbar;
      this.mTitle = toolbar.getTitle();
      this.mSubtitle = toolbar.getSubtitle();
      this.mTitleSet = this.mTitle != null;
      this.mNavIcon = toolbar.getNavigationIcon();
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(toolbar.getContext(), (AttributeSet)null, styleable.ActionBar, attr.actionBarStyle, 0);
      this.mDefaultNavigationIcon = a.getDrawable(styleable.ActionBar_homeAsUpIndicator);
      if (style) {
         CharSequence title = a.getText(styleable.ActionBar_title);
         if (!TextUtils.isEmpty(title)) {
            this.setTitle(title);
         }

         CharSequence subtitle = a.getText(styleable.ActionBar_subtitle);
         if (!TextUtils.isEmpty(subtitle)) {
            this.setSubtitle(subtitle);
         }

         Drawable logo = a.getDrawable(styleable.ActionBar_logo);
         if (logo != null) {
            this.setLogo(logo);
         }

         Drawable icon = a.getDrawable(styleable.ActionBar_icon);
         if (icon != null) {
            this.setIcon(icon);
         }

         if (this.mNavIcon == null && this.mDefaultNavigationIcon != null) {
            this.setNavigationIcon(this.mDefaultNavigationIcon);
         }

         this.setDisplayOptions(a.getInt(styleable.ActionBar_displayOptions, 0));
         int customNavId = a.getResourceId(styleable.ActionBar_customNavigationLayout, 0);
         if (customNavId != 0) {
            this.setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(customNavId, this.mToolbar, false));
            this.setDisplayOptions(this.mDisplayOpts | 16);
         }

         int height = a.getLayoutDimension(styleable.ActionBar_height, 0);
         if (height > 0) {
            LayoutParams lp = this.mToolbar.getLayoutParams();
            lp.height = height;
            this.mToolbar.setLayoutParams(lp);
         }

         int contentInsetStart = a.getDimensionPixelOffset(styleable.ActionBar_contentInsetStart, -1);
         int contentInsetEnd = a.getDimensionPixelOffset(styleable.ActionBar_contentInsetEnd, -1);
         if (contentInsetStart >= 0 || contentInsetEnd >= 0) {
            this.mToolbar.setContentInsetsRelative(Math.max(contentInsetStart, 0), Math.max(contentInsetEnd, 0));
         }

         int titleTextStyle = a.getResourceId(styleable.ActionBar_titleTextStyle, 0);
         if (titleTextStyle != 0) {
            this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), titleTextStyle);
         }

         int subtitleTextStyle = a.getResourceId(styleable.ActionBar_subtitleTextStyle, 0);
         if (subtitleTextStyle != 0) {
            this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), subtitleTextStyle);
         }

         int popupTheme = a.getResourceId(styleable.ActionBar_popupTheme, 0);
         if (popupTheme != 0) {
            this.mToolbar.setPopupTheme(popupTheme);
         }
      } else {
         this.mDisplayOpts = this.detectDisplayOptions();
      }

      a.recycle();
      this.setDefaultNavigationContentDescription(defaultNavigationContentDescription);
      this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
      this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
         final ActionMenuItem mNavItem;

         {
            this.mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.this.mToolbar.getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.this.mTitle);
         }

         public void onClick(View v) {
            if (ToolbarWidgetWrapper.this.mWindowCallback != null && ToolbarWidgetWrapper.this.mMenuPrepared) {
               ToolbarWidgetWrapper.this.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
            }

         }
      });
   }

   public void setDefaultNavigationContentDescription(int defaultNavigationContentDescription) {
      if (defaultNavigationContentDescription != this.mDefaultNavigationContentDescription) {
         this.mDefaultNavigationContentDescription = defaultNavigationContentDescription;
         if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
            this.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
         }

      }
   }

   private int detectDisplayOptions() {
      int opts = 11;
      if (this.mToolbar.getNavigationIcon() != null) {
         opts |= 4;
         this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
      }

      return opts;
   }

   public ViewGroup getViewGroup() {
      return this.mToolbar;
   }

   public Context getContext() {
      return this.mToolbar.getContext();
   }

   public boolean hasExpandedActionView() {
      return this.mToolbar.hasExpandedActionView();
   }

   public void collapseActionView() {
      this.mToolbar.collapseActionView();
   }

   public void setWindowCallback(Callback cb) {
      this.mWindowCallback = cb;
   }

   public void setWindowTitle(CharSequence title) {
      if (!this.mTitleSet) {
         this.setTitleInt(title);
      }

   }

   public CharSequence getTitle() {
      return this.mToolbar.getTitle();
   }

   public void setTitle(CharSequence title) {
      this.mTitleSet = true;
      this.setTitleInt(title);
   }

   private void setTitleInt(CharSequence title) {
      this.mTitle = title;
      if ((this.mDisplayOpts & 8) != 0) {
         this.mToolbar.setTitle(title);
      }

   }

   public CharSequence getSubtitle() {
      return this.mToolbar.getSubtitle();
   }

   public void setSubtitle(CharSequence subtitle) {
      this.mSubtitle = subtitle;
      if ((this.mDisplayOpts & 8) != 0) {
         this.mToolbar.setSubtitle(subtitle);
      }

   }

   public void initProgress() {
      Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
   }

   public void initIndeterminateProgress() {
      Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
   }

   public boolean hasIcon() {
      return this.mIcon != null;
   }

   public boolean hasLogo() {
      return this.mLogo != null;
   }

   public void setIcon(int resId) {
      this.setIcon(resId != 0 ? AppCompatResources.getDrawable(this.getContext(), resId) : null);
   }

   public void setIcon(Drawable d) {
      this.mIcon = d;
      this.updateToolbarLogo();
   }

   public void setLogo(int resId) {
      this.setLogo(resId != 0 ? AppCompatResources.getDrawable(this.getContext(), resId) : null);
   }

   public void setLogo(Drawable d) {
      this.mLogo = d;
      this.updateToolbarLogo();
   }

   private void updateToolbarLogo() {
      Drawable logo = null;
      if ((this.mDisplayOpts & 2) != 0) {
         if ((this.mDisplayOpts & 1) != 0) {
            logo = this.mLogo != null ? this.mLogo : this.mIcon;
         } else {
            logo = this.mIcon;
         }
      }

      this.mToolbar.setLogo(logo);
   }

   public boolean canShowOverflowMenu() {
      return this.mToolbar.canShowOverflowMenu();
   }

   public boolean isOverflowMenuShowing() {
      return this.mToolbar.isOverflowMenuShowing();
   }

   public boolean isOverflowMenuShowPending() {
      return this.mToolbar.isOverflowMenuShowPending();
   }

   public boolean showOverflowMenu() {
      return this.mToolbar.showOverflowMenu();
   }

   public boolean hideOverflowMenu() {
      return this.mToolbar.hideOverflowMenu();
   }

   public void setMenuPrepared() {
      this.mMenuPrepared = true;
   }

   public void setMenu(Menu menu, MenuPresenter.Callback cb) {
      if (this.mActionMenuPresenter == null) {
         this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
         this.mActionMenuPresenter.setId(id.action_menu_presenter);
      }

      this.mActionMenuPresenter.setCallback(cb);
      this.mToolbar.setMenu((MenuBuilder)menu, this.mActionMenuPresenter);
   }

   public void dismissPopupMenus() {
      this.mToolbar.dismissPopupMenus();
   }

   public int getDisplayOptions() {
      return this.mDisplayOpts;
   }

   public void setDisplayOptions(int newOpts) {
      int oldOpts = this.mDisplayOpts;
      int changed = oldOpts ^ newOpts;
      this.mDisplayOpts = newOpts;
      if (changed != 0) {
         if ((changed & 4) != 0) {
            if ((newOpts & 4) != 0) {
               this.updateHomeAccessibility();
            }

            this.updateNavigationIcon();
         }

         if ((changed & 3) != 0) {
            this.updateToolbarLogo();
         }

         if ((changed & 8) != 0) {
            if ((newOpts & 8) != 0) {
               this.mToolbar.setTitle(this.mTitle);
               this.mToolbar.setSubtitle(this.mSubtitle);
            } else {
               this.mToolbar.setTitle((CharSequence)null);
               this.mToolbar.setSubtitle((CharSequence)null);
            }
         }

         if ((changed & 16) != 0 && this.mCustomView != null) {
            if ((newOpts & 16) != 0) {
               this.mToolbar.addView(this.mCustomView);
            } else {
               this.mToolbar.removeView(this.mCustomView);
            }
         }
      }

   }

   public void setEmbeddedTabView(ScrollingTabContainerView tabView) {
      if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
         this.mToolbar.removeView(this.mTabView);
      }

      this.mTabView = tabView;
      if (tabView != null && this.mNavigationMode == 2) {
         this.mToolbar.addView(this.mTabView, 0);
         Toolbar.LayoutParams lp = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
         lp.width = -2;
         lp.height = -2;
         lp.gravity = 8388691;
         tabView.setAllowCollapse(true);
      }

   }

   public boolean hasEmbeddedTabs() {
      return this.mTabView != null;
   }

   public boolean isTitleTruncated() {
      return this.mToolbar.isTitleTruncated();
   }

   public void setCollapsible(boolean collapsible) {
      this.mToolbar.setCollapsible(collapsible);
   }

   public void setHomeButtonEnabled(boolean enable) {
   }

   public int getNavigationMode() {
      return this.mNavigationMode;
   }

   public void setNavigationMode(int mode) {
      int oldMode = this.mNavigationMode;
      if (mode != oldMode) {
         switch(oldMode) {
         case 1:
            if (this.mSpinner != null && this.mSpinner.getParent() == this.mToolbar) {
               this.mToolbar.removeView(this.mSpinner);
            }
            break;
         case 2:
            if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
               this.mToolbar.removeView(this.mTabView);
            }
         }

         this.mNavigationMode = mode;
         switch(mode) {
         case 0:
            break;
         case 1:
            this.ensureSpinner();
            this.mToolbar.addView(this.mSpinner, 0);
            break;
         case 2:
            if (this.mTabView != null) {
               this.mToolbar.addView(this.mTabView, 0);
               Toolbar.LayoutParams lp = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
               lp.width = -2;
               lp.height = -2;
               lp.gravity = 8388691;
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid navigation mode " + mode);
         }
      }

   }

   private void ensureSpinner() {
      if (this.mSpinner == null) {
         this.mSpinner = new AppCompatSpinner(this.getContext(), (AttributeSet)null, attr.actionDropDownStyle);
         Toolbar.LayoutParams lp = new Toolbar.LayoutParams(-2, -2, 8388627);
         this.mSpinner.setLayoutParams(lp);
      }

   }

   public void setDropdownParams(SpinnerAdapter adapter, OnItemSelectedListener listener) {
      this.ensureSpinner();
      this.mSpinner.setAdapter(adapter);
      this.mSpinner.setOnItemSelectedListener(listener);
   }

   public void setDropdownSelectedPosition(int position) {
      if (this.mSpinner == null) {
         throw new IllegalStateException("Can't set dropdown selected position without an adapter");
      } else {
         this.mSpinner.setSelection(position);
      }
   }

   public int getDropdownSelectedPosition() {
      return this.mSpinner != null ? this.mSpinner.getSelectedItemPosition() : 0;
   }

   public int getDropdownItemCount() {
      return this.mSpinner != null ? this.mSpinner.getCount() : 0;
   }

   public void setCustomView(View view) {
      if (this.mCustomView != null && (this.mDisplayOpts & 16) != 0) {
         this.mToolbar.removeView(this.mCustomView);
      }

      this.mCustomView = view;
      if (view != null && (this.mDisplayOpts & 16) != 0) {
         this.mToolbar.addView(this.mCustomView);
      }

   }

   public View getCustomView() {
      return this.mCustomView;
   }

   public void animateToVisibility(int visibility) {
      ViewPropertyAnimatorCompat anim = this.setupAnimatorToVisibility(visibility, 200L);
      if (anim != null) {
         anim.start();
      }

   }

   public ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int visibility, long duration) {
      return ViewCompat.animate(this.mToolbar).alpha(visibility == 0 ? 1.0F : 0.0F).setDuration(duration).setListener(new ViewPropertyAnimatorListenerAdapter() {
         private boolean mCanceled = false;

         public void onAnimationStart(View view) {
            ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
         }

         public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
               ToolbarWidgetWrapper.this.mToolbar.setVisibility(visibility);
            }

         }

         public void onAnimationCancel(View view) {
            this.mCanceled = true;
         }
      });
   }

   public void setNavigationIcon(Drawable icon) {
      this.mNavIcon = icon;
      this.updateNavigationIcon();
   }

   public void setNavigationIcon(int resId) {
      this.setNavigationIcon(resId != 0 ? AppCompatResources.getDrawable(this.getContext(), resId) : null);
   }

   public void setDefaultNavigationIcon(Drawable defaultNavigationIcon) {
      if (this.mDefaultNavigationIcon != defaultNavigationIcon) {
         this.mDefaultNavigationIcon = defaultNavigationIcon;
         this.updateNavigationIcon();
      }

   }

   private void updateNavigationIcon() {
      if ((this.mDisplayOpts & 4) != 0) {
         this.mToolbar.setNavigationIcon(this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon);
      } else {
         this.mToolbar.setNavigationIcon((Drawable)null);
      }

   }

   public void setNavigationContentDescription(CharSequence description) {
      this.mHomeDescription = description;
      this.updateHomeAccessibility();
   }

   public void setNavigationContentDescription(int resId) {
      this.setNavigationContentDescription(resId == 0 ? null : this.getContext().getString(resId));
   }

   private void updateHomeAccessibility() {
      if ((this.mDisplayOpts & 4) != 0) {
         if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
         } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
         }
      }

   }

   public void saveHierarchyState(SparseArray toolbarStates) {
      this.mToolbar.saveHierarchyState(toolbarStates);
   }

   public void restoreHierarchyState(SparseArray toolbarStates) {
      this.mToolbar.restoreHierarchyState(toolbarStates);
   }

   public void setBackgroundDrawable(Drawable d) {
      ViewCompat.setBackground(this.mToolbar, d);
   }

   public int getHeight() {
      return this.mToolbar.getHeight();
   }

   public void setVisibility(int visible) {
      this.mToolbar.setVisibility(visible);
   }

   public int getVisibility() {
      return this.mToolbar.getVisibility();
   }

   public void setMenuCallbacks(MenuPresenter.Callback actionMenuPresenterCallback, MenuBuilder.Callback menuBuilderCallback) {
      this.mToolbar.setMenuCallbacks(actionMenuPresenterCallback, menuBuilderCallback);
   }

   public Menu getMenu() {
      return this.mToolbar.getMenu();
   }
}
