package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.internal.zzbkp;
import java.util.Arrays;

public final class zzn extends com.google.android.gms.common.internal.safeparcel.zza implements DriveEvent {
   public static final Creator CREATOR = new zzo();
   private zzbkp zzaNl;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaNl, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzn(zzbkp var1) {
      this.zzaNl = var1;
   }

   public final int getType() {
      return 8;
   }

   public final zzbkp zzte() {
      return this.zzaNl;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzn var2 = (zzn)var1;
            return zzbe.equal(this.zzaNl, var2.zzaNl);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaNl});
   }

   public final String toString() {
      return String.format("TransferProgressEvent[%s]", this.zzaNl.toString());
   }
}
