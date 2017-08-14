package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.WindowInsets;

public class WindowInsetsCompat {
   private final Object mInsets;

   private WindowInsetsCompat(Object insets) {
      this.mInsets = insets;
   }

   public WindowInsetsCompat(WindowInsetsCompat src) {
      if (VERSION.SDK_INT >= 20) {
         this.mInsets = src == null ? null : new WindowInsets((WindowInsets)src.mInsets);
      } else {
         this.mInsets = null;
      }

   }

   public int getSystemWindowInsetLeft() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).getSystemWindowInsetLeft() : 0;
   }

   public int getSystemWindowInsetTop() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).getSystemWindowInsetTop() : 0;
   }

   public int getSystemWindowInsetRight() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).getSystemWindowInsetRight() : 0;
   }

   public int getSystemWindowInsetBottom() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).getSystemWindowInsetBottom() : 0;
   }

   public boolean hasSystemWindowInsets() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).hasSystemWindowInsets() : false;
   }

   public boolean hasInsets() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).hasInsets() : false;
   }

   public boolean isConsumed() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).isConsumed() : false;
   }

   public boolean isRound() {
      return VERSION.SDK_INT >= 20 ? ((WindowInsets)this.mInsets).isRound() : false;
   }

   public WindowInsetsCompat consumeSystemWindowInsets() {
      return VERSION.SDK_INT >= 20 ? new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeSystemWindowInsets()) : null;
   }

   public WindowInsetsCompat replaceSystemWindowInsets(int left, int top, int right, int bottom) {
      return VERSION.SDK_INT >= 20 ? new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(left, top, right, bottom)) : null;
   }

   public WindowInsetsCompat replaceSystemWindowInsets(Rect systemWindowInsets) {
      return VERSION.SDK_INT >= 21 ? new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(systemWindowInsets)) : null;
   }

   public int getStableInsetTop() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).getStableInsetTop() : 0;
   }

   public int getStableInsetLeft() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).getStableInsetLeft() : 0;
   }

   public int getStableInsetRight() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).getStableInsetRight() : 0;
   }

   public int getStableInsetBottom() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).getStableInsetBottom() : 0;
   }

   public boolean hasStableInsets() {
      return VERSION.SDK_INT >= 21 ? ((WindowInsets)this.mInsets).hasStableInsets() : false;
   }

   public WindowInsetsCompat consumeStableInsets() {
      return VERSION.SDK_INT >= 21 ? new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeStableInsets()) : null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         WindowInsetsCompat other = (WindowInsetsCompat)o;
         return this.mInsets == null ? other.mInsets == null : this.mInsets.equals(other.mInsets);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.mInsets == null ? 0 : this.mInsets.hashCode();
   }

   static WindowInsetsCompat wrap(Object insets) {
      return insets == null ? null : new WindowInsetsCompat(insets);
   }

   static Object unwrap(WindowInsetsCompat insets) {
      return insets == null ? null : insets.mInsets;
   }
}
