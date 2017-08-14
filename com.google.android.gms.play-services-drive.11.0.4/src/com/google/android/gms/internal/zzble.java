package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzble implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbld[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      MetadataBundle var4 = null;
      int var5 = 0;
      String var6 = null;
      DriveId var7 = null;
      Integer var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = (MetadataBundle)zzb.zza(var2, var9, MetadataBundle.CREATOR);
            break;
         case 3:
            var5 = zzb.zzg(var2, var9);
            break;
         case 4:
            var6 = zzb.zzq(var2, var9);
            break;
         case 5:
            var7 = (DriveId)zzb.zza(var2, var9, DriveId.CREATOR);
            break;
         case 6:
            var8 = zzb.zzh(var2, var9);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbld(var4, var5, var6, var7, var8);
   }
}
