package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

/** @deprecated */
@Deprecated
public final class zzbjh extends zza {
   public static final Creator CREATOR = new zzbji();
   public final boolean zzaLi;
   public final String key;

   public zzbjh(boolean var1, String var2) {
      this.zzaLi = var1;
      this.key = zzbo.zzcF(var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaLi);
      zzd.zza(var1, 3, this.key, false);
      zzd.zzI(var1, var5);
   }
}
