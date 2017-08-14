package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;

public interface DriveFolder extends DriveResource {
   String MIME_TYPE = "application/vnd.google-apps.folder";

   PendingResult listChildren(GoogleApiClient var1);

   PendingResult queryChildren(GoogleApiClient var1, Query var2);

   PendingResult createFile(GoogleApiClient var1, MetadataChangeSet var2, DriveContents var3);

   PendingResult createFile(GoogleApiClient var1, MetadataChangeSet var2, DriveContents var3, ExecutionOptions var4);

   PendingResult createFolder(GoogleApiClient var1, MetadataChangeSet var2);

   public interface DriveFolderResult extends Result {
      DriveFolder getDriveFolder();
   }

   public interface DriveFileResult extends Result {
      DriveFile getDriveFile();
   }
}
