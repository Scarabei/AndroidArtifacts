package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;

public interface Player extends Parcelable, Freezable {
   long CURRENT_XP_UNKNOWN = -1L;
   long TIMESTAMP_UNKNOWN = -1L;

   String getPlayerId();

   String getDisplayName();

   void getDisplayName(CharArrayBuffer var1);

   String zzuj();

   String getName();

   boolean zzuk();

   boolean hasIconImage();

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   boolean hasHiResImage();

   Uri getHiResImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getHiResImageUrl();

   long getRetrievedTimestamp();

   long getLastPlayedWithTimestamp();

   /** @deprecated */
   @Deprecated
   int zzul();

   boolean zzum();

   String getTitle();

   void getTitle(CharArrayBuffer var1);

   PlayerLevelInfo getLevelInfo();

   com.google.android.gms.games.internal.player.zza zzun();

   Uri getBannerImageLandscapeUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getBannerImageLandscapeUrl();

   Uri getBannerImagePortraitUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getBannerImagePortraitUrl();

   int zzuo();

   long zzup();

   boolean isMuted();
}
