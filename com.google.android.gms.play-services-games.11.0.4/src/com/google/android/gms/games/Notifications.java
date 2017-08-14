package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;

public interface Notifications {
   int NOTIFICATION_TYPE_INVITATION = 1;
   int NOTIFICATION_TYPE_MATCH_UPDATE = 2;
   int NOTIFICATION_TYPE_REQUEST = 4;
   int NOTIFICATION_TYPE_QUEST = 8;
   int NOTIFICATION_TYPE_LEVEL_UP = 16;
   int NOTIFICATION_TYPES_ALL = 63;
   int NOTIFICATION_TYPES_MULTIPLAYER = 3;

   void clearAll(GoogleApiClient var1);

   void clear(GoogleApiClient var1, int var2);
}
