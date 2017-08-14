package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class fk extends zza {
   public static final Creator CREATOR = new fl();
   public final ft[] zzbNX;
   public final fe zzbNY;
   private fe zzbNZ;
   private fe zzbOa;
   public final String zzbOb;
   private float zzbOc;
   public final String zzbNS;
   private int zzbOd;
   public final boolean zzbOe;
   public final int zzbOf;
   public final int zzbOg;

   public fk(ft[] var1, fe var2, fe var3, fe var4, String var5, float var6, String var7, int var8, boolean var9, int var10, int var11) {
      this.zzbNX = var1;
      this.zzbNY = var2;
      this.zzbNZ = var3;
      this.zzbOa = var4;
      this.zzbOb = var5;
      this.zzbOc = var6;
      this.zzbNS = var7;
      this.zzbOd = var8;
      this.zzbOe = var9;
      this.zzbOf = var10;
      this.zzbOg = var11;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzbNX, var2, false);
      zzd.zza(var1, 3, this.zzbNY, var2, false);
      zzd.zza(var1, 4, this.zzbNZ, var2, false);
      zzd.zza(var1, 5, this.zzbOa, var2, false);
      zzd.zza(var1, 6, this.zzbOb, false);
      zzd.zza(var1, 7, this.zzbOc);
      zzd.zza(var1, 8, this.zzbNS, false);
      zzd.zzc(var1, 9, this.zzbOd);
      zzd.zza(var1, 10, this.zzbOe);
      zzd.zzc(var1, 11, this.zzbOf);
      zzd.zzc(var1, 12, this.zzbOg);
      zzd.zzI(var1, var5);
   }
}
