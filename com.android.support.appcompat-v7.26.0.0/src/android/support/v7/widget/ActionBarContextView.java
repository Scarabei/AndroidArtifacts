package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarContextView extends AbsActionBarView {
   private static final String TAG = "ActionBarContextView";
   private CharSequence mTitle;
   private CharSequence mSubtitle;
   private View mClose;
   private View mCustomView;
   private LinearLayout mTitleLayout;
   private TextView mTitleView;
   private TextView mSubtitleView;
   private int mTitleStyleRes;
   private int mSubtitleStyleRes;
   private boolean mTitleOptional;
   private int mCloseItemLayout;

   public ActionBarContextView(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActionBarContextView(Context context, AttributeSet attrs) {
      this(context, attrs, attr.actionModeStyle);
   }

   public ActionBarContextView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.ActionMode, defStyle, 0);
      ViewCompat.setBackground(this, a.getDrawable(styleable.ActionMode_background));
      this.mTitleStyleRes = a.getResourceId(styleable.ActionMode_titleTextStyle, 0);
      this.mSubtitleStyleRes = a.getResourceId(styleable.ActionMode_subtitleTextStyle, 0);
      this.mContentHeight = a.getLayoutDimension(styleable.ActionMode_height, 0);
      this.mCloseItemLayout = a.getResourceId(styleable.ActionMode_closeItemLayout, layout.abc_action_mode_close_item_material);
      a.recycle();
   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.mActionMenuPresenter != null) {
         this.mActionMenuPresenter.hideOverflowMenu();
         this.mActionMenuPresenter.hideSubMenus();
      }

   }

   public void setContentHeight(int height) {
      this.mContentHeight = height;
   }

   public void setCustomView(View view) {
      if (this.mCustomView != null) {
         this.removeView(this.mCustomView);
      }

      this.mCustomView = view;
      if (view != null && this.mTitleLayout != null) {
         this.removeView(this.mTitleLayout);
         this.mTitleLayout = null;
      }

      if (view != null) {
         this.addView(view);
      }

      this.requestLayout();
   }

   public void setTitle(CharSequence title) {
      this.mTitle = title;
      this.initTitle();
   }

   public void setSubtitle(CharSequence subtitle) {
      this.mSubtitle = subtitle;
      this.initTitle();
   }

   public CharSequence getTitle() {
      return this.mTitle;
   }

   public CharSequence getSubtitle() {
      return this.mSubtitle;
   }

   private void initTitle() {
      if (this.mTitleLayout == null) {
         LayoutInflater inflater = LayoutInflater.from(this.getContext());
         inflater.inflate(layout.abc_action_bar_title_item, this);
         this.mTitleLayout = (LinearLayout)this.getChildAt(this.getChildCount() - 1);
         this.mTitleView = (TextView)this.mTitleLayout.findViewById(id.action_bar_title);
         this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(id.action_bar_subtitle);
         if (this.mTitleStyleRes != 0) {
            this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
         }

         if (this.mSubtitleStyleRes != 0) {
            this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
         }
      }

      this.mTitleView.setText(this.mTitle);
      this.mSubtitleView.setText(this.mSubtitle);
      boolean hasTitle = !TextUtils.isEmpty(this.mTitle);
      boolean hasSubtitle = !TextUtils.isEmpty(this.mSubtitle);
      this.mSubtitleView.setVisibility(hasSubtitle ? 0 : 8);
      this.mTitleLayout.setVisibility(!hasTitle && !hasSubtitle ? 8 : 0);
      if (this.mTitleLayout.getParent() == null) {
         this.addView(this.mTitleLayout);
      }

   }

   public void initForMode(final ActionMode mode) {
      if (this.mClose == null) {
         LayoutInflater inflater = LayoutInflater.from(this.getContext());
         this.mClose = inflater.inflate(this.mCloseItemLayout, this, false);
         this.addView(this.mClose);
      } else if (this.mClose.getParent() == null) {
         this.addView(this.mClose);
      }

      View closeButton = this.mClose.findViewById(id.action_mode_close_button);
      closeButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            mode.finish();
         }
      });
      MenuBuilder menu = (MenuBuilder)mode.getMenu();
      if (this.mActionMenuPresenter != null) {
         this.mActionMenuPresenter.dismissPopupMenus();
      }

      this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext());
      this.mActionMenuPresenter.setReserveOverflow(true);
      LayoutParams layoutParams = new LayoutParams(-2, -1);
      menu.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
      this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
      ViewCompat.setBackground(this.mMenuView, (Drawable)null);
      this.addView(this.mMenuView, layoutParams);
   }

   public void closeMode() {
      if (this.mClose == null) {
         this.killMode();
      }
   }

   public void killMode() {
      this.removeAllViews();
      this.mCustomView = null;
      this.mMenuView = null;
   }

   public boolean showOverflowMenu() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.showOverflowMenu() : false;
   }

   public boolean hideOverflowMenu() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.hideOverflowMenu() : false;
   }

   public boolean isOverflowMenuShowing() {
      return this.mActionMenuPresenter != null ? this.mActionMenuPresenter.isOverflowMenuShowing() : false;
   }

   protected LayoutParams generateDefaultLayoutParams() {
      return new MarginLayoutParams(-1, -2);
   }

   public LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new MarginLayoutParams(this.getContext(), attrs);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      if (widthMode != 1073741824) {
         throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
      } else {
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         if (heightMode == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
         } else {
            int contentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int maxHeight = this.mContentHeight > 0 ? this.mContentHeight : MeasureSpec.getSize(heightMeasureSpec);
            int verticalPadding = this.getPaddingTop() + this.getPaddingBottom();
            int availableWidth = contentWidth - this.getPaddingLeft() - this.getPaddingRight();
            int height = maxHeight - verticalPadding;
            int childSpecHeight = MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
            if (this.mClose != null) {
               availableWidth = this.measureChildView(this.mClose, availableWidth, childSpecHeight, 0);
               MarginLayoutParams lp = (MarginLayoutParams)this.mClose.getLayoutParams();
               availableWidth -= lp.leftMargin + lp.rightMargin;
            }

            if (this.mMenuView != null && this.mMenuView.getParent() == this) {
               availableWidth = this.measureChildView(this.mMenuView, availableWidth, childSpecHeight, 0);
            }

            int count;
            int measuredHeight;
            if (this.mTitleLayout != null && this.mCustomView == null) {
               if (this.mTitleOptional) {
                  measuredHeight = MeasureSpec.makeMeasureSpec(0, 0);
                  this.mTitleLayout.measure(measuredHeight, childSpecHeight);
                  count = this.mTitleLayout.getMeasuredWidth();
                  boolean titleFits = count <= availableWidth;
                  if (titleFits) {
                     availableWidth -= count;
                  }

                  this.mTitleLayout.setVisibility(titleFits ? 0 : 8);
               } else {
                  availableWidth = this.measureChildView(this.mTitleLayout, availableWidth, childSpecHeight, 0);
               }
            }

            int paddedViewHeight;
            int i;
            if (this.mCustomView != null) {
               LayoutParams lp = this.mCustomView.getLayoutParams();
               count = lp.width != -2 ? 1073741824 : Integer.MIN_VALUE;
               i = lp.width >= 0 ? Math.min(lp.width, availableWidth) : availableWidth;
               int customHeightMode = lp.height != -2 ? 1073741824 : Integer.MIN_VALUE;
               paddedViewHeight = lp.height >= 0 ? Math.min(lp.height, height) : height;
               this.mCustomView.measure(MeasureSpec.makeMeasureSpec(i, count), MeasureSpec.makeMeasureSpec(paddedViewHeight, customHeightMode));
            }

            if (this.mContentHeight <= 0) {
               measuredHeight = 0;
               count = this.getChildCount();

               for(i = 0; i < count; ++i) {
                  View v = this.getChildAt(i);
                  paddedViewHeight = v.getMeasuredHeight() + verticalPadding;
                  if (paddedViewHeight > measuredHeight) {
                     measuredHeight = paddedViewHeight;
                  }
               }

               this.setMeasuredDimension(contentWidth, measuredHeight);
            } else {
               this.setMeasuredDimension(contentWidth, maxHeight);
            }

         }
      }
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
      int x = isLayoutRtl ? r - l - this.getPaddingRight() : this.getPaddingLeft();
      int y = this.getPaddingTop();
      int contentHeight = b - t - this.getPaddingTop() - this.getPaddingBottom();
      if (this.mClose != null && this.mClose.getVisibility() != 8) {
         MarginLayoutParams lp = (MarginLayoutParams)this.mClose.getLayoutParams();
         int startMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
         int endMargin = isLayoutRtl ? lp.leftMargin : lp.rightMargin;
         x = next(x, startMargin, isLayoutRtl);
         x += this.positionChild(this.mClose, x, y, contentHeight, isLayoutRtl);
         x = next(x, endMargin, isLayoutRtl);
      }

      if (this.mTitleLayout != null && this.mCustomView == null && this.mTitleLayout.getVisibility() != 8) {
         x += this.positionChild(this.mTitleLayout, x, y, contentHeight, isLayoutRtl);
      }

      int var10000;
      if (this.mCustomView != null) {
         var10000 = x + this.positionChild(this.mCustomView, x, y, contentHeight, isLayoutRtl);
      }

      x = isLayoutRtl ? this.getPaddingLeft() : r - l - this.getPaddingRight();
      if (this.mMenuView != null) {
         var10000 = x + this.positionChild(this.mMenuView, x, y, contentHeight, !isLayoutRtl);
      }

   }

   public boolean shouldDelayChildPressedState() {
      return false;
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
      if (event.getEventType() == 32) {
         event.setSource(this);
         event.setClassName(this.getClass().getName());
         event.setPackageName(this.getContext().getPackageName());
         event.setContentDescription(this.mTitle);
      } else {
         super.onInitializeAccessibilityEvent(event);
      }

   }

   public void setTitleOptional(boolean titleOptional) {
      if (titleOptional != this.mTitleOptional) {
         this.requestLayout();
      }

      this.mTitleOptional = titleOptional;
   }

   public boolean isTitleOptional() {
      return this.mTitleOptional;
   }
}
