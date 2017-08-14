package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.BeaconStateResult;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.Status;

final class zzatx implements BeaconStateResult {
   // $FF: synthetic field
   private zzaud zzaok;

   zzatx(zzatw var1, zzaud var2) {
      this.zzaok = var2;
      super();
   }

   public final BeaconState getBeaconState() {
      return this.zzaok.zznb() == null ? null : this.zzaok.zznb().zzmW();
   }

   public final Status getStatus() {
      return this.zzaok.getStatus();
   }
}
