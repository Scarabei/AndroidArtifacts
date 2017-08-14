package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbub extends zza {
   public static final Creator CREATOR = new zzbuc();
   private int mIndex;
   private int zzaRJ;
   private int zzaRK;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.mIndex);
      zzd.zzc(var1, 3, this.zzaRJ);
      zzd.zzc(var1, 4, this.zzaRK);
      zzd.zzI(var1, var5);
   }

   public zzbub(int var1, int var2, int var3) {
      this.mIndex = var1;
      this.zzaRJ = var2;
      this.zzaRK = var3;
   }
}
