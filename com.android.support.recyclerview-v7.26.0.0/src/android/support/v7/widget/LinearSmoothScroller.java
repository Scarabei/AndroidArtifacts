package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
   private static final String TAG = "LinearSmoothScroller";
   private static final boolean DEBUG = false;
   private static final float MILLISECONDS_PER_INCH = 25.0F;
   private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
   public static final int SNAP_TO_START = -1;
   public static final int SNAP_TO_END = 1;
   public static final int SNAP_TO_ANY = 0;
   private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2F;
   protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
   protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
   protected PointF mTargetVector;
   private final float MILLISECONDS_PER_PX;
   protected int mInterimTargetDx = 0;
   protected int mInterimTargetDy = 0;

   public LinearSmoothScroller(Context context) {
      this.MILLISECONDS_PER_PX = this.calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
   }

   protected void onStart() {
   }

   protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
      int dx = this.calculateDxToMakeVisible(targetView, this.getHorizontalSnapPreference());
      int dy = this.calculateDyToMakeVisible(targetView, this.getVerticalSnapPreference());
      int distance = (int)Math.sqrt((double)(dx * dx + dy * dy));
      int time = this.calculateTimeForDeceleration(distance);
      if (time > 0) {
         action.update(-dx, -dy, time, this.mDecelerateInterpolator);
      }

   }

   protected void onSeekTargetStep(int dx, int dy, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
      if (this.getChildCount() == 0) {
         this.stop();
      } else {
         this.mInterimTargetDx = this.clampApplyScroll(this.mInterimTargetDx, dx);
         this.mInterimTargetDy = this.clampApplyScroll(this.mInterimTargetDy, dy);
         if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
            this.updateActionForInterimTarget(action);
         }

      }
   }

   protected void onStop() {
      this.mInterimTargetDx = this.mInterimTargetDy = 0;
      this.mTargetVector = null;
   }

   protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
      return 25.0F / (float)displayMetrics.densityDpi;
   }

   protected int calculateTimeForDeceleration(int dx) {
      return (int)Math.ceil((double)this.calculateTimeForScrolling(dx) / 0.3356D);
   }

   protected int calculateTimeForScrolling(int dx) {
      return (int)Math.ceil((double)((float)Math.abs(dx) * this.MILLISECONDS_PER_PX));
   }

   protected int getHorizontalSnapPreference() {
      return this.mTargetVector != null && this.mTargetVector.x != 0.0F ? (this.mTargetVector.x > 0.0F ? 1 : -1) : 0;
   }

   protected int getVerticalSnapPreference() {
      return this.mTargetVector != null && this.mTargetVector.y != 0.0F ? (this.mTargetVector.y > 0.0F ? 1 : -1) : 0;
   }

   protected void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
      PointF scrollVector = this.computeScrollVectorForPosition(this.getTargetPosition());
      int target;
      if (scrollVector != null && (scrollVector.x != 0.0F || scrollVector.y != 0.0F)) {
         this.normalize(scrollVector);
         this.mTargetVector = scrollVector;
         this.mInterimTargetDx = (int)(10000.0F * scrollVector.x);
         this.mInterimTargetDy = (int)(10000.0F * scrollVector.y);
         target = this.calculateTimeForScrolling(10000);
         action.update((int)((float)this.mInterimTargetDx * 1.2F), (int)((float)this.mInterimTargetDy * 1.2F), (int)((float)target * 1.2F), this.mLinearInterpolator);
      } else {
         target = this.getTargetPosition();
         action.jumpTo(target);
         this.stop();
      }
   }

   private int clampApplyScroll(int tmpDt, int dt) {
      int before = tmpDt;
      tmpDt -= dt;
      return before * tmpDt <= 0 ? 0 : tmpDt;
   }

   public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
      switch(snapPreference) {
      case -1:
         return boxStart - viewStart;
      case 0:
         int dtStart = boxStart - viewStart;
         if (dtStart > 0) {
            return dtStart;
         } else {
            int dtEnd = boxEnd - viewEnd;
            if (dtEnd < 0) {
               return dtEnd;
            }

            return 0;
         }
      case 1:
         return boxEnd - viewEnd;
      default:
         throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
      }
   }

   public int calculateDyToMakeVisible(View view, int snapPreference) {
      RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
      if (layoutManager != null && layoutManager.canScrollVertically()) {
         RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
         int top = layoutManager.getDecoratedTop(view) - params.topMargin;
         int bottom = layoutManager.getDecoratedBottom(view) + params.bottomMargin;
         int start = layoutManager.getPaddingTop();
         int end = layoutManager.getHeight() - layoutManager.getPaddingBottom();
         return this.calculateDtToFit(top, bottom, start, end, snapPreference);
      } else {
         return 0;
      }
   }

   public int calculateDxToMakeVisible(View view, int snapPreference) {
      RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
      if (layoutManager != null && layoutManager.canScrollHorizontally()) {
         RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
         int left = layoutManager.getDecoratedLeft(view) - params.leftMargin;
         int right = layoutManager.getDecoratedRight(view) + params.rightMargin;
         int start = layoutManager.getPaddingLeft();
         int end = layoutManager.getWidth() - layoutManager.getPaddingRight();
         return this.calculateDtToFit(left, right, start, end, snapPreference);
      } else {
         return 0;
      }
   }

   @Nullable
   public PointF computeScrollVectorForPosition(int targetPosition) {
      RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
      if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
         return ((RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(targetPosition);
      } else {
         Log.w("LinearSmoothScroller", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + RecyclerView.SmoothScroller.ScrollVectorProvider.class.getCanonicalName());
         return null;
      }
   }
}
