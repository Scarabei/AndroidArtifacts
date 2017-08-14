package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzate extends zza {
   public static final Creator CREATOR = new zzatf();
   private final int zzanY;
   private final double zzanZ;

   public zzate(int var1, double var2) {
      this.zzanY = var1;
      this.zzanZ = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzanY);
      zzd.zza(var1, 3, this.zzanZ);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = String.valueOf(Integer.toString(this.zzanY));
      double var2 = this.zzanZ;
      return (new StringBuilder(69 + String.valueOf(var1).length())).append("PowerConnectionState = ").append(var1).append(" Battery Percentage = ").append(var2).toString();
   }
}
