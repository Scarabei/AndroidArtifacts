package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbju implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbjt[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      zzbiw var5 = null;
      IBinder var6 = null;
      PendingIntent var7 = null;
      String var8 = null;
      long var9 = 0L;
      long var11 = 0L;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var13);
            break;
         case 3:
            var5 = (zzbiw)zzb.zza(var2, var13, zzbiw.CREATOR);
            break;
         case 4:
            var6 = zzb.zzr(var2, var13);
            break;
         case 5:
            var7 = (PendingIntent)zzb.zza(var2, var13, PendingIntent.CREATOR);
            break;
         case 6:
            var8 = zzb.zzq(var2, var13);
            break;
         case 7:
            var9 = zzb.zzi(var2, var13);
            break;
         case 8:
            var11 = zzb.zzi(var2, var13);
            break;
         default:
            zzb.zzb(var2, var13);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbjt(var4, var5, var6, var7, var8, var9, var11);
   }
}
