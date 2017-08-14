package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator {
   private final float[] mValues;
   private final float mStepSize;

   public LookupTableInterpolator(float[] values) {
      this.mValues = values;
      this.mStepSize = 1.0F / (float)(this.mValues.length - 1);
   }

   public float getInterpolation(float input) {
      if (input >= 1.0F) {
         return 1.0F;
      } else if (input <= 0.0F) {
         return 0.0F;
      } else {
         int position = Math.min((int)(input * (float)(this.mValues.length - 1)), this.mValues.length - 2);
         float quantized = (float)position * this.mStepSize;
         float diff = input - quantized;
         float weight = diff / this.mStepSize;
         return this.mValues[position] + weight * (this.mValues[position + 1] - this.mValues[position]);
      }
   }
}
