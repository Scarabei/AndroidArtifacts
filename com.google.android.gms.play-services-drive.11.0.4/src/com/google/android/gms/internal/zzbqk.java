package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzp;

public final class zzbqk extends zza {
   public static final Creator CREATOR = new zzbql();
   private DriveId zzaLV;
   private int zzaJo;
   private zzp zzaNs;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaLV, var2, false);
      zzd.zzc(var1, 3, this.zzaJo);
      zzd.zza(var1, 4, this.zzaNs, var2, false);
      zzd.zzI(var1, var5);
   }

   zzbqk(DriveId var1, int var2, zzp var3) {
      this.zzaLV = var1;
      this.zzaJo = var2;
      this.zzaNs = var3;
   }

   public zzbqk(DriveId var1, int var2) {
      this(var1, var2, (zzp)null);
   }
}
