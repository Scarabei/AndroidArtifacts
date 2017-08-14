package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzp implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new BinderWrapper[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      return new BinderWrapper(var1, (zzp)null);
   }
}
