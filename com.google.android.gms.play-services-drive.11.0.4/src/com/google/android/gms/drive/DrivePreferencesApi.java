package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public interface DrivePreferencesApi {
   PendingResult getFileUploadPreferences(GoogleApiClient var1);

   PendingResult setFileUploadPreferences(GoogleApiClient var1, FileUploadPreferences var2);

   public interface FileUploadPreferencesResult extends Result {
      FileUploadPreferences getFileUploadPreferences();
   }
}
