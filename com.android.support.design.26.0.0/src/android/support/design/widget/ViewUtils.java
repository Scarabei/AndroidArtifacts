package android.support.design.widget;

import android.graphics.PorterDuff.Mode;

class ViewUtils {
   static Mode parseTintMode(int value, Mode defaultMode) {
      switch(value) {
      case 3:
         return Mode.SRC_OVER;
      case 4:
      case 6:
      case 7:
      case 8:
      case 10:
      case 11:
      case 12:
      case 13:
      default:
         return defaultMode;
      case 5:
         return Mode.SRC_IN;
      case 9:
         return Mode.SRC_ATOP;
      case 14:
         return Mode.MULTIPLY;
      case 15:
         return Mode.SCREEN;
      }
   }
}
