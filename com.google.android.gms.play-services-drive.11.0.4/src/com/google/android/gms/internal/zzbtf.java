package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbtf extends zza {
   public static final Creator CREATOR = new zzbtg();
   private String zzaRF;
   private int mIndex;
   private boolean zzaRG;
   private int zzaRH;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaRF, false);
      zzd.zzc(var1, 3, this.mIndex);
      zzd.zza(var1, 4, this.zzaRG);
      zzd.zzc(var1, 5, this.zzaRH);
      zzd.zzI(var1, var5);
   }

   zzbtf(String var1, int var2, boolean var3, int var4) {
      this.zzaRF = var1;
      this.mIndex = var2;
      this.zzaRG = var3;
      this.zzaRH = var4;
   }
}
