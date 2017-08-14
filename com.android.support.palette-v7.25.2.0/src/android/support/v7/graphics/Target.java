package android.support.v7.graphics;

import android.support.annotation.FloatRange;

public final class Target {
   private static final float TARGET_DARK_LUMA = 0.26F;
   private static final float MAX_DARK_LUMA = 0.45F;
   private static final float MIN_LIGHT_LUMA = 0.55F;
   private static final float TARGET_LIGHT_LUMA = 0.74F;
   private static final float MIN_NORMAL_LUMA = 0.3F;
   private static final float TARGET_NORMAL_LUMA = 0.5F;
   private static final float MAX_NORMAL_LUMA = 0.7F;
   private static final float TARGET_MUTED_SATURATION = 0.3F;
   private static final float MAX_MUTED_SATURATION = 0.4F;
   private static final float TARGET_VIBRANT_SATURATION = 1.0F;
   private static final float MIN_VIBRANT_SATURATION = 0.35F;
   private static final float WEIGHT_SATURATION = 0.24F;
   private static final float WEIGHT_LUMA = 0.52F;
   private static final float WEIGHT_POPULATION = 0.24F;
   static final int INDEX_MIN = 0;
   static final int INDEX_TARGET = 1;
   static final int INDEX_MAX = 2;
   static final int INDEX_WEIGHT_SAT = 0;
   static final int INDEX_WEIGHT_LUMA = 1;
   static final int INDEX_WEIGHT_POP = 2;
   public static final Target LIGHT_VIBRANT = new Target();
   public static final Target VIBRANT;
   public static final Target DARK_VIBRANT;
   public static final Target LIGHT_MUTED;
   public static final Target MUTED;
   public static final Target DARK_MUTED;
   final float[] mSaturationTargets = new float[3];
   final float[] mLightnessTargets = new float[3];
   final float[] mWeights = new float[3];
   boolean mIsExclusive = true;

   Target() {
      setTargetDefaultValues(this.mSaturationTargets);
      setTargetDefaultValues(this.mLightnessTargets);
      this.setDefaultWeights();
   }

   Target(Target from) {
      System.arraycopy(from.mSaturationTargets, 0, this.mSaturationTargets, 0, this.mSaturationTargets.length);
      System.arraycopy(from.mLightnessTargets, 0, this.mLightnessTargets, 0, this.mLightnessTargets.length);
      System.arraycopy(from.mWeights, 0, this.mWeights, 0, this.mWeights.length);
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getMinimumSaturation() {
      return this.mSaturationTargets[0];
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getTargetSaturation() {
      return this.mSaturationTargets[1];
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getMaximumSaturation() {
      return this.mSaturationTargets[2];
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getMinimumLightness() {
      return this.mLightnessTargets[0];
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getTargetLightness() {
      return this.mLightnessTargets[1];
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public float getMaximumLightness() {
      return this.mLightnessTargets[2];
   }

   public float getSaturationWeight() {
      return this.mWeights[0];
   }

   public float getLightnessWeight() {
      return this.mWeights[1];
   }

   public float getPopulationWeight() {
      return this.mWeights[2];
   }

   public boolean isExclusive() {
      return this.mIsExclusive;
   }

   private static void setTargetDefaultValues(float[] values) {
      values[0] = 0.0F;
      values[1] = 0.5F;
      values[2] = 1.0F;
   }

   private void setDefaultWeights() {
      this.mWeights[0] = 0.24F;
      this.mWeights[1] = 0.52F;
      this.mWeights[2] = 0.24F;
   }

   void normalizeWeights() {
      float sum = 0.0F;
      int i = 0;

      int z;
      for(z = this.mWeights.length; i < z; ++i) {
         float weight = this.mWeights[i];
         if (weight > 0.0F) {
            sum += weight;
         }
      }

      if (sum != 0.0F) {
         i = 0;

         for(z = this.mWeights.length; i < z; ++i) {
            if (this.mWeights[i] > 0.0F) {
               this.mWeights[i] /= sum;
            }
         }
      }

   }

   private static void setDefaultDarkLightnessValues(Target target) {
      target.mLightnessTargets[1] = 0.26F;
      target.mLightnessTargets[2] = 0.45F;
   }

   private static void setDefaultNormalLightnessValues(Target target) {
      target.mLightnessTargets[0] = 0.3F;
      target.mLightnessTargets[1] = 0.5F;
      target.mLightnessTargets[2] = 0.7F;
   }

   private static void setDefaultLightLightnessValues(Target target) {
      target.mLightnessTargets[0] = 0.55F;
      target.mLightnessTargets[1] = 0.74F;
   }

   private static void setDefaultVibrantSaturationValues(Target target) {
      target.mSaturationTargets[0] = 0.35F;
      target.mSaturationTargets[1] = 1.0F;
   }

   private static void setDefaultMutedSaturationValues(Target target) {
      target.mSaturationTargets[1] = 0.3F;
      target.mSaturationTargets[2] = 0.4F;
   }

   static {
      setDefaultLightLightnessValues(LIGHT_VIBRANT);
      setDefaultVibrantSaturationValues(LIGHT_VIBRANT);
      VIBRANT = new Target();
      setDefaultNormalLightnessValues(VIBRANT);
      setDefaultVibrantSaturationValues(VIBRANT);
      DARK_VIBRANT = new Target();
      setDefaultDarkLightnessValues(DARK_VIBRANT);
      setDefaultVibrantSaturationValues(DARK_VIBRANT);
      LIGHT_MUTED = new Target();
      setDefaultLightLightnessValues(LIGHT_MUTED);
      setDefaultMutedSaturationValues(LIGHT_MUTED);
      MUTED = new Target();
      setDefaultNormalLightnessValues(MUTED);
      setDefaultMutedSaturationValues(MUTED);
      DARK_MUTED = new Target();
      setDefaultDarkLightnessValues(DARK_MUTED);
      setDefaultMutedSaturationValues(DARK_MUTED);
   }

   public static final class Builder {
      private final Target mTarget;

      public Builder() {
         this.mTarget = new Target();
      }

      public Builder(Target target) {
         this.mTarget = new Target(target);
      }

      public Target.Builder setMinimumSaturation(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mSaturationTargets[0] = value;
         return this;
      }

      public Target.Builder setTargetSaturation(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mSaturationTargets[1] = value;
         return this;
      }

      public Target.Builder setMaximumSaturation(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mSaturationTargets[2] = value;
         return this;
      }

      public Target.Builder setMinimumLightness(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mLightnessTargets[0] = value;
         return this;
      }

      public Target.Builder setTargetLightness(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mLightnessTargets[1] = value;
         return this;
      }

      public Target.Builder setMaximumLightness(@FloatRange(from = 0.0D,to = 1.0D) float value) {
         this.mTarget.mLightnessTargets[2] = value;
         return this;
      }

      public Target.Builder setSaturationWeight(@FloatRange(from = 0.0D) float weight) {
         this.mTarget.mWeights[0] = weight;
         return this;
      }

      public Target.Builder setLightnessWeight(@FloatRange(from = 0.0D) float weight) {
         this.mTarget.mWeights[1] = weight;
         return this;
      }

      public Target.Builder setPopulationWeight(@FloatRange(from = 0.0D) float weight) {
         this.mTarget.mWeights[2] = weight;
         return this;
      }

      public Target.Builder setExclusive(boolean exclusive) {
         this.mTarget.mIsExclusive = exclusive;
         return this;
      }

      public Target build() {
         return this.mTarget;
      }
   }
}
