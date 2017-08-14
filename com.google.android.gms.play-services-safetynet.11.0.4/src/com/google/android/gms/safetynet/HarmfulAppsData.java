package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class HarmfulAppsData extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   public final String apkPackageName;
   public final byte[] apkSha256;
   public final int apkCategory;

   public HarmfulAppsData(String var1, byte[] var2, int var3) {
      this.apkPackageName = var1;
      this.apkSha256 = var2;
      this.apkCategory = var3;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.apkPackageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.apkSha256, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.apkCategory);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
