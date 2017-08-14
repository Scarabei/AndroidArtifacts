package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
   private static final boolean DEBUG = false;
   private static final String TAG = "SimpleItemAnimator";
   boolean mSupportsChangeAnimations = true;

   public boolean getSupportsChangeAnimations() {
      return this.mSupportsChangeAnimations;
   }

   public void setSupportsChangeAnimations(boolean supportsChangeAnimations) {
      this.mSupportsChangeAnimations = supportsChangeAnimations;
   }

   public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
      return !this.mSupportsChangeAnimations || viewHolder.isInvalid();
   }

   public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo) {
      int oldLeft = preLayoutInfo.left;
      int oldTop = preLayoutInfo.top;
      View disappearingItemView = viewHolder.itemView;
      int newLeft = postLayoutInfo == null ? disappearingItemView.getLeft() : postLayoutInfo.left;
      int newTop = postLayoutInfo == null ? disappearingItemView.getTop() : postLayoutInfo.top;
      if (viewHolder.isRemoved() || oldLeft == newLeft && oldTop == newTop) {
         return this.animateRemove(viewHolder);
      } else {
         disappearingItemView.layout(newLeft, newTop, newLeft + disappearingItemView.getWidth(), newTop + disappearingItemView.getHeight());
         return this.animateMove(viewHolder, oldLeft, oldTop, newLeft, newTop);
      }
   }

   public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo) {
      return preLayoutInfo == null || preLayoutInfo.left == postLayoutInfo.left && preLayoutInfo.top == postLayoutInfo.top ? this.animateAdd(viewHolder) : this.animateMove(viewHolder, preLayoutInfo.left, preLayoutInfo.top, postLayoutInfo.left, postLayoutInfo.top);
   }

   public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postInfo) {
      if (preInfo.left == postInfo.left && preInfo.top == postInfo.top) {
         this.dispatchMoveFinished(viewHolder);
         return false;
      } else {
         return this.animateMove(viewHolder, preInfo.left, preInfo.top, postInfo.left, postInfo.top);
      }
   }

   public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postInfo) {
      int fromLeft = preInfo.left;
      int fromTop = preInfo.top;
      int toLeft;
      int toTop;
      if (newHolder.shouldIgnore()) {
         toLeft = preInfo.left;
         toTop = preInfo.top;
      } else {
         toLeft = postInfo.left;
         toTop = postInfo.top;
      }

      return this.animateChange(oldHolder, newHolder, fromLeft, fromTop, toLeft, toTop);
   }

   public abstract boolean animateRemove(RecyclerView.ViewHolder var1);

   public abstract boolean animateAdd(RecyclerView.ViewHolder var1);

   public abstract boolean animateMove(RecyclerView.ViewHolder var1, int var2, int var3, int var4, int var5);

   public abstract boolean animateChange(RecyclerView.ViewHolder var1, RecyclerView.ViewHolder var2, int var3, int var4, int var5, int var6);

   public final void dispatchRemoveFinished(RecyclerView.ViewHolder item) {
      this.onRemoveFinished(item);
      this.dispatchAnimationFinished(item);
   }

   public final void dispatchMoveFinished(RecyclerView.ViewHolder item) {
      this.onMoveFinished(item);
      this.dispatchAnimationFinished(item);
   }

   public final void dispatchAddFinished(RecyclerView.ViewHolder item) {
      this.onAddFinished(item);
      this.dispatchAnimationFinished(item);
   }

   public final void dispatchChangeFinished(RecyclerView.ViewHolder item, boolean oldItem) {
      this.onChangeFinished(item, oldItem);
      this.dispatchAnimationFinished(item);
   }

   public final void dispatchRemoveStarting(RecyclerView.ViewHolder item) {
      this.onRemoveStarting(item);
   }

   public final void dispatchMoveStarting(RecyclerView.ViewHolder item) {
      this.onMoveStarting(item);
   }

   public final void dispatchAddStarting(RecyclerView.ViewHolder item) {
      this.onAddStarting(item);
   }

   public final void dispatchChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
      this.onChangeStarting(item, oldItem);
   }

   public void onRemoveStarting(RecyclerView.ViewHolder item) {
   }

   public void onRemoveFinished(RecyclerView.ViewHolder item) {
   }

   public void onAddStarting(RecyclerView.ViewHolder item) {
   }

   public void onAddFinished(RecyclerView.ViewHolder item) {
   }

   public void onMoveStarting(RecyclerView.ViewHolder item) {
   }

   public void onMoveFinished(RecyclerView.ViewHolder item) {
   }

   public void onChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
   }

   public void onChangeFinished(RecyclerView.ViewHolder item, boolean oldItem) {
   }
}
