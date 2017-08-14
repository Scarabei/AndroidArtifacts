package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class zzt extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzu();
   private List zzaMU;
   private final Set zzaMV;

   zzt(List var1) {
      this(var1, var1 == null ? null : new HashSet(var1));
   }

   private zzt(List var1, Set var2) {
      this.zzaMU = var1;
      this.zzaMV = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaMU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return String.format(Locale.US, "TransferStateOptions[Spaces=%s]", this.zzaMU);
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzt var2 = (zzt)var1;
            return zzbe.equal(this.zzaMV, var2.zzaMV);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaMV});
   }
}
