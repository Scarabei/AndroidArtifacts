package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
   private static final String TAG = "ViewAnimatorCompat";
   private WeakReference mView;
   Runnable mStartAction = null;
   Runnable mEndAction = null;
   int mOldLayerType = -1;
   static final int LISTENER_TAG_ID = 2113929216;

   ViewPropertyAnimatorCompat(View view) {
      this.mView = new WeakReference(view);
   }

   public ViewPropertyAnimatorCompat setDuration(long value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().setDuration(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat alpha(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().alpha(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat alphaBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().alphaBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationX(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().translationX(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationY(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().translationY(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         if (VERSION.SDK_INT >= 16) {
            view.animate().withEndAction(runnable);
         } else {
            this.setListenerInternal(view, new ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14(this));
            this.mEndAction = runnable;
         }
      }

      return this;
   }

   public long getDuration() {
      View view;
      return (view = (View)this.mView.get()) != null ? view.animate().getDuration() : 0L;
   }

   public ViewPropertyAnimatorCompat setInterpolator(Interpolator value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().setInterpolator(value);
      }

      return this;
   }

   public Interpolator getInterpolator() {
      View view;
      return (view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 18 ? (Interpolator)view.animate().getInterpolator() : null;
   }

   public ViewPropertyAnimatorCompat setStartDelay(long value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().setStartDelay(value);
      }

      return this;
   }

   public long getStartDelay() {
      View view;
      return (view = (View)this.mView.get()) != null ? view.animate().getStartDelay() : 0L;
   }

   public ViewPropertyAnimatorCompat rotation(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotation(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat rotationBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotationBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat rotationX(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotationX(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat rotationXBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotationXBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat rotationY(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotationY(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat rotationYBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().rotationYBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat scaleX(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().scaleX(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat scaleXBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().scaleXBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat scaleY(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().scaleY(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat scaleYBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().scaleYBy(value);
      }

      return this;
   }

   public void cancel() {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().cancel();
      }

   }

   public ViewPropertyAnimatorCompat x(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().x(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat xBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().xBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat y(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().y(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat yBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().yBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationXBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().translationXBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationYBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().translationYBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationZBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 21) {
         view.animate().translationZBy(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat translationZ(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 21) {
         view.animate().translationZ(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat z(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 21) {
         view.animate().z(value);
      }

      return this;
   }

   public ViewPropertyAnimatorCompat zBy(float value) {
      View view;
      if ((view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 21) {
         view.animate().zBy(value);
      }

      return this;
   }

   public void start() {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         view.animate().start();
      }

   }

   public ViewPropertyAnimatorCompat withLayer() {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         if (VERSION.SDK_INT >= 16) {
            view.animate().withLayer();
         } else {
            this.mOldLayerType = view.getLayerType();
            this.setListenerInternal(view, new ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14(this));
         }
      }

      return this;
   }

   public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         if (VERSION.SDK_INT >= 16) {
            view.animate().withStartAction(runnable);
         } else {
            this.setListenerInternal(view, new ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14(this));
            this.mStartAction = runnable;
         }
      }

      return this;
   }

   public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener listener) {
      View view;
      if ((view = (View)this.mView.get()) != null) {
         if (VERSION.SDK_INT >= 16) {
            this.setListenerInternal(view, listener);
         } else {
            view.setTag(2113929216, listener);
            this.setListenerInternal(view, new ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14(this));
         }
      }

      return this;
   }

   private void setListenerInternal(final View view, final ViewPropertyAnimatorListener listener) {
      if (listener != null) {
         view.animate().setListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animation) {
               listener.onAnimationCancel(view);
            }

            public void onAnimationEnd(Animator animation) {
               listener.onAnimationEnd(view);
            }

            public void onAnimationStart(Animator animation) {
               listener.onAnimationStart(view);
            }
         });
      } else {
         view.animate().setListener((AnimatorListener)null);
      }

   }

   public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener listener) {
      final View view;
      if ((view = (View)this.mView.get()) != null && VERSION.SDK_INT >= 19) {
         AnimatorUpdateListener wrapped = null;
         if (listener != null) {
            wrapped = new AnimatorUpdateListener() {
               public void onAnimationUpdate(ValueAnimator valueAnimator) {
                  listener.onAnimationUpdate(view);
               }
            };
         }

         view.animate().setUpdateListener(wrapped);
      }

      return this;
   }

   static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
      ViewPropertyAnimatorCompat mVpa;
      boolean mAnimEndCalled;

      ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat vpa) {
         this.mVpa = vpa;
      }

      public void onAnimationStart(View view) {
         this.mAnimEndCalled = false;
         if (this.mVpa.mOldLayerType > -1) {
            view.setLayerType(2, (Paint)null);
         }

         if (this.mVpa.mStartAction != null) {
            Runnable startAction = this.mVpa.mStartAction;
            this.mVpa.mStartAction = null;
            startAction.run();
         }

         Object listenerTag = view.getTag(2113929216);
         ViewPropertyAnimatorListener listener = null;
         if (listenerTag instanceof ViewPropertyAnimatorListener) {
            listener = (ViewPropertyAnimatorListener)listenerTag;
         }

         if (listener != null) {
            listener.onAnimationStart(view);
         }

      }

      public void onAnimationEnd(View view) {
         if (this.mVpa.mOldLayerType > -1) {
            view.setLayerType(this.mVpa.mOldLayerType, (Paint)null);
            this.mVpa.mOldLayerType = -1;
         }

         if (VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
            if (this.mVpa.mEndAction != null) {
               Runnable endAction = this.mVpa.mEndAction;
               this.mVpa.mEndAction = null;
               endAction.run();
            }

            Object listenerTag = view.getTag(2113929216);
            ViewPropertyAnimatorListener listener = null;
            if (listenerTag instanceof ViewPropertyAnimatorListener) {
               listener = (ViewPropertyAnimatorListener)listenerTag;
            }

            if (listener != null) {
               listener.onAnimationEnd(view);
            }

            this.mAnimEndCalled = true;
         }

      }

      public void onAnimationCancel(View view) {
         Object listenerTag = view.getTag(2113929216);
         ViewPropertyAnimatorListener listener = null;
         if (listenerTag instanceof ViewPropertyAnimatorListener) {
            listener = (ViewPropertyAnimatorListener)listenerTag;
         }

         if (listener != null) {
            listener.onAnimationCancel(view);
         }

      }
   }
}
