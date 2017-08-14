package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzadk implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzadj[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      zzir var4 = null;
      String var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 2:
            var4 = (zzir)zzb.zza(var2, var6, zzir.CREATOR);
            break;
         case 3:
            var5 = zzb.zzq(var2, var6);
            break;
         default:
            zzb.zzb(var2, var6);
         }
      }

      zzb.zzF(var2, var3);
      return new zzadj(var4, var5);
   }
}
