package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzatg extends zza {
   public static final Creator CREATOR = new zzath();
   private final int zzaoa;

   public zzatg(int var1) {
      this.zzaoa = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaoa);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      if (this.zzaoa == 1) {
         return "ScreenState: SCREEN_OFF";
      } else {
         return this.zzaoa == 2 ? "ScreenState: SCREEN_ON" : "ScreenState: UNKNOWN";
      }
   }
}
