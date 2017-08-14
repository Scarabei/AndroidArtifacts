package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

/** @deprecated */
@Deprecated
public final class ViewConfigurationCompat {
   private static final String TAG = "ViewConfigCompat";
   private static Method sGetScaledScrollFactorMethod;

   /** @deprecated */
   @Deprecated
   public static int getScaledPagingTouchSlop(ViewConfiguration config) {
      return config.getScaledPagingTouchSlop();
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasPermanentMenuKey(ViewConfiguration config) {
      return config.hasPermanentMenuKey();
   }

   public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration config, @NonNull Context context) {
      return VERSION.SDK_INT >= 26 ? config.getScaledHorizontalScrollFactor() : getLegacyScrollFactor(config, context);
   }

   public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration config, @NonNull Context context) {
      return VERSION.SDK_INT >= 26 ? config.getScaledVerticalScrollFactor() : getLegacyScrollFactor(config, context);
   }

   private static float getLegacyScrollFactor(ViewConfiguration config, Context context) {
      if (VERSION.SDK_INT >= 25 && sGetScaledScrollFactorMethod != null) {
         try {
            return (float)((Integer)sGetScaledScrollFactorMethod.invoke(config)).intValue();
         } catch (Exception var3) {
            Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
         }
      }

      TypedValue outValue = new TypedValue();
      return context.getTheme().resolveAttribute(16842829, outValue, true) ? outValue.getDimension(context.getResources().getDisplayMetrics()) : 0.0F;
   }

   static {
      if (VERSION.SDK_INT == 25) {
         try {
            sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor");
         } catch (Exception var1) {
            Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
         }
      }

   }
}
