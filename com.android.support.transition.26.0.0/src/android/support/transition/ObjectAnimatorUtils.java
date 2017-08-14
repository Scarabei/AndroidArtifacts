package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.util.Property;

class ObjectAnimatorUtils {
   private static final ObjectAnimatorUtilsImpl IMPL;

   static ObjectAnimator ofPointF(Object target, Property property, Path path) {
      return IMPL.ofPointF(target, property, path);
   }

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new ObjectAnimatorUtilsApi21();
      } else {
         IMPL = new ObjectAnimatorUtilsApi14();
      }

   }
}
