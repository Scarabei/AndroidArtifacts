package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, OnClickListener, ActionMenuView.ActionMenuChildView {
   private static final String TAG = "ActionMenuItemView";
   MenuItemImpl mItemData;
   private CharSequence mTitle;
   private Drawable mIcon;
   MenuBuilder.ItemInvoker mItemInvoker;
   private ForwardingListener mForwardingListener;
   ActionMenuItemView.PopupCallback mPopupCallback;
   private boolean mAllowTextWithIcon;
   private boolean mExpandedFormat;
   private int mMinWidth;
   private int mSavedPaddingLeft;
   private static final int MAX_ICON_SIZE = 32;
   private int mMaxIconSize;

   public ActionMenuItemView(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActionMenuItemView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ActionMenuItemView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      Resources res = context.getResources();
      this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.ActionMenuItemView, defStyle, 0);
      this.mMinWidth = a.getDimensionPixelSize(styleable.ActionMenuItemView_android_minWidth, 0);
      a.recycle();
      float density = res.getDisplayMetrics().density;
      this.mMaxIconSize = (int)(32.0F * density + 0.5F);
      this.setOnClickListener(this);
      this.mSavedPaddingLeft = -1;
      this.setSaveEnabled(false);
   }

   public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
      this.updateTextButtonVisibility();
   }

   private boolean shouldAllowTextWithIcon() {
      Configuration config = this.getContext().getResources().getConfiguration();
      int widthDp = config.screenWidthDp;
      int heightDp = config.screenHeightDp;
      return widthDp >= 480 || widthDp >= 640 && heightDp >= 480 || config.orientation == 2;
   }

   public void setPadding(int l, int t, int r, int b) {
      this.mSavedPaddingLeft = l;
      super.setPadding(l, t, r, b);
   }

   public MenuItemImpl getItemData() {
      return this.mItemData;
   }

   public void initialize(MenuItemImpl itemData, int menuType) {
      this.mItemData = itemData;
      this.setIcon(itemData.getIcon());
      this.setTitle(itemData.getTitleForItemView(this));
      this.setId(itemData.getItemId());
      this.setVisibility(itemData.isVisible() ? 0 : 8);
      this.setEnabled(itemData.isEnabled());
      if (itemData.hasSubMenu() && this.mForwardingListener == null) {
         this.mForwardingListener = new ActionMenuItemView.ActionMenuItemForwardingListener();
      }

   }

   public boolean onTouchEvent(MotionEvent e) {
      return this.mItemData.hasSubMenu() && this.mForwardingListener != null && this.mForwardingListener.onTouch(this, e) ? true : super.onTouchEvent(e);
   }

   public void onClick(View v) {
      if (this.mItemInvoker != null) {
         this.mItemInvoker.invokeItem(this.mItemData);
      }

   }

   public void setItemInvoker(MenuBuilder.ItemInvoker invoker) {
      this.mItemInvoker = invoker;
   }

   public void setPopupCallback(ActionMenuItemView.PopupCallback popupCallback) {
      this.mPopupCallback = popupCallback;
   }

   public boolean prefersCondensedTitle() {
      return true;
   }

   public void setCheckable(boolean checkable) {
   }

   public void setChecked(boolean checked) {
   }

   public void setExpandedFormat(boolean expandedFormat) {
      if (this.mExpandedFormat != expandedFormat) {
         this.mExpandedFormat = expandedFormat;
         if (this.mItemData != null) {
            this.mItemData.actionFormatChanged();
         }
      }

   }

   private void updateTextButtonVisibility() {
      boolean visible = !TextUtils.isEmpty(this.mTitle);
      visible &= this.mIcon == null || this.mItemData.showsTextAsAction() && (this.mAllowTextWithIcon || this.mExpandedFormat);
      this.setText(visible ? this.mTitle : null);
      CharSequence contentDescription = this.mItemData.getContentDescription();
      if (TextUtils.isEmpty(contentDescription)) {
         this.setContentDescription(visible ? null : this.mItemData.getTitle());
      } else {
         this.setContentDescription(contentDescription);
      }

      CharSequence tooltipText = this.mItemData.getTooltipText();
      if (TextUtils.isEmpty(tooltipText)) {
         TooltipCompat.setTooltipText(this, visible ? null : this.mItemData.getTitle());
      } else {
         TooltipCompat.setTooltipText(this, tooltipText);
      }

   }

   public void setIcon(Drawable icon) {
      this.mIcon = icon;
      if (icon != null) {
         int width = icon.getIntrinsicWidth();
         int height = icon.getIntrinsicHeight();
         float scale;
         if (width > this.mMaxIconSize) {
            scale = (float)this.mMaxIconSize / (float)width;
            width = this.mMaxIconSize;
            height = (int)((float)width * scale);
         }

         if (height > this.mMaxIconSize) {
            scale = (float)this.mMaxIconSize / (float)height;
            height = this.mMaxIconSize;
            width = (int)((float)width * scale);
         }

         icon.setBounds(0, 0, width, height);
      }

      this.setCompoundDrawables(icon, (Drawable)null, (Drawable)null, (Drawable)null);
      this.updateTextButtonVisibility();
   }

   public boolean hasText() {
      return !TextUtils.isEmpty(this.getText());
   }

   public void setShortcut(boolean showShortcut, char shortcutKey) {
   }

   public void setTitle(CharSequence title) {
      this.mTitle = title;
      this.updateTextButtonVisibility();
   }

   public boolean showsIcon() {
      return true;
   }

   public boolean needsDividerBefore() {
      return this.hasText() && this.mItemData.getIcon() == null;
   }

   public boolean needsDividerAfter() {
      return this.hasText();
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      boolean textVisible = this.hasText();
      if (textVisible && this.mSavedPaddingLeft >= 0) {
         super.setPadding(this.mSavedPaddingLeft, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
      }

      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int oldMeasuredWidth = this.getMeasuredWidth();
      int targetWidth = widthMode == Integer.MIN_VALUE ? Math.min(widthSize, this.mMinWidth) : this.mMinWidth;
      if (widthMode != 1073741824 && this.mMinWidth > 0 && oldMeasuredWidth < targetWidth) {
         super.onMeasure(MeasureSpec.makeMeasureSpec(targetWidth, 1073741824), heightMeasureSpec);
      }

      if (!textVisible && this.mIcon != null) {
         int w = this.getMeasuredWidth();
         int dw = this.mIcon.getBounds().width();
         super.setPadding((w - dw) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
      }

   }

   public void onRestoreInstanceState(Parcelable state) {
      super.onRestoreInstanceState((Parcelable)null);
   }

   public abstract static class PopupCallback {
      public abstract ShowableListMenu getPopup();
   }

   private class ActionMenuItemForwardingListener extends ForwardingListener {
      public ActionMenuItemForwardingListener() {
         super(ActionMenuItemView.this);
      }

      public ShowableListMenu getPopup() {
         return ActionMenuItemView.this.mPopupCallback != null ? ActionMenuItemView.this.mPopupCallback.getPopup() : null;
      }

      protected boolean onForwardingStarted() {
         if (ActionMenuItemView.this.mItemInvoker != null && ActionMenuItemView.this.mItemInvoker.invokeItem(ActionMenuItemView.this.mItemData)) {
            ShowableListMenu popup = this.getPopup();
            return popup != null && popup.isShowing();
         } else {
            return false;
         }
      }
   }
}
