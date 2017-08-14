package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.List;

/** @deprecated */
@Deprecated
public final class zzaj extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzak();
   private String name;
   private String address;
   private String zzbkK;
   private String zzbkL;
   private List zzbkM;

   public zzaj(String var1, String var2, String var3, String var4, List var5) {
      this.name = var1;
      this.address = var2;
      this.zzbkK = var3;
      this.zzbkL = var4;
      this.zzbkM = var5;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzbkK).zzg("regularOpenHours", this.zzbkL).zzg("attributions", this.zzbkM).toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.name, this.address, this.zzbkK, this.zzbkL});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzaj)) {
         return false;
      } else {
         zzaj var2 = (zzaj)var1;
         return zzbe.equal(this.name, var2.name) && zzbe.equal(this.address, var2.address) && zzbe.equal(this.zzbkK, var2.zzbkK) && zzbe.equal(this.zzbkL, var2.zzbkL) && zzbe.equal(this.zzbkM, var2.zzbkM);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.name, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.address, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbkK, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbkL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 5, this.zzbkM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
