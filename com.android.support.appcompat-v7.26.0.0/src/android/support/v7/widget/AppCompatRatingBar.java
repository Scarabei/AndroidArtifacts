package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.appcompat.R.attr;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

public class AppCompatRatingBar extends RatingBar {
   private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

   public AppCompatRatingBar(Context context) {
      this(context, (AttributeSet)null);
   }

   public AppCompatRatingBar(Context context, AttributeSet attrs) {
      this(context, attrs, attr.ratingBarStyle);
   }

   public AppCompatRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mAppCompatProgressBarHelper = new AppCompatProgressBarHelper(this);
      this.mAppCompatProgressBarHelper.loadFromAttributes(attrs, defStyleAttr);
   }

   protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      Bitmap sampleTile = this.mAppCompatProgressBarHelper.getSampleTime();
      if (sampleTile != null) {
         int width = sampleTile.getWidth() * this.getNumStars();
         this.setMeasuredDimension(View.resolveSizeAndState(width, widthMeasureSpec, 0), this.getMeasuredHeight());
      }

   }
}
