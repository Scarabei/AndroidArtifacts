package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class fy extends zza {
   public static final Creator CREATOR = new fz();
   private byte[] zzbPY;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzbPY, false);
      zzd.zzI(var1, var5);
   }

   public fy(byte[] var1) {
      this.zzbPY = var1;
   }

   fy() {
      this(new byte[0]);
   }
}
