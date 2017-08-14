package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
   private Object mInfo;
   private static final int UNDEFINED = -1;
   public static final int TYPE_APPLICATION = 1;
   public static final int TYPE_INPUT_METHOD = 2;
   public static final int TYPE_SYSTEM = 3;
   public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
   public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;

   static AccessibilityWindowInfoCompat wrapNonNullInstance(Object object) {
      return object != null ? new AccessibilityWindowInfoCompat(object) : null;
   }

   private AccessibilityWindowInfoCompat(Object info) {
      this.mInfo = info;
   }

   public int getType() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getType() : -1;
   }

   public int getLayer() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getLayer() : -1;
   }

   public AccessibilityNodeInfoCompat getRoot() {
      return VERSION.SDK_INT >= 21 ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getRoot()) : null;
   }

   public AccessibilityWindowInfoCompat getParent() {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getParent()) : null;
   }

   public int getId() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getId() : -1;
   }

   public void getBoundsInScreen(Rect outBounds) {
      if (VERSION.SDK_INT >= 21) {
         ((AccessibilityWindowInfo)this.mInfo).getBoundsInScreen(outBounds);
      }

   }

   public boolean isActive() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isActive() : true;
   }

   public boolean isFocused() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isFocused() : true;
   }

   public boolean isAccessibilityFocused() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).isAccessibilityFocused() : true;
   }

   public int getChildCount() {
      return VERSION.SDK_INT >= 21 ? ((AccessibilityWindowInfo)this.mInfo).getChildCount() : 0;
   }

   public AccessibilityWindowInfoCompat getChild(int index) {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getChild(index)) : null;
   }

   public CharSequence getTitle() {
      return VERSION.SDK_INT >= 24 ? ((AccessibilityWindowInfo)this.mInfo).getTitle() : null;
   }

   public AccessibilityNodeInfoCompat getAnchor() {
      return VERSION.SDK_INT >= 24 ? AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)this.mInfo).getAnchor()) : null;
   }

   public static AccessibilityWindowInfoCompat obtain() {
      return VERSION.SDK_INT >= 21 ? wrapNonNullInstance(AccessibilityWindowInfo.obtain()) : null;
   }

   public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat info) {
      if (VERSION.SDK_INT >= 21) {
         return info == null ? null : wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)info.mInfo));
      } else {
         return null;
      }
   }

   public void recycle() {
      if (VERSION.SDK_INT >= 21) {
         ((AccessibilityWindowInfo)this.mInfo).recycle();
      }

   }

   public int hashCode() {
      return this.mInfo == null ? 0 : this.mInfo.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AccessibilityWindowInfoCompat other = (AccessibilityWindowInfoCompat)obj;
         if (this.mInfo == null) {
            if (other.mInfo != null) {
               return false;
            }
         } else if (!this.mInfo.equals(other.mInfo)) {
            return false;
         }

         return true;
      }
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      Rect bounds = new Rect();
      this.getBoundsInScreen(bounds);
      builder.append("AccessibilityWindowInfo[");
      builder.append("id=").append(this.getId());
      builder.append(", type=").append(typeToString(this.getType()));
      builder.append(", layer=").append(this.getLayer());
      builder.append(", bounds=").append(bounds);
      builder.append(", focused=").append(this.isFocused());
      builder.append(", active=").append(this.isActive());
      builder.append(", hasParent=").append(this.getParent() != null);
      builder.append(", hasChildren=").append(this.getChildCount() > 0);
      builder.append(']');
      return builder.toString();
   }

   private static String typeToString(int type) {
      switch(type) {
      case 1:
         return "TYPE_APPLICATION";
      case 2:
         return "TYPE_INPUT_METHOD";
      case 3:
         return "TYPE_SYSTEM";
      case 4:
         return "TYPE_ACCESSIBILITY_OVERLAY";
      default:
         return "<UNKNOWN>";
      }
   }
}
