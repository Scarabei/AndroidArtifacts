package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(14)
class PropertyValuesHolderUtilsApi14 implements PropertyValuesHolderUtilsImpl {
   public PropertyValuesHolder ofPointF(Property property, Path path) {
      return PropertyValuesHolder.ofFloat(new PathProperty(property, path), new float[]{0.0F, 1.0F});
   }
}
