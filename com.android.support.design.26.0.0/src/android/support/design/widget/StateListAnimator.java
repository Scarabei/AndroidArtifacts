package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator {
   private final ArrayList mTuples = new ArrayList();
   private StateListAnimator.Tuple mLastMatch = null;
   ValueAnimator mRunningAnimator = null;
   private final AnimatorListener mAnimationListener = new AnimatorListenerAdapter() {
      public void onAnimationEnd(Animator animator) {
         if (StateListAnimator.this.mRunningAnimator == animator) {
            StateListAnimator.this.mRunningAnimator = null;
         }

      }
   };

   public void addState(int[] specs, ValueAnimator animator) {
      StateListAnimator.Tuple tuple = new StateListAnimator.Tuple(specs, animator);
      animator.addListener(this.mAnimationListener);
      this.mTuples.add(tuple);
   }

   void setState(int[] state) {
      StateListAnimator.Tuple match = null;
      int count = this.mTuples.size();

      for(int i = 0; i < count; ++i) {
         StateListAnimator.Tuple tuple = (StateListAnimator.Tuple)this.mTuples.get(i);
         if (StateSet.stateSetMatches(tuple.mSpecs, state)) {
            match = tuple;
            break;
         }
      }

      if (match != this.mLastMatch) {
         if (this.mLastMatch != null) {
            this.cancel();
         }

         this.mLastMatch = match;
         if (match != null) {
            this.start(match);
         }

      }
   }

   private void start(StateListAnimator.Tuple match) {
      this.mRunningAnimator = match.mAnimator;
      this.mRunningAnimator.start();
   }

   private void cancel() {
      if (this.mRunningAnimator != null) {
         this.mRunningAnimator.cancel();
         this.mRunningAnimator = null;
      }

   }

   public void jumpToCurrentState() {
      if (this.mRunningAnimator != null) {
         this.mRunningAnimator.end();
         this.mRunningAnimator = null;
      }

   }

   static class Tuple {
      final int[] mSpecs;
      final ValueAnimator mAnimator;

      Tuple(int[] specs, ValueAnimator animator) {
         this.mSpecs = specs;
         this.mAnimator = animator;
      }
   }
}
