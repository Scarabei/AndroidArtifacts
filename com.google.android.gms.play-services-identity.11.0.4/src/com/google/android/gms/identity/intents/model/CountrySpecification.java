package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zzd;

public class CountrySpecification extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zza();
   private String zzVJ;

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzVJ, false);
      zzd.zzI(var1, var5);
   }

   public CountrySpecification(String var1) {
      this.zzVJ = var1;
   }

   public String getCountryCode() {
      return this.zzVJ;
   }
}
