package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
   private final ListView mTarget;

   public ListViewAutoScrollHelper(ListView target) {
      super(target);
      this.mTarget = target;
   }

   public void scrollTargetBy(int deltaX, int deltaY) {
      ListViewCompat.scrollListBy(this.mTarget, deltaY);
   }

   public boolean canTargetScrollHorizontally(int direction) {
      return false;
   }

   public boolean canTargetScrollVertically(int direction) {
      ListView target = this.mTarget;
      int itemCount = target.getCount();
      if (itemCount == 0) {
         return false;
      } else {
         int childCount = target.getChildCount();
         int firstPosition = target.getFirstVisiblePosition();
         int lastPosition = firstPosition + childCount;
         View firstView;
         if (direction > 0) {
            if (lastPosition >= itemCount) {
               firstView = target.getChildAt(childCount - 1);
               if (firstView.getBottom() <= target.getHeight()) {
                  return false;
               }
            }
         } else {
            if (direction >= 0) {
               return false;
            }

            if (firstPosition <= 0) {
               firstView = target.getChildAt(0);
               if (firstView.getTop() >= 0) {
                  return false;
               }
            }
         }

         return true;
      }
   }
}
