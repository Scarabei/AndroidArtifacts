package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzblh extends zza {
   public static final Creator CREATOR = new zzbli();
   private DriveId zzaNF;
   private MetadataBundle zzaND;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNF, var2, false);
      zzd.zza(var1, 3, this.zzaND, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzblh(DriveId var1, MetadataBundle var2) {
      this.zzaNF = (DriveId)zzbo.zzu(var1);
      this.zzaND = (MetadataBundle)zzbo.zzu(var2);
   }
}
