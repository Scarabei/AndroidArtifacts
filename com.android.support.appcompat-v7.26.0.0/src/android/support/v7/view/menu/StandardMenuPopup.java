package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.layout;
import android.support.v7.widget.MenuPopupWindow;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

final class StandardMenuPopup extends MenuPopup implements OnDismissListener, OnItemClickListener, MenuPresenter, OnKeyListener {
   private final Context mContext;
   private final MenuBuilder mMenu;
   private final MenuAdapter mAdapter;
   private final boolean mOverflowOnly;
   private final int mPopupMaxWidth;
   private final int mPopupStyleAttr;
   private final int mPopupStyleRes;
   final MenuPopupWindow mPopup;
   private final OnGlobalLayoutListener mGlobalLayoutListener = new OnGlobalLayoutListener() {
      public void onGlobalLayout() {
         if (StandardMenuPopup.this.isShowing() && !StandardMenuPopup.this.mPopup.isModal()) {
            View anchor = StandardMenuPopup.this.mShownAnchorView;
            if (anchor != null && anchor.isShown()) {
               StandardMenuPopup.this.mPopup.show();
            } else {
               StandardMenuPopup.this.dismiss();
            }
         }

      }
   };
   private final OnAttachStateChangeListener mAttachStateChangeListener = new OnAttachStateChangeListener() {
      public void onViewAttachedToWindow(View v) {
      }

      public void onViewDetachedFromWindow(View v) {
         if (StandardMenuPopup.this.mTreeObserver != null) {
            if (!StandardMenuPopup.this.mTreeObserver.isAlive()) {
               StandardMenuPopup.this.mTreeObserver = v.getViewTreeObserver();
            }

            StandardMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(StandardMenuPopup.this.mGlobalLayoutListener);
         }

         v.removeOnAttachStateChangeListener(this);
      }
   };
   private OnDismissListener mOnDismissListener;
   private View mAnchorView;
   View mShownAnchorView;
   private MenuPresenter.Callback mPresenterCallback;
   private ViewTreeObserver mTreeObserver;
   private boolean mWasDismissed;
   private boolean mHasContentWidth;
   private int mContentWidth;
   private int mDropDownGravity = 0;
   private boolean mShowTitle;

   public StandardMenuPopup(Context context, MenuBuilder menu, View anchorView, int popupStyleAttr, int popupStyleRes, boolean overflowOnly) {
      this.mContext = context;
      this.mMenu = menu;
      this.mOverflowOnly = overflowOnly;
      LayoutInflater inflater = LayoutInflater.from(context);
      this.mAdapter = new MenuAdapter(menu, inflater, this.mOverflowOnly);
      this.mPopupStyleAttr = popupStyleAttr;
      this.mPopupStyleRes = popupStyleRes;
      Resources res = context.getResources();
      this.mPopupMaxWidth = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
      this.mAnchorView = anchorView;
      this.mPopup = new MenuPopupWindow(this.mContext, (AttributeSet)null, this.mPopupStyleAttr, this.mPopupStyleRes);
      menu.addMenuPresenter(this, context);
   }

   public void setForceShowIcon(boolean forceShow) {
      this.mAdapter.setForceShowIcon(forceShow);
   }

   public void setGravity(int gravity) {
      this.mDropDownGravity = gravity;
   }

   private boolean tryShow() {
      if (this.isShowing()) {
         return true;
      } else if (!this.mWasDismissed && this.mAnchorView != null) {
         this.mShownAnchorView = this.mAnchorView;
         this.mPopup.setOnDismissListener(this);
         this.mPopup.setOnItemClickListener(this);
         this.mPopup.setModal(true);
         View anchor = this.mShownAnchorView;
         boolean addGlobalListener = this.mTreeObserver == null;
         this.mTreeObserver = anchor.getViewTreeObserver();
         if (addGlobalListener) {
            this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
         }

         anchor.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
         this.mPopup.setAnchorView(anchor);
         this.mPopup.setDropDownGravity(this.mDropDownGravity);
         if (!this.mHasContentWidth) {
            this.mContentWidth = measureIndividualMenuWidth(this.mAdapter, (ViewGroup)null, this.mContext, this.mPopupMaxWidth);
            this.mHasContentWidth = true;
         }

         this.mPopup.setContentWidth(this.mContentWidth);
         this.mPopup.setInputMethodMode(2);
         this.mPopup.setEpicenterBounds(this.getEpicenterBounds());
         this.mPopup.show();
         ListView listView = this.mPopup.getListView();
         listView.setOnKeyListener(this);
         if (this.mShowTitle && this.mMenu.getHeaderTitle() != null) {
            FrameLayout titleItemView = (FrameLayout)LayoutInflater.from(this.mContext).inflate(layout.abc_popup_menu_header_item_layout, listView, false);
            TextView titleView = (TextView)titleItemView.findViewById(16908310);
            if (titleView != null) {
               titleView.setText(this.mMenu.getHeaderTitle());
            }

            titleItemView.setEnabled(false);
            listView.addHeaderView(titleItemView, (Object)null, false);
         }

         this.mPopup.setAdapter(this.mAdapter);
         this.mPopup.show();
         return true;
      } else {
         return false;
      }
   }

   public void show() {
      if (!this.tryShow()) {
         throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
      }
   }

   public void dismiss() {
      if (this.isShowing()) {
         this.mPopup.dismiss();
      }

   }

   public void addMenu(MenuBuilder menu) {
   }

   public boolean isShowing() {
      return !this.mWasDismissed && this.mPopup.isShowing();
   }

   public void onDismiss() {
      this.mWasDismissed = true;
      this.mMenu.close();
      if (this.mTreeObserver != null) {
         if (!this.mTreeObserver.isAlive()) {
            this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
         }

         this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
         this.mTreeObserver = null;
      }

      this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
      if (this.mOnDismissListener != null) {
         this.mOnDismissListener.onDismiss();
      }

   }

   public void updateMenuView(boolean cleared) {
      this.mHasContentWidth = false;
      if (this.mAdapter != null) {
         this.mAdapter.notifyDataSetChanged();
      }

   }

   public void setCallback(MenuPresenter.Callback cb) {
      this.mPresenterCallback = cb;
   }

   public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
      if (subMenu.hasVisibleItems()) {
         MenuPopupHelper subPopup = new MenuPopupHelper(this.mContext, subMenu, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
         subPopup.setPresenterCallback(this.mPresenterCallback);
         subPopup.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenu));
         subPopup.setGravity(this.mDropDownGravity);
         subPopup.setOnDismissListener(this.mOnDismissListener);
         this.mOnDismissListener = null;
         this.mMenu.close(false);
         int horizontalOffset = this.mPopup.getHorizontalOffset();
         int verticalOffset = this.mPopup.getVerticalOffset();
         if (subPopup.tryShow(horizontalOffset, verticalOffset)) {
            if (this.mPresenterCallback != null) {
               this.mPresenterCallback.onOpenSubMenu(subMenu);
            }

            return true;
         }
      }

      return false;
   }

   public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      if (menu == this.mMenu) {
         this.dismiss();
         if (this.mPresenterCallback != null) {
            this.mPresenterCallback.onCloseMenu(menu, allMenusAreClosing);
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

   public void setAnchorView(View anchor) {
      this.mAnchorView = anchor;
   }

   public boolean onKey(View v, int keyCode, KeyEvent event) {
      if (event.getAction() == 1 && keyCode == 82) {
         this.dismiss();
         return true;
      } else {
         return false;
      }
   }

   public void setOnDismissListener(OnDismissListener listener) {
      this.mOnDismissListener = listener;
   }

   public ListView getListView() {
      return this.mPopup.getListView();
   }

   public void setHorizontalOffset(int x) {
      this.mPopup.setHorizontalOffset(x);
   }

   public void setVerticalOffset(int y) {
      this.mPopup.setVerticalOffset(y);
   }

   public void setShowTitle(boolean showTitle) {
      this.mShowTitle = showTitle;
   }
}
