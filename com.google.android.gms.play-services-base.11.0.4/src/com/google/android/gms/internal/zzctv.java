package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzctv extends zza {
   public static final Creator CREATOR = new zzctw();
   private int zzaku;
   private zzbp zzbCU;

   zzctv(int var1, zzbp var2) {
      this.zzaku = var1;
      this.zzbCU = var2;
   }

   public zzctv(zzbp var1) {
      this(1, var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzbCU, var2, false);
      zzd.zzI(var1, var5);
   }
}
