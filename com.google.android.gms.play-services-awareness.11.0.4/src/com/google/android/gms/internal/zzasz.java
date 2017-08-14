package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzasz extends zza {
   public static final Creator CREATOR = new zzata();
   private final int zzanV;
   private final int zzanW;

   public zzasz(int var1, int var2) {
      this.zzanV = var1;
      this.zzanW = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzanV);
      zzd.zzc(var1, 3, this.zzanW);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = String.valueOf(Integer.toString(this.zzanV));
      String var2 = String.valueOf(Integer.toString(this.zzanW));
      return (new StringBuilder(41 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("ConnectionState = ").append(var1).append(" NetworkMeteredState = ").append(var2).toString();
   }
}
