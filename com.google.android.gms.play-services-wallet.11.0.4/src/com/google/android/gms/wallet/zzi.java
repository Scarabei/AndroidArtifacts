package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GiftCardWalletObject[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      CommonWalletObject var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      long var8 = 0L;
      String var10 = null;
      long var11 = 0L;
      String var13 = null;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 2:
            var4 = (CommonWalletObject)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, CommonWalletObject.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var14);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var14);
            break;
         case 9:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var14);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GiftCardWalletObject(var4, var5, var6, var7, var8, var10, var11, var13);
   }
}
