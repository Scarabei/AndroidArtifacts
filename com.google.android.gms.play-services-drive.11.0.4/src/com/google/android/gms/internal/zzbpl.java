package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.zzv;

public final class zzbpl extends zzv {
   public static final Creator CREATOR = new zzbpm();
   final DataHolder zzaPi;
   final boolean zzaNP;

   protected final void zzJ(Parcel var1, int var2) {
      int var6 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaPi, var2, false);
      zzd.zza(var1, 3, this.zzaNP);
      zzd.zzI(var1, var6);
   }

   public zzbpl(DataHolder var1, boolean var2) {
      this.zzaPi = var1;
      this.zzaNP = var2;
   }
}
