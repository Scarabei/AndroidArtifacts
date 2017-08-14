package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzq extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzr();
   final String zzaMh;
   final long zzaMi;
   final int zzaMj;

   public zzq(String var1, long var2, int var4) {
      this.zzaMh = var1;
      this.zzaMi = var2;
      this.zzaMj = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaMh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaMi);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaMj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
