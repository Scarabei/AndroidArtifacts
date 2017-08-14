package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Visibility extends Transition {
   static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
   private static final String PROPNAME_PARENT = "android:visibility:parent";
   private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
   public static final int MODE_IN = 1;
   public static final int MODE_OUT = 2;
   private static final String[] sTransitionProperties = new String[]{"android:visibility:visibility", "android:visibility:parent"};
   private int mMode = 3;

   public Visibility() {
   }

   public Visibility(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.VISIBILITY_TRANSITION);
      int mode = TypedArrayUtils.getNamedInt(a, (XmlResourceParser)attrs, "transitionVisibilityMode", 0, 0);
      a.recycle();
      if (mode != 0) {
         this.setMode(mode);
      }

   }

   public void setMode(int mode) {
      if ((mode & -4) != 0) {
         throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
      } else {
         this.mMode = mode;
      }
   }

   public int getMode() {
      return this.mMode;
   }

   @Nullable
   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }

   private void captureValues(TransitionValues transitionValues) {
      int visibility = transitionValues.view.getVisibility();
      transitionValues.values.put("android:visibility:visibility", visibility);
      transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
      int[] loc = new int[2];
      transitionValues.view.getLocationOnScreen(loc);
      transitionValues.values.put("android:visibility:screenLocation", loc);
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public boolean isVisible(TransitionValues values) {
      if (values == null) {
         return false;
      } else {
         int visibility = ((Integer)values.values.get("android:visibility:visibility")).intValue();
         View parent = (View)values.values.get("android:visibility:parent");
         return visibility == 0 && parent != null;
      }
   }

   private Visibility.VisibilityInfo getVisibilityChangeInfo(TransitionValues startValues, TransitionValues endValues) {
      Visibility.VisibilityInfo visInfo = new Visibility.VisibilityInfo(null);
      visInfo.mVisibilityChange = false;
      visInfo.mFadeIn = false;
      if (startValues != null && startValues.values.containsKey("android:visibility:visibility")) {
         visInfo.mStartVisibility = ((Integer)startValues.values.get("android:visibility:visibility")).intValue();
         visInfo.mStartParent = (ViewGroup)startValues.values.get("android:visibility:parent");
      } else {
         visInfo.mStartVisibility = -1;
         visInfo.mStartParent = null;
      }

      if (endValues != null && endValues.values.containsKey("android:visibility:visibility")) {
         visInfo.mEndVisibility = ((Integer)endValues.values.get("android:visibility:visibility")).intValue();
         visInfo.mEndParent = (ViewGroup)endValues.values.get("android:visibility:parent");
      } else {
         visInfo.mEndVisibility = -1;
         visInfo.mEndParent = null;
      }

      if (startValues != null && endValues != null) {
         if (visInfo.mStartVisibility == visInfo.mEndVisibility && visInfo.mStartParent == visInfo.mEndParent) {
            return visInfo;
         }

         if (visInfo.mStartVisibility != visInfo.mEndVisibility) {
            if (visInfo.mStartVisibility == 0) {
               visInfo.mFadeIn = false;
               visInfo.mVisibilityChange = true;
            } else if (visInfo.mEndVisibility == 0) {
               visInfo.mFadeIn = true;
               visInfo.mVisibilityChange = true;
            }
         } else if (visInfo.mEndParent == null) {
            visInfo.mFadeIn = false;
            visInfo.mVisibilityChange = true;
         } else if (visInfo.mStartParent == null) {
            visInfo.mFadeIn = true;
            visInfo.mVisibilityChange = true;
         }
      } else if (startValues == null && visInfo.mEndVisibility == 0) {
         visInfo.mFadeIn = true;
         visInfo.mVisibilityChange = true;
      } else if (endValues == null && visInfo.mStartVisibility == 0) {
         visInfo.mFadeIn = false;
         visInfo.mVisibilityChange = true;
      }

      return visInfo;
   }

   @Nullable
   public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
      Visibility.VisibilityInfo visInfo = this.getVisibilityChangeInfo(startValues, endValues);
      if (visInfo.mVisibilityChange && (visInfo.mStartParent != null || visInfo.mEndParent != null)) {
         return visInfo.mFadeIn ? this.onAppear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility) : this.onDisappear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
      } else {
         return null;
      }
   }

   public Animator onAppear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
      if ((this.mMode & 1) == 1 && endValues != null) {
         if (startValues == null) {
            View endParent = (View)endValues.view.getParent();
            TransitionValues startParentValues = this.getMatchedTransitionValues(endParent, false);
            TransitionValues endParentValues = this.getTransitionValues(endParent, false);
            Visibility.VisibilityInfo parentVisibilityInfo = this.getVisibilityChangeInfo(startParentValues, endParentValues);
            if (parentVisibilityInfo.mVisibilityChange) {
               return null;
            }
         }

         return this.onAppear(sceneRoot, endValues.view, startValues, endValues);
      } else {
         return null;
      }
   }

   public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      return null;
   }

   public Animator onDisappear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
      if ((this.mMode & 2) != 2) {
         return null;
      } else {
         View startView = startValues != null ? startValues.view : null;
         View endView = endValues != null ? endValues.view : null;
         final View overlayView = null;
         View viewToKeep = null;
         if (endView != null && endView.getParent() != null) {
            if (endVisibility == 4) {
               viewToKeep = endView;
            } else if (startView == endView) {
               viewToKeep = endView;
            } else {
               overlayView = startView;
            }
         } else if (endView != null) {
            overlayView = endView;
         } else if (startView != null) {
            if (startView.getParent() == null) {
               overlayView = startView;
            } else if (startView.getParent() instanceof View) {
               View startParent = (View)startView.getParent();
               TransitionValues startParentValues = this.getTransitionValues(startParent, true);
               TransitionValues endParentValues = this.getMatchedTransitionValues(startParent, true);
               Visibility.VisibilityInfo parentVisibilityInfo = this.getVisibilityChangeInfo(startParentValues, endParentValues);
               if (!parentVisibilityInfo.mVisibilityChange) {
                  overlayView = TransitionUtils.copyViewImage(sceneRoot, startView, startParent);
               } else if (startParent.getParent() == null) {
                  int id = startParent.getId();
                  if (id != -1 && sceneRoot.findViewById(id) != null && this.mCanRemoveViews) {
                     overlayView = startView;
                  }
               }
            }
         }

         if (overlayView != null && startValues != null) {
            int[] screenLoc = (int[])((int[])startValues.values.get("android:visibility:screenLocation"));
            int screenX = screenLoc[0];
            int screenY = screenLoc[1];
            int[] loc = new int[2];
            sceneRoot.getLocationOnScreen(loc);
            overlayView.offsetLeftAndRight(screenX - loc[0] - overlayView.getLeft());
            overlayView.offsetTopAndBottom(screenY - loc[1] - overlayView.getTop());
            final ViewGroupOverlayImpl overlay = ViewGroupUtils.getOverlay(sceneRoot);
            overlay.add(overlayView);
            Animator animator = this.onDisappear(sceneRoot, overlayView, startValues, endValues);
            if (animator == null) {
               overlay.remove(overlayView);
            } else {
               animator.addListener(new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator animation) {
                     overlay.remove(overlayView);
                  }
               });
            }

            return animator;
         } else if (viewToKeep != null) {
            int originalVisibility = viewToKeep.getVisibility();
            ViewUtils.setTransitionVisibility(viewToKeep, 0);
            Animator animator = this.onDisappear(sceneRoot, viewToKeep, startValues, endValues);
            if (animator != null) {
               Visibility.DisappearListener disappearListener = new Visibility.DisappearListener(viewToKeep, endVisibility, true);
               animator.addListener(disappearListener);
               AnimatorUtils.addPauseListener(animator, disappearListener);
               this.addListener(disappearListener);
            } else {
               ViewUtils.setTransitionVisibility(viewToKeep, originalVisibility);
            }

            return animator;
         } else {
            return null;
         }
      }
   }

   public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      return null;
   }

   public boolean isTransitionRequired(TransitionValues startValues, TransitionValues newValues) {
      if (startValues == null && newValues == null) {
         return false;
      } else if (startValues != null && newValues != null && newValues.values.containsKey("android:visibility:visibility") != startValues.values.containsKey("android:visibility:visibility")) {
         return false;
      } else {
         Visibility.VisibilityInfo changeInfo = this.getVisibilityChangeInfo(startValues, newValues);
         return changeInfo.mVisibilityChange && (changeInfo.mStartVisibility == 0 || changeInfo.mEndVisibility == 0);
      }
   }

   private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener, AnimatorUtilsApi14.AnimatorPauseListenerCompat {
      private final View mView;
      private final int mFinalVisibility;
      private final ViewGroup mParent;
      private final boolean mSuppressLayout;
      private boolean mLayoutSuppressed;
      boolean mCanceled = false;

      DisappearListener(View view, int finalVisibility, boolean suppressLayout) {
         this.mView = view;
         this.mFinalVisibility = finalVisibility;
         this.mParent = (ViewGroup)view.getParent();
         this.mSuppressLayout = suppressLayout;
         this.suppressLayout(true);
      }

      public void onAnimationPause(Animator animation) {
         if (!this.mCanceled) {
            ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
         }

      }

      public void onAnimationResume(Animator animation) {
         if (!this.mCanceled) {
            ViewUtils.setTransitionVisibility(this.mView, 0);
         }

      }

      public void onAnimationCancel(Animator animation) {
         this.mCanceled = true;
      }

      public void onAnimationRepeat(Animator animation) {
      }

      public void onAnimationStart(Animator animation) {
      }

      public void onAnimationEnd(Animator animation) {
         this.hideViewWhenNotCanceled();
      }

      public void onTransitionStart(@NonNull Transition transition) {
      }

      public void onTransitionEnd(@NonNull Transition transition) {
         this.hideViewWhenNotCanceled();
         transition.removeListener(this);
      }

      public void onTransitionCancel(@NonNull Transition transition) {
      }

      public void onTransitionPause(@NonNull Transition transition) {
         this.suppressLayout(false);
      }

      public void onTransitionResume(@NonNull Transition transition) {
         this.suppressLayout(true);
      }

      private void hideViewWhenNotCanceled() {
         if (!this.mCanceled) {
            ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            if (this.mParent != null) {
               this.mParent.invalidate();
            }
         }

         this.suppressLayout(false);
      }

      private void suppressLayout(boolean suppress) {
         if (this.mSuppressLayout && this.mLayoutSuppressed != suppress && this.mParent != null) {
            this.mLayoutSuppressed = suppress;
            ViewGroupUtils.suppressLayout(this.mParent, suppress);
         }

      }
   }

   private static class VisibilityInfo {
      boolean mVisibilityChange;
      boolean mFadeIn;
      int mStartVisibility;
      int mEndVisibility;
      ViewGroup mStartParent;
      ViewGroup mEndParent;

      private VisibilityInfo() {
      }

      // $FF: synthetic method
      VisibilityInfo(Object x0) {
         this();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface Mode {
   }
}
