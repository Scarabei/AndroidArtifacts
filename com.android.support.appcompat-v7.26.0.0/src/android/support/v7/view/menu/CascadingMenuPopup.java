package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.layout;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, OnKeyListener, OnDismissListener {
   static final int HORIZ_POSITION_LEFT = 0;
   static final int HORIZ_POSITION_RIGHT = 1;
   static final int SUBMENU_TIMEOUT_MS = 200;
   private final Context mContext;
   private final int mMenuMaxWidth;
   private final int mPopupStyleAttr;
   private final int mPopupStyleRes;
   private final boolean mOverflowOnly;
   final Handler mSubMenuHoverHandler;
   private final List mPendingMenus = new LinkedList();
   final List mShowingMenus = new ArrayList();
   private final OnGlobalLayoutListener mGlobalLayoutListener = new OnGlobalLayoutListener() {
      public void onGlobalLayout() {
         if (CascadingMenuPopup.this.isShowing() && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !((CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(0)).window.isModal()) {
            View anchor = CascadingMenuPopup.this.mShownAnchorView;
            if (anchor != null && anchor.isShown()) {
               Iterator var2 = CascadingMenuPopup.this.mShowingMenus.iterator();

               while(var2.hasNext()) {
                  CascadingMenuPopup.CascadingMenuInfo info = (CascadingMenuPopup.CascadingMenuInfo)var2.next();
                  info.window.show();
               }
            } else {
               CascadingMenuPopup.this.dismiss();
            }
         }

      }
   };
   private final OnAttachStateChangeListener mAttachStateChangeListener = new OnAttachStateChangeListener() {
      public void onViewAttachedToWindow(View v) {
      }

      public void onViewDetachedFromWindow(View v) {
         if (CascadingMenuPopup.this.mTreeObserver != null) {
            if (!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
               CascadingMenuPopup.this.mTreeObserver = v.getViewTreeObserver();
            }

            CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
         }

         v.removeOnAttachStateChangeListener(this);
      }
   };
   private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener() {
      public void onItemHoverExit(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
         CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menu);
      }

      public void onItemHoverEnter(@NonNull final MenuBuilder menu, @NonNull final MenuItem item) {
         CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages((Object)null);
         int menuIndex = -1;
         int i = 0;

         int nextIndex;
         for(nextIndex = CascadingMenuPopup.this.mShowingMenus.size(); i < nextIndex; ++i) {
            if (menu == ((CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(i)).menu) {
               menuIndex = i;
               break;
            }
         }

         if (menuIndex != -1) {
            nextIndex = menuIndex + 1;
            final CascadingMenuPopup.CascadingMenuInfo nextInfo;
            if (nextIndex < CascadingMenuPopup.this.mShowingMenus.size()) {
               nextInfo = (CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(nextIndex);
            } else {
               nextInfo = null;
            }

            Runnable runnable = new Runnable() {
               public void run() {
                  if (nextInfo != null) {
                     CascadingMenuPopup.this.mShouldCloseImmediately = true;
                     nextInfo.menu.close(false);
                     CascadingMenuPopup.this.mShouldCloseImmediately = false;
                  }

                  if (item.isEnabled() && item.hasSubMenu()) {
                     menu.performItemAction(item, 4);
                  }

               }
            };
            long uptimeMillis = SystemClock.uptimeMillis() + 200L;
            CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(runnable, menu, uptimeMillis);
         }
      }
   };
   private int mRawDropDownGravity = 0;
   private int mDropDownGravity = 0;
   private View mAnchorView;
   View mShownAnchorView;
   private int mLastPosition;
   private boolean mHasXOffset;
   private boolean mHasYOffset;
   private int mXOffset;
   private int mYOffset;
   private boolean mForceShowIcon;
   private boolean mShowTitle;
   private MenuPresenter.Callback mPresenterCallback;
   private ViewTreeObserver mTreeObserver;
   private OnDismissListener mOnDismissListener;
   boolean mShouldCloseImmediately;

   public CascadingMenuPopup(@NonNull Context context, @NonNull View anchor, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes, boolean overflowOnly) {
      this.mContext = context;
      this.mAnchorView = anchor;
      this.mPopupStyleAttr = popupStyleAttr;
      this.mPopupStyleRes = popupStyleRes;
      this.mOverflowOnly = overflowOnly;
      this.mForceShowIcon = false;
      this.mLastPosition = this.getInitialMenuPosition();
      Resources res = context.getResources();
      this.mMenuMaxWidth = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
      this.mSubMenuHoverHandler = new Handler();
   }

   public void setForceShowIcon(boolean forceShow) {
      this.mForceShowIcon = forceShow;
   }

   private MenuPopupWindow createPopupWindow() {
      MenuPopupWindow popupWindow = new MenuPopupWindow(this.mContext, (AttributeSet)null, this.mPopupStyleAttr, this.mPopupStyleRes);
      popupWindow.setHoverListener(this.mMenuItemHoverListener);
      popupWindow.setOnItemClickListener(this);
      popupWindow.setOnDismissListener(this);
      popupWindow.setAnchorView(this.mAnchorView);
      popupWindow.setDropDownGravity(this.mDropDownGravity);
      popupWindow.setModal(true);
      popupWindow.setInputMethodMode(2);
      return popupWindow;
   }

   public void show() {
      if (!this.isShowing()) {
         Iterator var1 = this.mPendingMenus.iterator();

         while(var1.hasNext()) {
            MenuBuilder menu = (MenuBuilder)var1.next();
            this.showMenu(menu);
         }

         this.mPendingMenus.clear();
         this.mShownAnchorView = this.mAnchorView;
         if (this.mShownAnchorView != null) {
            boolean addGlobalListener = this.mTreeObserver == null;
            this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            if (addGlobalListener) {
               this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
            }

            this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
         }

      }
   }

   public void dismiss() {
      int length = this.mShowingMenus.size();
      if (length > 0) {
         CascadingMenuPopup.CascadingMenuInfo[] addedMenus = (CascadingMenuPopup.CascadingMenuInfo[])this.mShowingMenus.toArray(new CascadingMenuPopup.CascadingMenuInfo[length]);

         for(int i = length - 1; i >= 0; --i) {
            CascadingMenuPopup.CascadingMenuInfo info = addedMenus[i];
            if (info.window.isShowing()) {
               info.window.dismiss();
            }
         }
      }

   }

   public boolean onKey(View v, int keyCode, KeyEvent event) {
      if (event.getAction() == 1 && keyCode == 82) {
         this.dismiss();
         return true;
      } else {
         return false;
      }
   }

   private int getInitialMenuPosition() {
      int layoutDirection = ViewCompat.getLayoutDirection(this.mAnchorView);
      return layoutDirection == 1 ? 0 : 1;
   }

   private int getNextMenuPosition(int nextMenuWidth) {
      ListView lastListView = ((CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
      int[] screenLocation = new int[2];
      lastListView.getLocationOnScreen(screenLocation);
      Rect displayFrame = new Rect();
      this.mShownAnchorView.getWindowVisibleDisplayFrame(displayFrame);
      int left;
      if (this.mLastPosition == 1) {
         left = screenLocation[0] + lastListView.getWidth() + nextMenuWidth;
         return left > displayFrame.right ? 0 : 1;
      } else {
         left = screenLocation[0] - nextMenuWidth;
         return left < 0 ? 1 : 0;
      }
   }

   public void addMenu(MenuBuilder menu) {
      menu.addMenuPresenter(this, this.mContext);
      if (this.isShowing()) {
         this.showMenu(menu);
      } else {
         this.mPendingMenus.add(menu);
      }

   }

   private void showMenu(@NonNull MenuBuilder menu) {
      LayoutInflater inflater = LayoutInflater.from(this.mContext);
      MenuAdapter adapter = new MenuAdapter(menu, inflater, this.mOverflowOnly);
      if (!this.isShowing() && this.mForceShowIcon) {
         adapter.setForceShowIcon(true);
      } else if (this.isShowing()) {
         adapter.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menu));
      }

      int menuWidth = measureIndividualMenuWidth(adapter, (ViewGroup)null, this.mContext, this.mMenuMaxWidth);
      MenuPopupWindow popupWindow = this.createPopupWindow();
      popupWindow.setAdapter(adapter);
      popupWindow.setContentWidth(menuWidth);
      popupWindow.setDropDownGravity(this.mDropDownGravity);
      CascadingMenuPopup.CascadingMenuInfo parentInfo;
      View parentView;
      if (this.mShowingMenus.size() > 0) {
         parentInfo = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1);
         parentView = this.findParentViewForSubmenu(parentInfo, menu);
      } else {
         parentInfo = null;
         parentView = null;
      }

      if (parentView != null) {
         popupWindow.setTouchModal(false);
         popupWindow.setEnterTransition((Object)null);
         int nextMenuPosition = this.getNextMenuPosition(menuWidth);
         boolean showOnRight = nextMenuPosition == 1;
         this.mLastPosition = nextMenuPosition;
         int parentOffsetLeft;
         int parentOffsetTop;
         if (VERSION.SDK_INT >= 26) {
            popupWindow.setAnchorView(parentView);
            parentOffsetLeft = 0;
            parentOffsetTop = 0;
         } else {
            int[] anchorScreenLocation = new int[2];
            this.mAnchorView.getLocationOnScreen(anchorScreenLocation);
            int[] parentViewScreenLocation = new int[2];
            parentView.getLocationOnScreen(parentViewScreenLocation);
            parentOffsetLeft = parentViewScreenLocation[0] - anchorScreenLocation[0];
            parentOffsetTop = parentViewScreenLocation[1] - anchorScreenLocation[1];
         }

         int x;
         if ((this.mDropDownGravity & 5) == 5) {
            if (showOnRight) {
               x = parentOffsetLeft + menuWidth;
            } else {
               x = parentOffsetLeft - parentView.getWidth();
            }
         } else if (showOnRight) {
            x = parentOffsetLeft + parentView.getWidth();
         } else {
            x = parentOffsetLeft - menuWidth;
         }

         popupWindow.setHorizontalOffset(x);
         popupWindow.setOverlapAnchor(true);
         popupWindow.setVerticalOffset(parentOffsetTop);
      } else {
         if (this.mHasXOffset) {
            popupWindow.setHorizontalOffset(this.mXOffset);
         }

         if (this.mHasYOffset) {
            popupWindow.setVerticalOffset(this.mYOffset);
         }

         Rect epicenterBounds = this.getEpicenterBounds();
         popupWindow.setEpicenterBounds(epicenterBounds);
      }

      CascadingMenuPopup.CascadingMenuInfo menuInfo = new CascadingMenuPopup.CascadingMenuInfo(popupWindow, menu, this.mLastPosition);
      this.mShowingMenus.add(menuInfo);
      popupWindow.show();
      ListView listView = popupWindow.getListView();
      listView.setOnKeyListener(this);
      if (parentInfo == null && this.mShowTitle && menu.getHeaderTitle() != null) {
         FrameLayout titleItemView = (FrameLayout)inflater.inflate(layout.abc_popup_menu_header_item_layout, listView, false);
         TextView titleView = (TextView)titleItemView.findViewById(16908310);
         titleItemView.setEnabled(false);
         titleView.setText(menu.getHeaderTitle());
         listView.addHeaderView(titleItemView, (Object)null, false);
         popupWindow.show();
      }

   }

   private MenuItem findMenuItemForSubmenu(@NonNull MenuBuilder parent, @NonNull MenuBuilder submenu) {
      int i = 0;

      for(int count = parent.size(); i < count; ++i) {
         MenuItem item = parent.getItem(i);
         if (item.hasSubMenu() && submenu == item.getSubMenu()) {
            return item;
         }
      }

      return null;
   }

   @Nullable
   private View findParentViewForSubmenu(@NonNull CascadingMenuPopup.CascadingMenuInfo parentInfo, @NonNull MenuBuilder submenu) {
      MenuItem owner = this.findMenuItemForSubmenu(parentInfo.menu, submenu);
      if (owner == null) {
         return null;
      } else {
         ListView listView = parentInfo.getListView();
         ListAdapter listAdapter = listView.getAdapter();
         int headersCount;
         MenuAdapter menuAdapter;
         if (listAdapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter)listAdapter;
            headersCount = headerAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter)headerAdapter.getWrappedAdapter();
         } else {
            headersCount = 0;
            menuAdapter = (MenuAdapter)listAdapter;
         }

         int ownerPosition = -1;
         int ownerViewPosition = 0;

         for(int count = menuAdapter.getCount(); ownerViewPosition < count; ++ownerViewPosition) {
            if (owner == menuAdapter.getItem(ownerViewPosition)) {
               ownerPosition = ownerViewPosition;
               break;
            }
         }

         if (ownerPosition == -1) {
            return null;
         } else {
            ownerPosition += headersCount;
            ownerViewPosition = ownerPosition - listView.getFirstVisiblePosition();
            return ownerViewPosition >= 0 && ownerViewPosition < listView.getChildCount() ? listView.getChildAt(ownerViewPosition) : null;
         }
      }
   }

   public boolean isShowing() {
      return this.mShowingMenus.size() > 0 && ((CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(0)).window.isShowing();
   }

   public void onDismiss() {
      CascadingMenuPopup.CascadingMenuInfo dismissedInfo = null;
      int i = 0;

      for(int count = this.mShowingMenus.size(); i < count; ++i) {
         CascadingMenuPopup.CascadingMenuInfo info = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(i);
         if (!info.window.isShowing()) {
            dismissedInfo = info;
            break;
         }
      }

      if (dismissedInfo != null) {
         dismissedInfo.menu.close(false);
      }

   }

   public void updateMenuView(boolean cleared) {
      Iterator var2 = this.mShowingMenus.iterator();

      while(var2.hasNext()) {
         CascadingMenuPopup.CascadingMenuInfo info = (CascadingMenuPopup.CascadingMenuInfo)var2.next();
         toMenuAdapter(info.getListView().getAdapter()).notifyDataSetChanged();
      }

   }

   public void setCallback(MenuPresenter.Callback cb) {
      this.mPresenterCallback = cb;
   }

   public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
      Iterator var2 = this.mShowingMenus.iterator();

      CascadingMenuPopup.CascadingMenuInfo info;
      do {
         if (!var2.hasNext()) {
            if (subMenu.hasVisibleItems()) {
               this.addMenu(subMenu);
               if (this.mPresenterCallback != null) {
                  this.mPresenterCallback.onOpenSubMenu(subMenu);
               }

               return true;
            }

            return false;
         }

         info = (CascadingMenuPopup.CascadingMenuInfo)var2.next();
      } while(subMenu != info.menu);

      info.getListView().requestFocus();
      return true;
   }

   private int findIndexOfAddedMenu(@NonNull MenuBuilder menu) {
      int i = 0;

      for(int count = this.mShowingMenus.size(); i < count; ++i) {
         CascadingMenuPopup.CascadingMenuInfo info = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(i);
         if (menu == info.menu) {
            return i;
         }
      }

      return -1;
   }

   public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      int menuIndex = this.findIndexOfAddedMenu(menu);
      if (menuIndex >= 0) {
         int nextMenuIndex = menuIndex + 1;
         CascadingMenuPopup.CascadingMenuInfo info;
         if (nextMenuIndex < this.mShowingMenus.size()) {
            info = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(nextMenuIndex);
            info.menu.close(false);
         }

         info = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.remove(menuIndex);
         info.menu.removeMenuPresenter(this);
         if (this.mShouldCloseImmediately) {
            info.window.setExitTransition((Object)null);
            info.window.setAnimationStyle(0);
         }

         info.window.dismiss();
         int count = this.mShowingMenus.size();
         if (count > 0) {
            this.mLastPosition = ((CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(count - 1)).position;
         } else {
            this.mLastPosition = this.getInitialMenuPosition();
         }

         if (count == 0) {
            this.dismiss();
            if (this.mPresenterCallback != null) {
               this.mPresenterCallback.onCloseMenu(menu, true);
            }

            if (this.mTreeObserver != null) {
               if (this.mTreeObserver.isAlive()) {
                  this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
               }

               this.mTreeObserver = null;
            }

            this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
            this.mOnDismissListener.onDismiss();
         } else if (allMenusAreClosing) {
            CascadingMenuPopup.CascadingMenuInfo rootInfo = (CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(0);
            rootInfo.menu.close(false);
         }

      }
   }

   public boolean flagActionItems() {
      return false;
   }

   public Parcelable onSaveInstanceState() {
      return null;
   }

   public void onRestoreInstanceState(Parcelable state) {
   }

   public void setGravity(int dropDownGravity) {
      if (this.mRawDropDownGravity != dropDownGravity) {
         this.mRawDropDownGravity = dropDownGravity;
         this.mDropDownGravity = GravityCompat.getAbsoluteGravity(dropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
      }

   }

   public void setAnchorView(@NonNull View anchor) {
      if (this.mAnchorView != anchor) {
         this.mAnchorView = anchor;
         this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
      }

   }

   public void setOnDismissListener(OnDismissListener listener) {
      this.mOnDismissListener = listener;
   }

   public ListView getListView() {
      return this.mShowingMenus.isEmpty() ? null : ((CascadingMenuPopup.CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
   }

   public void setHorizontalOffset(int x) {
      this.mHasXOffset = true;
      this.mXOffset = x;
   }

   public void setVerticalOffset(int y) {
      this.mHasYOffset = true;
      this.mYOffset = y;
   }

   public void setShowTitle(boolean showTitle) {
      this.mShowTitle = showTitle;
   }

   protected boolean closeMenuOnSubMenuOpened() {
      return false;
   }

   private static class CascadingMenuInfo {
      public final MenuPopupWindow window;
      public final MenuBuilder menu;
      public final int position;

      public CascadingMenuInfo(@NonNull MenuPopupWindow window, @NonNull MenuBuilder menu, int position) {
         this.window = window;
         this.menu = menu;
         this.position = position;
      }

      public ListView getListView() {
         return this.window.getListView();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface HorizPosition {
   }
}
