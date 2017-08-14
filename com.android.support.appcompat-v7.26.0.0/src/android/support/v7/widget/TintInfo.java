package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

class TintInfo {
   public ColorStateList mTintList;
   public Mode mTintMode;
   public boolean mHasTintMode;
   public boolean mHasTintList;

   void clear() {
      this.mTintList = null;
      this.mHasTintList = false;
      this.mTintMode = null;
      this.mHasTintMode = false;
   }
}
