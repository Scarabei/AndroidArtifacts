package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbpv extends zza {
   public static final Creator CREATOR = new zzbpw();
   private final List zzaPl;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzb(var1, 2, this.zzaPl, false);
      zzd.zzI(var1, var5);
   }

   zzbpv(List var1) {
      this.zzaPl = var1;
   }
}
