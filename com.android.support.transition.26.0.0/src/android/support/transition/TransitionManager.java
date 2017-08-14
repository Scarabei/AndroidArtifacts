package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionManager {
   private static final String LOG_TAG = "TransitionManager";
   private static Transition sDefaultTransition = new AutoTransition();
   private ArrayMap mSceneTransitions = new ArrayMap();
   private ArrayMap mScenePairTransitions = new ArrayMap();
   private static ThreadLocal sRunningTransitions = new ThreadLocal();
   private static ArrayList sPendingTransitions = new ArrayList();

   public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
      this.mSceneTransitions.put(scene, transition);
   }

   public void setTransition(@NonNull Scene fromScene, @NonNull Scene toScene, @Nullable Transition transition) {
      ArrayMap sceneTransitionMap = (ArrayMap)this.mScenePairTransitions.get(toScene);
      if (sceneTransitionMap == null) {
         sceneTransitionMap = new ArrayMap();
         this.mScenePairTransitions.put(toScene, sceneTransitionMap);
      }

      sceneTransitionMap.put(fromScene, transition);
   }

   private Transition getTransition(Scene scene) {
      ViewGroup sceneRoot = scene.getSceneRoot();
      Transition transition;
      if (sceneRoot != null) {
         Scene currScene = Scene.getCurrentScene(sceneRoot);
         if (currScene != null) {
            ArrayMap sceneTransitionMap = (ArrayMap)this.mScenePairTransitions.get(scene);
            if (sceneTransitionMap != null) {
               transition = (Transition)sceneTransitionMap.get(currScene);
               if (transition != null) {
                  return transition;
               }
            }
         }
      }

      transition = (Transition)this.mSceneTransitions.get(scene);
      return transition != null ? transition : sDefaultTransition;
   }

   private static void changeScene(Scene scene, Transition transition) {
      ViewGroup sceneRoot = scene.getSceneRoot();
      if (!sPendingTransitions.contains(sceneRoot)) {
         if (transition == null) {
            scene.enter();
         } else {
            sPendingTransitions.add(sceneRoot);
            Transition transitionClone = transition.clone();
            transitionClone.setSceneRoot(sceneRoot);
            Scene oldScene = Scene.getCurrentScene(sceneRoot);
            if (oldScene != null && oldScene.isCreatedFromLayoutResource()) {
               transitionClone.setCanRemoveViews(true);
            }

            sceneChangeSetup(sceneRoot, transitionClone);
            scene.enter();
            sceneChangeRunTransition(sceneRoot, transitionClone);
         }
      }

   }

   static ArrayMap getRunningTransitions() {
      WeakReference runningTransitions = (WeakReference)sRunningTransitions.get();
      if (runningTransitions == null || runningTransitions.get() == null) {
         ArrayMap transitions = new ArrayMap();
         runningTransitions = new WeakReference(transitions);
         sRunningTransitions.set(runningTransitions);
      }

      return (ArrayMap)runningTransitions.get();
   }

   private static void sceneChangeRunTransition(ViewGroup sceneRoot, Transition transition) {
      if (transition != null && sceneRoot != null) {
         TransitionManager.MultiListener listener = new TransitionManager.MultiListener(transition, sceneRoot);
         sceneRoot.addOnAttachStateChangeListener(listener);
         sceneRoot.getViewTreeObserver().addOnPreDrawListener(listener);
      }

   }

   private static void sceneChangeSetup(ViewGroup sceneRoot, Transition transition) {
      ArrayList runningTransitions = (ArrayList)getRunningTransitions().get(sceneRoot);
      if (runningTransitions != null && runningTransitions.size() > 0) {
         Iterator var3 = runningTransitions.iterator();

         while(var3.hasNext()) {
            Transition runningTransition = (Transition)var3.next();
            runningTransition.pause(sceneRoot);
         }
      }

      if (transition != null) {
         transition.captureValues(sceneRoot, true);
      }

      Scene previousScene = Scene.getCurrentScene(sceneRoot);
      if (previousScene != null) {
         previousScene.exit();
      }

   }

   public void transitionTo(@NonNull Scene scene) {
      changeScene(scene, this.getTransition(scene));
   }

   public static void go(@NonNull Scene scene) {
      changeScene(scene, sDefaultTransition);
   }

   public static void go(@NonNull Scene scene, @Nullable Transition transition) {
      changeScene(scene, transition);
   }

   public static void beginDelayedTransition(@NonNull ViewGroup sceneRoot) {
      beginDelayedTransition(sceneRoot, (Transition)null);
   }

   public static void beginDelayedTransition(@NonNull ViewGroup sceneRoot, @Nullable Transition transition) {
      if (!sPendingTransitions.contains(sceneRoot) && ViewCompat.isLaidOut(sceneRoot)) {
         sPendingTransitions.add(sceneRoot);
         if (transition == null) {
            transition = sDefaultTransition;
         }

         Transition transitionClone = transition.clone();
         sceneChangeSetup(sceneRoot, transitionClone);
         Scene.setCurrentScene(sceneRoot, (Scene)null);
         sceneChangeRunTransition(sceneRoot, transitionClone);
      }

   }

   public static void endTransitions(ViewGroup sceneRoot) {
      sPendingTransitions.remove(sceneRoot);
      ArrayList runningTransitions = (ArrayList)getRunningTransitions().get(sceneRoot);
      if (runningTransitions != null && !runningTransitions.isEmpty()) {
         ArrayList copy = new ArrayList(runningTransitions);

         for(int i = copy.size() - 1; i >= 0; --i) {
            Transition transition = (Transition)copy.get(i);
            transition.forceToEnd(sceneRoot);
         }
      }

   }

   private static class MultiListener implements OnPreDrawListener, OnAttachStateChangeListener {
      Transition mTransition;
      ViewGroup mSceneRoot;

      MultiListener(Transition transition, ViewGroup sceneRoot) {
         this.mTransition = transition;
         this.mSceneRoot = sceneRoot;
      }

      private void removeListeners() {
         this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
         this.mSceneRoot.removeOnAttachStateChangeListener(this);
      }

      public void onViewAttachedToWindow(View v) {
      }

      public void onViewDetachedFromWindow(View v) {
         this.removeListeners();
         TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
         ArrayList runningTransitions = (ArrayList)TransitionManager.getRunningTransitions().get(this.mSceneRoot);
         if (runningTransitions != null && runningTransitions.size() > 0) {
            Iterator var3 = runningTransitions.iterator();

            while(var3.hasNext()) {
               Transition runningTransition = (Transition)var3.next();
               runningTransition.resume(this.mSceneRoot);
            }
         }

         this.mTransition.clearValues(true);
      }

      public boolean onPreDraw() {
         this.removeListeners();
         if (!TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
            return true;
         } else {
            final ArrayMap runningTransitions = TransitionManager.getRunningTransitions();
            ArrayList currentTransitions = (ArrayList)runningTransitions.get(this.mSceneRoot);
            ArrayList previousRunningTransitions = null;
            if (currentTransitions == null) {
               currentTransitions = new ArrayList();
               runningTransitions.put(this.mSceneRoot, currentTransitions);
            } else if (currentTransitions.size() > 0) {
               previousRunningTransitions = new ArrayList(currentTransitions);
            }

            currentTransitions.add(this.mTransition);
            this.mTransition.addListener(new TransitionListenerAdapter() {
               public void onTransitionEnd(@NonNull Transition transition) {
                  ArrayList currentTransitions = (ArrayList)runningTransitions.get(MultiListener.this.mSceneRoot);
                  currentTransitions.remove(transition);
               }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (previousRunningTransitions != null) {
               Iterator var4 = previousRunningTransitions.iterator();

               while(var4.hasNext()) {
                  Transition runningTransition = (Transition)var4.next();
                  runningTransition.resume(this.mSceneRoot);
               }
            }

            this.mTransition.playTransition(this.mSceneRoot);
            return true;
         }
      }
   }
}
