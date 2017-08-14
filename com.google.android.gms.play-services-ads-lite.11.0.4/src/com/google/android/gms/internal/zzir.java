package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;
import java.util.List;

@zzzn
public final class zzir extends zza {
   public static final Creator CREATOR = new zzit();
   public final int versionCode;
   public final long zzzN;
   public final Bundle extras;
   public final int zzzO;
   public final List zzzP;
   public final boolean zzzQ;
   public final int zzzR;
   public final boolean zzzS;
   public final String zzzT;
   public final zzlt zzzU;
   public final Location zzzV;
   public final String zzzW;
   public final Bundle zzzX;
   public final Bundle zzzY;
   public final List zzzZ;
   public final String zzAa;
   public final String zzAb;
   public final boolean zzAc;

   public zzir(int var1, long var2, Bundle var4, int var5, List var6, boolean var7, int var8, boolean var9, String var10, zzlt var11, Location var12, String var13, Bundle var14, Bundle var15, List var16, String var17, String var18, boolean var19) {
      this.versionCode = var1;
      this.zzzN = var2;
      this.extras = var4 == null ? new Bundle() : var4;
      this.zzzO = var5;
      this.zzzP = var6;
      this.zzzQ = var7;
      this.zzzR = var8;
      this.zzzS = var9;
      this.zzzT = var10;
      this.zzzU = var11;
      this.zzzV = var12;
      this.zzzW = var13;
      this.zzzX = var14 == null ? new Bundle() : var14;
      this.zzzY = var15;
      this.zzzZ = var16;
      this.zzAa = var17;
      this.zzAb = var18;
      this.zzAc = var19;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzzN);
      zzd.zza(var1, 3, this.extras, false);
      zzd.zzc(var1, 4, this.zzzO);
      zzd.zzb(var1, 5, this.zzzP, false);
      zzd.zza(var1, 6, this.zzzQ);
      zzd.zzc(var1, 7, this.zzzR);
      zzd.zza(var1, 8, this.zzzS);
      zzd.zza(var1, 9, this.zzzT, false);
      zzd.zza(var1, 10, this.zzzU, var2, false);
      zzd.zza(var1, 11, this.zzzV, var2, false);
      zzd.zza(var1, 12, this.zzzW, false);
      zzd.zza(var1, 13, this.zzzX, false);
      zzd.zza(var1, 14, this.zzzY, false);
      zzd.zzb(var1, 15, this.zzzZ, false);
      zzd.zza(var1, 16, this.zzAa, false);
      zzd.zza(var1, 17, this.zzAb, false);
      zzd.zza(var1, 18, this.zzAc);
      zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzir)) {
         return false;
      } else {
         zzir var2 = (zzir)var1;
         return this.versionCode == var2.versionCode && this.zzzN == var2.zzzN && zzbe.equal(this.extras, var2.extras) && this.zzzO == var2.zzzO && zzbe.equal(this.zzzP, var2.zzzP) && this.zzzQ == var2.zzzQ && this.zzzR == var2.zzzR && this.zzzS == var2.zzzS && zzbe.equal(this.zzzT, var2.zzzT) && zzbe.equal(this.zzzU, var2.zzzU) && zzbe.equal(this.zzzV, var2.zzzV) && zzbe.equal(this.zzzW, var2.zzzW) && zzbe.equal(this.zzzX, var2.zzzX) && zzbe.equal(this.zzzY, var2.zzzY) && zzbe.equal(this.zzzZ, var2.zzzZ) && zzbe.equal(this.zzAa, var2.zzAa) && zzbe.equal(this.zzAb, var2.zzAb) && this.zzAc == var2.zzAc;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.versionCode, this.zzzN, this.extras, this.zzzO, this.zzzP, this.zzzQ, this.zzzR, this.zzzS, this.zzzT, this.zzzU, this.zzzV, this.zzzW, this.zzzX, this.zzzY, this.zzzZ, this.zzAa, this.zzAb, this.zzAc});
   }

   public static void zzh(zzir var0) {
      var0.zzzX.putBundle("com.google.ads.mediation.admob.AdMobAdapter", var0.extras);
   }
}
