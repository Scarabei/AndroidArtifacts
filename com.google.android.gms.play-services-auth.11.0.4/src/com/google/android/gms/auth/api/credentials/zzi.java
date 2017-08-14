package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PasswordSpecification[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      ArrayList var6 = null;
      ArrayList var7 = null;
      int var8 = 0;
      int var9 = 0;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var10);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var10);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PasswordSpecification(var4, var5, var6, var7, var8, var9);
   }
}
