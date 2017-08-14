package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;

public interface SnapshotMetadata extends Parcelable, Freezable {
   long PLAYED_TIME_UNKNOWN = -1L;
   long PROGRESS_VALUE_UNKNOWN = -1L;

   Game getGame();

   Player getOwner();

   String getSnapshotId();

   Uri getCoverImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getCoverImageUrl();

   float getCoverImageAspectRatio();

   String getUniqueName();

   String getTitle();

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   long getLastModifiedTimestamp();

   long getPlayedTime();

   boolean hasChangePending();

   long getProgressValue();

   String getDeviceName();
}
