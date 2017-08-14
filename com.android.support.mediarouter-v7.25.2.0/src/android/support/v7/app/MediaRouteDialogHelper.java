package android.support.v7.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.mediarouter.R.dimen;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class MediaRouteDialogHelper {
   public static int getDialogWidth(Context context) {
      DisplayMetrics metrics = context.getResources().getDisplayMetrics();
      boolean isPortrait = metrics.widthPixels < metrics.heightPixels;
      TypedValue value = new TypedValue();
      context.getResources().getValue(isPortrait ? dimen.mr_dialog_fixed_width_minor : dimen.mr_dialog_fixed_width_major, value, true);
      if (value.type == 5) {
         return (int)value.getDimension(metrics);
      } else {
         return value.type == 6 ? (int)value.getFraction((float)metrics.widthPixels, (float)metrics.widthPixels) : -2;
      }
   }

   public static boolean listUnorderedEquals(List list1, List list2) {
      HashSet set1 = new HashSet(list1);
      HashSet set2 = new HashSet(list2);
      return set1.equals(set2);
   }

   public static Set getItemsAdded(List before, List after) {
      HashSet set = new HashSet(after);
      set.removeAll(before);
      return set;
   }

   public static Set getItemsRemoved(List before, List after) {
      HashSet set = new HashSet(before);
      set.removeAll(after);
      return set;
   }

   public static HashMap getItemBoundMap(ListView listView, ArrayAdapter adapter) {
      HashMap itemBoundMap = new HashMap();
      int firstVisiblePosition = listView.getFirstVisiblePosition();

      for(int i = 0; i < listView.getChildCount(); ++i) {
         int position = firstVisiblePosition + i;
         Object item = adapter.getItem(position);
         View view = listView.getChildAt(i);
         itemBoundMap.put(item, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
      }

      return itemBoundMap;
   }

   public static HashMap getItemBitmapMap(Context context, ListView listView, ArrayAdapter adapter) {
      HashMap itemBitmapMap = new HashMap();
      int firstVisiblePosition = listView.getFirstVisiblePosition();

      for(int i = 0; i < listView.getChildCount(); ++i) {
         int position = firstVisiblePosition + i;
         Object item = adapter.getItem(position);
         View view = listView.getChildAt(i);
         itemBitmapMap.put(item, getViewBitmap(context, view));
      }

      return itemBitmapMap;
   }

   private static BitmapDrawable getViewBitmap(Context context, View view) {
      Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      view.draw(canvas);
      return new BitmapDrawable(context.getResources(), bitmap);
   }
}
