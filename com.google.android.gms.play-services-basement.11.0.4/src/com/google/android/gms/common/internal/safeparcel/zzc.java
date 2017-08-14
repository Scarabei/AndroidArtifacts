package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcel;

public final class zzc extends RuntimeException {
   public zzc(String var1, Parcel var2) {
      int var3 = var2.dataPosition();
      int var4 = var2.dataSize();
      super((new StringBuilder(41 + String.valueOf(var1).length())).append(var1).append(" Parcel: pos=").append(var3).append(" size=").append(var4).toString());
   }
}
