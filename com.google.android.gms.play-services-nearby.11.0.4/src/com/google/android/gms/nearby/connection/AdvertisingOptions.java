package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class AdvertisingOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zza();
   private final Strategy zzbwl;
   @Nullable
   private final boolean zzbwm;

   public AdvertisingOptions(Strategy var1) {
      this(var1, true);
   }

   public AdvertisingOptions(Strategy var1, @Nullable boolean var2) {
      this.zzbwl = var1;
      this.zzbwm = var2;
   }

   public final Strategy getStrategy() {
      return this.zzbwl;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwl, this.zzbwm});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof AdvertisingOptions) {
         AdvertisingOptions var2 = (AdvertisingOptions)var1;
         return zzbe.equal(this.zzbwl, var2.zzbwl) && zzbe.equal(this.zzbwm, var2.zzbwm);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStrategy(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbwm);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
