package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbgf extends zza {
   public static final Creator CREATOR = new zzbgh();
   private int versionCode;
   final String zzaIF;
   final int zzaIG;

   zzbgf(int var1, String var2, int var3) {
      this.versionCode = var1;
      this.zzaIF = var2;
      this.zzaIG = var3;
   }

   zzbgf(String var1, int var2) {
      this.versionCode = 1;
      this.zzaIF = var1;
      this.zzaIG = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzaIF, false);
      zzd.zzc(var1, 3, this.zzaIG);
      zzd.zzI(var1, var5);
   }
}
