package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

final class zzdw implements Videos.CaptureAvailableResult {
   // $FF: synthetic field
   private Status zzakB;

   zzdw(zzdv var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final boolean isAvailable() {
      return false;
   }
}
