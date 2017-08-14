package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

/** @deprecated */
@Deprecated
public final class zzcdw extends zza {
   public static final Creator CREATOR = new zzcdx();
   private static zzcdw zzbli = new zzcdw("Home");
   private static zzcdw zzblj = new zzcdw("Work");
   private final String zzblk;

   zzcdw(String var1) {
      this.zzblk = var1;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzblk});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzcdw)) {
         return false;
      } else {
         zzcdw var2 = (zzcdw)var1;
         return zzbe.equal(this.zzblk, var2.zzblk);
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("alias", this.zzblk).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzblk, false);
      zzd.zzI(var1, var5);
   }
}
