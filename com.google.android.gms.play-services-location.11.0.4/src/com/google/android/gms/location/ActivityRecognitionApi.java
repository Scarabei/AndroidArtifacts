package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface ActivityRecognitionApi {
   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   PendingResult requestActivityUpdates(GoogleApiClient var1, long var2, PendingIntent var4);

   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   PendingResult removeActivityUpdates(GoogleApiClient var1, PendingIntent var2);
}
