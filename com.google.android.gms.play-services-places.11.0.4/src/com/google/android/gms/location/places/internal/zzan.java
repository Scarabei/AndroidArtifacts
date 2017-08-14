package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Collections;
import java.util.List;

public final class zzan extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzf();
   private int zzbkR;
   private int zzbkS;
   private int zzbkT;
   private int zzbkU;
   private int zzbkV;
   private int zzbkW;
   private List zzbkX;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbkR);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbkS);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbkT);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbkU);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbkV);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzbkW);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzbkX, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zzan(int var1, int var2, int var3, int var4, int var5, int var6, List var7) {
      this.zzbkR = var1;
      this.zzbkS = var2;
      this.zzbkT = var3;
      this.zzbkU = var4;
      this.zzbkV = var5;
      this.zzbkW = var6;
      this.zzbkX = Collections.unmodifiableList(var7);
   }
}
