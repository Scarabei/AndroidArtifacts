package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
interface GhostViewImpl {
   void setVisibility(int var1);

   void reserveEndViewTransition(ViewGroup var1, View var2);

   public interface Creator {
      GhostViewImpl addGhost(View var1, ViewGroup var2, Matrix var3);

      void removeGhost(View var1);
   }
}
