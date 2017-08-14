package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbqx extends zza {
   public static final Creator CREATOR = new zzbqy();
   private DriveId zzaNt;
   private MetadataBundle zzaNu;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNt, var2, false);
      zzd.zza(var1, 3, this.zzaNu, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbqx(DriveId var1, MetadataBundle var2) {
      this.zzaNt = var1;
      this.zzaNu = var2;
   }
}
