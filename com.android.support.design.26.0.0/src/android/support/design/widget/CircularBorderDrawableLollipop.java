package android.support.design.widget;

import android.graphics.Outline;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class CircularBorderDrawableLollipop extends CircularBorderDrawable {
   public void getOutline(Outline outline) {
      this.copyBounds(this.mRect);
      outline.setOval(this.mRect);
   }
}
