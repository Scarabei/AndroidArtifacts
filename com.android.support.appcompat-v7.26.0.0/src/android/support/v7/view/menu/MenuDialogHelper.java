package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.appcompat.R.layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.KeyEvent.DispatcherState;
import android.view.WindowManager.LayoutParams;

class MenuDialogHelper implements OnKeyListener, OnClickListener, OnDismissListener, MenuPresenter.Callback {
   private MenuBuilder mMenu;
   private AlertDialog mDialog;
   ListMenuPresenter mPresenter;
   private MenuPresenter.Callback mPresenterCallback;

   public MenuDialogHelper(MenuBuilder menu) {
      this.mMenu = menu;
   }

   public void show(IBinder windowToken) {
      MenuBuilder menu = this.mMenu;
      AlertDialog.Builder builder = new AlertDialog.Builder(menu.getContext());
      this.mPresenter = new ListMenuPresenter(builder.getContext(), layout.abc_list_menu_item_layout);
      this.mPresenter.setCallback(this);
      this.mMenu.addMenuPresenter(this.mPresenter);
      builder.setAdapter(this.mPresenter.getAdapter(), this);
      View headerView = menu.getHeaderView();
      if (headerView != null) {
         builder.setCustomTitle(headerView);
      } else {
         builder.setIcon(menu.getHeaderIcon()).setTitle(menu.getHeaderTitle());
      }

      builder.setOnKeyListener(this);
      this.mDialog = builder.create();
      this.mDialog.setOnDismissListener(this);
      LayoutParams lp = this.mDialog.getWindow().getAttributes();
      lp.type = 1003;
      if (windowToken != null) {
         lp.token = windowToken;
      }

      lp.flags |= 131072;
      this.mDialog.show();
   }

   public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
      if (keyCode == 82 || keyCode == 4) {
         Window win;
         View decor;
         DispatcherState ds;
         if (event.getAction() == 0 && event.getRepeatCount() == 0) {
            win = this.mDialog.getWindow();
            if (win != null) {
               decor = win.getDecorView();
               if (decor != null) {
                  ds = decor.getKeyDispatcherState();
                  if (ds != null) {
                     ds.startTracking(event, this);
                     return true;
                  }
               }
            }
         } else if (event.getAction() == 1 && !event.isCanceled()) {
            win = this.mDialog.getWindow();
            if (win != null) {
               decor = win.getDecorView();
               if (decor != null) {
                  ds = decor.getKeyDispatcherState();
                  if (ds != null && ds.isTracking(event)) {
                     this.mMenu.close(true);
                     dialog.dismiss();
                     return true;
                  }
               }
            }
         }
      }

      return this.mMenu.performShortcut(keyCode, event, 0);
   }

   public void setPresenterCallback(MenuPresenter.Callback cb) {
      this.mPresenterCallback = cb;
   }

   public void dismiss() {
      if (this.mDialog != null) {
         this.mDialog.dismiss();
      }

   }

   public void onDismiss(DialogInterface dialog) {
      this.mPresenter.onCloseMenu(this.mMenu, true);
   }

   public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      if (allMenusAreClosing || menu == this.mMenu) {
         this.dismiss();
      }

      if (this.mPresenterCallback != null) {
         this.mPresenterCallback.onCloseMenu(menu, allMenusAreClosing);
      }

   }

   public boolean onOpenSubMenu(MenuBuilder subMenu) {
      return this.mPresenterCallback != null ? this.mPresenterCallback.onOpenSubMenu(subMenu) : false;
   }

   public void onClick(DialogInterface dialog, int which) {
      this.mMenu.performItemAction((MenuItemImpl)this.mPresenter.getAdapter().getItem(which), 0);
   }
}
