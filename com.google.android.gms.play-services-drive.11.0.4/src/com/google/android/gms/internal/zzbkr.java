package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zze;
import com.google.android.gms.drive.events.zzp;
import com.google.android.gms.drive.events.zzt;

public final class zzbkr extends zza {
   public static final Creator CREATOR = new zzbks();
   final DriveId zzaLV;
   final int zzaJo;
   private zze zzaMR;
   private zzt zzaNr;
   private zzp zzaNs;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaLV, var2, false);
      zzd.zzc(var1, 3, this.zzaJo);
      zzd.zza(var1, 4, this.zzaMR, var2, false);
      zzd.zza(var1, 5, this.zzaNr, var2, false);
      zzd.zza(var1, 6, this.zzaNs, var2, false);
      zzd.zzI(var1, var5);
   }

   zzbkr(DriveId var1, int var2, zze var3, zzt var4, zzp var5) {
      this.zzaLV = var1;
      this.zzaJo = var2;
      this.zzaMR = var3;
      this.zzaNr = var4;
      this.zzaNs = var5;
   }

   public zzbkr(int var1, DriveId var2) {
      this((DriveId)zzbo.zzu(var2), 1, (zze)null, (zzt)null, (zzp)null);
   }
}
