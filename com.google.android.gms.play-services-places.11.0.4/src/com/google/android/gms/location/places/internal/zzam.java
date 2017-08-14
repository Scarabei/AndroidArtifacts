package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzam extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private int zzbkP;
   private int zzbkQ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbkP);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbkQ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzam(int var1, int var2) {
      this.zzbkP = var1;
      this.zzbkQ = var2;
   }
}
