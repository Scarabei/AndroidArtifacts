package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.nearby.messages.Strategy;

public final class zzay implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzax[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      zzaf var5 = null;
      Strategy var6 = null;
      IBinder var7 = null;
      String var8 = null;
      String var9 = null;
      boolean var10 = false;
      IBinder var11 = null;
      boolean var12 = false;
      ClientAppContext var13 = null;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         case 2:
            var5 = (zzaf)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, zzaf.CREATOR);
            break;
         case 3:
            var6 = (Strategy)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, Strategy.CREATOR);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var14);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var14);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14);
            break;
         case 10:
            var13 = (ClientAppContext)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, ClientAppContext.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var14);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzax(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }
}
