package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcok extends zza {
   public static final Creator CREATOR = new zzcol();
   private final int zzbxu;

   public zzcok(int var1) {
      this.zzbxu = var1;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxu});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcok) {
         zzcok var2 = (zzcok)var1;
         return zzbe.equal(this.zzbxu, var2.zzbxu);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzbxu);
      zzd.zzI(var1, var5);
   }
}
