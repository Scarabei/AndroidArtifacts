package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new OneoffTask[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      return new OneoffTask(var1, (zzf)null);
   }
}
