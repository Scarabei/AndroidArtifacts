package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/** @deprecated */
@Deprecated
public final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private String name;
   private String zzbgE;
   private String zzbgF;
   private String zzbgG;
   private String zzVJ;
   private String zzbOk;
   private String zzbOl;
   private String zzbgL;
   private String phoneNumber;
   private boolean zzbgN;
   private String zzbgO;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.name, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbgE, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbgF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbgG, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzVJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbOl, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbgL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.phoneNumber, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbgN);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbgO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zza() {
   }

   zza(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, boolean var10, String var11) {
      this.name = var1;
      this.zzbgE = var2;
      this.zzbgF = var3;
      this.zzbgG = var4;
      this.zzVJ = var5;
      this.zzbOk = var6;
      this.zzbOl = var7;
      this.zzbgL = var8;
      this.phoneNumber = var9;
      this.zzbgN = var10;
      this.zzbgO = var11;
   }
}
