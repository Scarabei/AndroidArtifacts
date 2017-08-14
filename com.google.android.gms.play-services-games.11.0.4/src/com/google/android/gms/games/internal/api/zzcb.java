package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

public final class zzcb implements Snapshots {
   public final int getMaxDataSize(GoogleApiClient var1) {
      return Games.zzf(var1).zzuK();
   }

   public final int getMaxCoverImageSize(GoogleApiClient var1) {
      return Games.zzf(var1).zzuL();
   }

   public final Intent getSelectSnapshotIntent(GoogleApiClient var1, String var2, boolean var3, boolean var4, int var5) {
      return Games.zzf(var1).zza(var2, var3, var4, var5);
   }

   public final PendingResult load(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzcc(this, var1, var2));
   }

   public final PendingResult open(GoogleApiClient var1, SnapshotMetadata var2) {
      return this.open(var1, var2.getUniqueName(), false);
   }

   public final PendingResult open(GoogleApiClient var1, SnapshotMetadata var2, int var3) {
      return this.open(var1, var2.getUniqueName(), false, var3);
   }

   public final PendingResult open(GoogleApiClient var1, String var2, boolean var3) {
      return this.open(var1, var2, var3, -1);
   }

   public final PendingResult open(GoogleApiClient var1, String var2, boolean var3, int var4) {
      return var1.zze(new zzcd(this, var1, var2, var3, var4));
   }

   public final PendingResult commitAndClose(GoogleApiClient var1, Snapshot var2, SnapshotMetadataChange var3) {
      return var1.zze(new zzce(this, var1, var2, var3));
   }

   public final void discardAndClose(GoogleApiClient var1, Snapshot var2) {
      Games.zzf(var1).zza(var2);
   }

   public final PendingResult delete(GoogleApiClient var1, SnapshotMetadata var2) {
      return var1.zze(new zzcf(this, var1, var2));
   }

   public final PendingResult resolveConflict(GoogleApiClient var1, String var2, Snapshot var3) {
      SnapshotMetadata var4 = var3.getMetadata();
      SnapshotMetadataChange var5 = (new SnapshotMetadataChange.Builder()).fromMetadata(var4).build();
      return this.resolveConflict(var1, var2, var4.getSnapshotId(), var5, var3.getSnapshotContents());
   }

   public final PendingResult resolveConflict(GoogleApiClient var1, String var2, String var3, SnapshotMetadataChange var4, SnapshotContents var5) {
      return var1.zze(new zzcg(this, var1, var2, var3, var4, var5));
   }

   public final SnapshotMetadata getSnapshotFromBundle(Bundle var1) {
      return var1 != null && var1.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA") ? (SnapshotMetadata)var1.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA") : null;
   }
}
