package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.Freezable;

public interface LeaderboardVariant extends Freezable {
   int COLLECTION_PUBLIC = 0;
   /** @deprecated */
   @Deprecated
   int COLLECTION_SOCIAL = 1;
   int TIME_SPAN_DAILY = 0;
   int TIME_SPAN_WEEKLY = 1;
   int TIME_SPAN_ALL_TIME = 2;
   int NUM_TIME_SPANS = 3;
   int PLAYER_SCORE_UNKNOWN = -1;
   int PLAYER_RANK_UNKNOWN = -1;
   int NUM_SCORES_UNKNOWN = -1;

   int getTimeSpan();

   int getCollection();

   boolean hasPlayerInfo();

   long getRawPlayerScore();

   String getDisplayPlayerScore();

   long getPlayerRank();

   String getDisplayPlayerRank();

   String getPlayerScoreTag();

   long getNumScores();

   String zzvo();

   String zzvp();

   String zzvq();
}
