package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import java.util.List;

/** @deprecated */
@Deprecated
public interface GameRequest extends Parcelable, Freezable {
   int TYPE_GIFT = 1;
   int TYPE_WISH = 2;
   @KeepName
   int TYPE_ALL = 65535;
   int STATUS_PENDING = 0;
   int STATUS_ACCEPTED = 1;
   int RECIPIENT_STATUS_PENDING = 0;
   int RECIPIENT_STATUS_ACCEPTED = 1;

   String getRequestId();

   Game getGame();

   Player getSender();

   List getRecipients();

   boolean isConsumed(String var1);

   byte[] getData();

   int getType();

   long getCreationTimestamp();

   long getExpirationTimestamp();

   int getRecipientStatus(String var1);

   int getStatus();
}
