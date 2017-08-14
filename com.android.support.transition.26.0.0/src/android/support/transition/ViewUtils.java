package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class ViewUtils {
   private static final ViewUtilsImpl IMPL;
   private static final String TAG = "ViewUtils";
   private static Field sViewFlagsField;
   private static boolean sViewFlagsFieldFetched;
   private static final int VISIBILITY_MASK = 12;
   static final Property TRANSITION_ALPHA;
   static final Property CLIP_BOUNDS;

   static ViewOverlayImpl getOverlay(@NonNull View view) {
      return IMPL.getOverlay(view);
   }

   static WindowIdImpl getWindowId(@NonNull View view) {
      return IMPL.getWindowId(view);
   }

   static void setTransitionAlpha(@NonNull View view, float alpha) {
      IMPL.setTransitionAlpha(view, alpha);
   }

   static float getTransitionAlpha(@NonNull View view) {
      return IMPL.getTransitionAlpha(view);
   }

   static void saveNonTransitionAlpha(@NonNull View view) {
      IMPL.saveNonTransitionAlpha(view);
   }

   static void clearNonTransitionAlpha(@NonNull View view) {
      IMPL.clearNonTransitionAlpha(view);
   }

   static void setTransitionVisibility(@NonNull View view, int visibility) {
      fetchViewFlagsField();
      if (sViewFlagsField != null) {
         try {
            int viewFlags = sViewFlagsField.getInt(view);
            sViewFlagsField.setInt(view, viewFlags & -13 | visibility);
         } catch (IllegalAccessException var3) {
            ;
         }
      }

   }

   static void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
      IMPL.transformMatrixToGlobal(view, matrix);
   }

   static void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
      IMPL.transformMatrixToLocal(view, matrix);
   }

   static void setAnimationMatrix(@NonNull View v, @Nullable Matrix m) {
      IMPL.setAnimationMatrix(v, m);
   }

   static void setLeftTopRightBottom(@NonNull View v, int left, int top, int right, int bottom) {
      IMPL.setLeftTopRightBottom(v, left, top, right, bottom);
   }

   private static void fetchViewFlagsField() {
      if (!sViewFlagsFieldFetched) {
         try {
            sViewFlagsField = View.class.getDeclaredField("mViewFlags");
            sViewFlagsField.setAccessible(true);
         } catch (NoSuchFieldException var1) {
            Log.i("ViewUtils", "fetchViewFlagsField: ");
         }

         sViewFlagsFieldFetched = true;
      }

   }

   static {
      if (VERSION.SDK_INT >= 22) {
         IMPL = new ViewUtilsApi22();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new ViewUtilsApi21();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new ViewUtilsApi19();
      } else if (VERSION.SDK_INT >= 18) {
         IMPL = new ViewUtilsApi18();
      } else {
         IMPL = new ViewUtilsApi14();
      }

      TRANSITION_ALPHA = new Property(Float.class, "translationAlpha") {
         public Float get(View view) {
            return ViewUtils.getTransitionAlpha(view);
         }

         public void set(View view, Float alpha) {
            ViewUtils.setTransitionAlpha(view, alpha.floatValue());
         }
      };
      CLIP_BOUNDS = new Property(Rect.class, "clipBounds") {
         public Rect get(View view) {
            return ViewCompat.getClipBounds(view);
         }

         public void set(View view, Rect clipBounds) {
            ViewCompat.setClipBounds(view, clipBounds);
         }
      };
   }
}
