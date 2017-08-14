package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbpb extends zza {
   public static final Creator CREATOR = new zzbpc();
   final zzbog zzaOW;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOW, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbpb(zzbog var1) {
      this.zzaOW = var1;
   }
}
