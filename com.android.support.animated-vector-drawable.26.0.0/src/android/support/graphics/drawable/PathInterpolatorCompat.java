package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({Scope.LIBRARY_GROUP})
public class PathInterpolatorCompat implements Interpolator {
   private static final float PRECISION = 0.002F;
   public static final int MAX_NUM_POINTS = 3000;
   public static final double EPSILON = 1.0E-5D;
   private float[] mX;
   private float[] mY;

   public PathInterpolatorCompat(Context context, AttributeSet attrs, XmlPullParser parser) {
      this(context.getResources(), context.getTheme(), attrs, parser);
   }

   public PathInterpolatorCompat(Resources res, Theme theme, AttributeSet attrs, XmlPullParser parser) {
      TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
      this.parseInterpolatorFromTypeArray(a, parser);
      a.recycle();
   }

   private void parseInterpolatorFromTypeArray(TypedArray a, XmlPullParser parser) {
      if (TypedArrayUtils.hasAttribute(parser, "pathData")) {
         String pathData = TypedArrayUtils.getNamedString(a, parser, "pathData", 4);
         Path path = PathParser.createPathFromPathData(pathData);
         if (path == null) {
            throw new InflateException("The path is null, which is created from " + pathData);
         }

         this.initPath(path);
      } else {
         if (!TypedArrayUtils.hasAttribute(parser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
         }

         if (!TypedArrayUtils.hasAttribute(parser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
         }

         float x1 = TypedArrayUtils.getNamedFloat(a, parser, "controlX1", 0, 0.0F);
         float y1 = TypedArrayUtils.getNamedFloat(a, parser, "controlY1", 1, 0.0F);
         boolean hasX2 = TypedArrayUtils.hasAttribute(parser, "controlX2");
         boolean hasY2 = TypedArrayUtils.hasAttribute(parser, "controlY2");
         if (hasX2 != hasY2) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
         }

         if (!hasX2) {
            this.initQuad(x1, y1);
         } else {
            float x2 = TypedArrayUtils.getNamedFloat(a, parser, "controlX2", 2, 0.0F);
            float y2 = TypedArrayUtils.getNamedFloat(a, parser, "controlY2", 3, 0.0F);
            this.initCubic(x1, y1, x2, y2);
         }
      }

   }

   private void initQuad(float controlX, float controlY) {
      Path path = new Path();
      path.moveTo(0.0F, 0.0F);
      path.quadTo(controlX, controlY, 1.0F, 1.0F);
      this.initPath(path);
   }

   private void initCubic(float x1, float y1, float x2, float y2) {
      Path path = new Path();
      path.moveTo(0.0F, 0.0F);
      path.cubicTo(x1, y1, x2, y2, 1.0F, 1.0F);
      this.initPath(path);
   }

   private void initPath(Path path) {
      PathMeasure pathMeasure = new PathMeasure(path, false);
      float pathLength = pathMeasure.getLength();
      int numPoints = Math.min(3000, (int)(pathLength / 0.002F) + 1);
      if (numPoints <= 0) {
         throw new IllegalArgumentException("The Path has a invalid length " + pathLength);
      } else {
         this.mX = new float[numPoints];
         this.mY = new float[numPoints];
         float[] position = new float[2];

         for(int i = 0; i < numPoints; ++i) {
            float distance = (float)i * pathLength / (float)(numPoints - 1);
            pathMeasure.getPosTan(distance, position, (float[])null);
            this.mX[i] = position[0];
            this.mY[i] = position[1];
         }

         if ((double)Math.abs(this.mX[0]) <= 1.0E-5D && (double)Math.abs(this.mY[0]) <= 1.0E-5D && (double)Math.abs(this.mX[numPoints - 1] - 1.0F) <= 1.0E-5D && (double)Math.abs(this.mY[numPoints - 1] - 1.0F) <= 1.0E-5D) {
            float prevX = 0.0F;
            int componentIndex = 0;

            for(int i = 0; i < numPoints; ++i) {
               float x = this.mX[componentIndex++];
               if (x < prevX) {
                  throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + x);
               }

               this.mX[i] = x;
               prevX = x;
            }

            if (pathMeasure.nextContour()) {
               throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
            }
         } else {
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1) start: " + this.mX[0] + "," + this.mY[0] + " end:" + this.mX[numPoints - 1] + "," + this.mY[numPoints - 1]);
         }
      }
   }

   public float getInterpolation(float t) {
      if (t <= 0.0F) {
         return 0.0F;
      } else if (t >= 1.0F) {
         return 1.0F;
      } else {
         int startIndex = 0;
         int endIndex = this.mX.length - 1;

         while(endIndex - startIndex > 1) {
            int midIndex = (startIndex + endIndex) / 2;
            if (t < this.mX[midIndex]) {
               endIndex = midIndex;
            } else {
               startIndex = midIndex;
            }
         }

         float xRange = this.mX[endIndex] - this.mX[startIndex];
         if (xRange == 0.0F) {
            return this.mY[startIndex];
         } else {
            float tInRange = t - this.mX[startIndex];
            float fraction = tInRange / xRange;
            float startY = this.mY[startIndex];
            float endY = this.mY[endIndex];
            return startY + fraction * (endY - startY);
         }
      }
   }
}
