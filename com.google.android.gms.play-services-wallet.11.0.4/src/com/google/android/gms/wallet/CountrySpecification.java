package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/** @deprecated */
@Deprecated
public class CountrySpecification extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private String zzVJ;

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzVJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public CountrySpecification(String var1) {
      this.zzVJ = var1;
   }

   public String getCountryCode() {
      return this.zzVJ;
   }
}
