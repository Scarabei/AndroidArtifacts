package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzci implements Snapshots.CommitSnapshotResult {
   // $FF: synthetic field
   private Status zzakB;

   zzci(zzch var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final SnapshotMetadata getSnapshotMetadata() {
      return null;
   }
}
