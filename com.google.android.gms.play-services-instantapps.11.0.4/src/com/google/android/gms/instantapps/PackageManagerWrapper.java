package com.google.android.gms.instantapps;

import android.app.Activity;
import android.content.ComponentName;

/** @deprecated */
@Deprecated
public interface PackageManagerWrapper extends PackageManagerCompat {
   /** @deprecated */
   @Deprecated
   String getCallingPackage(Activity var1);

   /** @deprecated */
   @Deprecated
   ComponentName getCallingActivity(Activity var1);

   /** @deprecated */
   @Deprecated
   boolean isInstantApp(int var1);
}
