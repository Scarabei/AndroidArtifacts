package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class LocationSettingsResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final Status mStatus;
   private final LocationSettingsStates zzbim;
   public static final Creator CREATOR = new zzw();

   public LocationSettingsResult(Status var1, LocationSettingsStates var2) {
      this.mStatus = var1;
      this.zzbim = var2;
   }

   public LocationSettingsResult(Status var1) {
      this(var1, (LocationSettingsStates)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getLocationSettingsStates(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final LocationSettingsStates getLocationSettingsStates() {
      return this.zzbim;
   }

   public final Status getStatus() {
      return this.mStatus;
   }
}
