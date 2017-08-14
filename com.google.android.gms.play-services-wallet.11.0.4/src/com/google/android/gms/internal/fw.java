package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class fw extends zza {
   public static final Creator CREATOR = new fx();
   private byte[] zzbPX;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzbPX, false);
      zzd.zzI(var1, var5);
   }

   public fw(byte[] var1) {
      this.zzbPX = var1;
   }

   fw() {
      this(new byte[0]);
   }
}
