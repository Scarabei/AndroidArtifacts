package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzaxq extends zza {
   public static final Creator CREATOR = new zzaxr();
   private String zzaxF;

   zzaxq(String var1) {
      this.zzaxF = var1;
   }

   public zzaxq() {
      this((String)null);
   }

   public final String zzoy() {
      return this.zzaxF;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaxF, false);
      zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzaxq)) {
         return false;
      } else {
         zzaxq var2 = (zzaxq)var1;
         return zzaye.zza(this.zzaxF, var2.zzaxF);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaxF});
   }
}
