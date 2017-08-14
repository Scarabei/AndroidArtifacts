package com.google.android.gms.games.quest;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Quests {
   int SELECT_UPCOMING = 1;
   int SELECT_OPEN = 2;
   int SELECT_ACCEPTED = 3;
   int SELECT_COMPLETED = 4;
   int SELECT_COMPLETED_UNCLAIMED = 101;
   int SELECT_EXPIRED = 5;
   int SELECT_ENDING_SOON = 102;
   int SELECT_FAILED = 6;
   int SELECT_RECENTLY_FAILED = 103;
   int[] SELECT_ALL_QUESTS = new int[]{1, 2, 3, 4, 101, 5, 102, 6, 103};
   int SORT_ORDER_RECENTLY_UPDATED_FIRST = 0;
   int SORT_ORDER_ENDING_SOON_FIRST = 1;
   String EXTRA_QUEST = "quest";

   void registerQuestUpdateListener(GoogleApiClient var1, QuestUpdateListener var2);

   void unregisterQuestUpdateListener(GoogleApiClient var1);

   PendingResult accept(GoogleApiClient var1, String var2);

   PendingResult claim(GoogleApiClient var1, String var2, String var3);

   PendingResult load(GoogleApiClient var1, int[] var2, int var3, boolean var4);

   PendingResult loadByIds(GoogleApiClient var1, boolean var2, String... var3);

   void showStateChangedPopup(GoogleApiClient var1, String var2);

   Intent getQuestsIntent(GoogleApiClient var1, int[] var2);

   Intent getQuestIntent(GoogleApiClient var1, String var2);

   public interface LoadQuestsResult extends Releasable, Result {
      QuestBuffer getQuests();
   }

   public interface ClaimMilestoneResult extends Result {
      Milestone getMilestone();

      Quest getQuest();
   }

   public interface AcceptQuestResult extends Result {
      Quest getQuest();
   }
}
