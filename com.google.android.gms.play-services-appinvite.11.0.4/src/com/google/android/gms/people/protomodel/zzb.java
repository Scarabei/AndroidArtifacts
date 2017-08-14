package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzc[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      ArrayList var5 = null;
      String var6 = null;
      Long var7 = null;
      Long var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var9);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var9, zzg.CREATOR);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var9);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzj(var2, var9);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzj(var2, var9);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzc(var4, var5, var6, var7, var8);
   }
}
