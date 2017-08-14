package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbvs;
import com.google.android.gms.internal.zzbvt;

public final class zzf extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int versionCode;
   private final zzbvs zzaWp;
   private DataType zzaSZ;
   private final boolean zzaWq;
   public static final Creator CREATOR = new zzg();

   zzf(int var1, IBinder var2, DataType var3, boolean var4) {
      this.versionCode = var1;
      this.zzaWp = zzbvt.zzO(var2);
      this.zzaSZ = var3;
      this.zzaWq = var4;
   }

   public zzf(zzbvs var1, DataType var2, boolean var3) {
      this.versionCode = 3;
      this.zzaWp = var1;
      this.zzaSZ = var2;
      this.zzaWq = var3;
   }

   public final String toString() {
      return String.format("DailyTotalRequest{%s}", this.zzaSZ == null ? "null" : this.zzaSZ.zztO());
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaWp.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaSZ, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaWq);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
