package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import java.util.Arrays;

public final class zzcoe extends zza {
   public static final Creator CREATOR = new zzcof();
   private final String zzbwG;
   private final PayloadTransferUpdate zzbxt;

   public zzcoe(String var1, PayloadTransferUpdate var2) {
      this.zzbwG = var1;
      this.zzbxt = var2;
   }

   public final String zzzF() {
      return this.zzbwG;
   }

   public final PayloadTransferUpdate zzzM() {
      return this.zzbxt;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwG, this.zzbxt});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcoe) {
         zzcoe var2 = (zzcoe)var1;
         return zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.zzbxt, var2.zzbxt);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwG, false);
      zzd.zza(var1, 2, this.zzbxt, var2, false);
      zzd.zzI(var1, var5);
   }
}
