package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import java.util.List;

public interface Quest extends Parcelable, Freezable {
   int STATE_UPCOMING = 1;
   int STATE_OPEN = 2;
   int STATE_ACCEPTED = 3;
   int STATE_COMPLETED = 4;
   int STATE_EXPIRED = 5;
   int STATE_FAILED = 6;
   @KeepName
   int[] QUEST_STATE_ALL = new int[]{1, 2, 3, 4, 6, 5};
   @KeepName
   String[] QUEST_STATE_NON_TERMINAL = new String[]{Integer.toString(1), Integer.toString(2), Integer.toString(3)};
   long UNSET_QUEST_TIMESTAMP = -1L;

   String getQuestId();

   String getName();

   void getName(CharArrayBuffer var1);

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   Uri getBannerImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getBannerImageUrl();

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   Milestone getCurrentMilestone();

   List zzvt();

   Game getGame();

   int getState();

   int getType();

   long getAcceptedTimestamp();

   long getEndTimestamp();

   long getLastUpdatedTimestamp();

   long zzvu();

   long getStartTimestamp();

   boolean isEndingSoon();
}
