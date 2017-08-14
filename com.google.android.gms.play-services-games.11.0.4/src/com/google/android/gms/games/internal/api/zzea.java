package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;

final class zzea implements Videos.CaptureStateResult {
   // $FF: synthetic field
   private Status zzakB;

   zzea(zzdz var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final CaptureState getCaptureState() {
      return null;
   }
}
