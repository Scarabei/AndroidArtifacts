package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Transition implements Cloneable {
   private static final String LOG_TAG = "Transition";
   static final boolean DBG = false;
   public static final int MATCH_INSTANCE = 1;
   private static final int MATCH_FIRST = 1;
   public static final int MATCH_NAME = 2;
   public static final int MATCH_ID = 3;
   public static final int MATCH_ITEM_ID = 4;
   private static final int MATCH_LAST = 4;
   private static final String MATCH_INSTANCE_STR = "instance";
   private static final String MATCH_NAME_STR = "name";
   private static final String MATCH_ID_STR = "id";
   private static final String MATCH_ITEM_ID_STR = "itemId";
   private static final int[] DEFAULT_MATCH_ORDER = new int[]{2, 1, 3, 4};
   private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {
      public Path getPath(float startX, float startY, float endX, float endY) {
         Path path = new Path();
         path.moveTo(startX, startY);
         path.lineTo(endX, endY);
         return path;
      }
   };
   private String mName = this.getClass().getName();
   private long mStartDelay = -1L;
   long mDuration = -1L;
   private TimeInterpolator mInterpolator = null;
   ArrayList mTargetIds = new ArrayList();
   ArrayList mTargets = new ArrayList();
   private ArrayList mTargetNames = null;
   private ArrayList mTargetTypes = null;
   private ArrayList mTargetIdExcludes = null;
   private ArrayList mTargetExcludes = null;
   private ArrayList mTargetTypeExcludes = null;
   private ArrayList mTargetNameExcludes = null;
   private ArrayList mTargetIdChildExcludes = null;
   private ArrayList mTargetChildExcludes = null;
   private ArrayList mTargetTypeChildExcludes = null;
   private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
   private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
   TransitionSet mParent = null;
   private int[] mMatchOrder;
   private ArrayList mStartValuesList;
   private ArrayList mEndValuesList;
   private static ThreadLocal sRunningAnimators = new ThreadLocal();
   private ViewGroup mSceneRoot;
   boolean mCanRemoveViews;
   private ArrayList mCurrentAnimators;
   private int mNumInstances;
   private boolean mPaused;
   private boolean mEnded;
   private ArrayList mListeners;
   private ArrayList mAnimators;
   TransitionPropagation mPropagation;
   private Transition.EpicenterCallback mEpicenterCallback;
   private ArrayMap mNameOverrides;
   private PathMotion mPathMotion;

   public Transition() {
      this.mMatchOrder = DEFAULT_MATCH_ORDER;
      this.mSceneRoot = null;
      this.mCanRemoveViews = false;
      this.mCurrentAnimators = new ArrayList();
      this.mNumInstances = 0;
      this.mPaused = false;
      this.mEnded = false;
      this.mListeners = null;
      this.mAnimators = new ArrayList();
      this.mPathMotion = STRAIGHT_PATH_MOTION;
   }

   public Transition(Context context, AttributeSet attrs) {
      this.mMatchOrder = DEFAULT_MATCH_ORDER;
      this.mSceneRoot = null;
      this.mCanRemoveViews = false;
      this.mCurrentAnimators = new ArrayList();
      this.mNumInstances = 0;
      this.mPaused = false;
      this.mEnded = false;
      this.mListeners = null;
      this.mAnimators = new ArrayList();
      this.mPathMotion = STRAIGHT_PATH_MOTION;
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.TRANSITION);
      XmlResourceParser parser = (XmlResourceParser)attrs;
      long duration = (long)TypedArrayUtils.getNamedInt(a, parser, "duration", 1, -1);
      if (duration >= 0L) {
         this.setDuration(duration);
      }

      long startDelay = (long)TypedArrayUtils.getNamedInt(a, parser, "startDelay", 2, -1);
      if (startDelay > 0L) {
         this.setStartDelay(startDelay);
      }

      int resId = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 0, 0);
      if (resId > 0) {
         this.setInterpolator(AnimationUtils.loadInterpolator(context, resId));
      }

      String matchOrder = TypedArrayUtils.getNamedString(a, parser, "matchOrder", 3);
      if (matchOrder != null) {
         this.setMatchOrder(parseMatchOrder(matchOrder));
      }

      a.recycle();
   }

   private static int[] parseMatchOrder(String matchOrderString) {
      StringTokenizer st = new StringTokenizer(matchOrderString, ",");
      int[] matches = new int[st.countTokens()];

      for(int index = 0; st.hasMoreTokens(); ++index) {
         String token = st.nextToken().trim();
         if ("id".equalsIgnoreCase(token)) {
            matches[index] = 3;
         } else if ("instance".equalsIgnoreCase(token)) {
            matches[index] = 1;
         } else if ("name".equalsIgnoreCase(token)) {
            matches[index] = 2;
         } else if ("itemId".equalsIgnoreCase(token)) {
            matches[index] = 4;
         } else {
            if (!token.isEmpty()) {
               throw new InflateException("Unknown match type in matchOrder: '" + token + "'");
            }

            int[] smallerMatches = new int[matches.length - 1];
            System.arraycopy(matches, 0, smallerMatches, 0, index);
            matches = smallerMatches;
            --index;
         }
      }

      return matches;
   }

   @NonNull
   public Transition setDuration(long duration) {
      this.mDuration = duration;
      return this;
   }

   public long getDuration() {
      return this.mDuration;
   }

   @NonNull
   public Transition setStartDelay(long startDelay) {
      this.mStartDelay = startDelay;
      return this;
   }

   public long getStartDelay() {
      return this.mStartDelay;
   }

   @NonNull
   public Transition setInterpolator(@Nullable TimeInterpolator interpolator) {
      this.mInterpolator = interpolator;
      return this;
   }

   @Nullable
   public TimeInterpolator getInterpolator() {
      return this.mInterpolator;
   }

   @Nullable
   public String[] getTransitionProperties() {
      return null;
   }

   @Nullable
   public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
      return null;
   }

   public void setMatchOrder(int... matches) {
      if (matches != null && matches.length != 0) {
         for(int i = 0; i < matches.length; ++i) {
            int match = matches[i];
            if (!isValidMatch(match)) {
               throw new IllegalArgumentException("matches contains invalid value");
            }

            if (alreadyContains(matches, i)) {
               throw new IllegalArgumentException("matches contains a duplicate value");
            }
         }

         this.mMatchOrder = (int[])matches.clone();
      } else {
         this.mMatchOrder = DEFAULT_MATCH_ORDER;
      }

   }

   private static boolean isValidMatch(int match) {
      return match >= 1 && match <= 4;
   }

   private static boolean alreadyContains(int[] array, int searchIndex) {
      int value = array[searchIndex];

      for(int i = 0; i < searchIndex; ++i) {
         if (array[i] == value) {
            return true;
         }
      }

      return false;
   }

   private void matchInstances(ArrayMap unmatchedStart, ArrayMap unmatchedEnd) {
      for(int i = unmatchedStart.size() - 1; i >= 0; --i) {
         View view = (View)unmatchedStart.keyAt(i);
         if (view != null && this.isValidTarget(view)) {
            TransitionValues end = (TransitionValues)unmatchedEnd.remove(view);
            if (end != null && end.view != null && this.isValidTarget(end.view)) {
               TransitionValues start = (TransitionValues)unmatchedStart.removeAt(i);
               this.mStartValuesList.add(start);
               this.mEndValuesList.add(end);
            }
         }
      }

   }

   private void matchItemIds(ArrayMap unmatchedStart, ArrayMap unmatchedEnd, LongSparseArray startItemIds, LongSparseArray endItemIds) {
      int numStartIds = startItemIds.size();

      for(int i = 0; i < numStartIds; ++i) {
         View startView = (View)startItemIds.valueAt(i);
         if (startView != null && this.isValidTarget(startView)) {
            View endView = (View)endItemIds.get(startItemIds.keyAt(i));
            if (endView != null && this.isValidTarget(endView)) {
               TransitionValues startValues = (TransitionValues)unmatchedStart.get(startView);
               TransitionValues endValues = (TransitionValues)unmatchedEnd.get(endView);
               if (startValues != null && endValues != null) {
                  this.mStartValuesList.add(startValues);
                  this.mEndValuesList.add(endValues);
                  unmatchedStart.remove(startView);
                  unmatchedEnd.remove(endView);
               }
            }
         }
      }

   }

   private void matchIds(ArrayMap unmatchedStart, ArrayMap unmatchedEnd, SparseArray startIds, SparseArray endIds) {
      int numStartIds = startIds.size();

      for(int i = 0; i < numStartIds; ++i) {
         View startView = (View)startIds.valueAt(i);
         if (startView != null && this.isValidTarget(startView)) {
            View endView = (View)endIds.get(startIds.keyAt(i));
            if (endView != null && this.isValidTarget(endView)) {
               TransitionValues startValues = (TransitionValues)unmatchedStart.get(startView);
               TransitionValues endValues = (TransitionValues)unmatchedEnd.get(endView);
               if (startValues != null && endValues != null) {
                  this.mStartValuesList.add(startValues);
                  this.mEndValuesList.add(endValues);
                  unmatchedStart.remove(startView);
                  unmatchedEnd.remove(endView);
               }
            }
         }
      }

   }

   private void matchNames(ArrayMap unmatchedStart, ArrayMap unmatchedEnd, ArrayMap startNames, ArrayMap endNames) {
      int numStartNames = startNames.size();

      for(int i = 0; i < numStartNames; ++i) {
         View startView = (View)startNames.valueAt(i);
         if (startView != null && this.isValidTarget(startView)) {
            View endView = (View)endNames.get(startNames.keyAt(i));
            if (endView != null && this.isValidTarget(endView)) {
               TransitionValues startValues = (TransitionValues)unmatchedStart.get(startView);
               TransitionValues endValues = (TransitionValues)unmatchedEnd.get(endView);
               if (startValues != null && endValues != null) {
                  this.mStartValuesList.add(startValues);
                  this.mEndValuesList.add(endValues);
                  unmatchedStart.remove(startView);
                  unmatchedEnd.remove(endView);
               }
            }
         }
      }

   }

   private void addUnmatched(ArrayMap unmatchedStart, ArrayMap unmatchedEnd) {
      int i;
      TransitionValues end;
      for(i = 0; i < unmatchedStart.size(); ++i) {
         end = (TransitionValues)unmatchedStart.valueAt(i);
         if (this.isValidTarget(end.view)) {
            this.mStartValuesList.add(end);
            this.mEndValuesList.add((Object)null);
         }
      }

      for(i = 0; i < unmatchedEnd.size(); ++i) {
         end = (TransitionValues)unmatchedEnd.valueAt(i);
         if (this.isValidTarget(end.view)) {
            this.mEndValuesList.add(end);
            this.mStartValuesList.add((Object)null);
         }
      }

   }

   private void matchStartAndEnd(TransitionValuesMaps startValues, TransitionValuesMaps endValues) {
      ArrayMap unmatchedStart = new ArrayMap(startValues.mViewValues);
      ArrayMap unmatchedEnd = new ArrayMap(endValues.mViewValues);

      for(int i = 0; i < this.mMatchOrder.length; ++i) {
         switch(this.mMatchOrder[i]) {
         case 1:
            this.matchInstances(unmatchedStart, unmatchedEnd);
            break;
         case 2:
            this.matchNames(unmatchedStart, unmatchedEnd, startValues.mNameValues, endValues.mNameValues);
            break;
         case 3:
            this.matchIds(unmatchedStart, unmatchedEnd, startValues.mIdValues, endValues.mIdValues);
            break;
         case 4:
            this.matchItemIds(unmatchedStart, unmatchedEnd, startValues.mItemIdValues, endValues.mItemIdValues);
         }
      }

      this.addUnmatched(unmatchedStart, unmatchedEnd);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void createAnimators(ViewGroup sceneRoot, TransitionValuesMaps startValues, TransitionValuesMaps endValues, ArrayList startValuesList, ArrayList endValuesList) {
      ArrayMap runningAnimators = getRunningAnimators();
      long minStartDelay = Long.MAX_VALUE;
      SparseIntArray startDelays = new SparseIntArray();
      int startValuesListCount = startValuesList.size();

      int i;
      for(i = 0; i < startValuesListCount; ++i) {
         TransitionValues start = (TransitionValues)startValuesList.get(i);
         TransitionValues end = (TransitionValues)endValuesList.get(i);
         if (start != null && !start.mTargetedTransitions.contains(this)) {
            start = null;
         }

         if (end != null && !end.mTargetedTransitions.contains(this)) {
            end = null;
         }

         if (start != null || end != null) {
            boolean isChanged = start == null || end == null || this.isTransitionRequired(start, end);
            if (isChanged) {
               Animator animator = this.createAnimator(sceneRoot, start, end);
               if (animator != null) {
                  TransitionValues infoValues = null;
                  View view;
                  if (end != null) {
                     view = end.view;
                     String[] properties = this.getTransitionProperties();
                     if (view != null && properties != null && properties.length > 0) {
                        infoValues = new TransitionValues();
                        infoValues.view = view;
                        TransitionValues newValues = (TransitionValues)endValues.mViewValues.get(view);
                        int numExistingAnims;
                        if (newValues != null) {
                           for(numExistingAnims = 0; numExistingAnims < properties.length; ++numExistingAnims) {
                              infoValues.values.put(properties[numExistingAnims], newValues.values.get(properties[numExistingAnims]));
                           }
                        }

                        numExistingAnims = runningAnimators.size();

                        for(int j = 0; j < numExistingAnims; ++j) {
                           Animator anim = (Animator)runningAnimators.keyAt(j);
                           Transition.AnimationInfo info = (Transition.AnimationInfo)runningAnimators.get(anim);
                           if (info.mValues != null && info.mView == view && info.mName.equals(this.getName()) && info.mValues.equals(infoValues)) {
                              animator = null;
                              break;
                           }
                        }
                     }
                  } else {
                     view = start.view;
                  }

                  if (animator != null) {
                     if (this.mPropagation != null) {
                        long delay = this.mPropagation.getStartDelay(sceneRoot, this, start, end);
                        startDelays.put(this.mAnimators.size(), (int)delay);
                        minStartDelay = Math.min(delay, minStartDelay);
                     }

                     Transition.AnimationInfo info = new Transition.AnimationInfo(view, this.getName(), this, ViewUtils.getWindowId(sceneRoot), infoValues);
                     runningAnimators.put(animator, info);
                     this.mAnimators.add(animator);
                  }
               }
            }
         }
      }

      if (minStartDelay != 0L) {
         for(i = 0; i < startDelays.size(); ++i) {
            int index = startDelays.keyAt(i);
            Animator animator = (Animator)this.mAnimators.get(index);
            long delay = (long)startDelays.valueAt(i) - minStartDelay + animator.getStartDelay();
            animator.setStartDelay(delay);
         }
      }

   }

   boolean isValidTarget(View target) {
      int targetId = target.getId();
      if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(targetId)) {
         return false;
      } else if (this.mTargetExcludes != null && this.mTargetExcludes.contains(target)) {
         return false;
      } else {
         int i;
         if (this.mTargetTypeExcludes != null) {
            i = this.mTargetTypeExcludes.size();

            for(int i = 0; i < i; ++i) {
               Class type = (Class)this.mTargetTypeExcludes.get(i);
               if (type.isInstance(target)) {
                  return false;
               }
            }
         }

         if (this.mTargetNameExcludes != null && ViewCompat.getTransitionName(target) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(target))) {
            return false;
         } else if (this.mTargetIds.size() != 0 || this.mTargets.size() != 0 || this.mTargetTypes != null && !this.mTargetTypes.isEmpty() || this.mTargetNames != null && !this.mTargetNames.isEmpty()) {
            if (!this.mTargetIds.contains(targetId) && !this.mTargets.contains(target)) {
               if (this.mTargetNames != null && this.mTargetNames.contains(ViewCompat.getTransitionName(target))) {
                  return true;
               } else {
                  if (this.mTargetTypes != null) {
                     for(i = 0; i < this.mTargetTypes.size(); ++i) {
                        if (((Class)this.mTargetTypes.get(i)).isInstance(target)) {
                           return true;
                        }
                     }
                  }

                  return false;
               }
            } else {
               return true;
            }
         } else {
            return true;
         }
      }
   }

   private static ArrayMap getRunningAnimators() {
      ArrayMap runningAnimators = (ArrayMap)sRunningAnimators.get();
      if (runningAnimators == null) {
         runningAnimators = new ArrayMap();
         sRunningAnimators.set(runningAnimators);
      }

      return runningAnimators;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void runAnimators() {
      this.start();
      ArrayMap runningAnimators = getRunningAnimators();
      Iterator var2 = this.mAnimators.iterator();

      while(var2.hasNext()) {
         Animator anim = (Animator)var2.next();
         if (runningAnimators.containsKey(anim)) {
            this.start();
            this.runAnimator(anim, runningAnimators);
         }
      }

      this.mAnimators.clear();
      this.end();
   }

   private void runAnimator(Animator animator, final ArrayMap runningAnimators) {
      if (animator != null) {
         animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animation) {
               Transition.this.mCurrentAnimators.add(animation);
            }

            public void onAnimationEnd(Animator animation) {
               runningAnimators.remove(animation);
               Transition.this.mCurrentAnimators.remove(animation);
            }
         });
         this.animate(animator);
      }

   }

   public abstract void captureStartValues(@NonNull TransitionValues var1);

   public abstract void captureEndValues(@NonNull TransitionValues var1);

   @NonNull
   public Transition addTarget(@NonNull View target) {
      this.mTargets.add(target);
      return this;
   }

   @NonNull
   public Transition addTarget(@IdRes int targetId) {
      if (targetId > 0) {
         this.mTargetIds.add(targetId);
      }

      return this;
   }

   @NonNull
   public Transition addTarget(@NonNull String targetName) {
      if (this.mTargetNames == null) {
         this.mTargetNames = new ArrayList();
      }

      this.mTargetNames.add(targetName);
      return this;
   }

   @NonNull
   public Transition addTarget(@NonNull Class targetType) {
      if (this.mTargetTypes == null) {
         this.mTargetTypes = new ArrayList();
      }

      this.mTargetTypes.add(targetType);
      return this;
   }

   @NonNull
   public Transition removeTarget(@NonNull View target) {
      this.mTargets.remove(target);
      return this;
   }

   @NonNull
   public Transition removeTarget(@IdRes int targetId) {
      if (targetId > 0) {
         this.mTargetIds.remove(targetId);
      }

      return this;
   }

   @NonNull
   public Transition removeTarget(@NonNull String targetName) {
      if (this.mTargetNames != null) {
         this.mTargetNames.remove(targetName);
      }

      return this;
   }

   @NonNull
   public Transition removeTarget(@NonNull Class target) {
      if (this.mTargetTypes != null) {
         this.mTargetTypes.remove(target);
      }

      return this;
   }

   private static ArrayList excludeObject(ArrayList list, Object target, boolean exclude) {
      if (target != null) {
         if (exclude) {
            list = Transition.ArrayListManager.add(list, target);
         } else {
            list = Transition.ArrayListManager.remove(list, target);
         }
      }

      return list;
   }

   @NonNull
   public Transition excludeTarget(@NonNull View target, boolean exclude) {
      this.mTargetExcludes = this.excludeView(this.mTargetExcludes, target, exclude);
      return this;
   }

   @NonNull
   public Transition excludeTarget(@IdRes int targetId, boolean exclude) {
      this.mTargetIdExcludes = this.excludeId(this.mTargetIdExcludes, targetId, exclude);
      return this;
   }

   @NonNull
   public Transition excludeTarget(@NonNull String targetName, boolean exclude) {
      this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, targetName, exclude);
      return this;
   }

   @NonNull
   public Transition excludeChildren(@NonNull View target, boolean exclude) {
      this.mTargetChildExcludes = this.excludeView(this.mTargetChildExcludes, target, exclude);
      return this;
   }

   @NonNull
   public Transition excludeChildren(@IdRes int targetId, boolean exclude) {
      this.mTargetIdChildExcludes = this.excludeId(this.mTargetIdChildExcludes, targetId, exclude);
      return this;
   }

   private ArrayList excludeId(ArrayList list, int targetId, boolean exclude) {
      if (targetId > 0) {
         if (exclude) {
            list = Transition.ArrayListManager.add(list, targetId);
         } else {
            list = Transition.ArrayListManager.remove(list, targetId);
         }
      }

      return list;
   }

   private ArrayList excludeView(ArrayList list, View target, boolean exclude) {
      if (target != null) {
         if (exclude) {
            list = Transition.ArrayListManager.add(list, target);
         } else {
            list = Transition.ArrayListManager.remove(list, target);
         }
      }

      return list;
   }

   @NonNull
   public Transition excludeTarget(@NonNull Class type, boolean exclude) {
      this.mTargetTypeExcludes = this.excludeType(this.mTargetTypeExcludes, type, exclude);
      return this;
   }

   @NonNull
   public Transition excludeChildren(@NonNull Class type, boolean exclude) {
      this.mTargetTypeChildExcludes = this.excludeType(this.mTargetTypeChildExcludes, type, exclude);
      return this;
   }

   private ArrayList excludeType(ArrayList list, Class type, boolean exclude) {
      if (type != null) {
         if (exclude) {
            list = Transition.ArrayListManager.add(list, type);
         } else {
            list = Transition.ArrayListManager.remove(list, type);
         }
      }

      return list;
   }

   @NonNull
   public List getTargetIds() {
      return this.mTargetIds;
   }

   @NonNull
   public List getTargets() {
      return this.mTargets;
   }

   @Nullable
   public List getTargetNames() {
      return this.mTargetNames;
   }

   @Nullable
   public List getTargetTypes() {
      return this.mTargetTypes;
   }

   void captureValues(ViewGroup sceneRoot, boolean start) {
      this.clearValues(start);
      int numOverrides;
      if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (this.mTargetNames == null || this.mTargetNames.isEmpty()) && (this.mTargetTypes == null || this.mTargetTypes.isEmpty())) {
         for(numOverrides = 0; numOverrides < this.mTargetIds.size(); ++numOverrides) {
            int id = ((Integer)this.mTargetIds.get(numOverrides)).intValue();
            View view = sceneRoot.findViewById(id);
            if (view != null) {
               TransitionValues values = new TransitionValues();
               values.view = view;
               if (start) {
                  this.captureStartValues(values);
               } else {
                  this.captureEndValues(values);
               }

               values.mTargetedTransitions.add(this);
               this.capturePropagationValues(values);
               if (start) {
                  addViewValues(this.mStartValues, view, values);
               } else {
                  addViewValues(this.mEndValues, view, values);
               }
            }
         }

         for(numOverrides = 0; numOverrides < this.mTargets.size(); ++numOverrides) {
            View view = (View)this.mTargets.get(numOverrides);
            TransitionValues values = new TransitionValues();
            values.view = view;
            if (start) {
               this.captureStartValues(values);
            } else {
               this.captureEndValues(values);
            }

            values.mTargetedTransitions.add(this);
            this.capturePropagationValues(values);
            if (start) {
               addViewValues(this.mStartValues, view, values);
            } else {
               addViewValues(this.mEndValues, view, values);
            }
         }
      } else {
         this.captureHierarchy(sceneRoot, start);
      }

      if (!start && this.mNameOverrides != null) {
         numOverrides = this.mNameOverrides.size();
         ArrayList overriddenViews = new ArrayList(numOverrides);

         int i;
         for(i = 0; i < numOverrides; ++i) {
            String fromName = (String)this.mNameOverrides.keyAt(i);
            overriddenViews.add(this.mStartValues.mNameValues.remove(fromName));
         }

         for(i = 0; i < numOverrides; ++i) {
            View view = (View)overriddenViews.get(i);
            if (view != null) {
               String toName = (String)this.mNameOverrides.valueAt(i);
               this.mStartValues.mNameValues.put(toName, view);
            }
         }
      }

   }

   private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
      transitionValuesMaps.mViewValues.put(view, transitionValues);
      int id = view.getId();
      if (id >= 0) {
         if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
            transitionValuesMaps.mIdValues.put(id, (Object)null);
         } else {
            transitionValuesMaps.mIdValues.put(id, view);
         }
      }

      String name = ViewCompat.getTransitionName(view);
      if (name != null) {
         if (transitionValuesMaps.mNameValues.containsKey(name)) {
            transitionValuesMaps.mNameValues.put(name, (Object)null);
         } else {
            transitionValuesMaps.mNameValues.put(name, view);
         }
      }

      if (view.getParent() instanceof ListView) {
         ListView listview = (ListView)view.getParent();
         if (listview.getAdapter().hasStableIds()) {
            int position = listview.getPositionForView(view);
            long itemId = listview.getItemIdAtPosition(position);
            if (transitionValuesMaps.mItemIdValues.indexOfKey(itemId) >= 0) {
               View alreadyMatched = (View)transitionValuesMaps.mItemIdValues.get(itemId);
               if (alreadyMatched != null) {
                  ViewCompat.setHasTransientState(alreadyMatched, false);
                  transitionValuesMaps.mItemIdValues.put(itemId, (Object)null);
               }
            } else {
               ViewCompat.setHasTransientState(view, true);
               transitionValuesMaps.mItemIdValues.put(itemId, view);
            }
         }
      }

   }

   void clearValues(boolean start) {
      if (start) {
         this.mStartValues.mViewValues.clear();
         this.mStartValues.mIdValues.clear();
         this.mStartValues.mItemIdValues.clear();
      } else {
         this.mEndValues.mViewValues.clear();
         this.mEndValues.mIdValues.clear();
         this.mEndValues.mItemIdValues.clear();
      }

   }

   private void captureHierarchy(View view, boolean start) {
      if (view != null) {
         int id = view.getId();
         if (this.mTargetIdExcludes == null || !this.mTargetIdExcludes.contains(id)) {
            if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view)) {
               int numTypes;
               int i;
               if (this.mTargetTypeExcludes != null) {
                  numTypes = this.mTargetTypeExcludes.size();

                  for(i = 0; i < numTypes; ++i) {
                     if (((Class)this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                        return;
                     }
                  }
               }

               if (view.getParent() instanceof ViewGroup) {
                  TransitionValues values = new TransitionValues();
                  values.view = view;
                  if (start) {
                     this.captureStartValues(values);
                  } else {
                     this.captureEndValues(values);
                  }

                  values.mTargetedTransitions.add(this);
                  this.capturePropagationValues(values);
                  if (start) {
                     addViewValues(this.mStartValues, view, values);
                  } else {
                     addViewValues(this.mEndValues, view, values);
                  }
               }

               if (view instanceof ViewGroup) {
                  if (this.mTargetIdChildExcludes != null && this.mTargetIdChildExcludes.contains(id)) {
                     return;
                  }

                  if (this.mTargetChildExcludes != null && this.mTargetChildExcludes.contains(view)) {
                     return;
                  }

                  if (this.mTargetTypeChildExcludes != null) {
                     numTypes = this.mTargetTypeChildExcludes.size();

                     for(i = 0; i < numTypes; ++i) {
                        if (((Class)this.mTargetTypeChildExcludes.get(i)).isInstance(view)) {
                           return;
                        }
                     }
                  }

                  ViewGroup parent = (ViewGroup)view;

                  for(i = 0; i < parent.getChildCount(); ++i) {
                     this.captureHierarchy(parent.getChildAt(i), start);
                  }
               }

            }
         }
      }
   }

   @Nullable
   public TransitionValues getTransitionValues(@NonNull View view, boolean start) {
      if (this.mParent != null) {
         return this.mParent.getTransitionValues(view, start);
      } else {
         TransitionValuesMaps valuesMaps = start ? this.mStartValues : this.mEndValues;
         return (TransitionValues)valuesMaps.mViewValues.get(view);
      }
   }

   TransitionValues getMatchedTransitionValues(View view, boolean viewInStart) {
      if (this.mParent != null) {
         return this.mParent.getMatchedTransitionValues(view, viewInStart);
      } else {
         ArrayList lookIn = viewInStart ? this.mStartValuesList : this.mEndValuesList;
         if (lookIn == null) {
            return null;
         } else {
            int count = lookIn.size();
            int index = -1;

            for(int i = 0; i < count; ++i) {
               TransitionValues values = (TransitionValues)lookIn.get(i);
               if (values == null) {
                  return null;
               }

               if (values.view == view) {
                  index = i;
                  break;
               }
            }

            TransitionValues values = null;
            if (index >= 0) {
               ArrayList matchIn = viewInStart ? this.mEndValuesList : this.mStartValuesList;
               values = (TransitionValues)matchIn.get(index);
            }

            return values;
         }
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void pause(View sceneRoot) {
      if (!this.mEnded) {
         ArrayMap runningAnimators = getRunningAnimators();
         int numOldAnims = runningAnimators.size();
         WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);

         for(int i = numOldAnims - 1; i >= 0; --i) {
            Transition.AnimationInfo info = (Transition.AnimationInfo)runningAnimators.valueAt(i);
            if (info.mView != null && windowId.equals(info.mWindowId)) {
               Animator anim = (Animator)runningAnimators.keyAt(i);
               AnimatorUtils.pause(anim);
            }
         }

         if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList tmpListeners = (ArrayList)this.mListeners.clone();
            int numListeners = tmpListeners.size();

            for(int i = 0; i < numListeners; ++i) {
               ((Transition.TransitionListener)tmpListeners.get(i)).onTransitionPause(this);
            }
         }

         this.mPaused = true;
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void resume(View sceneRoot) {
      if (this.mPaused) {
         if (!this.mEnded) {
            ArrayMap runningAnimators = getRunningAnimators();
            int numOldAnims = runningAnimators.size();
            WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);

            for(int i = numOldAnims - 1; i >= 0; --i) {
               Transition.AnimationInfo info = (Transition.AnimationInfo)runningAnimators.valueAt(i);
               if (info.mView != null && windowId.equals(info.mWindowId)) {
                  Animator anim = (Animator)runningAnimators.keyAt(i);
                  AnimatorUtils.resume(anim);
               }
            }

            if (this.mListeners != null && this.mListeners.size() > 0) {
               ArrayList tmpListeners = (ArrayList)this.mListeners.clone();
               int numListeners = tmpListeners.size();

               for(int i = 0; i < numListeners; ++i) {
                  ((Transition.TransitionListener)tmpListeners.get(i)).onTransitionResume(this);
               }
            }
         }

         this.mPaused = false;
      }

   }

   void playTransition(ViewGroup sceneRoot) {
      this.mStartValuesList = new ArrayList();
      this.mEndValuesList = new ArrayList();
      this.matchStartAndEnd(this.mStartValues, this.mEndValues);
      ArrayMap runningAnimators = getRunningAnimators();
      int numOldAnims = runningAnimators.size();
      WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);

      for(int i = numOldAnims - 1; i >= 0; --i) {
         Animator anim = (Animator)runningAnimators.keyAt(i);
         if (anim != null) {
            Transition.AnimationInfo oldInfo = (Transition.AnimationInfo)runningAnimators.get(anim);
            if (oldInfo != null && oldInfo.mView != null && windowId.equals(oldInfo.mWindowId)) {
               TransitionValues oldValues = oldInfo.mValues;
               View oldView = oldInfo.mView;
               TransitionValues startValues = this.getTransitionValues(oldView, true);
               TransitionValues endValues = this.getMatchedTransitionValues(oldView, true);
               boolean cancel = (startValues != null || endValues != null) && oldInfo.mTransition.isTransitionRequired(oldValues, endValues);
               if (cancel) {
                  if (!anim.isRunning() && !anim.isStarted()) {
                     runningAnimators.remove(anim);
                  } else {
                     anim.cancel();
                  }
               }
            }
         }
      }

      this.createAnimators(sceneRoot, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
      this.runAnimators();
   }

   public boolean isTransitionRequired(@Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
      boolean valuesChanged = false;
      if (startValues != null && endValues != null) {
         String[] properties = this.getTransitionProperties();
         if (properties != null) {
            String[] var5 = properties;
            int var6 = properties.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String property = var5[var7];
               if (isValueChanged(startValues, endValues, property)) {
                  valuesChanged = true;
                  break;
               }
            }
         } else {
            Iterator var9 = startValues.values.keySet().iterator();

            while(var9.hasNext()) {
               String key = (String)var9.next();
               if (isValueChanged(startValues, endValues, key)) {
                  valuesChanged = true;
                  break;
               }
            }
         }
      }

      return valuesChanged;
   }

   private static boolean isValueChanged(TransitionValues oldValues, TransitionValues newValues, String key) {
      Object oldValue = oldValues.values.get(key);
      Object newValue = newValues.values.get(key);
      boolean changed;
      if (oldValue == null && newValue == null) {
         changed = false;
      } else if (oldValue != null && newValue != null) {
         changed = !oldValue.equals(newValue);
      } else {
         changed = true;
      }

      return changed;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void animate(Animator animator) {
      if (animator == null) {
         this.end();
      } else {
         if (this.getDuration() >= 0L) {
            animator.setDuration(this.getDuration());
         }

         if (this.getStartDelay() >= 0L) {
            animator.setStartDelay(this.getStartDelay());
         }

         if (this.getInterpolator() != null) {
            animator.setInterpolator(this.getInterpolator());
         }

         animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
               Transition.this.end();
               animation.removeListener(this);
            }
         });
         animator.start();
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void start() {
      if (this.mNumInstances == 0) {
         if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList tmpListeners = (ArrayList)this.mListeners.clone();
            int numListeners = tmpListeners.size();

            for(int i = 0; i < numListeners; ++i) {
               ((Transition.TransitionListener)tmpListeners.get(i)).onTransitionStart(this);
            }
         }

         this.mEnded = false;
      }

      ++this.mNumInstances;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void end() {
      --this.mNumInstances;
      if (this.mNumInstances == 0) {
         if (this.mListeners != null && this.mListeners.size() > 0) {
            ArrayList tmpListeners = (ArrayList)this.mListeners.clone();
            int numListeners = tmpListeners.size();

            for(int i = 0; i < numListeners; ++i) {
               ((Transition.TransitionListener)tmpListeners.get(i)).onTransitionEnd(this);
            }
         }

         int i;
         View view;
         for(i = 0; i < this.mStartValues.mItemIdValues.size(); ++i) {
            view = (View)this.mStartValues.mItemIdValues.valueAt(i);
            if (view != null) {
               ViewCompat.setHasTransientState(view, false);
            }
         }

         for(i = 0; i < this.mEndValues.mItemIdValues.size(); ++i) {
            view = (View)this.mEndValues.mItemIdValues.valueAt(i);
            if (view != null) {
               ViewCompat.setHasTransientState(view, false);
            }
         }

         this.mEnded = true;
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void forceToEnd(ViewGroup sceneRoot) {
      ArrayMap runningAnimators = getRunningAnimators();
      int numOldAnims = runningAnimators.size();
      if (sceneRoot != null) {
         WindowIdImpl windowId = ViewUtils.getWindowId(sceneRoot);

         for(int i = numOldAnims - 1; i >= 0; --i) {
            Transition.AnimationInfo info = (Transition.AnimationInfo)runningAnimators.valueAt(i);
            if (info.mView != null && windowId != null && windowId.equals(info.mWindowId)) {
               Animator anim = (Animator)runningAnimators.keyAt(i);
               anim.end();
            }
         }
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected void cancel() {
      int numAnimators = this.mCurrentAnimators.size();

      for(int i = numAnimators - 1; i >= 0; --i) {
         Animator animator = (Animator)this.mCurrentAnimators.get(i);
         animator.cancel();
      }

      if (this.mListeners != null && this.mListeners.size() > 0) {
         ArrayList tmpListeners = (ArrayList)this.mListeners.clone();
         int numListeners = tmpListeners.size();

         for(int i = 0; i < numListeners; ++i) {
            ((Transition.TransitionListener)tmpListeners.get(i)).onTransitionCancel(this);
         }
      }

   }

   @NonNull
   public Transition addListener(@NonNull Transition.TransitionListener listener) {
      if (this.mListeners == null) {
         this.mListeners = new ArrayList();
      }

      this.mListeners.add(listener);
      return this;
   }

   @NonNull
   public Transition removeListener(@NonNull Transition.TransitionListener listener) {
      if (this.mListeners == null) {
         return this;
      } else {
         this.mListeners.remove(listener);
         if (this.mListeners.size() == 0) {
            this.mListeners = null;
         }

         return this;
      }
   }

   public void setPathMotion(@Nullable PathMotion pathMotion) {
      if (pathMotion == null) {
         this.mPathMotion = STRAIGHT_PATH_MOTION;
      } else {
         this.mPathMotion = pathMotion;
      }

   }

   @NonNull
   public PathMotion getPathMotion() {
      return this.mPathMotion;
   }

   public void setEpicenterCallback(@Nullable Transition.EpicenterCallback epicenterCallback) {
      this.mEpicenterCallback = epicenterCallback;
   }

   @Nullable
   public Transition.EpicenterCallback getEpicenterCallback() {
      return this.mEpicenterCallback;
   }

   @Nullable
   public Rect getEpicenter() {
      return this.mEpicenterCallback == null ? null : this.mEpicenterCallback.onGetEpicenter(this);
   }

   public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
      this.mPropagation = transitionPropagation;
   }

   @Nullable
   public TransitionPropagation getPropagation() {
      return this.mPropagation;
   }

   void capturePropagationValues(TransitionValues transitionValues) {
      if (this.mPropagation != null && !transitionValues.values.isEmpty()) {
         String[] propertyNames = this.mPropagation.getPropagationProperties();
         if (propertyNames == null) {
            return;
         }

         boolean containsAll = true;

         for(int i = 0; i < propertyNames.length; ++i) {
            if (!transitionValues.values.containsKey(propertyNames[i])) {
               containsAll = false;
               break;
            }
         }

         if (!containsAll) {
            this.mPropagation.captureValues(transitionValues);
         }
      }

   }

   Transition setSceneRoot(ViewGroup sceneRoot) {
      this.mSceneRoot = sceneRoot;
      return this;
   }

   void setCanRemoveViews(boolean canRemoveViews) {
      this.mCanRemoveViews = canRemoveViews;
   }

   public String toString() {
      return this.toString("");
   }

   public Transition clone() {
      try {
         Transition clone = (Transition)super.clone();
         clone.mAnimators = new ArrayList();
         clone.mStartValues = new TransitionValuesMaps();
         clone.mEndValues = new TransitionValuesMaps();
         clone.mStartValuesList = null;
         clone.mEndValuesList = null;
         return clone;
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   @NonNull
   public String getName() {
      return this.mName;
   }

   String toString(String indent) {
      String result = indent + this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + ": ";
      if (this.mDuration != -1L) {
         result = result + "dur(" + this.mDuration + ") ";
      }

      if (this.mStartDelay != -1L) {
         result = result + "dly(" + this.mStartDelay + ") ";
      }

      if (this.mInterpolator != null) {
         result = result + "interp(" + this.mInterpolator + ") ";
      }

      if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
         result = result + "tgts(";
         int i;
         if (this.mTargetIds.size() > 0) {
            for(i = 0; i < this.mTargetIds.size(); ++i) {
               if (i > 0) {
                  result = result + ", ";
               }

               result = result + this.mTargetIds.get(i);
            }
         }

         if (this.mTargets.size() > 0) {
            for(i = 0; i < this.mTargets.size(); ++i) {
               if (i > 0) {
                  result = result + ", ";
               }

               result = result + this.mTargets.get(i);
            }
         }

         result = result + ")";
      }

      return result;
   }

   public abstract static class EpicenterCallback {
      public abstract Rect onGetEpicenter(@NonNull Transition var1);
   }

   private static class ArrayListManager {
      static ArrayList add(ArrayList list, Object item) {
         if (list == null) {
            list = new ArrayList();
         }

         if (!list.contains(item)) {
            list.add(item);
         }

         return list;
      }

      static ArrayList remove(ArrayList list, Object item) {
         if (list != null) {
            list.remove(item);
            if (list.isEmpty()) {
               list = null;
            }
         }

         return list;
      }
   }

   private static class AnimationInfo {
      View mView;
      String mName;
      TransitionValues mValues;
      WindowIdImpl mWindowId;
      Transition mTransition;

      AnimationInfo(View view, String name, Transition transition, WindowIdImpl windowId, TransitionValues values) {
         this.mView = view;
         this.mName = name;
         this.mValues = values;
         this.mWindowId = windowId;
         this.mTransition = transition;
      }
   }

   public interface TransitionListener {
      void onTransitionStart(@NonNull Transition var1);

      void onTransitionEnd(@NonNull Transition var1);

      void onTransitionCancel(@NonNull Transition var1);

      void onTransitionPause(@NonNull Transition var1);

      void onTransitionResume(@NonNull Transition var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface MatchOrder {
   }
}
