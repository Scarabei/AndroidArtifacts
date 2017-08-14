package android.support.v7.widget;

import android.view.View;

class LayoutState {
   static final String TAG = "LayoutState";
   static final int LAYOUT_START = -1;
   static final int LAYOUT_END = 1;
   static final int INVALID_LAYOUT = Integer.MIN_VALUE;
   static final int ITEM_DIRECTION_HEAD = -1;
   static final int ITEM_DIRECTION_TAIL = 1;
   boolean mRecycle = true;
   int mAvailable;
   int mCurrentPosition;
   int mItemDirection;
   int mLayoutDirection;
   int mStartLine = 0;
   int mEndLine = 0;
   boolean mStopInFocusable;
   boolean mInfinite;

   boolean hasMore(RecyclerView.State state) {
      return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
   }

   View next(RecyclerView.Recycler recycler) {
      View view = recycler.getViewForPosition(this.mCurrentPosition);
      this.mCurrentPosition += this.mItemDirection;
      return view;
   }

   public String toString() {
      return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
   }
}
