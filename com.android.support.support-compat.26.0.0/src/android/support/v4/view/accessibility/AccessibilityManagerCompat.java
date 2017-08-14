package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

public final class AccessibilityManagerCompat {
   /** @deprecated */
   @Deprecated
   public static boolean addAccessibilityStateChangeListener(AccessibilityManager manager, AccessibilityManagerCompat.AccessibilityStateChangeListener listener) {
      return listener == null ? false : manager.addAccessibilityStateChangeListener(new AccessibilityManagerCompat.AccessibilityStateChangeListenerWrapper(listener));
   }

   /** @deprecated */
   @Deprecated
   public static boolean removeAccessibilityStateChangeListener(AccessibilityManager manager, AccessibilityManagerCompat.AccessibilityStateChangeListener listener) {
      return listener == null ? false : manager.removeAccessibilityStateChangeListener(new AccessibilityManagerCompat.AccessibilityStateChangeListenerWrapper(listener));
   }

   /** @deprecated */
   @Deprecated
   public static List getInstalledAccessibilityServiceList(AccessibilityManager manager) {
      return manager.getInstalledAccessibilityServiceList();
   }

   /** @deprecated */
   @Deprecated
   public static List getEnabledAccessibilityServiceList(AccessibilityManager manager, int feedbackTypeFlags) {
      return manager.getEnabledAccessibilityServiceList(feedbackTypeFlags);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isTouchExplorationEnabled(AccessibilityManager manager) {
      return manager.isTouchExplorationEnabled();
   }

   public static boolean addTouchExplorationStateChangeListener(AccessibilityManager manager, AccessibilityManagerCompat.TouchExplorationStateChangeListener listener) {
      if (VERSION.SDK_INT >= 19) {
         return listener == null ? false : manager.addTouchExplorationStateChangeListener(new AccessibilityManagerCompat.TouchExplorationStateChangeListenerWrapper(listener));
      } else {
         return false;
      }
   }

   public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager manager, AccessibilityManagerCompat.TouchExplorationStateChangeListener listener) {
      if (VERSION.SDK_INT >= 19) {
         return listener == null ? false : manager.removeTouchExplorationStateChangeListener(new AccessibilityManagerCompat.TouchExplorationStateChangeListenerWrapper(listener));
      } else {
         return false;
      }
   }

   public interface TouchExplorationStateChangeListener {
      void onTouchExplorationStateChanged(boolean var1);
   }

   /** @deprecated */
   @Deprecated
   public interface AccessibilityStateChangeListener {
      /** @deprecated */
      @Deprecated
      void onAccessibilityStateChanged(boolean var1);
   }

   /** @deprecated */
   @Deprecated
   public abstract static class AccessibilityStateChangeListenerCompat implements AccessibilityManagerCompat.AccessibilityStateChangeListener {
   }

   @RequiresApi(19)
   private static class TouchExplorationStateChangeListenerWrapper implements android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener {
      final AccessibilityManagerCompat.TouchExplorationStateChangeListener mListener;

      TouchExplorationStateChangeListenerWrapper(@NonNull AccessibilityManagerCompat.TouchExplorationStateChangeListener listener) {
         this.mListener = listener;
      }

      public int hashCode() {
         return this.mListener.hashCode();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AccessibilityManagerCompat.TouchExplorationStateChangeListenerWrapper other = (AccessibilityManagerCompat.TouchExplorationStateChangeListenerWrapper)o;
            return this.mListener.equals(other.mListener);
         } else {
            return false;
         }
      }

      public void onTouchExplorationStateChanged(boolean enabled) {
         this.mListener.onTouchExplorationStateChanged(enabled);
      }
   }

   private static class AccessibilityStateChangeListenerWrapper implements android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener {
      AccessibilityManagerCompat.AccessibilityStateChangeListener mListener;

      AccessibilityStateChangeListenerWrapper(@NonNull AccessibilityManagerCompat.AccessibilityStateChangeListener listener) {
         this.mListener = listener;
      }

      public int hashCode() {
         return this.mListener.hashCode();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AccessibilityManagerCompat.AccessibilityStateChangeListenerWrapper other = (AccessibilityManagerCompat.AccessibilityStateChangeListenerWrapper)o;
            return this.mListener.equals(other.mListener);
         } else {
            return false;
         }
      }

      public void onAccessibilityStateChanged(boolean enabled) {
         this.mListener.onAccessibilityStateChanged(enabled);
      }
   }
}
