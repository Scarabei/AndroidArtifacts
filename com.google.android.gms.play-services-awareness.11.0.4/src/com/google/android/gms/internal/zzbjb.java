package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbjb extends zza {
   public static final Creator CREATOR = new zzbjp();
   private int type;
   private List zzaLc;

   zzbjb(int var1, List var2) {
      this.type = var1;
      this.zzaLc = var2;
   }

   public static zzbjb zza(int var0, List var1) {
      return new zzbjb(var0, var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.type);
      zzd.zzb(var1, 3, this.zzaLc, false);
      zzd.zzI(var1, var5);
   }
}
