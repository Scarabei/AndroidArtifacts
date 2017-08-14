package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.FileUploadPreferences;

public final class zzbog extends zza implements FileUploadPreferences {
   public static final Creator CREATOR = new zzboh();
   private int zzaOL;
   private int zzaOM;
   private boolean zzaON;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaOL);
      zzd.zzc(var1, 3, this.zzaOM);
      zzd.zza(var1, 4, this.zzaON);
      zzd.zzI(var1, var5);
   }

   public zzbog(int var1, int var2, boolean var3) {
      this.zzaOL = var1;
      this.zzaOM = var2;
      this.zzaON = var3;
   }

   public final int getNetworkTypePreference() {
      return !zzaP(this.zzaOL) ? 0 : this.zzaOL;
   }

   public final void setNetworkTypePreference(int var1) {
      if (!zzaP(var1)) {
         throw new IllegalArgumentException("Invalid data connection preference value.");
      } else {
         this.zzaOL = var1;
      }
   }

   public final int getBatteryUsagePreference() {
      return !zzaQ(this.zzaOM) ? 0 : this.zzaOM;
   }

   public final void setBatteryUsagePreference(int var1) {
      if (!zzaQ(var1)) {
         throw new IllegalArgumentException("Invalid battery usage preference value.");
      } else {
         this.zzaOM = var1;
      }
   }

   public final boolean isRoamingAllowed() {
      return this.zzaON;
   }

   public final void setRoamingAllowed(boolean var1) {
      this.zzaON = var1;
   }

   private static boolean zzaP(int var0) {
      switch(var0) {
      case 1:
      case 2:
         return true;
      default:
         return false;
      }
   }

   private static boolean zzaQ(int var0) {
      switch(var0) {
      case 256:
      case 257:
         return true;
      default:
         return false;
      }
   }
}
