package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzbo;

public final class ai implements zzcxo {
   private Context mContext;

   public ai(Context var1) {
      this.mContext = var1;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);

      try {
         PackageManager var3;
         ApplicationInfo var4 = (var3 = this.mContext.getPackageManager()).getApplicationInfo(this.mContext.getPackageName(), 0);
         return new eb(var3.getApplicationLabel(var4).toString());
      } catch (NameNotFoundException var5) {
         return new eb("");
      }
   }
}
