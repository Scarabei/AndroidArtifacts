package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcoa extends zza {
   public static final Creator CREATOR = new zzcob();
   private final String zzbxq;

   public zzcoa(String var1) {
      this.zzbxq = var1;
   }

   public final String zzzJ() {
      return this.zzbxq;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxq});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcoa) {
         zzcoa var2 = (zzcoa)var1;
         return zzbe.equal(this.zzbxq, var2.zzbxq);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbxq, false);
      zzd.zzI(var1, var5);
   }
}
