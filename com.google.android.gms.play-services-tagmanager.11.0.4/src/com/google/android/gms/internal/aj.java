package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzbo;

public final class aj implements zzcxo {
   private final Context mContext;

   public aj(Context var1) {
      this.mContext = (Context)zzbo.zzu(var1);
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);

      try {
         PackageInfo var3 = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
         return new dt((double)var3.versionCode);
      } catch (NameNotFoundException var6) {
         String var4 = String.valueOf(this.mContext.getPackageName());
         String var5 = String.valueOf(var6.getMessage());
         zzcvk.e((new StringBuilder(25 + String.valueOf(var4).length() + String.valueOf(var5).length())).append("Package name ").append(var4).append(" not found. ").append(var5).toString());
         return dv.zzbLu;
      }
   }
}
