package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class DiscoveryOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   private final Strategy zzbwl;

   public DiscoveryOptions(Strategy var1) {
      this.zzbwl = var1;
   }

   public final Strategy getStrategy() {
      return this.zzbwl;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwl});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof DiscoveryOptions) {
         DiscoveryOptions var2 = (DiscoveryOptions)var1;
         return zzbe.equal(this.zzbwl, var2.zzbwl);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStrategy(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
