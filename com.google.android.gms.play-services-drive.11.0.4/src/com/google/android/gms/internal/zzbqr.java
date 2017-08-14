package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbqr extends zza {
   public static final Creator CREATOR = new zzbqs();
   private final List zzaPv;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzb(var1, 2, this.zzaPv, false);
      zzd.zzI(var1, var5);
   }

   public zzbqr(List var1) {
      this.zzaPv = var1;
   }
}
