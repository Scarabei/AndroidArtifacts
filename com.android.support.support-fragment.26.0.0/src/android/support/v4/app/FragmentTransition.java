package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
   private static final int[] INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

   static void startTransitions(FragmentManagerImpl fragmentManager, ArrayList records, ArrayList isRecordPop, int startIndex, int endIndex, boolean isReordered) {
      if (fragmentManager.mCurState >= 1) {
         if (VERSION.SDK_INT >= 21) {
            SparseArray transitioningFragments = new SparseArray();

            for(int i = startIndex; i < endIndex; ++i) {
               BackStackRecord record = (BackStackRecord)records.get(i);
               boolean isPop = ((Boolean)isRecordPop.get(i)).booleanValue();
               if (isPop) {
                  calculatePopFragments(record, transitioningFragments, isReordered);
               } else {
                  calculateFragments(record, transitioningFragments, isReordered);
               }
            }

            if (transitioningFragments.size() != 0) {
               View nonExistentView = new View(fragmentManager.mHost.getContext());
               int numContainers = transitioningFragments.size();

               for(int i = 0; i < numContainers; ++i) {
                  int containerId = transitioningFragments.keyAt(i);
                  ArrayMap nameOverrides = calculateNameOverrides(containerId, records, isRecordPop, startIndex, endIndex);
                  FragmentTransition.FragmentContainerTransition containerTransition = (FragmentTransition.FragmentContainerTransition)transitioningFragments.valueAt(i);
                  if (isReordered) {
                     configureTransitionsReordered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                  } else {
                     configureTransitionsOrdered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                  }
               }
            }
         }

      }
   }

   private static ArrayMap calculateNameOverrides(int containerId, ArrayList records, ArrayList isRecordPop, int startIndex, int endIndex) {
      ArrayMap nameOverrides = new ArrayMap();

      for(int recordNum = endIndex - 1; recordNum >= startIndex; --recordNum) {
         BackStackRecord record = (BackStackRecord)records.get(recordNum);
         if (record.interactsWith(containerId)) {
            boolean isPop = ((Boolean)isRecordPop.get(recordNum)).booleanValue();
            if (record.mSharedElementSourceNames != null) {
               int numSharedElements = record.mSharedElementSourceNames.size();
               ArrayList sources;
               ArrayList targets;
               if (isPop) {
                  targets = record.mSharedElementSourceNames;
                  sources = record.mSharedElementTargetNames;
               } else {
                  sources = record.mSharedElementSourceNames;
                  targets = record.mSharedElementTargetNames;
               }

               for(int i = 0; i < numSharedElements; ++i) {
                  String sourceName = (String)sources.get(i);
                  String targetName = (String)targets.get(i);
                  String previousTarget = (String)nameOverrides.remove(targetName);
                  if (previousTarget != null) {
                     nameOverrides.put(sourceName, previousTarget);
                  } else {
                     nameOverrides.put(sourceName, targetName);
                  }
               }
            }
         }
      }

      return nameOverrides;
   }

   @RequiresApi(21)
   private static void configureTransitionsReordered(FragmentManagerImpl fragmentManager, int containerId, FragmentTransition.FragmentContainerTransition fragments, View nonExistentView, ArrayMap nameOverrides) {
      ViewGroup sceneRoot = null;
      if (fragmentManager.mContainer.onHasView()) {
         sceneRoot = (ViewGroup)fragmentManager.mContainer.onFindViewById(containerId);
      }

      if (sceneRoot != null) {
         Fragment inFragment = fragments.lastIn;
         Fragment outFragment = fragments.firstOut;
         boolean inIsPop = fragments.lastInIsPop;
         boolean outIsPop = fragments.firstOutIsPop;
         ArrayList sharedElementsIn = new ArrayList();
         ArrayList sharedElementsOut = new ArrayList();
         Object enterTransition = getEnterTransition(inFragment, inIsPop);
         Object exitTransition = getExitTransition(outFragment, outIsPop);
         Object sharedElementTransition = configureSharedElementsReordered(sceneRoot, nonExistentView, nameOverrides, fragments, sharedElementsOut, sharedElementsIn, enterTransition, exitTransition);
         if (enterTransition != null || sharedElementTransition != null || exitTransition != null) {
            ArrayList exitingViews = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut, nonExistentView);
            ArrayList enteringViews = configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
            setViewVisibility(enteringViews, 4);
            Object transition = mergeTransitions(enterTransition, exitTransition, sharedElementTransition, inFragment, inIsPop);
            if (transition != null) {
               replaceHide(exitTransition, outFragment, exitingViews);
               ArrayList inNames = FragmentTransitionCompat21.prepareSetNameOverridesReordered(sharedElementsIn);
               FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition, enteringViews, exitTransition, exitingViews, sharedElementTransition, sharedElementsIn);
               FragmentTransitionCompat21.beginDelayedTransition(sceneRoot, transition);
               FragmentTransitionCompat21.setNameOverridesReordered(sceneRoot, sharedElementsOut, sharedElementsIn, inNames, nameOverrides);
               setViewVisibility(enteringViews, 0);
               FragmentTransitionCompat21.swapSharedElementTargets(sharedElementTransition, sharedElementsOut, sharedElementsIn);
            }

         }
      }
   }

   @RequiresApi(21)
   private static void replaceHide(Object exitTransition, Fragment exitingFragment, final ArrayList exitingViews) {
      if (exitingFragment != null && exitTransition != null && exitingFragment.mAdded && exitingFragment.mHidden && exitingFragment.mHiddenChanged) {
         exitingFragment.setHideReplaced(true);
         FragmentTransitionCompat21.scheduleHideFragmentView(exitTransition, exitingFragment.getView(), exitingViews);
         ViewGroup container = exitingFragment.mContainer;
         OneShotPreDrawListener.add(container, new Runnable() {
            public void run() {
               FragmentTransition.setViewVisibility(exitingViews, 4);
            }
         });
      }

   }

   @RequiresApi(21)
   private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManager, int containerId, FragmentTransition.FragmentContainerTransition fragments, View nonExistentView, ArrayMap nameOverrides) {
      ViewGroup sceneRoot = null;
      if (fragmentManager.mContainer.onHasView()) {
         sceneRoot = (ViewGroup)fragmentManager.mContainer.onFindViewById(containerId);
      }

      if (sceneRoot != null) {
         Fragment inFragment = fragments.lastIn;
         Fragment outFragment = fragments.firstOut;
         boolean inIsPop = fragments.lastInIsPop;
         boolean outIsPop = fragments.firstOutIsPop;
         Object enterTransition = getEnterTransition(inFragment, inIsPop);
         Object exitTransition = getExitTransition(outFragment, outIsPop);
         ArrayList sharedElementsOut = new ArrayList();
         ArrayList sharedElementsIn = new ArrayList();
         Object sharedElementTransition = configureSharedElementsOrdered(sceneRoot, nonExistentView, nameOverrides, fragments, sharedElementsOut, sharedElementsIn, enterTransition, exitTransition);
         if (enterTransition != null || sharedElementTransition != null || exitTransition != null) {
            ArrayList exitingViews = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut, nonExistentView);
            if (exitingViews == null || exitingViews.isEmpty()) {
               exitTransition = null;
            }

            FragmentTransitionCompat21.addTarget(enterTransition, nonExistentView);
            Object transition = mergeTransitions(enterTransition, exitTransition, sharedElementTransition, inFragment, fragments.lastInIsPop);
            if (transition != null) {
               ArrayList enteringViews = new ArrayList();
               FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition, enteringViews, exitTransition, exitingViews, sharedElementTransition, sharedElementsIn);
               scheduleTargetChange(sceneRoot, inFragment, nonExistentView, sharedElementsIn, enterTransition, enteringViews, exitTransition, exitingViews);
               FragmentTransitionCompat21.setNameOverridesOrdered(sceneRoot, sharedElementsIn, nameOverrides);
               FragmentTransitionCompat21.beginDelayedTransition(sceneRoot, transition);
               FragmentTransitionCompat21.scheduleNameReset(sceneRoot, sharedElementsIn, nameOverrides);
            }

         }
      }
   }

   @RequiresApi(21)
   private static void scheduleTargetChange(ViewGroup sceneRoot, final Fragment inFragment, final View nonExistentView, final ArrayList sharedElementsIn, final Object enterTransition, final ArrayList enteringViews, final Object exitTransition, final ArrayList exitingViews) {
      OneShotPreDrawListener.add(sceneRoot, new Runnable() {
         public void run() {
            ArrayList tempExiting;
            if (enterTransition != null) {
               FragmentTransitionCompat21.removeTarget(enterTransition, nonExistentView);
               tempExiting = FragmentTransition.configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
               enteringViews.addAll(tempExiting);
            }

            if (exitingViews != null) {
               if (exitTransition != null) {
                  tempExiting = new ArrayList();
                  tempExiting.add(nonExistentView);
                  FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, tempExiting);
               }

               exitingViews.clear();
               exitingViews.add(nonExistentView);
            }

         }
      });
   }

   @RequiresApi(21)
   private static Object getSharedElementTransition(Fragment inFragment, Fragment outFragment, boolean isPop) {
      if (inFragment != null && outFragment != null) {
         Object transition = FragmentTransitionCompat21.cloneTransition(isPop ? outFragment.getSharedElementReturnTransition() : inFragment.getSharedElementEnterTransition());
         return FragmentTransitionCompat21.wrapTransitionInSet(transition);
      } else {
         return null;
      }
   }

   @RequiresApi(21)
   private static Object getEnterTransition(Fragment inFragment, boolean isPop) {
      return inFragment == null ? null : FragmentTransitionCompat21.cloneTransition(isPop ? inFragment.getReenterTransition() : inFragment.getEnterTransition());
   }

   @RequiresApi(21)
   private static Object getExitTransition(Fragment outFragment, boolean isPop) {
      return outFragment == null ? null : FragmentTransitionCompat21.cloneTransition(isPop ? outFragment.getReturnTransition() : outFragment.getExitTransition());
   }

   @RequiresApi(21)
   private static Object configureSharedElementsReordered(ViewGroup sceneRoot, View nonExistentView, ArrayMap nameOverrides, FragmentTransition.FragmentContainerTransition fragments, ArrayList sharedElementsOut, ArrayList sharedElementsIn, Object enterTransition, Object exitTransition) {
      final Fragment inFragment = fragments.lastIn;
      final Fragment outFragment = fragments.firstOut;
      if (inFragment != null) {
         inFragment.getView().setVisibility(0);
      }

      if (inFragment != null && outFragment != null) {
         final boolean inIsPop = fragments.lastInIsPop;
         Object sharedElementTransition = nameOverrides.isEmpty() ? null : getSharedElementTransition(inFragment, outFragment, inIsPop);
         ArrayMap outSharedElements = captureOutSharedElements(nameOverrides, sharedElementTransition, fragments);
         final ArrayMap inSharedElements = captureInSharedElements(nameOverrides, sharedElementTransition, fragments);
         if (nameOverrides.isEmpty()) {
            sharedElementTransition = null;
            if (outSharedElements != null) {
               outSharedElements.clear();
            }

            if (inSharedElements != null) {
               inSharedElements.clear();
            }
         } else {
            addSharedElementsWithMatchingNames(sharedElementsOut, outSharedElements, nameOverrides.keySet());
            addSharedElementsWithMatchingNames(sharedElementsIn, inSharedElements, nameOverrides.values());
         }

         if (enterTransition == null && exitTransition == null && sharedElementTransition == null) {
            return null;
         } else {
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            final Rect epicenter;
            final View epicenterView;
            if (sharedElementTransition != null) {
               sharedElementsIn.add(nonExistentView);
               FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition, nonExistentView, sharedElementsOut);
               boolean outIsPop = fragments.firstOutIsPop;
               BackStackRecord outTransaction = fragments.firstOutTransaction;
               setOutEpicenter(sharedElementTransition, exitTransition, outSharedElements, outIsPop, outTransaction);
               epicenter = new Rect();
               epicenterView = getInEpicenterView(inSharedElements, fragments, enterTransition, inIsPop);
               if (epicenterView != null) {
                  FragmentTransitionCompat21.setEpicenter(enterTransition, epicenter);
               }
            } else {
               epicenter = null;
               epicenterView = null;
            }

            OneShotPreDrawListener.add(sceneRoot, new Runnable() {
               public void run() {
                  FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
                  if (epicenterView != null) {
                     FragmentTransitionCompat21.getBoundsOnScreen(epicenterView, epicenter);
                  }

               }
            });
            return sharedElementTransition;
         }
      } else {
         return null;
      }
   }

   private static void addSharedElementsWithMatchingNames(ArrayList views, ArrayMap sharedElements, Collection nameOverridesSet) {
      for(int i = sharedElements.size() - 1; i >= 0; --i) {
         View view = (View)sharedElements.valueAt(i);
         if (nameOverridesSet.contains(ViewCompat.getTransitionName(view))) {
            views.add(view);
         }
      }

   }

   @RequiresApi(21)
   private static Object configureSharedElementsOrdered(ViewGroup sceneRoot, final View nonExistentView, final ArrayMap nameOverrides, final FragmentTransition.FragmentContainerTransition fragments, final ArrayList sharedElementsOut, final ArrayList sharedElementsIn, final Object enterTransition, Object exitTransition) {
      final Fragment inFragment = fragments.lastIn;
      final Fragment outFragment = fragments.firstOut;
      if (inFragment != null && outFragment != null) {
         final boolean inIsPop = fragments.lastInIsPop;
         final Object sharedElementTransition = nameOverrides.isEmpty() ? null : getSharedElementTransition(inFragment, outFragment, inIsPop);
         ArrayMap outSharedElements = captureOutSharedElements(nameOverrides, sharedElementTransition, fragments);
         if (nameOverrides.isEmpty()) {
            sharedElementTransition = null;
         } else {
            sharedElementsOut.addAll(outSharedElements.values());
         }

         if (enterTransition == null && exitTransition == null && sharedElementTransition == null) {
            return null;
         } else {
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            final Rect inEpicenter;
            if (sharedElementTransition != null) {
               inEpicenter = new Rect();
               FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition, nonExistentView, sharedElementsOut);
               boolean outIsPop = fragments.firstOutIsPop;
               BackStackRecord outTransaction = fragments.firstOutTransaction;
               setOutEpicenter(sharedElementTransition, exitTransition, outSharedElements, outIsPop, outTransaction);
               if (enterTransition != null) {
                  FragmentTransitionCompat21.setEpicenter(enterTransition, inEpicenter);
               }
            } else {
               inEpicenter = null;
            }

            OneShotPreDrawListener.add(sceneRoot, new Runnable() {
               public void run() {
                  ArrayMap inSharedElements = FragmentTransition.captureInSharedElements(nameOverrides, sharedElementTransition, fragments);
                  if (inSharedElements != null) {
                     sharedElementsIn.addAll(inSharedElements.values());
                     sharedElementsIn.add(nonExistentView);
                  }

                  FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
                  if (sharedElementTransition != null) {
                     FragmentTransitionCompat21.swapSharedElementTargets(sharedElementTransition, sharedElementsOut, sharedElementsIn);
                     View inEpicenterView = FragmentTransition.getInEpicenterView(inSharedElements, fragments, enterTransition, inIsPop);
                     if (inEpicenterView != null) {
                        FragmentTransitionCompat21.getBoundsOnScreen(inEpicenterView, inEpicenter);
                     }
                  }

               }
            });
            return sharedElementTransition;
         }
      } else {
         return null;
      }
   }

   @RequiresApi(21)
   private static ArrayMap captureOutSharedElements(ArrayMap nameOverrides, Object sharedElementTransition, FragmentTransition.FragmentContainerTransition fragments) {
      if (!nameOverrides.isEmpty() && sharedElementTransition != null) {
         Fragment outFragment = fragments.firstOut;
         ArrayMap outSharedElements = new ArrayMap();
         FragmentTransitionCompat21.findNamedViews(outSharedElements, outFragment.getView());
         BackStackRecord outTransaction = fragments.firstOutTransaction;
         SharedElementCallback sharedElementCallback;
         ArrayList names;
         if (fragments.firstOutIsPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.mSharedElementTargetNames;
         } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.mSharedElementSourceNames;
         }

         outSharedElements.retainAll(names);
         if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, outSharedElements);

            for(int i = names.size() - 1; i >= 0; --i) {
               String name = (String)names.get(i);
               View view = (View)outSharedElements.get(name);
               if (view == null) {
                  nameOverrides.remove(name);
               } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                  String targetValue = (String)nameOverrides.remove(name);
                  nameOverrides.put(ViewCompat.getTransitionName(view), targetValue);
               }
            }
         } else {
            nameOverrides.retainAll(outSharedElements.keySet());
         }

         return outSharedElements;
      } else {
         nameOverrides.clear();
         return null;
      }
   }

   @RequiresApi(21)
   private static ArrayMap captureInSharedElements(ArrayMap nameOverrides, Object sharedElementTransition, FragmentTransition.FragmentContainerTransition fragments) {
      Fragment inFragment = fragments.lastIn;
      View fragmentView = inFragment.getView();
      if (!nameOverrides.isEmpty() && sharedElementTransition != null && fragmentView != null) {
         ArrayMap inSharedElements = new ArrayMap();
         FragmentTransitionCompat21.findNamedViews(inSharedElements, fragmentView);
         BackStackRecord inTransaction = fragments.lastInTransaction;
         SharedElementCallback sharedElementCallback;
         ArrayList names;
         if (fragments.lastInIsPop) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.mSharedElementSourceNames;
         } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.mSharedElementTargetNames;
         }

         if (names != null) {
            inSharedElements.retainAll(names);
         }

         if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, inSharedElements);

            for(int i = names.size() - 1; i >= 0; --i) {
               String name = (String)names.get(i);
               View view = (View)inSharedElements.get(name);
               String key;
               if (view == null) {
                  key = findKeyForValue(nameOverrides, name);
                  if (key != null) {
                     nameOverrides.remove(key);
                  }
               } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                  key = findKeyForValue(nameOverrides, name);
                  if (key != null) {
                     nameOverrides.put(key, ViewCompat.getTransitionName(view));
                  }
               }
            }
         } else {
            retainValues(nameOverrides, inSharedElements);
         }

         return inSharedElements;
      } else {
         nameOverrides.clear();
         return null;
      }
   }

   private static String findKeyForValue(ArrayMap map, String value) {
      int numElements = map.size();

      for(int i = 0; i < numElements; ++i) {
         if (value.equals(map.valueAt(i))) {
            return (String)map.keyAt(i);
         }
      }

      return null;
   }

   private static View getInEpicenterView(ArrayMap inSharedElements, FragmentTransition.FragmentContainerTransition fragments, Object enterTransition, boolean inIsPop) {
      BackStackRecord inTransaction = fragments.lastInTransaction;
      if (enterTransition != null && inSharedElements != null && inTransaction.mSharedElementSourceNames != null && !inTransaction.mSharedElementSourceNames.isEmpty()) {
         String targetName = inIsPop ? (String)inTransaction.mSharedElementSourceNames.get(0) : (String)inTransaction.mSharedElementTargetNames.get(0);
         return (View)inSharedElements.get(targetName);
      } else {
         return null;
      }
   }

   @RequiresApi(21)
   private static void setOutEpicenter(Object sharedElementTransition, Object exitTransition, ArrayMap outSharedElements, boolean outIsPop, BackStackRecord outTransaction) {
      if (outTransaction.mSharedElementSourceNames != null && !outTransaction.mSharedElementSourceNames.isEmpty()) {
         String sourceName = outIsPop ? (String)outTransaction.mSharedElementTargetNames.get(0) : (String)outTransaction.mSharedElementSourceNames.get(0);
         View outEpicenterView = (View)outSharedElements.get(sourceName);
         FragmentTransitionCompat21.setEpicenter(sharedElementTransition, outEpicenterView);
         if (exitTransition != null) {
            FragmentTransitionCompat21.setEpicenter(exitTransition, outEpicenterView);
         }
      }

   }

   private static void retainValues(ArrayMap nameOverrides, ArrayMap namedViews) {
      for(int i = nameOverrides.size() - 1; i >= 0; --i) {
         String targetName = (String)nameOverrides.valueAt(i);
         if (!namedViews.containsKey(targetName)) {
            nameOverrides.removeAt(i);
         }
      }

   }

   private static void callSharedElementStartEnd(Fragment inFragment, Fragment outFragment, boolean isPop, ArrayMap sharedElements, boolean isStart) {
      SharedElementCallback sharedElementCallback = isPop ? outFragment.getEnterTransitionCallback() : inFragment.getEnterTransitionCallback();
      if (sharedElementCallback != null) {
         ArrayList views = new ArrayList();
         ArrayList names = new ArrayList();
         int count = sharedElements == null ? 0 : sharedElements.size();

         for(int i = 0; i < count; ++i) {
            names.add(sharedElements.keyAt(i));
            views.add(sharedElements.valueAt(i));
         }

         if (isStart) {
            sharedElementCallback.onSharedElementStart(names, views, (List)null);
         } else {
            sharedElementCallback.onSharedElementEnd(names, views, (List)null);
         }
      }

   }

   @RequiresApi(21)
   private static ArrayList configureEnteringExitingViews(Object transition, Fragment fragment, ArrayList sharedElements, View nonExistentView) {
      ArrayList viewList = null;
      if (transition != null) {
         viewList = new ArrayList();
         View root = fragment.getView();
         if (root != null) {
            FragmentTransitionCompat21.captureTransitioningViews(viewList, root);
         }

         if (sharedElements != null) {
            viewList.removeAll(sharedElements);
         }

         if (!viewList.isEmpty()) {
            viewList.add(nonExistentView);
            FragmentTransitionCompat21.addTargets(transition, viewList);
         }
      }

      return viewList;
   }

   private static void setViewVisibility(ArrayList views, int visibility) {
      if (views != null) {
         for(int i = views.size() - 1; i >= 0; --i) {
            View view = (View)views.get(i);
            view.setVisibility(visibility);
         }

      }
   }

   @RequiresApi(21)
   private static Object mergeTransitions(Object enterTransition, Object exitTransition, Object sharedElementTransition, Fragment inFragment, boolean isPop) {
      boolean overlap = true;
      if (enterTransition != null && exitTransition != null && inFragment != null) {
         overlap = isPop ? inFragment.getAllowReturnTransitionOverlap() : inFragment.getAllowEnterTransitionOverlap();
      }

      Object transition;
      if (overlap) {
         transition = FragmentTransitionCompat21.mergeTransitionsTogether(exitTransition, enterTransition, sharedElementTransition);
      } else {
         transition = FragmentTransitionCompat21.mergeTransitionsInSequence(exitTransition, enterTransition, sharedElementTransition);
      }

      return transition;
   }

   public static void calculateFragments(BackStackRecord transaction, SparseArray transitioningFragments, boolean isReordered) {
      int numOps = transaction.mOps.size();

      for(int opNum = 0; opNum < numOps; ++opNum) {
         BackStackRecord.Op op = (BackStackRecord.Op)transaction.mOps.get(opNum);
         addToFirstInLastOut(transaction, op, transitioningFragments, false, isReordered);
      }

   }

   public static void calculatePopFragments(BackStackRecord transaction, SparseArray transitioningFragments, boolean isReordered) {
      if (transaction.mManager.mContainer.onHasView()) {
         int numOps = transaction.mOps.size();

         for(int opNum = numOps - 1; opNum >= 0; --opNum) {
            BackStackRecord.Op op = (BackStackRecord.Op)transaction.mOps.get(opNum);
            addToFirstInLastOut(transaction, op, transitioningFragments, true, isReordered);
         }

      }
   }

   private static void addToFirstInLastOut(BackStackRecord transaction, BackStackRecord.Op op, SparseArray transitioningFragments, boolean isPop, boolean isReorderedTransaction) {
      Fragment fragment = op.fragment;
      if (fragment != null) {
         int containerId = fragment.mContainerId;
         if (containerId != 0) {
            int command = isPop ? INVERSE_OPS[op.cmd] : op.cmd;
            boolean setLastIn = false;
            boolean wasRemoved = false;
            boolean setFirstOut = false;
            boolean wasAdded = false;
            switch(command) {
            case 1:
            case 7:
               if (isReorderedTransaction) {
                  setLastIn = fragment.mIsNewlyAdded;
               } else {
                  setLastIn = !fragment.mAdded && !fragment.mHidden;
               }

               wasAdded = true;
            case 2:
            default:
               break;
            case 3:
            case 6:
               if (isReorderedTransaction) {
                  setFirstOut = !fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0F;
               } else {
                  setFirstOut = fragment.mAdded && !fragment.mHidden;
               }

               wasRemoved = true;
               break;
            case 4:
               if (isReorderedTransaction) {
                  setFirstOut = fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden;
               } else {
                  setFirstOut = fragment.mAdded && !fragment.mHidden;
               }

               wasRemoved = true;
               break;
            case 5:
               if (!isReorderedTransaction) {
                  setLastIn = fragment.mHidden;
               } else {
                  setLastIn = fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded;
               }

               wasAdded = true;
            }

            FragmentTransition.FragmentContainerTransition containerTransition = (FragmentTransition.FragmentContainerTransition)transitioningFragments.get(containerId);
            if (setLastIn) {
               containerTransition = ensureContainer(containerTransition, transitioningFragments, containerId);
               containerTransition.lastIn = fragment;
               containerTransition.lastInIsPop = isPop;
               containerTransition.lastInTransaction = transaction;
            }

            if (!isReorderedTransaction && wasAdded) {
               if (containerTransition != null && containerTransition.firstOut == fragment) {
                  containerTransition.firstOut = null;
               }

               FragmentManagerImpl manager = transaction.mManager;
               if (fragment.mState < 1 && manager.mCurState >= 1 && !transaction.mReorderingAllowed) {
                  manager.makeActive(fragment);
                  manager.moveToState(fragment, 1, 0, 0, false);
               }
            }

            if (setFirstOut && (containerTransition == null || containerTransition.firstOut == null)) {
               containerTransition = ensureContainer(containerTransition, transitioningFragments, containerId);
               containerTransition.firstOut = fragment;
               containerTransition.firstOutIsPop = isPop;
               containerTransition.firstOutTransaction = transaction;
            }

            if (!isReorderedTransaction && wasRemoved && containerTransition != null && containerTransition.lastIn == fragment) {
               containerTransition.lastIn = null;
            }

         }
      }
   }

   private static FragmentTransition.FragmentContainerTransition ensureContainer(FragmentTransition.FragmentContainerTransition containerTransition, SparseArray transitioningFragments, int containerId) {
      if (containerTransition == null) {
         containerTransition = new FragmentTransition.FragmentContainerTransition();
         transitioningFragments.put(containerId, containerTransition);
      }

      return containerTransition;
   }

   static class FragmentContainerTransition {
      public Fragment lastIn;
      public boolean lastInIsPop;
      public BackStackRecord lastInTransaction;
      public Fragment firstOut;
      public boolean firstOutIsPop;
      public BackStackRecord firstOutTransaction;
   }
}
