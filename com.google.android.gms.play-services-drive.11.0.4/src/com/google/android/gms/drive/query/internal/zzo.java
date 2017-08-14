package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzo implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzn[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      MetadataBundle var4 = null;

      while(var2.dataPosition() < var3) {
         int var5;
         switch((var5 = var2.readInt()) & 65535) {
         case 1:
            var4 = (MetadataBundle)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var5, MetadataBundle.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var5);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzn(var4);
   }
}
