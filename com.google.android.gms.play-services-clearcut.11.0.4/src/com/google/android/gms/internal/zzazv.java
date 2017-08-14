package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzazv implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzazu[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      zzbak var4 = null;
      byte[] var5 = null;
      int[] var6 = null;
      String[] var7 = null;
      int[] var8 = null;
      byte[][] var9 = null;
      boolean var10 = true;
      zzcqn[] var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = (zzbak)zzb.zza(var2, var12, zzbak.CREATOR);
            break;
         case 3:
            var5 = zzb.zzt(var2, var12);
            break;
         case 4:
            var6 = zzb.zzw(var2, var12);
            break;
         case 5:
            var7 = zzb.zzA(var2, var12);
            break;
         case 6:
            var8 = zzb.zzw(var2, var12);
            break;
         case 7:
            var9 = zzb.zzu(var2, var12);
            break;
         case 8:
            var10 = zzb.zzc(var2, var12);
            break;
         case 9:
            var11 = (zzcqn[])zzb.zzb(var2, var12, zzcqn.CREATOR);
            break;
         default:
            zzb.zzb(var2, var12);
         }
      }

      zzb.zzF(var2, var3);
      return new zzazu(var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
