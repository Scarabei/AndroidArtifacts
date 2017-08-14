package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import java.util.ArrayList;

public interface Leaderboard extends Freezable {
   int SCORE_ORDER_SMALLER_IS_BETTER = 0;
   int SCORE_ORDER_LARGER_IS_BETTER = 1;

   String getLeaderboardId();

   String getDisplayName();

   void getDisplayName(CharArrayBuffer var1);

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   int getScoreOrder();

   ArrayList getVariants();

   Game getGame();
}
