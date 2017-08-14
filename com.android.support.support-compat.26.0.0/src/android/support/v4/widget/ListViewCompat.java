package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

public final class ListViewCompat {
   public static void scrollListBy(@NonNull ListView listView, int y) {
      if (VERSION.SDK_INT >= 19) {
         listView.scrollListBy(y);
      } else {
         int firstPosition = listView.getFirstVisiblePosition();
         if (firstPosition == -1) {
            return;
         }

         View firstView = listView.getChildAt(0);
         if (firstView == null) {
            return;
         }

         int newTop = firstView.getTop() - y;
         listView.setSelectionFromTop(firstPosition, newTop);
      }

   }

   public static boolean canScrollList(@NonNull ListView listView, int direction) {
      if (VERSION.SDK_INT >= 19) {
         return listView.canScrollList(direction);
      } else {
         int childCount = listView.getChildCount();
         if (childCount == 0) {
            return false;
         } else {
            int firstPosition = listView.getFirstVisiblePosition();
            int firstTop;
            if (direction > 0) {
               firstTop = listView.getChildAt(childCount - 1).getBottom();
               int lastPosition = firstPosition + childCount;
               return lastPosition < listView.getCount() || firstTop > listView.getHeight() - listView.getListPaddingBottom();
            } else {
               firstTop = listView.getChildAt(0).getTop();
               return firstPosition > 0 || firstTop < listView.getListPaddingTop();
            }
         }
      }
   }
}
