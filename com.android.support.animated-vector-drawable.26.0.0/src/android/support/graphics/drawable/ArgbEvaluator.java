package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ArgbEvaluator implements TypeEvaluator {
   private static final ArgbEvaluator sInstance = new ArgbEvaluator();

   public static ArgbEvaluator getInstance() {
      return sInstance;
   }

   public Object evaluate(float fraction, Object startValue, Object endValue) {
      int startInt = ((Integer)startValue).intValue();
      float startA = (float)(startInt >> 24 & 255) / 255.0F;
      float startR = (float)(startInt >> 16 & 255) / 255.0F;
      float startG = (float)(startInt >> 8 & 255) / 255.0F;
      float startB = (float)(startInt & 255) / 255.0F;
      int endInt = ((Integer)endValue).intValue();
      float endA = (float)(endInt >> 24 & 255) / 255.0F;
      float endR = (float)(endInt >> 16 & 255) / 255.0F;
      float endG = (float)(endInt >> 8 & 255) / 255.0F;
      float endB = (float)(endInt & 255) / 255.0F;
      startR = (float)Math.pow((double)startR, 2.2D);
      startG = (float)Math.pow((double)startG, 2.2D);
      startB = (float)Math.pow((double)startB, 2.2D);
      endR = (float)Math.pow((double)endR, 2.2D);
      endG = (float)Math.pow((double)endG, 2.2D);
      endB = (float)Math.pow((double)endB, 2.2D);
      float a = startA + fraction * (endA - startA);
      float r = startR + fraction * (endR - startR);
      float g = startG + fraction * (endG - startG);
      float b = startB + fraction * (endB - startB);
      a *= 255.0F;
      r = (float)Math.pow((double)r, 0.45454545454545453D) * 255.0F;
      g = (float)Math.pow((double)g, 0.45454545454545453D) * 255.0F;
      b = (float)Math.pow((double)b, 0.45454545454545453D) * 255.0F;
      return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
   }
}
