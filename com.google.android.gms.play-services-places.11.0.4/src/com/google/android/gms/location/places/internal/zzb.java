package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzb extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzaw();
   final int mOffset;
   final int mLength;

   public zzb(int var1, int var2) {
      this.mOffset = var1;
      this.mLength = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.mOffset);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.mLength);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("offset", this.mOffset).zzg("length", this.mLength).toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mOffset, this.mLength});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzb)) {
         return false;
      } else {
         zzb var2 = (zzb)var1;
         return zzbe.equal(this.mOffset, var2.mOffset) && zzbe.equal(this.mLength, var2.mLength);
      }
   }
}
