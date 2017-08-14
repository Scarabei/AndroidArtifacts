package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzm;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzblf extends zza {
   public static final Creator CREATOR = new zzblg();
   private DriveId zzaNF;
   private MetadataBundle zzaND;
   private zzc zzaNv;
   private Integer zzaNE;
   private boolean zzaNG;
   private String zzaMr;
   private int zzaNH;
   private int zzaNI;
   private String zzaMu;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaNF, var2, false);
      zzd.zza(var1, 3, this.zzaND, var2, false);
      zzd.zza(var1, 4, this.zzaNv, var2, false);
      zzd.zza(var1, 5, this.zzaNE, false);
      zzd.zza(var1, 6, this.zzaNG);
      zzd.zza(var1, 7, this.zzaMr, false);
      zzd.zzc(var1, 8, this.zzaNH);
      zzd.zzc(var1, 9, this.zzaNI);
      zzd.zza(var1, 10, this.zzaMu, false);
      zzd.zzI(var1, var5);
   }

   zzblf(DriveId var1, MetadataBundle var2, zzc var3, Integer var4, boolean var5, String var6, int var7, int var8, String var9) {
      if (var3 != null && var8 != 0) {
         zzbo.zzb(var3.getRequestId() == var8, "inconsistent contents reference");
      }

      if ((var4 == null || var4.intValue() == 0) && var3 == null && var8 == 0) {
         throw new IllegalArgumentException("Need a valid contents");
      } else {
         this.zzaNF = (DriveId)zzbo.zzu(var1);
         this.zzaND = (MetadataBundle)zzbo.zzu(var2);
         this.zzaNv = var3;
         this.zzaNE = var4;
         this.zzaMr = var6;
         this.zzaNH = var7;
         this.zzaNG = var5;
         this.zzaNI = var8;
         this.zzaMu = var9;
      }
   }

   public zzblf(DriveId var1, MetadataBundle var2, int var3, int var4, zzm var5) {
      this(var1, var2, (zzc)null, var4, var5.zzsQ(), var5.zzsP(), var5.zzsR(), var3, var5.zzsT());
   }
}
