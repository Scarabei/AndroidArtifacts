package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.mediarouter.R.attr;
import android.support.v7.mediarouter.R.style;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;

final class MediaRouterThemeHelper {
   private static final float MIN_CONTRAST = 3.0F;
   static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
   static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;

   public static Context createThemedContext(Context context, int style) {
      int theme;
      if (isLightTheme(context)) {
         if (getControllerColor(context, style) == -570425344) {
            theme = style.Theme_MediaRouter_Light;
         } else {
            theme = style.Theme_MediaRouter_Light_DarkControlPanel;
         }
      } else if (getControllerColor(context, style) == -570425344) {
         theme = style.Theme_MediaRouter_LightControlPanel;
      } else {
         theme = style.Theme_MediaRouter;
      }

      int mediaRouteThemeResId = getThemeResource(context, attr.mediaRouteTheme);
      Context themedContext = new ContextThemeWrapper(context, theme);
      if (mediaRouteThemeResId != 0) {
         themedContext = new ContextThemeWrapper(themedContext, mediaRouteThemeResId);
      }

      return themedContext;
   }

   public static int getThemeResource(Context context, int attr) {
      TypedValue value = new TypedValue();
      return context.getTheme().resolveAttribute(attr, value, true) ? value.resourceId : 0;
   }

   public static float getDisabledAlpha(Context context) {
      TypedValue value = new TypedValue();
      return context.getTheme().resolveAttribute(16842803, value, true) ? value.getFloat() : 0.5F;
   }

   public static int getControllerColor(Context context, int style) {
      int primaryColor = getThemeColor(context, style, android.support.v7.appcompat.R.attr.colorPrimary);
      return ColorUtils.calculateContrast(-1, primaryColor) >= 3.0D ? -1 : -570425344;
   }

   public static int getButtonTextColor(Context context) {
      int primaryColor = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimary);
      int backgroundColor = getThemeColor(context, 0, 16842801);
      return ColorUtils.calculateContrast(primaryColor, backgroundColor) < 3.0D ? getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorAccent) : primaryColor;
   }

   public static void setMediaControlsBackgroundColor(Context context, View mainControls, View groupControls, boolean hasGroup) {
      int primaryColor = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimary);
      int primaryDarkColor = getThemeColor(context, 0, android.support.v7.appcompat.R.attr.colorPrimaryDark);
      if (hasGroup && getControllerColor(context, 0) == -570425344) {
         primaryDarkColor = primaryColor;
         primaryColor = -1;
      }

      mainControls.setBackgroundColor(primaryColor);
      groupControls.setBackgroundColor(primaryDarkColor);
      mainControls.setTag(primaryColor);
      groupControls.setTag(primaryDarkColor);
   }

   public static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider volumeSlider, View backgroundView) {
      int controllerColor = getControllerColor(context, 0);
      if (Color.alpha(controllerColor) != 255) {
         int backgroundColor = ((Integer)backgroundView.getTag()).intValue();
         controllerColor = ColorUtils.compositeColors(controllerColor, backgroundColor);
      }

      volumeSlider.setColor(controllerColor);
   }

   public static int getAlertDialogResolvedTheme(Context context, int themeResId) {
      if (themeResId >= 16777216) {
         return themeResId;
      } else {
         TypedValue outValue = new TypedValue();
         context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.alertDialogTheme, outValue, true);
         return outValue.resourceId;
      }
   }

   private static boolean isLightTheme(Context context) {
      TypedValue value = new TypedValue();
      return context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.isLightTheme, value, true) && value.data != 0;
   }

   private static int getThemeColor(Context context, int style, int attr) {
      if (style != 0) {
         int[] attrs = new int[]{attr};
         TypedArray ta = context.obtainStyledAttributes(style, attrs);
         int color = ta.getColor(0, 0);
         ta.recycle();
         if (color != 0) {
            return color;
         }
      }

      TypedValue value = new TypedValue();
      context.getTheme().resolveAttribute(attr, value, true);
      return value.resourceId != 0 ? context.getResources().getColor(value.resourceId) : value.data;
   }
}
