package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Process;

public final class zzbgz {
   private Context mContext;

   public zzbgz(Context var1) {
      this.mContext = var1;
   }

   public final ApplicationInfo getApplicationInfo(String var1, int var2) throws NameNotFoundException {
      return this.mContext.getPackageManager().getApplicationInfo(var1, var2);
   }

   public final PackageInfo getPackageInfo(String var1, int var2) throws NameNotFoundException {
      return this.mContext.getPackageManager().getPackageInfo(var1, var2);
   }

   public final String[] getPackagesForUid(int var1) {
      return this.mContext.getPackageManager().getPackagesForUid(var1);
   }

   @TargetApi(19)
   public final boolean zzf(int var1, String var2) {
      if (com.google.android.gms.common.util.zzq.zzsc()) {
         try {
            ((AppOpsManager)this.mContext.getSystemService("appops")).checkPackage(var1, var2);
            return true;
         } catch (SecurityException var5) {
            return false;
         }
      } else {
         String[] var3 = this.mContext.getPackageManager().getPackagesForUid(var1);
         if (var2 != null && var3 != null) {
            for(int var4 = 0; var4 < var3.length; ++var4) {
               if (var2.equals(var3[var4])) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public final int checkCallingOrSelfPermission(String var1) {
      return this.mContext.checkCallingOrSelfPermission(var1);
   }

   public final int checkPermission(String var1, String var2) {
      return this.mContext.getPackageManager().checkPermission(var1, var2);
   }

   public final CharSequence zzcM(String var1) throws NameNotFoundException {
      return this.mContext.getPackageManager().getApplicationLabel(this.mContext.getPackageManager().getApplicationInfo(var1, 0));
   }

   public final boolean zzsl() {
      return Binder.getCallingUid() == Process.myUid() ? zzbgy.zzaN(this.mContext) : false;
   }
}
