package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzad extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzae();
   private int zzaku;
   private String zzbxU;
   private String type;

   zzad(int var1, String var2, String var3) {
      this.zzaku = var1;
      this.zzbxU = var2;
      this.type = var3;
   }

   public zzad(String var1, String var2) {
      this(1, var1, var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbxU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.type, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = this.zzbxU;
      String var2 = this.type;
      return (new StringBuilder(17 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("namespace=").append(var1).append(", type=").append(var2).toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzad && this.hashCode() == var1.hashCode()) {
         zzad var2 = (zzad)var1;
         return com.google.android.gms.common.internal.zzbe.equal(this.zzbxU, var2.zzbxU) && com.google.android.gms.common.internal.zzbe.equal(this.type, var2.type);
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxU, this.type});
   }
}
