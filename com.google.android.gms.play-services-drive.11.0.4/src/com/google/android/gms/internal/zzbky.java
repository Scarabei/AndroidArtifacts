package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.zzc;

public final class zzbky extends zza {
   public static final Creator CREATOR = new zzbkz();
   private zzc zzaNv;
   private Boolean zzaNz;
   private int zzaNx;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNv, var2, false);
      zzd.zza(var1, 3, this.zzaNz, false);
      zzd.zzc(var1, 4, this.zzaNx);
      zzd.zzI(var1, var5);
   }

   zzbky(zzc var1, Boolean var2, int var3) {
      this.zzaNv = var1;
      this.zzaNz = var2;
      this.zzaNx = var3;
   }

   public zzbky(int var1, boolean var2) {
      this((zzc)null, false, var1);
   }
}
