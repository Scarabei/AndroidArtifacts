package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbld extends zza {
   public static final Creator CREATOR = new zzble();
   private MetadataBundle zzaND;
   private int zzaLT;
   private String zzaoy;
   private DriveId zzaME;
   private Integer zzaNE;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaND, var2, false);
      zzd.zzc(var1, 3, this.zzaLT);
      zzd.zza(var1, 4, this.zzaoy, false);
      zzd.zza(var1, 5, this.zzaME, var2, false);
      zzd.zza(var1, 6, this.zzaNE, false);
      zzd.zzI(var1, var5);
   }

   public zzbld(MetadataBundle var1, int var2, String var3, DriveId var4, Integer var5) {
      this.zzaND = var1;
      this.zzaLT = var2;
      this.zzaoy = var3;
      this.zzaME = var4;
      this.zzaNE = var5;
   }
}
