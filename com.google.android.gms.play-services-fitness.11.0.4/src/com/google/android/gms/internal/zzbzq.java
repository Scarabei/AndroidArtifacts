package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.fitness.data.DataSource;

public final class zzbzq extends zza {
   private final int zzaku;
   private final DataSource zzaUd;
   public static final Creator CREATOR = new zzbzr();

   zzbzq(int var1, DataSource var2) {
      this.zzaku = var1;
      this.zzaUd = var2;
   }

   public final DataSource getDataSource() {
      return this.zzaUd;
   }

   public final String toString() {
      return String.format("ApplicationUnregistrationRequest{%s}", this.zzaUd);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzaUd, var2, false);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }
}
