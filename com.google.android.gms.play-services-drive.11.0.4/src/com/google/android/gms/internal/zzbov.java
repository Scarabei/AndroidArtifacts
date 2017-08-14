package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;

public final class zzbov extends zza {
   public static final Creator CREATOR = new zzbow();
   private DriveId zzaOQ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOQ, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbov(DriveId var1) {
      this.zzaOQ = var1;
   }
}
