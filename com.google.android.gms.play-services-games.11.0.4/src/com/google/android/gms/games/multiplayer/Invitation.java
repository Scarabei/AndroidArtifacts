package com.google.android.gms.games.multiplayer;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;

public interface Invitation extends Parcelable, Freezable, Participatable {
   int INVITATION_TYPE_REAL_TIME = 0;
   int INVITATION_TYPE_TURN_BASED = 1;

   Game getGame();

   String getInvitationId();

   Participant getInviter();

   long getCreationTimestamp();

   int getInvitationType();

   int getVariant();

   int getAvailableAutoMatchSlots();
}
