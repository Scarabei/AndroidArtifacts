package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(21)
class PropertyValuesHolderUtilsApi21 implements PropertyValuesHolderUtilsImpl {
   public PropertyValuesHolder ofPointF(Property property, Path path) {
      return PropertyValuesHolder.ofObject(property, (TypeConverter)null, path);
   }
}
