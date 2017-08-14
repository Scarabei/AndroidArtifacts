package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzs extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzt();
   private String zzaMF;
   private int zzaMG;
   private String zzaMH;
   private String zzaMI;
   private int zzaMJ;
   private boolean zzaMK;

   public zzs(String var1, int var2, String var3, String var4, int var5, boolean var6) {
      this.zzaMF = var1;
      this.zzaMG = var2;
      this.zzaMH = var3;
      this.zzaMI = var4;
      this.zzaMJ = var5;
      this.zzaMK = var6;
   }

   private static boolean zzaL(int var0) {
      switch(var0) {
      case 256:
      case 257:
      case 258:
         return true;
      default:
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, !zzaL(this.zzaMG) ? null : this.zzaMF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, !zzaL(this.zzaMG) ? -1 : this.zzaMG);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaMH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaMI, false);
      boolean var10002;
      switch(this.zzaMJ) {
      case 0:
      case 1:
      case 2:
      case 3:
         var10002 = true;
         break;
      default:
         var10002 = false;
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, !var10002 ? -1 : this.zzaMJ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaMK);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaMF, this.zzaMG, this.zzaMJ, this.zzaMK});
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzs var2 = (zzs)var1;
            return zzbe.equal(this.zzaMF, var2.zzaMF) && this.zzaMG == var2.zzaMG && this.zzaMJ == var2.zzaMJ && this.zzaMK == var2.zzaMK;
         }
      } else {
         return false;
      }
   }
}
