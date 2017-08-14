package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat {
   private static final String TAG = "ViewParentCompat";
   static final ViewParentCompat.ViewParentCompatBaseImpl IMPL;

   /** @deprecated */
   @Deprecated
   public static boolean requestSendAccessibilityEvent(ViewParent parent, View child, AccessibilityEvent event) {
      return parent.requestSendAccessibilityEvent(child, event);
   }

   public static boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes) {
      return onStartNestedScroll(parent, child, target, nestedScrollAxes, 0);
   }

   public static void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes) {
      onNestedScrollAccepted(parent, child, target, nestedScrollAxes, 0);
   }

   public static void onStopNestedScroll(ViewParent parent, View target) {
      onStopNestedScroll(parent, target, 0);
   }

   public static void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
      onNestedScroll(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
   }

   public static void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed) {
      onNestedPreScroll(parent, target, dx, dy, consumed, 0);
   }

   public static boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
      if (parent instanceof NestedScrollingParent2) {
         return ((NestedScrollingParent2)parent).onStartNestedScroll(child, target, nestedScrollAxes, type);
      } else {
         return type == 0 ? IMPL.onStartNestedScroll(parent, child, target, nestedScrollAxes) : false;
      }
   }

   public static void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
      if (parent instanceof NestedScrollingParent2) {
         ((NestedScrollingParent2)parent).onNestedScrollAccepted(child, target, nestedScrollAxes, type);
      } else if (type == 0) {
         IMPL.onNestedScrollAccepted(parent, child, target, nestedScrollAxes);
      }

   }

   public static void onStopNestedScroll(ViewParent parent, View target, int type) {
      if (parent instanceof NestedScrollingParent2) {
         ((NestedScrollingParent2)parent).onStopNestedScroll(target, type);
      } else if (type == 0) {
         IMPL.onStopNestedScroll(parent, target);
      }

   }

   public static void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
      if (parent instanceof NestedScrollingParent2) {
         ((NestedScrollingParent2)parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
      } else if (type == 0) {
         IMPL.onNestedScroll(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
      }

   }

   public static void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed, int type) {
      if (parent instanceof NestedScrollingParent2) {
         ((NestedScrollingParent2)parent).onNestedPreScroll(target, dx, dy, consumed, type);
      } else if (type == 0) {
         IMPL.onNestedPreScroll(parent, target, dx, dy, consumed);
      }

   }

   public static boolean onNestedFling(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
      return IMPL.onNestedFling(parent, target, velocityX, velocityY, consumed);
   }

   public static boolean onNestedPreFling(ViewParent parent, View target, float velocityX, float velocityY) {
      return IMPL.onNestedPreFling(parent, target, velocityX, velocityY);
   }

   public static void notifySubtreeAccessibilityStateChanged(ViewParent parent, View child, View source, int changeType) {
      IMPL.notifySubtreeAccessibilityStateChanged(parent, child, source, changeType);
   }

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new ViewParentCompat.ViewParentCompatApi21Impl();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new ViewParentCompat.ViewParentCompatApi19Impl();
      } else {
         IMPL = new ViewParentCompat.ViewParentCompatBaseImpl();
      }

   }

   @RequiresApi(21)
   static class ViewParentCompatApi21Impl extends ViewParentCompat.ViewParentCompatApi19Impl {
      public boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes) {
         try {
            return parent.onStartNestedScroll(child, target, nestedScrollAxes);
         } catch (AbstractMethodError var6) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onStartNestedScroll", var6);
            return false;
         }
      }

      public void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes) {
         try {
            parent.onNestedScrollAccepted(child, target, nestedScrollAxes);
         } catch (AbstractMethodError var6) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onNestedScrollAccepted", var6);
         }

      }

      public void onStopNestedScroll(ViewParent parent, View target) {
         try {
            parent.onStopNestedScroll(target);
         } catch (AbstractMethodError var4) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onStopNestedScroll", var4);
         }

      }

      public void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
         try {
            parent.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
         } catch (AbstractMethodError var8) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onNestedScroll", var8);
         }

      }

      public void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed) {
         try {
            parent.onNestedPreScroll(target, dx, dy, consumed);
         } catch (AbstractMethodError var7) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onNestedPreScroll", var7);
         }

      }

      public boolean onNestedFling(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
         try {
            return parent.onNestedFling(target, velocityX, velocityY, consumed);
         } catch (AbstractMethodError var7) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onNestedFling", var7);
            return false;
         }
      }

      public boolean onNestedPreFling(ViewParent parent, View target, float velocityX, float velocityY) {
         try {
            return parent.onNestedPreFling(target, velocityX, velocityY);
         } catch (AbstractMethodError var6) {
            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface " + "method onNestedPreFling", var6);
            return false;
         }
      }
   }

   @RequiresApi(19)
   static class ViewParentCompatApi19Impl extends ViewParentCompat.ViewParentCompatBaseImpl {
      public void notifySubtreeAccessibilityStateChanged(ViewParent parent, View child, View source, int changeType) {
         parent.notifySubtreeAccessibilityStateChanged(child, source, changeType);
      }
   }

   static class ViewParentCompatBaseImpl {
      public boolean onStartNestedScroll(ViewParent parent, View child, View target, int nestedScrollAxes) {
         return parent instanceof NestedScrollingParent ? ((NestedScrollingParent)parent).onStartNestedScroll(child, target, nestedScrollAxes) : false;
      }

      public void onNestedScrollAccepted(ViewParent parent, View child, View target, int nestedScrollAxes) {
         if (parent instanceof NestedScrollingParent) {
            ((NestedScrollingParent)parent).onNestedScrollAccepted(child, target, nestedScrollAxes);
         }

      }

      public void onStopNestedScroll(ViewParent parent, View target) {
         if (parent instanceof NestedScrollingParent) {
            ((NestedScrollingParent)parent).onStopNestedScroll(target);
         }

      }

      public void onNestedScroll(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
         if (parent instanceof NestedScrollingParent) {
            ((NestedScrollingParent)parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
         }

      }

      public void onNestedPreScroll(ViewParent parent, View target, int dx, int dy, int[] consumed) {
         if (parent instanceof NestedScrollingParent) {
            ((NestedScrollingParent)parent).onNestedPreScroll(target, dx, dy, consumed);
         }

      }

      public boolean onNestedFling(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
         return parent instanceof NestedScrollingParent ? ((NestedScrollingParent)parent).onNestedFling(target, velocityX, velocityY, consumed) : false;
      }

      public boolean onNestedPreFling(ViewParent parent, View target, float velocityX, float velocityY) {
         return parent instanceof NestedScrollingParent ? ((NestedScrollingParent)parent).onNestedPreFling(target, velocityX, velocityY) : false;
      }

      public void notifySubtreeAccessibilityStateChanged(ViewParent parent, View child, View source, int changeType) {
      }
   }
}
