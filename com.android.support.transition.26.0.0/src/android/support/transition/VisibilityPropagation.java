package android.support.transition;

import android.view.View;

public abstract class VisibilityPropagation extends TransitionPropagation {
   private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
   private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
   private static final String[] VISIBILITY_PROPAGATION_VALUES = new String[]{"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

   public void captureValues(TransitionValues values) {
      View view = values.view;
      Integer visibility = (Integer)values.values.get("android:visibility:visibility");
      if (visibility == null) {
         visibility = view.getVisibility();
      }

      values.values.put("android:visibilityPropagation:visibility", visibility);
      int[] loc = new int[2];
      view.getLocationOnScreen(loc);
      loc[0] += Math.round(view.getTranslationX());
      loc[0] += view.getWidth() / 2;
      loc[1] += Math.round(view.getTranslationY());
      loc[1] += view.getHeight() / 2;
      values.values.put("android:visibilityPropagation:center", loc);
   }

   public String[] getPropagationProperties() {
      return VISIBILITY_PROPAGATION_VALUES;
   }

   public int getViewVisibility(TransitionValues values) {
      if (values == null) {
         return 8;
      } else {
         Integer visibility = (Integer)values.values.get("android:visibilityPropagation:visibility");
         return visibility == null ? 8 : visibility.intValue();
      }
   }

   public int getViewX(TransitionValues values) {
      return getViewCoordinate(values, 0);
   }

   public int getViewY(TransitionValues values) {
      return getViewCoordinate(values, 1);
   }

   private static int getViewCoordinate(TransitionValues values, int coordinateIndex) {
      if (values == null) {
         return -1;
      } else {
         int[] coordinates = (int[])((int[])values.values.get("android:visibilityPropagation:center"));
         return coordinates == null ? -1 : coordinates[coordinateIndex];
      }
   }
}
