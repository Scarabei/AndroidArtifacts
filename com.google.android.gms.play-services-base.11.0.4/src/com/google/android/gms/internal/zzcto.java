package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcto implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzctn[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      Intent var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var7);
            break;
         case 2:
            var5 = zzb.zzg(var2, var7);
            break;
         case 3:
            var6 = (Intent)zzb.zza(var2, var7, Intent.CREATOR);
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      zzb.zzF(var2, var3);
      return new zzctn(var4, var5, var6);
   }
}
