package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.List;

public final class zzaa extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzab();
   private final List zzbix;
   private final PendingIntent mPendingIntent;
   private final String mTag;

   public static zzaa zzB(List var0) {
      zzbo.zzb(var0, "geofence can't be null.");
      zzbo.zzb(!var0.isEmpty(), "Geofences must contains at least one id.");
      return new zzaa(var0, (PendingIntent)null, "");
   }

   public static zzaa zzb(PendingIntent var0) {
      zzbo.zzb(var0, "PendingIntent can not be null.");
      return new zzaa((List)null, var0, "");
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 1, this.zzbix, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.mPendingIntent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.mTag, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzaa(@Nullable List var1, @Nullable PendingIntent var2, String var3) {
      this.zzbix = var1 == null ? Collections.emptyList() : Collections.unmodifiableList(var1);
      this.mPendingIntent = var2;
      this.mTag = var3;
   }
}
