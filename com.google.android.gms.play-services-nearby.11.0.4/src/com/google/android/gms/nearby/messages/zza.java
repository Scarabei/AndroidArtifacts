package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzcpj;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Message[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      byte[] var5 = null;
      String var6 = null;
      String var7 = null;
      zzcpj[] var8 = null;
      long var9 = 0L;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var11);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 4:
            var8 = (zzcpj[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11, zzcpj.CREATOR);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var11);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Message(var4, var5, var6, var7, var8, var9);
   }
}
