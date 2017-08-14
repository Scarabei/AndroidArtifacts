package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzbte implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbtd[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      long var4 = 0L;
      ArrayList var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzi(var2, var7);
            break;
         case 3:
            var6 = zzb.zzc(var2, var7, zzbtl.CREATOR);
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbtd(var4, var6);
   }
}
