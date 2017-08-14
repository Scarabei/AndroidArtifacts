package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(14)
class ObjectAnimatorUtilsApi14 implements ObjectAnimatorUtilsImpl {
   public ObjectAnimator ofPointF(Object target, Property property, Path path) {
      return ObjectAnimator.ofFloat(target, new PathProperty(property, path), new float[]{0.0F, 1.0F});
   }
}
