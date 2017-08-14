package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;

public final class CustomCap extends Cap {
   public final BitmapDescriptor bitmapDescriptor;
   public final float refWidth;

   public CustomCap(@NonNull BitmapDescriptor var1, float var2) {
      BitmapDescriptor var10001 = (BitmapDescriptor)zzbo.zzb(var1, "bitmapDescriptor must not be null");
      String var3 = "refWidth must be positive";
      if (var2 <= 0.0F) {
         throw new IllegalArgumentException(var3);
      } else {
         super(var10001, var2);
         this.bitmapDescriptor = var1;
         this.refWidth = var2;
      }
   }

   public CustomCap(@NonNull BitmapDescriptor var1) {
      this(var1, 10.0F);
   }

   public final String toString() {
      String var1 = String.valueOf(this.bitmapDescriptor);
      float var2 = this.refWidth;
      return (new StringBuilder(55 + String.valueOf(var1).length())).append("[CustomCap: bitmapDescriptor=").append(var1).append(" refWidth=").append(var2).append("]").toString();
   }
}
