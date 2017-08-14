package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;

public class ResolvableApiException extends ApiException {
   public ResolvableApiException(@NonNull Status var1) {
      super(var1);
   }

   public void startResolutionForResult(Activity var1, int var2) throws SendIntentException {
      this.mStatus.startResolutionForResult(var1, var2);
   }

   public PendingIntent getResolution() {
      return this.mStatus.getResolution();
   }
}
