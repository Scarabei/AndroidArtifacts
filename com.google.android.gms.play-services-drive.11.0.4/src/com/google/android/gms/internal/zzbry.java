package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbry extends zza {
   public static final Creator CREATOR = new zzbrz();
   private boolean zzaRD;
   private String mName;
   private boolean zzaRE;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaRD);
      zzd.zza(var1, 3, this.mName, false);
      zzd.zza(var1, 4, this.zzaRE);
      zzd.zzI(var1, var5);
   }

   public zzbry(boolean var1, String var2, boolean var3) {
      this.zzaRD = var1;
      this.mName = var2;
      this.zzaRE = var3;
   }
}
