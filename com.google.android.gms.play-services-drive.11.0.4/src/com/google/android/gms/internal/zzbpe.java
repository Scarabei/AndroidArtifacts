package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.zzg;
import java.util.ArrayList;

public final class zzbpe implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbpd[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      long var4 = 0L;
      long var6 = 0L;
      int var8 = 0;
      ArrayList var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzi(var2, var10);
            break;
         case 3:
            var6 = zzb.zzi(var2, var10);
            break;
         case 4:
            var8 = zzb.zzg(var2, var10);
            break;
         case 5:
            var9 = zzb.zzc(var2, var10, zzg.CREATOR);
            break;
         default:
            zzb.zzb(var2, var10);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbpd(var4, var6, var8, var9);
   }
}
