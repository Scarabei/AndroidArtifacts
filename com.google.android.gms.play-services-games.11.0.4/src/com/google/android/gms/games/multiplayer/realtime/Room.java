package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.Participatable;
import java.util.ArrayList;

public interface Room extends Parcelable, Freezable, Participatable {
   int ROOM_VARIANT_DEFAULT = -1;
   int ROOM_STATUS_INVITING = 0;
   int ROOM_STATUS_AUTO_MATCHING = 1;
   int ROOM_STATUS_CONNECTING = 2;
   int ROOM_STATUS_ACTIVE = 3;

   String getRoomId();

   String getCreatorId();

   long getCreationTimestamp();

   int getStatus();

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   int getVariant();

   Bundle getAutoMatchCriteria();

   int getAutoMatchWaitEstimateSeconds();

   int getParticipantStatus(String var1);

   ArrayList getParticipantIds();

   String getParticipantId(String var1);

   Participant getParticipant(String var1);
}
