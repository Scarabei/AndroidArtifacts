package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbsa extends zza {
   public static final Creator CREATOR = new zzbsb();

   public final void writeToParcel(Parcel var1, int var2) {
      int var4 = zzd.zze(var1);
      zzd.zzI(var1, var4);
   }
}
