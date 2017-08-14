package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;

public interface ExperienceEvent extends Parcelable, Freezable {
   String zzuY();

   Game getGame();

   String zzuZ();

   String zzva();

   /** @deprecated */
   @Deprecated
   @KeepName
   String getIconImageUrl();

   Uri getIconImageUri();

   long zzvb();

   long zzvc();

   long zzvd();

   int getType();

   int zzve();
}
