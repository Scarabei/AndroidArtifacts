package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.zzc;

public final class zzboz extends zza {
   public static final Creator CREATOR = new zzbpa();
   final zzc zzaOg;
   final boolean zzaOV;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOg, var2, false);
      zzd.zza(var1, 3, this.zzaOV);
      zzd.zzI(var1, var5);
   }

   public zzboz(zzc var1, boolean var2) {
      this.zzaOg = var1;
      this.zzaOV = var2;
   }
}
