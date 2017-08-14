package android.support.transition;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.R.id;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(14)
class ViewGroupUtilsApi14 implements ViewGroupUtilsImpl {
   private static final String TAG = "ViewGroupUtilsApi14";
   private static final int LAYOUT_TRANSITION_CHANGING = 4;
   private static LayoutTransition sEmptyLayoutTransition;
   private static Field sLayoutSuppressedField;
   private static boolean sLayoutSuppressedFieldFetched;
   private static Method sCancelMethod;
   private static boolean sCancelMethodFetched;

   public ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup group) {
      return ViewGroupOverlayApi14.createFrom(group);
   }

   public void suppressLayout(@NonNull ViewGroup group, boolean suppress) {
      if (sEmptyLayoutTransition == null) {
         sEmptyLayoutTransition = new LayoutTransition() {
            public boolean isChangingLayout() {
               return true;
            }
         };
         sEmptyLayoutTransition.setAnimator(2, (Animator)null);
         sEmptyLayoutTransition.setAnimator(0, (Animator)null);
         sEmptyLayoutTransition.setAnimator(1, (Animator)null);
         sEmptyLayoutTransition.setAnimator(3, (Animator)null);
         sEmptyLayoutTransition.setAnimator(4, (Animator)null);
      }

      if (suppress) {
         LayoutTransition layoutTransition = group.getLayoutTransition();
         if (layoutTransition != null) {
            if (layoutTransition.isRunning()) {
               cancelLayoutTransition(layoutTransition);
            }

            if (layoutTransition != sEmptyLayoutTransition) {
               group.setTag(id.transition_layout_save, layoutTransition);
            }
         }

         group.setLayoutTransition(sEmptyLayoutTransition);
      } else {
         group.setLayoutTransition((LayoutTransition)null);
         if (!sLayoutSuppressedFieldFetched) {
            try {
               sLayoutSuppressedField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
               sLayoutSuppressedField.setAccessible(true);
            } catch (NoSuchFieldException var6) {
               Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }

            sLayoutSuppressedFieldFetched = true;
         }

         boolean layoutSuppressed = false;
         if (sLayoutSuppressedField != null) {
            try {
               layoutSuppressed = sLayoutSuppressedField.getBoolean(group);
               if (layoutSuppressed) {
                  sLayoutSuppressedField.setBoolean(group, false);
               }
            } catch (IllegalAccessException var5) {
               Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
            }
         }

         if (layoutSuppressed) {
            group.requestLayout();
         }

         LayoutTransition layoutTransition = (LayoutTransition)group.getTag(id.transition_layout_save);
         if (layoutTransition != null) {
            group.setTag(id.transition_layout_save, (Object)null);
            group.setLayoutTransition(layoutTransition);
         }
      }

   }

   private static void cancelLayoutTransition(LayoutTransition t) {
      if (!sCancelMethodFetched) {
         try {
            sCancelMethod = LayoutTransition.class.getDeclaredMethod("cancel");
            sCancelMethod.setAccessible(true);
         } catch (NoSuchMethodException var4) {
            Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
         }

         sCancelMethodFetched = true;
      }

      if (sCancelMethod != null) {
         try {
            sCancelMethod.invoke(t);
         } catch (IllegalAccessException var2) {
            Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
         } catch (InvocationTargetException var3) {
            Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
         }
      }

   }
}
