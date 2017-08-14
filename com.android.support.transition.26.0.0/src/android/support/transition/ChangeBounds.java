package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ChangeBounds extends Transition {
   private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
   private static final String PROPNAME_CLIP = "android:changeBounds:clip";
   private static final String PROPNAME_PARENT = "android:changeBounds:parent";
   private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
   private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
   private static final String[] sTransitionProperties = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
   private static final Property DRAWABLE_ORIGIN_PROPERTY = new Property(PointF.class, "boundsOrigin") {
      private Rect mBounds = new Rect();

      public void set(Drawable object, PointF value) {
         object.copyBounds(this.mBounds);
         this.mBounds.offsetTo(Math.round(value.x), Math.round(value.y));
         object.setBounds(this.mBounds);
      }

      public PointF get(Drawable object) {
         object.copyBounds(this.mBounds);
         return new PointF((float)this.mBounds.left, (float)this.mBounds.top);
      }
   };
   private static final Property TOP_LEFT_PROPERTY = new Property(PointF.class, "topLeft") {
      public void set(ChangeBounds.ViewBounds viewBounds, PointF topLeft) {
         viewBounds.setTopLeft(topLeft);
      }

      public PointF get(ChangeBounds.ViewBounds viewBounds) {
         return null;
      }
   };
   private static final Property BOTTOM_RIGHT_PROPERTY = new Property(PointF.class, "bottomRight") {
      public void set(ChangeBounds.ViewBounds viewBounds, PointF bottomRight) {
         viewBounds.setBottomRight(bottomRight);
      }

      public PointF get(ChangeBounds.ViewBounds viewBounds) {
         return null;
      }
   };
   private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new Property(PointF.class, "bottomRight") {
      public void set(View view, PointF bottomRight) {
         int left = view.getLeft();
         int top = view.getTop();
         int right = Math.round(bottomRight.x);
         int bottom = Math.round(bottomRight.y);
         ViewUtils.setLeftTopRightBottom(view, left, top, right, bottom);
      }

      public PointF get(View view) {
         return null;
      }
   };
   private static final Property TOP_LEFT_ONLY_PROPERTY = new Property(PointF.class, "topLeft") {
      public void set(View view, PointF topLeft) {
         int left = Math.round(topLeft.x);
         int top = Math.round(topLeft.y);
         int right = view.getRight();
         int bottom = view.getBottom();
         ViewUtils.setLeftTopRightBottom(view, left, top, right, bottom);
      }

      public PointF get(View view) {
         return null;
      }
   };
   private static final Property POSITION_PROPERTY = new Property(PointF.class, "position") {
      public void set(View view, PointF topLeft) {
         int left = Math.round(topLeft.x);
         int top = Math.round(topLeft.y);
         int right = left + view.getWidth();
         int bottom = top + view.getHeight();
         ViewUtils.setLeftTopRightBottom(view, left, top, right, bottom);
      }

      public PointF get(View view) {
         return null;
      }
   };
   private int[] mTempLocation = new int[2];
   private boolean mResizeClip = false;
   private boolean mReparent = false;
   private static RectEvaluator sRectEvaluator = new RectEvaluator();

   public ChangeBounds() {
   }

   public ChangeBounds(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.CHANGE_BOUNDS);
      boolean resizeClip = TypedArrayUtils.getNamedBoolean(a, (XmlResourceParser)attrs, "resizeClip", 0, false);
      a.recycle();
      this.setResizeClip(resizeClip);
   }

   @Nullable
   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }

   public void setResizeClip(boolean resizeClip) {
      this.mResizeClip = resizeClip;
   }

   public boolean getResizeClip() {
      return this.mResizeClip;
   }

   private void captureValues(TransitionValues values) {
      View view = values.view;
      if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
         values.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
         values.values.put("android:changeBounds:parent", values.view.getParent());
         if (this.mReparent) {
            values.view.getLocationInWindow(this.mTempLocation);
            values.values.put("android:changeBounds:windowX", this.mTempLocation[0]);
            values.values.put("android:changeBounds:windowY", this.mTempLocation[1]);
         }

         if (this.mResizeClip) {
            values.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
         }
      }

   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   private boolean parentMatches(View startParent, View endParent) {
      boolean parentMatches = true;
      if (this.mReparent) {
         TransitionValues endValues = this.getMatchedTransitionValues(startParent, true);
         if (endValues == null) {
            parentMatches = startParent == endParent;
         } else {
            parentMatches = endParent == endValues.view;
         }
      }

      return parentMatches;
   }

   @Nullable
   public Animator createAnimator(@NonNull final ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
      if (startValues != null && endValues != null) {
         Map startParentVals = startValues.values;
         Map endParentVals = endValues.values;
         ViewGroup startParent = (ViewGroup)startParentVals.get("android:changeBounds:parent");
         ViewGroup endParent = (ViewGroup)endParentVals.get("android:changeBounds:parent");
         if (startParent != null && endParent != null) {
            final View view = endValues.view;
            int startLeft;
            final int endLeft;
            if (this.parentMatches(startParent, endParent)) {
               Rect startBounds = (Rect)startValues.values.get("android:changeBounds:bounds");
               Rect endBounds = (Rect)endValues.values.get("android:changeBounds:bounds");
               startLeft = startBounds.left;
               endLeft = endBounds.left;
               int startTop = startBounds.top;
               final int endTop = endBounds.top;
               int startRight = startBounds.right;
               final int endRight = endBounds.right;
               int startBottom = startBounds.bottom;
               final int endBottom = endBounds.bottom;
               int startWidth = startRight - startLeft;
               int startHeight = startBottom - startTop;
               int endWidth = endRight - endLeft;
               int endHeight = endBottom - endTop;
               Rect startClip = (Rect)startValues.values.get("android:changeBounds:clip");
               Rect endClip = (Rect)endValues.values.get("android:changeBounds:clip");
               int numChanges = 0;
               if (startWidth != 0 && startHeight != 0 || endWidth != 0 && endHeight != 0) {
                  if (startLeft != endLeft || startTop != endTop) {
                     ++numChanges;
                  }

                  if (startRight != endRight || startBottom != endBottom) {
                     ++numChanges;
                  }
               }

               if (startClip != null && !startClip.equals(endClip) || startClip == null && endClip != null) {
                  ++numChanges;
               }

               if (numChanges > 0) {
                  Object anim;
                  ObjectAnimator positionAnimator;
                  Path bottomRightPath;
                  ObjectAnimator clipAnimator;
                  if (!this.mResizeClip) {
                     ViewUtils.setLeftTopRightBottom(view, startLeft, startTop, startRight, startBottom);
                     Path topLeftPath;
                     if (numChanges == 2) {
                        if (startWidth == endWidth && startHeight == endHeight) {
                           topLeftPath = this.getPathMotion().getPath((float)startLeft, (float)startTop, (float)endLeft, (float)endTop);
                           anim = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, topLeftPath);
                        } else {
                           final ChangeBounds.ViewBounds viewBounds = new ChangeBounds.ViewBounds(view);
                           Path topLeftPath = this.getPathMotion().getPath((float)startLeft, (float)startTop, (float)endLeft, (float)endTop);
                           positionAnimator = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, topLeftPath);
                           bottomRightPath = this.getPathMotion().getPath((float)startRight, (float)startBottom, (float)endRight, (float)endBottom);
                           clipAnimator = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, bottomRightPath);
                           AnimatorSet set = new AnimatorSet();
                           set.playTogether(new Animator[]{positionAnimator, clipAnimator});
                           anim = set;
                           set.addListener(new AnimatorListenerAdapter() {
                              private ChangeBounds.ViewBounds mViewBounds = viewBounds;
                           });
                        }
                     } else if (startLeft == endLeft && startTop == endTop) {
                        topLeftPath = this.getPathMotion().getPath((float)startRight, (float)startBottom, (float)endRight, (float)endBottom);
                        anim = ObjectAnimatorUtils.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, topLeftPath);
                     } else {
                        topLeftPath = this.getPathMotion().getPath((float)startLeft, (float)startTop, (float)endLeft, (float)endTop);
                        anim = ObjectAnimatorUtils.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, topLeftPath);
                     }
                  } else {
                     int maxWidth = Math.max(startWidth, endWidth);
                     int maxHeight = Math.max(startHeight, endHeight);
                     ViewUtils.setLeftTopRightBottom(view, startLeft, startTop, startLeft + maxWidth, startTop + maxHeight);
                     positionAnimator = null;
                     if (startLeft != endLeft || startTop != endTop) {
                        bottomRightPath = this.getPathMotion().getPath((float)startLeft, (float)startTop, (float)endLeft, (float)endTop);
                        positionAnimator = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, bottomRightPath);
                     }

                     final Rect finalClip = endClip;
                     if (startClip == null) {
                        startClip = new Rect(0, 0, startWidth, startHeight);
                     }

                     if (endClip == null) {
                        endClip = new Rect(0, 0, endWidth, endHeight);
                     }

                     clipAnimator = null;
                     if (!startClip.equals(endClip)) {
                        ViewCompat.setClipBounds(view, startClip);
                        clipAnimator = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, new Object[]{startClip, endClip});
                        clipAnimator.addListener(new AnimatorListenerAdapter() {
                           private boolean mIsCanceled;

                           public void onAnimationCancel(Animator animation) {
                              this.mIsCanceled = true;
                           }

                           public void onAnimationEnd(Animator animation) {
                              if (!this.mIsCanceled) {
                                 ViewCompat.setClipBounds(view, finalClip);
                                 ViewUtils.setLeftTopRightBottom(view, endLeft, endTop, endRight, endBottom);
                              }

                           }
                        });
                     }

                     anim = TransitionUtils.mergeAnimators(positionAnimator, clipAnimator);
                  }

                  if (view.getParent() instanceof ViewGroup) {
                     final ViewGroup parent = (ViewGroup)view.getParent();
                     ViewGroupUtils.suppressLayout(parent, true);
                     Transition.TransitionListener transitionListener = new TransitionListenerAdapter() {
                        boolean mCanceled = false;

                        public void onTransitionCancel(@NonNull Transition transition) {
                           ViewGroupUtils.suppressLayout(parent, false);
                           this.mCanceled = true;
                        }

                        public void onTransitionEnd(@NonNull Transition transition) {
                           if (!this.mCanceled) {
                              ViewGroupUtils.suppressLayout(parent, false);
                           }

                           transition.removeListener(this);
                        }

                        public void onTransitionPause(@NonNull Transition transition) {
                           ViewGroupUtils.suppressLayout(parent, false);
                        }

                        public void onTransitionResume(@NonNull Transition transition) {
                           ViewGroupUtils.suppressLayout(parent, true);
                        }
                     };
                     this.addListener(transitionListener);
                  }

                  return (Animator)anim;
               }
            } else {
               int startX = ((Integer)startValues.values.get("android:changeBounds:windowX")).intValue();
               int startY = ((Integer)startValues.values.get("android:changeBounds:windowY")).intValue();
               startLeft = ((Integer)endValues.values.get("android:changeBounds:windowX")).intValue();
               endLeft = ((Integer)endValues.values.get("android:changeBounds:windowY")).intValue();
               if (startX != startLeft || startY != endLeft) {
                  sceneRoot.getLocationInWindow(this.mTempLocation);
                  Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
                  Canvas canvas = new Canvas(bitmap);
                  view.draw(canvas);
                  final BitmapDrawable drawable = new BitmapDrawable(bitmap);
                  final float transitionAlpha = ViewUtils.getTransitionAlpha(view);
                  ViewUtils.setTransitionAlpha(view, 0.0F);
                  ViewUtils.getOverlay(sceneRoot).add(drawable);
                  Path topLeftPath = this.getPathMotion().getPath((float)(startX - this.mTempLocation[0]), (float)(startY - this.mTempLocation[1]), (float)(startLeft - this.mTempLocation[0]), (float)(endLeft - this.mTempLocation[1]));
                  PropertyValuesHolder origin = PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, topLeftPath);
                  ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(drawable, new PropertyValuesHolder[]{origin});
                  anim.addListener(new AnimatorListenerAdapter() {
                     public void onAnimationEnd(Animator animation) {
                        ViewUtils.getOverlay(sceneRoot).remove(drawable);
                        ViewUtils.setTransitionAlpha(view, transitionAlpha);
                     }
                  });
                  return anim;
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static class ViewBounds {
      private int mLeft;
      private int mTop;
      private int mRight;
      private int mBottom;
      private View mView;
      private int mTopLeftCalls;
      private int mBottomRightCalls;

      ViewBounds(View view) {
         this.mView = view;
      }

      void setTopLeft(PointF topLeft) {
         this.mLeft = Math.round(topLeft.x);
         this.mTop = Math.round(topLeft.y);
         ++this.mTopLeftCalls;
         if (this.mTopLeftCalls == this.mBottomRightCalls) {
            this.setLeftTopRightBottom();
         }

      }

      void setBottomRight(PointF bottomRight) {
         this.mRight = Math.round(bottomRight.x);
         this.mBottom = Math.round(bottomRight.y);
         ++this.mBottomRightCalls;
         if (this.mTopLeftCalls == this.mBottomRightCalls) {
            this.setLeftTopRightBottom();
         }

      }

      private void setLeftTopRightBottom() {
         ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
         this.mTopLeftCalls = 0;
         this.mBottomRightCalls = 0;
      }
   }
}
