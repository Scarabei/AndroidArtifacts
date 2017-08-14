package android.support.v7.view;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ViewPropertyAnimatorCompatSet {
   final ArrayList mAnimators = new ArrayList();
   private long mDuration = -1L;
   private Interpolator mInterpolator;
   ViewPropertyAnimatorListener mListener;
   private boolean mIsStarted;
   private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter() {
      private boolean mProxyStarted = false;
      private int mProxyEndCount = 0;

      public void onAnimationStart(View view) {
         if (!this.mProxyStarted) {
            this.mProxyStarted = true;
            if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
               ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart((View)null);
            }

         }
      }

      void onEnd() {
         this.mProxyEndCount = 0;
         this.mProxyStarted = false;
         ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
      }

      public void onAnimationEnd(View view) {
         if (++this.mProxyEndCount == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
            if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
               ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd((View)null);
            }

            this.onEnd();
         }

      }
   };

   public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat animator) {
      if (!this.mIsStarted) {
         this.mAnimators.add(animator);
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat anim1, ViewPropertyAnimatorCompat anim2) {
      this.mAnimators.add(anim1);
      anim2.setStartDelay(anim1.getDuration());
      this.mAnimators.add(anim2);
      return this;
   }

   public void start() {
      if (!this.mIsStarted) {
         ViewPropertyAnimatorCompat animator;
         for(Iterator var1 = this.mAnimators.iterator(); var1.hasNext(); animator.start()) {
            animator = (ViewPropertyAnimatorCompat)var1.next();
            if (this.mDuration >= 0L) {
               animator.setDuration(this.mDuration);
            }

            if (this.mInterpolator != null) {
               animator.setInterpolator(this.mInterpolator);
            }

            if (this.mListener != null) {
               animator.setListener(this.mProxyListener);
            }
         }

         this.mIsStarted = true;
      }
   }

   void onAnimationsEnded() {
      this.mIsStarted = false;
   }

   public void cancel() {
      if (this.mIsStarted) {
         Iterator var1 = this.mAnimators.iterator();

         while(var1.hasNext()) {
            ViewPropertyAnimatorCompat animator = (ViewPropertyAnimatorCompat)var1.next();
            animator.cancel();
         }

         this.mIsStarted = false;
      }
   }

   public ViewPropertyAnimatorCompatSet setDuration(long duration) {
      if (!this.mIsStarted) {
         this.mDuration = duration;
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator) {
      if (!this.mIsStarted) {
         this.mInterpolator = interpolator;
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener listener) {
      if (!this.mIsStarted) {
         this.mListener = listener;
      }

      return this;
   }
}
