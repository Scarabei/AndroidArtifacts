package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;

public final class BitmapCompat {
   static final BitmapCompat.BitmapCompatBaseImpl IMPL;

   public static boolean hasMipMap(Bitmap bitmap) {
      return IMPL.hasMipMap(bitmap);
   }

   public static void setHasMipMap(Bitmap bitmap, boolean hasMipMap) {
      IMPL.setHasMipMap(bitmap, hasMipMap);
   }

   public static int getAllocationByteCount(Bitmap bitmap) {
      return IMPL.getAllocationByteCount(bitmap);
   }

   static {
      if (VERSION.SDK_INT >= 19) {
         IMPL = new BitmapCompat.BitmapCompatApi19Impl();
      } else if (VERSION.SDK_INT >= 18) {
         IMPL = new BitmapCompat.BitmapCompatApi18Impl();
      } else {
         IMPL = new BitmapCompat.BitmapCompatBaseImpl();
      }

   }

   @RequiresApi(19)
   static class BitmapCompatApi19Impl extends BitmapCompat.BitmapCompatApi18Impl {
      public int getAllocationByteCount(Bitmap bitmap) {
         return bitmap.getAllocationByteCount();
      }
   }

   @RequiresApi(18)
   static class BitmapCompatApi18Impl extends BitmapCompat.BitmapCompatBaseImpl {
      public boolean hasMipMap(Bitmap bitmap) {
         return bitmap.hasMipMap();
      }

      public void setHasMipMap(Bitmap bitmap, boolean hasMipMap) {
         bitmap.setHasMipMap(hasMipMap);
      }
   }

   static class BitmapCompatBaseImpl {
      public boolean hasMipMap(Bitmap bitmap) {
         return false;
      }

      public void setHasMipMap(Bitmap bitmap, boolean hasMipMap) {
      }

      public int getAllocationByteCount(Bitmap bitmap) {
         return bitmap.getByteCount();
      }
   }
}
