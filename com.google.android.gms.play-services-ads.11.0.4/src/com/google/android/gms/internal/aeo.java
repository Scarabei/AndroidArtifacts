package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class aeo {
   private static String zzcuz;

   public static String zzbU(Context var0) {
      if (zzcuz != null) {
         return zzcuz;
      } else {
         PackageManager var1 = var0.getPackageManager();
         Intent var2 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
         ResolveInfo var3 = var1.resolveActivity(var2, 0);
         String var4 = null;
         if (var3 != null) {
            var4 = var3.activityInfo.packageName;
         }

         List var5 = var1.queryIntentActivities(var2, 0);
         ArrayList var6 = new ArrayList();
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            ResolveInfo var8 = (ResolveInfo)var7.next();
            Intent var9;
            (var9 = new Intent()).setAction("android.support.customtabs.action.CustomTabsService");
            var9.setPackage(var8.activityInfo.packageName);
            if (var1.resolveService(var9, 0) != null) {
               var6.add(var8.activityInfo.packageName);
            }
         }

         if (var6.isEmpty()) {
            zzcuz = null;
         } else if (var6.size() == 1) {
            zzcuz = (String)var6.get(0);
         } else if (!TextUtils.isEmpty(var4) && !zzk(var0, var2) && var6.contains(var4)) {
            zzcuz = var4;
         } else if (var6.contains("com.android.chrome")) {
            zzcuz = "com.android.chrome";
         } else if (var6.contains("com.chrome.beta")) {
            zzcuz = "com.chrome.beta";
         } else if (var6.contains("com.chrome.dev")) {
            zzcuz = "com.chrome.dev";
         } else if (var6.contains("com.google.android.apps.chrome")) {
            zzcuz = "com.google.android.apps.chrome";
         }

         return zzcuz;
      }
   }

   private static boolean zzk(Context var0, Intent var1) {
      try {
         List var2;
         if ((var2 = var0.getPackageManager().queryIntentActivities(var1, 64)) == null || var2.size() == 0) {
            return false;
         }

         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            ResolveInfo var4;
            IntentFilter var5;
            if ((var5 = (var4 = (ResolveInfo)var3.next()).filter) != null && var5.countDataAuthorities() != 0 && var5.countDataPaths() != 0 && var4.activityInfo != null) {
               return true;
            }
         }
      } catch (RuntimeException var6) {
         Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
      }

      return false;
   }
}
