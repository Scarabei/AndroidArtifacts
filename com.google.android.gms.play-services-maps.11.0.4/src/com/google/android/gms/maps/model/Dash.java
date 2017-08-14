package com.google.android.gms.maps.model;

public final class Dash extends PatternItem {
   public final float length;

   public Dash(float var1) {
      super(0, Math.max(var1, 0.0F));
      this.length = Math.max(var1, 0.0F);
   }

   public final String toString() {
      float var1 = this.length;
      return (new StringBuilder(30)).append("[Dash: length=").append(var1).append("]").toString();
   }
}
