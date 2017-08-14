package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import java.util.List;

public interface DriveApi {
   PendingResult query(GoogleApiClient var1, Query var2);

   PendingResult newDriveContents(GoogleApiClient var1);

   PendingResult fetchDriveId(GoogleApiClient var1, String var2);

   DriveFolder getRootFolder(GoogleApiClient var1);

   DriveFolder getAppFolder(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   DriveFile getFile(GoogleApiClient var1, DriveId var2);

   /** @deprecated */
   @Deprecated
   DriveFolder getFolder(GoogleApiClient var1, DriveId var2);

   OpenFileActivityBuilder newOpenFileActivityBuilder();

   CreateFileActivityBuilder newCreateFileActivityBuilder();

   PendingResult requestSync(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult cancelPendingActions(GoogleApiClient var1, List var2);

   /** @deprecated */
   @Deprecated
   PendingResult isAutobackupEnabled(GoogleApiClient var1);

   public interface DriveIdResult extends Result {
      DriveId getDriveId();
   }

   public interface DriveContentsResult extends Result {
      DriveContents getDriveContents();
   }

   public interface MetadataBufferResult extends Releasable, Result {
      MetadataBuffer getMetadataBuffer();
   }
}
