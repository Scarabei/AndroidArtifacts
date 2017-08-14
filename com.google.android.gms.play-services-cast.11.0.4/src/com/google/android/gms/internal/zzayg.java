package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzayg implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzayf[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      double var4 = 0.0D;
      boolean var6 = false;
      int var7 = 0;
      ApplicationMetadata var8 = null;
      int var9 = 0;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzn(var2, var10);
            break;
         case 3:
            var6 = zzb.zzc(var2, var10);
            break;
         case 4:
            var7 = zzb.zzg(var2, var10);
            break;
         case 5:
            var8 = (ApplicationMetadata)zzb.zza(var2, var10, ApplicationMetadata.CREATOR);
            break;
         case 6:
            var9 = zzb.zzg(var2, var10);
            break;
         default:
            zzb.zzb(var2, var10);
         }
      }

      zzb.zzF(var2, var3);
      return new zzayf(var4, var6, var7, var8, var9);
   }
}
