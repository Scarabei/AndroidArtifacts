package com.google.android.gms.nearby.messages;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface Messages {
   PendingResult publish(GoogleApiClient var1, Message var2);

   PendingResult publish(GoogleApiClient var1, Message var2, PublishOptions var3);

   PendingResult unpublish(GoogleApiClient var1, Message var2);

   PendingResult subscribe(GoogleApiClient var1, MessageListener var2);

   PendingResult subscribe(GoogleApiClient var1, MessageListener var2, SubscribeOptions var3);

   PendingResult subscribe(GoogleApiClient var1, PendingIntent var2, SubscribeOptions var3);

   PendingResult subscribe(GoogleApiClient var1, PendingIntent var2);

   PendingResult unsubscribe(GoogleApiClient var1, MessageListener var2);

   PendingResult unsubscribe(GoogleApiClient var1, PendingIntent var2);

   /** @deprecated */
   @Deprecated
   PendingResult getPermissionStatus(GoogleApiClient var1);

   PendingResult registerStatusCallback(GoogleApiClient var1, StatusCallback var2);

   PendingResult unregisterStatusCallback(GoogleApiClient var1, StatusCallback var2);

   void handleIntent(Intent var1, MessageListener var2);
}
