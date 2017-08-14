package android.support.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(21)
class ObjectAnimatorUtilsApi21 implements ObjectAnimatorUtilsImpl {
   public ObjectAnimator ofPointF(Object target, Property property, Path path) {
      return ObjectAnimator.ofObject(target, property, (TypeConverter)null, path);
   }
}
