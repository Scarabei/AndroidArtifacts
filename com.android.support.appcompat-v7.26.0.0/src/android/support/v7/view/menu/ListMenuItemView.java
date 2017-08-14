package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView {
   private static final String TAG = "ListMenuItemView";
   private MenuItemImpl mItemData;
   private ImageView mIconView;
   private RadioButton mRadioButton;
   private TextView mTitleView;
   private CheckBox mCheckBox;
   private TextView mShortcutView;
   private ImageView mSubMenuArrowView;
   private Drawable mBackground;
   private int mTextAppearance;
   private Context mTextAppearanceContext;
   private boolean mPreserveIconSpacing;
   private Drawable mSubMenuArrow;
   private int mMenuType;
   private LayoutInflater mInflater;
   private boolean mForceShowIcon;

   public ListMenuItemView(Context context, AttributeSet attrs) {
      this(context, attrs, attr.listMenuViewStyle);
   }

   public ListMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.getContext(), attrs, styleable.MenuView, defStyleAttr, 0);
      this.mBackground = a.getDrawable(styleable.MenuView_android_itemBackground);
      this.mTextAppearance = a.getResourceId(styleable.MenuView_android_itemTextAppearance, -1);
      this.mPreserveIconSpacing = a.getBoolean(styleable.MenuView_preserveIconSpacing, false);
      this.mTextAppearanceContext = context;
      this.mSubMenuArrow = a.getDrawable(styleable.MenuView_subMenuArrow);
      a.recycle();
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      ViewCompat.setBackground(this, this.mBackground);
      this.mTitleView = (TextView)this.findViewById(id.title);
      if (this.mTextAppearance != -1) {
         this.mTitleView.setTextAppearance(this.mTextAppearanceContext, this.mTextAppearance);
      }

      this.mShortcutView = (TextView)this.findViewById(id.shortcut);
      this.mSubMenuArrowView = (ImageView)this.findViewById(id.submenuarrow);
      if (this.mSubMenuArrowView != null) {
         this.mSubMenuArrowView.setImageDrawable(this.mSubMenuArrow);
      }

   }

   public void initialize(MenuItemImpl itemData, int menuType) {
      this.mItemData = itemData;
      this.mMenuType = menuType;
      this.setVisibility(itemData.isVisible() ? 0 : 8);
      this.setTitle(itemData.getTitleForItemView(this));
      this.setCheckable(itemData.isCheckable());
      this.setShortcut(itemData.shouldShowShortcut(), itemData.getShortcut());
      this.setIcon(itemData.getIcon());
      this.setEnabled(itemData.isEnabled());
      this.setSubMenuArrowVisible(itemData.hasSubMenu());
      this.setContentDescription(itemData.getContentDescription());
   }

   public void setForceShowIcon(boolean forceShow) {
      this.mPreserveIconSpacing = this.mForceShowIcon = forceShow;
   }

   public void setTitle(CharSequence title) {
      if (title != null) {
         this.mTitleView.setText(title);
         if (this.mTitleView.getVisibility() != 0) {
            this.mTitleView.setVisibility(0);
         }
      } else if (this.mTitleView.getVisibility() != 8) {
         this.mTitleView.setVisibility(8);
      }

   }

   public MenuItemImpl getItemData() {
      return this.mItemData;
   }

   public void setCheckable(boolean checkable) {
      if (checkable || this.mRadioButton != null || this.mCheckBox != null) {
         Object compoundButton;
         Object otherCompoundButton;
         if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
               this.insertRadioButton();
            }

            compoundButton = this.mRadioButton;
            otherCompoundButton = this.mCheckBox;
         } else {
            if (this.mCheckBox == null) {
               this.insertCheckBox();
            }

            compoundButton = this.mCheckBox;
            otherCompoundButton = this.mRadioButton;
         }

         if (checkable) {
            ((CompoundButton)compoundButton).setChecked(this.mItemData.isChecked());
            int newVisibility = checkable ? 0 : 8;
            if (((CompoundButton)compoundButton).getVisibility() != newVisibility) {
               ((CompoundButton)compoundButton).setVisibility(newVisibility);
            }

            if (otherCompoundButton != null && ((CompoundButton)otherCompoundButton).getVisibility() != 8) {
               ((CompoundButton)otherCompoundButton).setVisibility(8);
            }
         } else {
            if (this.mCheckBox != null) {
               this.mCheckBox.setVisibility(8);
            }

            if (this.mRadioButton != null) {
               this.mRadioButton.setVisibility(8);
            }
         }

      }
   }

   public void setChecked(boolean checked) {
      Object compoundButton;
      if (this.mItemData.isExclusiveCheckable()) {
         if (this.mRadioButton == null) {
            this.insertRadioButton();
         }

         compoundButton = this.mRadioButton;
      } else {
         if (this.mCheckBox == null) {
            this.insertCheckBox();
         }

         compoundButton = this.mCheckBox;
      }

      ((CompoundButton)compoundButton).setChecked(checked);
   }

   private void setSubMenuArrowVisible(boolean hasSubmenu) {
      if (this.mSubMenuArrowView != null) {
         this.mSubMenuArrowView.setVisibility(hasSubmenu ? 0 : 8);
      }

   }

   public void setShortcut(boolean showShortcut, char shortcutKey) {
      int newVisibility = showShortcut && this.mItemData.shouldShowShortcut() ? 0 : 8;
      if (newVisibility == 0) {
         this.mShortcutView.setText(this.mItemData.getShortcutLabel());
      }

      if (this.mShortcutView.getVisibility() != newVisibility) {
         this.mShortcutView.setVisibility(newVisibility);
      }

   }

   public void setIcon(Drawable icon) {
      boolean showIcon = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
      if (showIcon || this.mPreserveIconSpacing) {
         if (this.mIconView != null || icon != null || this.mPreserveIconSpacing) {
            if (this.mIconView == null) {
               this.insertIconView();
            }

            if (icon == null && !this.mPreserveIconSpacing) {
               this.mIconView.setVisibility(8);
            } else {
               this.mIconView.setImageDrawable(showIcon ? icon : null);
               if (this.mIconView.getVisibility() != 0) {
                  this.mIconView.setVisibility(0);
               }
            }

         }
      }
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      if (this.mIconView != null && this.mPreserveIconSpacing) {
         LayoutParams lp = this.getLayoutParams();
         android.widget.LinearLayout.LayoutParams iconLp = (android.widget.LinearLayout.LayoutParams)this.mIconView.getLayoutParams();
         if (lp.height > 0 && iconLp.width <= 0) {
            iconLp.width = lp.height;
         }
      }

      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }

   private void insertIconView() {
      LayoutInflater inflater = this.getInflater();
      this.mIconView = (ImageView)inflater.inflate(layout.abc_list_menu_item_icon, this, false);
      this.addView(this.mIconView, 0);
   }

   private void insertRadioButton() {
      LayoutInflater inflater = this.getInflater();
      this.mRadioButton = (RadioButton)inflater.inflate(layout.abc_list_menu_item_radio, this, false);
      this.addView(this.mRadioButton);
   }

   private void insertCheckBox() {
      LayoutInflater inflater = this.getInflater();
      this.mCheckBox = (CheckBox)inflater.inflate(layout.abc_list_menu_item_checkbox, this, false);
      this.addView(this.mCheckBox);
   }

   public boolean prefersCondensedTitle() {
      return false;
   }

   public boolean showsIcon() {
      return this.mForceShowIcon;
   }

   private LayoutInflater getInflater() {
      if (this.mInflater == null) {
         this.mInflater = LayoutInflater.from(this.getContext());
      }

      return this.mInflater;
   }
}
