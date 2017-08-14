package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Participant extends Parcelable, Freezable {
   int STATUS_NOT_INVITED_YET = 0;
   int STATUS_INVITED = 1;
   int STATUS_JOINED = 2;
   int STATUS_DECLINED = 3;
   int STATUS_LEFT = 4;
   int STATUS_FINISHED = 5;
   int STATUS_UNRESPONSIVE = 6;

   int getStatus();

   String zzvr();

   int getCapabilities();

   boolean isConnectedToRoom();

   String getDisplayName();

   void getDisplayName(CharArrayBuffer var1);

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   Uri getHiResImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getHiResImageUrl();

   String getParticipantId();

   Player getPlayer();

   ParticipantResult getResult();
}
