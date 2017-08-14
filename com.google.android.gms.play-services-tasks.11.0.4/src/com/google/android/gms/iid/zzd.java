package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MessengerCompat[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      IBinder var2;
      return (var2 = var1.readStrongBinder()) != null ? new MessengerCompat(var2) : null;
   }
}
