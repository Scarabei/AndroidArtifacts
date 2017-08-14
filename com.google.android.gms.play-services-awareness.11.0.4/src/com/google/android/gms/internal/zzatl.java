package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.TimeIntervalsResult;
import com.google.android.gms.awareness.state.TimeIntervals;
import com.google.android.gms.common.api.Status;

final class zzatl implements TimeIntervalsResult {
   // $FF: synthetic field
   private zzaud zzaok;

   zzatl(zzatk var1, zzaud var2) {
      this.zzaok = var2;
      super();
   }

   public final TimeIntervals getTimeIntervals() {
      return this.zzaok.zznb() == null ? null : this.zzaok.zznb().zzna();
   }

   public final Status getStatus() {
      return this.zzaok.getStatus();
   }
}
