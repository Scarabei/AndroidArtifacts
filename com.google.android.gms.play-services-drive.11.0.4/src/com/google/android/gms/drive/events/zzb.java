package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Locale;

public final class zzb extends com.google.android.gms.common.internal.safeparcel.zza implements DriveEvent {
   public static final Creator CREATOR = new zzc();
   private String zzakh;
   private zze zzaMR;

   public zzb(String var1, zze var2) {
      this.zzakh = var1;
      this.zzaMR = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaMR, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int getType() {
      return 4;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzb var2 = (zzb)var1;
            return zzbe.equal(this.zzaMR, var2.zzaMR) && zzbe.equal(this.zzakh, var2.zzakh);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaMR, this.zzakh});
   }

   public final String toString() {
      return String.format(Locale.US, "ChangesAvailableEvent [changesAvailableOptions=%s]", this.zzaMR);
   }
}
