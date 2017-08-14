package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ListViewCompat extends ListView {
   public static final int INVALID_POSITION = -1;
   public static final int NO_POSITION = -1;
   private static final int[] STATE_SET_NOTHING = new int[]{0};
   final Rect mSelectorRect;
   int mSelectionLeftPadding;
   int mSelectionTopPadding;
   int mSelectionRightPadding;
   int mSelectionBottomPadding;
   protected int mMotionPosition;
   private Field mIsChildViewEnabled;
   private ListViewCompat.GateKeeperDrawable mSelector;

   public ListViewCompat(Context context) {
      this(context, (AttributeSet)null);
   }

   public ListViewCompat(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ListViewCompat(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mSelectorRect = new Rect();
      this.mSelectionLeftPadding = 0;
      this.mSelectionTopPadding = 0;
      this.mSelectionRightPadding = 0;
      this.mSelectionBottomPadding = 0;

      try {
         this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
         this.mIsChildViewEnabled.setAccessible(true);
      } catch (NoSuchFieldException var5) {
         var5.printStackTrace();
      }

   }

   public void setSelector(Drawable sel) {
      this.mSelector = sel != null ? new ListViewCompat.GateKeeperDrawable(sel) : null;
      super.setSelector(this.mSelector);
      Rect padding = new Rect();
      if (sel != null) {
         sel.getPadding(padding);
      }

      this.mSelectionLeftPadding = padding.left;
      this.mSelectionTopPadding = padding.top;
      this.mSelectionRightPadding = padding.right;
      this.mSelectionBottomPadding = padding.bottom;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      this.setSelectorEnabled(true);
      this.updateSelectorStateCompat();
   }

   protected void dispatchDraw(Canvas canvas) {
      boolean drawSelectorOnTop = false;
      this.drawSelectorCompat(canvas);
      super.dispatchDraw(canvas);
   }

   public boolean onTouchEvent(MotionEvent ev) {
      switch(ev.getAction()) {
      case 0:
         this.mMotionPosition = this.pointToPosition((int)ev.getX(), (int)ev.getY());
      default:
         return super.onTouchEvent(ev);
      }
   }

   protected void updateSelectorStateCompat() {
      Drawable selector = this.getSelector();
      if (selector != null && this.shouldShowSelectorCompat()) {
         selector.setState(this.getDrawableState());
      }

   }

   protected boolean shouldShowSelectorCompat() {
      return this.touchModeDrawsInPressedStateCompat() && this.isPressed();
   }

   protected boolean touchModeDrawsInPressedStateCompat() {
      return false;
   }

   protected void drawSelectorCompat(Canvas canvas) {
      if (!this.mSelectorRect.isEmpty()) {
         Drawable selector = this.getSelector();
         if (selector != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas);
         }
      }

   }

   public int lookForSelectablePosition(int position, boolean lookDown) {
      ListAdapter adapter = this.getAdapter();
      if (adapter != null && !this.isInTouchMode()) {
         int count = adapter.getCount();
         if (this.getAdapter().areAllItemsEnabled()) {
            return position >= 0 && position < count ? position : -1;
         } else {
            if (lookDown) {
               for(position = Math.max(0, position); position < count && !adapter.isEnabled(position); ++position) {
                  ;
               }
            } else {
               for(position = Math.min(position, count - 1); position >= 0 && !adapter.isEnabled(position); --position) {
                  ;
               }
            }

            return position >= 0 && position < count ? position : -1;
         }
      } else {
         return -1;
      }
   }

   protected void positionSelectorLikeTouchCompat(int position, View sel, float x, float y) {
      this.positionSelectorLikeFocusCompat(position, sel);
      Drawable selector = this.getSelector();
      if (selector != null && position != -1) {
         DrawableCompat.setHotspot(selector, x, y);
      }

   }

   protected void positionSelectorLikeFocusCompat(int position, View sel) {
      Drawable selector = this.getSelector();
      boolean manageState = selector != null && position != -1;
      if (manageState) {
         selector.setVisible(false, false);
      }

      this.positionSelectorCompat(position, sel);
      if (manageState) {
         Rect bounds = this.mSelectorRect;
         float x = bounds.exactCenterX();
         float y = bounds.exactCenterY();
         selector.setVisible(this.getVisibility() == 0, false);
         DrawableCompat.setHotspot(selector, x, y);
      }

   }

   protected void positionSelectorCompat(int position, View sel) {
      Rect selectorRect = this.mSelectorRect;
      selectorRect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
      selectorRect.left -= this.mSelectionLeftPadding;
      selectorRect.top -= this.mSelectionTopPadding;
      selectorRect.right += this.mSelectionRightPadding;
      selectorRect.bottom += this.mSelectionBottomPadding;

      try {
         boolean isChildViewEnabled = this.mIsChildViewEnabled.getBoolean(this);
         if (sel.isEnabled() != isChildViewEnabled) {
            this.mIsChildViewEnabled.set(this, !isChildViewEnabled);
            if (position != -1) {
               this.refreshDrawableState();
            }
         }
      } catch (IllegalAccessException var5) {
         var5.printStackTrace();
      }

   }

   public int measureHeightOfChildrenCompat(int widthMeasureSpec, int startPosition, int endPosition, int maxHeight, int disallowPartialChildPosition) {
      int paddingTop = this.getListPaddingTop();
      int paddingBottom = this.getListPaddingBottom();
      int paddingLeft = this.getListPaddingLeft();
      int paddingRight = this.getListPaddingRight();
      int reportedDividerHeight = this.getDividerHeight();
      Drawable divider = this.getDivider();
      ListAdapter adapter = this.getAdapter();
      if (adapter == null) {
         return paddingTop + paddingBottom;
      } else {
         int returnedHeight = paddingTop + paddingBottom;
         int dividerHeight = reportedDividerHeight > 0 && divider != null ? reportedDividerHeight : 0;
         int prevHeightWithoutPartialChild = 0;
         View child = null;
         int viewType = 0;
         int count = adapter.getCount();

         for(int i = 0; i < count; ++i) {
            int newType = adapter.getItemViewType(i);
            if (newType != viewType) {
               child = null;
               viewType = newType;
            }

            child = adapter.getView(i, child, this);
            LayoutParams childLp = child.getLayoutParams();
            if (childLp == null) {
               childLp = this.generateDefaultLayoutParams();
               child.setLayoutParams(childLp);
            }

            int heightMeasureSpec;
            if (childLp.height > 0) {
               heightMeasureSpec = MeasureSpec.makeMeasureSpec(childLp.height, 1073741824);
            } else {
               heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            }

            child.measure(widthMeasureSpec, heightMeasureSpec);
            child.forceLayout();
            if (i > 0) {
               returnedHeight += dividerHeight;
            }

            returnedHeight += child.getMeasuredHeight();
            if (returnedHeight >= maxHeight) {
               return disallowPartialChildPosition >= 0 && i > disallowPartialChildPosition && prevHeightWithoutPartialChild > 0 && returnedHeight != maxHeight ? prevHeightWithoutPartialChild : maxHeight;
            }

            if (disallowPartialChildPosition >= 0 && i >= disallowPartialChildPosition) {
               prevHeightWithoutPartialChild = returnedHeight;
            }
         }

         return returnedHeight;
      }
   }

   protected void setSelectorEnabled(boolean enabled) {
      if (this.mSelector != null) {
         this.mSelector.setEnabled(enabled);
      }

   }

   private static class GateKeeperDrawable extends DrawableWrapper {
      private boolean mEnabled = true;

      public GateKeeperDrawable(Drawable drawable) {
         super(drawable);
      }

      void setEnabled(boolean enabled) {
         this.mEnabled = enabled;
      }

      public boolean setState(int[] stateSet) {
         return this.mEnabled ? super.setState(stateSet) : false;
      }

      public void draw(Canvas canvas) {
         if (this.mEnabled) {
            super.draw(canvas);
         }

      }

      public void setHotspot(float x, float y) {
         if (this.mEnabled) {
            super.setHotspot(x, y);
         }

      }

      public void setHotspotBounds(int left, int top, int right, int bottom) {
         if (this.mEnabled) {
            super.setHotspotBounds(left, top, right, bottom);
         }

      }

      public boolean setVisible(boolean visible, boolean restart) {
         return this.mEnabled ? super.setVisible(visible, restart) : false;
      }
   }
}
