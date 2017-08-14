package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzs implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MaskedWalletRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      Cart var11 = null;
      boolean var12 = false;
      boolean var13 = false;
      CountrySpecification[] var14 = null;
      boolean var15 = true;
      boolean var16 = true;
      ArrayList var17 = null;
      PaymentMethodTokenizationParameters var18 = null;
      ArrayList var19 = null;
      String var20 = null;

      while(var2.dataPosition() < var3) {
         int var21;
         switch((var21 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 9:
            var11 = (Cart)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var21, Cart.CREATOR);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 12:
            var14 = (CountrySpecification[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var21, CountrySpecification.CREATOR);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 14:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21, com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR);
            break;
         case 16:
            var18 = (PaymentMethodTokenizationParameters)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var21, PaymentMethodTokenizationParameters.CREATOR);
            break;
         case 17:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var21);
            break;
         case 18:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var21);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MaskedWalletRequest(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20);
   }
}
