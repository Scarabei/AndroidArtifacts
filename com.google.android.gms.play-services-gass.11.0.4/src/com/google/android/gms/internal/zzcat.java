package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzcat extends zza {
   public static final Creator CREATOR = new zzcau();
   private int versionCode;
   private String packageName;
   private String zzbfs;

   zzcat(int var1, String var2, String var3) {
      this.versionCode = var1;
      this.packageName = var2;
      this.zzbfs = var3;
   }

   public zzcat(String var1, String var2) {
      this(1, var1, var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.packageName, false);
      zzd.zza(var1, 3, this.zzbfs, false);
      zzd.zzI(var1, var5);
   }
}
