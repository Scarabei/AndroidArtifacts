package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Locale;

public final class zzp extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzq();
   private int zzaNm;

   public zzp(int var1) {
      this.zzaNm = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaNm);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return String.format(Locale.US, "TransferProgressOptions[type=%d]", this.zzaNm);
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzp var2 = (zzp)var1;
            return zzbe.equal(this.zzaNm, var2.zzaNm);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaNm});
   }
}
