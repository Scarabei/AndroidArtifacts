package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
   private static final String LOGTAG = "AnimatedVDCompat";
   private static final String ANIMATED_VECTOR = "animated-vector";
   private static final String TARGET = "target";
   private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
   private AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState mAnimatedVectorState;
   private Context mContext;
   private android.animation.ArgbEvaluator mArgbEvaluator;
   AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
   private AnimatorListener mAnimatorListener;
   private ArrayList mAnimationCallbacks;
   final Callback mCallback;

   AnimatedVectorDrawableCompat() {
      this((Context)null, (AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState)null, (Resources)null);
   }

   private AnimatedVectorDrawableCompat(@Nullable Context context) {
      this(context, (AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState)null, (Resources)null);
   }

   private AnimatedVectorDrawableCompat(@Nullable Context context, @Nullable AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState state, @Nullable Resources res) {
      this.mArgbEvaluator = null;
      this.mAnimatorListener = null;
      this.mAnimationCallbacks = null;
      this.mCallback = new Callback() {
         public void invalidateDrawable(Drawable who) {
            AnimatedVectorDrawableCompat.this.invalidateSelf();
         }

         public void scheduleDrawable(Drawable who, Runnable what, long when) {
            AnimatedVectorDrawableCompat.this.scheduleSelf(what, when);
         }

         public void unscheduleDrawable(Drawable who, Runnable what) {
            AnimatedVectorDrawableCompat.this.unscheduleSelf(what);
         }
      };
      this.mContext = context;
      if (state != null) {
         this.mAnimatedVectorState = state;
      } else {
         this.mAnimatedVectorState = new AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState(context, state, this.mCallback, res);
      }

   }

   public Drawable mutate() {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.mutate();
      }

      return this;
   }

   @Nullable
   public static AnimatedVectorDrawableCompat create(@NonNull Context context, @DrawableRes int resId) {
      if (VERSION.SDK_INT >= 24) {
         AnimatedVectorDrawableCompat drawable = new AnimatedVectorDrawableCompat(context);
         drawable.mDelegateDrawable = ResourcesCompat.getDrawable(context.getResources(), resId, context.getTheme());
         drawable.mDelegateDrawable.setCallback(drawable.mCallback);
         drawable.mCachedConstantStateDelegate = new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState(drawable.mDelegateDrawable.getConstantState());
         return drawable;
      } else {
         Resources resources = context.getResources();

         try {
            XmlPullParser parser = resources.getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);

            int type;
            while((type = parser.next()) != 2 && type != 1) {
               ;
            }

            if (type != 2) {
               throw new XmlPullParserException("No start tag found");
            }

            return createFromXmlInner(context, context.getResources(), parser, attrs, context.getTheme());
         } catch (XmlPullParserException var6) {
            Log.e("AnimatedVDCompat", "parser error", var6);
         } catch (IOException var7) {
            Log.e("AnimatedVDCompat", "parser error", var7);
         }

         return null;
      }
   }

   public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources r, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
      AnimatedVectorDrawableCompat drawable = new AnimatedVectorDrawableCompat(context);
      drawable.inflate(r, parser, attrs, theme);
      return drawable;
   }

   public ConstantState getConstantState() {
      return this.mDelegateDrawable != null && VERSION.SDK_INT >= 24 ? new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState()) : null;
   }

   public int getChangingConfigurations() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getChangingConfigurations() : super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
   }

   public void draw(Canvas canvas) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.draw(canvas);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.draw(canvas);
         if (this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            this.invalidateSelf();
         }

      }
   }

   protected void onBoundsChange(Rect bounds) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setBounds(bounds);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setBounds(bounds);
      }
   }

   protected boolean onStateChange(int[] state) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.setState(state) : this.mAnimatedVectorState.mVectorDrawable.setState(state);
   }

   protected boolean onLevelChange(int level) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.setLevel(level) : this.mAnimatedVectorState.mVectorDrawable.setLevel(level);
   }

   public int getAlpha() {
      return this.mDelegateDrawable != null ? DrawableCompat.getAlpha(this.mDelegateDrawable) : this.mAnimatedVectorState.mVectorDrawable.getAlpha();
   }

   public void setAlpha(int alpha) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setAlpha(alpha);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setAlpha(alpha);
      }
   }

   public void setColorFilter(ColorFilter colorFilter) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setColorFilter(colorFilter);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter);
      }
   }

   public void setTint(int tint) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTint(this.mDelegateDrawable, tint);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setTint(tint);
      }
   }

   public void setTintList(ColorStateList tint) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTintList(this.mDelegateDrawable, tint);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setTintList(tint);
      }
   }

   public void setTintMode(Mode tintMode) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setTintMode(tintMode);
      }
   }

   public boolean setVisible(boolean visible, boolean restart) {
      if (this.mDelegateDrawable != null) {
         return this.mDelegateDrawable.setVisible(visible, restart);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setVisible(visible, restart);
         return super.setVisible(visible, restart);
      }
   }

   public boolean isStateful() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.isStateful() : this.mAnimatedVectorState.mVectorDrawable.isStateful();
   }

   public int getOpacity() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getOpacity() : this.mAnimatedVectorState.mVectorDrawable.getOpacity();
   }

   public int getIntrinsicWidth() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getIntrinsicWidth() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
   }

   public int getIntrinsicHeight() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getIntrinsicHeight() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
   }

   public boolean isAutoMirrored() {
      return this.mDelegateDrawable != null ? DrawableCompat.isAutoMirrored(this.mDelegateDrawable) : this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
   }

   public void setAutoMirrored(boolean mirrored) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setAutoMirrored(this.mDelegateDrawable, mirrored);
      } else {
         this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(mirrored);
      }
   }

   public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.inflate(this.mDelegateDrawable, res, parser, attrs, theme);
      } else {
         int eventType = parser.getEventType();

         for(int innerDepth = parser.getDepth() + 1; eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3); eventType = parser.next()) {
            if (eventType == 2) {
               String tagName = parser.getName();
               TypedArray a;
               if ("animated-vector".equals(tagName)) {
                  a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
                  int drawableRes = a.getResourceId(0, 0);
                  if (drawableRes != 0) {
                     VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(res, drawableRes, theme);
                     vectorDrawable.setAllowCaching(false);
                     vectorDrawable.setCallback(this.mCallback);
                     if (this.mAnimatedVectorState.mVectorDrawable != null) {
                        this.mAnimatedVectorState.mVectorDrawable.setCallback((Callback)null);
                     }

                     this.mAnimatedVectorState.mVectorDrawable = vectorDrawable;
                  }

                  a.recycle();
               } else if ("target".equals(tagName)) {
                  a = res.obtainAttributes(attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
                  String target = a.getString(0);
                  int id = a.getResourceId(1, 0);
                  if (id != 0) {
                     if (this.mContext == null) {
                        a.recycle();
                        throw new IllegalStateException("Context can't be null when inflating animators");
                     }

                     Animator objectAnimator = AnimatorInflaterCompat.loadAnimator(this.mContext, id);
                     this.setupAnimatorsForTarget(target, objectAnimator);
                  }

                  a.recycle();
               }
            }
         }

         this.mAnimatedVectorState.setupAnimatorSet();
      }
   }

   public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
      this.inflate(res, parser, attrs, (Theme)null);
   }

   public void applyTheme(Theme t) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.applyTheme(this.mDelegateDrawable, t);
      }
   }

   public boolean canApplyTheme() {
      return this.mDelegateDrawable != null ? DrawableCompat.canApplyTheme(this.mDelegateDrawable) : false;
   }

   private void setupColorAnimator(Animator animator) {
      if (animator instanceof AnimatorSet) {
         List childAnimators = ((AnimatorSet)animator).getChildAnimations();
         if (childAnimators != null) {
            for(int i = 0; i < childAnimators.size(); ++i) {
               this.setupColorAnimator((Animator)childAnimators.get(i));
            }
         }
      }

      if (animator instanceof ObjectAnimator) {
         ObjectAnimator objectAnim = (ObjectAnimator)animator;
         String propertyName = objectAnim.getPropertyName();
         if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
            if (this.mArgbEvaluator == null) {
               this.mArgbEvaluator = new android.animation.ArgbEvaluator();
            }

            objectAnim.setEvaluator(this.mArgbEvaluator);
         }
      }

   }

   private void setupAnimatorsForTarget(String name, Animator animator) {
      Object target = this.mAnimatedVectorState.mVectorDrawable.getTargetByName(name);
      animator.setTarget(target);
      if (VERSION.SDK_INT < 21) {
         this.setupColorAnimator(animator);
      }

      if (this.mAnimatedVectorState.mAnimators == null) {
         this.mAnimatedVectorState.mAnimators = new ArrayList();
         this.mAnimatedVectorState.mTargetNameMap = new ArrayMap();
      }

      this.mAnimatedVectorState.mAnimators.add(animator);
      this.mAnimatedVectorState.mTargetNameMap.put(animator, name);
   }

   public boolean isRunning() {
      return this.mDelegateDrawable != null ? ((AnimatedVectorDrawable)this.mDelegateDrawable).isRunning() : this.mAnimatedVectorState.mAnimatorSet.isRunning();
   }

   public void start() {
      if (this.mDelegateDrawable != null) {
         ((AnimatedVectorDrawable)this.mDelegateDrawable).start();
      } else if (!this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
         this.mAnimatedVectorState.mAnimatorSet.start();
         this.invalidateSelf();
      }
   }

   public void stop() {
      if (this.mDelegateDrawable != null) {
         ((AnimatedVectorDrawable)this.mDelegateDrawable).stop();
      } else {
         this.mAnimatedVectorState.mAnimatorSet.end();
      }
   }

   @RequiresApi(23)
   private static boolean unregisterPlatformCallback(AnimatedVectorDrawable dr, Animatable2Compat.AnimationCallback callback) {
      return dr.unregisterAnimationCallback(callback.getPlatformCallback());
   }

   public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback callback) {
      if (this.mDelegateDrawable != null) {
         registerPlatformCallback((AnimatedVectorDrawable)this.mDelegateDrawable, callback);
      } else if (callback != null) {
         if (this.mAnimationCallbacks == null) {
            this.mAnimationCallbacks = new ArrayList();
         }

         if (!this.mAnimationCallbacks.contains(callback)) {
            this.mAnimationCallbacks.add(callback);
            if (this.mAnimatorListener == null) {
               this.mAnimatorListener = new AnimatorListenerAdapter() {
                  public void onAnimationStart(Animator animation) {
                     ArrayList tmpCallbacks = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                     int size = tmpCallbacks.size();

                     for(int i = 0; i < size; ++i) {
                        ((Animatable2Compat.AnimationCallback)tmpCallbacks.get(i)).onAnimationStart(AnimatedVectorDrawableCompat.this);
                     }

                  }

                  public void onAnimationEnd(Animator animation) {
                     ArrayList tmpCallbacks = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                     int size = tmpCallbacks.size();

                     for(int i = 0; i < size; ++i) {
                        ((Animatable2Compat.AnimationCallback)tmpCallbacks.get(i)).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                     }

                  }
               };
            }

            this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
         }
      }
   }

   @RequiresApi(23)
   private static void registerPlatformCallback(@NonNull AnimatedVectorDrawable avd, @NonNull Animatable2Compat.AnimationCallback callback) {
      avd.registerAnimationCallback(callback.getPlatformCallback());
   }

   private void removeAnimatorSetListener() {
      if (this.mAnimatorListener != null) {
         this.mAnimatedVectorState.mAnimatorSet.removeListener(this.mAnimatorListener);
         this.mAnimatorListener = null;
      }

   }

   public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback callback) {
      if (this.mDelegateDrawable != null) {
         unregisterPlatformCallback((AnimatedVectorDrawable)this.mDelegateDrawable, callback);
      }

      if (this.mAnimationCallbacks != null && callback != null) {
         boolean removed = this.mAnimationCallbacks.remove(callback);
         if (this.mAnimationCallbacks.size() == 0) {
            this.removeAnimatorSetListener();
         }

         return removed;
      } else {
         return false;
      }
   }

   public void clearAnimationCallbacks() {
      if (this.mDelegateDrawable != null) {
         ((AnimatedVectorDrawable)this.mDelegateDrawable).clearAnimationCallbacks();
      } else {
         this.removeAnimatorSetListener();
         if (this.mAnimationCallbacks != null) {
            this.mAnimationCallbacks.clear();
         }
      }
   }

   public static void registerAnimationCallback(Drawable dr, Animatable2Compat.AnimationCallback callback) {
      if (dr != null && callback != null) {
         if (dr instanceof Animatable) {
            if (VERSION.SDK_INT >= 24) {
               registerPlatformCallback((AnimatedVectorDrawable)dr, callback);
            } else {
               ((AnimatedVectorDrawableCompat)dr).registerAnimationCallback(callback);
            }

         }
      }
   }

   public static boolean unregisterAnimationCallback(Drawable dr, Animatable2Compat.AnimationCallback callback) {
      if (dr != null && callback != null) {
         if (!(dr instanceof Animatable)) {
            return false;
         } else {
            return VERSION.SDK_INT >= 24 ? unregisterPlatformCallback((AnimatedVectorDrawable)dr, callback) : ((AnimatedVectorDrawableCompat)dr).unregisterAnimationCallback(callback);
         }
      } else {
         return false;
      }
   }

   public static void clearAnimationCallbacks(Drawable dr) {
      if (dr != null && dr instanceof Animatable) {
         if (VERSION.SDK_INT >= 24) {
            ((AnimatedVectorDrawable)dr).clearAnimationCallbacks();
         } else {
            ((AnimatedVectorDrawableCompat)dr).clearAnimationCallbacks();
         }

      }
   }

   private static class AnimatedVectorDrawableCompatState extends ConstantState {
      int mChangingConfigurations;
      VectorDrawableCompat mVectorDrawable;
      AnimatorSet mAnimatorSet;
      private ArrayList mAnimators;
      ArrayMap mTargetNameMap;

      public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState copy, Callback owner, Resources res) {
         if (copy != null) {
            this.mChangingConfigurations = copy.mChangingConfigurations;
            if (copy.mVectorDrawable != null) {
               ConstantState cs = copy.mVectorDrawable.getConstantState();
               if (res != null) {
                  this.mVectorDrawable = (VectorDrawableCompat)cs.newDrawable(res);
               } else {
                  this.mVectorDrawable = (VectorDrawableCompat)cs.newDrawable();
               }

               this.mVectorDrawable = (VectorDrawableCompat)this.mVectorDrawable.mutate();
               this.mVectorDrawable.setCallback(owner);
               this.mVectorDrawable.setBounds(copy.mVectorDrawable.getBounds());
               this.mVectorDrawable.setAllowCaching(false);
            }

            if (copy.mAnimators != null) {
               int numAnimators = copy.mAnimators.size();
               this.mAnimators = new ArrayList(numAnimators);
               this.mTargetNameMap = new ArrayMap(numAnimators);

               for(int i = 0; i < numAnimators; ++i) {
                  Animator anim = (Animator)copy.mAnimators.get(i);
                  Animator animClone = anim.clone();
                  String targetName = (String)copy.mTargetNameMap.get(anim);
                  Object targetObject = this.mVectorDrawable.getTargetByName(targetName);
                  animClone.setTarget(targetObject);
                  this.mAnimators.add(animClone);
                  this.mTargetNameMap.put(animClone, targetName);
               }

               this.setupAnimatorSet();
            }
         }

      }

      public Drawable newDrawable() {
         throw new IllegalStateException("No constant state support for SDK < 24.");
      }

      public Drawable newDrawable(Resources res) {
         throw new IllegalStateException("No constant state support for SDK < 24.");
      }

      public int getChangingConfigurations() {
         return this.mChangingConfigurations;
      }

      public void setupAnimatorSet() {
         if (this.mAnimatorSet == null) {
            this.mAnimatorSet = new AnimatorSet();
         }

         this.mAnimatorSet.playTogether(this.mAnimators);
      }
   }

   @RequiresApi(24)
   private static class AnimatedVectorDrawableDelegateState extends ConstantState {
      private final ConstantState mDelegateState;

      public AnimatedVectorDrawableDelegateState(ConstantState state) {
         this.mDelegateState = state;
      }

      public Drawable newDrawable() {
         AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
         drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
         drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
         return drawableCompat;
      }

      public Drawable newDrawable(Resources res) {
         AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
         drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res);
         drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
         return drawableCompat;
      }

      public Drawable newDrawable(Resources res, Theme theme) {
         AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
         drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res, theme);
         drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
         return drawableCompat;
      }

      public boolean canApplyTheme() {
         return this.mDelegateState.canApplyTheme();
      }

      public int getChangingConfigurations() {
         return this.mDelegateState.getChangingConfigurations();
      }
   }
}
