package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   public int mode;
   public int zzbNG;
   public int zzbNH;
   public boolean zzbNI;
   public boolean zzbNJ;
   public float zzbNK;

   public zzc() {
   }

   public zzc(int var1, int var2, int var3, boolean var4, boolean var5, float var6) {
      this.mode = var1;
      this.zzbNG = var2;
      this.zzbNH = var3;
      this.zzbNI = var4;
      this.zzbNJ = var5;
      this.zzbNK = var6;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.mode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbNG);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbNH);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbNI);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbNJ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbNK);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
