package android.support.transition;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

public abstract class PathMotion {
   public PathMotion() {
   }

   public PathMotion(Context context, AttributeSet attrs) {
   }

   public abstract Path getPath(float var1, float var2, float var3, float var4);
}
