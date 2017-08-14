package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public final class zzcdo implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcdn[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      LocationRequest var4 = null;
      Object var5 = zzcdn.zzbiU;
      String var6 = null;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      String var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var4 = (LocationRequest)zzb.zza(var2, var11, LocationRequest.CREATOR);
            break;
         case 2:
         case 3:
         case 4:
         default:
            zzb.zzb(var2, var11);
            break;
         case 5:
            var5 = zzb.zzc(var2, var11, zzcbz.CREATOR);
            break;
         case 6:
            var6 = zzb.zzq(var2, var11);
            break;
         case 7:
            var7 = zzb.zzc(var2, var11);
            break;
         case 8:
            var8 = zzb.zzc(var2, var11);
            break;
         case 9:
            var9 = zzb.zzc(var2, var11);
            break;
         case 10:
            var10 = zzb.zzq(var2, var11);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcdn(var4, (List)var5, var6, var7, var8, var9, var10);
   }
}
