package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzon extends zza {
   public static final Creator CREATOR = new zzoo();
   public final int versionCode;
   public final boolean zzIn;
   public final int zzIo;
   public final boolean zzIp;
   public final int zzIq;
   @Nullable
   public final zzlx zzIr;

   public zzon(NativeAdOptions var1) {
      this(3, var1.shouldReturnUrlsForImageAssets(), var1.getImageOrientation(), var1.shouldRequestMultipleImages(), var1.getAdChoicesPlacement(), var1.getVideoOptions() != null ? new zzlx(var1.getVideoOptions()) : null);
   }

   public zzon(int var1, boolean var2, int var3, boolean var4, int var5, zzlx var6) {
      this.versionCode = var1;
      this.zzIn = var2;
      this.zzIo = var3;
      this.zzIp = var4;
      this.zzIq = var5;
      this.zzIr = var6;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzIn);
      zzd.zzc(var1, 3, this.zzIo);
      zzd.zza(var1, 4, this.zzIp);
      zzd.zzc(var1, 5, this.zzIq);
      zzd.zza(var1, 6, this.zzIr, var2, false);
      zzd.zzI(var1, var5);
   }
}
