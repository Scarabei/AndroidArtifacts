package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzco implements Snapshots.OpenSnapshotResult {
   // $FF: synthetic field
   private Status zzakB;

   zzco(zzcn var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final Snapshot getSnapshot() {
      return null;
   }

   public final String getConflictId() {
      return null;
   }

   public final Snapshot getConflictingSnapshot() {
      return null;
   }

   public final SnapshotContents getResolutionSnapshotContents() {
      return null;
   }
}
