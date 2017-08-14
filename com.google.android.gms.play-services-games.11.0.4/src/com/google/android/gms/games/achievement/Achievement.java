package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Achievement extends Parcelable, Freezable {
   int STATE_UNLOCKED = 0;
   int STATE_REVEALED = 1;
   int STATE_HIDDEN = 2;
   int TYPE_STANDARD = 0;
   int TYPE_INCREMENTAL = 1;

   String getAchievementId();

   int getType();

   String getName();

   void getName(CharArrayBuffer var1);

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   Uri getUnlockedImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getUnlockedImageUrl();

   Uri getRevealedImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getRevealedImageUrl();

   int getTotalSteps();

   String getFormattedTotalSteps();

   void getFormattedTotalSteps(CharArrayBuffer var1);

   Player getPlayer();

   int getState();

   int getCurrentSteps();

   String getFormattedCurrentSteps();

   void getFormattedCurrentSteps(CharArrayBuffer var1);

   long getLastUpdatedTimestamp();

   long getXpValue();
}
