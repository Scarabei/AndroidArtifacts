package android.support.v7.graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TimingLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Palette {
   static final int DEFAULT_RESIZE_BITMAP_AREA = 12544;
   static final int DEFAULT_CALCULATE_NUMBER_COLORS = 16;
   static final float MIN_CONTRAST_TITLE_TEXT = 3.0F;
   static final float MIN_CONTRAST_BODY_TEXT = 4.5F;
   static final String LOG_TAG = "Palette";
   static final boolean LOG_TIMINGS = false;
   private final List mSwatches;
   private final List mTargets;
   private final Map mSelectedSwatches;
   private final SparseBooleanArray mUsedColors;
   private final Palette.Swatch mDominantSwatch;
   static final Palette.Filter DEFAULT_FILTER = new Palette.Filter() {
      private static final float BLACK_MAX_LIGHTNESS = 0.05F;
      private static final float WHITE_MIN_LIGHTNESS = 0.95F;

      public boolean isAllowed(int rgb, float[] hsl) {
         return !this.isWhite(hsl) && !this.isBlack(hsl) && !this.isNearRedILine(hsl);
      }

      private boolean isBlack(float[] hslColor) {
         return hslColor[2] <= 0.05F;
      }

      private boolean isWhite(float[] hslColor) {
         return hslColor[2] >= 0.95F;
      }

      private boolean isNearRedILine(float[] hslColor) {
         return hslColor[0] >= 10.0F && hslColor[0] <= 37.0F && hslColor[1] <= 0.82F;
      }
   };

   public static Palette.Builder from(Bitmap bitmap) {
      return new Palette.Builder(bitmap);
   }

   public static Palette from(List swatches) {
      return (new Palette.Builder(swatches)).generate();
   }

   /** @deprecated */
   @Deprecated
   public static Palette generate(Bitmap bitmap) {
      return from(bitmap).generate();
   }

   /** @deprecated */
   @Deprecated
   public static Palette generate(Bitmap bitmap, int numColors) {
      return from(bitmap).maximumColorCount(numColors).generate();
   }

   /** @deprecated */
   @Deprecated
   public static AsyncTask generateAsync(Bitmap bitmap, Palette.PaletteAsyncListener listener) {
      return from(bitmap).generate(listener);
   }

   /** @deprecated */
   @Deprecated
   public static AsyncTask generateAsync(Bitmap bitmap, int numColors, Palette.PaletteAsyncListener listener) {
      return from(bitmap).maximumColorCount(numColors).generate(listener);
   }

   Palette(List swatches, List targets) {
      this.mSwatches = swatches;
      this.mTargets = targets;
      this.mUsedColors = new SparseBooleanArray();
      this.mSelectedSwatches = new ArrayMap();
      this.mDominantSwatch = this.findDominantSwatch();
   }

   @NonNull
   public List getSwatches() {
      return Collections.unmodifiableList(this.mSwatches);
   }

   @NonNull
   public List getTargets() {
      return Collections.unmodifiableList(this.mTargets);
   }

   @Nullable
   public Palette.Swatch getVibrantSwatch() {
      return this.getSwatchForTarget(Target.VIBRANT);
   }

   @Nullable
   public Palette.Swatch getLightVibrantSwatch() {
      return this.getSwatchForTarget(Target.LIGHT_VIBRANT);
   }

   @Nullable
   public Palette.Swatch getDarkVibrantSwatch() {
      return this.getSwatchForTarget(Target.DARK_VIBRANT);
   }

   @Nullable
   public Palette.Swatch getMutedSwatch() {
      return this.getSwatchForTarget(Target.MUTED);
   }

   @Nullable
   public Palette.Swatch getLightMutedSwatch() {
      return this.getSwatchForTarget(Target.LIGHT_MUTED);
   }

   @Nullable
   public Palette.Swatch getDarkMutedSwatch() {
      return this.getSwatchForTarget(Target.DARK_MUTED);
   }

   @ColorInt
   public int getVibrantColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.VIBRANT, defaultColor);
   }

   @ColorInt
   public int getLightVibrantColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.LIGHT_VIBRANT, defaultColor);
   }

   @ColorInt
   public int getDarkVibrantColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.DARK_VIBRANT, defaultColor);
   }

   @ColorInt
   public int getMutedColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.MUTED, defaultColor);
   }

   @ColorInt
   public int getLightMutedColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.LIGHT_MUTED, defaultColor);
   }

   @ColorInt
   public int getDarkMutedColor(@ColorInt int defaultColor) {
      return this.getColorForTarget(Target.DARK_MUTED, defaultColor);
   }

   @Nullable
   public Palette.Swatch getSwatchForTarget(@NonNull Target target) {
      return (Palette.Swatch)this.mSelectedSwatches.get(target);
   }

   @ColorInt
   public int getColorForTarget(@NonNull Target target, @ColorInt int defaultColor) {
      Palette.Swatch swatch = this.getSwatchForTarget(target);
      return swatch != null ? swatch.getRgb() : defaultColor;
   }

   @Nullable
   public Palette.Swatch getDominantSwatch() {
      return this.mDominantSwatch;
   }

   @ColorInt
   public int getDominantColor(@ColorInt int defaultColor) {
      return this.mDominantSwatch != null ? this.mDominantSwatch.getRgb() : defaultColor;
   }

   void generate() {
      int i = 0;

      for(int count = this.mTargets.size(); i < count; ++i) {
         Target target = (Target)this.mTargets.get(i);
         target.normalizeWeights();
         this.mSelectedSwatches.put(target, this.generateScoredTarget(target));
      }

      this.mUsedColors.clear();
   }

   private Palette.Swatch generateScoredTarget(Target target) {
      Palette.Swatch maxScoreSwatch = this.getMaxScoredSwatchForTarget(target);
      if (maxScoreSwatch != null && target.isExclusive()) {
         this.mUsedColors.append(maxScoreSwatch.getRgb(), true);
      }

      return maxScoreSwatch;
   }

   private Palette.Swatch getMaxScoredSwatchForTarget(Target target) {
      float maxScore = 0.0F;
      Palette.Swatch maxScoreSwatch = null;
      int i = 0;

      for(int count = this.mSwatches.size(); i < count; ++i) {
         Palette.Swatch swatch = (Palette.Swatch)this.mSwatches.get(i);
         if (this.shouldBeScoredForTarget(swatch, target)) {
            float score = this.generateScore(swatch, target);
            if (maxScoreSwatch == null || score > maxScore) {
               maxScoreSwatch = swatch;
               maxScore = score;
            }
         }
      }

      return maxScoreSwatch;
   }

   private boolean shouldBeScoredForTarget(Palette.Swatch swatch, Target target) {
      float[] hsl = swatch.getHsl();
      return hsl[1] >= target.getMinimumSaturation() && hsl[1] <= target.getMaximumSaturation() && hsl[2] >= target.getMinimumLightness() && hsl[2] <= target.getMaximumLightness() && !this.mUsedColors.get(swatch.getRgb());
   }

   private float generateScore(Palette.Swatch swatch, Target target) {
      float[] hsl = swatch.getHsl();
      float saturationScore = 0.0F;
      float luminanceScore = 0.0F;
      float populationScore = 0.0F;
      int maxPopulation = this.mDominantSwatch != null ? this.mDominantSwatch.getPopulation() : 1;
      if (target.getSaturationWeight() > 0.0F) {
         saturationScore = target.getSaturationWeight() * (1.0F - Math.abs(hsl[1] - target.getTargetSaturation()));
      }

      if (target.getLightnessWeight() > 0.0F) {
         luminanceScore = target.getLightnessWeight() * (1.0F - Math.abs(hsl[2] - target.getTargetLightness()));
      }

      if (target.getPopulationWeight() > 0.0F) {
         populationScore = target.getPopulationWeight() * ((float)swatch.getPopulation() / (float)maxPopulation);
      }

      return saturationScore + luminanceScore + populationScore;
   }

   private Palette.Swatch findDominantSwatch() {
      int maxPop = Integer.MIN_VALUE;
      Palette.Swatch maxSwatch = null;
      int i = 0;

      for(int count = this.mSwatches.size(); i < count; ++i) {
         Palette.Swatch swatch = (Palette.Swatch)this.mSwatches.get(i);
         if (swatch.getPopulation() > maxPop) {
            maxSwatch = swatch;
            maxPop = swatch.getPopulation();
         }
      }

      return maxSwatch;
   }

   private static float[] copyHslValues(Palette.Swatch color) {
      float[] newHsl = new float[3];
      System.arraycopy(color.getHsl(), 0, newHsl, 0, 3);
      return newHsl;
   }

   public interface Filter {
      boolean isAllowed(int var1, float[] var2);
   }

   public static final class Builder {
      private final List mSwatches;
      private final Bitmap mBitmap;
      private final List mTargets = new ArrayList();
      private int mMaxColors = 16;
      private int mResizeArea = 12544;
      private int mResizeMaxDimension = -1;
      private final List mFilters = new ArrayList();
      private Rect mRegion;

      public Builder(Bitmap bitmap) {
         if (bitmap != null && !bitmap.isRecycled()) {
            this.mFilters.add(Palette.DEFAULT_FILTER);
            this.mBitmap = bitmap;
            this.mSwatches = null;
            this.mTargets.add(Target.LIGHT_VIBRANT);
            this.mTargets.add(Target.VIBRANT);
            this.mTargets.add(Target.DARK_VIBRANT);
            this.mTargets.add(Target.LIGHT_MUTED);
            this.mTargets.add(Target.MUTED);
            this.mTargets.add(Target.DARK_MUTED);
         } else {
            throw new IllegalArgumentException("Bitmap is not valid");
         }
      }

      public Builder(List swatches) {
         if (swatches != null && !swatches.isEmpty()) {
            this.mFilters.add(Palette.DEFAULT_FILTER);
            this.mSwatches = swatches;
            this.mBitmap = null;
         } else {
            throw new IllegalArgumentException("List of Swatches is not valid");
         }
      }

      @NonNull
      public Palette.Builder maximumColorCount(int colors) {
         this.mMaxColors = colors;
         return this;
      }

      /** @deprecated */
      @Deprecated
      @NonNull
      public Palette.Builder resizeBitmapSize(int maxDimension) {
         this.mResizeMaxDimension = maxDimension;
         this.mResizeArea = -1;
         return this;
      }

      @NonNull
      public Palette.Builder resizeBitmapArea(int area) {
         this.mResizeArea = area;
         this.mResizeMaxDimension = -1;
         return this;
      }

      @NonNull
      public Palette.Builder clearFilters() {
         this.mFilters.clear();
         return this;
      }

      @NonNull
      public Palette.Builder addFilter(Palette.Filter filter) {
         if (filter != null) {
            this.mFilters.add(filter);
         }

         return this;
      }

      @NonNull
      public Palette.Builder setRegion(int left, int top, int right, int bottom) {
         if (this.mBitmap != null) {
            if (this.mRegion == null) {
               this.mRegion = new Rect();
            }

            this.mRegion.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
            if (!this.mRegion.intersect(left, top, right, bottom)) {
               throw new IllegalArgumentException("The given region must intersect with the Bitmap's dimensions.");
            }
         }

         return this;
      }

      @NonNull
      public Palette.Builder clearRegion() {
         this.mRegion = null;
         return this;
      }

      @NonNull
      public Palette.Builder addTarget(@NonNull Target target) {
         if (!this.mTargets.contains(target)) {
            this.mTargets.add(target);
         }

         return this;
      }

      @NonNull
      public Palette.Builder clearTargets() {
         if (this.mTargets != null) {
            this.mTargets.clear();
         }

         return this;
      }

      @NonNull
      public Palette generate() {
         TimingLogger logger = null;
         List swatches;
         if (this.mBitmap != null) {
            Bitmap bitmap = this.scaleBitmapDown(this.mBitmap);
            if (logger != null) {
               ((TimingLogger)logger).addSplit("Processed Bitmap");
            }

            Rect region = this.mRegion;
            if (bitmap != this.mBitmap && region != null) {
               double scale = (double)bitmap.getWidth() / (double)this.mBitmap.getWidth();
               region.left = (int)Math.floor((double)region.left * scale);
               region.top = (int)Math.floor((double)region.top * scale);
               region.right = Math.min((int)Math.ceil((double)region.right * scale), bitmap.getWidth());
               region.bottom = Math.min((int)Math.ceil((double)region.bottom * scale), bitmap.getHeight());
            }

            ColorCutQuantizer quantizer = new ColorCutQuantizer(this.getPixelsFromBitmap(bitmap), this.mMaxColors, this.mFilters.isEmpty() ? null : (Palette.Filter[])this.mFilters.toArray(new Palette.Filter[this.mFilters.size()]));
            if (bitmap != this.mBitmap) {
               bitmap.recycle();
            }

            swatches = quantizer.getQuantizedColors();
            if (logger != null) {
               ((TimingLogger)logger).addSplit("Color quantization completed");
            }
         } else {
            swatches = this.mSwatches;
         }

         Palette p = new Palette(swatches, this.mTargets);
         p.generate();
         if (logger != null) {
            ((TimingLogger)logger).addSplit("Created Palette");
            ((TimingLogger)logger).dumpToLog();
         }

         return p;
      }

      @NonNull
      public AsyncTask generate(final Palette.PaletteAsyncListener listener) {
         if (listener == null) {
            throw new IllegalArgumentException("listener can not be null");
         } else {
            return AsyncTaskCompat.executeParallel(new AsyncTask() {
               protected Palette doInBackground(Bitmap... params) {
                  try {
                     return Builder.this.generate();
                  } catch (Exception var3) {
                     Log.e("Palette", "Exception thrown during async generate", var3);
                     return null;
                  }
               }

               protected void onPostExecute(Palette colorExtractor) {
                  listener.onGenerated(colorExtractor);
               }
            }, new Bitmap[]{this.mBitmap});
         }
      }

      private int[] getPixelsFromBitmap(Bitmap bitmap) {
         int bitmapWidth = bitmap.getWidth();
         int bitmapHeight = bitmap.getHeight();
         int[] pixels = new int[bitmapWidth * bitmapHeight];
         bitmap.getPixels(pixels, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight);
         if (this.mRegion == null) {
            return pixels;
         } else {
            int regionWidth = this.mRegion.width();
            int regionHeight = this.mRegion.height();
            int[] subsetPixels = new int[regionWidth * regionHeight];

            for(int row = 0; row < regionHeight; ++row) {
               System.arraycopy(pixels, (row + this.mRegion.top) * bitmapWidth + this.mRegion.left, subsetPixels, row * regionWidth, regionWidth);
            }

            return subsetPixels;
         }
      }

      private Bitmap scaleBitmapDown(Bitmap bitmap) {
         double scaleRatio = -1.0D;
         int maxDimension;
         if (this.mResizeArea > 0) {
            maxDimension = bitmap.getWidth() * bitmap.getHeight();
            if (maxDimension > this.mResizeArea) {
               scaleRatio = Math.sqrt((double)this.mResizeArea / (double)maxDimension);
            }
         } else if (this.mResizeMaxDimension > 0) {
            maxDimension = Math.max(bitmap.getWidth(), bitmap.getHeight());
            if (maxDimension > this.mResizeMaxDimension) {
               scaleRatio = (double)this.mResizeMaxDimension / (double)maxDimension;
            }
         }

         return scaleRatio <= 0.0D ? bitmap : Bitmap.createScaledBitmap(bitmap, (int)Math.ceil((double)bitmap.getWidth() * scaleRatio), (int)Math.ceil((double)bitmap.getHeight() * scaleRatio), false);
      }
   }

   public static final class Swatch {
      private final int mRed;
      private final int mGreen;
      private final int mBlue;
      private final int mRgb;
      private final int mPopulation;
      private boolean mGeneratedTextColors;
      private int mTitleTextColor;
      private int mBodyTextColor;
      private float[] mHsl;

      public Swatch(@ColorInt int color, int population) {
         this.mRed = Color.red(color);
         this.mGreen = Color.green(color);
         this.mBlue = Color.blue(color);
         this.mRgb = color;
         this.mPopulation = population;
      }

      Swatch(int red, int green, int blue, int population) {
         this.mRed = red;
         this.mGreen = green;
         this.mBlue = blue;
         this.mRgb = Color.rgb(red, green, blue);
         this.mPopulation = population;
      }

      Swatch(float[] hsl, int population) {
         this(ColorUtils.HSLToColor(hsl), population);
         this.mHsl = hsl;
      }

      @ColorInt
      public int getRgb() {
         return this.mRgb;
      }

      public float[] getHsl() {
         if (this.mHsl == null) {
            this.mHsl = new float[3];
         }

         ColorUtils.RGBToHSL(this.mRed, this.mGreen, this.mBlue, this.mHsl);
         return this.mHsl;
      }

      public int getPopulation() {
         return this.mPopulation;
      }

      @ColorInt
      public int getTitleTextColor() {
         this.ensureTextColorsGenerated();
         return this.mTitleTextColor;
      }

      @ColorInt
      public int getBodyTextColor() {
         this.ensureTextColorsGenerated();
         return this.mBodyTextColor;
      }

      private void ensureTextColorsGenerated() {
         if (!this.mGeneratedTextColors) {
            int lightBodyAlpha = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 4.5F);
            int lightTitleAlpha = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 3.0F);
            if (lightBodyAlpha != -1 && lightTitleAlpha != -1) {
               this.mBodyTextColor = ColorUtils.setAlphaComponent(-1, lightBodyAlpha);
               this.mTitleTextColor = ColorUtils.setAlphaComponent(-1, lightTitleAlpha);
               this.mGeneratedTextColors = true;
               return;
            }

            int darkBodyAlpha = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 4.5F);
            int darkTitleAlpha = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 3.0F);
            if (darkBodyAlpha != -1 && darkBodyAlpha != -1) {
               this.mBodyTextColor = ColorUtils.setAlphaComponent(-16777216, darkBodyAlpha);
               this.mTitleTextColor = ColorUtils.setAlphaComponent(-16777216, darkTitleAlpha);
               this.mGeneratedTextColors = true;
               return;
            }

            this.mBodyTextColor = lightBodyAlpha != -1 ? ColorUtils.setAlphaComponent(-1, lightBodyAlpha) : ColorUtils.setAlphaComponent(-16777216, darkBodyAlpha);
            this.mTitleTextColor = lightTitleAlpha != -1 ? ColorUtils.setAlphaComponent(-1, lightTitleAlpha) : ColorUtils.setAlphaComponent(-16777216, darkTitleAlpha);
            this.mGeneratedTextColors = true;
         }

      }

      public String toString() {
         return this.getClass().getSimpleName() + " [RGB: #" + Integer.toHexString(this.getRgb()) + ']' + " [HSL: " + Arrays.toString(this.getHsl()) + ']' + " [Population: " + this.mPopulation + ']' + " [Title Text: #" + Integer.toHexString(this.getTitleTextColor()) + ']' + " [Body Text: #" + Integer.toHexString(this.getBodyTextColor()) + ']';
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Palette.Swatch swatch = (Palette.Swatch)o;
            return this.mPopulation == swatch.mPopulation && this.mRgb == swatch.mRgb;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return 31 * this.mRgb + this.mPopulation;
      }
   }

   public interface PaletteAsyncListener {
      void onGenerated(Palette var1);
   }
}
