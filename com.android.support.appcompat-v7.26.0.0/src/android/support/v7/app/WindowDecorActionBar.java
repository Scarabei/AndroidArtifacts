package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
   private static final String TAG = "WindowDecorActionBar";
   private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
   private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
   Context mContext;
   private Context mThemedContext;
   private Activity mActivity;
   private Dialog mDialog;
   ActionBarOverlayLayout mOverlayLayout;
   ActionBarContainer mContainerView;
   DecorToolbar mDecorToolbar;
   ActionBarContextView mContextView;
   View mContentView;
   ScrollingTabContainerView mTabScrollView;
   private ArrayList mTabs = new ArrayList();
   private WindowDecorActionBar.TabImpl mSelectedTab;
   private int mSavedTabPosition = -1;
   private boolean mDisplayHomeAsUpSet;
   WindowDecorActionBar.ActionModeImpl mActionMode;
   ActionMode mDeferredDestroyActionMode;
   ActionMode.Callback mDeferredModeDestroyCallback;
   private boolean mLastMenuVisibility;
   private ArrayList mMenuVisibilityListeners = new ArrayList();
   private static final int INVALID_POSITION = -1;
   private static final long FADE_OUT_DURATION_MS = 100L;
   private static final long FADE_IN_DURATION_MS = 200L;
   private boolean mHasEmbeddedTabs;
   private int mCurWindowVisibility = 0;
   boolean mContentAnimations = true;
   boolean mHiddenByApp;
   boolean mHiddenBySystem;
   private boolean mShowingForMode;
   private boolean mNowShowing = true;
   ViewPropertyAnimatorCompatSet mCurrentShowAnim;
   private boolean mShowHideAnimationEnabled;
   boolean mHideOnContentScroll;
   final ViewPropertyAnimatorListener mHideListener = new ViewPropertyAnimatorListenerAdapter() {
      public void onAnimationEnd(View view) {
         if (WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
            WindowDecorActionBar.this.mContentView.setTranslationY(0.0F);
            WindowDecorActionBar.this.mContainerView.setTranslationY(0.0F);
         }

         WindowDecorActionBar.this.mContainerView.setVisibility(8);
         WindowDecorActionBar.this.mContainerView.setTransitioning(false);
         WindowDecorActionBar.this.mCurrentShowAnim = null;
         WindowDecorActionBar.this.completeDeferredDestroyActionMode();
         if (WindowDecorActionBar.this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets(WindowDecorActionBar.this.mOverlayLayout);
         }

      }
   };
   final ViewPropertyAnimatorListener mShowListener = new ViewPropertyAnimatorListenerAdapter() {
      public void onAnimationEnd(View view) {
         WindowDecorActionBar.this.mCurrentShowAnim = null;
         WindowDecorActionBar.this.mContainerView.requestLayout();
      }
   };
   final ViewPropertyAnimatorUpdateListener mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
      public void onAnimationUpdate(View view) {
         ViewParent parent = WindowDecorActionBar.this.mContainerView.getParent();
         ((View)parent).invalidate();
      }
   };

   public WindowDecorActionBar(Activity activity, boolean overlayMode) {
      this.mActivity = activity;
      Window window = activity.getWindow();
      View decor = window.getDecorView();
      this.init(decor);
      if (!overlayMode) {
         this.mContentView = decor.findViewById(16908290);
      }

   }

   public WindowDecorActionBar(Dialog dialog) {
      this.mDialog = dialog;
      this.init(dialog.getWindow().getDecorView());
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public WindowDecorActionBar(View layout) {
      assert layout.isInEditMode();

      this.init(layout);
   }

   private void init(View decor) {
      this.mOverlayLayout = (ActionBarOverlayLayout)decor.findViewById(id.decor_content_parent);
      if (this.mOverlayLayout != null) {
         this.mOverlayLayout.setActionBarVisibilityCallback(this);
      }

      this.mDecorToolbar = this.getDecorToolbar(decor.findViewById(id.action_bar));
      this.mContextView = (ActionBarContextView)decor.findViewById(id.action_context_bar);
      this.mContainerView = (ActionBarContainer)decor.findViewById(id.action_bar_container);
      if (this.mDecorToolbar != null && this.mContextView != null && this.mContainerView != null) {
         this.mContext = this.mDecorToolbar.getContext();
         int current = this.mDecorToolbar.getDisplayOptions();
         boolean homeAsUp = (current & 4) != 0;
         if (homeAsUp) {
            this.mDisplayHomeAsUpSet = true;
         }

         ActionBarPolicy abp = ActionBarPolicy.get(this.mContext);
         this.setHomeButtonEnabled(abp.enableHomeButtonByDefault() || homeAsUp);
         this.setHasEmbeddedTabs(abp.hasEmbeddedTabs());
         TypedArray a = this.mContext.obtainStyledAttributes((AttributeSet)null, styleable.ActionBar, attr.actionBarStyle, 0);
         if (a.getBoolean(styleable.ActionBar_hideOnContentScroll, false)) {
            this.setHideOnContentScrollEnabled(true);
         }

         int elevation = a.getDimensionPixelSize(styleable.ActionBar_elevation, 0);
         if (elevation != 0) {
            this.setElevation((float)elevation);
         }

         a.recycle();
      } else {
         throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
      }
   }

   private DecorToolbar getDecorToolbar(View view) {
      if (view instanceof DecorToolbar) {
         return (DecorToolbar)view;
      } else if (view instanceof Toolbar) {
         return ((Toolbar)view).getWrapper();
      } else {
         throw new IllegalStateException("Can't make a decor toolbar out of " + view != null ? view.getClass().getSimpleName() : "null");
      }
   }

   public void setElevation(float elevation) {
      ViewCompat.setElevation(this.mContainerView, elevation);
   }

   public float getElevation() {
      return ViewCompat.getElevation(this.mContainerView);
   }

   public void onConfigurationChanged(Configuration newConfig) {
      this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
   }

   private void setHasEmbeddedTabs(boolean hasEmbeddedTabs) {
      this.mHasEmbeddedTabs = hasEmbeddedTabs;
      if (!this.mHasEmbeddedTabs) {
         this.mDecorToolbar.setEmbeddedTabView((ScrollingTabContainerView)null);
         this.mContainerView.setTabContainer(this.mTabScrollView);
      } else {
         this.mContainerView.setTabContainer((ScrollingTabContainerView)null);
         this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
      }

      boolean isInTabMode = this.getNavigationMode() == 2;
      if (this.mTabScrollView != null) {
         if (isInTabMode) {
            this.mTabScrollView.setVisibility(0);
            if (this.mOverlayLayout != null) {
               ViewCompat.requestApplyInsets(this.mOverlayLayout);
            }
         } else {
            this.mTabScrollView.setVisibility(8);
         }
      }

      this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && isInTabMode);
      this.mOverlayLayout.setHasNonEmbeddedTabs(!this.mHasEmbeddedTabs && isInTabMode);
   }

   private void ensureTabsExist() {
      if (this.mTabScrollView == null) {
         ScrollingTabContainerView tabScroller = new ScrollingTabContainerView(this.mContext);
         if (this.mHasEmbeddedTabs) {
            tabScroller.setVisibility(0);
            this.mDecorToolbar.setEmbeddedTabView(tabScroller);
         } else {
            if (this.getNavigationMode() == 2) {
               tabScroller.setVisibility(0);
               if (this.mOverlayLayout != null) {
                  ViewCompat.requestApplyInsets(this.mOverlayLayout);
               }
            } else {
               tabScroller.setVisibility(8);
            }

            this.mContainerView.setTabContainer(tabScroller);
         }

         this.mTabScrollView = tabScroller;
      }
   }

   void completeDeferredDestroyActionMode() {
      if (this.mDeferredModeDestroyCallback != null) {
         this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
         this.mDeferredDestroyActionMode = null;
         this.mDeferredModeDestroyCallback = null;
      }

   }

   public void onWindowVisibilityChanged(int visibility) {
      this.mCurWindowVisibility = visibility;
   }

   public void setShowHideAnimationEnabled(boolean enabled) {
      this.mShowHideAnimationEnabled = enabled;
      if (!enabled && this.mCurrentShowAnim != null) {
         this.mCurrentShowAnim.cancel();
      }

   }

   public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
      this.mMenuVisibilityListeners.add(listener);
   }

   public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
      this.mMenuVisibilityListeners.remove(listener);
   }

   public void dispatchMenuVisibilityChanged(boolean isVisible) {
      if (isVisible != this.mLastMenuVisibility) {
         this.mLastMenuVisibility = isVisible;
         int count = this.mMenuVisibilityListeners.size();

         for(int i = 0; i < count; ++i) {
            ((ActionBar.OnMenuVisibilityListener)this.mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(isVisible);
         }

      }
   }

   public void setCustomView(int resId) {
      this.setCustomView(LayoutInflater.from(this.getThemedContext()).inflate(resId, this.mDecorToolbar.getViewGroup(), false));
   }

   public void setDisplayUseLogoEnabled(boolean useLogo) {
      this.setDisplayOptions(useLogo ? 1 : 0, 1);
   }

   public void setDisplayShowHomeEnabled(boolean showHome) {
      this.setDisplayOptions(showHome ? 2 : 0, 2);
   }

   public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
      this.setDisplayOptions(showHomeAsUp ? 4 : 0, 4);
   }

   public void setDisplayShowTitleEnabled(boolean showTitle) {
      this.setDisplayOptions(showTitle ? 8 : 0, 8);
   }

   public void setDisplayShowCustomEnabled(boolean showCustom) {
      this.setDisplayOptions(showCustom ? 16 : 0, 16);
   }

   public void setHomeButtonEnabled(boolean enable) {
      this.mDecorToolbar.setHomeButtonEnabled(enable);
   }

   public void setTitle(int resId) {
      this.setTitle(this.mContext.getString(resId));
   }

   public void setSubtitle(int resId) {
      this.setSubtitle(this.mContext.getString(resId));
   }

   public void setSelectedNavigationItem(int position) {
      switch(this.mDecorToolbar.getNavigationMode()) {
      case 1:
         this.mDecorToolbar.setDropdownSelectedPosition(position);
         break;
      case 2:
         this.selectTab((ActionBar.Tab)this.mTabs.get(position));
         break;
      default:
         throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
      }

   }

   public void removeAllTabs() {
      this.cleanupTabs();
   }

   private void cleanupTabs() {
      if (this.mSelectedTab != null) {
         this.selectTab((ActionBar.Tab)null);
      }

      this.mTabs.clear();
      if (this.mTabScrollView != null) {
         this.mTabScrollView.removeAllTabs();
      }

      this.mSavedTabPosition = -1;
   }

   public void setTitle(CharSequence title) {
      this.mDecorToolbar.setTitle(title);
   }

   public void setWindowTitle(CharSequence title) {
      this.mDecorToolbar.setWindowTitle(title);
   }

   public boolean requestFocus() {
      ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
      if (viewGroup != null && !viewGroup.hasFocus()) {
         viewGroup.requestFocus();
         return true;
      } else {
         return false;
      }
   }

   public void setSubtitle(CharSequence subtitle) {
      this.mDecorToolbar.setSubtitle(subtitle);
   }

   public void setDisplayOptions(int options) {
      if ((options & 4) != 0) {
         this.mDisplayHomeAsUpSet = true;
      }

      this.mDecorToolbar.setDisplayOptions(options);
   }

   public void setDisplayOptions(int options, int mask) {
      int current = this.mDecorToolbar.getDisplayOptions();
      if ((mask & 4) != 0) {
         this.mDisplayHomeAsUpSet = true;
      }

      this.mDecorToolbar.setDisplayOptions(options & mask | current & ~mask);
   }

   public void setBackgroundDrawable(Drawable d) {
      this.mContainerView.setPrimaryBackground(d);
   }

   public void setStackedBackgroundDrawable(Drawable d) {
      this.mContainerView.setStackedBackground(d);
   }

   public void setSplitBackgroundDrawable(Drawable d) {
   }

   public View getCustomView() {
      return this.mDecorToolbar.getCustomView();
   }

   public CharSequence getTitle() {
      return this.mDecorToolbar.getTitle();
   }

   public CharSequence getSubtitle() {
      return this.mDecorToolbar.getSubtitle();
   }

   public int getNavigationMode() {
      return this.mDecorToolbar.getNavigationMode();
   }

   public int getDisplayOptions() {
      return this.mDecorToolbar.getDisplayOptions();
   }

   public ActionMode startActionMode(ActionMode.Callback callback) {
      if (this.mActionMode != null) {
         this.mActionMode.finish();
      }

      this.mOverlayLayout.setHideOnContentScrollEnabled(false);
      this.mContextView.killMode();
      WindowDecorActionBar.ActionModeImpl mode = new WindowDecorActionBar.ActionModeImpl(this.mContextView.getContext(), callback);
      if (mode.dispatchOnCreate()) {
         this.mActionMode = mode;
         mode.invalidate();
         this.mContextView.initForMode(mode);
         this.animateToMode(true);
         this.mContextView.sendAccessibilityEvent(32);
         return mode;
      } else {
         return null;
      }
   }

   private void configureTab(ActionBar.Tab tab, int position) {
      WindowDecorActionBar.TabImpl tabi = (WindowDecorActionBar.TabImpl)tab;
      ActionBar.TabListener callback = tabi.getCallback();
      if (callback == null) {
         throw new IllegalStateException("Action Bar Tab must have a Callback");
      } else {
         tabi.setPosition(position);
         this.mTabs.add(position, tabi);
         int count = this.mTabs.size();

         for(int i = position + 1; i < count; ++i) {
            ((WindowDecorActionBar.TabImpl)this.mTabs.get(i)).setPosition(i);
         }

      }
   }

   public void addTab(ActionBar.Tab tab) {
      this.addTab(tab, this.mTabs.isEmpty());
   }

   public void addTab(ActionBar.Tab tab, int position) {
      this.addTab(tab, position, this.mTabs.isEmpty());
   }

   public void addTab(ActionBar.Tab tab, boolean setSelected) {
      this.ensureTabsExist();
      this.mTabScrollView.addTab(tab, setSelected);
      this.configureTab(tab, this.mTabs.size());
      if (setSelected) {
         this.selectTab(tab);
      }

   }

   public void addTab(ActionBar.Tab tab, int position, boolean setSelected) {
      this.ensureTabsExist();
      this.mTabScrollView.addTab(tab, position, setSelected);
      this.configureTab(tab, position);
      if (setSelected) {
         this.selectTab(tab);
      }

   }

   public ActionBar.Tab newTab() {
      return new WindowDecorActionBar.TabImpl();
   }

   public void removeTab(ActionBar.Tab tab) {
      this.removeTabAt(tab.getPosition());
   }

   public void removeTabAt(int position) {
      if (this.mTabScrollView != null) {
         int selectedTabPosition = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : this.mSavedTabPosition;
         this.mTabScrollView.removeTabAt(position);
         WindowDecorActionBar.TabImpl removedTab = (WindowDecorActionBar.TabImpl)this.mTabs.remove(position);
         if (removedTab != null) {
            removedTab.setPosition(-1);
         }

         int newTabCount = this.mTabs.size();

         for(int i = position; i < newTabCount; ++i) {
            ((WindowDecorActionBar.TabImpl)this.mTabs.get(i)).setPosition(i);
         }

         if (selectedTabPosition == position) {
            this.selectTab(this.mTabs.isEmpty() ? null : (WindowDecorActionBar.TabImpl)this.mTabs.get(Math.max(0, position - 1)));
         }

      }
   }

   public void selectTab(ActionBar.Tab tab) {
      if (this.getNavigationMode() != 2) {
         this.mSavedTabPosition = tab != null ? tab.getPosition() : -1;
      } else {
         FragmentTransaction trans;
         if (this.mActivity instanceof FragmentActivity && !this.mDecorToolbar.getViewGroup().isInEditMode()) {
            trans = ((FragmentActivity)this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
         } else {
            trans = null;
         }

         if (this.mSelectedTab == tab) {
            if (this.mSelectedTab != null) {
               this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, trans);
               this.mTabScrollView.animateToTab(tab.getPosition());
            }
         } else {
            this.mTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1);
            if (this.mSelectedTab != null) {
               this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, trans);
            }

            this.mSelectedTab = (WindowDecorActionBar.TabImpl)tab;
            if (this.mSelectedTab != null) {
               this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, trans);
            }
         }

         if (trans != null && !trans.isEmpty()) {
            trans.commit();
         }

      }
   }

   public ActionBar.Tab getSelectedTab() {
      return this.mSelectedTab;
   }

   public int getHeight() {
      return this.mContainerView.getHeight();
   }

   public void enableContentAnimations(boolean enabled) {
      this.mContentAnimations = enabled;
   }

   public void show() {
      if (this.mHiddenByApp) {
         this.mHiddenByApp = false;
         this.updateVisibility(false);
      }

   }

   private void showForActionMode() {
      if (!this.mShowingForMode) {
         this.mShowingForMode = true;
         if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setShowingForActionMode(true);
         }

         this.updateVisibility(false);
      }

   }

   public void showForSystem() {
      if (this.mHiddenBySystem) {
         this.mHiddenBySystem = false;
         this.updateVisibility(true);
      }

   }

   public void hide() {
      if (!this.mHiddenByApp) {
         this.mHiddenByApp = true;
         this.updateVisibility(false);
      }

   }

   private void hideForActionMode() {
      if (this.mShowingForMode) {
         this.mShowingForMode = false;
         if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setShowingForActionMode(false);
         }

         this.updateVisibility(false);
      }

   }

   public void hideForSystem() {
      if (!this.mHiddenBySystem) {
         this.mHiddenBySystem = true;
         this.updateVisibility(true);
      }

   }

   public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
      if (hideOnContentScroll && !this.mOverlayLayout.isInOverlayMode()) {
         throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
      } else {
         this.mHideOnContentScroll = hideOnContentScroll;
         this.mOverlayLayout.setHideOnContentScrollEnabled(hideOnContentScroll);
      }
   }

   public boolean isHideOnContentScrollEnabled() {
      return this.mOverlayLayout.isHideOnContentScrollEnabled();
   }

   public int getHideOffset() {
      return this.mOverlayLayout.getActionBarHideOffset();
   }

   public void setHideOffset(int offset) {
      if (offset != 0 && !this.mOverlayLayout.isInOverlayMode()) {
         throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
      } else {
         this.mOverlayLayout.setActionBarHideOffset(offset);
      }
   }

   static boolean checkShowingFlags(boolean hiddenByApp, boolean hiddenBySystem, boolean showingForMode) {
      if (showingForMode) {
         return true;
      } else {
         return !hiddenByApp && !hiddenBySystem;
      }
   }

   private void updateVisibility(boolean fromSystem) {
      boolean shown = checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode);
      if (shown) {
         if (!this.mNowShowing) {
            this.mNowShowing = true;
            this.doShow(fromSystem);
         }
      } else if (this.mNowShowing) {
         this.mNowShowing = false;
         this.doHide(fromSystem);
      }

   }

   public void doShow(boolean fromSystem) {
      if (this.mCurrentShowAnim != null) {
         this.mCurrentShowAnim.cancel();
      }

      this.mContainerView.setVisibility(0);
      if (this.mCurWindowVisibility == 0 && (this.mShowHideAnimationEnabled || fromSystem)) {
         this.mContainerView.setTranslationY(0.0F);
         float startingY = (float)(-this.mContainerView.getHeight());
         if (fromSystem) {
            int[] topLeft = new int[]{0, 0};
            this.mContainerView.getLocationInWindow(topLeft);
            startingY -= (float)topLeft[1];
         }

         this.mContainerView.setTranslationY(startingY);
         ViewPropertyAnimatorCompatSet anim = new ViewPropertyAnimatorCompatSet();
         ViewPropertyAnimatorCompat a = ViewCompat.animate(this.mContainerView).translationY(0.0F);
         a.setUpdateListener(this.mUpdateListener);
         anim.play(a);
         if (this.mContentAnimations && this.mContentView != null) {
            this.mContentView.setTranslationY(startingY);
            anim.play(ViewCompat.animate(this.mContentView).translationY(0.0F));
         }

         anim.setInterpolator(sShowInterpolator);
         anim.setDuration(250L);
         anim.setListener(this.mShowListener);
         this.mCurrentShowAnim = anim;
         anim.start();
      } else {
         this.mContainerView.setAlpha(1.0F);
         this.mContainerView.setTranslationY(0.0F);
         if (this.mContentAnimations && this.mContentView != null) {
            this.mContentView.setTranslationY(0.0F);
         }

         this.mShowListener.onAnimationEnd((View)null);
      }

      if (this.mOverlayLayout != null) {
         ViewCompat.requestApplyInsets(this.mOverlayLayout);
      }

   }

   public void doHide(boolean fromSystem) {
      if (this.mCurrentShowAnim != null) {
         this.mCurrentShowAnim.cancel();
      }

      if (this.mCurWindowVisibility != 0 || !this.mShowHideAnimationEnabled && !fromSystem) {
         this.mHideListener.onAnimationEnd((View)null);
      } else {
         this.mContainerView.setAlpha(1.0F);
         this.mContainerView.setTransitioning(true);
         ViewPropertyAnimatorCompatSet anim = new ViewPropertyAnimatorCompatSet();
         float endingY = (float)(-this.mContainerView.getHeight());
         if (fromSystem) {
            int[] topLeft = new int[]{0, 0};
            this.mContainerView.getLocationInWindow(topLeft);
            endingY -= (float)topLeft[1];
         }

         ViewPropertyAnimatorCompat a = ViewCompat.animate(this.mContainerView).translationY(endingY);
         a.setUpdateListener(this.mUpdateListener);
         anim.play(a);
         if (this.mContentAnimations && this.mContentView != null) {
            anim.play(ViewCompat.animate(this.mContentView).translationY(endingY));
         }

         anim.setInterpolator(sHideInterpolator);
         anim.setDuration(250L);
         anim.setListener(this.mHideListener);
         this.mCurrentShowAnim = anim;
         anim.start();
      }

   }

   public boolean isShowing() {
      int height = this.getHeight();
      return this.mNowShowing && (height == 0 || this.getHideOffset() < height);
   }

   public void animateToMode(boolean toActionMode) {
      if (toActionMode) {
         this.showForActionMode();
      } else {
         this.hideForActionMode();
      }

      if (this.shouldAnimateContextView()) {
         ViewPropertyAnimatorCompat fadeIn;
         ViewPropertyAnimatorCompat fadeOut;
         if (toActionMode) {
            fadeOut = this.mDecorToolbar.setupAnimatorToVisibility(4, 100L);
            fadeIn = this.mContextView.setupAnimatorToVisibility(0, 200L);
         } else {
            fadeIn = this.mDecorToolbar.setupAnimatorToVisibility(0, 200L);
            fadeOut = this.mContextView.setupAnimatorToVisibility(8, 100L);
         }

         ViewPropertyAnimatorCompatSet set = new ViewPropertyAnimatorCompatSet();
         set.playSequentially(fadeOut, fadeIn);
         set.start();
      } else if (toActionMode) {
         this.mDecorToolbar.setVisibility(4);
         this.mContextView.setVisibility(0);
      } else {
         this.mDecorToolbar.setVisibility(0);
         this.mContextView.setVisibility(8);
      }

   }

   private boolean shouldAnimateContextView() {
      return ViewCompat.isLaidOut(this.mContainerView);
   }

   public Context getThemedContext() {
      if (this.mThemedContext == null) {
         TypedValue outValue = new TypedValue();
         Theme currentTheme = this.mContext.getTheme();
         currentTheme.resolveAttribute(attr.actionBarWidgetTheme, outValue, true);
         int targetThemeRes = outValue.resourceId;
         if (targetThemeRes != 0) {
            this.mThemedContext = new ContextThemeWrapper(this.mContext, targetThemeRes);
         } else {
            this.mThemedContext = this.mContext;
         }
      }

      return this.mThemedContext;
   }

   public boolean isTitleTruncated() {
      return this.mDecorToolbar != null && this.mDecorToolbar.isTitleTruncated();
   }

   public void setHomeAsUpIndicator(Drawable indicator) {
      this.mDecorToolbar.setNavigationIcon(indicator);
   }

   public void setHomeAsUpIndicator(int resId) {
      this.mDecorToolbar.setNavigationIcon(resId);
   }

   public void setHomeActionContentDescription(CharSequence description) {
      this.mDecorToolbar.setNavigationContentDescription(description);
   }

   public void setHomeActionContentDescription(int resId) {
      this.mDecorToolbar.setNavigationContentDescription(resId);
   }

   public void onContentScrollStarted() {
      if (this.mCurrentShowAnim != null) {
         this.mCurrentShowAnim.cancel();
         this.mCurrentShowAnim = null;
      }

   }

   public void onContentScrollStopped() {
   }

   public boolean collapseActionView() {
      if (this.mDecorToolbar != null && this.mDecorToolbar.hasExpandedActionView()) {
         this.mDecorToolbar.collapseActionView();
         return true;
      } else {
         return false;
      }
   }

   public void setCustomView(View view) {
      this.mDecorToolbar.setCustomView(view);
   }

   public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
      view.setLayoutParams(layoutParams);
      this.mDecorToolbar.setCustomView(view);
   }

   public void setListNavigationCallbacks(SpinnerAdapter adapter, ActionBar.OnNavigationListener callback) {
      this.mDecorToolbar.setDropdownParams(adapter, new NavItemSelectedListener(callback));
   }

   public int getSelectedNavigationIndex() {
      switch(this.mDecorToolbar.getNavigationMode()) {
      case 1:
         return this.mDecorToolbar.getDropdownSelectedPosition();
      case 2:
         return this.mSelectedTab != null ? this.mSelectedTab.getPosition() : -1;
      default:
         return -1;
      }
   }

   public int getNavigationItemCount() {
      switch(this.mDecorToolbar.getNavigationMode()) {
      case 1:
         return this.mDecorToolbar.getDropdownItemCount();
      case 2:
         return this.mTabs.size();
      default:
         return 0;
      }
   }

   public int getTabCount() {
      return this.mTabs.size();
   }

   public void setNavigationMode(int mode) {
      int oldMode = this.mDecorToolbar.getNavigationMode();
      switch(oldMode) {
      case 2:
         this.mSavedTabPosition = this.getSelectedNavigationIndex();
         this.selectTab((ActionBar.Tab)null);
         this.mTabScrollView.setVisibility(8);
      }

      if (oldMode != mode && !this.mHasEmbeddedTabs && this.mOverlayLayout != null) {
         ViewCompat.requestApplyInsets(this.mOverlayLayout);
      }

      this.mDecorToolbar.setNavigationMode(mode);
      switch(mode) {
      case 2:
         this.ensureTabsExist();
         this.mTabScrollView.setVisibility(0);
         if (this.mSavedTabPosition != -1) {
            this.setSelectedNavigationItem(this.mSavedTabPosition);
            this.mSavedTabPosition = -1;
         }
      }

      this.mDecorToolbar.setCollapsible(mode == 2 && !this.mHasEmbeddedTabs);
      this.mOverlayLayout.setHasNonEmbeddedTabs(mode == 2 && !this.mHasEmbeddedTabs);
   }

   public ActionBar.Tab getTabAt(int index) {
      return (ActionBar.Tab)this.mTabs.get(index);
   }

   public void setIcon(int resId) {
      this.mDecorToolbar.setIcon(resId);
   }

   public void setIcon(Drawable icon) {
      this.mDecorToolbar.setIcon(icon);
   }

   public boolean hasIcon() {
      return this.mDecorToolbar.hasIcon();
   }

   public void setLogo(int resId) {
      this.mDecorToolbar.setLogo(resId);
   }

   public void setLogo(Drawable logo) {
      this.mDecorToolbar.setLogo(logo);
   }

   public boolean hasLogo() {
      return this.mDecorToolbar.hasLogo();
   }

   public void setDefaultDisplayHomeAsUpEnabled(boolean enable) {
      if (!this.mDisplayHomeAsUpSet) {
         this.setDisplayHomeAsUpEnabled(enable);
      }

   }

   public boolean onKeyShortcut(int keyCode, KeyEvent event) {
      if (this.mActionMode == null) {
         return false;
      } else {
         Menu menu = this.mActionMode.getMenu();
         if (menu != null) {
            KeyCharacterMap kmap = KeyCharacterMap.load(event != null ? event.getDeviceId() : -1);
            menu.setQwertyMode(kmap.getKeyboardType() != 1);
            return menu.performShortcut(keyCode, event, 0);
         } else {
            return false;
         }
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public class TabImpl extends ActionBar.Tab {
      private ActionBar.TabListener mCallback;
      private Object mTag;
      private Drawable mIcon;
      private CharSequence mText;
      private CharSequence mContentDesc;
      private int mPosition = -1;
      private View mCustomView;

      public Object getTag() {
         return this.mTag;
      }

      public ActionBar.Tab setTag(Object tag) {
         this.mTag = tag;
         return this;
      }

      public ActionBar.TabListener getCallback() {
         return this.mCallback;
      }

      public ActionBar.Tab setTabListener(ActionBar.TabListener callback) {
         this.mCallback = callback;
         return this;
      }

      public View getCustomView() {
         return this.mCustomView;
      }

      public ActionBar.Tab setCustomView(View view) {
         this.mCustomView = view;
         if (this.mPosition >= 0) {
            WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
         }

         return this;
      }

      public ActionBar.Tab setCustomView(int layoutResId) {
         return this.setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(layoutResId, (ViewGroup)null));
      }

      public Drawable getIcon() {
         return this.mIcon;
      }

      public int getPosition() {
         return this.mPosition;
      }

      public void setPosition(int position) {
         this.mPosition = position;
      }

      public CharSequence getText() {
         return this.mText;
      }

      public ActionBar.Tab setIcon(Drawable icon) {
         this.mIcon = icon;
         if (this.mPosition >= 0) {
            WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
         }

         return this;
      }

      public ActionBar.Tab setIcon(int resId) {
         return this.setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.mContext, resId));
      }

      public ActionBar.Tab setText(CharSequence text) {
         this.mText = text;
         if (this.mPosition >= 0) {
            WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
         }

         return this;
      }

      public ActionBar.Tab setText(int resId) {
         return this.setText(WindowDecorActionBar.this.mContext.getResources().getText(resId));
      }

      public void select() {
         WindowDecorActionBar.this.selectTab(this);
      }

      public ActionBar.Tab setContentDescription(int resId) {
         return this.setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(resId));
      }

      public ActionBar.Tab setContentDescription(CharSequence contentDesc) {
         this.mContentDesc = contentDesc;
         if (this.mPosition >= 0) {
            WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
         }

         return this;
      }

      public CharSequence getContentDescription() {
         return this.mContentDesc;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
      private final Context mActionModeContext;
      private final MenuBuilder mMenu;
      private ActionMode.Callback mCallback;
      private WeakReference mCustomView;

      public ActionModeImpl(Context context, ActionMode.Callback callback) {
         this.mActionModeContext = context;
         this.mCallback = callback;
         this.mMenu = (new MenuBuilder(context)).setDefaultShowAsAction(1);
         this.mMenu.setCallback(this);
      }

      public MenuInflater getMenuInflater() {
         return new SupportMenuInflater(this.mActionModeContext);
      }

      public Menu getMenu() {
         return this.mMenu;
      }

      public void finish() {
         if (WindowDecorActionBar.this.mActionMode == this) {
            if (!WindowDecorActionBar.checkShowingFlags(WindowDecorActionBar.this.mHiddenByApp, WindowDecorActionBar.this.mHiddenBySystem, false)) {
               WindowDecorActionBar.this.mDeferredDestroyActionMode = this;
               WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
            } else {
               this.mCallback.onDestroyActionMode(this);
            }

            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            WindowDecorActionBar.this.mContextView.closeMode();
            WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
            WindowDecorActionBar.this.mOverlayLayout.setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
         }
      }

      public void invalidate() {
         if (WindowDecorActionBar.this.mActionMode == this) {
            this.mMenu.stopDispatchingItemsChanged();

            try {
               this.mCallback.onPrepareActionMode(this, this.mMenu);
            } finally {
               this.mMenu.startDispatchingItemsChanged();
            }

         }
      }

      public boolean dispatchOnCreate() {
         this.mMenu.stopDispatchingItemsChanged();

         boolean var1;
         try {
            var1 = this.mCallback.onCreateActionMode(this, this.mMenu);
         } finally {
            this.mMenu.startDispatchingItemsChanged();
         }

         return var1;
      }

      public void setCustomView(View view) {
         WindowDecorActionBar.this.mContextView.setCustomView(view);
         this.mCustomView = new WeakReference(view);
      }

      public void setSubtitle(CharSequence subtitle) {
         WindowDecorActionBar.this.mContextView.setSubtitle(subtitle);
      }

      public void setTitle(CharSequence title) {
         WindowDecorActionBar.this.mContextView.setTitle(title);
      }

      public void setTitle(int resId) {
         this.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(resId));
      }

      public void setSubtitle(int resId) {
         this.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(resId));
      }

      public CharSequence getTitle() {
         return WindowDecorActionBar.this.mContextView.getTitle();
      }

      public CharSequence getSubtitle() {
         return WindowDecorActionBar.this.mContextView.getSubtitle();
      }

      public void setTitleOptionalHint(boolean titleOptional) {
         super.setTitleOptionalHint(titleOptional);
         WindowDecorActionBar.this.mContextView.setTitleOptional(titleOptional);
      }

      public boolean isTitleOptional() {
         return WindowDecorActionBar.this.mContextView.isTitleOptional();
      }

      public View getCustomView() {
         return this.mCustomView != null ? (View)this.mCustomView.get() : null;
      }

      public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
         return this.mCallback != null ? this.mCallback.onActionItemClicked(this, item) : false;
      }

      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      }

      public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
         if (this.mCallback == null) {
            return false;
         } else if (!subMenu.hasVisibleItems()) {
            return true;
         } else {
            (new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenu)).show();
            return true;
         }
      }

      public void onCloseSubMenu(SubMenuBuilder menu) {
      }

      public void onMenuModeChange(MenuBuilder menu) {
         if (this.mCallback != null) {
            this.invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu();
         }
      }
   }
}
