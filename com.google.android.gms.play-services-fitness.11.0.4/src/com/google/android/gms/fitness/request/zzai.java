package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbwx;
import com.google.android.gms.internal.zzbwy;

public final class zzai extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final DataType zzaUe;
   private final zzbwx zzaWR;
   public static final Creator CREATOR = new zzaj();

   zzai(int var1, DataType var2, IBinder var3) {
      this.zzaku = var1;
      this.zzaUe = var2;
      this.zzaWR = zzbwy.zzT(var3);
   }

   public zzai(DataType var1, zzbwx var2) {
      this.zzaku = 3;
      this.zzaUe = var1;
      this.zzaWR = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWR == null ? null : this.zzaWR.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
