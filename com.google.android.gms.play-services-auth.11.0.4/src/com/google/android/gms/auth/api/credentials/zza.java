package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Credential[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      Uri var7 = null;
      ArrayList var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      String var13 = null;
      String var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 3:
            var7 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, Uri.CREATOR);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, IdToken.CREATOR);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 9:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 10:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Credential(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
   }
}
