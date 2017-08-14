package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class zzp {
   public IBinder zzbaM;
   public int gravity;
   public int zzbaN;
   public int left;
   public int top;
   public int right;
   public int bottom;

   private zzp(int var1, IBinder var2) {
      this.zzbaN = -1;
      this.left = 0;
      this.top = 0;
      this.right = 0;
      this.bottom = 0;
      this.gravity = var1;
      this.zzbaM = var2;
   }

   public final Bundle zzuW() {
      Bundle var1;
      (var1 = new Bundle()).putInt("popupLocationInfo.gravity", this.gravity);
      var1.putInt("popupLocationInfo.displayId", this.zzbaN);
      var1.putInt("popupLocationInfo.left", this.left);
      var1.putInt("popupLocationInfo.top", this.top);
      var1.putInt("popupLocationInfo.right", this.right);
      var1.putInt("popupLocationInfo.bottom", this.bottom);
      return var1;
   }

   // $FF: synthetic method
   zzp(int var1, IBinder var2, zzo var3) {
      this(var1, var2);
   }
}
