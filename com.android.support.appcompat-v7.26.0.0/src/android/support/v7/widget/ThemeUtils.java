package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

class ThemeUtils {
   private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();
   static final int[] DISABLED_STATE_SET = new int[]{-16842910};
   static final int[] FOCUSED_STATE_SET = new int[]{16842908};
   static final int[] ACTIVATED_STATE_SET = new int[]{16843518};
   static final int[] PRESSED_STATE_SET = new int[]{16842919};
   static final int[] CHECKED_STATE_SET = new int[]{16842912};
   static final int[] SELECTED_STATE_SET = new int[]{16842913};
   static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = new int[]{-16842919, -16842908};
   static final int[] EMPTY_STATE_SET = new int[0];
   private static final int[] TEMP_ARRAY = new int[1];

   public static ColorStateList createDisabledStateList(int textColor, int disabledTextColor) {
      int[][] states = new int[2][];
      int[] colors = new int[2];
      int i = 0;
      states[i] = DISABLED_STATE_SET;
      colors[i] = disabledTextColor;
      int i = i + 1;
      states[i] = EMPTY_STATE_SET;
      colors[i] = textColor;
      ++i;
      return new ColorStateList(states, colors);
   }

   public static int getThemeAttrColor(Context context, int attr) {
      TEMP_ARRAY[0] = attr;
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)null, TEMP_ARRAY);

      int var3;
      try {
         var3 = a.getColor(0, 0);
      } finally {
         a.recycle();
      }

      return var3;
   }

   public static ColorStateList getThemeAttrColorStateList(Context context, int attr) {
      TEMP_ARRAY[0] = attr;
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)null, TEMP_ARRAY);

      ColorStateList var3;
      try {
         var3 = a.getColorStateList(0);
      } finally {
         a.recycle();
      }

      return var3;
   }

   public static int getDisabledThemeAttrColor(Context context, int attr) {
      ColorStateList csl = getThemeAttrColorStateList(context, attr);
      if (csl != null && csl.isStateful()) {
         return csl.getColorForState(DISABLED_STATE_SET, csl.getDefaultColor());
      } else {
         TypedValue tv = getTypedValue();
         context.getTheme().resolveAttribute(16842803, tv, true);
         float disabledAlpha = tv.getFloat();
         return getThemeAttrColor(context, attr, disabledAlpha);
      }
   }

   private static TypedValue getTypedValue() {
      TypedValue typedValue = (TypedValue)TL_TYPED_VALUE.get();
      if (typedValue == null) {
         typedValue = new TypedValue();
         TL_TYPED_VALUE.set(typedValue);
      }

      return typedValue;
   }

   static int getThemeAttrColor(Context context, int attr, float alpha) {
      int color = getThemeAttrColor(context, attr);
      int originalAlpha = Color.alpha(color);
      return ColorUtils.setAlphaComponent(color, Math.round((float)originalAlpha * alpha));
   }
}
