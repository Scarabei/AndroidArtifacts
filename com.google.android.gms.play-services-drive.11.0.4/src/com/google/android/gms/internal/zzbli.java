package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbli implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzblh[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      DriveId var4 = null;
      MetadataBundle var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DriveId)zzb.zza(var2, var6, DriveId.CREATOR);
            break;
         case 3:
            var5 = (MetadataBundle)zzb.zza(var2, var6, MetadataBundle.CREATOR);
            break;
         default:
            zzb.zzb(var2, var6);
         }
      }

      zzb.zzF(var2, var3);
      return new zzblh(var4, var5);
   }
}
