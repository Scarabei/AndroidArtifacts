package com.google.android.gms.maps.model;

public final class Gap extends PatternItem {
   public final float length;

   public Gap(float var1) {
      super(2, Math.max(var1, 0.0F));
      this.length = Math.max(var1, 0.0F);
   }

   public final String toString() {
      float var1 = this.length;
      return (new StringBuilder(29)).append("[Gap: length=").append(var1).append("]").toString();
   }
}
