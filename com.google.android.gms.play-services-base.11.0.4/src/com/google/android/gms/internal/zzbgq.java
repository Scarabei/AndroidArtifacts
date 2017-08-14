package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbgq extends zza {
   public static final Creator CREATOR = new zzbgn();
   private int versionCode;
   final String key;
   final zzbgj zzaIV;

   zzbgq(int var1, String var2, zzbgj var3) {
      this.versionCode = var1;
      this.key = var2;
      this.zzaIV = var3;
   }

   zzbgq(String var1, zzbgj var2) {
      this.versionCode = 1;
      this.key = var1;
      this.zzaIV = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.key, false);
      zzd.zza(var1, 3, this.zzaIV, var2, false);
      zzd.zzI(var1, var5);
   }
}
