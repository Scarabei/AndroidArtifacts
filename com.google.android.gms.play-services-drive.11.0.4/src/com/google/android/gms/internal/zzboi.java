package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;

public final class zzboi extends zza {
   public static final Creator CREATOR = new zzboj();
   private DriveId zzaNt;
   private boolean zzaOO;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNt, var2, false);
      zzd.zza(var1, 3, this.zzaOO);
      zzd.zzI(var1, var5);
   }

   public zzboi(DriveId var1, boolean var2) {
      this.zzaNt = var1;
      this.zzaOO = var2;
   }
}
