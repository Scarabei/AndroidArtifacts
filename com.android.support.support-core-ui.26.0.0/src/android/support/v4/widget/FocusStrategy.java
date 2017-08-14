package android.support.v4.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy {
   public static Object findNextFocusInRelativeDirection(@NonNull Object focusables, @NonNull FocusStrategy.CollectionAdapter collectionAdapter, @NonNull FocusStrategy.BoundsAdapter adapter, @Nullable Object focused, int direction, boolean isLayoutRtl, boolean wrap) {
      int count = collectionAdapter.size(focusables);
      ArrayList sortedFocusables = new ArrayList(count);

      for(int i = 0; i < count; ++i) {
         sortedFocusables.add(collectionAdapter.get(focusables, i));
      }

      FocusStrategy.SequentialComparator comparator = new FocusStrategy.SequentialComparator(isLayoutRtl, adapter);
      Collections.sort(sortedFocusables, comparator);
      switch(direction) {
      case 1:
         return getPreviousFocusable(focused, sortedFocusables, wrap);
      case 2:
         return getNextFocusable(focused, sortedFocusables, wrap);
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
      }
   }

   private static Object getNextFocusable(Object focused, ArrayList focusables, boolean wrap) {
      int count = focusables.size();
      int position = (focused == null ? -1 : focusables.lastIndexOf(focused)) + 1;
      if (position < count) {
         return focusables.get(position);
      } else {
         return wrap && count > 0 ? focusables.get(0) : null;
      }
   }

   private static Object getPreviousFocusable(Object focused, ArrayList focusables, boolean wrap) {
      int count = focusables.size();
      int position = (focused == null ? count : focusables.indexOf(focused)) - 1;
      if (position >= 0) {
         return focusables.get(position);
      } else {
         return wrap && count > 0 ? focusables.get(count - 1) : null;
      }
   }

   public static Object findNextFocusInAbsoluteDirection(@NonNull Object focusables, @NonNull FocusStrategy.CollectionAdapter collectionAdapter, @NonNull FocusStrategy.BoundsAdapter adapter, @Nullable Object focused, @NonNull Rect focusedRect, int direction) {
      Rect bestCandidateRect = new Rect(focusedRect);
      switch(direction) {
      case 17:
         bestCandidateRect.offset(focusedRect.width() + 1, 0);
         break;
      case 33:
         bestCandidateRect.offset(0, focusedRect.height() + 1);
         break;
      case 66:
         bestCandidateRect.offset(-(focusedRect.width() + 1), 0);
         break;
      case 130:
         bestCandidateRect.offset(0, -(focusedRect.height() + 1));
         break;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }

      Object closest = null;
      int count = collectionAdapter.size(focusables);
      Rect focusableRect = new Rect();

      for(int i = 0; i < count; ++i) {
         Object focusable = collectionAdapter.get(focusables, i);
         if (focusable != focused) {
            adapter.obtainBounds(focusable, focusableRect);
            if (isBetterCandidate(direction, focusedRect, focusableRect, bestCandidateRect)) {
               bestCandidateRect.set(focusableRect);
               closest = focusable;
            }
         }
      }

      return closest;
   }

   private static boolean isBetterCandidate(int direction, @NonNull Rect source, @NonNull Rect candidate, @NonNull Rect currentBest) {
      if (!isCandidate(source, candidate, direction)) {
         return false;
      } else if (!isCandidate(source, currentBest, direction)) {
         return true;
      } else if (beamBeats(direction, source, candidate, currentBest)) {
         return true;
      } else if (beamBeats(direction, source, currentBest, candidate)) {
         return false;
      } else {
         int candidateDist = getWeightedDistanceFor(majorAxisDistance(direction, source, candidate), minorAxisDistance(direction, source, candidate));
         int currentBestDist = getWeightedDistanceFor(majorAxisDistance(direction, source, currentBest), minorAxisDistance(direction, source, currentBest));
         return candidateDist < currentBestDist;
      }
   }

   private static boolean beamBeats(int direction, @NonNull Rect source, @NonNull Rect rect1, @NonNull Rect rect2) {
      boolean rect1InSrcBeam = beamsOverlap(direction, source, rect1);
      boolean rect2InSrcBeam = beamsOverlap(direction, source, rect2);
      if (!rect2InSrcBeam && rect1InSrcBeam) {
         if (!isToDirectionOf(direction, source, rect2)) {
            return true;
         } else if (direction != 17 && direction != 66) {
            return majorAxisDistance(direction, source, rect1) < majorAxisDistanceToFarEdge(direction, source, rect2);
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   private static int getWeightedDistanceFor(int majorAxisDistance, int minorAxisDistance) {
      return 13 * majorAxisDistance * majorAxisDistance + minorAxisDistance * minorAxisDistance;
   }

   private static boolean isCandidate(@NonNull Rect srcRect, @NonNull Rect destRect, int direction) {
      switch(direction) {
      case 17:
         return (srcRect.right > destRect.right || srcRect.left >= destRect.right) && srcRect.left > destRect.left;
      case 33:
         return (srcRect.bottom > destRect.bottom || srcRect.top >= destRect.bottom) && srcRect.top > destRect.top;
      case 66:
         return (srcRect.left < destRect.left || srcRect.right <= destRect.left) && srcRect.right < destRect.right;
      case 130:
         return (srcRect.top < destRect.top || srcRect.bottom <= destRect.top) && srcRect.bottom < destRect.bottom;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   private static boolean beamsOverlap(int direction, @NonNull Rect rect1, @NonNull Rect rect2) {
      switch(direction) {
      case 17:
      case 66:
         return rect2.bottom >= rect1.top && rect2.top <= rect1.bottom;
      case 33:
      case 130:
         return rect2.right >= rect1.left && rect2.left <= rect1.right;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   private static boolean isToDirectionOf(int direction, @NonNull Rect src, @NonNull Rect dest) {
      switch(direction) {
      case 17:
         return src.left >= dest.right;
      case 33:
         return src.top >= dest.bottom;
      case 66:
         return src.right <= dest.left;
      case 130:
         return src.bottom <= dest.top;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   private static int majorAxisDistance(int direction, @NonNull Rect source, @NonNull Rect dest) {
      return Math.max(0, majorAxisDistanceRaw(direction, source, dest));
   }

   private static int majorAxisDistanceRaw(int direction, @NonNull Rect source, @NonNull Rect dest) {
      switch(direction) {
      case 17:
         return source.left - dest.right;
      case 33:
         return source.top - dest.bottom;
      case 66:
         return dest.left - source.right;
      case 130:
         return dest.top - source.bottom;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   private static int majorAxisDistanceToFarEdge(int direction, @NonNull Rect source, @NonNull Rect dest) {
      return Math.max(1, majorAxisDistanceToFarEdgeRaw(direction, source, dest));
   }

   private static int majorAxisDistanceToFarEdgeRaw(int direction, @NonNull Rect source, @NonNull Rect dest) {
      switch(direction) {
      case 17:
         return source.left - dest.left;
      case 33:
         return source.top - dest.top;
      case 66:
         return dest.right - source.right;
      case 130:
         return dest.bottom - source.bottom;
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   private static int minorAxisDistance(int direction, @NonNull Rect source, @NonNull Rect dest) {
      switch(direction) {
      case 17:
      case 66:
         return Math.abs(source.top + source.height() / 2 - (dest.top + dest.height() / 2));
      case 33:
      case 130:
         return Math.abs(source.left + source.width() / 2 - (dest.left + dest.width() / 2));
      default:
         throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
   }

   public interface CollectionAdapter {
      Object get(Object var1, int var2);

      int size(Object var1);
   }

   public interface BoundsAdapter {
      void obtainBounds(Object var1, Rect var2);
   }

   private static class SequentialComparator implements Comparator {
      private final Rect mTemp1 = new Rect();
      private final Rect mTemp2 = new Rect();
      private final boolean mIsLayoutRtl;
      private final FocusStrategy.BoundsAdapter mAdapter;

      SequentialComparator(boolean isLayoutRtl, FocusStrategy.BoundsAdapter adapter) {
         this.mIsLayoutRtl = isLayoutRtl;
         this.mAdapter = adapter;
      }

      public int compare(Object first, Object second) {
         Rect firstRect = this.mTemp1;
         Rect secondRect = this.mTemp2;
         this.mAdapter.obtainBounds(first, firstRect);
         this.mAdapter.obtainBounds(second, secondRect);
         if (firstRect.top < secondRect.top) {
            return -1;
         } else if (firstRect.top > secondRect.top) {
            return 1;
         } else if (firstRect.left < secondRect.left) {
            return this.mIsLayoutRtl ? 1 : -1;
         } else if (firstRect.left > secondRect.left) {
            return this.mIsLayoutRtl ? -1 : 1;
         } else if (firstRect.bottom < secondRect.bottom) {
            return -1;
         } else if (firstRect.bottom > secondRect.bottom) {
            return 1;
         } else if (firstRect.right < secondRect.right) {
            return this.mIsLayoutRtl ? 1 : -1;
         } else if (firstRect.right > secondRect.right) {
            return this.mIsLayoutRtl ? -1 : 1;
         } else {
            return 0;
         }
      }
   }
}
