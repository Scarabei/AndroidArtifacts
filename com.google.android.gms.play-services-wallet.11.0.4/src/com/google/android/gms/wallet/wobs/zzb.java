package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new CommonWalletObject[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      int var12 = 0;
      ArrayList var13 = new ArrayList();
      zzm var14 = null;
      ArrayList var15 = new ArrayList();
      String var16 = null;
      String var17 = null;
      ArrayList var18 = new ArrayList();
      boolean var19 = false;
      ArrayList var20 = new ArrayList();
      ArrayList var21 = new ArrayList();
      ArrayList var22 = new ArrayList();

      while(var2.dataPosition() < var3) {
         int var23;
         switch((var23 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var23);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, zzq.CREATOR);
            break;
         case 12:
            var14 = (zzm)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var23, zzm.CREATOR);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, LatLng.CREATOR);
            break;
         case 14:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var23);
            break;
         case 16:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, zze.CREATOR);
            break;
         case 17:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23);
            break;
         case 18:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, zzo.CREATOR);
            break;
         case 19:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, zzk.CREATOR);
            break;
         case 20:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, zzo.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var23);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new CommonWalletObject(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22);
   }
}
