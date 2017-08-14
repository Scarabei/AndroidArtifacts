package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.dimen;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow.OnDismissListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MenuPopupHelper implements MenuHelper {
   private static final int TOUCH_EPICENTER_SIZE_DP = 48;
   private final Context mContext;
   private final MenuBuilder mMenu;
   private final boolean mOverflowOnly;
   private final int mPopupStyleAttr;
   private final int mPopupStyleRes;
   private View mAnchorView;
   private int mDropDownGravity;
   private boolean mForceShowIcon;
   private MenuPresenter.Callback mPresenterCallback;
   private MenuPopup mPopup;
   private OnDismissListener mOnDismissListener;
   private final OnDismissListener mInternalOnDismissListener;

   public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menu) {
      this(context, menu, (View)null, false, attr.popupMenuStyle, 0);
   }

   public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menu, @NonNull View anchorView) {
      this(context, menu, anchorView, false, attr.popupMenuStyle, 0);
   }

   public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menu, @NonNull View anchorView, boolean overflowOnly, @AttrRes int popupStyleAttr) {
      this(context, menu, anchorView, overflowOnly, popupStyleAttr, 0);
   }

   public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menu, @NonNull View anchorView, boolean overflowOnly, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes) {
      this.mDropDownGravity = 8388611;
      this.mInternalOnDismissListener = new OnDismissListener() {
         public void onDismiss() {
            MenuPopupHelper.this.onDismiss();
         }
      };
      this.mContext = context;
      this.mMenu = menu;
      this.mAnchorView = anchorView;
      this.mOverflowOnly = overflowOnly;
      this.mPopupStyleAttr = popupStyleAttr;
      this.mPopupStyleRes = popupStyleRes;
   }

   public void setOnDismissListener(@Nullable OnDismissListener listener) {
      this.mOnDismissListener = listener;
   }

   public void setAnchorView(@NonNull View anchor) {
      this.mAnchorView = anchor;
   }

   public void setForceShowIcon(boolean forceShowIcon) {
      this.mForceShowIcon = forceShowIcon;
      if (this.mPopup != null) {
         this.mPopup.setForceShowIcon(forceShowIcon);
      }

   }

   public void setGravity(int gravity) {
      this.mDropDownGravity = gravity;
   }

   public int getGravity() {
      return this.mDropDownGravity;
   }

   public void show() {
      if (!this.tryShow()) {
         throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
      }
   }

   public void show(int x, int y) {
      if (!this.tryShow(x, y)) {
         throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
      }
   }

   @NonNull
   public MenuPopup getPopup() {
      if (this.mPopup == null) {
         this.mPopup = this.createPopup();
      }

      return this.mPopup;
   }

   public boolean tryShow() {
      if (this.isShowing()) {
         return true;
      } else if (this.mAnchorView == null) {
         return false;
      } else {
         this.showPopup(0, 0, false, false);
         return true;
      }
   }

   public boolean tryShow(int x, int y) {
      if (this.isShowing()) {
         return true;
      } else if (this.mAnchorView == null) {
         return false;
      } else {
         this.showPopup(x, y, true, true);
         return true;
      }
   }

   @NonNull
   private MenuPopup createPopup() {
      WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
      Display display = windowManager.getDefaultDisplay();
      Point displaySize = new Point();
      if (VERSION.SDK_INT >= 17) {
         display.getRealSize(displaySize);
      } else {
         display.getSize(displaySize);
      }

      int smallestWidth = Math.min(displaySize.x, displaySize.y);
      int minSmallestWidthCascading = this.mContext.getResources().getDimensionPixelSize(dimen.abc_cascading_menus_min_smallest_width);
      boolean enableCascadingSubmenus = smallestWidth >= minSmallestWidthCascading;
      Object popup;
      if (enableCascadingSubmenus) {
         popup = new CascadingMenuPopup(this.mContext, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
      } else {
         popup = new StandardMenuPopup(this.mContext, this.mMenu, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
      }

      ((MenuPopup)popup).addMenu(this.mMenu);
      ((MenuPopup)popup).setOnDismissListener(this.mInternalOnDismissListener);
      ((MenuPopup)popup).setAnchorView(this.mAnchorView);
      ((MenuPopup)popup).setCallback(this.mPresenterCallback);
      ((MenuPopup)popup).setForceShowIcon(this.mForceShowIcon);
      ((MenuPopup)popup).setGravity(this.mDropDownGravity);
      return (MenuPopup)popup;
   }

   private void showPopup(int xOffset, int yOffset, boolean useOffsets, boolean showTitle) {
      MenuPopup popup = this.getPopup();
      popup.setShowTitle(showTitle);
      if (useOffsets) {
         int hgrav = GravityCompat.getAbsoluteGravity(this.mDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView)) & 7;
         if (hgrav == 5) {
            xOffset += this.mAnchorView.getWidth();
         }

         popup.setHorizontalOffset(xOffset);
         popup.setVerticalOffset(yOffset);
         float density = this.mContext.getResources().getDisplayMetrics().density;
         int halfSize = (int)(48.0F * density / 2.0F);
         Rect epicenter = new Rect(xOffset - halfSize, yOffset - halfSize, xOffset + halfSize, yOffset + halfSize);
         popup.setEpicenterBounds(epicenter);
      }

      popup.show();
   }

   public void dismiss() {
      if (this.isShowing()) {
         this.mPopup.dismiss();
      }

   }

   protected void onDismiss() {
      this.mPopup = null;
      if (this.mOnDismissListener != null) {
         this.mOnDismissListener.onDismiss();
      }

   }

   public boolean isShowing() {
      return this.mPopup != null && this.mPopup.isShowing();
   }

   public void setPresenterCallback(@Nullable MenuPresenter.Callback cb) {
      this.mPresenterCallback = cb;
      if (this.mPopup != null) {
         this.mPopup.setCallback(cb);
      }

   }
}
