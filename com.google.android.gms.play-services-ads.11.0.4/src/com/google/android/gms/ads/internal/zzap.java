package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzap extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzaq();
   public final boolean zzur;
   public final boolean zzus;
   private String zzut;
   public final boolean zzuu;
   public final float zzuv;
   public final int zzuw;
   public final boolean zzux;

   public zzap(boolean var1, boolean var2, boolean var3, float var4, int var5, boolean var6) {
      this(var1, var2, (String)null, var3, var4, var5, var6);
   }

   zzap(boolean var1, boolean var2, String var3, boolean var4, float var5, int var6, boolean var7) {
      this.zzur = var1;
      this.zzus = var2;
      this.zzut = var3;
      this.zzuu = var4;
      this.zzuv = var5;
      this.zzuw = var6;
      this.zzux = var7;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzur);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzus);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzut, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzuu);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzuv);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzuw);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzux);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
