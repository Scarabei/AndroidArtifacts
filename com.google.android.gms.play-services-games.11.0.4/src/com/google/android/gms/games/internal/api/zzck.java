package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzck implements Snapshots.DeleteSnapshotResult {
   // $FF: synthetic field
   private Status zzakB;

   zzck(zzcj var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final String getSnapshotId() {
      return null;
   }
}
