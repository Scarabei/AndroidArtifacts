package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbpj extends zza {
   public static final Creator CREATOR = new zzbpk();
   private ParcelFileDescriptor zzaPh;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaPh, var5, false);
      zzd.zzI(var1, var6);
   }

   public zzbpj(ParcelFileDescriptor var1) {
      this.zzaPh = var1;
   }
}
