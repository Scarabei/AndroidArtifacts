package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

final class zzqu implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      WindowManager var3 = (WindowManager)var1.getContext().getSystemService("window");
      com.google.android.gms.ads.internal.zzbs.zzbz();
      View var5 = (View)var1;
      DisplayMetrics var7;
      int var8 = (var7 = zzagz.zza(var3)).widthPixels;
      int var9 = var7.heightPixels;
      int[] var10 = new int[2];
      HashMap var11 = new HashMap();
      var5.getLocationInWindow(var10);
      var11.put("xInPixels", var10[0]);
      var11.put("yInPixels", var10[1]);
      var11.put("windowWidthInPixels", var8);
      var11.put("windowHeightInPixels", var9);
      var1.zza("locationReady", (Map)var11);
      zzafr.zzaT("GET LOCATION COMPILED");
   }
}
