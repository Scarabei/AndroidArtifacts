package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wearable.MessageEvent;

public final class zzdx extends com.google.android.gms.common.internal.safeparcel.zza implements MessageEvent {
   public static final Creator CREATOR = new zzdy();
   private final int zzaLT;
   private final String mPath;
   private final byte[] zzbdY;
   private final String zzaeK;

   public zzdx(int var1, String var2, byte[] var3, String var4) {
      this.zzaLT = var1;
      this.mPath = var2;
      this.zzbdY = var3;
      this.zzaeK = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getRequestId());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getPath(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getData(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getSourceNodeId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      int var1 = this.zzaLT;
      String var2 = this.mPath;
      String var3 = String.valueOf(this.zzbdY == null ? "null" : this.zzbdY.length);
      return (new StringBuilder(43 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("MessageEventParcelable[").append(var1).append(",").append(var2).append(", size=").append(var3).append("]").toString();
   }

   public final int getRequestId() {
      return this.zzaLT;
   }

   public final String getPath() {
      return this.mPath;
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public final String getSourceNodeId() {
      return this.zzaeK;
   }
}
