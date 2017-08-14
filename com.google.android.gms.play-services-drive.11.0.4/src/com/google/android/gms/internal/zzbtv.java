package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbtv extends zza {
   public static final Creator CREATOR = new zzbtw();
   private int zzaRI;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaRI);
      zzd.zzI(var1, var5);
   }

   public zzbtv(int var1) {
      this.zzaRI = var1;
   }
}
