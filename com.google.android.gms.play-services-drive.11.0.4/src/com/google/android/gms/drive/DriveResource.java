package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.events.ChangeListener;
import java.util.Set;

public interface DriveResource {
   PendingResult getMetadata(GoogleApiClient var1);

   PendingResult updateMetadata(GoogleApiClient var1, MetadataChangeSet var2);

   DriveId getDriveId();

   PendingResult listParents(GoogleApiClient var1);

   PendingResult delete(GoogleApiClient var1);

   PendingResult setParents(GoogleApiClient var1, Set var2);

   PendingResult addChangeListener(GoogleApiClient var1, ChangeListener var2);

   PendingResult removeChangeListener(GoogleApiClient var1, ChangeListener var2);

   PendingResult addChangeSubscription(GoogleApiClient var1);

   PendingResult removeChangeSubscription(GoogleApiClient var1);

   PendingResult trash(GoogleApiClient var1);

   PendingResult untrash(GoogleApiClient var1);

   public interface MetadataResult extends Result {
      Metadata getMetadata();
   }
}
