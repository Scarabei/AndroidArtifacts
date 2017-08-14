package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
   private static final WeakHashMap sInstances = new WeakHashMap();
   public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";

   public static DisplayManagerCompat getInstance(Context context) {
      WeakHashMap var1 = sInstances;
      synchronized(sInstances) {
         DisplayManagerCompat instance = (DisplayManagerCompat)sInstances.get(context);
         if (instance == null) {
            if (VERSION.SDK_INT >= 17) {
               instance = new DisplayManagerCompat.DisplayManagerCompatApi17Impl(context);
            } else {
               instance = new DisplayManagerCompat.DisplayManagerCompatApi14Impl(context);
            }

            sInstances.put(context, instance);
         }

         return (DisplayManagerCompat)instance;
      }
   }

   public abstract Display getDisplay(int var1);

   public abstract Display[] getDisplays();

   public abstract Display[] getDisplays(String var1);

   @RequiresApi(17)
   private static class DisplayManagerCompatApi17Impl extends DisplayManagerCompat {
      private final DisplayManager mDisplayManager;

      DisplayManagerCompatApi17Impl(Context context) {
         this.mDisplayManager = (DisplayManager)context.getSystemService("display");
      }

      public Display getDisplay(int displayId) {
         return this.mDisplayManager.getDisplay(displayId);
      }

      public Display[] getDisplays() {
         return this.mDisplayManager.getDisplays();
      }

      public Display[] getDisplays(String category) {
         return this.mDisplayManager.getDisplays(category);
      }
   }

   private static class DisplayManagerCompatApi14Impl extends DisplayManagerCompat {
      private final WindowManager mWindowManager;

      DisplayManagerCompatApi14Impl(Context context) {
         this.mWindowManager = (WindowManager)context.getSystemService("window");
      }

      public Display getDisplay(int displayId) {
         Display display = this.mWindowManager.getDefaultDisplay();
         return display.getDisplayId() == displayId ? display : null;
      }

      public Display[] getDisplays() {
         return new Display[]{this.mWindowManager.getDefaultDisplay()};
      }

      public Display[] getDisplays(String category) {
         return category == null ? this.getDisplays() : new Display[0];
      }
   }
}
