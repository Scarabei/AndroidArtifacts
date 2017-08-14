package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;

public final class zzcbp {
   private static zzcbp zzbhq;
   private final Context mContext;

   @Nullable
   public static synchronized zzcbp zzbf(Context var0) {
      if (zzbhq == null) {
         Context var1 = var0.getApplicationContext();
         zzcbp var10000;
         if (VERSION.SDK_INT < 16) {
            var10000 = null;
         } else if (!zzcbs.zzbg(var1)) {
            var10000 = null;
         } else {
            ProviderInfo var2;
            if ((var2 = var1.getPackageManager().resolveContentProvider(zzcbq.zzbhr.getAuthority(), 0)) == null) {
               var10000 = null;
            } else if (!var2.packageName.equals("com.google.android.gms")) {
               String var3 = String.valueOf(var2.packageName);
               Log.e("IAMetadataClient", (new StringBuilder(85 + String.valueOf(var3).length())).append("Package ").append(var3).append(" is invalid for instant apps content provider; instant apps will be disabled.").toString());
               var10000 = null;
            } else {
               var10000 = new zzcbp(var1);
            }
         }

         zzbhq = var10000;
      }

      return zzbhq;
   }

   private zzcbp(Context var1) {
      this.mContext = var1;
   }

   @TargetApi(16)
   private final Bundle zzg(String var1, Bundle var2) throws RemoteException {
      long var4 = Binder.clearCallingIdentity();

      Bundle var3;
      try {
         var3 = this.mContext.getContentResolver().call(zzcbq.zzbhr, var1, (String)null, var2);
      } finally {
         Binder.restoreCallingIdentity(var4);
      }

      if (var3 == null) {
         throw new RemoteException();
      } else {
         return var3;
      }
   }

   public final String zzbi(int var1) throws RemoteException {
      Bundle var2;
      (var2 = new Bundle()).putInt("uid", var1);
      return this.zzg("getAppPackageForUid", var2).getString("result");
   }

   public final ApplicationInfo getApplicationInfo(String var1, int var2) throws RemoteException {
      Bundle var3;
      (var3 = new Bundle()).putString("packageName", var1);
      var3.putInt("flags", var2);
      return (ApplicationInfo)this.zzg("getWHApplicationInfo", var3).getParcelable("result");
   }

   public final PackageInfo getPackageInfo(String var1, int var2) throws RemoteException {
      Bundle var3;
      (var3 = new Bundle()).putString("packageName", var1);
      var3.putInt("flags", var2);
      return (PackageInfo)this.zzg("getWHPackageInfo", var3).getParcelable("result");
   }

   public final String zzdt(String var1) throws RemoteException {
      Bundle var2;
      (var2 = new Bundle()).putString("packageName", var1);
      return this.zzg("getApplicationLabel", var2).getString("result");
   }

   public final ComponentName zzdu(String var1) throws RemoteException {
      Bundle var2;
      (var2 = new Bundle()).putString("shadowActivity", var1);
      return (ComponentName)this.zzg("getCallingActivity", var2).getParcelable("result");
   }

   public final boolean isInstantApp(String var1) throws RemoteException {
      Bundle var2;
      (var2 = new Bundle()).putString("packageName", var1);
      return this.zzg("isInstantApp", var2).getBoolean("result");
   }
}
