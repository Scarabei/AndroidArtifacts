package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.drawable;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.TooltipCompat;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;

@RestrictTo({Scope.LIBRARY_GROUP})
public class NavigationMenuItemView extends ForegroundLinearLayout implements ItemView {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private final int mIconSize;
   private boolean mNeedsEmptyIcon;
   boolean mCheckable;
   private final CheckedTextView mTextView;
   private FrameLayout mActionArea;
   private MenuItemImpl mItemData;
   private ColorStateList mIconTintList;
   private boolean mHasIconTintList;
   private Drawable mEmptyDrawable;
   private final AccessibilityDelegateCompat mAccessibilityDelegate;

   public NavigationMenuItemView(Context context) {
      this(context, (AttributeSet)null);
   }

   public NavigationMenuItemView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public NavigationMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mAccessibilityDelegate = new AccessibilityDelegateCompat() {
         public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setCheckable(NavigationMenuItemView.this.mCheckable);
         }
      };
      this.setOrientation(0);
      LayoutInflater.from(context).inflate(layout.design_navigation_menu_item, this, true);
      this.mIconSize = context.getResources().getDimensionPixelSize(dimen.design_navigation_icon_size);
      this.mTextView = (CheckedTextView)this.findViewById(id.design_menu_item_text);
      this.mTextView.setDuplicateParentStateEnabled(true);
      ViewCompat.setAccessibilityDelegate(this.mTextView, this.mAccessibilityDelegate);
   }

   public void initialize(MenuItemImpl itemData, int menuType) {
      this.mItemData = itemData;
      this.setVisibility(itemData.isVisible() ? 0 : 8);
      if (this.getBackground() == null) {
         ViewCompat.setBackground(this, this.createDefaultBackground());
      }

      this.setCheckable(itemData.isCheckable());
      this.setChecked(itemData.isChecked());
      this.setEnabled(itemData.isEnabled());
      this.setTitle(itemData.getTitle());
      this.setIcon(itemData.getIcon());
      this.setActionView(itemData.getActionView());
      this.setContentDescription(itemData.getContentDescription());
      TooltipCompat.setTooltipText(this, itemData.getTooltipText());
      this.adjustAppearance();
   }

   private boolean shouldExpandActionArea() {
      return this.mItemData.getTitle() == null && this.mItemData.getIcon() == null && this.mItemData.getActionView() != null;
   }

   private void adjustAppearance() {
      LayoutParams params;
      if (this.shouldExpandActionArea()) {
         this.mTextView.setVisibility(8);
         if (this.mActionArea != null) {
            params = (LayoutParams)this.mActionArea.getLayoutParams();
            params.width = -1;
            this.mActionArea.setLayoutParams(params);
         }
      } else {
         this.mTextView.setVisibility(0);
         if (this.mActionArea != null) {
            params = (LayoutParams)this.mActionArea.getLayoutParams();
            params.width = -2;
            this.mActionArea.setLayoutParams(params);
         }
      }

   }

   public void recycle() {
      if (this.mActionArea != null) {
         this.mActionArea.removeAllViews();
      }

      this.mTextView.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
   }

   private void setActionView(View actionView) {
      if (actionView != null) {
         if (this.mActionArea == null) {
            this.mActionArea = (FrameLayout)((ViewStub)this.findViewById(id.design_menu_item_action_area_stub)).inflate();
         }

         this.mActionArea.removeAllViews();
         this.mActionArea.addView(actionView);
      }

   }

   private StateListDrawable createDefaultBackground() {
      TypedValue value = new TypedValue();
      if (this.getContext().getTheme().resolveAttribute(attr.colorControlHighlight, value, true)) {
         StateListDrawable drawable = new StateListDrawable();
         drawable.addState(CHECKED_STATE_SET, new ColorDrawable(value.data));
         drawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
         return drawable;
      } else {
         return null;
      }
   }

   public MenuItemImpl getItemData() {
      return this.mItemData;
   }

   public void setTitle(CharSequence title) {
      this.mTextView.setText(title);
   }

   public void setCheckable(boolean checkable) {
      this.refreshDrawableState();
      if (this.mCheckable != checkable) {
         this.mCheckable = checkable;
         this.mAccessibilityDelegate.sendAccessibilityEvent(this.mTextView, 2048);
      }

   }

   public void setChecked(boolean checked) {
      this.refreshDrawableState();
      this.mTextView.setChecked(checked);
   }

   public void setShortcut(boolean showShortcut, char shortcutKey) {
   }

   public void setIcon(Drawable icon) {
      if (icon != null) {
         if (this.mHasIconTintList) {
            ConstantState state = icon.getConstantState();
            icon = DrawableCompat.wrap(state == null ? icon : state.newDrawable()).mutate();
            DrawableCompat.setTintList(icon, this.mIconTintList);
         }

         icon.setBounds(0, 0, this.mIconSize, this.mIconSize);
      } else if (this.mNeedsEmptyIcon) {
         if (this.mEmptyDrawable == null) {
            this.mEmptyDrawable = ResourcesCompat.getDrawable(this.getResources(), drawable.navigation_empty_icon, this.getContext().getTheme());
            if (this.mEmptyDrawable != null) {
               this.mEmptyDrawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
            }
         }

         icon = this.mEmptyDrawable;
      }

      TextViewCompat.setCompoundDrawablesRelative(this.mTextView, icon, (Drawable)null, (Drawable)null, (Drawable)null);
   }

   public boolean prefersCondensedTitle() {
      return false;
   }

   public boolean showsIcon() {
      return true;
   }

   protected int[] onCreateDrawableState(int extraSpace) {
      int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
      if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
         mergeDrawableStates(drawableState, CHECKED_STATE_SET);
      }

      return drawableState;
   }

   void setIconTintList(ColorStateList tintList) {
      this.mIconTintList = tintList;
      this.mHasIconTintList = this.mIconTintList != null;
      if (this.mItemData != null) {
         this.setIcon(this.mItemData.getIcon());
      }

   }

   public void setTextAppearance(int textAppearance) {
      TextViewCompat.setTextAppearance(this.mTextView, textAppearance);
   }

   public void setTextColor(ColorStateList colors) {
      this.mTextView.setTextColor(colors);
   }

   public void setNeedsEmptyIcon(boolean needsEmptyIcon) {
      this.mNeedsEmptyIcon = needsEmptyIcon;
   }
}
