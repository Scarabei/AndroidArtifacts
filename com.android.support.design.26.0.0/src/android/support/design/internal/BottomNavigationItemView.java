package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.drawable;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.TooltipCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BottomNavigationItemView extends FrameLayout implements ItemView {
   public static final int INVALID_ITEM_POSITION = -1;
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private final int mDefaultMargin;
   private final int mShiftAmount;
   private final float mScaleUpFactor;
   private final float mScaleDownFactor;
   private boolean mShiftingMode;
   private ImageView mIcon;
   private final TextView mSmallLabel;
   private final TextView mLargeLabel;
   private int mItemPosition;
   private MenuItemImpl mItemData;
   private ColorStateList mIconTint;

   public BottomNavigationItemView(@NonNull Context context) {
      this(context, (AttributeSet)null);
   }

   public BottomNavigationItemView(@NonNull Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public BottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mItemPosition = -1;
      Resources res = this.getResources();
      int inactiveLabelSize = res.getDimensionPixelSize(dimen.design_bottom_navigation_text_size);
      int activeLabelSize = res.getDimensionPixelSize(dimen.design_bottom_navigation_active_text_size);
      this.mDefaultMargin = res.getDimensionPixelSize(dimen.design_bottom_navigation_margin);
      this.mShiftAmount = inactiveLabelSize - activeLabelSize;
      this.mScaleUpFactor = 1.0F * (float)activeLabelSize / (float)inactiveLabelSize;
      this.mScaleDownFactor = 1.0F * (float)inactiveLabelSize / (float)activeLabelSize;
      LayoutInflater.from(context).inflate(layout.design_bottom_navigation_item, this, true);
      this.setBackgroundResource(drawable.design_bottom_navigation_item_background);
      this.mIcon = (ImageView)this.findViewById(id.icon);
      this.mSmallLabel = (TextView)this.findViewById(id.smallLabel);
      this.mLargeLabel = (TextView)this.findViewById(id.largeLabel);
   }

   public void initialize(MenuItemImpl itemData, int menuType) {
      this.mItemData = itemData;
      this.setCheckable(itemData.isCheckable());
      this.setChecked(itemData.isChecked());
      this.setEnabled(itemData.isEnabled());
      this.setIcon(itemData.getIcon());
      this.setTitle(itemData.getTitle());
      this.setId(itemData.getItemId());
      this.setContentDescription(itemData.getContentDescription());
      TooltipCompat.setTooltipText(this, itemData.getTooltipText());
   }

   public void setItemPosition(int position) {
      this.mItemPosition = position;
   }

   public int getItemPosition() {
      return this.mItemPosition;
   }

   public void setShiftingMode(boolean enabled) {
      this.mShiftingMode = enabled;
   }

   public MenuItemImpl getItemData() {
      return this.mItemData;
   }

   public void setTitle(CharSequence title) {
      this.mSmallLabel.setText(title);
      this.mLargeLabel.setText(title);
   }

   public void setCheckable(boolean checkable) {
      this.refreshDrawableState();
   }

   public void setChecked(boolean checked) {
      this.mLargeLabel.setPivotX((float)(this.mLargeLabel.getWidth() / 2));
      this.mLargeLabel.setPivotY((float)this.mLargeLabel.getBaseline());
      this.mSmallLabel.setPivotX((float)(this.mSmallLabel.getWidth() / 2));
      this.mSmallLabel.setPivotY((float)this.mSmallLabel.getBaseline());
      LayoutParams iconParams;
      if (this.mShiftingMode) {
         if (checked) {
            iconParams = (LayoutParams)this.mIcon.getLayoutParams();
            iconParams.gravity = 49;
            iconParams.topMargin = this.mDefaultMargin;
            this.mIcon.setLayoutParams(iconParams);
            this.mLargeLabel.setVisibility(0);
            this.mLargeLabel.setScaleX(1.0F);
            this.mLargeLabel.setScaleY(1.0F);
         } else {
            iconParams = (LayoutParams)this.mIcon.getLayoutParams();
            iconParams.gravity = 17;
            iconParams.topMargin = this.mDefaultMargin;
            this.mIcon.setLayoutParams(iconParams);
            this.mLargeLabel.setVisibility(4);
            this.mLargeLabel.setScaleX(0.5F);
            this.mLargeLabel.setScaleY(0.5F);
         }

         this.mSmallLabel.setVisibility(4);
      } else if (checked) {
         iconParams = (LayoutParams)this.mIcon.getLayoutParams();
         iconParams.gravity = 49;
         iconParams.topMargin = this.mDefaultMargin + this.mShiftAmount;
         this.mIcon.setLayoutParams(iconParams);
         this.mLargeLabel.setVisibility(0);
         this.mSmallLabel.setVisibility(4);
         this.mLargeLabel.setScaleX(1.0F);
         this.mLargeLabel.setScaleY(1.0F);
         this.mSmallLabel.setScaleX(this.mScaleUpFactor);
         this.mSmallLabel.setScaleY(this.mScaleUpFactor);
      } else {
         iconParams = (LayoutParams)this.mIcon.getLayoutParams();
         iconParams.gravity = 49;
         iconParams.topMargin = this.mDefaultMargin;
         this.mIcon.setLayoutParams(iconParams);
         this.mLargeLabel.setVisibility(4);
         this.mSmallLabel.setVisibility(0);
         this.mLargeLabel.setScaleX(this.mScaleDownFactor);
         this.mLargeLabel.setScaleY(this.mScaleDownFactor);
         this.mSmallLabel.setScaleX(1.0F);
         this.mSmallLabel.setScaleY(1.0F);
      }

      this.refreshDrawableState();
   }

   public void setEnabled(boolean enabled) {
      super.setEnabled(enabled);
      this.mSmallLabel.setEnabled(enabled);
      this.mLargeLabel.setEnabled(enabled);
      this.mIcon.setEnabled(enabled);
      if (enabled) {
         ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
      } else {
         ViewCompat.setPointerIcon(this, (PointerIconCompat)null);
      }

   }

   public int[] onCreateDrawableState(int extraSpace) {
      int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
      if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
         mergeDrawableStates(drawableState, CHECKED_STATE_SET);
      }

      return drawableState;
   }

   public void setShortcut(boolean showShortcut, char shortcutKey) {
   }

   public void setIcon(Drawable icon) {
      if (icon != null) {
         ConstantState state = icon.getConstantState();
         icon = DrawableCompat.wrap(state == null ? icon : state.newDrawable()).mutate();
         DrawableCompat.setTintList(icon, this.mIconTint);
      }

      this.mIcon.setImageDrawable(icon);
   }

   public boolean prefersCondensedTitle() {
      return false;
   }

   public boolean showsIcon() {
      return true;
   }

   public void setIconTintList(ColorStateList tint) {
      this.mIconTint = tint;
      if (this.mItemData != null) {
         this.setIcon(this.mItemData.getIcon());
      }

   }

   public void setTextColor(ColorStateList color) {
      this.mSmallLabel.setTextColor(color);
      this.mLargeLabel.setTextColor(color);
   }

   public void setItemBackground(int background) {
      Drawable backgroundDrawable = background == 0 ? null : ContextCompat.getDrawable(this.getContext(), background);
      ViewCompat.setBackground(this, backgroundDrawable);
   }
}
