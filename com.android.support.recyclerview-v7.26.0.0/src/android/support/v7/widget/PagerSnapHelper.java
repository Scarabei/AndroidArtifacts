package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

public class PagerSnapHelper extends SnapHelper {
   private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
   @Nullable
   private OrientationHelper mVerticalHelper;
   @Nullable
   private OrientationHelper mHorizontalHelper;

   @Nullable
   public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
      int[] out = new int[2];
      if (layoutManager.canScrollHorizontally()) {
         out[0] = this.distanceToCenter(layoutManager, targetView, this.getHorizontalHelper(layoutManager));
      } else {
         out[0] = 0;
      }

      if (layoutManager.canScrollVertically()) {
         out[1] = this.distanceToCenter(layoutManager, targetView, this.getVerticalHelper(layoutManager));
      } else {
         out[1] = 0;
      }

      return out;
   }

   @Nullable
   public View findSnapView(RecyclerView.LayoutManager layoutManager) {
      if (layoutManager.canScrollVertically()) {
         return this.findCenterView(layoutManager, this.getVerticalHelper(layoutManager));
      } else {
         return layoutManager.canScrollHorizontally() ? this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager)) : null;
      }
   }

   public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
      int itemCount = layoutManager.getItemCount();
      if (itemCount == 0) {
         return -1;
      } else {
         View mStartMostChildView = null;
         if (layoutManager.canScrollVertically()) {
            mStartMostChildView = this.findStartView(layoutManager, this.getVerticalHelper(layoutManager));
         } else if (layoutManager.canScrollHorizontally()) {
            mStartMostChildView = this.findStartView(layoutManager, this.getHorizontalHelper(layoutManager));
         }

         if (mStartMostChildView == null) {
            return -1;
         } else {
            int centerPosition = layoutManager.getPosition(mStartMostChildView);
            if (centerPosition == -1) {
               return -1;
            } else {
               boolean forwardDirection;
               if (layoutManager.canScrollHorizontally()) {
                  forwardDirection = velocityX > 0;
               } else {
                  forwardDirection = velocityY > 0;
               }

               boolean reverseLayout = false;
               if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
                  RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider = (RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager;
                  PointF vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1);
                  if (vectorForEnd != null) {
                     reverseLayout = vectorForEnd.x < 0.0F || vectorForEnd.y < 0.0F;
                  }
               }

               return reverseLayout ? (forwardDirection ? centerPosition - 1 : centerPosition) : (forwardDirection ? centerPosition + 1 : centerPosition);
            }
         }
      }
   }

   protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
      return !(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) ? null : new LinearSmoothScroller(this.mRecyclerView.getContext()) {
         protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int[] snapDistances = PagerSnapHelper.this.calculateDistanceToFinalSnap(PagerSnapHelper.this.mRecyclerView.getLayoutManager(), targetView);
            int dx = snapDistances[0];
            int dy = snapDistances[1];
            int time = this.calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
            if (time > 0) {
               action.update(dx, dy, time, this.mDecelerateInterpolator);
            }

         }

         protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0F / (float)displayMetrics.densityDpi;
         }

         protected int calculateTimeForScrolling(int dx) {
            return Math.min(100, super.calculateTimeForScrolling(dx));
         }
      };
   }

   private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView, OrientationHelper helper) {
      int childCenter = helper.getDecoratedStart(targetView) + helper.getDecoratedMeasurement(targetView) / 2;
      int containerCenter;
      if (layoutManager.getClipToPadding()) {
         containerCenter = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
      } else {
         containerCenter = helper.getEnd() / 2;
      }

      return childCenter - containerCenter;
   }

   @Nullable
   private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
      int childCount = layoutManager.getChildCount();
      if (childCount == 0) {
         return null;
      } else {
         View closestChild = null;
         int center;
         if (layoutManager.getClipToPadding()) {
            center = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
         } else {
            center = helper.getEnd() / 2;
         }

         int absClosest = Integer.MAX_VALUE;

         for(int i = 0; i < childCount; ++i) {
            View child = layoutManager.getChildAt(i);
            int childCenter = helper.getDecoratedStart(child) + helper.getDecoratedMeasurement(child) / 2;
            int absDistance = Math.abs(childCenter - center);
            if (absDistance < absClosest) {
               absClosest = absDistance;
               closestChild = child;
            }
         }

         return closestChild;
      }
   }

   @Nullable
   private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
      int childCount = layoutManager.getChildCount();
      if (childCount == 0) {
         return null;
      } else {
         View closestChild = null;
         int startest = Integer.MAX_VALUE;

         for(int i = 0; i < childCount; ++i) {
            View child = layoutManager.getChildAt(i);
            int childStart = helper.getDecoratedStart(child);
            if (childStart < startest) {
               startest = childStart;
               closestChild = child;
            }
         }

         return closestChild;
      }
   }

   @NonNull
   private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
      if (this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != layoutManager) {
         this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
      }

      return this.mVerticalHelper;
   }

   @NonNull
   private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
      if (this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != layoutManager) {
         this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
      }

      return this.mHorizontalHelper;
   }
}
