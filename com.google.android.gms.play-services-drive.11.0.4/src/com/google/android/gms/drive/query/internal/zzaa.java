package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzaa implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzz[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);

      while(var2.dataPosition() < var3) {
         int var4 = var2.readInt();
         com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var4);
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzz();
   }
}
