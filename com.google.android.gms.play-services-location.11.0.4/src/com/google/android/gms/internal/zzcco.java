package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzcco extends zza implements Result {
   private static zzcco zzbiJ;
   private final Status mStatus;
   public static final Creator CREATOR;

   public zzcco(Status var1) {
      this.mStatus = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getStatus(), var2, false);
      zzd.zzI(var1, var5);
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   static {
      zzbiJ = new zzcco(Status.zzaBm);
      CREATOR = new zzccp();
   }
}
