package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class PayloadTransferUpdate extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private final long zzbwz;
   private final int status;
   private final long zzbwA;
   private final long zzbwB;

   public PayloadTransferUpdate(long var1, int var3, long var4, long var6) {
      this.zzbwz = var1;
      this.status = var3;
      this.zzbwA = var4;
      this.zzbwB = var6;
   }

   public final long getPayloadId() {
      return this.zzbwz;
   }

   public final int getStatus() {
      return this.status;
   }

   public final long getTotalBytes() {
      return this.zzbwA;
   }

   public final long getBytesTransferred() {
      return this.zzbwB;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwz, this.status, this.zzbwA, this.zzbwB});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof PayloadTransferUpdate) {
         PayloadTransferUpdate var2 = (PayloadTransferUpdate)var1;
         return zzbe.equal(this.zzbwz, var2.zzbwz) && zzbe.equal(this.status, var2.status) && zzbe.equal(this.zzbwA, var2.zzbwA) && zzbe.equal(this.zzbwB, var2.zzbwB);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getPayloadId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getStatus());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getTotalBytes());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getBytesTransferred());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Status {
      int SUCCESS = 1;
      int FAILURE = 2;
      int IN_PROGRESS = 3;
   }
}
