package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.HashSet;

public final class zzcro implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcri.zzc[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      HashSet var4 = new HashSet();
      int var5 = 0;
      String var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 1:
            var5 = zzb.zzg(var2, var7);
            var4.add(Integer.valueOf(1));
            break;
         case 2:
            var6 = zzb.zzq(var2, var7);
            var4.add(Integer.valueOf(2));
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      if (var2.dataPosition() != var3) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var3).toString(), var2);
      } else {
         return new zzcri.zzc(var4, var5, var6);
      }
   }
}
