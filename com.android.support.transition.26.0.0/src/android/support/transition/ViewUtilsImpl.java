package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(14)
interface ViewUtilsImpl {
   ViewOverlayImpl getOverlay(@NonNull View var1);

   WindowIdImpl getWindowId(@NonNull View var1);

   void setTransitionAlpha(@NonNull View var1, float var2);

   float getTransitionAlpha(@NonNull View var1);

   void saveNonTransitionAlpha(@NonNull View var1);

   void clearNonTransitionAlpha(@NonNull View var1);

   void transformMatrixToGlobal(@NonNull View var1, @NonNull Matrix var2);

   void transformMatrixToLocal(@NonNull View var1, @NonNull Matrix var2);

   void setAnimationMatrix(@NonNull View var1, Matrix var2);

   void setLeftTopRightBottom(View var1, int var2, int var3, int var4, int var5);
}
