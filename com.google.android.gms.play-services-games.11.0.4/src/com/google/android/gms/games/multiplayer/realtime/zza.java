package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new RealTimeMessage[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      return new RealTimeMessage(var1, (zza)null);
   }
}
