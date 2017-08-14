package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcpe implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcpd[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);

      while(var2.dataPosition() < var3) {
         int var4 = var2.readInt();
         zzb.zzb(var2, var4);
      }

      zzb.zzF(var2, var3);
      return new zzcpd();
   }
}
