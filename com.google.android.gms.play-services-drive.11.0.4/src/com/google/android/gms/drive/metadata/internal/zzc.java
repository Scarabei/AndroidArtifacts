package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Arrays;

public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   final CustomPropertyKey zzaPJ;
   final String mValue;

   public zzc(CustomPropertyKey var1, String var2) {
      zzbo.zzb(var1, "key");
      this.zzaPJ = var1;
      this.mValue = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaPJ, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.mValue, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaPJ, this.mValue});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && var1.getClass() == this.getClass()) {
         zzc var2 = (zzc)var1;
         return zzbe.equal(this.zzaPJ, var2.zzaPJ) && zzbe.equal(this.mValue, var2.mValue);
      } else {
         return false;
      }
   }
}
