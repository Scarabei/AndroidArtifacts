package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.Transition.EpicenterCallback;
import android.transition.Transition.TransitionListener;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RequiresApi(21)
class FragmentTransitionCompat21 {
   public static Object cloneTransition(Object transition) {
      Transition copy = null;
      if (transition != null) {
         copy = ((Transition)transition).clone();
      }

      return copy;
   }

   public static Object wrapTransitionInSet(Object transition) {
      if (transition == null) {
         return null;
      } else {
         TransitionSet transitionSet = new TransitionSet();
         transitionSet.addTransition((Transition)transition);
         return transitionSet;
      }
   }

   public static void setSharedElementTargets(Object transitionObj, View nonExistentView, ArrayList sharedViews) {
      TransitionSet transition = (TransitionSet)transitionObj;
      List views = transition.getTargets();
      views.clear();
      int count = sharedViews.size();

      for(int i = 0; i < count; ++i) {
         View view = (View)sharedViews.get(i);
         bfsAddViewChildren(views, view);
      }

      views.add(nonExistentView);
      sharedViews.add(nonExistentView);
      addTargets(transition, sharedViews);
   }

   private static void bfsAddViewChildren(List views, View startView) {
      int startIndex = views.size();
      if (!containedBeforeIndex(views, startView, startIndex)) {
         views.add(startView);

         for(int index = startIndex; index < views.size(); ++index) {
            View view = (View)views.get(index);
            if (view instanceof ViewGroup) {
               ViewGroup viewGroup = (ViewGroup)view;
               int childCount = viewGroup.getChildCount();

               for(int childIndex = 0; childIndex < childCount; ++childIndex) {
                  View child = viewGroup.getChildAt(childIndex);
                  if (!containedBeforeIndex(views, child, startIndex)) {
                     views.add(child);
                  }
               }
            }
         }

      }
   }

   private static boolean containedBeforeIndex(List views, View view, int maxIndex) {
      for(int i = 0; i < maxIndex; ++i) {
         if (views.get(i) == view) {
            return true;
         }
      }

      return false;
   }

   public static void setEpicenter(Object transitionObj, View view) {
      if (view != null) {
         Transition transition = (Transition)transitionObj;
         final Rect epicenter = new Rect();
         getBoundsOnScreen(view, epicenter);
         transition.setEpicenterCallback(new EpicenterCallback() {
            public Rect onGetEpicenter(Transition transition) {
               return epicenter;
            }
         });
      }

   }

   public static void getBoundsOnScreen(View view, Rect epicenter) {
      int[] loc = new int[2];
      view.getLocationOnScreen(loc);
      epicenter.set(loc[0], loc[1], loc[0] + view.getWidth(), loc[1] + view.getHeight());
   }

   public static void addTargets(Object transitionObj, ArrayList views) {
      Transition transition = (Transition)transitionObj;
      if (transition != null) {
         int numViews;
         int i;
         if (transition instanceof TransitionSet) {
            TransitionSet set = (TransitionSet)transition;
            numViews = set.getTransitionCount();

            for(i = 0; i < numViews; ++i) {
               Transition child = set.getTransitionAt(i);
               addTargets(child, views);
            }
         } else if (!hasSimpleTarget(transition)) {
            List targets = transition.getTargets();
            if (isNullOrEmpty(targets)) {
               numViews = views.size();

               for(i = 0; i < numViews; ++i) {
                  transition.addTarget((View)views.get(i));
               }
            }
         }

      }
   }

   private static boolean hasSimpleTarget(Transition transition) {
      return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes());
   }

   private static boolean isNullOrEmpty(List list) {
      return list == null || list.isEmpty();
   }

   public static Object mergeTransitionsTogether(Object transition1, Object transition2, Object transition3) {
      TransitionSet transitionSet = new TransitionSet();
      if (transition1 != null) {
         transitionSet.addTransition((Transition)transition1);
      }

      if (transition2 != null) {
         transitionSet.addTransition((Transition)transition2);
      }

      if (transition3 != null) {
         transitionSet.addTransition((Transition)transition3);
      }

      return transitionSet;
   }

   public static void scheduleHideFragmentView(Object exitTransitionObj, final View fragmentView, final ArrayList exitingViews) {
      Transition exitTransition = (Transition)exitTransitionObj;
      exitTransition.addListener(new TransitionListener() {
         public void onTransitionStart(Transition transition) {
         }

         public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
            fragmentView.setVisibility(8);
            int numViews = exitingViews.size();

            for(int i = 0; i < numViews; ++i) {
               ((View)exitingViews.get(i)).setVisibility(0);
            }

         }

         public void onTransitionCancel(Transition transition) {
         }

         public void onTransitionPause(Transition transition) {
         }

         public void onTransitionResume(Transition transition) {
         }
      });
   }

   public static Object mergeTransitionsInSequence(Object exitTransitionObj, Object enterTransitionObj, Object sharedElementTransitionObj) {
      Transition staggered = null;
      Transition exitTransition = (Transition)exitTransitionObj;
      Transition enterTransition = (Transition)enterTransitionObj;
      Transition sharedElementTransition = (Transition)sharedElementTransitionObj;
      if (exitTransition != null && enterTransition != null) {
         staggered = (new TransitionSet()).addTransition(exitTransition).addTransition(enterTransition).setOrdering(1);
      } else if (exitTransition != null) {
         staggered = exitTransition;
      } else if (enterTransition != null) {
         staggered = enterTransition;
      }

      if (sharedElementTransition != null) {
         TransitionSet together = new TransitionSet();
         if (staggered != null) {
            together.addTransition((Transition)staggered);
         }

         together.addTransition(sharedElementTransition);
         return together;
      } else {
         return staggered;
      }
   }

   public static void beginDelayedTransition(ViewGroup sceneRoot, Object transition) {
      TransitionManager.beginDelayedTransition(sceneRoot, (Transition)transition);
   }

   public static ArrayList prepareSetNameOverridesReordered(ArrayList sharedElementsIn) {
      ArrayList names = new ArrayList();
      int numSharedElements = sharedElementsIn.size();

      for(int i = 0; i < numSharedElements; ++i) {
         View view = (View)sharedElementsIn.get(i);
         names.add(view.getTransitionName());
         view.setTransitionName((String)null);
      }

      return names;
   }

   public static void setNameOverridesReordered(View sceneRoot, final ArrayList sharedElementsOut, final ArrayList sharedElementsIn, final ArrayList inNames, Map nameOverrides) {
      final int numSharedElements = sharedElementsIn.size();
      final ArrayList outNames = new ArrayList();

      for(int i = 0; i < numSharedElements; ++i) {
         View view = (View)sharedElementsOut.get(i);
         String name = view.getTransitionName();
         outNames.add(name);
         if (name != null) {
            view.setTransitionName((String)null);
            String inName = (String)nameOverrides.get(name);

            for(int j = 0; j < numSharedElements; ++j) {
               if (inName.equals(inNames.get(j))) {
                  ((View)sharedElementsIn.get(j)).setTransitionName(name);
                  break;
               }
            }
         }
      }

      OneShotPreDrawListener.add(sceneRoot, new Runnable() {
         public void run() {
            for(int i = 0; i < numSharedElements; ++i) {
               ((View)sharedElementsIn.get(i)).setTransitionName((String)inNames.get(i));
               ((View)sharedElementsOut.get(i)).setTransitionName((String)outNames.get(i));
            }

         }
      });
   }

   public static void captureTransitioningViews(ArrayList transitioningViews, View view) {
      if (view.getVisibility() == 0) {
         if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            if (viewGroup.isTransitionGroup()) {
               transitioningViews.add(viewGroup);
            } else {
               int count = viewGroup.getChildCount();

               for(int i = 0; i < count; ++i) {
                  View child = viewGroup.getChildAt(i);
                  captureTransitioningViews(transitioningViews, child);
               }
            }
         } else {
            transitioningViews.add(view);
         }
      }

   }

   public static void findNamedViews(Map namedViews, View view) {
      if (view.getVisibility() == 0) {
         String transitionName = view.getTransitionName();
         if (transitionName != null) {
            namedViews.put(transitionName, view);
         }

         if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            int count = viewGroup.getChildCount();

            for(int i = 0; i < count; ++i) {
               View child = viewGroup.getChildAt(i);
               findNamedViews(namedViews, child);
            }
         }
      }

   }

   public static void setNameOverridesOrdered(View sceneRoot, final ArrayList sharedElementsIn, final Map nameOverrides) {
      OneShotPreDrawListener.add(sceneRoot, new Runnable() {
         public void run() {
            int numSharedElements = sharedElementsIn.size();

            for(int i = 0; i < numSharedElements; ++i) {
               View view = (View)sharedElementsIn.get(i);
               String name = view.getTransitionName();
               if (name != null) {
                  String inName = FragmentTransitionCompat21.findKeyForValue(nameOverrides, name);
                  view.setTransitionName(inName);
               }
            }

         }
      });
   }

   private static String findKeyForValue(Map map, String value) {
      Iterator var2 = map.entrySet().iterator();

      Entry entry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entry = (Entry)var2.next();
      } while(!value.equals(entry.getValue()));

      return (String)entry.getKey();
   }

   public static void scheduleRemoveTargets(Object overallTransitionObj, final Object enterTransition, final ArrayList enteringViews, final Object exitTransition, final ArrayList exitingViews, final Object sharedElementTransition, final ArrayList sharedElementsIn) {
      Transition overallTransition = (Transition)overallTransitionObj;
      overallTransition.addListener(new TransitionListener() {
         public void onTransitionStart(Transition transition) {
            if (enterTransition != null) {
               FragmentTransitionCompat21.replaceTargets(enterTransition, enteringViews, (ArrayList)null);
            }

            if (exitTransition != null) {
               FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, (ArrayList)null);
            }

            if (sharedElementTransition != null) {
               FragmentTransitionCompat21.replaceTargets(sharedElementTransition, sharedElementsIn, (ArrayList)null);
            }

         }

         public void onTransitionEnd(Transition transition) {
         }

         public void onTransitionCancel(Transition transition) {
         }

         public void onTransitionPause(Transition transition) {
         }

         public void onTransitionResume(Transition transition) {
         }
      });
   }

   public static void swapSharedElementTargets(Object sharedElementTransitionObj, ArrayList sharedElementsOut, ArrayList sharedElementsIn) {
      TransitionSet sharedElementTransition = (TransitionSet)sharedElementTransitionObj;
      if (sharedElementTransition != null) {
         sharedElementTransition.getTargets().clear();
         sharedElementTransition.getTargets().addAll(sharedElementsIn);
         replaceTargets(sharedElementTransition, sharedElementsOut, sharedElementsIn);
      }

   }

   public static void replaceTargets(Object transitionObj, ArrayList oldTargets, ArrayList newTargets) {
      Transition transition = (Transition)transitionObj;
      int targetCount;
      int i;
      if (transition instanceof TransitionSet) {
         TransitionSet set = (TransitionSet)transition;
         targetCount = set.getTransitionCount();

         for(i = 0; i < targetCount; ++i) {
            Transition child = set.getTransitionAt(i);
            replaceTargets(child, oldTargets, newTargets);
         }
      } else if (!hasSimpleTarget(transition)) {
         List targets = transition.getTargets();
         if (targets != null && targets.size() == oldTargets.size() && targets.containsAll(oldTargets)) {
            targetCount = newTargets == null ? 0 : newTargets.size();

            for(i = 0; i < targetCount; ++i) {
               transition.addTarget((View)newTargets.get(i));
            }

            for(i = oldTargets.size() - 1; i >= 0; --i) {
               transition.removeTarget((View)oldTargets.get(i));
            }
         }
      }

   }

   public static void addTarget(Object transitionObj, View view) {
      if (transitionObj != null) {
         Transition transition = (Transition)transitionObj;
         transition.addTarget(view);
      }

   }

   public static void removeTarget(Object transitionObj, View view) {
      if (transitionObj != null) {
         Transition transition = (Transition)transitionObj;
         transition.removeTarget(view);
      }

   }

   public static void setEpicenter(Object transitionObj, final Rect epicenter) {
      if (transitionObj != null) {
         Transition transition = (Transition)transitionObj;
         transition.setEpicenterCallback(new EpicenterCallback() {
            public Rect onGetEpicenter(Transition transition) {
               return epicenter != null && !epicenter.isEmpty() ? epicenter : null;
            }
         });
      }

   }

   public static void scheduleNameReset(ViewGroup sceneRoot, final ArrayList sharedElementsIn, final Map nameOverrides) {
      OneShotPreDrawListener.add(sceneRoot, new Runnable() {
         public void run() {
            int numSharedElements = sharedElementsIn.size();

            for(int i = 0; i < numSharedElements; ++i) {
               View view = (View)sharedElementsIn.get(i);
               String name = view.getTransitionName();
               String inName = (String)nameOverrides.get(name);
               view.setTransitionName(inName);
            }

         }
      });
   }
}
