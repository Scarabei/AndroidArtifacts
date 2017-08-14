package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbpq extends zza {
   public static final Creator CREATOR = new zzbpr();
   final MetadataBundle zzaND;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaND, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbpq(MetadataBundle var1) {
      this.zzaND = var1;
   }
}
