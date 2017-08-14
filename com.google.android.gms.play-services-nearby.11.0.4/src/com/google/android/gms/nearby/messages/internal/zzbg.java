package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzcpj;
import com.google.android.gms.nearby.messages.Message;

public final class zzbg implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Update[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      Message var6 = null;
      zze var7 = null;
      zza var8 = null;
      zzcpj var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 3:
            var6 = (Message)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, Message.CREATOR);
            break;
         case 4:
            var7 = (zze)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, zze.CREATOR);
            break;
         case 5:
            var8 = (zza)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, zza.CREATOR);
            break;
         case 6:
            var9 = (zzcpj)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, zzcpj.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Update(var4, var5, var6, var7, var8, var9);
   }
}
