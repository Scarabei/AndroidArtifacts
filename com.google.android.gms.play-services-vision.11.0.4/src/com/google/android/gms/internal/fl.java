package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class fl implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new fk[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      ft[] var4 = null;
      fe var5 = null;
      fe var6 = null;
      fe var7 = null;
      String var8 = null;
      float var9 = 0.0F;
      String var10 = null;
      int var11 = 0;
      boolean var12 = false;
      int var13 = 0;
      int var14 = 0;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 2:
            var4 = (ft[])zzb.zzb(var2, var15, ft.CREATOR);
            break;
         case 3:
            var5 = (fe)zzb.zza(var2, var15, fe.CREATOR);
            break;
         case 4:
            var6 = (fe)zzb.zza(var2, var15, fe.CREATOR);
            break;
         case 5:
            var7 = (fe)zzb.zza(var2, var15, fe.CREATOR);
            break;
         case 6:
            var8 = zzb.zzq(var2, var15);
            break;
         case 7:
            var9 = zzb.zzl(var2, var15);
            break;
         case 8:
            var10 = zzb.zzq(var2, var15);
            break;
         case 9:
            var11 = zzb.zzg(var2, var15);
            break;
         case 10:
            var12 = zzb.zzc(var2, var15);
            break;
         case 11:
            var13 = zzb.zzg(var2, var15);
            break;
         case 12:
            var14 = zzb.zzg(var2, var15);
            break;
         default:
            zzb.zzb(var2, var15);
         }
      }

      zzb.zzF(var2, var3);
      return new fk(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
   }
}
