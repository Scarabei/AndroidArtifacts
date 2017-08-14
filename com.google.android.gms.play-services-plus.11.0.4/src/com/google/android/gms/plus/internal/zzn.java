package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzn extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzo();
   private final int zzaku;
   private final String zzakh;
   private final String[] zzbAH;
   private final String[] zzbAI;
   private final String[] zzbAJ;
   private final String zzbAK;
   private final String zzbAL;
   private final String zzaxq;
   private final String zzbAM;
   private final PlusCommonExtras zzbAN;

   zzn(int var1, String var2, String[] var3, String[] var4, String[] var5, String var6, String var7, String var8, String var9, PlusCommonExtras var10) {
      this.zzaku = var1;
      this.zzakh = var2;
      this.zzbAH = var3;
      this.zzbAI = var4;
      this.zzbAJ = var5;
      this.zzbAK = var6;
      this.zzbAL = var7;
      this.zzaxq = var8;
      this.zzbAM = var9;
      this.zzbAN = var10;
   }

   public zzn(String var1, String[] var2, String[] var3, String[] var4, String var5, String var6, String var7, PlusCommonExtras var8) {
      this.zzaku = 1;
      this.zzakh = var1;
      this.zzbAH = var2;
      this.zzbAI = var3;
      this.zzbAJ = var4;
      this.zzbAK = var5;
      this.zzbAL = var6;
      this.zzaxq = null;
      this.zzbAM = null;
      this.zzbAN = var8;
   }

   public final String[] zzAg() {
      return this.zzbAI;
   }

   public final String zzAh() {
      return this.zzbAK;
   }

   public final Bundle zzAi() {
      Bundle var1;
      (var1 = new Bundle()).setClassLoader(PlusCommonExtras.class.getClassLoader());
      PlusCommonExtras var2 = this.zzbAN;
      var1.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", com.google.android.gms.common.internal.safeparcel.zze.zza(var2));
      return var1;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaku, this.zzakh, this.zzbAH, this.zzbAI, this.zzbAJ, this.zzbAK, this.zzbAL, this.zzaxq, this.zzbAM, this.zzbAN});
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("versionCode", this.zzaku).zzg("accountName", this.zzakh).zzg("requestedScopes", this.zzbAH).zzg("visibleActivities", this.zzbAI).zzg("requiredFeatures", this.zzbAJ).zzg("packageNameForAuth", this.zzbAK).zzg("callingPackageName", this.zzbAL).zzg("applicationName", this.zzaxq).zzg("extra", this.zzbAN.toString()).toString();
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzn)) {
         return false;
      } else {
         zzn var2 = (zzn)var1;
         return this.zzaku == var2.zzaku && zzbe.equal(this.zzakh, var2.zzakh) && Arrays.equals(this.zzbAH, var2.zzbAH) && Arrays.equals(this.zzbAI, var2.zzbAI) && Arrays.equals(this.zzbAJ, var2.zzbAJ) && zzbe.equal(this.zzbAK, var2.zzbAK) && zzbe.equal(this.zzbAL, var2.zzbAL) && zzbe.equal(this.zzaxq, var2.zzaxq) && zzbe.equal(this.zzbAM, var2.zzbAM) && zzbe.equal(this.zzbAN, var2.zzbAN);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbAH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbAI, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbAJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbAK, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbAL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaxq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbAM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbAN, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
