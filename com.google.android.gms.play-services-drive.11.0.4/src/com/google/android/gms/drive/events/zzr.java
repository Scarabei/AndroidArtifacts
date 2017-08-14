package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.List;

public final class zzr extends com.google.android.gms.common.internal.safeparcel.zza implements DriveEvent {
   public static final Creator CREATOR = new zzs();
   private String zzakh;
   private List zzaNn;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaNn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zzr(String var1, List var2) {
      this.zzakh = var1;
      this.zzaNn = var2;
   }

   public final int getType() {
      return 7;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzr var2 = (zzr)var1;
            return zzbe.equal(this.zzakh, var2.zzakh) && zzbe.equal(this.zzaNn, var2.zzaNn);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzakh, this.zzaNn});
   }

   public final String toString() {
      return String.format("TransferStateEvent[%s]", TextUtils.join("','", this.zzaNn));
   }
}
