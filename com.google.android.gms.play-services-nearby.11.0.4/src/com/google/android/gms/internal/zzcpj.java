package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcpj extends zza {
   public static final Creator CREATOR = new zzcpk();
   private static final String zzbyL = null;
   public static final zzcpj zzbyM = new zzcpj("", (String)null);
   private int zzaku;
   private final String zzbyN;
   @Nullable
   private final String zzbyO;

   zzcpj(int var1, @Nullable String var2, @Nullable String var3) {
      this.zzaku = ((Integer)zzbo.zzu(var1)).intValue();
      this.zzbyN = var2 == null ? "" : var2;
      this.zzbyO = var3;
   }

   private zzcpj(String var1, String var2) {
      this(1, var1, (String)null);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzcpj)) {
         return false;
      } else {
         zzcpj var2 = (zzcpj)var1;
         return zzbe.equal(this.zzbyN, var2.zzbyN) && zzbe.equal(this.zzbyO, var2.zzbyO);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbyN, this.zzbyO});
   }

   public final String toString() {
      String var1 = this.zzbyN;
      String var2 = this.zzbyO;
      return (new StringBuilder(40 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("NearbyDevice{handle=").append(var1).append(", bluetoothAddress=").append(var2).append("}").toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 3, this.zzbyN, false);
      zzd.zza(var1, 6, this.zzbyO, false);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }
}
