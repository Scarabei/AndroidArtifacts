package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;

public class PopupMenu {
   private final Context mContext;
   private final MenuBuilder mMenu;
   private final View mAnchor;
   final MenuPopupHelper mPopup;
   PopupMenu.OnMenuItemClickListener mMenuItemClickListener;
   PopupMenu.OnDismissListener mOnDismissListener;
   private OnTouchListener mDragListener;

   public PopupMenu(@NonNull Context context, @NonNull View anchor) {
      this(context, anchor, 0);
   }

   public PopupMenu(@NonNull Context context, @NonNull View anchor, int gravity) {
      this(context, anchor, gravity, attr.popupMenuStyle, 0);
   }

   public PopupMenu(@NonNull Context context, @NonNull View anchor, int gravity, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes) {
      this.mContext = context;
      this.mAnchor = anchor;
      this.mMenu = new MenuBuilder(context);
      this.mMenu.setCallback(new MenuBuilder.Callback() {
         public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            return PopupMenu.this.mMenuItemClickListener != null ? PopupMenu.this.mMenuItemClickListener.onMenuItemClick(item) : false;
         }

         public void onMenuModeChange(MenuBuilder menu) {
         }
      });
      this.mPopup = new MenuPopupHelper(context, this.mMenu, anchor, false, popupStyleAttr, popupStyleRes);
      this.mPopup.setGravity(gravity);
      this.mPopup.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener() {
         public void onDismiss() {
            if (PopupMenu.this.mOnDismissListener != null) {
               PopupMenu.this.mOnDismissListener.onDismiss(PopupMenu.this);
            }

         }
      });
   }

   public void setGravity(int gravity) {
      this.mPopup.setGravity(gravity);
   }

   public int getGravity() {
      return this.mPopup.getGravity();
   }

   @NonNull
   public OnTouchListener getDragToOpenListener() {
      if (this.mDragListener == null) {
         this.mDragListener = new ForwardingListener(this.mAnchor) {
            protected boolean onForwardingStarted() {
               PopupMenu.this.show();
               return true;
            }

            protected boolean onForwardingStopped() {
               PopupMenu.this.dismiss();
               return true;
            }

            public ShowableListMenu getPopup() {
               return PopupMenu.this.mPopup.getPopup();
            }
         };
      }

      return this.mDragListener;
   }

   @NonNull
   public Menu getMenu() {
      return this.mMenu;
   }

   @NonNull
   public MenuInflater getMenuInflater() {
      return new SupportMenuInflater(this.mContext);
   }

   public void inflate(@MenuRes int menuRes) {
      this.getMenuInflater().inflate(menuRes, this.mMenu);
   }

   public void show() {
      this.mPopup.show();
   }

   public void dismiss() {
      this.mPopup.dismiss();
   }

   public void setOnMenuItemClickListener(@Nullable PopupMenu.OnMenuItemClickListener listener) {
      this.mMenuItemClickListener = listener;
   }

   public void setOnDismissListener(@Nullable PopupMenu.OnDismissListener listener) {
      this.mOnDismissListener = listener;
   }

   public interface OnDismissListener {
      void onDismiss(PopupMenu var1);
   }

   public interface OnMenuItemClickListener {
      boolean onMenuItemClick(MenuItem var1);
   }
}
