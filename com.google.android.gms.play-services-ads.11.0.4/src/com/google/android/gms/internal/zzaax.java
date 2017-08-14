package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzaax extends zza {
   public static final Creator CREATOR = new zzaay();
   private Bundle zzTU;
   private zzaje zzTV;
   private PackageInfo zzSA;
   private ApplicationInfo applicationInfo;

   public zzaax(Bundle var1, zzaje var2, PackageInfo var3, ApplicationInfo var4) {
      this.zzTU = var1;
      this.zzTV = var2;
      this.zzSA = var3;
      this.applicationInfo = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzTU, false);
      zzd.zza(var1, 2, this.zzTV, var2, false);
      zzd.zza(var1, 3, this.zzSA, var2, false);
      zzd.zza(var1, 4, this.applicationInfo, var2, false);
      zzd.zzI(var1, var5);
   }
}
