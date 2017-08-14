package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbok extends zza {
   public static final Creator CREATOR = new zzbol();
   private List zzaOP;
   private int zzJA;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaOP, false);
      zzd.zzc(var1, 3, this.zzJA);
      zzd.zzI(var1, var5);
   }

   public zzbok(List var1, int var2) {
      this.zzaOP = var1;
      this.zzJA = var2;
   }
}
