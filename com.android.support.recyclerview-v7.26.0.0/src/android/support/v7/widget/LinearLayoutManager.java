package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
   private static final String TAG = "LinearLayoutManager";
   static final boolean DEBUG = false;
   public static final int HORIZONTAL = 0;
   public static final int VERTICAL = 1;
   public static final int INVALID_OFFSET = Integer.MIN_VALUE;
   private static final float MAX_SCROLL_FACTOR = 0.33333334F;
   int mOrientation;
   private LinearLayoutManager.LayoutState mLayoutState;
   OrientationHelper mOrientationHelper;
   private boolean mLastStackFromEnd;
   private boolean mReverseLayout;
   boolean mShouldReverseLayout;
   private boolean mStackFromEnd;
   private boolean mSmoothScrollbarEnabled;
   int mPendingScrollPosition;
   int mPendingScrollPositionOffset;
   private boolean mRecycleChildrenOnDetach;
   LinearLayoutManager.SavedState mPendingSavedState;
   final LinearLayoutManager.AnchorInfo mAnchorInfo;
   private final LinearLayoutManager.LayoutChunkResult mLayoutChunkResult;
   private int mInitialPrefetchItemCount;

   public LinearLayoutManager(Context context) {
      this(context, 1, false);
   }

   public LinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
      this.mReverseLayout = false;
      this.mShouldReverseLayout = false;
      this.mStackFromEnd = false;
      this.mSmoothScrollbarEnabled = true;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mPendingSavedState = null;
      this.mAnchorInfo = new LinearLayoutManager.AnchorInfo();
      this.mLayoutChunkResult = new LinearLayoutManager.LayoutChunkResult();
      this.mInitialPrefetchItemCount = 2;
      this.setOrientation(orientation);
      this.setReverseLayout(reverseLayout);
      this.setAutoMeasureEnabled(true);
   }

   public LinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      this.mReverseLayout = false;
      this.mShouldReverseLayout = false;
      this.mStackFromEnd = false;
      this.mSmoothScrollbarEnabled = true;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mPendingSavedState = null;
      this.mAnchorInfo = new LinearLayoutManager.AnchorInfo();
      this.mLayoutChunkResult = new LinearLayoutManager.LayoutChunkResult();
      this.mInitialPrefetchItemCount = 2;
      RecyclerView.LayoutManager.Properties properties = getProperties(context, attrs, defStyleAttr, defStyleRes);
      this.setOrientation(properties.orientation);
      this.setReverseLayout(properties.reverseLayout);
      this.setStackFromEnd(properties.stackFromEnd);
      this.setAutoMeasureEnabled(true);
   }

   public RecyclerView.LayoutParams generateDefaultLayoutParams() {
      return new RecyclerView.LayoutParams(-2, -2);
   }

   public boolean getRecycleChildrenOnDetach() {
      return this.mRecycleChildrenOnDetach;
   }

   public void setRecycleChildrenOnDetach(boolean recycleChildrenOnDetach) {
      this.mRecycleChildrenOnDetach = recycleChildrenOnDetach;
   }

   public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
      super.onDetachedFromWindow(view, recycler);
      if (this.mRecycleChildrenOnDetach) {
         this.removeAndRecycleAllViews(recycler);
         recycler.clear();
      }

   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
      super.onInitializeAccessibilityEvent(event);
      if (this.getChildCount() > 0) {
         event.setFromIndex(this.findFirstVisibleItemPosition());
         event.setToIndex(this.findLastVisibleItemPosition());
      }

   }

   public Parcelable onSaveInstanceState() {
      if (this.mPendingSavedState != null) {
         return new LinearLayoutManager.SavedState(this.mPendingSavedState);
      } else {
         LinearLayoutManager.SavedState state = new LinearLayoutManager.SavedState();
         if (this.getChildCount() > 0) {
            this.ensureLayoutState();
            boolean didLayoutFromEnd = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            state.mAnchorLayoutFromEnd = didLayoutFromEnd;
            View refChild;
            if (didLayoutFromEnd) {
               refChild = this.getChildClosestToEnd();
               state.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(refChild);
               state.mAnchorPosition = this.getPosition(refChild);
            } else {
               refChild = this.getChildClosestToStart();
               state.mAnchorPosition = this.getPosition(refChild);
               state.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(refChild) - this.mOrientationHelper.getStartAfterPadding();
            }
         } else {
            state.invalidateAnchor();
         }

         return state;
      }
   }

   public void onRestoreInstanceState(Parcelable state) {
      if (state instanceof LinearLayoutManager.SavedState) {
         this.mPendingSavedState = (LinearLayoutManager.SavedState)state;
         this.requestLayout();
      }

   }

   public boolean canScrollHorizontally() {
      return this.mOrientation == 0;
   }

   public boolean canScrollVertically() {
      return this.mOrientation == 1;
   }

   public void setStackFromEnd(boolean stackFromEnd) {
      this.assertNotInLayoutOrScroll((String)null);
      if (this.mStackFromEnd != stackFromEnd) {
         this.mStackFromEnd = stackFromEnd;
         this.requestLayout();
      }
   }

   public boolean getStackFromEnd() {
      return this.mStackFromEnd;
   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public void setOrientation(int orientation) {
      if (orientation != 0 && orientation != 1) {
         throw new IllegalArgumentException("invalid orientation:" + orientation);
      } else {
         this.assertNotInLayoutOrScroll((String)null);
         if (orientation != this.mOrientation) {
            this.mOrientation = orientation;
            this.mOrientationHelper = null;
            this.requestLayout();
         }
      }
   }

   private void resolveShouldLayoutReverse() {
      if (this.mOrientation != 1 && this.isLayoutRTL()) {
         this.mShouldReverseLayout = !this.mReverseLayout;
      } else {
         this.mShouldReverseLayout = this.mReverseLayout;
      }

   }

   public boolean getReverseLayout() {
      return this.mReverseLayout;
   }

   public void setReverseLayout(boolean reverseLayout) {
      this.assertNotInLayoutOrScroll((String)null);
      if (reverseLayout != this.mReverseLayout) {
         this.mReverseLayout = reverseLayout;
         this.requestLayout();
      }
   }

   public View findViewByPosition(int position) {
      int childCount = this.getChildCount();
      if (childCount == 0) {
         return null;
      } else {
         int firstChild = this.getPosition(this.getChildAt(0));
         int viewPosition = position - firstChild;
         if (viewPosition >= 0 && viewPosition < childCount) {
            View child = this.getChildAt(viewPosition);
            if (this.getPosition(child) == position) {
               return child;
            }
         }

         return super.findViewByPosition(position);
      }
   }

   protected int getExtraLayoutSpace(RecyclerView.State state) {
      return state.hasTargetScrollPosition() ? this.mOrientationHelper.getTotalSpace() : 0;
   }

   public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
      LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
      linearSmoothScroller.setTargetPosition(position);
      this.startSmoothScroll(linearSmoothScroller);
   }

   public PointF computeScrollVectorForPosition(int targetPosition) {
      if (this.getChildCount() == 0) {
         return null;
      } else {
         int firstChildPos = this.getPosition(this.getChildAt(0));
         int direction = targetPosition < firstChildPos != this.mShouldReverseLayout ? -1 : 1;
         return this.mOrientation == 0 ? new PointF((float)direction, 0.0F) : new PointF(0.0F, (float)direction);
      }
   }

   public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
      if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
         this.removeAndRecycleAllViews(recycler);
      } else {
         if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
         }

         this.ensureLayoutState();
         this.mLayoutState.mRecycle = false;
         this.resolveShouldLayoutReverse();
         View focused = this.getFocusedChild();
         if (this.mAnchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) {
            if (focused != null && (this.mOrientationHelper.getDecoratedStart(focused) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(focused) <= this.mOrientationHelper.getStartAfterPadding())) {
               this.mAnchorInfo.assignFromViewAndKeepVisibleRect(focused);
            }
         } else {
            this.mAnchorInfo.reset();
            this.mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            this.updateAnchorInfoForLayout(recycler, state, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
         }

         int extra = this.getExtraLayoutSpace(state);
         int extraForStart;
         int extraForEnd;
         if (this.mLayoutState.mLastScrollDelta >= 0) {
            extraForEnd = extra;
            extraForStart = 0;
         } else {
            extraForStart = extra;
            extraForEnd = 0;
         }

         extraForStart += this.mOrientationHelper.getStartAfterPadding();
         extraForEnd += this.mOrientationHelper.getEndPadding();
         int endOffset;
         int upcomingOffset;
         if (state.isPreLayout() && this.mPendingScrollPosition != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
            View existing = this.findViewByPosition(this.mPendingScrollPosition);
            if (existing != null) {
               if (this.mShouldReverseLayout) {
                  endOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(existing);
                  upcomingOffset = endOffset - this.mPendingScrollPositionOffset;
               } else {
                  endOffset = this.mOrientationHelper.getDecoratedStart(existing) - this.mOrientationHelper.getStartAfterPadding();
                  upcomingOffset = this.mPendingScrollPositionOffset - endOffset;
               }

               if (upcomingOffset > 0) {
                  extraForStart += upcomingOffset;
               } else {
                  extraForEnd -= upcomingOffset;
               }
            }
         }

         if (this.mAnchorInfo.mLayoutFromEnd) {
            upcomingOffset = this.mShouldReverseLayout ? 1 : -1;
         } else {
            upcomingOffset = this.mShouldReverseLayout ? -1 : 1;
         }

         this.onAnchorReady(recycler, state, this.mAnchorInfo, upcomingOffset);
         this.detachAndScrapAttachedViews(recycler);
         this.mLayoutState.mInfinite = this.resolveIsInfinite();
         this.mLayoutState.mIsPreLayout = state.isPreLayout();
         int fixOffset;
         int startOffset;
         if (this.mAnchorInfo.mLayoutFromEnd) {
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForStart;
            this.fill(recycler, this.mLayoutState, state, false);
            startOffset = this.mLayoutState.mOffset;
            fixOffset = this.mLayoutState.mCurrentPosition;
            if (this.mLayoutState.mAvailable > 0) {
               extraForEnd += this.mLayoutState.mAvailable;
            }

            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForEnd;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state, false);
            endOffset = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
               extraForStart = this.mLayoutState.mAvailable;
               this.updateLayoutStateToFillStart(fixOffset, startOffset);
               this.mLayoutState.mExtra = extraForStart;
               this.fill(recycler, this.mLayoutState, state, false);
               startOffset = this.mLayoutState.mOffset;
            }
         } else {
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForEnd;
            this.fill(recycler, this.mLayoutState, state, false);
            endOffset = this.mLayoutState.mOffset;
            fixOffset = this.mLayoutState.mCurrentPosition;
            if (this.mLayoutState.mAvailable > 0) {
               extraForStart += this.mLayoutState.mAvailable;
            }

            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = extraForStart;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state, false);
            startOffset = this.mLayoutState.mOffset;
            if (this.mLayoutState.mAvailable > 0) {
               extraForEnd = this.mLayoutState.mAvailable;
               this.updateLayoutStateToFillEnd(fixOffset, endOffset);
               this.mLayoutState.mExtra = extraForEnd;
               this.fill(recycler, this.mLayoutState, state, false);
               endOffset = this.mLayoutState.mOffset;
            }
         }

         if (this.getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
               fixOffset = this.fixLayoutEndGap(endOffset, recycler, state, true);
               startOffset += fixOffset;
               endOffset += fixOffset;
               fixOffset = this.fixLayoutStartGap(startOffset, recycler, state, false);
               startOffset += fixOffset;
               endOffset += fixOffset;
            } else {
               fixOffset = this.fixLayoutStartGap(startOffset, recycler, state, true);
               startOffset += fixOffset;
               endOffset += fixOffset;
               fixOffset = this.fixLayoutEndGap(endOffset, recycler, state, false);
               startOffset += fixOffset;
               endOffset += fixOffset;
            }
         }

         this.layoutForPredictiveAnimations(recycler, state, startOffset, endOffset);
         if (!state.isPreLayout()) {
            this.mOrientationHelper.onLayoutComplete();
         } else {
            this.mAnchorInfo.reset();
         }

         this.mLastStackFromEnd = this.mStackFromEnd;
      }
   }

   public void onLayoutCompleted(RecyclerView.State state) {
      super.onLayoutCompleted(state);
      this.mPendingSavedState = null;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mAnchorInfo.reset();
   }

   void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int firstLayoutItemDirection) {
   }

   private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler, RecyclerView.State state, int startOffset, int endOffset) {
      if (state.willRunPredictiveAnimations() && this.getChildCount() != 0 && !state.isPreLayout() && this.supportsPredictiveItemAnimations()) {
         int scrapExtraStart = 0;
         int scrapExtraEnd = 0;
         List scrapList = recycler.getScrapList();
         int scrapSize = scrapList.size();
         int firstChildPos = this.getPosition(this.getChildAt(0));

         for(int i = 0; i < scrapSize; ++i) {
            RecyclerView.ViewHolder scrap = (RecyclerView.ViewHolder)scrapList.get(i);
            if (!scrap.isRemoved()) {
               int position = scrap.getLayoutPosition();
               int direction = position < firstChildPos != this.mShouldReverseLayout ? -1 : 1;
               if (direction == -1) {
                  scrapExtraStart += this.mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
               } else {
                  scrapExtraEnd += this.mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
               }
            }
         }

         this.mLayoutState.mScrapList = scrapList;
         View anchor;
         if (scrapExtraStart > 0) {
            anchor = this.getChildClosestToStart();
            this.updateLayoutStateToFillStart(this.getPosition(anchor), startOffset);
            this.mLayoutState.mExtra = scrapExtraStart;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(recycler, this.mLayoutState, state, false);
         }

         if (scrapExtraEnd > 0) {
            anchor = this.getChildClosestToEnd();
            this.updateLayoutStateToFillEnd(this.getPosition(anchor), endOffset);
            this.mLayoutState.mExtra = scrapExtraEnd;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(recycler, this.mLayoutState, state, false);
         }

         this.mLayoutState.mScrapList = null;
      }
   }

   private void updateAnchorInfoForLayout(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo) {
      if (!this.updateAnchorFromPendingData(state, anchorInfo)) {
         if (!this.updateAnchorFromChildren(recycler, state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = this.mStackFromEnd ? state.getItemCount() - 1 : 0;
         }
      }
   }

   private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo) {
      if (this.getChildCount() == 0) {
         return false;
      } else {
         View focused = this.getFocusedChild();
         if (focused != null && anchorInfo.isViewValidAsAnchor(focused, state)) {
            anchorInfo.assignFromViewAndKeepVisibleRect(focused);
            return true;
         } else if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
         } else {
            View referenceChild = anchorInfo.mLayoutFromEnd ? this.findReferenceChildClosestToEnd(recycler, state) : this.findReferenceChildClosestToStart(recycler, state);
            if (referenceChild == null) {
               return false;
            } else {
               anchorInfo.assignFromView(referenceChild);
               if (!state.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                  boolean notVisible = this.mOrientationHelper.getDecoratedStart(referenceChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(referenceChild) < this.mOrientationHelper.getStartAfterPadding();
                  if (notVisible) {
                     anchorInfo.mCoordinate = anchorInfo.mLayoutFromEnd ? this.mOrientationHelper.getEndAfterPadding() : this.mOrientationHelper.getStartAfterPadding();
                  }
               }

               return true;
            }
         }
      }
   }

   private boolean updateAnchorFromPendingData(RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo) {
      if (!state.isPreLayout() && this.mPendingScrollPosition != -1) {
         if (this.mPendingScrollPosition >= 0 && this.mPendingScrollPosition < state.getItemCount()) {
            anchorInfo.mPosition = this.mPendingScrollPosition;
            if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
               anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
               if (anchorInfo.mLayoutFromEnd) {
                  anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
               } else {
                  anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
               }

               return true;
            } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
               View child = this.findViewByPosition(this.mPendingScrollPosition);
               int pos;
               if (child != null) {
                  pos = this.mOrientationHelper.getDecoratedMeasurement(child);
                  if (pos > this.mOrientationHelper.getTotalSpace()) {
                     anchorInfo.assignCoordinateFromPadding();
                     return true;
                  }

                  int startGap = this.mOrientationHelper.getDecoratedStart(child) - this.mOrientationHelper.getStartAfterPadding();
                  if (startGap < 0) {
                     anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                     anchorInfo.mLayoutFromEnd = false;
                     return true;
                  }

                  int endGap = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(child);
                  if (endGap < 0) {
                     anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                     anchorInfo.mLayoutFromEnd = true;
                     return true;
                  }

                  anchorInfo.mCoordinate = anchorInfo.mLayoutFromEnd ? this.mOrientationHelper.getDecoratedEnd(child) + this.mOrientationHelper.getTotalSpaceChange() : this.mOrientationHelper.getDecoratedStart(child);
               } else {
                  if (this.getChildCount() > 0) {
                     pos = this.getPosition(this.getChildAt(0));
                     anchorInfo.mLayoutFromEnd = this.mPendingScrollPosition < pos == this.mShouldReverseLayout;
                  }

                  anchorInfo.assignCoordinateFromPadding();
               }

               return true;
            } else {
               anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
               if (this.mShouldReverseLayout) {
                  anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
               } else {
                  anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
               }

               return true;
            }
         } else {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
         }
      } else {
         return false;
      }
   }

   private int fixLayoutEndGap(int endOffset, RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
      int gap = this.mOrientationHelper.getEndAfterPadding() - endOffset;
      int fixOffset = false;
      if (gap > 0) {
         int fixOffset = -this.scrollBy(-gap, recycler, state);
         endOffset += fixOffset;
         if (canOffsetChildren) {
            gap = this.mOrientationHelper.getEndAfterPadding() - endOffset;
            if (gap > 0) {
               this.mOrientationHelper.offsetChildren(gap);
               return gap + fixOffset;
            }
         }

         return fixOffset;
      } else {
         return 0;
      }
   }

   private int fixLayoutStartGap(int startOffset, RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
      int gap = startOffset - this.mOrientationHelper.getStartAfterPadding();
      int fixOffset = false;
      if (gap > 0) {
         int fixOffset = -this.scrollBy(gap, recycler, state);
         startOffset += fixOffset;
         if (canOffsetChildren) {
            gap = startOffset - this.mOrientationHelper.getStartAfterPadding();
            if (gap > 0) {
               this.mOrientationHelper.offsetChildren(-gap);
               return fixOffset - gap;
            }
         }

         return fixOffset;
      } else {
         return 0;
      }
   }

   private void updateLayoutStateToFillEnd(LinearLayoutManager.AnchorInfo anchorInfo) {
      this.updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
   }

   private void updateLayoutStateToFillEnd(int itemPosition, int offset) {
      this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - offset;
      this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
      this.mLayoutState.mCurrentPosition = itemPosition;
      this.mLayoutState.mLayoutDirection = 1;
      this.mLayoutState.mOffset = offset;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
   }

   private void updateLayoutStateToFillStart(LinearLayoutManager.AnchorInfo anchorInfo) {
      this.updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
   }

   private void updateLayoutStateToFillStart(int itemPosition, int offset) {
      this.mLayoutState.mAvailable = offset - this.mOrientationHelper.getStartAfterPadding();
      this.mLayoutState.mCurrentPosition = itemPosition;
      this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
      this.mLayoutState.mLayoutDirection = -1;
      this.mLayoutState.mOffset = offset;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
   }

   protected boolean isLayoutRTL() {
      return this.getLayoutDirection() == 1;
   }

   void ensureLayoutState() {
      if (this.mLayoutState == null) {
         this.mLayoutState = this.createLayoutState();
      }

      if (this.mOrientationHelper == null) {
         this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
      }

   }

   LinearLayoutManager.LayoutState createLayoutState() {
      return new LinearLayoutManager.LayoutState();
   }

   public void scrollToPosition(int position) {
      this.mPendingScrollPosition = position;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      if (this.mPendingSavedState != null) {
         this.mPendingSavedState.invalidateAnchor();
      }

      this.requestLayout();
   }

   public void scrollToPositionWithOffset(int position, int offset) {
      this.mPendingScrollPosition = position;
      this.mPendingScrollPositionOffset = offset;
      if (this.mPendingSavedState != null) {
         this.mPendingSavedState.invalidateAnchor();
      }

      this.requestLayout();
   }

   public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mOrientation == 1 ? 0 : this.scrollBy(dx, recycler, state);
   }

   public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mOrientation == 0 ? 0 : this.scrollBy(dy, recycler, state);
   }

   public int computeHorizontalScrollOffset(RecyclerView.State state) {
      return this.computeScrollOffset(state);
   }

   public int computeVerticalScrollOffset(RecyclerView.State state) {
      return this.computeScrollOffset(state);
   }

   public int computeHorizontalScrollExtent(RecyclerView.State state) {
      return this.computeScrollExtent(state);
   }

   public int computeVerticalScrollExtent(RecyclerView.State state) {
      return this.computeScrollExtent(state);
   }

   public int computeHorizontalScrollRange(RecyclerView.State state) {
      return this.computeScrollRange(state);
   }

   public int computeVerticalScrollRange(RecyclerView.State state) {
      return this.computeScrollRange(state);
   }

   private int computeScrollOffset(RecyclerView.State state) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollOffset(state, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
      }
   }

   private int computeScrollExtent(RecyclerView.State state) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollExtent(state, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
      }
   }

   private int computeScrollRange(RecyclerView.State state) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollRange(state, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
      }
   }

   public void setSmoothScrollbarEnabled(boolean enabled) {
      this.mSmoothScrollbarEnabled = enabled;
   }

   public boolean isSmoothScrollbarEnabled() {
      return this.mSmoothScrollbarEnabled;
   }

   private void updateLayoutState(int layoutDirection, int requiredSpace, boolean canUseExistingSpace, RecyclerView.State state) {
      this.mLayoutState.mInfinite = this.resolveIsInfinite();
      this.mLayoutState.mExtra = this.getExtraLayoutSpace(state);
      this.mLayoutState.mLayoutDirection = layoutDirection;
      int scrollingOffset;
      View child;
      if (layoutDirection == 1) {
         this.mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
         child = this.getChildClosestToEnd();
         this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
         this.mLayoutState.mCurrentPosition = this.getPosition(child) + this.mLayoutState.mItemDirection;
         this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(child);
         scrollingOffset = this.mOrientationHelper.getDecoratedEnd(child) - this.mOrientationHelper.getEndAfterPadding();
      } else {
         child = this.getChildClosestToStart();
         this.mLayoutState.mExtra += this.mOrientationHelper.getStartAfterPadding();
         this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
         this.mLayoutState.mCurrentPosition = this.getPosition(child) + this.mLayoutState.mItemDirection;
         this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(child);
         scrollingOffset = -this.mOrientationHelper.getDecoratedStart(child) + this.mOrientationHelper.getStartAfterPadding();
      }

      this.mLayoutState.mAvailable = requiredSpace;
      if (canUseExistingSpace) {
         this.mLayoutState.mAvailable -= scrollingOffset;
      }

      this.mLayoutState.mScrollingOffset = scrollingOffset;
   }

   boolean resolveIsInfinite() {
      return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
   }

   void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
      int pos = layoutState.mCurrentPosition;
      if (pos >= 0 && pos < state.getItemCount()) {
         layoutPrefetchRegistry.addPosition(pos, Math.max(0, layoutState.mScrollingOffset));
      }

   }

   public void collectInitialPrefetchPositions(int adapterItemCount, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
      boolean fromEnd;
      int anchorPos;
      if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
         fromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
         anchorPos = this.mPendingSavedState.mAnchorPosition;
      } else {
         this.resolveShouldLayoutReverse();
         fromEnd = this.mShouldReverseLayout;
         if (this.mPendingScrollPosition == -1) {
            anchorPos = fromEnd ? adapterItemCount - 1 : 0;
         } else {
            anchorPos = this.mPendingScrollPosition;
         }
      }

      int direction = fromEnd ? -1 : 1;
      int targetPos = anchorPos;

      for(int i = 0; i < this.mInitialPrefetchItemCount && targetPos >= 0 && targetPos < adapterItemCount; ++i) {
         layoutPrefetchRegistry.addPosition(targetPos, 0);
         targetPos += direction;
      }

   }

   public void setInitialPrefetchItemCount(int itemCount) {
      this.mInitialPrefetchItemCount = itemCount;
   }

   public int getInitialPrefetchItemCount() {
      return this.mInitialPrefetchItemCount;
   }

   public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
      int delta = this.mOrientation == 0 ? dx : dy;
      if (this.getChildCount() != 0 && delta != 0) {
         this.ensureLayoutState();
         int layoutDirection = delta > 0 ? 1 : -1;
         int absDy = Math.abs(delta);
         this.updateLayoutState(layoutDirection, absDy, true, state);
         this.collectPrefetchPositionsForLayoutState(state, this.mLayoutState, layoutPrefetchRegistry);
      }
   }

   int scrollBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
      if (this.getChildCount() != 0 && dy != 0) {
         this.mLayoutState.mRecycle = true;
         this.ensureLayoutState();
         int layoutDirection = dy > 0 ? 1 : -1;
         int absDy = Math.abs(dy);
         this.updateLayoutState(layoutDirection, absDy, true, state);
         int consumed = this.mLayoutState.mScrollingOffset + this.fill(recycler, this.mLayoutState, state, false);
         if (consumed < 0) {
            return 0;
         } else {
            int scrolled = absDy > consumed ? layoutDirection * consumed : dy;
            this.mOrientationHelper.offsetChildren(-scrolled);
            this.mLayoutState.mLastScrollDelta = scrolled;
            return scrolled;
         }
      } else {
         return 0;
      }
   }

   public void assertNotInLayoutOrScroll(String message) {
      if (this.mPendingSavedState == null) {
         super.assertNotInLayoutOrScroll(message);
      }

   }

   private void recycleChildren(RecyclerView.Recycler recycler, int startIndex, int endIndex) {
      if (startIndex != endIndex) {
         int i;
         if (endIndex > startIndex) {
            for(i = endIndex - 1; i >= startIndex; --i) {
               this.removeAndRecycleViewAt(i, recycler);
            }
         } else {
            for(i = startIndex; i > endIndex; --i) {
               this.removeAndRecycleViewAt(i, recycler);
            }
         }

      }
   }

   private void recycleViewsFromStart(RecyclerView.Recycler recycler, int dt) {
      if (dt >= 0) {
         int limit = dt;
         int childCount = this.getChildCount();
         int i;
         View child;
         if (this.mShouldReverseLayout) {
            for(i = childCount - 1; i >= 0; --i) {
               child = this.getChildAt(i);
               if (this.mOrientationHelper.getDecoratedEnd(child) > limit || this.mOrientationHelper.getTransformedEndWithDecoration(child) > limit) {
                  this.recycleChildren(recycler, childCount - 1, i);
                  return;
               }
            }
         } else {
            for(i = 0; i < childCount; ++i) {
               child = this.getChildAt(i);
               if (this.mOrientationHelper.getDecoratedEnd(child) > limit || this.mOrientationHelper.getTransformedEndWithDecoration(child) > limit) {
                  this.recycleChildren(recycler, 0, i);
                  return;
               }
            }
         }

      }
   }

   private void recycleViewsFromEnd(RecyclerView.Recycler recycler, int dt) {
      int childCount = this.getChildCount();
      if (dt >= 0) {
         int limit = this.mOrientationHelper.getEnd() - dt;
         int i;
         View child;
         if (this.mShouldReverseLayout) {
            for(i = 0; i < childCount; ++i) {
               child = this.getChildAt(i);
               if (this.mOrientationHelper.getDecoratedStart(child) < limit || this.mOrientationHelper.getTransformedStartWithDecoration(child) < limit) {
                  this.recycleChildren(recycler, 0, i);
                  return;
               }
            }
         } else {
            for(i = childCount - 1; i >= 0; --i) {
               child = this.getChildAt(i);
               if (this.mOrientationHelper.getDecoratedStart(child) < limit || this.mOrientationHelper.getTransformedStartWithDecoration(child) < limit) {
                  this.recycleChildren(recycler, childCount - 1, i);
                  return;
               }
            }
         }

      }
   }

   private void recycleByLayoutState(RecyclerView.Recycler recycler, LinearLayoutManager.LayoutState layoutState) {
      if (layoutState.mRecycle && !layoutState.mInfinite) {
         if (layoutState.mLayoutDirection == -1) {
            this.recycleViewsFromEnd(recycler, layoutState.mScrollingOffset);
         } else {
            this.recycleViewsFromStart(recycler, layoutState.mScrollingOffset);
         }

      }
   }

   int fill(RecyclerView.Recycler recycler, LinearLayoutManager.LayoutState layoutState, RecyclerView.State state, boolean stopOnFocusable) {
      int start = layoutState.mAvailable;
      if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
         if (layoutState.mAvailable < 0) {
            layoutState.mScrollingOffset += layoutState.mAvailable;
         }

         this.recycleByLayoutState(recycler, layoutState);
      }

      int remainingSpace = layoutState.mAvailable + layoutState.mExtra;
      LinearLayoutManager.LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;

      while((layoutState.mInfinite || remainingSpace > 0) && layoutState.hasMore(state)) {
         layoutChunkResult.resetInternal();
         this.layoutChunk(recycler, state, layoutState, layoutChunkResult);
         if (layoutChunkResult.mFinished) {
            break;
         }

         layoutState.mOffset += layoutChunkResult.mConsumed * layoutState.mLayoutDirection;
         if (!layoutChunkResult.mIgnoreConsumed || this.mLayoutState.mScrapList != null || !state.isPreLayout()) {
            layoutState.mAvailable -= layoutChunkResult.mConsumed;
            remainingSpace -= layoutChunkResult.mConsumed;
         }

         if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            layoutState.mScrollingOffset += layoutChunkResult.mConsumed;
            if (layoutState.mAvailable < 0) {
               layoutState.mScrollingOffset += layoutState.mAvailable;
            }

            this.recycleByLayoutState(recycler, layoutState);
         }

         if (stopOnFocusable && layoutChunkResult.mFocusable) {
            break;
         }
      }

      return start - layoutState.mAvailable;
   }

   void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult result) {
      View view = layoutState.next(recycler);
      if (view == null) {
         result.mFinished = true;
      } else {
         RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
         if (layoutState.mScrapList == null) {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
               this.addView(view);
            } else {
               this.addView(view, 0);
            }
         } else if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
            this.addDisappearingView(view);
         } else {
            this.addDisappearingView(view, 0);
         }

         this.measureChildWithMargins(view, 0, 0);
         result.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(view);
         int left;
         int top;
         int right;
         int bottom;
         if (this.mOrientation == 1) {
            if (this.isLayoutRTL()) {
               right = this.getWidth() - this.getPaddingRight();
               left = right - this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            } else {
               left = this.getPaddingLeft();
               right = left + this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            }

            if (layoutState.mLayoutDirection == -1) {
               bottom = layoutState.mOffset;
               top = layoutState.mOffset - result.mConsumed;
            } else {
               top = layoutState.mOffset;
               bottom = layoutState.mOffset + result.mConsumed;
            }
         } else {
            top = this.getPaddingTop();
            bottom = top + this.mOrientationHelper.getDecoratedMeasurementInOther(view);
            if (layoutState.mLayoutDirection == -1) {
               right = layoutState.mOffset;
               left = layoutState.mOffset - result.mConsumed;
            } else {
               left = layoutState.mOffset;
               right = layoutState.mOffset + result.mConsumed;
            }
         }

         this.layoutDecoratedWithMargins(view, left, top, right, bottom);
         if (params.isItemRemoved() || params.isItemChanged()) {
            result.mIgnoreConsumed = true;
         }

         result.mFocusable = view.hasFocusable();
      }
   }

   boolean shouldMeasureTwice() {
      return this.getHeightMode() != 1073741824 && this.getWidthMode() != 1073741824 && this.hasFlexibleChildInBothOrientations();
   }

   int convertFocusDirectionToLayoutDirection(int focusDirection) {
      switch(focusDirection) {
      case 1:
         if (this.mOrientation == 1) {
            return -1;
         } else {
            if (this.isLayoutRTL()) {
               return 1;
            }

            return -1;
         }
      case 2:
         if (this.mOrientation == 1) {
            return 1;
         } else {
            if (this.isLayoutRTL()) {
               return -1;
            }

            return 1;
         }
      case 17:
         return this.mOrientation == 0 ? -1 : Integer.MIN_VALUE;
      case 33:
         return this.mOrientation == 1 ? -1 : Integer.MIN_VALUE;
      case 66:
         return this.mOrientation == 0 ? 1 : Integer.MIN_VALUE;
      case 130:
         return this.mOrientation == 1 ? 1 : Integer.MIN_VALUE;
      default:
         return Integer.MIN_VALUE;
      }
   }

   private View getChildClosestToStart() {
      return this.getChildAt(this.mShouldReverseLayout ? this.getChildCount() - 1 : 0);
   }

   private View getChildClosestToEnd() {
      return this.getChildAt(this.mShouldReverseLayout ? 0 : this.getChildCount() - 1);
   }

   private View findFirstVisibleChildClosestToStart(boolean completelyVisible, boolean acceptPartiallyVisible) {
      return this.mShouldReverseLayout ? this.findOneVisibleChild(this.getChildCount() - 1, -1, completelyVisible, acceptPartiallyVisible) : this.findOneVisibleChild(0, this.getChildCount(), completelyVisible, acceptPartiallyVisible);
   }

   private View findFirstVisibleChildClosestToEnd(boolean completelyVisible, boolean acceptPartiallyVisible) {
      return this.mShouldReverseLayout ? this.findOneVisibleChild(0, this.getChildCount(), completelyVisible, acceptPartiallyVisible) : this.findOneVisibleChild(this.getChildCount() - 1, -1, completelyVisible, acceptPartiallyVisible);
   }

   private View findReferenceChildClosestToEnd(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mShouldReverseLayout ? this.findFirstReferenceChild(recycler, state) : this.findLastReferenceChild(recycler, state);
   }

   private View findReferenceChildClosestToStart(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mShouldReverseLayout ? this.findLastReferenceChild(recycler, state) : this.findFirstReferenceChild(recycler, state);
   }

   private View findFirstReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.findReferenceChild(recycler, state, 0, this.getChildCount(), state.getItemCount());
   }

   private View findLastReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.findReferenceChild(recycler, state, this.getChildCount() - 1, -1, state.getItemCount());
   }

   View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int start, int end, int itemCount) {
      this.ensureLayoutState();
      View invalidMatch = null;
      View outOfBoundsMatch = null;
      int boundsStart = this.mOrientationHelper.getStartAfterPadding();
      int boundsEnd = this.mOrientationHelper.getEndAfterPadding();
      int diff = end > start ? 1 : -1;

      for(int i = start; i != end; i += diff) {
         View view = this.getChildAt(i);
         int position = this.getPosition(view);
         if (position >= 0 && position < itemCount) {
            if (((RecyclerView.LayoutParams)view.getLayoutParams()).isItemRemoved()) {
               if (invalidMatch == null) {
                  invalidMatch = view;
               }
            } else {
               if (this.mOrientationHelper.getDecoratedStart(view) < boundsEnd && this.mOrientationHelper.getDecoratedEnd(view) >= boundsStart) {
                  return view;
               }

               if (outOfBoundsMatch == null) {
                  outOfBoundsMatch = view;
               }
            }
         }
      }

      return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
   }

   private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mShouldReverseLayout ? this.findFirstPartiallyOrCompletelyInvisibleChild(recycler, state) : this.findLastPartiallyOrCompletelyInvisibleChild(recycler, state);
   }

   private View findPartiallyOrCompletelyInvisibleChildClosestToStart(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.mShouldReverseLayout ? this.findLastPartiallyOrCompletelyInvisibleChild(recycler, state) : this.findFirstPartiallyOrCompletelyInvisibleChild(recycler, state);
   }

   private View findFirstPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.findOnePartiallyOrCompletelyInvisibleChild(0, this.getChildCount());
   }

   private View findLastPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
      return this.findOnePartiallyOrCompletelyInvisibleChild(this.getChildCount() - 1, -1);
   }

   public int findFirstVisibleItemPosition() {
      View child = this.findOneVisibleChild(0, this.getChildCount(), false, true);
      return child == null ? -1 : this.getPosition(child);
   }

   public int findFirstCompletelyVisibleItemPosition() {
      View child = this.findOneVisibleChild(0, this.getChildCount(), true, false);
      return child == null ? -1 : this.getPosition(child);
   }

   public int findLastVisibleItemPosition() {
      View child = this.findOneVisibleChild(this.getChildCount() - 1, -1, false, true);
      return child == null ? -1 : this.getPosition(child);
   }

   public int findLastCompletelyVisibleItemPosition() {
      View child = this.findOneVisibleChild(this.getChildCount() - 1, -1, true, false);
      return child == null ? -1 : this.getPosition(child);
   }

   View findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible, boolean acceptPartiallyVisible) {
      this.ensureLayoutState();
      int preferredBoundsFlag = false;
      int acceptableBoundsFlag = 0;
      short preferredBoundsFlag;
      if (completelyVisible) {
         preferredBoundsFlag = 24579;
      } else {
         preferredBoundsFlag = 320;
      }

      if (acceptPartiallyVisible) {
         acceptableBoundsFlag = 320;
      }

      return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
   }

   View findOnePartiallyOrCompletelyInvisibleChild(int fromIndex, int toIndex) {
      this.ensureLayoutState();
      int next = toIndex > fromIndex ? 1 : (toIndex < fromIndex ? -1 : 0);
      if (next == 0) {
         return this.getChildAt(fromIndex);
      } else {
         int preferredBoundsFlag = false;
         int acceptableBoundsFlag = false;
         short preferredBoundsFlag;
         short acceptableBoundsFlag;
         if (this.mOrientationHelper.getDecoratedStart(this.getChildAt(fromIndex)) < this.mOrientationHelper.getStartAfterPadding()) {
            preferredBoundsFlag = 16644;
            acceptableBoundsFlag = 16388;
         } else {
            preferredBoundsFlag = 4161;
            acceptableBoundsFlag = 4097;
         }

         return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
      }
   }

   public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler, RecyclerView.State state) {
      this.resolveShouldLayoutReverse();
      if (this.getChildCount() == 0) {
         return null;
      } else {
         int layoutDir = this.convertFocusDirectionToLayoutDirection(focusDirection);
         if (layoutDir == Integer.MIN_VALUE) {
            return null;
         } else {
            this.ensureLayoutState();
            this.ensureLayoutState();
            int maxScroll = (int)(0.33333334F * (float)this.mOrientationHelper.getTotalSpace());
            this.updateLayoutState(layoutDir, maxScroll, false, state);
            this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
            this.mLayoutState.mRecycle = false;
            this.fill(recycler, this.mLayoutState, state, true);
            View nextCandidate;
            if (layoutDir == -1) {
               nextCandidate = this.findPartiallyOrCompletelyInvisibleChildClosestToStart(recycler, state);
            } else {
               nextCandidate = this.findPartiallyOrCompletelyInvisibleChildClosestToEnd(recycler, state);
            }

            View nextFocus;
            if (layoutDir == -1) {
               nextFocus = this.getChildClosestToStart();
            } else {
               nextFocus = this.getChildClosestToEnd();
            }

            if (nextFocus.hasFocusable()) {
               return nextCandidate == null ? null : nextFocus;
            } else {
               return nextCandidate;
            }
         }
      }
   }

   private void logChildren() {
      Log.d("LinearLayoutManager", "internal representation of views on the screen");

      for(int i = 0; i < this.getChildCount(); ++i) {
         View child = this.getChildAt(i);
         Log.d("LinearLayoutManager", "item " + this.getPosition(child) + ", coord:" + this.mOrientationHelper.getDecoratedStart(child));
      }

      Log.d("LinearLayoutManager", "==============");
   }

   void validateChildOrder() {
      Log.d("LinearLayoutManager", "validating child count " + this.getChildCount());
      if (this.getChildCount() >= 1) {
         int lastPos = this.getPosition(this.getChildAt(0));
         int lastScreenLoc = this.mOrientationHelper.getDecoratedStart(this.getChildAt(0));
         int i;
         View child;
         int pos;
         int screenLoc;
         if (this.mShouldReverseLayout) {
            for(i = 1; i < this.getChildCount(); ++i) {
               child = this.getChildAt(i);
               pos = this.getPosition(child);
               screenLoc = this.mOrientationHelper.getDecoratedStart(child);
               if (pos < lastPos) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid position. loc invalid? " + (screenLoc < lastScreenLoc));
               }

               if (screenLoc > lastScreenLoc) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid location");
               }
            }
         } else {
            for(i = 1; i < this.getChildCount(); ++i) {
               child = this.getChildAt(i);
               pos = this.getPosition(child);
               screenLoc = this.mOrientationHelper.getDecoratedStart(child);
               if (pos < lastPos) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid position. loc invalid? " + (screenLoc < lastScreenLoc));
               }

               if (screenLoc < lastScreenLoc) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid location");
               }
            }
         }

      }
   }

   public boolean supportsPredictiveItemAnimations() {
      return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void prepareForDrop(View view, View target, int x, int y) {
      this.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
      this.ensureLayoutState();
      this.resolveShouldLayoutReverse();
      int myPos = this.getPosition(view);
      int targetPos = this.getPosition(target);
      int dropDirection = myPos < targetPos ? 1 : -1;
      if (this.mShouldReverseLayout) {
         if (dropDirection == 1) {
            this.scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(target) + this.mOrientationHelper.getDecoratedMeasurement(view)));
         } else {
            this.scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(target));
         }
      } else if (dropDirection == -1) {
         this.scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getDecoratedStart(target));
      } else {
         this.scrollToPositionWithOffset(targetPos, this.mOrientationHelper.getDecoratedEnd(target) - this.mOrientationHelper.getDecoratedMeasurement(view));
      }

   }

   protected static class LayoutChunkResult {
      public int mConsumed;
      public boolean mFinished;
      public boolean mIgnoreConsumed;
      public boolean mFocusable;

      void resetInternal() {
         this.mConsumed = 0;
         this.mFinished = false;
         this.mIgnoreConsumed = false;
         this.mFocusable = false;
      }
   }

   class AnchorInfo {
      int mPosition;
      int mCoordinate;
      boolean mLayoutFromEnd;
      boolean mValid;

      AnchorInfo() {
         this.reset();
      }

      void reset() {
         this.mPosition = -1;
         this.mCoordinate = Integer.MIN_VALUE;
         this.mLayoutFromEnd = false;
         this.mValid = false;
      }

      void assignCoordinateFromPadding() {
         this.mCoordinate = this.mLayoutFromEnd ? LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() : LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
      }

      public String toString() {
         return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
      }

      boolean isViewValidAsAnchor(View child, RecyclerView.State state) {
         RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
         return !lp.isItemRemoved() && lp.getViewLayoutPosition() >= 0 && lp.getViewLayoutPosition() < state.getItemCount();
      }

      public void assignFromViewAndKeepVisibleRect(View child) {
         int spaceChange = LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
         if (spaceChange >= 0) {
            this.assignFromView(child);
         } else {
            this.mPosition = LinearLayoutManager.this.getPosition(child);
            int childStart;
            int startMarginx;
            int previousEndMargin;
            int childSize;
            int estimatedChildStart;
            int layoutStart;
            int endMargin;
            if (this.mLayoutFromEnd) {
               childStart = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - spaceChange;
               startMarginx = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(child);
               previousEndMargin = childStart - startMarginx;
               this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - previousEndMargin;
               if (previousEndMargin > 0) {
                  childSize = LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(child);
                  estimatedChildStart = this.mCoordinate - childSize;
                  layoutStart = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                  endMargin = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(child) - layoutStart;
                  int startReference = layoutStart + Math.min(endMargin, 0);
                  int startMargin = estimatedChildStart - startReference;
                  if (startMargin < 0) {
                     this.mCoordinate += Math.min(previousEndMargin, -startMargin);
                  }
               }
            } else {
               childStart = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(child);
               startMarginx = childStart - LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
               this.mCoordinate = childStart;
               if (startMarginx > 0) {
                  previousEndMargin = childStart + LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(child);
                  childSize = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - spaceChange;
                  estimatedChildStart = childSize - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(child);
                  layoutStart = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - Math.min(0, estimatedChildStart);
                  endMargin = layoutStart - previousEndMargin;
                  if (endMargin < 0) {
                     this.mCoordinate -= Math.min(startMarginx, -endMargin);
                  }
               }
            }

         }
      }

      public void assignFromView(View child) {
         if (this.mLayoutFromEnd) {
            this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(child) + LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
         } else {
            this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(child);
         }

         this.mPosition = LinearLayoutManager.this.getPosition(child);
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class SavedState implements Parcelable {
      int mAnchorPosition;
      int mAnchorOffset;
      boolean mAnchorLayoutFromEnd;
      public static final Creator CREATOR = new Creator() {
         public LinearLayoutManager.SavedState createFromParcel(Parcel in) {
            return new LinearLayoutManager.SavedState(in);
         }

         public LinearLayoutManager.SavedState[] newArray(int size) {
            return new LinearLayoutManager.SavedState[size];
         }
      };

      public SavedState() {
      }

      SavedState(Parcel in) {
         this.mAnchorPosition = in.readInt();
         this.mAnchorOffset = in.readInt();
         this.mAnchorLayoutFromEnd = in.readInt() == 1;
      }

      public SavedState(LinearLayoutManager.SavedState other) {
         this.mAnchorPosition = other.mAnchorPosition;
         this.mAnchorOffset = other.mAnchorOffset;
         this.mAnchorLayoutFromEnd = other.mAnchorLayoutFromEnd;
      }

      boolean hasValidAnchor() {
         return this.mAnchorPosition >= 0;
      }

      void invalidateAnchor() {
         this.mAnchorPosition = -1;
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(this.mAnchorPosition);
         dest.writeInt(this.mAnchorOffset);
         dest.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
      }
   }

   static class LayoutState {
      static final String TAG = "LLM#LayoutState";
      static final int LAYOUT_START = -1;
      static final int LAYOUT_END = 1;
      static final int INVALID_LAYOUT = Integer.MIN_VALUE;
      static final int ITEM_DIRECTION_HEAD = -1;
      static final int ITEM_DIRECTION_TAIL = 1;
      static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
      boolean mRecycle = true;
      int mOffset;
      int mAvailable;
      int mCurrentPosition;
      int mItemDirection;
      int mLayoutDirection;
      int mScrollingOffset;
      int mExtra = 0;
      boolean mIsPreLayout = false;
      int mLastScrollDelta;
      List mScrapList = null;
      boolean mInfinite;

      boolean hasMore(RecyclerView.State state) {
         return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
      }

      View next(RecyclerView.Recycler recycler) {
         if (this.mScrapList != null) {
            return this.nextViewFromScrapList();
         } else {
            View view = recycler.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return view;
         }
      }

      private View nextViewFromScrapList() {
         int size = this.mScrapList.size();

         for(int i = 0; i < size; ++i) {
            View view = ((RecyclerView.ViewHolder)this.mScrapList.get(i)).itemView;
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)view.getLayoutParams();
            if (!lp.isItemRemoved() && this.mCurrentPosition == lp.getViewLayoutPosition()) {
               this.assignPositionFromScrapList(view);
               return view;
            }
         }

         return null;
      }

      public void assignPositionFromScrapList() {
         this.assignPositionFromScrapList((View)null);
      }

      public void assignPositionFromScrapList(View ignore) {
         View closest = this.nextViewInLimitedList(ignore);
         if (closest == null) {
            this.mCurrentPosition = -1;
         } else {
            this.mCurrentPosition = ((RecyclerView.LayoutParams)closest.getLayoutParams()).getViewLayoutPosition();
         }

      }

      public View nextViewInLimitedList(View ignore) {
         int size = this.mScrapList.size();
         View closest = null;
         int closestDistance = Integer.MAX_VALUE;

         for(int i = 0; i < size; ++i) {
            View view = ((RecyclerView.ViewHolder)this.mScrapList.get(i)).itemView;
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)view.getLayoutParams();
            if (view != ignore && !lp.isItemRemoved()) {
               int distance = (lp.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
               if (distance >= 0 && distance < closestDistance) {
                  closest = view;
                  closestDistance = distance;
                  if (distance == 0) {
                     break;
                  }
               }
            }
         }

         return closest;
      }

      void log() {
         Log.d("LLM#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
      }
   }
}
