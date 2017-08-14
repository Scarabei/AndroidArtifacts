package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;

public final class zzbs implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbr[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      IBinder var5 = null;
      ConnectionResult var6 = null;
      boolean var7 = false;
      boolean var8 = false;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var9);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var9);
            break;
         case 3:
            var6 = (ConnectionResult)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, ConnectionResult.CREATOR);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var9);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var9);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzbr(var4, var5, var6, var7, var8);
   }
}
