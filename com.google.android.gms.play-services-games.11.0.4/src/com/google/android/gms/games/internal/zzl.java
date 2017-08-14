package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzl extends zzc {
   public static final Creator CREATOR = new zzm();
   private final Bundle zzbaI;
   private final IBinder zzbaJ;

   public zzl(zzp var1) {
      this.zzbaI = var1.zzuW();
      this.zzbaJ = var1.zzbaM;
   }

   zzl(Bundle var1, IBinder var2) {
      this.zzbaI = var1;
      this.zzbaJ = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbaI, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbaJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
