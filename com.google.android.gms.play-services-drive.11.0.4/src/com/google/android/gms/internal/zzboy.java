package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zza;
import java.util.ArrayList;

public final class zzboy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbox[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      DataHolder var4 = null;
      ArrayList var5 = null;
      zza var6 = null;
      boolean var7 = false;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DataHolder)zzb.zza(var2, var8, DataHolder.CREATOR);
            break;
         case 3:
            var5 = zzb.zzc(var2, var8, DriveId.CREATOR);
            break;
         case 4:
            var6 = (zza)zzb.zza(var2, var8, zza.CREATOR);
            break;
         case 5:
            var7 = zzb.zzc(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbox(var4, var5, var6, var7);
   }
}
