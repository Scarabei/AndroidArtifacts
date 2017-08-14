package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbc[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      zzaf var5 = null;
      IBinder var6 = null;
      String var7 = null;
      String var8 = null;
      boolean var9 = false;
      ClientAppContext var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var11);
            break;
         case 2:
            var5 = (zzaf)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, zzaf.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var11);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var11);
            break;
         case 7:
            var10 = (ClientAppContext)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, ClientAppContext.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzbc(var4, var5, var6, var7, var8, var9, var10);
   }
}
