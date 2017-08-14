package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzaay implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaax[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      Bundle var4 = null;
      zzaje var5 = null;
      PackageInfo var6 = null;
      ApplicationInfo var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzs(var2, var8);
            break;
         case 2:
            var5 = (zzaje)zzb.zza(var2, var8, zzaje.CREATOR);
            break;
         case 3:
            var6 = (PackageInfo)zzb.zza(var2, var8, PackageInfo.CREATOR);
            break;
         case 4:
            var7 = (ApplicationInfo)zzb.zza(var2, var8, ApplicationInfo.CREATOR);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzaax(var4, var5, var6, var7);
   }
}
