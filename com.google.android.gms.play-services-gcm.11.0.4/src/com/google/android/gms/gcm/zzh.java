package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzh implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PeriodicTask[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      return new PeriodicTask(var1, (zzh)null);
   }
}
