package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R.attr;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

class DropDownListView extends ListViewCompat {
   private boolean mListSelectionHidden;
   private boolean mHijackFocus;
   private boolean mDrawsInPressedState;
   private ViewPropertyAnimatorCompat mClickAnimation;
   private ListViewAutoScrollHelper mScrollHelper;

   public DropDownListView(Context context, boolean hijackFocus) {
      super(context, (AttributeSet)null, attr.dropDownListViewStyle);
      this.mHijackFocus = hijackFocus;
      this.setCacheColorHint(0);
   }

   public boolean onForwardedEvent(MotionEvent event, int activePointerId) {
      boolean handledEvent = true;
      boolean clearPressedItem = false;
      int actionMasked = event.getActionMasked();
      switch(actionMasked) {
      case 1:
         handledEvent = false;
      case 2:
         int activeIndex = event.findPointerIndex(activePointerId);
         if (activeIndex < 0) {
            handledEvent = false;
         } else {
            int x = (int)event.getX(activeIndex);
            int y = (int)event.getY(activeIndex);
            int position = this.pointToPosition(x, y);
            if (position == -1) {
               clearPressedItem = true;
            } else {
               View child = this.getChildAt(position - this.getFirstVisiblePosition());
               this.setPressedItem(child, position, (float)x, (float)y);
               handledEvent = true;
               if (actionMasked == 1) {
                  this.clickPressedItem(child, position);
               }
            }
         }
         break;
      case 3:
         handledEvent = false;
      }

      if (!handledEvent || clearPressedItem) {
         this.clearPressedItem();
      }

      if (handledEvent) {
         if (this.mScrollHelper == null) {
            this.mScrollHelper = new ListViewAutoScrollHelper(this);
         }

         this.mScrollHelper.setEnabled(true);
         this.mScrollHelper.onTouch(this, event);
      } else if (this.mScrollHelper != null) {
         this.mScrollHelper.setEnabled(false);
      }

      return handledEvent;
   }

   private void clickPressedItem(View child, int position) {
      long id = this.getItemIdAtPosition(position);
      this.performItemClick(child, position, id);
   }

   void setListSelectionHidden(boolean hideListSelection) {
      this.mListSelectionHidden = hideListSelection;
   }

   private void clearPressedItem() {
      this.mDrawsInPressedState = false;
      this.setPressed(false);
      this.drawableStateChanged();
      View motionView = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
      if (motionView != null) {
         motionView.setPressed(false);
      }

      if (this.mClickAnimation != null) {
         this.mClickAnimation.cancel();
         this.mClickAnimation = null;
      }

   }

   private void setPressedItem(View child, int position, float x, float y) {
      this.mDrawsInPressedState = true;
      if (VERSION.SDK_INT >= 21) {
         this.drawableHotspotChanged(x, y);
      }

      if (!this.isPressed()) {
         this.setPressed(true);
      }

      this.layoutChildren();
      if (this.mMotionPosition != -1) {
         View motionView = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
         if (motionView != null && motionView != child && motionView.isPressed()) {
            motionView.setPressed(false);
         }
      }

      this.mMotionPosition = position;
      float childX = x - (float)child.getLeft();
      float childY = y - (float)child.getTop();
      if (VERSION.SDK_INT >= 21) {
         child.drawableHotspotChanged(childX, childY);
      }

      if (!child.isPressed()) {
         child.setPressed(true);
      }

      this.positionSelectorLikeTouchCompat(position, child, x, y);
      this.setSelectorEnabled(false);
      this.refreshDrawableState();
   }

   protected boolean touchModeDrawsInPressedStateCompat() {
      return this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
   }

   public boolean isInTouchMode() {
      return this.mHijackFocus && this.mListSelectionHidden || super.isInTouchMode();
   }

   public boolean hasWindowFocus() {
      return this.mHijackFocus || super.hasWindowFocus();
   }

   public boolean isFocused() {
      return this.mHijackFocus || super.isFocused();
   }

   public boolean hasFocus() {
      return this.mHijackFocus || super.hasFocus();
   }
}
