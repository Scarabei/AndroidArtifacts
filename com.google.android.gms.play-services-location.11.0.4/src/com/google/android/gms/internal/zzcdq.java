package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcdq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcdp[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 1;
      zzcdn var5 = null;
      IBinder var6 = null;
      PendingIntent var7 = null;
      IBinder var8 = null;
      IBinder var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var10);
            break;
         case 2:
            var5 = (zzcdn)zzb.zza(var2, var10, zzcdn.CREATOR);
            break;
         case 3:
            var6 = zzb.zzr(var2, var10);
            break;
         case 4:
            var7 = (PendingIntent)zzb.zza(var2, var10, PendingIntent.CREATOR);
            break;
         case 5:
            var8 = zzb.zzr(var2, var10);
            break;
         case 6:
            var9 = zzb.zzr(var2, var10);
            break;
         default:
            zzb.zzb(var2, var10);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcdp(var4, var5, var6, var7, var8, var9);
   }
}
