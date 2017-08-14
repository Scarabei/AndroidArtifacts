package android.support.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty extends Property {
   private final Property mProperty;
   private final PathMeasure mPathMeasure;
   private final float mPathLength;
   private final float[] mPosition = new float[2];
   private final PointF mPointF = new PointF();
   private float mCurrentFraction;

   PathProperty(Property property, Path path) {
      super(Float.class, property.getName());
      this.mProperty = property;
      this.mPathMeasure = new PathMeasure(path, false);
      this.mPathLength = this.mPathMeasure.getLength();
   }

   public Float get(Object object) {
      return this.mCurrentFraction;
   }

   public void set(Object target, Float fraction) {
      this.mCurrentFraction = fraction.floatValue();
      this.mPathMeasure.getPosTan(this.mPathLength * fraction.floatValue(), this.mPosition, (float[])null);
      this.mPointF.x = this.mPosition[0];
      this.mPointF.y = this.mPosition[1];
      this.mProperty.set(target, this.mPointF);
   }
}
