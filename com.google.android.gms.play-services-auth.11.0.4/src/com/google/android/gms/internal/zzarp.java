package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzarp extends zza {
   public static final Creator CREATOR = new zzarq();
   private int zzaku;
   private final Credential zzalx;

   zzarp(int var1, Credential var2) {
      this.zzaku = var1;
      this.zzalx = var2;
   }

   public zzarp(Credential var1) {
      this(1, var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzalx, var2, false);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }
}
