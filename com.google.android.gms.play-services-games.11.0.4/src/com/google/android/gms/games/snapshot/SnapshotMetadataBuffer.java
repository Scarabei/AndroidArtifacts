package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class SnapshotMetadataBuffer extends AbstractDataBuffer {
   public SnapshotMetadataBuffer(DataHolder var1) {
      super(var1);
   }

   public final SnapshotMetadata get(int var1) {
      return new SnapshotMetadataRef(this.zzaCX, var1);
   }
}
