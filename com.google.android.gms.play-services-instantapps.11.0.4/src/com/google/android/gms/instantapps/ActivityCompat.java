package com.google.android.gms.instantapps;

import android.content.ComponentName;

public interface ActivityCompat {
   String getCallingPackage();

   ComponentName getCallingActivity();
}
