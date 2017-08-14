package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ApiException extends Exception {
   protected final Status mStatus;

   public ApiException(@NonNull Status var1) {
      super(var1.getStatusMessage());
      this.mStatus = var1;
   }

   public int getStatusCode() {
      return this.mStatus.getStatusCode();
   }

   @Nullable
   public String getStatusMessage() {
      return this.mStatus.getStatusMessage();
   }
}
