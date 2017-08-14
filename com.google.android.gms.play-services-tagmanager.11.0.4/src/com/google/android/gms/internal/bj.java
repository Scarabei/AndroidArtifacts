package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.common.internal.zzbo;

public final class bj implements zzcxo {
   private Context mContext;
   private DisplayMetrics zzxF;

   public bj(Context var1) {
      this.mContext = var1;
      this.zzxF = new DisplayMetrics();
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(this.zzxF);
      StringBuilder var3;
      (var3 = new StringBuilder()).append(this.zzxF.widthPixels);
      var3.append("x");
      var3.append(this.zzxF.heightPixels);
      return new eb(var3.toString());
   }
}
