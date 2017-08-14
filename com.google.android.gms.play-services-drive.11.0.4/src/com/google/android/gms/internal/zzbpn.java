package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.zzv;

public final class zzbpn extends zzv {
   public static final Creator CREATOR = new zzbpo();
   final DataHolder zzaPj;

   protected final void zzJ(Parcel var1, int var2) {
      int var6 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaPj, var2, false);
      zzd.zzI(var1, var6);
   }

   public zzbpn(DataHolder var1) {
      this.zzaPj = var1;
   }
}
