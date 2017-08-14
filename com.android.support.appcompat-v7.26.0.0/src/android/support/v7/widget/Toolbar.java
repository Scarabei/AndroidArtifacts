package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
   private static final String TAG = "Toolbar";
   private ActionMenuView mMenuView;
   private TextView mTitleTextView;
   private TextView mSubtitleTextView;
   private ImageButton mNavButtonView;
   private ImageView mLogoView;
   private Drawable mCollapseIcon;
   private CharSequence mCollapseDescription;
   ImageButton mCollapseButtonView;
   View mExpandedActionView;
   private Context mPopupContext;
   private int mPopupTheme;
   private int mTitleTextAppearance;
   private int mSubtitleTextAppearance;
   int mButtonGravity;
   private int mMaxButtonHeight;
   private int mTitleMarginStart;
   private int mTitleMarginEnd;
   private int mTitleMarginTop;
   private int mTitleMarginBottom;
   private RtlSpacingHelper mContentInsets;
   private int mContentInsetStartWithNavigation;
   private int mContentInsetEndWithActions;
   private int mGravity;
   private CharSequence mTitleText;
   private CharSequence mSubtitleText;
   private int mTitleTextColor;
   private int mSubtitleTextColor;
   private boolean mEatingTouch;
   private boolean mEatingHover;
   private final ArrayList mTempViews;
   private final ArrayList mHiddenViews;
   private final int[] mTempMargins;
   Toolbar.OnMenuItemClickListener mOnMenuItemClickListener;
   private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
   private ToolbarWidgetWrapper mWrapper;
   private ActionMenuPresenter mOuterActionMenuPresenter;
   private Toolbar.ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
   private MenuPresenter.Callback mActionMenuPresenterCallback;
   private MenuBuilder.Callback mMenuBuilderCallback;
   private boolean mCollapsible;
   private final Runnable mShowOverflowMenuRunnable;

   public Toolbar(Context context) {
      this(context, (AttributeSet)null);
   }

   public Toolbar(Context context, @Nullable AttributeSet attrs) {
      this(context, attrs, attr.toolbarStyle);
   }

   public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mGravity = 8388627;
      this.mTempViews = new ArrayList();
      this.mHiddenViews = new ArrayList();
      this.mTempMargins = new int[2];
      this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() {
         public boolean onMenuItemClick(MenuItem item) {
            return Toolbar.this.mOnMenuItemClickListener != null ? Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(item) : false;
         }
      };
      this.mShowOverflowMenuRunnable = new Runnable() {
         public void run() {
            Toolbar.this.showOverflowMenu();
         }
      };
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.getContext(), attrs, styleable.Toolbar, defStyleAttr, 0);
      this.mTitleTextAppearance = a.getResourceId(styleable.Toolbar_titleTextAppearance, 0);
      this.mSubtitleTextAppearance = a.getResourceId(styleable.Toolbar_subtitleTextAppearance, 0);
      this.mGravity = a.getInteger(styleable.Toolbar_android_gravity, this.mGravity);
      this.mButtonGravity = a.getInteger(styleable.Toolbar_buttonGravity, 48);
      int titleMargin = a.getDimensionPixelOffset(styleable.Toolbar_titleMargin, 0);
      if (a.hasValue(styleable.Toolbar_titleMargins)) {
         titleMargin = a.getDimensionPixelOffset(styleable.Toolbar_titleMargins, titleMargin);
      }

      this.mTitleMarginStart = this.mTitleMarginEnd = this.mTitleMarginTop = this.mTitleMarginBottom = titleMargin;
      int marginStart = a.getDimensionPixelOffset(styleable.Toolbar_titleMarginStart, -1);
      if (marginStart >= 0) {
         this.mTitleMarginStart = marginStart;
      }

      int marginEnd = a.getDimensionPixelOffset(styleable.Toolbar_titleMarginEnd, -1);
      if (marginEnd >= 0) {
         this.mTitleMarginEnd = marginEnd;
      }

      int marginTop = a.getDimensionPixelOffset(styleable.Toolbar_titleMarginTop, -1);
      if (marginTop >= 0) {
         this.mTitleMarginTop = marginTop;
      }

      int marginBottom = a.getDimensionPixelOffset(styleable.Toolbar_titleMarginBottom, -1);
      if (marginBottom >= 0) {
         this.mTitleMarginBottom = marginBottom;
      }

      this.mMaxButtonHeight = a.getDimensionPixelSize(styleable.Toolbar_maxButtonHeight, -1);
      int contentInsetStart = a.getDimensionPixelOffset(styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
      int contentInsetEnd = a.getDimensionPixelOffset(styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
      int contentInsetLeft = a.getDimensionPixelSize(styleable.Toolbar_contentInsetLeft, 0);
      int contentInsetRight = a.getDimensionPixelSize(styleable.Toolbar_contentInsetRight, 0);
      this.ensureContentInsets();
      this.mContentInsets.setAbsolute(contentInsetLeft, contentInsetRight);
      if (contentInsetStart != Integer.MIN_VALUE || contentInsetEnd != Integer.MIN_VALUE) {
         this.mContentInsets.setRelative(contentInsetStart, contentInsetEnd);
      }

      this.mContentInsetStartWithNavigation = a.getDimensionPixelOffset(styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
      this.mContentInsetEndWithActions = a.getDimensionPixelOffset(styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
      this.mCollapseIcon = a.getDrawable(styleable.Toolbar_collapseIcon);
      this.mCollapseDescription = a.getText(styleable.Toolbar_collapseContentDescription);
      CharSequence title = a.getText(styleable.Toolbar_title);
      if (!TextUtils.isEmpty(title)) {
         this.setTitle(title);
      }

      CharSequence subtitle = a.getText(styleable.Toolbar_subtitle);
      if (!TextUtils.isEmpty(subtitle)) {
         this.setSubtitle(subtitle);
      }

      this.mPopupContext = this.getContext();
      this.setPopupTheme(a.getResourceId(styleable.Toolbar_popupTheme, 0));
      Drawable navIcon = a.getDrawable(styleable.Toolbar_navigationIcon);
      if (navIcon != null) {
         this.setNavigationIcon(navIcon);
      }

      CharSequence navDesc = a.getText(styleable.Toolbar_navigationContentDescription);
      if (!TextUtils.isEmpty(navDesc)) {
         this.setNavigationContentDescription(navDesc);
      }

      Drawable logo = a.getDrawable(styleable.Toolbar_logo);
      if (logo != null) {
         this.setLogo(logo);
      }

      CharSequence logoDesc = a.getText(styleable.Toolbar_logoDescription);
      if (!TextUtils.isEmpty(logoDesc)) {
         this.setLogoDescription(logoDesc);
      }

      if (a.hasValue(styleable.Toolbar_titleTextColor)) {
         this.setTitleTextColor(a.getColor(styleable.Toolbar_titleTextColor, -1));
      }

      if (a.hasValue(styleable.Toolbar_subtitleTextColor)) {
         this.setSubtitleTextColor(a.getColor(styleable.Toolbar_subtitleTextColor, -1));
      }

      a.recycle();
   }

   public void setPopupTheme(@StyleRes int resId) {
      if (this.mPopupTheme != resId) {
         this.mPopupTheme = resId;
         if (resId == 0) {
            this.mPopupContext = this.getContext();
         } else {
            this.mPopupContext = new ContextThemeWrapper(this.getContext(), resId);
         }
      }

   }

   public int getPopupTheme() {
      return this.mPopupTheme;
   }

   public void setTitleMargin(int start, int top, int end, int bottom) {
      this.mTitleMarginStart = start;
      this.mTitleMarginTop = top;
      this.mTitleMarginEnd = end;
      this.mTitleMarginBottom = bottom;
      this.requestLayout();
   }

   public int getTitleMarginStart() {
      return this.mTitleMarginStart;
   }

   public void setTitleMarginStart(int margin) {
      this.mTitleMarginStart = margin;
      this.requestLayout();
   }

   public int getTitleMarginTop() {
      return this.mTitleMarginTop;
   }

   public void setTitleMarginTop(int margin) {
      this.mTitleMarginTop = margin;
      this.requestLayout();
   }

   public int getTitleMarginEnd() {
      return this.mTitleMarginEnd;
   }

   public void setTitleMarginEnd(int margin) {
      this.mTitleMarginEnd = margin;
      this.requestLayout();
   }

   public int getTitleMarginBottom() {
      return this.mTitleMarginBottom;
   }

   public void setTitleMarginBottom(int margin) {
      this.mTitleMarginBottom = margin;
      this.requestLayout();
   }

   public void onRtlPropertiesChanged(int layoutDirection) {
      if (VERSION.SDK_INT >= 17) {
         super.onRtlPropertiesChanged(layoutDirection);
      }

      this.ensureContentInsets();
      this.mContentInsets.setDirection(layoutDirection == 1);
   }

   public void setLogo(@DrawableRes int resId) {
      this.setLogo(AppCompatResources.getDrawable(this.getContext(), resId));
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean canShowOverflowMenu() {
      return this.getVisibility() == 0 && this.mMenuView != null && this.mMenuView.isOverflowReserved();
   }

   public boolean isOverflowMenuShowing() {
      return this.mMenuView != null && this.mMenuView.isOverflowMenuShowing();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isOverflowMenuShowPending() {
      return this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending();
   }

   public boolean showOverflowMenu() {
      return this.mMenuView != null && this.mMenuView.showOverflowMenu();
   }

   public boolean hideOverflowMenu() {
      return this.mMenuView != null && this.mMenuView.hideOverflowMenu();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setMenu(MenuBuilder menu, ActionMenuPresenter outerPresenter) {
      if (menu != null || this.mMenuView != null) {
         this.ensureMenuView();
         MenuBuilder oldMenu = this.mMenuView.peekMenu();
         if (oldMenu != menu) {
            if (oldMenu != null) {
               oldMenu.removeMenuPresenter(this.mOuterActionMenuPresenter);
               oldMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
            }

            if (this.mExpandedMenuPresenter == null) {
               this.mExpandedMenuPresenter = new Toolbar.ExpandedActionViewMenuPresenter();
            }

            outerPresenter.setExpandedActionViewsExclusive(true);
            if (menu != null) {
               menu.addMenuPresenter(outerPresenter, this.mPopupContext);
               menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
            } else {
               outerPresenter.initForMenu(this.mPopupContext, (MenuBuilder)null);
               this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder)null);
               outerPresenter.updateMenuView(true);
               this.mExpandedMenuPresenter.updateMenuView(true);
            }

            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setPresenter(outerPresenter);
            this.mOuterActionMenuPresenter = outerPresenter;
         }
      }
   }

   public void dismissPopupMenus() {
      if (this.mMenuView != null) {
         this.mMenuView.dismissPopupMenus();
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isTitleTruncated() {
      if (this.mTitleTextView == null) {
         return false;
      } else {
         Layout titleLayout = this.mTitleTextView.getLayout();
         if (titleLayout == null) {
            return false;
         } else {
            int lineCount = titleLayout.getLineCount();

            for(int i = 0; i < lineCount; ++i) {
               if (titleLayout.getEllipsisCount(i) > 0) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public void setLogo(Drawable drawable) {
      if (drawable != null) {
         this.ensureLogoView();
         if (!this.isChildOrHidden(this.mLogoView)) {
            this.addSystemView(this.mLogoView, true);
         }
      } else if (this.mLogoView != null && this.isChildOrHidden(this.mLogoView)) {
         this.removeView(this.mLogoView);
         this.mHiddenViews.remove(this.mLogoView);
      }

      if (this.mLogoView != null) {
         this.mLogoView.setImageDrawable(drawable);
      }

   }

   public Drawable getLogo() {
      return this.mLogoView != null ? this.mLogoView.getDrawable() : null;
   }

   public void setLogoDescription(@StringRes int resId) {
      this.setLogoDescription(this.getContext().getText(resId));
   }

   public void setLogoDescription(CharSequence description) {
      if (!TextUtils.isEmpty(description)) {
         this.ensureLogoView();
      }

      if (this.mLogoView != null) {
         this.mLogoView.setContentDescription(description);
      }

   }

   public CharSequence getLogoDescription() {
      return this.mLogoView != null ? this.mLogoView.getContentDescription() : null;
   }

   private void ensureLogoView() {
      if (this.mLogoView == null) {
         this.mLogoView = new AppCompatImageView(this.getContext());
      }

   }

   public boolean hasExpandedActionView() {
      return this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null;
   }

   public void collapseActionView() {
      MenuItemImpl item = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
      if (item != null) {
         item.collapseActionView();
      }

   }

   public CharSequence getTitle() {
      return this.mTitleText;
   }

   public void setTitle(@StringRes int resId) {
      this.setTitle(this.getContext().getText(resId));
   }

   public void setTitle(CharSequence title) {
      if (!TextUtils.isEmpty(title)) {
         if (this.mTitleTextView == null) {
            Context context = this.getContext();
            this.mTitleTextView = new AppCompatTextView(context);
            this.mTitleTextView.setSingleLine();
            this.mTitleTextView.setEllipsize(TruncateAt.END);
            if (this.mTitleTextAppearance != 0) {
               this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
            }

            if (this.mTitleTextColor != 0) {
               this.mTitleTextView.setTextColor(this.mTitleTextColor);
            }
         }

         if (!this.isChildOrHidden(this.mTitleTextView)) {
            this.addSystemView(this.mTitleTextView, true);
         }
      } else if (this.mTitleTextView != null && this.isChildOrHidden(this.mTitleTextView)) {
         this.removeView(this.mTitleTextView);
         this.mHiddenViews.remove(this.mTitleTextView);
      }

      if (this.mTitleTextView != null) {
         this.mTitleTextView.setText(title);
      }

      this.mTitleText = title;
   }

   public CharSequence getSubtitle() {
      return this.mSubtitleText;
   }

   public void setSubtitle(@StringRes int resId) {
      this.setSubtitle(this.getContext().getText(resId));
   }

   public void setSubtitle(CharSequence subtitle) {
      if (!TextUtils.isEmpty(subtitle)) {
         if (this.mSubtitleTextView == null) {
            Context context = this.getContext();
            this.mSubtitleTextView = new AppCompatTextView(context);
            this.mSubtitleTextView.setSingleLine();
            this.mSubtitleTextView.setEllipsize(TruncateAt.END);
            if (this.mSubtitleTextAppearance != 0) {
               this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
            }

            if (this.mSubtitleTextColor != 0) {
               this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
            }
         }

         if (!this.isChildOrHidden(this.mSubtitleTextView)) {
            this.addSystemView(this.mSubtitleTextView, true);
         }
      } else if (this.mSubtitleTextView != null && this.isChildOrHidden(this.mSubtitleTextView)) {
         this.removeView(this.mSubtitleTextView);
         this.mHiddenViews.remove(this.mSubtitleTextView);
      }

      if (this.mSubtitleTextView != null) {
         this.mSubtitleTextView.setText(subtitle);
      }

      this.mSubtitleText = subtitle;
   }

   public void setTitleTextAppearance(Context context, @StyleRes int resId) {
      this.mTitleTextAppearance = resId;
      if (this.mTitleTextView != null) {
         this.mTitleTextView.setTextAppearance(context, resId);
      }

   }

   public void setSubtitleTextAppearance(Context context, @StyleRes int resId) {
      this.mSubtitleTextAppearance = resId;
      if (this.mSubtitleTextView != null) {
         this.mSubtitleTextView.setTextAppearance(context, resId);
      }

   }

   public void setTitleTextColor(@ColorInt int color) {
      this.mTitleTextColor = color;
      if (this.mTitleTextView != null) {
         this.mTitleTextView.setTextColor(color);
      }

   }

   public void setSubtitleTextColor(@ColorInt int color) {
      this.mSubtitleTextColor = color;
      if (this.mSubtitleTextView != null) {
         this.mSubtitleTextView.setTextColor(color);
      }

   }

   @Nullable
   public CharSequence getNavigationContentDescription() {
      return this.mNavButtonView != null ? this.mNavButtonView.getContentDescription() : null;
   }

   public void setNavigationContentDescription(@StringRes int resId) {
      this.setNavigationContentDescription(resId != 0 ? this.getContext().getText(resId) : null);
   }

   public void setNavigationContentDescription(@Nullable CharSequence description) {
      if (!TextUtils.isEmpty(description)) {
         this.ensureNavButtonView();
      }

      if (this.mNavButtonView != null) {
         this.mNavButtonView.setContentDescription(description);
      }

   }

   public void setNavigationIcon(@DrawableRes int resId) {
      this.setNavigationIcon(AppCompatResources.getDrawable(this.getContext(), resId));
   }

   public void setNavigationIcon(@Nullable Drawable icon) {
      if (icon != null) {
         this.ensureNavButtonView();
         if (!this.isChildOrHidden(this.mNavButtonView)) {
            this.addSystemView(this.mNavButtonView, true);
         }
      } else if (this.mNavButtonView != null && this.isChildOrHidden(this.mNavButtonView)) {
         this.removeView(this.mNavButtonView);
         this.mHiddenViews.remove(this.mNavButtonView);
      }

      if (this.mNavButtonView != null) {
         this.mNavButtonView.setImageDrawable(icon);
      }

   }

   @Nullable
   public Drawable getNavigationIcon() {
      return this.mNavButtonView != null ? this.mNavButtonView.getDrawable() : null;
   }

   public void setNavigationOnClickListener(OnClickListener listener) {
      this.ensureNavButtonView();
      this.mNavButtonView.setOnClickListener(listener);
   }

   public Menu getMenu() {
      this.ensureMenu();
      return this.mMenuView.getMenu();
   }

   public void setOverflowIcon(@Nullable Drawable icon) {
      this.ensureMenu();
      this.mMenuView.setOverflowIcon(icon);
   }

   @Nullable
   public Drawable getOverflowIcon() {
      this.ensureMenu();
      return this.mMenuView.getOverflowIcon();
   }

   private void ensureMenu() {
      this.ensureMenuView();
      if (this.mMenuView.peekMenu() == null) {
         MenuBuilder menu = (MenuBuilder)this.mMenuView.getMenu();
         if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new Toolbar.ExpandedActionViewMenuPresenter();
         }

         this.mMenuView.setExpandedActionViewsExclusive(true);
         menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
      }

   }

   private void ensureMenuView() {
      if (this.mMenuView == null) {
         this.mMenuView = new ActionMenuView(this.getContext());
         this.mMenuView.setPopupTheme(this.mPopupTheme);
         this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
         this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
         Toolbar.LayoutParams lp = this.generateDefaultLayoutParams();
         lp.gravity = 8388613 | this.mButtonGravity & 112;
         this.mMenuView.setLayoutParams(lp);
         this.addSystemView(this.mMenuView, false);
      }

   }

   private MenuInflater getMenuInflater() {
      return new SupportMenuInflater(this.getContext());
   }

   public void inflateMenu(@MenuRes int resId) {
      this.getMenuInflater().inflate(resId, this.getMenu());
   }

   public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
      this.mOnMenuItemClickListener = listener;
   }

   public void setContentInsetsRelative(int contentInsetStart, int contentInsetEnd) {
      this.ensureContentInsets();
      this.mContentInsets.setRelative(contentInsetStart, contentInsetEnd);
   }

   public int getContentInsetStart() {
      return this.mContentInsets != null ? this.mContentInsets.getStart() : 0;
   }

   public int getContentInsetEnd() {
      return this.mContentInsets != null ? this.mContentInsets.getEnd() : 0;
   }

   public void setContentInsetsAbsolute(int contentInsetLeft, int contentInsetRight) {
      this.ensureContentInsets();
      this.mContentInsets.setAbsolute(contentInsetLeft, contentInsetRight);
   }

   public int getContentInsetLeft() {
      return this.mContentInsets != null ? this.mContentInsets.getLeft() : 0;
   }

   public int getContentInsetRight() {
      return this.mContentInsets != null ? this.mContentInsets.getRight() : 0;
   }

   public int getContentInsetStartWithNavigation() {
      return this.mContentInsetStartWithNavigation != Integer.MIN_VALUE ? this.mContentInsetStartWithNavigation : this.getContentInsetStart();
   }

   public void setContentInsetStartWithNavigation(int insetStartWithNavigation) {
      if (insetStartWithNavigation < 0) {
         insetStartWithNavigation = Integer.MIN_VALUE;
      }

      if (insetStartWithNavigation != this.mContentInsetStartWithNavigation) {
         this.mContentInsetStartWithNavigation = insetStartWithNavigation;
         if (this.getNavigationIcon() != null) {
            this.requestLayout();
         }
      }

   }

   public int getContentInsetEndWithActions() {
      return this.mContentInsetEndWithActions != Integer.MIN_VALUE ? this.mContentInsetEndWithActions : this.getContentInsetEnd();
   }

   public void setContentInsetEndWithActions(int insetEndWithActions) {
      if (insetEndWithActions < 0) {
         insetEndWithActions = Integer.MIN_VALUE;
      }

      if (insetEndWithActions != this.mContentInsetEndWithActions) {
         this.mContentInsetEndWithActions = insetEndWithActions;
         if (this.getNavigationIcon() != null) {
            this.requestLayout();
         }
      }

   }

   public int getCurrentContentInsetStart() {
      return this.getNavigationIcon() != null ? Math.max(this.getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : this.getContentInsetStart();
   }

   public int getCurrentContentInsetEnd() {
      boolean hasActions = false;
      if (this.mMenuView != null) {
         MenuBuilder mb = this.mMenuView.peekMenu();
         hasActions = mb != null && mb.hasVisibleItems();
      }

      return hasActions ? Math.max(this.getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0)) : this.getContentInsetEnd();
   }

   public int getCurrentContentInsetLeft() {
      return ViewCompat.getLayoutDirection(this) == 1 ? this.getCurrentContentInsetEnd() : this.getCurrentContentInsetStart();
   }

   public int getCurrentContentInsetRight() {
      return ViewCompat.getLayoutDirection(this) == 1 ? this.getCurrentContentInsetStart() : this.getCurrentContentInsetEnd();
   }

   private void ensureNavButtonView() {
      if (this.mNavButtonView == null) {
         this.mNavButtonView = new AppCompatImageButton(this.getContext(), (AttributeSet)null, attr.toolbarNavigationButtonStyle);
         Toolbar.LayoutParams lp = this.generateDefaultLayoutParams();
         lp.gravity = 8388611 | this.mButtonGravity & 112;
         this.mNavButtonView.setLayoutParams(lp);
      }

   }

   void ensureCollapseButtonView() {
      if (this.mCollapseButtonView == null) {
         this.mCollapseButtonView = new AppCompatImageButton(this.getContext(), (AttributeSet)null, attr.toolbarNavigationButtonStyle);
         this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
         this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
         Toolbar.LayoutParams lp = this.generateDefaultLayoutParams();
         lp.gravity = 8388611 | this.mButtonGravity & 112;
         lp.mViewType = 2;
         this.mCollapseButtonView.setLayoutParams(lp);
         this.mCollapseButtonView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               Toolbar.this.collapseActionView();
            }
         });
      }

   }

   private void addSystemView(View v, boolean allowHide) {
      android.view.ViewGroup.LayoutParams vlp = v.getLayoutParams();
      Toolbar.LayoutParams lp;
      if (vlp == null) {
         lp = this.generateDefaultLayoutParams();
      } else if (!this.checkLayoutParams(vlp)) {
         lp = this.generateLayoutParams(vlp);
      } else {
         lp = (Toolbar.LayoutParams)vlp;
      }

      lp.mViewType = 1;
      if (allowHide && this.mExpandedActionView != null) {
         v.setLayoutParams(lp);
         this.mHiddenViews.add(v);
      } else {
         this.addView(v, lp);
      }

   }

   protected Parcelable onSaveInstanceState() {
      Toolbar.SavedState state = new Toolbar.SavedState(super.onSaveInstanceState());
      if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
         state.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
      }

      state.isOverflowOpen = this.isOverflowMenuShowing();
      return state;
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof Toolbar.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         Toolbar.SavedState ss = (Toolbar.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         Menu menu = this.mMenuView != null ? this.mMenuView.peekMenu() : null;
         if (ss.expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && menu != null) {
            MenuItem item = menu.findItem(ss.expandedMenuItemId);
            if (item != null) {
               item.expandActionView();
            }
         }

         if (ss.isOverflowOpen) {
            this.postShowOverflowMenu();
         }

      }
   }

   private void postShowOverflowMenu() {
      this.removeCallbacks(this.mShowOverflowMenuRunnable);
      this.post(this.mShowOverflowMenuRunnable);
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.removeCallbacks(this.mShowOverflowMenuRunnable);
   }

   public boolean onTouchEvent(MotionEvent ev) {
      int action = ev.getActionMasked();
      if (action == 0) {
         this.mEatingTouch = false;
      }

      if (!this.mEatingTouch) {
         boolean handled = super.onTouchEvent(ev);
         if (action == 0 && !handled) {
            this.mEatingTouch = true;
         }
      }

      if (action == 1 || action == 3) {
         this.mEatingTouch = false;
      }

      return true;
   }

   public boolean onHoverEvent(MotionEvent ev) {
      int action = ev.getActionMasked();
      if (action == 9) {
         this.mEatingHover = false;
      }

      if (!this.mEatingHover) {
         boolean handled = super.onHoverEvent(ev);
         if (action == 9 && !handled) {
            this.mEatingHover = true;
         }
      }

      if (action == 10 || action == 3) {
         this.mEatingHover = false;
      }

      return true;
   }

   private void measureChildConstrained(View child, int parentWidthSpec, int widthUsed, int parentHeightSpec, int heightUsed, int heightConstraint) {
      MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
      int childWidthSpec = getChildMeasureSpec(parentWidthSpec, this.getPaddingLeft() + this.getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
      int childHeightSpec = getChildMeasureSpec(parentHeightSpec, this.getPaddingTop() + this.getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);
      int childHeightMode = MeasureSpec.getMode(childHeightSpec);
      if (childHeightMode != 1073741824 && heightConstraint >= 0) {
         int size = childHeightMode != 0 ? Math.min(MeasureSpec.getSize(childHeightSpec), heightConstraint) : heightConstraint;
         childHeightSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
      }

      child.measure(childWidthSpec, childHeightSpec);
   }

   private int measureChildCollapseMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed, int[] collapsingMargins) {
      MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
      int leftDiff = lp.leftMargin - collapsingMargins[0];
      int rightDiff = lp.rightMargin - collapsingMargins[1];
      int leftMargin = Math.max(0, leftDiff);
      int rightMargin = Math.max(0, rightDiff);
      int hMargins = leftMargin + rightMargin;
      collapsingMargins[0] = Math.max(0, -leftDiff);
      collapsingMargins[1] = Math.max(0, -rightDiff);
      int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, this.getPaddingLeft() + this.getPaddingRight() + hMargins + widthUsed, lp.width);
      int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, this.getPaddingTop() + this.getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);
      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
      return child.getMeasuredWidth() + hMargins;
   }

   private boolean shouldCollapse() {
      if (!this.mCollapsible) {
         return false;
      } else {
         int childCount = this.getChildCount();

         for(int i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (this.shouldLayout(child) && child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
               return false;
            }
         }

         return true;
      }
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int width = 0;
      int height = 0;
      int childState = 0;
      int[] collapsingMargins = this.mTempMargins;
      byte marginStartIndex;
      byte marginEndIndex;
      if (ViewUtils.isLayoutRtl(this)) {
         marginStartIndex = 1;
         marginEndIndex = 0;
      } else {
         marginStartIndex = 0;
         marginEndIndex = 1;
      }

      int navWidth = 0;
      if (this.shouldLayout(this.mNavButtonView)) {
         this.measureChildConstrained(this.mNavButtonView, widthMeasureSpec, width, heightMeasureSpec, 0, this.mMaxButtonHeight);
         navWidth = this.mNavButtonView.getMeasuredWidth() + this.getHorizontalMargins(this.mNavButtonView);
         height = Math.max(height, this.mNavButtonView.getMeasuredHeight() + this.getVerticalMargins(this.mNavButtonView));
         childState = View.combineMeasuredStates(childState, this.mNavButtonView.getMeasuredState());
      }

      if (this.shouldLayout(this.mCollapseButtonView)) {
         this.measureChildConstrained(this.mCollapseButtonView, widthMeasureSpec, width, heightMeasureSpec, 0, this.mMaxButtonHeight);
         navWidth = this.mCollapseButtonView.getMeasuredWidth() + this.getHorizontalMargins(this.mCollapseButtonView);
         height = Math.max(height, this.mCollapseButtonView.getMeasuredHeight() + this.getVerticalMargins(this.mCollapseButtonView));
         childState = View.combineMeasuredStates(childState, this.mCollapseButtonView.getMeasuredState());
      }

      int contentInsetStart = this.getCurrentContentInsetStart();
      int width = width + Math.max(contentInsetStart, navWidth);
      collapsingMargins[marginStartIndex] = Math.max(0, contentInsetStart - navWidth);
      int menuWidth = 0;
      if (this.shouldLayout(this.mMenuView)) {
         this.measureChildConstrained(this.mMenuView, widthMeasureSpec, width, heightMeasureSpec, 0, this.mMaxButtonHeight);
         menuWidth = this.mMenuView.getMeasuredWidth() + this.getHorizontalMargins(this.mMenuView);
         height = Math.max(height, this.mMenuView.getMeasuredHeight() + this.getVerticalMargins(this.mMenuView));
         childState = View.combineMeasuredStates(childState, this.mMenuView.getMeasuredState());
      }

      int contentInsetEnd = this.getCurrentContentInsetEnd();
      width += Math.max(contentInsetEnd, menuWidth);
      collapsingMargins[marginEndIndex] = Math.max(0, contentInsetEnd - menuWidth);
      if (this.shouldLayout(this.mExpandedActionView)) {
         width += this.measureChildCollapseMargins(this.mExpandedActionView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
         height = Math.max(height, this.mExpandedActionView.getMeasuredHeight() + this.getVerticalMargins(this.mExpandedActionView));
         childState = View.combineMeasuredStates(childState, this.mExpandedActionView.getMeasuredState());
      }

      if (this.shouldLayout(this.mLogoView)) {
         width += this.measureChildCollapseMargins(this.mLogoView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
         height = Math.max(height, this.mLogoView.getMeasuredHeight() + this.getVerticalMargins(this.mLogoView));
         childState = View.combineMeasuredStates(childState, this.mLogoView.getMeasuredState());
      }

      int childCount = this.getChildCount();

      int titleWidth;
      for(titleWidth = 0; titleWidth < childCount; ++titleWidth) {
         View child = this.getChildAt(titleWidth);
         Toolbar.LayoutParams lp = (Toolbar.LayoutParams)child.getLayoutParams();
         if (lp.mViewType == 0 && this.shouldLayout(child)) {
            width += this.measureChildCollapseMargins(child, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
            height = Math.max(height, child.getMeasuredHeight() + this.getVerticalMargins(child));
            childState = View.combineMeasuredStates(childState, child.getMeasuredState());
         }
      }

      titleWidth = 0;
      int titleHeight = 0;
      int titleVertMargins = this.mTitleMarginTop + this.mTitleMarginBottom;
      int titleHorizMargins = this.mTitleMarginStart + this.mTitleMarginEnd;
      if (this.shouldLayout(this.mTitleTextView)) {
         this.measureChildCollapseMargins(this.mTitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleVertMargins, collapsingMargins);
         titleWidth = this.mTitleTextView.getMeasuredWidth() + this.getHorizontalMargins(this.mTitleTextView);
         titleHeight = this.mTitleTextView.getMeasuredHeight() + this.getVerticalMargins(this.mTitleTextView);
         childState = View.combineMeasuredStates(childState, this.mTitleTextView.getMeasuredState());
      }

      if (this.shouldLayout(this.mSubtitleTextView)) {
         titleWidth = Math.max(titleWidth, this.measureChildCollapseMargins(this.mSubtitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleHeight + titleVertMargins, collapsingMargins));
         titleHeight += this.mSubtitleTextView.getMeasuredHeight() + this.getVerticalMargins(this.mSubtitleTextView);
         childState = View.combineMeasuredStates(childState, this.mSubtitleTextView.getMeasuredState());
      }

      width += titleWidth;
      height = Math.max(height, titleHeight);
      width += this.getPaddingLeft() + this.getPaddingRight();
      height += this.getPaddingTop() + this.getPaddingBottom();
      int measuredWidth = View.resolveSizeAndState(Math.max(width, this.getSuggestedMinimumWidth()), widthMeasureSpec, childState & -16777216);
      int measuredHeight = View.resolveSizeAndState(Math.max(height, this.getSuggestedMinimumHeight()), heightMeasureSpec, childState << 16);
      this.setMeasuredDimension(measuredWidth, this.shouldCollapse() ? 0 : measuredHeight);
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
      int width = this.getWidth();
      int height = this.getHeight();
      int paddingLeft = this.getPaddingLeft();
      int paddingRight = this.getPaddingRight();
      int paddingTop = this.getPaddingTop();
      int paddingBottom = this.getPaddingBottom();
      int left = paddingLeft;
      int right = width - paddingRight;
      int[] collapsingMargins = this.mTempMargins;
      collapsingMargins[0] = collapsingMargins[1] = 0;
      int minHeight = ViewCompat.getMinimumHeight(this);
      int alignmentHeight = minHeight >= 0 ? Math.min(minHeight, b - t) : 0;
      if (this.shouldLayout(this.mNavButtonView)) {
         if (isRtl) {
            right = this.layoutChildRight(this.mNavButtonView, right, collapsingMargins, alignmentHeight);
         } else {
            left = this.layoutChildLeft(this.mNavButtonView, paddingLeft, collapsingMargins, alignmentHeight);
         }
      }

      if (this.shouldLayout(this.mCollapseButtonView)) {
         if (isRtl) {
            right = this.layoutChildRight(this.mCollapseButtonView, right, collapsingMargins, alignmentHeight);
         } else {
            left = this.layoutChildLeft(this.mCollapseButtonView, left, collapsingMargins, alignmentHeight);
         }
      }

      if (this.shouldLayout(this.mMenuView)) {
         if (isRtl) {
            left = this.layoutChildLeft(this.mMenuView, left, collapsingMargins, alignmentHeight);
         } else {
            right = this.layoutChildRight(this.mMenuView, right, collapsingMargins, alignmentHeight);
         }
      }

      int contentInsetLeft = this.getCurrentContentInsetLeft();
      int contentInsetRight = this.getCurrentContentInsetRight();
      collapsingMargins[0] = Math.max(0, contentInsetLeft - left);
      collapsingMargins[1] = Math.max(0, contentInsetRight - (width - paddingRight - right));
      left = Math.max(left, contentInsetLeft);
      right = Math.min(right, width - paddingRight - contentInsetRight);
      if (this.shouldLayout(this.mExpandedActionView)) {
         if (isRtl) {
            right = this.layoutChildRight(this.mExpandedActionView, right, collapsingMargins, alignmentHeight);
         } else {
            left = this.layoutChildLeft(this.mExpandedActionView, left, collapsingMargins, alignmentHeight);
         }
      }

      if (this.shouldLayout(this.mLogoView)) {
         if (isRtl) {
            right = this.layoutChildRight(this.mLogoView, right, collapsingMargins, alignmentHeight);
         } else {
            left = this.layoutChildLeft(this.mLogoView, left, collapsingMargins, alignmentHeight);
         }
      }

      boolean layoutTitle = this.shouldLayout(this.mTitleTextView);
      boolean layoutSubtitle = this.shouldLayout(this.mSubtitleTextView);
      int titleHeight = 0;
      Toolbar.LayoutParams lp;
      if (layoutTitle) {
         lp = (Toolbar.LayoutParams)this.mTitleTextView.getLayoutParams();
         titleHeight += lp.topMargin + this.mTitleTextView.getMeasuredHeight() + lp.bottomMargin;
      }

      if (layoutSubtitle) {
         lp = (Toolbar.LayoutParams)this.mSubtitleTextView.getLayoutParams();
         titleHeight += lp.topMargin + this.mSubtitleTextView.getMeasuredHeight() + lp.bottomMargin;
      }

      int centerRight;
      int centerViewsCount;
      int i;
      int titleTop;
      if (layoutTitle || layoutSubtitle) {
         View topChild = layoutTitle ? this.mTitleTextView : this.mSubtitleTextView;
         View bottomChild = layoutSubtitle ? this.mSubtitleTextView : this.mTitleTextView;
         Toolbar.LayoutParams toplp = (Toolbar.LayoutParams)topChild.getLayoutParams();
         Toolbar.LayoutParams bottomlp = (Toolbar.LayoutParams)bottomChild.getLayoutParams();
         boolean titleHasWidth = layoutTitle && this.mTitleTextView.getMeasuredWidth() > 0 || layoutSubtitle && this.mSubtitleTextView.getMeasuredWidth() > 0;
         switch(this.mGravity & 112) {
         case 16:
         default:
            centerRight = height - paddingTop - paddingBottom;
            centerViewsCount = (centerRight - titleHeight) / 2;
            if (centerViewsCount < toplp.topMargin + this.mTitleMarginTop) {
               centerViewsCount = toplp.topMargin + this.mTitleMarginTop;
            } else {
               i = height - paddingBottom - titleHeight - centerViewsCount - paddingTop;
               if (i < toplp.bottomMargin + this.mTitleMarginBottom) {
                  centerViewsCount = Math.max(0, centerViewsCount - (bottomlp.bottomMargin + this.mTitleMarginBottom - i));
               }
            }

            titleTop = paddingTop + centerViewsCount;
            break;
         case 48:
            titleTop = this.getPaddingTop() + toplp.topMargin + this.mTitleMarginTop;
            break;
         case 80:
            titleTop = height - paddingBottom - bottomlp.bottomMargin - this.mTitleMarginBottom - titleHeight;
         }

         int var10000;
         Toolbar.LayoutParams lp;
         int subtitleLeft;
         int subtitleBottom;
         if (isRtl) {
            centerRight = (titleHasWidth ? this.mTitleMarginStart : 0) - collapsingMargins[1];
            right -= Math.max(0, centerRight);
            collapsingMargins[1] = Math.max(0, -centerRight);
            centerViewsCount = right;
            i = right;
            if (layoutTitle) {
               lp = (Toolbar.LayoutParams)this.mTitleTextView.getLayoutParams();
               subtitleLeft = right - this.mTitleTextView.getMeasuredWidth();
               subtitleBottom = titleTop + this.mTitleTextView.getMeasuredHeight();
               this.mTitleTextView.layout(subtitleLeft, titleTop, right, subtitleBottom);
               centerViewsCount = subtitleLeft - this.mTitleMarginEnd;
               titleTop = subtitleBottom + lp.bottomMargin;
            }

            if (layoutSubtitle) {
               lp = (Toolbar.LayoutParams)this.mSubtitleTextView.getLayoutParams();
               titleTop += lp.topMargin;
               subtitleLeft = right - this.mSubtitleTextView.getMeasuredWidth();
               subtitleBottom = titleTop + this.mSubtitleTextView.getMeasuredHeight();
               this.mSubtitleTextView.layout(subtitleLeft, titleTop, right, subtitleBottom);
               i = right - this.mTitleMarginEnd;
               var10000 = subtitleBottom + lp.bottomMargin;
            }

            if (titleHasWidth) {
               right = Math.min(centerViewsCount, i);
            }
         } else {
            centerRight = (titleHasWidth ? this.mTitleMarginStart : 0) - collapsingMargins[0];
            left += Math.max(0, centerRight);
            collapsingMargins[0] = Math.max(0, -centerRight);
            centerViewsCount = left;
            i = left;
            if (layoutTitle) {
               lp = (Toolbar.LayoutParams)this.mTitleTextView.getLayoutParams();
               subtitleLeft = left + this.mTitleTextView.getMeasuredWidth();
               subtitleBottom = titleTop + this.mTitleTextView.getMeasuredHeight();
               this.mTitleTextView.layout(left, titleTop, subtitleLeft, subtitleBottom);
               centerViewsCount = subtitleLeft + this.mTitleMarginEnd;
               titleTop = subtitleBottom + lp.bottomMargin;
            }

            if (layoutSubtitle) {
               lp = (Toolbar.LayoutParams)this.mSubtitleTextView.getLayoutParams();
               titleTop += lp.topMargin;
               subtitleLeft = left + this.mSubtitleTextView.getMeasuredWidth();
               subtitleBottom = titleTop + this.mSubtitleTextView.getMeasuredHeight();
               this.mSubtitleTextView.layout(left, titleTop, subtitleLeft, subtitleBottom);
               i = subtitleLeft + this.mTitleMarginEnd;
               var10000 = subtitleBottom + lp.bottomMargin;
            }

            if (titleHasWidth) {
               left = Math.max(centerViewsCount, i);
            }
         }
      }

      this.addCustomViewsWithGravity(this.mTempViews, 3);
      titleTop = this.mTempViews.size();

      int rightViewsCount;
      for(rightViewsCount = 0; rightViewsCount < titleTop; ++rightViewsCount) {
         left = this.layoutChildLeft((View)this.mTempViews.get(rightViewsCount), left, collapsingMargins, alignmentHeight);
      }

      this.addCustomViewsWithGravity(this.mTempViews, 5);
      rightViewsCount = this.mTempViews.size();

      int centerViewsWidth;
      for(centerViewsWidth = 0; centerViewsWidth < rightViewsCount; ++centerViewsWidth) {
         right = this.layoutChildRight((View)this.mTempViews.get(centerViewsWidth), right, collapsingMargins, alignmentHeight);
      }

      this.addCustomViewsWithGravity(this.mTempViews, 1);
      centerViewsWidth = this.getViewListMeasuredWidth(this.mTempViews, collapsingMargins);
      int parentCenter = paddingLeft + (width - paddingLeft - paddingRight) / 2;
      int halfCenterViewsWidth = centerViewsWidth / 2;
      int centerLeft = parentCenter - halfCenterViewsWidth;
      centerRight = centerLeft + centerViewsWidth;
      if (centerLeft < left) {
         centerLeft = left;
      } else if (centerRight > right) {
         centerLeft -= centerRight - right;
      }

      centerViewsCount = this.mTempViews.size();

      for(i = 0; i < centerViewsCount; ++i) {
         centerLeft = this.layoutChildLeft((View)this.mTempViews.get(i), centerLeft, collapsingMargins, alignmentHeight);
      }

      this.mTempViews.clear();
   }

   private int getViewListMeasuredWidth(List views, int[] collapsingMargins) {
      int collapseLeft = collapsingMargins[0];
      int collapseRight = collapsingMargins[1];
      int width = 0;
      int count = views.size();

      for(int i = 0; i < count; ++i) {
         View v = (View)views.get(i);
         Toolbar.LayoutParams lp = (Toolbar.LayoutParams)v.getLayoutParams();
         int l = lp.leftMargin - collapseLeft;
         int r = lp.rightMargin - collapseRight;
         int leftMargin = Math.max(0, l);
         int rightMargin = Math.max(0, r);
         collapseLeft = Math.max(0, -l);
         collapseRight = Math.max(0, -r);
         width += leftMargin + v.getMeasuredWidth() + rightMargin;
      }

      return width;
   }

   private int layoutChildLeft(View child, int left, int[] collapsingMargins, int alignmentHeight) {
      Toolbar.LayoutParams lp = (Toolbar.LayoutParams)child.getLayoutParams();
      int l = lp.leftMargin - collapsingMargins[0];
      left += Math.max(0, l);
      collapsingMargins[0] = Math.max(0, -l);
      int top = this.getChildTop(child, alignmentHeight);
      int childWidth = child.getMeasuredWidth();
      child.layout(left, top, left + childWidth, top + child.getMeasuredHeight());
      left += childWidth + lp.rightMargin;
      return left;
   }

   private int layoutChildRight(View child, int right, int[] collapsingMargins, int alignmentHeight) {
      Toolbar.LayoutParams lp = (Toolbar.LayoutParams)child.getLayoutParams();
      int r = lp.rightMargin - collapsingMargins[1];
      right -= Math.max(0, r);
      collapsingMargins[1] = Math.max(0, -r);
      int top = this.getChildTop(child, alignmentHeight);
      int childWidth = child.getMeasuredWidth();
      child.layout(right - childWidth, top, right, top + child.getMeasuredHeight());
      right -= childWidth + lp.leftMargin;
      return right;
   }

   private int getChildTop(View child, int alignmentHeight) {
      Toolbar.LayoutParams lp = (Toolbar.LayoutParams)child.getLayoutParams();
      int childHeight = child.getMeasuredHeight();
      int alignmentOffset = alignmentHeight > 0 ? (childHeight - alignmentHeight) / 2 : 0;
      switch(this.getChildVerticalGravity(lp.gravity)) {
      case 16:
      default:
         int paddingTop = this.getPaddingTop();
         int paddingBottom = this.getPaddingBottom();
         int height = this.getHeight();
         int space = height - paddingTop - paddingBottom;
         int spaceAbove = (space - childHeight) / 2;
         if (spaceAbove < lp.topMargin) {
            spaceAbove = lp.topMargin;
         } else {
            int spaceBelow = height - paddingBottom - childHeight - spaceAbove - paddingTop;
            if (spaceBelow < lp.bottomMargin) {
               spaceAbove = Math.max(0, spaceAbove - (lp.bottomMargin - spaceBelow));
            }
         }

         return paddingTop + spaceAbove;
      case 48:
         return this.getPaddingTop() - alignmentOffset;
      case 80:
         return this.getHeight() - this.getPaddingBottom() - childHeight - lp.bottomMargin - alignmentOffset;
      }
   }

   private int getChildVerticalGravity(int gravity) {
      int vgrav = gravity & 112;
      switch(vgrav) {
      case 16:
      case 48:
      case 80:
         return vgrav;
      default:
         return this.mGravity & 112;
      }
   }

   private void addCustomViewsWithGravity(List views, int gravity) {
      boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
      int childCount = this.getChildCount();
      int absGrav = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this));
      views.clear();
      int i;
      View child;
      Toolbar.LayoutParams lp;
      if (isRtl) {
         for(i = childCount - 1; i >= 0; --i) {
            child = this.getChildAt(i);
            lp = (Toolbar.LayoutParams)child.getLayoutParams();
            if (lp.mViewType == 0 && this.shouldLayout(child) && this.getChildHorizontalGravity(lp.gravity) == absGrav) {
               views.add(child);
            }
         }
      } else {
         for(i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            lp = (Toolbar.LayoutParams)child.getLayoutParams();
            if (lp.mViewType == 0 && this.shouldLayout(child) && this.getChildHorizontalGravity(lp.gravity) == absGrav) {
               views.add(child);
            }
         }
      }

   }

   private int getChildHorizontalGravity(int gravity) {
      int ld = ViewCompat.getLayoutDirection(this);
      int absGrav = GravityCompat.getAbsoluteGravity(gravity, ld);
      int hGrav = absGrav & 7;
      switch(hGrav) {
      case 1:
      case 3:
      case 5:
         return hGrav;
      case 2:
      case 4:
      default:
         return ld == 1 ? 5 : 3;
      }
   }

   private boolean shouldLayout(View view) {
      return view != null && view.getParent() == this && view.getVisibility() != 8;
   }

   private int getHorizontalMargins(View v) {
      MarginLayoutParams mlp = (MarginLayoutParams)v.getLayoutParams();
      return MarginLayoutParamsCompat.getMarginStart(mlp) + MarginLayoutParamsCompat.getMarginEnd(mlp);
   }

   private int getVerticalMargins(View v) {
      MarginLayoutParams mlp = (MarginLayoutParams)v.getLayoutParams();
      return mlp.topMargin + mlp.bottomMargin;
   }

   public Toolbar.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new Toolbar.LayoutParams(this.getContext(), attrs);
   }

   protected Toolbar.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      if (p instanceof Toolbar.LayoutParams) {
         return new Toolbar.LayoutParams((Toolbar.LayoutParams)p);
      } else if (p instanceof ActionBar.LayoutParams) {
         return new Toolbar.LayoutParams((ActionBar.LayoutParams)p);
      } else {
         return p instanceof MarginLayoutParams ? new Toolbar.LayoutParams((MarginLayoutParams)p) : new Toolbar.LayoutParams(p);
      }
   }

   protected Toolbar.LayoutParams generateDefaultLayoutParams() {
      return new Toolbar.LayoutParams(-2, -2);
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return super.checkLayoutParams(p) && p instanceof Toolbar.LayoutParams;
   }

   private static boolean isCustomView(View child) {
      return ((Toolbar.LayoutParams)child.getLayoutParams()).mViewType == 0;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public DecorToolbar getWrapper() {
      if (this.mWrapper == null) {
         this.mWrapper = new ToolbarWidgetWrapper(this, true);
      }

      return this.mWrapper;
   }

   void removeChildrenForExpandedActionView() {
      int childCount = this.getChildCount();

      for(int i = childCount - 1; i >= 0; --i) {
         View child = this.getChildAt(i);
         Toolbar.LayoutParams lp = (Toolbar.LayoutParams)child.getLayoutParams();
         if (lp.mViewType != 2 && child != this.mMenuView) {
            this.removeViewAt(i);
            this.mHiddenViews.add(child);
         }
      }

   }

   void addChildrenForExpandedActionView() {
      int count = this.mHiddenViews.size();

      for(int i = count - 1; i >= 0; --i) {
         this.addView((View)this.mHiddenViews.get(i));
      }

      this.mHiddenViews.clear();
   }

   private boolean isChildOrHidden(View child) {
      return child.getParent() == this || this.mHiddenViews.contains(child);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setCollapsible(boolean collapsible) {
      this.mCollapsible = collapsible;
      this.requestLayout();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setMenuCallbacks(MenuPresenter.Callback pcb, MenuBuilder.Callback mcb) {
      this.mActionMenuPresenterCallback = pcb;
      this.mMenuBuilderCallback = mcb;
      if (this.mMenuView != null) {
         this.mMenuView.setMenuCallbacks(pcb, mcb);
      }

   }

   private void ensureContentInsets() {
      if (this.mContentInsets == null) {
         this.mContentInsets = new RtlSpacingHelper();
      }

   }

   ActionMenuPresenter getOuterActionMenuPresenter() {
      return this.mOuterActionMenuPresenter;
   }

   Context getPopupContext() {
      return this.mPopupContext;
   }

   private class ExpandedActionViewMenuPresenter implements MenuPresenter {
      MenuBuilder mMenu;
      MenuItemImpl mCurrentExpandedItem;

      public void initForMenu(Context context, MenuBuilder menu) {
         if (this.mMenu != null && this.mCurrentExpandedItem != null) {
            this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
         }

         this.mMenu = menu;
      }

      public MenuView getMenuView(ViewGroup root) {
         return null;
      }

      public void updateMenuView(boolean cleared) {
         if (this.mCurrentExpandedItem != null) {
            boolean found = false;
            if (this.mMenu != null) {
               int count = this.mMenu.size();

               for(int i = 0; i < count; ++i) {
                  MenuItem item = this.mMenu.getItem(i);
                  if (item == this.mCurrentExpandedItem) {
                     found = true;
                     break;
                  }
               }
            }

            if (!found) {
               this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
         }

      }

      public void setCallback(MenuPresenter.Callback cb) {
      }

      public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
         return false;
      }

      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      }

      public boolean flagActionItems() {
         return false;
      }

      public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
         Toolbar.this.ensureCollapseButtonView();
         if (Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
            Toolbar.this.addView(Toolbar.this.mCollapseButtonView);
         }

         Toolbar.this.mExpandedActionView = item.getActionView();
         this.mCurrentExpandedItem = item;
         if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
            Toolbar.LayoutParams lp = Toolbar.this.generateDefaultLayoutParams();
            lp.gravity = 8388611 | Toolbar.this.mButtonGravity & 112;
            lp.mViewType = 2;
            Toolbar.this.mExpandedActionView.setLayoutParams(lp);
            Toolbar.this.addView(Toolbar.this.mExpandedActionView);
         }

         Toolbar.this.removeChildrenForExpandedActionView();
         Toolbar.this.requestLayout();
         item.setActionViewExpanded(true);
         if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
         }

         return true;
      }

      public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
         if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
            ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
         }

         Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
         Toolbar.this.removeView(Toolbar.this.mCollapseButtonView);
         Toolbar.this.mExpandedActionView = null;
         Toolbar.this.addChildrenForExpandedActionView();
         this.mCurrentExpandedItem = null;
         Toolbar.this.requestLayout();
         item.setActionViewExpanded(false);
         return true;
      }

      public int getId() {
         return 0;
      }

      public Parcelable onSaveInstanceState() {
         return null;
      }

      public void onRestoreInstanceState(Parcelable state) {
      }
   }

   public static class SavedState extends AbsSavedState {
      int expandedMenuItemId;
      boolean isOverflowOpen;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public Toolbar.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new Toolbar.SavedState(in, loader);
         }

         public Toolbar.SavedState createFromParcel(Parcel in) {
            return new Toolbar.SavedState(in, (ClassLoader)null);
         }

         public Toolbar.SavedState[] newArray(int size) {
            return new Toolbar.SavedState[size];
         }
      };

      public SavedState(Parcel source) {
         this(source, (ClassLoader)null);
      }

      public SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         this.expandedMenuItemId = source.readInt();
         this.isOverflowOpen = source.readInt() != 0;
      }

      public SavedState(Parcelable superState) {
         super(superState);
      }

      public void writeToParcel(Parcel out, int flags) {
         super.writeToParcel(out, flags);
         out.writeInt(this.expandedMenuItemId);
         out.writeInt(this.isOverflowOpen ? 1 : 0);
      }
   }

   public static class LayoutParams extends ActionBar.LayoutParams {
      static final int CUSTOM = 0;
      static final int SYSTEM = 1;
      static final int EXPANDED = 2;
      int mViewType;

      public LayoutParams(@NonNull Context c, AttributeSet attrs) {
         super(c, attrs);
         this.mViewType = 0;
      }

      public LayoutParams(int width, int height) {
         super(width, height);
         this.mViewType = 0;
         this.gravity = 8388627;
      }

      public LayoutParams(int width, int height, int gravity) {
         super(width, height);
         this.mViewType = 0;
         this.gravity = gravity;
      }

      public LayoutParams(int gravity) {
         this(-2, -1, gravity);
      }

      public LayoutParams(Toolbar.LayoutParams source) {
         super((ActionBar.LayoutParams)source);
         this.mViewType = 0;
         this.mViewType = source.mViewType;
      }

      public LayoutParams(ActionBar.LayoutParams source) {
         super(source);
         this.mViewType = 0;
      }

      public LayoutParams(MarginLayoutParams source) {
         super((android.view.ViewGroup.LayoutParams)source);
         this.mViewType = 0;
         this.copyMarginsFromCompat(source);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams source) {
         super(source);
         this.mViewType = 0;
      }

      void copyMarginsFromCompat(MarginLayoutParams source) {
         this.leftMargin = source.leftMargin;
         this.topMargin = source.topMargin;
         this.rightMargin = source.rightMargin;
         this.bottomMargin = source.bottomMargin;
      }
   }

   public interface OnMenuItemClickListener {
      boolean onMenuItemClick(MenuItem var1);
   }
}
