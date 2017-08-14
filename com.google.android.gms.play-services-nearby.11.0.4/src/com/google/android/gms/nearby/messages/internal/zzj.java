package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzj extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzk();
   private int versionCode;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;
   private int zzbzc;

   zzj(int var1, ClientAppContext var2, int var3) {
      this.versionCode = var1;
      this.zzbzb = var2;
      this.zzbzc = var3;
   }

   public zzj(int var1) {
      this(1, (ClientAppContext)null, var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbzc);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
