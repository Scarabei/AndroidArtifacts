package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

public class ArcMotion extends PathMotion {
   private static final float DEFAULT_MIN_ANGLE_DEGREES = 0.0F;
   private static final float DEFAULT_MAX_ANGLE_DEGREES = 70.0F;
   private static final float DEFAULT_MAX_TANGENT = (float)Math.tan(Math.toRadians(35.0D));
   private float mMinimumHorizontalAngle = 0.0F;
   private float mMinimumVerticalAngle = 0.0F;
   private float mMaximumAngle = 70.0F;
   private float mMinimumHorizontalTangent = 0.0F;
   private float mMinimumVerticalTangent = 0.0F;
   private float mMaximumTangent;

   public ArcMotion() {
      this.mMaximumTangent = DEFAULT_MAX_TANGENT;
   }

   public ArcMotion(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mMaximumTangent = DEFAULT_MAX_TANGENT;
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.ARC_MOTION);
      XmlPullParser parser = (XmlPullParser)attrs;
      float minimumVerticalAngle = TypedArrayUtils.getNamedFloat(a, parser, "minimumVerticalAngle", 1, 0.0F);
      this.setMinimumVerticalAngle(minimumVerticalAngle);
      float minimumHorizontalAngle = TypedArrayUtils.getNamedFloat(a, parser, "minimumHorizontalAngle", 0, 0.0F);
      this.setMinimumHorizontalAngle(minimumHorizontalAngle);
      float maximumAngle = TypedArrayUtils.getNamedFloat(a, parser, "maximumAngle", 2, 70.0F);
      this.setMaximumAngle(maximumAngle);
      a.recycle();
   }

   public void setMinimumHorizontalAngle(float angleInDegrees) {
      this.mMinimumHorizontalAngle = angleInDegrees;
      this.mMinimumHorizontalTangent = toTangent(angleInDegrees);
   }

   public float getMinimumHorizontalAngle() {
      return this.mMinimumHorizontalAngle;
   }

   public void setMinimumVerticalAngle(float angleInDegrees) {
      this.mMinimumVerticalAngle = angleInDegrees;
      this.mMinimumVerticalTangent = toTangent(angleInDegrees);
   }

   public float getMinimumVerticalAngle() {
      return this.mMinimumVerticalAngle;
   }

   public void setMaximumAngle(float angleInDegrees) {
      this.mMaximumAngle = angleInDegrees;
      this.mMaximumTangent = toTangent(angleInDegrees);
   }

   public float getMaximumAngle() {
      return this.mMaximumAngle;
   }

   private static float toTangent(float arcInDegrees) {
      if (arcInDegrees >= 0.0F && arcInDegrees <= 90.0F) {
         return (float)Math.tan(Math.toRadians((double)(arcInDegrees / 2.0F)));
      } else {
         throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
      }
   }

   public Path getPath(float startX, float startY, float endX, float endY) {
      Path path = new Path();
      path.moveTo(startX, startY);
      float deltaX = endX - startX;
      float deltaY = endY - startY;
      float h2 = deltaX * deltaX + deltaY * deltaY;
      float dx = (startX + endX) / 2.0F;
      float dy = (startY + endY) / 2.0F;
      float midDist2 = h2 * 0.25F;
      boolean isMovingUpwards = startY > endY;
      float ex;
      float ey;
      float minimumArcDist2;
      float arcDistX;
      if (Math.abs(deltaX) < Math.abs(deltaY)) {
         arcDistX = Math.abs(h2 / (2.0F * deltaY));
         if (isMovingUpwards) {
            ey = endY + arcDistX;
            ex = endX;
         } else {
            ey = startY + arcDistX;
            ex = startX;
         }

         minimumArcDist2 = midDist2 * this.mMinimumVerticalTangent * this.mMinimumVerticalTangent;
      } else {
         arcDistX = h2 / (2.0F * deltaX);
         if (isMovingUpwards) {
            ex = startX + arcDistX;
            ey = startY;
         } else {
            ex = endX - arcDistX;
            ey = endY;
         }

         minimumArcDist2 = midDist2 * this.mMinimumHorizontalTangent * this.mMinimumHorizontalTangent;
      }

      arcDistX = dx - ex;
      float arcDistY = dy - ey;
      float arcDist2 = arcDistX * arcDistX + arcDistY * arcDistY;
      float maximumArcDist2 = midDist2 * this.mMaximumTangent * this.mMaximumTangent;
      float newArcDistance2 = 0.0F;
      if (arcDist2 < minimumArcDist2) {
         newArcDistance2 = minimumArcDist2;
      } else if (arcDist2 > maximumArcDist2) {
         newArcDistance2 = maximumArcDist2;
      }

      float control1X;
      float control1Y;
      if (newArcDistance2 != 0.0F) {
         control1X = newArcDistance2 / arcDist2;
         control1Y = (float)Math.sqrt((double)control1X);
         ex = dx + control1Y * (ex - dx);
         ey = dy + control1Y * (ey - dy);
      }

      control1X = (startX + ex) / 2.0F;
      control1Y = (startY + ey) / 2.0F;
      float control2X = (ex + endX) / 2.0F;
      float control2Y = (ey + endY) / 2.0F;
      path.cubicTo(control1X, control1Y, control2X, control2Y, endX, endY);
      return path;
   }
}
