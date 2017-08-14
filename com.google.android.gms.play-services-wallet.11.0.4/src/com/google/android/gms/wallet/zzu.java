package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzu implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new NotifyTransactionStatusRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      String var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var7);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var7);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var7);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var7);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new NotifyTransactionStatusRequest(var4, var5, var6);
   }
}
