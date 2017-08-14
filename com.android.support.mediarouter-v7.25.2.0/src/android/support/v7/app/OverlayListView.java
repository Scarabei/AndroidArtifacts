package android.support.v7.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class OverlayListView extends ListView {
   private final List mOverlayObjects = new ArrayList();

   public OverlayListView(Context context) {
      super(context);
   }

   public OverlayListView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public OverlayListView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public void addOverlayObject(OverlayListView.OverlayObject object) {
      this.mOverlayObjects.add(object);
   }

   public void startAnimationAll() {
      Iterator var1 = this.mOverlayObjects.iterator();

      while(var1.hasNext()) {
         OverlayListView.OverlayObject object = (OverlayListView.OverlayObject)var1.next();
         if (!object.isAnimationStarted()) {
            object.startAnimation(this.getDrawingTime());
         }
      }

   }

   public void stopAnimationAll() {
      Iterator var1 = this.mOverlayObjects.iterator();

      while(var1.hasNext()) {
         OverlayListView.OverlayObject object = (OverlayListView.OverlayObject)var1.next();
         object.stopAnimation();
      }

   }

   public void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      if (this.mOverlayObjects.size() > 0) {
         Iterator it = this.mOverlayObjects.iterator();

         while(it.hasNext()) {
            OverlayListView.OverlayObject object = (OverlayListView.OverlayObject)it.next();
            BitmapDrawable bitmap = object.getBitmapDrawable();
            if (bitmap != null) {
               bitmap.draw(canvas);
            }

            if (!object.update(this.getDrawingTime())) {
               it.remove();
            }
         }
      }

   }

   public static class OverlayObject {
      private BitmapDrawable mBitmap;
      private float mCurrentAlpha = 1.0F;
      private Rect mCurrentBounds;
      private Interpolator mInterpolator;
      private long mDuration;
      private Rect mStartRect;
      private int mDeltaY;
      private float mStartAlpha = 1.0F;
      private float mEndAlpha = 1.0F;
      private long mStartTime;
      private boolean mIsAnimationStarted;
      private boolean mIsAnimationEnded;
      private OverlayListView.OverlayObject.OnAnimationEndListener mListener;

      public OverlayObject(BitmapDrawable bitmap, Rect startRect) {
         this.mBitmap = bitmap;
         this.mStartRect = startRect;
         this.mCurrentBounds = new Rect(startRect);
         if (this.mBitmap != null && this.mCurrentBounds != null) {
            this.mBitmap.setAlpha((int)(this.mCurrentAlpha * 255.0F));
            this.mBitmap.setBounds(this.mCurrentBounds);
         }

      }

      public BitmapDrawable getBitmapDrawable() {
         return this.mBitmap;
      }

      public boolean isAnimationStarted() {
         return this.mIsAnimationStarted;
      }

      public OverlayListView.OverlayObject setAlphaAnimation(float startAlpha, float endAlpha) {
         this.mStartAlpha = startAlpha;
         this.mEndAlpha = endAlpha;
         return this;
      }

      public OverlayListView.OverlayObject setTranslateYAnimation(int deltaY) {
         this.mDeltaY = deltaY;
         return this;
      }

      public OverlayListView.OverlayObject setDuration(long duration) {
         this.mDuration = duration;
         return this;
      }

      public OverlayListView.OverlayObject setInterpolator(Interpolator interpolator) {
         this.mInterpolator = interpolator;
         return this;
      }

      public OverlayListView.OverlayObject setAnimationEndListener(OverlayListView.OverlayObject.OnAnimationEndListener listener) {
         this.mListener = listener;
         return this;
      }

      public void startAnimation(long startTime) {
         this.mStartTime = startTime;
         this.mIsAnimationStarted = true;
      }

      public void stopAnimation() {
         this.mIsAnimationStarted = true;
         this.mIsAnimationEnded = true;
         if (this.mListener != null) {
            this.mListener.onAnimationEnd();
         }

      }

      public boolean update(long currentTime) {
         if (this.mIsAnimationEnded) {
            return false;
         } else {
            float normalizedTime = (float)(currentTime - this.mStartTime) / (float)this.mDuration;
            normalizedTime = Math.max(0.0F, Math.min(1.0F, normalizedTime));
            if (!this.mIsAnimationStarted) {
               normalizedTime = 0.0F;
            }

            float interpolatedTime = this.mInterpolator == null ? normalizedTime : this.mInterpolator.getInterpolation(normalizedTime);
            int deltaY = (int)((float)this.mDeltaY * interpolatedTime);
            this.mCurrentBounds.top = this.mStartRect.top + deltaY;
            this.mCurrentBounds.bottom = this.mStartRect.bottom + deltaY;
            this.mCurrentAlpha = this.mStartAlpha + (this.mEndAlpha - this.mStartAlpha) * interpolatedTime;
            if (this.mBitmap != null && this.mCurrentBounds != null) {
               this.mBitmap.setAlpha((int)(this.mCurrentAlpha * 255.0F));
               this.mBitmap.setBounds(this.mCurrentBounds);
            }

            if (this.mIsAnimationStarted && normalizedTime >= 1.0F) {
               this.mIsAnimationEnded = true;
               if (this.mListener != null) {
                  this.mListener.onAnimationEnd();
               }
            }

            return !this.mIsAnimationEnded;
         }
      }

      public interface OnAnimationEndListener {
         void onAnimationEnd();
      }
   }
}
