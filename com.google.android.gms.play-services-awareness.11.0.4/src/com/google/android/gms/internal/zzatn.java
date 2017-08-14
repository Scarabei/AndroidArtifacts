package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;

final class zzatn implements DetectedActivityResult {
   // $FF: synthetic field
   private zzaud zzaok;

   zzatn(zzatm var1, zzaud var2) {
      this.zzaok = var2;
      super();
   }

   public final ActivityRecognitionResult getActivityRecognitionResult() {
      return this.zzaok.zznb() == null ? null : this.zzaok.zznb().getActivityRecognitionResult();
   }

   public final Status getStatus() {
      return this.zzaok.getStatus();
   }
}
