package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;

final class zzdy implements Videos.CaptureCapabilitiesResult {
   // $FF: synthetic field
   private Status zzakB;

   zzdy(zzdx var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final VideoCapabilities getCapabilities() {
      return null;
   }
}
