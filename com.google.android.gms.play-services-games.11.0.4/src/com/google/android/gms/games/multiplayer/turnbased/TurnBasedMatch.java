package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.Participatable;
import java.util.ArrayList;

public interface TurnBasedMatch extends Parcelable, Freezable, Participatable {
   int MATCH_VARIANT_DEFAULT = -1;
   int MATCH_STATUS_AUTO_MATCHING = 0;
   int MATCH_STATUS_ACTIVE = 1;
   int MATCH_STATUS_COMPLETE = 2;
   int MATCH_STATUS_EXPIRED = 3;
   int MATCH_STATUS_CANCELED = 4;
   int MATCH_TURN_STATUS_INVITED = 0;
   int MATCH_TURN_STATUS_MY_TURN = 1;
   int MATCH_TURN_STATUS_THEIR_TURN = 2;
   int MATCH_TURN_STATUS_COMPLETE = 3;
   @KeepName
   int[] MATCH_TURN_STATUS_ALL = new int[]{0, 1, 2, 3};

   Game getGame();

   String getMatchId();

   String getCreatorId();

   long getCreationTimestamp();

   int getStatus();

   int getTurnStatus();

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   int getVariant();

   String getLastUpdaterId();

   long getLastUpdatedTimestamp();

   String getPendingParticipantId();

   byte[] getData();

   int getVersion();

   boolean canRematch();

   String getRematchId();

   byte[] getPreviousMatchData();

   int getMatchNumber();

   Bundle getAutoMatchCriteria();

   int getAvailableAutoMatchSlots();

   boolean isLocallyModified();

   int getParticipantStatus(String var1);

   ArrayList getParticipantIds();

   String getParticipantId(String var1);

   Participant getParticipant(String var1);

   String getDescriptionParticipantId();

   Participant getDescriptionParticipant();
}
