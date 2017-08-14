package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.HashSet;

public final class zzcrl implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcri.zzb[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      HashSet var4 = new HashSet();
      int var5 = 0;
      zzcri.zzb.zza var6 = null;
      zzcri.zzb.zzb var7 = null;
      int var8 = 0;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 1:
            var5 = zzb.zzg(var2, var9);
            var4.add(Integer.valueOf(1));
            break;
         case 2:
            var6 = (zzcri.zzb.zza)zzb.zza(var2, var9, zzcri.zzb.zza.CREATOR);
            var4.add(Integer.valueOf(2));
            break;
         case 3:
            var7 = (zzcri.zzb.zzb)zzb.zza(var2, var9, zzcri.zzb.zzb.CREATOR);
            var4.add(Integer.valueOf(3));
            break;
         case 4:
            var8 = zzb.zzg(var2, var9);
            var4.add(Integer.valueOf(4));
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      if (var2.dataPosition() != var3) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var3).toString(), var2);
      } else {
         return new zzcri.zzb(var4, var5, var6, var7, var8);
      }
   }
}
