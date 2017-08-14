package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzp;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbkw extends zza {
   public static final Creator CREATOR = new zzbkx();
   private DriveId zzaNt;
   private MetadataBundle zzaNu;
   private zzc zzaNv;
   private boolean zzaMs;
   private String zzaMr;
   private int zzaNw;
   private int zzaNx;
   private boolean zzaNy;
   private boolean zzaMw;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNt, var2, false);
      zzd.zza(var1, 3, this.zzaNu, var2, false);
      zzd.zza(var1, 4, this.zzaNv, var2, false);
      zzd.zza(var1, 5, this.zzaMs);
      zzd.zza(var1, 6, this.zzaMr, false);
      zzd.zzc(var1, 7, this.zzaNw);
      zzd.zzc(var1, 8, this.zzaNx);
      zzd.zza(var1, 9, this.zzaNy);
      zzd.zza(var1, 10, this.zzaMw);
      zzd.zzI(var1, var5);
   }

   zzbkw(DriveId var1, MetadataBundle var2, zzc var3, boolean var4, String var5, int var6, int var7, boolean var8, boolean var9) {
      this.zzaNt = var1;
      this.zzaNu = var2;
      this.zzaNv = var3;
      this.zzaMs = var4;
      this.zzaMr = var5;
      this.zzaNw = var6;
      this.zzaNx = var7;
      this.zzaNy = var8;
      this.zzaMw = var9;
   }

   public zzbkw(DriveId var1, MetadataBundle var2, int var3, boolean var4, zzp var5) {
      this(var1, var2, (zzc)null, var5.zzsQ(), var5.zzsP(), var5.zzsR(), var3, var4, var5.zzsV());
   }
}
