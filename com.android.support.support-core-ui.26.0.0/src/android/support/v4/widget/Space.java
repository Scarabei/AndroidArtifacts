package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class Space extends View {
   public Space(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      if (this.getVisibility() == 0) {
         this.setVisibility(4);
      }

   }

   public Space(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public Space(Context context) {
      this(context, (AttributeSet)null);
   }

   @SuppressLint({"MissingSuperCall"})
   public void draw(Canvas canvas) {
   }

   private static int getDefaultSize2(int size, int measureSpec) {
      int result = size;
      int specMode = MeasureSpec.getMode(measureSpec);
      int specSize = MeasureSpec.getSize(measureSpec);
      switch(specMode) {
      case Integer.MIN_VALUE:
         result = Math.min(size, specSize);
         break;
      case 0:
         result = size;
         break;
      case 1073741824:
         result = specSize;
      }

      return result;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.setMeasuredDimension(getDefaultSize2(this.getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize2(this.getSuggestedMinimumHeight(), heightMeasureSpec));
   }
}
