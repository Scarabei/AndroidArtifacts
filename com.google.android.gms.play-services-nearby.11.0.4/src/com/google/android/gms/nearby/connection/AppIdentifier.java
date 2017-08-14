package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

/** @deprecated */
@Deprecated
public final class AppIdentifier extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private final String zzaVf;

   public AppIdentifier(String var1) {
      this.zzaVf = zzbo.zzh(var1, "Missing application identifier value");
   }

   public final String getIdentifier() {
      return this.zzaVf;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getIdentifier(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
