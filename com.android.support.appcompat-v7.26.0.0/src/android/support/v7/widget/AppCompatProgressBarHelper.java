package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper {
   private static final int[] TINT_ATTRS = new int[]{16843067, 16843068};
   private final ProgressBar mView;
   private Bitmap mSampleTile;

   AppCompatProgressBarHelper(ProgressBar view) {
      this.mView = view;
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, TINT_ATTRS, defStyleAttr, 0);
      Drawable drawable = a.getDrawableIfKnown(0);
      if (drawable != null) {
         this.mView.setIndeterminateDrawable(this.tileifyIndeterminate(drawable));
      }

      drawable = a.getDrawableIfKnown(1);
      if (drawable != null) {
         this.mView.setProgressDrawable(this.tileify(drawable, false));
      }

      a.recycle();
   }

   private Drawable tileify(Drawable drawable, boolean clip) {
      if (drawable instanceof DrawableWrapper) {
         Drawable inner = ((DrawableWrapper)drawable).getWrappedDrawable();
         if (inner != null) {
            inner = this.tileify(inner, clip);
            ((DrawableWrapper)drawable).setWrappedDrawable(inner);
         }
      } else {
         if (drawable instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable)drawable;
            int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];

            int i;
            for(int i = 0; i < N; ++i) {
               i = background.getId(i);
               outDrawables[i] = this.tileify(background.getDrawable(i), i == 16908301 || i == 16908303);
            }

            LayerDrawable newBg = new LayerDrawable(outDrawables);

            for(i = 0; i < N; ++i) {
               newBg.setId(i, background.getId(i));
            }

            return newBg;
         }

         if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
            Bitmap tileBitmap = bitmapDrawable.getBitmap();
            if (this.mSampleTile == null) {
               this.mSampleTile = tileBitmap;
            }

            ShapeDrawable shapeDrawable = new ShapeDrawable(this.getDrawableShape());
            BitmapShader bitmapShader = new BitmapShader(tileBitmap, TileMode.REPEAT, TileMode.CLAMP);
            shapeDrawable.getPaint().setShader(bitmapShader);
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return (Drawable)(clip ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable);
         }
      }

      return drawable;
   }

   private Drawable tileifyIndeterminate(Drawable drawable) {
      if (drawable instanceof AnimationDrawable) {
         AnimationDrawable background = (AnimationDrawable)drawable;
         int N = background.getNumberOfFrames();
         AnimationDrawable newBg = new AnimationDrawable();
         newBg.setOneShot(background.isOneShot());

         for(int i = 0; i < N; ++i) {
            Drawable frame = this.tileify(background.getFrame(i), true);
            frame.setLevel(10000);
            newBg.addFrame(frame, background.getDuration(i));
         }

         newBg.setLevel(10000);
         drawable = newBg;
      }

      return (Drawable)drawable;
   }

   private Shape getDrawableShape() {
      float[] roundedCorners = new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
      return new RoundRectShape(roundedCorners, (RectF)null, (float[])null);
   }

   Bitmap getSampleTime() {
      return this.mSampleTile;
   }
}
