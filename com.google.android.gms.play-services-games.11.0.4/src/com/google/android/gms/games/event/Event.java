package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Event extends Parcelable, Freezable {
   String getEventId();

   String getName();

   void getName(CharArrayBuffer var1);

   String getDescription();

   void getDescription(CharArrayBuffer var1);

   Uri getIconImageUri();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   Player getPlayer();

   long getValue();

   String getFormattedValue();

   void getFormattedValue(CharArrayBuffer var1);

   boolean isVisible();
}
