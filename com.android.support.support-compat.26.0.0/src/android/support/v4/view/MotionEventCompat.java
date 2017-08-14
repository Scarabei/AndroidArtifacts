package android.support.v4.view;

import android.view.MotionEvent;

public final class MotionEventCompat {
   /** @deprecated */
   @Deprecated
   public static final int ACTION_MASK = 255;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_POINTER_DOWN = 5;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_POINTER_UP = 6;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_HOVER_MOVE = 7;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_SCROLL = 8;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_POINTER_INDEX_MASK = 65280;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_POINTER_INDEX_SHIFT = 8;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_HOVER_ENTER = 9;
   /** @deprecated */
   @Deprecated
   public static final int ACTION_HOVER_EXIT = 10;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_X = 0;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_Y = 1;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_PRESSURE = 2;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_SIZE = 3;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_TOUCH_MAJOR = 4;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_TOUCH_MINOR = 5;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_TOOL_MAJOR = 6;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_TOOL_MINOR = 7;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_ORIENTATION = 8;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_VSCROLL = 9;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_HSCROLL = 10;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_Z = 11;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_RX = 12;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_RY = 13;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_RZ = 14;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_HAT_X = 15;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_HAT_Y = 16;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_LTRIGGER = 17;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_RTRIGGER = 18;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_THROTTLE = 19;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_RUDDER = 20;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_WHEEL = 21;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GAS = 22;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_BRAKE = 23;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_DISTANCE = 24;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_TILT = 25;
   public static final int AXIS_SCROLL = 26;
   public static final int AXIS_RELATIVE_X = 27;
   public static final int AXIS_RELATIVE_Y = 28;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_1 = 32;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_2 = 33;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_3 = 34;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_4 = 35;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_5 = 36;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_6 = 37;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_7 = 38;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_8 = 39;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_9 = 40;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_10 = 41;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_11 = 42;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_12 = 43;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_13 = 44;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_14 = 45;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_15 = 46;
   /** @deprecated */
   @Deprecated
   public static final int AXIS_GENERIC_16 = 47;
   /** @deprecated */
   @Deprecated
   public static final int BUTTON_PRIMARY = 1;

   /** @deprecated */
   @Deprecated
   public static int getActionMasked(MotionEvent event) {
      return event.getActionMasked();
   }

   /** @deprecated */
   @Deprecated
   public static int getActionIndex(MotionEvent event) {
      return event.getActionIndex();
   }

   /** @deprecated */
   @Deprecated
   public static int findPointerIndex(MotionEvent event, int pointerId) {
      return event.findPointerIndex(pointerId);
   }

   /** @deprecated */
   @Deprecated
   public static int getPointerId(MotionEvent event, int pointerIndex) {
      return event.getPointerId(pointerIndex);
   }

   /** @deprecated */
   @Deprecated
   public static float getX(MotionEvent event, int pointerIndex) {
      return event.getX(pointerIndex);
   }

   /** @deprecated */
   @Deprecated
   public static float getY(MotionEvent event, int pointerIndex) {
      return event.getY(pointerIndex);
   }

   /** @deprecated */
   @Deprecated
   public static int getPointerCount(MotionEvent event) {
      return event.getPointerCount();
   }

   /** @deprecated */
   @Deprecated
   public static int getSource(MotionEvent event) {
      return event.getSource();
   }

   public static boolean isFromSource(MotionEvent event, int source) {
      return (event.getSource() & source) == source;
   }

   /** @deprecated */
   @Deprecated
   public static float getAxisValue(MotionEvent event, int axis) {
      return event.getAxisValue(axis);
   }

   /** @deprecated */
   @Deprecated
   public static float getAxisValue(MotionEvent event, int axis, int pointerIndex) {
      return event.getAxisValue(axis, pointerIndex);
   }

   /** @deprecated */
   @Deprecated
   public static int getButtonState(MotionEvent event) {
      return event.getButtonState();
   }
}
