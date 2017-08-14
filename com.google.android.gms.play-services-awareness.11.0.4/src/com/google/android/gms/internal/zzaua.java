package com.google.android.gms.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.ActivityRecognitionResult;

public final class zzaua implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzati[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      ActivityRecognitionResult var4 = null;
      zzast var5 = null;
      zzasx var6 = null;
      Location var7 = null;
      zzasz var8 = null;
      DataHolder var9 = null;
      zzate var10 = null;
      zzatg var11 = null;
      zzauh var12 = null;
      zzaue var13 = null;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 2:
            var4 = (ActivityRecognitionResult)zzb.zza(var2, var14, ActivityRecognitionResult.CREATOR);
            break;
         case 3:
            var5 = (zzast)zzb.zza(var2, var14, zzast.CREATOR);
            break;
         case 4:
            var6 = (zzasx)zzb.zza(var2, var14, zzasx.CREATOR);
            break;
         case 5:
            var7 = (Location)zzb.zza(var2, var14, Location.CREATOR);
            break;
         case 6:
            var8 = (zzasz)zzb.zza(var2, var14, zzasz.CREATOR);
            break;
         case 7:
            var9 = (DataHolder)zzb.zza(var2, var14, DataHolder.CREATOR);
            break;
         case 8:
            var10 = (zzate)zzb.zza(var2, var14, zzate.CREATOR);
            break;
         case 9:
            var11 = (zzatg)zzb.zza(var2, var14, zzatg.CREATOR);
            break;
         case 10:
            var12 = (zzauh)zzb.zza(var2, var14, zzauh.CREATOR);
            break;
         case 11:
            var13 = (zzaue)zzb.zza(var2, var14, zzaue.CREATOR);
            break;
         default:
            zzb.zzb(var2, var14);
         }
      }

      zzb.zzF(var2, var3);
      return new zzati(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }
}
