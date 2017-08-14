package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbku extends zza {
   public static final Creator CREATOR = new zzbkv();
   private List zzaMZ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzb(var1, 2, this.zzaMZ, false);
      zzd.zzI(var1, var5);
   }

   public zzbku(List var1) {
      this.zzaMZ = var1;
   }
}
