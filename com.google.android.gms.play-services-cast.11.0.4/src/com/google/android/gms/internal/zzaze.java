package com.google.android.gms.internal;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

final class zzaze implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
   private final Status mStatus;
   private final Display zzPO;

   public zzaze(Display var1) {
      this.mStatus = Status.zzaBm;
      this.zzPO = var1;
   }

   public zzaze(Status var1) {
      this.mStatus = var1;
      this.zzPO = null;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Display getPresentationDisplay() {
      return this.zzPO;
   }
}
