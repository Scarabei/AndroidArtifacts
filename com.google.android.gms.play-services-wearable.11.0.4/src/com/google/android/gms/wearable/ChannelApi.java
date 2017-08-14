package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ChannelApi {
   String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";

   PendingResult openChannel(GoogleApiClient var1, String var2, String var3);

   PendingResult addListener(GoogleApiClient var1, ChannelApi.ChannelListener var2);

   PendingResult removeListener(GoogleApiClient var1, ChannelApi.ChannelListener var2);

   @Retention(RetentionPolicy.SOURCE)
   public @interface CloseReason {
   }

   public interface ChannelListener {
      int CLOSE_REASON_NORMAL = 0;
      int CLOSE_REASON_DISCONNECTED = 1;
      int CLOSE_REASON_REMOTE_CLOSE = 2;
      int CLOSE_REASON_LOCAL_CLOSE = 3;

      void onChannelOpened(Channel var1);

      void onChannelClosed(Channel var1, int var2, int var3);

      void onInputClosed(Channel var1, int var2, int var3);

      void onOutputClosed(Channel var1, int var2, int var3);
   }

   public interface OpenChannelResult extends Result {
      Channel getChannel();
   }
}
