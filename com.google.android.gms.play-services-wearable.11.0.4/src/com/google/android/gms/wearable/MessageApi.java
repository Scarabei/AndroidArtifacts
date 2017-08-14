package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface MessageApi {
   String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";
   int FILTER_LITERAL = 0;
   int FILTER_PREFIX = 1;
   int UNKNOWN_REQUEST_ID = -1;

   PendingResult sendMessage(GoogleApiClient var1, String var2, String var3, byte[] var4);

   PendingResult addListener(GoogleApiClient var1, MessageApi.MessageListener var2);

   PendingResult addListener(GoogleApiClient var1, MessageApi.MessageListener var2, Uri var3, int var4);

   PendingResult removeListener(GoogleApiClient var1, MessageApi.MessageListener var2);

   public interface MessageListener {
      void onMessageReceived(MessageEvent var1);
   }

   public interface SendMessageResult extends Result {
      int getRequestId();
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface FilterType {
   }
}
