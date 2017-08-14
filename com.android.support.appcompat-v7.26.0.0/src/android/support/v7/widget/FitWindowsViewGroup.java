package android.support.v7.widget;

import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface FitWindowsViewGroup {
   void setOnFitSystemWindowsListener(FitWindowsViewGroup.OnFitSystemWindowsListener var1);

   public interface OnFitSystemWindowsListener {
      void onFitSystemWindows(Rect var1);
   }
}
