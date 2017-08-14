package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcm implements Snapshots.LoadSnapshotsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzcm(zzcl var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final SnapshotMetadataBuffer getSnapshots() {
      return new SnapshotMetadataBuffer(DataHolder.zzau(14));
   }
}
