package android.support.v7.widget;

import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DrawableUtils {
   private static final String TAG = "DrawableUtils";
   public static final Rect INSETS_NONE = new Rect();
   private static Class sInsetsClazz;
   private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";

   public static Rect getOpticalBounds(Drawable drawable) {
      if (sInsetsClazz != null) {
         try {
            drawable = DrawableCompat.unwrap(drawable);
            Method getOpticalInsetsMethod = drawable.getClass().getMethod("getOpticalInsets");
            Object insets = getOpticalInsetsMethod.invoke(drawable);
            if (insets != null) {
               Rect result = new Rect();
               Field[] var4 = sInsetsClazz.getFields();
               int var5 = var4.length;

               for(int var6 = 0; var6 < var5; ++var6) {
                  Field field = var4[var6];
                  String var8 = field.getName();
                  byte var9 = -1;
                  switch(var8.hashCode()) {
                  case -1383228885:
                     if (var8.equals("bottom")) {
                        var9 = 3;
                     }
                     break;
                  case 115029:
                     if (var8.equals("top")) {
                        var9 = 1;
                     }
                     break;
                  case 3317767:
                     if (var8.equals("left")) {
                        var9 = 0;
                     }
                     break;
                  case 108511772:
                     if (var8.equals("right")) {
                        var9 = 2;
                     }
                  }

                  switch(var9) {
                  case 0:
                     result.left = field.getInt(insets);
                     break;
                  case 1:
                     result.top = field.getInt(insets);
                     break;
                  case 2:
                     result.right = field.getInt(insets);
                     break;
                  case 3:
                     result.bottom = field.getInt(insets);
                  }
               }

               return result;
            }
         } catch (Exception var10) {
            Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
         }
      }

      return INSETS_NONE;
   }

   static void fixDrawable(@NonNull Drawable drawable) {
      if (VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
         fixVectorDrawableTinting(drawable);
      }

   }

   public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
      if (VERSION.SDK_INT < 15 && drawable instanceof InsetDrawable) {
         return false;
      } else if (VERSION.SDK_INT < 15 && drawable instanceof GradientDrawable) {
         return false;
      } else if (VERSION.SDK_INT < 17 && drawable instanceof LayerDrawable) {
         return false;
      } else {
         if (drawable instanceof DrawableContainer) {
            ConstantState state = drawable.getConstantState();
            if (state instanceof DrawableContainerState) {
               DrawableContainerState containerState = (DrawableContainerState)state;
               Drawable[] var3 = containerState.getChildren();
               int var4 = var3.length;

               for(int var5 = 0; var5 < var4; ++var5) {
                  Drawable child = var3[var5];
                  if (!canSafelyMutateDrawable(child)) {
                     return false;
                  }
               }
            }
         } else {
            if (drawable instanceof DrawableWrapper) {
               return canSafelyMutateDrawable(((DrawableWrapper)drawable).getWrappedDrawable());
            }

            if (drawable instanceof android.support.v7.graphics.drawable.DrawableWrapper) {
               return canSafelyMutateDrawable(((android.support.v7.graphics.drawable.DrawableWrapper)drawable).getWrappedDrawable());
            }

            if (drawable instanceof ScaleDrawable) {
               return canSafelyMutateDrawable(((ScaleDrawable)drawable).getDrawable());
            }
         }

         return true;
      }
   }

   private static void fixVectorDrawableTinting(Drawable drawable) {
      int[] originalState = drawable.getState();
      if (originalState != null && originalState.length != 0) {
         drawable.setState(ThemeUtils.EMPTY_STATE_SET);
      } else {
         drawable.setState(ThemeUtils.CHECKED_STATE_SET);
      }

      drawable.setState(originalState);
   }

   public static Mode parseTintMode(int value, Mode defaultMode) {
      switch(value) {
      case 3:
         return Mode.SRC_OVER;
      case 4:
      case 6:
      case 7:
      case 8:
      case 10:
      case 11:
      case 12:
      case 13:
      default:
         return defaultMode;
      case 5:
         return Mode.SRC_IN;
      case 9:
         return Mode.SRC_ATOP;
      case 14:
         return Mode.MULTIPLY;
      case 15:
         return Mode.SCREEN;
      case 16:
         return VERSION.SDK_INT >= 11 ? Mode.valueOf("ADD") : defaultMode;
      }
   }

   static {
      if (VERSION.SDK_INT >= 18) {
         try {
            sInsetsClazz = Class.forName("android.graphics.Insets");
         } catch (ClassNotFoundException var1) {
            ;
         }
      }

   }
}
