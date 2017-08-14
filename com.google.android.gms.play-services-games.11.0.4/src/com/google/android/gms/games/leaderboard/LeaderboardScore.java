package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface LeaderboardScore extends Freezable {
   int LEADERBOARD_RANK_UNKNOWN = -1;

   long getRank();

   String getDisplayRank();

   void getDisplayRank(CharArrayBuffer var1);

   String getDisplayScore();

   void getDisplayScore(CharArrayBuffer var1);

   long getRawScore();

   long getTimestampMillis();

   String getScoreHolderDisplayName();

   void getScoreHolderDisplayName(CharArrayBuffer var1);

   Uri getScoreHolderIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getScoreHolderIconImageUrl();

   Uri getScoreHolderHiResImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getScoreHolderHiResImageUrl();

   Player getScoreHolder();

   String getScoreTag();
}
