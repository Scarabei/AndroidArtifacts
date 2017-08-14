package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;

final class zzf implements Games.GetServerAuthCodeResult {
   // $FF: synthetic field
   private Status zzakB;

   zzf(Games.zzc var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final String getCode() {
      return null;
   }
}
