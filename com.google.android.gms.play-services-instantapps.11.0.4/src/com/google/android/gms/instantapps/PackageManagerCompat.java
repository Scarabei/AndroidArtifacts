package com.google.android.gms.instantapps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public interface PackageManagerCompat {
   String[] getPackagesForUid(int var1);

   PackageInfo getPackageInfo(String var1, int var2) throws NameNotFoundException;

   ApplicationInfo getApplicationInfo(String var1, int var2) throws NameNotFoundException;

   CharSequence getApplicationLabel(ApplicationInfo var1);

   boolean isInstantApp();

   boolean isInstantApp(String var1);
}
