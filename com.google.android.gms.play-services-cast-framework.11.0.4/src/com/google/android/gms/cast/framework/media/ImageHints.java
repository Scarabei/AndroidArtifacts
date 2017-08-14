package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ImageHints extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzf();
   private final int zzamr;
   private final int zzatI;
   private final int zzatJ;

   public ImageHints(int var1, int var2, int var3) {
      this.zzamr = var1;
      this.zzatI = var2;
      this.zzatJ = var3;
   }

   public int getType() {
      return this.zzamr;
   }

   public int getWidthInPixels() {
      return this.zzatI;
   }

   public int getHeightInPixels() {
      return this.zzatJ;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getWidthInPixels());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getHeightInPixels());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
