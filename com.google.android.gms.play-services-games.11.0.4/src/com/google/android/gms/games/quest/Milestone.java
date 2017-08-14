package com.google.android.gms.games.quest;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Milestone extends Parcelable, Freezable {
   int STATE_NOT_STARTED = 1;
   int STATE_NOT_COMPLETED = 2;
   int STATE_COMPLETED_NOT_CLAIMED = 3;
   int STATE_CLAIMED = 4;

   String getMilestoneId();

   long getCurrentProgress();

   String getEventId();

   int getState();

   long getTargetProgress();

   byte[] getCompletionRewardData();
}
