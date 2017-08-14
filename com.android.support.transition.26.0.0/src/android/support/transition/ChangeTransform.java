package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.transition.R.id;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

public class ChangeTransform extends Transition {
   private static final String PROPNAME_MATRIX = "android:changeTransform:matrix";
   private static final String PROPNAME_TRANSFORMS = "android:changeTransform:transforms";
   private static final String PROPNAME_PARENT = "android:changeTransform:parent";
   private static final String PROPNAME_PARENT_MATRIX = "android:changeTransform:parentMatrix";
   private static final String PROPNAME_INTERMEDIATE_PARENT_MATRIX = "android:changeTransform:intermediateParentMatrix";
   private static final String PROPNAME_INTERMEDIATE_MATRIX = "android:changeTransform:intermediateMatrix";
   private static final String[] sTransitionProperties = new String[]{"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
   private static final Property NON_TRANSLATIONS_PROPERTY = new Property(float[].class, "nonTranslations") {
      public float[] get(ChangeTransform.PathAnimatorMatrix object) {
         return null;
      }

      public void set(ChangeTransform.PathAnimatorMatrix object, float[] value) {
         object.setValues(value);
      }
   };
   private static final Property TRANSLATIONS_PROPERTY = new Property(PointF.class, "translations") {
      public PointF get(ChangeTransform.PathAnimatorMatrix object) {
         return null;
      }

      public void set(ChangeTransform.PathAnimatorMatrix object, PointF value) {
         object.setTranslation(value);
      }
   };
   private static final boolean SUPPORTS_VIEW_REMOVAL_SUPPRESSION;
   private boolean mUseOverlay = true;
   private boolean mReparent = true;
   private Matrix mTempMatrix = new Matrix();

   public ChangeTransform() {
   }

   public ChangeTransform(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.CHANGE_TRANSFORM);
      this.mUseOverlay = TypedArrayUtils.getNamedBoolean(a, (XmlPullParser)attrs, "reparentWithOverlay", 1, true);
      this.mReparent = TypedArrayUtils.getNamedBoolean(a, (XmlPullParser)attrs, "reparent", 0, true);
      a.recycle();
   }

   public boolean getReparentWithOverlay() {
      return this.mUseOverlay;
   }

   public void setReparentWithOverlay(boolean reparentWithOverlay) {
      this.mUseOverlay = reparentWithOverlay;
   }

   public boolean getReparent() {
      return this.mReparent;
   }

   public void setReparent(boolean reparent) {
      this.mReparent = reparent;
   }

   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }

   private void captureValues(TransitionValues transitionValues) {
      View view = transitionValues.view;
      if (view.getVisibility() != 8) {
         transitionValues.values.put("android:changeTransform:parent", view.getParent());
         ChangeTransform.Transforms transforms = new ChangeTransform.Transforms(view);
         transitionValues.values.put("android:changeTransform:transforms", transforms);
         Matrix matrix = view.getMatrix();
         if (matrix != null && !matrix.isIdentity()) {
            matrix = new Matrix(matrix);
         } else {
            matrix = null;
         }

         transitionValues.values.put("android:changeTransform:matrix", matrix);
         if (this.mReparent) {
            Matrix parentMatrix = new Matrix();
            ViewGroup parent = (ViewGroup)view.getParent();
            ViewUtils.transformMatrixToGlobal(parent, parentMatrix);
            parentMatrix.preTranslate((float)(-parent.getScrollX()), (float)(-parent.getScrollY()));
            transitionValues.values.put("android:changeTransform:parentMatrix", parentMatrix);
            transitionValues.values.put("android:changeTransform:intermediateMatrix", view.getTag(id.transition_transform));
            transitionValues.values.put("android:changeTransform:intermediateParentMatrix", view.getTag(id.parent_matrix));
         }

      }
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
      if (!SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
         ((ViewGroup)transitionValues.view.getParent()).startViewTransition(transitionValues.view);
      }

   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public Animator createAnimator(@NonNull ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
      if (startValues != null && endValues != null && startValues.values.containsKey("android:changeTransform:parent") && endValues.values.containsKey("android:changeTransform:parent")) {
         ViewGroup startParent = (ViewGroup)startValues.values.get("android:changeTransform:parent");
         ViewGroup endParent = (ViewGroup)endValues.values.get("android:changeTransform:parent");
         boolean handleParentChange = this.mReparent && !this.parentsMatch(startParent, endParent);
         Matrix startMatrix = (Matrix)startValues.values.get("android:changeTransform:intermediateMatrix");
         if (startMatrix != null) {
            startValues.values.put("android:changeTransform:matrix", startMatrix);
         }

         Matrix startParentMatrix = (Matrix)startValues.values.get("android:changeTransform:intermediateParentMatrix");
         if (startParentMatrix != null) {
            startValues.values.put("android:changeTransform:parentMatrix", startParentMatrix);
         }

         if (handleParentChange) {
            this.setMatricesForParent(startValues, endValues);
         }

         ObjectAnimator transformAnimator = this.createTransformAnimator(startValues, endValues, handleParentChange);
         if (handleParentChange && transformAnimator != null && this.mUseOverlay) {
            this.createGhostView(sceneRoot, startValues, endValues);
         } else if (!SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            startParent.endViewTransition(startValues.view);
         }

         return transformAnimator;
      } else {
         return null;
      }
   }

   private ObjectAnimator createTransformAnimator(TransitionValues startValues, TransitionValues endValues, final boolean handleParentChange) {
      Matrix startMatrix = (Matrix)startValues.values.get("android:changeTransform:matrix");
      final Matrix endMatrix = (Matrix)endValues.values.get("android:changeTransform:matrix");
      if (startMatrix == null) {
         startMatrix = MatrixUtils.IDENTITY_MATRIX;
      }

      if (endMatrix == null) {
         endMatrix = MatrixUtils.IDENTITY_MATRIX;
      }

      if (startMatrix.equals(endMatrix)) {
         return null;
      } else {
         final ChangeTransform.Transforms transforms = (ChangeTransform.Transforms)endValues.values.get("android:changeTransform:transforms");
         final View view = endValues.view;
         setIdentityTransforms(view);
         float[] startMatrixValues = new float[9];
         startMatrix.getValues(startMatrixValues);
         float[] endMatrixValues = new float[9];
         endMatrix.getValues(endMatrixValues);
         final ChangeTransform.PathAnimatorMatrix pathAnimatorMatrix = new ChangeTransform.PathAnimatorMatrix(view, startMatrixValues);
         PropertyValuesHolder valuesProperty = PropertyValuesHolder.ofObject(NON_TRANSLATIONS_PROPERTY, new FloatArrayEvaluator(new float[9]), new float[][]{startMatrixValues, endMatrixValues});
         Path path = this.getPathMotion().getPath(startMatrixValues[2], startMatrixValues[5], endMatrixValues[2], endMatrixValues[5]);
         PropertyValuesHolder translationProperty = PropertyValuesHolderUtils.ofPointF(TRANSLATIONS_PROPERTY, path);
         ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(pathAnimatorMatrix, new PropertyValuesHolder[]{valuesProperty, translationProperty});
         AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {
            private boolean mIsCanceled;
            private Matrix mTempMatrix = new Matrix();

            public void onAnimationCancel(Animator animation) {
               this.mIsCanceled = true;
            }

            public void onAnimationEnd(Animator animation) {
               if (!this.mIsCanceled) {
                  if (handleParentChange && ChangeTransform.this.mUseOverlay) {
                     this.setCurrentMatrix(endMatrix);
                  } else {
                     view.setTag(id.transition_transform, (Object)null);
                     view.setTag(id.parent_matrix, (Object)null);
                  }
               }

               ViewUtils.setAnimationMatrix(view, (Matrix)null);
               transforms.restore(view);
            }

            public void onAnimationPause(Animator animation) {
               Matrix currentMatrix = pathAnimatorMatrix.getMatrix();
               this.setCurrentMatrix(currentMatrix);
            }

            public void onAnimationResume(Animator animation) {
               ChangeTransform.setIdentityTransforms(view);
            }

            private void setCurrentMatrix(Matrix currentMatrix) {
               this.mTempMatrix.set(currentMatrix);
               view.setTag(id.transition_transform, this.mTempMatrix);
               transforms.restore(view);
            }
         };
         animator.addListener(listener);
         AnimatorUtils.addPauseListener(animator, listener);
         return animator;
      }
   }

   private boolean parentsMatch(ViewGroup startParent, ViewGroup endParent) {
      boolean parentsMatch = false;
      if (this.isValidTarget(startParent) && this.isValidTarget(endParent)) {
         TransitionValues endValues = this.getMatchedTransitionValues(startParent, true);
         if (endValues != null) {
            parentsMatch = endParent == endValues.view;
         }
      } else {
         parentsMatch = startParent == endParent;
      }

      return parentsMatch;
   }

   private void createGhostView(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
      View view = endValues.view;
      Matrix endMatrix = (Matrix)endValues.values.get("android:changeTransform:parentMatrix");
      Matrix localEndMatrix = new Matrix(endMatrix);
      ViewUtils.transformMatrixToLocal(sceneRoot, localEndMatrix);
      GhostViewImpl ghostView = GhostViewUtils.addGhost(view, sceneRoot, localEndMatrix);
      if (ghostView != null) {
         ghostView.reserveEndViewTransition((ViewGroup)startValues.values.get("android:changeTransform:parent"), startValues.view);

         Object outerTransition;
         for(outerTransition = this; ((Transition)outerTransition).mParent != null; outerTransition = ((Transition)outerTransition).mParent) {
            ;
         }

         ChangeTransform.GhostListener listener = new ChangeTransform.GhostListener(view, ghostView);
         ((Transition)outerTransition).addListener(listener);
         if (SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            if (startValues.view != endValues.view) {
               ViewUtils.setTransitionAlpha(startValues.view, 0.0F);
            }

            ViewUtils.setTransitionAlpha(view, 1.0F);
         }

      }
   }

   private void setMatricesForParent(TransitionValues startValues, TransitionValues endValues) {
      Matrix endParentMatrix = (Matrix)endValues.values.get("android:changeTransform:parentMatrix");
      endValues.view.setTag(id.parent_matrix, endParentMatrix);
      Matrix toLocal = this.mTempMatrix;
      toLocal.reset();
      endParentMatrix.invert(toLocal);
      Matrix startLocal = (Matrix)startValues.values.get("android:changeTransform:matrix");
      if (startLocal == null) {
         startLocal = new Matrix();
         startValues.values.put("android:changeTransform:matrix", startLocal);
      }

      Matrix startParentMatrix = (Matrix)startValues.values.get("android:changeTransform:parentMatrix");
      startLocal.postConcat(startParentMatrix);
      startLocal.postConcat(toLocal);
   }

   private static void setIdentityTransforms(View view) {
      setTransforms(view, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
   }

   private static void setTransforms(View view, float translationX, float translationY, float translationZ, float scaleX, float scaleY, float rotationX, float rotationY, float rotationZ) {
      view.setTranslationX(translationX);
      view.setTranslationY(translationY);
      ViewCompat.setTranslationZ(view, translationZ);
      view.setScaleX(scaleX);
      view.setScaleY(scaleY);
      view.setRotationX(rotationX);
      view.setRotationY(rotationY);
      view.setRotation(rotationZ);
   }

   static {
      SUPPORTS_VIEW_REMOVAL_SUPPRESSION = VERSION.SDK_INT >= 21;
   }

   private static class PathAnimatorMatrix {
      private final Matrix mMatrix = new Matrix();
      private final View mView;
      private final float[] mValues;
      private float mTranslationX;
      private float mTranslationY;

      PathAnimatorMatrix(View view, float[] values) {
         this.mView = view;
         this.mValues = (float[])values.clone();
         this.mTranslationX = this.mValues[2];
         this.mTranslationY = this.mValues[5];
         this.setAnimationMatrix();
      }

      void setValues(float[] values) {
         System.arraycopy(values, 0, this.mValues, 0, values.length);
         this.setAnimationMatrix();
      }

      void setTranslation(PointF translation) {
         this.mTranslationX = translation.x;
         this.mTranslationY = translation.y;
         this.setAnimationMatrix();
      }

      private void setAnimationMatrix() {
         this.mValues[2] = this.mTranslationX;
         this.mValues[5] = this.mTranslationY;
         this.mMatrix.setValues(this.mValues);
         ViewUtils.setAnimationMatrix(this.mView, this.mMatrix);
      }

      Matrix getMatrix() {
         return this.mMatrix;
      }
   }

   private static class GhostListener extends TransitionListenerAdapter {
      private View mView;
      private GhostViewImpl mGhostView;

      GhostListener(View view, GhostViewImpl ghostView) {
         this.mView = view;
         this.mGhostView = ghostView;
      }

      public void onTransitionEnd(@NonNull Transition transition) {
         transition.removeListener(this);
         GhostViewUtils.removeGhost(this.mView);
         this.mView.setTag(id.transition_transform, (Object)null);
         this.mView.setTag(id.parent_matrix, (Object)null);
      }

      public void onTransitionPause(@NonNull Transition transition) {
         this.mGhostView.setVisibility(4);
      }

      public void onTransitionResume(@NonNull Transition transition) {
         this.mGhostView.setVisibility(0);
      }
   }

   private static class Transforms {
      final float mTranslationX;
      final float mTranslationY;
      final float mTranslationZ;
      final float mScaleX;
      final float mScaleY;
      final float mRotationX;
      final float mRotationY;
      final float mRotationZ;

      Transforms(View view) {
         this.mTranslationX = view.getTranslationX();
         this.mTranslationY = view.getTranslationY();
         this.mTranslationZ = ViewCompat.getTranslationZ(view);
         this.mScaleX = view.getScaleX();
         this.mScaleY = view.getScaleY();
         this.mRotationX = view.getRotationX();
         this.mRotationY = view.getRotationY();
         this.mRotationZ = view.getRotation();
      }

      public void restore(View view) {
         ChangeTransform.setTransforms(view, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mScaleX, this.mScaleY, this.mRotationX, this.mRotationY, this.mRotationZ);
      }

      public boolean equals(Object that) {
         if (!(that instanceof ChangeTransform.Transforms)) {
            return false;
         } else {
            ChangeTransform.Transforms thatTransform = (ChangeTransform.Transforms)that;
            return thatTransform.mTranslationX == this.mTranslationX && thatTransform.mTranslationY == this.mTranslationY && thatTransform.mTranslationZ == this.mTranslationZ && thatTransform.mScaleX == this.mScaleX && thatTransform.mScaleY == this.mScaleY && thatTransform.mRotationX == this.mRotationX && thatTransform.mRotationY == this.mRotationY && thatTransform.mRotationZ == this.mRotationZ;
         }
      }

      public int hashCode() {
         int code = this.mTranslationX != 0.0F ? Float.floatToIntBits(this.mTranslationX) : 0;
         code = 31 * code + (this.mTranslationY != 0.0F ? Float.floatToIntBits(this.mTranslationY) : 0);
         code = 31 * code + (this.mTranslationZ != 0.0F ? Float.floatToIntBits(this.mTranslationZ) : 0);
         code = 31 * code + (this.mScaleX != 0.0F ? Float.floatToIntBits(this.mScaleX) : 0);
         code = 31 * code + (this.mScaleY != 0.0F ? Float.floatToIntBits(this.mScaleY) : 0);
         code = 31 * code + (this.mRotationX != 0.0F ? Float.floatToIntBits(this.mRotationX) : 0);
         code = 31 * code + (this.mRotationY != 0.0F ? Float.floatToIntBits(this.mRotationY) : 0);
         code = 31 * code + (this.mRotationZ != 0.0F ? Float.floatToIntBits(this.mRotationZ) : 0);
         return code;
      }
   }
}
