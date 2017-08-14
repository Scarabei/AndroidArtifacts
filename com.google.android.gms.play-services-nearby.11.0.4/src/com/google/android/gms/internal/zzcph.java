package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcph extends zza {
   private final int zzaku;
   private final ParcelUuid zzbyD;
   private final ParcelUuid zzbyE;
   private final ParcelUuid zzbyF;
   private final byte[] zzbyG;
   private final byte[] zzbyH;
   private final int zzbyI;
   private final byte[] zzbyJ;
   private final byte[] zzbyK;
   public static final Creator CREATOR = new zzcpi();

   zzcph(int var1, ParcelUuid var2, ParcelUuid var3, ParcelUuid var4, byte[] var5, byte[] var6, int var7, byte[] var8, byte[] var9) {
      this.zzaku = var1;
      this.zzbyD = var2;
      this.zzbyE = var3;
      this.zzbyF = var4;
      this.zzbyG = var5;
      this.zzbyH = var6;
      this.zzbyI = var7;
      this.zzbyJ = var8;
      this.zzbyK = var9;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 4, this.zzbyD, var2, false);
      zzd.zza(var1, 5, this.zzbyE, var2, false);
      zzd.zza(var1, 6, this.zzbyF, var2, false);
      zzd.zza(var1, 7, this.zzbyG, false);
      zzd.zza(var1, 8, this.zzbyH, false);
      zzd.zzc(var1, 9, this.zzbyI);
      zzd.zza(var1, 10, this.zzbyJ, false);
      zzd.zza(var1, 11, this.zzbyK, false);
      zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbyI, Arrays.hashCode(this.zzbyJ), Arrays.hashCode(this.zzbyK), this.zzbyF, Arrays.hashCode(this.zzbyG), Arrays.hashCode(this.zzbyH), this.zzbyD, this.zzbyE});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzcph var2 = (zzcph)var1;
         return this.zzbyI == var2.zzbyI && Arrays.equals(this.zzbyJ, var2.zzbyJ) && Arrays.equals(this.zzbyK, var2.zzbyK) && zzbe.equal(this.zzbyF, var2.zzbyF) && Arrays.equals(this.zzbyG, var2.zzbyG) && Arrays.equals(this.zzbyH, var2.zzbyH) && zzbe.equal(this.zzbyD, var2.zzbyD) && zzbe.equal(this.zzbyE, var2.zzbyE);
      } else {
         return false;
      }
   }
}
