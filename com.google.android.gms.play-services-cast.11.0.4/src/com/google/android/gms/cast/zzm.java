package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

final class zzm implements Cast.ApplicationConnectionResult {
   // $FF: synthetic field
   private Status zzakB;

   zzm(Cast.zza var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final boolean getWasLaunched() {
      return false;
   }

   public final String getSessionId() {
      return null;
   }

   public final String getApplicationStatus() {
      return null;
   }

   public final ApplicationMetadata getApplicationMetadata() {
      return null;
   }
}
