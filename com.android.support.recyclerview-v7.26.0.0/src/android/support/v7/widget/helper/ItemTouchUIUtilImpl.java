package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R.id;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class ItemTouchUIUtilImpl {
   static class BaseImpl implements ItemTouchUIUtil {
      public void clearView(View view) {
         view.setTranslationX(0.0F);
         view.setTranslationY(0.0F);
      }

      public void onSelected(View view) {
      }

      public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
         view.setTranslationX(dX);
         view.setTranslationY(dY);
      }

      public void onDrawOver(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
      }
   }

   static class Api21Impl extends ItemTouchUIUtilImpl.BaseImpl {
      public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
         if (isCurrentlyActive) {
            Object originalElevation = view.getTag(id.item_touch_helper_previous_elevation);
            if (originalElevation == null) {
               Object originalElevation = ViewCompat.getElevation(view);
               float newElevation = 1.0F + this.findMaxElevation(recyclerView, view);
               ViewCompat.setElevation(view, newElevation);
               view.setTag(id.item_touch_helper_previous_elevation, originalElevation);
            }
         }

         super.onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
      }

      private float findMaxElevation(RecyclerView recyclerView, View itemView) {
         int childCount = recyclerView.getChildCount();
         float max = 0.0F;

         for(int i = 0; i < childCount; ++i) {
            View child = recyclerView.getChildAt(i);
            if (child != itemView) {
               float elevation = ViewCompat.getElevation(child);
               if (elevation > max) {
                  max = elevation;
               }
            }
         }

         return max;
      }

      public void clearView(View view) {
         Object tag = view.getTag(id.item_touch_helper_previous_elevation);
         if (tag != null && tag instanceof Float) {
            ViewCompat.setElevation(view, ((Float)tag).floatValue());
         }

         view.setTag(id.item_touch_helper_previous_elevation, (Object)null);
         super.clearView(view);
      }
   }
}
