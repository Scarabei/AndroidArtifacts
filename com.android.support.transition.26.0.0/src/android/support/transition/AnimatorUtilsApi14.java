package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator.AnimatorListener;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;

@RequiresApi(14)
class AnimatorUtilsApi14 implements AnimatorUtilsImpl {
   public void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter listener) {
   }

   public void pause(@NonNull Animator animator) {
      ArrayList listeners = animator.getListeners();
      if (listeners != null) {
         int i = 0;

         for(int size = listeners.size(); i < size; ++i) {
            AnimatorListener listener = (AnimatorListener)listeners.get(i);
            if (listener instanceof AnimatorUtilsApi14.AnimatorPauseListenerCompat) {
               ((AnimatorUtilsApi14.AnimatorPauseListenerCompat)listener).onAnimationPause(animator);
            }
         }
      }

   }

   public void resume(@NonNull Animator animator) {
      ArrayList listeners = animator.getListeners();
      if (listeners != null) {
         int i = 0;

         for(int size = listeners.size(); i < size; ++i) {
            AnimatorListener listener = (AnimatorListener)listeners.get(i);
            if (listener instanceof AnimatorUtilsApi14.AnimatorPauseListenerCompat) {
               ((AnimatorUtilsApi14.AnimatorPauseListenerCompat)listener).onAnimationResume(animator);
            }
         }
      }

   }

   interface AnimatorPauseListenerCompat {
      void onAnimationPause(Animator var1);

      void onAnimationResume(Animator var1);
   }
}
