package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzadj extends zza {
   public static final Creator CREATOR = new zzadk();
   public final zzir zzSz;
   public final String zzvR;

   public zzadj(zzir var1, String var2) {
      this.zzSz = var1;
      this.zzvR = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzSz, var2, false);
      zzd.zza(var1, 3, this.zzvR, false);
      zzd.zzI(var1, var5);
   }
}
