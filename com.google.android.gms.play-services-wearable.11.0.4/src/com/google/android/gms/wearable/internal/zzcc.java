package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcb[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Uri var4 = null;
      Bundle var5 = null;
      byte[] var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var7, Uri.CREATOR);
            break;
         case 3:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var7);
            break;
         case 4:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var7);
            break;
         case 5:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var7);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzcb(var4, var5, var6);
   }
}
