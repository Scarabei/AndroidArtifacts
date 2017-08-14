package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;

public final class zzcof implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcoe[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      PayloadTransferUpdate var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var6);
            break;
         case 2:
            var5 = (PayloadTransferUpdate)zzb.zza(var2, var6, PayloadTransferUpdate.CREATOR);
            break;
         default:
            zzb.zzb(var2, var6);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcoe(var4, var5);
   }
}
