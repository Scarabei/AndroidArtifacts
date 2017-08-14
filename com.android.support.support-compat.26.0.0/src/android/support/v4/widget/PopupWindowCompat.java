package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
   static final PopupWindowCompat.PopupWindowCompatBaseImpl IMPL;

   public static void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
      IMPL.showAsDropDown(popup, anchor, xoff, yoff, gravity);
   }

   public static void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
      IMPL.setOverlapAnchor(popupWindow, overlapAnchor);
   }

   public static boolean getOverlapAnchor(PopupWindow popupWindow) {
      return IMPL.getOverlapAnchor(popupWindow);
   }

   public static void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
      IMPL.setWindowLayoutType(popupWindow, layoutType);
   }

   public static int getWindowLayoutType(PopupWindow popupWindow) {
      return IMPL.getWindowLayoutType(popupWindow);
   }

   static {
      if (VERSION.SDK_INT >= 23) {
         IMPL = new PopupWindowCompat.PopupWindowCompatApi23Impl();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new PopupWindowCompat.PopupWindowCompatApi21Impl();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new PopupWindowCompat.PopupWindowCompatApi19Impl();
      } else {
         IMPL = new PopupWindowCompat.PopupWindowCompatBaseImpl();
      }

   }

   @RequiresApi(23)
   static class PopupWindowCompatApi23Impl extends PopupWindowCompat.PopupWindowCompatApi21Impl {
      public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
         popupWindow.setOverlapAnchor(overlapAnchor);
      }

      public boolean getOverlapAnchor(PopupWindow popupWindow) {
         return popupWindow.getOverlapAnchor();
      }

      public void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
         popupWindow.setWindowLayoutType(layoutType);
      }

      public int getWindowLayoutType(PopupWindow popupWindow) {
         return popupWindow.getWindowLayoutType();
      }
   }

   @RequiresApi(21)
   static class PopupWindowCompatApi21Impl extends PopupWindowCompat.PopupWindowCompatApi19Impl {
      private static final String TAG = "PopupWindowCompatApi21";
      private static Field sOverlapAnchorField;

      public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
         if (sOverlapAnchorField != null) {
            try {
               sOverlapAnchorField.set(popupWindow, overlapAnchor);
            } catch (IllegalAccessException var4) {
               Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", var4);
            }
         }

      }

      public boolean getOverlapAnchor(PopupWindow popupWindow) {
         if (sOverlapAnchorField != null) {
            try {
               return ((Boolean)sOverlapAnchorField.get(popupWindow)).booleanValue();
            } catch (IllegalAccessException var3) {
               Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", var3);
            }
         }

         return false;
      }

      static {
         try {
            sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
            sOverlapAnchorField.setAccessible(true);
         } catch (NoSuchFieldException var1) {
            Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", var1);
         }

      }
   }

   @RequiresApi(19)
   static class PopupWindowCompatApi19Impl extends PopupWindowCompat.PopupWindowCompatBaseImpl {
      public void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
         popup.showAsDropDown(anchor, xoff, yoff, gravity);
      }
   }

   static class PopupWindowCompatBaseImpl {
      private static Method sSetWindowLayoutTypeMethod;
      private static boolean sSetWindowLayoutTypeMethodAttempted;
      private static Method sGetWindowLayoutTypeMethod;
      private static boolean sGetWindowLayoutTypeMethodAttempted;

      public void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
         int hgrav = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(anchor)) & 7;
         if (hgrav == 5) {
            xoff -= popup.getWidth() - anchor.getWidth();
         }

         popup.showAsDropDown(anchor, xoff, yoff);
      }

      public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
      }

      public boolean getOverlapAnchor(PopupWindow popupWindow) {
         return false;
      }

      public void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
         if (!sSetWindowLayoutTypeMethodAttempted) {
            try {
               sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
               sSetWindowLayoutTypeMethod.setAccessible(true);
            } catch (Exception var5) {
               ;
            }

            sSetWindowLayoutTypeMethodAttempted = true;
         }

         if (sSetWindowLayoutTypeMethod != null) {
            try {
               sSetWindowLayoutTypeMethod.invoke(popupWindow, layoutType);
            } catch (Exception var4) {
               ;
            }
         }

      }

      public int getWindowLayoutType(PopupWindow popupWindow) {
         if (!sGetWindowLayoutTypeMethodAttempted) {
            try {
               sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType");
               sGetWindowLayoutTypeMethod.setAccessible(true);
            } catch (Exception var3) {
               ;
            }

            sGetWindowLayoutTypeMethodAttempted = true;
         }

         if (sGetWindowLayoutTypeMethod != null) {
            try {
               return ((Integer)sGetWindowLayoutTypeMethod.invoke(popupWindow)).intValue();
            } catch (Exception var4) {
               ;
            }
         }

         return 0;
      }
   }
}
