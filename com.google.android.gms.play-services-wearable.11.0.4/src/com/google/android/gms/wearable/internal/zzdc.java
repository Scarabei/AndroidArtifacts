package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;

public final class zzdc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzdd();
   public final int statusCode;
   public final ParcelFileDescriptor zzbww;

   public zzdc(int var1, ParcelFileDescriptor var2) {
      this.statusCode = var1;
      this.zzbww = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.statusCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbww, var5, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var6);
   }
}
