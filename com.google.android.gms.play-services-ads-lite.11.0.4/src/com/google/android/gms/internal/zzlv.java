package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzlv extends zziv {
   public zzlv(zziv var1) {
      super(var1.zzAs, var1.height, var1.heightPixels, var1.zzAt, var1.width, var1.widthPixels, var1.zzAu, var1.zzAv, var1.zzAw, var1.zzAx);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var3 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzAs, false);
      zzd.zzc(var1, 3, this.height);
      zzd.zzc(var1, 6, this.width);
      zzd.zzI(var1, var3);
   }
}
