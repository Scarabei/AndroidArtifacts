package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;

public interface Game extends Parcelable, Freezable {
   String getApplicationId();

   String getDisplayName();

   void getDisplayName(CharArrayBuffer var1);

   String getPrimaryCategory();

   String getSecondaryCategory();

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   String getDeveloperName();

   void getDeveloperName(CharArrayBuffer var1);

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   Uri getHiResImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getHiResImageUrl();

   Uri getFeaturedImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getFeaturedImageUrl();

   boolean zzud();

   boolean isMuted();

   boolean zzue();

   boolean zzuf();

   String zzug();

   int getAchievementTotalCount();

   int getLeaderboardCount();

   boolean isRealTimeMultiplayerEnabled();

   boolean isTurnBasedMultiplayerEnabled();

   boolean areSnapshotsEnabled();

   String getThemeColor();

   boolean hasGamepadSupport();
}
