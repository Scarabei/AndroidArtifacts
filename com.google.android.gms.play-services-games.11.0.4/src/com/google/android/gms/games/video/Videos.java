package com.google.android.gms.games.video;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public interface Videos {
   int CAPTURE_OVERLAY_STATE_SHOWN = 1;
   int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
   int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
   int CAPTURE_OVERLAY_STATE_DISMISSED = 4;

   PendingResult getCaptureCapabilities(GoogleApiClient var1);

   Intent getCaptureOverlayIntent(GoogleApiClient var1);

   PendingResult getCaptureState(GoogleApiClient var1);

   PendingResult isCaptureAvailable(GoogleApiClient var1, int var2);

   boolean isCaptureSupported(GoogleApiClient var1);

   void registerCaptureOverlayStateChangedListener(GoogleApiClient var1, Videos.CaptureOverlayStateListener var2);

   void unregisterCaptureOverlayStateChangedListener(GoogleApiClient var1);

   public interface CaptureStreamingUrlResult extends Result {
      String getUrl();
   }

   public interface CaptureStateResult extends Result {
      CaptureState getCaptureState();
   }

   public interface CaptureCapabilitiesResult extends Result {
      VideoCapabilities getCapabilities();
   }

   public interface CaptureAvailableResult extends Result {
      boolean isAvailable();
   }

   public interface CaptureOverlayStateListener {
      void onCaptureOverlayStateChanged(int var1);
   }
}
