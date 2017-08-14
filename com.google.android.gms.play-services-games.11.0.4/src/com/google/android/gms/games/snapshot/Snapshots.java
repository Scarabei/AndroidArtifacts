package com.google.android.gms.games.snapshot;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Snapshots {
   String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
   String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
   int DISPLAY_LIMIT_NONE = -1;
   int RESOLUTION_POLICY_MANUAL = -1;
   int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
   int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
   int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
   int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;

   int getMaxDataSize(GoogleApiClient var1);

   int getMaxCoverImageSize(GoogleApiClient var1);

   Intent getSelectSnapshotIntent(GoogleApiClient var1, String var2, boolean var3, boolean var4, int var5);

   PendingResult load(GoogleApiClient var1, boolean var2);

   PendingResult open(GoogleApiClient var1, String var2, boolean var3);

   PendingResult open(GoogleApiClient var1, String var2, boolean var3, int var4);

   PendingResult open(GoogleApiClient var1, SnapshotMetadata var2);

   PendingResult open(GoogleApiClient var1, SnapshotMetadata var2, int var3);

   PendingResult commitAndClose(GoogleApiClient var1, Snapshot var2, SnapshotMetadataChange var3);

   void discardAndClose(GoogleApiClient var1, Snapshot var2);

   PendingResult delete(GoogleApiClient var1, SnapshotMetadata var2);

   SnapshotMetadata getSnapshotFromBundle(Bundle var1);

   PendingResult resolveConflict(GoogleApiClient var1, String var2, Snapshot var3);

   PendingResult resolveConflict(GoogleApiClient var1, String var2, String var3, SnapshotMetadataChange var4, SnapshotContents var5);

   public interface DeleteSnapshotResult extends Result {
      String getSnapshotId();
   }

   public interface CommitSnapshotResult extends Result {
      SnapshotMetadata getSnapshotMetadata();
   }

   public interface OpenSnapshotResult extends Result {
      Snapshot getSnapshot();

      String getConflictId();

      Snapshot getConflictingSnapshot();

      SnapshotContents getResolutionSnapshotContents();
   }

   public interface LoadSnapshotsResult extends Releasable, Result {
      SnapshotMetadataBuffer getSnapshots();
   }
}
