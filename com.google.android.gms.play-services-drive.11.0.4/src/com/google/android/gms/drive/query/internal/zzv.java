package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;

public final class zzv extends zza {
   public static final Creator CREATOR = new zzw();
   private FilterHolder zzaRt;

   zzv(FilterHolder var1) {
      this.zzaRt = var1;
   }

   public zzv(Filter var1) {
      this(new FilterHolder(var1));
   }

   public final Object zza(zzj var1) {
      return var1.zzv(this.zzaRt.getFilter().zza(var1));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaRt, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
