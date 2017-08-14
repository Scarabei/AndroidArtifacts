package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;

public final class zzw implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new LocationSettingsResult[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Status var4 = null;
      LocationSettingsStates var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 1:
            var4 = (Status)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, Status.CREATOR);
            break;
         case 2:
            var5 = (LocationSettingsStates)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, LocationSettingsStates.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var6);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new LocationSettingsResult(var4, var5);
   }
}
