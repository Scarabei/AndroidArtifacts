package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

public final class ColorUtils {
   private static final double XYZ_WHITE_REFERENCE_X = 95.047D;
   private static final double XYZ_WHITE_REFERENCE_Y = 100.0D;
   private static final double XYZ_WHITE_REFERENCE_Z = 108.883D;
   private static final double XYZ_EPSILON = 0.008856D;
   private static final double XYZ_KAPPA = 903.3D;
   private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
   private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
   private static final ThreadLocal TEMP_ARRAY = new ThreadLocal();

   public static int compositeColors(@ColorInt int foreground, @ColorInt int background) {
      int bgAlpha = Color.alpha(background);
      int fgAlpha = Color.alpha(foreground);
      int a = compositeAlpha(fgAlpha, bgAlpha);
      int r = compositeComponent(Color.red(foreground), fgAlpha, Color.red(background), bgAlpha, a);
      int g = compositeComponent(Color.green(foreground), fgAlpha, Color.green(background), bgAlpha, a);
      int b = compositeComponent(Color.blue(foreground), fgAlpha, Color.blue(background), bgAlpha, a);
      return Color.argb(a, r, g, b);
   }

   private static int compositeAlpha(int foregroundAlpha, int backgroundAlpha) {
      return 255 - (255 - backgroundAlpha) * (255 - foregroundAlpha) / 255;
   }

   private static int compositeComponent(int fgC, int fgA, int bgC, int bgA, int a) {
      return a == 0 ? 0 : (255 * fgC * fgA + bgC * bgA * (255 - fgA)) / (a * 255);
   }

   @FloatRange(
      from = 0.0D,
      to = 1.0D
   )
   public static double calculateLuminance(@ColorInt int color) {
      double[] result = getTempDouble3Array();
      colorToXYZ(color, result);
      return result[1] / 100.0D;
   }

   public static double calculateContrast(@ColorInt int foreground, @ColorInt int background) {
      if (Color.alpha(background) != 255) {
         throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(background));
      } else {
         if (Color.alpha(foreground) < 255) {
            foreground = compositeColors(foreground, background);
         }

         double luminance1 = calculateLuminance(foreground) + 0.05D;
         double luminance2 = calculateLuminance(background) + 0.05D;
         return Math.max(luminance1, luminance2) / Math.min(luminance1, luminance2);
      }
   }

   public static int calculateMinimumAlpha(@ColorInt int foreground, @ColorInt int background, float minContrastRatio) {
      if (Color.alpha(background) != 255) {
         throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(background));
      } else {
         int testForeground = setAlphaComponent(foreground, 255);
         double testRatio = calculateContrast(testForeground, background);
         if (testRatio < (double)minContrastRatio) {
            return -1;
         } else {
            int numIterations = 0;
            int minAlpha = 0;

            int maxAlpha;
            for(maxAlpha = 255; numIterations <= 10 && maxAlpha - minAlpha > 1; ++numIterations) {
               int testAlpha = (minAlpha + maxAlpha) / 2;
               testForeground = setAlphaComponent(foreground, testAlpha);
               testRatio = calculateContrast(testForeground, background);
               if (testRatio < (double)minContrastRatio) {
                  minAlpha = testAlpha;
               } else {
                  maxAlpha = testAlpha;
               }
            }

            return maxAlpha;
         }
      }
   }

   public static void RGBToHSL(@IntRange(from = 0L,to = 255L) int r, @IntRange(from = 0L,to = 255L) int g, @IntRange(from = 0L,to = 255L) int b, @NonNull float[] outHsl) {
      float rf = (float)r / 255.0F;
      float gf = (float)g / 255.0F;
      float bf = (float)b / 255.0F;
      float max = Math.max(rf, Math.max(gf, bf));
      float min = Math.min(rf, Math.min(gf, bf));
      float deltaMaxMin = max - min;
      float l = (max + min) / 2.0F;
      float h;
      float s;
      if (max == min) {
         s = 0.0F;
         h = 0.0F;
      } else {
         if (max == rf) {
            h = (gf - bf) / deltaMaxMin % 6.0F;
         } else if (max == gf) {
            h = (bf - rf) / deltaMaxMin + 2.0F;
         } else {
            h = (rf - gf) / deltaMaxMin + 4.0F;
         }

         s = deltaMaxMin / (1.0F - Math.abs(2.0F * l - 1.0F));
      }

      h = h * 60.0F % 360.0F;
      if (h < 0.0F) {
         h += 360.0F;
      }

      outHsl[0] = constrain(h, 0.0F, 360.0F);
      outHsl[1] = constrain(s, 0.0F, 1.0F);
      outHsl[2] = constrain(l, 0.0F, 1.0F);
   }

   public static void colorToHSL(@ColorInt int color, @NonNull float[] outHsl) {
      RGBToHSL(Color.red(color), Color.green(color), Color.blue(color), outHsl);
   }

   @ColorInt
   public static int HSLToColor(@NonNull float[] hsl) {
      float h = hsl[0];
      float s = hsl[1];
      float l = hsl[2];
      float c = (1.0F - Math.abs(2.0F * l - 1.0F)) * s;
      float m = l - 0.5F * c;
      float x = c * (1.0F - Math.abs(h / 60.0F % 2.0F - 1.0F));
      int hueSegment = (int)h / 60;
      int r = 0;
      int g = 0;
      int b = 0;
      switch(hueSegment) {
      case 0:
         r = Math.round(255.0F * (c + m));
         g = Math.round(255.0F * (x + m));
         b = Math.round(255.0F * m);
         break;
      case 1:
         r = Math.round(255.0F * (x + m));
         g = Math.round(255.0F * (c + m));
         b = Math.round(255.0F * m);
         break;
      case 2:
         r = Math.round(255.0F * m);
         g = Math.round(255.0F * (c + m));
         b = Math.round(255.0F * (x + m));
         break;
      case 3:
         r = Math.round(255.0F * m);
         g = Math.round(255.0F * (x + m));
         b = Math.round(255.0F * (c + m));
         break;
      case 4:
         r = Math.round(255.0F * (x + m));
         g = Math.round(255.0F * m);
         b = Math.round(255.0F * (c + m));
         break;
      case 5:
      case 6:
         r = Math.round(255.0F * (c + m));
         g = Math.round(255.0F * m);
         b = Math.round(255.0F * (x + m));
      }

      r = constrain(r, 0, 255);
      g = constrain(g, 0, 255);
      b = constrain(b, 0, 255);
      return Color.rgb(r, g, b);
   }

   @ColorInt
   public static int setAlphaComponent(@ColorInt int color, @IntRange(from = 0L,to = 255L) int alpha) {
      if (alpha >= 0 && alpha <= 255) {
         return color & 16777215 | alpha << 24;
      } else {
         throw new IllegalArgumentException("alpha must be between 0 and 255.");
      }
   }

   public static void colorToLAB(@ColorInt int color, @NonNull double[] outLab) {
      RGBToLAB(Color.red(color), Color.green(color), Color.blue(color), outLab);
   }

   public static void RGBToLAB(@IntRange(from = 0L,to = 255L) int r, @IntRange(from = 0L,to = 255L) int g, @IntRange(from = 0L,to = 255L) int b, @NonNull double[] outLab) {
      RGBToXYZ(r, g, b, outLab);
      XYZToLAB(outLab[0], outLab[1], outLab[2], outLab);
   }

   public static void colorToXYZ(@ColorInt int color, @NonNull double[] outXyz) {
      RGBToXYZ(Color.red(color), Color.green(color), Color.blue(color), outXyz);
   }

   public static void RGBToXYZ(@IntRange(from = 0L,to = 255L) int r, @IntRange(from = 0L,to = 255L) int g, @IntRange(from = 0L,to = 255L) int b, @NonNull double[] outXyz) {
      if (outXyz.length != 3) {
         throw new IllegalArgumentException("outXyz must have a length of 3.");
      } else {
         double sr = (double)r / 255.0D;
         sr = sr < 0.04045D ? sr / 12.92D : Math.pow((sr + 0.055D) / 1.055D, 2.4D);
         double sg = (double)g / 255.0D;
         sg = sg < 0.04045D ? sg / 12.92D : Math.pow((sg + 0.055D) / 1.055D, 2.4D);
         double sb = (double)b / 255.0D;
         sb = sb < 0.04045D ? sb / 12.92D : Math.pow((sb + 0.055D) / 1.055D, 2.4D);
         outXyz[0] = 100.0D * (sr * 0.4124D + sg * 0.3576D + sb * 0.1805D);
         outXyz[1] = 100.0D * (sr * 0.2126D + sg * 0.7152D + sb * 0.0722D);
         outXyz[2] = 100.0D * (sr * 0.0193D + sg * 0.1192D + sb * 0.9505D);
      }
   }

   public static void XYZToLAB(@FloatRange(from = 0.0D,to = 95.047D) double x, @FloatRange(from = 0.0D,to = 100.0D) double y, @FloatRange(from = 0.0D,to = 108.883D) double z, @NonNull double[] outLab) {
      if (outLab.length != 3) {
         throw new IllegalArgumentException("outLab must have a length of 3.");
      } else {
         x = pivotXyzComponent(x / 95.047D);
         y = pivotXyzComponent(y / 100.0D);
         z = pivotXyzComponent(z / 108.883D);
         outLab[0] = Math.max(0.0D, 116.0D * y - 16.0D);
         outLab[1] = 500.0D * (x - y);
         outLab[2] = 200.0D * (y - z);
      }
   }

   public static void LABToXYZ(@FloatRange(from = 0.0D,to = 100.0D) double l, @FloatRange(from = -128.0D,to = 127.0D) double a, @FloatRange(from = -128.0D,to = 127.0D) double b, @NonNull double[] outXyz) {
      double fy = (l + 16.0D) / 116.0D;
      double fx = a / 500.0D + fy;
      double fz = fy - b / 200.0D;
      double tmp = Math.pow(fx, 3.0D);
      double xr = tmp > 0.008856D ? tmp : (116.0D * fx - 16.0D) / 903.3D;
      double yr = l > 7.9996247999999985D ? Math.pow(fy, 3.0D) : l / 903.3D;
      tmp = Math.pow(fz, 3.0D);
      double zr = tmp > 0.008856D ? tmp : (116.0D * fz - 16.0D) / 903.3D;
      outXyz[0] = xr * 95.047D;
      outXyz[1] = yr * 100.0D;
      outXyz[2] = zr * 108.883D;
   }

   @ColorInt
   public static int XYZToColor(@FloatRange(from = 0.0D,to = 95.047D) double x, @FloatRange(from = 0.0D,to = 100.0D) double y, @FloatRange(from = 0.0D,to = 108.883D) double z) {
      double r = (x * 3.2406D + y * -1.5372D + z * -0.4986D) / 100.0D;
      double g = (x * -0.9689D + y * 1.8758D + z * 0.0415D) / 100.0D;
      double b = (x * 0.0557D + y * -0.204D + z * 1.057D) / 100.0D;
      r = r > 0.0031308D ? 1.055D * Math.pow(r, 0.4166666666666667D) - 0.055D : 12.92D * r;
      g = g > 0.0031308D ? 1.055D * Math.pow(g, 0.4166666666666667D) - 0.055D : 12.92D * g;
      b = b > 0.0031308D ? 1.055D * Math.pow(b, 0.4166666666666667D) - 0.055D : 12.92D * b;
      return Color.rgb(constrain((int)Math.round(r * 255.0D), 0, 255), constrain((int)Math.round(g * 255.0D), 0, 255), constrain((int)Math.round(b * 255.0D), 0, 255));
   }

   @ColorInt
   public static int LABToColor(@FloatRange(from = 0.0D,to = 100.0D) double l, @FloatRange(from = -128.0D,to = 127.0D) double a, @FloatRange(from = -128.0D,to = 127.0D) double b) {
      double[] result = getTempDouble3Array();
      LABToXYZ(l, a, b, result);
      return XYZToColor(result[0], result[1], result[2]);
   }

   public static double distanceEuclidean(@NonNull double[] labX, @NonNull double[] labY) {
      return Math.sqrt(Math.pow(labX[0] - labY[0], 2.0D) + Math.pow(labX[1] - labY[1], 2.0D) + Math.pow(labX[2] - labY[2], 2.0D));
   }

   private static float constrain(float amount, float low, float high) {
      return amount < low ? low : (amount > high ? high : amount);
   }

   private static int constrain(int amount, int low, int high) {
      return amount < low ? low : (amount > high ? high : amount);
   }

   private static double pivotXyzComponent(double component) {
      return component > 0.008856D ? Math.pow(component, 0.3333333333333333D) : (903.3D * component + 16.0D) / 116.0D;
   }

   @ColorInt
   public static int blendARGB(@ColorInt int color1, @ColorInt int color2, @FloatRange(from = 0.0D,to = 1.0D) float ratio) {
      float inverseRatio = 1.0F - ratio;
      float a = (float)Color.alpha(color1) * inverseRatio + (float)Color.alpha(color2) * ratio;
      float r = (float)Color.red(color1) * inverseRatio + (float)Color.red(color2) * ratio;
      float g = (float)Color.green(color1) * inverseRatio + (float)Color.green(color2) * ratio;
      float b = (float)Color.blue(color1) * inverseRatio + (float)Color.blue(color2) * ratio;
      return Color.argb((int)a, (int)r, (int)g, (int)b);
   }

   public static void blendHSL(@NonNull float[] hsl1, @NonNull float[] hsl2, @FloatRange(from = 0.0D,to = 1.0D) float ratio, @NonNull float[] outResult) {
      if (outResult.length != 3) {
         throw new IllegalArgumentException("result must have a length of 3.");
      } else {
         float inverseRatio = 1.0F - ratio;
         outResult[0] = circularInterpolate(hsl1[0], hsl2[0], ratio);
         outResult[1] = hsl1[1] * inverseRatio + hsl2[1] * ratio;
         outResult[2] = hsl1[2] * inverseRatio + hsl2[2] * ratio;
      }
   }

   public static void blendLAB(@NonNull double[] lab1, @NonNull double[] lab2, @FloatRange(from = 0.0D,to = 1.0D) double ratio, @NonNull double[] outResult) {
      if (outResult.length != 3) {
         throw new IllegalArgumentException("outResult must have a length of 3.");
      } else {
         double inverseRatio = 1.0D - ratio;
         outResult[0] = lab1[0] * inverseRatio + lab2[0] * ratio;
         outResult[1] = lab1[1] * inverseRatio + lab2[1] * ratio;
         outResult[2] = lab1[2] * inverseRatio + lab2[2] * ratio;
      }
   }

   @VisibleForTesting
   static float circularInterpolate(float a, float b, float f) {
      if (Math.abs(b - a) > 180.0F) {
         if (b > a) {
            a += 360.0F;
         } else {
            b += 360.0F;
         }
      }

      return (a + (b - a) * f) % 360.0F;
   }

   private static double[] getTempDouble3Array() {
      double[] result = (double[])TEMP_ARRAY.get();
      if (result == null) {
         result = new double[3];
         TEMP_ARRAY.set(result);
      }

      return result;
   }
}
