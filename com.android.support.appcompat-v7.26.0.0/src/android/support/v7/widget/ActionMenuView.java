package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
   private static final String TAG = "ActionMenuView";
   static final int MIN_CELL_SIZE = 56;
   static final int GENERATED_ITEM_PADDING = 4;
   private MenuBuilder mMenu;
   private Context mPopupContext;
   private int mPopupTheme;
   private boolean mReserveOverflow;
   private ActionMenuPresenter mPresenter;
   private MenuPresenter.Callback mActionMenuPresenterCallback;
   MenuBuilder.Callback mMenuBuilderCallback;
   private boolean mFormatItems;
   private int mFormatItemsWidth;
   private int mMinCellSize;
   private int mGeneratedItemPadding;
   ActionMenuView.OnMenuItemClickListener mOnMenuItemClickListener;

   public ActionMenuView(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActionMenuView(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.setBaselineAligned(false);
      float density = context.getResources().getDisplayMetrics().density;
      this.mMinCellSize = (int)(56.0F * density);
      this.mGeneratedItemPadding = (int)(4.0F * density);
      this.mPopupContext = context;
      this.mPopupTheme = 0;
   }

   public void setPopupTheme(@StyleRes int resId) {
      if (this.mPopupTheme != resId) {
         this.mPopupTheme = resId;
         if (resId == 0) {
            this.mPopupContext = this.getContext();
         } else {
            this.mPopupContext = new ContextThemeWrapper(this.getContext(), resId);
         }
      }

   }

   public int getPopupTheme() {
      return this.mPopupTheme;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setPresenter(ActionMenuPresenter presenter) {
      this.mPresenter = presenter;
      this.mPresenter.setMenuView(this);
   }

   public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      if (this.mPresenter != null) {
         this.mPresenter.updateMenuView(false);
         if (this.mPresenter.isOverflowMenuShowing()) {
            this.mPresenter.hideOverflowMenu();
            this.mPresenter.showOverflowMenu();
         }
      }

   }

   public void setOnMenuItemClickListener(ActionMenuView.OnMenuItemClickListener listener) {
      this.mOnMenuItemClickListener = listener;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      boolean wasFormatted = this.mFormatItems;
      this.mFormatItems = MeasureSpec.getMode(widthMeasureSpec) == 1073741824;
      if (wasFormatted != this.mFormatItems) {
         this.mFormatItemsWidth = 0;
      }

      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      if (this.mFormatItems && this.mMenu != null && widthSize != this.mFormatItemsWidth) {
         this.mFormatItemsWidth = widthSize;
         this.mMenu.onItemsChanged(true);
      }

      int childCount = this.getChildCount();
      if (this.mFormatItems && childCount > 0) {
         this.onMeasureExactFormat(widthMeasureSpec, heightMeasureSpec);
      } else {
         for(int i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            ActionMenuView.LayoutParams lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
            lp.leftMargin = lp.rightMargin = 0;
         }

         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

   }

   private void onMeasureExactFormat(int widthMeasureSpec, int heightMeasureSpec) {
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      int widthPadding = this.getPaddingLeft() + this.getPaddingRight();
      int heightPadding = this.getPaddingTop() + this.getPaddingBottom();
      int itemHeightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, -2);
      widthSize -= widthPadding;
      int cellCount = widthSize / this.mMinCellSize;
      int cellSizeRemaining = widthSize % this.mMinCellSize;
      if (cellCount == 0) {
         this.setMeasuredDimension(widthSize, 0);
      } else {
         int cellSize = this.mMinCellSize + cellSizeRemaining / cellCount;
         int cellsRemaining = cellCount;
         int maxChildHeight = 0;
         int maxCellsUsed = 0;
         int expandableItemCount = 0;
         int visibleItemCount = 0;
         boolean hasOverflow = false;
         long smallestItemsAt = 0L;
         int childCount = this.getChildCount();

         boolean singleItem;
         int extraPixels;
         int i;
         for(int i = 0; i < childCount; ++i) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
               singleItem = child instanceof ActionMenuItemView;
               ++visibleItemCount;
               if (singleItem) {
                  child.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
               }

               ActionMenuView.LayoutParams lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
               lp.expanded = false;
               lp.extraPixels = 0;
               lp.cellsUsed = 0;
               lp.expandable = false;
               lp.leftMargin = 0;
               lp.rightMargin = 0;
               lp.preventEdgeOffset = singleItem && ((ActionMenuItemView)child).hasText();
               extraPixels = lp.isOverflowButton ? 1 : cellsRemaining;
               i = measureChildForCells(child, cellSize, extraPixels, itemHeightSpec, heightPadding);
               maxCellsUsed = Math.max(maxCellsUsed, i);
               if (lp.expandable) {
                  ++expandableItemCount;
               }

               if (lp.isOverflowButton) {
                  hasOverflow = true;
               }

               cellsRemaining -= i;
               maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
               if (i == 1) {
                  smallestItemsAt |= (long)(1 << i);
               }
            }
         }

         boolean centerSingleExpandedItem = hasOverflow && visibleItemCount == 2;

         int i;
         boolean needsExpansion;
         for(needsExpansion = false; expandableItemCount > 0 && cellsRemaining > 0; needsExpansion = true) {
            int minCells = Integer.MAX_VALUE;
            long minCellsAt = 0L;
            i = 0;

            View child;
            ActionMenuView.LayoutParams lp;
            for(i = 0; i < childCount; ++i) {
               child = this.getChildAt(i);
               lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
               if (lp.expandable) {
                  if (lp.cellsUsed < minCells) {
                     minCells = lp.cellsUsed;
                     minCellsAt = (long)(1 << i);
                     i = 1;
                  } else if (lp.cellsUsed == minCells) {
                     minCellsAt |= (long)(1 << i);
                     ++i;
                  }
               }
            }

            smallestItemsAt |= minCellsAt;
            if (i > cellsRemaining) {
               break;
            }

            ++minCells;

            for(i = 0; i < childCount; ++i) {
               child = this.getChildAt(i);
               lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
               if ((minCellsAt & (long)(1 << i)) == 0L) {
                  if (lp.cellsUsed == minCells) {
                     smallestItemsAt |= (long)(1 << i);
                  }
               } else {
                  if (centerSingleExpandedItem && lp.preventEdgeOffset && cellsRemaining == 1) {
                     child.setPadding(this.mGeneratedItemPadding + cellSize, 0, this.mGeneratedItemPadding, 0);
                  }

                  ++lp.cellsUsed;
                  lp.expanded = true;
                  --cellsRemaining;
               }
            }
         }

         singleItem = !hasOverflow && visibleItemCount == 1;
         if (cellsRemaining > 0 && smallestItemsAt != 0L && (cellsRemaining < visibleItemCount - 1 || singleItem || maxCellsUsed > 1)) {
            float expandCount = (float)Long.bitCount(smallestItemsAt);
            if (!singleItem) {
               ActionMenuView.LayoutParams lp;
               if ((smallestItemsAt & 1L) != 0L) {
                  lp = (ActionMenuView.LayoutParams)this.getChildAt(0).getLayoutParams();
                  if (!lp.preventEdgeOffset) {
                     expandCount -= 0.5F;
                  }
               }

               if ((smallestItemsAt & (long)(1 << childCount - 1)) != 0L) {
                  lp = (ActionMenuView.LayoutParams)this.getChildAt(childCount - 1).getLayoutParams();
                  if (!lp.preventEdgeOffset) {
                     expandCount -= 0.5F;
                  }
               }
            }

            extraPixels = expandCount > 0.0F ? (int)((float)(cellsRemaining * cellSize) / expandCount) : 0;

            for(i = 0; i < childCount; ++i) {
               if ((smallestItemsAt & (long)(1 << i)) != 0L) {
                  View child = this.getChildAt(i);
                  ActionMenuView.LayoutParams lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
                  if (child instanceof ActionMenuItemView) {
                     lp.extraPixels = extraPixels;
                     lp.expanded = true;
                     if (i == 0 && !lp.preventEdgeOffset) {
                        lp.leftMargin = -extraPixels / 2;
                     }

                     needsExpansion = true;
                  } else if (lp.isOverflowButton) {
                     lp.extraPixels = extraPixels;
                     lp.expanded = true;
                     lp.rightMargin = -extraPixels / 2;
                     needsExpansion = true;
                  } else {
                     if (i != 0) {
                        lp.leftMargin = extraPixels / 2;
                     }

                     if (i != childCount - 1) {
                        lp.rightMargin = extraPixels / 2;
                     }
                  }
               }
            }

            boolean var30 = false;
         }

         if (needsExpansion) {
            for(int i = 0; i < childCount; ++i) {
               View child = this.getChildAt(i);
               ActionMenuView.LayoutParams lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
               if (lp.expanded) {
                  i = lp.cellsUsed * cellSize + lp.extraPixels;
                  child.measure(MeasureSpec.makeMeasureSpec(i, 1073741824), itemHeightSpec);
               }
            }
         }

         if (heightMode != 1073741824) {
            heightSize = maxChildHeight;
         }

         this.setMeasuredDimension(widthSize, heightSize);
      }
   }

   static int measureChildForCells(View child, int cellSize, int cellsRemaining, int parentHeightMeasureSpec, int parentHeightPadding) {
      ActionMenuView.LayoutParams lp = (ActionMenuView.LayoutParams)child.getLayoutParams();
      int childHeightSize = MeasureSpec.getSize(parentHeightMeasureSpec) - parentHeightPadding;
      int childHeightMode = MeasureSpec.getMode(parentHeightMeasureSpec);
      int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeightSize, childHeightMode);
      ActionMenuItemView itemView = child instanceof ActionMenuItemView ? (ActionMenuItemView)child : null;
      boolean hasText = itemView != null && itemView.hasText();
      int cellsUsed = 0;
      int measuredWidth;
      if (cellsRemaining > 0 && (!hasText || cellsRemaining >= 2)) {
         int childWidthSpec = MeasureSpec.makeMeasureSpec(cellSize * cellsRemaining, Integer.MIN_VALUE);
         child.measure(childWidthSpec, childHeightSpec);
         measuredWidth = child.getMeasuredWidth();
         cellsUsed = measuredWidth / cellSize;
         if (measuredWidth % cellSize != 0) {
            ++cellsUsed;
         }

         if (hasText && cellsUsed < 2) {
            cellsUsed = 2;
         }
      }

      boolean expandable = !lp.isOverflowButton && hasText;
      lp.expandable = expandable;
      lp.cellsUsed = cellsUsed;
      measuredWidth = cellsUsed * cellSize;
      child.measure(MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), childHeightSpec);
      return cellsUsed;
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      if (!this.mFormatItems) {
         super.onLayout(changed, left, top, right, bottom);
      } else {
         int childCount = this.getChildCount();
         int midVertical = (bottom - top) / 2;
         int dividerWidth = this.getDividerWidth();
         int overflowWidth = false;
         int nonOverflowWidth = 0;
         int nonOverflowCount = 0;
         int widthRemaining = right - left - this.getPaddingRight() - this.getPaddingLeft();
         boolean hasOverflow = false;
         boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);

         int i;
         int height;
         int r;
         int l;
         int width;
         int height;
         for(i = 0; i < childCount; ++i) {
            View v = this.getChildAt(i);
            if (v.getVisibility() != 8) {
               ActionMenuView.LayoutParams p = (ActionMenuView.LayoutParams)v.getLayoutParams();
               if (p.isOverflowButton) {
                  int overflowWidth = v.getMeasuredWidth();
                  if (this.hasSupportDividerBeforeChildAt(i)) {
                     overflowWidth += dividerWidth;
                  }

                  height = v.getMeasuredHeight();
                  if (isLayoutRtl) {
                     l = this.getPaddingLeft() + p.leftMargin;
                     r = l + overflowWidth;
                  } else {
                     r = this.getWidth() - this.getPaddingRight() - p.rightMargin;
                     l = r - overflowWidth;
                  }

                  width = midVertical - height / 2;
                  height = width + height;
                  v.layout(l, width, r, height);
                  widthRemaining -= overflowWidth;
                  hasOverflow = true;
               } else {
                  height = v.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                  nonOverflowWidth += height;
                  widthRemaining -= height;
                  if (this.hasSupportDividerBeforeChildAt(i)) {
                     nonOverflowWidth += dividerWidth;
                  }

                  ++nonOverflowCount;
               }
            }
         }

         int spacerSize;
         int startRight;
         if (childCount == 1 && !hasOverflow) {
            View v = this.getChildAt(0);
            spacerSize = v.getMeasuredWidth();
            startRight = v.getMeasuredHeight();
            height = (right - left) / 2;
            r = height - spacerSize / 2;
            l = midVertical - startRight / 2;
            v.layout(r, l, r + spacerSize, l + startRight);
         } else {
            i = nonOverflowCount - (hasOverflow ? 0 : 1);
            spacerSize = Math.max(0, i > 0 ? widthRemaining / i : 0);
            int t;
            View v;
            ActionMenuView.LayoutParams lp;
            if (isLayoutRtl) {
               startRight = this.getWidth() - this.getPaddingRight();

               for(height = 0; height < childCount; ++height) {
                  v = this.getChildAt(height);
                  lp = (ActionMenuView.LayoutParams)v.getLayoutParams();
                  if (v.getVisibility() != 8 && !lp.isOverflowButton) {
                     startRight -= lp.rightMargin;
                     width = v.getMeasuredWidth();
                     height = v.getMeasuredHeight();
                     t = midVertical - height / 2;
                     v.layout(startRight - width, t, startRight, t + height);
                     startRight -= width + lp.leftMargin + spacerSize;
                  }
               }
            } else {
               startRight = this.getPaddingLeft();

               for(height = 0; height < childCount; ++height) {
                  v = this.getChildAt(height);
                  lp = (ActionMenuView.LayoutParams)v.getLayoutParams();
                  if (v.getVisibility() != 8 && !lp.isOverflowButton) {
                     startRight += lp.leftMargin;
                     width = v.getMeasuredWidth();
                     height = v.getMeasuredHeight();
                     t = midVertical - height / 2;
                     v.layout(startRight, t, startRight + width, t + height);
                     startRight += width + lp.rightMargin + spacerSize;
                  }
               }
            }

         }
      }
   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.dismissPopupMenus();
   }

   public void setOverflowIcon(@Nullable Drawable icon) {
      this.getMenu();
      this.mPresenter.setOverflowIcon(icon);
   }

   @Nullable
   public Drawable getOverflowIcon() {
      this.getMenu();
      return this.mPresenter.getOverflowIcon();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isOverflowReserved() {
      return this.mReserveOverflow;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setOverflowReserved(boolean reserveOverflow) {
      this.mReserveOverflow = reserveOverflow;
   }

   protected ActionMenuView.LayoutParams generateDefaultLayoutParams() {
      ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(-2, -2);
      params.gravity = 16;
      return params;
   }

   public ActionMenuView.LayoutParams generateLayoutParams(AttributeSet attrs) {
      return new ActionMenuView.LayoutParams(this.getContext(), attrs);
   }

   protected ActionMenuView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
      if (p != null) {
         ActionMenuView.LayoutParams result = p instanceof ActionMenuView.LayoutParams ? new ActionMenuView.LayoutParams((ActionMenuView.LayoutParams)p) : new ActionMenuView.LayoutParams(p);
         if (result.gravity <= 0) {
            result.gravity = 16;
         }

         return result;
      } else {
         return this.generateDefaultLayoutParams();
      }
   }

   protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
      return p != null && p instanceof ActionMenuView.LayoutParams;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public ActionMenuView.LayoutParams generateOverflowButtonLayoutParams() {
      ActionMenuView.LayoutParams result = this.generateDefaultLayoutParams();
      result.isOverflowButton = true;
      return result;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean invokeItem(MenuItemImpl item) {
      return this.mMenu.performItemAction(item, 0);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int getWindowAnimations() {
      return 0;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void initialize(MenuBuilder menu) {
      this.mMenu = menu;
   }

   public Menu getMenu() {
      if (this.mMenu == null) {
         Context context = this.getContext();
         this.mMenu = new MenuBuilder(context);
         this.mMenu.setCallback(new ActionMenuView.MenuBuilderCallback());
         this.mPresenter = new ActionMenuPresenter(context);
         this.mPresenter.setReserveOverflow(true);
         this.mPresenter.setCallback((MenuPresenter.Callback)(this.mActionMenuPresenterCallback != null ? this.mActionMenuPresenterCallback : new ActionMenuView.ActionMenuPresenterCallback()));
         this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
         this.mPresenter.setMenuView(this);
      }

      return this.mMenu;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setMenuCallbacks(MenuPresenter.Callback pcb, MenuBuilder.Callback mcb) {
      this.mActionMenuPresenterCallback = pcb;
      this.mMenuBuilderCallback = mcb;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public MenuBuilder peekMenu() {
      return this.mMenu;
   }

   public boolean showOverflowMenu() {
      return this.mPresenter != null && this.mPresenter.showOverflowMenu();
   }

   public boolean hideOverflowMenu() {
      return this.mPresenter != null && this.mPresenter.hideOverflowMenu();
   }

   public boolean isOverflowMenuShowing() {
      return this.mPresenter != null && this.mPresenter.isOverflowMenuShowing();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isOverflowMenuShowPending() {
      return this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending();
   }

   public void dismissPopupMenus() {
      if (this.mPresenter != null) {
         this.mPresenter.dismissPopupMenus();
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected boolean hasSupportDividerBeforeChildAt(int childIndex) {
      if (childIndex == 0) {
         return false;
      } else {
         View childBefore = this.getChildAt(childIndex - 1);
         View child = this.getChildAt(childIndex);
         boolean result = false;
         if (childIndex < this.getChildCount() && childBefore instanceof ActionMenuView.ActionMenuChildView) {
            result |= ((ActionMenuView.ActionMenuChildView)childBefore).needsDividerAfter();
         }

         if (childIndex > 0 && child instanceof ActionMenuView.ActionMenuChildView) {
            result |= ((ActionMenuView.ActionMenuChildView)child).needsDividerBefore();
         }

         return result;
      }
   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setExpandedActionViewsExclusive(boolean exclusive) {
      this.mPresenter.setExpandedActionViewsExclusive(exclusive);
   }

   public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
      @ExportedProperty
      public boolean isOverflowButton;
      @ExportedProperty
      public int cellsUsed;
      @ExportedProperty
      public int extraPixels;
      @ExportedProperty
      public boolean expandable;
      @ExportedProperty
      public boolean preventEdgeOffset;
      boolean expanded;

      public LayoutParams(Context c, AttributeSet attrs) {
         super(c, attrs);
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams other) {
         super(other);
      }

      public LayoutParams(ActionMenuView.LayoutParams other) {
         super((android.view.ViewGroup.LayoutParams)other);
         this.isOverflowButton = other.isOverflowButton;
      }

      public LayoutParams(int width, int height) {
         super(width, height);
         this.isOverflowButton = false;
      }

      LayoutParams(int width, int height, boolean isOverflowButton) {
         super(width, height);
         this.isOverflowButton = isOverflowButton;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public interface ActionMenuChildView {
      boolean needsDividerBefore();

      boolean needsDividerAfter();
   }

   private static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
      }

      public boolean onOpenSubMenu(MenuBuilder subMenu) {
         return false;
      }
   }

   private class MenuBuilderCallback implements MenuBuilder.Callback {
      public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
         return ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(item);
      }

      public void onMenuModeChange(MenuBuilder menu) {
         if (ActionMenuView.this.mMenuBuilderCallback != null) {
            ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menu);
         }

      }
   }

   public interface OnMenuItemClickListener {
      boolean onMenuItemClick(MenuItem var1);
   }
}
