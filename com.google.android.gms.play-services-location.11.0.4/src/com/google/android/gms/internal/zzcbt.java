package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.ActivityRecognitionApi;

public final class zzcbt implements ActivityRecognitionApi {
   public final PendingResult requestActivityUpdates(GoogleApiClient var1, long var2, PendingIntent var4) {
      return var1.zze(new zzcbu(this, var1, var2, var4));
   }

   public final PendingResult removeActivityUpdates(GoogleApiClient var1, PendingIntent var2) {
      return var1.zze(new zzcbv(this, var1, var2));
   }
}
