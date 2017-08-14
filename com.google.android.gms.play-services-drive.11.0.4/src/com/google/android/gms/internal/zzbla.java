package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbla extends zza {
   public static final Creator CREATOR = new zzblb();
   private int zzaLU;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaLU);
      zzd.zzI(var1, var5);
   }

   public zzbla(int var1) {
      zzbo.zzb(var1 == 536870912 || var1 == 805306368, "Cannot create a new read-only contents!");
      this.zzaLU = var1;
   }
}
