package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzbto implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbtn[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      ArrayList var4 = null;
      DataHolder var5 = null;
      boolean var6 = false;
      ArrayList var7 = null;
      zzbtd var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzc(var2, var9, zzbtl.CREATOR);
            break;
         case 3:
            var5 = (DataHolder)zzb.zza(var2, var9, DataHolder.CREATOR);
            break;
         case 4:
            var6 = zzb.zzc(var2, var9);
            break;
         case 5:
            var7 = zzb.zzC(var2, var9);
            break;
         case 6:
            var8 = (zzbtd)zzb.zza(var2, var9, zzbtd.CREATOR);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbtn(var4, var5, var6, var7, var8);
   }
}
