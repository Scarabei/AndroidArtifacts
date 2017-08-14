package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
   private final int zzakt;

   GooglePlayServicesAvailabilityException(int var1, String var2, Intent var3) {
      super(var2, var3);
      this.zzakt = var1;
   }

   public int getConnectionStatusCode() {
      return this.zzakt;
   }
}
