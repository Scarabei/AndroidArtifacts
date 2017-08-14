package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class GhostViewApi21 implements GhostViewImpl {
   private static final String TAG = "GhostViewApi21";
   private static Class sGhostViewClass;
   private static boolean sGhostViewClassFetched;
   private static Method sAddGhostMethod;
   private static boolean sAddGhostMethodFetched;
   private static Method sRemoveGhostMethod;
   private static boolean sRemoveGhostMethodFetched;
   private final View mGhostView;

   private GhostViewApi21(@NonNull View ghostView) {
      this.mGhostView = ghostView;
   }

   public void setVisibility(int visibility) {
      this.mGhostView.setVisibility(visibility);
   }

   public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
   }

   private static void fetchGhostViewClass() {
      if (!sGhostViewClassFetched) {
         try {
            sGhostViewClass = Class.forName("android.view.GhostView");
         } catch (ClassNotFoundException var1) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", var1);
         }

         sGhostViewClassFetched = true;
      }

   }

   private static void fetchAddGhostMethod() {
      if (!sAddGhostMethodFetched) {
         try {
            fetchGhostViewClass();
            sAddGhostMethod = sGhostViewClass.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
            sAddGhostMethod.setAccessible(true);
         } catch (NoSuchMethodException var1) {
            Log.i("GhostViewApi21", "Failed to retrieve addGhost method", var1);
         }

         sAddGhostMethodFetched = true;
      }

   }

   private static void fetchRemoveGhostMethod() {
      if (!sRemoveGhostMethodFetched) {
         try {
            fetchGhostViewClass();
            sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", View.class);
            sRemoveGhostMethod.setAccessible(true);
         } catch (NoSuchMethodException var1) {
            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", var1);
         }

         sRemoveGhostMethodFetched = true;
      }

   }

   // $FF: synthetic method
   GhostViewApi21(View x0, Object x1) {
      this(x0);
   }

   static class Creator implements GhostViewImpl.Creator {
      public GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
         GhostViewApi21.fetchAddGhostMethod();
         if (GhostViewApi21.sAddGhostMethod != null) {
            try {
               return new GhostViewApi21((View)GhostViewApi21.sAddGhostMethod.invoke((Object)null, view, viewGroup, matrix));
            } catch (IllegalAccessException var5) {
               ;
            } catch (InvocationTargetException var6) {
               throw new RuntimeException(var6.getCause());
            }
         }

         return null;
      }

      public void removeGhost(View view) {
         GhostViewApi21.fetchRemoveGhostMethod();
         if (GhostViewApi21.sRemoveGhostMethod != null) {
            try {
               GhostViewApi21.sRemoveGhostMethod.invoke((Object)null, view);
            } catch (IllegalAccessException var3) {
               ;
            } catch (InvocationTargetException var4) {
               throw new RuntimeException(var4.getCause());
            }
         }

      }
   }
}
