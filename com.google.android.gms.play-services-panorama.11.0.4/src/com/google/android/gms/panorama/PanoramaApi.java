package com.google.android.gms.panorama;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public interface PanoramaApi {
   PendingResult loadPanoramaInfo(GoogleApiClient var1, Uri var2);

   PendingResult loadPanoramaInfoAndGrantAccess(GoogleApiClient var1, Uri var2);

   public interface PanoramaResult extends Result {
      Intent getViewerIntent();
   }
}
