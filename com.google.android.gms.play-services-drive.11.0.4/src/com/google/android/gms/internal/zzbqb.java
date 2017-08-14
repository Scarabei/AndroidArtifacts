package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;

public final class zzbqb extends zza {
   public static final Creator CREATOR = new zzbqc();
   private DriveId zzaNt;
   private int zzaLU;
   private int zzaPo;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNt, var2, false);
      zzd.zzc(var1, 3, this.zzaLU);
      zzd.zzc(var1, 4, this.zzaPo);
      zzd.zzI(var1, var5);
   }

   public zzbqb(DriveId var1, int var2, int var3) {
      this.zzaNt = var1;
      this.zzaLU = var2;
      this.zzaPo = var3;
   }
}
