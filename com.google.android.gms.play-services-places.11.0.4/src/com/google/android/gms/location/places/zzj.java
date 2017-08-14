package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public final class zzj implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PlacePhotoMetadataResult[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Status var4 = null;
      DataHolder var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 1:
            var4 = (Status)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, Status.CREATOR);
            break;
         case 2:
            var5 = (DataHolder)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, DataHolder.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var6);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PlacePhotoMetadataResult(var4, var5);
   }
}
