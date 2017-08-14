package android.support.v4.view;

import android.view.KeyEvent;
import android.view.View;
import android.view.KeyEvent.Callback;
import android.view.KeyEvent.DispatcherState;

/** @deprecated */
@Deprecated
public final class KeyEventCompat {
   /** @deprecated */
   @Deprecated
   public static int normalizeMetaState(int metaState) {
      return KeyEvent.normalizeMetaState(metaState);
   }

   /** @deprecated */
   @Deprecated
   public static boolean metaStateHasModifiers(int metaState, int modifiers) {
      return KeyEvent.metaStateHasModifiers(metaState, modifiers);
   }

   /** @deprecated */
   @Deprecated
   public static boolean metaStateHasNoModifiers(int metaState) {
      return KeyEvent.metaStateHasNoModifiers(metaState);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasModifiers(KeyEvent event, int modifiers) {
      return event.hasModifiers(modifiers);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasNoModifiers(KeyEvent event) {
      return event.hasNoModifiers();
   }

   /** @deprecated */
   @Deprecated
   public static void startTracking(KeyEvent event) {
      event.startTracking();
   }

   /** @deprecated */
   @Deprecated
   public static boolean isTracking(KeyEvent event) {
      return event.isTracking();
   }

   /** @deprecated */
   @Deprecated
   public static Object getKeyDispatcherState(View view) {
      return view.getKeyDispatcherState();
   }

   /** @deprecated */
   @Deprecated
   public static boolean dispatch(KeyEvent event, Callback receiver, Object state, Object target) {
      return event.dispatch(receiver, (DispatcherState)state, target);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isCtrlPressed(KeyEvent event) {
      return event.isCtrlPressed();
   }
}
